
package com.tarsus.example.TEST.taro;
import com.tarsus.example.decorator.TarsusParam;
import java.util.List;

import com.alibaba.fastjson.JSON;


@TarsusParam
public class Basic{
  public String token;
    
  public Basic(List<String> list){
    this.token = list.get(0);
    
  }
}
  