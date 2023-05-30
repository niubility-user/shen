package com.jingdong.jdsdk;

import com.jingdong.sdk.oklog.OKLog;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/* loaded from: classes14.dex */
public class JDSoftReference<T> extends SoftReference<T> {
    private final String TAG;
    private int key;
    private Object tag;

    public JDSoftReference(T t) {
        super(t);
        this.TAG = getClass().getSimpleName();
    }

    public int getKey() {
        if (OKLog.D) {
            OKLog.d(this.TAG, "getKey() = " + this.key);
        }
        return this.key;
    }

    public Object getTag() {
        if (OKLog.D) {
            OKLog.d(this.TAG, "getTag() = " + this.tag);
        }
        return this.tag;
    }

    public void setKey(int i2) {
        if (OKLog.D) {
            OKLog.d(this.TAG, "setKey() = " + i2);
        }
        this.key = i2;
    }

    public void setTag(Object obj) {
        if (OKLog.D) {
            OKLog.d(this.TAG, "setTag() = " + obj);
        }
        this.tag = obj;
    }

    public JDSoftReference(T t, ReferenceQueue<? super T> referenceQueue) {
        super(t, referenceQueue);
        this.TAG = getClass().getSimpleName();
    }
}
