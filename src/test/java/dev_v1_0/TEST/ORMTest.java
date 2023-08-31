package dev_v1_0.TEST;

import dev_v1_0.base.TarsusBaseOrm;
import dev_v1_0.enity.Drug;
import dev_v1_0.utils.SqlUtil;

import java.util.List;

public class ORMTest {

    public static void main(String[] args) {
//        TestGetOneBy();
//        TestQuery();
//        TestGetList();
//        TestSqlGetBody();
        TestCount();
    }

    static void TestQuery() {
        List<Drug> query = TarsusBaseOrm.query("select * from drug", Drug.class);
        final List<Drug> query1 = TarsusBaseOrm.query("select * from drug where id = ?", new String[]{"1"}, Drug.class);

        for (Drug drug : query) {
            System.out.println(drug.toString());
        }
        for (Drug drug : query1) {
            System.out.println(drug.toString());
        }
    }

    static void TestGetOneBy() {
        final Drug oneBy = new Drug().getOneBy("1");
        System.out.println(oneBy);
    }

    static void TestGetList(){
        final List<Drug> list = new Drug().getList("0", "10");
        for (Drug drug : list) {
            System.out.println(drug);
        }
    }

    static void TestSqlGetBody(){
        final String[] strings = {"a", "b", "c"};
        final String testTable = SqlUtil.getBody("TestTable",strings);
        System.out.println(testTable);
    }

    static void TestCount(){
        Integer a = new Drug().getCountBy( "1");
        System.out.println(a);
    }

}
