package com.jdpay.image.loader.target;

import android.graphics.BitmapFactory;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;

/* loaded from: classes18.dex */
public abstract class RequestTarget<T> {
    private BitmapFactory.Options bitmapOptions;
    private final int hash;
    private final WeakReference<T> target;
    private String uri;

    public RequestTarget(@NonNull T t) {
        this.target = new WeakReference<>(t);
        this.hash = t.hashCode();
    }

    public abstract void apply(@Nullable Object obj);

    public abstract void applyPlaceholder();

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof RequestTarget) && this.hash == ((RequestTarget) obj).hash;
    }

    public T get() {
        return this.target.get();
    }

    public BitmapFactory.Options getBitmapOptions() {
        return this.bitmapOptions;
    }

    public String getUri() {
        return this.uri;
    }

    public int hashCode() {
        return this.hash;
    }

    public void setBitmapOptions(BitmapFactory.Options options) {
        this.bitmapOptions = options;
    }

    public void setUri(String str) {
        this.uri = str;
        applyPlaceholder();
    }
}
