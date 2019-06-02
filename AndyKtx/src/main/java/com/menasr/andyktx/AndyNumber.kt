package com.menasr.andyktx

import java.util.*

class AndyNumber {
    /**
     * Get factorial of a number
     */
    fun getFatorial(number: Int): Long {
        return if (number == 0) 0
        else if (number == 1) 1
        else number * getFatorial(number - 1)
    }

    /**
     * Get Fibonacci series upto total no of digits
     */
    fun getFibonacci(totalDigits: Int): List<Int> {
        val allFibo = LinkedList<Int>()
        var t1 = 0
        var t2 = 1
        for (i in 1..totalDigits) {
            allFibo.add(t1)

            val sum = t1 + t2
            t1 = t2
            t2 = sum
        }
        return allFibo
    }


    /**
     * Function to get Progress percentage
     *
     * @param currentDuration current progress duration
     * @param totalDuration total duration
     */
    fun getProgressPercentage(currentDuration: Long, totalDuration: Long): Int {
        val currentSeconds = (currentDuration / 1000).toInt().toLong()
        val totalSeconds = (totalDuration / 1000).toInt().toLong()

        return (currentSeconds.toDouble() / totalSeconds * 100).toInt()
    }

    /**
     * This method filter the value in array, which is two frequent
     *
     * @param input  input array to filter
     * @param output output array to filter
     * @param filter factor to filter input and output(best is 0.05 to 0.25)
     * @return output array on basis of filter
     */
    fun lowPassFilter(input: FloatArray, output: FloatArray?, filter: Float): FloatArray {
        if (output == null) return input
        for (i in input.indices)
            output[i] = output[i] + filter * (input[i] - output[i])

        return output
    }
}