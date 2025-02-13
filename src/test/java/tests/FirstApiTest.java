package tests;

import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import specifications.Spec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static specifications.Spec.*;

@Tag("allTests")
public class FirstApiTest extends TestBase{

    private static final Logger log = LoggerFactory.getLogger(FirstApiTest.class);

    @DisplayName("Проверяем создание пользака")
    @Test
    void createUserTest() {
        NewReq req = new NewReq();
        req.setName("morpheus");
        req.setJob("leader");
        LombokResponse response = step ("Our request", () ->
                given(Spec.usReqSpec)
                        .body(req)

                        .when()
                        .post("/users")

                        .then()
                        .spec(Spec.usRespSpec)
                        .extract().as(LombokResponse.class));

        step("Check our response", () -> {
            assertEquals("morpheus",response.getName());
            assertEquals("leader",response.getJob());
        });

    }

    @DisplayName("Апдейт юзера")
    @Test
    void updateUserInfoTest() {
        NewReq req = new NewReq();
        req.setName("morpheus");
        req.setJob("zion resident");
        LombokRdResponce response = step ("Request", () ->
        given(changeUserSpec)
                .body(req)
                .when()
                .put()
                .then()
                .spec(changeRespSpec)
                .extract().as(LombokRdResponce.class));
        step("Check response",() ->{
            assertEquals("morpheus",response.getName());
            assertEquals("zion resident",response.getJob());
        });
    }

    @DisplayName("Delete User")
    @Test
    void deleteUserTest() {
        step("delete user", () ->
        given(requestSpec)
                .log().uri()
                .delete("/users/{id}",2)
                .then()
                .assertThat()
                .log().all()
                .spec(resp204Spec));
    }

    @DisplayName("Unsuccessful  reg")
    @Test
    void unsuccessfulRegistrationTest() {
        LombokRequest req = new LombokRequest();
        req.setEmail("");
        req.setPassword("");
        ErrorResp response = step("Make request", () ->
        given(requestSpec)
                .body(req)
                .when()
                .post("/register")
                .then()
                .spec(createResponse400)
                .extract().as(ErrorResp.class));
        step("check response", () ->
                assertEquals("Missing email or username", response.getError()));

    }

    @DisplayName("Check singleUser")
    @Test
    void singleUserCheckTest() {
        step("Check user info", () ->
        given(viewUserReq)
                .get()
                .then()
                .spec(viewUserResp));


    }
}



