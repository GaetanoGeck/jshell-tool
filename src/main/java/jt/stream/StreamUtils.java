package jt.stream;

import java.util.Iterator;
import java.util.stream.Stream;

public class StreamUtils {
	
	public static <T> Stream<T> toStream(Iterator<T> it) {
		if (it == null) {
			return null;
		}
		return Stream.generate(() -> null) //
				.takeWhile(x -> it.hasNext()) //
				.map(x -> it.next());
	}
}
