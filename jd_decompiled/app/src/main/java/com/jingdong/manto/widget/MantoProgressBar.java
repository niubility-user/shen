package com.jingdong.manto.widget;

import android.content.Context;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import com.jingdong.manto.sdk.thread.MantoHandler;
import com.jingdong.manto.utils.MantoUtils;

/* loaded from: classes16.dex */
public class MantoProgressBar extends ProgressBar {
    private boolean a;
    private MantoHandler b;

    /* renamed from: c  reason: collision with root package name */
    private float f14331c;
    private float d;

    /* renamed from: e  reason: collision with root package name */
    private float f14332e;

    /* renamed from: f  reason: collision with root package name */
    private float f14333f;

    /* renamed from: g  reason: collision with root package name */
    private float f14334g;

    /* renamed from: h  reason: collision with root package name */
    public boolean f14335h;

    /* loaded from: classes16.dex */
    class a extends MantoHandler {
        a(Looper looper) {
            super(looper);
        }

        @Override // com.jingdong.manto.sdk.thread.MantoHandler, com.jingdong.manto.sdk.thread.d.a
        public final void a(Message message) {
            switch (message.what) {
                case 1000:
                    MantoProgressBar.c(MantoProgressBar.this);
                    return;
                case 1001:
                    MantoProgressBar.d(MantoProgressBar.this);
                    return;
                case 1002:
                    MantoProgressBar.a(MantoProgressBar.this);
                    return;
                case 1003:
                    MantoProgressBar.b(MantoProgressBar.this);
                    return;
                default:
                    return;
            }
        }
    }

    public MantoProgressBar(Context context) {
        this(context, null);
        a();
    }

    public MantoProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MantoProgressBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.a = false;
        this.f14331c = 0.0f;
        this.f14335h = true;
        this.b = new a(Looper.getMainLooper());
        setMax(1000);
        a();
    }

    private void a() {
        float f2;
        int netType = MantoUtils.getNetType(getContext());
        if (netType == 0 || netType == 10) {
            this.d = 4.0f;
            this.f14332e = 1.0f;
            f2 = 0.3f;
        } else {
            this.d = 2.0f;
            this.f14332e = 0.5f;
            f2 = 0.15f;
        }
        this.f14333f = f2;
        this.f14334g = 50.0f;
    }

    static void a(MantoProgressBar mantoProgressBar) {
        Float valueOf;
        if (mantoProgressBar.a) {
            float f2 = mantoProgressBar.f14331c;
            if (f2 < 950.0f) {
                float f3 = f2 + mantoProgressBar.f14334g;
                mantoProgressBar.f14331c = f3;
                mantoProgressBar.f14331c = f3 <= 950.0f ? f3 : 950.0f;
                valueOf = Float.valueOf(1.0f);
            } else {
                float f4 = f2 + 1.0f;
                mantoProgressBar.f14331c = f4;
                valueOf = Float.valueOf(((1000.0f - f4) * 0.01f) + 0.3f);
            }
            MantoHandler mantoHandler = mantoProgressBar.b;
            if (mantoHandler != null) {
                if (mantoProgressBar.f14331c < 1000.0f) {
                    mantoHandler.a(1002, 10L);
                } else {
                    mantoProgressBar.f14331c = 1000.0f;
                    mantoHandler.a(1002);
                    mantoProgressBar.b.a(1003, 10L);
                }
            }
            mantoProgressBar.setAlpha(valueOf.floatValue());
            mantoProgressBar.setProgress((int) mantoProgressBar.f14331c);
        }
    }

    static void b(MantoProgressBar mantoProgressBar) {
        mantoProgressBar.f14331c = 0.0f;
        mantoProgressBar.a = false;
        mantoProgressBar.postInvalidateDelayed(200L);
        mantoProgressBar.setVisibility(8);
    }

    static void c(MantoProgressBar mantoProgressBar) {
        mantoProgressBar.setVisibility(0);
        mantoProgressBar.setAlpha(1.0f);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static void d(com.jingdong.manto.widget.MantoProgressBar r4) {
        /*
            float r0 = r4.f14331c
            r1 = 1147535360(0x44660000, float:920.0)
            r2 = 1142292480(0x44160000, float:600.0)
            int r3 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r3 >= 0) goto L10
            float r2 = r4.d
        Lc:
            float r0 = r0 + r2
            r4.f14331c = r0
            goto L28
        L10:
            r3 = 1145569280(0x44480000, float:800.0)
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 < 0) goto L1d
            int r2 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r2 >= 0) goto L1d
            float r2 = r4.f14332e
            goto Lc
        L1d:
            int r2 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r2 < 0) goto L28
            int r2 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r2 >= 0) goto L28
            float r2 = r4.f14333f
            goto Lc
        L28:
            com.jingdong.manto.sdk.thread.MantoHandler r0 = r4.b
            if (r0 == 0) goto L3d
            float r2 = r4.f14331c
            r3 = 1001(0x3e9, float:1.403E-42)
            int r1 = (r2 > r1 ? 1 : (r2 == r1 ? 0 : -1))
            if (r1 >= 0) goto L3a
            r1 = 10
            r0.a(r3, r1)
            goto L3d
        L3a:
            r0.a(r3)
        L3d:
            float r0 = r4.f14331c
            int r0 = (int) r0
            r4.setProgress(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.widget.MantoProgressBar.d(com.jingdong.manto.widget.MantoProgressBar):void");
    }

    public final void b() {
        MantoHandler mantoHandler = this.b;
        if (mantoHandler != null) {
            mantoHandler.b(1002);
        }
    }

    public final void c() {
        if (!this.f14335h || this.a) {
            return;
        }
        this.a = true;
        a();
        MantoHandler mantoHandler = this.b;
        if (mantoHandler != null) {
            mantoHandler.b(1000);
            this.b.b(1001);
        }
    }
}
