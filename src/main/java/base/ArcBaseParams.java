package base;

import decorator.ArcSort;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author leemulus 自动生成参数类
 * @since 2023.2.17
 * @deprecated 弃用
 * 根据 ArcSort 的排序自动生成类
 * 在反序列化时，根据 @ArcSort 给定的参数依次为各个成员变量赋值
 * 比如 传入过来的参数解析后为 ["aaa","bbb","ccc","ddd"]
 * 则反序列化生成类之后 name = aaa , age = bbb , address = ccc,sex = ddd
 * ````
 * @ArcParams
 * public class Person extends ArcBaseParams {
 *     @ArcSort("0")
 *     public String name;
 *     @ArcSort("1")
 *     public String age;
 *     @ArcSort("2")
 *     public String address;
 *     @ArcSort("3")
 *     public String sex;
 *     public Person(List list) {
 *         super(list);
 *     }
 * }
 * ````
 */
public class ArcBaseParams {
    public ArcBaseParams(List list){
        // 拿到所有字段
        final Field[] declaredFields = this.getClass().getDeclaredFields();

        for (Field declaredField : declaredFields) {
            final boolean annotationPresent = declaredField.isAnnotationPresent(ArcSort.class);
            if (annotationPresent) {
                final ArcSort annotation = declaredField.getAnnotation(ArcSort.class);
                try {
                    declaredField.set(this, list.get(Integer.parseInt(annotation.value())));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

