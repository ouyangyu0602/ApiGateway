package com.blueocn.api.kong.model;

import com.blueocn.api.support.utils.MapConverter;
import lombok.*;

import java.util.Map;

/**
 * Title: Customer
 * Description: Kong Customer 请求信息实体, 参见
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-16 13:20
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Consumer {
    public Map<String, Object> toMap() {
        return MapConverter.convert(this);
    }
}
