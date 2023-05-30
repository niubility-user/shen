package com.jdpay.net;

import android.util.SparseArray;
import androidx.annotation.NonNull;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/* loaded from: classes18.dex */
public abstract class RequestAdapter {
    protected SparseArray<Object> extras;
    protected Type resultType;

    public abstract Object build(@NonNull Method method, @NonNull Object[] objArr, @NonNull ServiceFactory serviceFactory);

    public synchronized void putExtra(int i2, Object obj) {
        if (this.extras == null) {
            this.extras = new SparseArray<>();
        }
        this.extras.put(i2, obj);
    }

    public abstract Request request();

    public void setResultType(Type type) {
        this.resultType = type;
    }
}
