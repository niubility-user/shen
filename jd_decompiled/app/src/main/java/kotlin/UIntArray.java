package kotlin;

import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.UIntIterator;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\f\b\u0087@\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u00011B\u0014\b\u0001\u0012\u0006\u0010'\u001a\u00020&\u00f8\u0001\u0000\u00a2\u0006\u0004\b.\u0010/B\u0014\b\u0016\u0012\u0006\u0010-\u001a\u00020\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b.\u00100J\u001b\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0003H\u0086\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0006J#\u0010\f\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0002H\u0086\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u0010\u001a\u00020\rH\u0096\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0002H\u0096\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0013\u0010\u0014J \u0010\u0019\u001a\u00020\u00122\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0016\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u001c\u001a\u00020\u0012H\u0016\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001e\u001a\u00020\u001dH\u00d6\u0001\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020\u0003H\u00d6\u0001\u00a2\u0006\u0004\b \u0010!J\u001a\u0010$\u001a\u00020\u00122\b\u0010#\u001a\u0004\u0018\u00010\"H\u00d6\u0003\u00a2\u0006\u0004\b$\u0010%R\u001c\u0010'\u001a\u00020&8\u0000@\u0001X\u0081\u0004\u00a2\u0006\f\n\u0004\b'\u0010(\u0012\u0004\b)\u0010*R\u0016\u0010-\u001a\u00020\u00038V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b+\u0010,\u00f8\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00062"}, d2 = {"Lkotlin/UIntArray;", "", "Lkotlin/UInt;", "", "index", "get-impl", "([II)I", IMantoServerRequester.GET, "value", "", "set-VXSXFK8", "([III)V", "set", "Lkotlin/collections/UIntIterator;", "iterator-impl", "([I)Lkotlin/collections/UIntIterator;", "iterator", "element", "", "contains-WZ4Q5Ns", "([II)Z", "contains", "elements", "containsAll-impl", "([ILjava/util/Collection;)Z", "containsAll", "isEmpty-impl", "([I)Z", CartConstant.KEY_GLOBAL_IS_EMPTY, "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "", "other", "equals", "(Ljava/lang/Object;)Z", "", IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, "[I", "storage$annotations", "()V", "getSize-impl", "([I)I", ApkDownloadTable.FIELD_SIZE, "constructor-impl", "([I)[I", "(I)[I", "Iterator", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
@ExperimentalUnsignedTypes
/* loaded from: classes11.dex */
public final class UIntArray implements Collection<UInt>, KMappedMarker {
    @NotNull
    private final int[] storage;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0003\u001a\u00020\u0002H\u0096\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0006\u001a\u00020\u0005H\u0016\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0016\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\t\u0010\nR\u0016\u0010\f\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\f\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"Lkotlin/UIntArray$Iterator;", "Lkotlin/collections/UIntIterator;", "", "hasNext", "()Z", "Lkotlin/UInt;", "nextUInt", "()I", "", "array", "[I", "", "index", "I", "<init>", "([I)V", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes11.dex */
    public static final class Iterator extends UIntIterator {
        private final int[] array;
        private int index;

        public Iterator(@NotNull int[] iArr) {
            this.array = iArr;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.index < this.array.length;
        }

        @Override // kotlin.collections.UIntIterator
        public int nextUInt() {
            int i2 = this.index;
            int[] iArr = this.array;
            if (i2 < iArr.length) {
                this.index = i2 + 1;
                return UInt.m285constructorimpl(iArr[i2]);
            }
            throw new NoSuchElementException(String.valueOf(this.index));
        }
    }

    @PublishedApi
    private /* synthetic */ UIntArray(@NotNull int[] iArr) {
        this.storage = iArr;
    }

    @NotNull
    /* renamed from: box-impl */
    public static final /* synthetic */ UIntArray m329boximpl(@NotNull int[] iArr) {
        return new UIntArray(iArr);
    }

    @NotNull
    /* renamed from: constructor-impl */
    public static int[] m330constructorimpl(int i2) {
        return m331constructorimpl(new int[i2]);
    }

    @PublishedApi
    @NotNull
    /* renamed from: constructor-impl */
    public static int[] m331constructorimpl(@NotNull int[] iArr) {
        return iArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x002d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:? A[LOOP:0: B:28:0x000e->B:42:?, LOOP_END, SYNTHETIC] */
    /* renamed from: containsAll-impl */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean m333containsAllimpl(int[] r4, @org.jetbrains.annotations.NotNull java.util.Collection<kotlin.UInt> r5) {
        /*
            boolean r0 = r5.isEmpty()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto La
        L8:
            r1 = 1
            goto L2d
        La:
            java.util.Iterator r5 = r5.iterator()
        Le:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L8
            java.lang.Object r0 = r5.next()
            boolean r3 = r0 instanceof kotlin.UInt
            if (r3 == 0) goto L2a
            kotlin.UInt r0 = (kotlin.UInt) r0
            int r0 = r0.getData()
            boolean r0 = kotlin.collections.ArraysKt.contains(r4, r0)
            if (r0 == 0) goto L2a
            r0 = 1
            goto L2b
        L2a:
            r0 = 0
        L2b:
            if (r0 != 0) goto Le
        L2d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.UIntArray.m333containsAllimpl(int[], java.util.Collection):boolean");
    }

    /* renamed from: equals-impl */
    public static boolean m334equalsimpl(int[] iArr, @Nullable Object obj) {
        return (obj instanceof UIntArray) && Intrinsics.areEqual(iArr, ((UIntArray) obj).getStorage());
    }

    /* renamed from: equals-impl0 */
    public static final boolean m335equalsimpl0(@NotNull int[] iArr, @NotNull int[] iArr2) {
        return Intrinsics.areEqual(iArr, iArr2);
    }

    /* renamed from: get-impl */
    public static final int m336getimpl(int[] iArr, int i2) {
        return UInt.m285constructorimpl(iArr[i2]);
    }

    /* renamed from: getSize-impl */
    public static int m337getSizeimpl(int[] iArr) {
        return iArr.length;
    }

    /* renamed from: hashCode-impl */
    public static int m338hashCodeimpl(int[] iArr) {
        if (iArr != null) {
            return Arrays.hashCode(iArr);
        }
        return 0;
    }

    /* renamed from: isEmpty-impl */
    public static boolean m339isEmptyimpl(int[] iArr) {
        return iArr.length == 0;
    }

    @NotNull
    /* renamed from: iterator-impl */
    public static UIntIterator m340iteratorimpl(int[] iArr) {
        return new Iterator(iArr);
    }

    /* renamed from: set-VXSXFK8 */
    public static final void m341setVXSXFK8(int[] iArr, int i2, int i3) {
        iArr[i2] = i3;
    }

    @PublishedApi
    public static /* synthetic */ void storage$annotations() {
    }

    @NotNull
    /* renamed from: toString-impl */
    public static String m342toStringimpl(int[] iArr) {
        return "UIntArray(storage=" + Arrays.toString(iArr) + ")";
    }

    @Override // java.util.Collection
    public /* synthetic */ boolean add(UInt uInt) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: add-WZ4Q5Ns */
    public boolean m343addWZ4Q5Ns(int i2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends UInt> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof UInt) {
            return m344containsWZ4Q5Ns(((UInt) obj).getData());
        }
        return false;
    }

    /* renamed from: contains-WZ4Q5Ns */
    public boolean m344containsWZ4Q5Ns(int i2) {
        return m332containsWZ4Q5Ns(this.storage, i2);
    }

    @Override // java.util.Collection
    public boolean containsAll(@NotNull Collection<? extends Object> collection) {
        return m333containsAllimpl(this.storage, collection);
    }

    @Override // java.util.Collection
    public boolean equals(Object other) {
        return m334equalsimpl(this.storage, other);
    }

    public int getSize() {
        return m337getSizeimpl(this.storage);
    }

    @Override // java.util.Collection
    public int hashCode() {
        return m338hashCodeimpl(this.storage);
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return m339isEmptyimpl(this.storage);
    }

    @Override // java.util.Collection, java.lang.Iterable
    @NotNull
    public UIntIterator iterator() {
        return m340iteratorimpl(this.storage);
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) CollectionToArray.toArray(this, tArr);
    }

    public String toString() {
        return m342toStringimpl(this.storage);
    }

    @NotNull
    /* renamed from: unbox-impl  reason: from getter */
    public final /* synthetic */ int[] getStorage() {
        return this.storage;
    }

    /* renamed from: contains-WZ4Q5Ns */
    public static boolean m332containsWZ4Q5Ns(int[] iArr, int i2) {
        boolean contains;
        contains = ArraysKt___ArraysKt.contains(iArr, i2);
        return contains;
    }
}
