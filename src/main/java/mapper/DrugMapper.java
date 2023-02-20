package mapper;

import base.AdoBaseOrm;
import decorator.ioc.Mapper;
import enity.Drug;

import java.util.List;

@Mapper
public class DrugMapper {

    public List<Drug> getList(String[] args){
        final List<Drug> query = AdoBaseOrm.query("select * from drug", args, Drug.class);
        return query;
    }

}


