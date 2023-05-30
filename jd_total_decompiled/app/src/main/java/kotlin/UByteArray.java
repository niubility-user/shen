package kotlin;

import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.UByteIterator;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\f\b\u0087@\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u00011B\u0014\b\u0001\u0012\u0006\u0010'\u001a\u00020&\u00f8\u0001\u0000\u00a2\u0006\u0004\b.\u0010/B\u0014\b\u0016\u0012\u0006\u0010-\u001a\u00020\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b.\u00100J\u001b\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0003H\u0086\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0006J#\u0010\f\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0002H\u0086\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u0010\u001a\u00020\rH\u0096\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0002H\u0096\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0013\u0010\u0014J \u0010\u0019\u001a\u00020\u00122\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0016\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u001c\u001a\u00020\u0012H\u0016\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001e\u001a\u00020\u001dH\u00d6\u0001\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020\u0003H\u00d6\u0001\u00a2\u0006\u0004\b \u0010!J\u001a\u0010$\u001a\u00020\u00122\b\u0010#\u001a\u0004\u0018\u00010\"H\u00d6\u0003\u00a2\u0006\u0004\b$\u0010%R\u001c\u0010'\u001a\u00020&8\u0000@\u0001X\u0081\u0004\u00a2\u0006\f\n\u0004\b'\u0010(\u0012\u0004\b)\u0010*R\u0016\u0010-\u001a\u00020\u00038V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b+\u0010,\u00f8\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00062"}, d2 = {"Lkotlin/UByteArray;", "", "Lkotlin/UByte;", "", "index", "get-impl", "([BI)B", IMantoServerRequester.GET, "value", "", "set-VurrAj0", "([BIB)V", "set", "Lkotlin/collections/UByteIterator;", "iterator-impl", "([B)Lkotlin/collections/UByteIterator;", "iterator", "element", "", "contains-7apg3OU", "([BB)Z", "contains", "elements", "containsAll-impl", "([BLjava/util/Collection;)Z", "containsAll", "isEmpty-impl", "([B)Z", CartConstant.KEY_GLOBAL_IS_EMPTY, "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "", "other", "equals", "(Ljava/lang/Object;)Z", "", IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, "[B", "storage$annotations", "()V", "getSize-impl", "([B)I", ApkDownloadTable.FIELD_SIZE, "constructor-impl", "([B)[B", "(I)[B", "Iterator", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
@ExperimentalUnsignedTypes
/* loaded from: classes11.dex */
public final class UByteArray implements Collection<UByte>, KMappedMarker {
    @NotNull
    private final byte[] storage;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0003\u001a\u00020\u0002H\u0096\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0006\u001a\u00020\u0005H\u0016\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0016\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\t\u0010\nR\u0016\u0010\f\u001a\u00020\u000b8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\f\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"Lkotlin/UByteArray$Iterator;", "Lkotlin/collections/UByteIterator;", "", "hasNext", "()Z", "Lkotlin/UByte;", "nextUByte", "()B", "", "index", "I", "", "array", "[B", "<init>", "([B)V", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes11.dex */
    public static final class Iterator extends UByteIterator {
        private final byte[] array;
        private int index;

        public Iterator(@NotNull byte[] bArr) {
            this.array = bArr;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.index < this.array.length;
        }

        @Override // kotlin.collections.UByteIterator
        public byte nextUByte() {
            int i2 = this.index;
            byte[] bArr = this.array;
            if (i2 < bArr.length) {
                this.index = i2 + 1;
                return UByte.m218constructorimpl(bArr[i2]);
            }
            throw new NoSuchElementException(String.valueOf(this.index));
        }
    }

    @PublishedApi
    private /* synthetic */ UByteArray(@NotNull byte[] bArr) {
        this.storage = bArr;
    }

    @NotNull
    /* renamed from: box-impl */
    public static final /* synthetic */ UByteArray m260boximpl(@NotNull byte[] bArr) {
        return new UByteArray(bArr);
    }

    @NotNull
    /* renamed from: constructor-impl */
    public static byte[] m261constructorimpl(int i2) {
        return m262constructorimpl(new byte[i2]);
    }

    @PublishedApi
    @NotNull
    /* renamed from: constructor-impl */
    public static byte[] m262constructorimpl(@NotNull byte[] bArr) {
        return bArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x002d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:? A[LOOP:0: B:50:0x000e->B:64:?, LOOP_END, SYNTHETIC] */
    /* renamed from: containsAll-impl */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean m264containsAllimpl(byte[] bArr, @NotNull Collection<UByte> collection) {
        boolean z;
        boolean contains;
        if (!collection.isEmpty()) {
            for (Object obj : collection) {
                if (obj instanceof UByte) {
                    contains = ArraysKt___ArraysKt.contains(bArr, ((UByte) obj).getData());
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
    public static boolean m265equalsimpl(byte[] bArr, @Nullable Object obj) {
        return (obj instanceof UByteArray) && Intrinsics.areEqual(bArr, ((UByteArray) obj).getStorage());
    }

    /* renamed from: equals-impl0 */
    public static final boolean m266equalsimpl0(@NotNull byte[] bArr, @NotNull byte[] bArr2) {
        return Intrinsics.areEqual(bArr, bArr2);
    }

    /* renamed from: get-impl */
    public static final byte m267getimpl(byte[] bArr, int i2) {
        return UByte.m218constructorimpl(bArr[i2]);
    }

    /* renamed from: getSize-impl */
    public static int m268getSizeimpl(byte[] bArr) {
        return bArr.length;
    }

    /* renamed from: hashCode-impl */
    public static int m269hashCodeimpl(byte[] bArr) {
        if (bArr != null) {
            return Arrays.hashCode(bArr);
        }
        return 0;
    }

    /* renamed from: isEmpty-impl */
    public static boolean m270isEmptyimpl(byte[] bArr) {
        return bArr.length == 0;
    }

    @NotNull
    /* renamed from: iterator-impl */
    public static UByteIterator m271iteratorimpl(byte[] bArr) {
        return new Iterator(bArr);
    }

    /* renamed from: set-VurrAj0 */
    public static final void m272setVurrAj0(byte[] bArr, int i2, byte b) {
        bArr[i2] = b;
    }

    @PublishedApi
    public static /* synthetic */ void storage$annotations() {
    }

    @NotNull
    /* renamed from: toString-impl */
    public static String m273toStringimpl(byte[] bArr) {
        return "UByteArray(storage=" + Arrays.toString(bArr) + ")";
    }

    @Override // java.util.Collection
    public /* synthetic */ boolean add(UByte uByte) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: add-7apg3OU */
    public boolean m274add7apg3OU(byte b) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends UByte> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof UByte) {
            return m275contains7apg3OU(((UByte) obj).getData());
        }
        return false;
    }

    /* renamed from: contains-7apg3OU */
    public boolean m275contains7apg3OU(byte b) {
        return m263contains7apg3OU(this.storage, b);
    }

    @Override // java.util.Collection
    public boolean containsAll(@NotNull Collection<? extends Object> collection) {
        return m264containsAllimpl(this.storage, collection);
    }

    @Override // java.util.Collection
    public boolean equals(Object other) {
        return m265equalsimpl(this.storage, other);
    }

    public int getSize() {
        return m268getSizeimpl(this.storage);
    }

    @Override // java.util.Collection
    public int hashCode() {
        return m269hashCodeimpl(this.storage);
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return m270isEmptyimpl(this.storage);
    }

    @Override // java.util.Collection, java.lang.Iterable
    @NotNull
    public UByteIterator iterator() {
        return m271iteratorimpl(this.storage);
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
        return m273toStringimpl(this.storage);
    }

    @NotNull
    /* renamed from: unbox-impl  reason: from getter */
    public final /* synthetic */ byte[] getStorage() {
        return this.storage;
    }

    /* renamed from: contains-7apg3OU */
    public static boolean m263contains7apg3OU(byte[] bArr, byte b) {
        boolean contains;
        contains = ArraysKt___ArraysKt.contains(bArr, b);
        return contains;
    }
}
