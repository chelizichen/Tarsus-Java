
package com.tarsus.example.TEST.taro;
import com.tarsus.example.decorator.TarsusParam;
import java.util.List;

import com.alibaba.fastjson.JSON;

 import com.tarsus.example.TEST.taro.User;

@TarsusParam
public class GetUserByIdRes{
  public Integer code;
    public String message;
    public User data;
    
  public GetUserByIdRes(List<String> list){
    this.code = Integer.valueOf(list.get(0));
  this.message = list.get(1);
  this.data = new User(JSON.parseArray(list.get(2),String.class));
    
  }
}
  