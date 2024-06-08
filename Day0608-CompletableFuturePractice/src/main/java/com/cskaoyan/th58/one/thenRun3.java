package com.cskaoyan.th58.one;

import java.util.Timer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class thenRun3 {
    public static void main(String[] args) {
        //测试CompletableFuture<VOid> thenRunAsync(Runnalbe action)
        //thenRunAsync方法参数和返回结果同thenRun,但是区别是thenRunAsync方法传递的异步任务所指向的线程是一个新的线程
        CompletableFuture<Void> firstFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        CompletableFuture<Void> secondFuture = firstFuture.thenRunAsync(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        secondFuture.join();
    }
}
