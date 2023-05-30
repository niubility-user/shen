package com.jingdong.common.widget.custom;

import java.util.HashMap;

/* loaded from: classes12.dex */
public final class CusPageHold {
    private static CusPageHold instance;
    private final String key1 = "key1";
    private final String key2 = "key2";
    private final String key3 = "key3";
    private HashMap<String, Object> hashMap = new HashMap<>();

    private CusPageHold() {
    }

    public static CusPageHold getInstance() {
        if (instance == null) {
            instance = new CusPageHold();
        }
        return instance;
    }

    public void add(String str, Object obj) {
        this.hashMap.put(str, obj);
    }

    public void add1(Object obj) {
        this.hashMap.put("key1", obj);
    }

    public void add2(Object obj) {
        this.hashMap.put("key2", obj);
    }

    public void add3(Object obj) {
        this.hashMap.put("key3", obj);
    }

    public Object get(String str) {
        return this.hashMap.get(str);
    }

    public Object get1() {
        return this.hashMap.get("key1");
    }

    public Object get2() {
        return this.hashMap.get("key2");
    }

    public Object get3() {
        return this.hashMap.get("key3");
    }

    public void onCreate() {
        this.hashMap.clear();
    }
}
