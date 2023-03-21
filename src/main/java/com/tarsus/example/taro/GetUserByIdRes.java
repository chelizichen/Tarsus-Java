
package com.tarsus.example.taro;
import com.tarsus.example.decorator.TaroStruct;
import java.util.List;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
 import com.tarsus.example.taro.User;


@TaroStruct
public class GetUserByIdRes{
  public Integer code;
  public User data;
  public String message;
  

  // ListConstructor
  public GetUserByIdRes(List<Object> list){
    this.code = Integer.valueOf((String) list.get(0));
  this.data = new User((List<Object>)list.get(1));
  this.message = (String) list.get(2);
    
  }

  // NoArgsConstructor
  public GetUserByIdRes(){

  }
  // toJson
  public String json(){
    Object o = JSONObject.toJSON(this);
    return o.toString();
  }
}
  