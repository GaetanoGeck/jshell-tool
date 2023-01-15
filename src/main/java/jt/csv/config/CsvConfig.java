package jt.csv.config;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVFormat.Builder;

public class CsvConfig {
	private CSVFormat baseFormat;
	private String delimiter;
	private boolean isWithHeader;
	private String[] headerNames;

	public CsvConfig() {
		this.baseFormat = CSVFormat.EXCEL;
		this.delimiter = ";";
		this.isWithHeader = true;
		this.headerNames = new String[0];
	}

	public CsvConfig(CsvConfig other) {
		this.baseFormat = other.baseFormat;
		this.delimiter = other.delimiter;
		this.isWithHeader = other.isWithHeader;
		this.headerNames = other.headerNames;
	}
	
	public boolean isWithHeader() {
		return isWithHeader;
	}

	public CsvConfig withHeader(String... header) {
		this.isWithHeader = true;
		this.headerNames = header;
		return this;
	}

	public CsvConfig withoutHeader() {
		this.isWithHeader = false;
		this.headerNames = null;
		return this;
	}

	public CSVFormat getInputFormat() {
		return getFormat(true);
	}

	public CSVFormat getOutputFormat() {
		return getFormat(false);
	}

	private CSVFormat getFormat(boolean input) {
		Builder b = baseFormat.builder();
		applyDelimiter(b);
		applyHeader(b, input);
		return b.build();
	}

	private void applyDelimiter(Builder b) {
		b.setDelimiter(delimiter);
	}

	private void applyHeader(Builder b, boolean input) {
		if (isWithHeader && input) {
			b.setSkipHeaderRecord(true);
			b.setHeader(headerNames);
		} else {
			b.setSkipHeaderRecord(false);
		}
	}
}