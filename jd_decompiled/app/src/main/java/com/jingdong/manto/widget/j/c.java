package com.jingdong.manto.widget.j;

import android.graphics.drawable.Drawable;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

/* loaded from: classes16.dex */
public interface c {
    d a(int i2, @StringRes int i3, @DrawableRes int i4);

    d a(int i2, @StringRes int i3, @DrawableRes int i4, boolean z, int i5);

    d a(int i2, CharSequence charSequence, Drawable drawable);

    void clear();

    d getItem(int i2);

    int size();
}
