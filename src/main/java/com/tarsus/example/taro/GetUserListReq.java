
package com.tarsus.example.taro;
import com.tarsus.example.decorator.TaroStruct;
import java.util.List;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
 import com.tarsus.example.taro.Basic;


@TaroStruct
public class GetUserListReq{
  public String sa;
  public Basic basic;
  public List<Integer> ids;


  // ListConstructor
  public GetUserListReq(List<Object> list){
    this.sa = (String) list.get(0);
    System.out.println("走到这一步1");
    this.basic = new Basic((List<Object>)list.get(1));
    System.out.println("走到这一步2");
    this.ids = JSON.parseArray((String) list.get(2),Integer.class);
  }

  // NoArgsConstructor
  public GetUserListReq(){

  }
  // toJson
  public String json(){
    Object o = JSONObject.toJSON(this);
    return o.toString();
  }
}
