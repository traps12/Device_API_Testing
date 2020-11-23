package stepDefinition;

import common.DeviceConfiguration;
import common.DeviceUtility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetState {
    RequestSpecification request;
    DeviceUtility deviceUtility;
    DeviceConfiguration config;
    Response response;
    String name, ip, color, brightness;

    @Given("state API endpoint")
    public void state_api_endpoint() {
        config = DeviceConfiguration.getInstance();
        deviceUtility = new DeviceUtility();
        request = deviceUtility.createNewRequestSpec(config.getBaseURI());
    }

    @Given("device is connected")
    public void device_is_connected() {
        deviceUtility.connectDevice(request,config.getConnectURL());
        request = deviceUtility.createNewRequestSpec(config.getBaseURI());
    }


    @When("I perform get state of connected device")
    public void i_perform_get_state_of_connected_device() {
        response = request.get(config.getStateUrl());
    }

    @Then("get state information about device")
    public void get_state_information_about_device() {
      name = response.getBody().jsonPath().getString("name");
      ip = response.getBody().jsonPath().getString("ip");
      color = response.getBody().jsonPath().getString("color");
      brightness = response.getBody().jsonPath().get("brightness").toString();
    }

    @Then("response structure should match with json schema of state API")
    public void response_structure_should_match_with_json_schema_of_state_api() {
        response.then().assertThat().body(matchesJsonSchemaInClasspath("state_response_json_schema.json"));
    }

    @Given("device is disconnected")
    public void device_is_disconnected() {
        response= deviceUtility.disconnectDevice(request,"/disconnect");
    }

    @Then("response from state api should contains success false message")
    public void response_from_state_api_should_contains_success_false_message() {
        Assert.assertEquals(200, response.getStatusCode());
        String successMsg = response.getBody().jsonPath().getString("success");
        Assert.assertEquals("false", successMsg);
    }


}
