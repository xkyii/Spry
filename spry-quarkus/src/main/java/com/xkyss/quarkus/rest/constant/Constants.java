package com.xkyss.quarkus.rest.constant;

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
    public static final String KEY_LOG_ENTITY = "log-entity";

    // i18n
    public static final String I18N_ERROR = "i18n/error";
    public static final String I18N_SERVER_ERROR = "i18n/_server_error";
    public static final String I18N_VALIDATION = "i18n/validation";

    /// 优先级 (值越小,优先级越高)
    public static final int PRIORITY_REST = 500;
    public static final int PRIORITY_REST_HTTP_LOG = PRIORITY_REST + 1;
}
