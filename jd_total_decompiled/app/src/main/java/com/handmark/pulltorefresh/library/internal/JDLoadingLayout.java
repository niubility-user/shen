package com.handmark.pulltorefresh.library.internal;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.OnHeaderScrollChangeListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.utils.config.UnDeviceInfo;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.UnLog;
import com.jingdong.common.widget.UnLottieAnimationView;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes12.dex */
public class JDLoadingLayout extends BaseLoadingLayout {
    final String LOG_TAG;
    private boolean isAutoDark;
    private boolean isRefreshCompleteState;
    private CharSequence mCompleteLabel;
    private RelativeLayout mHeaderContent;
    private RelativeLayout mHeaderLayout;
    private int mMinHeaderTranslation;
    protected final PullToRefreshBase.Mode mMode;
    private CharSequence mPullLabel;
    private CharSequence mRefreshingLabel;
    private CharSequence mReleaseLabel;
    protected final PullToRefreshBase.Orientation mScrollDirection;
    private UnLottieAnimationView pullAnimView;
    private String pullLottie;
    private UnLottieAnimationView refreshAnimView;
    private boolean startPullAnim;
    private TextView tvHint;

    /* renamed from: com.handmark.pulltorefresh.library.internal.JDLoadingLayout$2  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode;
        static final /* synthetic */ int[] $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation;

        static {
            int[] iArr = new int[PullToRefreshBase.Mode.values().length];
            $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode = iArr;
            try {
                iArr[PullToRefreshBase.Mode.PULL_FROM_END.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[PullToRefreshBase.Mode.PULL_FROM_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[PullToRefreshBase.Orientation.values().length];
            $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation = iArr2;
            try {
                iArr2[PullToRefreshBase.Orientation.HORIZONTAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[PullToRefreshBase.Orientation.VERTICAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public JDLoadingLayout(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.Orientation orientation, TypedArray typedArray) {
        super(context);
        this.LOG_TAG = "JDLoadingLayout";
        this.isRefreshCompleteState = false;
        this.startPullAnim = true;
        this.mMode = mode;
        this.mScrollDirection = orientation;
        if (AnonymousClass2.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[orientation.ordinal()] != 1) {
            LayoutInflater.from(context).inflate(R.layout.jd_pull_to_refresh_header, this);
        } else {
            LayoutInflater.from(context).inflate(R.layout.jd_pull_to_refresh_header, this);
        }
        int i2 = R.styleable.PullToRefresh_ptrIsAutoDark;
        if (typedArray.hasValue(i2)) {
            boolean z = typedArray.getBoolean(i2, false);
            this.isAutoDark = z;
            setAutoDark(z);
        }
        this.mHeaderLayout = (RelativeLayout) findViewById(R.id.pull_header_layout);
        this.mHeaderContent = (RelativeLayout) findViewById(R.id.headerlayout);
        if (isSettingDark()) {
            this.mHeaderLayout.setBackgroundDrawable(getBackground());
        }
        this.pullAnimView = (UnLottieAnimationView) findViewById(R.id.pull_anim_view);
        this.pullLottie = getRefreshStartJson();
        this.pullAnimView.setAnimation(getRefreshStartJson());
        this.pullAnimView.setProgress(0.0f);
        this.pullAnimView.setVisibility(0);
        this.pullAnimView.addAnimatorListener(new Animator.AnimatorListener() { // from class: com.handmark.pulltorefresh.library.internal.JDLoadingLayout.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                boolean z2 = UnLog.D;
                JDLoadingLayout.this.pullAnimView.cancelAnimation();
                JDLoadingLayout.this.pullAnimView.setVisibility(8);
                JDLoadingLayout.this.refreshAnimView.setVisibility(0);
                try {
                    JDLoadingLayout.this.refreshAnimView.setProgress(0.0f);
                    JDLoadingLayout.this.refreshAnimView.playAnimation();
                } catch (Exception unused) {
                    JDLoadingLayout.this.refreshAnimView.setImageResource(R.drawable.app_refresh_joy);
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        });
        UnLottieAnimationView unLottieAnimationView = (UnLottieAnimationView) findViewById(R.id.refresh_anim_view);
        this.refreshAnimView = unLottieAnimationView;
        unLottieAnimationView.setAnimation(getRefreshLoadingJson());
        this.refreshAnimView.loop(true);
        this.refreshAnimView.setVisibility(8);
        this.tvHint = (TextView) findViewById(R.id.tv_hint);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mHeaderLayout.getLayoutParams();
        if (AnonymousClass2.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[mode.ordinal()] != 1) {
            layoutParams.gravity = orientation == PullToRefreshBase.Orientation.VERTICAL ? 80 : 5;
            this.mPullLabel = context.getString(R.string.pull_to_refresh_header_hint_pull);
            this.mReleaseLabel = context.getString(R.string.pull_to_refresh_header_hint_let);
            this.mRefreshingLabel = context.getString(R.string.pull_to_refresh_header_hint_refresh);
            this.mCompleteLabel = context.getString(R.string.pull_to_refresh_header_hint_complete);
            return;
        }
        layoutParams.gravity = orientation == PullToRefreshBase.Orientation.VERTICAL ? 48 : 3;
        this.mPullLabel = context.getString(R.string.pull_to_refresh_header_hint_normal2);
        this.mRefreshingLabel = context.getString(R.string.pull_to_refresh_header_hint_loading);
        this.mReleaseLabel = context.getString(R.string.pull_to_refresh_header_hint_ready);
    }

    private String getRefreshLoadingJson() {
        return isRedMode() ? "refresh/refresh_loading_white.json" : "refresh/refresh_loading_grey.json";
    }

    private String getRefreshStartJson() {
        return isRedMode() ? "refresh/refresh_start_white.json" : "refresh/refresh_start_grey.json";
    }

    private void setTipColor() {
        if (isRedMode()) {
            this.tvHint.setTextColor(getResources().getColor(R.color.white));
        } else {
            this.tvHint.setTextColor(getResources().getColor(R.color.un_content_level_2));
        }
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void addHeaderView(View view, ViewGroup.LayoutParams layoutParams) {
        RelativeLayout relativeLayout = this.mHeaderLayout;
        if (relativeLayout != null) {
            relativeLayout.addView(view, layoutParams);
        }
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public final int getContentSize() {
        if (AnonymousClass2.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[this.mMode.ordinal()] == 2 && this.mHeaderContent != null) {
            if (AnonymousClass2.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[this.mScrollDirection.ordinal()] != 1) {
                return this.mHeaderContent.getHeight();
            }
            return this.mHeaderContent.getWidth();
        }
        return (int) (UnDeviceInfo.getDensity() * 40.0f);
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void hideAllViews() {
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public final void onPull(float f2) {
        boolean z = UnLog.D;
        this.isRefreshCompleteState = false;
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void onScroll(int i2, int i3) {
        OnHeaderScrollChangeListener onHeaderScrollChangeListener = this.headerScrollListener;
        if (onHeaderScrollChangeListener != null) {
            onHeaderScrollChangeListener.onScroll(i2, i3);
        }
        if (i3 == 0) {
            this.startPullAnim = true;
            this.pullAnimView.cancelAnimation();
            this.pullAnimView.setVisibility(0);
            this.refreshAnimView.cancelAnimation();
            this.refreshAnimView.setVisibility(8);
        }
        try {
            if (i3 >= DpiUtil.dip2px(getContext(), 35.0f) && !this.pullAnimView.isAnimating() && this.startPullAnim) {
                this.pullAnimView.setProgress(0.0f);
                this.pullAnimView.playAnimation();
                this.startPullAnim = false;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.isRefreshCompleteState = false;
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public final void pullToRefresh() {
        boolean z = UnLog.D;
        if (isSettingDark()) {
            this.mHeaderLayout.setBackgroundDrawable(getBackground());
        }
        this.isRefreshCompleteState = false;
        this.tvHint.setText(this.mPullLabel);
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void refreshComplete() {
        super.refreshComplete();
        boolean z = UnLog.D;
        this.isRefreshCompleteState = true;
        this.tvHint.setText(this.mCompleteLabel);
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void refreshTheme() {
        this.mHeaderLayout.setBackgroundDrawable(getBackground());
        if (TextUtils.equals(getRefreshStartJson(), this.pullLottie)) {
            return;
        }
        this.pullLottie = getRefreshStartJson();
        setTipColor();
        this.pullAnimView.cancelAnimation();
        this.pullAnimView.setAnimation(getRefreshStartJson());
        this.pullAnimView.setProgress(0.0f);
        this.refreshAnimView.cancelAnimation();
        this.refreshAnimView.setAnimation(getRefreshLoadingJson());
        this.refreshAnimView.setProgress(0.0f);
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public final void refreshing() {
        boolean z = UnLog.D;
        this.isRefreshCompleteState = false;
        this.tvHint.setText(this.mRefreshingLabel);
        this.pullAnimView.cancelAnimation();
        this.pullAnimView.setVisibility(8);
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public final void releaseToRefresh() {
        boolean z = UnLog.D;
        this.isRefreshCompleteState = false;
        this.tvHint.setText(this.mReleaseLabel);
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public final void reset() {
        if (UnLog.D) {
            String str = " reset isRefreshCompleteState " + this.isRefreshCompleteState;
        }
        if (!this.isRefreshCompleteState) {
            boolean z = UnLog.D;
            this.pullAnimView.setVisibility(0);
            this.refreshAnimView.setVisibility(8);
            try {
                this.pullAnimView.cancelAnimation();
                this.refreshAnimView.cancelAnimation();
            } catch (Exception e2) {
                this.isRefreshCompleteState = false;
                e2.printStackTrace();
            }
        }
        this.isRefreshCompleteState = false;
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public final void setHeight(int i2) {
        getLayoutParams().height = i2;
        requestLayout();
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout, com.handmark.pulltorefresh.library.ILoadingLayout
    public void setLastUpdatedLabel(CharSequence charSequence) {
        this.tvHint.setText(charSequence);
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout, com.handmark.pulltorefresh.library.ILoadingLayout
    public final void setLoadingDrawable(Drawable drawable) {
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout, com.handmark.pulltorefresh.library.ILoadingLayout
    public void setPullLabel(CharSequence charSequence) {
        this.mPullLabel = charSequence;
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void setRefreshTextColor(int i2) {
        TextView textView = this.tvHint;
        if (textView != null) {
            textView.setTextColor(i2);
        }
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout, com.handmark.pulltorefresh.library.ILoadingLayout
    public void setRefreshingLabel(CharSequence charSequence) {
        this.mRefreshingLabel = charSequence;
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout, com.handmark.pulltorefresh.library.ILoadingLayout
    public void setReleaseLabel(CharSequence charSequence) {
        this.mReleaseLabel = charSequence;
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout, com.handmark.pulltorefresh.library.ILoadingLayout
    public void setTextTypeface(Typeface typeface) {
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public final void setWidth(int i2) {
        getLayoutParams().width = i2;
        requestLayout();
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void showInvisibleViews() {
    }
}
