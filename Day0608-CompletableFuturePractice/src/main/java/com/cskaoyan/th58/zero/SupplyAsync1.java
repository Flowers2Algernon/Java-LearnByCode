package com.cskaoyan.th58.zero;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SupplyAsync1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //测试static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello supplyAsync");
        String s = future.get();
        System.out.println(s);
    }
}
