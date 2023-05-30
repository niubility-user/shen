package com.jdpay.image.loader.request;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpay.exception.JPException;
import com.jdpay.image.loader.BuildConfig;
import com.jdpay.image.loader.converter.BitmapFileCacheConverter;
import com.jdpay.image.loader.converter.PathBitmapConverter;
import com.jdpay.image.loader.converter.ScaleConfig;
import com.jdpay.image.loader.target.ImageViewTarget;
import com.jdpay.image.loader.target.RequestTarget;
import com.jdpay.image.loader.target.ViewTarget;
import com.jdpay.lib.converter.Converter;
import com.jdpay.lib.name.MD5NameGenerator;
import com.jdpay.lib.name.NameGenerator;
import com.jdpay.lib.util.JDPayLog;
import com.jdpay.net.Result;
import com.jdpay.net.ResultObserver;
import com.jdpay.net.http.HttpProvider;
import com.jdpay.net.http.HttpRequest;
import com.jdpay.net.http.HttpResponse;
import com.jdpay.net.http.HttpResult;
import java.io.File;
import java.util.Iterator;

/* loaded from: classes18.dex */
public class ImageRequest {
    private BitmapFactory.Options bitmapOptions;
    private File cache;
    private File cacheDir;
    private boolean isFd;
    private volatile boolean isLoading;
    private final JDImageLoader manager;
    private NameGenerator nameGenerator;
    private ResultObserver<Object> observer;
    private ResultObserver<Object> outerObserver;
    private int reqId;
    private Converter<HttpResponse, Object> responseConverter;
    private ScaleConfig scaleConfig;
    private final String uri;

    /* JADX INFO: Access modifiers changed from: protected */
    public ImageRequest(@NonNull String str, @NonNull JDImageLoader jDImageLoader) {
        this.uri = str;
        this.manager = jDImageLoader;
    }

    public ImageRequest bitmapOptions(@Nullable BitmapFactory.Options options) {
        if (!this.isLoading) {
            this.bitmapOptions = options;
        }
        return this;
    }

    public ImageRequest cache(@Nullable File file) {
        if (!this.isLoading) {
            this.cacheDir = file;
        }
        return this;
    }

    public ImageRequest cacheNameGenerator(@NonNull NameGenerator nameGenerator) {
        if (!this.isLoading) {
            this.nameGenerator = nameGenerator;
        }
        return this;
    }

    public void cancel() {
        if (this.isLoading) {
            this.manager.getProvider().cancel(this.reqId);
        }
    }

    public ImageRequest defaultCache(@NonNull Context context) {
        if (!this.isLoading) {
            this.cacheDir = new File(context.getCacheDir(), BuildConfig.DEFAULT_CACHE_SUB_DIR);
        }
        return this;
    }

    protected Runnable getCacheLoader() {
        return new Runnable() { // from class: com.jdpay.image.loader.request.ImageRequest.3
            @Override // java.lang.Runnable
            public void run() {
                Result result = new Result(ImageRequest.this.observer);
                if (ImageRequest.this.cache == null || !ImageRequest.this.cache.exists()) {
                    result.onHandleResult(null, null, new JPException("Load cache failed. Uri:" + ImageRequest.this.uri));
                } else {
                    result.onHandleResult(ImageRequest.this.cache, new PathBitmapConverter(ImageRequest.this.bitmapOptions, ImageRequest.this.isFd, ImageRequest.this.scaleConfig), null);
                }
                ImageRequest.this.isLoading = false;
            }
        };
    }

    protected Converter<HttpResponse, Object> getFileCacheResponseConverter() {
        return new Converter<HttpResponse, Object>() { // from class: com.jdpay.image.loader.request.ImageRequest.2
            @Override // com.jdpay.lib.converter.Converter
            public Object convert(@Nullable HttpResponse httpResponse) throws Throwable {
                if (httpResponse == null || !httpResponse.isSuccessful()) {
                    return null;
                }
                return new BitmapFileCacheConverter(ImageRequest.this.cache, ImageRequest.this.bitmapOptions, ImageRequest.this.isFd, ImageRequest.this.scaleConfig).convert(httpResponse.getInputStream());
            }
        };
    }

    protected final synchronized ResultObserver<Object> getResultObserver() {
        return new ResultObserver<Object>() { // from class: com.jdpay.image.loader.request.ImageRequest.1
            private void onComplete() {
                try {
                    ImageRequest.this.manager.release(ImageRequest.this.uri);
                } finally {
                    try {
                    } finally {
                    }
                }
            }

            @Override // com.jdpay.net.ResultObserver
            public void onFailure(@NonNull Throwable th) {
                JDPayLog.e(th);
                try {
                    if (ImageRequest.this.cache != null && ImageRequest.this.cache.exists()) {
                        ImageRequest.this.cache.delete();
                    }
                    if (ImageRequest.this.outerObserver != null) {
                        ImageRequest.this.outerObserver.onFailure(th);
                    }
                } catch (Throwable th2) {
                    JDPayLog.e(th2);
                }
                onComplete();
            }

            @Override // com.jdpay.net.ResultObserver
            public void onSuccess(@Nullable Object obj) {
                try {
                    Iterator<RequestTarget> it = ImageRequest.this.manager.removeTargetsByUri(ImageRequest.this.uri).iterator();
                    while (it.hasNext()) {
                        it.next().apply(obj);
                    }
                    if (ImageRequest.this.outerObserver != null) {
                        ImageRequest.this.outerObserver.onSuccess(obj);
                    }
                    onComplete();
                } catch (Throwable th) {
                    onFailure(th);
                }
            }
        };
    }

    public boolean isLoading() {
        return this.isLoading;
    }

    protected final synchronized boolean isMatch(@Nullable RequestTarget requestTarget) {
        boolean z;
        if (requestTarget != null) {
            z = this.uri.equals(requestTarget.getUri());
        }
        return z;
    }

    public void load() {
        ResultObserver<Object> resultObserver;
        ResultObserver<Object> resultObserver2;
        if (TextUtils.isEmpty(this.uri) && (resultObserver2 = this.outerObserver) != null) {
            resultObserver2.onFailure(new IllegalArgumentException("Url is empty"));
        } else if (!this.uri.startsWith("http://") && !this.uri.startsWith("https://") && (resultObserver = this.outerObserver) != null) {
            resultObserver.onFailure(new IllegalArgumentException("[" + this.uri + "] protocol is not support"));
        } else if (this.reqId <= 0 && !this.isLoading) {
            if (this.observer == null) {
                this.observer = getResultObserver();
            }
            if (this.cacheDir != null) {
                if (this.nameGenerator == null) {
                    this.nameGenerator = new MD5NameGenerator(this.uri);
                }
                this.cache = new File(this.cacheDir, this.nameGenerator.generate());
                if (this.responseConverter == null) {
                    this.responseConverter = getFileCacheResponseConverter();
                }
            }
            if (this.responseConverter == null) {
                JDPayLog.e("Response converter is null");
                return;
            }
            this.isLoading = true;
            File file = this.cache;
            if (file != null && file.exists()) {
                this.manager.getCacheExecutor().execute(getCacheLoader());
                return;
            }
            HttpProvider provider = this.manager.getProvider();
            HttpRequest httpRequest = (HttpRequest) ((HttpRequest.Builder) provider.obtainBuilder()).setResponseConverter((HttpRequest.Builder) this.responseConverter).setUrl(this.uri).setMethod("GET").build();
            this.reqId = httpRequest.getId();
            provider.enqueue(httpRequest, new HttpResult(this.observer));
        } else {
            JDPayLog.d("Ignore image request Req:" + this.reqId + " isLoading:" + this.isLoading);
        }
    }

    public ImageRequest observe(ResultObserver<Object> resultObserver) {
        this.outerObserver = resultObserver;
        return this;
    }

    public ImageRequest responseConverter(Converter<HttpResponse, Object> converter) {
        if (!this.isLoading) {
            this.responseConverter = converter;
        }
        return this;
    }

    public ImageRequest setFd(boolean z) {
        this.isFd = z;
        return this;
    }

    public ImageRequest setScaleConfig(ScaleConfig scaleConfig) {
        if (ScaleConfig.isValid(scaleConfig)) {
            this.scaleConfig = scaleConfig;
        }
        return this;
    }

    public synchronized ImageRequest to(@NonNull Object obj) {
        if (this.manager.hasTarget(this.uri, obj)) {
            return this;
        }
        RequestTarget requestTarget = null;
        if (obj instanceof RequestTarget) {
            requestTarget = (RequestTarget) obj;
        } else if (obj instanceof ImageView) {
            requestTarget = new ImageViewTarget((ImageView) obj);
            requestTarget.setUri(this.uri);
        } else if (obj instanceof View) {
            requestTarget = new ViewTarget((View) obj);
            requestTarget.setUri(this.uri);
        }
        if (requestTarget != null) {
            this.manager.addOrReplaceTarget(requestTarget);
        }
        return this;
    }
}
