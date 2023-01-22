package com.xkyii.spry.common.error;


import java.util.Locale;
import java.util.ResourceBundle;

public class ErrorMessageManager {
    public String getMessage(Integer code) {
        return getMessage(String.valueOf(code));
    }

    public String getMessage(String key) {
        return ResourceBundle.getBundle("i18n/error", Locale.getDefault()).getString(key);
    }

}
