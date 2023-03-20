package com.tarsus.example.enity;

import com.alibaba.fastjson.JSON;
import com.tarsus.example.decorator.TarsusParam;

import java.sql.SQLOutput;
import java.util.List;

@TarsusParam
public class TestList {
    public String name;
//    public List<Job> jobList;
    public Job job;
    public TestList(List<Object> list) {

        this.name = (String) list.get(0);
        System.out.println("原参数"+list + "    " + list.size());

        try {
            System.out.println("走到这里来了 需要解析的参数");
            System.out.println(list.get(1));
            this.job = new Job((List<Object>) list.get(1));
        }catch (Exception exception){
            System.out.println("错误！！！");
            throw exception;
        };

    }
}
