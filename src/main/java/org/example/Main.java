package org.example;

import edu.princeton.cs.algs4.StdOut;

import java.security.InvalidParameterException;

public class Main {
    public static void main(String[] args) {
        for (long n = 1; n <= 200; n++) {
            StdOut.printf("%d <= log2(%d)\n", log(2, n), n);
        }
    }

    public static long log(double base, long num) {
        if (!(base > 0 && base != 1)) {
            throw new InvalidParameterException("Base must be positive and non-equal to 1");
        }
        if (!(num > 0)) {
            throw new InvalidParameterException("Num must be positive");
        }

        long lo = Long.MIN_VALUE;
        long hi = Long.MAX_VALUE;

        long lo_half = lo / 2;
        long hi_half = hi / 2;
        long lo_rem = lo % 2;
        long hi_rem = hi % 2;

        while (lo < hi) {
            long mid = lo_half + hi_half + (lo_rem + hi_rem > 0 ? 1 : 0);
            double p = pow(base, mid);
            if (p <= num) {
                lo = mid;
                lo_half = lo / 2;
                lo_rem = lo % 2;
            } else {
                hi = mid - 1;
                hi_half = hi / 2;
                hi_rem = hi % 2;
            }
        }

        return lo;
    }

    private static double pow(double num, long deg) {
        double result = 1;

        boolean negative = deg < 0;
        if (negative) deg = -deg;
        while (deg != 0) {
            if (deg % 2 == 0) {
                num *= num;
                deg /= 2;
            } else {
                result *= num;
                deg--;
            }
        }
        if (negative) result = 1 / result;

        return result;
    }
}
