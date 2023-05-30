package com.jingdong.aura.core.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes4.dex */
public class c {
    static Map<String, ReentrantReadWriteLock> a = new HashMap();

    public static void a(String str) {
        ReentrantReadWriteLock reentrantReadWriteLock;
        synchronized (a) {
            reentrantReadWriteLock = a.get(str);
            if (reentrantReadWriteLock == null) {
                reentrantReadWriteLock = new ReentrantReadWriteLock();
                a.put(str, reentrantReadWriteLock);
            }
        }
        reentrantReadWriteLock.writeLock().lock();
    }

    public static void b(String str) {
        synchronized (a) {
            ReentrantReadWriteLock reentrantReadWriteLock = a.get(str);
            if (reentrantReadWriteLock == null) {
                return;
            }
            reentrantReadWriteLock.writeLock().unlock();
        }
    }
}
