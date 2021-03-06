package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class BuyAndSellStock {
  @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
  public static double computeMaxProfit(List<Double> prices) {
    // TODO - you fill in here.
    if (prices.isEmpty())
      return 0.0;
    double maxProfit = 0.0;
    double currentMin = prices.get(0);
    for (int i = 1; i < prices.size(); ++i) {
      currentMin = Math.min(currentMin, prices.get(i));
      maxProfit = Math.max(maxProfit, prices.get(i) - currentMin);
    }
    return maxProfit;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStock.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
