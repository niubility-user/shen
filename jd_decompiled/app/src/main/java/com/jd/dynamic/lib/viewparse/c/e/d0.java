package com.jd.dynamic.lib.viewparse.c.e;

import android.widget.EditText;
import com.jd.dynamic.base.DynamicTemplateEngine;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes13.dex */
public class d0 extends p0<EditText> {
    private String f(String str) {
        long j2;
        try {
            j2 = Long.parseLong(str);
        } catch (Exception unused) {
            j2 = 0;
        }
        return com.jd.dynamic.lib.utils.m.i(j2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(List list, EditText editText) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            DynamicTemplateEngine dynamicTemplateEngine = this.a;
            com.jd.dynamic.lib.utils.s.b((String) it.next(), dynamicTemplateEngine.currentData, dynamicTemplateEngine, editText);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x0118, code lost:
        if (com.jd.dynamic.DYConstants.DY_JD_LIGHT.equals(r0) != false) goto L55;
     */
    @Override // com.jd.dynamic.lib.viewparse.c.e.q0
    @androidx.annotation.RequiresApi(api = 16)
    /* renamed from: g  reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(java.util.HashMap<java.lang.String, java.lang.String> r8, final android.widget.EditText r9) {
        /*
            Method dump skipped, instructions count: 383
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.viewparse.c.e.d0.a(java.util.HashMap, android.widget.EditText):void");
    }
}
