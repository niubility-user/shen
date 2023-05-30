package com.jdpay.image.loader.request;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import com.jdpay.image.loader.BuildConfig;
import com.jdpay.image.loader.target.RequestTarget;
import com.jdpay.net.http.HttpProvider;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes18.dex */
public class JDImageLoader {
    private final ExecutorService cacheExecutor;
    private HttpProvider provider;
    private final HashMap<String, ImageRequest> requests;
    private final LinkedList<RequestTarget> targets;

    public JDImageLoader(@NonNull HttpProvider httpProvider) {
        this(httpProvider, new ThreadPoolExecutor(0, 16, 1L, TimeUnit.MINUTES, new LinkedBlockingQueue(128), new ThreadPoolExecutor.DiscardPolicy()));
    }

    private String checkUri(@NonNull String str) {
        return Build.VERSION.SDK_INT >= 28 ? str.replace("http://", "https://") : str;
    }

    public void addOrReplaceTarget(@NonNull RequestTarget requestTarget) {
        synchronized (this.targets) {
            Iterator<RequestTarget> it = this.targets.iterator();
            int i2 = 0;
            while (true) {
                if (!it.hasNext()) {
                    i2 = -1;
                    break;
                }
                RequestTarget next = it.next();
                if (next != null && next.equals(requestTarget)) {
                    break;
                }
                i2++;
            }
            if (i2 != -1) {
                this.targets.set(i2, requestTarget);
            } else {
                this.targets.add(requestTarget);
            }
        }
    }

    public void cancel(@NonNull String str) {
        ImageRequest imageRequest;
        String checkUri = checkUri(str);
        synchronized (this.requests) {
            imageRequest = this.requests.get(checkUri);
        }
        if (imageRequest != null) {
            imageRequest.cancel();
            removeTargetsByUri(checkUri);
        }
    }

    public void cancellAll() {
        synchronized (this.requests) {
            Iterator<ImageRequest> it = this.requests.values().iterator();
            while (it.hasNext()) {
                it.next().cancel();
            }
        }
    }

    public void clearDefaultCache(@NonNull Context context) {
        for (File file : new File(context.getCacheDir(), BuildConfig.DEFAULT_CACHE_SUB_DIR).listFiles()) {
            file.delete();
        }
    }

    public ExecutorService getCacheExecutor() {
        return this.cacheExecutor;
    }

    public HttpProvider getProvider() {
        return this.provider;
    }

    public boolean hasTarget(@NonNull String str, @NonNull Object obj) {
        int hashCode = obj.hashCode();
        Iterator<RequestTarget> it = this.targets.iterator();
        while (it.hasNext()) {
            RequestTarget next = it.next();
            if (hashCode == next.hashCode() && next.getUri().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void release(@NonNull String str) {
        String checkUri = checkUri(str);
        synchronized (this.requests) {
            this.requests.remove(checkUri);
            removeTargetsByUri(checkUri);
        }
    }

    public List<RequestTarget> removeTargetsByUri(@NonNull String str) {
        String checkUri = checkUri(str);
        LinkedList linkedList = new LinkedList();
        synchronized (this.targets) {
            Iterator<RequestTarget> it = this.targets.iterator();
            while (it.hasNext()) {
                RequestTarget next = it.next();
                if (next != null && next.getUri().equals(checkUri)) {
                    linkedList.add(next);
                    it.remove();
                }
            }
        }
        return linkedList;
    }

    public ImageRequest uri(@NonNull String str) {
        ImageRequest imageRequest;
        String checkUri = checkUri(str);
        synchronized (this.requests) {
            imageRequest = this.requests.get(checkUri);
            if (imageRequest == null) {
                imageRequest = new ImageRequest(checkUri, this);
                this.requests.put(checkUri, imageRequest);
            }
        }
        return imageRequest;
    }

    public JDImageLoader(@NonNull HttpProvider httpProvider, @NonNull ExecutorService executorService) {
        this.requests = new HashMap<>();
        this.targets = new LinkedList<>();
        this.provider = httpProvider;
        this.cacheExecutor = executorService;
    }
}
