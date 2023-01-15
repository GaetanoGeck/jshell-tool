package jt.json.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.jayway.jsonpath.JsonPath;

import jt.common.Config;
import jt.common.InputOutputException;
import jt.file.FileIO;
import jt.json.JsonWorker;

/**
 * File specific implementation of {@link JsonWorker}.
 */
public class JsonFile extends JsonWorker {
	private final File file;

	public JsonFile(Config config, File file) {
		super(config);
		this.file = Objects.requireNonNull(file);
	}

	public JsonFile(Config config, String path) {
		super(config);
		this.file = new File(Objects.requireNonNull(path));
	}

	@Override
	public <T> T extract(String jsonPath) {
		try {
			return JsonPath.read(file, jsonPath);
		} catch (IOException e) {
			throw new InputOutputException(e);
		}
	}
	
	@Override
	protected JsonReader createJsonReader(Gson gson) {
		BufferedReader fr = createFileReader();
		return gson.newJsonReader(fr);
	}
	
	@Override
	protected BufferedWriter createWriter() {
		return FileIO.createWriter(config, file);
	}

	private BufferedReader createFileReader() {
		return FileIO.createReader(config, file);
	}

}
