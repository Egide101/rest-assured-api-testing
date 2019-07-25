package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import helper.FixtureApi;
import helper.TestData;
import models.Fixture;

public class StoreNewFixture {
	@BeforeAll
	static void setUp() throws Exception {
		baseURI = "http://localhost";
		port = 3000;
	}

	@AfterAll
	static void tearDown() throws Exception {
		  FixtureApi.deleteFixtureByFixtureId("5");
		  FixtureApi.deleteFixtureByFixtureId("6");
		  FixtureApi.deleteFixtureByFixtureId("7");
	}

	/*
	 * 2. Using the model guide in apiDocs.html, store a new fixture in the
	 * database.
	 */
	@Test
	void store_new_fixture_and_assert_first_object_of_array_teamId_HOME() {
		String newFixtureId = "5";
		Fixture fixture = TestData.getFixtureTestData();
		fixture.setFixtureId(newFixtureId);
		String fixtureJson = new Gson().toJson(fixture);
		
		//make sure the fixture does not already exist
		FixtureApi.deleteFixtureByFixtureId(newFixtureId);
		
		//Add new fixture
		FixtureApi.CreateNewFixture(fixtureJson);
		
		//assert new fixture has been added
		assertNotNull(FixtureApi.getFixture(180,newFixtureId),"Fixture has not been created");
		
		//Assert, within the teams array, that the first object has a teamId of 'HOME'
		given()
			.pathParam("fixtureId", newFixtureId).
		when()
			.get("fixture/{fixtureId}").
		then()
			.assertThat().statusCode(HttpStatus.SC_OK).
		and()
			.body("footballFullState.teams.teamId[0]",is("HOME"));
	}

	/*
	 * 3.To simulate latency within systems, there is an intentional, random delay
	 * to store a new fixture on the server. Bearing the delay in mind, create a new
	 * fixture and then retrieve it as soon as it's available
	 */
	@Test
	void store_new_fixture_then_retrieve_it_asap() {
		String newFixtureId = "6";
		Fixture fixture = TestData.getFixtureTestData();
		fixture.setFixtureId(newFixtureId);
		String fixtureJson = new Gson().toJson(fixture);
		
		//make sure the fixture does not already exist
		FixtureApi.deleteFixtureByFixtureId(newFixtureId);
		
		//Add new fixture
		FixtureApi.CreateNewFixture(fixtureJson);
		
		//retrieve the new fixture asap and within 3 minutes
		String newFixture = FixtureApi.getFixture(180,newFixtureId);
		
		assertNotNull(newFixture,"new fixture has not been created");		
	}
	
	/*
	 * 4.Create and delete a new fixture. Assert that the fixture no longer exists.
	 */
	@Test
	void create_fixture_then_delete_fixture() {
		String newFixtureId = "7";
		Fixture fixture = TestData.getFixtureTestData();
		fixture.setFixtureId(newFixtureId);
		String fixtureJson = new Gson().toJson(fixture);
		
		//make sure that the fixture is not in the database already
		FixtureApi.deleteFixtureByFixtureId(newFixtureId);
		
		//create new fixture
		FixtureApi.CreateNewFixture(fixtureJson);
		
		//assert new fixture has been added
		assertNotNull(FixtureApi.getFixture(180,newFixtureId),"Fixture has not been created");
		
		//delete the fixture created
		FixtureApi.deleteFixtureByFixtureId(newFixtureId);
		
		//assert that the fixture has been deleted
		assertNull(FixtureApi.getFixture(180,newFixtureId),"Fixture has not been deleted");
	}
}
