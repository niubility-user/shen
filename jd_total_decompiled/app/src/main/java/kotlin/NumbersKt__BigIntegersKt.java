package kotlin;

import com.jd.aips.verify.tracker.VerifyTracker;
import com.jingdong.common.entity.personal.PersonalConstants;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001c\u0010\u0002\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\n\u00a2\u0006\u0004\b\u0002\u0010\u0003\u001a\u001c\u0010\u0004\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\n\u00a2\u0006\u0004\b\u0004\u0010\u0003\u001a\u001c\u0010\u0005\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\n\u00a2\u0006\u0004\b\u0005\u0010\u0003\u001a\u001c\u0010\u0006\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\n\u00a2\u0006\u0004\b\u0006\u0010\u0003\u001a\u001c\u0010\u0007\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\n\u00a2\u0006\u0004\b\u0007\u0010\u0003\u001a\u0014\u0010\b\u001a\u00020\u0000*\u00020\u0000H\u0087\n\u00a2\u0006\u0004\b\b\u0010\t\u001a\u0014\u0010\n\u001a\u00020\u0000*\u00020\u0000H\u0087\n\u00a2\u0006\u0004\b\n\u0010\t\u001a\u0014\u0010\u000b\u001a\u00020\u0000*\u00020\u0000H\u0087\n\u00a2\u0006\u0004\b\u000b\u0010\t\u001a\u0014\u0010\f\u001a\u00020\u0000*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\f\u0010\t\u001a\u001c\u0010\r\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\f\u00a2\u0006\u0004\b\r\u0010\u0003\u001a\u001c\u0010\u000e\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\f\u00a2\u0006\u0004\b\u000e\u0010\u0003\u001a\u001c\u0010\u000f\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\f\u00a2\u0006\u0004\b\u000f\u0010\u0003\u001a\u001c\u0010\u0012\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0010H\u0087\f\u00a2\u0006\u0004\b\u0012\u0010\u0013\u001a\u001c\u0010\u0014\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0010H\u0087\f\u00a2\u0006\u0004\b\u0014\u0010\u0013\u001a\u0014\u0010\u0015\u001a\u00020\u0000*\u00020\u0010H\u0087\b\u00a2\u0006\u0004\b\u0015\u0010\u0016\u001a\u0014\u0010\u0015\u001a\u00020\u0000*\u00020\u0017H\u0087\b\u00a2\u0006\u0004\b\u0015\u0010\u0018\u001a\u0014\u0010\u001a\u001a\u00020\u0019*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\u001a\u0010\u001b\u001a(\u0010\u001a\u001a\u00020\u0019*\u00020\u00002\b\b\u0002\u0010\u001c\u001a\u00020\u00102\b\b\u0002\u0010\u001e\u001a\u00020\u001dH\u0087\b\u00a2\u0006\u0004\b\u001a\u0010\u001f\u00a8\u0006 "}, d2 = {"Ljava/math/BigInteger;", "other", "plus", "(Ljava/math/BigInteger;Ljava/math/BigInteger;)Ljava/math/BigInteger;", "minus", VerifyTracker.KEY_TIMES, "div", "rem", "unaryMinus", "(Ljava/math/BigInteger;)Ljava/math/BigInteger;", "inc", "dec", "inv", "and", "or", "xor", "", PersonalConstants.ICON_STYLE_N, "shl", "(Ljava/math/BigInteger;I)Ljava/math/BigInteger;", "shr", "toBigInteger", "(I)Ljava/math/BigInteger;", "", "(J)Ljava/math/BigInteger;", "Ljava/math/BigDecimal;", "toBigDecimal", "(Ljava/math/BigInteger;)Ljava/math/BigDecimal;", "scale", "Ljava/math/MathContext;", "mathContext", "(Ljava/math/BigInteger;ILjava/math/MathContext;)Ljava/math/BigDecimal;", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/NumbersKt")
/* loaded from: classes11.dex */
public class NumbersKt__BigIntegersKt extends NumbersKt__BigDecimalsKt {
    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger and(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger and = bigInteger.and(bigInteger2);
        Intrinsics.checkExpressionValueIsNotNull(and, "this.and(other)");
        return and;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger dec(@NotNull BigInteger bigInteger) {
        BigInteger subtract = bigInteger.subtract(BigInteger.ONE);
        Intrinsics.checkExpressionValueIsNotNull(subtract, "this.subtract(BigInteger.ONE)");
        return subtract;
    }

    @InlineOnly
    private static final BigInteger div(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger divide = bigInteger.divide(bigInteger2);
        Intrinsics.checkExpressionValueIsNotNull(divide, "this.divide(other)");
        return divide;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger inc(@NotNull BigInteger bigInteger) {
        BigInteger add = bigInteger.add(BigInteger.ONE);
        Intrinsics.checkExpressionValueIsNotNull(add, "this.add(BigInteger.ONE)");
        return add;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger inv(@NotNull BigInteger bigInteger) {
        BigInteger not = bigInteger.not();
        Intrinsics.checkExpressionValueIsNotNull(not, "this.not()");
        return not;
    }

    @InlineOnly
    private static final BigInteger minus(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger subtract = bigInteger.subtract(bigInteger2);
        Intrinsics.checkExpressionValueIsNotNull(subtract, "this.subtract(other)");
        return subtract;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger or(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger or = bigInteger.or(bigInteger2);
        Intrinsics.checkExpressionValueIsNotNull(or, "this.or(other)");
        return or;
    }

    @InlineOnly
    private static final BigInteger plus(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger add = bigInteger.add(bigInteger2);
        Intrinsics.checkExpressionValueIsNotNull(add, "this.add(other)");
        return add;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final BigInteger rem(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger remainder = bigInteger.remainder(bigInteger2);
        Intrinsics.checkExpressionValueIsNotNull(remainder, "this.remainder(other)");
        return remainder;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger shl(@NotNull BigInteger bigInteger, int i2) {
        BigInteger shiftLeft = bigInteger.shiftLeft(i2);
        Intrinsics.checkExpressionValueIsNotNull(shiftLeft, "this.shiftLeft(n)");
        return shiftLeft;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger shr(@NotNull BigInteger bigInteger, int i2) {
        BigInteger shiftRight = bigInteger.shiftRight(i2);
        Intrinsics.checkExpressionValueIsNotNull(shiftRight, "this.shiftRight(n)");
        return shiftRight;
    }

    @InlineOnly
    private static final BigInteger times(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger multiply = bigInteger.multiply(bigInteger2);
        Intrinsics.checkExpressionValueIsNotNull(multiply, "this.multiply(other)");
        return multiply;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(@NotNull BigInteger bigInteger) {
        return new BigDecimal(bigInteger);
    }

    static /* synthetic */ BigDecimal toBigDecimal$default(BigInteger bigInteger, int i2, MathContext mathContext, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = 0;
        }
        if ((i3 & 2) != 0) {
            mathContext = MathContext.UNLIMITED;
            Intrinsics.checkExpressionValueIsNotNull(mathContext, "MathContext.UNLIMITED");
        }
        return new BigDecimal(bigInteger, i2, mathContext);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger toBigInteger(int i2) {
        BigInteger valueOf = BigInteger.valueOf(i2);
        Intrinsics.checkExpressionValueIsNotNull(valueOf, "BigInteger.valueOf(this.toLong())");
        return valueOf;
    }

    @InlineOnly
    private static final BigInteger unaryMinus(@NotNull BigInteger bigInteger) {
        BigInteger negate = bigInteger.negate();
        Intrinsics.checkExpressionValueIsNotNull(negate, "this.negate()");
        return negate;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger xor(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger xor = bigInteger.xor(bigInteger2);
        Intrinsics.checkExpressionValueIsNotNull(xor, "this.xor(other)");
        return xor;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(@NotNull BigInteger bigInteger, int i2, MathContext mathContext) {
        return new BigDecimal(bigInteger, i2, mathContext);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger toBigInteger(long j2) {
        BigInteger valueOf = BigInteger.valueOf(j2);
        Intrinsics.checkExpressionValueIsNotNull(valueOf, "BigInteger.valueOf(this)");
        return valueOf;
    }
}
