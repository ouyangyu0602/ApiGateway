package com.blueocn.api.support.exception;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Constructor;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Title: CommonException
 * Description: 通用程序业务异常封装, 所有Api Gateway的异常基于此类封装,
 * 自定义异常不直接继承于此异常类.
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-29 17:39
 */
public class CommonException extends RuntimeException {

    private static final long serialVersionUID = -5097768787801034398L;

    /**
     * 异常ID，用于表示某一异常实例，每一个异常实例都有一个唯一的异常ID
     */
    protected String id;

    /**
     * 异常信息，包含必要的上下文业务信息，用于打印日志
     */
    protected String message;

    /**
     * 具体异常码，即异常码code的后3位，由各具体异常实例化时自己定义
     */
    protected String defineCode;

    protected String realClassName;

    protected CommonException(String defineCode) {
        super();
        this.defineCode = defineCode;
        initId();
    }

    private CommonException(String errorMessage, Throwable exception) {
        super(errorMessage, exception);
    }

    private void initId() {
        this.id = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
    }

    public String getId() {
        return id;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message, Object... args) {
        this.message = MessageFormat.format(message, args);
    }

    public String getDefineCode() {
        return defineCode;
    }

    @SuppressWarnings("unchecked")
    public static <T extends CommonException> T newException(T exception, String message,
        Object... args) {
        if (exception == null) {
            throw new CommonException("no exception instance specified");
        }
        try {
            Constructor constructor = exception.getClass().getDeclaredConstructor(String.class);
            constructor.setAccessible(true);
            T newException = (T) constructor.newInstance(exception.getDefineCode());
            newException.setMessage(message, args);
            return newException;
        } catch (Throwable e) {
            throw new CommonException("create exception instance fail : " + e.getMessage(), e);
        }
    }

    /**
     * 比较异常的class和defineCode是否相同
     *
     * @param e CommonException
     */
    public boolean codeEquals(CommonException e) {
        if (e == null) {
            return false;
        } else if (!e.getClass().equals(this.getClass())) {
            return false;
        }
        return e.getDefineCode().equals(getDefineCode());
    }

    public CommonException upCasting() {
        if (this.getClass().equals(CommonException.class)) {
            return this;
        }
        CommonException superException = new CommonException(this.defineCode);
        superException.message = this.message;
        superException.realClassName = this.getClass().getName();
        superException.id = this.id;
        superException.setStackTrace(this.getStackTrace());
        return superException;
    }

    @SuppressWarnings("unchecked")
    public CommonException downCasting() {
        if (this.realClassName == null || CommonException.class.getName()
            .equals(this.realClassName)) {
            return this;
        }
        Class clz = null;
        try {
            clz = Class.forName(this.realClassName);
        } catch (Exception e) { // NOSONAR
        }
        if (clz == null) {
            return this;
        }
        try {
            Constructor constructor = clz.getDeclaredConstructor(String.class);
            constructor.setAccessible(true);
            CommonException newException = (CommonException) constructor.newInstance(this.defineCode);
            newException.message = this.message;
            newException.id = this.id;
            newException.setStackTrace(this.getStackTrace());
            return newException;
        } catch (Throwable e) { // NOSONAR
            return this;
        }
    }

    public String getRealClassName() {
        if (realClassName == null) {
            return this.getClass().getName();
        }
        return realClassName;
    }

    public void mergeStackTrace(StackTraceElement[] stackTrace) {
        this.setStackTrace(ArrayUtils.addAll(this.getStackTrace(), stackTrace));
    }

    public StackTraceElement[] getCoreStackTrace() {
        List<StackTraceElement> list = new ArrayList<>();
        for (StackTraceElement traceEle : getStackTrace()) {
            if (traceEle.getClassName().startsWith("com.yeepay")) {
                list.add(traceEle);
            }
        }
        StackTraceElement[] stackTrace = new StackTraceElement[list.size()];
        return list.toArray(stackTrace);
    }

    public String getCoreStackTraceStr() {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement traceEle : getCoreStackTrace()) {
            sb.append("\n").append(traceEle);
        }
        return sb.toString();
    }
}
