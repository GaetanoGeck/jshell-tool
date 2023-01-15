package jt.collection;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.ComparatorUtils;

public class Coll {
	/**
	 * Map list to list by applying the mapper function element-wise.
	 * 
	 * @param <R> type of the elements in the resulting list
	 * @param <T> type of the elements in the input list
	 * @param list input list
	 * @param mapper mapper function
	 * @return result list
	 */
	public static <R, T> List<R> map(List<T> list, Function<? super T, ? extends R> mapper) {
		return mapToList(list, mapper);
	}

	/**
	 * Map arbitrary collection to list by applying the mapper function element-wise.
	 * 
	 * @param <R> type of the elements in the result list
	 * @param <T> type of the elements in the input collection
	 * @param collection input collection
	 * @param mapper mapper function
	 * @return result list
	 */
	public static <R, T> List<R> mapToList(Collection<T> collection, Function<? super T, ? extends R> mapper) {
		return collection.stream().map(mapper).collect(Collectors.toList());
	}

	/**
	 * Map arbitrary collection to set by applying the mapper function element-wise.
	 * 
	 * @param <R> type of the elements in the result set
	 * @param <T> type of the elements in the input collection
	 * @param collection input collection
	 * @param mapper mapper function
	 * @return result set
	 */
	public static <R, T> Set<R> mapToSet(Collection<T> collection, Function<? super T, ? extends R> mapper) {
		return collection.stream().map(mapper).collect(Collectors.toSet());
	}

	/**
	 * Filter list to list that only contains those element satisfying the predicate.
	 * 
	 * @param <T> type of the elements of the lists
	 * @param list input list
	 * @param predicate predicate
	 * @return filtered list
	 */
	public static <T> List<T> filter(List<T> list, Predicate<? super T> predicate) {
		return filterToList(list, predicate);
	}

	/**
	 * Filter arbitrary collection to list that only contains those elements satisfying the predicate.
	 * 
	 * @param <T> type of the elements of the collection
	 * @param collection input collection
	 * @param predicate predicate
	 * @return filtered list
	 */
	public static <T> List<T> filterToList(Collection<T> collection, Predicate<? super T> predicate) {
		return collection.stream().filter(predicate).collect(Collectors.toList());
	}

	/**
	 * Filter arbitrary collection to set that only contains those elements satisfying the predicate.
	 * 
	 * @param <T> type of the elements of the collection
	 * @param collection input collection
	 * @param predicate predicate
	 * @return filtered set
	 */
	public static <T> Set<T> filterToSet(Collection<T> collection, Predicate<? super T> predicate) {
		return collection.stream().filter(predicate).collect(Collectors.toSet());
	}

	/**
	 * Create a function that sorts a stream by comparing the projection of the stream's elements.
	 * 
	 * @param <T> type of the elements of the input stream
	 * @param <S> type of the projections of the elements
	 * @param projection function defining the projection
	 * @return sorting function
	 */
	public static <T, S extends Comparable<S>> Function<Stream<T>,Stream<T>> streamSortedBy(Function<T, S> projection) {
		Comparator<T> comparator = compareProjections(projection);
		return stream -> stream.sorted(comparator);
	}

	/**
	 * Create a comparator that considers the projections of the compared elements.
	 * 
	 * @param <T> type of the compared elements
	 * @param <S> type of the projections of the elements
	 * @param projection function defining the projection
	 * @return comparator
	 */
	public static <T, S extends Comparable<S>> Comparator<T> compareProjections(Function<T, S> projection) {
		return (a, b) -> Objects.compare( //
				(a == null) ? null : projection.apply(a), //
				(b == null) ? null : projection.apply(b), //
				ComparatorUtils.naturalComparator());
	}
}
