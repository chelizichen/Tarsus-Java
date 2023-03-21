 package com.tarsus.example.taro;

 import com.tarsus.example.decorator.TarsusInterFace;
 import com.tarsus.example.decorator.TarsusMethod;

public interface  TaroInterFace {
  public int getUserById(GetUserByIdReq req, GetUserByIdRes res);
public int getUserList(GetUserListReq req, GetUserListRes res);

}