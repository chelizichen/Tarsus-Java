package mapper;

import base.TarsusBaseOrm;
import decorator.ioc.Mapper;
import enity.Drug;

import java.util.List;

@Mapper
public class DrugMapper {

    public List<Drug> getList(String[] args){
        final List<Drug> query = TarsusBaseOrm.query("select * from drug", args, Drug.class);
        return query;
    }

}


