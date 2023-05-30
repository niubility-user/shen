package c.t.m.g;

import android.os.Bundle;

/* loaded from: classes.dex */
public abstract class p5 {
    public String a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public boolean f606c = true;

    public p5(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public void a() {
    }

    public boolean b(Bundle bundle) {
        if (this.f606c) {
            return c(bundle);
        }
        return false;
    }

    public abstract boolean c(Bundle bundle);

    public String toString() {
        return "[name=" + this.a + ",desc=" + this.b + ",enabled=" + this.f606c + "]";
    }
}
