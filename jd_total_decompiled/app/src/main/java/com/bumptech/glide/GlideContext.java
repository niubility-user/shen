package com.bumptech.glide;

import android.content.Context;
import android.content.ContextWrapper;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTargetFactory;
import com.bumptech.glide.request.target.ViewTarget;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class GlideContext extends ContextWrapper {
    @VisibleForTesting
    static final TransitionOptions<?, ?> DEFAULT_TRANSITION_OPTIONS = new GenericTransitionOptions();
    private final ArrayPool arrayPool;
    private final List<RequestListener<Object>> defaultRequestListeners;
    private final RequestOptions defaultRequestOptions;
    private final Map<Class<?>, TransitionOptions<?, ?>> defaultTransitionOptions;
    private final Engine engine;
    private final ImageViewTargetFactory imageViewTargetFactory;
    private final boolean isLoggingRequestOriginsEnabled;
    private final int logLevel;
    private final Registry registry;

    public GlideContext(@NonNull Context context, @NonNull ArrayPool arrayPool, @NonNull Registry registry, @NonNull ImageViewTargetFactory imageViewTargetFactory, @NonNull RequestOptions requestOptions, @NonNull Map<Class<?>, TransitionOptions<?, ?>> map, @NonNull List<RequestListener<Object>> list, @NonNull Engine engine, boolean z, int i2) {
        super(context.getApplicationContext());
        this.arrayPool = arrayPool;
        this.registry = registry;
        this.imageViewTargetFactory = imageViewTargetFactory;
        this.defaultRequestOptions = requestOptions;
        this.defaultRequestListeners = list;
        this.defaultTransitionOptions = map;
        this.engine = engine;
        this.isLoggingRequestOriginsEnabled = z;
        this.logLevel = i2;
    }

    @NonNull
    public <X> ViewTarget<ImageView, X> buildImageViewTarget(@NonNull ImageView imageView, @NonNull Class<X> cls) {
        return this.imageViewTargetFactory.buildTarget(imageView, cls);
    }

    @NonNull
    public ArrayPool getArrayPool() {
        return this.arrayPool;
    }

    public List<RequestListener<Object>> getDefaultRequestListeners() {
        return this.defaultRequestListeners;
    }

    public RequestOptions getDefaultRequestOptions() {
        return this.defaultRequestOptions;
    }

    @NonNull
    public <T> TransitionOptions<?, T> getDefaultTransitionOptions(@NonNull Class<T> cls) {
        TransitionOptions<?, T> transitionOptions = (TransitionOptions<?, T>) this.defaultTransitionOptions.get(cls);
        if (transitionOptions == null) {
            for (Map.Entry<Class<?>, TransitionOptions<?, ?>> entry : this.defaultTransitionOptions.entrySet()) {
                if (entry.getKey().isAssignableFrom(cls)) {
                    transitionOptions = (TransitionOptions<?, T>) entry.getValue();
                }
            }
        }
        return transitionOptions == null ? (TransitionOptions<?, T>) DEFAULT_TRANSITION_OPTIONS : transitionOptions;
    }

    @NonNull
    public Engine getEngine() {
        return this.engine;
    }

    public int getLogLevel() {
        return this.logLevel;
    }

    @NonNull
    public Registry getRegistry() {
        return this.registry;
    }

    public boolean isLoggingRequestOriginsEnabled() {
        return this.isLoggingRequestOriginsEnabled;
    }
}
