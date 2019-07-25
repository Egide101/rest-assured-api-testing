package helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.google.gson.Gson;

import models.Fixture;

public class TestData {

	public static Fixture getFixtureTestData() {
		Fixture fixture = null;

		String fileName = "testData/FixtureData.json";
		ClassLoader classLoader = new TestData().getClass().getClassLoader();

		File file = new File(classLoader.getResource(fileName).getFile());

		String content;
		try {
			content = new String(Files.readAllBytes(file.toPath()));

			Gson gson = new Gson();
			fixture = gson.fromJson(content, Fixture.class);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return fixture;
	}
}
