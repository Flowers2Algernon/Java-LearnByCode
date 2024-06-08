package com.cskaoyan.th58.future;

import java.util.concurrent.*;

public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //create a thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        //show future object
        Future<Object> objectFuture = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                TimeUnit.SECONDS.sleep(3);
                return "hello future object";
            }
        });

        //in main thread, 获取线程池的子线程执行结果
        Object o = objectFuture.get();
        System.out.println(o);
    }
}
