package com.cskaoyan.th58.one;

import java.util.concurrent.CompletableFuture;

public class ThenRun1 {
    public static void main(String[] args) {
        //测试CompletableFuture<VOid> thenRun(Runnable action)方法
        //当当前异步任务执行完之后，会接着触发下一个异步任务，无参且没有返回值
        CompletableFuture<Void> firstFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        firstFuture.thenRun(()->{
            System.out.println(Thread.currentThread().getName());
        });

    }
}
