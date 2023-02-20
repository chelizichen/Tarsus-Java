package mapper;

import base.ArcBaseOrm;
import decorator.ioc.Mapper;
import enity.Drug;

import java.util.List;

@Mapper
public class DrugMapper {

    public List<Drug> getList(String[] args){
        final List<Drug> query = ArcBaseOrm.query("select * from drug", args, Drug.class);
        return query;
    }

}


