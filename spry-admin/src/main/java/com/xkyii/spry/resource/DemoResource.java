package com.xkyii.spry.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import com.xkyii.spry.TestBean;
import io.quarkus.qute.Qute;
import org.jboss.logging.Logger;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

@Path("demo")
public class DemoResource {

    @Inject
    Logger log;

    @GET
    public String get() {
        TestBean tb = new TestBean();
        return tb.getTest()  + " " + TestBean.FLAG;
    }

    @GET
    @Path("logging")
    public String logging() {
//        String quteMsg = Qute.fmt("Hello {}!", "Lucy");
//        String slfMsg = MessageFormatter.format("Hello {}!", "Lucy").getMessage();

        final int COUNT = 10000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++) {
            Qute.fmt("Hello {}!", "Lucy");
        }
        long quteTime = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++) {
            MessageFormatter.format("Hello {}!", "Lucy").getMessage();
        }
        long slfTime = System.currentTimeMillis() - start;

        // Qute: 162 ms
        // Slf4j: 3 ms
        return "Qute: " + quteTime + " ms\n" + "Slf4j: " + slfTime + " ms";
    }
}
