package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0010\u0001\n\u0002\b\u0003\u001a#\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\b\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u00a2\u0006\u0004\b\u0003\u0010\u0004\u001a1\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u00002\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\b\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u00a2\u0006\u0004\b\u0003\u0010\b\u001a1\u0010\n\u001a\u00028\u0000\"\b\b\u0000\u0010\t*\u00020\u00062\b\u0010\u0001\u001a\u0004\u0018\u00018\u0000H\u0087\b\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001\u00a2\u0006\u0004\b\n\u0010\u000b\u001a?\u0010\n\u001a\u00028\u0000\"\b\b\u0000\u0010\t*\u00020\u00062\b\u0010\u0001\u001a\u0004\u0018\u00018\u00002\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\b\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001\u00a2\u0006\u0004\b\n\u0010\f\u001a#\u0010\r\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\b\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u00a2\u0006\u0004\b\r\u0010\u0004\u001a1\u0010\r\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u00002\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\b\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u00a2\u0006\u0004\b\r\u0010\b\u001a1\u0010\u000e\u001a\u00028\u0000\"\b\b\u0000\u0010\t*\u00020\u00062\b\u0010\u0001\u001a\u0004\u0018\u00018\u0000H\u0087\b\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001\u00a2\u0006\u0004\b\u000e\u0010\u000b\u001a?\u0010\u000e\u001a\u00028\u0000\"\b\b\u0000\u0010\t*\u00020\u00062\b\u0010\u0001\u001a\u0004\u0018\u00018\u00002\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\b\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001\u00a2\u0006\u0004\b\u000e\u0010\f\u001a\u0018\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0006H\u0087\b\u00a2\u0006\u0004\b\u0011\u0010\u0012\u00a8\u0006\u0013"}, d2 = {"", "value", "", "require", "(Z)V", "Lkotlin/Function0;", "", "lazyMessage", "(ZLkotlin/jvm/functions/Function0;)V", "T", "requireNotNull", "(Ljava/lang/Object;)Ljava/lang/Object;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "check", "checkNotNull", "message", "", "error", "(Ljava/lang/Object;)Ljava/lang/Void;", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/PreconditionsKt")
/* loaded from: classes11.dex */
class PreconditionsKt__PreconditionsKt extends PreconditionsKt__AssertionsJVMKt {
    @InlineOnly
    private static final void check(boolean z) {
        if (!z) {
            throw new IllegalStateException("Check failed.".toString());
        }
    }

    @InlineOnly
    private static final <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    @InlineOnly
    private static final Void error(Object obj) {
        throw new IllegalStateException(obj.toString());
    }

    @InlineOnly
    private static final void require(boolean z) {
        if (!z) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    @InlineOnly
    private static final <T> T requireNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }

    @InlineOnly
    private static final void check(boolean z, Function0<? extends Object> function0) {
        if (!z) {
            throw new IllegalStateException(function0.invoke().toString());
        }
    }

    @InlineOnly
    private static final <T> T checkNotNull(T t, Function0<? extends Object> function0) {
        if (t != null) {
            return t;
        }
        throw new IllegalStateException(function0.invoke().toString());
    }

    @InlineOnly
    private static final void require(boolean z, Function0<? extends Object> function0) {
        if (!z) {
            throw new IllegalArgumentException(function0.invoke().toString());
        }
    }

    @InlineOnly
    private static final <T> T requireNotNull(T t, Function0<? extends Object> function0) {
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException(function0.invoke().toString());
    }
}
