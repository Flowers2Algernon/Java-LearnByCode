package com.cskaoyan.th58.zero;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SupplyAsync2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //测试static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
        //在指定线程池中，执行下一个异步任务--有返回值的情况
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            String name = Thread.currentThread().getName();
            return name;
        }, executorService);
        String s = future.get();
        System.out.println(s);
    }
}
