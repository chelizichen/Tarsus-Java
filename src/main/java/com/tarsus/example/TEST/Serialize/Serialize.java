package com.tarsus.example.TEST.Serialize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Serialize {
    public static Map<String, Object> serialize(Map<String, Object> obj) {
        Map<String, Object> hash = new HashMap<>();
        for (String key : obj.keySet()) {
            Object value = obj.get(key);
            if (value instanceof Map) {
// 如果值是Map，则递归调用 serialize 函数，并将结果存入哈希表中
                Map<String, Object> subHash = serialize((Map<String, Object>) value);
                for (String subKey : subHash.keySet()) {
                    hash.put(key + "__" + subKey, subHash.get(subKey));
                }
            }else if(value instanceof List){

            }else {
// 如果值不是Map，则直接存入哈希表中
                hash.put(key, value);
            }
        }
        return hash;
    }

    public Map<String, Object> List_Sear(List<Object> value){
        System.out.println(value);
        Map<String, Object> hash = new HashMap<>();
        for (Object val: value) {
            if(val instanceof  Map){
                Map<String, Object> serialize = serialize((Map<String, Object>) val);
            }
        }
        return hash;
    }

    public static Map<String, Object> deserialize(Map<String, Object> hash) {
        Map<String, Object> obj = new HashMap<>();
        for (String key : hash.keySet()) {
            String[] segments = key.split("__");
            Map<String, Object> currentObj = obj;
            for (int i = 0; i < segments.length - 1; i++) {
                String segment = segments[i];
                if (!currentObj.containsKey(segment)) {
                    currentObj.put(segment, new HashMap<String, Object>());
                }
                currentObj = (Map<String, Object>) currentObj.get(segment);
            }
            currentObj.put(segments[segments.length - 1], hash.get(key));
        }
        return obj;
    }

    public static void main(String[] args) {
        List jsonArray = JSON.parseArray("[\"GetUserByIdReq---1\",\"\",[\"ASDASDASDSADSA\",[\"12412312\"],[\"asda1\",\"11\"],[1,2,3,4,5],[[\"1\",\"2\"],[\"1\",\"2\"],[\"1\",\"2\"]]]]\n");
        System.out.println(jsonArray.get(2) instanceof List);
        List a = (List)jsonArray.get(2);
        System.out.println(a.size());
        System.out.println(jsonArray);
//        HashMap<String, Object> hmp = new HashMap<>();
//        HashMap<Object, Object> asdasd = new HashMap<>();
//        ArrayList<Object> objects = new ArrayList<>();
//        objects.add("111");
//        objects.add("222");
//        asdasd.put("jwt","token");
//        hmp.put("test",asdasd);
//        hmp.put("CES",objects);
//        ArrayList<Object> objects1 = new ArrayList<>();
//        objects1.add(asdasd);
//        objects1.add(asdasd);
//        hmp.put("CESDSD",objects1);
//        Map<String, Object> serialize = serialize(hmp);
//        System.out.println(serialize);
//        Map<String, Object> deserialize = deserialize(serialize);
//        System.out.println(deserialize);
    }
}
