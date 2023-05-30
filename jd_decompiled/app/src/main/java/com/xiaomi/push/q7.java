package com.xiaomi.push;

/* loaded from: classes11.dex */
public enum q7 {
    RegIdExpired(0),
    PackageUnregistered(1),
    Init(2);
    

    /* renamed from: a  reason: collision with other field name */
    private final int f201a;

    q7(int i2) {
        this.f201a = i2;
    }

    public static q7 a(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    return null;
                }
                return Init;
            }
            return PackageUnregistered;
        }
        return RegIdExpired;
    }

    public int a() {
        return this.f201a;
    }
}
