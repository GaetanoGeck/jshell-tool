package jt.csv.file;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import jt.common.Config;
import jt.util.TestResourceLocator;

class CsvFileTestBase {

	protected static TestResourceLocator resource;
	protected Config config;

	@BeforeAll
	static void setUpAll() {
		resource = new TestResourceLocator("csv/file");
	}

	@BeforeEach
	void setUpEach() {
		config = new Config();
	}

}
