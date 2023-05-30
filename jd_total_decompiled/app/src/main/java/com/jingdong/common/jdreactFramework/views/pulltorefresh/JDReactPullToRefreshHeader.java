package com.jingdong.common.jdreactFramework.views.pulltorefresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class JDReactPullToRefreshHeader extends FrameLayout implements JDReactPullToRefreshUIHandler {
    private static final String TAG = JDReactPullToRefreshHeader.class.getSimpleName();
    private Drawable goodsDrawable;
    private AnimationDrawable mAnimation;
    private RelativeLayout mHeaderContent;
    private ImageView mHeaderGoods;
    private ImageView mHeaderIcon;
    private int mMinHeaderTranslation;
    private CharSequence mPullLabel;
    private CharSequence mRefreshingLabel;
    private CharSequence mReleaseLabel;
    private TextView mTimeText;
    private Drawable peopleAnimDrawable;
    private Drawable peopleDrawable;

    public JDReactPullToRefreshHeader(Context context) {
        this(context, null);
    }

    private void crossRotateLineFromBottomUnderTouch(JDReactPullToRefreshBasicFrameLayout jDReactPullToRefreshBasicFrameLayout) {
        this.mTimeText.setVisibility(0);
        this.mTimeText.setText(this.mPullLabel);
    }

    private void crossRotateLineFromTopUnderTouch(JDReactPullToRefreshBasicFrameLayout jDReactPullToRefreshBasicFrameLayout) {
        if (jDReactPullToRefreshBasicFrameLayout.isPullToRefresh()) {
            return;
        }
        this.mTimeText.setVisibility(0);
        this.mTimeText.setText(this.mReleaseLabel);
    }

    private void initViews() {
        LayoutInflater.from(getContext()).inflate(R.layout.jdreact_pull_to_refresh_header_main, this);
        this.mHeaderContent = (RelativeLayout) findViewById(R.id.ar);
        this.mHeaderIcon = (ImageView) findViewById(R.id.d1);
        this.mHeaderGoods = (ImageView) findViewById(R.id.ao);
        this.mTimeText = (TextView) findViewById(R.id.f9do);
        this.peopleDrawable = getResources().getDrawable(R.drawable.wk);
        this.peopleAnimDrawable = getResources().getDrawable(R.drawable.aa);
        this.goodsDrawable = getResources().getDrawable(R.drawable.wj);
        this.mPullLabel = getResources().getString(R.string.k2);
        this.mRefreshingLabel = getResources().getString(R.string.k1);
        this.mReleaseLabel = getResources().getString(R.string.k4);
        resetView();
    }

    private void resetDefalut(View view) {
        setView(view, 1.0f, 1.0f, 0.0f);
    }

    private void resetView() {
        AnimationDrawable animationDrawable = this.mAnimation;
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
        this.mAnimation = null;
        this.mHeaderGoods.setVisibility(0);
        this.mHeaderIcon.setImageDrawable(this.peopleDrawable);
        this.mHeaderGoods.setImageDrawable(this.goodsDrawable);
    }

    private void setView(View view, float f2, float f3, float f4) {
        view.setScaleX(f2);
        view.setScaleY(f2);
        if (f3 > 1.0f) {
            f3 = 1.0f;
        }
        view.setAlpha(f3);
        if (f4 > 0.0f) {
            f4 = 0.0f;
        }
        view.setTranslationX(f4);
    }

    public final int getContentSize() {
        return this.mHeaderContent.getHeight();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // com.jingdong.common.jdreactFramework.views.pulltorefresh.JDReactPullToRefreshUIHandler
    public void onUIPositionChange(JDReactPullToRefreshBasicFrameLayout jDReactPullToRefreshBasicFrameLayout, boolean z, byte b, JDReactPullToRefreshIndicator jDReactPullToRefreshIndicator) {
        int offsetToRefresh = jDReactPullToRefreshIndicator.getOffsetToRefresh();
        int currentPosY = jDReactPullToRefreshIndicator.getCurrentPosY();
        int lastPosY = jDReactPullToRefreshIndicator.getLastPosY();
        OKLog.d(TAG, "onUIPositionChange isUnderTouch = " + z + ",status " + ((int) b) + "mOffsetToRefresh = " + offsetToRefresh + ", currentPos =" + currentPosY + ", lastPos =" + lastPosY);
        int contentSize = getContentSize();
        this.mMinHeaderTranslation = contentSize;
        float f2 = (float) currentPosY;
        float f3 = f2 / ((float) contentSize);
        float f4 = f2 / 2.5f;
        float f5 = ((float) contentSize) / 2.5f;
        float f6 = (-f5) + f4;
        float f7 = f5 - f4;
        setView(this.mHeaderIcon, f3 <= 1.0f ? f3 : 1.0f, f3, f6);
        ImageView imageView = this.mHeaderGoods;
        float f8 = f3 <= 0.7f ? f3 : 0.7f;
        if (f7 < 0.0f) {
            f7 = 0.0f;
        }
        setView(imageView, f8, f3, f7);
        if (currentPosY < offsetToRefresh && lastPosY >= offsetToRefresh) {
            if (z && b == 2) {
                crossRotateLineFromBottomUnderTouch(jDReactPullToRefreshBasicFrameLayout);
            }
        } else if (currentPosY <= offsetToRefresh || lastPosY > offsetToRefresh || !z || b != 2) {
        } else {
            crossRotateLineFromTopUnderTouch(jDReactPullToRefreshBasicFrameLayout);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.views.pulltorefresh.JDReactPullToRefreshUIHandler
    public void onUIRefreshBegin(JDReactPullToRefreshBasicFrameLayout jDReactPullToRefreshBasicFrameLayout) {
        OKLog.d(TAG, "onUIRefreshBegin");
        this.mTimeText.setVisibility(0);
        this.mTimeText.setText(this.mRefreshingLabel);
        refreshing();
    }

    @Override // com.jingdong.common.jdreactFramework.views.pulltorefresh.JDReactPullToRefreshUIHandler
    public void onUIRefreshComplete(JDReactPullToRefreshBasicFrameLayout jDReactPullToRefreshBasicFrameLayout) {
        OKLog.d(TAG, "onUIRefreshComplete");
        resetView();
    }

    @Override // com.jingdong.common.jdreactFramework.views.pulltorefresh.JDReactPullToRefreshUIHandler
    public void onUIRefreshPrepare(JDReactPullToRefreshBasicFrameLayout jDReactPullToRefreshBasicFrameLayout) {
        OKLog.d(TAG, "onUIRefreshPrepare");
        this.mTimeText.setVisibility(0);
        this.mTimeText.setText(this.mPullLabel);
    }

    @Override // com.jingdong.common.jdreactFramework.views.pulltorefresh.JDReactPullToRefreshUIHandler
    public void onUIReset(JDReactPullToRefreshBasicFrameLayout jDReactPullToRefreshBasicFrameLayout) {
        OKLog.d(TAG, "onUIReset");
        resetView();
    }

    public final void refreshing() {
        if (this.mAnimation == null) {
            try {
                try {
                    this.mHeaderGoods.setVisibility(8);
                    this.mHeaderGoods.setImageDrawable(new ColorDrawable(0));
                    this.mAnimation = (AnimationDrawable) this.mHeaderIcon.getDrawable();
                } catch (ClassCastException unused) {
                    this.mHeaderIcon.setImageDrawable(this.peopleAnimDrawable);
                    this.mAnimation = (AnimationDrawable) this.mHeaderIcon.getDrawable();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (this.mAnimation != null) {
                resetDefalut(this.mHeaderIcon);
                this.mAnimation.start();
            }
        }
    }

    public JDReactPullToRefreshHeader(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public JDReactPullToRefreshHeader(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        initViews();
    }
}
