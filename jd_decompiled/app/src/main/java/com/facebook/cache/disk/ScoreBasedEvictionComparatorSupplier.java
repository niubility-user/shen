package com.facebook.cache.disk;

import com.facebook.cache.disk.DiskStorage;
import com.facebook.common.internal.VisibleForTesting;

/* loaded from: classes.dex */
public class ScoreBasedEvictionComparatorSupplier implements EntryEvictionComparatorSupplier {
    private final float mAgeWeight;
    private final float mSizeWeight;

    public ScoreBasedEvictionComparatorSupplier(float f2, float f3) {
        this.mAgeWeight = f2;
        this.mSizeWeight = f3;
    }

    @VisibleForTesting
    float calculateScore(DiskStorage.Entry entry, long j2) {
        return (this.mAgeWeight * ((float) (j2 - entry.getTimestamp()))) + (this.mSizeWeight * ((float) entry.getSize()));
    }

    @Override // com.facebook.cache.disk.EntryEvictionComparatorSupplier
    public EntryEvictionComparator get() {
        return new EntryEvictionComparator() { // from class: com.facebook.cache.disk.ScoreBasedEvictionComparatorSupplier.1
            long now = System.currentTimeMillis();

            @Override // java.util.Comparator
            public int compare(DiskStorage.Entry entry, DiskStorage.Entry entry2) {
                float calculateScore = ScoreBasedEvictionComparatorSupplier.this.calculateScore(entry, this.now);
                float calculateScore2 = ScoreBasedEvictionComparatorSupplier.this.calculateScore(entry2, this.now);
                if (calculateScore < calculateScore2) {
                    return 1;
                }
                return calculateScore2 == calculateScore ? 0 : -1;
            }
        };
    }
}
