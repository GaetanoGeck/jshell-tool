package jt.csv;

import java.io.File;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import org.apache.commons.csv.CSVRecord;

import jt.collection.Coll;
import jt.common.Config;
import jt.csv.file.CsvFile;

/**
 * Facade to CSV functionality.
 */
public class Csv {
	private final Config config;

	public Csv(Config config) {
		super();
		this.config = new Config(config);
	}

	public CsvFile file(File file) {
		Config fileConfig = new Config(config);
		return new CsvFile(fileConfig, file);
	}

	public CsvFile file(String path) {
		Config fileConfig = new Config(config);
		return new CsvFile(fileConfig, path);
	}

	public Csv withConfig(Consumer<Config> configConsumer) {
		Csv configuredCsv = new Csv(config);
		configConsumer.accept(configuredCsv.config);
		return configuredCsv;
	}

	public void transformFile(String inputPath, String outputPath,
			Function<Stream<CSVRecord>, Stream<CSVRecord>> transformer) {
		CsvFile input = file(inputPath);
		CsvFile output = file(outputPath);
		Stream<CSVRecord> recordInputStream = input.readStream();
		Stream<CSVRecord> recordOutputStream = transformer.apply(recordInputStream);
		output.write(recordOutputStream);
	}

	public static Function<Stream<CSVRecord>, Stream<CSVRecord>> sortedByColumn(String column) {
		Function<CSVRecord, String> projection = record -> record.get(column);
		return Coll.streamSortedBy(projection);
	}
}
