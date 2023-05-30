package com.jdpay.image.loader.target;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpay.lib.util.JDPayLog;

/* loaded from: classes18.dex */
public class ImageViewTarget extends ViewTarget<ImageView> {
    public ImageViewTarget(@NonNull ImageView imageView) {
        super(imageView);
    }

    @Override // com.jdpay.image.loader.target.ViewTarget, com.jdpay.image.loader.target.RequestTarget
    public void applyPlaceholder() {
        ImageView imageView = (ImageView) get();
        if (imageView == null) {
            return;
        }
        String uri = getUri();
        if (isIgnorePlaceholder()) {
            JDPayLog.d("Ignore placeholder:" + getUri());
            return;
        }
        imageView.setTag(ViewTarget.KEY_URI, uri);
        if (getPlaceholderDrawable() != null) {
            apply(imageView, (Object) getPlaceholderDrawable());
        } else if (getPlaceholderResId() > 0) {
            imageView.setImageResource(getPlaceholderResId());
        } else {
            imageView.setImageDrawable(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jdpay.image.loader.target.ViewTarget
    public void apply(@NonNull ImageView imageView, @Nullable Object obj) {
        if (obj instanceof Bitmap) {
            imageView.setImageBitmap((Bitmap) obj);
        } else if (obj instanceof Drawable) {
            imageView.setImageDrawable((Drawable) obj);
        } else {
            imageView.setImageBitmap(null);
        }
    }
}
