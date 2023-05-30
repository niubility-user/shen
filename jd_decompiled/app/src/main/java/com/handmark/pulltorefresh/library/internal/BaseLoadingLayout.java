package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.OnHeaderScrollChangeListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.global.theme.UnWidgetThemeController;

/* loaded from: classes12.dex */
public abstract class BaseLoadingLayout extends FrameLayout implements ILoadingLayout {
    private Drawable bg;
    public OnHeaderScrollChangeListener headerScrollListener;
    private boolean isAutoDark;
    private boolean isDarkMode;
    private boolean isRedMode;
    private boolean isSettingDark;
    private PullToRefreshBase.Mode mode;
    private PullToRefreshBase.Orientation orientation;

    public BaseLoadingLayout(Context context) {
        super(context);
        this.isAutoDark = false;
    }

    public void addHeaderView(View view, ViewGroup.LayoutParams layoutParams) {
    }

    @Override // android.view.View
    public Drawable getBackground() {
        Drawable drawable = this.bg;
        if (drawable != null) {
            return drawable;
        }
        if (isDarkMode()) {
            if (isRedMode()) {
                return getResources().getDrawable(R.drawable.un_refresh_red_bg_dark);
            }
            return getResources().getDrawable(R.color.un_bg_level_1_dark);
        } else if (isRedMode()) {
            return getResources().getDrawable(R.drawable.un_refresh_red_bg);
        } else {
            return getResources().getDrawable(R.color.un_bg_level_1);
        }
    }

    public abstract int getContentSize();

    public PullToRefreshBase.Mode getMode() {
        return this.mode;
    }

    public PullToRefreshBase.Orientation getOrientation() {
        return this.orientation;
    }

    public void hideAllViews() {
    }

    public boolean isDarkMode() {
        if (this.isAutoDark) {
            return UnWidgetThemeController.getInstance().isDarkMode();
        }
        return this.isDarkMode;
    }

    public boolean isRedMode() {
        return this.isRedMode;
    }

    public boolean isSettingDark() {
        return this.isSettingDark;
    }

    public void onPull(float f2) {
    }

    public void onScroll(int i2, int i3) {
    }

    public abstract void pullToRefresh();

    public void refreshComplete() {
    }

    public void refreshTheme() {
    }

    public abstract void refreshing();

    public abstract void releaseToRefresh();

    public abstract void reset();

    public void setAutoDark(boolean z) {
        this.isAutoDark = z;
        this.isSettingDark = true;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        this.bg = drawable;
    }

    public void setDarkMode(boolean z) {
        this.isDarkMode = z;
        this.isSettingDark = true;
    }

    public void setHeaderScrollListener(OnHeaderScrollChangeListener onHeaderScrollChangeListener) {
        this.headerScrollListener = onHeaderScrollChangeListener;
    }

    public abstract void setHeight(int i2);

    @Override // com.handmark.pulltorefresh.library.ILoadingLayout
    public void setLastUpdatedLabel(CharSequence charSequence) {
    }

    @Override // com.handmark.pulltorefresh.library.ILoadingLayout
    public void setLoadingDrawable(Drawable drawable) {
    }

    @Override // com.handmark.pulltorefresh.library.ILoadingLayout
    public void setPullLabel(CharSequence charSequence) {
    }

    public void setRedMode(boolean z) {
        this.isRedMode = z;
    }

    public void setRefreshTextColor(int i2) {
    }

    @Override // com.handmark.pulltorefresh.library.ILoadingLayout
    public void setRefreshingLabel(CharSequence charSequence) {
    }

    @Override // com.handmark.pulltorefresh.library.ILoadingLayout
    public void setReleaseLabel(CharSequence charSequence) {
    }

    @Override // com.handmark.pulltorefresh.library.ILoadingLayout
    public void setTextTypeface(Typeface typeface) {
    }

    public abstract void setWidth(int i2);

    public void showInvisibleViews() {
    }

    public BaseLoadingLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isAutoDark = false;
    }

    public BaseLoadingLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.isAutoDark = false;
    }

    public BaseLoadingLayout(@NonNull Context context, AttributeSet attributeSet, @NonNull PullToRefreshBase.Mode mode, @NonNull PullToRefreshBase.Orientation orientation) {
        super(context, attributeSet);
        this.isAutoDark = false;
        this.mode = mode;
        this.orientation = orientation;
    }
}
