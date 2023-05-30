package com.jingdong.common.utils.text;

import android.content.Context;
import com.jingdong.amon.router.annotation.AnnoConst;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\t\b\u00c0\u0002\u0018\u00002\u00020\u0001:\u0001\u0010B\t\b\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0007\u00a2\u0006\u0004\b\u0006\u0010\u0007R)\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\b8B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0011"}, d2 = {"Lcom/jingdong/common/utils/text/TextScaleSizeHelper;", "", "Landroid/content/Context;", AnnoConst.Constructor_Context, "", "originalSize", "dynamicFontSize", "(Landroid/content/Context;F)F", "", "sizeMap$delegate", "Lkotlin/Lazy;", "getSizeMap", "()Ljava/util/Map;", "sizeMap", "<init>", "()V", "OriginalSize", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class TextScaleSizeHelper {
    public static final TextScaleSizeHelper INSTANCE = new TextScaleSizeHelper();

    /* renamed from: sizeMap$delegate  reason: from kotlin metadata */
    private static final Lazy sizeMap;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0011\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0005\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0004R\u0016\u0010\u0006\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0004R\u0016\u0010\u0007\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0004R\u0016\u0010\b\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0004R\u0016\u0010\t\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\t\u0010\u0004R\u0016\u0010\n\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\n\u0010\u0004R\u0016\u0010\u000b\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\u0004R\u0016\u0010\f\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\f\u0010\u0004R\u0016\u0010\r\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\r\u0010\u0004R\u0016\u0010\u000e\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u0004R\u0016\u0010\u000f\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0004R\u0016\u0010\u0010\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0004\u00a8\u0006\u0013"}, d2 = {"Lcom/jingdong/common/utils/text/TextScaleSizeHelper$OriginalSize;", "", "", "PX18", "F", "PX30", "PX28", "PX36", "PX40", "PX24", "PX32", "PX60", "PX16", "PX20", "PX22", "PX34", "PX26", "<init>", "()V", "personallib"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes6.dex */
    public static final class OriginalSize {
        public static final OriginalSize INSTANCE = new OriginalSize();
        public static final float PX16 = 16.0f;
        public static final float PX18 = 18.0f;
        public static final float PX20 = 20.0f;
        public static final float PX22 = 22.0f;
        public static final float PX24 = 24.0f;
        public static final float PX26 = 26.0f;
        public static final float PX28 = 28.0f;
        public static final float PX30 = 30.0f;
        public static final float PX32 = 32.0f;
        public static final float PX34 = 34.0f;
        public static final float PX36 = 36.0f;
        public static final float PX40 = 40.0f;
        public static final float PX60 = 60.0f;

        private OriginalSize() {
        }
    }

    static {
        Lazy lazy;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<Map<Float, ? extends Float>>() { // from class: com.jingdong.common.utils.text.TextScaleSizeHelper$sizeMap$2
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final Map<Float, ? extends Float> invoke() {
                Map<Float, ? extends Float> mapOf;
                Float valueOf = Float.valueOf(16.0f);
                Float valueOf2 = Float.valueOf(20.0f);
                Float valueOf3 = Float.valueOf(18.0f);
                Float valueOf4 = Float.valueOf(22.0f);
                Float valueOf5 = Float.valueOf(24.0f);
                Float valueOf6 = Float.valueOf(26.0f);
                Float valueOf7 = Float.valueOf(28.0f);
                Float valueOf8 = Float.valueOf(32.0f);
                Float valueOf9 = Float.valueOf(34.0f);
                Float valueOf10 = Float.valueOf(30.0f);
                Float valueOf11 = Float.valueOf(36.0f);
                Float valueOf12 = Float.valueOf(40.0f);
                mapOf = MapsKt__MapsKt.mapOf(TuplesKt.to(valueOf, valueOf2), TuplesKt.to(valueOf3, valueOf4), TuplesKt.to(valueOf2, valueOf5), TuplesKt.to(valueOf4, valueOf6), TuplesKt.to(valueOf5, valueOf7), TuplesKt.to(valueOf6, valueOf8), TuplesKt.to(valueOf7, valueOf9), TuplesKt.to(valueOf10, valueOf11), TuplesKt.to(valueOf8, Float.valueOf(38.0f)), TuplesKt.to(valueOf9, valueOf12), TuplesKt.to(valueOf11, Float.valueOf(44.0f)), TuplesKt.to(valueOf12, Float.valueOf(48.0f)), TuplesKt.to(Float.valueOf(60.0f), Float.valueOf(72.0f)));
                return mapOf;
            }
        });
        sizeMap = lazy;
    }

    private TextScaleSizeHelper() {
    }

    @JvmStatic
    public static final float dynamicFontSize(@NotNull Context context, float originalSize) {
        String textSizeScaleMode = TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode();
        int hashCode = textSizeScaleMode.hashCode();
        if (hashCode != 102742843) {
            if (hashCode != 1312628413 || textSizeScaleMode.equals(ScaleModeConstants.TEXT_SCALE_MODE_STANDARD)) {
                return originalSize;
            }
        } else if (textSizeScaleMode.equals(ScaleModeConstants.TEXT_SCALE_MODE_LARGE)) {
            Float f2 = INSTANCE.getSizeMap().get(Float.valueOf(originalSize));
            if (f2 == null) {
                f2 = Float.valueOf(originalSize);
            }
            return f2.floatValue();
        }
        return originalSize;
    }

    private final Map<Float, Float> getSizeMap() {
        return (Map) sizeMap.getValue();
    }
}
