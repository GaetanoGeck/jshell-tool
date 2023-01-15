package jt.csv.file;

import java.io.File;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CsvFileReadListTest extends CsvFileTestBase {

	@Test
	void whenThreeRecordsWithoutHeaderInInputFileThenReturnListOfThreeRecords() {
		config.csv.withoutHeader();
		List<CSVRecord> list = readList("places.utf8.csv");

		Assertions.assertThat(list) //
				.hasSize(3);
		assertThatRecordContainsExactly(list.get(0), "France", "Paris");
		assertThatRecordContainsExactly(list.get(1), "Germany", "Berlin");
		assertThatRecordContainsExactly(list.get(2), "Spain", "Madrid");
	}

	@Test
	void whenThreeRecordsWithHeaderInInputFileThenReturnListOfThreeRecords() {
		List<CSVRecord> list = readList("places.header.utf8.csv");

		Assertions.assertThat(list) //
				.hasSize(3);
		assertThatRecordContainsExactly(list.get(0), "France", "Paris");
		assertThatRecordContainsExactly(list.get(1), "Germany", "Berlin");
		assertThatRecordContainsExactly(list.get(2), "Spain", "Madrid");

		Assertions.assertThat(headerNames(list.get(0))) //
				.containsExactly("State", "Capital");
	}
	
	private static List<String> headerNames(CSVRecord record) {
		CSVParser parser = record.getParser();
		return parser.getHeaderNames();
	}

	private static void assertThatRecordContainsExactly(CSVRecord record, String... values) {
		Assertions.assertThat(record.toList()) //
				.containsExactly(values);
	}

	private List<CSVRecord> readList(String resourcePath) {
		File file = resource.getFile(resourcePath);
		CsvFile cf = new CsvFile(config, file);
		return cf.readList();
	}

}
