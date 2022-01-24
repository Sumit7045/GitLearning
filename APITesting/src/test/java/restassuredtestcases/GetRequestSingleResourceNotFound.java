package restassuredtestcases;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.filter.log.LogDetail;

public class GetRequestSingleResourceNotFound {
	@Test
	public void UserNotFound() {
		

		baseURI = "https://reqres.in/api";
		given()
			.get("https://reqres.in/api/unknown/23")
		.then()
			.statusCode(404)
			.log().ifValidationFails(LogDetail.STATUS)
			.log().status()
			.log().body();
	}
}
