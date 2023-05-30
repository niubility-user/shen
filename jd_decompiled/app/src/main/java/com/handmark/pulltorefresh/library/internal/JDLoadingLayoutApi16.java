package com.handmark.pulltorefresh.library.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.utils.config.UnDeviceInfo;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes12.dex */
public class JDLoadingLayoutApi16 extends BaseLoadingLayout {
    static final Interpolator ANIMATION_INTERPOLATOR = new LinearInterpolator();
    static final String LOG_TAG = "PullToRefresh-LoadingLayout";
    private Drawable goodsDrawable;
    private boolean isAutoDark;
    private AnimationDrawable mAnimation;
    private RelativeLayout mHeaderContent;
    private ImageView mHeaderGoods;
    private ImageView mHeaderIcon;
    private RelativeLayout mHeaderLayout;
    private int mMinHeaderTranslation;
    protected final PullToRefreshBase.Mode mMode;
    private CharSequence mPullLabel;
    private CharSequence mRefreshingLabel;
    private CharSequence mReleaseLabel;
    protected final PullToRefreshBase.Orientation mScrollDirection;
    private TextView mTimeText;
    private Drawable peopleAnimDrawable;
    private Drawable peopleDrawable;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.handmark.pulltorefresh.library.internal.JDLoadingLayoutApi16$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass1 {
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

    public JDLoadingLayoutApi16(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.Orientation orientation, TypedArray typedArray) {
        super(context);
        this.mMode = mode;
        this.mScrollDirection = orientation;
        if (AnonymousClass1.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[orientation.ordinal()] != 1) {
            LayoutInflater.from(context).inflate(R.layout.jd_pull_to_refresh_header_api16, this);
        } else {
            LayoutInflater.from(context).inflate(R.layout.jd_pull_to_refresh_header_api16, this);
        }
        this.mHeaderLayout = (RelativeLayout) findViewById(R.id.pull_header_layout);
        this.mHeaderContent = (RelativeLayout) findViewById(R.id.headerlayout);
        this.mHeaderIcon = (ImageView) findViewById(R.id.people);
        this.mHeaderGoods = (ImageView) findViewById(R.id.goods);
        this.mTimeText = (TextView) findViewById(R.id.refresh_time);
        this.peopleDrawable = context.getResources().getDrawable(R.drawable.app_refresh_people_0);
        this.peopleAnimDrawable = context.getResources().getDrawable(R.drawable.app_refresh_people);
        this.goodsDrawable = context.getResources().getDrawable(R.drawable.app_refresh_goods_0);
        int i2 = R.styleable.PullToRefresh_ptrIsAutoDark;
        if (typedArray.hasValue(i2)) {
            boolean z = typedArray.getBoolean(i2, false);
            this.isAutoDark = z;
            setAutoDark(z);
        }
        if (isSettingDark()) {
            this.mHeaderLayout.setBackgroundDrawable(getBackground());
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mHeaderLayout.getLayoutParams();
        if (AnonymousClass1.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[mode.ordinal()] != 1) {
            layoutParams.gravity = orientation == PullToRefreshBase.Orientation.VERTICAL ? 80 : 5;
            this.mPullLabel = context.getString(R.string.pull_to_refresh_header_hint_normal);
            this.mRefreshingLabel = context.getString(R.string.pull_to_refresh_header_hint_loading);
            this.mReleaseLabel = context.getString(R.string.pull_to_refresh_header_hint_ready);
        } else {
            layoutParams.gravity = orientation == PullToRefreshBase.Orientation.VERTICAL ? 48 : 3;
            this.mPullLabel = context.getString(R.string.pull_to_refresh_header_hint_normal2);
            this.mRefreshingLabel = context.getString(R.string.pull_to_refresh_header_hint_loading);
            this.mReleaseLabel = context.getString(R.string.pull_to_refresh_header_hint_ready);
        }
        reset();
    }

    private void resetDefalut(View view) {
        setView(view, 1.0f, 1.0f, 0.0f);
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

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void addHeaderView(View view, ViewGroup.LayoutParams layoutParams) {
        RelativeLayout relativeLayout = this.mHeaderLayout;
        if (relativeLayout != null) {
            relativeLayout.addView(view, layoutParams);
        }
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public final int getContentSize() {
        if (AnonymousClass1.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[this.mMode.ordinal()] == 2 && this.mHeaderContent != null) {
            if (AnonymousClass1.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[this.mScrollDirection.ordinal()] != 1) {
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
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void onScroll(int i2, int i3) {
        super.onScroll(i2, i3);
        int contentSize = getContentSize();
        this.mMinHeaderTranslation = contentSize;
        float f2 = i3;
        float f3 = f2 / contentSize;
        float f4 = f2 / 2.5f;
        float f5 = contentSize / 2.5f;
        float f6 = (-f5) + f4;
        float f7 = f5 - f4;
        setView(this.mHeaderIcon, f3 <= 1.0f ? f3 : 1.0f, f3, f6);
        ImageView imageView = this.mHeaderGoods;
        float f8 = f3 <= 0.7f ? f3 : 0.7f;
        if (f7 < 0.0f) {
            f7 = 0.0f;
        }
        setView(imageView, f8, f3, f7);
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public final void pullToRefresh() {
        if (isSettingDark()) {
            this.mHeaderLayout.setBackgroundDrawable(getBackground());
        }
        this.mHeaderGoods.setVisibility(0);
        this.mHeaderIcon.setImageDrawable(this.peopleDrawable);
        this.mHeaderGoods.setImageDrawable(this.goodsDrawable);
        this.mTimeText.setText(this.mPullLabel);
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void refreshTheme() {
        this.mHeaderLayout.setBackgroundDrawable(getBackground());
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public final void refreshing() {
        this.mTimeText.setText(this.mRefreshingLabel);
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

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public final void releaseToRefresh() {
        this.mHeaderGoods.setVisibility(0);
        this.mTimeText.setText(this.mReleaseLabel);
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public final void reset() {
        AnimationDrawable animationDrawable = this.mAnimation;
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
        this.mAnimation = null;
        this.mHeaderGoods.setVisibility(0);
        this.mHeaderIcon.setImageDrawable(this.peopleDrawable);
        this.mHeaderGoods.setImageDrawable(this.goodsDrawable);
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public final void setHeight(int i2) {
        getLayoutParams().height = i2;
        requestLayout();
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout, com.handmark.pulltorefresh.library.ILoadingLayout
    public void setLastUpdatedLabel(CharSequence charSequence) {
        this.mTimeText.setText(charSequence);
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout, com.handmark.pulltorefresh.library.ILoadingLayout
    public final void setLoadingDrawable(Drawable drawable) {
        this.mHeaderIcon.setImageDrawable(drawable);
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout, com.handmark.pulltorefresh.library.ILoadingLayout
    public void setPullLabel(CharSequence charSequence) {
        this.mPullLabel = charSequence;
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public void setRefreshTextColor(int i2) {
        TextView textView = this.mTimeText;
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
