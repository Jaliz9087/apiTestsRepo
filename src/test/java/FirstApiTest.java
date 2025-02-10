import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.lang.reflect.Array.get;
import static org.hamcrest.core.Is.is;

public class FirstApiTest extends TestBase{

    private static final Logger log = LoggerFactory.getLogger(FirstApiTest.class);

    @DisplayName("Проверяем создание пользака")
    @Test
    void createUserTest(){
        given()
                .body(createPost)
                .contentType(JSON)
                .when()
                .log().uri()
                .post("/users")

                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }
    @DisplayName("Апдейт юзера")
    @Test
    void updateUserInfoTest(){
        given()
                .body(updateUser)
                .contentType(JSON)
                .when()
                .log().uri()
                .put("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));
    }
    @DisplayName("Delete User")
    @Test
    void deleteUserTest(){
        given()
                .log().uri()
                .delete("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }
    @DisplayName("Unsuccessful  reg")
    @Test
    void  unsuccessfulRegistrationTest(){
        given()
                .body(unsuccessful)
                .contentType(JSON)
                .when()
                .log().uri()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));

    }
    @DisplayName("Check singleUser")
    @Test
    void singleUserCheckTest(){
        given()
                .log().all()
                .when()
                .get("/users/2")
                .then()
                .statusCode(200)
                .log().all()
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"));


    }
}

