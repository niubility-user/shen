package com.jd.voice.jdvoicesdk.c;

/* loaded from: classes18.dex */
public class a {
    private String a;

    public a(int i2, String str) {
        if (i2 == 0) {
            this.a = "00-" + str;
        } else if (i2 == 1) {
            this.a = "01-" + str;
        } else if (i2 == 2) {
            this.a = "02-" + str;
        } else if (i2 != 3) {
        } else {
            this.a = "03-" + str;
        }
    }

    public String toString() {
        return this.a;
    }
}
