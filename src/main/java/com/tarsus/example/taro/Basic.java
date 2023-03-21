
package com.tarsus.example.taro;
import com.tarsus.example.decorator.TaroStruct;
import java.util.List;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;


@TaroStruct
public class Basic{
  public String token;
  

  // ListConstructor
  public Basic(List<Object> list){
    this.token = (String) list.get(0);
    
  }

  // NoArgsConstructor
  public Basic(){

  }
  // toJson
  public String json(){
    Object o = JSONObject.toJSON(this);
    return o.toString();
  }
}
  