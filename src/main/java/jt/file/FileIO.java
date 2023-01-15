package jt.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

import jt.common.Config;
import jt.common.InputOutputException;

/**
 * Facade for file functionality.
 */
public class FileIO {
	public static BufferedReader createReader(Config config, File file) {
		Charset charset = config.getInputCharset();
		try {
			FileReader fr = new FileReader(file, charset);
			return new BufferedReader(fr);
		} catch (IOException e) {
			throw new InputOutputException(e);
		}
	}
	
	public static BufferedWriter createWriter(Config config, File file) {
		Charset charset = config.getOutputCharset();
		try {
			FileWriter fw = new FileWriter(file, charset);
			return new BufferedWriter(fw);
		} catch (IOException e) {
			throw new InputOutputException(e);
		}
	}
}
