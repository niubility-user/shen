package kotlinx.coroutines.channels;

import com.jingdong.jdsdk.constant.JshopConst;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.InternalCoroutinesApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@InternalCoroutinesApi
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0003\n\u0002\b\b\b\u0087@\u0018\u0000  *\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0002! B\u0016\b\u0000\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001f\u0010\u0011J\u000f\u0010\u0006\u001a\u00020\u0003H\u0016\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\u0007H\u00d6\u0001\u00a2\u0006\u0004\b\b\u0010\tJ\u001a\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\u0002H\u00d6\u0003\u00a2\u0006\u0004\b\f\u0010\rR\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0014\u001a\u00028\u00008F@\u0006\u00a2\u0006\f\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0017\u001a\u0004\u0018\u00018\u00008F@\u0006\u00a2\u0006\f\u0012\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0015\u0010\u0011R\u0013\u0010\u0019\u001a\u00020\u000b8F@\u0006\u00a2\u0006\u0006\u001a\u0004\b\u0018\u0010\rR\u001b\u0010\u001e\u001a\u0004\u0018\u00010\u001a8F@\u0006\u00a2\u0006\f\u0012\u0004\b\u001d\u0010\u0013\u001a\u0004\b\u001b\u0010\u001c\u00f8\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\""}, d2 = {"Lkotlinx/coroutines/channels/ValueOrClosed;", "T", "", "", "toString-impl", "(Ljava/lang/Object;)Ljava/lang/String;", "toString", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "holder", "Ljava/lang/Object;", "getValue-impl", "(Ljava/lang/Object;)Ljava/lang/Object;", "value$annotations", "()V", "value", "getValueOrNull-impl", "valueOrNull$annotations", "valueOrNull", "isClosed-impl", "isClosed", "", "getCloseCause-impl", "(Ljava/lang/Object;)Ljava/lang/Throwable;", "closeCause$annotations", "closeCause", "constructor-impl", "Companion", "Closed", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class ValueOrClosed<T> {

    /* renamed from: Companion  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Object holder;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\r\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0004\u001a\u00020\u00032\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0096\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\n\u0010\u000bR\u0018\u0010\r\u001a\u0004\u0018\u00010\f8\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000e\u00a8\u0006\u0011"}, d2 = {"Lkotlinx/coroutines/channels/ValueOrClosed$Closed;", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "", "cause", "Ljava/lang/Throwable;", "<init>", "(Ljava/lang/Throwable;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes11.dex */
    public static final class Closed {
        @JvmField
        @Nullable

        /* renamed from: cause  reason: from kotlin metadata and from toString */
        public final Throwable ;

        public Closed(@Nullable Throwable th) {
            this. = th;
        }

        public boolean equals(@Nullable Object other) {
            return (other instanceof Closed) && Intrinsics.areEqual(this., ((Closed) other).);
        }

        public int hashCode() {
            Throwable th = this.;
            if (th != null) {
                return th.hashCode();
            }
            return 0;
        }

        @NotNull
        public String toString() {
            return "Closed(" + this. + ')';
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0007\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\f\u0010\rJ'\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004\"\u0004\b\u0001\u0010\u00022\u0006\u0010\u0003\u001a\u00028\u0001H\u0080\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0006J)\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004\"\u0004\b\u0001\u0010\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0080\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\t\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/channels/ValueOrClosed$Companion;", "", "E", "value", "Lkotlinx/coroutines/channels/ValueOrClosed;", "value$kotlinx_coroutines_core", "(Ljava/lang/Object;)Ljava/lang/Object;", "", "cause", "closed$kotlinx_coroutines_core", "(Ljava/lang/Throwable;)Ljava/lang/Object;", JshopConst.JSKEY_SHOP_CLOSED, "<init>", "()V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes11.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final <E> Object closed$kotlinx_coroutines_core(@Nullable Throwable cause) {
            return ValueOrClosed.m1228constructorimpl(new Closed(cause));
        }

        @NotNull
        public final <E> Object value$kotlinx_coroutines_core(E value) {
            return ValueOrClosed.m1228constructorimpl(value);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private /* synthetic */ ValueOrClosed(@Nullable Object obj) {
        this.holder = obj;
    }

    @NotNull
    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ ValueOrClosed m1227boximpl(@Nullable Object obj) {
        return new ValueOrClosed(obj);
    }

    public static /* synthetic */ void closeCause$annotations() {
    }

    @NotNull
    /* renamed from: constructor-impl  reason: not valid java name */
    public static Object m1228constructorimpl(@Nullable Object obj) {
        return obj;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m1229equalsimpl(Object obj, @Nullable Object obj2) {
        return (obj2 instanceof ValueOrClosed) && Intrinsics.areEqual(obj, ((ValueOrClosed) obj2).getHolder());
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m1230equalsimpl0(@Nullable Object obj, @Nullable Object obj2) {
        return Intrinsics.areEqual(obj, obj2);
    }

    @Nullable
    /* renamed from: getCloseCause-impl  reason: not valid java name */
    public static final Throwable m1231getCloseCauseimpl(Object obj) {
        if (obj instanceof Closed) {
            return ((Closed) obj).;
        }
        throw new IllegalStateException("Channel was not closed".toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: getValue-impl  reason: not valid java name */
    public static final T m1232getValueimpl(Object obj) {
        if (obj instanceof Closed) {
            throw new IllegalStateException(ChannelsKt.DEFAULT_CLOSE_MESSAGE.toString());
        }
        return obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    /* renamed from: getValueOrNull-impl  reason: not valid java name */
    public static final T m1233getValueOrNullimpl(Object obj) {
        if (obj instanceof Closed) {
            return null;
        }
        return obj;
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m1234hashCodeimpl(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    /* renamed from: isClosed-impl  reason: not valid java name */
    public static final boolean m1235isClosedimpl(Object obj) {
        return obj instanceof Closed;
    }

    @NotNull
    /* renamed from: toString-impl  reason: not valid java name */
    public static String m1236toStringimpl(Object obj) {
        if (obj instanceof Closed) {
            return obj.toString();
        }
        return "Value(" + obj + ')';
    }

    public static /* synthetic */ void value$annotations() {
    }

    public static /* synthetic */ void valueOrNull$annotations() {
    }

    public boolean equals(Object other) {
        return m1229equalsimpl(this.holder, other);
    }

    public int hashCode() {
        return m1234hashCodeimpl(this.holder);
    }

    @NotNull
    public String toString() {
        return m1236toStringimpl(this.holder);
    }

    @Nullable
    /* renamed from: unbox-impl  reason: not valid java name and from getter */
    public final /* synthetic */ Object getHolder() {
        return this.holder;
    }
}
