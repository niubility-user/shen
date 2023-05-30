package com.jingdong.app.mall.home.category.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.n;
import com.jingdong.app.mall.home.floor.ctrl.h;
import com.jingdong.app.mall.home.floor.model.entity.IconFloorEntity;
import com.jingdong.app.mall.home.n.c;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class CaLoadingView extends View {

    /* renamed from: g  reason: collision with root package name */
    private Paint f8818g;

    /* renamed from: h  reason: collision with root package name */
    private Path f8819h;

    /* renamed from: i  reason: collision with root package name */
    private Paint f8820i;

    /* renamed from: j  reason: collision with root package name */
    private Path f8821j;

    /* renamed from: k  reason: collision with root package name */
    private Paint f8822k;

    /* renamed from: l  reason: collision with root package name */
    private Path f8823l;

    /* renamed from: m  reason: collision with root package name */
    private com.jingdong.app.mall.home.n.a[] f8824m;

    /* renamed from: n  reason: collision with root package name */
    private boolean f8825n;
    private int o;

    public CaLoadingView(Context context, com.jingdong.app.mall.home.n.a[] aVarArr) {
        super(context);
        this.f8818g = new Paint(1);
        this.f8819h = new Path();
        this.f8820i = new Paint(1);
        this.f8821j = new Path();
        this.f8822k = new Paint(1);
        this.f8823l = new Path();
        this.f8824m = new com.jingdong.app.mall.home.n.a[]{com.jingdong.app.mall.home.n.a.C_ICON, com.jingdong.app.mall.home.n.a.C_FLASH_SALE, com.jingdong.app.mall.home.n.a.C_TITLE, com.jingdong.app.mall.home.n.a.C_FEEDS_SKU};
        this.f8825n = false;
        this.f8824m = aVarArr;
        q();
    }

    private void a(Path path, RectF rectF, int i2) {
        float f2 = i2;
        path.addRoundRect(rectF, f2, f2, Path.Direction.CCW);
    }

    private static boolean c() {
        return "MI 4LTE".equals(n.b());
    }

    private void d() {
        int p;
        int d = d.d(24);
        int i2 = 0;
        for (com.jingdong.app.mall.home.n.a aVar : this.f8824m) {
            if (aVar == com.jingdong.app.mall.home.n.a.C_ICON) {
                i2 += d;
                p = j(i2, d);
            } else if (aVar == com.jingdong.app.mall.home.n.a.C_FLASH_SALE && !c()) {
                i2 += d;
                p = h(i2, d);
            } else if (aVar == com.jingdong.app.mall.home.n.a.C_TITLE) {
                p = p(i2, d);
            } else {
                if (aVar == com.jingdong.app.mall.home.n.a.C_FEEDS_SKU) {
                    f(i2, d);
                }
            }
            i2 += p;
        }
    }

    private void e(int i2, int i3, int i4) {
        int d = d.d(R2.attr.channelId);
        int d2 = d.d(R2.attr.actionOverflowButtonStyle);
        float f2 = i2;
        int i5 = i3 + d2;
        float f3 = i5 - i4;
        float f4 = i2 + d2;
        a(this.f8819h, new RectF(f2, f3, f4, d + i3), i4);
        float f5 = i5;
        a(this.f8823l, new RectF(f2, i3, f4, f5), i4);
        a(this.f8823l, new RectF(f2, f3, f4, f5), 0);
        int d3 = d.d(321);
        int d4 = d.d(8);
        int d5 = i3 + d2 + d.d(24);
        float f6 = i4 + i2;
        a(this.f8821j, new RectF(f6, d5, d3 + i2, d.d(26) + d5), d4);
        int d6 = d5 + d.d(34);
        a(this.f8821j, new RectF(f6, d6, r11 + d.d(200), d.d(26) + d6), d4);
        int d7 = d6 + d.d(50);
        a(this.f8821j, new RectF(f6, d7, d.d(120) + i2, d.d(26) + d7), d4);
        a(this.f8821j, new RectF(f6, d7 + d.d(41), i2 + d.d(100), r10 + d.d(26)), d4);
    }

    private void f(int i2, int i3) {
        int d = d.d(R2.attr.channelId);
        int d2 = this.f8824m.length == 1 ? d.d(6) : i3;
        int i4 = i2;
        for (int i5 = 0; i5 < 4; i5++) {
            e(d2, i4, i3);
            i4 = i4 + d + d.d(12);
        }
        for (int i6 = 0; i6 < 4; i6++) {
            e(d.d(R2.attr.adUnitId) + d2, i2, i3);
            i2 = i2 + d + d.d(12);
        }
    }

    private void g(int i2, int i3) {
        int d = i2 + d.d(36);
        int i4 = i3 << 1;
        a(this.f8821j, new RectF(i4, d, d.d(144) + i4, d.d(36) + d), d.d(8));
        int d2 = d + d.d(69);
        int d3 = d.d(R2.anim.settlement_dialog_right_enter);
        int i5 = d.f9279g - i3;
        for (int i6 = 0; i6 < 4; i6++) {
            int d4 = (d.d(R2.anim.slide_out_to_right) * i6) + i4;
            float f2 = d4;
            int i7 = d4 + d3;
            a(this.f8821j, new RectF(f2, d2, Math.min(i5, i7), d2 + d3), i3);
            a(this.f8821j, new RectF(d.d(42) + d4, d.d(203) + d2, Math.min(i5, d4 + d.d(R2.anim.manto_translate_dialog_in)), d.d(231) + d2), d.d(6));
            a(this.f8821j, new RectF(f2, d.d(244) + d2, Math.min(i5, i7), d.d(254) + d2), d.d(4));
        }
    }

    private int h(int i2, int i3) {
        int d = d.d(R2.attr.arrowLocation);
        a(this.f8819h, new RectF(i3, i2, d.f9279g - i3, i2 + d), i3);
        g(i2, i3);
        return d;
    }

    private void i(int i2, int i3) {
        int d = d.d(120);
        for (int i4 = 0; i4 < 5; i4++) {
            int d2 = i3 + i3 + (d.d(134) * i4);
            float f2 = d2;
            float f3 = d2 + d;
            int i5 = i2 + d;
            a(this.f8821j, new RectF(f2, i2, f3, i5), d.d(12));
            a(this.f8821j, new RectF(f2, i5 + d.d(12), f3, r7 + d.d(24)), d.d(6));
        }
    }

    private int j(int i2, int i3) {
        int d = d.d(R2.attr.animSize);
        a(this.f8819h, new RectF(i3, i2, d.f9279g - i3, i2 + d), i3);
        i(d.d(26) + i2, i3);
        i(i2 + d.d(201), i3);
        return d;
    }

    private void k(int i2, int i3) {
        int d = d.d(122);
        for (int i4 = 0; i4 < 4; i4++) {
            int d2 = (d.d(R2.anim.pickerview_dialog_scale_in) * i4) + d.d(38);
            int i5 = i2 + d;
            a(this.f8821j, new RectF(d2, i2, d2 + d, i5), d.d(12));
            a(this.f8821j, new RectF(d2 + d.d(20), i5 + d.d(24), r6 - d.d(20), r8 + d.d(24)), d.d(8));
        }
    }

    private int l(int i2, int i3, int i4) {
        int d = d.d(R2.attr.buttonTextColor);
        a(this.f8819h, new RectF(0.0f, i2, i3, i2 + d), i4);
        a(this.f8821j, new RectF(d.d(38), d.d(34) + i2, d.d(R2.anim.pop_left_top_out), d.d(62) + i2), d.d(8));
        int d2 = i2 + d.d(98);
        k(d2, i4);
        k(d2 + d.d(211), i4);
        return d;
    }

    private void m() {
        int d = d.d(24);
        int d2 = d.d(120) + h.A;
        int d3 = d.d(R2.attr.constraintSet);
        a(this.f8823l, new RectF(d.d(38), r0 - d.d(36), d.d(214), d2 - d.d(24)), d.d(8));
        for (int i2 = 0; i2 < 4; i2++) {
            d2 = d2 + l(d2, d3, d) + d;
        }
    }

    private void o() {
        if (this.f8825n) {
            m();
        } else {
            d();
        }
    }

    private int p(int i2, int i3) {
        int d = d.d(94);
        int d2 = d.d(280);
        int d3 = d.d(36);
        a(this.f8823l, new RectF((d.f9279g - d2) >> 1, i2 + d.d(40), r2 + d2, r8 + d3), d.d(8));
        return d;
    }

    private void q() {
        this.o = d.f9279g;
        n();
        o();
    }

    public void b() {
        if (this.o != d.f9279g) {
            this.f8819h.reset();
            this.f8821j.reset();
            this.f8823l.reset();
            o();
            postInvalidate();
            this.o = d.f9279g;
        }
    }

    public void n() {
        this.f8818g.setColor(-1);
        this.f8820i.setColor(IconFloorEntity.BGCOLOR_DEFAULT);
        this.f8822k.setColor(-1118482);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        try {
            n();
            canvas.drawColor(c.d());
            canvas.drawPath(this.f8819h, this.f8818g);
            canvas.drawPath(this.f8821j, this.f8820i);
            canvas.drawPath(this.f8823l, this.f8822k);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        b();
    }

    public CaLoadingView(Context context, boolean z) {
        super(context);
        this.f8818g = new Paint(1);
        this.f8819h = new Path();
        this.f8820i = new Paint(1);
        this.f8821j = new Path();
        this.f8822k = new Paint(1);
        this.f8823l = new Path();
        this.f8824m = new com.jingdong.app.mall.home.n.a[]{com.jingdong.app.mall.home.n.a.C_ICON, com.jingdong.app.mall.home.n.a.C_FLASH_SALE, com.jingdong.app.mall.home.n.a.C_TITLE, com.jingdong.app.mall.home.n.a.C_FEEDS_SKU};
        this.f8825n = z;
        q();
    }
}
