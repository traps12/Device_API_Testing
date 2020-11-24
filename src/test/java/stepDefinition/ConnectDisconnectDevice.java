package stepDefinition;

import common.DeviceConfiguration;
import common.DeviceUtility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.AfterClass;
import org.junit.Assert;

public class ConnectDisconnectDevice {
    RequestSpecification request;
    Response response;
    DeviceConfiguration config = DeviceConfiguration.getInstance();
    DeviceUtility deviceUtility = new DeviceUtility();
    String successMsg;

    @Given("API endpoint")
    public void api_endpoint() {
        request = deviceUtility.createNewRequestSpec(config.getBaseURI());
    }

    @When("disconnect device using post operation")
    public void disconnect_device_using_post_operation() {
        request = deviceUtility.createNewRequestSpec(config.getBaseURI());
        response= deviceUtility.disconnectDevice(request,"/disconnect");
        successMsg = deviceUtility.getDataFromResponse(response,"success");
    }

    @When("connect to device using post operation")
    public void connect_to_device_using_post_operation() {
        response = deviceUtility.connectDevice(request, config.getConnectURL());
    }

    @Then("device should get connected")
    public void device_should_get_connected() {
        Assert.assertEquals(200, response.getStatusCode());
        String successMsg = deviceUtility.getDataFromResponse(response,"success");
        Assert.assertEquals("true", successMsg);
    }

    @Then("device should get disconnected")
    public void device_should_get_disconnected() {
        Assert.assertEquals("true", successMsg);
    }


}
