package jt.util;

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;

public class TestResourceLocator {
	private static final Path rootPath = Path.of("src/test/resources");
	private Path resourceGroupPath;

	public TestResourceLocator(String resourceGroupPath) {
		this(Path.of(resourceGroupPath));
	}
	
	public TestResourceLocator(Path resourceGroupPath) {
		super();
		this.resourceGroupPath = rootPath.resolve(resourceGroupPath);
	}
	
	public File getFile(String subPath) {
		return getFile(Path.of(subPath));
	}
	
	public File getFile(Path resourcePath) {
		Objects.requireNonNull(resourcePath);
		Path path = resourceGroupPath.resolve(resourcePath);
		return path.toFile();
	}
}
