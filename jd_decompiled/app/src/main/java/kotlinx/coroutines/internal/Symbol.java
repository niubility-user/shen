package kotlinx.coroutines.internal;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\t\u001a\u00020\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0003\u0010\u0004J \u0010\u0007\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001H\u0086\b\u00a2\u0006\u0004\b\u0007\u0010\bR\u0019\u0010\t\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\u0004\u00a8\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/internal/Symbol;", "", "", "toString", "()Ljava/lang/String;", "T", "value", "unbox", "(Ljava/lang/Object;)Ljava/lang/Object;", "symbol", "Ljava/lang/String;", "getSymbol", "<init>", "(Ljava/lang/String;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class Symbol {
    @NotNull
    private final String symbol;

    public Symbol(@NotNull String str) {
        this.symbol = str;
    }

    @NotNull
    public final String getSymbol() {
        return this.symbol;
    }

    @NotNull
    public String toString() {
        return this.symbol;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T> T unbox(@Nullable Object value) {
        if (value == this) {
            return null;
        }
        return value;
    }
}
