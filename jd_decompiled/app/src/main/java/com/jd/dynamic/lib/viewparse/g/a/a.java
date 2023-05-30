package com.jd.dynamic.lib.viewparse.g.a;

import android.content.Context;
import android.view.ViewGroup;
import com.jd.dynamic.lib.viewparse.d;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class a implements com.jd.dynamic.lib.viewparse.g.b<ViewGroup.MarginLayoutParams> {
    @Override // com.jd.dynamic.lib.viewparse.g.b
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams a(Context context, HashMap hashMap, ViewGroup.MarginLayoutParams marginLayoutParams) {
        ViewGroup.MarginLayoutParams marginLayoutParams2 = marginLayoutParams;
        b(context, hashMap, marginLayoutParams2);
        return marginLayoutParams2;
    }

    public ViewGroup.LayoutParams b(Context context, HashMap<String, String> hashMap, ViewGroup.MarginLayoutParams marginLayoutParams) {
        if (hashMap.containsKey("marginLeft")) {
            marginLayoutParams.leftMargin = (int) d.a(hashMap.get("marginLeft"), context);
        }
        if (hashMap.containsKey("marginTop")) {
            marginLayoutParams.topMargin = (int) d.a(hashMap.get("marginTop"), context);
        }
        if (hashMap.containsKey("marginRight")) {
            marginLayoutParams.rightMargin = (int) d.a(hashMap.get("marginRight"), context);
        }
        if (hashMap.containsKey("marginBottom")) {
            marginLayoutParams.bottomMargin = (int) d.a(hashMap.get("marginBottom"), context);
        }
        if (hashMap.containsKey("margin")) {
            int a = (int) d.a(hashMap.get("margin"), context);
            marginLayoutParams.leftMargin = a;
            marginLayoutParams.topMargin = a;
            marginLayoutParams.rightMargin = a;
            marginLayoutParams.bottomMargin = a;
        }
        return marginLayoutParams;
    }
}
