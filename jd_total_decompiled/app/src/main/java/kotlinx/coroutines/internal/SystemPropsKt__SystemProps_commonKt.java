package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.text.StringsKt__StringNumberConversionsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\u001a\u001f\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0000\u00a2\u0006\u0004\b\u0004\u0010\u0005\u001a3\u0010\u0004\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0006H\u0000\u00a2\u0006\u0004\b\u0004\u0010\t\u001a3\u0010\u0004\u001a\u00020\n2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\n2\b\b\u0002\u0010\u0007\u001a\u00020\n2\b\b\u0002\u0010\b\u001a\u00020\nH\u0000\u00a2\u0006\u0004\b\u0004\u0010\u000b\u00a8\u0006\f"}, d2 = {"", "propertyName", "", "defaultValue", "systemProp", "(Ljava/lang/String;Z)Z", "", "minValue", "maxValue", "(Ljava/lang/String;III)I", "", "(Ljava/lang/String;JJJ)J", "kotlinx-coroutines-core"}, k = 5, mv = {1, 4, 0}, xs = "kotlinx/coroutines/internal/SystemPropsKt")
/* loaded from: classes11.dex */
public final /* synthetic */ class SystemPropsKt__SystemProps_commonKt {
    public static final boolean systemProp(@NotNull String str, boolean z) {
        String systemProp = SystemPropsKt.systemProp(str);
        return systemProp != null ? Boolean.parseBoolean(systemProp) : z;
    }

    public static /* synthetic */ int systemProp$default(String str, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 4) != 0) {
            i3 = 1;
        }
        if ((i5 & 8) != 0) {
            i4 = Integer.MAX_VALUE;
        }
        return SystemPropsKt.systemProp(str, i2, i3, i4);
    }

    public static final int systemProp(@NotNull String str, int i2, int i3, int i4) {
        return (int) SystemPropsKt.systemProp(str, i2, i3, i4);
    }

    public static /* synthetic */ long systemProp$default(String str, long j2, long j3, long j4, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            j3 = 1;
        }
        long j5 = j3;
        if ((i2 & 8) != 0) {
            j4 = Long.MAX_VALUE;
        }
        return SystemPropsKt.systemProp(str, j2, j5, j4);
    }

    public static final long systemProp(@NotNull String str, long j2, long j3, long j4) {
        Long longOrNull;
        String systemProp = SystemPropsKt.systemProp(str);
        if (systemProp != null) {
            longOrNull = StringsKt__StringNumberConversionsKt.toLongOrNull(systemProp);
            if (longOrNull == null) {
                throw new IllegalStateException(("System property '" + str + "' has unrecognized value '" + systemProp + '\'').toString());
            }
            long longValue = longOrNull.longValue();
            if (j3 > longValue || j4 < longValue) {
                throw new IllegalStateException(("System property '" + str + "' should be in range " + j3 + ".." + j4 + ", but is '" + longValue + '\'').toString());
            }
            return longValue;
        }
        return j2;
    }
}
