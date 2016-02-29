package com.blueocn.api.support.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * Title: MapConverter
 * Description: 将 Fastjson 定义的数据实体信息转成 Map 的 toString 方法, 便于 Retrofit2 的调用
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-16 15:52
 */
public final class MapConverter {

    private MapConverter() {
        // No Construct
    }

    /**
     * 将对象转换为 JSON, 然后转成 Map
     *
     * @param object 想转成 Map 的对象
     */
    public static Map<String, Object> convert(Object object) {
        String jsonStr = JSON.toJSONString(object);
        return JSON.parseObject(jsonStr, new TypeReference<Map<String, Object>>() {
        });
    }

    /**
     * 将 Map 转为指定对象
     */
    public static <T> T convert(Map<String, Object> map, Class<T> clazz) {
        String jsonStr = JSON.toJSONString(map);
        return JSON.parseObject(jsonStr, clazz);
    }
}
