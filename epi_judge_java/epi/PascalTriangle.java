package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.ArrayList;
import java.util.List;
public class PascalTriangle {
  @EpiTest(testDataFile = "pascal_triangle.tsv")

  public static List<List<Integer>> generatePascalTriangle(int numRows) {
    List<List<Integer>> result = new ArrayList<>();
    if (numRows > 0) {
      result.add(List.of(1));
    }
    for (int i = 1; i < numRows; ++i) {
      List<Integer> lastRow = result.get(result.size() - 1);
      List<Integer> nextRow = new ArrayList<>(List.of(1));
      for (int j = 0; j < lastRow.size() - 1; ++j) {
        nextRow.add(lastRow.get(j) + lastRow.get(j + 1));
      }
      nextRow.add(1);
      result.add(nextRow);
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PascalTriangle.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
