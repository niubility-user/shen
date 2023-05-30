package com.jdpay.image.loader.target;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.jdpay.lib.util.JDPayLog;

/* loaded from: classes18.dex */
public class ViewTarget<T extends View> extends RequestTarget<T> {
    public static final int KEY_URI = 90680716;
    private Drawable placeholderDrawable;
    private int placeholderResId;

    public ViewTarget(@NonNull T t) {
        super(t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.jdpay.image.loader.target.RequestTarget
    public void apply(@Nullable Object obj) {
        View view = (View) get();
        if (view != null) {
            apply(view, obj);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.jdpay.image.loader.target.RequestTarget
    public void applyPlaceholder() {
        View view = (View) get();
        if (view == null) {
            return;
        }
        String uri = getUri();
        if (isIgnorePlaceholder()) {
            JDPayLog.d("Ignore placeholder:" + uri);
            return;
        }
        view.setTag(KEY_URI, uri);
        Drawable drawable = this.placeholderDrawable;
        if (drawable != null) {
            apply(view, drawable);
            return;
        }
        int i2 = this.placeholderResId;
        if (i2 > 0) {
            view.setBackgroundResource(i2);
        }
    }

    public Drawable getPlaceholderDrawable() {
        return this.placeholderDrawable;
    }

    public int getPlaceholderResId() {
        return this.placeholderResId;
    }

    public boolean isIgnorePlaceholder() {
        View view = (View) get();
        if (view == null) {
            return false;
        }
        Object tag = view.getTag(KEY_URI);
        return tag != null && tag.equals(getUri());
    }

    public void setPlaceholderDrawable(Drawable drawable) {
        this.placeholderDrawable = drawable;
    }

    public void setPlaceholderResId(int i2) {
        this.placeholderResId = i2;
    }

    protected void apply(@NonNull T t, @Nullable Object obj) {
        if (obj instanceof Bitmap) {
            ViewCompat.setBackground(t, new BitmapDrawable((Bitmap) obj));
        } else if (obj instanceof Drawable) {
            ViewCompat.setBackground(t, (Drawable) obj);
        } else if (obj instanceof Integer) {
            t.setBackgroundResource(((Integer) obj).intValue());
        }
    }
}
