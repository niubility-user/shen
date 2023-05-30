package com.jingdong.sdk.eldermode.util;

import com.jingdong.sdk.eldermode.helper.IElderModeHelper;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010$\n\u0002\b\u0006\u001a\u001d\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005\u001a'\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0000\u00a2\u0006\u0004\b\u0004\u0010\t\u001a\u0015\u0010\n\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000\u00a2\u0006\u0004\b\n\u0010\u000b\u001a\r\u0010\f\u001a\u00020\u0000\u00a2\u0006\u0004\b\f\u0010\r\u001a\r\u0010\u000e\u001a\u00020\u0000\u00a2\u0006\u0004\b\u000e\u0010\r\")\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u000f8B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u0015"}, d2 = {"", "normalSize", "", "supportMode", "getTextSizeBySupportMode", "(FI)F", "", "supportElderMode", "supportLargeSize", "(FZZ)F", "getElderTextSize", "(F)F", "getMajorTextSize", "()F", "getSecondaryTextSize", "", "ELDER_SIZE_MAP$delegate", "Lkotlin/Lazy;", "getELDER_SIZE_MAP$JDElderModeUtils__JDElderModeTextSizeUtilsKt", "()Ljava/util/Map;", "ELDER_SIZE_MAP", "eldermodelib"}, k = 5, mv = {1, 4, 0}, xs = "com/jingdong/sdk/eldermode/util/JDElderModeUtils")
/* loaded from: classes7.dex */
public final /* synthetic */ class JDElderModeUtils__JDElderModeTextSizeUtilsKt {
    private static final Lazy ELDER_SIZE_MAP$delegate;

    static {
        Lazy lazy;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<Map<Float, ? extends Float>>() { // from class: com.jingdong.sdk.eldermode.util.JDElderModeUtils__JDElderModeTextSizeUtilsKt$ELDER_SIZE_MAP$2
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final Map<Float, ? extends Float> invoke() {
                Map<Float, ? extends Float> mapOf;
                Float valueOf = Float.valueOf(18.0f);
                Float valueOf2 = Float.valueOf(16.0f);
                Float valueOf3 = Float.valueOf(14.0f);
                mapOf = MapsKt__MapsKt.mapOf(TuplesKt.to(Float.valueOf(30.0f), Float.valueOf(36.0f)), TuplesKt.to(Float.valueOf(20.0f), Float.valueOf(22.0f)), TuplesKt.to(valueOf, valueOf), TuplesKt.to(Float.valueOf(17.0f), valueOf), TuplesKt.to(valueOf2, valueOf), TuplesKt.to(Float.valueOf(15.0f), valueOf), TuplesKt.to(valueOf3, valueOf2), TuplesKt.to(Float.valueOf(13.0f), valueOf2), TuplesKt.to(Float.valueOf(12.0f), valueOf2), TuplesKt.to(Float.valueOf(11.0f), valueOf3), TuplesKt.to(Float.valueOf(10.0f), valueOf3));
                return mapOf;
            }
        });
        ELDER_SIZE_MAP$delegate = lazy;
    }

    private static final Map<Float, Float> getELDER_SIZE_MAP$JDElderModeUtils__JDElderModeTextSizeUtilsKt() {
        return (Map) ELDER_SIZE_MAP$delegate.getValue();
    }

    public static final float getElderTextSize(float f2) {
        Float f3 = getELDER_SIZE_MAP$JDElderModeUtils__JDElderModeTextSizeUtilsKt().get(Float.valueOf(f2));
        if (f3 == null) {
            f3 = Float.valueOf(f2);
        }
        return f3.floatValue();
    }

    public static final float getMajorTextSize() {
        return 18.0f;
    }

    public static final float getSecondaryTextSize() {
        return 16.0f;
    }

    public static final float getTextSizeBySupportMode(float f2, int i2) {
        return JDElderModeUtils.getTextSizeBySupportMode(f2, JDElderModeUtils.hasFlag(i2, 1), JDElderModeUtils.hasFlag(i2, 4));
    }

    public static final float getTextSizeBySupportMode(float f2, boolean z, boolean z2) {
        IElderModeHelper helper;
        if (z && JDElderModeManager.INSTANCE.isElderMode()) {
            return JDElderModeUtils.getElderTextSize(f2);
        }
        return (!z2 || (helper = JDElderModeManager.INSTANCE.getHelper()) == null) ? f2 : helper.getScaleSize(f2);
    }
}
