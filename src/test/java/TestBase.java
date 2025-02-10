import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;


public class TestBase {

    //Variables
    String createPost = "{\n" +
            "    \"name\": \"morpheus\",\n" +
            "    \"job\": \"leader\"\n" +
            "}";
    String updateUser = "{\n" +
            "    \"name\": \"morpheus\",\n" +
            "    \"job\": \"zion resident\"\n" +
            "}";
    String unsuccessful = "{\n" +
            "    \"email\": \"sydney@fife\"\n" +
            "}";

    //BeforeAll
    @BeforeAll
    public static void beforeAllForTests(){
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }
}
