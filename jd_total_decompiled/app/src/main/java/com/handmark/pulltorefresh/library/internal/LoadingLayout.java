package com.handmark.pulltorefresh.library.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.jd.lib.un.basewidget.R;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes12.dex */
public abstract class LoadingLayout extends BaseLoadingLayout {
    static final Interpolator ANIMATION_INTERPOLATOR = new LinearInterpolator();
    static final String LOG_TAG = "PullToRefresh-LoadingLayout";
    protected final ImageView mHeaderImage;
    protected final ProgressBar mHeaderProgress;
    private final TextView mHeaderText;
    private FrameLayout mInnerLayout;
    protected final PullToRefreshBase.Mode mMode;
    private CharSequence mPullLabel;
    private CharSequence mRefreshingLabel;
    private CharSequence mReleaseLabel;
    protected final PullToRefreshBase.Orientation mScrollDirection;
    private final TextView mSubHeaderText;
    private boolean mUseIntrinsicAnimation;

    /* renamed from: com.handmark.pulltorefresh.library.internal.LoadingLayout$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
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

    public LoadingLayout(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.Orientation orientation, TypedArray typedArray) {
        super(context);
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        Drawable drawable;
        this.mMode = mode;
        this.mScrollDirection = orientation;
        if (AnonymousClass1.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[orientation.ordinal()] != 1) {
            LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_header_vertical, this);
        } else {
            LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_header_horizontal, this);
        }
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fl_inner);
        this.mInnerLayout = frameLayout;
        this.mHeaderText = (TextView) frameLayout.findViewById(R.id.pull_to_refresh_text);
        this.mHeaderProgress = (ProgressBar) this.mInnerLayout.findViewById(R.id.pull_to_refresh_progress);
        this.mSubHeaderText = (TextView) this.mInnerLayout.findViewById(R.id.pull_to_refresh_sub_text);
        this.mHeaderImage = (ImageView) this.mInnerLayout.findViewById(R.id.pull_to_refresh_image);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mInnerLayout.getLayoutParams();
        int[] iArr = AnonymousClass1.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode;
        if (iArr[mode.ordinal()] != 1) {
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
        if (typedArray != null) {
            int i2 = R.styleable.PullToRefresh_ptrHeaderBackground;
            if (typedArray.hasValue(i2) && (drawable = typedArray.getDrawable(i2)) != null) {
                ViewCompat.setBackground(this, drawable);
            }
            int i3 = R.styleable.PullToRefresh_ptrHeaderTextAppearance;
            if (typedArray.hasValue(i3)) {
                TypedValue typedValue = new TypedValue();
                typedArray.getValue(i3, typedValue);
                setTextAppearance(typedValue.data);
            }
            int i4 = R.styleable.PullToRefresh_ptrSubHeaderTextAppearance;
            if (typedArray.hasValue(i4)) {
                TypedValue typedValue2 = new TypedValue();
                typedArray.getValue(i4, typedValue2);
                setSubTextAppearance(typedValue2.data);
            }
            int i5 = R.styleable.PullToRefresh_ptrHeaderTextColor;
            if (typedArray.hasValue(i5) && (colorStateList2 = typedArray.getColorStateList(i5)) != null) {
                setTextColor(colorStateList2);
            }
            int i6 = R.styleable.PullToRefresh_ptrHeaderSubTextColor;
            if (typedArray.hasValue(i6) && (colorStateList = typedArray.getColorStateList(i6)) != null) {
                setSubTextColor(colorStateList);
            }
            int i7 = R.styleable.PullToRefresh_ptrDrawable;
            r7 = typedArray.hasValue(i7) ? typedArray.getDrawable(i7) : null;
            if (iArr[mode.ordinal()] != 1) {
                int i8 = R.styleable.PullToRefresh_ptrDrawableStart;
                if (typedArray.hasValue(i8)) {
                    r7 = typedArray.getDrawable(i8);
                } else {
                    int i9 = R.styleable.PullToRefresh_ptrDrawableTop;
                    if (typedArray.hasValue(i9)) {
                        Utils.warnDeprecation("ptrDrawableTop", "ptrDrawableStart");
                        r7 = typedArray.getDrawable(i9);
                    }
                }
            } else {
                int i10 = R.styleable.PullToRefresh_ptrDrawableEnd;
                if (typedArray.hasValue(i10)) {
                    r7 = typedArray.getDrawable(i10);
                } else {
                    int i11 = R.styleable.PullToRefresh_ptrDrawableBottom;
                    if (typedArray.hasValue(i11)) {
                        Utils.warnDeprecation("ptrDrawableBottom", "ptrDrawableEnd");
                        r7 = typedArray.getDrawable(i11);
                    }
                }
            }
        }
        setLoadingDrawable(r7 == null ? context.getResources().getDrawable(getDefaultDrawableResId()) : r7);
        reset();
    }

    private void setSubHeaderText(CharSequence charSequence) {
        if (this.mSubHeaderText != null) {
            if (TextUtils.isEmpty(charSequence)) {
                this.mSubHeaderText.setVisibility(8);
                return;
            }
            this.mSubHeaderText.setText(charSequence);
            if (8 == this.mSubHeaderText.getVisibility()) {
                this.mSubHeaderText.setVisibility(0);
            }
        }
    }

    private void setSubTextAppearance(int i2) {
        TextView textView = this.mSubHeaderText;
        if (textView != null) {
            textView.setTextAppearance(getContext(), i2);
        }
    }

    private void setSubTextColor(ColorStateList colorStateList) {
        TextView textView = this.mSubHeaderText;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    private void setTextAppearance(int i2) {
        TextView textView = this.mHeaderText;
        if (textView != null) {
            textView.setTextAppearance(getContext(), i2);
        }
        TextView textView2 = this.mSubHeaderText;
        if (textView2 != null) {
            textView2.setTextAppearance(getContext(), i2);
        }
    }

    private void setTextColor(ColorStateList colorStateList) {
        TextView textView = this.mHeaderText;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
        TextView textView2 = this.mSubHeaderText;
        if (textView2 != null) {
            textView2.setTextColor(colorStateList);
        }
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public final int getContentSize() {
        if (AnonymousClass1.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[this.mScrollDirection.ordinal()] != 1) {
            return this.mInnerLayout.getHeight();
        }
        return this.mInnerLayout.getWidth();
    }

    protected abstract int getDefaultDrawableResId();

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public final void hideAllViews() {
        if (this.mHeaderText.getVisibility() == 0) {
            this.mHeaderText.setVisibility(4);
        }
        if (this.mHeaderProgress.getVisibility() == 0) {
            this.mHeaderProgress.setVisibility(4);
        }
        if (this.mHeaderImage.getVisibility() == 0) {
            this.mHeaderImage.setVisibility(4);
        }
        if (this.mSubHeaderText.getVisibility() == 0) {
            this.mSubHeaderText.setVisibility(4);
        }
    }

    protected abstract void onLoadingDrawableSet(Drawable drawable);

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public final void onPull(float f2) {
        if (this.mUseIntrinsicAnimation) {
            return;
        }
        onPullImpl(f2);
    }

    protected abstract void onPullImpl(float f2);

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public final void pullToRefresh() {
        TextView textView = this.mHeaderText;
        if (textView != null) {
            textView.setText(this.mPullLabel);
        }
        pullToRefreshImpl();
    }

    protected abstract void pullToRefreshImpl();

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public final void refreshing() {
        TextView textView = this.mHeaderText;
        if (textView != null) {
            textView.setText(this.mRefreshingLabel);
        }
        if (this.mUseIntrinsicAnimation) {
            ((AnimationDrawable) this.mHeaderImage.getDrawable()).start();
        } else {
            refreshingImpl();
        }
        TextView textView2 = this.mSubHeaderText;
        if (textView2 != null) {
            textView2.setVisibility(8);
        }
    }

    protected abstract void refreshingImpl();

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public final void releaseToRefresh() {
        TextView textView = this.mHeaderText;
        if (textView != null) {
            textView.setText(this.mReleaseLabel);
        }
        releaseToRefreshImpl();
    }

    protected abstract void releaseToRefreshImpl();

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public final void reset() {
        TextView textView = this.mHeaderText;
        if (textView != null) {
            textView.setText(this.mPullLabel);
        }
        this.mHeaderImage.setVisibility(0);
        if (this.mUseIntrinsicAnimation) {
            ((AnimationDrawable) this.mHeaderImage.getDrawable()).stop();
        } else {
            resetImpl();
        }
        TextView textView2 = this.mSubHeaderText;
        if (textView2 != null) {
            if (TextUtils.isEmpty(textView2.getText())) {
                this.mSubHeaderText.setVisibility(8);
            } else {
                this.mSubHeaderText.setVisibility(0);
            }
        }
    }

    protected abstract void resetImpl();

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public final void setHeight(int i2) {
        getLayoutParams().height = i2;
        requestLayout();
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout, com.handmark.pulltorefresh.library.ILoadingLayout
    public void setLastUpdatedLabel(CharSequence charSequence) {
        setSubHeaderText(charSequence);
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout, com.handmark.pulltorefresh.library.ILoadingLayout
    public final void setLoadingDrawable(Drawable drawable) {
        this.mHeaderImage.setImageDrawable(drawable);
        this.mUseIntrinsicAnimation = drawable instanceof AnimationDrawable;
        onLoadingDrawableSet(drawable);
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout, com.handmark.pulltorefresh.library.ILoadingLayout
    public void setPullLabel(CharSequence charSequence) {
        this.mPullLabel = charSequence;
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
        this.mHeaderText.setTypeface(typeface);
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public final void setWidth(int i2) {
        getLayoutParams().width = i2;
        requestLayout();
    }

    @Override // com.handmark.pulltorefresh.library.internal.BaseLoadingLayout
    public final void showInvisibleViews() {
        if (4 == this.mHeaderText.getVisibility()) {
            this.mHeaderText.setVisibility(0);
        }
        if (4 == this.mHeaderProgress.getVisibility()) {
            this.mHeaderProgress.setVisibility(0);
        }
        if (4 == this.mHeaderImage.getVisibility()) {
            this.mHeaderImage.setVisibility(0);
        }
        if (4 == this.mSubHeaderText.getVisibility()) {
            this.mSubHeaderText.setVisibility(0);
        }
    }
}
