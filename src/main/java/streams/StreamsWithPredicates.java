package streams;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamsWithPredicates {
	// we can use methods on classes
	public static long countEmptyStrings(String... array) {
		return Stream.of(array).filter(String::isEmpty).count();
	}

	// or wrap it into predicates
	public static long countNonEmptyStrings(String... array) {
		return Stream.of(array).filter(as(String::isEmpty).negate()).count();
	}

	public static <T> Predicate<T> as(Predicate<T> predicate) {
		return predicate;
	}
}
