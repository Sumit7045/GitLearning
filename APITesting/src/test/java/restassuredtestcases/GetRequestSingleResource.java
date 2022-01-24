package restassuredtestcases;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.filter.log.LogDetail;

public class GetRequestSingleResource {
	@Test
	public void ListTest() {
		given()
		.get("https://reqres.in/api/unknown/2")
	.then()
		.statusCode(200)
		.log().ifValidationFails(LogDetail.STATUS)
		.log().status()
		.log().body();
}
}
