package specifications;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static help.AllureListener.withCustomTemplates;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;


public class Spec {
    public static RequestSpecification usReqSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .log().headers()
            .contentType(JSON)
            .basePath("/api/users");


    public static ResponseSpecification usRespSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(STATUS)
            .log(BODY)
            .build();

    public static RequestSpecification changeUserSpec = with()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .log().body()
            .log().uri()
            .log().headers()
            .basePath("/api/users/2");

    public static ResponseSpecification changeRespSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();

    public static RequestSpecification requestSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .contentType(ContentType.JSON);


    public static ResponseSpecification resp204Spec=new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(STATUS)
            .build();

    public static ResponseSpecification createResponse400=new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(STATUS)
            .log(BODY)
            .build();
    public static RequestSpecification viewUserReq = with()
            .filter(withCustomTemplates())
            .log().body()
            .log().uri()
            .log().headers()
            .basePath("/api/users/2");
    public static ResponseSpecification viewUserResp = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();
}
