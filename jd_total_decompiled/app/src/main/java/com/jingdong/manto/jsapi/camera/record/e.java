package com.jingdong.manto.jsapi.camera.record;

import androidx.annotation.NonNull;
import com.jingdong.jdsdk.constant.JshopConst;

/* loaded from: classes15.dex */
public class e implements Comparable<e> {
    private final int a;
    private final int b;

    public e(int i2, int i3) {
        this.a = i2;
        this.b = i3;
    }

    public int a() {
        return this.b;
    }

    @Override // java.lang.Comparable
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public int compareTo(@NonNull e eVar) {
        return (this.a * this.b) - (eVar.a * eVar.b);
    }

    public int b() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof e) {
            e eVar = (e) obj;
            return this.a == eVar.a && this.b == eVar.b;
        }
        return false;
    }

    public int hashCode() {
        int i2 = this.b;
        int i3 = this.a;
        return i2 ^ ((i3 >>> 16) | (i3 << 16));
    }

    public String toString() {
        return this.a + JshopConst.JSHOP_PROMOTIO_X + this.b;
    }
}
