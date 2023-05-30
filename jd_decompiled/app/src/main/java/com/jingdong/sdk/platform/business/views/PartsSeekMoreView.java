package com.jingdong.sdk.platform.business.views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.handmark.pulltorefresh.library.internal.BaseLoadingLayout;
import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes10.dex */
public class PartsSeekMoreView extends BaseLoadingLayout {
    public PartsSeekMoreView(Context context) {
        super(context);
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void addHeaderView(View view, ViewGroup.LayoutParams layoutParams) {
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public int getContentSize() {
        return DPIUtil.dip2px(20.0f);
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void hideAllViews() {
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void onPull(float f2) {
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void onScroll(int i2, int i3) {
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
