package jt.text;

import java.io.File;

import jt.common.Config;
import jt.text.file.TextFile;

public class Text {
	private final Config config;

	public Text(Config config) {
		super();
		this.config = config;
	}

	public TextFile file(File file) {
		Config fileConfig = new Config(config);
		return new TextFile(fileConfig, file);
	}
	
	public TextFile file(String path) {
		Config fileConfig = new Config(config);
		return new TextFile(fileConfig, path);
	}
}
