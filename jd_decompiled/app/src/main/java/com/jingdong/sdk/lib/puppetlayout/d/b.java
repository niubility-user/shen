package com.jingdong.sdk.lib.puppetlayout.d;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.sdk.lib.puppetlayout.view.ui.builder.m;
import com.jingdong.sdk.lib.puppetlayout.ylayout.ActionProcessor;
import com.jingdong.sdk.lib.puppetlayout.ylayout.PuppetContext;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.Action;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.Iterate;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.Span;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class b {
    private Map<com.jingdong.sdk.lib.puppetlayout.h.a, List<com.jingdong.sdk.lib.puppetlayout.f.c.a>> a = new HashMap();
    private Map<com.jingdong.sdk.lib.puppetlayout.h.a, ArrayList<Action>> b = new HashMap();

    /* renamed from: c */
    private Map<com.jingdong.sdk.lib.puppetlayout.h.a, String> f15197c = new HashMap();
    private Map<com.jingdong.sdk.lib.puppetlayout.h.a, String> d = new HashMap();

    /* renamed from: e */
    public Map<com.jingdong.sdk.lib.puppetlayout.h.a, Iterate> f15198e = new HashMap();

    /* renamed from: f */
    private Map<m, ArrayList<Span>> f15199f = new HashMap();

    public void a(com.jingdong.sdk.lib.puppetlayout.h.a aVar, ArrayList<Action> arrayList) {
        if (aVar == null || arrayList == null) {
            return;
        }
        this.b.put(aVar, arrayList);
    }

    public void b(com.jingdong.sdk.lib.puppetlayout.h.a aVar, List<com.jingdong.sdk.lib.puppetlayout.f.c.a> list) {
        if (aVar == null || list == null) {
            return;
        }
        this.a.put(aVar, list);
    }

    public void c(com.jingdong.sdk.lib.puppetlayout.h.a aVar, String str) {
        if (aVar == null || TextUtils.isEmpty(str)) {
            return;
        }
        this.f15197c.put(aVar, str);
    }

    public void d(com.jingdong.sdk.lib.puppetlayout.h.a aVar, String str) {
        if (aVar == null || TextUtils.isEmpty(str)) {
            return;
        }
        this.d.put(aVar, str);
    }

    public void e(m mVar, ArrayList<Span> arrayList) {
        if (mVar == null || arrayList == null) {
            return;
        }
        this.f15199f.put(mVar, arrayList);
    }

    public void f(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        Iterator<Map.Entry<com.jingdong.sdk.lib.puppetlayout.h.a, List<com.jingdong.sdk.lib.puppetlayout.f.c.a>>> it = this.a.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<com.jingdong.sdk.lib.puppetlayout.h.a, List<com.jingdong.sdk.lib.puppetlayout.f.c.a>> next = it.next();
            if (next != null && next.getValue() != null) {
                for (int i2 = 0; i2 < next.getValue().size(); i2++) {
                    if (next.getValue().get(i2) != null && next.getKey() != null) {
                        next.getValue().get(i2).b(next.getKey(), jDJSONObject);
                    }
                }
                if (next.getKey() != null) {
                    next.getKey().s();
                }
            }
        }
        if (this.b.size() > 0) {
            for (Map.Entry<com.jingdong.sdk.lib.puppetlayout.h.a, ArrayList<Action>> entry : this.b.entrySet()) {
                if (entry != null && entry.getValue() != null) {
                    for (int i3 = 0; i3 < entry.getValue().size(); i3++) {
                        if (entry.getValue().get(i3) != null && entry.getKey() != null) {
                            entry.getValue().get(i3).compute(entry.getKey().b, jDJSONObject);
                        }
                    }
                }
            }
        }
        for (Map.Entry<com.jingdong.sdk.lib.puppetlayout.h.a, Iterate> entry2 : this.f15198e.entrySet()) {
            if (entry2 != null && entry2.getValue() != null && entry2.getValue() != null && entry2.getKey() != null) {
                entry2.getValue().setData(entry2.getKey(), jDJSONObject);
            }
        }
        if (this.f15199f.size() > 0) {
            for (Map.Entry<m, ArrayList<Span>> entry3 : this.f15199f.entrySet()) {
                if (entry3 != null && entry3.getValue() != null) {
                    for (int i4 = 0; i4 < entry3.getValue().size(); i4++) {
                        if (entry3.getValue().get(i4) != null && entry3.getKey() != null) {
                            entry3.getValue().get(i4).compute(entry3.getKey().b, jDJSONObject);
                        }
                    }
                    if (entry3.getKey() != null) {
                        entry3.getKey().C(entry3.getValue());
                    }
                }
            }
        }
    }

    public void g() {
        if (this.f15197c.size() == 0) {
            return;
        }
        for (Map.Entry<com.jingdong.sdk.lib.puppetlayout.h.a, String> entry : this.f15197c.entrySet()) {
            if (entry != null && !TextUtils.isEmpty(entry.getValue())) {
                entry.getKey().q("hiddenType", entry.getValue(), "attribute");
            }
        }
    }

    public void h(PuppetContext puppetContext) {
        ActionProcessor f2;
        if (this.b.size() == 0) {
            return;
        }
        for (Map.Entry<com.jingdong.sdk.lib.puppetlayout.h.a, ArrayList<Action>> entry : this.b.entrySet()) {
            if (entry != null && entry.getValue() != null) {
                for (int i2 = 0; i2 < entry.getValue().size(); i2++) {
                    if (entry.getValue().get(i2) != null && entry.getKey() != null && (f2 = entry.getKey().f()) != null) {
                        f2.process(puppetContext, entry.getKey().f15224j, true);
                    }
                }
            }
        }
    }
}
