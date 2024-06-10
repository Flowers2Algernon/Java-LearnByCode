package com.cskaoyan.th58.exceptional;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ExceptionalTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<int[]> completableFuture = CompletableFuture.supplyAsync(() -> {
            int[] arr = new int[3];

            //制造异常
            System.out.println(arr[4]);

            return arr;
        }).exceptionally(e -> {
            e.printStackTrace();
            return new int[0];
        });
        int[] ints = completableFuture.get();
        System.out.println(ints.length);
    }
}
