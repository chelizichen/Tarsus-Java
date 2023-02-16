package params;

import base.ArcBaseParams;
import decorator.ArcSort;
import decorator.ArcParams;

import java.util.List;

@ArcParams
public class Person extends ArcBaseParams {

    @ArcSort("0")
    public String name;
    @ArcSort("1")
    public String age;
    @ArcSort("2")
    public String address;
    @ArcSort("3")
    public String sex;

    public Person(List list) {
        super(list);
    }
}

//class TestPerson{
//    public static void main(String[] args) {
//        final ArrayList<Object> objects = new ArrayList<>();
//        objects.add(0,"aaaa");
//        objects.add(1,"bbbb");
//        objects.add(2,"cccc");
//        objects.add(3,"dddd");
//        final Person person = new Person(objects);
//    }
//}
