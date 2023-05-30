package com.jd.lib.productdetail.mainimage.bigimage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.internal.BaseLoadingLayout;
import com.jd.lib.productdetail.mainimage.R;

/* loaded from: classes15.dex */
public class PdMBigImageMoreView extends BaseLoadingLayout {

    /* renamed from: g  reason: collision with root package name */
    public final String f4585g;

    /* renamed from: h  reason: collision with root package name */
    public LinearLayout f4586h;

    public PdMBigImageMoreView(Context context) {
        super(context);
        this.f4585g = PdMBigImageMoreView.class.getSimpleName();
        LayoutInflater.from(context).inflate(R.layout.lib_pd_mainimage_big_image_more, this);
        this.f4586h = (LinearLayout) findViewById(R.id.lib_pd_big_image_more_ll);
        ImageView imageView = (ImageView) findViewById(R.id.lib_pd_big_image_pull_arrow);
        TextView textView = (TextView) findViewById(R.id.lib_pd_big_image_pull_text);
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void addHeaderView(View view, ViewGroup.LayoutParams layoutParams) {
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public int getContentSize() {
        LinearLayout linearLayout = this.f4586h;
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
