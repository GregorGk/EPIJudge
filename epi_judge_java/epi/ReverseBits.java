package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ReverseBits {

  @EpiTest(testDataFile = "reverse_bits.tsv")
  public static long reverseBits(long x) {
    final int MASK_SIZE = 16;
    final int MASK = 0xFFFF;
    long toReturn =
        (precomputedReverse[(int) (x & MASK)] << (3 * MASK_SIZE) |
            precomputedReverse[(int) ((x >>> MASK_SIZE) & MASK)] << (2 * MASK_SIZE) |
            precomputedReverse[(int) (((x >>> (2 * MASK_SIZE)) & MASK))] << MASK_SIZE |
            precomputedReverse[(int) (((x >>> (3 * MASK_SIZE)) & MASK))]);
    System.out.println(Long.toBinaryString(toReturn));
    return toReturn;
  }

  public static long[] precomputedReverse = new long[1 << 16];

  static {
    precompute();
  }

  public static void precompute() {
    for (int i = 0; i < (1 << 16); i++) {
      long result = i;
      for (byte j = 0; j < 1 << 3; j++) {
        result = SwapBits.swapBits(result, j, 15 - j);
      }
      precomputedReverse[i] = result;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseBits.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
