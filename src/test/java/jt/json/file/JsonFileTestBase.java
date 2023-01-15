package jt.json.file;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import jt.common.Config;
import jt.util.TestResourceLocator;

public class JsonFileTestBase {
	protected static TestResourceLocator resource;
	protected Config config;

	@BeforeAll
	static void setUpAll() {
		resource = new TestResourceLocator("json/file");
	}

	@BeforeEach
	void setUpEach() {
		config = new Config();
	}
}
