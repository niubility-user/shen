package com.google.common.base;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
/* loaded from: classes12.dex */
abstract class CommonPattern {
    public abstract boolean equals(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int flags();

    public abstract int hashCode();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract CommonMatcher matcher(CharSequence charSequence);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String pattern();

    public abstract String toString();
}
