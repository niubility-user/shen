package com.jingdong.manto.widget.input;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.view.DisplayCutout;
import android.view.View;
import com.jingdong.manto.utils.MantoDensityUtils;
import java.util.List;

/* loaded from: classes16.dex */
public final class p implements com.jingdong.manto.widget.i.a {
    private int a = 0;
    private final Rect b = new Rect();

    /* renamed from: c  reason: collision with root package name */
    private boolean f14483c = false;
    private View d;

    /* renamed from: e  reason: collision with root package name */
    a f14484e;

    /* loaded from: classes16.dex */
    interface a {
        void a(boolean z);

        int getHeight();

        void setHeight(int i2);
    }

    private Context a() {
        View view = this.d;
        return view == null ? com.jingdong.manto.c.a() : view.getContext();
    }

    private void a(Rect rect) {
        View view = this.d;
        if (view != null) {
            view.getWindowVisibleDisplayFrame(rect);
        }
    }

    private int b() {
        View view = this.d;
        if ((view == null ? null : view.getRootView()) == null) {
            return 0;
        }
        Rect rect = this.b;
        a(rect);
        return MantoDensityUtils.getDMHeightPixels() - rect.top;
    }

    private int b(View view) {
        if (Build.VERSION.SDK_INT >= 28) {
            try {
                DisplayCutout displayCutout = view.getRootWindowInsets().getDisplayCutout();
                List<Rect> boundingRects = displayCutout.getBoundingRects();
                if (boundingRects != null && boundingRects.size() > 0) {
                    return displayCutout.getSafeInsetTop();
                }
            } catch (Exception unused) {
            }
        }
        return 0;
    }

    @Override // com.jingdong.manto.widget.i.a
    public final void a(View view) {
        a aVar;
        this.d = view;
        Rect rect = this.b;
        a(rect);
        int height = rect.height();
        if (this.a == 0) {
            this.a = height;
        } else {
            int b = (b() - height) + b(view);
            if (b > 0) {
                boolean b2 = com.jingdong.manto.utils.e.a(a()) != b ? com.jingdong.manto.utils.e.b(a(), b) : false;
                a aVar2 = this.f14484e;
                if (aVar2 != null && b2 && aVar2.getHeight() != b) {
                    this.f14484e.setHeight(b);
                }
            }
        }
        boolean z = b() > height;
        if (this.f14483c != z && (aVar = this.f14484e) != null) {
            aVar.a(z);
        }
        this.f14483c = z;
        this.a = height;
        this.d = null;
    }
}
