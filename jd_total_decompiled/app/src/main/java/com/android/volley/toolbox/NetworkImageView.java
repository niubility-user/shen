package com.android.volley.toolbox;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.android.volley.error.VolleyError;
import com.android.volley.toolbox.ImageLoader;

/* loaded from: classes.dex */
public class NetworkImageView extends ImageView {
    private int mDefaultImageId;
    private int mErrorImageId;
    private ImageLoader.ImageContainer mImageContainer;
    private ImageLoader mImageLoader;
    private String mUrl;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.android.volley.toolbox.NetworkImageView$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements ImageLoader.ImageListener {
        final /* synthetic */ boolean val$isInLayoutPass;

        AnonymousClass1(boolean z) {
            this.val$isInLayoutPass = z;
        }

        @Override // com.android.volley.Response.ErrorListener
        public void onCancel() {
        }

        @Override // com.android.volley.Response.ErrorListener
        public void onErrorResponse(VolleyError volleyError) {
            if (NetworkImageView.this.mErrorImageId != 0) {
                NetworkImageView networkImageView = NetworkImageView.this;
                networkImageView.setImageResource(networkImageView.mErrorImageId);
            }
        }

        @Override // com.android.volley.toolbox.ImageLoader.ImageListener
        public void onResponse(final ImageLoader.ImageContainer imageContainer, boolean z) {
            if (z && this.val$isInLayoutPass) {
                NetworkImageView.this.post(new Runnable() { // from class: com.android.volley.toolbox.NetworkImageView.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AnonymousClass1.this.onResponse(imageContainer, false);
                    }
                });
            } else if (imageContainer.getBitmap() == null) {
                if (NetworkImageView.this.mDefaultImageId != 0) {
                    NetworkImageView networkImageView = NetworkImageView.this;
                    networkImageView.setImageResource(networkImageView.mDefaultImageId);
                }
            } else {
                NetworkImageView.this.setImageBitmap(imageContainer.getBitmap());
            }
        }

        @Override // com.android.volley.Response.ErrorListener
        public void onStart() {
        }
    }

    public NetworkImageView(Context context) {
        this(context, null);
    }

    private void setDefaultImageOrNull() {
        int i2 = this.mDefaultImageId;
        if (i2 != 0) {
            setImageResource(i2);
        } else {
            setImageBitmap(null);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void loadImageIfNecessary(boolean z) {
        boolean z2;
        boolean z3;
        int width = getWidth();
        int height = getHeight();
        ImageView.ScaleType scaleType = getScaleType();
        if (getLayoutParams() != null) {
            z2 = getLayoutParams().width == -2;
            if (getLayoutParams().height == -2) {
                z3 = true;
                boolean z4 = !z2 && z3;
                if (width == 0 || height != 0 || z4) {
                    if (!TextUtils.isEmpty(this.mUrl)) {
                        ImageLoader.ImageContainer imageContainer = this.mImageContainer;
                        if (imageContainer != null) {
                            imageContainer.cancelRequest();
                            this.mImageContainer = null;
                        }
                        setDefaultImageOrNull();
                        return;
                    }
                    ImageLoader.ImageContainer imageContainer2 = this.mImageContainer;
                    if (imageContainer2 != null && imageContainer2.getRequestUrl() != null) {
                        if (this.mImageContainer.getRequestUrl().equals(this.mUrl)) {
                            return;
                        }
                        this.mImageContainer.cancelRequest();
                        setDefaultImageOrNull();
                    }
                    if (z2) {
                        width = 0;
                    }
                    this.mImageContainer = this.mImageLoader.get(this.mUrl, new AnonymousClass1(z), width, z3 ? 0 : height, scaleType);
                    return;
                }
                return;
            }
        } else {
            z2 = false;
        }
        z3 = false;
        if (z2) {
        }
        if (width == 0) {
        }
        if (!TextUtils.isEmpty(this.mUrl)) {
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        ImageLoader.ImageContainer imageContainer = this.mImageContainer;
        if (imageContainer != null) {
            imageContainer.cancelRequest();
            setImageBitmap(null);
            this.mImageContainer = null;
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        loadImageIfNecessary(true);
    }

    public void setDefaultImageResId(int i2) {
        this.mDefaultImageId = i2;
    }

    public void setErrorImageResId(int i2) {
        this.mErrorImageId = i2;
    }

    public void setImageUrl(String str, ImageLoader imageLoader) {
        this.mUrl = str;
        this.mImageLoader = imageLoader;
        loadImageIfNecessary(false);
    }

    public NetworkImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NetworkImageView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
