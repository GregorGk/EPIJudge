package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ClosestIntSameWeight {

  @EpiTest(testDataFile = "closest_int_same_weight.tsv")
  public static long closestIntSameBitCount(long x) {
    long lowestSet = x & -x;
    long lowestUnset = ~x & -~x;
    if (lowestSet > lowestUnset) {
      x ^= lowestSet;
      x ^= lowestSet >>> 1;
    } else {
      x ^= lowestUnset;
      x ^= lowestUnset >>> 1;
    }
    return x;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ClosestIntSameWeight.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
