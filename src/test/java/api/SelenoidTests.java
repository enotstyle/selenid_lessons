package api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelenoidTests {
    /*
        1. Make request to https://selenoid.autotests.cloud/status
        2. Get response { total: 20, used: 0, queued: 0, pending: 0, browsers: { android: { 8.1: { } },
            chrome: { 100.0: { }, 99.0: { } }, chrome-mobile: { 86.0: { } }, firefox: { 97.0: { }, 98.0: { } },
            opera: { 84.0: { }, 85.0: { } } } }
        3. Check total is 20
     */

    //https://github.com/rest-assured/rest-assured/wiki/Usage

    @Test
    void checkTotal() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(20));
    }

    @Test
    void checkTotalWithStatus() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200)
                .body("total", is(20));
    }


    @Test
    void checkTotalWithGiven() {
        given() //определяет, что будет отправлено в запросе
                .log().all()
                .when() //  с каким методом и на какой эндпоинт отправляем запрос
                .get("https://selenoid.autotests.cloud/status")
                .then() //как проверяется пришедший ответ
                .log().all()
                .statusCode(200)
                .body("total", is(20));
    }

    @Test
    void checkTotalWithSomeLogs() {
        given()
                .log().uri()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().body()
                .statusCode(200)
                .body("total", is(20));
    }

    @Test
    void checkChromeVersion() {
        given()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200)
                .body("browsers.chrome", hasKey("100.0"));
    }


    @Test
    void checkResponseBadPractice() {

        String body = "{ \"total\": 20, \"used\": 0, \"queued\": 0, \"pending\": 0, \"browsers\": { \"android\": { \"8.1\": { } }, \"chrome\": { \"100.0\": { }, \"99.0\": { } }, \"chrome-mobile\": { \"86.0\": { } }, \"firefox\": { \"97.0\": { }, \"98.0\": { } }, \"opera\": { \"84.0\": { }, \"85.0\": { } } } }";

        Response actualBody = given()
                .log().uri()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().response();
        assertEquals(body, actualBody.asString());
    }

    @Test
    void checkResponseGoodPractice() {

        Integer expectTotal = 20;
        Integer actualTotal = given()
                .log().uri()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().path("total");
        assertEquals(expectTotal, actualTotal);
    }

    /*
        1. Make request to https://selenoid.autotests.cloud/wd/hub/status
        2. Get response {"value":{"message":"Selenoid 1.10.7 built at 2021-11-21_05:46:32AM","ready":true}}
        3. Check ready is true
     */


    @Test
    void checkWdHubStatus401() {
        given()
                .log().uri()
                .when()
                .get("https://selenoid.autotests.cloud/wd/hub/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(401);
    }

    @Test
    void checkWdHubStatus200() {
        given()
                .log().uri()
                .when()
                .get("https://user1:1234@selenoid.autotests.cloud/wd/hub/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("value.ready", is(true));
    }


    @Test
    void checkWdHubWithAuthStatus() {
        given()
                .log().uri()
                .auth().basic("user1", "1234")
                .when()
                .get("https://selenoid.autotests.cloud/wd/hub/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("value.ready", is(true));
    }
}
