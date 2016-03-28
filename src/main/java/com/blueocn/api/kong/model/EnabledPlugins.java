package com.blueocn.api.kong.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Title: EnabledPlugins
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-29 17:12
 */
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnabledPlugins implements Serializable {
    private static final long serialVersionUID = -2383652413092210923L;

    private List<String> enabled_plugins;
}
