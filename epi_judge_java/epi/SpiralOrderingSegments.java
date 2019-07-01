package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.ArrayList;
import java.util.List;
public class SpiralOrderingSegments {
  @EpiTest(testDataFile = "spiral_ordering_segments.tsv")

  public static List<Integer>
  matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
    List<Integer> spiralOrder = new ArrayList<>((int) Math.pow(squareMatrix.size(), 2));
    /**
     * Size of a row equals size of a column.
     */
    int size = squareMatrix.size();
    int maxOffset = size >> 1;
    for (int offset = 0; offset < maxOffset; ++offset) {
      for (int j = offset; j < size - offset - 1; ++j) {
        spiralOrder.add(squareMatrix.get(offset).get(j));
      }
      for (int i = offset; i < size - offset - 1; ++i) {
        spiralOrder.add(squareMatrix.get(i).get(size - offset - 1));
      }
      for (int j = size - offset - 1; j > offset; --j) {
        spiralOrder.add(squareMatrix.get(size - offset - 1).get(j));
      }
      for (int i = size - offset - 1; i > offset; --i) {
        spiralOrder.add(squareMatrix.get(i).get(offset));
      }
    }
    if (size % 2 == 1) {
      spiralOrder.add(squareMatrix.get(size >> 1).get(size >> 1));
    }
    return spiralOrder;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpiralOrderingSegments.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
