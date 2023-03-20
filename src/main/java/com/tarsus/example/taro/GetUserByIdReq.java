
package com.tarsus.example.taro;
import com.tarsus.example.decorator.TaroStruct;
import java.util.List;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

import java.util.HashMap;

 import com.tarsus.example.taro.Basic;


@TaroStruct
public class GetUserByIdReq{
  public Integer id;
  public Basic basic;
  

  // ListConstructor
  public GetUserByIdReq(List<Object> list){
    this.id = Integer.valueOf((String) list.get(0));
  this.basic = new Basic((List<Object>)list.get(1));
    
  }

  // NoArgsConstructor
  public GetUserByIdReq(){

  }
}
  