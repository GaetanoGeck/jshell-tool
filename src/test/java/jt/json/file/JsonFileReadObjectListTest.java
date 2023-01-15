package jt.json.file;

import java.io.File;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class JsonFileReadObjectListTest extends JsonFileTestBase {

	@Test
	void whenThreeStringsInInputFileThenReturnListOfThreeStrings() {
		List<String> list = readObjectList("strings.utf8.json", String.class);
		
		Assertions.assertThat(list) //
				.contains("alpha", "beta", "gamma");
	}
	
	@Test
	void whenThreeIntegersInInputFileThenReturnListOfThreeIntegers() {
		List<Integer> list = readObjectList("integers.utf8.json", Integer.class);
		
		Assertions.assertThat(list) //
				.containsExactly(123, 456, 789);
	}
	
	private <T> List<T> readObjectList(String resourcePath, Class<T> objectClass) {
		File file = resource.getFile(resourcePath);
		JsonFile jf = new JsonFile(config, file);
		return jf.readObjectList(objectClass);
	}

}
