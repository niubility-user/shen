package com.facebook.fresco.animation.bitmap.cache;

import android.net.Uri;
import com.facebook.cache.common.CacheKey;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class AnimationFrameCacheKey implements CacheKey {
    private static final String URI_PREFIX = "anim://";
    private final String mAnimationUriString;

    public AnimationFrameCacheKey(int i2) {
        this.mAnimationUriString = URI_PREFIX + i2;
    }

    @Override // com.facebook.cache.common.CacheKey
    public boolean containsUri(Uri uri) {
        return uri.toString().startsWith(this.mAnimationUriString);
    }

    @Override // com.facebook.cache.common.CacheKey
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AnimationFrameCacheKey.class != obj.getClass()) {
            return false;
        }
        return this.mAnimationUriString.equals(((AnimationFrameCacheKey) obj).mAnimationUriString);
    }

    @Override // com.facebook.cache.common.CacheKey
    public String getUriString() {
        return this.mAnimationUriString;
    }

    @Override // com.facebook.cache.common.CacheKey
    public int hashCode() {
        return this.mAnimationUriString.hashCode();
    }

    @Override // com.facebook.cache.common.CacheKey
    public boolean isResourceIdForDebugging() {
        return false;
    }
}
