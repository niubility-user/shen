package com.jingdong.sdk.lib.puppetlayout.ylayout.util;

import java.lang.Comparable;

/* loaded from: classes8.dex */
public final class Range<T extends Comparable<? super T>> {
    private final T mLower;
    private final T mUpper;

    public Range(T t, T t2) {
        this.mLower = t;
        this.mUpper = t2;
        if (t.compareTo(t2) > 0) {
            throw new IllegalArgumentException("lower must be less than or equal to upper");
        }
    }

    public static <T extends Comparable<? super T>> Range<T> create(T t, T t2) {
        return new Range<>(t, t2);
    }

    public T clamp(T t) {
        if (t.compareTo(this.mLower) < 0) {
            return this.mLower;
        }
        return t.compareTo(this.mUpper) > 0 ? this.mUpper : t;
    }

    public boolean contains(T t) {
        return (t.compareTo(this.mLower) >= 0) && (t.compareTo(this.mUpper) <= 0);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof Range) {
            Range range = (Range) obj;
            return this.mLower.equals(range.mLower) && this.mUpper.equals(range.mUpper);
        }
        return false;
    }

    public Range<T> extend(Range<T> range) {
        int compareTo = range.mLower.compareTo(this.mLower);
        int compareTo2 = range.mUpper.compareTo(this.mUpper);
        if (compareTo > 0 || compareTo2 < 0) {
            if (compareTo < 0 || compareTo2 > 0) {
                return create(compareTo >= 0 ? this.mLower : range.mLower, compareTo2 <= 0 ? this.mUpper : range.mUpper);
            }
            return this;
        }
        return range;
    }

    public T getLower() {
        return this.mLower;
    }

    public T getUpper() {
        return this.mUpper;
    }

    public Range<T> intersect(Range<T> range) {
        int compareTo = range.mLower.compareTo(this.mLower);
        int compareTo2 = range.mUpper.compareTo(this.mUpper);
        if (compareTo > 0 || compareTo2 < 0) {
            if (compareTo < 0 || compareTo2 > 0) {
                return create(compareTo <= 0 ? this.mLower : range.mLower, compareTo2 >= 0 ? this.mUpper : range.mUpper);
            }
            return range;
        }
        return this;
    }

    public String toString() {
        return String.format("[%s, %s]", this.mLower, this.mUpper);
    }

    public boolean contains(Range<T> range) {
        return (range.mLower.compareTo(this.mLower) >= 0) && (range.mUpper.compareTo(this.mUpper) <= 0);
    }

    public Range<T> extend(T t, T t2) {
        int compareTo = t.compareTo(this.mLower);
        int compareTo2 = t2.compareTo(this.mUpper);
        if (compareTo < 0 || compareTo2 > 0) {
            if (compareTo >= 0) {
                t = this.mLower;
            }
            if (compareTo2 <= 0) {
                t2 = this.mUpper;
            }
            return create(t, t2);
        }
        return this;
    }

    public Range<T> intersect(T t, T t2) {
        int compareTo = t.compareTo(this.mLower);
        int compareTo2 = t2.compareTo(this.mUpper);
        if (compareTo > 0 || compareTo2 < 0) {
            if (compareTo <= 0) {
                t = this.mLower;
            }
            if (compareTo2 >= 0) {
                t2 = this.mUpper;
            }
            return create(t, t2);
        }
        return this;
    }

    public Range<T> extend(T t) {
        return extend(t, t);
    }
}
