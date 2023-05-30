package com.jingdong.app.mall.home.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.common.R;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class MainRightErrorLayout extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    private RelativeLayout f8571g;

    /* renamed from: h  reason: collision with root package name */
    private ImageView f8572h;

    /* renamed from: i  reason: collision with root package name */
    private TextView f8573i;

    /* renamed from: j  reason: collision with root package name */
    private TextView f8574j;

    /* renamed from: k  reason: collision with root package name */
    private TextView f8575k;

    /* renamed from: l  reason: collision with root package name */
    private GradientDrawable f8576l;

    /* loaded from: classes4.dex */
    class a extends RelativeLayout {
        a(Context context) {
            super(context);
        }

        @Override // android.widget.RelativeLayout, android.view.View
        protected void onMeasure(int i2, int i3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(MainRightErrorLayout.this.b(320), 1073741824), View.MeasureSpec.makeMeasureSpec(MainRightErrorLayout.this.b(R2.attr.channelId), 1073741824));
        }
    }

    /* loaded from: classes4.dex */
    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MainRightErrorLayout.this.c();
        }
    }

    public MainRightErrorLayout(Context context) {
        super(context);
        a aVar = new a(context);
        this.f8571g = aVar;
        aVar.setOnClickListener(new b());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(b(320), b(R2.attr.channelId));
        layoutParams.addRule(13);
        addView(this.f8571g, layoutParams);
        ImageView imageView = new ImageView(context);
        this.f8572h = imageView;
        imageView.setBackgroundResource(R.drawable.y_03);
        this.f8571g.addView(this.f8572h, new RelativeLayout.LayoutParams(b(320), b(320)));
        g gVar = new g(context, false);
        gVar.l(-9079435);
        gVar.d(17);
        gVar.j(true);
        gVar.m(28);
        gVar.i("\u7f51\u7edc\u8bf7\u6c42\u5931\u8d25");
        this.f8573i = gVar.a();
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, b(R2.attr.behavior_autoHide));
        layoutParams2.addRule(12);
        this.f8571g.addView(this.f8573i, layoutParams2);
        g gVar2 = new g(context, false);
        gVar2.l(-9079435);
        gVar2.d(17);
        gVar2.m(24);
        gVar2.i("\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc");
        this.f8574j = gVar2.a();
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, b(R2.attr.additionBottom));
        layoutParams3.addRule(12);
        this.f8571g.addView(this.f8574j, layoutParams3);
        g gVar3 = new g(context, false);
        gVar3.l(-15066598);
        gVar3.d(17);
        gVar3.m(24);
        gVar3.i("\u91cd\u65b0\u52a0\u8f7d");
        this.f8575k = gVar3.a();
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(b(144), b(56));
        layoutParams4.bottomMargin = b(80);
        layoutParams4.addRule(14);
        layoutParams4.addRule(12);
        GradientDrawable gradientDrawable = new GradientDrawable();
        this.f8576l = gradientDrawable;
        gradientDrawable.setCornerRadius(b(56));
        this.f8576l.setStroke(d.d(1) + 1, -9079435);
        this.f8575k.setBackgroundDrawable(this.f8576l);
        this.f8571g.addView(this.f8575k, layoutParams4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b(int i2) {
        return d.b((Activity) getContext(), i2);
    }

    protected void c() {
    }
}
