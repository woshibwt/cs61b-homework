package hw2;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.lang.IllegalArgumentException;

public class PercolationStats {
    // perform T independent experiments on an N-by-N grid
    private int N;
    private int T;
    private double[] threshold;

    public PercolationStats(int N, int T, PercolationFactory pf) throws IllegalArgumentException {
        if (N <= 0 || T <= 0) {
            throw new  IllegalArgumentException();
        }
        this.N = N;
        this.T = T;
        threshold = new double[T];

        /* Monte Carlo Simulation */
        for (int t = 0; t < T; t ++) {
            Percolation p = pf.make(N);
            threshold[t] = simulate(p);
        }
    }
    /* Open randomly until the system percolates. Return the threshold */
    private double simulate(Percolation p){
        int[] openOrder = StdRandom.permutation(N * N);
        for (int i = 0; !p.percolates(); i ++ ) {
            int x = coordinate(openOrder[i])[0];
            int y = coordinate(openOrder[i])[1];
            p.open(x, y);
        }
        return ((double) p.numberOfOpenSites()) / (N * N);
    }

    private int[] coordinate(int index) {
        return new int[] {index / N, index % N};
    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(threshold);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(threshold);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow(){
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh(){
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }



}
