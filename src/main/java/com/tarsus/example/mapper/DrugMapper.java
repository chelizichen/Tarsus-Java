package com.tarsus.example.mapper;

import com.tarsus.example.base.TarsusBaseOrm;
import com.tarsus.example.decorator.ioc.Mapper;
import com.tarsus.example.enity.Drug;

import java.util.List;

@Mapper
public class DrugMapper {

    public List<Drug> getList(String[] args){
        final List<Drug> query = TarsusBaseOrm.query("select * from drug", args, Drug.class);
        return query;
    }

}


