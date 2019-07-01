package epi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RotatedMatrix {

  private List<List<Integer>> wrappedSquareMatrix;

  public RotatedMatrix(List<List<Integer>> squareMatrix) {
    this.wrappedSquareMatrix = squareMatrix;
  }

  public int readEntry(int i, int j) {
    return wrappedSquareMatrix.get(wrappedSquareMatrix.size() - 1 - j).get(i);
  }

  public void writeEntry(int i, int j, int v) {
    this.wrappedSquareMatrix = copyOnWrite(wrappedSquareMatrix);
    wrappedSquareMatrix.get(wrappedSquareMatrix.size() - 1 - j).set(i, v);
  }

  public static void printMatrix(List<List<Integer>> matrix) {
    System.out.println();
    matrix.forEach(System.out::println);
  }

  public List<List<Integer>>  copyOnWrite(List<List<Integer>> matrix) {
    List<List<Integer>> result = new ArrayList<>();
    matrix.forEach(row -> result.add(new ArrayList<>(row)));
    return result;
  }

  public static void main(String[] args) {
    List<List<Integer>> squareMatrix = new ArrayList<>(List.of(
        new ArrayList<>(List.of(1, 2, 3)),
        new ArrayList<>(List.of(4, 5, 6)),
        new ArrayList<>(List.of(7, 8, 9))
    ));
    RotatedMatrix rotated = new RotatedMatrix(squareMatrix);
    printMatrix(squareMatrix);
    rotated.writeEntry(2, 2, 0);
    printMatrix(squareMatrix);
  }
}
