package com.blueocn.api.support.spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.util.HashMap;
import java.util.Map;

/**
 * 对 Map 类型与 JSON 的互转操作工具类
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-15 17:54
 */
public class MapEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (!StringUtils.hasText(text)) {
            setValue(null);
        } else {
            setValue(JSON.parseObject(text, new TypeReference<HashMap<String, String>>() {
                //
            }));
        }
    }

    @Override
    public String getAsText() {
        Map<?, ?> value = (Map<?, ?>) getValue();

        if (value == null) {
            return "";
        }
        return JSON.toJSONString(value);
    }
}
