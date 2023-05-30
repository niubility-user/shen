package com.jingdong.manto.utils;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.text.TextUtils;

/* loaded from: classes16.dex */
public final class x {
    public static Bitmap a(String str, int i2, int i3) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Bitmap createVideoThumbnail = ThumbnailUtils.createVideoThumbnail(str, 1);
        return createVideoThumbnail != null ? com.jingdong.manto.sdk.b.a(createVideoThumbnail, i3, i2, true, true) : createVideoThumbnail;
    }
}
