package in.reqres;

import in.reqres.models.CreateUserResponseModel;
import in.reqres.models.ErrorResponseModel;
import in.reqres.models.RegisterUserDataModel;
import in.reqres.models.UserDataModel;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.UserSpec.*;
import static in.reqres.specs.RegisterUserSpec.tryToRegisterRequestSpec;
import static in.reqres.specs.RegisterUserSpec.tryToRegisterResponseSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class ReqresTest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }

    //1
    @Test
    public void createUserWithNameAndJobTest() {

        UserDataModel userDataModel = new UserDataModel();
        userDataModel.setName("Gregory");
        userDataModel.setJob("dvornik");

        CreateUserResponseModel createUserResponseModel = given(createUserRequestSpec)
                .body(userDataModel)
                .when()
                .post("/users")
                .then()
                .spec(createUserResponseSpec)
                .extract().as(CreateUserResponseModel.class);

        assertEquals(userDataModel.getName(), createUserResponseModel.getName());
        assertEquals(userDataModel.getJob(), createUserResponseModel.getJob());
        assertNotNull(createUserResponseModel.getId());
        assertNotNull(createUserResponseModel.getCreatedAt());
    }

    //2
    @Test
    public void createUserWithoutJobTest() {

        UserDataModel userDataModel = new UserDataModel();
        userDataModel.setName("Gregory");

        given(createUserRequestSpec)
                .body(userDataModel)
                .when()
                .post("/users")
                .then()
                .spec(createUserUnsuccessfulResponseSpec);
    }

    //3
    @Test
    public void deleteUserTest() {

        int userId = 23;

        given()
                .log().uri()
                .when()
                .delete("/users/" + userId)
                .then()
                .statusCode(204);
    }

    //4
    @Test
    public void tryToRegisterUser() {

        RegisterUserDataModel registerUserDataModel = new RegisterUserDataModel();
        registerUserDataModel.setUsername("Emma");
        registerUserDataModel.setEmail("emma.wong@reqres.in");
        registerUserDataModel.setPassword("pistol");

        ErrorResponseModel errorResponseModel = given(tryToRegisterRequestSpec)
                .body(registerUserDataModel)
                .when()
                .post("/register")
                .then()
                .spec(tryToRegisterResponseSpec)
                .extract().as(ErrorResponseModel.class);

        assertEquals("Note: Only defined users succeed registration", errorResponseModel.getError());
    }

    //5
    @Test
    public void verifyUsersOnPage() {

        given(getUsersRequestSpec)
                .get("/users?page=1")
                .then()
                .spec(getUsersResponseSpec)
                .body("data.size()", equalTo(6))
                .body("data.email", hasItems(
                        "george.bluth@reqres.in",
                        "janet.weaver@reqres.in",
                        "eve.holt@reqres.in",
                        "emma.wong@reqres.in",
                        "charles.morris@reqres.in",
                        "tracey.ramos@reqres.in"
                ));
    }


}
