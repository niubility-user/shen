package com.jingdong.app.mall.home.floor.view.linefloor.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.b.c;
import com.jingdong.app.mall.home.floor.view.linefloor.base.a;
import com.jingdong.app.mall.home.floor.view.view.MallFloorLineMore;
import com.jingdong.app.mall.home.floor.view.widget.FitTopImage;

/* loaded from: classes4.dex */
public abstract class BaseLineLayout<T extends a> extends BaseAnimateLayout {
    protected Context o;
    protected com.jingdong.app.mall.home.floor.view.b.a p;
    private FitTopImage q;
    private T r;

    public BaseLineLayout(Context context, com.jingdong.app.mall.home.floor.view.b.a aVar) {
        super(context);
        this.o = context;
        this.p = aVar;
    }

    private void s(T t) {
        int[] bgColors = t.getBgColors();
        int[] iArr = new int[2];
        if (bgColors.length == 1) {
            int i2 = bgColors[0];
            iArr[1] = i2;
            iArr[0] = i2;
            bgColors = iArr;
        }
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, bgColors);
        String e2 = t.e();
        if (this.q == null) {
            FitTopImage fitTopImage = new FitTopImage(this.o);
            this.q = fitTopImage;
            fitTopImage.setScaleType(ImageView.ScaleType.FIT_XY);
            f fVar = new f(-1, -1);
            FitTopImage fitTopImage2 = this.q;
            addView(fitTopImage2, 0, fVar.u(fitTopImage2));
        }
        r(false, this.q);
        FitTopImage fitTopImage3 = this.q;
        if (TextUtils.isEmpty(e2)) {
            e2 = "empty";
        }
        e.n(fitTopImage3, e2, gradientDrawable, Bitmap.Config.RGB_565);
    }

    private void t(T t) {
        float[] h2 = m.h(t.l().u);
        int d = d.d(24);
        int d2 = d.d(24);
        boolean w = t.w();
        boolean A = t.A();
        if ((w || A) && h2 != null && h2.length == 4) {
            if (Math.max(h2[0], h2[1]) == 0.0f) {
                d = 0;
            }
            if (Math.max(h2[2], h2[3]) == 0.0f) {
                d2 = 0;
            }
        }
        if (w && !A) {
            d2 = 0;
        } else if (A && !w) {
            d = d.d(24);
        }
        if (d != 0 && d2 != 0) {
            if (A) {
                com.jingdong.app.mall.home.n.h.e.d(this, d);
            } else {
                com.jingdong.app.mall.home.n.h.e.h(this, d);
            }
        } else if (d != 0) {
            com.jingdong.app.mall.home.n.h.e.h(this, d);
        } else if (d2 != 0) {
            com.jingdong.app.mall.home.n.h.e.a(this, d2);
        } else {
            com.jingdong.app.mall.home.n.h.e.d(this, 0);
        }
    }

    public void q(@NonNull T t, MallFloorLineMore mallFloorLineMore, int i2, int i3) {
        if (this.r != t || t.f9855i.getAsyncTime() > 0) {
            t.c(i2, i3);
            this.r = t;
            if (t.a == c.SPECIAL) {
                t(t);
                s(t);
            }
            v(t, i2);
        }
    }

    public void r(boolean z, View... viewArr) {
        com.jingdong.app.mall.home.n.h.c.k(z, viewArr);
    }

    @Override // android.view.View
    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.width = 0;
            layoutParams2.height = -1;
            layoutParams2.weight = this.p.getWeight();
        }
        super.setLayoutParams(layoutParams);
    }

    public int u() {
        return this.p.getWeight();
    }

    protected abstract void v(@NonNull T t, int i2);

    public void w() {
    }
}
