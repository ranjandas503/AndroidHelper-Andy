package com.menasr.andyktx

class Algorithm {

    private var array: IntArray? = null
    private var tempMergArr: IntArray? = null
    private var length: Int = 0

    /**For bubble sort in Array*/
    fun <T : Comparable<T>> bubbleSort(inputArray: Array<T>, isAscending: Boolean) {
        var temp: T
        var swapped = true
        var j = 1
        while ((j < inputArray.size) and swapped) {
            swapped = false
            for (i in 0 until inputArray.size - j) {
                if (if (isAscending) inputArray[i].compareTo(inputArray[i + 1]) > 0
                        else inputArray[i].compareTo(inputArray[i + 1]) > 0) {
                    temp = inputArray[i]
                    inputArray[i] = inputArray[i + 1]
                    inputArray[i + 1] = temp
                    swapped = true
                }
            }
            j++
        }
    }

    /**For bubble sort in List*/
    fun <T : Comparable<T>> bubbleSort(inputArray: MutableList<T>, isAscending: Boolean) {
        var temp: T
        var swapped = true
        var j = 1
        while ((j < inputArray.size) and swapped) {
            swapped = false
            for (i in 0 until inputArray.size - j) {
                if (if (isAscending)
                            inputArray[i].compareTo(inputArray[i + 1]) > 0
                        else
                            inputArray[i].compareTo(inputArray[i + 1]) < 0) {
                    temp = inputArray[i]
                    inputArray[i] = inputArray[i + 1]
                    inputArray[i + 1] = temp
                    swapped = true
                }
            }
            j++
        }
    }

    /**
     * Sort the given array by merge sort procedure
     *
     * @param inputArr array which is to be sorted
     *                 currently, it supports only numbers
     */
    fun sort(inputArr: IntArray) {
        this.array = inputArr
        this.length = inputArr.size
        this.tempMergArr = IntArray(length)
        doMergeSort(0, length - 1)
    }

    private fun doMergeSort(lowerIndex: Int, higherIndex: Int) {

        if (lowerIndex < higherIndex) {
            val middle = lowerIndex + (higherIndex - lowerIndex) / 2
            // Below step sorts the left side of the array
            doMergeSort(lowerIndex, middle)
            // Below step sorts the right side of the array
            doMergeSort(middle + 1, higherIndex)
            // Now merge both sides
            mergeParts(lowerIndex, middle, higherIndex)
        }
    }

    private fun mergeParts(lowerIndex: Int, middle: Int, higherIndex: Int) {

        System.arraycopy(array, lowerIndex, tempMergArr, lowerIndex, higherIndex + 1 - lowerIndex)

        var i = lowerIndex
        var j = middle + 1
        var k = lowerIndex
        while (i <= middle && j <= higherIndex) {
            if (tempMergArr!![i] <= tempMergArr!![j]) {
                array!![k] = tempMergArr!![i]
                i++
            } else {
                array!![k] = tempMergArr!![j]
                j++
            }
            k++
        }
        while (i <= middle) {
            array!![k] = tempMergArr!![i]
            k++
            i++
        }

    }

}