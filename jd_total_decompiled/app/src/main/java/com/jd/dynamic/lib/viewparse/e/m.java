package com.jd.dynamic.lib.viewparse.e;

import android.content.Context;
import android.text.TextUtils;
import androidx.appcompat.widget.AppCompatTextView;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.viewparse.c.r;

/* loaded from: classes13.dex */
public class m extends p<AppCompatTextView> {

    /* renamed from: g  reason: collision with root package name */
    private com.jd.dynamic.lib.viewparse.c.f<AppCompatTextView> f2458g = new r();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.dynamic.lib.viewparse.e.p
    /* renamed from: i  reason: merged with bridge method [inline-methods] */
    public AppCompatTextView d(Context context) {
        AppCompatTextView appCompatTextView = new AppCompatTextView(context);
        appCompatTextView.setTextSize(2, 14.0f);
        appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
        if (com.jd.dynamic.b.a.b.o().b()) {
            appCompatTextView.setSingleLine();
        } else {
            appCompatTextView.setMaxLines(1);
        }
        if (com.jd.dynamic.b.a.b.o().w("textViewGravity")) {
            appCompatTextView.setGravity(19);
        }
        return appCompatTextView;
    }

    @Override // com.jd.dynamic.lib.viewparse.e.p
    /* renamed from: j  reason: merged with bridge method [inline-methods] */
    public AppCompatTextView a(ViewNode viewNode, Context context) {
        AppCompatTextView appCompatTextView = (AppCompatTextView) super.a(viewNode, context);
        if (this.d || viewNode.getAttributes() == null) {
            return appCompatTextView;
        }
        this.f2458g.b(this.f2460c);
        return this.f2458g.a(com.jd.dynamic.lib.utils.m.Q(viewNode.getAttributes()), appCompatTextView);
    }
}
