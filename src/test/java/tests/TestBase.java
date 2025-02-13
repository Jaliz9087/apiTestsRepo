package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;


public class TestBase {
    @BeforeAll
    public static void beforeAllForTests(){
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }
}
