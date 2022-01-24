package restassuredtestcases;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.filter.log.LogDetail;

public class ListResource {
	
	@Test
	public void ListTest() {
		given()
		.get("https://reqres.in/api/unknown")
	.then()
		.statusCode(200)
		.log().ifValidationFails(LogDetail.STATUS)
		.log().status()
		.log().body();
	}
}
