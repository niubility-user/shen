package com.jingdong.common.unification.router.builder;

import android.os.Bundle;
import com.jingdong.common.unification.router.builder.BaseBundleBuilder;

/* loaded from: classes6.dex */
public abstract class BaseBundleBuilder<T extends BaseBundleBuilder, K> implements IBuilder<K> {
    protected Bundle mBundle = new Bundle();

    public Bundle getBundle() {
        return this.mBundle;
    }

    public T putBoolean(String str, boolean z) {
        this.mBundle.putBoolean(str, z);
        return this;
    }

    public T putByte(String str, byte b) {
        this.mBundle.putByte(str, b);
        return this;
    }

    public T putChar(String str, char c2) {
        this.mBundle.putChar(str, c2);
        return this;
    }

    public T putInt(String str, int i2) {
        this.mBundle.putInt(str, i2);
        return this;
    }

    public T putLong(String str, long j2) {
        this.mBundle.putLong(str, j2);
        return this;
    }

    public T putString(String str, String str2) {
        this.mBundle.putString(str, str2);
        return this;
    }
}
