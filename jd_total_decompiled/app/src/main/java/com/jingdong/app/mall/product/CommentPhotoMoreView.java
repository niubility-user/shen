package com.jingdong.app.mall.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.handmark.pulltorefresh.library.internal.BaseLoadingLayout;
import com.jingdong.app.mall.R;

/* loaded from: classes4.dex */
public class CommentPhotoMoreView extends BaseLoadingLayout {

    /* renamed from: g  reason: collision with root package name */
    private LinearLayout f11552g;

    public CommentPhotoMoreView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.comment_photo_more, this);
        this.f11552g = (LinearLayout) findViewById(R.id.uy);
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void addHeaderView(View view, ViewGroup.LayoutParams layoutParams) {
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public int getContentSize() {
        return this.f11552g.getWidth();
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
