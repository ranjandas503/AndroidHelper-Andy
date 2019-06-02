package com.menasr.andy;

import java.util.LinkedList;
import java.util.List;

public class AndyNumber {
    /**
     * Function to get Progress percentage
     *
     * @param currentDuration current progress duration
     * @param totalDuration   total duration
     */
    public int getProgressPercentage(long currentDuration, long totalDuration) {
        Double percentage = (double) 0;

        long currentSeconds = (int) (currentDuration / 1000);
        long totalSeconds = (int) (totalDuration / 1000);

        // calculating percentage
        percentage = (((double) currentSeconds) / totalSeconds) * 100;
        return percentage.intValue();
    }


    /**
     * Get factorial of a number
     */
    public long getFatorial(int number) {
        if (number == 0)
            return 0;
        else if (number == 1)
            return 1;
        else return number * getFatorial(number - 1);
    }

    /**
     * Get Fibonacci series upto total no of digits
     */
    public List<Integer> getFibonacci(int totalDigits) {
        List<Integer> allFibo = new LinkedList<>();
        int t1 = 0, t2 = 1;
        for (int i = 1; i <= totalDigits; ++i) {
            allFibo.add(t1);

            int sum = t1 + t2;
            t1 = t2;
            t2 = sum;
        }
        return allFibo;
    }
}
