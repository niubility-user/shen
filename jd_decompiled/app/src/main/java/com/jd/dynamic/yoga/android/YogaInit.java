package com.jd.dynamic.yoga.android;

import android.content.Context;
import androidx.annotation.Keep;
import com.facebook.soloader.SoLoader;

@Keep
/* loaded from: classes13.dex */
public class YogaInit {
    public static void initYoga(Context context) {
        SoLoader.init(context, false);
    }
}
