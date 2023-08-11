package com.xkyii.spry.web.constant;

import static com.xkyii.spry.framework.constant.FrameworkConstants.SPRY_FRAMEWORK_CONFIG_PREFIX;
import static com.xkyii.spry.framework.constant.FrameworkConstants.SPRY_FRAMEWORK_PRIORITY;

public class Constants {
    /// Config
    public static final String ADMIN_CONFIG_PREFIX = SPRY_FRAMEWORK_CONFIG_PREFIX + ".admin";

    /// 路由
    public static final String ADMIN_ROUTER_PREFIX = "/api/v1";

    /// Priority
    public static final int ADMIN_PRIORITY = SPRY_FRAMEWORK_PRIORITY + 500;
    public static final int ADMIN_STARTUP_PRIORITY = ADMIN_PRIORITY + 1;

    /// EventBus
    public static final String ADMIN_EVENT_创建登录日志 = "事件-创建登录日志";

    /// Cache
    public static final String ADMIN_CACHE_NAME_LOGIN_USER = "已登录用户";

    /// Context
    public static final String ADMIN_CONTEXT_KEY_LOGIN_USER = "已登录用户";
}
