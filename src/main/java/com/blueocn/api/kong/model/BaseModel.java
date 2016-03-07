package com.blueocn.api.kong.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

import static com.blueocn.api.support.utils.MapConverter.convert;

/**
 * Title: BaseModel
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-25 16:47
 */
@Getter
@Setter
public abstract class BaseModel implements Serializable {
    private static final long serialVersionUID = -8884322649295904796L;

    private String errorMessage;
    /**
     * @see com.blueocn.api.support.utils.MapConverter#convert(Object)
     */
    public Map<String, Object> toMap() {
        return convert(this);
    }
}
