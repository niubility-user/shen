package rx.schedulers;

/* loaded from: classes11.dex */
public class TimeInterval<T> {
    private final long intervalInMilliseconds;
    private final T value;

    public TimeInterval(long j2, T t) {
        this.value = t;
        this.intervalInMilliseconds = j2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            TimeInterval timeInterval = (TimeInterval) obj;
            if (this.intervalInMilliseconds != timeInterval.intervalInMilliseconds) {
                return false;
            }
            T t = this.value;
            if (t == null) {
                if (timeInterval.value != null) {
                    return false;
                }
            } else if (!t.equals(timeInterval.value)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public long getIntervalInMilliseconds() {
        return this.intervalInMilliseconds;
    }

    public T getValue() {
        return this.value;
    }

    public int hashCode() {
        long j2 = this.intervalInMilliseconds;
        int i2 = (((int) (j2 ^ (j2 >>> 32))) + 31) * 31;
        T t = this.value;
        return i2 + (t == null ? 0 : t.hashCode());
    }

    public String toString() {
        return "TimeInterval [intervalInMilliseconds=" + this.intervalInMilliseconds + ", value=" + this.value + "]";
    }
}
