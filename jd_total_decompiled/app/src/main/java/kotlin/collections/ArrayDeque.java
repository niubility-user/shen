package kotlin.collections;

import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.jingdong.common.sample.jshop.utils.DataCompassUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u000f\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B\u0011\b\u0016\u0012\u0006\u0010R\u001a\u00020\u0003\u00a2\u0006\u0004\bS\u0010\u0007B\t\b\u0016\u00a2\u0006\u0004\bS\u0010@B\u0017\b\u0016\u0012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013\u00a2\u0006\u0004\bS\u0010TJ\u0017\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\t\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\t\u0010\u0007J\u0018\u0010\u000b\u001a\u00028\u00002\u0006\u0010\n\u001a\u00020\u0003H\u0083\b\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000e\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0010\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003H\u0083\b\u00a2\u0006\u0004\b\n\u0010\u000fJ\u0017\u0010\u0011\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u000fJ\u0017\u0010\u0012\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u000fJ%\u0010\u0015\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00032\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J$\u0010\u001a\u001a\u00020\u00182\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00180\u0017H\u0082\b\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u001f\u0010\b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0000\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010\u001f\u001a\u00020\u0018H\u0016\u00a2\u0006\u0004\b\u001f\u0010 J\r\u0010!\u001a\u00028\u0000\u00a2\u0006\u0004\b!\u0010\"J\u000f\u0010#\u001a\u0004\u0018\u00018\u0000\u00a2\u0006\u0004\b#\u0010\"J\r\u0010$\u001a\u00028\u0000\u00a2\u0006\u0004\b$\u0010\"J\u000f\u0010%\u001a\u0004\u0018\u00018\u0000\u00a2\u0006\u0004\b%\u0010\"J\u0015\u0010'\u001a\u00020\u00052\u0006\u0010&\u001a\u00028\u0000\u00a2\u0006\u0004\b'\u0010(J\u0015\u0010)\u001a\u00020\u00052\u0006\u0010&\u001a\u00028\u0000\u00a2\u0006\u0004\b)\u0010(J\r\u0010*\u001a\u00028\u0000\u00a2\u0006\u0004\b*\u0010\"J\u000f\u0010+\u001a\u0004\u0018\u00018\u0000\u00a2\u0006\u0004\b+\u0010\"J\r\u0010,\u001a\u00028\u0000\u00a2\u0006\u0004\b,\u0010\"J\u000f\u0010-\u001a\u0004\u0018\u00018\u0000\u00a2\u0006\u0004\b-\u0010\"J\u0017\u0010.\u001a\u00020\u00182\u0006\u0010&\u001a\u00028\u0000H\u0016\u00a2\u0006\u0004\b.\u0010/J\u001f\u0010.\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010&\u001a\u00028\u0000H\u0016\u00a2\u0006\u0004\b.\u00100J\u001d\u00101\u001a\u00020\u00182\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0016\u00a2\u0006\u0004\b1\u00102J%\u00101\u001a\u00020\u00182\u0006\u0010\r\u001a\u00020\u00032\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0016\u00a2\u0006\u0004\b1\u00103J\u0018\u00104\u001a\u00028\u00002\u0006\u0010\r\u001a\u00020\u0003H\u0096\u0002\u00a2\u0006\u0004\b4\u0010\fJ \u00105\u001a\u00028\u00002\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010&\u001a\u00028\u0000H\u0096\u0002\u00a2\u0006\u0004\b5\u00106J\u0018\u00107\u001a\u00020\u00182\u0006\u0010&\u001a\u00028\u0000H\u0096\u0002\u00a2\u0006\u0004\b7\u0010/J\u0017\u00108\u001a\u00020\u00032\u0006\u0010&\u001a\u00028\u0000H\u0016\u00a2\u0006\u0004\b8\u00109J\u0017\u0010:\u001a\u00020\u00032\u0006\u0010&\u001a\u00028\u0000H\u0016\u00a2\u0006\u0004\b:\u00109J\u0017\u0010;\u001a\u00020\u00182\u0006\u0010&\u001a\u00028\u0000H\u0016\u00a2\u0006\u0004\b;\u0010/J\u0017\u0010<\u001a\u00028\u00002\u0006\u0010\r\u001a\u00020\u0003H\u0016\u00a2\u0006\u0004\b<\u0010\fJ\u001d\u0010=\u001a\u00020\u00182\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0016\u00a2\u0006\u0004\b=\u00102J\u001d\u0010>\u001a\u00020\u00182\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0016\u00a2\u0006\u0004\b>\u00102J\u000f\u0010?\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b?\u0010@JO\u0010J\u001a\u00020\u00052>\u0010G\u001a:\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(D\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010F0E\u00a2\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00050AH\u0000\u00a2\u0006\u0004\bH\u0010IR\u0016\u0010D\u001a\u00020\u00038\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bD\u0010KR$\u0010M\u001a\u00020\u00032\u0006\u0010L\u001a\u00020\u00038\u0016@RX\u0096\u000e\u00a2\u0006\f\n\u0004\bM\u0010K\u001a\u0004\bN\u0010OR\u001e\u0010P\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010F0E8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bP\u0010Q\u00a8\u0006U"}, d2 = {"Lkotlin/collections/ArrayDeque;", "E", "Lkotlin/collections/AbstractMutableList;", "", "minCapacity", "", "ensureCapacity", "(I)V", "newCapacity", "copyElements", "internalIndex", "internalGet", "(I)Ljava/lang/Object;", "index", "positiveMod", "(I)I", "negativeMod", "incremented", "decremented", "", "elements", "copyCollectionElements", "(ILjava/util/Collection;)V", "Lkotlin/Function1;", "", "predicate", "filterInPlace", "(Lkotlin/jvm/functions/Function1;)Z", "oldCapacity", "newCapacity$kotlin_stdlib", "(II)I", CartConstant.KEY_GLOBAL_IS_EMPTY, "()Z", "first", "()Ljava/lang/Object;", "firstOrNull", "last", "lastOrNull", "element", "addFirst", "(Ljava/lang/Object;)V", "addLast", "removeFirst", "removeFirstOrNull", "removeLast", "removeLastOrNull", "add", "(Ljava/lang/Object;)Z", "(ILjava/lang/Object;)V", "addAll", "(Ljava/util/Collection;)Z", "(ILjava/util/Collection;)Z", IMantoServerRequester.GET, "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "contains", "indexOf", "(Ljava/lang/Object;)I", "lastIndexOf", "remove", "removeAt", "removeAll", "retainAll", "clear", "()V", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", DataCompassUtils.MODULE_TYPE_HEAD, "", "", "structure", "internalStructure$kotlin_stdlib", "(Lkotlin/jvm/functions/Function2;)V", "internalStructure", "I", "<set-?>", ApkDownloadTable.FIELD_SIZE, "getSize", "()I", "elementData", "[Ljava/lang/Object;", "initialCapacity", "<init>", "(Ljava/util/Collection;)V", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
@ExperimentalStdlibApi
/* loaded from: classes11.dex */
public final class ArrayDeque<E> extends AbstractMutableList<E> {
    private Object[] elementData;
    private int head;
    private int size;

    public ArrayDeque(int i2) {
        Object[] objArr;
        if (i2 == 0) {
            objArr = ArrayDequeKt.emptyElementData;
        } else if (i2 > 0) {
            objArr = new Object[i2];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + i2);
        }
        this.elementData = objArr;
    }

    private final void copyCollectionElements(int internalIndex, Collection<? extends E> elements) {
        Iterator<? extends E> it = elements.iterator();
        int length = this.elementData.length;
        while (internalIndex < length && it.hasNext()) {
            this.elementData[internalIndex] = it.next();
            internalIndex++;
        }
        int i2 = this.head;
        for (int i3 = 0; i3 < i2 && it.hasNext(); i3++) {
            this.elementData[i3] = it.next();
        }
        this.size = size() + elements.size();
    }

    private final void copyElements(int newCapacity) {
        Object[] objArr = new Object[newCapacity];
        Object[] objArr2 = this.elementData;
        ArraysKt___ArraysJvmKt.copyInto(objArr2, objArr, 0, this.head, objArr2.length);
        Object[] objArr3 = this.elementData;
        int length = objArr3.length;
        int i2 = this.head;
        ArraysKt___ArraysJvmKt.copyInto(objArr3, objArr, length - i2, 0, i2);
        this.head = 0;
        this.elementData = objArr;
    }

    private final int decremented(int index) {
        return index == 0 ? ArraysKt___ArraysKt.getLastIndex(this.elementData) : index - 1;
    }

    private final void ensureCapacity(int minCapacity) {
        Object[] objArr;
        int coerceAtLeast;
        if (minCapacity >= 0) {
            Object[] objArr2 = this.elementData;
            if (minCapacity <= objArr2.length) {
                return;
            }
            objArr = ArrayDequeKt.emptyElementData;
            if (objArr2 == objArr) {
                coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(minCapacity, 10);
                this.elementData = new Object[coerceAtLeast];
                return;
            }
            copyElements(newCapacity$kotlin_stdlib(this.elementData.length, minCapacity));
            return;
        }
        throw new IllegalStateException("Deque is too big.");
    }

    private final boolean filterInPlace(Function1<? super E, Boolean> predicate) {
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty()) {
            if (!(this.elementData.length == 0)) {
                int positiveMod = positiveMod(this.head + size());
                int i2 = this.head;
                if (this.head < positiveMod) {
                    for (int i3 = this.head; i3 < positiveMod; i3++) {
                        Object obj = this.elementData[i3];
                        if (predicate.invoke(obj).booleanValue()) {
                            this.elementData[i2] = obj;
                            i2++;
                        } else {
                            z = true;
                        }
                    }
                    ArraysKt___ArraysJvmKt.fill(this.elementData, (Object) null, i2, positiveMod);
                } else {
                    int length = this.elementData.length;
                    boolean z2 = false;
                    for (int i4 = this.head; i4 < length; i4++) {
                        Object obj2 = this.elementData[i4];
                        this.elementData[i4] = null;
                        if (predicate.invoke(obj2).booleanValue()) {
                            this.elementData[i2] = obj2;
                            i2++;
                        } else {
                            z2 = true;
                        }
                    }
                    i2 = positiveMod(i2);
                    for (int i5 = 0; i5 < positiveMod; i5++) {
                        Object obj3 = this.elementData[i5];
                        this.elementData[i5] = null;
                        if (predicate.invoke(obj3).booleanValue()) {
                            this.elementData[i2] = obj3;
                            i2 = incremented(i2);
                        } else {
                            z2 = true;
                        }
                    }
                    z = z2;
                }
                if (z) {
                    this.size = negativeMod(i2 - this.head);
                }
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int incremented(int index) {
        if (index == ArraysKt___ArraysKt.getLastIndex(this.elementData)) {
            return 0;
        }
        return index + 1;
    }

    @InlineOnly
    private final E internalGet(int internalIndex) {
        return (E) this.elementData[internalIndex];
    }

    @InlineOnly
    private final int internalIndex(int index) {
        return positiveMod(this.head + index);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int negativeMod(int index) {
        return index < 0 ? index + this.elementData.length : index;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int positiveMod(int index) {
        Object[] objArr = this.elementData;
        return index >= objArr.length ? index - objArr.length : index;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E element) {
        addLast(element);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(@NotNull Collection<? extends E> elements) {
        if (elements.isEmpty()) {
            return false;
        }
        ensureCapacity(size() + elements.size());
        copyCollectionElements(positiveMod(this.head + size()), elements);
        return true;
    }

    public final void addFirst(E element) {
        ensureCapacity(size() + 1);
        int decremented = decremented(this.head);
        this.head = decremented;
        this.elementData[decremented] = element;
        this.size = size() + 1;
    }

    public final void addLast(E element) {
        ensureCapacity(size() + 1);
        this.elementData[positiveMod(this.head + size())] = element;
        this.size = size() + 1;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        int positiveMod = positiveMod(this.head + size());
        int i2 = this.head;
        if (i2 < positiveMod) {
            ArraysKt___ArraysJvmKt.fill(this.elementData, (Object) null, i2, positiveMod);
        } else if ((!isEmpty()) != false) {
            Object[] objArr = this.elementData;
            ArraysKt___ArraysJvmKt.fill(objArr, (Object) null, this.head, objArr.length);
            ArraysKt___ArraysJvmKt.fill(this.elementData, (Object) null, 0, positiveMod);
        }
        this.head = 0;
        this.size = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(Object element) {
        return indexOf(element) != -1;
    }

    public final E first() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        return (E) this.elementData[this.head];
    }

    @Nullable
    public final E firstOrNull() {
        if (isEmpty()) {
            return null;
        }
        return (E) this.elementData[this.head];
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int index) {
        AbstractList.INSTANCE.checkElementIndex$kotlin_stdlib(index, size());
        return (E) this.elementData[positiveMod(this.head + index)];
    }

    @Override // kotlin.collections.AbstractMutableList
    public int getSize() {
        return this.size;
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object element) {
        int i2;
        int positiveMod = positiveMod(this.head + size());
        int i3 = this.head;
        if (i3 < positiveMod) {
            while (i3 < positiveMod) {
                if (Intrinsics.areEqual(element, this.elementData[i3])) {
                    i2 = this.head;
                } else {
                    i3++;
                }
            }
            return -1;
        } else if (i3 < positiveMod) {
            return -1;
        } else {
            int length = this.elementData.length;
            while (true) {
                if (i3 >= length) {
                    for (int i4 = 0; i4 < positiveMod; i4++) {
                        if (Intrinsics.areEqual(element, this.elementData[i4])) {
                            i3 = i4 + this.elementData.length;
                            i2 = this.head;
                        }
                    }
                    return -1;
                } else if (Intrinsics.areEqual(element, this.elementData[i3])) {
                    i2 = this.head;
                    break;
                } else {
                    i3++;
                }
            }
        }
        return i3 - i2;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object[], java.lang.Object] */
    public final void internalStructure$kotlin_stdlib(@NotNull Function2<? super Integer, ? super Object[], Unit> structure) {
        int positiveMod = positiveMod(this.head + size());
        if (isEmpty()) {
            structure.invoke(Integer.valueOf(this.head), new Object[0]);
            return;
        }
        ?? r0 = new Object[size()];
        int i2 = this.head;
        if (i2 < positiveMod) {
            ArraysKt___ArraysJvmKt.copyInto$default(this.elementData, (Object[]) r0, 0, i2, positiveMod, 2, (Object) null);
            structure.invoke(Integer.valueOf(this.head), r0);
            return;
        }
        ArraysKt___ArraysJvmKt.copyInto$default(this.elementData, (Object[]) r0, 0, i2, 0, 10, (Object) null);
        Object[] objArr = this.elementData;
        ArraysKt___ArraysJvmKt.copyInto(objArr, (Object[]) r0, objArr.length - this.head, 0, positiveMod);
        structure.invoke(Integer.valueOf(this.head - this.elementData.length), r0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        return size() == 0;
    }

    public final E last() {
        int lastIndex;
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        lastIndex = CollectionsKt__CollectionsKt.getLastIndex(this);
        return (E) this.elementData[positiveMod(this.head + lastIndex)];
    }

    @Override // java.util.AbstractList, java.util.List
    public int lastIndexOf(Object element) {
        int lastIndex;
        int i2;
        int positiveMod = positiveMod(this.head + size());
        int i3 = this.head;
        if (i3 < positiveMod) {
            lastIndex = positiveMod - 1;
            if (lastIndex < i3) {
                return -1;
            }
            while (!Intrinsics.areEqual(element, this.elementData[lastIndex])) {
                if (lastIndex == i3) {
                    return -1;
                }
                lastIndex--;
            }
            i2 = this.head;
        } else if (i3 <= positiveMod) {
            return -1;
        } else {
            int i4 = positiveMod - 1;
            while (true) {
                if (i4 >= 0) {
                    if (Intrinsics.areEqual(element, this.elementData[i4])) {
                        lastIndex = i4 + this.elementData.length;
                        i2 = this.head;
                        break;
                    }
                    i4--;
                } else {
                    lastIndex = ArraysKt___ArraysKt.getLastIndex(this.elementData);
                    int i5 = this.head;
                    if (lastIndex < i5) {
                        return -1;
                    }
                    while (!Intrinsics.areEqual(element, this.elementData[lastIndex])) {
                        if (lastIndex == i5) {
                            return -1;
                        }
                        lastIndex--;
                    }
                    i2 = this.head;
                }
            }
        }
        return lastIndex - i2;
    }

    @Nullable
    public final E lastOrNull() {
        int lastIndex;
        if (isEmpty()) {
            return null;
        }
        lastIndex = CollectionsKt__CollectionsKt.getLastIndex(this);
        return (E) this.elementData[positiveMod(this.head + lastIndex)];
    }

    public final int newCapacity$kotlin_stdlib(int oldCapacity, int minCapacity) {
        int i2 = oldCapacity + (oldCapacity >> 1);
        if (i2 - minCapacity < 0) {
            i2 = minCapacity;
        }
        return i2 - 2147483639 > 0 ? minCapacity > 2147483639 ? Integer.MAX_VALUE : 2147483639 : i2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object element) {
        int indexOf = indexOf(element);
        if (indexOf == -1) {
            return false;
        }
        remove(indexOf);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(@NotNull Collection<? extends Object> elements) {
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty()) {
            if (!(this.elementData.length == 0)) {
                int positiveMod = positiveMod(this.head + size());
                int i2 = this.head;
                if (this.head < positiveMod) {
                    for (int i3 = this.head; i3 < positiveMod; i3++) {
                        Object obj = this.elementData[i3];
                        if ((!elements.contains(obj)) == true) {
                            this.elementData[i2] = obj;
                            i2++;
                        } else {
                            z = true;
                        }
                    }
                    ArraysKt___ArraysJvmKt.fill(this.elementData, (Object) null, i2, positiveMod);
                } else {
                    int length = this.elementData.length;
                    boolean z2 = false;
                    for (int i4 = this.head; i4 < length; i4++) {
                        Object obj2 = this.elementData[i4];
                        this.elementData[i4] = null;
                        if ((!elements.contains(obj2)) == true) {
                            this.elementData[i2] = obj2;
                            i2++;
                        } else {
                            z2 = true;
                        }
                    }
                    i2 = positiveMod(i2);
                    for (int i5 = 0; i5 < positiveMod; i5++) {
                        Object obj3 = this.elementData[i5];
                        this.elementData[i5] = null;
                        if ((!elements.contains(obj3)) == true) {
                            this.elementData[i2] = obj3;
                            i2 = incremented(i2);
                        } else {
                            z2 = true;
                        }
                    }
                    z = z2;
                }
                if (z) {
                    this.size = negativeMod(i2 - this.head);
                }
            }
        }
        return z;
    }

    @Override // kotlin.collections.AbstractMutableList
    public E removeAt(int index) {
        int lastIndex;
        int lastIndex2;
        AbstractList.INSTANCE.checkElementIndex$kotlin_stdlib(index, size());
        lastIndex = CollectionsKt__CollectionsKt.getLastIndex(this);
        if (index == lastIndex) {
            return removeLast();
        }
        if (index != 0) {
            int positiveMod = positiveMod(this.head + index);
            E e2 = (E) this.elementData[positiveMod];
            if (index < (size() >> 1)) {
                int i2 = this.head;
                if (positiveMod >= i2) {
                    Object[] objArr = this.elementData;
                    ArraysKt___ArraysJvmKt.copyInto(objArr, objArr, i2 + 1, i2, positiveMod);
                } else {
                    Object[] objArr2 = this.elementData;
                    ArraysKt___ArraysJvmKt.copyInto(objArr2, objArr2, 1, 0, positiveMod);
                    Object[] objArr3 = this.elementData;
                    objArr3[0] = objArr3[objArr3.length - 1];
                    int i3 = this.head;
                    ArraysKt___ArraysJvmKt.copyInto(objArr3, objArr3, i3 + 1, i3, objArr3.length - 1);
                }
                Object[] objArr4 = this.elementData;
                int i4 = this.head;
                objArr4[i4] = null;
                this.head = incremented(i4);
            } else {
                lastIndex2 = CollectionsKt__CollectionsKt.getLastIndex(this);
                int positiveMod2 = positiveMod(this.head + lastIndex2);
                if (positiveMod <= positiveMod2) {
                    Object[] objArr5 = this.elementData;
                    ArraysKt___ArraysJvmKt.copyInto(objArr5, objArr5, positiveMod, positiveMod + 1, positiveMod2 + 1);
                } else {
                    Object[] objArr6 = this.elementData;
                    ArraysKt___ArraysJvmKt.copyInto(objArr6, objArr6, positiveMod, positiveMod + 1, objArr6.length);
                    Object[] objArr7 = this.elementData;
                    objArr7[objArr7.length - 1] = objArr7[0];
                    ArraysKt___ArraysJvmKt.copyInto(objArr7, objArr7, 0, 1, positiveMod2 + 1);
                }
                this.elementData[positiveMod2] = null;
            }
            this.size = size() - 1;
            return e2;
        }
        return removeFirst();
    }

    public final E removeFirst() {
        if (!isEmpty()) {
            E e2 = (E) this.elementData[this.head];
            Object[] objArr = this.elementData;
            int i2 = this.head;
            objArr[i2] = null;
            this.head = incremented(i2);
            this.size = size() - 1;
            return e2;
        }
        throw new NoSuchElementException("ArrayDeque is empty.");
    }

    @Nullable
    public final E removeFirstOrNull() {
        if (isEmpty()) {
            return null;
        }
        return removeFirst();
    }

    public final E removeLast() {
        int lastIndex;
        if (!isEmpty()) {
            lastIndex = CollectionsKt__CollectionsKt.getLastIndex(this);
            int positiveMod = positiveMod(this.head + lastIndex);
            E e2 = (E) this.elementData[positiveMod];
            this.elementData[positiveMod] = null;
            this.size = size() - 1;
            return e2;
        }
        throw new NoSuchElementException("ArrayDeque is empty.");
    }

    @Nullable
    public final E removeLastOrNull() {
        if (isEmpty()) {
            return null;
        }
        return removeLast();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(@NotNull Collection<? extends Object> elements) {
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty()) {
            if (!(this.elementData.length == 0)) {
                int positiveMod = positiveMod(this.head + size());
                int i2 = this.head;
                if (this.head < positiveMod) {
                    for (int i3 = this.head; i3 < positiveMod; i3++) {
                        Object obj = this.elementData[i3];
                        if (elements.contains(obj)) {
                            this.elementData[i2] = obj;
                            i2++;
                        } else {
                            z = true;
                        }
                    }
                    ArraysKt___ArraysJvmKt.fill(this.elementData, (Object) null, i2, positiveMod);
                } else {
                    int length = this.elementData.length;
                    boolean z2 = false;
                    for (int i4 = this.head; i4 < length; i4++) {
                        Object obj2 = this.elementData[i4];
                        this.elementData[i4] = null;
                        if (elements.contains(obj2)) {
                            this.elementData[i2] = obj2;
                            i2++;
                        } else {
                            z2 = true;
                        }
                    }
                    i2 = positiveMod(i2);
                    for (int i5 = 0; i5 < positiveMod; i5++) {
                        Object obj3 = this.elementData[i5];
                        this.elementData[i5] = null;
                        if (elements.contains(obj3)) {
                            this.elementData[i2] = obj3;
                            i2 = incremented(i2);
                        } else {
                            z2 = true;
                        }
                    }
                    z = z2;
                }
                if (z) {
                    this.size = negativeMod(i2 - this.head);
                }
            }
        }
        return z;
    }

    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    public E set(int index, E element) {
        AbstractList.INSTANCE.checkElementIndex$kotlin_stdlib(index, size());
        int positiveMod = positiveMod(this.head + index);
        E e2 = (E) this.elementData[positiveMod];
        this.elementData[positiveMod] = element;
        return e2;
    }

    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    public void add(int index, E element) {
        AbstractList.INSTANCE.checkPositionIndex$kotlin_stdlib(index, size());
        if (index == size()) {
            addLast(element);
        } else if (index == 0) {
            addFirst(element);
        } else {
            ensureCapacity(size() + 1);
            int positiveMod = positiveMod(this.head + index);
            if (index < ((size() + 1) >> 1)) {
                int decremented = decremented(positiveMod);
                int decremented2 = decremented(this.head);
                int i2 = this.head;
                if (decremented >= i2) {
                    Object[] objArr = this.elementData;
                    objArr[decremented2] = objArr[i2];
                    ArraysKt___ArraysJvmKt.copyInto(objArr, objArr, i2, i2 + 1, decremented + 1);
                } else {
                    Object[] objArr2 = this.elementData;
                    ArraysKt___ArraysJvmKt.copyInto(objArr2, objArr2, i2 - 1, i2, objArr2.length);
                    Object[] objArr3 = this.elementData;
                    objArr3[objArr3.length - 1] = objArr3[0];
                    ArraysKt___ArraysJvmKt.copyInto(objArr3, objArr3, 0, 1, decremented + 1);
                }
                this.elementData[decremented] = element;
                this.head = decremented2;
            } else {
                int positiveMod2 = positiveMod(this.head + size());
                if (positiveMod < positiveMod2) {
                    Object[] objArr4 = this.elementData;
                    ArraysKt___ArraysJvmKt.copyInto(objArr4, objArr4, positiveMod + 1, positiveMod, positiveMod2);
                } else {
                    Object[] objArr5 = this.elementData;
                    ArraysKt___ArraysJvmKt.copyInto(objArr5, objArr5, 1, 0, positiveMod2);
                    Object[] objArr6 = this.elementData;
                    objArr6[0] = objArr6[objArr6.length - 1];
                    ArraysKt___ArraysJvmKt.copyInto(objArr6, objArr6, positiveMod + 1, positiveMod, objArr6.length - 1);
                }
                this.elementData[positiveMod] = element;
            }
            this.size = size() + 1;
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int index, @NotNull Collection<? extends E> elements) {
        AbstractList.INSTANCE.checkPositionIndex$kotlin_stdlib(index, size());
        if (elements.isEmpty()) {
            return false;
        }
        if (index == size()) {
            return addAll(elements);
        }
        ensureCapacity(size() + elements.size());
        int positiveMod = positiveMod(this.head + size());
        int positiveMod2 = positiveMod(this.head + index);
        int size = elements.size();
        if (index < ((size() + 1) >> 1)) {
            int i2 = this.head;
            int i3 = i2 - size;
            if (positiveMod2 < i2) {
                Object[] objArr = this.elementData;
                ArraysKt___ArraysJvmKt.copyInto(objArr, objArr, i3, i2, objArr.length);
                if (size >= positiveMod2) {
                    Object[] objArr2 = this.elementData;
                    ArraysKt___ArraysJvmKt.copyInto(objArr2, objArr2, objArr2.length - size, 0, positiveMod2);
                } else {
                    Object[] objArr3 = this.elementData;
                    ArraysKt___ArraysJvmKt.copyInto(objArr3, objArr3, objArr3.length - size, 0, size);
                    Object[] objArr4 = this.elementData;
                    ArraysKt___ArraysJvmKt.copyInto(objArr4, objArr4, 0, size, positiveMod2);
                }
            } else if (i3 >= 0) {
                Object[] objArr5 = this.elementData;
                ArraysKt___ArraysJvmKt.copyInto(objArr5, objArr5, i3, i2, positiveMod2);
            } else {
                Object[] objArr6 = this.elementData;
                i3 += objArr6.length;
                int i4 = positiveMod2 - i2;
                int length = objArr6.length - i3;
                if (length >= i4) {
                    ArraysKt___ArraysJvmKt.copyInto(objArr6, objArr6, i3, i2, positiveMod2);
                } else {
                    ArraysKt___ArraysJvmKt.copyInto(objArr6, objArr6, i3, i2, i2 + length);
                    Object[] objArr7 = this.elementData;
                    ArraysKt___ArraysJvmKt.copyInto(objArr7, objArr7, 0, this.head + length, positiveMod2);
                }
            }
            this.head = i3;
            copyCollectionElements(negativeMod(positiveMod2 - size), elements);
        } else {
            int i5 = positiveMod2 + size;
            if (positiveMod2 < positiveMod) {
                int i6 = size + positiveMod;
                Object[] objArr8 = this.elementData;
                if (i6 <= objArr8.length) {
                    ArraysKt___ArraysJvmKt.copyInto(objArr8, objArr8, i5, positiveMod2, positiveMod);
                } else if (i5 >= objArr8.length) {
                    ArraysKt___ArraysJvmKt.copyInto(objArr8, objArr8, i5 - objArr8.length, positiveMod2, positiveMod);
                } else {
                    int length2 = positiveMod - (i6 - objArr8.length);
                    ArraysKt___ArraysJvmKt.copyInto(objArr8, objArr8, 0, length2, positiveMod);
                    Object[] objArr9 = this.elementData;
                    ArraysKt___ArraysJvmKt.copyInto(objArr9, objArr9, i5, positiveMod2, length2);
                }
            } else {
                Object[] objArr10 = this.elementData;
                ArraysKt___ArraysJvmKt.copyInto(objArr10, objArr10, size, 0, positiveMod);
                Object[] objArr11 = this.elementData;
                if (i5 >= objArr11.length) {
                    ArraysKt___ArraysJvmKt.copyInto(objArr11, objArr11, i5 - objArr11.length, positiveMod2, objArr11.length);
                } else {
                    ArraysKt___ArraysJvmKt.copyInto(objArr11, objArr11, 0, objArr11.length - size, objArr11.length);
                    Object[] objArr12 = this.elementData;
                    ArraysKt___ArraysJvmKt.copyInto(objArr12, objArr12, i5, positiveMod2, objArr12.length - size);
                }
            }
            copyCollectionElements(positiveMod2, elements);
        }
        return true;
    }

    public ArrayDeque() {
        Object[] objArr;
        objArr = ArrayDequeKt.emptyElementData;
        this.elementData = objArr;
    }

    public ArrayDeque(@NotNull Collection<? extends E> collection) {
        Object[] objArr;
        Object[] array = collection.toArray(new Object[0]);
        if (array != null) {
            this.elementData = array;
            this.size = array.length;
            if (array.length == 0) {
                objArr = ArrayDequeKt.emptyElementData;
                this.elementData = objArr;
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }
}
