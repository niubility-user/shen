package com.facebook.react.views.imagehelper;

import android.content.Context;
import android.net.Uri;
import com.facebook.infer.annotation.Assertions;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class ImageSource {
    private boolean isResource;
    private boolean mDisableAutoPlay;
    private double mSize;
    private String mSource;
    @Nullable
    private Uri mUri;

    public ImageSource(Context context, String str, double d, double d2) {
        this.mDisableAutoPlay = false;
        this.mSource = str;
        this.mSize = d * d2;
        this.mUri = computeUri(context);
    }

    private Uri computeLocalUri(Context context) {
        this.isResource = true;
        return ResourceDrawableIdHelper.getInstance().getResourceDrawableUri(context, this.mSource);
    }

    private Uri computeUri(Context context) {
        try {
            Uri parse = Uri.parse(this.mSource);
            return parse.getScheme() == null ? computeLocalUri(context) : parse;
        } catch (Exception unused) {
            return computeLocalUri(context);
        }
    }

    public double getSize() {
        return this.mSize;
    }

    public String getSource() {
        return this.mSource;
    }

    public Uri getUri() {
        return (Uri) Assertions.assertNotNull(this.mUri);
    }

    public boolean isDisableAutoPlay() {
        return this.mDisableAutoPlay;
    }

    public boolean isResource() {
        return this.isResource;
    }

    public void setDisableAutoPlay(boolean z) {
        this.mDisableAutoPlay = z;
    }

    public ImageSource(Context context, String str) {
        this(context, str, 0.0d, 0.0d);
    }
}
