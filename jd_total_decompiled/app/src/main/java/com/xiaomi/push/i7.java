package com.xiaomi.push;

/* loaded from: classes11.dex */
public enum i7 {
    MISC_CONFIG(1),
    PLUGIN_CONFIG(2);
    

    /* renamed from: a  reason: collision with other field name */
    private final int f155a;

    i7(int i2) {
        this.f155a = i2;
    }

    public static i7 a(int i2) {
        if (i2 != 1) {
            if (i2 != 2) {
                return null;
            }
            return PLUGIN_CONFIG;
        }
        return MISC_CONFIG;
    }

    public int a() {
        return this.f155a;
    }
}
