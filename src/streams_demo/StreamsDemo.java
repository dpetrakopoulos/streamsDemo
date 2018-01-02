package streams_demo;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
/**
The Streams API lets you express complex data processing queries. 
- You can filter and slice a stream using the filter , distinct , skip , and limit methods.
- You can extract or transform elements of a stream using the map and flatMap methods.
- You can find elements in a stream using the findFirst and findAny methods. You can match a given
predicate in a stream using the allMatch , noneMatch , and anyMatch methods.
- These methods make use of short-circuiting: a computation stops as soon as a result is found; there’s
no need to process the whole stream.
- You can combine all elements of a stream iteratively to produce a result using the reduce method, for
example, to calculate the sum or find the maximum of a stream.
- Some operations such as filter and map are stateless; they don’t store any state. Some operations
such as reduce store state to calculate a value. Some operations such as sorted and distinct also
store state because they need to buffer all the elements of a stream before returning a new stream.
Such operations are called stateful operations.
- There are three primitive specializations of streams: IntStream , DoubleStream , and LongStream .
Their operations are also specialized accordingly.
- Streams can be created not only from a collection but also from values, arrays, files, and specific
methods such as iterate and generate.
- An infinite stream is a stream that has no fixed size.
 */

public class StreamsDemo {

	public static void main(String[] args) {
		playWithIntegerStreams();
		
		playWithStringStreams();

		streamsFromValues();
		
		streamsIterateAndGenerate();
	}
	
	public static void playWithIntegerStreams() {
		System.out.println("Inside playWithIntegerStreams()");
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5); 
		
		System.out.println("filter elements greater than 3 and limit 1");
		List<Integer> result = numbers.stream()
									  .filter(number -> number > 3)
									  .limit(1) //return only 1 element
									  .collect(Collectors.toList());	
		
		result.forEach(System.out::println);
		System.out.println("------------------------------");
		System.out.println("filter elements greater than 2 and skip 2");
		List<Integer> resultSkipped = numbers.stream()
									  .filter(number -> number > 2)
									  .skip(2) //skip first 2 elements
									  .collect(Collectors.toList());	
		
		resultSkipped.forEach(System.out::println);
		
		System.out.println("------------------------------");
		System.out.println("return an arbitrary element greater than 2 and print");
		numbers.stream().filter(number -> number > 2).findAny().ifPresent(number -> System.out.println(number));
							
		System.out.println("------------------------------");
		
		System.out.println("return a list of the square of each number");
		List<Integer> squares = numbers.stream()
									  .map(number -> number * number)
									  .collect(Collectors.toList());	
		
		squares.forEach(System.out::println);
		
		System.out.println("------------------------------");
		System.out.println("get first element");
		Optional<Integer> first = numbers.stream().findFirst();
		try {
			System.out.println(first.get());
		}
		catch(NoSuchElementException e){
			System.out.println(e);
		}
		System.out.println("------------------------------");
		
		System.out.println("sum up the numbers using mapToInt");
		int sumMapToInt = numbers.stream().mapToInt(n -> n.intValue()).sum();
		System.out.println(sumMapToInt);
	
		System.out.println("------------------------------");
		
		System.out.println("sum up the numbers using reduce and lambda");
		int sumReduceLambda = numbers.stream().reduce(0, (a,b) -> a + b);
		System.out.println(sumReduceLambda);
	
		System.out.println("------------------------------");
		
		System.out.println("sum up the numbers using reduce, lambda without initial value");
		Optional<Integer> sumReduceLambdaNoInitialValue = numbers.stream().reduce((a,b) -> (a + b));
		System.out.println(sumReduceLambdaNoInitialValue.get());
	
		
		System.out.println("------------------------------");
		
		System.out.println("sum up the numbers using reduce and method reference");
		int sumReduceMethodReference = numbers.stream().reduce(0, Integer::sum);
		System.out.println(sumReduceMethodReference);
	
		
		System.out.println("------------------------------");
		
		System.out.println("multiply the numbers using reduce");
		int multiplyReduce = numbers.stream().reduce(1, (a,b) -> a * b);
		System.out.println(multiplyReduce);
	
		
		System.out.println("------------------------------");
		
		System.out.println("finding the max using mapToInt");
		OptionalInt maxMapToInt = numbers.stream().mapToInt(n -> n.intValue()).max();
		System.out.println(maxMapToInt.orElse(1));
		
		System.out.println("------------------------------");
		
		System.out.println("finding the max using reduce");
		Optional<Integer> maxReduce = numbers.stream().reduce(Integer::max);
		System.out.println(maxReduce.get());
		
		System.out.println("------------------------------");
		
		System.out.println("finding the min using reduce");
		Optional<Integer> minReduce = numbers.stream().reduce(Integer::min);
		System.out.println(minReduce.get());
	
		System.out.println("------------------------------");
		
		System.out.println("find number of even numbers between 1 and 100 with rangeClosed (inclusive)");
		IntStream evenNumbersRangeClosed = IntStream.rangeClosed(1,  100).filter(n -> n % 2 == 0);
		System.out.println(evenNumbersRangeClosed.count());
	
		System.out.println("------------------------------");
		
		System.out.println("find number of even numbers between 1 and 100 with range (exclusive)");
		IntStream evenNumbersRange = IntStream.range(1,  100).filter(n -> n % 2 == 0);
		System.out.println(evenNumbersRange.count());
		
		System.out.println("------------------------------");
		System.out.println("------------------------------");
	}
	
	public static void playWithStringStreams() {
		System.out.println("Inside playWithStringStreams()");
		List<String> words = Arrays.asList("One","Two", "Three", "Four", "Two");
		
		System.out.println("convert all elements to uppercase"); 
		List<String> wordsResult = words.stream()
										.map(word -> word.toUpperCase())
										.collect(Collectors.toList());
		
		wordsResult.forEach(System.out::println);
		System.out.println("------------------------------");
		
		System.out.println("match all words with predicate (contains T))");
		Boolean matcherAll = wordsResult.stream().allMatch(word -> word.contains("T"));
		System.out.println(matcherAll);
		System.out.println("------------------------------");
		
		System.out.println("match any words with predicate (contains T))");
		Boolean matcherAny = wordsResult.stream().anyMatch(word -> word.contains("T"));
		System.out.println(matcherAny);
		System.out.println("------------------------------");
		
		System.out.println("return a list of the number of characters for each word");
		List<Integer> wordLengths = wordsResult.stream().map(String::length).collect(Collectors.toList());
		wordLengths.forEach(System.out::println);
		
		System.out.println("------------------------------");
	
		System.out.println("return a list of unique characters for the list of words");
		List<String> uniqueCharacters = wordsResult.stream()
												    .map(w -> w.split(""))
												    .flatMap(Arrays::stream)
												    .distinct()
												    .collect(Collectors.toList());
		uniqueCharacters.forEach(System.out::println);
		
		System.out.println("------------------------------");
		
		
		System.out.println("get distinct elements"); 
		List<String> distinct = wordsResult.stream().distinct().collect(Collectors.toList());
		System.out.println("Distinct list: " + distinct);
		System.out.println("------------------------------");
		
		System.out.println("Concatenate all elements in list");
		String concatResult = wordsResult.stream().reduce("", (a,b) -> a + b);
		System.out.println(concatResult);
		System.out.println("------------------------------");
		
		System.out.println("Use peek");
		List<String> resultPeek = Stream.of("EURO/INR", "USD/AUD", "USD/GBP", "USD/EURO") 
									.filter(e -> e.length() > 7) 
									.peek(e -> System.out.println("Filtered value: " + e))
									.map(String::toLowerCase) 
									.peek(e -> System.out.println("Mapped value: " + e)) 
									.collect(Collectors.toList());
	}
	
	public static void streamsFromValues() {
		System.out.println("------------------------------");
		System.out.println("------------------------------");
		System.out.println("Inside streamsFromValues()");
		System.out.println("Create a stream of strings using Stream.of, convert to uppercase and print");
		
		Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
		stream.map(String::toUpperCase).forEach(System.out::println);
		
		System.out.println("------------------------------");
		System.out.println("Create an integer stream from array of ints and sum");
		
		int[] numbers = {2, 3, 5, 7, 11, 13};
		int sum = Arrays.stream(numbers).sum();
		System.out.println(sum);
	}

	public static void streamsIterateAndGenerate() {
		System.out.println("------------------------------");
		System.out.println("------------------------------");
		System.out.println("Inside streamsIterateAndGenerate()");
		System.out.println("Create an infinite stream of ints using iterate, use limit to limit size of stream");
		
		Stream.iterate(0, n -> n + 2)
			  .limit(10)
			  .forEach(System.out::println);
	
		System.out.println("------------------------------");
		
		System.out.println("Generate a stream of five random double numbers from 0 to 1");
		
		Stream.generate(Math::random)
			  .limit(5)
			  .forEach(System.out::println);
	
	
	}	
	
}

