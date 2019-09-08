package com.lxf.eye.common.domain;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

public class CommonUtil {
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws IllegalAccessException, InstantiationException {
        if (map == null){
            return null;
        }
        Object obj = beanClass.newInstance();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }
            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }

        return obj;
    }

    public static int getRandom(int max){
        return (int)(Math.random()*(max-1));
    }
}
