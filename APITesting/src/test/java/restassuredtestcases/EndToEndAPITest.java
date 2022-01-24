package restassuredtestcases;

import org.json.simple.JSONObject;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import java.sql.DatabaseMetaData;
import org.testng.annotations.Test;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class EndToEndAPITest {
	@Test
	public void validationOfCompleteFlow()
	{


	//Register a user >> extract id and token
	baseURI = "https://reqres.in/api";



	JSONObject json = new JSONObject();
	json.put("email", "eve.holt@reqres.in");
	json.put("password", "pistol");



	System.out.println(json.toJSONString());



	int id =given()
	.body(json.toJSONString())
	.header("charset", "utf-8")
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)



	.when()
	.post("/register")

	.then()
	.extract().path("id");

	System.out.println(id);

	String token =given()
	.body(json.toJSONString())
	.header("charset", "utf-8")
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)



	.when()
	.post("/register")

	.then()
	.extract().path("token");

	System.out.println(token);

	// Log in with the above created user >> extract token

	String Logintoken = given()
	.body(json.toJSONString())
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	.header("charset","utf-8")
	.when()
	.post("/login")
	.then()
	.extract().path("token");

	//run get single user to find the same user id >> validate name and job details
	int userId =given()
	.body(json.toJSONString())
	.header("charset", "utf-8")
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)



	.when()
	.get("/users/"+id)

	.then()
	.body("data.id",equalTo(id))
	.body("data.email",equalTo("eve.holt@reqres.in"))
	.body("data.first_name", equalTo("Eve"))
	.body("data.last_name", equalTo("Holt"))
	.extract().path("data.id");

	System.out.println(userId);

	//then update user details >> add validations >> search user and validate again

	json.put("name", "Supriya");
	json.put("job","Quality Engineer");

	given()
	.header("Content-Type","application/json")
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	.header("Connection","keep-alive")
	.body(json.toJSONString())
	.when()
	.put("/users/"+id)
	.then()
	.statusCode(200)
	.body("name", equalTo("Supriya"))
	.body("job", equalTo("Quality Engineer"))
	.log().body();

	//Patch same user >> validate response >> search user >> validate
	json.put("job","Quality Control");

	given()
	.header("Content-Type","application/json")
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	.header("Connection","keep-alive")
	.body(json.toJSONString())
	.when()
	.patch("/users/"+id)
	.then()
	.statusCode(200)
	.body("name", equalTo("Supriya"))
	.body("job", equalTo("Quality Control"))
	.log().body();

	//Delete same user >> validate code >> search user >> validate
	given()
	.header("Content-Type","application/json")
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	.body(json.toJSONString())

	.when()
	.delete("/users/"+id)
	.then()
	.statusCode(204)
	.log().ifValidationFails(LogDetail.STATUS)
	.log().body();

	}
	}


