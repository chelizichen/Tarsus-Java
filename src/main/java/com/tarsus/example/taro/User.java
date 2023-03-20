
package com.tarsus.example.taro;
import com.tarsus.example.decorator.TaroStruct;
import java.util.List;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

import java.util.HashMap;



@TaroStruct
public class User{
  public String id;
  public String name;
  public String age;
  public String fullName;
  public String address;
  

  // ListConstructor
  public User(List<Object> list){
    this.id = (String) list.get(0);
  this.name = (String) list.get(1);
  this.age = (String) list.get(2);
  this.fullName = (String) list.get(3);
  this.address = (String) list.get(4);
    
  }

  // NoArgsConstructor
  public User(){

  }
}
  