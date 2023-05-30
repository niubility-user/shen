package com.jd.dynamic.lib.viewparse.c;

import android.view.View;
import android.widget.EditText;
import com.jd.dynamic.lib.viewparse.c.e.b0;
import com.jd.dynamic.lib.viewparse.c.e.c0;
import com.jd.dynamic.lib.viewparse.c.e.d0;
import com.jd.dynamic.lib.viewparse.c.e.e0;
import com.jd.dynamic.lib.viewparse.c.e.p0;
import com.jd.dynamic.lib.viewparse.c.e.q0;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes13.dex */
public class h extends f<EditText> {

    /* renamed from: c  reason: collision with root package name */
    private List<q0<EditText>> f2437c;

    public h() {
        ArrayList arrayList = new ArrayList();
        this.f2437c = arrayList;
        arrayList.add(new c0());
        this.f2437c.add(new d0());
        this.f2437c.add(new b0());
        this.f2437c.add(new e0());
    }

    @Override // com.jd.dynamic.lib.viewparse.c.j
    public /* bridge */ /* synthetic */ View a(HashMap hashMap, View view) {
        EditText editText = (EditText) view;
        d(hashMap, editText);
        return editText;
    }

    public EditText d(HashMap<String, String> hashMap, EditText editText) {
        for (q0<EditText> q0Var : this.f2437c) {
            if (q0Var instanceof p0) {
                ((p0) q0Var).e(this.a);
            }
            q0Var.a(hashMap, editText);
        }
        return editText;
    }
}
