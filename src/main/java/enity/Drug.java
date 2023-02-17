package enity;

import base.AdoBaseOrm;
import decorator.ioc.Collect;
import decorator.orm.AdoEntity;
import decorator.orm.AdoField;
import decorator.orm.Key;

import java.util.List;

@AdoEntity
@Collect
public class Drug extends AdoBaseOrm {

    @AdoField
    @Key
    String id;

    @AdoField("dr_name")
    String DrName;

    @AdoField("dr_price")
    String DrPrice;

    @AdoField("dr_remark")
    String DrRemark;

    @AdoField("dr_code")
    String DrCode;

    @Override
    public String toString() {
        return "Drug{" +
                "id='" + id + '\'' +
                ", dr_name='" + DrName + '\'' +
                ", dr_price='" + DrPrice + '\'' +
                ", dr_remark='" + DrRemark + '\'' +
                ", dr_code='" + DrCode + '\'' +
                '}';
    }
}

class TestClass{

    public static void main(String[] args) {
        TestGetOneBy();
//        TestQuery();
    }

    static void TestQuery(){
        List<Drug> query = AdoBaseOrm.query("select * from drug", Drug.class);
        final List<Drug> query1 = AdoBaseOrm.query("select * from drug where id = ?", new String[]{"1"}, Drug.class);

        for (Drug drug : query) {
            System.out.println(drug.toString());
        }
        for (Drug drug : query1) {
            System.out.println(drug.toString());
        }
    }

    static void TestGetOneBy(){
        final Drug oneBy = new Drug().getOneBy("1");
        System.out.println(oneBy);
    }
}

