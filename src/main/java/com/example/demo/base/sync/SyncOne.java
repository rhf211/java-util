package com.example.demo.base.sync;

import cn.hutool.core.lang.hash.Hash;

import java.util.Date;
import java.util.HashMap;

/**
 * @author: 阮晗飞
 * @date: 2022/11/10
 */
public class SyncOne {
    private static HashMap<String, String> map = new HashMap<>();
    private static int j = 1;

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            SyncOne syncOne = new SyncOne();
            new Thread(() -> {
                syncOne.test3();
            }, "thread" + i).start();
        }
    }

    public synchronized static void test1() {
        System.out.println(j++);
    }

    public synchronized void test2() {
        System.out.println(j++);

    }
    public void test3(){
        synchronized (this){
            System.out.println(j++);
        }
    }
}
