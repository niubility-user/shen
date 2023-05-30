package com.jingdong.manto.utils;

import android.content.Context;
import com.jingdong.manto.R;

/* loaded from: classes16.dex */
public class g {
    public static int a(Context context) {
        return context.getResources().getDimensionPixelSize(MantoDensityUtils.getDMWidthPixels() > MantoDensityUtils.getDMHeightPixels() ? R.dimen.action_bar_height_hori : R.dimen.action_bar_height);
    }
}
