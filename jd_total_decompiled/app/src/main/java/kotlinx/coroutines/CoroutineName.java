package kotlinx.coroutines;

import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0005\u0010\u0004J\u001a\u0010\u0007\u001a\u00020\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tH\u00d6\u0001\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u00d6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0006\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0011\u001a\u0004\b\u0012\u0010\u0004\u00a8\u0006\u0016"}, d2 = {"Lkotlinx/coroutines/CoroutineName;", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "", "toString", "()Ljava/lang/String;", "component1", "name", JDViewKitEventHelper.ActionType_Copy, "(Ljava/lang/String;)Lkotlinx/coroutines/CoroutineName;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getName", "<init>", "(Ljava/lang/String;)V", "Key", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final /* data */ class CoroutineName extends AbstractCoroutineContextElement {

    /* renamed from: Key  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    @NotNull

    /* renamed from: name  reason: from kotlin metadata and from toString */
    private final String ;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lkotlinx/coroutines/CoroutineName$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlinx/coroutines/CoroutineName;", "<init>", "()V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    /* renamed from: kotlinx.coroutines.CoroutineName$Key  reason: from kotlin metadata */
    /* loaded from: classes11.dex */
    public static final class Companion implements CoroutineContext.Key<CoroutineName> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public CoroutineName(@NotNull String str) {
        super(INSTANCE);
        this. = str;
    }

    public static /* synthetic */ CoroutineName copy$default(CoroutineName coroutineName, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = coroutineName.;
        }
        return coroutineName.copy(str);
    }

    @NotNull
    /* renamed from: component1  reason: from getter */
    public final String get() {
        return this.;
    }

    @NotNull
    public final CoroutineName copy(@NotNull String name) {
        return new CoroutineName(name);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            return (other instanceof CoroutineName) && Intrinsics.areEqual(this., ((CoroutineName) other).);
        }
        return true;
    }

    @NotNull
    public final String getName() {
        return this.;
    }

    public int hashCode() {
        String str = this.;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        return "CoroutineName(" + this. + ')';
    }
}
