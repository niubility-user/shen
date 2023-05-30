package com.jdpay.lib.cache;

import androidx.annotation.NonNull;

/* loaded from: classes18.dex */
public interface Cache {
    void clear() throws Exception;

    int length();

    @NonNull
    byte[] read() throws Exception;

    void write(@NonNull byte[] bArr) throws Exception;
}
