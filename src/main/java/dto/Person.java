package dto;

import decorator.ArcParams;

import java.util.ArrayList;
import java.util.List;

@ArcParams("Person")
public class Person {
    public String name;
    public String age;
    public String address;
    public String sex;

    public Person(String name, String age, String address, String sex) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.sex = sex;
    }

    public Person(List list){
        final Object o = list.get(0);
        this.name = (String) o;
        final Object o1 = list.get(1);
        this.age = (String) o1;
        final Object o2 = list.get(2);
        this.address = (String) o2;
        final Object o3 = list.get(3);
        this.sex = (String) o3;
    }
}
