package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.NoOpMemoryTrimmableRegistry;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import javax.annotation.concurrent.Immutable;

@Immutable
/* loaded from: classes.dex */
public class PoolConfig {
    public static final int BITMAP_POOL_MAX_BITMAP_SIZE_DEFAULT = 4194304;
    private final int mBitmapPoolMaxBitmapSize;
    private final int mBitmapPoolMaxPoolSize;
    private final PoolParams mBitmapPoolParams;
    private final PoolStatsTracker mBitmapPoolStatsTracker;
    private final String mBitmapPoolType;
    private final PoolParams mFlexByteArrayPoolParams;
    private final boolean mIgnoreBitmapPoolHardCap;
    private final PoolParams mMemoryChunkPoolParams;
    private final PoolStatsTracker mMemoryChunkPoolStatsTracker;
    private final MemoryTrimmableRegistry mMemoryTrimmableRegistry;
    private final boolean mRegisterLruBitmapPoolAsMemoryTrimmable;
    private final PoolParams mSmallByteArrayPoolParams;
    private final PoolStatsTracker mSmallByteArrayPoolStatsTracker;

    /* loaded from: classes.dex */
    public static class Builder {
        private int mBitmapPoolMaxBitmapSize;
        private int mBitmapPoolMaxPoolSize;
        private PoolParams mBitmapPoolParams;
        private PoolStatsTracker mBitmapPoolStatsTracker;
        private String mBitmapPoolType;
        private PoolParams mFlexByteArrayPoolParams;
        public boolean mIgnoreBitmapPoolHardCap;
        private PoolParams mMemoryChunkPoolParams;
        private PoolStatsTracker mMemoryChunkPoolStatsTracker;
        private MemoryTrimmableRegistry mMemoryTrimmableRegistry;
        private boolean mRegisterLruBitmapPoolAsMemoryTrimmable;
        private PoolParams mSmallByteArrayPoolParams;
        private PoolStatsTracker mSmallByteArrayPoolStatsTracker;

        private Builder() {
        }

        public PoolConfig build() {
            return new PoolConfig(this);
        }

        public Builder setBitmapPoolMaxBitmapSize(int i2) {
            this.mBitmapPoolMaxBitmapSize = i2;
            return this;
        }

        public Builder setBitmapPoolMaxPoolSize(int i2) {
            this.mBitmapPoolMaxPoolSize = i2;
            return this;
        }

        public Builder setBitmapPoolParams(PoolParams poolParams) {
            this.mBitmapPoolParams = (PoolParams) Preconditions.checkNotNull(poolParams);
            return this;
        }

        public Builder setBitmapPoolStatsTracker(PoolStatsTracker poolStatsTracker) {
            this.mBitmapPoolStatsTracker = (PoolStatsTracker) Preconditions.checkNotNull(poolStatsTracker);
            return this;
        }

        public Builder setBitmapPoolType(String str) {
            this.mBitmapPoolType = str;
            return this;
        }

        public Builder setFlexByteArrayPoolParams(PoolParams poolParams) {
            this.mFlexByteArrayPoolParams = poolParams;
            return this;
        }

        public Builder setIgnoreBitmapPoolHardCap(boolean z) {
            this.mIgnoreBitmapPoolHardCap = z;
            return this;
        }

        public Builder setMemoryTrimmableRegistry(MemoryTrimmableRegistry memoryTrimmableRegistry) {
            this.mMemoryTrimmableRegistry = memoryTrimmableRegistry;
            return this;
        }

        public Builder setNativeMemoryChunkPoolParams(PoolParams poolParams) {
            this.mMemoryChunkPoolParams = (PoolParams) Preconditions.checkNotNull(poolParams);
            return this;
        }

        public Builder setNativeMemoryChunkPoolStatsTracker(PoolStatsTracker poolStatsTracker) {
            this.mMemoryChunkPoolStatsTracker = (PoolStatsTracker) Preconditions.checkNotNull(poolStatsTracker);
            return this;
        }

        public Builder setRegisterLruBitmapPoolAsMemoryTrimmable(boolean z) {
            this.mRegisterLruBitmapPoolAsMemoryTrimmable = z;
            return this;
        }

        public Builder setSmallByteArrayPoolParams(PoolParams poolParams) {
            this.mSmallByteArrayPoolParams = (PoolParams) Preconditions.checkNotNull(poolParams);
            return this;
        }

        public Builder setSmallByteArrayPoolStatsTracker(PoolStatsTracker poolStatsTracker) {
            this.mSmallByteArrayPoolStatsTracker = (PoolStatsTracker) Preconditions.checkNotNull(poolStatsTracker);
            return this;
        }
    }

    private PoolConfig(Builder builder) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("PoolConfig()");
        }
        this.mBitmapPoolParams = builder.mBitmapPoolParams == null ? DefaultBitmapPoolParams.get() : builder.mBitmapPoolParams;
        this.mBitmapPoolStatsTracker = builder.mBitmapPoolStatsTracker == null ? NoOpPoolStatsTracker.getInstance() : builder.mBitmapPoolStatsTracker;
        this.mFlexByteArrayPoolParams = builder.mFlexByteArrayPoolParams == null ? DefaultFlexByteArrayPoolParams.get() : builder.mFlexByteArrayPoolParams;
        this.mMemoryTrimmableRegistry = builder.mMemoryTrimmableRegistry == null ? NoOpMemoryTrimmableRegistry.getInstance() : builder.mMemoryTrimmableRegistry;
        this.mMemoryChunkPoolParams = builder.mMemoryChunkPoolParams == null ? DefaultNativeMemoryChunkPoolParams.get() : builder.mMemoryChunkPoolParams;
        this.mMemoryChunkPoolStatsTracker = builder.mMemoryChunkPoolStatsTracker == null ? NoOpPoolStatsTracker.getInstance() : builder.mMemoryChunkPoolStatsTracker;
        this.mSmallByteArrayPoolParams = builder.mSmallByteArrayPoolParams == null ? DefaultByteArrayPoolParams.get() : builder.mSmallByteArrayPoolParams;
        this.mSmallByteArrayPoolStatsTracker = builder.mSmallByteArrayPoolStatsTracker == null ? NoOpPoolStatsTracker.getInstance() : builder.mSmallByteArrayPoolStatsTracker;
        this.mBitmapPoolType = builder.mBitmapPoolType == null ? "legacy" : builder.mBitmapPoolType;
        this.mBitmapPoolMaxPoolSize = builder.mBitmapPoolMaxPoolSize;
        this.mBitmapPoolMaxBitmapSize = builder.mBitmapPoolMaxBitmapSize > 0 ? builder.mBitmapPoolMaxBitmapSize : 4194304;
        this.mRegisterLruBitmapPoolAsMemoryTrimmable = builder.mRegisterLruBitmapPoolAsMemoryTrimmable;
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
        this.mIgnoreBitmapPoolHardCap = builder.mIgnoreBitmapPoolHardCap;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public int getBitmapPoolMaxBitmapSize() {
        return this.mBitmapPoolMaxBitmapSize;
    }

    public int getBitmapPoolMaxPoolSize() {
        return this.mBitmapPoolMaxPoolSize;
    }

    public PoolParams getBitmapPoolParams() {
        return this.mBitmapPoolParams;
    }

    public PoolStatsTracker getBitmapPoolStatsTracker() {
        return this.mBitmapPoolStatsTracker;
    }

    public String getBitmapPoolType() {
        return this.mBitmapPoolType;
    }

    public PoolParams getFlexByteArrayPoolParams() {
        return this.mFlexByteArrayPoolParams;
    }

    public PoolParams getMemoryChunkPoolParams() {
        return this.mMemoryChunkPoolParams;
    }

    public PoolStatsTracker getMemoryChunkPoolStatsTracker() {
        return this.mMemoryChunkPoolStatsTracker;
    }

    public MemoryTrimmableRegistry getMemoryTrimmableRegistry() {
        return this.mMemoryTrimmableRegistry;
    }

    public PoolParams getSmallByteArrayPoolParams() {
        return this.mSmallByteArrayPoolParams;
    }

    public PoolStatsTracker getSmallByteArrayPoolStatsTracker() {
        return this.mSmallByteArrayPoolStatsTracker;
    }

    public boolean isIgnoreBitmapPoolHardCap() {
        return this.mIgnoreBitmapPoolHardCap;
    }

    public boolean isRegisterLruBitmapPoolAsMemoryTrimmable() {
        return this.mRegisterLruBitmapPoolAsMemoryTrimmable;
    }
}
