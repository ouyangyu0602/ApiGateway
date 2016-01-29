package com.blueocn.api.kong.data;

import java.io.Serializable;

/**
 * Title: CustomerKeyData
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-18 11:39
 */
public class CustomerKeyData implements Serializable {
    private static final long serialVersionUID = -3396597656788385559L;

    private String key;

    private String consumer_id;

    private String created_at;

    private String id;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getConsumer_id() {
        return consumer_id;
    }

    public void setConsumer_id(String consumer_id) {
        this.consumer_id = consumer_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
