package com.jd.cashier.app.jdlibcutter.protocol.ui.title;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

/* loaded from: classes13.dex */
public interface IThemeTitle {
    TextView getRightTv1TextView(View view);

    TextView getTitleView(View view);

    View instanceTitleView(Context context);

    boolean isThemeLightStyleColor(View view);

    void loadTheme(View view);

    void notifyChange(View view);

    void onHandDarkMode(View view);

    void setCustomOpen(View view, boolean z);

    void setLeft1BackDrawableId(View view);

    void setLeft1ImageViewVisibility(View view, int i2);

    void setLeftIv1ClickListener(View view, View.OnClickListener onClickListener);

    void setRightTv1ClickListener(View view, View.OnClickListener onClickListener);

    void setRightTv1Text(View view, String str);

    void setRightTv1Visibility(View view, int i2);

    void setStatusBarColorStyle(View view, Activity activity);

    void setStatusBarHint(View view, boolean z);

    void setTitleBgImageViewAlpha(View view, float f2);

    void setTitleText(View view, String str);
}
