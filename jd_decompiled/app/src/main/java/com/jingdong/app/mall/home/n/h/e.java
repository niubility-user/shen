package com.jingdong.app.mall.home.n.h;

import android.graphics.Outline;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.annotation.RequiresApi;

/* loaded from: classes4.dex */
public class e {
    private static final Rect a = new Rect(0, 0, 0, 0);
    private static final Rect b = new Rect(0, 0, 1, 0);

    /* renamed from: c  reason: collision with root package name */
    private static final Rect f10443c = new Rect(0, 0, 0, 1);
    private static final Rect d = new Rect(1, 0, 0, 0);

    /* renamed from: e  reason: collision with root package name */
    private static final Rect f10444e = new Rect(0, 1, 0, 0);

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(api = 21)
    /* loaded from: classes4.dex */
    public static class a extends ViewOutlineProvider {
        private Rect a = new Rect();
        private int b;

        /* renamed from: c  reason: collision with root package name */
        private float f10445c;

        a() {
        }

        boolean a(boolean z, Rect rect, int i2, float f2) {
            int i3 = rect.left * (z ? 1 : -i2);
            int i4 = rect.top * (z ? 1 : -i2);
            int i5 = rect.right * (z ? -1 : i2);
            int i6 = rect.bottom * (z ? -1 : i2);
            this.f10445c = f2;
            boolean z2 = false;
            Rect rect2 = this.a;
            if (rect2.left != i3) {
                rect2.left = i3;
                z2 = true;
            }
            if (rect2.top != i4) {
                rect2.top = i4;
                z2 = true;
            }
            if (rect2.right != i5) {
                rect2.right = i5;
                z2 = true;
            }
            if (rect2.bottom != i6) {
                rect2.bottom = i6;
                z2 = true;
            }
            if (this.b != i2) {
                this.b = i2;
                return true;
            }
            return z2;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            Rect rect = this.a;
            outline.setRoundRect(rect.left, rect.top, view.getWidth() + this.a.right, view.getHeight() + this.a.bottom, this.b + this.f10445c);
        }
    }

    public static void a(View view, int i2) {
        f(view, false, f10444e, i2);
    }

    public static void b(View view, int i2) {
        f(view, false, b, i2);
    }

    public static void c(View view, int i2) {
        f(view, false, d, i2);
    }

    public static void d(View view, int i2) {
        f(view, false, a, i2);
    }

    public static void e(View view, int i2, float f2) {
        g(view, false, a, i2, f2);
    }

    public static void f(View view, boolean z, Rect rect, int i2) {
        g(view, z, rect, i2, 0.0f);
    }

    public static void g(View view, boolean z, Rect rect, int i2, float f2) {
        a aVar;
        if (view != null && com.jingdong.app.mall.home.o.a.f.M0()) {
            if (i2 <= 0 && !z) {
                i(view);
                return;
            }
            ViewOutlineProvider outlineProvider = view.getOutlineProvider();
            if (outlineProvider instanceof a) {
                aVar = (a) outlineProvider;
            } else {
                aVar = new a();
            }
            boolean a2 = aVar.a(z, rect, i2, f2);
            if (!view.getClipToOutline()) {
                view.setClipToOutline(true);
            }
            if (a2) {
                view.setOutlineProvider(aVar);
            }
        }
    }

    public static void h(View view, int i2) {
        f(view, false, f10443c, i2);
    }

    public static void i(View view) {
        if (view == null || Build.VERSION.SDK_INT < 21) {
            return;
        }
        view.setClipToOutline(false);
        view.setOutlineProvider(ViewOutlineProvider.BACKGROUND);
    }
}
