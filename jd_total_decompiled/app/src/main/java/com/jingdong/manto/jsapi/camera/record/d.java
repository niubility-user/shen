package com.jingdong.manto.jsapi.camera.record;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.Display;
import android.view.OrientationEventListener;

/* loaded from: classes15.dex */
abstract class d {
    static final SparseIntArray d;
    private final OrientationEventListener a;
    Display b;

    /* renamed from: c  reason: collision with root package name */
    private int f13178c = 0;

    /* loaded from: classes15.dex */
    class a extends OrientationEventListener {
        private int a;

        a(Context context) {
            super(context);
            this.a = -1;
        }

        @Override // android.view.OrientationEventListener
        public void onOrientationChanged(int i2) {
            Display display;
            int rotation;
            if (i2 == -1 || (display = d.this.b) == null || this.a == (rotation = display.getRotation())) {
                return;
            }
            this.a = rotation;
            d.this.a(d.d.get(rotation));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        d = sparseIntArray;
        sparseIntArray.put(0, 0);
        sparseIntArray.put(1, 90);
        sparseIntArray.put(2, 180);
        sparseIntArray.put(3, 270);
    }

    public d(Context context) {
        this.a = new a(context);
    }

    public void a() {
        this.a.disable();
        this.b = null;
    }

    void a(int i2) {
        this.f13178c = i2;
        b(i2);
    }

    public void a(Display display) {
        this.b = display;
        this.a.enable();
        a(d.get(display.getRotation()));
    }

    public int b() {
        return this.f13178c;
    }

    public abstract void b(int i2);
}
