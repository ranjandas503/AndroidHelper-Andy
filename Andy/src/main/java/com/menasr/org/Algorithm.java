package com.menasr.org;

import java.util.List;

public class Algorithm {

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

    private int[] array;
    private int[] tempMergArr;
    private int length;

    public void sort(int inputArr[]) {
        this.array = inputArr;
        this.length = inputArr.length;
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

}




