package com.cskaoyan.th58.two;

import java.util.concurrent.*;

public class ThenCombine {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //thenCombine();

        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        //第一步，实现第一个有返回值的零元依赖的异步任务
        CompletableFuture<String> first = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName());
            return "first";
        }, executorService);

        //第二部，实现第二个有返回值的零元依赖异步任务
        CompletableFuture<String> second = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName());
            return "second";
        }, executorService);

        //实现二元依赖的异步任务
        CompletableFuture<String> completableFuture = first.thenCombineAsync(second, (str1, str2) -> {
            System.out.println(Thread.currentThread().getName());
            return str1 + str2;
        }, executorService);
        String s = completableFuture.get();
        System.out.println(s);
    }

    private static void thenCombine() throws InterruptedException, ExecutionException {
        //第一步，实现第一个有返回值的零元依赖的异步任务
        CompletableFuture<String> first = CompletableFuture.supplyAsync(() -> "first");

        //第二部，实现第二个有返回值的零元依赖异步任务
        CompletableFuture<String> second = CompletableFuture.supplyAsync(() -> "second");

        //实现二元依赖的异步任务
        CompletableFuture<String> completableFuture = first.thenCombine(second, (str1, str2) -> str1 + str2);
        String s = completableFuture.get();
        System.out.println(s);
    }
}
