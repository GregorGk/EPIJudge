package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DutchNationalFlag {

  public enum Color {RED, WHITE, BLUE}

  public static void dutchFlagPartition(int pivotIndex, List<Color> A) {
//    int nextLessThanIndex = 0;
//    int pivotOrdinal = A.get(pivotIndex).ordinal();
//    for (int i = 0; i < A.size(); i++) {
//      if (A.get(i).ordinal() < pivotOrdinal) {
//        Collections.swap(A, i, nextLessThanIndex++);
//      }
//    }
//    for (int i = nextLessThanIndex, nextEqualIndex = nextLessThanIndex;
//        i < A.size(); i++) {
//      if (A.get(i).ordinal() == pivotOrdinal) {
//        Collections.swap(A, i, nextEqualIndex++);
//      }
//    }
    /**
     * Invariants:
     * A.subList(0, smaller) - < pivot
     * A.subList(smaller, equal) - equal pivot
     * A.subList(equal, larger) - unclassified
     * A.subList(larger, A.size()) - larger
     */
    int smaller = 0, equal = 0, larger = A.size();
    int pivot = A.get(pivotIndex).ordinal();
    while (equal < larger) {
      int current = A.get(equal).ordinal();
      if (current < pivot) {
        Collections.swap(A, equal++, smaller++);
      } else if (current == pivot) {
        equal++;
      } else {
        Collections.swap(A, equal, --larger);
      }
    }
  }

  @EpiTest(testDataFile = "dutch_national_flag.tsv")
  public static void dutchFlagPartitionWrapper(TimedExecutor executor,
      List<Integer> A, int pivotIdx)
      throws Exception {
    List<Color> colors = new ArrayList<>();
    int[] count = new int[3];

    Color[] C = Color.values();
    for (int i = 0; i < A.size(); i++) {
      count[A.get(i)]++;
      colors.add(C[A.get(i)]);
    }

    Color pivot = colors.get(pivotIdx);
    executor.run(() -> dutchFlagPartition(pivotIdx, colors));

    int i = 0;
    while (i < colors.size() && colors.get(i).ordinal() < pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() == pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() > pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    if (i != colors.size()) {
      throw new TestFailure("Not partitioned after " + Integer.toString(i) +
          "th element");
    } else if (count[0] != 0 || count[1] != 0 || count[2] != 0) {
      throw new TestFailure("Some elements are missing from original array");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DutchNationalFlag.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
