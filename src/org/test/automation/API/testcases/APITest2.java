package org.test.automation.API.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import com.jayway.restassured.specification.RequestSpecification;

import net.sf.json.JSONObject;

public class APITest2 {

	@Test
	public void RegistrationSuccessful() {
		RestAssured.baseURI = "http://restapi.demoqa.com/customer";
		RequestSpecification request = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "Virender"); // Cast
		requestParams.put("LastName", "Singh");
		requestParams.put("UserName", "sdimpleuser2dd2011");
		requestParams.put("Password", "password1");

		requestParams.put("Email", "sample2ee26d9@gmail.com");
		request.body(requestParams.toString());
		Response response = request.post("/register");

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);

		ResponseBody body = response.getBody();

		System.out.println("Response Body is: " + body.asString());

		// String successCode = ((Object) response).jsonPath().get("SuccessCode");
		// Assert.assertEquals( "Correct Success code was returned", successCode,
		// "OPERATION_SUCCESS");
	}

}
