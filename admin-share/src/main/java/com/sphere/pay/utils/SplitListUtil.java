package com.sphere.pay.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SplitListUtil {

    private static <T> List<List<T>> splitList(List<T> batchList, long splitSize) {
        long maxSize = (batchList.size() + splitSize - 1) / splitSize;
        return Stream.iterate(0, n -> n + 1)
                .limit(maxSize)
                .parallel()
                .map(a -> batchList.parallelStream().skip(a * splitSize).limit(splitSize).collect(Collectors.toList()))
                .filter(b -> !b.isEmpty())
                .collect(Collectors.toList());
    }
}
