package base;

import decorator.ProxyInterFace;

import java.util.HashMap;
import java.util.Map;

/**
 * @description  所有Rpc控制类基于此类
 */
public class ArcInterFace {
    protected static Map<String, ArcInterFace> ClazzMap = new HashMap();

    public ArcInterFace(){
        boolean hasAnnotation = this.getClass().isAnnotationPresent(ProxyInterFace.class);
        String interFaceName_Or_ClazzName = "";
        if(hasAnnotation){
            ProxyInterFace testAnnotation = this.getClass().getAnnotation(ProxyInterFace.class);
            interFaceName_Or_ClazzName = testAnnotation.interFace();
        }else {
            interFaceName_Or_ClazzName = this.getClass().getSimpleName();
        }
        ArcInterFace.ClazzMap.put(interFaceName_Or_ClazzName,this);
    }
}
