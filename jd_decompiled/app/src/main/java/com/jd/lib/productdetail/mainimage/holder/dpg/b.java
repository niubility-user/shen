package com.jd.lib.productdetail.mainimage.holder.dpg;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.productdetail.core.entitys.wozhe.MatchSkusBean;
import com.jd.lib.productdetail.core.utils.PDUtils;

/* loaded from: classes15.dex */
public class b implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ ConstraintLayout f4805g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ View f4806h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ SimpleDraweeView f4807i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ int f4808j;

    /* renamed from: k  reason: collision with root package name */
    public final /* synthetic */ MatchSkusBean f4809k;

    /* renamed from: l  reason: collision with root package name */
    public final /* synthetic */ PdMImageAnchorLayout f4810l;

    public b(PdMImageAnchorLayout pdMImageAnchorLayout, ConstraintLayout constraintLayout, View view, SimpleDraweeView simpleDraweeView, int i2, MatchSkusBean matchSkusBean) {
        this.f4810l = pdMImageAnchorLayout;
        this.f4805g = constraintLayout;
        this.f4806h = view;
        this.f4807i = simpleDraweeView;
        this.f4808j = i2;
        this.f4809k = matchSkusBean;
    }

    @Override // java.lang.Runnable
    public void run() {
        View view;
        if (this.f4805g == null || (view = this.f4806h) == null || this.f4807i == null) {
            return;
        }
        boolean z = false;
        view.measure(View.MeasureSpec.makeMeasureSpec(this.f4808j, 0), View.MeasureSpec.makeMeasureSpec(this.f4808j, 0));
        if (this.f4808j < this.f4809k.changeY + this.f4806h.getMeasuredHeight()) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.f4806h.getLayoutParams();
            this.f4809k.changeY = (this.f4808j - this.f4806h.getMeasuredHeight()) - PDUtils.dip2px(2.0f);
            layoutParams.topMargin = (int) this.f4809k.changeY;
            this.f4806h.setLayoutParams(layoutParams);
        } else if (this.f4809k.changeY < this.f4806h.getMeasuredHeight()) {
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.f4806h.getLayoutParams();
            this.f4809k.changeY = this.f4806h.getMeasuredHeight() + PDUtils.dip2px(2.0f);
            layoutParams2.topMargin = (int) this.f4809k.changeY;
            this.f4806h.setLayoutParams(layoutParams2);
        }
        if (this.f4806h.getLayoutParams() instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) this.f4806h.getLayoutParams();
            float f2 = this.f4809k.changeX;
            float f3 = this.f4808j;
            if (f2 > f3 / 2.0f) {
                PdMImageAnchorLayout pdMImageAnchorLayout = this.f4810l;
                View view2 = this.f4806h;
                int i2 = PdMImageAnchorLayout.v;
                pdMImageAnchorLayout.getClass();
                if (f3 - ((view2.getMeasuredWidth() + f2) - PDUtils.dip2px(5.0f)) < 0.0f) {
                    PdMImageAnchorLayout.c(this.f4810l, this.f4806h, this.f4805g, this.f4807i);
                    PdMImageAnchorLayout pdMImageAnchorLayout2 = this.f4810l;
                    MatchSkusBean matchSkusBean = this.f4809k;
                    Point a = PdMImageAnchorLayout.a(pdMImageAnchorLayout2, matchSkusBean.changeX, matchSkusBean.changeY, this.f4806h);
                    layoutParams3.leftMargin = a.x;
                    layoutParams3.topMargin = a.y;
                } else {
                    PdMImageAnchorLayout pdMImageAnchorLayout3 = this.f4810l;
                    MatchSkusBean matchSkusBean2 = this.f4809k;
                    Point d = PdMImageAnchorLayout.d(pdMImageAnchorLayout3, matchSkusBean2.changeX, matchSkusBean2.changeY, this.f4806h);
                    layoutParams3.leftMargin = d.x;
                    layoutParams3.topMargin = d.y;
                }
            } else {
                PdMImageAnchorLayout pdMImageAnchorLayout4 = this.f4810l;
                View view3 = this.f4806h;
                int i3 = PdMImageAnchorLayout.v;
                pdMImageAnchorLayout4.getClass();
                if ((f2 - view3.getMeasuredWidth()) + PDUtils.dip2px(5.0f) < 0.0f) {
                    PdMImageAnchorLayout pdMImageAnchorLayout5 = this.f4810l;
                    MatchSkusBean matchSkusBean3 = this.f4809k;
                    Point d2 = PdMImageAnchorLayout.d(pdMImageAnchorLayout5, matchSkusBean3.changeX, matchSkusBean3.changeY, this.f4806h);
                    layoutParams3.leftMargin = d2.x;
                    layoutParams3.topMargin = d2.y;
                } else {
                    PdMImageAnchorLayout.c(this.f4810l, this.f4806h, this.f4805g, this.f4807i);
                    PdMImageAnchorLayout pdMImageAnchorLayout6 = this.f4810l;
                    MatchSkusBean matchSkusBean4 = this.f4809k;
                    Point a2 = PdMImageAnchorLayout.a(pdMImageAnchorLayout6, matchSkusBean4.changeX, matchSkusBean4.changeY, this.f4806h);
                    layoutParams3.leftMargin = a2.x;
                    layoutParams3.topMargin = a2.y;
                }
            }
            Rect rect = new Rect();
            int i4 = layoutParams3.leftMargin;
            rect.left = i4;
            rect.top = layoutParams3.topMargin;
            rect.right = i4 + this.f4806h.getMeasuredWidth();
            rect.bottom = rect.top + this.f4806h.getMeasuredHeight();
            if (this.f4810l.f4799j != null) {
                for (int i5 = 0; i5 < this.f4810l.f4799j.size(); i5++) {
                    if (Rect.intersects(this.f4810l.f4799j.get(i5), rect)) {
                        break;
                    }
                }
            }
            z = true;
            if (z) {
                this.f4806h.setLayoutParams(layoutParams3);
                this.f4810l.f4799j.add(rect);
                return;
            }
            this.f4810l.f4797h.removeView(this.f4806h);
        }
    }
}
