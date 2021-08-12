package steps;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BDDStyledMethod {
    public static void simpleGetPost(String postNumber){
        given().contentType(ContentType.JSON).
                when().get(String.format("http://localhost:3000/posts/%s",postNumber)).
                then().body("author", Is.is("Karthik KK"));
    }

    public static void performContainsCollection(){
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("http://localhost:3000/posts/")
        .then()
                .body("author", containsInAnyOrder("Karthik KK","Karthik KK", null,"ExecuteAutomation","ExecuteAutomation")).statusCode(200);
    }

    public static void performPathParameter(){
        given()
                .contentType(ContentType.JSON).
        with()
                .pathParams("post",3).
        when()
                .get("http://localhost:3000/posts/{post}").
        then()
                .body("author",containsString("ExecuteAutomation"));

    }

    public static void performPostWithBodyParameter(){
        HashMap<String,String> postContent=new HashMap<>();
        postContent.put("id","7");
        postContent.put("author","Harries");
        postContent.put("title","Bold and Beats");

        given()
                .contentType(ContentType.JSON).
        with()
                .body(postContent).
        when()
                .post("http://localhost:3000/posts").
        then()
                .body("author",Is.is("Harries"));

    }


}
