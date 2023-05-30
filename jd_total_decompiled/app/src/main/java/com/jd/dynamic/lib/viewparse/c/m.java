package com.jd.dynamic.lib.viewparse.c;

import android.view.View;
import com.jd.dynamic.lib.viewparse.c.e.i0;
import com.jd.dynamic.lib.views.RichTextViewContainer;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class m extends f<RichTextViewContainer> {

    /* renamed from: c  reason: collision with root package name */
    private i0 f2440c = new i0();

    @Override // com.jd.dynamic.lib.viewparse.c.j
    public /* bridge */ /* synthetic */ View a(HashMap hashMap, View view) {
        RichTextViewContainer richTextViewContainer = (RichTextViewContainer) view;
        d(hashMap, richTextViewContainer);
        return richTextViewContainer;
    }

    public RichTextViewContainer d(HashMap<String, String> hashMap, RichTextViewContainer richTextViewContainer) {
        this.f2440c.e(this.a);
        this.f2440c.a(hashMap, richTextViewContainer);
        return richTextViewContainer;
    }
}
