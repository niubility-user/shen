package com.facebook.common.internal;

/* loaded from: classes.dex */
public class Suppliers {
    public static final Supplier<Boolean> BOOLEAN_TRUE = new Supplier<Boolean>() { // from class: com.facebook.common.internal.Suppliers.2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.facebook.common.internal.Supplier
        public Boolean get() {
            return Boolean.TRUE;
        }
    };
    public static final Supplier<Boolean> BOOLEAN_FALSE = new Supplier<Boolean>() { // from class: com.facebook.common.internal.Suppliers.3
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.facebook.common.internal.Supplier
        public Boolean get() {
            return Boolean.FALSE;
        }
    };

    public static <T> Supplier<T> of(final T t) {
        return new Supplier<T>() { // from class: com.facebook.common.internal.Suppliers.1
            @Override // com.facebook.common.internal.Supplier
            public T get() {
                return (T) t;
            }
        };
    }
}
