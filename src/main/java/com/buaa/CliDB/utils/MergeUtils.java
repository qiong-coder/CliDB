package com.buaa.CliDB.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;


import java.lang.reflect.Field;
import java.util.Set;

public class MergeUtils {

//    public static <T> T mergeObjects(T first, T second) {
//        Class<?> clazz = first.getClass();
//        Field[] fields = clazz.getDeclaredFields();
//        try {
//            Object returnValue = clazz.newInstance();
//            for (Field field : fields) {
//                field.setAccessible(true);
//                Object value1 = field.get(first);
//                Object value2 = field.get(second);
//                Object value = (value1 != null) ? value1 : value2;
//                field.set(returnValue, value);
//            }
//            return (T) returnValue;
//        } catch (Exception e) {
//            return null;
//        }
//    }



    public static <T> T mergeObjects(T first, T second, Class<T> object) {
        JSONObject second_json = JSON.parseObject(JSONObject.toJSONString(second));
        second_json.putAll(JSONObject.parseObject(JSONObject.toJSONString(first)));
        return JSONObject.toJavaObject(second_json,object);
    }

}
