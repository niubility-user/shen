package com.jd.lib.productdetail.mainimage.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.internal.BaseLoadingLayout;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;

/* loaded from: classes15.dex */
public class PDMainImageMoreViewNew extends BaseLoadingLayout {

    /* renamed from: g  reason: collision with root package name */
    public TextView f5212g;

    /* renamed from: h  reason: collision with root package name */
    public int f5213h;

    public PDMainImageMoreViewNew(Context context) {
        super(context);
        this.f5212g = (TextView) LayoutInflater.from(context).inflate(R.layout.lib_pd_mainimage_viewpager_more_detail, this).findViewById(R.id.lib_pd_pull_detail_text);
        this.f5213h = PDUtils.dip2px(45.0f);
    }

    public void a(int i2) {
        this.f5213h = i2;
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void addHeaderView(View view, ViewGroup.LayoutParams layoutParams) {
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public int getContentSize() {
        return this.f5213h;
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void hideAllViews() {
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void onPull(float f2) {
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void onScroll(int i2, int i3) {
        TextView textView = this.f5212g;
        if (textView != null) {
            if (i2 >= this.f5213h) {
                textView.setText(R.string.lib_pd_image_big_image_more_2);
            } else {
                textView.setText(R.string.lib_pd_image_big_image_more_1);
            }
        }
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
