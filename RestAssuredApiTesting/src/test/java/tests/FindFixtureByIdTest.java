package tests;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static org.hamcrest.Matchers.equalTo;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;

class FindFixtureByIdTest {

	@BeforeAll
	static void setUp() throws Exception {
	    baseURI = "http://localhost";
	    port = 3000;
	}

	@Test
	void get_fixture_by_fixtureId() {
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
	void get_fixture_by_fixtureId_assert_first_fixtureId_is_1() {
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
