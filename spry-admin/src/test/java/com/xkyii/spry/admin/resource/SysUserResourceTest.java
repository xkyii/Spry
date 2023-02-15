package com.xkyii.spry.admin.resource;

import com.xkyii.spry.admin.constant.AdminError;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.jboss.resteasy.reactive.RestResponse.Status.BAD_REQUEST;
import static org.jboss.resteasy.reactive.RestResponse.Status.OK;

@QuarkusTest
@TestHTTPEndpoint(SysUserResource.class)
public class SysUserResourceTest {

    /**
     * 注册失败,密码过短
     */
    @Test
    public void testRegisterPasswordTooShort() {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(new JsonObject()
                        .put("username", "hello")
                        .put("password", "1")
                        .getMap())
                .when().post("/register")
                .then()
                .extract().response();

        Assertions.assertEquals(BAD_REQUEST.getStatusCode(), response.statusCode());
        Assertions.assertEquals(AdminError.参数校验失败, response.jsonPath().getInt("code"));
        Assertions.assertTrue(AdminError.校验用户密码长度.startsWith(response.jsonPath().getString("data[0].code")));
    }

    /**
     * 成功注册
     */
    @Test
    public void testRegisterSuccess() {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(new JsonObject()
                        .put("username", "hello")
                        .put("password", "123456")
                        .getMap())
                .when().post("/register")
                .then()
                .extract().response();

        Assertions.assertEquals(OK.getStatusCode(), response.statusCode());
        Assertions.assertEquals(AdminError.成功, response.jsonPath().getInt("code"));
        Assertions.assertEquals("hello", response.jsonPath().getString("data.username"));
    }
}
