package com.jd.dynamic.lib.j;

import android.os.AsyncTask;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.DynamicUtils;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.utils.m;
import com.jd.dynamic.lib.utils.p;
import com.jd.dynamic.lib.utils.t;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Observable;
import rx.functions.Action1;

/* loaded from: classes13.dex */
public class c {
    private ViewNode a;
    private DynamicTemplateEngine b;

    /* renamed from: c  reason: collision with root package name */
    private JSONArray f2250c;
    private ArrayList<JSONObject> d = new ArrayList<>();

    /* renamed from: e  reason: collision with root package name */
    private Map<String, Integer> f2251e = new HashMap();

    /* renamed from: f  reason: collision with root package name */
    private AtomicInteger f2252f = new AtomicInteger(100);

    public c(ViewNode viewNode, DynamicTemplateEngine dynamicTemplateEngine, JSONArray jSONArray) {
        this.a = viewNode;
        this.b = dynamicTemplateEngine;
        this.f2250c = jSONArray;
        i();
    }

    private void c(int i2, JSONObject jSONObject, HashMap<Integer, HashMap<String, String>> hashMap, String str, JSONObject jSONObject2) {
        JSONArray jSONArray = new JSONArray();
        ViewNode h2 = h(i2);
        d(h2, jSONObject, hashMap);
        f(h2.getChilds(), jSONObject, hashMap);
        for (Map.Entry<Integer, HashMap<String, String>> entry : hashMap.entrySet()) {
            int intValue = entry.getKey().intValue();
            HashMap<String, String> value = entry.getValue();
            if (intValue != 0 && m.J(value)) {
                JSONObject jSONObject3 = new JSONObject();
                try {
                    jSONObject3.put("layoutId", intValue);
                    jSONObject3.put("attrs", new JSONObject(value));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                jSONArray.put(jSONObject3);
            }
        }
        try {
            if (jSONObject2 != null) {
                jSONObject2.put(str, jSONArray);
            } else {
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put(str, jSONArray);
                this.b.currentData.put("dynamic_item_private_data", jSONObject4);
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    private void d(ViewNode viewNode, JSONObject jSONObject, HashMap<Integer, HashMap<String, String>> hashMap) {
        try {
            if (this.b.getUnbindMap() == null || viewNode == null) {
                return;
            }
            ViewNode viewNode2 = new ViewNode();
            viewNode2.viewId = viewNode.viewId;
            int i2 = viewNode.viewId;
            for (Map.Entry<ViewNode, HashMap<String, String>> entry : this.b.getUnbindMap().entrySet()) {
                ViewNode key = entry.getKey();
                if (i2 == key.viewId) {
                    for (Map.Entry<String, String> entry2 : entry.getValue().entrySet()) {
                        String key2 = entry2.getKey();
                        String value = entry2.getValue();
                        if (DynamicUtils.isElOrKnownSymbol(value)) {
                            this.b.newBindDataWithEL(viewNode2, jSONObject, value, key2);
                            if (!TextUtils.isEmpty(value) && value.contains("$dark(") && DynamicSdk.getEngine().getDynamicDark() != null) {
                                String str = viewNode2.getELAttributes().get(key2);
                                if (!TextUtils.isEmpty(str)) {
                                    try {
                                        JSONObject jSONObject2 = new JSONObject();
                                        jSONObject2.put(DynamicSdk.getEngine().getDynamicDark().isDarkMode() ? CustomThemeConstance.NAVI_IMAGE_DARK_TAG : "unDark", str);
                                        viewNode2.getELAttributes().put(key2 + "_dark", jSONObject2.toString());
                                    } catch (Exception unused) {
                                    }
                                }
                            }
                        }
                    }
                    hashMap.put(Integer.valueOf(i2), viewNode2.getELAttributes());
                    if (com.jd.dynamic.b.a.b.o().z() && (TextUtils.equals(key.getViewName(), DYConstants.DYN_TAG_VIEW) || TextUtils.equals(key.getViewName(), DYConstants.DYN_COLLECTION_VIEW))) {
                        String str2 = viewNode2.getELAttributes().get("data");
                        if (!TextUtils.isEmpty(str2)) {
                            d dVar = new d(this.b, i2);
                            dVar.c(new JSONArray(str2));
                            dVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                        }
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void e(Throwable th) {
    }

    private void f(List<ViewNode> list, final JSONObject jSONObject, final HashMap<Integer, HashMap<String, String>> hashMap) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Observable.from(list).forEach(new Action1() { // from class: com.jd.dynamic.lib.j.b
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                c.this.g(jSONObject, hashMap, (ViewNode) obj);
            }
        }, new Action1() { // from class: com.jd.dynamic.lib.j.a
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                c.e((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(JSONObject jSONObject, HashMap hashMap, ViewNode viewNode) {
        if (DYConstants.DY_ITEMS.equals(viewNode.getViewName())) {
            return;
        }
        d(viewNode, jSONObject, hashMap);
        f(viewNode.getChilds(), jSONObject, hashMap);
    }

    private void i() {
        this.d.clear();
        JSONArray jSONArray = this.f2250c;
        if (jSONArray == null || jSONArray.length() <= 0) {
            return;
        }
        for (int i2 = 0; i2 < this.f2250c.length(); i2++) {
            this.d.add(this.f2250c.optJSONObject(i2));
        }
    }

    private int j() {
        ArrayList<JSONObject> arrayList = this.d;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    private int k(int i2) {
        ViewNode viewNode;
        JSONObject jSONObject = this.d.get(i2);
        if (jSONObject == null || (viewNode = this.a) == null) {
            return 1000;
        }
        try {
            String str = viewNode.getAttributes().get(DYConstants.DY_ITEM_IDENTIFIER);
            if (TextUtils.isEmpty(str)) {
                str = DYConstants.DY_IDENTIFIER;
            }
            String optString = jSONObject.optString(str);
            if (TextUtils.isEmpty(optString)) {
                return 1000;
            }
            Integer num = this.f2251e.get(optString);
            if (num != null) {
                return num.intValue();
            }
            int parseInt = Integer.parseInt(optString);
            this.f2251e.put(optString, Integer.valueOf(parseInt));
            return parseInt;
        } catch (Exception unused) {
            int andDecrement = this.f2252f.getAndDecrement();
            this.f2251e.put(null, Integer.valueOf(andDecrement));
            return andDecrement;
        }
    }

    public int a() {
        return j();
    }

    @NonNull
    public void b(int i2) {
        int k2 = k(i2);
        ArrayList<JSONObject> arrayList = this.d;
        if (arrayList == null) {
            return;
        }
        JSONObject jSONObject = arrayList.get(i2);
        HashMap<Integer, HashMap<String, String>> hashMap = new HashMap<>();
        String a = p.a(jSONObject.toString());
        JSONObject optJSONObject = this.b.currentData.optJSONObject("dynamic_item_private_data");
        if (optJSONObject != null && optJSONObject.has(a)) {
            t.e("PreParseTask", "\u5df2\u7ecf\u6709\u7f13\u5b58\uff0c\u4e0d\u9700\u8981\u89e3\u6790");
            return;
        }
        this.b.getItemLock(a, true);
        c(k2, jSONObject, hashMap, a, optJSONObject);
        this.b.removeItemLock(a);
    }

    public ViewNode h(int i2) {
        ViewNode next;
        if (m.I(this.a.getChilds())) {
            Iterator<ViewNode> it = this.a.getChilds().iterator();
            while (it.hasNext() && (next = it.next()) != null && m.J(next.getAttributes()) && !TextUtils.isEmpty(next.getAttributes().get(DYConstants.DY_IDENTIFIER))) {
                Integer num = this.f2251e.get(next.getAttributes().get(DYConstants.DY_IDENTIFIER));
                if (num != null && i2 == num.intValue()) {
                    return next;
                }
            }
            return this.a.getChilds().get(0);
        }
        return null;
    }
}
