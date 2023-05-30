package com.jingdong.common.listui;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class Observable {
    private volatile boolean isCancel;
    private ConcurrentHashMap<String, Action> mConcurrentHashMap = new ConcurrentHashMap<>();
    private Handler mHandler = new Handler(Looper.getMainLooper());

    /* loaded from: classes5.dex */
    public interface Action<T> {
        void call(T t);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void post(String str, Object obj) {
        if (str == null || !this.mConcurrentHashMap.containsKey(str) || this.isCancel) {
            return;
        }
        try {
            this.mConcurrentHashMap.get(str).call(obj);
        } catch (Exception unused) {
        }
    }

    public void clear() {
        this.isCancel = true;
        this.mHandler = null;
        this.mConcurrentHashMap.clear();
    }

    public final void postMainThread(final String str, final Object obj) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            post(str, obj);
            return;
        }
        Handler handler = this.mHandler;
        if (handler == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.jingdong.common.listui.Observable.1
            @Override // java.lang.Runnable
            public void run() {
                Observable.this.post(str, obj);
            }
        });
    }

    public <T> Observable subscribe(String str, @NonNull Action<T> action) {
        if (str != null) {
            this.mConcurrentHashMap.put(str, action);
        }
        return this;
    }
}
