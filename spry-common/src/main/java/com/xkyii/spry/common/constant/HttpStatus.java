package com.xkyii.spry.common.constant;

/**
 * Http状态码
 */
public class HttpStatus {

    /**
     * 200 OK, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.2.1">HTTP/1.1 documentation</a>.
     */
    public static final int OK = 200;

    /**
     * 201 Created, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.2.2">HTTP/1.1
     * documentation</a>.
     */
    public static final int CREATED = 201;

    /**
     * 202 Accepted, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.2.3">HTTP/1.1
     * documentation</a>.
     */
    public static final int ACCEPTED = 202;

    /**
     * 204 No Content, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.2.5">HTTP/1.1
     * documentation</a>.
     */
    public static final int NO_CONTENT = 204;
    /**
     * 205 Reset Content, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.2.6">HTTP/1.1
     * documentation</a>.
     *
     * @since 2.0
     */
    public static final int RESET_CONTENT = 205;
    /**
     * 206 Reset Content, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.2.7">HTTP/1.1
     * documentation</a>.
     *
     * @since 2.0
     */
    public static final int PARTIAL_CONTENT = 206;
    /**
     * 300 Multiple Choices, see <a href="https://datatracker.ietf.org/doc/html/rfc7231#section-6.4.1">HTTP/1.1:
     * Semantics and Content</a>.
     *
     * @since 3.1
     */
    public static final int MULTIPLE_CHOICES = 300;
    /**
     * 301 Moved Permanently, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.3.2">HTTP/1.1
     * documentation</a>.
     */
    public static final int MOVED_PERMANENTLY = 301;
    /**
     * 302 Found, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.3.3">HTTP/1.1 documentation</a>.
     *
     * @since 2.0
     */
    public static final int FOUND = 302;
    /**
     * 303 See Other, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.3.4">HTTP/1.1
     * documentation</a>.
     */
    public static final int SEE_OTHER = 303;
    /**
     * 304 Not Modified, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.3.5">HTTP/1.1
     * documentation</a>.
     */
    public static final int NOT_MODIFIED = 304;
    /**
     * 305 Use Proxy, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.3.6">HTTP/1.1
     * documentation</a>.
     *
     * @since 2.0
     */
    public static final int USE_PROXY = 305;
    /**
     * 307 Temporary Redirect, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.3.8">HTTP/1.1
     * documentation</a>.
     */
    public static final int TEMPORARY_REDIRECT = 307;
    /**
     * 308 Permanent Redirect, see <a href="https://tools.ietf.org/html/rfc7538">RFC 7538:
     * The Hypertext Transfer Protocol Status Code 308  = Permanent Redirect)</a>.
     *
     * @since 3.1
     */
    public static final int PERMANENT_REDIRECT = 308;
    /**
     * 400 Bad Request, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.1">HTTP/1.1
     * documentation</a>.
     */
    public static final int BAD_REQUEST = 400;
    /**
     * 401 Unauthorized, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.2">HTTP/1.1
     * documentation</a>.
     */
    public static final int UNAUTHORIZED = 401;
    /**
     * 402 Payment Required, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.3">HTTP/1.1
     * documentation</a>.
     *
     * @since 2.0
     */
    public static final int PAYMENT_REQUIRED = 402;
    /**
     * 403 Forbidden, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.4">HTTP/1.1
     * documentation</a>.
     */
    public static final int FORBIDDEN = 403;
    /**
     * 404 Not Found, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.5">HTTP/1.1
     * documentation</a>.
     */
    public static final int NOT_FOUND = 404;
    /**
     * 405 Method Not Allowed, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.6">HTTP/1.1
     * documentation</a>.
     *
     * @since 2.0
     */
    public static final int METHOD_NOT_ALLOWED = 405;
    /**
     * 406 Not Acceptable, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.7">HTTP/1.1
     * documentation</a>.
     */
    public static final int NOT_ACCEPTABLE = 406;
    /**
     * 407 Proxy Authentication Required, see
     * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.8">HTTP/1.1 documentation</a>.
     *
     * @since 2.0
     */
    public static final int PROXY_AUTHENTICATION_REQUIRED = 407;
    /**
     * 408 Request Timeout, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.9">HTTP/1.1
     * documentation</a>.
     *
     * @since 2.0
     */
    public static final int REQUEST_TIMEOUT = 408;
    /**
     * 409 Conflict, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.10">HTTP/1.1
     * documentation</a>.
     */
    public static final int CONFLICT = 409;
    /**
     * 410 Gone, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.11">HTTP/1.1 documentation</a>.
     */
    public static final int GONE = 410;
    /**
     * 411 Length Required, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.12">HTTP/1.1
     * documentation</a>.
     *
     * @since 2.0
     */
    public static final int LENGTH_REQUIRED = 411;
    /**
     * 412 Precondition Failed, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.13">HTTP/1.1
     * documentation</a>.
     */
    public static final int PRECONDITION_FAILED = 412;
    /**
     * 413 Request Entity Too Large, see
     * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.14">HTTP/1.1 documentation</a>.
     *
     * @since 2.0
     */
    public static final int REQUEST_ENTITY_TOO_LARGE = 413;
    /**
     * 414 Request-URI Too Long, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.15">HTTP/1.1
     * documentation</a>.
     *
     * @since 2.0
     */
    public static final int REQUEST_URI_TOO_LONG = 414;
    /**
     * 415 Unsupported Media Type, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.16">HTTP/1.1
     * documentation</a>.
     */
    public static final int UNSUPPORTED_MEDIA_TYPE = 415;
    /**
     * 416 Requested Range Not Satisfiable, see
     * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.17">HTTP/1.1 documentation</a>.
     *
     * @since 2.0
     */
    public static final int REQUESTED_RANGE_NOT_SATISFIABLE = 416;
    /**
     * 417 Expectation Failed, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.18">HTTP/1.1
     * documentation</a>.
     *
     * @since 2.0
     */
    public static final int EXPECTATION_FAILED = 417;
    /**
     * 428 Precondition required, see <a href="https://tools.ietf.org/html/rfc6585#section-3">RFC 6585: Additional HTTP
     * Status Codes</a>.
     *
     * @since 2.1
     */
    public static final int PRECONDITION_REQUIRED = 428;
    /**
     * 429 Too Many Requests, see <a href="https://tools.ietf.org/html/rfc6585#section-4">RFC 6585: Additional HTTP Status
     * Codes</a>.
     *
     * @since 2.1
     */
    public static final int TOO_MANY_REQUESTS = 429;
    /**
     * 431 Request Header Fields Too Large, see <a href="https://tools.ietf.org/html/rfc6585#section-5">RFC 6585: Additional
     * HTTP Status Codes</a>.
     *
     * @since 2.1
     */
    public static final int REQUEST_HEADER_FIELDS_TOO_LARGE = 431;
    /**
     * 451 Unavailable For Legal Reasons, see <a href="https://tools.ietf.org/html/rfc7725">RFC 7725:
     * An HTTP Status Code to Report Legal Obstacles</a>.
     *
     * @since 3.1
     */
    public static final int UNAVAILABLE_FOR_LEGAL_REASONS = 451;
    /**
     * 500 Internal Server Error, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.5.1">HTTP/1.1
     * documentation</a>.
     */
    public static final int INTERNAL_SERVER_ERROR = 500;
    /**
     * 501 Not Implemented, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.5.2">HTTP/1.1
     * documentation</a>.
     *
     * @since 2.0
     */
    public static final int NOT_IMPLEMENTED = 501;
    /**
     * 502 Bad Gateway, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.5.3">HTTP/1.1
     * documentation</a>.
     *
     * @since 2.0
     */
    public static final int BAD_GATEWAY = 502;
    /**
     * 503 Service Unavailable, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.5.4">HTTP/1.1
     * documentation</a>.
     */
    public static final int SERVICE_UNAVAILABLE = 503;
    /**
     * 504 Gateway Timeout, see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.5.5">HTTP/1.1
     * documentation</a>.
     *
     * @since 2.0
     */
    public static final int GATEWAY_TIMEOUT = 504;
    /**
     * 505 HTTP Version Not Supported, see
     * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.5.6">HTTP/1.1 documentation</a>.
     *
     * @since 2.0
     */
    public static final int HTTP_VERSION_NOT_SUPPORTED = 505;
    /**
     * 511 Network Authentication Required, see <a href="https://tools.ietf.org/html/rfc6585#section-6">RFC 6585: Additional
     * HTTP Status Codes</a>.
     *
     * @since 2.1
     */
    public static final int NETWORK_AUTHENTICATION_REQUIRED = 511;

    public static final int WARN = 601;

}
