package com.facebook.common.references;

import android.graphics.Bitmap;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import java.util.IdentityHashMap;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

@VisibleForTesting
/* loaded from: classes.dex */
public class SharedReference<T> {
    @GuardedBy("itself")
    private static final Map<Object, Integer> sLiveObjects = new IdentityHashMap();
    @GuardedBy("this")
    private int mRefCount = 1;
    private final ResourceReleaser<T> mResourceReleaser;
    @GuardedBy("this")
    private T mValue;

    /* loaded from: classes.dex */
    public static class NullReferenceException extends RuntimeException {
        public NullReferenceException() {
            super("Null shared reference");
        }
    }

    public SharedReference(T t, ResourceReleaser<T> resourceReleaser) {
        this.mValue = (T) Preconditions.checkNotNull(t);
        this.mResourceReleaser = (ResourceReleaser) Preconditions.checkNotNull(resourceReleaser);
        addLiveReference(t);
    }

    private static void addLiveReference(Object obj) {
        if (CloseableReference.useGc() && ((obj instanceof Bitmap) || (obj instanceof HasBitmap))) {
            return;
        }
        Map<Object, Integer> map = sLiveObjects;
        synchronized (map) {
            Integer num = map.get(obj);
            map.put(obj, num == null ? 1 : Integer.valueOf(num.intValue() + 1));
        }
    }

    private synchronized int decreaseRefCount() {
        int i2;
        ensureValid();
        Preconditions.checkArgument(this.mRefCount > 0);
        i2 = this.mRefCount - 1;
        this.mRefCount = i2;
        return i2;
    }

    private void ensureValid() {
        if (!isValid(this)) {
            throw new NullReferenceException();
        }
    }

    public static boolean isValid(SharedReference<?> sharedReference) {
        return sharedReference != null && sharedReference.isValid();
    }

    private static void removeLiveReference(Object obj) {
        Map<Object, Integer> map = sLiveObjects;
        synchronized (map) {
            Integer num = map.get(obj);
            if (num == null) {
                FLog.wtf("SharedReference", "No entry in sLiveObjects for value of type %s", obj.getClass());
            } else if (num.intValue() == 1) {
                map.remove(obj);
            } else {
                map.put(obj, Integer.valueOf(num.intValue() - 1));
            }
        }
    }

    public static String reportData() {
        return Objects.toStringHelper("SharedReference").add("live_objects_count", sLiveObjects.size()).toString();
    }

    public synchronized void addReference() {
        ensureValid();
        this.mRefCount++;
    }

    public synchronized boolean addReferenceIfValid() {
        boolean z;
        if (isValid()) {
            addReference();
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public void deleteReference() {
        T t;
        if (decreaseRefCount() == 0) {
            synchronized (this) {
                t = this.mValue;
                this.mValue = null;
            }
            this.mResourceReleaser.release(t);
            removeLiveReference(t);
        }
    }

    public synchronized boolean deleteReferenceIfValid() {
        boolean z;
        if (isValid()) {
            deleteReference();
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public synchronized T get() {
        return this.mValue;
    }

    public synchronized int getRefCountTestOnly() {
        return this.mRefCount;
    }

    public synchronized boolean isValid() {
        return this.mRefCount > 0;
    }
}
