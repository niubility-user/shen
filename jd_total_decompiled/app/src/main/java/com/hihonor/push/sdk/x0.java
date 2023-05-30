package com.hihonor.push.sdk;

import android.text.TextUtils;
import java.util.Arrays;

/* loaded from: classes12.dex */
public class x0 {
    public final String a;
    public final int b;

    public x0(String str) {
        this.a = str;
        this.b = a(str);
    }

    public static int a(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    public static x0 b(String str) {
        return new x0(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || x0.class != obj.getClass()) {
            return false;
        }
        return TextUtils.equals(this.a, ((x0) obj).a);
    }

    public final int hashCode() {
        return this.b;
    }
}
