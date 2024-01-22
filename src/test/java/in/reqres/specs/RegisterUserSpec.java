package in.reqres.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;

public class RegisterUserSpec {

    public static RequestSpecification tryToRegisterRequestSpec = with()
            .contentType(ContentType.JSON)
            .log().uri()
            .log().body();

    public static ResponseSpecification tryToRegisterResponseSpec = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectStatusCode(400)
            .build();

}
