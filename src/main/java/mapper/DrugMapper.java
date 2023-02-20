package mapper;

import base.AdoBaseOrm;
import decorator.ioc.Collect;
import enity.Drug;

import java.util.List;

@Collect
public class DrugMapper {

    public List<Drug> getList(String[] args){
        final List<Drug> query = AdoBaseOrm.query("select * from drug", args, Drug.class);
        return query;
    }

}


