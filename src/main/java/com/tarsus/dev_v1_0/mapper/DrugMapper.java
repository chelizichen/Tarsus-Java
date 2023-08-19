package com.tarsus.dev_v1_0.mapper;

import com.tarsus.dev_v1_0.decorator.ioc.Mapper;
import com.tarsus.dev_v1_0.base.TarsusBaseOrm;
import com.tarsus.dev_v1_0.enity.Drug;

import java.util.List;

@Mapper
public class DrugMapper {
    public List<Drug> getList(String[] args){
        final List<Drug> query = TarsusBaseOrm.query("select * from drug", args, Drug.class);
        return query;
    }
}


