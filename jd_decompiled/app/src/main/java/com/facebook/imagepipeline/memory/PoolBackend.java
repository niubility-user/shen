package com.facebook.imagepipeline.memory;

import javax.annotation.Nullable;

/* loaded from: classes.dex */
interface PoolBackend<T> {
    @Nullable
    T get(int i2);

    int getSize(T t);

    @Nullable
    T pop();

    void put(T t);
}
