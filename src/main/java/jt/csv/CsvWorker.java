package jt.csv;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import jt.common.Config;
import jt.common.InputOutputException;
import jt.csv.util.CsvRecordWriter;

public abstract class CsvWorker {
	protected final Config config;

	public CsvWorker(Config config) {
		this.config = config;
	}

	public Stream<CSVRecord> readStream() {
		CSVParser parser = getParser();
		return parser.stream();
	}

	public List<CSVRecord> readList() {
		return readStream().collect(Collectors.toList());
	}

	public void write(Iterable<CSVRecord> records) {
		try (Writer writer = getWriter(); CSVPrinter printer = getPrinter(writer)) {
			CsvRecordWriter.write(config, printer, records);
		} catch (IOException e) {
			throw new InputOutputException(e);
		}
	}
	
	public void write(Stream<CSVRecord> records) {
		Iterator<CSVRecord> it = records.iterator();
		write(IteratorUtils.asIterable(it));
	}

	public CsvWorker withHeader(String... header) {
		config.csv.withHeader(header);
		return this;
	}

	public CsvWorker withoutHeader() {
		config.csv.withoutHeader();
		return this;
	}

	public CSVParser getParser() {
		CSVFormat format = config.csv.getInputFormat();
		Reader reader = getReader();
		try {
			return format.parse(reader);
		} catch (IOException e) {
			throw new InputOutputException(e);
		}
	}

	public CSVPrinter getPrinter(Writer writer) {
		CSVFormat format = config.csv.getOutputFormat();
		try {
			return new CSVPrinter(writer, format);
		} catch (IOException e) {
			throw new InputOutputException(e);
		}
	}

	protected abstract Reader getReader();

	protected abstract Writer getWriter();

}
