package com.jingdong.app.mall.home.floor.bottomfloat;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.ctrl.e;

/* loaded from: classes4.dex */
public class ImageTextView extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    private Context f9181g;

    /* renamed from: h  reason: collision with root package name */
    private SimpleDraweeView f9182h;

    /* renamed from: i  reason: collision with root package name */
    private TextView f9183i;

    /* renamed from: j  reason: collision with root package name */
    private f f9184j;

    /* renamed from: k  reason: collision with root package name */
    private f f9185k;

    /* loaded from: classes4.dex */
    class a implements e.b {
        a() {
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onFailed(String str, View view) {
            ImageTextView.this.setVisibility(8);
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onStart(String str, View view) {
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onSuccess(String str, View view) {
        }
    }

    public ImageTextView(Context context) {
        super(context);
        this.f9181g = context;
    }

    public void a(String str, String str2, int i2) {
        SimpleDraweeView simpleDraweeView = this.f9182h;
        if (simpleDraweeView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(this.f9181g);
            this.f9182h = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            f fVar = new f(-1, -1);
            this.f9184j = fVar;
            SimpleDraweeView simpleDraweeView2 = this.f9182h;
            addView(simpleDraweeView2, fVar.u(simpleDraweeView2));
        } else {
            f.c(simpleDraweeView, this.f9184j);
        }
        e.p(this.f9182h, str, e.b, new a());
        TextView textView = this.f9183i;
        if (textView == null) {
            g gVar = new g(this.f9181g, false);
            gVar.d(16);
            gVar.h();
            this.f9183i = gVar.a();
            f fVar2 = new f(-2, -1);
            this.f9185k = fVar2;
            TextView textView2 = this.f9183i;
            addView(textView2, fVar2.u(textView2));
        } else {
            f.c(textView, this.f9185k);
        }
        this.f9183i.setText(com.jingdong.app.mall.home.o.a.f.j(i2, str2));
    }

    public void b(int i2, int i3, boolean z) {
        TextView textView = this.f9183i;
        if (textView == null) {
            return;
        }
        textView.setTextColor(i2);
        g.o(this.f9183i, i3);
        g.k(this.f9183i, z);
    }

    public void c(int i2, int i3, int i4, int i5) {
        f fVar = this.f9185k;
        if (fVar == null || this.f9183i == null) {
            return;
        }
        fVar.E(i2, i3, i4, i5);
        f.d(this.f9183i, this.f9185k, true);
    }
}
