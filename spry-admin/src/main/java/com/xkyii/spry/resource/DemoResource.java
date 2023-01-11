package com.xkyii.spry.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import com.xkyii.spry.TestBean;

@Path("demo")
public class DemoResource {

    @GET
    public String get() {
        TestBean tb = new TestBean();
        return tb.getTest()  + " " + TestBean.FLAG;
    }
}
