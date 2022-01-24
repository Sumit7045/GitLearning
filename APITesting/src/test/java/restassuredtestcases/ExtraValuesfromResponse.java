package restassuredtestcases;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class ExtraValuesfromResponse {
	@Test
	public void test() {

		baseURI = "https://reqres.in/api";

		JSONObject reqData = new JSONObject();
		reqData.put("email", "eve.holt@reqres.in");
		reqData.put("password", "cityslicka");

		String token = given().
		body(reqData.toJSONString()).contentType(ContentType.JSON).accept(ContentType.JSON)
		.header("charset", "utf-8").when().post("/login").then().extract().path("token");

		System.out.println(token);
}
}
