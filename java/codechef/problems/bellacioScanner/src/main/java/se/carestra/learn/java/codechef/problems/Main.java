package se.carestra.learn.java.codechef.problems;


import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Bella ciao
 * Read problem statements in Vietnamese, Bengali, Mandarin Chinese, and Russian as well.
 * Chef is planning a heist in the reserve bank of Chefland. They are planning to hijack the bank for D days and
 * print the money. The initial rate of printing the currency is P dollars per day and they increase the production by
 * Q dollars after every interval of d days. For example, after d days the rate is P+Q dollars per day, and after
 * 2d days the rate is P+2Q dollars per day, and so on. Output the amount of money they will be able to print in the
 * given period.
 *
 * Input
 * The first line contains an integer T, the number of test cases. Then the test cases follow.
 * Each test case contains a single line of input, four integers D,d,P,Q.
 *
 * 3
 * 2 1 1 1
 * 3 2 1 1
 * 5 2 1 2
 *
 * Output
 * For each test case, output in a single line the answer to the problem.
 * 3
 * 4
 * 13
 *
 */
public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter nr of test: ");
    int nrOfTest = scanner.nextInt();
    // consume a newLine after using a token-based method, i.e. scanner.nextInt().
    scanner.nextLine();

    int testNr = 1;
    List<String> testCases = new ArrayList<>();
    while (nrOfTest-- > 0) {
      System.out.print("Enter test " + testNr + " input D d P Q: ");
      String testCase = scanner.nextLine();
      testCases.add(testCase);
      testNr++;
    }

    List<List<Integer>> testValues = testCases.stream()
        .map(s -> Arrays.stream(s.split(" ")).toList())
        .map(inputValues -> inputValues.stream().flatMap(flatParser).toList())
        .toList();

    List<Integer> result = testValues.stream()
        .map(currentTest -> {
              var nrOfDays = currentTest.get(0); // D
              var interval = currentTest.get(1); // d
              var currency = currentTest.get(2); // P
              var delta = currentTest.get(3); // Q

              var sum = 0;
              List<Integer> rates = buildRates(interval, nrOfDays);
              System.out.println("rates: " + rates);
              for (var i = 0; i < nrOfDays; i++) {
                int dayRate = moneyPrintPerDay(currency, delta, rates.get(i));
                System.out.println("dayRate: " + dayRate);
                sum += dayRate;
              }

              return sum;
            }
        )
        .toList();
    System.out.println(result);
  }

  private static List<Integer> buildRates(int interval, int nrOfDays) {
    return IntStream.range(0, nrOfDays)
        .boxed()
        .map(element -> Collections.nCopies(interval, element))
        .flatMap(List::stream)
        .toList();
  }

  private static int moneyPrintPerDay(int currency, int delta, int rate) {
    return currency + rate * delta;
  }


  private static Function<String, Stream<Integer>> flatParser = input -> {
    try {
      return Stream.of(Integer.parseInt(input));
    } catch (NumberFormatException e) {
      // empty on purpose
    }
    return Stream.empty();
  };
}