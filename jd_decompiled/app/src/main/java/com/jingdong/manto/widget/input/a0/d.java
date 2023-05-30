package com.jingdong.manto.widget.input.a0;

/* loaded from: classes16.dex */
public enum d {
    LEFT,
    RIGHT,
    CENTER;

    public static d a(String str) {
        Enum a = e.a(str, d.class);
        return a != null ? (d) a : LEFT;
    }
}
