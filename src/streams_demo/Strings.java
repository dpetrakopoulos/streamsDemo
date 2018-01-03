package streams_demo;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Strings {
	private static final List<String> words = Arrays.asList("One", "Two", "Three", "Four", "Two");
	
	static void concatenateElements() {
		System.out.println("Concatenate all elements in list");
		String concatResult = words.stream().reduce("", (a, b) -> a + b);
		System.out.println(concatResult);
		System.out.println("------------------------------");
		
		System.out.println("Concatenate all elements in list with joining");
		String concatResultWithJoining = words.stream().collect(joining());
		System.out.println(concatResultWithJoining);
		System.out.println("------------------------------");
		
	}

	static void getDistinctElements() {
		System.out.println("get distinct elements");
		List<String> distinct = words.stream().distinct().collect(toList());
		long numberOfDisctinct = distinct.stream().collect(counting());
		System.out.println("Distinct list: " + distinct);
		System.out.println("Number of distinct: " + numberOfDisctinct);
		System.out.println("------------------------------");
	}

	static void characterCount() {
		System.out.println("return a list of the number of characters for each word");
		List<Integer> wordLengths = words.stream().map(String::length).collect(toList());
		wordLengths.forEach(System.out::println);

		System.out.println("------------------------------");

		System.out.println("return a list of unique characters for the list of words");
		List<String> uniqueCharacters = words.stream().map(w -> w.split("")).flatMap(Arrays::stream).distinct()
				.collect(toList());
		uniqueCharacters.forEach(System.out::println);

		System.out.println("------------------------------");
	}

	static void matchWithPredicate() {
		System.out.println("match all words with predicate (contains T))");
		Boolean matcherAll = words.stream().allMatch(word -> word.contains("T"));
		System.out.println(matcherAll);
		System.out.println("------------------------------");

		System.out.println("match any words with predicate (contains T))");
		Boolean matcherAny = words.stream().anyMatch(word -> word.contains("T"));
		System.out.println(matcherAny);
		System.out.println("------------------------------");
	}

	static void convertToUppercase() {
		System.out.println("convert all elements to uppercase");
		List<String> wordsResult = words.stream().map(word -> word.toUpperCase()).collect(toList());

		wordsResult.forEach(System.out::println);
		System.out.println("------------------------------");
	}
	
	static void usePeek() {
		System.out.println("Use peek");
		Stream.of("EURO/INR", "USD/AUD", "USD/GBP", "USD/EURO").filter(e -> e.length() > 7)
				.peek(e -> System.out.println("Filtered value: " + e)).map(String::toLowerCase)
				.peek(e -> System.out.println("Mapped value: " + e)).collect(toList());
	}
}
