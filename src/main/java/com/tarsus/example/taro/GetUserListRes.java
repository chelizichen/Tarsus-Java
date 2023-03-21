
package com.tarsus.example.taro;
import com.tarsus.example.decorator.TaroStruct;
import java.util.List;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
 import com.tarsus.example.taro.User;


@TaroStruct
public class GetUserListRes{
  public Integer code;
  public String message;
  public List<User> data;
  

  // ListConstructor
  public GetUserListRes(List<Object> list){
    this.code = Integer.valueOf((String) list.get(0));
  this.message = (String) list.get(1);
  
      List<HashMap> listMaps2 = JSON.parseArray((String) list.get(2), HashMap.class);
      this.data = new ArrayList<>();
      for(HashMap hm : listMaps2){
        User user = new User();
        user.id = (String) hm.get("id");
        user.name = (String) hm.get("name");
        user.age = (String) hm.get("age");
        user.fullName = (String) hm.get("fullName");
        user.address = (String) hm.get("address");
        this.data.add(user);
      }
        
  }

  // NoArgsConstructor
  public GetUserListRes(){

  }
  // toJson
  public String json(){
    Object o = JSONObject.toJSON(this);
    return o.toString();
  }
}
  