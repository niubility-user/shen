package com.jingdong.sdk.lib.puppetlayout.f.c;

import android.text.TextUtils;
import android.util.SparseArray;
import com.jingdong.sdk.lib.puppetlayout.ylayout.DynamicHelper;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.Action;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.Span;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes8.dex */
public class b {
    public Map<String, String> a = new HashMap();
    public SparseArray<Float> b = new SparseArray<>();

    /* renamed from: c */
    public SparseArray<String> f15209c = new SparseArray<>();
    public List<a> d = new ArrayList();

    /* renamed from: e */
    public Set<String> f15210e = new HashSet();

    /* renamed from: f */
    public String f15211f = "";

    /* renamed from: g */
    public String f15212g = "";

    /* renamed from: h */
    private HashMap<String, String> f15213h = new HashMap<>();

    /* renamed from: i */
    public ArrayList<Action> f15214i = null;

    /* renamed from: j */
    public ArrayList<Span> f15215j = null;

    /* renamed from: k */
    public boolean f15216k = false;

    /* renamed from: l */
    private DynamicHelper f15217l = new DynamicHelper();

    public b(com.jingdong.sdk.lib.puppetlayout.b bVar) {
    }

    private void c(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        if ("hiddenType".equals(str)) {
            this.f15211f = str2;
        } else if ("showType".equals(str)) {
            this.f15212g = str2;
        } else {
            this.a.put(str, str2);
        }
    }

    private void f(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        com.jingdong.sdk.lib.puppetlayout.h.d.b.b(str, str2, this.b, this.f15209c);
    }

    public void a(String str, String str2, String str3, Object obj) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        if (!"layout".equals(str3) && !"attribute".equals(str3)) {
            if ("actions".equals(str3)) {
                Action action = (Action) obj;
                if (this.f15214i == null) {
                    this.f15214i = new ArrayList<>();
                }
                this.f15214i.add(action);
                if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                    com.jingdong.sdk.lib.puppetlayout.g.b.a("setClickAction", "setClickAction actionList : actionList " + this.f15214i);
                }
            }
        } else if (DynamicHelper.isThreeUnknown(str2)) {
            a aVar = new a(str, str2, str3, 1);
            this.d.add(aVar);
            this.f15210e.add(aVar.a());
        } else if (DynamicHelper.isDynamic(str2)) {
            a aVar2 = new a(str, str2, str3);
            this.d.add(aVar2);
            this.f15210e.add(aVar2.a());
        } else if ("layout".equals(str3)) {
            f(str, str2);
        } else if ("attribute".equals(str3)) {
            c(str, str2);
        }
    }

    public HashMap<String, String> b() {
        return this.f15213h;
    }

    public void d(ArrayList<Span> arrayList, boolean z) {
        this.f15215j = arrayList;
        this.f15216k = z;
    }

    public void e(com.jingdong.sdk.lib.puppetlayout.h.a aVar) {
        if (aVar != null) {
            for (Map.Entry<String, String> entry : this.a.entrySet()) {
                aVar.q(entry.getKey(), entry.getValue(), "attribute");
            }
            aVar.s();
        }
    }
}
