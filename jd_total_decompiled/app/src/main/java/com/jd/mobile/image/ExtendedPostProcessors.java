package com.jd.mobile.image;

import android.content.Context;
import com.facebook.imagepipeline.request.Postprocessor;
import com.jd.mobile.image.a.e.a;

/* loaded from: classes17.dex */
public class ExtendedPostProcessors {
    public static Postprocessor newScalingBlurPostProcessor(Context context, int i2, int i3) {
        return new a(context, i2, i3);
    }
}
