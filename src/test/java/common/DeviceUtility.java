package common;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import common.DeviceConfiguration;


/**
 * Class for utility purpose,
 */
public class DeviceUtility {

    RequestSpecification requestSpec;
    DeviceConfiguration config;
    String deviceIp = "192.168.100.10";
    public RequestSpecification createNewRequestSpec(String baseUri) {
        requestSpec = null;
        RestAssured.baseURI = baseUri;
        requestSpec = RestAssured.given();
        return requestSpec;
    }

    /**
     * @param apiURL to be called
     * @return response body
     */
    public Response makeCallToAPI(String apiURL){
        return requestSpec.get(apiURL);
    }

    public String getDataFromResponseBody(Response response, String obj){
        String data = null;
        if(response != null && response.getStatusCode() == 200){
            JSONArray jsonarray = new JSONArray(response.getBody().asString());
            JSONObject jsonobject = jsonarray.getJSONObject(0);
            data = jsonobject.getString(obj);

        }
        return data;
    }

    public Response connectDevice(RequestSpecification request,String url ){
        org.json.simple.JSONObject requestBody = new org.json.simple.JSONObject();
        requestBody.put("ip", deviceIp);

        request.header("Content-Type", "application/json");
        request.body(requestBody.toJSONString());
        return  request.post(url);
    }

    public Response setBrightness(RequestSpecification request,String url , String brightnessVal){
        return callToPostApi(request,"brightness",brightnessVal,url);
    }

    public Response setColor(RequestSpecification request,String url, String colorValue ){
        return callToPostApi(request,"color",colorValue,url);
    }


    public Response setName(RequestSpecification request,String url, String nameValue){
        return callToPostApi(request,"name",nameValue,url);
    }

    public Response disconnectDevice(RequestSpecification request,String url ){
       return request.post(url);
    }

    public String getStateAttributeValue(RequestSpecification request,String attribute){
        config = DeviceConfiguration.getInstance();
        Response response = request.get(config.getStateUrl());
        String attributeValue = response.getBody().jsonPath().getString(attribute);
        return attributeValue;
    }

    public Response callToPostApi(RequestSpecification request, String attr, String attrVal, String url) {
        org.json.simple.JSONObject requestBody = new org.json.simple.JSONObject();
        requestBody.put(attr, attrVal);
        request.header("Content-Type", "application/json");
        request.body(requestBody.toJSONString());
        return  request.post(url);
    }

    public String getDataFromResponse(Response response, String data){
        return response.getBody().jsonPath().getString(data);
    }
}
