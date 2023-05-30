package com.jd.lib.babel.servicekit.imagekit;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/* loaded from: classes13.dex */
public interface BabelImageKitServer {
    void displayImage(ImageArr imageArr);

    ImageView newImageView(Context context, AttributeSet attributeSet);

    void obtainDrawable(Context context, String str, BabelDrawableListener babelDrawableListener);
}
