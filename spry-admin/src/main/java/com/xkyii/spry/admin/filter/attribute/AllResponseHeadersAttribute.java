package com.xkyii.spry.admin.filter.attribute;


import java.util.Map;
import java.util.StringJoiner;

import io.quarkus.vertx.http.runtime.attribute.ExchangeAttribute;
import io.quarkus.vertx.http.runtime.attribute.ExchangeAttributeBuilder;
import io.quarkus.vertx.http.runtime.attribute.ReadOnlyAttributeException;
import io.vertx.core.MultiMap;
import io.vertx.ext.web.RoutingContext;

public class AllResponseHeadersAttribute implements ExchangeAttribute {

    public static final AllResponseHeadersAttribute INSTANCE = new AllResponseHeadersAttribute();

    private AllResponseHeadersAttribute() {
    }

    @Override
    public String readAttribute(RoutingContext exchange) {
        final MultiMap headers = exchange.response().headers();

        if (headers.isEmpty()) {
            return null;
        } else {
            final StringJoiner joiner = new StringJoiner(System.lineSeparator());

            for (Map.Entry<String, String> header : headers) {
                joiner.add(header.getKey() + ": " + header.getValue());
            }

            return joiner.toString();
        }
    }

    @Override
    public void writeAttribute(RoutingContext exchange, String newValue) throws ReadOnlyAttributeException {
        throw new ReadOnlyAttributeException("Headers", newValue);
    }

    public static final class Builder implements ExchangeAttributeBuilder {

        @Override
        public String name() {
            return "ResponseHeaders";
        }

        @Override
        public ExchangeAttribute build(final String token) {
            if (token.equals("%{ALL_RESPONSE_HEADERS}")) {
                return INSTANCE;
            }
            return null;
        }

        @Override
        public int priority() {
            return 0;
        }
    }

}
