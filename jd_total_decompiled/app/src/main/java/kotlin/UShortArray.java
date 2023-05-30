package kotlin;

import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.UShortIterator;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0017\n\u0002\b\t\b\u0087@\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u00011B\u0014\b\u0001\u0012\u0006\u0010*\u001a\u00020)\u00f8\u0001\u0000\u00a2\u0006\u0004\b.\u0010/B\u0014\b\u0016\u0012\u0006\u0010(\u001a\u00020\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b.\u00100J\u001b\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0003H\u0086\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0006J#\u0010\f\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0002H\u0086\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u0010\u001a\u00020\rH\u0096\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0002H\u0096\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0013\u0010\u0014J \u0010\u0019\u001a\u00020\u00122\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0016\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u001c\u001a\u00020\u0012H\u0016\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001e\u001a\u00020\u001dH\u00d6\u0001\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020\u0003H\u00d6\u0001\u00a2\u0006\u0004\b \u0010!J\u001a\u0010$\u001a\u00020\u00122\b\u0010#\u001a\u0004\u0018\u00010\"H\u00d6\u0003\u00a2\u0006\u0004\b$\u0010%R\u0016\u0010(\u001a\u00020\u00038V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b&\u0010'R\u001c\u0010*\u001a\u00020)8\u0000@\u0001X\u0081\u0004\u00a2\u0006\f\n\u0004\b*\u0010+\u0012\u0004\b,\u0010-\u00f8\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00062"}, d2 = {"Lkotlin/UShortArray;", "", "Lkotlin/UShort;", "", "index", "get-impl", "([SI)S", IMantoServerRequester.GET, "value", "", "set-01HTLdE", "([SIS)V", "set", "Lkotlin/collections/UShortIterator;", "iterator-impl", "([S)Lkotlin/collections/UShortIterator;", "iterator", "element", "", "contains-xj2QHRw", "([SS)Z", "contains", "elements", "containsAll-impl", "([SLjava/util/Collection;)Z", "containsAll", "isEmpty-impl", "([S)Z", CartConstant.KEY_GLOBAL_IS_EMPTY, "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "", "other", "equals", "(Ljava/lang/Object;)Z", "getSize-impl", "([S)I", ApkDownloadTable.FIELD_SIZE, "", IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, "[S", "storage$annotations", "()V", "constructor-impl", "([S)[S", "(I)[S", "Iterator", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
@ExperimentalUnsignedTypes
/* loaded from: classes11.dex */
public final class UShortArray implements Collection<UShort>, KMappedMarker {
    @NotNull
    private final short[] storage;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0017\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0003\u001a\u00020\u0002H\u0096\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0006\u001a\u00020\u0005H\u0016\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0016\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\t\u0010\nR\u0016\u0010\f\u001a\u00020\u000b8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\f\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"Lkotlin/UShortArray$Iterator;", "Lkotlin/collections/UShortIterator;", "", "hasNext", "()Z", "Lkotlin/UShort;", "nextUShort", "()S", "", "index", "I", "", "array", "[S", "<init>", "([S)V", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes11.dex */
    public static final class Iterator extends UShortIterator {
        private final short[] array;
        private int index;

        public Iterator(@NotNull short[] sArr) {
            this.array = sArr;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.index < this.array.length;
        }

        @Override // kotlin.collections.UShortIterator
        public short nextUShort() {
            int i2 = this.index;
            short[] sArr = this.array;
            if (i2 < sArr.length) {
                this.index = i2 + 1;
                return UShort.m451constructorimpl(sArr[i2]);
            }
            throw new NoSuchElementException(String.valueOf(this.index));
        }
    }

    @PublishedApi
    private /* synthetic */ UShortArray(@NotNull short[] sArr) {
        this.storage = sArr;
    }

    @NotNull
    /* renamed from: box-impl */
    public static final /* synthetic */ UShortArray m493boximpl(@NotNull short[] sArr) {
        return new UShortArray(sArr);
    }

    @NotNull
    /* renamed from: constructor-impl */
    public static short[] m494constructorimpl(int i2) {
        return m495constructorimpl(new short[i2]);
    }

    @PublishedApi
    @NotNull
    /* renamed from: constructor-impl */
    public static short[] m495constructorimpl(@NotNull short[] sArr) {
        return sArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x002d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:? A[LOOP:0: B:50:0x000e->B:64:?, LOOP_END, SYNTHETIC] */
    /* renamed from: containsAll-impl */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean m497containsAllimpl(short[] sArr, @NotNull Collection<UShort> collection) {
        boolean z;
        boolean contains;
        if (!collection.isEmpty()) {
            for (Object obj : collection) {
                if (obj instanceof UShort) {
                    contains = ArraysKt___ArraysKt.contains(sArr, ((UShort) obj).getData());
                    if (contains) {
                        z = true;
                        if (z) {
                            return false;
                        }
                    }
                }
                z = false;
                if (z) {
                }
            }
        }
        return true;
    }

    /* renamed from: equals-impl */
    public static boolean m498equalsimpl(short[] sArr, @Nullable Object obj) {
        return (obj instanceof UShortArray) && Intrinsics.areEqual(sArr, ((UShortArray) obj).getStorage());
    }

    /* renamed from: equals-impl0 */
    public static final boolean m499equalsimpl0(@NotNull short[] sArr, @NotNull short[] sArr2) {
        return Intrinsics.areEqual(sArr, sArr2);
    }

    /* renamed from: get-impl */
    public static final short m500getimpl(short[] sArr, int i2) {
        return UShort.m451constructorimpl(sArr[i2]);
    }

    /* renamed from: getSize-impl */
    public static int m501getSizeimpl(short[] sArr) {
        return sArr.length;
    }

    /* renamed from: hashCode-impl */
    public static int m502hashCodeimpl(short[] sArr) {
        if (sArr != null) {
            return Arrays.hashCode(sArr);
        }
        return 0;
    }

    /* renamed from: isEmpty-impl */
    public static boolean m503isEmptyimpl(short[] sArr) {
        return sArr.length == 0;
    }

    @NotNull
    /* renamed from: iterator-impl */
    public static UShortIterator m504iteratorimpl(short[] sArr) {
        return new Iterator(sArr);
    }

    /* renamed from: set-01HTLdE */
    public static final void m505set01HTLdE(short[] sArr, int i2, short s) {
        sArr[i2] = s;
    }

    @PublishedApi
    public static /* synthetic */ void storage$annotations() {
    }

    @NotNull
    /* renamed from: toString-impl */
    public static String m506toStringimpl(short[] sArr) {
        return "UShortArray(storage=" + Arrays.toString(sArr) + ")";
    }

    @Override // java.util.Collection
    public /* synthetic */ boolean add(UShort uShort) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: add-xj2QHRw */
    public boolean m507addxj2QHRw(short s) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends UShort> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof UShort) {
            return m508containsxj2QHRw(((UShort) obj).getData());
        }
        return false;
    }

    /* renamed from: contains-xj2QHRw */
    public boolean m508containsxj2QHRw(short s) {
        return m496containsxj2QHRw(this.storage, s);
    }

    @Override // java.util.Collection
    public boolean containsAll(@NotNull Collection<? extends Object> collection) {
        return m497containsAllimpl(this.storage, collection);
    }

    @Override // java.util.Collection
    public boolean equals(Object other) {
        return m498equalsimpl(this.storage, other);
    }

    public int getSize() {
        return m501getSizeimpl(this.storage);
    }

    @Override // java.util.Collection
    public int hashCode() {
        return m502hashCodeimpl(this.storage);
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return m503isEmptyimpl(this.storage);
    }

    @Override // java.util.Collection, java.lang.Iterable
    @NotNull
    public UShortIterator iterator() {
        return m504iteratorimpl(this.storage);
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
        return m506toStringimpl(this.storage);
    }

    @NotNull
    /* renamed from: unbox-impl  reason: from getter */
    public final /* synthetic */ short[] getStorage() {
        return this.storage;
    }

    /* renamed from: contains-xj2QHRw */
    public static boolean m496containsxj2QHRw(short[] sArr, short s) {
        boolean contains;
        contains = ArraysKt___ArraysKt.contains(sArr, s);
        return contains;
    }
}
