package com.xkyss.rest.constant;

public class Constants {
    public static final String CONFIG_PREFIX = "xkyss";
    public static final String CONFIG_BUILD_PREFIX = "xkyss.build";
    public static final String CONFIG_REST_PREFIX = CONFIG_PREFIX + ".rest";
    public static final String CONFIG_REST_BUILD_PREFIX = CONFIG_BUILD_PREFIX + ".rest";


    public static final String KEY_REQUEST_TIME = "request-time";
    public static final String KEY_REQUEST_BODY = "request-body";
    public static final String KEY_REQUEST_BODY_HASHCODE = "request-body-hashcode-key";
    public static final String KEY_RESPONSE_BODY = "response-body";
    public static final String KEY_RESPONSE_FUNC = "response-func";


    /// 优先级 (值越小,优先级越高)
    public static final int PRIORITY_REST = 500;
    public static final int PRIORITY_REST_HTTP_LOG = PRIORITY_REST + 1;
}
