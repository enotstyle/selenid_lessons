package api.homework.tests;

import api.homework.model.list_users.ListUsers;
import api.homework.specs.ListUsersSpec;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static api.homework.specs.ListUsersSpec.requestListUsersSpec;
import static api.homework.specs.ListUsersSpec.responseListUsersSpec;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ListUsersTests {
    @Test
    void test() {
        ListUsers listUsers = given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().all()
                .extract().as(ListUsers.class);

        System.out.println(listUsers.getData().get(1));
    }

    @Test
    void testCountOfUsers() {
        ListUsers listUsers = given(requestListUsersSpec)
                .when()
                .get("/users?page=2")
                .then()
                .spec(responseListUsersSpec)
                .extract().as(ListUsers.class);

        assertEquals(listUsers.getPerPage(), listUsers.getData().size());
    }

}
