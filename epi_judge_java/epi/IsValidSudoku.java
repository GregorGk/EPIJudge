package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IsValidSudoku {

  @EpiTest(testDataFile = "is_valid_sudoku.tsv")

  // Check if a partially filled matrix has any conflicts.
  public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {
    //Check row constraints.
    for (int i = 0; i < partialAssignment.size(); ++i) {
      if (hasDuplicates(partialAssignment, i, i + 1, 0, partialAssignment.size())) {
        return false;
      }
    }

    //Check column constraints.
    for (int j = 0; j < partialAssignment.size(); ++j) {
      if (hasDuplicates(partialAssignment, 0, partialAssignment.size(), j, j + 1)) {
        return false;
      }
    }

    //Check region constraints.
    int regionSize = (int) Math.sqrt(partialAssignment.size());
    for (int I = 0; I < regionSize; ++I) {
      for (int J = 0; J < regionSize; ++J) {
        if (hasDuplicates(partialAssignment,
            regionSize * I, regionSize * (I + 1),
            regionSize * J, regionSize * (J + 1))) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Return true if subarray partialAssignment[startRow, endRow)[startColumn, endColumn) contains
   * any duplicates in {1, 2, ..., partialAssignment.size()}; otherwise return false.
   */
  public static boolean hasDuplicates(List<List<Integer>> partialAssignment,
      int startRow, int endRow,
      int startColumn, int endColumn) {
    Set<Integer> seen = new HashSet<>(9);
    for (int i = startRow; i < endRow; ++i) {
      for (int j = startColumn; j < endColumn; ++j) {
        if (partialAssignment.get(i).get(j) != 0 && seen
            .contains(partialAssignment.get(i).get(j))) {
          return true;
        }
        seen.add(partialAssignment.get(i).get(j));
      }
    }
    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidSudoku.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
