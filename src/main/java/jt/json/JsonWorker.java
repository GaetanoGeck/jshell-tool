package jt.json;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

import jt.common.Config;
import jt.common.InputOutputException;

public abstract class JsonWorker {
	protected Config config;

	public JsonWorker(Config config) {
		super();
		this.config = config;
	}

	public Gson createGson() {
		return config.json.getGson();
	}

	public <T> List<T> readObjectList(Class<T> elementClass) {
		Gson gson = createGson();
		JsonReader jr = createJsonReader(gson);
		jr.setLenient(true);
		try {
			return collectParseJsonToList(jr, gson, elementClass);
		} catch (JsonIOException | JsonSyntaxException | IOException e) {
			throw new InputOutputException(e);
		}
	}

	public <T> Stream<T> readObjectStream(Class<T> elementClass) {
		return readObjectList(elementClass).stream();
	}

	private <T> List<T> collectParseJsonToList(JsonReader jr, Gson gson, Class<T> elementClass)
			throws JsonIOException, JsonSyntaxException, IOException {
		List<T> list = new ArrayList<>();
		while (jr.hasNext()) {
			T element = gson.fromJson(jr, elementClass);
			list.add(element);
		}
		return list;
	}

	/**
	 * Extract JSON elements according to the specified JSON-path.
	 * 
	 * @param jsonPath path
	 * @return result
	 */
	public abstract <T> T extract(String jsonPath);
	
	/**
	 * Write single object as JSON.
	 * @param object Object
	 */
	public  void write(Object object) {
		writeEach(Collections.singletonList(object));
	}
	
	/**
	 * Write all objects from the iterable.
	 * @param <T> Type of elements
	 * @param iterable Iterable
	 */
	public <T> void writeEach(Iterable<T> iterable) {
		Gson gson = createGson();
		try (BufferedWriter w = createWriter()) {
			iterable.forEach(obj -> append(w, obj, gson));
		} catch (IOException e) {
			throw new InputOutputException(e);
		}
	}
	
	private void append(BufferedWriter w, Object object, Gson gson) {
		gson.toJson(object, (Appendable) w);
		try {
			w.newLine();
		} catch (IOException e) {
			throw new InputOutputException(e);
		}
	}

	protected abstract BufferedWriter createWriter();
	protected abstract JsonReader createJsonReader(Gson gson);
}
