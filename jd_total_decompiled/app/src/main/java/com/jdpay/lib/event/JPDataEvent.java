package com.jdpay.lib.event;

import androidx.annotation.Nullable;

/* loaded from: classes18.dex */
public class JPDataEvent<D> extends JPEvent {
    public final D data;

    public JPDataEvent(int i2, D d) {
        super(i2);
        this.data = d;
    }

    public JPDataEvent(int i2, @Nullable String str, @Nullable D d) {
        super(i2, str);
        this.data = d;
    }
}
