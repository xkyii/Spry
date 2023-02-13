//package com.xkyii.spry.admin.filter;
//
//import jakarta.inject.Inject;
//import jakarta.ws.rs.WebApplicationException;
//import jakarta.ws.rs.client.ClientRequestContext;
//import jakarta.ws.rs.client.ClientRequestFilter;
//import jakarta.ws.rs.client.ClientResponseContext;
//import jakarta.ws.rs.client.ClientResponseFilter;
//import jakarta.ws.rs.container.ContainerRequestContext;
//import jakarta.ws.rs.container.ContainerRequestFilter;
//import jakarta.ws.rs.container.ContainerResponseContext;
//import jakarta.ws.rs.container.ContainerResponseFilter;
//import jakarta.ws.rs.ext.*;
//import org.jboss.logging.Logger;
//
//import java.io.IOException;
//
//@Provider
//public class ExceptionxFilter
//        implements
//        ContainerRequestFilter,
//        ContainerResponseFilter,
//        ClientRequestFilter,
//        ClientResponseFilter,
//        ReaderInterceptor,
//        WriterInterceptor
//{
//
//    @Inject
//    Logger logger;
//
//    @Override
//    public void filter(ContainerRequestContext requestContext)
//            throws IOException {
//        logger.info("filter ContainerRequestContext");
//    }
//
//    @Override
//    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
//        logger.info("filter ContainerResponseContext");
//    }
//
//    @Override
//    public void filter(ClientRequestContext clientRequestContext) throws IOException {
//        logger.info("filter ClientRequestContext");
//    }
//
//    @Override
//    public void filter(ClientRequestContext clientRequestContext, ClientResponseContext clientResponseContext) throws IOException {
//        logger.info("filter ClientResponseContext");
//
//    }
//
//    @Override
//    public Object aroundReadFrom(ReaderInterceptorContext readerInterceptorContext) throws IOException, WebApplicationException {
//        logger.info("filter ReaderInterceptorContext");
//        return readerInterceptorContext.proceed();
//    }
//
//    @Override
//    public void aroundWriteTo(WriterInterceptorContext writerInterceptorContext) throws IOException, WebApplicationException {
//        logger.info("filter writerInterceptorContext");
//    }
//}
