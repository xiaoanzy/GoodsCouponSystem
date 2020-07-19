package com.jhxaa.yhj.utli;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 实体工具类
 */
public class BeanUtil {

    /**
     * 创建一个list集合
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> List<T> newList(Class<T> tClass) {
        T tObject = null;
        if (tClass.getName().contains("Integer")) {
            tObject = (T) new Integer(0);
        }

        try {
            tObject = tClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        List<T> collect = Stream.of(tObject).collect(Collectors.toList());
        collect.clear();
        return collect;
    }


    /**
     * 集合判断空
     *
     * @param collection
     * @return
     */
    public static boolean isListEmpty(Collection collection) {
        System.out.println(null == collection);
        System.out.println(collection.isEmpty());
        System.out.println(collection.size());
        if (null == collection || collection.isEmpty() || collection.size() == 0) {
            return true;
        }
        return false;
    }
}
