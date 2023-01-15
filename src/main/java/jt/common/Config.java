package jt.common;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import jt.csv.config.CsvConfig;
import jt.json.config.JsonConfig;

public class Config  {
	
	private Charset inputCharset;
	private Charset outputCharset;
	
	public final CsvConfig csv;
	public final JsonConfig json;
	
	public Config() {
		this.inputCharset = StandardCharsets.UTF_8;
		this.outputCharset  = StandardCharsets.UTF_8;
		this.csv = new CsvConfig();
		this.json = new JsonConfig();
	}
	
	public Config(Config other) {
		this.inputCharset = other.inputCharset;
		this.outputCharset = other.outputCharset;
		this.csv = new CsvConfig(other.csv);
		this.json = new JsonConfig(other.json);
	}
	
	public void setCharset(Charset charset) {
		setInputCharset(charset);
		setOutputCharset(charset);
	}

	public Charset getInputCharset() {
		return inputCharset;
	}

	public void setInputCharset(Charset inputCharset) {
		this.inputCharset = Objects.requireNonNull(inputCharset);
	}

	public Charset getOutputCharset() {
		return outputCharset;
	}

	public void setOutputCharset(Charset outputCharset) {
		this.outputCharset = Objects.requireNonNull(outputCharset);
	}
	


}
