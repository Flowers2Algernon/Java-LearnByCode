package com.cskaoyan.th58.zero;

import java.util.concurrent.CompletableFuture;

public class RunAsync {
    public static void main(String[] args) {
        //static CompletableFuture<void> runAsync(Runnable runnable)
        //测试没有指定线程池的情况
        CompletableFuture<Void> helloCompletableFuture = CompletableFuture.runAsync(() -> System.out.println("hello completableFuture"));
        helloCompletableFuture.join();
    }
}
