package utilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;


public class RestAssuredExtension {

    public static RequestSpecification Request;

    public RestAssuredExtension() {
        //Arrange
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri("http://localhost:3000/");
        builder.setContentType(ContentType.JSON);
        var requestSpec = builder.build();
        Request = RestAssured.given().spec(requestSpec);
    }

    public static ResponseOptions<Response> GetOpsWithPathParams(String url, Map<String, String> pathParam) {
        Request.pathParams(pathParam);
        return Request.get(url);
    }

    public static ResponseOptions<Response> GetOpsWithToken(String url,String token) {
        try {
            Request.header(new Header("Authorization", "Bearer "+ token));
            return Request.get(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResponseOptions<Response> PostOpsWithBodyAndPathParams(String url, Map<String, String> pathParam, Map<String, String> body)  {
        Request.pathParams(pathParam);
        Request.body(body);
        return Request.post(url);
    }

    public static ResponseOptions<Response> PostOpsWithBody(String url, Map<String, String> body)  {
        Request.body(body);
        return Request.post(url);
    }

    public static ResponseOptions<Response> DeleteOpsWithPathParams(String url, Map<String, String> pathParam)  {
        Request.pathParams(pathParam);
        return Request.delete(url);
    }

    public static ResponseOptions<Response> GetWithQueryParamsWithToken(String url, Map<String, String> queryParam,String token)  {
        Request.header(new Header("Authorization", "Bearer "+token));
        Request.queryParams(queryParam);
        return Request.get(url);
    }


    public static ResponseOptions<Response> PUTOpsWithBodyAndPathParams(String url, Map<String, String> body,Map<String, String> pathParams) {
        Request.body(body);
        Request.pathParams(pathParams);
        return Request.put(url);
    }
}
