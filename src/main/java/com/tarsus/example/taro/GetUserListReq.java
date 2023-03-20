
package com.tarsus.example.taro;
import com.tarsus.example.decorator.TaroStruct;
import java.util.List;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

import java.util.HashMap;

 import com.tarsus.example.taro.Basic;


@TaroStruct
public class GetUserListReq{
  public Basic basic;
  public List<Integer> ids;
  

  // ListConstructor
  public GetUserListReq(List<Object> list){
    this.basic = new Basic((List<Object>)list.get(0));
  this.ids = JSON.parseArray((String) list.get(1),Integer.class);
    
  }

  // NoArgsConstructor
  public GetUserListReq(){

  }
}
  