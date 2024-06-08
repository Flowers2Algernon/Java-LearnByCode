package com.cskaoyan.th58.one;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThenRun2 {
    public static void main(String[] args) {
        //测试CompletableFuture<VOid> thenRunAsync(Runnalbe action)
        //thenRunAsync方法参数和返回结果同thenRun,但是区别是thenRunAsync方法传递的异步任务所指向的线程是一个新的线程
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<Void> firstFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
        },executorService);
        firstFuture.thenRunAsync(()->{
            System.out.println(Thread.currentThread().getName());
        },executorService);
    }
}
