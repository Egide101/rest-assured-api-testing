package tests;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static org.hamcrest.Matchers.equalTo;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;

class FindFixtureById {

	@BeforeEach
	void setUp() throws Exception {
	    baseURI = "http://localhost";
	    port = 3000;
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void givenValidFixtureIdThenReturnsOk() {
		given()
			.contentType(ContentType.JSON).
		with()
			.pathParam("fixtureId", 1).
		when()
			.get("fixture/{fixtureId}").
		then()
			.assertThat().statusCode(HttpStatus.SC_OK);
	}
	
	@Test
	void eachOfThreeFixturesHasAFixtureId() {
		given()
			.contentType(ContentType.JSON).
		with()
			.pathParam("fixtureId", 1).
		when()
			.get("fixture/{fixtureId}").
		then()
			.assertThat().body("fixtureId",equalTo("1"));
	}

}
