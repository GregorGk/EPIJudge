package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApplyPermutation {

  public static void applyPermutation(List<Integer> perm, List<Integer> A) {
    System.out.println(perm);
    inverse(perm);
    inverse(perm);
    System.out.println(perm);
    for (int i = 0; i < A.size(); ++i) {
      int next = i;
      while (perm.get(next) >= 0) {
        Collections.swap(A, i, perm.get(next));
        int temp = perm.get(next);
        perm.set(next, perm.get(next) - perm.size());
        next = temp;
      }
    }
  }

  public static void inverse(List<Integer> A) {
    for (int i = 0; i < A.size(); ++i) {
      int index = A.get(i);
      int value = i;
      do {
        int temp = A.get(index);
        A.set(index, value);
        value = index;
        index = temp;
      } while (value != i);
    }
  }

  @EpiTest(testDataFile = "apply_permutation.tsv")
  public static List<Integer> applyPermutationWrapper(List<Integer> perm,
      List<Integer> A) {
    applyPermutation(perm, A);
    return A;
  }

//  public static int count() {
//    int ans = 1;
//    for (int i = 0; i < (1 << 8); ++i) {
//      ans = ()? (ans/2)*3 : ans*2;
//      ans = ()? (ans/2)*3 : ans*2;
//      ans = ()? (ans/2)*3 : ans*2;
//      ans = ()? (ans/2)*3 : ans*2;
//      ans = ()? (ans/2)*3 : ans*2;
//      ans = ()? (ans/2)*3 : ans*2;
//      ans = ()? (ans/2)*3 : ans*2;
//      ans = ()? (ans/2)*3 : ans*2;
//    }
//    return ans;
//  }

  public static void main(String[] args) {
    var l = new ArrayList<>(List.of(2, 0, 1));
    System.out.println(l);
    inverse(l);
    System.out.println(l);
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ApplyPermutation.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
