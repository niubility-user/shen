package com.jingdong.sdk.jfsprovider;

import android.view.View;
import com.facebook.imagepipeline.image.ImageInfo;
import com.jd.mobile.image.ImageRequestListener;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.util.image.JDDisplayImageOptions;

/* loaded from: classes7.dex */
public class BackgroundHelper {
    private static final String TAG = "CalorieUtil";

    public static void setBackgroundBitmapFromUrl(String str, View view) {
        JDImageLoader.display(str, view, (JDDisplayImageOptions) null, (ImageRequestListener<ImageInfo>) null);
    }
}
