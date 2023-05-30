package com.jd.dynamic.lib.viewparse.e;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;
import com.jd.dynamic.entity.ViewNode;

/* loaded from: classes13.dex */
public class d extends p<EditText> {

    /* renamed from: g  reason: collision with root package name */
    private com.jd.dynamic.lib.viewparse.c.j<EditText> f2448g = new com.jd.dynamic.lib.viewparse.c.h();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.dynamic.lib.viewparse.e.p
    /* renamed from: i  reason: merged with bridge method [inline-methods] */
    public EditText d(Context context) {
        EditText editText = new EditText(context);
        editText.setPadding(0, 0, 0, 0);
        editText.setTextSize(2, 14.0f);
        editText.setEllipsize(TextUtils.TruncateAt.END);
        editText.setGravity(19);
        return editText;
    }

    @Override // com.jd.dynamic.lib.viewparse.e.p
    /* renamed from: j  reason: merged with bridge method [inline-methods] */
    public EditText a(ViewNode viewNode, Context context) {
        EditText editText = (EditText) super.a(viewNode, context);
        if (this.d || viewNode.getAttributes() == null) {
            return editText;
        }
        com.jd.dynamic.lib.viewparse.c.j<EditText> jVar = this.f2448g;
        if (jVar instanceof com.jd.dynamic.lib.viewparse.c.f) {
            ((com.jd.dynamic.lib.viewparse.c.f) jVar).b(this.f2460c);
        }
        return this.f2448g.a(com.jd.dynamic.lib.utils.m.Q(viewNode.getAttributes()), editText);
    }
}
