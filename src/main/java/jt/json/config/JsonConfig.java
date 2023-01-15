package jt.json.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonConfig {
	private GsonBuilder gsonBuilder;
	private boolean enablePrettyPrinting;

	public JsonConfig() {
		this.gsonBuilder = new GsonBuilder();
		this.enablePrettyPrinting = true;
	}

	public JsonConfig(JsonConfig other) {
		this.gsonBuilder = new GsonBuilder();
		this.enablePrettyPrinting = other.enablePrettyPrinting;
	}

	public Gson getGson() {
		applyPrettyPrinting();
		return gsonBuilder.create();
	}

	private void applyPrettyPrinting() {
		if (enablePrettyPrinting) {
			gsonBuilder.setPrettyPrinting();
		}
	}

}
