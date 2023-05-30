package com.jdpay.lib.event;

import androidx.annotation.Nullable;

/* loaded from: classes18.dex */
public class JPEvent {
    public final String from;
    public final int id;

    public JPEvent(int i2) {
        this(i2, null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        JPEvent jPEvent = (JPEvent) obj;
        if (this.id != jPEvent.id) {
            return false;
        }
        String str = this.from;
        String str2 = jPEvent.from;
        return str != null ? str.equals(str2) : str2 == null;
    }

    public int hashCode() {
        int i2 = this.id * 31;
        String str = this.from;
        return i2 + (str != null ? str.hashCode() : 0);
    }

    public JPEvent(int i2, @Nullable String str) {
        this.id = i2;
        this.from = str;
    }
}
