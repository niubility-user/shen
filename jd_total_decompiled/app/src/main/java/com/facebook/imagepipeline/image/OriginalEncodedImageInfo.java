package com.facebook.imagepipeline.image;

import android.net.Uri;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class OriginalEncodedImageInfo {
    @Nullable
    private final Object mCallerContext;
    private final int mHeight;
    @Nullable
    private final EncodedImageOrigin mOrigin;
    private final int mSize;
    private final Uri mUri;
    private final int mWidth;

    public OriginalEncodedImageInfo(Uri uri, EncodedImageOrigin encodedImageOrigin, @Nullable Object obj, int i2, int i3, int i4) {
        this.mUri = uri;
        this.mOrigin = encodedImageOrigin;
        this.mCallerContext = obj;
        this.mWidth = i2;
        this.mHeight = i3;
        this.mSize = i4;
    }

    @Nullable
    public Object getCallerContext() {
        return this.mCallerContext;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public EncodedImageOrigin getOrigin() {
        return this.mOrigin;
    }

    public int getSize() {
        return this.mSize;
    }

    public Uri getUri() {
        return this.mUri;
    }

    public int getWidth() {
        return this.mWidth;
    }
}
