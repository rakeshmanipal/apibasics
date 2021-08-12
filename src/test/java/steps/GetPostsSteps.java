package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import pojo.Address;
import pojo.Location;
import pojo.LoginBody;
import pojo.Posts;
import utilities.APIConstants;
import utilities.HTTPResponse;
import utilities.RestAssuredExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GetPostsSteps {

    private static ResponseOptions<Response> response;
    private static String token;

    @Given("I perform GET Operation for {string}")
    public void iPerformGETOperationFor(String uri) {
        response = RestAssuredExtension.GetOpsWithToken(uri, token);
    }

    @Then("I should see the author name as {string}")
    public void iShouldSeeTheAuthorNameAs(String authorName) {
        //With Builder Pattern

        var posts = new Posts.Builder().build();

        var postss = new Posts.Builder().setAuthor("fdAFa")
                .setId(1)
                .setTitle("Hellwww").build();

        System.out.println("Postss  author-- > " + postss.getAuthor());


        var post = response.getBody().as(posts.getClass());


        //Without Builder pattern
//        var posts = response.getBody().as(Posts.class);
        assertThat(post.getAuthor(), equalTo(authorName));
        //assertThat(response.getBody().jsonPath().get("author"), hasItem(authorName));
    }

    @Then("I should see the authors name")
    public void iShouldSeeTheAuthorsName() {
        BDDStyledMethod.performContainsCollection();
    }

    @Then("I should see verify Get Parameter")
    public void iShouldSeeVerifyGetParameter() {
        BDDStyledMethod.performPathParameter();
    }


    @Then("I should see the has name as {string}")
    public void iShouldSeeTheHasNameAs(String name) {
        System.out.println("Response Name---->" + response.getBody().jsonPath().get("name"));
        assertThat(response.getBody().jsonPath().get("name"), equalTo(name));

    }

    @Given("I perform Authenticate operation for {string} with body")
    public void iPerformAuthenticateOperationForWithBody(String uri, DataTable table) {
        List<List<String>> data = table.asLists();
        //Body
//        Map<String, String> body = new HashMap<>();
//        body.put("email", data.get(1).get(0));
//        body.put("password", data.get(1).get(1));
        LoginBody loginBody = new LoginBody();
        loginBody.setEmail(data.get(1).get(0));
        loginBody.setPassword(data.get(1).get(1));
        //Post the body
        //response = RestAssuredExtension.PostOpsWithBody(url, body);
        HTTPResponse httpResponse = new HTTPResponse(uri, APIConstants.ApiMethods.POST, null);
        token = httpResponse.Authenticate(loginBody);
        System.out.println("token---->" + token);

    }

    @And("I perform GET operation with path parameter for address {string}")
    public void iPerformGETOperationWithPathParameterForAddress(String uri, DataTable table) {
        List<List<String>> data = table.asLists();
        //Path param
        Map<String, String> queryParam = new HashMap<>();
        queryParam.put("id", data.get(1).get(0));
        //response = RestAssuredExtension.GetWithQueryParamsWithToken(uri, queryParam, response.getBody().jsonPath().getString("access_token"));
        HTTPResponse httpResponse = new HTTPResponse(uri, APIConstants.ApiMethods.GET, token);
        response = httpResponse.ExecuteWithQueryParams(queryParam);
    }


    @Then("I should see the street name as {string} for the {string} address")
    public void iShouldSeeTheStreetNameAsForTheAddress(String streetName, String addressType) {
        var location = response.getBody().as(Location[].class);

        System.out.println("Location --->" + location[0].getAddress().get(0).getStreet());

        //Filter the address based the type of address
        Address address = location[0].getAddress().stream().filter(x -> x.getType().equalsIgnoreCase(addressType))
                .findFirst().orElse(null);
        assertThat(address.getStreet(), equalTo(streetName));
    }

    @Then("I should see the author name as {string} with json validation")
    public void iShouldSeeTheAuthorNameAsWithJsonValidation(String authorName) {
        //Returns body as String
        var responseBody = response.getBody().asString();

        //Assert
        assertThat(responseBody, matchesJsonSchemaInClasspath("post.json"));
    }
}
