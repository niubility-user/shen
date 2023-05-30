package com.jd.dynamic.lib.viewparse.b.carouselView;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.internal.BaseLoadingLayout;
import com.jd.dynamic.R;

/* loaded from: classes13.dex */
public class a extends BaseLoadingLayout {

    /* renamed from: g  reason: collision with root package name */
    private final String f2299g;

    /* renamed from: h  reason: collision with root package name */
    private LinearLayout f2300h;

    /* renamed from: i  reason: collision with root package name */
    private ImageView f2301i;

    /* renamed from: j  reason: collision with root package name */
    private TextView f2302j;

    /* renamed from: k  reason: collision with root package name */
    private boolean f2303k;

    /* renamed from: l  reason: collision with root package name */
    private String f2304l;

    /* renamed from: m  reason: collision with root package name */
    private String f2305m;

    /* renamed from: n  reason: collision with root package name */
    private float f2306n;

    public a(Context context) {
        super(context);
        this.f2299g = a.class.getSimpleName();
        this.f2303k = false;
        LayoutInflater.from(context).inflate(R.layout.lib_pd_big_image_more, this);
        this.f2300h = (LinearLayout) findViewById(R.id.lib_pd_big_image_more_ll);
        this.f2301i = (ImageView) findViewById(R.id.lib_pd_big_image_pull_arrow);
        this.f2302j = (TextView) findViewById(R.id.lib_pd_big_image_pull_text);
        if (TextUtils.isEmpty(this.f2304l)) {
            return;
        }
        this.f2302j.setText(this.f2304l);
    }

    private synchronized void a() {
        if (this.f2302j != null && this.f2301i != null) {
            RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 180.0f, 1, 0.5f, 1, 0.5f);
            rotateAnimation.setDuration(300L);
            rotateAnimation.setFillAfter(true);
            if (TextUtils.isEmpty(this.f2305m)) {
                this.f2302j.setText(R.string.lib_pd_big_image_more_2);
            } else {
                this.f2302j.setText(this.f2305m);
            }
            this.f2301i.startAnimation(rotateAnimation);
        }
    }

    private synchronized void b() {
        if (this.f2302j != null && this.f2301i != null) {
            RotateAnimation rotateAnimation = new RotateAnimation(180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
            rotateAnimation.setDuration(300L);
            rotateAnimation.setFillAfter(true);
            if (TextUtils.isEmpty(this.f2304l)) {
                this.f2302j.setText(R.string.lib_pd_big_image_more_1);
            } else {
                this.f2302j.setText(this.f2304l);
            }
            this.f2301i.startAnimation(rotateAnimation);
        }
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void addHeaderView(View view, ViewGroup.LayoutParams layoutParams) {
    }

    public void c(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f2304l = str;
        if (this.f2303k) {
            return;
        }
        this.f2302j.setText(str);
    }

    public void d(String str) {
        TextUtils.isEmpty(str);
    }

    public void e(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f2305m = str;
        if (this.f2303k) {
            this.f2302j.setText(str);
        }
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public int getContentSize() {
        LinearLayout linearLayout = this.f2300h;
        if (linearLayout == null) {
            return 0;
        }
        return linearLayout.getWidth();
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void hideAllViews() {
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void onPull(float f2) {
        if (f2 >= 1.0f && !this.f2303k && f2 > this.f2306n) {
            a();
            this.f2303k = true;
        }
        if (this.f2303k && f2 < 1.0f && this.f2306n > f2) {
            b();
            this.f2303k = false;
        }
        this.f2306n = f2;
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void onScroll(int i2, int i3) {
        String str = "onScroll--->" + i2 + "   :  " + i3;
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void pullToRefresh() {
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void refreshing() {
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void releaseToRefresh() {
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void reset() {
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void setHeight(int i2) {
        getLayoutParams().height = i2;
        requestLayout();
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void setWidth(int i2) {
        getLayoutParams().width = i2;
        requestLayout();
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void showInvisibleViews() {
    }
}
