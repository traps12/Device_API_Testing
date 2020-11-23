package stepDefinition;

import common.DeviceConfiguration;
import common.DeviceUtility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class SetDeviceStateAttribute {

    RequestSpecification request;
    DeviceUtility deviceUtility;
    DeviceConfiguration config;
    Response response;

    @Given("API end points")
    public void api_end_points() {
        config = DeviceConfiguration.getInstance();
        deviceUtility = new DeviceUtility();
        request = deviceUtility.createNewRequestSpec(config.getBaseURI());
    }
    @Given("you are connected to a device")
    public void you_are_connected_to_a_device() {
        deviceUtility.connectDevice(request,config.getConnectURL());
        request = deviceUtility.createNewRequestSpec(config.getBaseURI());
    }

    @When("set brightness {string} using post operation")
    public void set_brightness_using_post_operation(String brightness) {
        response = deviceUtility.setBrightness(request,config.getBrightnessURL(), brightness);
    }

    @Then("brightness should change for connected device")
    public void brightness_should_change_for_connected_device() {
        Assert.assertEquals(200, response.getStatusCode());
        String successMsg = response.getBody().jsonPath().getString("success");
        Assert.assertEquals("true", successMsg);
    }

    @Then("verify {string} is updated to {string} for the state")
    public void verify_is_updated_to_for_the_state(String attribute, String attributeValue) {
        request = deviceUtility.createNewRequestSpec(config.getBaseURI());
        String actualAttributeValue = deviceUtility.getStateAttributeValue(request, attribute).toString();
        Assert.assertEquals(attributeValue, actualAttributeValue);
    }

    @When("set name {string} using post operation")
    public void set_name_using_post_operation(String name) {
        response = deviceUtility.setName(request,config.getNameURL(),name);
    }

    @Then("name should change for connected device")
    public void name_should_change_for_connected_device() {
        Assert.assertEquals(200, response.getStatusCode());
        String successMsg = response.getBody().jsonPath().getString("success");
        Assert.assertEquals("true", successMsg);
    }

    @When("set color {string} using post operation")
    public void set_color_using_post_operation(String color) {
        response = deviceUtility.setColor(request,config.getColorURL(),color);
    }

    @Then("color should change for connected device")
    public void color_should_change_for_connected_device() {
        Assert.assertEquals(200, response.getStatusCode());
        String successMsg = response.getBody().jsonPath().getString("success");
        Assert.assertEquals("true", successMsg);
    }

    @Then("response should contains success false message")
    public void response_should_contains_success_false_message() {
        Assert.assertEquals(200, response.getStatusCode());
        String successMsg = response.getBody().jsonPath().getString("success");
        Assert.assertEquals("false", successMsg);
    }

    @Then("verify {string} is not updated to {string} for the state")
    public void verify_is_not_updated_to_for_the_state(String attribute, String attributeValue) {
        request = deviceUtility.createNewRequestSpec(config.getBaseURI());
        String actualAttributeValue = deviceUtility.getStateAttributeValue(request, attribute).toString();
        Assert.assertNotEquals(attributeValue, actualAttributeValue);
    }

    @Given("disconnect device")
    public void disconnect_device() {
        response= deviceUtility.disconnectDevice(request,"/disconnect");
    }


}
