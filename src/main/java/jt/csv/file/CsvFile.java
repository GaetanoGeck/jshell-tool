package jt.csv.file;

import java.io.File;
import java.io.Reader;
import java.io.Writer;
import java.util.Objects;

import jt.common.Config;
import jt.csv.CsvWorker;
import jt.file.FileIO;

public class CsvFile extends CsvWorker {
	private final File file;

	public CsvFile(Config config, File file) {
		super(config);
		this.file = Objects.requireNonNull(file);
	}

	public CsvFile(Config config, String path) {
		super(config);
		this.file = new File(Objects.requireNonNull(path));
	}

	@Override
	public Reader getReader() {
		return FileIO.createReader(config, file);
	}

	@Override
	protected Writer getWriter() {
		return FileIO.createWriter(config, file);
	}

}
