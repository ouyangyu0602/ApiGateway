package com.blueocn.api.support.rule;

import com.blueocn.api.repository.domain.RuleEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Title: FilterContext
 * Description:
 * The Request Context holds request, response,  state information and data for RuleFilters to access and share.
 * The RequestContext lives for the duration of the request and is ThreadLocal.
 * Extensions of RequestContext can be substituted by setting the contextClass.
 * Most methods here are convenience wrapper methods; the RequestContext is an extension of a ConcurrentHashMap.
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-23 00:18
 */
public class FilterContext extends ConcurrentHashMap<String, Object> {
    private static final long serialVersionUID = -396621024800433554L;
    private static final Logger LOGGER = LoggerFactory.getLogger(FilterContext.class);

    protected static Class<? extends FilterContext> contextClass = FilterContext.class;
    private static FilterContext testContext = null;

    public FilterContext() {
        super();
    }

    /**
     * Creates a new instance of the class represented by this {@code Class}
     * object.  The class is instantiated as if by a {@code new}
     * expression with an empty argument list.  The class is initialized if it
     * has not already been initialized.
     */
    protected static final ThreadLocal<? extends FilterContext> threadLocal = new ThreadLocal<FilterContext>() {
        @Override
        protected FilterContext initialValue() {
            try {
                return contextClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                LOGGER.info("", e);
                throw new RuntimeException(e);
            }
        }
    };

    /**
     * Override the default RequestContext
     *
     * @param clazz 上下文类型, 约定为FilterContext的继承类, 便于个人扩展定义
     */
    public static void setContextClass(Class<? extends FilterContext> clazz) {
        contextClass = clazz;
    }

    /**
     * set an overriden "test" context
     *
     * @param context 测试请求上下文
     */
    public static void testSetCurrentContext(FilterContext context) {
        testContext = context;
    }

    /**
     * Get the current RequestContext
     *
     * @return the current RequestContext
     */
    public static FilterContext getCurrentContext() {
        if (testContext != null) {
            return testContext;
        }
        return threadLocal.get();
    }

    /**
     * Convenience method to return a boolean value for a given key
     *
     * @param key 标识
     * @return true or false depending what was set. default is false
     */
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    /**
     * Convenience method to return a boolean value for a given key
     *
     * @param key             标识
     * @param defaultResponse 默认值
     * @return true or false depending what was set. default defaultResponse
     */
    public boolean getBoolean(String key, boolean defaultResponse) {
        Boolean b = (Boolean) get(key);
        if (b != null) {
            return b;
        }
        return defaultResponse;
    }

    /**
     * sets a key value to Boolean.TRUE
     *
     * @param key 标识
     */
    public void set(String key) {
        put(key, Boolean.TRUE);
    }

    /**
     * puts the key, value into the map. a null value will remove the key from the map
     *
     * @param key   标识
     * @param value 值
     */
    public void set(String key, Object value) {
        if (value != null)
            put(key, value);
        else
            remove(key);
    }

    /**
     * 获取规则上下文
     *
     * @return {@code RuleEntity}
     */
    @SuppressWarnings("unchecked")
    public static RuleEntity getRule() {
        return (RuleEntity) getObject("ruleEntity");
    }

    /**
     * 存储规则上下文
     *
     * @param rule {@code RuleEntity}
     */
    public static void setRule(RuleEntity rule) {
        setObject("ruleEntity", rule);
    }

    private static void setObject(String objKey, Object obj) {
        // The feature of ConcurrentHashMap, just use it for further consideration
        threadLocal.get().putIfAbsent(objKey, obj);
    }

    private static Object getObject(String objKey) {
        // 这个调用, 利用了 ThreadLocal 的特性, 每次获取时, 如果不存在上下文, 则存储一个对象于 ThreadLocal 中
        return threadLocal.get().get(objKey);
    }

    /**
     * dnset the threadLocal context. Done at the end of the request.
     */
    public void unset() {
        threadLocal.remove();
    }
}
