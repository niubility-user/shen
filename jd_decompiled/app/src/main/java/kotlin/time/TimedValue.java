package kotlin.time;

import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import com.jingdong.jdsdk.auraSetting.AuraConstants;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tv.danmaku.ijk.media.pha.JDHPlayerJSEvent;

@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\u0087\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u001a\u0012\u0006\u0010\b\u001a\u00028\u0000\u0012\u0006\u0010\t\u001a\u00020\u0005\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u0003\u001a\u00028\u0000H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0006\u001a\u00020\u0005H\u00c6\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0006\u0010\u0007J-\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\b\u001a\u00028\u00002\b\b\u0002\u0010\t\u001a\u00020\u0005H\u00c6\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u000e\u001a\u00020\rH\u00d6\u0001\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u0010H\u00d6\u0001\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002H\u00d6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u0016R\u0019\u0010\b\u001a\u00028\u00008\u0006@\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u0017\u001a\u0004\b\u0018\u0010\u0004R\u001c\u0010\t\u001a\u00020\u00058\u0006@\u0006\u00f8\u0001\u0000\u00a2\u0006\f\n\u0004\b\t\u0010\u0019\u001a\u0004\b\u001a\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001d"}, d2 = {"Lkotlin/time/TimedValue;", "T", "", "component1", "()Ljava/lang/Object;", "Lkotlin/time/Duration;", "component2", "()D", "value", "duration", "copy-RFiDyg4", "(Ljava/lang/Object;D)Lkotlin/time/TimedValue;", JDViewKitEventHelper.ActionType_Copy, "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/Object;", "getValue", AuraConstants.MESSAGE_COUPON_TYPE_WILL_EXPIRE, JDHPlayerJSEvent.PLAY_DURATION, "<init>", "(Ljava/lang/Object;DLkotlin/jvm/internal/DefaultConstructorMarker;)V", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
@ExperimentalTime
/* loaded from: classes11.dex */
public final /* data */ class TimedValue<T> {
    private final double duration;
    private final T value;

    private TimedValue(T t, double d) {
        this.value = t;
        this.duration = d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: copy-RFiDyg4$default  reason: not valid java name */
    public static /* synthetic */ TimedValue m1214copyRFiDyg4$default(TimedValue timedValue, Object obj, double d, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            obj = timedValue.value;
        }
        if ((i2 & 2) != 0) {
            d = timedValue.duration;
        }
        return timedValue.m1215copyRFiDyg4(obj, d);
    }

    public final T component1() {
        return this.value;
    }

    /* renamed from: component2  reason: from getter */
    public final double getDuration() {
        return this.duration;
    }

    @NotNull
    /* renamed from: copy-RFiDyg4  reason: not valid java name */
    public final TimedValue<T> m1215copyRFiDyg4(T value, double duration) {
        return new TimedValue<>(value, duration);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            if (other instanceof TimedValue) {
                TimedValue timedValue = (TimedValue) other;
                return Intrinsics.areEqual(this.value, timedValue.value) && Double.compare(this.duration, timedValue.duration) == 0;
            }
            return false;
        }
        return true;
    }

    public final double getDuration() {
        return this.duration;
    }

    public final T getValue() {
        return this.value;
    }

    public int hashCode() {
        T t = this.value;
        int hashCode = t != null ? t.hashCode() : 0;
        long doubleToLongBits = Double.doubleToLongBits(this.duration);
        return (hashCode * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
    }

    @NotNull
    public String toString() {
        return "TimedValue(value=" + this.value + ", duration=" + Duration.m1203toStringimpl(this.duration) + ")";
    }

    public /* synthetic */ TimedValue(Object obj, double d, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, d);
    }
}
