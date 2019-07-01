package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class PrimeSieve {
  @EpiTest(testDataFile = "prime_sieve.tsv")
  // Given n, return all primes up to and including n.
  public static List<Integer> generatePrimes(int n) {
    if (n < 2) {
      return Collections.EMPTY_LIST;
    }
    final int size = (int) (Math.floor(0.5 * (n - 3))) + 1;
    List<Boolean> isPrime = new ArrayList<>(Collections.nCopies(size, true));
    List<Integer> primes = new ArrayList<>();
    primes.add(2);
    for (long i = 0; i < size; i++) {
      if (isPrime.get((int)i)) {
        int p = (int)(2 * i + 3);
        primes.add(p);
        for (long j = 2 * i * i + 6 * i + 3; j < size; j += p) {
          isPrime.set((int)j, false);
        }
      }
    }
    return primes;
  }

  public List<Integer> generatePrimesNonoptimal(int n) {
    List<Integer> primes = new ArrayList<>();
    List<Boolean> isPrime = new ArrayList<>(Collections.nCopies(n + 1, true));
    isPrime.set(0, false);
    isPrime.set(1, false);
    for (int i = 2; i <= n; i ++) {
      if (isPrime.get(i)) {
        primes.add(i);
        for (int j = i; j <= n; j += i) {
          isPrime.set(j, false);
        }
      }
    }
    return primes;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimeSieve.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
