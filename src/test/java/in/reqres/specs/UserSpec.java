package in.reqres.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;

public class UserSpec {

    public static RequestSpecification createUserRequestSpec = with()
            .contentType(ContentType.JSON)
            .log().uri()
            .log().body();

    public static ResponseSpecification createUserResponseSpec = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectStatusCode(201)
            .build();

    public static ResponseSpecification createUserUnsuccessfulResponseSpec = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectStatusCode(201)
            .build();

    public static RequestSpecification getUsersRequestSpec = with()
            .log().uri()
            .log().body();

    public static ResponseSpecification getUsersResponseSpec = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectStatusCode(200)
            .build();


}
