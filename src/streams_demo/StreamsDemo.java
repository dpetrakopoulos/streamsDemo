package streams_demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsDemo {

	public static void main(String[] args) {
		List<Integer> numbers = new ArrayList<>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		numbers.add(4);
		numbers.add(5);
	
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

}

