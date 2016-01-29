package com.blueocn.api.support.rule;

/**
 * Title: RuleFilter
 * Description: 规则基础类, 基于此约束, 定义基本的规则该有的方法.
 * Better Than Interface, we use a abstract class
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-23 00:13
 */
public abstract class RuleFilter {

    /**
     * 定义规则的启用状态, 部分规则约束可以动态启用或者禁用
     */
    private boolean ruleStatus;

    /**
     * 规则调用顺序保留字段
     */
    private int order;

    public boolean isActive() {
        return ruleStatus;
    }

    /**
     * 这个为实际的规则调用约束方法
     */
    public abstract void doFilter();
}
