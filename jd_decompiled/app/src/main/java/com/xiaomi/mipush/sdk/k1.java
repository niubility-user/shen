package com.xiaomi.mipush.sdk;

import android.text.TextUtils;

/* loaded from: classes11.dex */
class k1 {
    int a = 0;
    String b = "";

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof k1)) {
            return false;
        }
        k1 k1Var = (k1) obj;
        return !TextUtils.isEmpty(k1Var.b) && k1Var.b.equals(this.b);
    }
}
