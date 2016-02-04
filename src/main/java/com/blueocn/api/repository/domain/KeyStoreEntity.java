package com.blueocn.api.repository.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

/**
 * Title: KeyStoreEntity
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-17 03:57
 */
@Alias("KeyStoreEntity")
@Setter
@Getter
public class KeyStoreEntity extends PersistenceEntity {
    private static final long serialVersionUID = 5412799030856027766L;

    private Long userId;

    private String keyType;

    private String keyValue;
}
