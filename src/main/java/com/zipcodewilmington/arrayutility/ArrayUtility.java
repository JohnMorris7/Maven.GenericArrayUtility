package com.zipcodewilmington.arrayutility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by leon on 3/6/18.
 */
public class ArrayUtility<T> {

    T[] inputArray;

    public ArrayUtility(T[] inputArray) {
        this.inputArray = inputArray;
    }

    public T[] removeValue(T valueToRemove) {
       return Arrays.stream(inputArray).filter(z -> z != valueToRemove).toArray(newSize -> Arrays.copyOf(inputArray, newSize));
    }

    public Integer countDuplicatesInMerge(T[] arrayToMerge, T valueToEvaluate) {
        T[] mergedArray = (T[]) Stream.of(inputArray, arrayToMerge).flatMap(Stream::of).toArray();
            int counter = 0;
            for (int i = 0; i < mergedArray.length; i++) {
                if (valueToEvaluate == mergedArray[i]) {
                    counter ++;
                }
            }
            return counter;
        }


    public T getMostCommonFromMerge(T[] arrayToMerge) {
        T[] mergedArray = (T[]) Stream.of(inputArray, arrayToMerge).flatMap(Stream::of).toArray();
        Map<T, Long> map = Arrays.stream(mergedArray).collect(Collectors.groupingBy(x ->x, Collectors.counting()));
        return map.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();

    }

    public Integer getNumberOfOccurrences(T valueToEvaluate) {
        return Arrays.stream(inputArray).filter(z -> z == valueToEvaluate).collect(Collectors.reducing(0, e -> 1, Integer::sum));

    }
}
