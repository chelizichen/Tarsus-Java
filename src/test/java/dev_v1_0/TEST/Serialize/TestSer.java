package dev_v1_0.TEST.Serialize;

import java.util.*;

public class TestSer {

    private static final String ITEM_SEPARATOR = ";";
    private static final String KEY_VALUE_SEPARATOR = "=";

    public static String serialize(Object obj) {
        StringBuilder sb = new StringBuilder();
        if (obj instanceof Map<?, ?>) {
            Map<?, ?> map = (Map<?, ?>) obj;
            for (Object key : map.keySet()) {
                Object value = map.get(key);
                String keyStr = key.toString();
                String valueStr = serialize(value);
                sb.append(keyStr).append(KEY_VALUE_SEPARATOR).append(valueStr).append(ITEM_SEPARATOR);
            }
        } else if (obj instanceof List<?>) {
            List<?> list = (List<?>) obj;
            for (int i = 0; i < list.size(); i++) {
                Object value = list.get(i);
                String keyStr = String.valueOf(i);
                String valueStr = serialize(value);
                sb.append(keyStr).append(KEY_VALUE_SEPARATOR).append(valueStr).append(ITEM_SEPARATOR);
            }
        } else {
            sb.append(obj.toString()).append(ITEM_SEPARATOR);
        }
        return sb.toString();
    }

    public static Object deserialize(String str) {
        Map<String, Object> map = new HashMap<>();
        String[] items = str.split(ITEM_SEPARATOR);
        for (String item : items) {
            String[] keyValue = item.split(KEY_VALUE_SEPARATOR);
            String key = keyValue[0];
            String valueStr = keyValue.length > 1 ? keyValue[1] : "";
            Object value = valueStr.isEmpty() ? "" : deserialize(valueStr);
            if (isNumeric(key)) {
                map.put(key, value);
            } else {
                String[] segments = key.split("\\.");
                Map<String, Object> currentObj = map;
                for (int i = 0; i < segments.length - 1; i++) {
                    String segment = segments[i];
                    if (!currentObj.containsKey(segment)) {
                        currentObj.put(segment, new HashMap<String, Object>());
                    }
                    currentObj = (Map<String, Object>) currentObj.get(segment);
                }
                currentObj.put(segments[segments.length - 1], value);
            }
        }
        return map;
    }

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static void main(String[] args) {
        Map<String, Object> obj = new HashMap<>();
        Map<String, Object> userReq = new HashMap<>();
        Map<String, Object> basic = new HashMap<>();
        basic.put("TOKEN", "ASDASDASDSADSA");
        Map<String, Object> set = new HashMap<>();
        set.put("jwt", "jwtToken");
        basic.put("set", set);
        Map<String, Object> bok = new HashMap<>();
        bok.put("sasd1", "based12");
        basic.put("bok", bok);
        basic.put("asdasd", "asdas");
        userReq.put("id", "GetUserByIdReq---1");
        userReq.put("basic", basic);
        userReq.put("asd", "123");
        obj.put("GetUserByIdReq", userReq);
        obj.put("asd", "123");

        String serialized = serialize(obj);
        System.out.println(serialized);
// 输出: GetUserByIdReq.id=GetUserByIdReq---1;GetUserByIdReq.basic.TOKEN=ASDASDASDSADSA;GetUserByIdReq.basic.set.jwt=jwtToken;GetUserByIdReq.basic.bok.sasd1=based12;GetUserByIdReq.basic.asdasd=asdas;GetUserByIdReq.asd=123;asd=123;

        Object deserialized = deserialize(serialized);
        System.out.println(deserialized);
// 输出: {asd=123, GetUserByIdReq={id=GetUserByIdReq---1, basic={TOKEN=ASDASDASDSADSA, set={jwt=jwtToken}, bok={sasd1=based12}, asdasd=asdas}, asd=123}}


    }
}
