package kotlin;

import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.ULongIterator;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0016\n\u0002\b\t\b\u0087@\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u00011B\u0014\b\u0001\u0012\u0006\u0010*\u001a\u00020)\u00f8\u0001\u0000\u00a2\u0006\u0004\b.\u0010/B\u0014\b\u0016\u0012\u0006\u0010(\u001a\u00020\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b.\u00100J\u001b\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0003H\u0086\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0006J#\u0010\f\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0002H\u0086\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u0010\u001a\u00020\rH\u0096\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0002H\u0096\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0013\u0010\u0014J \u0010\u0019\u001a\u00020\u00122\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0016\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u001c\u001a\u00020\u0012H\u0016\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001e\u001a\u00020\u001dH\u00d6\u0001\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020\u0003H\u00d6\u0001\u00a2\u0006\u0004\b \u0010!J\u001a\u0010$\u001a\u00020\u00122\b\u0010#\u001a\u0004\u0018\u00010\"H\u00d6\u0003\u00a2\u0006\u0004\b$\u0010%R\u0016\u0010(\u001a\u00020\u00038V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b&\u0010'R\u001c\u0010*\u001a\u00020)8\u0000@\u0001X\u0081\u0004\u00a2\u0006\f\n\u0004\b*\u0010+\u0012\u0004\b,\u0010-\u00f8\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00062"}, d2 = {"Lkotlin/ULongArray;", "", "Lkotlin/ULong;", "", "index", "get-impl", "([JI)J", IMantoServerRequester.GET, "value", "", "set-k8EXiF4", "([JIJ)V", "set", "Lkotlin/collections/ULongIterator;", "iterator-impl", "([J)Lkotlin/collections/ULongIterator;", "iterator", "element", "", "contains-VKZWuLQ", "([JJ)Z", "contains", "elements", "containsAll-impl", "([JLjava/util/Collection;)Z", "containsAll", "isEmpty-impl", "([J)Z", CartConstant.KEY_GLOBAL_IS_EMPTY, "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "", "other", "equals", "(Ljava/lang/Object;)Z", "getSize-impl", "([J)I", ApkDownloadTable.FIELD_SIZE, "", IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, "[J", "storage$annotations", "()V", "constructor-impl", "([J)[J", "(I)[J", "Iterator", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
@ExperimentalUnsignedTypes
/* loaded from: classes11.dex */
public final class ULongArray implements Collection<ULong>, KMappedMarker {
    @NotNull
    private final long[] storage;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0003\u001a\u00020\u0002H\u0096\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0006\u001a\u00020\u0005H\u0016\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0016\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\t\u0010\nR\u0016\u0010\f\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\f\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"Lkotlin/ULongArray$Iterator;", "Lkotlin/collections/ULongIterator;", "", "hasNext", "()Z", "Lkotlin/ULong;", "nextULong", "()J", "", "array", "[J", "", "index", "I", "<init>", "([J)V", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes11.dex */
    public static final class Iterator extends ULongIterator {
        private final long[] array;
        private int index;

        public Iterator(@NotNull long[] jArr) {
            this.array = jArr;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.index < this.array.length;
        }

        @Override // kotlin.collections.ULongIterator
        public long nextULong() {
            int i2 = this.index;
            long[] jArr = this.array;
            if (i2 < jArr.length) {
                this.index = i2 + 1;
                return ULong.m354constructorimpl(jArr[i2]);
            }
            throw new NoSuchElementException(String.valueOf(this.index));
        }
    }

    @PublishedApi
    private /* synthetic */ ULongArray(@NotNull long[] jArr) {
        this.storage = jArr;
    }

    @NotNull
    /* renamed from: box-impl */
    public static final /* synthetic */ ULongArray m398boximpl(@NotNull long[] jArr) {
        return new ULongArray(jArr);
    }

    @NotNull
    /* renamed from: constructor-impl */
    public static long[] m399constructorimpl(int i2) {
        return m400constructorimpl(new long[i2]);
    }

    @PublishedApi
    @NotNull
    /* renamed from: constructor-impl */
    public static long[] m400constructorimpl(@NotNull long[] jArr) {
        return jArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x002d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:? A[LOOP:0: B:28:0x000e->B:42:?, LOOP_END, SYNTHETIC] */
    /* renamed from: containsAll-impl */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean m402containsAllimpl(long[] r5, @org.jetbrains.annotations.NotNull java.util.Collection<kotlin.ULong> r6) {
        /*
            boolean r0 = r6.isEmpty()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto La
        L8:
            r1 = 1
            goto L2d
        La:
            java.util.Iterator r6 = r6.iterator()
        Le:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L8
            java.lang.Object r0 = r6.next()
            boolean r3 = r0 instanceof kotlin.ULong
            if (r3 == 0) goto L2a
            kotlin.ULong r0 = (kotlin.ULong) r0
            long r3 = r0.getData()
            boolean r0 = kotlin.collections.ArraysKt.contains(r5, r3)
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
        throw new UnsupportedOperationException("Method not decompiled: kotlin.ULongArray.m402containsAllimpl(long[], java.util.Collection):boolean");
    }

    /* renamed from: equals-impl */
    public static boolean m403equalsimpl(long[] jArr, @Nullable Object obj) {
        return (obj instanceof ULongArray) && Intrinsics.areEqual(jArr, ((ULongArray) obj).getStorage());
    }

    /* renamed from: equals-impl0 */
    public static final boolean m404equalsimpl0(@NotNull long[] jArr, @NotNull long[] jArr2) {
        return Intrinsics.areEqual(jArr, jArr2);
    }

    /* renamed from: get-impl */
    public static final long m405getimpl(long[] jArr, int i2) {
        return ULong.m354constructorimpl(jArr[i2]);
    }

    /* renamed from: getSize-impl */
    public static int m406getSizeimpl(long[] jArr) {
        return jArr.length;
    }

    /* renamed from: hashCode-impl */
    public static int m407hashCodeimpl(long[] jArr) {
        if (jArr != null) {
            return Arrays.hashCode(jArr);
        }
        return 0;
    }

    /* renamed from: isEmpty-impl */
    public static boolean m408isEmptyimpl(long[] jArr) {
        return jArr.length == 0;
    }

    @NotNull
    /* renamed from: iterator-impl */
    public static ULongIterator m409iteratorimpl(long[] jArr) {
        return new Iterator(jArr);
    }

    /* renamed from: set-k8EXiF4 */
    public static final void m410setk8EXiF4(long[] jArr, int i2, long j2) {
        jArr[i2] = j2;
    }

    @PublishedApi
    public static /* synthetic */ void storage$annotations() {
    }

    @NotNull
    /* renamed from: toString-impl */
    public static String m411toStringimpl(long[] jArr) {
        return "ULongArray(storage=" + Arrays.toString(jArr) + ")";
    }

    @Override // java.util.Collection
    public /* synthetic */ boolean add(ULong uLong) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: add-VKZWuLQ */
    public boolean m412addVKZWuLQ(long j2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends ULong> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof ULong) {
            return m413containsVKZWuLQ(((ULong) obj).getData());
        }
        return false;
    }

    /* renamed from: contains-VKZWuLQ */
    public boolean m413containsVKZWuLQ(long j2) {
        return m401containsVKZWuLQ(this.storage, j2);
    }

    @Override // java.util.Collection
    public boolean containsAll(@NotNull Collection<? extends Object> collection) {
        return m402containsAllimpl(this.storage, collection);
    }

    @Override // java.util.Collection
    public boolean equals(Object other) {
        return m403equalsimpl(this.storage, other);
    }

    public int getSize() {
        return m406getSizeimpl(this.storage);
    }

    @Override // java.util.Collection
    public int hashCode() {
        return m407hashCodeimpl(this.storage);
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return m408isEmptyimpl(this.storage);
    }

    @Override // java.util.Collection, java.lang.Iterable
    @NotNull
    public ULongIterator iterator() {
        return m409iteratorimpl(this.storage);
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
        return m411toStringimpl(this.storage);
    }

    @NotNull
    /* renamed from: unbox-impl  reason: from getter */
    public final /* synthetic */ long[] getStorage() {
        return this.storage;
    }

    /* renamed from: contains-VKZWuLQ */
    public static boolean m401containsVKZWuLQ(long[] jArr, long j2) {
        boolean contains;
        contains = ArraysKt___ArraysKt.contains(jArr, j2);
        return contains;
    }
}
