package com.google.common.base;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
/* loaded from: classes12.dex */
abstract class CommonMatcher {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int end();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean find();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean find(int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean matches();

    abstract String replaceAll(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int start();
}
