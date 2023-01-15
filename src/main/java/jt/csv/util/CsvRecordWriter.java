package jt.csv.util;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import jt.common.Config;
import jt.common.InputOutputException;

public class CsvRecordWriter {
	private Config config;
	private CSVPrinter printer;

	public static void write(Config config, CSVPrinter printer, Iterable<CSVRecord> records) {
		CsvRecordWriter w = new CsvRecordWriter(config, printer);
		w.write(records);
	}

	private CsvRecordWriter(Config config, CSVPrinter printer) {
		super();
		this.config = config;
		this.printer = printer;
	}

	private void write(Iterable<CSVRecord> records) {
		Iterator<CSVRecord> it = records.iterator();
		if (it.hasNext()) {
			writeFirstRecord(it.next());
		}
		it.forEachRemaining(this::writeRecord);
	}

	private void writeFirstRecord(CSVRecord record) {
		writeHeaderIfEnabled(record);
		writeRecord(record);
	}

	private void writeHeaderIfEnabled(CSVRecord record) {
		if (isHeaderEnabled()) {
			List<String> headerNames = getHeaderNames(record);
			writeHeader(headerNames);
		}
	}

	private boolean isHeaderEnabled() {
		return config.csv.isWithHeader();
	}
	
	private List<String> getHeaderNames(CSVRecord record) {
		CSVParser parser = record.getParser();
		List<String> headerNames = parser.getHeaderNames();
		if (headerNames == null) {
			String msg = "Record definiert keinen Header";
			throw new InputOutputException(msg);
		}
		return headerNames;
	}

	private void writeHeader(List<String> headerNames) {
		try {
			printer.printRecord(headerNames);
		} catch (IOException e) {
			throw new InputOutputException(e);
		}
	}

	private void writeRecord(CSVRecord record) {
		try {
			printer.printRecord(record);
		} catch (IOException e) {
			throw new InputOutputException(e);
		}
	}
}
