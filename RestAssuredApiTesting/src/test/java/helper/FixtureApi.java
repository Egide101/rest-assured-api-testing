package helper;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;

import java.time.Duration;
import java.time.Instant;

import org.apache.http.HttpStatus;

import io.restassured.http.ContentType;

public class FixtureApi {
	
	static {
		baseURI = "http://localhost";
		port = 3000;
	}
	
	public static void CreateNewFixture(String fixtureJson) {		
		given()
			.contentType(ContentType.JSON).
		and()
			.request().body(fixtureJson).
		when()
			.post("/fixture").
		then().assertThat()
			.statusCode(HttpStatus.SC_OK);
	}
	
	public static String getFixture(int timeoutInseconds,String fixtureId) {
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

	public static void deleteFixtureByFixtureId(String fixtureId) {
		given()
			.pathParam("fixtureId", fixtureId).
		when()
			.delete("fixture/{fixtureId}");
	}
}