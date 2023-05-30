package rx;

import com.jingdong.common.utils.LangUtils;

/* loaded from: classes11.dex */
public final class Notification<T> {
    private static final Notification<Void> ON_COMPLETED = new Notification<>(Kind.OnCompleted, null, null);
    private final Kind kind;
    private final Throwable throwable;
    private final T value;

    /* loaded from: classes11.dex */
    public enum Kind {
        OnNext,
        OnError,
        OnCompleted
    }

    private Notification(Kind kind, T t, Throwable th) {
        this.value = t;
        this.throwable = th;
        this.kind = kind;
    }

    public static <T> Notification<T> createOnCompleted() {
        return (Notification<T>) ON_COMPLETED;
    }

    public static <T> Notification<T> createOnError(Throwable th) {
        return new Notification<>(Kind.OnError, null, th);
    }

    public static <T> Notification<T> createOnNext(T t) {
        return new Notification<>(Kind.OnNext, t, null);
    }

    public void accept(Observer<? super T> observer) {
        if (isOnNext()) {
            observer.onNext(getValue());
        } else if (isOnCompleted()) {
            observer.onCompleted();
        } else if (isOnError()) {
            observer.onError(getThrowable());
        }
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj.getClass() != Notification.class) {
            return false;
        }
        Notification notification = (Notification) obj;
        if (notification.getKind() != getKind()) {
            return false;
        }
        if (!hasValue() || getValue().equals(notification.getValue())) {
            if (!hasThrowable() || getThrowable().equals(notification.getThrowable())) {
                if (hasValue() || hasThrowable() || !notification.hasValue()) {
                    return hasValue() || hasThrowable() || !notification.hasThrowable();
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public Kind getKind() {
        return this.kind;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public T getValue() {
        return this.value;
    }

    public boolean hasThrowable() {
        return isOnError() && this.throwable != null;
    }

    public boolean hasValue() {
        return isOnNext() && this.value != null;
    }

    public int hashCode() {
        int hashCode = getKind().hashCode();
        if (hasValue()) {
            hashCode = (hashCode * 31) + getValue().hashCode();
        }
        return hasThrowable() ? (hashCode * 31) + getThrowable().hashCode() : hashCode;
    }

    public boolean isOnCompleted() {
        return getKind() == Kind.OnCompleted;
    }

    public boolean isOnError() {
        return getKind() == Kind.OnError;
    }

    public boolean isOnNext() {
        return getKind() == Kind.OnNext;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(super.toString());
        sb.append(LangUtils.SINGLE_SPACE);
        sb.append(getKind());
        if (hasValue()) {
            sb.append(LangUtils.SINGLE_SPACE);
            sb.append(getValue());
        }
        if (hasThrowable()) {
            sb.append(LangUtils.SINGLE_SPACE);
            sb.append(getThrowable().getMessage());
        }
        sb.append("]");
        return sb.toString();
    }

    public static <T> Notification<T> createOnCompleted(Class<T> cls) {
        return (Notification<T>) ON_COMPLETED;
    }
}
