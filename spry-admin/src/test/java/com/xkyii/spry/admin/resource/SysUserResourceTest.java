package com.xkyii.spry.admin.resource;

import com.xkyii.spry.admin.dto.login.RegisterInput;
import com.xkyii.spry.admin.dto.login.RegisterOutput;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.jboss.resteasy.reactive.RestResponse.Status.OK;

@QuarkusTest
@TestHTTPEndpoint(SysUserResource.class)
public class SysUserResourceTest {

    @Test
    public void testRegister() {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(new JsonObject()
                        .put("username", "hello")
                        .put("password", "123456")
                        .put("code", "4")
                        .put("uuid", "uuid")
                        .getMap())
                .when().post("/register")
                .then()
                .extract().response();

        Assertions.assertEquals(OK.getStatusCode(), response.statusCode());
        Assertions.assertEquals(0, response.jsonPath().getInt("code"));
        Assertions.assertEquals("成功", response.jsonPath().getString("message"));
        Assertions.assertEquals("hello", response.jsonPath().getString("data.username"));
    }
}
