package com.cskaoyan.th58.zero;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunAsync2 {
    public static void main(String[] args) {
        //此处测试static CompletableFuture<void> runAsync(Runnable runnable, Executor executor)
        //即在指定线程池中，执行下一个异步任务
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            String name = Thread.currentThread().getName();
            System.out.println(name);
        }, executorService);
        future.join();
    }
}
