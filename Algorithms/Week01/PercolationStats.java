import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;

public class PercolationStats {
  private double[] thresholds;
  private int T;
  // perform T independent experiments on an N-by-N grid
  public PercolationStats(int n, int t) {
    if (n <= 0 || t <= 0) throw new IllegalArgumentException();
    T = t;
    thresholds = new double[t];
    for (int i = 0; i < t; i++) {
      int count = 0;
      Percolation perc = new Percolation(n);
      while (!perc.percolates()) {
        int randRow = StdRandom.uniform(1, n+1);
        int randCol = StdRandom.uniform(1, n+1);
        if (!perc.isOpen(randRow, randCol)) {
          perc.open(randRow, randCol);
          count++;
        }
      }
      double percThreshold = (double) count/(n*n);
      thresholds[i] = percThreshold;
    }
  }
  // sample mean of percolation threshold
  public double mean() {
    return StdStats.mean(thresholds);
  }
  // sample standard deviation of percolation threshold
  public double stddev() {
    return StdStats.stddev(thresholds);
  }
  // low endpoint of 95% confidence interval
  public double confidenceLo() {
    return mean() - (1.96*stddev()/Math.sqrt(T));
  }
  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return mean() + (1.96*stddev()/Math.sqrt(T));
  }
  // test client
  public static void main(String[] args) {
    int n = StdIn.readInt();
    int t = StdIn.readInt();
    PercolationStats test = new PercolationStats(n, t);
    System.out.println("mean                    " + "= " + test.mean());
    System.out.println("stddev                  " + "= " + test.stddev());
    System.out.println("95% confidence interval = " + test.confidenceLo()
      + ", " + test.confidenceHi());
  }
}
