package com.jingdong.common.model.smarttablayout.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;

/* loaded from: classes5.dex */
public class ViewPagerItem extends PagerItem {
    private final int resource;

    protected ViewPagerItem(CharSequence charSequence, float f2, @LayoutRes int i2) {
        super(charSequence, f2);
        this.resource = i2;
    }

    public static ViewPagerItem of(CharSequence charSequence, @LayoutRes int i2) {
        return of(charSequence, 1.0f, i2);
    }

    public View initiate(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(this.resource, viewGroup, false);
    }

    public static ViewPagerItem of(CharSequence charSequence, float f2, @LayoutRes int i2) {
        return new ViewPagerItem(charSequence, f2, i2);
    }
}
