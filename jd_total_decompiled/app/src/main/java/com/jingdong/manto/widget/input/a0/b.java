package com.jingdong.manto.widget.input.a0;

/* loaded from: classes16.dex */
public enum b {
    DONE(6),
    SEARCH(3),
    NEXT(5),
    GO(2),
    SEND(4);
    
    public final int a;

    b(int i2) {
        this.a = i2;
    }

    public static b a() {
        return DONE;
    }

    public static b a(String str) {
        return (b) e.a(str, b.class);
    }
}
