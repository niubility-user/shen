package com.facebook.soloader;

import javax.annotation.Nullable;

/* loaded from: classes12.dex */
class MergedSoMapping {
    MergedSoMapping() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void invokeJniOnload(String str) {
        throw new IllegalArgumentException("Unknown library: " + str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static String mapLibName(String str) {
        return null;
    }
}
