package com.jd.dynamic.lib.viewparse.g;

import android.content.Context;
import android.view.ViewGroup;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class c implements b<ViewGroup.LayoutParams> {
    private b<ViewGroup.LayoutParams> a = new com.jd.dynamic.lib.viewparse.g.a.b();

    private ViewGroup.LayoutParams b(Context context, HashMap<String, String> hashMap, ViewGroup.LayoutParams layoutParams) {
        return this.a.a(context, hashMap, layoutParams);
    }

    @Override // com.jd.dynamic.lib.viewparse.g.b
    public ViewGroup.LayoutParams a(Context context, HashMap hashMap, ViewGroup.LayoutParams layoutParams) {
        return b(context, hashMap, layoutParams);
    }
}
