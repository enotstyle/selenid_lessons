package api.homework.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static org.hamcrest.Matchers.notNullValue;

public class ListUsersSpec {

    public static RequestSpecification requestListUsersSpec = with()
            .log().uri()
            .contentType(ContentType.JSON)
            .baseUri("https://reqres.in")
            .basePath("/api");


    public static ResponseSpecification responseListUsersSpec = new ResponseSpecBuilder()
            .log(STATUS).log(BODY)
            .expectStatusCode(200)
            .expectBody("",  notNullValue())
            .build();

}
