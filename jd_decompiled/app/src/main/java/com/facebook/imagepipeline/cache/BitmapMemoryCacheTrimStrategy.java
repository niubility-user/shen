package com.facebook.imagepipeline.cache;

import android.os.Build;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.imagepipeline.cache.MemoryCache;

/* loaded from: classes.dex */
public class BitmapMemoryCacheTrimStrategy implements MemoryCache.CacheTrimStrategy {
    private static final String TAG = "BitmapMemoryCacheTrimStrategy";

    /* renamed from: com.facebook.imagepipeline.cache.BitmapMemoryCacheTrimStrategy$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$common$memory$MemoryTrimType;

        static {
            int[] iArr = new int[MemoryTrimType.values().length];
            $SwitchMap$com$facebook$common$memory$MemoryTrimType = iArr;
            try {
                iArr[MemoryTrimType.OnCloseToDalvikHeapLimit.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$common$memory$MemoryTrimType[MemoryTrimType.OnAppBackgrounded.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$common$memory$MemoryTrimType[MemoryTrimType.OnSystemMemoryCriticallyLowWhileAppInForeground.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$common$memory$MemoryTrimType[MemoryTrimType.OnSystemLowMemoryWhileAppInForeground.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$facebook$common$memory$MemoryTrimType[MemoryTrimType.OnSystemLowMemoryWhileAppInBackground.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache.CacheTrimStrategy
    public double getTrimRatio(MemoryTrimType memoryTrimType) {
        int i2 = AnonymousClass1.$SwitchMap$com$facebook$common$memory$MemoryTrimType[memoryTrimType.ordinal()];
        if (i2 == 1) {
            if (Build.VERSION.SDK_INT >= 21) {
                return MemoryTrimType.OnCloseToDalvikHeapLimit.getSuggestedTrimRatio();
            }
            return 0.0d;
        } else if (i2 == 2 || i2 == 3 || i2 == 4 || i2 == 5) {
            return 1.0d;
        } else {
            FLog.wtf(TAG, "unknown trim type: %s", memoryTrimType);
            return 0.0d;
        }
    }
}
