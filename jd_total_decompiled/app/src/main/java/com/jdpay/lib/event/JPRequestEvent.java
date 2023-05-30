package com.jdpay.lib.event;

import androidx.annotation.Nullable;

/* loaded from: classes18.dex */
public class JPRequestEvent extends JPEvent {
    public final int requestId;

    public JPRequestEvent(int i2, int i3) {
        super(i2);
        this.requestId = i3;
    }

    @Override // com.jdpay.lib.event.JPEvent
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && super.equals(obj) && this.requestId == ((JPRequestEvent) obj).requestId;
    }

    @Override // com.jdpay.lib.event.JPEvent
    public int hashCode() {
        return (super.hashCode() * 31) + this.requestId;
    }

    public JPRequestEvent(int i2, @Nullable String str, int i3) {
        super(i2, str);
        this.requestId = i3;
    }
}
