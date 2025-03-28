package se.carestra.learn.java.codechef.problems;

public record Guest(Integer index, Integer arrival, Integer departure) {
  public Guest {
    if (arrival >= departure) {
      throw new IllegalArgumentException("Arrival should not be equal or greater than departure.");
    }
  }
}
