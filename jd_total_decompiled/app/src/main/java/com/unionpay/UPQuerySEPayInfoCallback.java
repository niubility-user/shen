package com.unionpay;

import android.os.Bundle;

/* loaded from: classes11.dex */
public interface UPQuerySEPayInfoCallback {
    void onError(String str, String str2, String str3, String str4);

    void onResult(String str, String str2, int i2, Bundle bundle);
}
