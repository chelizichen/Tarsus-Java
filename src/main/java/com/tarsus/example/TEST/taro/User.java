
package com.tarsus.example.TEST.taro;
import com.tarsus.example.decorator.TarsusParam;
import java.util.List;

import com.alibaba.fastjson.JSON;


@TarsusParam
public class User{
  public String id;
    public String name;
    public String age;
    public String fullName;
    public String address;
    
  public User(List<String> list){
    this.id = list.get(0);
  this.name = list.get(1);
  this.age = list.get(2);
  this.fullName = list.get(3);
  this.address = list.get(4);
    
  }
}
  