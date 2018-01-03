package streams_demo;

import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Numbers {
	
	private static final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
	
	static void findEvenNumbers() {
		System.out.println("find number of even numbers between 1 and 100 with rangeClosed (inclusive)");
		IntStream evenNumbersRangeClosed = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0);
		System.out.println(evenNumbersRangeClosed.count());

		System.out.println("------------------------------");

		System.out.println("find number of even numbers between 1 and 100 with range (exclusive)");
		IntStream evenNumbersRange = IntStream.range(1, 100).filter(n -> n % 2 == 0);
		System.out.println(evenNumbersRange.count());

		System.out.println("------------------------------");
		System.out.println("------------------------------");
	}

	static void findFirst() {
		System.out.println("------------------------------");
		System.out.println("get first element");
		Optional<Integer> first = numbers.stream().findFirst();
		try {
			System.out.println(first.get());
		} catch (NoSuchElementException e) {
			System.out.println(e);
		}
		System.out.println("------------------------------");
	}

	static void multiplyNumbers() {
		System.out.println("return a list of the square of each number");
		List<Integer> squares = numbers.stream().map(number -> number * number).collect(toList());

		squares.forEach(System.out::println);

		System.out.println("------------------------------");

		System.out.println("multiply the numbers using reduce");
		int multiplyReduce = numbers.stream().reduce(1, (a, b) -> a * b);
		System.out.println(multiplyReduce);
	}

	static void sumNumbers() {
		System.out.println("sum up the numbers using mapToInt");
		int sumMapToInt = numbers.stream().mapToInt(n -> n.intValue()).sum();
		System.out.println(sumMapToInt);

		System.out.println("------------------------------");

		System.out.println("sum up the numbers using reduce and lambda");
		int sumReduceLambda = numbers.stream().reduce(0, (a, b) -> a + b);
		System.out.println(sumReduceLambda);

		System.out.println("------------------------------");

		System.out.println("sum up the numbers using reduce, lambda without initial value");
		Optional<Integer> sumReduceLambdaNoInitialValue = numbers.stream().reduce((a, b) -> (a + b));
		System.out.println(sumReduceLambdaNoInitialValue.get());

		System.out.println("------------------------------");

		System.out.println("sum up the numbers with collect and reduce");
		int sumCollectReducing = numbers.stream().collect(reducing(0, Integer::sum));
		System.out.println(sumCollectReducing);
		
		System.out.println("------------------------------");
		System.out.println("sum up the numbers using Collectors.summingInt");
		int sum = numbers.stream().collect(summingInt(n -> n.intValue()));
		System.out.println(sum);

		System.out.println("------------------------------");

		System.out.println("sum up the numbers using reduce and method reference");
		int sumReduceMethodReference = numbers.stream().reduce(0, Integer::sum);
		System.out.println(sumReduceMethodReference);
		
		System.out.println("------------------------------");

		System.out.println("sum all numbers starting from 1 to 1000 using parallel streams");
		long sumParallelStreams = Stream.iterate(1L, i -> i + 1).limit(1000).parallel().reduce(0L, Long::sum);
		System.out.println(sumParallelStreams);
	}

	static void filterNumbers() {
		System.out.println("filter elements greater than 3 and limit 1");
		List<Integer> result = numbers.stream().filter(number -> number > 3).limit(1) // return only 1 element
				.collect(toList());

		result.forEach(System.out::println);
		System.out.println("------------------------------");
		System.out.println("filter elements greater than 2 and skip 2");
		List<Integer> resultSkipped = numbers.stream().filter(number -> number > 2).skip(2) // skip first 2 elements
				.collect(toList());

		resultSkipped.forEach(System.out::println);

		System.out.println("------------------------------");

		System.out.println("return an arbitrary element greater than 2 and print");
		numbers.stream().filter(number -> number > 2).findAny().ifPresent(number -> System.out.println(number));

		System.out.println("------------------------------");
	}

	static void findMaxMin() {
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
	}
}
