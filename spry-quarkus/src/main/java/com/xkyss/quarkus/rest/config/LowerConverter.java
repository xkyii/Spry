package com.xkyss.quarkus.rest.config;

import org.eclipse.microprofile.config.spi.Converter;

import java.util.Locale;

public class LowerConverter implements Converter<String>{
    @Override
    public String convert(String s) throws IllegalArgumentException, NullPointerException {
        if (s == null) {
            return null;
        }

        String trimmedString = s.trim();

        if (trimmedString.isEmpty()) {
            return null;
        }

        return trimmedString.toLowerCase(Locale.ROOT);
    }
}
