package com.jd.android.sdk.coreinfo;

import com.jd.dynamic.DYConstants;

/* loaded from: classes.dex */
public class ScreenSize {
    public int heightPixels;
    public int widthPixels;

    public ScreenSize(int i2, int i3) {
        this.widthPixels = i2;
        this.heightPixels = i3;
    }

    public String toString() {
        return this.widthPixels + DYConstants.DY_REGEX_COMMA + this.heightPixels;
    }
}
