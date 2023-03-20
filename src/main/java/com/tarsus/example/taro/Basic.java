
package com.tarsus.example.taro;
import com.tarsus.example.decorator.TaroStruct;
import java.util.List;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

import java.util.HashMap;



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
}
  