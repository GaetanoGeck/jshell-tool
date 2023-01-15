package jt.json;

import java.io.File;

import jt.common.Config;
import jt.json.file.JsonFile;

/**
 * Facade to JSON functionality.
 */
public class Json {
	private final Config config;

	public Json(Config config) {
		super();
		this.config = config;
	}
	
	public JsonFile file(File file) {
		Config fileConfig = new Config(config);
		return new JsonFile(fileConfig, file);
	}
	
	public JsonFile file(String path) {
		Config fileConfig = new Config(config);
		return new JsonFile(fileConfig, path);
	}
}
