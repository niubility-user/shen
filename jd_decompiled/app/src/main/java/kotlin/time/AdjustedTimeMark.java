package kotlin.time;

import com.jingdong.jdsdk.auraSetting.AuraConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0003\u0018\u00002\u00020\u0001B\u001a\u0012\u0006\u0010\f\u001a\u00020\u0001\u0012\u0006\u0010\t\u001a\u00020\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0012\u0010\u0003\u001a\u00020\u0002H\u0016\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u001b\u0010\b\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0002H\u0096\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u001c\u0010\t\u001a\u00020\u00028\u0006@\u0006\u00f8\u0001\u0000\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\u0004R\u0019\u0010\f\u001a\u00020\u00018\u0006@\u0006\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0012"}, d2 = {"Lkotlin/time/AdjustedTimeMark;", "Lkotlin/time/TimeMark;", "Lkotlin/time/Duration;", "elapsedNow", "()D", "duration", "plus-LRDsOJo", "(D)Lkotlin/time/TimeMark;", "plus", "adjustment", AuraConstants.MESSAGE_COUPON_TYPE_WILL_EXPIRE, "getAdjustment", "mark", "Lkotlin/time/TimeMark;", "getMark", "()Lkotlin/time/TimeMark;", "<init>", "(Lkotlin/time/TimeMark;DLkotlin/jvm/internal/DefaultConstructorMarker;)V", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
@ExperimentalTime
/* loaded from: classes11.dex */
public final class AdjustedTimeMark extends TimeMark {
    private final double adjustment;
    @NotNull
    private final TimeMark mark;

    private AdjustedTimeMark(TimeMark timeMark, double d) {
        this.mark = timeMark;
        this.adjustment = d;
    }

    @Override // kotlin.time.TimeMark
    public double elapsedNow() {
        return Duration.m1188minusLRDsOJo(this.mark.elapsedNow(), this.adjustment);
    }

    public final double getAdjustment() {
        return this.adjustment;
    }

    @NotNull
    public final TimeMark getMark() {
        return this.mark;
    }

    @Override // kotlin.time.TimeMark
    @NotNull
    /* renamed from: plus-LRDsOJo */
    public TimeMark mo1162plusLRDsOJo(double duration) {
        return new AdjustedTimeMark(this.mark, Duration.m1189plusLRDsOJo(this.adjustment, duration), null);
    }

    public /* synthetic */ AdjustedTimeMark(TimeMark timeMark, double d, DefaultConstructorMarker defaultConstructorMarker) {
        this(timeMark, d);
    }
}
