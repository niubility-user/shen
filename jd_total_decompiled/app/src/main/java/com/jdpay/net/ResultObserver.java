package com.jdpay.net;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes18.dex */
public interface ResultObserver<DATA> {
    void onFailure(@NonNull Throwable th);

    void onSuccess(@Nullable DATA data);
}
