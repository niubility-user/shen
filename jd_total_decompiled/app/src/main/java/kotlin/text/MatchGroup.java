package kotlin.text;

import ..;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u0003\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0006\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007J$\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u0005H\u00c6\u0001\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002H\u00d6\u0001\u00a2\u0006\u0004\b\f\u0010\u0004J\u0010\u0010\u000e\u001a\u00020\rH\u00d6\u0001\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001a\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0013R\u0019\u0010\t\u001a\u00020\u00058\u0006@\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u0014\u001a\u0004\b\u0015\u0010\u0007R\u0019\u0010\b\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u0016\u001a\u0004\b\u0017\u0010\u0004\u00a8\u0006\u001a"}, d2 = {"Lkotlin/text/MatchGroup;", "", "", "component1", "()Ljava/lang/String;", "Lkotlin/ranges/IntRange;", "component2", "()Lkotlin/ranges/IntRange;", "value", "range", JDViewKitEventHelper.ActionType_Copy, "(Ljava/lang/String;Lkotlin/ranges/IntRange;)Lkotlin/text/MatchGroup;", "toString", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Lkotlin/ranges/IntRange;", "getRange", "Ljava/lang/String;", "getValue", "<init>", "(Ljava/lang/String;Lkotlin/ranges/IntRange;)V", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final /* data */ class MatchGroup {
    @NotNull
    private final  range;
    @NotNull
    private final String value;

    public MatchGroup(@NotNull String str, @NotNull  Var) {
        this.value = str;
        this.range = Var;
    }

    public static /* synthetic */ MatchGroup copy$default(MatchGroup matchGroup, String str,  Var, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = matchGroup.value;
        }
        if ((i2 & 2) != 0) {
            Var = matchGroup.range;
        }
        return matchGroup.copy(str, Var);
    }

    @NotNull
    /* renamed from: component1  reason: from getter */
    public final String getValue() {
        return this.value;
    }

    @NotNull
    /* renamed from: component2  reason: from getter */
    public final  getRange() {
        return this.range;
    }

    @NotNull
    public final MatchGroup copy(@NotNull String value, @NotNull  range) {
        return new MatchGroup(value, range);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            if (other instanceof MatchGroup) {
                MatchGroup matchGroup = (MatchGroup) other;
                return Intrinsics.areEqual(this.value, matchGroup.value) && Intrinsics.areEqual(this.range, matchGroup.range);
            }
            return false;
        }
        return true;
    }

    @NotNull
    public final  getRange() {
        return this.range;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        String str = this.value;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
         Var = this.range;
        return hashCode + (Var != null ? Var.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "MatchGroup(value=" + this.value + ", range=" + this.range + ")";
    }
}
