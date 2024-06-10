package com.cskaoyan.th58.all;

import java.util.concurrent.CompletableFuture;

public class AllOf {
    public static void main(String[] args) {
        //创建多个零元依赖异步任务，每个异步任务中对user设置一个属性值
        User user = new User();

        //first
        CompletableFuture<Void> first = CompletableFuture.runAsync(() -> {
            user.setAge(1);
            return;
        });

        //second
        CompletableFuture<Void> second = CompletableFuture.runAsync(() -> {
            user.setName("zs");
        });

        //third
        CompletableFuture<Void> third = CompletableFuture.runAsync(() -> {
            user.setPassword("123456");
        });

        //使用allOf方法
        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(first, second, third);
        completableFuture.join();

        //观察user对象是否设置成功
        System.out.println(user);
    }
}
