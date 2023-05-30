package com.jdpay.bury;

import androidx.annotation.NonNull;
import com.jdpay.bury.proguard.APIKeep;
import com.jdpay.bury.proguard.ImplMethodsKeep;

@APIKeep
@ImplMethodsKeep
/* loaded from: classes18.dex */
public interface DynamicValue {
    @NonNull
    String get();
}
