package base;

import decorator.ArcSort;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author leemulus 基础类最后自动生成
 */
public class ArcBaseParams {


    public ArcBaseParams(List list){
        // 拿到所有字段
        final Field[] declaredFields = this.getClass().getDeclaredFields();

        for (int i = 0; i < declaredFields.length; i++) {
            final boolean annotationPresent = declaredFields[i].isAnnotationPresent(ArcSort.class);
            if(annotationPresent){
                final ArcSort annotation = declaredFields[i].getAnnotation(ArcSort.class);
                try {
                    declaredFields[i].set(this,list.get(Integer.parseInt(annotation.value())));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

