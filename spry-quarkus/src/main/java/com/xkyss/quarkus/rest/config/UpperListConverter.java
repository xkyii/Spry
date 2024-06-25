package com.xkyss.quarkus.rest.config;

import io.vertx.core.http.HttpMethod;
import org.eclipse.microprofile.config.spi.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class UpperListConverter implements Converter<List<String>>{
    @Override
    public List<String> convert(String s) throws IllegalArgumentException, NullPointerException {
        if (s == null) {
            return new ArrayList<>();
        }

        String trimmedString = s.trim();
        if (trimmedString.isEmpty()) {
            return new ArrayList<>();
        }

        return Arrays.stream(trimmedString.split(","))
            .map(String::trim)
            .filter(x -> !x.isEmpty())
            .map(String::toUpperCase)
            .toList()
            ;
    }
}
