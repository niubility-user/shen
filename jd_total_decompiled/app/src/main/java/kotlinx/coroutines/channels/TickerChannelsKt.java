package kotlinx.coroutines.channels;

import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.amon.router.annotation.AnnoConst;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.EventLoop_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.ObsoleteCoroutinesApi;
import kotlinx.coroutines.TimeSource;
import kotlinx.coroutines.TimeSourceKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a;\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u0007\u00a2\u0006\u0004\b\t\u0010\n\u001a1\u0010\r\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\u000bH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\r\u0010\u000e\u001a1\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\u000bH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000f\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"", "delayMillis", "initialDelayMillis", "Lkotlin/coroutines/CoroutineContext;", AnnoConst.Constructor_Context, "Lkotlinx/coroutines/channels/TickerMode;", "mode", "Lkotlinx/coroutines/channels/ReceiveChannel;", "", RemoteMessageConst.Notification.TICKER, "(JJLkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/channels/TickerMode;)Lkotlinx/coroutines/channels/ReceiveChannel;", "Lkotlinx/coroutines/channels/SendChannel;", "channel", "fixedPeriodTicker", "(JJLkotlinx/coroutines/channels/SendChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fixedDelayTicker", "kotlinx-coroutines-core"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class TickerChannelsKt {

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes11.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TickerMode.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[TickerMode.FIXED_PERIOD.ordinal()] = 1;
            iArr[TickerMode.FIXED_DELAY.ordinal()] = 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x007e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0091 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x008f -> B:14:0x0036). Please submit an issue!!! */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object fixedDelayTicker(long j2, long j3, @NotNull SendChannel<? super Unit> sendChannel, @NotNull Continuation<? super Unit> continuation) {
        TickerChannelsKt$fixedDelayTicker$1 tickerChannelsKt$fixedDelayTicker$1;
        Object coroutine_suspended;
        int i2;
        long j4;
        long j5;
        SendChannel<? super Unit> sendChannel2;
        Unit unit;
        if (continuation instanceof TickerChannelsKt$fixedDelayTicker$1) {
            tickerChannelsKt$fixedDelayTicker$1 = (TickerChannelsKt$fixedDelayTicker$1) continuation;
            int i3 = tickerChannelsKt$fixedDelayTicker$1.label;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                tickerChannelsKt$fixedDelayTicker$1.label = i3 - Integer.MIN_VALUE;
                Object obj = tickerChannelsKt$fixedDelayTicker$1.result;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = tickerChannelsKt$fixedDelayTicker$1.label;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    tickerChannelsKt$fixedDelayTicker$1.J$0 = j2;
                    tickerChannelsKt$fixedDelayTicker$1.J$1 = j3;
                    tickerChannelsKt$fixedDelayTicker$1.L$0 = sendChannel;
                    tickerChannelsKt$fixedDelayTicker$1.label = 1;
                    if (DelayKt.delay(j3, tickerChannelsKt$fixedDelayTicker$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i2 == 1) {
                    sendChannel = (SendChannel) tickerChannelsKt$fixedDelayTicker$1.L$0;
                    j3 = tickerChannelsKt$fixedDelayTicker$1.J$1;
                    j2 = tickerChannelsKt$fixedDelayTicker$1.J$0;
                    ResultKt.throwOnFailure(obj);
                } else if (i2 == 2) {
                    sendChannel2 = (SendChannel) tickerChannelsKt$fixedDelayTicker$1.L$0;
                    j5 = tickerChannelsKt$fixedDelayTicker$1.J$1;
                    j4 = tickerChannelsKt$fixedDelayTicker$1.J$0;
                    ResultKt.throwOnFailure(obj);
                    tickerChannelsKt$fixedDelayTicker$1.J$0 = j4;
                    tickerChannelsKt$fixedDelayTicker$1.J$1 = j5;
                    tickerChannelsKt$fixedDelayTicker$1.L$0 = sendChannel2;
                    tickerChannelsKt$fixedDelayTicker$1.label = 3;
                    if (DelayKt.delay(j4, tickerChannelsKt$fixedDelayTicker$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    sendChannel = sendChannel2;
                    j3 = j5;
                    j2 = j4;
                } else if (i2 != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    sendChannel2 = (SendChannel) tickerChannelsKt$fixedDelayTicker$1.L$0;
                    j5 = tickerChannelsKt$fixedDelayTicker$1.J$1;
                    j4 = tickerChannelsKt$fixedDelayTicker$1.J$0;
                    ResultKt.throwOnFailure(obj);
                    sendChannel = sendChannel2;
                    j3 = j5;
                    j2 = j4;
                }
                unit = Unit.INSTANCE;
                tickerChannelsKt$fixedDelayTicker$1.J$0 = j2;
                tickerChannelsKt$fixedDelayTicker$1.J$1 = j3;
                tickerChannelsKt$fixedDelayTicker$1.L$0 = sendChannel;
                tickerChannelsKt$fixedDelayTicker$1.label = 2;
                if (sendChannel.send(unit, tickerChannelsKt$fixedDelayTicker$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                long j6 = j2;
                sendChannel2 = sendChannel;
                j5 = j3;
                j4 = j6;
                tickerChannelsKt$fixedDelayTicker$1.J$0 = j4;
                tickerChannelsKt$fixedDelayTicker$1.J$1 = j5;
                tickerChannelsKt$fixedDelayTicker$1.L$0 = sendChannel2;
                tickerChannelsKt$fixedDelayTicker$1.label = 3;
                if (DelayKt.delay(j4, tickerChannelsKt$fixedDelayTicker$1) == coroutine_suspended) {
                }
                sendChannel = sendChannel2;
                j3 = j5;
                j2 = j4;
                unit = Unit.INSTANCE;
                tickerChannelsKt$fixedDelayTicker$1.J$0 = j2;
                tickerChannelsKt$fixedDelayTicker$1.J$1 = j3;
                tickerChannelsKt$fixedDelayTicker$1.L$0 = sendChannel;
                tickerChannelsKt$fixedDelayTicker$1.label = 2;
                if (sendChannel.send(unit, tickerChannelsKt$fixedDelayTicker$1) == coroutine_suspended) {
                }
            }
        }
        tickerChannelsKt$fixedDelayTicker$1 = new TickerChannelsKt$fixedDelayTicker$1(continuation);
        Object obj2 = tickerChannelsKt$fixedDelayTicker$1.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = tickerChannelsKt$fixedDelayTicker$1.label;
        if (i2 != 0) {
        }
        unit = Unit.INSTANCE;
        tickerChannelsKt$fixedDelayTicker$1.J$0 = j2;
        tickerChannelsKt$fixedDelayTicker$1.J$1 = j3;
        tickerChannelsKt$fixedDelayTicker$1.L$0 = sendChannel;
        tickerChannelsKt$fixedDelayTicker$1.label = 2;
        if (sendChannel.send(unit, tickerChannelsKt$fixedDelayTicker$1) == coroutine_suspended) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00e0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0178 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0179  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x014a -> B:55:0x0181). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:53:0x0179 -> B:54:0x017c). Please submit an issue!!! */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object fixedPeriodTicker(long j2, long j3, @NotNull SendChannel<? super Unit> sendChannel, @NotNull Continuation<? super Unit> continuation) {
        TickerChannelsKt$fixedPeriodTicker$1 tickerChannelsKt$fixedPeriodTicker$1;
        Object coroutine_suspended;
        int i2;
        SendChannel<? super Unit> sendChannel2;
        long j4;
        long j5;
        Long boxLong;
        long delayToNanos;
        long j6;
        long j7;
        SendChannel<? super Unit> sendChannel3;
        long j8;
        SendChannel<? super Unit> sendChannel4;
        long j9;
        long j10;
        long nanoTime;
        TickerChannelsKt$fixedPeriodTicker$1 tickerChannelsKt$fixedPeriodTicker$12;
        long j11;
        long coerceAtLeast;
        long delayNanosToMillis;
        Long boxLong2;
        SendChannel<? super Unit> sendChannel5;
        Unit unit;
        long j12 = j3;
        if (continuation instanceof TickerChannelsKt$fixedPeriodTicker$1) {
            tickerChannelsKt$fixedPeriodTicker$1 = (TickerChannelsKt$fixedPeriodTicker$1) continuation;
            int i3 = tickerChannelsKt$fixedPeriodTicker$1.label;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                tickerChannelsKt$fixedPeriodTicker$1.label = i3 - Integer.MIN_VALUE;
                Object obj = tickerChannelsKt$fixedPeriodTicker$1.result;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = tickerChannelsKt$fixedPeriodTicker$1.label;
                int i4 = 2;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    TimeSource timeSource = TimeSourceKt.getTimeSource();
                    long nanoTime2 = ((timeSource == null || (boxLong = Boxing.boxLong(timeSource.nanoTime())) == null) ? System.nanoTime() : boxLong.longValue()) + EventLoop_commonKt.delayToNanos(j3);
                    tickerChannelsKt$fixedPeriodTicker$1.J$0 = j2;
                    tickerChannelsKt$fixedPeriodTicker$1.J$1 = j12;
                    sendChannel2 = sendChannel;
                    tickerChannelsKt$fixedPeriodTicker$1.L$0 = sendChannel2;
                    tickerChannelsKt$fixedPeriodTicker$1.J$2 = nanoTime2;
                    tickerChannelsKt$fixedPeriodTicker$1.label = 1;
                    if (DelayKt.delay(j12, tickerChannelsKt$fixedPeriodTicker$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    j4 = nanoTime2;
                    j5 = j2;
                } else if (i2 == 1) {
                    long j13 = tickerChannelsKt$fixedPeriodTicker$1.J$2;
                    long j14 = tickerChannelsKt$fixedPeriodTicker$1.J$1;
                    j5 = tickerChannelsKt$fixedPeriodTicker$1.J$0;
                    ResultKt.throwOnFailure(obj);
                    sendChannel2 = (SendChannel) tickerChannelsKt$fixedPeriodTicker$1.L$0;
                    j12 = j14;
                    j4 = j13;
                } else if (i2 == 2) {
                    j8 = tickerChannelsKt$fixedPeriodTicker$1.J$3;
                    j10 = tickerChannelsKt$fixedPeriodTicker$1.J$2;
                    sendChannel3 = (SendChannel) tickerChannelsKt$fixedPeriodTicker$1.L$0;
                    j7 = tickerChannelsKt$fixedPeriodTicker$1.J$1;
                    j6 = tickerChannelsKt$fixedPeriodTicker$1.J$0;
                    ResultKt.throwOnFailure(obj);
                    TimeSource timeSource2 = TimeSourceKt.getTimeSource();
                    nanoTime = (timeSource2 != null || (boxLong2 = Boxing.boxLong(timeSource2.nanoTime())) == null) ? System.nanoTime() : boxLong2.longValue();
                    tickerChannelsKt$fixedPeriodTicker$12 = tickerChannelsKt$fixedPeriodTicker$1;
                    j11 = j10;
                    coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(j10 - nanoTime, 0L);
                    if (coerceAtLeast != 0) {
                    }
                    j4 = j11;
                    delayNanosToMillis = EventLoop_commonKt.delayNanosToMillis(coerceAtLeast);
                    tickerChannelsKt$fixedPeriodTicker$12.J$0 = j6;
                    tickerChannelsKt$fixedPeriodTicker$12.J$1 = j7;
                    tickerChannelsKt$fixedPeriodTicker$12.L$0 = sendChannel3;
                    tickerChannelsKt$fixedPeriodTicker$12.J$2 = j4;
                    tickerChannelsKt$fixedPeriodTicker$12.J$3 = j8;
                    long j15 = j8;
                    tickerChannelsKt$fixedPeriodTicker$12.J$4 = nanoTime;
                    tickerChannelsKt$fixedPeriodTicker$12.J$5 = coerceAtLeast;
                    tickerChannelsKt$fixedPeriodTicker$12.label = 4;
                    if (DelayKt.delay(delayNanosToMillis, tickerChannelsKt$fixedPeriodTicker$12) != coroutine_suspended) {
                    }
                } else if (i2 == 3) {
                    long j16 = tickerChannelsKt$fixedPeriodTicker$1.J$6;
                    long j17 = tickerChannelsKt$fixedPeriodTicker$1.J$5;
                    long j18 = tickerChannelsKt$fixedPeriodTicker$1.J$4;
                    j8 = tickerChannelsKt$fixedPeriodTicker$1.J$3;
                    j4 = tickerChannelsKt$fixedPeriodTicker$1.J$2;
                    sendChannel3 = (SendChannel) tickerChannelsKt$fixedPeriodTicker$1.L$0;
                    j7 = tickerChannelsKt$fixedPeriodTicker$1.J$1;
                    j6 = tickerChannelsKt$fixedPeriodTicker$1.J$0;
                    ResultKt.throwOnFailure(obj);
                    sendChannel4 = sendChannel3;
                    j9 = j8;
                    j12 = j7;
                    j5 = j6;
                    i4 = 2;
                    delayToNanos = j9;
                    sendChannel5 = sendChannel4;
                    j10 = j4 + delayToNanos;
                    unit = Unit.INSTANCE;
                    tickerChannelsKt$fixedPeriodTicker$1.J$0 = j5;
                    tickerChannelsKt$fixedPeriodTicker$1.J$1 = j12;
                    tickerChannelsKt$fixedPeriodTicker$1.L$0 = sendChannel5;
                    tickerChannelsKt$fixedPeriodTicker$1.J$2 = j10;
                    tickerChannelsKt$fixedPeriodTicker$1.J$3 = delayToNanos;
                    tickerChannelsKt$fixedPeriodTicker$1.label = i4;
                    if (sendChannel5.send(unit, tickerChannelsKt$fixedPeriodTicker$1) != coroutine_suspended) {
                    }
                } else if (i2 != 4) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    long j19 = tickerChannelsKt$fixedPeriodTicker$1.J$5;
                    long j20 = tickerChannelsKt$fixedPeriodTicker$1.J$4;
                    long j21 = tickerChannelsKt$fixedPeriodTicker$1.J$3;
                    j4 = tickerChannelsKt$fixedPeriodTicker$1.J$2;
                    sendChannel3 = (SendChannel) tickerChannelsKt$fixedPeriodTicker$1.L$0;
                    j7 = tickerChannelsKt$fixedPeriodTicker$1.J$1;
                    j6 = tickerChannelsKt$fixedPeriodTicker$1.J$0;
                    ResultKt.throwOnFailure(obj);
                    long j22 = j21;
                    sendChannel4 = sendChannel3;
                    j9 = j22;
                    j12 = j7;
                    j5 = j6;
                    i4 = 2;
                    delayToNanos = j9;
                    sendChannel5 = sendChannel4;
                    j10 = j4 + delayToNanos;
                    unit = Unit.INSTANCE;
                    tickerChannelsKt$fixedPeriodTicker$1.J$0 = j5;
                    tickerChannelsKt$fixedPeriodTicker$1.J$1 = j12;
                    tickerChannelsKt$fixedPeriodTicker$1.L$0 = sendChannel5;
                    tickerChannelsKt$fixedPeriodTicker$1.J$2 = j10;
                    tickerChannelsKt$fixedPeriodTicker$1.J$3 = delayToNanos;
                    tickerChannelsKt$fixedPeriodTicker$1.label = i4;
                    if (sendChannel5.send(unit, tickerChannelsKt$fixedPeriodTicker$1) != coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    sendChannel3 = sendChannel5;
                    long j23 = j12;
                    j8 = delayToNanos;
                    j6 = j5;
                    j7 = j23;
                    TimeSource timeSource22 = TimeSourceKt.getTimeSource();
                    nanoTime = (timeSource22 != null || (boxLong2 = Boxing.boxLong(timeSource22.nanoTime())) == null) ? System.nanoTime() : boxLong2.longValue();
                    tickerChannelsKt$fixedPeriodTicker$12 = tickerChannelsKt$fixedPeriodTicker$1;
                    j11 = j10;
                    coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(j10 - nanoTime, 0L);
                    if (coerceAtLeast != 0 && j8 != 0) {
                        long j24 = j8 - ((nanoTime - j11) % j8);
                        long j25 = nanoTime + j24;
                        long delayNanosToMillis2 = EventLoop_commonKt.delayNanosToMillis(j24);
                        tickerChannelsKt$fixedPeriodTicker$12.J$0 = j6;
                        tickerChannelsKt$fixedPeriodTicker$12.J$1 = j7;
                        tickerChannelsKt$fixedPeriodTicker$12.L$0 = sendChannel3;
                        tickerChannelsKt$fixedPeriodTicker$12.J$2 = j25;
                        tickerChannelsKt$fixedPeriodTicker$12.J$3 = j8;
                        tickerChannelsKt$fixedPeriodTicker$12.J$4 = nanoTime;
                        tickerChannelsKt$fixedPeriodTicker$12.J$5 = coerceAtLeast;
                        tickerChannelsKt$fixedPeriodTicker$12.J$6 = j24;
                        tickerChannelsKt$fixedPeriodTicker$12.label = 3;
                        if (DelayKt.delay(delayNanosToMillis2, tickerChannelsKt$fixedPeriodTicker$12) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        tickerChannelsKt$fixedPeriodTicker$1 = tickerChannelsKt$fixedPeriodTicker$12;
                        j4 = j25;
                        sendChannel4 = sendChannel3;
                        j9 = j8;
                        j12 = j7;
                        j5 = j6;
                        i4 = 2;
                        delayToNanos = j9;
                        sendChannel5 = sendChannel4;
                        j10 = j4 + delayToNanos;
                        unit = Unit.INSTANCE;
                        tickerChannelsKt$fixedPeriodTicker$1.J$0 = j5;
                        tickerChannelsKt$fixedPeriodTicker$1.J$1 = j12;
                        tickerChannelsKt$fixedPeriodTicker$1.L$0 = sendChannel5;
                        tickerChannelsKt$fixedPeriodTicker$1.J$2 = j10;
                        tickerChannelsKt$fixedPeriodTicker$1.J$3 = delayToNanos;
                        tickerChannelsKt$fixedPeriodTicker$1.label = i4;
                        if (sendChannel5.send(unit, tickerChannelsKt$fixedPeriodTicker$1) != coroutine_suspended) {
                        }
                    } else {
                        j4 = j11;
                        delayNanosToMillis = EventLoop_commonKt.delayNanosToMillis(coerceAtLeast);
                        tickerChannelsKt$fixedPeriodTicker$12.J$0 = j6;
                        tickerChannelsKt$fixedPeriodTicker$12.J$1 = j7;
                        tickerChannelsKt$fixedPeriodTicker$12.L$0 = sendChannel3;
                        tickerChannelsKt$fixedPeriodTicker$12.J$2 = j4;
                        tickerChannelsKt$fixedPeriodTicker$12.J$3 = j8;
                        long j152 = j8;
                        tickerChannelsKt$fixedPeriodTicker$12.J$4 = nanoTime;
                        tickerChannelsKt$fixedPeriodTicker$12.J$5 = coerceAtLeast;
                        tickerChannelsKt$fixedPeriodTicker$12.label = 4;
                        if (DelayKt.delay(delayNanosToMillis, tickerChannelsKt$fixedPeriodTicker$12) != coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        j22 = j152;
                        tickerChannelsKt$fixedPeriodTicker$1 = tickerChannelsKt$fixedPeriodTicker$12;
                        sendChannel4 = sendChannel3;
                        j9 = j22;
                        j12 = j7;
                        j5 = j6;
                        i4 = 2;
                        delayToNanos = j9;
                        sendChannel5 = sendChannel4;
                        j10 = j4 + delayToNanos;
                        unit = Unit.INSTANCE;
                        tickerChannelsKt$fixedPeriodTicker$1.J$0 = j5;
                        tickerChannelsKt$fixedPeriodTicker$1.J$1 = j12;
                        tickerChannelsKt$fixedPeriodTicker$1.L$0 = sendChannel5;
                        tickerChannelsKt$fixedPeriodTicker$1.J$2 = j10;
                        tickerChannelsKt$fixedPeriodTicker$1.J$3 = delayToNanos;
                        tickerChannelsKt$fixedPeriodTicker$1.label = i4;
                        if (sendChannel5.send(unit, tickerChannelsKt$fixedPeriodTicker$1) != coroutine_suspended) {
                        }
                    }
                }
                delayToNanos = EventLoop_commonKt.delayToNanos(j5);
                sendChannel5 = sendChannel2;
                j10 = j4 + delayToNanos;
                unit = Unit.INSTANCE;
                tickerChannelsKt$fixedPeriodTicker$1.J$0 = j5;
                tickerChannelsKt$fixedPeriodTicker$1.J$1 = j12;
                tickerChannelsKt$fixedPeriodTicker$1.L$0 = sendChannel5;
                tickerChannelsKt$fixedPeriodTicker$1.J$2 = j10;
                tickerChannelsKt$fixedPeriodTicker$1.J$3 = delayToNanos;
                tickerChannelsKt$fixedPeriodTicker$1.label = i4;
                if (sendChannel5.send(unit, tickerChannelsKt$fixedPeriodTicker$1) != coroutine_suspended) {
                }
            }
        }
        tickerChannelsKt$fixedPeriodTicker$1 = new TickerChannelsKt$fixedPeriodTicker$1(continuation);
        Object obj2 = tickerChannelsKt$fixedPeriodTicker$1.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = tickerChannelsKt$fixedPeriodTicker$1.label;
        int i42 = 2;
        if (i2 != 0) {
        }
        delayToNanos = EventLoop_commonKt.delayToNanos(j5);
        sendChannel5 = sendChannel2;
        j10 = j4 + delayToNanos;
        unit = Unit.INSTANCE;
        tickerChannelsKt$fixedPeriodTicker$1.J$0 = j5;
        tickerChannelsKt$fixedPeriodTicker$1.J$1 = j12;
        tickerChannelsKt$fixedPeriodTicker$1.L$0 = sendChannel5;
        tickerChannelsKt$fixedPeriodTicker$1.J$2 = j10;
        tickerChannelsKt$fixedPeriodTicker$1.J$3 = delayToNanos;
        tickerChannelsKt$fixedPeriodTicker$1.label = i42;
        if (sendChannel5.send(unit, tickerChannelsKt$fixedPeriodTicker$1) != coroutine_suspended) {
        }
    }

    @ObsoleteCoroutinesApi
    @NotNull
    public static final ReceiveChannel<Unit> ticker(long j2, long j3, @NotNull CoroutineContext coroutineContext, @NotNull TickerMode tickerMode) {
        if (!(j2 >= 0)) {
            throw new IllegalArgumentException(("Expected non-negative delay, but has " + j2 + " ms").toString());
        }
        if (j3 >= 0) {
            return ProduceKt.produce(GlobalScope.INSTANCE, Dispatchers.getUnconfined().plus(coroutineContext), 0, new TickerChannelsKt$ticker$3(tickerMode, j2, j3, null));
        }
        throw new IllegalArgumentException(("Expected non-negative initial delay, but has " + j3 + " ms").toString());
    }

    public static /* synthetic */ ReceiveChannel ticker$default(long j2, long j3, CoroutineContext coroutineContext, TickerMode tickerMode, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            j3 = j2;
        }
        if ((i2 & 4) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i2 & 8) != 0) {
            tickerMode = TickerMode.FIXED_PERIOD;
        }
        return ticker(j2, j3, coroutineContext, tickerMode);
    }
}
