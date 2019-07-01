package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class IntAsArrayIncrement {
  @EpiTest(testDataFile = "int_as_array_increment.tsv")
  public static List<Integer> plusOne(List<Integer> A) {
    int index = A.size() - 1;
    A.set(index, A.get(index) + 1);
    while (A.get(index) == 10 && index > 0) {
      A.set(index, 0);
      A.set(index - 1, A.get(index - 1) + 1);
      index--;
    }
    if (A.get(index) == 10) {
      A.set(0, 1);
      A.add(0);
    }
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
