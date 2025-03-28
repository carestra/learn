package se.carestra.learn.java.codechef.problems;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class Util {
  public static List<TestRun> extractRTestRuns(List<String> lines) {
    if (lines == null || lines.isEmpty()) {
      throw new IllegalArgumentException("Input can not be null nor empty.");
    }

    // get indexes
    List<Integer> nrTestsAndGuestsIndexes = Stream
        .iterate(
            0, // seed
            i -> i < lines.size(), // hasnext
            i -> i == 0 ? i + 1 : i + 3 // next
        ).toList();

    int nrOfTest = java.lang.Integer.parseInt(lines.get(nrTestsAndGuestsIndexes.getFirst()));
    if ((nrOfTest * 3) + 1 != lines.size()) {
      throw new IllegalArgumentException("Input must contain exactly " + nrOfTest * 3 + " lines.");
    }

    List<Integer> guestNrIndexes = nrTestsAndGuestsIndexes.stream().skip(1).toList();

    return guestNrIndexes.stream()
        .map(guestNrIndex -> {
          int nrOfGuests = java.lang.Integer.parseInt(lines.get(guestNrIndex));
          // extract arrivals and departures
          List<List<Integer>> arrivalsDepartures =
              lines.subList(guestNrIndex + 1, guestNrIndex + 3)
                  .stream()
                  .map(s -> Arrays.stream(s.split(" ")).toList())
                  .map(s -> s.stream().flatMap(flatParser).toList())
                  .toList();
          if (arrivalsDepartures.size() != 2) {
            throw new IllegalArgumentException("Nr of guest" + nrOfGuests + " does not match with the input lines.");
          }
          List<Integer> arrivals = arrivalsDepartures.getFirst();
          List<Integer> departures = arrivalsDepartures.getLast();
          if (nrOfGuests != arrivals.size() || nrOfGuests != departures.size()) {
            throw new IllegalArgumentException("Test[index=" + guestNrIndex + "] does not contains the expected nr[" + nrOfGuests + "] of entries");
          }

          // construct guests for testrun
          var guests = IntStream.range(0, arrivals.size())
              .mapToObj(index -> new Guest(index, arrivals.get(index), departures.get(index)))
              .toList();

          return new TestRun(nrOfGuests, guests);
        })
        .toList();
  }

  public static int findMaxGuest(TestRun testRun) {
    // find upper bound of table
    var upperBound = testRun.guests().stream().map(Guest::departure).max(Integer::compareTo).orElse(0);

    // create table and filled with zeros
    int[][] table = IntStream.range(0, testRun.nrOfGuest())
        .mapToObj(index -> IntStream.range(0, upperBound).map(value -> 0).toArray())
        .toArray(int[][]::new);

    // for every time slot, filled with one
    var row = new AtomicInteger(0);
    testRun.guests().forEach(guest -> {
      int currentRow = row.get();
      for(int col = guest.arrival()-1; col < guest.departure()-1; col++) {
        table[currentRow][col] = 1;
      }
      row.set(currentRow + 1);
    });

    // for every row, calculate max slots filled with one's
    var max = new AtomicInteger(0);
    var nrofCols = table[0].length;
    for (int tableCol = 0; tableCol < nrofCols; tableCol++) {
      var sum = new AtomicInteger(0);
      for (int tableRow = 0; tableRow < table.length; tableRow++) {
        sum.set(sum.get() + table[tableRow][tableCol]);
      }
      if (sum.get() > max.get()) {
        max.set(sum.get());
      }
    }

    return max.get();
  }

  // Function that returns a Stream of Integer if integer can be parsed.
  // use with flatMap
  private static final Function<String, Stream<Integer>> flatParser = s -> {
    try {
      return Stream.of(Integer.parseInt(s));
    } catch (NumberFormatException e) {
      // empty on purpose
    }
    return Stream.empty();
  };
}
