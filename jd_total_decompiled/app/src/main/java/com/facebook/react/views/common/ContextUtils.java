package com.facebook.react.views.common;

import android.content.Context;
import android.content.ContextWrapper;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class ContextUtils {
    /* JADX WARN: Code restructure failed: missing block: B:0:?, code lost:
        r2 = r2;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v6 */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static <T> T findContextOfType(@Nullable Context context, Class<? extends T> cls) {
        Object obj;
        Object baseContext;
        while (!cls.isInstance(obj)) {
            if (!(obj instanceof ContextWrapper) || obj == (baseContext = obj.getBaseContext())) {
                return null;
            }
            obj = (T) baseContext;
        }
        return (T) obj;
    }
}
