package com.xkyii.spry.web.resource;

import com.xkyii.spry.common.dto.login.LoginCommand;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.xkyss.quarkus.server.error.ErrorCode.成功;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@TestHTTPEndpoint(LoginController.class)
public class LoginControllerTest {
    @Test
    public void test_login() {
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
            .statusCode(200)
            .body("code", equalTo(成功));
    }
}
