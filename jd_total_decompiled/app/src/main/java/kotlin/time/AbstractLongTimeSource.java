package kotlin.time;

import com.jingdong.jdsdk.auraSetting.AuraConstants;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import tv.danmaku.ijk.media.player.IMediaPlayer;

@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b'\u0018\u00002\u00020\u0001:\u0001\u0010B\u0013\u0012\n\u0010\n\u001a\u00060\bj\u0002`\t\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0003\u001a\u00020\u0002H$\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0007R \u0010\n\u001a\u00060\bj\u0002`\t8\u0004@\u0004X\u0084\u0004\u00a2\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0011"}, d2 = {"Lkotlin/time/AbstractLongTimeSource;", "Lkotlin/time/TimeSource;", "", "read", "()J", "Lkotlin/time/TimeMark;", "markNow", "()Lkotlin/time/TimeMark;", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "unit", "Ljava/util/concurrent/TimeUnit;", "getUnit", "()Ljava/util/concurrent/TimeUnit;", "<init>", "(Ljava/util/concurrent/TimeUnit;)V", "LongTimeMark", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
@ExperimentalTime
/* loaded from: classes11.dex */
public abstract class AbstractLongTimeSource implements TimeSource {
    @NotNull
    private final TimeUnit unit;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\"\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0012\u0010\u0003\u001a\u00020\u0002H\u0016\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u001b\u0010\b\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0002H\u0096\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0016\u0010\n\u001a\u00020\t8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0016\u0010\r\u001a\u00020\f8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0019\u0010\u000f\u001a\u00020\u00028\u0002@\u0002X\u0082\u0004\u00f8\u0001\u0000\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0013"}, d2 = {"Lkotlin/time/AbstractLongTimeSource$LongTimeMark;", "Lkotlin/time/TimeMark;", "Lkotlin/time/Duration;", "elapsedNow", "()D", "duration", "plus-LRDsOJo", "(D)Lkotlin/time/TimeMark;", "plus", "", "startedAt", "J", "Lkotlin/time/AbstractLongTimeSource;", "timeSource", "Lkotlin/time/AbstractLongTimeSource;", IMediaPlayer.OnNativeInvokeListener.ARG_OFFSET, AuraConstants.MESSAGE_COUPON_TYPE_WILL_EXPIRE, "<init>", "(JLkotlin/time/AbstractLongTimeSource;DLkotlin/jvm/internal/DefaultConstructorMarker;)V", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes11.dex */
    public static final class LongTimeMark extends TimeMark {
        private final double offset;
        private final long startedAt;
        private final AbstractLongTimeSource timeSource;

        private LongTimeMark(long j2, AbstractLongTimeSource abstractLongTimeSource, double d) {
            this.startedAt = j2;
            this.timeSource = abstractLongTimeSource;
            this.offset = d;
        }

        @Override // kotlin.time.TimeMark
        public double elapsedNow() {
            return Duration.m1188minusLRDsOJo(DurationKt.toDuration(this.timeSource.getReading() - this.startedAt, this.timeSource.getUnit()), this.offset);
        }

        @Override // kotlin.time.TimeMark
        @NotNull
        /* renamed from: plus-LRDsOJo */
        public TimeMark mo1162plusLRDsOJo(double duration) {
            return new LongTimeMark(this.startedAt, this.timeSource, Duration.m1189plusLRDsOJo(this.offset, duration), null);
        }

        public /* synthetic */ LongTimeMark(long j2, AbstractLongTimeSource abstractLongTimeSource, double d, DefaultConstructorMarker defaultConstructorMarker) {
            this(j2, abstractLongTimeSource, d);
        }
    }

    public AbstractLongTimeSource(@NotNull TimeUnit timeUnit) {
        this.unit = timeUnit;
    }

    @NotNull
    public final TimeUnit getUnit() {
        return this.unit;
    }

    @Override // kotlin.time.TimeSource
    @NotNull
    public TimeMark markNow() {
        return new LongTimeMark(getReading(), this, Duration.INSTANCE.getZERO(), null);
    }

    /* renamed from: read */
    protected abstract long getReading();
}
