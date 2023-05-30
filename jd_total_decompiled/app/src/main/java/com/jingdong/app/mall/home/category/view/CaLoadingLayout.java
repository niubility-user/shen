package com.jingdong.app.mall.home.category.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeImageView;
import com.jingdong.app.mall.home.category.widget.CaLoadingView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.n.c;

/* loaded from: classes4.dex */
public class CaLoadingLayout extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    private CaLoadingView f8737g;

    /* renamed from: h  reason: collision with root package name */
    private LinearLayout f8738h;

    /* renamed from: i  reason: collision with root package name */
    private int f8739i;

    /* renamed from: j  reason: collision with root package name */
    private ImageView f8740j;

    /* renamed from: k  reason: collision with root package name */
    private f f8741k;

    /* renamed from: l  reason: collision with root package name */
    private TextView f8742l;

    /* renamed from: m  reason: collision with root package name */
    private f f8743m;

    /* renamed from: n  reason: collision with root package name */
    private TextView f8744n;
    private b o;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (CaLoadingLayout.this.o != null) {
                CaLoadingLayout.this.o.onRetry();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public interface b {
        void onRetry();
    }

    public CaLoadingLayout(Context context, boolean z) {
        super(context);
        this.f8741k = new f(300, 300);
        this.f8743m = new f(160, 52);
        CaLoadingView caLoadingView = new CaLoadingView(context, z);
        this.f8737g = caLoadingView;
        caLoadingView.setClickable(true);
        LinearLayout linearLayout = new LinearLayout(context);
        this.f8738h = linearLayout;
        linearLayout.setOrientation(1);
        this.f8738h.setGravity(17);
        addView(this.f8737g, new RelativeLayout.LayoutParams(-1, -1));
    }

    private void c() {
        this.f8738h.removeAllViews();
        HomeImageView homeImageView = new HomeImageView(getContext());
        this.f8740j = homeImageView;
        homeImageView.setBackgroundResource(R.drawable.y_03);
        this.f8738h.addView(this.f8740j, new LinearLayout.LayoutParams(this.f8741k.v(), this.f8741k.h()));
        TextView textView = new TextView(getContext());
        this.f8742l = textView;
        textView.setTextColor(com.jingdong.app.mall.home.state.dark.a.e(-1, -9934744));
        String string = getResources().getString(R.string.cart_error_fail);
        String string2 = getResources().getString(R.string.cart_error_fail_check);
        this.f8742l.setTextSize(0, d.d(32));
        this.f8742l.setText(string.concat(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE).concat(string2));
        this.f8742l.setGravity(1);
        this.f8738h.addView(this.f8742l);
        TextView textView2 = new TextView(getContext());
        this.f8744n = textView2;
        textView2.setOnClickListener(new a());
        this.f8744n.setGravity(17);
        this.f8744n.setTextColor(-9934744);
        this.f8744n.setText(R.string.loading_error_again);
        this.f8744n.setTextSize(0, d.d(28));
        this.f8744n.setBackgroundResource(R.drawable.button_d_01);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.f8743m.v(), this.f8743m.h());
        this.f8743m.G(new Rect(0, 20, 0, 0), layoutParams);
        this.f8738h.addView(this.f8744n, layoutParams);
        m.b(this, this.f8738h, 0);
    }

    public void b() {
        int i2 = this.f8739i;
        if (i2 > 0 && i2 != d.f9279g) {
            TextView textView = this.f8742l;
            if (textView != null) {
                textView.setTextSize(0, d.d(32));
            }
            TextView textView2 = this.f8744n;
            if (textView2 != null) {
                textView2.setTextSize(0, d.d(28));
            }
            f.c(this.f8740j, this.f8741k);
            f.c(this.f8744n, this.f8743m);
        }
        TextView textView3 = this.f8742l;
        if (textView3 != null) {
            textView3.setTextColor(com.jingdong.app.mall.home.state.dark.a.e(-1, -9934744));
        }
        this.f8739i = d.f9279g;
    }

    public void d(boolean z) {
        if (z && this.f8738h.getChildCount() == 0) {
            c();
        }
        this.f8737g.setVisibility(z ? 8 : 0);
        this.f8738h.setVisibility(z ? 0 : 8);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        canvas.drawColor(c.d());
        super.dispatchDraw(canvas);
    }

    public void e(b bVar) {
        this.o = bVar;
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        return layoutParams;
    }
}
