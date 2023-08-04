package com.xkyii.spry.web.resource;


import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import static com.xkyii.spry.web.constant.Constants.ROUTER_PREFIX;

@Path(ROUTER_PREFIX + "/demo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(description = "DEMO")
public class DemoResource {

    @GET
    @Operation(summary = "Get Demo")
    public Response get() {
        return Response.ok(JsonObject.of("data", "Demo")).build();
    }
}
