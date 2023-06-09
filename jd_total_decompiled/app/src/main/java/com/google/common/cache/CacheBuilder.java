package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Equivalence;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.base.Ticker;
import com.google.common.cache.AbstractCache;
import com.google.common.cache.LocalCache;
import com.google.errorprone.annotations.CheckReturnValue;
import com.meizu.cloud.pushsdk.notification.model.NotificationStyle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@GwtCompatible(emulated = true)
/* loaded from: classes12.dex */
public final class CacheBuilder<K, V> {
    private static final int DEFAULT_CONCURRENCY_LEVEL = 4;
    private static final int DEFAULT_EXPIRATION_NANOS = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final int DEFAULT_REFRESH_NANOS = 0;
    static final int UNSET_INT = -1;
    Equivalence<Object> keyEquivalence;
    LocalCache.Strength keyStrength;
    RemovalListener<? super K, ? super V> removalListener;
    Ticker ticker;
    Equivalence<Object> valueEquivalence;
    LocalCache.Strength valueStrength;
    Weigher<? super K, ? super V> weigher;
    static final Supplier<? extends AbstractCache.StatsCounter> NULL_STATS_COUNTER = Suppliers.ofInstance(new AbstractCache.StatsCounter() { // from class: com.google.common.cache.CacheBuilder.1
        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public void recordEviction() {
        }

        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public void recordHits(int i2) {
        }

        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public void recordLoadException(long j2) {
        }

        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public void recordLoadSuccess(long j2) {
        }

        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public void recordMisses(int i2) {
        }

        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public CacheStats snapshot() {
            return CacheBuilder.EMPTY_STATS;
        }
    });
    static final CacheStats EMPTY_STATS = new CacheStats(0, 0, 0, 0, 0, 0);
    static final Supplier<AbstractCache.StatsCounter> CACHE_STATS_COUNTER = new Supplier<AbstractCache.StatsCounter>() { // from class: com.google.common.cache.CacheBuilder.2
        @Override // com.google.common.base.Supplier
        public AbstractCache.StatsCounter get() {
            return new AbstractCache.SimpleStatsCounter();
        }
    };
    static final Ticker NULL_TICKER = new Ticker() { // from class: com.google.common.cache.CacheBuilder.3
        @Override // com.google.common.base.Ticker
        public long read() {
            return 0L;
        }
    };
    private static final Logger logger = Logger.getLogger(CacheBuilder.class.getName());
    boolean strictParsing = true;
    int initialCapacity = -1;
    int concurrencyLevel = -1;
    long maximumSize = -1;
    long maximumWeight = -1;
    long expireAfterWriteNanos = -1;
    long expireAfterAccessNanos = -1;
    long refreshNanos = -1;
    Supplier<? extends AbstractCache.StatsCounter> statsCounterSupplier = NULL_STATS_COUNTER;

    /* loaded from: classes12.dex */
    public enum NullListener implements RemovalListener<Object, Object> {
        INSTANCE;

        @Override // com.google.common.cache.RemovalListener
        public void onRemoval(RemovalNotification<Object, Object> removalNotification) {
        }
    }

    /* loaded from: classes12.dex */
    public enum OneWeigher implements Weigher<Object, Object> {
        INSTANCE;

        @Override // com.google.common.cache.Weigher
        public int weigh(Object obj, Object obj2) {
            return 1;
        }
    }

    private CacheBuilder() {
    }

    private void checkNonLoadingCache() {
        Preconditions.checkState(this.refreshNanos == -1, "refreshAfterWrite requires a LoadingCache");
    }

    private void checkWeightWithWeigher() {
        if (this.weigher == null) {
            Preconditions.checkState(this.maximumWeight == -1, "maximumWeight requires weigher");
        } else if (this.strictParsing) {
            Preconditions.checkState(this.maximumWeight != -1, "weigher requires maximumWeight");
        } else if (this.maximumWeight == -1) {
            logger.log(Level.WARNING, "ignoring weigher specified without maximumWeight");
        }
    }

    @GwtIncompatible
    public static CacheBuilder<Object, Object> from(CacheBuilderSpec cacheBuilderSpec) {
        return cacheBuilderSpec.toCacheBuilder().lenientParsing();
    }

    public static CacheBuilder<Object, Object> newBuilder() {
        return new CacheBuilder<>();
    }

    public <K1 extends K, V1 extends V> LoadingCache<K1, V1> build(CacheLoader<? super K1, V1> cacheLoader) {
        checkWeightWithWeigher();
        return new LocalCache.LocalLoadingCache(this, cacheLoader);
    }

    public CacheBuilder<K, V> concurrencyLevel(int i2) {
        int i3 = this.concurrencyLevel;
        Preconditions.checkState(i3 == -1, "concurrency level was already set to %s", i3);
        Preconditions.checkArgument(i2 > 0);
        this.concurrencyLevel = i2;
        return this;
    }

    public CacheBuilder<K, V> expireAfterAccess(long j2, TimeUnit timeUnit) {
        long j3 = this.expireAfterAccessNanos;
        Preconditions.checkState(j3 == -1, "expireAfterAccess was already set to %s ns", j3);
        Preconditions.checkArgument(j2 >= 0, "duration cannot be negative: %s %s", j2, timeUnit);
        this.expireAfterAccessNanos = timeUnit.toNanos(j2);
        return this;
    }

    public CacheBuilder<K, V> expireAfterWrite(long j2, TimeUnit timeUnit) {
        long j3 = this.expireAfterWriteNanos;
        Preconditions.checkState(j3 == -1, "expireAfterWrite was already set to %s ns", j3);
        Preconditions.checkArgument(j2 >= 0, "duration cannot be negative: %s %s", j2, timeUnit);
        this.expireAfterWriteNanos = timeUnit.toNanos(j2);
        return this;
    }

    public int getConcurrencyLevel() {
        int i2 = this.concurrencyLevel;
        if (i2 == -1) {
            return 4;
        }
        return i2;
    }

    public long getExpireAfterAccessNanos() {
        long j2 = this.expireAfterAccessNanos;
        if (j2 == -1) {
            return 0L;
        }
        return j2;
    }

    public long getExpireAfterWriteNanos() {
        long j2 = this.expireAfterWriteNanos;
        if (j2 == -1) {
            return 0L;
        }
        return j2;
    }

    public int getInitialCapacity() {
        int i2 = this.initialCapacity;
        if (i2 == -1) {
            return 16;
        }
        return i2;
    }

    public Equivalence<Object> getKeyEquivalence() {
        return (Equivalence) MoreObjects.firstNonNull(this.keyEquivalence, getKeyStrength().defaultEquivalence());
    }

    public LocalCache.Strength getKeyStrength() {
        return (LocalCache.Strength) MoreObjects.firstNonNull(this.keyStrength, LocalCache.Strength.STRONG);
    }

    public long getMaximumWeight() {
        if (this.expireAfterWriteNanos == 0 || this.expireAfterAccessNanos == 0) {
            return 0L;
        }
        return this.weigher == null ? this.maximumSize : this.maximumWeight;
    }

    public long getRefreshNanos() {
        long j2 = this.refreshNanos;
        if (j2 == -1) {
            return 0L;
        }
        return j2;
    }

    public <K1 extends K, V1 extends V> RemovalListener<K1, V1> getRemovalListener() {
        return (RemovalListener) MoreObjects.firstNonNull(this.removalListener, NullListener.INSTANCE);
    }

    public Supplier<? extends AbstractCache.StatsCounter> getStatsCounterSupplier() {
        return this.statsCounterSupplier;
    }

    public Ticker getTicker(boolean z) {
        Ticker ticker = this.ticker;
        return ticker != null ? ticker : z ? Ticker.systemTicker() : NULL_TICKER;
    }

    public Equivalence<Object> getValueEquivalence() {
        return (Equivalence) MoreObjects.firstNonNull(this.valueEquivalence, getValueStrength().defaultEquivalence());
    }

    public LocalCache.Strength getValueStrength() {
        return (LocalCache.Strength) MoreObjects.firstNonNull(this.valueStrength, LocalCache.Strength.STRONG);
    }

    public <K1 extends K, V1 extends V> Weigher<K1, V1> getWeigher() {
        return (Weigher) MoreObjects.firstNonNull(this.weigher, OneWeigher.INSTANCE);
    }

    public CacheBuilder<K, V> initialCapacity(int i2) {
        int i3 = this.initialCapacity;
        Preconditions.checkState(i3 == -1, "initial capacity was already set to %s", i3);
        Preconditions.checkArgument(i2 >= 0);
        this.initialCapacity = i2;
        return this;
    }

    boolean isRecordingStats() {
        return this.statsCounterSupplier == CACHE_STATS_COUNTER;
    }

    @GwtIncompatible
    public CacheBuilder<K, V> keyEquivalence(Equivalence<Object> equivalence) {
        Equivalence<Object> equivalence2 = this.keyEquivalence;
        Preconditions.checkState(equivalence2 == null, "key equivalence was already set to %s", equivalence2);
        this.keyEquivalence = (Equivalence) Preconditions.checkNotNull(equivalence);
        return this;
    }

    @GwtIncompatible
    CacheBuilder<K, V> lenientParsing() {
        this.strictParsing = false;
        return this;
    }

    public CacheBuilder<K, V> maximumSize(long j2) {
        long j3 = this.maximumSize;
        Preconditions.checkState(j3 == -1, "maximum size was already set to %s", j3);
        long j4 = this.maximumWeight;
        Preconditions.checkState(j4 == -1, "maximum weight was already set to %s", j4);
        Preconditions.checkState(this.weigher == null, "maximum size can not be combined with weigher");
        Preconditions.checkArgument(j2 >= 0, "maximum size must not be negative");
        this.maximumSize = j2;
        return this;
    }

    @GwtIncompatible
    public CacheBuilder<K, V> maximumWeight(long j2) {
        long j3 = this.maximumWeight;
        Preconditions.checkState(j3 == -1, "maximum weight was already set to %s", j3);
        long j4 = this.maximumSize;
        Preconditions.checkState(j4 == -1, "maximum size was already set to %s", j4);
        this.maximumWeight = j2;
        Preconditions.checkArgument(j2 >= 0, "maximum weight must not be negative");
        return this;
    }

    public CacheBuilder<K, V> recordStats() {
        this.statsCounterSupplier = CACHE_STATS_COUNTER;
        return this;
    }

    @GwtIncompatible
    public CacheBuilder<K, V> refreshAfterWrite(long j2, TimeUnit timeUnit) {
        Preconditions.checkNotNull(timeUnit);
        long j3 = this.refreshNanos;
        Preconditions.checkState(j3 == -1, "refresh was already set to %s ns", j3);
        Preconditions.checkArgument(j2 > 0, "duration must be positive: %s %s", j2, timeUnit);
        this.refreshNanos = timeUnit.toNanos(j2);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @CheckReturnValue
    public <K1 extends K, V1 extends V> CacheBuilder<K1, V1> removalListener(RemovalListener<? super K1, ? super V1> removalListener) {
        Preconditions.checkState(this.removalListener == null);
        this.removalListener = (RemovalListener) Preconditions.checkNotNull(removalListener);
        return this;
    }

    public CacheBuilder<K, V> setKeyStrength(LocalCache.Strength strength) {
        LocalCache.Strength strength2 = this.keyStrength;
        Preconditions.checkState(strength2 == null, "Key strength was already set to %s", strength2);
        this.keyStrength = (LocalCache.Strength) Preconditions.checkNotNull(strength);
        return this;
    }

    public CacheBuilder<K, V> setValueStrength(LocalCache.Strength strength) {
        LocalCache.Strength strength2 = this.valueStrength;
        Preconditions.checkState(strength2 == null, "Value strength was already set to %s", strength2);
        this.valueStrength = (LocalCache.Strength) Preconditions.checkNotNull(strength);
        return this;
    }

    @GwtIncompatible
    public CacheBuilder<K, V> softValues() {
        return setValueStrength(LocalCache.Strength.SOFT);
    }

    public CacheBuilder<K, V> ticker(Ticker ticker) {
        Preconditions.checkState(this.ticker == null);
        this.ticker = (Ticker) Preconditions.checkNotNull(ticker);
        return this;
    }

    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper(this);
        int i2 = this.initialCapacity;
        if (i2 != -1) {
            stringHelper.add("initialCapacity", i2);
        }
        int i3 = this.concurrencyLevel;
        if (i3 != -1) {
            stringHelper.add("concurrencyLevel", i3);
        }
        long j2 = this.maximumSize;
        if (j2 != -1) {
            stringHelper.add("maximumSize", j2);
        }
        long j3 = this.maximumWeight;
        if (j3 != -1) {
            stringHelper.add("maximumWeight", j3);
        }
        if (this.expireAfterWriteNanos != -1) {
            stringHelper.add("expireAfterWrite", this.expireAfterWriteNanos + NotificationStyle.NOTIFICATION_STYLE);
        }
        if (this.expireAfterAccessNanos != -1) {
            stringHelper.add("expireAfterAccess", this.expireAfterAccessNanos + NotificationStyle.NOTIFICATION_STYLE);
        }
        LocalCache.Strength strength = this.keyStrength;
        if (strength != null) {
            stringHelper.add("keyStrength", Ascii.toLowerCase(strength.toString()));
        }
        LocalCache.Strength strength2 = this.valueStrength;
        if (strength2 != null) {
            stringHelper.add("valueStrength", Ascii.toLowerCase(strength2.toString()));
        }
        if (this.keyEquivalence != null) {
            stringHelper.addValue("keyEquivalence");
        }
        if (this.valueEquivalence != null) {
            stringHelper.addValue("valueEquivalence");
        }
        if (this.removalListener != null) {
            stringHelper.addValue("removalListener");
        }
        return stringHelper.toString();
    }

    @GwtIncompatible
    public CacheBuilder<K, V> valueEquivalence(Equivalence<Object> equivalence) {
        Equivalence<Object> equivalence2 = this.valueEquivalence;
        Preconditions.checkState(equivalence2 == null, "value equivalence was already set to %s", equivalence2);
        this.valueEquivalence = (Equivalence) Preconditions.checkNotNull(equivalence);
        return this;
    }

    @GwtIncompatible
    public CacheBuilder<K, V> weakKeys() {
        return setKeyStrength(LocalCache.Strength.WEAK);
    }

    @GwtIncompatible
    public CacheBuilder<K, V> weakValues() {
        return setValueStrength(LocalCache.Strength.WEAK);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible
    public <K1 extends K, V1 extends V> CacheBuilder<K1, V1> weigher(Weigher<? super K1, ? super V1> weigher) {
        Preconditions.checkState(this.weigher == null);
        if (this.strictParsing) {
            long j2 = this.maximumSize;
            Preconditions.checkState(j2 == -1, "weigher can not be combined with maximum size", j2);
        }
        this.weigher = (Weigher) Preconditions.checkNotNull(weigher);
        return this;
    }

    @GwtIncompatible
    public static CacheBuilder<Object, Object> from(String str) {
        return from(CacheBuilderSpec.parse(str));
    }

    public <K1 extends K, V1 extends V> Cache<K1, V1> build() {
        checkWeightWithWeigher();
        checkNonLoadingCache();
        return new LocalCache.LocalManualCache(this);
    }
}
