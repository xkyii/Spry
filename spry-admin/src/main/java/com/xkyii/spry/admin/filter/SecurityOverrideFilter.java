package com.xkyii.spry.admin.filter;

import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

import java.io.IOException;

@Provider
@PreMatching
public class SecurityOverrideFilter implements ContainerRequestFilter {

    @Inject
    Logger logger;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        logger.info("SecurityOverrideFilter ----");
    }
}
