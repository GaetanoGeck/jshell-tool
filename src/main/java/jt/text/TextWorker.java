package jt.text;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import jt.common.Config;

public abstract class TextWorker {
	protected final Config config;
	
	public TextWorker(Config config) {
		this.config = config;
	}
	
	public abstract List<String> readLineList();
	public abstract Stream<String> readLineStream();
	public abstract void writeLines(Collection<String> lines);
}
