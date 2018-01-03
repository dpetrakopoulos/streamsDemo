package streams_demo;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * The Streams API lets you express complex data processing queries. 
 * - You can filter and slice a stream using the filter , distinct , skip , and limit methods. 
 * - You can extract or transform elements of a stream using the map and flatMap methods. 
 * - You can find elements in a stream using the findFirst and findAny methods. 
 * - You can match a given predicate in a stream using the allMatch , noneMatch , and anyMatch methods. 
 * - These methods make use of short-circuiting: a computation stops as soon as a result is found; there’s
 * no need to process the whole stream. 
 * - You can combine all elements of a stream iteratively to produce a result using the reduce method, 
 * for example, to calculate the sum or find the maximum of a stream. 
 * - Some operations such as filter and map are stateless; they don’t store any state. Some operations
 * such as reduce store state to calculate a value. Some operations such as sorted and distinct 
 * also store state because they need to buffer all the elements of a stream before returning a new stream. 
 * Such operations are called stateful operations. 
 * - There are three primitive specializations of streams: IntStream , DoubleStream , and LongStream. 
 * Their operations are also specialized accordingly. 
 * - Streams can be created not only from a collection but also from values, arrays, files, and specific methods 
 * such as iterate and generate. 
 * - An infinite stream is a stream that has no fixed size.
 */

public class StreamsDemo {

	public static void main(String[] args) {
		playWithIntegerStreams();

		playWithStringStreams();

		streamsFromValues();

		streamsIterateAndGenerate();
	}

	private static void playWithIntegerStreams() {
		System.out.println("Inside playWithIntegerStreams()");
		
		Numbers.filterNumbers();

		Numbers.multiplyNumbers();

		Numbers.findFirst();

		Numbers.sumNumbers();

		Numbers.findMaxMin();

		Numbers.findEvenNumbers();
	}
	
	private static void playWithStringStreams() {
		System.out.println("Inside playWithStringStreams()");

		Strings.convertToUppercase();

		Strings.matchWithPredicate();

		Strings.characterCount();

		Strings.getDistinctElements();

		Strings.concatenateElements();

		Strings.usePeek();

	}
	
	private static void streamsFromValues() {
		System.out.println("------------------------------");
		System.out.println("------------------------------");
		System.out.println("Inside streamsFromValues()");
		System.out.println("Create a stream of strings using Stream.of, convert to uppercase and print");

		Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
		stream.map(String::toUpperCase).forEach(System.out::println);

		System.out.println("------------------------------");
		System.out.println("Create an integer stream from array of ints and sum");

		int[] numbers = { 2, 3, 5, 7, 11, 13 };
		int sum = Arrays.stream(numbers).sum();
		System.out.println(sum);
	}

	private static void streamsIterateAndGenerate() {
		System.out.println("------------------------------");
		System.out.println("------------------------------");
		System.out.println("Inside streamsIterateAndGenerate()");
		System.out.println("Create an infinite stream of ints using iterate, use limit to limit size of stream");

		Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);

		System.out.println("------------------------------");

		System.out.println("Generate a stream of five random double numbers from 0 to 1");

		Stream.generate(Math::random).limit(5).forEach(System.out::println);
	}
}
