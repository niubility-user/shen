package com.jingdong.app.mall.home.floor.view.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.ctrl.f;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;

/* loaded from: classes4.dex */
public class SecKillSkuLabelText extends GradientTextView {

    /* renamed from: g  reason: collision with root package name */
    private boolean f10144g;

    /* renamed from: h  reason: collision with root package name */
    private NinePatch f10145h;

    /* renamed from: i  reason: collision with root package name */
    private int f10146i;

    /* renamed from: j  reason: collision with root package name */
    private String f10147j;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.t.a {
        final /* synthetic */ String a;

        a(String str) {
            this.a = str;
        }

        @Override // com.jingdong.app.mall.home.t.a
        public void onBitmapWithUiNull(Bitmap bitmap) {
            if (bitmap == null) {
                SecKillSkuLabelText.this.f();
            } else {
                SecKillSkuLabelText.this.e(bitmap, this.a);
            }
        }
    }

    public SecKillSkuLabelText(Context context) {
        super(context);
    }

    private void c(Canvas canvas) {
        NinePatch ninePatch = this.f10145h;
        if (ninePatch != null) {
            ninePatch.draw(canvas, new Rect(0, 0, getWidth(), getHeight()));
        } else {
            com.jingdong.app.mall.home.n.h.c.k(true, this);
        }
    }

    private boolean d(String str) {
        return TextUtils.equals(str, this.f10147j) && this.f10146i == getHeight();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Bitmap bitmap, String str) {
        int d = d.d(30);
        Bitmap x = e.x(bitmap, d);
        byte[] y = e.y(x, 0.5f);
        if (y != null) {
            com.jingdong.app.mall.home.n.h.c.k(false, this);
            this.f10145h = new NinePatch(x, y, null);
            this.f10146i = d;
            this.f10147j = str;
        } else {
            this.f10146i = 0;
            this.f10145h = null;
            this.f10147j = null;
        }
        postInvalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.f10145h = null;
        this.f10147j = null;
        this.f10146i = 0;
        postInvalidate();
    }

    public void g(String str) {
        if (d(str)) {
            return;
        }
        String b = com.jingdong.app.mall.home.m.a.b(str);
        if (!TextUtils.isEmpty(b) && !b.toLowerCase().endsWith(".gif")) {
            f.i(b, new a(b));
        } else {
            f();
        }
    }

    public void h(boolean z) {
        this.f10144g = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.view.GradientTextView, android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        if (this.f10144g) {
            c(canvas);
        }
        super.onDraw(canvas);
    }

    public SecKillSkuLabelText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SecKillSkuLabelText(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
