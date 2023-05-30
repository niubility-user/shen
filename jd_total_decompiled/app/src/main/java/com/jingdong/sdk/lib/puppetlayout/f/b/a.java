package com.jingdong.sdk.lib.puppetlayout.f.b;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.sdk.lib.puppetlayout.ylayout.PuppetContext;
import com.jingdong.sdk.lib.puppetlayout.ylayout.android.YogaLayout;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.TemplateViewBase;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class a extends c {

    /* renamed from: f  reason: collision with root package name */
    private List<c> f15203f;

    public a(String str, com.jingdong.sdk.lib.puppetlayout.f.c.b bVar) {
        super(str, bVar);
        this.f15203f = new ArrayList();
    }

    private void e(Context context, ViewGroup viewGroup, com.jingdong.sdk.lib.puppetlayout.d.b bVar, com.jingdong.sdk.lib.puppetlayout.d.a aVar, PuppetContext puppetContext) {
        View view = null;
        for (c cVar : this.f15203f) {
            if (!TemplateViewBase.ELEM_TYPE_INDICATOR_1.equals(cVar.b) && !TemplateViewBase.ELEM_TYPE_INDICATOR_2.equals(cVar.b)) {
                View b = cVar.b(context, viewGroup, true, bVar, aVar, puppetContext);
                if (TemplateViewBase.ELEM_TYPE_CAROUSEL.equals(cVar.b)) {
                    view = b;
                }
            } else {
                cVar.c(context, viewGroup, true, bVar, aVar, puppetContext, view);
            }
        }
    }

    @Override // com.jingdong.sdk.lib.puppetlayout.f.b.c
    public void a(c cVar) {
        if (cVar != null) {
            cVar.d(this);
            this.f15203f.add(cVar);
        }
    }

    @Override // com.jingdong.sdk.lib.puppetlayout.f.b.c
    public View b(Context context, ViewGroup viewGroup, boolean z, com.jingdong.sdk.lib.puppetlayout.d.b bVar, com.jingdong.sdk.lib.puppetlayout.d.a aVar, PuppetContext puppetContext) {
        View b = super.b(context, viewGroup, z, bVar, aVar, puppetContext);
        if (b instanceof YogaLayout) {
            e(context, (ViewGroup) b, bVar, aVar, puppetContext);
        }
        return b;
    }
}
