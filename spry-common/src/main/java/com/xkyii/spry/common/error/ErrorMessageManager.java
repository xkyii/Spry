package com.xkyii.spry.common.error;


import java.util.Locale;
import java.util.ResourceBundle;

public class ErrorMessageManager {

    private static final String PATH = "i18n/error";

    public String getMessage(Integer code) {
        return getMessage(String.valueOf(code), Locale.getDefault());
    }

    public String getMessage(Integer code, Locale locale) {
        return getMessage(String.valueOf(code), locale);
    }

    public String getMessage(String key) {
        return getMessage(key, Locale.getDefault());
    }

    public String getMessage(String key, Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle(PATH, locale);
        if (bundle.containsKey(key)) {
            return bundle.getString(key);
        }
        else {
            return key;
        }
    }

}
