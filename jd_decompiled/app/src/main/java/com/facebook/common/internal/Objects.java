package com.facebook.common.internal;

import java.util.Arrays;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public final class Objects {

    /* loaded from: classes.dex */
    public static final class ToStringHelper {
        private final String className;
        private ValueHolder holderHead;
        private ValueHolder holderTail;
        private boolean omitNullValues;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static final class ValueHolder {
            @Nullable
            String name;
            ValueHolder next;
            @Nullable
            Object value;

            private ValueHolder() {
            }
        }

        private ToStringHelper(String str) {
            ValueHolder valueHolder = new ValueHolder();
            this.holderHead = valueHolder;
            this.holderTail = valueHolder;
            this.omitNullValues = false;
            this.className = (String) Preconditions.checkNotNull(str);
        }

        private ValueHolder addHolder() {
            ValueHolder valueHolder = new ValueHolder();
            this.holderTail.next = valueHolder;
            this.holderTail = valueHolder;
            return valueHolder;
        }

        private ToStringHelper addHolder(@Nullable Object obj) {
            addHolder().value = obj;
            return this;
        }

        private ToStringHelper addHolder(String str, @Nullable Object obj) {
            ValueHolder addHolder = addHolder();
            addHolder.value = obj;
            addHolder.name = (String) Preconditions.checkNotNull(str);
            return this;
        }

        public ToStringHelper add(String str, char c2) {
            return addHolder(str, String.valueOf(c2));
        }

        public ToStringHelper add(String str, double d) {
            return addHolder(str, String.valueOf(d));
        }

        public ToStringHelper add(String str, float f2) {
            return addHolder(str, String.valueOf(f2));
        }

        public ToStringHelper add(String str, int i2) {
            return addHolder(str, String.valueOf(i2));
        }

        public ToStringHelper add(String str, long j2) {
            return addHolder(str, String.valueOf(j2));
        }

        public ToStringHelper add(String str, @Nullable Object obj) {
            return addHolder(str, obj);
        }

        public ToStringHelper add(String str, boolean z) {
            return addHolder(str, String.valueOf(z));
        }

        public ToStringHelper addValue(char c2) {
            return addHolder(String.valueOf(c2));
        }

        public ToStringHelper addValue(double d) {
            return addHolder(String.valueOf(d));
        }

        public ToStringHelper addValue(float f2) {
            return addHolder(String.valueOf(f2));
        }

        public ToStringHelper addValue(int i2) {
            return addHolder(String.valueOf(i2));
        }

        public ToStringHelper addValue(long j2) {
            return addHolder(String.valueOf(j2));
        }

        public ToStringHelper addValue(@Nullable Object obj) {
            return addHolder(obj);
        }

        public ToStringHelper addValue(boolean z) {
            return addHolder(String.valueOf(z));
        }

        public ToStringHelper omitNullValues() {
            this.omitNullValues = true;
            return this;
        }

        public String toString() {
            boolean z = this.omitNullValues;
            StringBuilder sb = new StringBuilder(32);
            sb.append(this.className);
            sb.append('{');
            String str = "";
            for (ValueHolder valueHolder = this.holderHead.next; valueHolder != null; valueHolder = valueHolder.next) {
                if (!z || valueHolder.value != null) {
                    sb.append(str);
                    String str2 = valueHolder.name;
                    if (str2 != null) {
                        sb.append(str2);
                        sb.append('=');
                    }
                    sb.append(valueHolder.value);
                    str = ", ";
                }
            }
            sb.append('}');
            return sb.toString();
        }
    }

    private Objects() {
    }

    @CheckReturnValue
    public static boolean equal(@Nullable Object obj, @Nullable Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static <T> T firstNonNull(@Nullable T t, @Nullable T t2) {
        return t != null ? t : (T) Preconditions.checkNotNull(t2);
    }

    public static int hashCode(@Nullable Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    private static String simpleName(Class<?> cls) {
        String replaceAll = cls.getName().replaceAll("\\$[0-9]+", "\\$");
        int lastIndexOf = replaceAll.lastIndexOf(36);
        if (lastIndexOf == -1) {
            lastIndexOf = replaceAll.lastIndexOf(46);
        }
        return replaceAll.substring(lastIndexOf + 1);
    }

    public static ToStringHelper toStringHelper(Class<?> cls) {
        return new ToStringHelper(simpleName(cls));
    }

    public static ToStringHelper toStringHelper(Object obj) {
        return new ToStringHelper(simpleName(obj.getClass()));
    }

    public static ToStringHelper toStringHelper(String str) {
        return new ToStringHelper(str);
    }
}
