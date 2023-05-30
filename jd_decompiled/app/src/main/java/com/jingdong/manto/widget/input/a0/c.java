package com.jingdong.manto.widget.input.a0;

/* loaded from: classes16.dex */
public enum c {
    NORMAL(0),
    BOLD(1);
    
    public final int a;

    c(int i2) {
        this.a = i2;
    }

    public static c a(String str) {
        e.a(str, c.class);
        return NORMAL;
    }
}
