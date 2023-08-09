package com.xkyii.spry.web.resource;

import com.xkyii.spry.common.dto.login.LoginCommand;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.jboss.resteasy.reactive.RestResponse.StatusCode.INTERNAL_SERVER_ERROR;
import static org.jboss.resteasy.reactive.RestResponse.StatusCode.OK;

@QuarkusTest
@TestHTTPEndpoint(LoginController.class)
public class LoginControllerTest {
    @Test
    public void test_login_success() {
        LoginCommand command = new LoginCommand();
        command.setUsername("admin");
        command.setPassword("admin123");
        command.setCode("10");
        command.setUuid(UUID.randomUUID().toString());

        given()
            .contentType(ContentType.JSON)
            .body(command)
        .when()
            .post("login")
        .then()
            .statusCode(OK)
            .and().body("code", equalTo(OK))
            .and().body("token", notNullValue())
        ;
    }

    @Test
    public void test_login_wrong_password() {
        LoginCommand command = new LoginCommand();
        command.setUsername("admin");
        command.setPassword("admin123456");
        command.setCode("10");
        command.setUuid(UUID.randomUUID().toString());

        given()
            .contentType(ContentType.JSON)
            .body(command)
        .when()
            .post("login")
        .then()
            .statusCode(OK)
            .and().body("code", equalTo(INTERNAL_SERVER_ERROR))
            .and().body("msg", equalTo("用户不存在/密码错误"))
        ;
    }

    @Test
    public void test_getInfo() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("getInfo")
        .then()
            .statusCode(Response.Status.OK.getStatusCode())
        ;
    }
}
