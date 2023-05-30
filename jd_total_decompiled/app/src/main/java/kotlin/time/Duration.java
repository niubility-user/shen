package kotlin.time;

import com.jd.aips.verify.tracker.VerifyTracker;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.jdsdk.auraSetting.AuraConstants;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.DoubleCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u0000\n\u0002\b$\b\u0087@\u0018\u0000 v2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001vB\u0014\b\u0000\u0012\u0006\u0010J\u001a\u00020\u0010\u00f8\u0001\u0000\u00a2\u0006\u0004\bu\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u0000H\u0086\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0000H\u0086\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\n\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0000H\u0086\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\t\u0010\u0007J\u001b\u0010\u000f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u000bH\u0086\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001b\u0010\u000f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0010H\u0086\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\r\u0010\u0007J\u001b\u0010\u0012\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u000bH\u0086\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0011\u0010\u000eJ\u001b\u0010\u0012\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0010H\u0086\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0011\u0010\u0007J\u001b\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u0000H\u0086\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0013\u0010\u0007J\r\u0010\u0017\u001a\u00020\u0014\u00a2\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0019\u001a\u00020\u0014\u00a2\u0006\u0004\b\u0018\u0010\u0016J\r\u0010\u001b\u001a\u00020\u0014\u00a2\u0006\u0004\b\u001a\u0010\u0016J\r\u0010\u001d\u001a\u00020\u0014\u00a2\u0006\u0004\b\u001c\u0010\u0016J\u001b\u0010 \u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0000H\u0096\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u008d\u0001\u0010-\u001a\u00028\u0000\"\u0004\b\u0000\u0010!2u\u0010*\u001aq\u0012\u0013\u0012\u00110\u000b\u00a2\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(%\u0012\u0013\u0012\u00110\u000b\u00a2\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(&\u0012\u0013\u0012\u00110\u000b\u00a2\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b('\u0012\u0013\u0012\u00110\u000b\u00a2\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b((\u0012\u0013\u0012\u00110\u000b\u00a2\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b()\u0012\u0004\u0012\u00028\u00000\"H\u0086\b\u00a2\u0006\u0004\b+\u0010,Jx\u0010-\u001a\u00028\u0000\"\u0004\b\u0000\u0010!2`\u0010*\u001a\\\u0012\u0013\u0012\u00110\u000b\u00a2\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(&\u0012\u0013\u0012\u00110\u000b\u00a2\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b('\u0012\u0013\u0012\u00110\u000b\u00a2\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b((\u0012\u0013\u0012\u00110\u000b\u00a2\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b()\u0012\u0004\u0012\u00028\u00000.H\u0086\b\u00a2\u0006\u0004\b+\u0010/Jc\u0010-\u001a\u00028\u0000\"\u0004\b\u0000\u0010!2K\u0010*\u001aG\u0012\u0013\u0012\u00110\u000b\u00a2\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b('\u0012\u0013\u0012\u00110\u000b\u00a2\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b((\u0012\u0013\u0012\u00110\u000b\u00a2\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b()\u0012\u0004\u0012\u00028\u000000H\u0086\b\u00a2\u0006\u0004\b+\u00101JN\u0010-\u001a\u00028\u0000\"\u0004\b\u0000\u0010!26\u0010*\u001a2\u0012\u0013\u0012\u001103\u00a2\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b((\u0012\u0013\u0012\u00110\u000b\u00a2\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b()\u0012\u0004\u0012\u00028\u000002H\u0086\b\u00a2\u0006\u0004\b+\u00104J\u0019\u0010:\u001a\u00020\u00102\n\u00107\u001a\u000605j\u0002`6\u00a2\u0006\u0004\b8\u00109J\u0019\u0010=\u001a\u0002032\n\u00107\u001a\u000605j\u0002`6\u00a2\u0006\u0004\b;\u0010<J\u0019\u0010@\u001a\u00020\u000b2\n\u00107\u001a\u000605j\u0002`6\u00a2\u0006\u0004\b>\u0010?J\r\u0010C\u001a\u000203\u00a2\u0006\u0004\bA\u0010BJ\r\u0010E\u001a\u000203\u00a2\u0006\u0004\bD\u0010BJ\u000f\u0010I\u001a\u00020FH\u0016\u00a2\u0006\u0004\bG\u0010HJ\u0017\u0010L\u001a\u00020\u000b2\u0006\u0010J\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\bK\u0010\u001fJ#\u0010I\u001a\u00020F2\n\u00107\u001a\u000605j\u0002`62\b\b\u0002\u0010M\u001a\u00020\u000b\u00a2\u0006\u0004\bG\u0010NJ\r\u0010P\u001a\u00020F\u00a2\u0006\u0004\bO\u0010HJ\u0010\u0010Q\u001a\u00020\u000bH\u00d6\u0001\u00a2\u0006\u0004\bQ\u0010RJ\u001a\u0010T\u001a\u00020\u00142\b\u0010\u0005\u001a\u0004\u0018\u00010SH\u00d6\u0003\u00a2\u0006\u0004\bT\u0010UR\u0013\u0010W\u001a\u00020\u00108F@\u0006\u00a2\u0006\u0006\u001a\u0004\bV\u0010\u0003R\u0016\u0010J\u001a\u00020\u00108\u0000@\u0000X\u0080\u0004\u00a2\u0006\u0006\n\u0004\bJ\u0010XR\u0013\u0010Z\u001a\u00020\u00108F@\u0006\u00a2\u0006\u0006\u001a\u0004\bY\u0010\u0003R\u0013\u0010\\\u001a\u00020\u00108F@\u0006\u00a2\u0006\u0006\u001a\u0004\b[\u0010\u0003R\u001c\u0010a\u001a\u00020\u000b8@@\u0001X\u0081\u0004\u00a2\u0006\f\u0012\u0004\b_\u0010`\u001a\u0004\b]\u0010^R\u001c\u0010d\u001a\u00020\u000b8@@\u0001X\u0081\u0004\u00a2\u0006\f\u0012\u0004\bc\u0010`\u001a\u0004\bb\u0010^R\u0016\u0010f\u001a\u00020\u00008F@\u0006\u00f8\u0001\u0000\u00a2\u0006\u0006\u001a\u0004\be\u0010\u0003R\u0013\u0010h\u001a\u00020\u00108F@\u0006\u00a2\u0006\u0006\u001a\u0004\bg\u0010\u0003R\u0013\u0010j\u001a\u00020\u00108F@\u0006\u00a2\u0006\u0006\u001a\u0004\bi\u0010\u0003R\u001c\u0010m\u001a\u00020\u000b8@@\u0001X\u0081\u0004\u00a2\u0006\f\u0012\u0004\bl\u0010`\u001a\u0004\bk\u0010^R\u0013\u0010o\u001a\u00020\u00108F@\u0006\u00a2\u0006\u0006\u001a\u0004\bn\u0010\u0003R\u0013\u0010q\u001a\u00020\u00108F@\u0006\u00a2\u0006\u0006\u001a\u0004\bp\u0010\u0003R\u001c\u0010t\u001a\u00020\u000b8@@\u0001X\u0081\u0004\u00a2\u0006\f\u0012\u0004\bs\u0010`\u001a\u0004\br\u0010^\u00f8\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006w"}, d2 = {"Lkotlin/time/Duration;", "", "unaryMinus-impl", "(D)D", "unaryMinus", "other", "plus-LRDsOJo", "(DD)D", "plus", "minus-LRDsOJo", "minus", "", "scale", "times-impl", "(DI)D", VerifyTracker.KEY_TIMES, "", "div-impl", "div", "div-LRDsOJo", "", "isNegative-impl", "(D)Z", "isNegative", "isPositive-impl", "isPositive", "isInfinite-impl", "isInfinite", "isFinite-impl", "isFinite", "compareTo-LRDsOJo", "(DD)I", "compareTo", "T", "Lkotlin/Function5;", "Lkotlin/ParameterName;", "name", "days", "hours", "minutes", "seconds", "nanoseconds", "action", "toComponents-impl", "(DLkotlin/jvm/functions/Function5;)Ljava/lang/Object;", "toComponents", "Lkotlin/Function4;", "(DLkotlin/jvm/functions/Function4;)Ljava/lang/Object;", "Lkotlin/Function3;", "(DLkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "Lkotlin/Function2;", "", "(DLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "unit", "toDouble-impl", "(DLjava/util/concurrent/TimeUnit;)D", "toDouble", "toLong-impl", "(DLjava/util/concurrent/TimeUnit;)J", "toLong", "toInt-impl", "(DLjava/util/concurrent/TimeUnit;)I", "toInt", "toLongNanoseconds-impl", "(D)J", "toLongNanoseconds", "toLongMilliseconds-impl", "toLongMilliseconds", "", "toString-impl", "(D)Ljava/lang/String;", "toString", "value", "precision-impl", "precision", "decimals", "(DLjava/util/concurrent/TimeUnit;I)Ljava/lang/String;", "toIsoString-impl", "toIsoString", "hashCode", "()I", "", "equals", "(Ljava/lang/Object;)Z", "getInDays-impl", "inDays", AuraConstants.MESSAGE_COUPON_TYPE_WILL_EXPIRE, "getInHours-impl", "inHours", "getInMicroseconds-impl", "inMicroseconds", "getMinutesComponent-impl", "(D)I", "minutesComponent$annotations", "()V", "minutesComponent", "getNanosecondsComponent-impl", "nanosecondsComponent$annotations", "nanosecondsComponent", "getAbsoluteValue-impl", "absoluteValue", "getInMilliseconds-impl", "inMilliseconds", "getInMinutes-impl", "inMinutes", "getHoursComponent-impl", "hoursComponent$annotations", "hoursComponent", "getInSeconds-impl", "inSeconds", "getInNanoseconds-impl", "inNanoseconds", "getSecondsComponent-impl", "secondsComponent$annotations", "secondsComponent", "constructor-impl", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
@ExperimentalTime
/* loaded from: classes11.dex */
public final class Duration implements Comparable<Duration> {
    private final double value;

    /* renamed from: Companion  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final double ZERO = m1165constructorimpl(0.0d);
    private static final double INFINITE = m1165constructorimpl(DoubleCompanionObject.INSTANCE.getPOSITIVE_INFINITY());

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J-\u0010\b\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\n\u0010\u0006\u001a\u00060\u0004j\u0002`\u00052\n\u0010\u0007\u001a\u00060\u0004j\u0002`\u0005\u00a2\u0006\u0004\b\b\u0010\tR\u001c\u0010\u000b\u001a\u00020\n8\u0006@\u0006\u00f8\u0001\u0000\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u00020\n8\u0006@\u0006\u00f8\u0001\u0000\u00a2\u0006\f\n\u0004\b\u000f\u0010\f\u001a\u0004\b\u0010\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0013"}, d2 = {"Lkotlin/time/Duration$Companion;", "", "", "value", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "sourceUnit", "targetUnit", "convert", "(DLjava/util/concurrent/TimeUnit;Ljava/util/concurrent/TimeUnit;)D", "Lkotlin/time/Duration;", "ZERO", AuraConstants.MESSAGE_COUPON_TYPE_WILL_EXPIRE, "getZERO", "()D", "INFINITE", "getINFINITE", "<init>", "()V", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes11.dex */
    public static final class Companion {
        private Companion() {
        }

        public final double convert(double value, @NotNull TimeUnit sourceUnit, @NotNull TimeUnit targetUnit) {
            return DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(value, sourceUnit, targetUnit);
        }

        public final double getINFINITE() {
            return Duration.INFINITE;
        }

        public final double getZERO() {
            return Duration.ZERO;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private /* synthetic */ Duration(double d) {
        this.value = d;
    }

    @NotNull
    /* renamed from: box-impl */
    public static final /* synthetic */ Duration m1163boximpl(double d) {
        return new Duration(d);
    }

    /* renamed from: constructor-impl */
    public static double m1165constructorimpl(double d) {
        return d;
    }

    /* renamed from: div-LRDsOJo */
    public static final double m1166divLRDsOJo(double d, double d2) {
        return d / d2;
    }

    /* renamed from: div-impl */
    public static final double m1168divimpl(double d, int i2) {
        double d2 = i2;
        Double.isNaN(d2);
        return m1165constructorimpl(d / d2);
    }

    /* renamed from: equals-impl */
    public static boolean m1169equalsimpl(double d, @Nullable Object obj) {
        return (obj instanceof Duration) && Double.compare(d, ((Duration) obj).getValue()) == 0;
    }

    /* renamed from: equals-impl0 */
    public static final boolean m1170equalsimpl0(double d, double d2) {
        return Double.compare(d, d2) == 0;
    }

    /* renamed from: getAbsoluteValue-impl */
    public static final double m1171getAbsoluteValueimpl(double d) {
        return m1186isNegativeimpl(d) ? m1206unaryMinusimpl(d) : d;
    }

    /* renamed from: getHoursComponent-impl */
    public static final int m1172getHoursComponentimpl(double d) {
        double m1174getInHoursimpl = m1174getInHoursimpl(d);
        double d2 = 24;
        Double.isNaN(d2);
        return (int) (m1174getInHoursimpl % d2);
    }

    /* renamed from: getInDays-impl */
    public static final double m1173getInDaysimpl(double d) {
        return m1197toDoubleimpl(d, TimeUnit.DAYS);
    }

    /* renamed from: getInHours-impl */
    public static final double m1174getInHoursimpl(double d) {
        return m1197toDoubleimpl(d, TimeUnit.HOURS);
    }

    /* renamed from: getInMicroseconds-impl */
    public static final double m1175getInMicrosecondsimpl(double d) {
        return m1197toDoubleimpl(d, TimeUnit.MICROSECONDS);
    }

    /* renamed from: getInMilliseconds-impl */
    public static final double m1176getInMillisecondsimpl(double d) {
        return m1197toDoubleimpl(d, TimeUnit.MILLISECONDS);
    }

    /* renamed from: getInMinutes-impl */
    public static final double m1177getInMinutesimpl(double d) {
        return m1197toDoubleimpl(d, TimeUnit.MINUTES);
    }

    /* renamed from: getInNanoseconds-impl */
    public static final double m1178getInNanosecondsimpl(double d) {
        return m1197toDoubleimpl(d, TimeUnit.NANOSECONDS);
    }

    /* renamed from: getInSeconds-impl */
    public static final double m1179getInSecondsimpl(double d) {
        return m1197toDoubleimpl(d, TimeUnit.SECONDS);
    }

    /* renamed from: getMinutesComponent-impl */
    public static final int m1180getMinutesComponentimpl(double d) {
        double m1177getInMinutesimpl = m1177getInMinutesimpl(d);
        double d2 = 60;
        Double.isNaN(d2);
        return (int) (m1177getInMinutesimpl % d2);
    }

    /* renamed from: getNanosecondsComponent-impl */
    public static final int m1181getNanosecondsComponentimpl(double d) {
        return (int) (m1178getInNanosecondsimpl(d) % 1.0E9d);
    }

    /* renamed from: getSecondsComponent-impl */
    public static final int m1182getSecondsComponentimpl(double d) {
        double m1179getInSecondsimpl = m1179getInSecondsimpl(d);
        double d2 = 60;
        Double.isNaN(d2);
        return (int) (m1179getInSecondsimpl % d2);
    }

    /* renamed from: hashCode-impl */
    public static int m1183hashCodeimpl(double d) {
        long doubleToLongBits = Double.doubleToLongBits(d);
        return (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
    }

    @PublishedApi
    public static /* synthetic */ void hoursComponent$annotations() {
    }

    /* renamed from: isFinite-impl */
    public static final boolean m1184isFiniteimpl(double d) {
        return (Double.isInfinite(d) || Double.isNaN(d)) ? false : true;
    }

    /* renamed from: isInfinite-impl */
    public static final boolean m1185isInfiniteimpl(double d) {
        return Double.isInfinite(d);
    }

    /* renamed from: isNegative-impl */
    public static final boolean m1186isNegativeimpl(double d) {
        return d < ((double) 0);
    }

    /* renamed from: isPositive-impl */
    public static final boolean m1187isPositiveimpl(double d) {
        return d > ((double) 0);
    }

    /* renamed from: minus-LRDsOJo */
    public static final double m1188minusLRDsOJo(double d, double d2) {
        return m1165constructorimpl(d - d2);
    }

    @PublishedApi
    public static /* synthetic */ void minutesComponent$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void nanosecondsComponent$annotations() {
    }

    /* renamed from: plus-LRDsOJo */
    public static final double m1189plusLRDsOJo(double d, double d2) {
        return m1165constructorimpl(d + d2);
    }

    /* renamed from: precision-impl */
    private static final int m1190precisionimpl(double d, double d2) {
        if (d2 < 1) {
            return 3;
        }
        if (d2 < 10) {
            return 2;
        }
        return d2 < ((double) 100) ? 1 : 0;
    }

    @PublishedApi
    public static /* synthetic */ void secondsComponent$annotations() {
    }

    /* renamed from: times-impl */
    public static final double m1192timesimpl(double d, int i2) {
        double d2 = i2;
        Double.isNaN(d2);
        return m1165constructorimpl(d * d2);
    }

    /* renamed from: toComponents-impl */
    public static final <T> T m1196toComponentsimpl(double d, @NotNull Function5<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> function5) {
        return function5.invoke(Integer.valueOf((int) m1173getInDaysimpl(d)), Integer.valueOf(m1172getHoursComponentimpl(d)), Integer.valueOf(m1180getMinutesComponentimpl(d)), Integer.valueOf(m1182getSecondsComponentimpl(d)), Integer.valueOf(m1181getNanosecondsComponentimpl(d)));
    }

    /* renamed from: toDouble-impl */
    public static final double m1197toDoubleimpl(double d, @NotNull TimeUnit timeUnit) {
        TimeUnit storageUnit;
        storageUnit = DurationKt.getStorageUnit();
        return DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(d, storageUnit, timeUnit);
    }

    /* renamed from: toInt-impl */
    public static final int m1198toIntimpl(double d, @NotNull TimeUnit timeUnit) {
        return (int) m1197toDoubleimpl(d, timeUnit);
    }

    @NotNull
    /* renamed from: toIsoString-impl */
    public static final String m1199toIsoStringimpl(double d) {
        String padStart;
        StringBuilder sb = new StringBuilder();
        if (m1186isNegativeimpl(d)) {
            sb.append('-');
        }
        sb.append("PT");
        double m1171getAbsoluteValueimpl = m1171getAbsoluteValueimpl(d);
        int m1174getInHoursimpl = (int) m1174getInHoursimpl(m1171getAbsoluteValueimpl);
        int m1180getMinutesComponentimpl = m1180getMinutesComponentimpl(m1171getAbsoluteValueimpl);
        int m1182getSecondsComponentimpl = m1182getSecondsComponentimpl(m1171getAbsoluteValueimpl);
        int m1181getNanosecondsComponentimpl = m1181getNanosecondsComponentimpl(m1171getAbsoluteValueimpl);
        boolean z = true;
        boolean z2 = m1174getInHoursimpl != 0;
        boolean z3 = (m1182getSecondsComponentimpl == 0 && m1181getNanosecondsComponentimpl == 0) ? false : true;
        if (m1180getMinutesComponentimpl == 0 && (!z3 || !z2)) {
            z = false;
        }
        if (z2) {
            sb.append(m1174getInHoursimpl);
            sb.append('H');
        }
        if (z) {
            sb.append(m1180getMinutesComponentimpl);
            sb.append('M');
        }
        if (z3 || (!z2 && !z)) {
            sb.append(m1182getSecondsComponentimpl);
            if (m1181getNanosecondsComponentimpl != 0) {
                sb.append(OrderISVUtil.MONEY_DECIMAL_CHAR);
                padStart = StringsKt__StringsKt.padStart(String.valueOf(m1181getNanosecondsComponentimpl), 9, '0');
                if (m1181getNanosecondsComponentimpl % 1000000 == 0) {
                    sb.append((CharSequence) padStart, 0, 3);
                    Intrinsics.checkExpressionValueIsNotNull(sb, "this.append(value, startIndex, endIndex)");
                } else if (m1181getNanosecondsComponentimpl % 1000 == 0) {
                    sb.append((CharSequence) padStart, 0, 6);
                    Intrinsics.checkExpressionValueIsNotNull(sb, "this.append(value, startIndex, endIndex)");
                } else {
                    sb.append(padStart);
                }
            }
            sb.append('S');
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    /* renamed from: toLong-impl */
    public static final long m1200toLongimpl(double d, @NotNull TimeUnit timeUnit) {
        return (long) m1197toDoubleimpl(d, timeUnit);
    }

    /* renamed from: toLongMilliseconds-impl */
    public static final long m1201toLongMillisecondsimpl(double d) {
        return m1200toLongimpl(d, TimeUnit.MILLISECONDS);
    }

    /* renamed from: toLongNanoseconds-impl */
    public static final long m1202toLongNanosecondsimpl(double d) {
        return m1200toLongimpl(d, TimeUnit.NANOSECONDS);
    }

    /* JADX WARN: Removed duplicated region for block: B:136:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x009f  */
    @NotNull
    /* renamed from: toString-impl */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String m1203toStringimpl(double d) {
        TimeUnit timeUnit;
        int i2;
        String formatToExactDecimals;
        if (m1185isInfiniteimpl(d)) {
            return String.valueOf(d);
        }
        if (d == 0.0d) {
            return "0s";
        }
        double m1178getInNanosecondsimpl = m1178getInNanosecondsimpl(m1171getAbsoluteValueimpl(d));
        boolean z = false;
        if (m1178getInNanosecondsimpl < 1.0E-6d) {
            timeUnit = TimeUnit.SECONDS;
        } else {
            if (m1178getInNanosecondsimpl < 1) {
                timeUnit = TimeUnit.NANOSECONDS;
                i2 = 7;
            } else {
                if (m1178getInNanosecondsimpl < 1000.0d) {
                    timeUnit = TimeUnit.NANOSECONDS;
                } else if (m1178getInNanosecondsimpl < 1000000.0d) {
                    timeUnit = TimeUnit.MICROSECONDS;
                } else if (m1178getInNanosecondsimpl < 1.0E9d) {
                    timeUnit = TimeUnit.MILLISECONDS;
                } else if (m1178getInNanosecondsimpl < 1.0E12d) {
                    timeUnit = TimeUnit.SECONDS;
                } else if (m1178getInNanosecondsimpl < 6.0E13d) {
                    timeUnit = TimeUnit.MINUTES;
                } else if (m1178getInNanosecondsimpl < 3.6E15d) {
                    timeUnit = TimeUnit.HOURS;
                } else if (m1178getInNanosecondsimpl < 8.64E20d) {
                    timeUnit = TimeUnit.DAYS;
                } else {
                    timeUnit = TimeUnit.DAYS;
                }
                i2 = 0;
            }
            double m1197toDoubleimpl = m1197toDoubleimpl(d, timeUnit);
            StringBuilder sb = new StringBuilder();
            if (!z) {
                formatToExactDecimals = FormatToDecimalsKt.formatScientific(m1197toDoubleimpl);
            } else if (i2 > 0) {
                formatToExactDecimals = FormatToDecimalsKt.formatUpToDecimals(m1197toDoubleimpl, i2);
            } else {
                formatToExactDecimals = FormatToDecimalsKt.formatToExactDecimals(m1197toDoubleimpl, m1190precisionimpl(d, Math.abs(m1197toDoubleimpl)));
            }
            sb.append(formatToExactDecimals);
            sb.append(DurationUnitKt__DurationUnitKt.shortName(timeUnit));
            return sb.toString();
        }
        i2 = 0;
        z = true;
        double m1197toDoubleimpl2 = m1197toDoubleimpl(d, timeUnit);
        StringBuilder sb2 = new StringBuilder();
        if (!z) {
        }
        sb2.append(formatToExactDecimals);
        sb2.append(DurationUnitKt__DurationUnitKt.shortName(timeUnit));
        return sb2.toString();
    }

    /* renamed from: toString-impl$default */
    public static /* synthetic */ String m1205toStringimpl$default(double d, TimeUnit timeUnit, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        return m1204toStringimpl(d, timeUnit, i2);
    }

    /* renamed from: unaryMinus-impl */
    public static final double m1206unaryMinusimpl(double d) {
        return m1165constructorimpl(-d);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Duration duration) {
        return m1207compareToLRDsOJo(duration.getValue());
    }

    /* renamed from: compareTo-LRDsOJo */
    public int m1207compareToLRDsOJo(double d) {
        return m1164compareToLRDsOJo(this.value, d);
    }

    public boolean equals(Object other) {
        return m1169equalsimpl(this.value, other);
    }

    public int hashCode() {
        return m1183hashCodeimpl(this.value);
    }

    @NotNull
    public String toString() {
        return m1203toStringimpl(this.value);
    }

    /* renamed from: unbox-impl  reason: from getter */
    public final /* synthetic */ double getValue() {
        return this.value;
    }

    /* renamed from: compareTo-LRDsOJo */
    public static int m1164compareToLRDsOJo(double d, double d2) {
        return Double.compare(d, d2);
    }

    /* renamed from: div-impl */
    public static final double m1167divimpl(double d, double d2) {
        return m1165constructorimpl(d / d2);
    }

    /* renamed from: times-impl */
    public static final double m1191timesimpl(double d, double d2) {
        return m1165constructorimpl(d * d2);
    }

    /* renamed from: toComponents-impl */
    public static final <T> T m1195toComponentsimpl(double d, @NotNull Function4<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> function4) {
        return function4.invoke(Integer.valueOf((int) m1174getInHoursimpl(d)), Integer.valueOf(m1180getMinutesComponentimpl(d)), Integer.valueOf(m1182getSecondsComponentimpl(d)), Integer.valueOf(m1181getNanosecondsComponentimpl(d)));
    }

    /* renamed from: toComponents-impl */
    public static final <T> T m1194toComponentsimpl(double d, @NotNull Function3<? super Integer, ? super Integer, ? super Integer, ? extends T> function3) {
        return function3.invoke(Integer.valueOf((int) m1177getInMinutesimpl(d)), Integer.valueOf(m1182getSecondsComponentimpl(d)), Integer.valueOf(m1181getNanosecondsComponentimpl(d)));
    }

    /* renamed from: toComponents-impl */
    public static final <T> T m1193toComponentsimpl(double d, @NotNull Function2<? super Long, ? super Integer, ? extends T> function2) {
        return function2.invoke(Long.valueOf((long) m1179getInSecondsimpl(d)), Integer.valueOf(m1181getNanosecondsComponentimpl(d)));
    }

    @NotNull
    /* renamed from: toString-impl */
    public static final String m1204toStringimpl(double d, @NotNull TimeUnit timeUnit, int i2) {
        String formatScientific;
        int coerceAtMost;
        if (i2 >= 0) {
            if (m1185isInfiniteimpl(d)) {
                return String.valueOf(d);
            }
            double m1197toDoubleimpl = m1197toDoubleimpl(d, timeUnit);
            StringBuilder sb = new StringBuilder();
            if (Math.abs(m1197toDoubleimpl) < 1.0E14d) {
                coerceAtMost = RangesKt___RangesKt.coerceAtMost(i2, 12);
                formatScientific = FormatToDecimalsKt.formatToExactDecimals(m1197toDoubleimpl, coerceAtMost);
            } else {
                formatScientific = FormatToDecimalsKt.formatScientific(m1197toDoubleimpl);
            }
            sb.append(formatScientific);
            sb.append(DurationUnitKt__DurationUnitKt.shortName(timeUnit));
            return sb.toString();
        }
        throw new IllegalArgumentException(("decimals must be not negative, but was " + i2).toString());
    }
}
