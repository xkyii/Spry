package com.xkyss.rest.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.hibernate.validator.resourceloading.AggregateResourceBundleLocator;
import org.hibernate.validator.spi.resourceloading.ResourceBundleLocator;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.xkyss.rest.constant.Constants.I18N_ERROR;
import static com.xkyss.rest.constant.Constants.I18N_SERVER_ERROR;


@ApplicationScoped
public class ErrorMessageService {

    private final ResourceBundleLocator resourceBundleLocator;

    public ErrorMessageService() {
        resourceBundleLocator = new AggregateResourceBundleLocator(List.of(I18N_ERROR, I18N_SERVER_ERROR));
    }

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
        ResourceBundle bundle = resourceBundleLocator.getResourceBundle(locale);
        if (bundle.containsKey(key)) {
            return bundle.getString(key);
        }
        else {
            return key;
        }
    }
}
