package com.xkyii.spry.common.error;

import com.xkyii.spry.common.util.Strings;

public class ApiException extends RuntimeException {
    protected IErrorCode errorCode;

    protected String message;

    protected Object[] args;

    protected String formattedMessage;
    protected String i18nFormattedMessage;

    public ApiException(Throwable e, IErrorCode errorCode, Object... args) {
        super(e);
        fillErrorCode(errorCode, args);
    }

    public ApiException(IErrorCode errorCode, Object... args) {
        fillErrorCode(errorCode, args);
    }

    public ApiException(IErrorCode errorCode) {
        fillErrorCode(errorCode);
    }

    private void fillErrorCode(IErrorCode errorCode, Object... args) {
        this.errorCode = errorCode;
        this.message = errorCode.message();
        this.args = args;

        this.formattedMessage = Strings.format(this.message, args);

//        try {
//            this.i18nFormattedMessage = MessageUtils.message(errorCode.i18nKey(), args);
//        } catch (Exception e) {
//            log.error("could not found i18nMessage entry for key: " + errorCode.i18nKey());
//        }
    }

    @Override
    public String getMessage() {
        return i18nFormattedMessage != null ? i18nFormattedMessage : formattedMessage;
    }

    @Override
    public String getLocalizedMessage() {
        return i18nFormattedMessage;
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }

    public String getFormattedMessage() {
        return formattedMessage;
    }

    public String getI18nFormattedMessage() {
        return i18nFormattedMessage;
    }
}
