package com.xkyii.spry.web.resource;


import com.xkyii.spry.web.entity.SysMenu;
import com.xkyii.spry.web.repository.SysMenuRepository;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import static com.xkyii.spry.web.constant.Constants.ADMIN_ROUTER_PREFIX;

@Path(ADMIN_ROUTER_PREFIX + "/demo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(description = "DEMO")
public class DemoResource {

    @GET
    @Operation(summary = "Get Demo")
    public Response get() {
        return Response.ok(JsonObject.of("data", "Demo")).build();
    }

    @Inject
    SysMenuRepository menuRepository;

    @POST
    @WithTransaction
    public Uni<SysMenu> save() {
        SysMenu menu = new SysMenu();
        // menu.setMenuId(99999L);
        menu.setMenuName("测试菜单");
        menu.setMenuType("9");
        menu.setPerms("a:b:c");
        menu.setIsCache("0");
        menu.setIsFrame("1");
        menu.setOrderNum(1);
        menu.setPath("pppp");
        return menuRepository.persist(menu);
    }
}
