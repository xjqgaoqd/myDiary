package com.demo.diary;

import java.util.concurrent.locks.StampedLock;

public class StampLockTest {
    public static void main(String[] args) throws InterruptedException {
        DataContainerStamper dataContainerStamper = new DataContainerStamper(1000);

        new Thread(() -> {
            dataContainerStamper.read(1000);
        },"t1").start();
        Thread.sleep(500);
        new Thread(() -> {
            dataContainerStamper.write(0);
        },"t2").start();
    }
}
class DataContainerStamper{
    private int data;
    private final StampedLock lock = new StampedLock();

    public DataContainerStamper(int data) {
        this.data = data;
    }

    public void write(int newData){
        long stamp = lock.writeLock();
        System.out.println("write lock "+ stamp);
        try {
            Thread.sleep(2000);
            this.data = newData;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("write unlock"+stamp);
            lock.unlock(stamp);
        }
    }

    public int read(int readTime){
        long stamp = lock.tryOptimisticRead();
        System.out.println("optimistic");
        if (lock.validate(stamp)){
            System.out.println("read finish"+stamp);
            return data;
        }
        System.out.println("updating to read lock");//升级锁为读锁
        try {
            stamp = lock.readLock();
            System.out.println("read lock"+stamp);
            Thread.sleep(readTime);
            System.out.println("read finish"+stamp);
            return data;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return 0;
        } finally {
            System.out.println("read unlock"+stamp);
            lock.unlockRead(stamp);
        }
    }
}