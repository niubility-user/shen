package com.jingdong.common.network;

import androidx.annotation.NonNull;

/* loaded from: classes5.dex */
public abstract class AbstractTask<T> implements Comparable<AbstractTask> {
    private int mPriority;

    public abstract void executeTask(T t);

    @Override // java.lang.Comparable
    public int compareTo(@NonNull AbstractTask abstractTask) {
        return this.mPriority - abstractTask.mPriority;
    }
}
