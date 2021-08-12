package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import utilities.RestAssuredExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class POSTProfileSteps {

    private static ResponseOptions<Response> response;

    @Given("I perform Post Operation for {string} with body")
    public void iPerformPostOperationForWithBody(String url, DataTable table) {
        //Cucumber table data retrieve
        List<List<String>> data = table.asLists();

        //Set Body
        HashMap<String,String> body  = new HashMap<>();
        body.put("name",data.get(1).get(0));

        //Set Path Param
        HashMap<String,String> pathParams  = new HashMap<>();
        pathParams.put("profileNo",data.get(1).get(1));

        System.out.println("Request Name---->" +data.get(1).get(0));
        System.out.println("Request Id------>" +data.get(1).get(1));

        //Perform post Operation
        response  = RestAssuredExtension.PostOpsWithBodyAndPathParams(url,pathParams,body);
    }

    @Then("I should see the response has name as {string}")
    public void iShouldSeeTheResponseHasNameAs(String name) {
        System.out.println("Response Name---->" + response.getBody().jsonPath().get("name"));
        assertThat(response.getBody().jsonPath().get("name"),equalTo(name));

    }
    @Given("I perform Post Operation for {string}")
    public void iPerformPostOperationFor(String arg0) {
        BDDStyledMethod.performPostWithBodyParameter();
    }


    @Given("I ensure Perform POST operation for {string} with body as")
    public void iEnsurePerformPOSTOperationForWithBodyAs(String url,DataTable table) {
        List<List<String>> data = table.asLists();
        //Body
        Map<String,String> body=new HashMap<>();
        body.put("id",data.get(1).get(0));
        body.put("title",data.get(1).get(1));
        body.put("author",data.get(1).get(2));
        //Perform post operation
        RestAssuredExtension.PostOpsWithBody(url,body);


    }

    @And("I Perform DELETE operation for {string}")
    public void iPerformDELETEOperationFor(String url,DataTable table) {
        List<List<String>> data = table.asLists();
        //Path Params
        Map<String,String> pathParams=new HashMap<>();
        pathParams.put("postid",data.get(1).get(0));
        //Perform Delete Operation
        RestAssuredExtension.DeleteOpsWithPathParams(url,pathParams);
    }

    @And("I perform GET operation with path parameter for {string}")
    public void iPerformGETOperationWithPathParameterFor(String url,DataTable table) {
        List<List<String>> data = table.asLists();
        //Path Params
        Map<String,String> pathParams=new HashMap<>();
        pathParams.put("postid",data.get(1).get(0));
        //Perform Get Operation
        response  = RestAssuredExtension.GetOpsWithPathParams(url,pathParams);
    }

    @Then("I {string} see the body with title as {string}")
    public void iShouldNotSeeTheBodyWithTitleAs(String condition,String title) {
        if(condition.equalsIgnoreCase("should not")) {
            assertThat(response.getBody().jsonPath().get("title"), IsNot.not(title));
        }else {
            assertThat(response.getBody().jsonPath().get("title"), is(title));
        }

    }

    @And("I Perform PUT operation for {string}")
    public void iPerformPUTOperationFor(String url,DataTable table) {
        List<List<String>> data = table.asLists();
        //Body
        Map<String,String> body=new HashMap<>();
        body.put("id",data.get(1).get(0));
        body.put("title",data.get(1).get(1));
        body.put("author",data.get(1).get(2));
        //Path Params
        Map<String,String> pathParams=new HashMap<>();
        pathParams.put("postid",data.get(1).get(0));

        RestAssuredExtension.PUTOpsWithBodyAndPathParams(url,body,pathParams);

    }
}
