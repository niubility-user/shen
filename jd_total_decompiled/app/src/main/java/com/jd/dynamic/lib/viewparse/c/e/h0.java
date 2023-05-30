package com.jd.dynamic.lib.viewparse.c.e;

import android.view.View;
import com.jd.dynamic.yoga.android.YogaLayout;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class h0 implements q0<View> {
    @Override // com.jd.dynamic.lib.viewparse.c.e.q0
    public void a(HashMap<String, String> hashMap, View view) {
        if (view instanceof YogaLayout) {
            return;
        }
        int paddingLeft = view.getPaddingLeft();
        int paddingTop = view.getPaddingTop();
        int paddingRight = view.getPaddingRight();
        int paddingBottom = view.getPaddingBottom();
        if (hashMap.containsKey("paddingLeft")) {
            paddingLeft = (int) com.jd.dynamic.lib.viewparse.d.a(hashMap.get("paddingLeft"), view.getContext());
        }
        if (hashMap.containsKey("paddingTop")) {
            paddingTop = (int) com.jd.dynamic.lib.viewparse.d.a(hashMap.get("paddingTop"), view.getContext());
        }
        if (hashMap.containsKey("paddingRight")) {
            paddingRight = (int) com.jd.dynamic.lib.viewparse.d.a(hashMap.get("paddingRight"), view.getContext());
        }
        if (hashMap.containsKey("paddingBottom")) {
            paddingBottom = (int) com.jd.dynamic.lib.viewparse.d.a(hashMap.get("paddingBottom"), view.getContext());
        }
        if (hashMap.containsKey("padding")) {
            paddingLeft = (int) com.jd.dynamic.lib.viewparse.d.a(hashMap.get("padding"), view.getContext());
            paddingTop = paddingLeft;
            paddingRight = paddingTop;
            paddingBottom = paddingRight;
        }
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }
}
