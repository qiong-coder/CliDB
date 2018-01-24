package com.buaa.CliDB.utils;

import java.lang.reflect.Field;

public class MergeUtils {

    public static <T> T mergeObjects(T first, T second) {
        Class<?> clazz = first.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            Object returnValue = clazz.newInstance();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value1 = field.get(first);
                Object value2 = field.get(second);
                Object value = (value1 != null) ? value1 : value2;
                field.set(returnValue, value);
            }
            return (T) returnValue;
        } catch (Exception e) {
            return null;
        }
    }

}
