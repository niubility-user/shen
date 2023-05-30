package com.jingdong.sdk.lib.puppetlayout.f.b;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.yoga2.YogaNode;
import com.jingdong.sdk.lib.puppetlayout.view.ui.builder.m;
import com.jingdong.sdk.lib.puppetlayout.ylayout.PuppetContext;
import com.jingdong.sdk.lib.puppetlayout.ylayout.android.YogaLayout;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.Action;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.Iterate;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.Span;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.TemplateViewBase;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class c {
    private c a;
    public String b;

    /* renamed from: c */
    public String f15204c;
    protected com.jingdong.sdk.lib.puppetlayout.f.c.b d;

    /* renamed from: e */
    public Iterate f15205e;

    public c(String str, com.jingdong.sdk.lib.puppetlayout.f.c.b bVar) {
        this.b = str;
        this.d = bVar;
    }

    public void a(c cVar) {
        throw new RuntimeException("IWidget node " + this.b + " dose not allow addProperty child");
    }

    public View b(Context context, ViewGroup viewGroup, boolean z, com.jingdong.sdk.lib.puppetlayout.d.b bVar, com.jingdong.sdk.lib.puppetlayout.d.a aVar, PuppetContext puppetContext) {
        return c(context, viewGroup, z, bVar, aVar, puppetContext, null);
    }

    public View c(Context context, ViewGroup viewGroup, boolean z, com.jingdong.sdk.lib.puppetlayout.d.b bVar, com.jingdong.sdk.lib.puppetlayout.d.a aVar, PuppetContext puppetContext, View view) {
        YogaNode yogaNode;
        ArrayList<Span> arrayList;
        com.jingdong.sdk.lib.puppetlayout.h.a a = com.jingdong.sdk.lib.puppetlayout.h.b.a(puppetContext, this.b, this.f15204c);
        if (a != null) {
            if (!TemplateViewBase.ELEM_TYPE_INDICATOR_1.equals(this.b) && !TemplateViewBase.ELEM_TYPE_INDICATOR_2.equals(this.b)) {
                a.d(context);
            } else {
                a.e(context, view);
            }
            a.b = puppetContext;
            com.jingdong.sdk.lib.puppetlayout.f.c.b bVar2 = this.d;
            if (bVar2 != null) {
                List<com.jingdong.sdk.lib.puppetlayout.f.c.a> list = bVar2.d;
                if (list != null && list.size() > 0 && bVar != null) {
                    bVar.b(a, this.d.d);
                }
                if (this.d.b() != null && this.d.b().size() > 0 && aVar != null) {
                    aVar.a(a, this.d.b());
                }
                ArrayList<Action> arrayList2 = this.d.f15214i;
                if (arrayList2 != null && arrayList2.size() > 0) {
                    if (aVar != null) {
                        aVar.b = arrayList2;
                    }
                    a.l(puppetContext, arrayList2);
                    if (bVar != null) {
                        bVar.a(a, arrayList2);
                    }
                }
                if ((a instanceof m) && bVar != null && (arrayList = this.d.f15215j) != null && arrayList.size() > 0) {
                    com.jingdong.sdk.lib.puppetlayout.f.c.b bVar3 = this.d;
                    if (bVar3.f15216k) {
                        bVar.e((m) a, bVar3.f15215j);
                    } else {
                        ((m) a).C(bVar3.f15215j);
                    }
                }
                if (!TextUtils.isEmpty(this.d.f15211f) && bVar != null) {
                    bVar.c(a, this.d.f15211f);
                }
                if (!TextUtils.isEmpty(this.d.f15212g) && bVar != null) {
                    bVar.d(a, this.d.f15211f);
                }
                Iterate iterate = this.f15205e;
                if (iterate != null && bVar != null) {
                    bVar.f15198e.put(a, iterate);
                }
                this.d.e(a);
            }
            if (this.a == null) {
                if ((a.h() instanceof YogaLayout) && (yogaNode = ((YogaLayout) a.h()).getYogaNode()) != null) {
                    com.jingdong.sdk.lib.puppetlayout.f.c.b bVar4 = this.d;
                    com.jingdong.sdk.lib.puppetlayout.h.d.b.a(bVar4.b, bVar4.f15209c, yogaNode, a.h(), true);
                }
            } else if (viewGroup != null) {
                YogaLayout.LayoutParams layoutParams = new YogaLayout.LayoutParams(-2, -2);
                SparseArray<Float> sparseArray = this.d.b;
                if (sparseArray != null && sparseArray.size() > 0) {
                    layoutParams.numericAttributes = this.d.b;
                }
                SparseArray<String> sparseArray2 = this.d.f15209c;
                if (sparseArray2 != null && sparseArray2.size() > 0) {
                    layoutParams.stringAttributes = this.d.f15209c;
                }
                viewGroup.addView(a.h(), -1, layoutParams);
            }
            return a.h();
        }
        return null;
    }

    public void d(c cVar) {
        this.a = cVar;
    }

    public c(String str, String str2, com.jingdong.sdk.lib.puppetlayout.f.c.b bVar) {
        this(str, bVar);
        this.f15204c = str2;
    }
}
