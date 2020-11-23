package stepDefinition;

import common.DeviceConfiguration;
import common.DeviceUtility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.json.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class GetDevice {

    DeviceUtility deviceUtility;
    DeviceConfiguration config;
    String invalidUrl = "/invalid";

    public static RequestSpecification httpRequest;
    public static Response response;

    @Given("devices API endpoint")
    public void devices_api_endpoint() {
        config = DeviceConfiguration.getInstance();
        deviceUtility = new DeviceUtility();
        deviceUtility.createNewRequestSpec(config.getBaseURI());
    }

    @When("I perform GET operation")
    public void i_perform_get_operation() {
        response = deviceUtility.makeCallToAPI(config.getDeviceURL());
    }

    @Then("I get name and IP of devices")
    public void i_get_name_and_ip_of_devices() {
        Assert.assertEquals(200, response.getStatusCode());
        String jsonStr = response.getBody().asString();
        String ip =  deviceUtility.getDataFromResponseBody(response, "ip");
    }

    @Then("response structure should match with json schema")
    public void response_structure_should_match_with_json_schema() {
        response.then().assertThat().body(matchesJsonSchemaInClasspath("device_response_json_schema.json"));
    }

    @When("I perform GET operation with invalid url")
    public void i_perform_get_operation_with_invalid_url() {
        response = deviceUtility.makeCallToAPI(invalidUrl);
    }

    @Then("I should get 404 status")
    public void i_should_get_status() {
        Assert.assertEquals(404, response.getStatusCode());
    }

}
