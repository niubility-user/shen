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
    */
    static void d(MantoProgressBar mantoProgressBar) {
        float f2;
        MantoHandler mantoHandler;
        float f3 = mantoProgressBar.f14331c;
        if (f3 < 600.0f) {
            f2 = mantoProgressBar.d;
        } else if (f3 < 600.0f || f3 >= 800.0f) {
            if (f3 >= 800.0f && f3 < 920.0f) {
                f2 = mantoProgressBar.f14333f;
            }
            mantoHandler = mantoProgressBar.b;
            if (mantoHandler != null) {
                if (mantoProgressBar.f14331c < 920.0f) {
                    mantoHandler.a(1001, 10L);
                } else {
                    mantoHandler.a(1001);
                }
            }
            mantoProgressBar.setProgress((int) mantoProgressBar.f14331c);
        } else {
            f2 = mantoProgressBar.f14332e;
        }
        mantoProgressBar.f14331c = f3 + f2;
        mantoHandler = mantoProgressBar.b;
        if (mantoHandler != null) {
        }
        mantoProgressBar.setProgress((int) mantoProgressBar.f14331c);
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
