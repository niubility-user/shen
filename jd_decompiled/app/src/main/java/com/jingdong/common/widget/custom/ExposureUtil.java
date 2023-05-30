package com.jingdong.common.widget.custom;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes12.dex */
public class ExposureUtil {
    private HashMap<String, HashSet<String>> list;
    private ReentrantReadWriteLock lock;

    /* loaded from: classes12.dex */
    private static final class Instance {
        private static final ExposureUtil instance = new ExposureUtil();

        private Instance() {
        }
    }

    public static ExposureUtil getInstance() {
        return Instance.instance;
    }

    public void add(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.lock.writeLock().lock();
        HashSet<String> hashSet = this.list.containsKey(str) ? this.list.get(str) : null;
        if (hashSet == null) {
            hashSet = new HashSet<>();
            this.list.put(str, hashSet);
        }
        hashSet.add(str2);
        this.lock.writeLock().unlock();
    }

    public String get(String str) {
        String str2;
        this.lock.readLock().lock();
        if (this.list.containsKey(str)) {
            str2 = CustomMtaUtil.zuhe(this.list.get(str).toArray());
            this.list.get(str).clear();
            this.list.remove(str);
        } else {
            str2 = "";
        }
        this.lock.readLock().unlock();
        return str2;
    }

    private ExposureUtil() {
        this.list = new HashMap<>();
        this.lock = new ReentrantReadWriteLock();
    }
}
