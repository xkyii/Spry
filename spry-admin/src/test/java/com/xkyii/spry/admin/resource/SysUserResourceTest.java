package com.xkyii.spry.admin.resource;

import com.xkyii.spry.admin.constant.AdminError;
import com.xkyss.mocky.unit.text.Hashes;
import com.xkyss.mocky.unit.text.Strings;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

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
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Strings strings = new Strings(random);
        String username = "test_" + strings.size(5).get();

        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(new JsonObject()
                        .put("username", username)
                        .put("password", "123456")
                        .getMap())
                .when().post("/register")
                .then()
                .extract().response();

        Assertions.assertEquals(OK.getStatusCode(), response.statusCode());
        Assertions.assertEquals(AdminError.成功, response.jsonPath().getInt("code"));
        Assertions.assertEquals(username, response.jsonPath().getString("data.username"));
    }

    /**
     * 登录默认账号
     * admin: admin123
     */
    @Test
    public void testLogin01() {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(new JsonObject()
                        .put("username", "admin")
                        .put("password", "epBqlhkISqOgzqy8ePae/spvVp7r3x/NZrNNSPbUlhU1QZZxyceoIUaaswzGIaRn8KZexwjJsTU6/BZvyYszBMCKPXKkyOHHPZLfDhg9UDCfnkdCm5fJznwilaLyGpBKSBCvoSrkfIibkCQoEQi0V8LmtM0SY+MZaQ640FYWe5TYqOm1/Vfd9IqngQXuFPlmXn4RUt3U4Nti4ay+hZJFxJXFrg0S8pNBUGKg8hLL2weOq0wMZa45o6scVUpxezpw/1TAbegFMNFZ0RtaJWsIJFvC1nV5C2CEuGrFLHDodsuiGkiGWh5AGB7SVEwVxFV0gqbTvzNDD/D7Il/IFmBcGA==")
                        .getMap())
                .when().post("/login")
                .then()
                .extract().response();

        Assertions.assertEquals(OK.getStatusCode(), response.statusCode());
        Assertions.assertEquals(AdminError.成功, response.jsonPath().getInt("code"));
        Assertions.assertNotNull(response.jsonPath().getString("data.token"));
    }

    /**
     * 登录默认账号
     * xk1: lin123
     */
    @Test
    public void testLogin02() {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(new JsonObject()
                        .put("username", "xk1")
                        .put("password", "CHpQWTGKnxDXFTtf6b4SY/4NBxPSntvxm0s+I22ir3LsW8qe1zumoJVRaQm4jvKljvejhcVddfQsE3cp+6n+qzwcvVYDCwJIJonaZuQSYzCPVwIYnWJK0EWFP4qFlLxHGNVzH7B+gJcjmZ/po92saSDNXHTT0qASbr1KCBKFectdzX/3CjWQc7Y8ZrmuUSvzBBjTUTmM2BlNZtVPxmsmYZEmjTkGlnVhhuBoqKktdt/U3EMxHWRViI/oFLojqPREi4AZdgBY/i4HJv9HLI1FUgMjiKI58Ksiz4OF99rALjxsdzZo2qLZzZYltB9285qolGYN+e/MmyzVFwaxAY2LcQ==")
                        .getMap())
                .when().post("/login")
                .then()
                .extract().response();

        Assertions.assertEquals(OK.getStatusCode(), response.statusCode());
        Assertions.assertEquals(AdminError.成功, response.jsonPath().getInt("code"));
        Assertions.assertNotNull(response.jsonPath().getString("data.token"));
    }

    /**
     * 登录默认账号
     * xk2: lin123
     */
    @Test
    public void testLogin03() {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(new JsonObject()
                        .put("username", "xk2")
                        .put("password", "CHpQWTGKnxDXFTtf6b4SY/4NBxPSntvxm0s+I22ir3LsW8qe1zumoJVRaQm4jvKljvejhcVddfQsE3cp+6n+qzwcvVYDCwJIJonaZuQSYzCPVwIYnWJK0EWFP4qFlLxHGNVzH7B+gJcjmZ/po92saSDNXHTT0qASbr1KCBKFectdzX/3CjWQc7Y8ZrmuUSvzBBjTUTmM2BlNZtVPxmsmYZEmjTkGlnVhhuBoqKktdt/U3EMxHWRViI/oFLojqPREi4AZdgBY/i4HJv9HLI1FUgMjiKI58Ksiz4OF99rALjxsdzZo2qLZzZYltB9285qolGYN+e/MmyzVFwaxAY2LcQ==")
                        .getMap())
                .when().post("/login")
                .then()
                .extract().response();

        Assertions.assertEquals(OK.getStatusCode(), response.statusCode());
        Assertions.assertEquals(AdminError.成功, response.jsonPath().getInt("code"));
        Assertions.assertNotNull(response.jsonPath().getString("data.token"));
    }
}
