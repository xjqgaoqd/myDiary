package com.demo.diary;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//与countdownlatch优化
public class CyclicBarrierTest {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);
        CyclicBarrier cs = new CyclicBarrier(2,() -> {
            System.out.println("task1/task2 finish----");
        });
        for (int i = 0; i < 3; i++){//不会重新创建计数对象
            es.submit(() -> {
                System.out.println("task1 begin");
                try {
                    Thread.sleep(1000);
                    cs.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });

            es.submit(() -> {
                System.out.println("task2 begin");
                try {
                    Thread.sleep(1000);
                    cs.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        es.shutdown();
    }
}
