package com.facebook.react.bridge;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public interface Promise {
    @Deprecated
    void reject(String str);

    void reject(String str, @Nonnull WritableMap writableMap);

    void reject(String str, String str2);

    void reject(String str, String str2, @Nonnull WritableMap writableMap);

    void reject(String str, String str2, Throwable th);

    void reject(String str, String str2, Throwable th, WritableMap writableMap);

    void reject(String str, Throwable th);

    void reject(String str, Throwable th, WritableMap writableMap);

    void reject(Throwable th);

    void reject(Throwable th, WritableMap writableMap);

    void resolve(@Nullable Object obj);
}
