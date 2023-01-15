package jt.text.file;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;

import jt.common.Config;
import jt.common.InputOutputException;
import jt.text.TextWorker;

public class TextFile extends TextWorker {
	private final File file;

	public TextFile(Config config, File file) {
		super(config);
		this.file = Objects.requireNonNull(file);
	}
	
	public TextFile(Config config, String path) {
		super(config);
		this.file = new File(Objects.requireNonNull(path));
	}
	
	@Override
	public List<String> readLineList() {
		try {
			return FileUtils.readLines(file, config.getInputCharset());
		} catch (IOException e) {
			throw new InputOutputException(e);
		}
	}

	@Override
	public Stream<String> readLineStream() {
		return readLineList().stream();
	}

	@Override
	public void writeLines(Collection<String> lines) {
		String charsetName = config.getOutputCharset().name();
		try {
			FileUtils.writeLines(file, charsetName, lines);
		} catch (IOException e) {
			throw new InputOutputException(e);
		}
	}
	
}