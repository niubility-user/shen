package com.jingdong.common.unification.filter;

import android.graphics.Bitmap;
import com.jingdong.common.unification.filter.FilterTools;

/* loaded from: classes6.dex */
public class FilterInfo {
    public Bitmap bitmap;
    public boolean isSelect;
    public String name;
    public FilterTools.FilterType type;

    public FilterInfo(FilterTools.FilterType filterType, String str, Bitmap bitmap) {
        this.type = filterType;
        this.name = str;
        this.isSelect = false;
        this.bitmap = bitmap;
    }

    public FilterInfo(FilterTools.FilterType filterType, String str, boolean z, Bitmap bitmap) {
        this.type = filterType;
        this.name = str;
        this.isSelect = z;
        this.bitmap = bitmap;
    }
}
