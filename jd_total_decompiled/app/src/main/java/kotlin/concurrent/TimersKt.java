package kotlin.concurrent;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\u001a7\u0010\b\u001a\u00020\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0019\b\u0004\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00a2\u0006\u0002\b\u0006H\u0087\b\u00a2\u0006\u0004\b\b\u0010\t\u001a7\u0010\b\u001a\u00020\u0004*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\n2\u0019\b\u0004\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00a2\u0006\u0002\b\u0006H\u0087\b\u00a2\u0006\u0004\b\b\u0010\f\u001a?\u0010\b\u001a\u00020\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u00012\u0019\b\u0004\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00a2\u0006\u0002\b\u0006H\u0087\b\u00a2\u0006\u0004\b\b\u0010\u000e\u001a?\u0010\b\u001a\u00020\u0004*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u00012\u0019\b\u0004\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00a2\u0006\u0002\b\u0006H\u0087\b\u00a2\u0006\u0004\b\b\u0010\u000f\u001a?\u0010\u0010\u001a\u00020\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u00012\u0019\b\u0004\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00a2\u0006\u0002\b\u0006H\u0087\b\u00a2\u0006\u0004\b\u0010\u0010\u000e\u001a?\u0010\u0010\u001a\u00020\u0004*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u00012\u0019\b\u0004\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00a2\u0006\u0002\b\u0006H\u0087\b\u00a2\u0006\u0004\b\u0010\u0010\u000f\u001a!\u0010\u0015\u001a\u00020\u00002\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0001\u00a2\u0006\u0004\b\u0015\u0010\u0016\u001aS\u0010\u0015\u001a\u00020\u00002\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0017\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u00012\u0019\b\u0004\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00a2\u0006\u0002\b\u0006H\u0087\b\u00a2\u0006\u0004\b\u0015\u0010\u0018\u001aQ\u0010\u0015\u001a\u00020\u00002\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u00012\u0019\b\u0004\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00a2\u0006\u0002\b\u0006H\u0087\b\u00a2\u0006\u0004\b\u0015\u0010\u001a\u001aS\u0010\u001b\u001a\u00020\u00002\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0017\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u00012\u0019\b\u0004\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00a2\u0006\u0002\b\u0006H\u0087\b\u00a2\u0006\u0004\b\u001b\u0010\u0018\u001aQ\u0010\u001b\u001a\u00020\u00002\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u00012\u0019\b\u0004\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00a2\u0006\u0002\b\u0006H\u0087\b\u00a2\u0006\u0004\b\u001b\u0010\u001a\u001a+\u0010\u001c\u001a\u00020\u00042\u0019\b\u0004\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00a2\u0006\u0002\b\u0006H\u0087\b\u00a2\u0006\u0004\b\u001c\u0010\u001d\u00a8\u0006\u001e"}, d2 = {"Ljava/util/Timer;", "", "delay", "Lkotlin/Function1;", "Ljava/util/TimerTask;", "", "Lkotlin/ExtensionFunctionType;", "action", "schedule", "(Ljava/util/Timer;JLkotlin/jvm/functions/Function1;)Ljava/util/TimerTask;", "Ljava/util/Date;", "time", "(Ljava/util/Timer;Ljava/util/Date;Lkotlin/jvm/functions/Function1;)Ljava/util/TimerTask;", "period", "(Ljava/util/Timer;JJLkotlin/jvm/functions/Function1;)Ljava/util/TimerTask;", "(Ljava/util/Timer;Ljava/util/Date;JLkotlin/jvm/functions/Function1;)Ljava/util/TimerTask;", "scheduleAtFixedRate", "", "name", "", "daemon", "timer", "(Ljava/lang/String;Z)Ljava/util/Timer;", "initialDelay", "(Ljava/lang/String;ZJJLkotlin/jvm/functions/Function1;)Ljava/util/Timer;", "startAt", "(Ljava/lang/String;ZLjava/util/Date;JLkotlin/jvm/functions/Function1;)Ljava/util/Timer;", "fixedRateTimer", "timerTask", "(Lkotlin/jvm/functions/Function1;)Ljava/util/TimerTask;", "kotlin-stdlib"}, k = 2, mv = {1, 4, 0})
@JvmName(name = "TimersKt")
/* loaded from: classes11.dex */
public final class TimersKt {
    @InlineOnly
    private static final Timer fixedRateTimer(String str, boolean z, long j2, long j3, Function1<? super TimerTask, Unit> function1) {
        Timer timer = timer(str, z);
        timer.scheduleAtFixedRate(new TimersKt$timerTask$1(function1), j2, j3);
        return timer;
    }

    static /* synthetic */ Timer fixedRateTimer$default(String str, boolean z, long j2, long j3, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = null;
        }
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            j2 = 0;
        }
        Timer timer = timer(str, z);
        timer.scheduleAtFixedRate(new TimersKt$timerTask$1(function1), j2, j3);
        return timer;
    }

    @InlineOnly
    private static final TimerTask schedule(@NotNull Timer timer, long j2, Function1<? super TimerTask, Unit> function1) {
        TimersKt$timerTask$1 timersKt$timerTask$1 = new TimersKt$timerTask$1(function1);
        timer.schedule(timersKt$timerTask$1, j2);
        return timersKt$timerTask$1;
    }

    @InlineOnly
    private static final TimerTask scheduleAtFixedRate(@NotNull Timer timer, long j2, long j3, Function1<? super TimerTask, Unit> function1) {
        TimersKt$timerTask$1 timersKt$timerTask$1 = new TimersKt$timerTask$1(function1);
        timer.scheduleAtFixedRate(timersKt$timerTask$1, j2, j3);
        return timersKt$timerTask$1;
    }

    @PublishedApi
    @NotNull
    public static final Timer timer(@Nullable String str, boolean z) {
        return str == null ? new Timer(z) : new Timer(str, z);
    }

    static /* synthetic */ Timer timer$default(String str, boolean z, long j2, long j3, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = null;
        }
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            j2 = 0;
        }
        Timer timer = timer(str, z);
        timer.schedule(new TimersKt$timerTask$1(function1), j2, j3);
        return timer;
    }

    @InlineOnly
    private static final TimerTask timerTask(Function1<? super TimerTask, Unit> function1) {
        return new TimersKt$timerTask$1(function1);
    }

    @InlineOnly
    private static final Timer timer(String str, boolean z, long j2, long j3, Function1<? super TimerTask, Unit> function1) {
        Timer timer = timer(str, z);
        timer.schedule(new TimersKt$timerTask$1(function1), j2, j3);
        return timer;
    }

    @InlineOnly
    private static final Timer fixedRateTimer(String str, boolean z, Date date, long j2, Function1<? super TimerTask, Unit> function1) {
        Timer timer = timer(str, z);
        timer.scheduleAtFixedRate(new TimersKt$timerTask$1(function1), date, j2);
        return timer;
    }

    static /* synthetic */ Timer fixedRateTimer$default(String str, boolean z, Date date, long j2, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = null;
        }
        if ((i2 & 2) != 0) {
            z = false;
        }
        Timer timer = timer(str, z);
        timer.scheduleAtFixedRate(new TimersKt$timerTask$1(function1), date, j2);
        return timer;
    }

    @InlineOnly
    private static final TimerTask schedule(@NotNull Timer timer, Date date, Function1<? super TimerTask, Unit> function1) {
        TimersKt$timerTask$1 timersKt$timerTask$1 = new TimersKt$timerTask$1(function1);
        timer.schedule(timersKt$timerTask$1, date);
        return timersKt$timerTask$1;
    }

    @InlineOnly
    private static final TimerTask scheduleAtFixedRate(@NotNull Timer timer, Date date, long j2, Function1<? super TimerTask, Unit> function1) {
        TimersKt$timerTask$1 timersKt$timerTask$1 = new TimersKt$timerTask$1(function1);
        timer.scheduleAtFixedRate(timersKt$timerTask$1, date, j2);
        return timersKt$timerTask$1;
    }

    static /* synthetic */ Timer timer$default(String str, boolean z, Date date, long j2, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = null;
        }
        if ((i2 & 2) != 0) {
            z = false;
        }
        Timer timer = timer(str, z);
        timer.schedule(new TimersKt$timerTask$1(function1), date, j2);
        return timer;
    }

    @InlineOnly
    private static final Timer timer(String str, boolean z, Date date, long j2, Function1<? super TimerTask, Unit> function1) {
        Timer timer = timer(str, z);
        timer.schedule(new TimersKt$timerTask$1(function1), date, j2);
        return timer;
    }

    @InlineOnly
    private static final TimerTask schedule(@NotNull Timer timer, long j2, long j3, Function1<? super TimerTask, Unit> function1) {
        TimersKt$timerTask$1 timersKt$timerTask$1 = new TimersKt$timerTask$1(function1);
        timer.schedule(timersKt$timerTask$1, j2, j3);
        return timersKt$timerTask$1;
    }

    @InlineOnly
    private static final TimerTask schedule(@NotNull Timer timer, Date date, long j2, Function1<? super TimerTask, Unit> function1) {
        TimersKt$timerTask$1 timersKt$timerTask$1 = new TimersKt$timerTask$1(function1);
        timer.schedule(timersKt$timerTask$1, date, j2);
        return timersKt$timerTask$1;
    }
}
