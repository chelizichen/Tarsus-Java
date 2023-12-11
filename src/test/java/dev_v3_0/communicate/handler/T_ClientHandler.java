package dev_v3_0.communicate.handler;

import dev_v3_0.category.*;
import dev_v3_0.stream.T_RStream;
import dev_v3_0.stream.T_WStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

// 处理客户端的链接类
public class T_ClientHandler implements Runnable {
    private final Socket clientSocket;

    public T_ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        // 处理客户端请求的代码
        try {
            // 读取Buffer，反序列化然后INVOKE
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
            ByteBuffer RequestBuffer = BufferReaderToStream(reader);
            ByteBuffer ResponseBuffer = ReadyToRead(RequestBuffer);
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.write(ResponseBuffer.asCharBuffer().array());
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
        T_RPC.T_Context Context = new T_RPC.T_Context(ByteLength, ModuleName, InvokeMethod, InvokeRequest, new T_String(ResponseStruct._t_className), TraceId);
        Method getModuleMethod = T_RPC.GetModuleMethod(ModuleName.GetValue(), InvokeMethod.GetValue());
        T_Base invoke = (T_Base) getModuleMethod.invoke(T_RPC.GetModule(ModuleName.GetValue()), Context, InvokeRequestBody);
        T_WStream ws = ReadyToWrite(invoke, Context);
        return ws.originBuf;
    }

    public ByteBuffer BufferReaderToStream(BufferedReader reader) throws IOException {
        String streams = "";
        StringBuilder stringBuffer = new StringBuilder();
        while ((streams = reader.readLine()) != null) {
            stringBuffer.append(streams);
        }
        byte[] bytes = stringBuffer.toString().getBytes();
        return ByteBuffer.wrap(bytes);
    }

    public T_WStream ReadyToWrite(T_Base response, T_RPC.T_Context Context) throws Exception {
        T_WStream ws = new T_WStream();
        ws.WriteString(0, Context.ModuleName.GetValue());
        ws.WriteString(1, Context.InvokeMethod.GetValue());
        ws.WriteString(2, Context.InvokeRequest.GetValue());
        ws.WriteVector(3, Context.TraceId);
        T_JceStruct ResponseStruct = T_Container.JCE_STRUCT.get(Context.InvokeResponse.GetValue());
        ws.WriteStruct(4, response, ResponseStruct.Write);
        return ws;
    }
}