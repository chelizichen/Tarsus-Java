
package com.tarsus.example.TEST.taro;
import com.tarsus.example.decorator.TarsusParam;
import java.util.List;

import com.alibaba.fastjson.JSON;

 import com.tarsus.example.TEST.taro.Basic;

@TarsusParam
public class GetUserByIdReq{
  public Integer id;
    public Basic basic;
    
  public GetUserByIdReq(List<String> list){
    this.id = Integer.valueOf(list.get(0));
  this.basic = new Basic(JSON.parseArray(list.get(1),String.class));
    
  }
}
  