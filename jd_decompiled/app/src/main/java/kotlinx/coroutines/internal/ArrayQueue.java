package kotlinx.coroutines.internal;

import com.jingdong.common.sample.jshop.utils.DataCompassUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0004\b\u0010\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0016\u0010\u0005J\u000f\u0010\u0004\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00028\u0000\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u0004\u0018\u00018\u0000\u00a2\u0006\u0004\b\t\u0010\nJ\r\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\u0004\b\u000b\u0010\u0005R\u0016\u0010\r\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0013\u0010\u0010\u001a\u00020\u000f8F@\u0006\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u000eR\u001e\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015\u00a8\u0006\u0017"}, d2 = {"Lkotlinx/coroutines/internal/ArrayQueue;", "", "T", "", "ensureCapacity", "()V", "element", "addLast", "(Ljava/lang/Object;)V", "removeFirstOrNull", "()Ljava/lang/Object;", "clear", "", DataCompassUtils.MODULE_TYPE_HEAD, "I", "", CartConstant.KEY_GLOBAL_IS_EMPTY, "()Z", "tail", "", "elements", "[Ljava/lang/Object;", "<init>", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public class ArrayQueue<T> {
    private Object[] elements = new Object[16];
    private int head;
    private int tail;

    private final void ensureCapacity() {
        Object[] objArr = this.elements;
        int length = objArr.length;
        Object[] objArr2 = new Object[length << 1];
        ArraysKt___ArraysJvmKt.copyInto$default(objArr, objArr2, 0, this.head, 0, 10, (Object) null);
        Object[] objArr3 = this.elements;
        int length2 = objArr3.length;
        int i2 = this.head;
        ArraysKt___ArraysJvmKt.copyInto$default(objArr3, objArr2, length2 - i2, 0, i2, 4, (Object) null);
        this.elements = objArr2;
        this.head = 0;
        this.tail = length;
    }

    public final void addLast(@NotNull T element) {
        Object[] objArr = this.elements;
        int i2 = this.tail;
        objArr[i2] = element;
        int length = (objArr.length - 1) & (i2 + 1);
        this.tail = length;
        if (length == this.head) {
            ensureCapacity();
        }
    }

    public final void clear() {
        this.head = 0;
        this.tail = 0;
        this.elements = new Object[this.elements.length];
    }

    public final boolean isEmpty() {
        return this.head == this.tail;
    }

    @Nullable
    public final T removeFirstOrNull() {
        int i2 = this.head;
        if (i2 == this.tail) {
            return null;
        }
        Object[] objArr = this.elements;
        T t = (T) objArr[i2];
        objArr[i2] = null;
        this.head = (i2 + 1) & (objArr.length - 1);
        if (t != null) {
            return t;
        }
        throw new TypeCastException("null cannot be cast to non-null type T");
    }
}
