package se.carestra.learn.java.codechef.problems;

import io.hosuaby.inject.resources.core.ResourceAsLines;
import io.hosuaby.inject.resources.junit.jupiter.TestWithResources;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static io.hosuaby.inject.resources.core.InjectResources.resource;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Hotel Bytelandia
 * A holiday weekend is coming up, and Hotel Bytelandia needs to find out if it has enough rooms to accommodate all potential guests. A number of guests have made reservations. Each reservation consists of an arrival time, and a departure time. The hotel management has hired you to calculate the maximum number of guests that will be at the hotel simultaneously. Note that if one guest arrives at the same time another leaves, they are never considered to be at the hotel simultaneously (see the second example).
 * <p>
 * Input
 * Input will begin with an integer T, the number of test cases. Each test case begins with an integer N, the number of guests. Two lines follow, each with exactly N positive integers. The i-th integer of the first line is the arrival time of the i-th guest, and the i-th integer of the second line is the departure time of the i-th guest (which will be strictly greater than the arrival time).
 * <p>
 * Output
 * For each test case, print the maximum number of guests that are simultaneously at the hotel.
 * <p>
 * Constraints
 * T≤100
 * N≤100
 * All arrival/departure times will be between 1 and 1000, inclusive
 * <p>
 * INPUT:
 * 3
 * 3
 * 1 2 3
 * 4 5 6
 * 5
 * 1 2 3 4 5
 * 2 3 4 5 6
 * 7
 * 13 6 5 8 2 10 12
 * 19 18 6 9 9 11 15
 * <p>
 * OUTPUT:
 * 3
 * 1
 * 3
 */
@TestWithResources
class ReadInputTest {

  @Test
  void readInputFileContainingOne() {
    Path path = Paths.get("./src/test/resources/onetest-oneguest.txt");
    List<TestRun> testRuns = new ArrayList<>();
    try (BufferedReader reader = Files.newBufferedReader(path)) {
      var lines = reader.lines().toList();
      testRuns = Util.extractRTestRuns(lines);
    } catch (IOException e) {
      fail("Could not read test file." + e.getMessage());
    }
    var nrOfTest = testRuns.size();
    assertThat(nrOfTest, is(1));
    List<Integer> maxNrOfSimGuests = testRuns.stream().mapToInt(Util::findMaxGuest).boxed().toList();
    var maxGuest = maxNrOfSimGuests.stream().max(Integer::compareTo).orElse(-1);
    assertThat(maxGuest, is(1));
    assertThat(maxNrOfSimGuests, is(List.of(1)));
  }

  @Test
  void readInputFileContainingSeveral() {
    List<String> lines;
    try (ResourceAsLines resource = resource()
        .withPath("severaltest-severalguest.txt")
        .asLines()) {
      lines = resource.lines().toList();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    List<TestRun> testRuns = Util.extractRTestRuns(lines);
    var nrOfTest = testRuns.size();
    assertThat(nrOfTest, is(3));
    List<Integer> maxNrOfSimGuests = testRuns.stream().mapToInt(Util::findMaxGuest).boxed().toList();
    var maxGuest = maxNrOfSimGuests.stream().max(Integer::compareTo).orElse(-1);
    assertThat(maxGuest, is(2));
    assertThat(maxNrOfSimGuests, is(List.of(1, 1, 2)));
  }

  @Test
  void maxNrOfSimultianiousGuests() {
    List<String> lines;
    try (ResourceAsLines resource = resource()
        .withPath("first-example.txt")
        .asLines()) {
      lines = resource.lines().toList();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    List<TestRun> testRuns = Util.extractRTestRuns(lines);
    var nrOfTest = testRuns.size();
    assertThat(nrOfTest, is(3));
    List<Integer> maxNrOfSimGuests = testRuns.stream().mapToInt(Util::findMaxGuest).boxed().toList();
    var maxGuest = maxNrOfSimGuests.stream().max(Integer::compareTo).orElse(-1);
    assertThat(maxGuest, is(3));
    assertThat(maxNrOfSimGuests, is(List.of(3, 1, 3)));
  }


}
