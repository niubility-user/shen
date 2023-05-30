package kotlin;

import com.jd.aips.verify.tracker.VerifyTracker;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.tencent.open.SocialConstants;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\u001a\u0010\u0010\u0001\u001a\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\u0001\u0010\u0002\u001a\u0018\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0003H\u0087\b\u00a2\u0006\u0004\b\u0001\u0010\u0005\u001a1\u0010\t\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0087\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0004\b\t\u0010\n\u001aF\u0010\t\u001a\u00028\u0001\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\u0006*\u00028\u00002\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\f\u00a2\u0006\u0002\b\rH\u0087\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0004\b\t\u0010\u000e\u001aJ\u0010\u0010\u001a\u00028\u0001\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\u00062\u0006\u0010\u000f\u001a\u00028\u00002\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\f\u00a2\u0006\u0002\b\rH\u0087\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u00a2\u0006\u0004\b\u0010\u0010\u000e\u001a@\u0010\u0012\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u000b*\u00028\u00002\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00110\f\u00a2\u0006\u0002\b\rH\u0087\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0004\b\u0012\u0010\u000e\u001a;\u0010\u0013\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u000b*\u00028\u00002\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00110\fH\u0087\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0004\b\u0013\u0010\u000e\u001aA\u0010\u0014\u001a\u00028\u0001\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\u0006*\u00028\u00002\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\fH\u0087\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0004\b\u0014\u0010\u000e\u001a=\u0010\u0017\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u000b*\u00028\u00002\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00150\fH\u0087\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0004\b\u0017\u0010\u000e\u001a=\u0010\u0018\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u000b*\u00028\u00002\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00150\fH\u0087\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0004\b\u0018\u0010\u000e\u001a7\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u00192\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00110\fH\u0087\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001d\u00a8\u0006\u001e"}, d2 = {"", "TODO", "()Ljava/lang/Void;", "", "reason", "(Ljava/lang/String;)Ljava/lang/Void;", "R", "Lkotlin/Function0;", JDReactConstant.BLOCK, "run", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "T", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", SocialConstants.PARAM_RECEIVER, "with", "", "apply", "also", "let", "", "predicate", "takeIf", "takeUnless", "", VerifyTracker.KEY_TIMES, "action", "repeat", "(ILkotlin/jvm/functions/Function1;)V", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/StandardKt")
/* loaded from: classes11.dex */
public class StandardKt__StandardKt {
    /* JADX WARN: Type inference failed for: r1v0, types: [kotlin.jvm.internal.DefaultConstructorMarker, java.lang.String] */
    @InlineOnly
    private static final Void TODO() {
        ?? r1 = 0;
        throw new NotImplementedError(r1, 1, r1);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> T also(T t, Function1<? super T, Unit> function1) {
        function1.invoke(t);
        return t;
    }

    @InlineOnly
    private static final <T> T apply(T t, Function1<? super T, Unit> function1) {
        function1.invoke(t);
        return t;
    }

    @InlineOnly
    private static final <T, R> R let(T t, Function1<? super T, ? extends R> function1) {
        return function1.invoke(t);
    }

    @InlineOnly
    private static final void repeat(int i2, Function1<? super Integer, Unit> function1) {
        for (int i3 = 0; i3 < i2; i3++) {
            function1.invoke(Integer.valueOf(i3));
        }
    }

    @InlineOnly
    private static final <R> R run(Function0<? extends R> function0) {
        return function0.invoke();
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> T takeIf(T t, Function1<? super T, Boolean> function1) {
        if (function1.invoke(t).booleanValue()) {
            return t;
        }
        return null;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> T takeUnless(T t, Function1<? super T, Boolean> function1) {
        if (function1.invoke(t).booleanValue()) {
            return null;
        }
        return t;
    }

    @InlineOnly
    private static final <T, R> R with(T t, Function1<? super T, ? extends R> function1) {
        return function1.invoke(t);
    }

    @InlineOnly
    private static final Void TODO(String str) {
        throw new NotImplementedError("An operation is not implemented: " + str);
    }

    @InlineOnly
    private static final <T, R> R run(T t, Function1<? super T, ? extends R> function1) {
        return function1.invoke(t);
    }
}
