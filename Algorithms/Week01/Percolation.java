import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/*------------------------------------------------------
 *  Author:        Vincent Bundage
 *  Written:       9/11/2015
 *  Last updated:  9/15/2015
 *
 *  Compilation:   javac Percolation.java
 *  Execution:     javac Percolation
 *
 *  The Percolation program creates a grid of n-by-n sites
 *  that can be modified to have open sites and check
 *  if the grid percolates.
 *------------------------------------------------------*/
public class Percolation {
  private int n;
  private int[] oneDimensionUFObject;
  private boolean[][] boolGrid;
  private WeightedQuickUnionUF grid;
  //  create n-by-n grid, with all sites blocked
  public Percolation(int n) {
    this.n = n;
    if (n <= 0) throw new IllegalArgumentException();
    grid = new WeightedQuickUnionUF((n+1)*n+2);
    oneDimensionUFObject = new int[(n+1)*n+2];
    boolGrid = new boolean[n+2][n+2];
    for (int i = 1; i <= n; i++) {
      grid.union(2, xyTo1D(1, i));
      grid.union((n+1)*n+1, xyTo1D(n, i));
    }
  }
  //  open site (row i, column j) if it is not open already
  public void open(int i, int j) {
    validate(i, j);
    if (!isOpen(i, j)) {
      boolGrid[i][j] = true;
      if (boolGrid[i][j-1]) grid.union(xyTo1D(i, j), xyTo1D(i, j-1));
      if (boolGrid[i][j+1]) grid.union(xyTo1D(i, j), xyTo1D(i, j+1));
      if (boolGrid[i-1][j]) grid.union(xyTo1D(i, j), xyTo1D(i-1, j));
      if (boolGrid[i+1][j]) grid.union(xyTo1D(i, j), xyTo1D(i+1, j));
    }
  }
  //  is site (row i, column j) open?
  public boolean isOpen(int i, int j) {
    validate(i, j);
    return boolGrid[i][j];
  }
  //  is site (row i, column j) closed?
  public boolean isFull(int i, int j) {
    validate(i, j);
    if (isOpen(i, j)) return grid.connected(2, xyTo1D(i, j));
    return false;
  }
  //  does the system percolate?
  public boolean percolates() {
    if (n == 1) return (isOpen(1,1));
    return grid.connected(2, (n+1)*n+1);
  }
  //  validate if i and j are in the range of 1 to n
  private void validate(int i, int j) {
    if (i <= 0 || i > n || j <= 0 || j > n)
      throw new IndexOutOfBoundsException();
  }
  //  convert 2D coordinates to 1D UF coordinates
  private int xyTo1D(int x, int y) {
    int conversionIndex = n * x + y;
    oneDimensionUFObject[conversionIndex] = conversionIndex;
    return conversionIndex;
  }
  public static void main(String[] args) {
  }
}
