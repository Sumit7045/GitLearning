package restassuredtestcases;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class PostRequestLoginSuccessful {
	
	@Test
	public void testLoginSuccessful() 
	{
		baseURI = "https://reqres.in/api";

		JSONObject postData = new JSONObject();
		postData.put("email", "eve.holt@reqres.in");
		postData.put("password", "pistol");

		given()
		.header("Content-Type", "application/json")
		.accept(ContentType.JSON)
		.body(postData.toJSONString())
		.when()
		.post("/login")
		.then()
		.statusCode(200)
		.log().body();
	}
}
