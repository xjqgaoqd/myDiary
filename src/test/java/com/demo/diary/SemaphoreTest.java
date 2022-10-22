package com.demo.diary;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10;i++){
            new Thread( () -> {
                try {
                    semaphore.acquire();
                    System.out.println("running+++");
                    Thread.sleep(1000);
                    System.out.println("end---");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }).start();        }
    }
}
