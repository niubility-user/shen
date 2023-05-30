package com.jingdong.common.widget;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import com.handmark.pulltorefresh.library.internal.BaseLoadingLayout;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.HashMap;

/* loaded from: classes12.dex */
public class LoadingHead extends BaseLoadingLayout {
    public static final String LOADING_STATE_PULL_TO_REFRESH = "pullToRefresh";
    public static final String LOADING_STATE_REFRESHING = "refreshing";
    public static final String LOADING_STATE_RELEASE_TO_REFRESH = "releaseToRefresh";
    public static final String LOADING_STATE_RESET = "reset";
    public static final String TITLE_TAG = "com.jingdong.common.widget.LoadingHead.mTextView";
    private HashMap<String, String> mLoadingState;
    private TextView mTextView;
    private int mViewHeight;

    public LoadingHead(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TextView textView = new TextView(context);
        this.mTextView = textView;
        textView.setTag(TITLE_TAG);
        double density = BaseInfo.getDensity() * 70.0f;
        Double.isNaN(density);
        this.mViewHeight = (int) (density + 0.5d);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, this.mViewHeight);
        layoutParams.gravity = 80;
        this.mTextView.setTextSize(1, 13.0f);
        this.mTextView.setTextColor(Color.parseColor("#3d3d3d"));
        this.mTextView.setGravity(81);
        TextView textView2 = this.mTextView;
        double d = getResources().getDisplayMetrics().density * 10.0f;
        Double.isNaN(d);
        textView2.setPadding(0, 0, 0, (int) (d + 0.5d));
        addView(this.mTextView, layoutParams);
        HashMap<String, String> hashMap = new HashMap<>();
        this.mLoadingState = hashMap;
        hashMap.put("reset", "");
        this.mLoadingState.put(LOADING_STATE_RELEASE_TO_REFRESH, "\u677e\u624b\u66f4\u65b0");
        this.mLoadingState.put(LOADING_STATE_REFRESHING, "\u66f4\u65b0\u4e2d...");
        this.mLoadingState.put(LOADING_STATE_PULL_TO_REFRESH, "\u4e0b\u62c9\u5237\u65b0");
    }

    public void addState(String str, String str2) {
        this.mLoadingState.put(str, str2);
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public int getContentSize() {
        return this.mViewHeight;
    }

    public HashMap<String, String> getLoadingState() {
        return this.mLoadingState;
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void onScroll(int i2, int i3) {
        super.onScroll(i2, i3);
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void pullToRefresh() {
        this.mTextView.setText(TextUtils.isEmpty(this.mLoadingState.get(LOADING_STATE_PULL_TO_REFRESH)) ? "" : this.mLoadingState.get(LOADING_STATE_PULL_TO_REFRESH));
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void refreshing() {
        this.mTextView.setText(TextUtils.isEmpty(this.mLoadingState.get(LOADING_STATE_REFRESHING)) ? "" : this.mLoadingState.get(LOADING_STATE_REFRESHING));
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void releaseToRefresh() {
        this.mTextView.setText(TextUtils.isEmpty(this.mLoadingState.get(LOADING_STATE_RELEASE_TO_REFRESH)) ? "" : this.mLoadingState.get(LOADING_STATE_RELEASE_TO_REFRESH));
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void reset() {
        this.mTextView.setText(TextUtils.isEmpty(this.mLoadingState.get("reset")) ? "" : this.mLoadingState.get("reset"));
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void setHeight(int i2) {
        getLayoutParams().height = i2;
        requestLayout();
    }

    public void setTextColor(@ColorInt int i2) {
        this.mTextView.setTextColor(i2);
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void setWidth(int i2) {
        getLayoutParams().width = i2;
        requestLayout();
    }
}
