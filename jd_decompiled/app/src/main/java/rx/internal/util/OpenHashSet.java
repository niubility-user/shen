package rx.internal.util;

import java.util.Arrays;
import rx.functions.Action1;
import rx.internal.util.unsafe.Pow2;

/* loaded from: classes11.dex */
public final class OpenHashSet<T> {
    private static final int INT_PHI = -1640531527;
    T[] keys;
    final float loadFactor;
    int mask;
    int maxSize;
    int size;

    public OpenHashSet() {
        this(16, 0.75f);
    }

    static int mix(int i2) {
        int i3 = i2 * INT_PHI;
        return i3 ^ (i3 >>> 16);
    }

    public boolean add(T t) {
        T t2;
        T[] tArr = this.keys;
        int i2 = this.mask;
        int mix = mix(t.hashCode()) & i2;
        T t3 = tArr[mix];
        if (t3 != null) {
            if (t3.equals(t)) {
                return false;
            }
            do {
                mix = (mix + 1) & i2;
                t2 = tArr[mix];
                if (t2 == null) {
                }
            } while (!t2.equals(t));
            return false;
        }
        tArr[mix] = t;
        int i3 = this.size + 1;
        this.size = i3;
        if (i3 >= this.maxSize) {
            rehash();
        }
        return true;
    }

    public void clear(Action1<? super T> action1) {
        if (this.size == 0) {
            return;
        }
        T[] tArr = this.keys;
        for (T t : tArr) {
            Object obj = (Object) t;
            if (obj != 0) {
                action1.call(obj);
            }
        }
        Arrays.fill(tArr, (Object) null);
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    void rehash() {
        T[] tArr = this.keys;
        int length = tArr.length;
        int i2 = length << 1;
        int i3 = i2 - 1;
        T[] tArr2 = (T[]) new Object[i2];
        int i4 = this.size;
        while (true) {
            int i5 = i4 - 1;
            if (i4 == 0) {
                this.mask = i3;
                this.maxSize = (int) (i2 * this.loadFactor);
                this.keys = tArr2;
                return;
            }
            do {
                length--;
            } while (tArr[length] == null);
            int mix = mix(tArr[length].hashCode()) & i3;
            if (tArr2[mix] == null) {
                tArr2[mix] = tArr[length];
                i4 = i5;
            }
            do {
                mix = (mix + 1) & i3;
            } while (tArr2[mix] != null);
            tArr2[mix] = tArr[length];
            i4 = i5;
        }
    }

    public boolean remove(T t) {
        T t2;
        T[] tArr = this.keys;
        int i2 = this.mask;
        int mix = mix(t.hashCode()) & i2;
        T t3 = tArr[mix];
        if (t3 == null) {
            return false;
        }
        if (t3.equals(t)) {
            return removeEntry(mix, tArr, i2);
        }
        do {
            mix = (mix + 1) & i2;
            t2 = tArr[mix];
            if (t2 == null) {
                return false;
            }
        } while (!t2.equals(t));
        return removeEntry(mix, tArr, i2);
    }

    boolean removeEntry(int i2, T[] tArr, int i3) {
        int i4;
        T t;
        this.size--;
        while (true) {
            int i5 = i2 + 1;
            while (true) {
                i4 = i5 & i3;
                t = tArr[i4];
                if (t == null) {
                    tArr[i2] = null;
                    return true;
                }
                int mix = mix(t.hashCode()) & i3;
                if (i2 > i4) {
                    if (i2 >= mix && mix > i4) {
                        break;
                    }
                    i5 = i4 + 1;
                } else if (i2 < mix && mix <= i4) {
                    i5 = i4 + 1;
                }
            }
            tArr[i2] = t;
            i2 = i4;
        }
    }

    public void terminate() {
        this.size = 0;
        this.keys = (T[]) new Object[0];
    }

    public T[] values() {
        return this.keys;
    }

    public OpenHashSet(int i2) {
        this(i2, 0.75f);
    }

    public OpenHashSet(int i2, float f2) {
        this.loadFactor = f2;
        int roundToPowerOfTwo = Pow2.roundToPowerOfTwo(i2);
        this.mask = roundToPowerOfTwo - 1;
        this.maxSize = (int) (f2 * roundToPowerOfTwo);
        this.keys = (T[]) new Object[roundToPowerOfTwo];
    }
}
