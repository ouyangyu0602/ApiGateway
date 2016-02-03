package com.blueocn.api.support.exception;

/**
 * Title: CoreException
 * Description: 所有Api Gateway自定义异常公共父类
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-29 17:58
 */
public abstract class CoreException extends CommonException { // NOSONAR

    private static final long serialVersionUID = -1740616168688483053L;

    protected CoreException(String defineCode) {
        super(defineCode);
    }
}
