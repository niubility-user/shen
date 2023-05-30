package rx.schedulers;

/* loaded from: classes11.dex */
public final class Timestamped<T> {
    private final long timestampMillis;
    private final T value;

    public Timestamped(long j2, T t) {
        this.value = t;
        this.timestampMillis = j2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof Timestamped)) {
            Timestamped timestamped = (Timestamped) obj;
            if (this.timestampMillis != timestamped.timestampMillis) {
                return false;
            }
            T t = this.value;
            if (t == null) {
                if (timestamped.value != null) {
                    return false;
                }
            } else if (!t.equals(timestamped.value)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public long getTimestampMillis() {
        return this.timestampMillis;
    }

    public T getValue() {
        return this.value;
    }

    public int hashCode() {
        long j2 = this.timestampMillis;
        int i2 = (((int) (j2 ^ (j2 >>> 32))) + 31) * 31;
        T t = this.value;
        return i2 + (t == null ? 0 : t.hashCode());
    }

    public String toString() {
        return String.format("Timestamped(timestampMillis = %d, value = %s)", Long.valueOf(this.timestampMillis), this.value.toString());
    }
}
