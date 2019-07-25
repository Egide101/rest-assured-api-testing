package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Duration;
import java.time.Instant;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import io.restassured.http.ContentType;
import models.Fixture;

public class StoreNewFixture {
	@BeforeEach
	void setUp() throws Exception {
		baseURI = "http://localhost";
		port = 3000;
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void storeANewFixtureInDb() {

		Fixture fixture = TestData.getFixtureTestData();
		fixture.setFixtureId("5");
		String fixtureJson = new Gson().toJson(fixture);
		
		removeFixtureWithFixtureId("5");
		
		//Add new fixture
		given()
			.contentType(ContentType.JSON).
		and()
			.request().body(fixtureJson).
		when()
			.post("/fixture").
		then().assertThat()
			.statusCode(HttpStatus.SC_OK);
		
		//get the new fixture 
		given()
			.pathParam("fixtureId", "5").
		when()
			.get("fixture/{fixtureId}").
		then()
			.assertThat().statusCode(HttpStatus.SC_OK).
		and()
			.body("footballFullState.teams.teamId[0]",is("HOME"));
	}
	
	@Test
	void storeANewFixture_ThenReadAsap() {
		String newFixtureId = "6";
		Fixture fixture = TestData.getFixtureTestData();
		fixture.setFixtureId(newFixtureId);
		String fixtureJson = new Gson().toJson(fixture);
		
		removeFixtureWithFixtureId(newFixtureId);
		
		//Add new fixture
		given()
			.contentType(ContentType.JSON).
		and()
			.request().body(fixtureJson).
		when()
			.post("/fixture").
		then().assertThat()
			.statusCode(HttpStatus.SC_OK);
		
		//retrieve the new fixture within 3 minutes or timeout
		String newFixture = getFixture(180,newFixtureId);
		
		System.out.println(newFixture);
		
	}
	
	@Test
	void createFixture_ThenDeleteFixture() {
		String newFixtureId = "6";
		Fixture fixture = TestData.getFixtureTestData();
		fixture.setFixtureId(newFixtureId);
		String fixtureJson = new Gson().toJson(fixture);
		
		removeFixtureWithFixtureId(newFixtureId);
		
		CreateNewFixture(fixtureJson);
		
		assertNotNull(getFixture(180,newFixtureId),"Fixture has not been created");
		
		removeFixtureWithFixtureId(newFixtureId);
		
		assertNull(getFixture(180,newFixtureId),"Fixture has not been deleted");
	}
	

	private void CreateNewFixture(String fixtureJson) {
		given()
			.contentType(ContentType.JSON).
		and()
			.request().body(fixtureJson).
		when()
			.post("/fixture").
		then().assertThat()
			.statusCode(HttpStatus.SC_OK);
	}
	
	private String getFixture(int timeoutInseconds,String fixtureId) {
		int statusCode = 0;
		String newFixture = null;
		
		Instant start = Instant.now();

		long timeElapsed;
		
		do {
			try {
				
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			statusCode = given()
					.pathParam("fixtureId", fixtureId).
				when()
					.get("fixture/{fixtureId}").
				then().extract().statusCode();
						
			timeElapsed = Duration.between(start, Instant.now()).getSeconds();
		} while (statusCode != HttpStatus.SC_OK && timeElapsed < timeoutInseconds);
		
		if(statusCode == HttpStatus.SC_OK) {
		newFixture = 
			given()
				.pathParam("fixtureId", fixtureId).
			when()
				.get("fixture/{fixtureId}").
			then().extract().body().asString();
		}
		
		return newFixture;
	}

	private void removeFixtureWithFixtureId(String fixtureId) {
		given()
			.pathParam("fixtureId", fixtureId).
		when()
			.delete("fixture/{fixtureId}");
	}
}
