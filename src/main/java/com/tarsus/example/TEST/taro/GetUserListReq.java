
package com.tarsus.example.TEST.taro;
import com.tarsus.example.decorator.TarsusParam;
import java.util.List;

import com.alibaba.fastjson.JSON;

 import com.tarsus.example.TEST.taro.Basic;

@TarsusParam
public class GetUserListReq{
  public Basic basic;
    public List<Integer> ids;
    
  public GetUserListReq(List<String> list){
    this.basic = new Basic(JSON.parseArray(list.get(0),String.class));
  this.ids = JSON.parseArray(list.get(1),int.class);
    
  }
}
  