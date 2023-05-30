package kotlin.collections;

import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\b\u0086\b\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B\u0017\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00028\u0000\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u0004\u001a\u00020\u0003H\u00c6\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00028\u0000H\u00c6\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007J*\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00028\u0000H\u00c6\u0001\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fH\u00d6\u0001\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001\u00a2\u0006\u0004\b\u000f\u0010\u0005J\u001a\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0002H\u00d6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0013R\u0019\u0010\b\u001a\u00020\u00038\u0006@\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u0014\u001a\u0004\b\u0015\u0010\u0005R\u0019\u0010\t\u001a\u00028\u00008\u0006@\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u0016\u001a\u0004\b\u0017\u0010\u0007\u00a8\u0006\u001a"}, d2 = {"Lkotlin/collections/IndexedValue;", "T", "", "", "component1", "()I", "component2", "()Ljava/lang/Object;", "index", "value", JDViewKitEventHelper.ActionType_Copy, "(ILjava/lang/Object;)Lkotlin/collections/IndexedValue;", "", "toString", "()Ljava/lang/String;", "hashCode", "other", "", "equals", "(Ljava/lang/Object;)Z", "I", "getIndex", "Ljava/lang/Object;", "getValue", "<init>", "(ILjava/lang/Object;)V", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final /* data */ class IndexedValue<T> {
    private final int index;
    private final T value;

    public IndexedValue(int i2, T t) {
        this.index = i2;
        this.value = t;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ IndexedValue copy$default(IndexedValue indexedValue, int i2, Object obj, int i3, Object obj2) {
        if ((i3 & 1) != 0) {
            i2 = indexedValue.index;
        }
        if ((i3 & 2) != 0) {
            obj = indexedValue.value;
        }
        return indexedValue.copy(i2, obj);
    }

    /* renamed from: component1  reason: from getter */
    public final int getIndex() {
        return this.index;
    }

    public final T component2() {
        return this.value;
    }

    @NotNull
    public final IndexedValue<T> copy(int index, T value) {
        return new IndexedValue<>(index, value);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            if (other instanceof IndexedValue) {
                IndexedValue indexedValue = (IndexedValue) other;
                return this.index == indexedValue.index && Intrinsics.areEqual(this.value, indexedValue.value);
            }
            return false;
        }
        return true;
    }

    public final int getIndex() {
        return this.index;
    }

    public final T getValue() {
        return this.value;
    }

    public int hashCode() {
        int i2 = this.index * 31;
        T t = this.value;
        return i2 + (t != null ? t.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "IndexedValue(index=" + this.index + ", value=" + this.value + ")";
    }
}
