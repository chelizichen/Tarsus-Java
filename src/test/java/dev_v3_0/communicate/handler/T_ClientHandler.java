package dev_v3_0.communicate.handler;

import dev_v3_0.category.*;
import dev_v3_0.stream.T_RStream;
import dev_v3_0.stream.T_WStream;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

// 处理客户端的链接类
public class T_ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final OutputStream outputStream;

    @Setter
    @Getter
    private ByteBuffer WholeBuffer = null;

    @Setter
    @Getter
    private Integer WholeLength = 0;

    public SocketChannel socketChannel;

    public T_ClientHandler(Socket clientSocket) throws IOException {
        System.out.println("有新的链接进入");
        this.clientSocket = clientSocket;
        this.socketChannel = clientSocket.getChannel();
        this.outputStream = clientSocket.getOutputStream();
//        this.run();
    }

    @Override
    public void run() {
        // 处理客户端请求的代码
        try {
            // 读取Buffer，反序列化然后INVOKE
            InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            BufferReaderToStream(reader);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 处理客户端请求的代码，例如读取输入内容并输出到客户端等操作。
    }

    public ByteBuffer ReadyToRead(ByteBuffer buffer) throws Exception {
        T_RStream rs = new T_RStream(buffer);
        T_INT32 ByteLength = rs.ReadInt32(0);
        T_String ModuleName = rs.ReadString(1);
        T_String InvokeMethod = rs.ReadString(2);
        T_String InvokeRequest = rs.ReadString(3);
        T_Vector<T_String> TraceId = rs.ReadVector(4, T_String.class);
        T_JceStruct RequestStruct = T_Container.JCE_STRUCT.get(InvokeRequest.GetValue());
        T_Base InvokeRequestBody = rs.ReadStruct(5, RequestStruct.Base, RequestStruct.Read);
        T_JceStruct ResponseStruct = T_RPC.METHODS.get(InvokeMethod.GetValue()).get(T_RPC.Handlers.Res);
        T_String InvokeResponse = new T_String(ResponseStruct._t_className);
        T_RPC.T_Context Context = new T_RPC.T_Context(ByteLength, ModuleName, InvokeMethod, InvokeRequest, InvokeResponse, TraceId);
        Method getModuleMethod = T_RPC.GetModuleMethod(ModuleName.GetValue(), InvokeMethod.GetValue(), RequestStruct.Base);
        T_Base invoke = (T_Base) getModuleMethod.invoke(T_RPC.GetModule(ModuleName.GetValue()), Context, InvokeRequestBody);
        T_WStream ws = ReadyToWrite(invoke, Context);
        System.out.println("ws.position" + ws.position);
        return ws.toBuf();
    }

    public void BufferReaderToStream(BufferedReader reader) throws Exception {
        String streams = "";
        StringBuilder stringBuffer = new StringBuilder();
        while ((streams = reader.readLine()) != null) {
            stringBuffer.append(streams);
            {
                byte[] bytes = stringBuffer.toString().getBytes();
                ByteBuffer wrap = ByteBuffer.wrap(bytes);
                this.HandleBufferInvoke(wrap, bytes);
                stringBuffer.delete(0, stringBuffer.length()); // 每次清空
            }
        }
    }

    public void HandleBufferInvoke(ByteBuffer wrap, byte[] bytes) throws Exception {
        if (getWholeLength() == 0) { // 重制状态,有完整包
            int ByteLength = wrap.getInt(0);
            if (ByteLength + 4 <= bytes.length) { // 一次性发了多个包，需要拆分
                ByteBuffer buffer1 = ByteBuffer.wrap(bytes, 0, ByteLength);
                ByteBuffer ResponseBuffer = ReadyToRead(buffer1);
                this.outputStream.write(ResponseBuffer.array());
                this.outputStream.flush();
                // 拆分包 然后继续调用
                if (ByteLength + 4 != bytes.length) {
                    ByteBuffer wrap1 = ByteBuffer.wrap(bytes, ByteLength + 4, bytes.length);
                    this.HandleBufferInvoke(wrap1, wrap1.array());
                    return;
                }
                return;
            }
            if (ByteLength + 4 > bytes.length) { // 给的不够 需要存起来
                setWholeLength(ByteLength);
                setWholeBuffer(wrap);
            }
        } else { // 不是完整包，先将bytes添加入临时变量里
            ByteBuffer put = getWholeBuffer().put(bytes);
            int length = put.array().length; // 加入之后的长度
            if (length >= getWholeLength()) { // 如果大于给定的，则代表是个完整包，重置后走Invoke逻辑
                this.Reset();
                this.HandleBufferInvoke(put, put.array());
            } else {
                setWholeBuffer(put);
            }
        }

    }


    public T_WStream ReadyToWrite(T_Base response, T_RPC.T_Context Context) throws Exception {
        T_WStream ws = new T_WStream();
        ws.WriteString(0, Context.ModuleName.GetValue());
        ws.WriteString(1, Context.InvokeMethod.GetValue());
        ws.WriteString(2, Context.InvokeResponse.GetValue());
        ws.WriteVector(3, Context.TraceId);
        T_JceStruct ResponseStruct = T_Container.JCE_STRUCT.get(Context.InvokeResponse.GetValue());
        ws.WriteStruct(4, response, ResponseStruct.Write);
        T_WStream resp = new T_WStream();
        resp.WriteInt32(0, ws.position);
        resp.WriteBuf(-1, ws.originBuf.array(), ws.position);
        return resp;
    }

    public void Reset() {
        setWholeBuffer(null); // 标记不可达，gc
        setWholeLength(0);
    }

}