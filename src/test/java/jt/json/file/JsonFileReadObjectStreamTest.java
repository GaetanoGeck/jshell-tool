package jt.json.file;

import java.io.File;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class JsonFileReadObjectStreamTest extends JsonFileTestBase {

	@Test
	void whenThreeStringInInputFileThenReturnStreamOfThreeStrings() {
		Stream<String> stream = readObjectStream("strings.utf8.json", String.class);
		
		Assertions.assertThat(stream)
				.contains("alpha", "beta", "gamma");
	}
	
	@Test
	void whenThreeIntegersInInputFileThenReturnStreamOfThreeIntegers() {
		Stream<Integer> stream = readObjectStream("integers.utf8.json", Integer.class);
		
		Assertions.assertThat(stream)
				.containsExactly(123, 456, 789);
	}
	
	private <T> Stream<T> readObjectStream(String resourcePath, Class<T> objectClass) {
		File file = resource.getFile(resourcePath);
		JsonFile jf = new JsonFile(config, file);
		return jf.readObjectStream(objectClass);
	}

}
