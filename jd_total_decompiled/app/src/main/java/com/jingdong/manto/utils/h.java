package com.jingdong.manto.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

/* loaded from: classes16.dex */
public class h {
    public static Activity a(Context context) {
        if (context != null && (context instanceof ContextWrapper) && !(context instanceof Activity)) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        return null;
    }
}
