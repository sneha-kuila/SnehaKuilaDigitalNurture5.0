import java.util.HashMap;
import java.util.Map;

public class FinancialForecasting {

    /**
     * Recursively projects a future value given a present value,
     * an annual growth rate, and a number of years.
     * futureValue(P, r, n) = P * (1 + r)^n
     *
     * O(n) time, O(n) call stack space (naive recursion).
     */
    public static double futureValueRecursive(double presentValue, double growthRate, int years) {
        if (years == 0) {
            return presentValue;
        }
        return futureValueRecursive(presentValue, growthRate, years - 1) * (1 + growthRate);
    }

    /**
     * Memoized version to avoid recomputation when forecasting for
     * multiple year counts with the same rate.
     */
    public static double futureValueMemoized(double presentValue, double growthRate, int years,
                                              Map<Integer, Double> cache) {
        if (years == 0) {
            return presentValue;
        }
        if (cache.containsKey(years)) {
            return cache.get(years);
        }
        double result = futureValueMemoized(presentValue, growthRate, years - 1, cache) * (1 + growthRate);
        cache.put(years, result);
        return result;
    }

    public static void main(String[] args) {
        double presentValue = 100000; // e.g. starting revenue
        double growthRate = 0.08;     // 8% annual growth
        int years = 5;

        double result = futureValueRecursive(presentValue, growthRate, years);
        System.out.printf("Projected value after %d years: %.2f%n", years, result);

        Map<Integer, Double> cache = new HashMap<>();
        double memoResult = futureValueMemoized(presentValue, growthRate, years, cache);
        System.out.printf("Memoized projected value after %d years: %.2f%n", years, memoResult);
    }
}
