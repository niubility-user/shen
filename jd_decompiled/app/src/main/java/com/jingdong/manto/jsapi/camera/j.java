package com.jingdong.manto.jsapi.camera;

import java.util.HashMap;

/* loaded from: classes15.dex */
public final class j {

    /* renamed from: e */
    private static j f13145e = new j();
    boolean a;
    boolean b;

    /* renamed from: c */
    boolean f13146c;
    HashMap<Integer, MantoCameraViewContainer> d = new HashMap<>();

    private j() {
    }

    public static j a() {
        return f13145e;
    }

    public boolean a(Integer num) {
        if (this.d.containsKey(num)) {
            MantoCameraViewContainer remove = this.d.remove(num);
            if (remove != null) {
                remove.k();
                return true;
            }
            return true;
        }
        return false;
    }

    public boolean b() {
        return this.a;
    }
}
