package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PrimitiveMultiply {
  @EpiTest(testDataFile = "primitive_multiply.tsv")
  public static long multiply(long x, long y) {
    long sum = 0;
    while(x != 0) {
      if ((x & 1) != 0) {
        sum = add(sum, y);
      }
      x >>>= 1;
      y <<= 1;
    }
    return sum;
  }

  public static long add(long a, long b) {
//   return (b == 0)? a : add(a ^ b, (a & b) << 1);
    while (b != 0) {
      long temp = a;
      a ^= b;
      b = (temp & b) << 1;
    }
    return a;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
