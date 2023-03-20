
package com.tarsus.example.TEST.taro;
import com.tarsus.example.decorator.TarsusParam;
import java.util.List;

import com.alibaba.fastjson.JSON;

 import com.tarsus.example.TEST.taro.User;

@TarsusParam
public class GetUserListRes{
  public Integer code;
    public String message;
    public List<User> data;
    
  public GetUserListRes(List<String> list){
    this.code = Integer.valueOf(list.get(0));
  this.message = list.get(1);
  this.data = JSON.parseArray(list.get(2),User.class);
    
  }
}
  