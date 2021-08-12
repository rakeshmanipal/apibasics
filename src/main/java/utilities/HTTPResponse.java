package utilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class HTTPResponse {

    private RequestSpecBuilder builder = new RequestSpecBuilder();
    private String url;
    private String method;

    public HTTPResponse() {
    }

    /**
     * Constructor for initial settings
     * @param uri
     * @param method
     * @param token
     */
    public HTTPResponse(String uri, String method, String token) {
        this.url = "http://localhost:3000" + uri;
        this.method = method;

        if (token != null) {
            builder.addHeader("Authorization", "Bearer " + token);
        }

    }

    /**
     * To get Bearer token
     * @param body
     * @return String - Token
     */
    public String Authenticate(Object body) {
        builder.setBody(body);
        return ExecuteAPI().getBody().jsonPath().get("access_token");
    }

    /**
     * To Execute GET POST DELETE Request
     * @return ResponseOptions
     */
    private ResponseOptions<Response> ExecuteAPI() {
        RequestSpecification requestSpecification = builder.build();
        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.spec(requestSpecification);

        if (this.method.equalsIgnoreCase(APIConstants.ApiMethods.POST)) {
            return request.post(this.url);
        } else if (this.method.equalsIgnoreCase(APIConstants.ApiMethods.DELETE)) {
            return request.delete(this.url);
        } else if (this.method.equalsIgnoreCase(APIConstants.ApiMethods.GET)) {
            return request.get(this.url);
        }
        return null;
    }

    /**
     * Executing API with query params being passed as input of it
     * @param queryPath
     * @return response
     */

    public ResponseOptions<Response> ExecuteWithQueryParams(Map<String,String> queryPath){
    builder.addQueryParams(queryPath);
    return ExecuteAPI();
    }

    /**
     * Execute API with Path params
     * @param pathParam
     * @return response
     */
    public ResponseOptions<Response> ExecuteWithPathParams(Map<String,String> pathParam){
        builder.addPathParams(pathParam);
        return ExecuteAPI();
    }

    /**
     * Execute API with Pathparams and Body
     * @param pathParam
     * @param body
     * @return Response
     */
    public ResponseOptions<Response> ExecuteWithPathParamsAndBody(Map<String,String> pathParam,Map<String,String> body){
        builder.addParams(pathParam);
        builder.setBody(body);
        return ExecuteAPI();
    }

}


