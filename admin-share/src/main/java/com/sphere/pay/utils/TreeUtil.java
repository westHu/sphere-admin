package com.sphere.pay.utils;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TreeUtil {

    public static <E> List<E> makeTree(List<E> list, Predicate<E> root, BiFunction<E, E, Boolean> parentCheck,
                                       BiConsumer<E, List<E>> children) {

        return list.stream().filter(root).peek(x -> children.accept(x, makeChildren(x, list, parentCheck, children)))
                .collect(Collectors.toList());
    }

    public static <E> List<E> makeChildren(E parent, List<E> allData, BiFunction<E, E, Boolean> parentCheck,
                                           BiConsumer<E, List<E>> children) {
        return allData.stream().filter(x -> parentCheck.apply(parent, x)).peek(x -> children.accept(x, makeChildren(x
                , allData, parentCheck, children))).collect(Collectors.toList());
    }

}
