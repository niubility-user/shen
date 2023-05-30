package com.jingdong.manto.widget.g;

import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes16.dex */
public interface d {
    void a(int i2, String str, boolean z);

    void a(boolean z);

    void b(boolean z);

    View getActionView();

    float getAlpha();

    int getBackgroundColor();

    int getForegroundColor();

    ViewGroup.LayoutParams getLayoutParams();

    View getMenuButtonContainer();

    void setAlpha(double d);

    void setAnchorViewVisible(boolean z);

    void setBackgroundColor(int i2);

    void setFakeStatusBarColor(int i2);

    void setForegroundColor(int i2);

    void setForegroundStyle(String str);

    void setNavBarFavoriteClickListener(View.OnClickListener onClickListener);

    void setNavigationBarLoadingVisible(boolean z);

    void setOnBackClickListener(View.OnClickListener onClickListener);

    void setOnBackHomeClickListener(View.OnClickListener onClickListener);

    void setOnCloseClickListener(View.OnClickListener onClickListener);

    void setOnHomeLongClickListener(View.OnLongClickListener onLongClickListener);

    void setOnOptionClickListener(View.OnClickListener onClickListener);

    void setOnStatusBarClickListener(View.OnClickListener onClickListener);

    void setTitle(String str);
}
