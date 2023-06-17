package api;

import api.model.lombok.LoginBodyLombokModel;
import api.model.lombok.LoginResponseLombokModel;
import api.model.pojo.LoginBodyPojoModel;
import api.model.pojo.LoginResponsePojoModel;
import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.Test;

import static api.helpers.CustomAllureListener.withCustomTemplates;
import static api.specs.LoginSpec.loginRequestSpec;
import static api.specs.LoginSpec.loginResponseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;

import static org.assertj.core.api.Assertions.*;

public class ReqresInEXTests {


    @Test
    void loginWithoutModelsTest() {
        String data = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void loginWithPojoModelsTest() {
        LoginBodyPojoModel loginBody = new LoginBodyPojoModel();
        loginBody.setEmail("eve.holt@reqres.in");
        loginBody.setPassword("cityslicka");

        LoginResponsePojoModel loginResponse = given()
                .log().body()
                .contentType(JSON)
                .body(loginBody)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponsePojoModel.class);


        assertThat(loginResponse.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void loginWithLombokModelsTest() {
        //lombok позволяет не заморачиватся с геттерами и стеттерами

        LoginBodyLombokModel loginBody = new LoginBodyLombokModel();
        loginBody.setEmail("eve.holt@reqres.in");
        loginBody.setPassword("cityslicka");

        LoginResponseLombokModel loginResponse = given()
                .log().body()
                .contentType(JSON)
                .body(loginBody)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);


        assertThat(loginResponse.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void loginWithAllureTest() {
        //lombok позволяет не заморачиватся с геттерами и стеттерами

        LoginBodyLombokModel loginBody = new LoginBodyLombokModel();
        loginBody.setEmail("eve.holt@reqres.in");
        loginBody.setPassword("cityslicka");

        LoginResponsePojoModel loginResponse = given()
                .filter(new AllureRestAssured())
                .log().body()
                .contentType(JSON)
                .body(loginBody)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponsePojoModel.class);


        assertThat(loginResponse.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void loginWithCustomAllureTest() {
        LoginBodyLombokModel loginBody = new LoginBodyLombokModel();
        loginBody.setEmail("eve.holt@reqres.in");
        loginBody.setPassword("cityslicka");

        LoginResponseLombokModel loginResponse = given()
                .filter(withCustomTemplates())
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(loginBody)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);

        assertThat(loginResponse.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void loginWithAllureStepsTest() {
        LoginBodyLombokModel loginBody = new LoginBodyLombokModel();
        loginBody.setEmail("eve.holt@reqres.in");
        loginBody.setPassword("cityslicka");

        LoginResponseLombokModel loginResponse =
                step("Get authorization data", () ->
                        given()
                                .filter(withCustomTemplates())
                                .log().uri()
                                .log().body()
                                .contentType(JSON)
                                .body(loginBody)
                                .when()
                                .post("https://reqres.in/api/login")
                                .then()
                                .log().status()
                                .log().body()
                                .statusCode(200)
                                .extract().as(LoginResponseLombokModel.class));

        step("Verify authorization response", () ->
                assertThat(loginResponse.getToken()).isEqualTo("QpwL5tke4Pnpja7X4"));
    }


    @Test
    void loginWithSpecsTest() {
        LoginBodyLombokModel loginBody = new LoginBodyLombokModel();
        loginBody.setEmail("eve.holt@reqres.in");
        loginBody.setPassword("cityslicka");

        LoginResponseLombokModel loginResponse = given(loginRequestSpec)
                .body(loginBody)
                .when()
                .post("/login")
                .then()
                .spec(loginResponseSpec)
                .extract().as(LoginResponseLombokModel.class);

        assertThat(loginResponse.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }


}