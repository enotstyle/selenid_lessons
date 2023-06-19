package api.homework.tests;

import api.homework.model.LoginBodyModel;
import api.homework.model.LoginResponseModel;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static api.helpers.CustomAllureListener.withCustomTemplates;
import static api.specs.LoginSpec.loginRequestSpec;
import static com.codeborne.pdftest.assertj.Assertions.assertThat;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ReqresInTests {

    @Test
    void testCreateUsers() {
        String user = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"));
    }


    @Test
    void testCreateUsersWithModels() {
        LoginBodyModel body = new LoginBodyModel();
        body.setJob("leader");
        body.setName("morpheus");

        LoginResponseModel responseBody = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", is("morpheus"))
                .extract().as(LoginResponseModel.class);
        assertThat(responseBody.getName()).isEqualTo(body.getName());
    }

    @Test
    void testCreateUsersWithAllureAndSpecs() {
        LoginBodyModel body = new LoginBodyModel();
        body.setJob("leader");
        body.setName("morpheus");

        LoginResponseModel responseBody = step("выполянем запрос", () ->
                given()
                        .spec(loginRequestSpec)
                        .filter(withCustomTemplates())
                        .body(body)
                        .when()
                        .post("/users")
                        .then()
                        .statusCode(201)
                        .body("name", is("morpheus"))
                        .extract().as(LoginResponseModel.class)
        );

        step("делаем проверку", () -> assertThat(responseBody.getName()).isEqualTo(body.getName()));

    }


}
