package com.jingdong.sdk.threadpool;

import com.jingdong.sdk.threadpool.common.Disposable;
import com.jingdong.sdk.threadpool.utils.LogUtil;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: classes10.dex */
public class CompositeDisposable {
    private ConcurrentLinkedQueue<Disposable> a = new ConcurrentLinkedQueue<>();

    public void add(Disposable disposable) {
        this.a.add(disposable);
    }

    public void dispose() {
        LogUtil.e("before dispose:::size::" + this.a.size());
        Iterator<Disposable> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().dispose();
            it.remove();
        }
        LogUtil.e("after dispose:::size::" + this.a.size());
    }

    public int size() {
        return this.a.size();
    }
}
