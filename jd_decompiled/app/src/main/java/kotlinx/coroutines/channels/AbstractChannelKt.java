package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.channels.ValueOrClosed;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a%\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\"\u0004\b\u0000\u0010\u0000*\u0004\u0018\u00010\u0001H\u0082\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0003\u0010\u0004\u001a'\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\"\u0004\b\u0000\u0010\u0000*\u0006\u0012\u0002\b\u00030\u0005H\u0082\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0003\u0010\u0006\"\u0016\u0010\b\u001a\u00020\u00078\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b\b\u0010\t\"\u0016\u0010\n\u001a\u00020\u00078\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b\n\u0010\t\"\u0016\u0010\u000b\u001a\u00020\u00078\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\t\"\u001c\u0010\f\u001a\u00020\u00018\u0000@\u0001X\u0081\u0004\u00a2\u0006\f\n\u0004\b\f\u0010\r\u0012\u0004\b\u000e\u0010\u000f\"\u001c\u0010\u0010\u001a\u00020\u00018\u0000@\u0001X\u0081\u0004\u00a2\u0006\f\n\u0004\b\u0010\u0010\r\u0012\u0004\b\u0011\u0010\u000f\"\u001c\u0010\u0012\u001a\u00020\u00018\u0000@\u0001X\u0081\u0004\u00a2\u0006\f\n\u0004\b\u0012\u0010\r\u0012\u0004\b\u0013\u0010\u000f\"\u001c\u0010\u0014\u001a\u00020\u00018\u0000@\u0001X\u0081\u0004\u00a2\u0006\f\n\u0004\b\u0014\u0010\r\u0012\u0004\b\u0015\u0010\u000f\"\u001c\u0010\u0016\u001a\u00020\u00018\u0000@\u0001X\u0081\u0004\u00a2\u0006\f\n\u0004\b\u0016\u0010\r\u0012\u0004\b\u0017\u0010\u000f*(\b\u0000\u0010\u001b\"\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0019\u0012\u0004\u0012\u00020\u001a0\u00182\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0019\u0012\u0004\u0012\u00020\u001a0\u0018\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001c"}, d2 = {"E", "", "Lkotlinx/coroutines/channels/ValueOrClosed;", "toResult", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/Closed;", "(Lkotlinx/coroutines/channels/Closed;)Ljava/lang/Object;", "", "RECEIVE_RESULT", "I", "RECEIVE_NULL_ON_CLOSE", "RECEIVE_THROWS_ON_CLOSE", "OFFER_SUCCESS", "Ljava/lang/Object;", "OFFER_SUCCESS$annotations", "()V", "POLL_FAILED", "POLL_FAILED$annotations", "HANDLER_INVOKED", "HANDLER_INVOKED$annotations", "OFFER_FAILED", "OFFER_FAILED$annotations", "ENQUEUE_FAILED", "ENQUEUE_FAILED$annotations", "Lkotlin/Function1;", "", "", "Handler", "kotlinx-coroutines-core"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class AbstractChannelKt {
    public static final int RECEIVE_NULL_ON_CLOSE = 1;
    public static final int RECEIVE_RESULT = 2;
    public static final int RECEIVE_THROWS_ON_CLOSE = 0;
    @JvmField
    @NotNull
    public static final Object OFFER_SUCCESS = new Symbol("OFFER_SUCCESS");
    @JvmField
    @NotNull
    public static final Object OFFER_FAILED = new Symbol("OFFER_FAILED");
    @JvmField
    @NotNull
    public static final Object POLL_FAILED = new Symbol("POLL_FAILED");
    @JvmField
    @NotNull
    public static final Object ENQUEUE_FAILED = new Symbol("ENQUEUE_FAILED");
    @JvmField
    @NotNull
    public static final Object HANDLER_INVOKED = new Symbol("ON_CLOSE_HANDLER_INVOKED");

    public static /* synthetic */ void ENQUEUE_FAILED$annotations() {
    }

    public static /* synthetic */ void HANDLER_INVOKED$annotations() {
    }

    public static /* synthetic */ void OFFER_FAILED$annotations() {
    }

    public static /* synthetic */ void OFFER_SUCCESS$annotations() {
    }

    public static /* synthetic */ void POLL_FAILED$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <E> Object toResult(@Nullable Object obj) {
        if (obj instanceof Closed@) {
            ValueOrClosed.Companion companion = ValueOrClosed.INSTANCE;
            return ValueOrClosed.m1228constructorimpl(new ValueOrClosed.Closed(((Closed@) obj).Closed@));
        }
        ValueOrClosed.Companion companion2 = ValueOrClosed.INSTANCE;
        return ValueOrClosed.m1228constructorimpl(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <E> Object toResult(@NotNull Closed@<?> r1) {
        ValueOrClosed.Companion companion = ValueOrClosed.INSTANCE;
        return ValueOrClosed.m1228constructorimpl(new ValueOrClosed.Closed(r1.Closed@));
    }
}
