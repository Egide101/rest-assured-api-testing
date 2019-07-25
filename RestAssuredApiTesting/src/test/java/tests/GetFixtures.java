package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GetFixtures {
	@BeforeEach
	void setUp() throws Exception {
	    baseURI = "http://localhost";
	    port = 3000;
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void getAllFixturesAndAssetThatThereAreThreeFixturesEachWithFictureId() {
		given()
			.get("fixtures").
		then().assertThat()
			.body("size()",is(3)).
		and().assertThat()
			.body("fixtureId",hasSize(3));
	}
}
