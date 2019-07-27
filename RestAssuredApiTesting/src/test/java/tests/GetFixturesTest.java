package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class GetFixturesTest {
	
	@BeforeAll
	static void setUp() throws Exception {
	    baseURI = "http://localhost";
	    port = 3000;
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	
	/*
	 * 1. Retrieve all fixtures.
	 * Assert that there are 3 fixtures within the returned object. 
	 * Assert that each of the 3 fixtures has a fixtureId value.
	 */
	
	@Test
	void get_all_fixtures_and_asset_that_there_are_three_fixtures_each_with_fixtureId() {
		given()
			.get("fixtures").
		then().assertThat()
			.body("size()",is(3)).
		and().assertThat()
			.body("fixtureId",hasSize(3));
	}
}
