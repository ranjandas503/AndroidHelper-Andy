package com.menasr.andy;

import java.util.List;

public class Algorithm {

    private int[] array;
    private int[] tempMergArr;

    /**For bubble sort in Array*/
    public <T extends Comparable<T>> void bubbleSort(T[] inputArray, boolean isAscending) {
        T temp;
        boolean swapped = true;
        for (int j = 1; j < inputArray.length & swapped; j++) {
            swapped = false;
            for (int i = 0; i < inputArray.length - j; i++) {
                if (isAscending ? (inputArray[i].compareTo(inputArray[i + 1]) > 0) :
                        (inputArray[i].compareTo(inputArray[i + 1]) > 0)) {
                    temp = inputArray[i];
                    inputArray[i] = inputArray[i + 1];
                    inputArray[i + 1] = temp;
                    swapped = true;
                }
            }
        }
    }

    /**For bubble sort in List*/
    public <T extends Comparable<T>> void bubbleSort(List<T> inputArray, boolean isAscending) {
        T temp;
        boolean swapped = true;
        for (int j = 1; j < inputArray.size() & swapped; j++) {
            swapped = false;
            for (int i = 0; i < inputArray.size() - j; i++) {
                if (isAscending ? (inputArray.get(i).compareTo(inputArray.get(i + 1)) > 0) :
                        (inputArray.get(i).compareTo(inputArray.get(i + 1)) < 0)) {
                    temp = inputArray.get(i);
                    inputArray.set(i, inputArray.get(i + 1));
                    inputArray.set(i + 1, temp);
                    swapped = true;
                }
            }
        }
    }

    /**
     * Sort the given array by merge sort procedure
     *
     * @param inputArr array which is to be sorted
     *                 currently, it supports only numbers
     */
    public void sort(int[] inputArr) {
        this.array = inputArr;
        int length = inputArr.length;
        this.tempMergArr = new int[length];
        doMergeSort(0, length - 1);
    }

    private void doMergeSort(int lowerIndex, int higherIndex) {

        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            doMergeSort(lowerIndex, middle);
            // Below step sorts the right side of the array
            doMergeSort(middle + 1, higherIndex);
            // Now merge both sides
            mergeParts(lowerIndex, middle, higherIndex);
        }
    }

    private void mergeParts(int lowerIndex, int middle, int higherIndex) {

        System.arraycopy(array, lowerIndex, tempMergArr, lowerIndex, higherIndex + 1 - lowerIndex);

        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (tempMergArr[i] <= tempMergArr[j]) {
                array[k] = tempMergArr[i];
                i++;
            } else {
                array[k] = tempMergArr[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            array[k] = tempMergArr[i];
            k++;
            i++;
        }

    }

    /**
     * This method filter the value in array, which is two frequent
     *
     * @param input  input array to filter
     * @param output output array to filter
     * @param filter factor to filter input and output(best is 0.05 to 0.25)
     * @return output array on basis of filter
     */
    public float[] lowPassFilter(float[] input, float[] output, float filter) {
        if (output == null) return input;

        for (int i = 0; i < input.length; i++) {
            output[i] = output[i] + filter * (input[i] - output[i]);
        }
        return output;
    }

}




