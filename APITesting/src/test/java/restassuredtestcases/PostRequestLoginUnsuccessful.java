package restassuredtestcases;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class PostRequestLoginUnsuccessful {
	@Test
	public void testLoginUnSuccessful() 
	{
		baseURI = "https://reqres.in/api";
		
		JSONObject reqData = new JSONObject();
		reqData.put("email", "sumitkadam6165@gmail.com");
		
		
		System.out.println(reqData.toJSONString());
		
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(reqData.toJSONString())
		.when()
			.post("/login")
		.then()
			.statusCode(400)
			.log().body();
	}
}
