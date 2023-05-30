package com.jd.lib.productdetail.mainimage.holder.comment;

import android.graphics.Rect;
import android.widget.LinearLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.productdetail.core.utils.PDUtils;

/* loaded from: classes15.dex */
public class n implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ SimpleDraweeView f4785g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ LinearLayout.LayoutParams f4786h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ int f4787i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ PdMImageZcxView f4788j;

    public n(PdMImageZcxView pdMImageZcxView, SimpleDraweeView simpleDraweeView, LinearLayout.LayoutParams layoutParams, int i2) {
        this.f4788j = pdMImageZcxView;
        this.f4785g = simpleDraweeView;
        this.f4786h = layoutParams;
        this.f4787i = i2;
    }

    @Override // java.lang.Runnable
    public void run() {
        SimpleDraweeView simpleDraweeView = this.f4785g;
        if (simpleDraweeView == null || this.f4786h == null) {
            return;
        }
        simpleDraweeView.setVisibility(0);
        Rect rect = new Rect();
        this.f4785g.getLocalVisibleRect(rect);
        if (rect.height() < this.f4787i) {
            if (rect.height() < PDUtils.dip2px(40.0f)) {
                PdMImageZcxView pdMImageZcxView = this.f4788j;
                if (pdMImageZcxView == null || pdMImageZcxView.getVisibility() != 0) {
                    return;
                }
                this.f4788j.setVisibility(8);
                return;
            }
            this.f4786h.height = rect.height();
            this.f4786h.width = rect.height();
            if (this.f4788j.f4761k != null) {
                for (int i2 = 0; i2 < this.f4788j.f4761k.size(); i2++) {
                    SimpleDraweeView simpleDraweeView2 = this.f4788j.f4761k.get(i2);
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) simpleDraweeView2.getLayoutParams();
                    layoutParams.height = rect.height();
                    layoutParams.width = rect.height();
                    simpleDraweeView2.setLayoutParams(layoutParams);
                }
            }
            if (this.f4788j.getVisibility() != 0) {
                this.f4788j.setVisibility(0);
            }
        } else if (this.f4788j.getVisibility() != 0) {
            this.f4788j.setVisibility(0);
        }
    }
}
