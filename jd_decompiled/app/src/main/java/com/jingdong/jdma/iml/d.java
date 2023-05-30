package com.jingdong.jdma.iml;

import java.util.HashMap;

/* loaded from: classes12.dex */
public abstract class d implements Runnable {
    private HashMap<String, String> a;

    public d(HashMap<String, String> hashMap) {
        this.a = hashMap;
    }

    public abstract void a(HashMap<String, String> hashMap);

    @Override // java.lang.Runnable
    public void run() {
        HashMap<String, String> hashMap = this.a;
        if (hashMap != null) {
            a(hashMap);
        }
    }
}
