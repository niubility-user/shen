package com.jd.dynamic.lib.views;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.R;
import com.jd.dynamic.base.DynamicMtaUtil;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.DynamicUtils;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.viewparse.c.e.g0;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Observable;
import rx.functions.Action1;

@Keep
/* loaded from: classes13.dex */
public class ItemView {
    public static final int TYPE_EXPAND_AND_FOLD = 4000;
    public static final int TYPE_HEADER = 2000;
    public static final int TYPE_ITEM = 1000;
    public static final int TYPE_LOAD_MORE = 3000;
    private final Context context;
    private DynamicTemplateEngine engine;
    private Gson gson;
    private int mItemType;
    public ViewNode mine;
    public ViewNode viewNode;
    private HashMap<Integer, HashMap<String, String>> tagViewOnBind = new HashMap<>(16);
    private Map<Integer, Boolean> onLoadDispatchFinish = new HashMap();

    public ItemView(@NonNull Context context, ViewNode viewNode, DynamicTemplateEngine dynamicTemplateEngine) {
        this.context = context;
        this.mine = viewNode;
        if (viewNode != null && viewNode.getChilds() != null && this.mine.getChilds().size() > 0) {
            this.viewNode = this.mine.getChilds().get(0);
        }
        this.engine = dynamicTemplateEngine;
    }

    /* renamed from: a */
    public /* synthetic */ void b(View view, JSONObject jSONObject, ViewNode viewNode) {
        if (DYConstants.DY_ITEMS.equals(viewNode.getViewName())) {
            return;
        }
        bindData(viewNode, view, jSONObject);
        bindChild(viewNode.getChilds(), view, jSONObject);
    }

    private void bindChild(List<ViewNode> list, final View view, final JSONObject jSONObject) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Observable.from(list).forEach(new Action1() { // from class: com.jd.dynamic.lib.views.n
            {
                ItemView.this = this;
            }

            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ItemView.this.b(view, jSONObject, (ViewNode) obj);
            }
        }, new Action1() { // from class: com.jd.dynamic.lib.views.j
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ItemView.c((Throwable) obj);
            }
        });
    }

    private void bindChildWithCache(List<ViewNode> list, final View view, final JSONObject jSONObject, final HashMap<View, HashMap<String, String>> hashMap) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Observable.from(list).forEach(new Action1() { // from class: com.jd.dynamic.lib.views.m
            {
                ItemView.this = this;
            }

            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ItemView.this.e(view, jSONObject, hashMap, (ViewNode) obj);
            }
        }, new Action1() { // from class: com.jd.dynamic.lib.views.l
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ItemView.f((Throwable) obj);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x01ba A[Catch: Exception -> 0x01ef, TryCatch #0 {Exception -> 0x01ef, blocks: (B:67:0x0006, B:69:0x000e, B:70:0x002e, B:72:0x0034, B:74:0x0047, B:76:0x004b, B:78:0x0058, B:81:0x008a, B:83:0x0098, B:85:0x00c7, B:87:0x00d4, B:88:0x00dd, B:90:0x00ea, B:92:0x0116, B:91:0x0105, B:94:0x0161, B:95:0x016a, B:97:0x0170, B:99:0x017c, B:101:0x0188, B:102:0x0190, B:103:0x01a9, B:105:0x01ba, B:106:0x01d5), top: B:114:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x01d5 A[Catch: Exception -> 0x01ef, TRY_LEAVE, TryCatch #0 {Exception -> 0x01ef, blocks: (B:67:0x0006, B:69:0x000e, B:70:0x002e, B:72:0x0034, B:74:0x0047, B:76:0x004b, B:78:0x0058, B:81:0x008a, B:83:0x0098, B:85:0x00c7, B:87:0x00d4, B:88:0x00dd, B:90:0x00ea, B:92:0x0116, B:91:0x0105, B:94:0x0161, B:95:0x016a, B:97:0x0170, B:99:0x017c, B:101:0x0188, B:102:0x0190, B:103:0x01a9, B:105:0x01ba, B:106:0x01d5), top: B:114:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0170 A[Catch: Exception -> 0x01ef, TryCatch #0 {Exception -> 0x01ef, blocks: (B:67:0x0006, B:69:0x000e, B:70:0x002e, B:72:0x0034, B:74:0x0047, B:76:0x004b, B:78:0x0058, B:81:0x008a, B:83:0x0098, B:85:0x00c7, B:87:0x00d4, B:88:0x00dd, B:90:0x00ea, B:92:0x0116, B:91:0x0105, B:94:0x0161, B:95:0x016a, B:97:0x0170, B:99:0x017c, B:101:0x0188, B:102:0x0190, B:103:0x01a9, B:105:0x01ba, B:106:0x01d5), top: B:114:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void bindData(com.jd.dynamic.entity.ViewNode r20, android.view.View r21, org.json.JSONObject r22) {
        /*
            Method dump skipped, instructions count: 500
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.views.ItemView.bindData(com.jd.dynamic.entity.ViewNode, android.view.View, org.json.JSONObject):void");
    }

    private void bindDataWithCache(ViewNode viewNode, View view, JSONObject jSONObject, HashMap<View, HashMap<String, String>> hashMap) {
        try {
            if (this.engine.getUnbindMap() != null) {
                com.jd.dynamic.lib.viewparse.c.v vVar = new com.jd.dynamic.lib.viewparse.c.v();
                vVar.c(jSONObject);
                vVar.b(this.engine);
                if (viewNode == null) {
                    return;
                }
                this.engine.updateScreenWidth();
                View findViewById = view.findViewById(viewNode.viewId);
                System.nanoTime();
                for (Map.Entry<ViewNode, HashMap<String, String>> entry : this.engine.getUnbindMap().entrySet()) {
                    ViewNode key = entry.getKey();
                    if (key != null && viewNode.viewId == key.viewId) {
                        HashMap<String, String> value = entry.getValue();
                        if (findViewById != null) {
                            int i2 = R.id.dynamic_collection_view;
                            findViewById.setTag(i2, view.getTag(i2));
                            int i3 = R.id.dynamic_item_position;
                            findViewById.setTag(i3, view.getTag(i3));
                            findViewById.setTag(R.id.dynamic_item_data, jSONObject);
                            for (Map.Entry<String, String> entry2 : value.entrySet()) {
                                String key2 = entry2.getKey();
                                String value2 = entry2.getValue();
                                if (DynamicUtils.isElOrKnownSymbol(value2)) {
                                    this.engine.newBindDataWithELWithView(key, jSONObject, value2, key2, findViewById);
                                    if (!TextUtils.isEmpty(value2) && value2.contains("$dark(") && DynamicSdk.getEngine().getDynamicDark() != null) {
                                        String str = key.getELAttributes().get(key2);
                                        if (!TextUtils.isEmpty(str)) {
                                            try {
                                                JSONObject jSONObject2 = new JSONObject();
                                                jSONObject2.put(DynamicSdk.getEngine().getDynamicDark().isDarkMode() ? CustomThemeConstance.NAVI_IMAGE_DARK_TAG : "unDark", str);
                                                key.getELAttributes().put(key2 + "_dark", jSONObject2.toString());
                                            } catch (Exception unused) {
                                            }
                                        }
                                    }
                                }
                            }
                            vVar.a(key.getELAttributes(), findViewById);
                            String str2 = key.getAttributes().get("onBind");
                            if (view.getTag(R.id.dynamic_tag_view_item) == null) {
                                dispatchOnBindEvent(str2, jSONObject, findViewById);
                                dispatchOnLoadEvent(key.getAttributes().get("onLoad"), findViewById);
                            } else {
                                this.tagViewOnBind.put(Integer.valueOf(findViewById.getId()), key.getAttributes());
                            }
                            hashMap.put(findViewById, key.getELAttributes());
                        }
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:267:0x031c  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x02c4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean bindViewUseCache(android.view.View r37, java.lang.String r38, org.json.JSONObject r39) {
        /*
            Method dump skipped, instructions count: 1034
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.views.ItemView.bindViewUseCache(android.view.View, java.lang.String, org.json.JSONObject):boolean");
    }

    public static /* synthetic */ void c(Throwable th) {
    }

    /* renamed from: d */
    public /* synthetic */ void e(View view, JSONObject jSONObject, HashMap hashMap, ViewNode viewNode) {
        if (DYConstants.DY_ITEMS.equals(viewNode.getViewName())) {
            return;
        }
        bindDataWithCache(viewNode, view, jSONObject, hashMap);
        bindChildWithCache(viewNode.getChilds(), view, jSONObject, hashMap);
    }

    public static /* synthetic */ void f(Throwable th) {
    }

    /* renamed from: g */
    public /* synthetic */ void h(View view, JSONObject jSONObject, Map.Entry entry) {
        int intValue = ((Integer) entry.getKey()).intValue();
        HashMap<String, String> hashMap = (HashMap) entry.getValue();
        View d = com.jd.dynamic.lib.utils.m.d(this.engine, intValue, view);
        String str = hashMap.get("onBind");
        if (d != null) {
            if (!TextUtils.isEmpty(str)) {
                dispatchOnBindEvent(str, jSONObject, d);
            }
            String str2 = hashMap.get("onLoad");
            if (!TextUtils.isEmpty(str2)) {
                dispatchOnLoadEvent(str2, d);
            }
            g0 g0Var = new g0();
            g0Var.e(this.engine);
            g0Var.c(jSONObject);
            g0Var.a(hashMap, d);
        }
    }

    private void handCache(String str, JSONArray jSONArray, HashMap<View, HashMap<String, String>> hashMap) {
        JSONObject optJSONObject = this.engine.currentData.optJSONObject("dynamic_item_private_data");
        if (optJSONObject != null && optJSONObject.has(str)) {
            com.jd.dynamic.lib.utils.t.e("handCache", "\u5df2\u7ecf\u6709\u7f13\u5b58");
            return;
        }
        for (Map.Entry<View, HashMap<String, String>> entry : hashMap.entrySet()) {
            View key = entry.getKey();
            HashMap<String, String> value = entry.getValue();
            if (key.getId() != 0 && com.jd.dynamic.lib.utils.m.J(value)) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("layoutId", key.getId());
                    jSONObject.put("attrs", new JSONObject(value));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                jSONArray.put(jSONObject);
            }
        }
        try {
            JSONObject optJSONObject2 = this.engine.currentData.optJSONObject("dynamic_item_private_data");
            if (optJSONObject2 != null) {
                optJSONObject2.put(str, jSONArray);
            } else {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(str, jSONArray);
                this.engine.currentData.put("dynamic_item_private_data", jSONObject2);
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void bindData(View view, JSONObject jSONObject) {
        if (this.viewNode == null) {
            return;
        }
        long nanoTime = System.nanoTime();
        DynamicTemplateEngine dynamicTemplateEngine = this.engine;
        if (dynamicTemplateEngine != null) {
            dynamicTemplateEngine.updateScreenWidth();
        }
        view.setTag(R.id.dynamic_item_type, com.jd.dynamic.b.c.a.b);
        bindData(this.viewNode, view, jSONObject);
        bindChild(this.viewNode.getChilds(), view, jSONObject);
        com.jd.dynamic.lib.utils.t.e("BindItemViewTime", "bindData", view.getTag(R.id.dynamic_item_position), DynamicMtaUtil.getCurMicroseconds(System.nanoTime() - nanoTime));
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x00b6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void bindDataWithCache(android.view.View r17, org.json.JSONObject r18) {
        /*
            Method dump skipped, instructions count: 256
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.views.ItemView.bindDataWithCache(android.view.View, org.json.JSONObject):void");
    }

    public View createView() {
        return createView(false);
    }

    public View createView(boolean z) {
        if (this.viewNode == null) {
            return null;
        }
        long nanoTime = System.nanoTime();
        View view = (View) com.jd.dynamic.lib.viewparse.f.b(this.viewNode.getViewName(), this.viewNode.getAttributes(), this.engine, false, true, z).a(this.viewNode, getContext());
        int i2 = R.id.dynamic_item_view;
        Object obj = com.jd.dynamic.b.c.a.b;
        view.setTag(i2, obj);
        view.setTag(R.id.dynamic_comm_attr_binded, obj);
        com.jd.dynamic.lib.utils.t.e("createItemView", DynamicMtaUtil.getCurMicroseconds(System.nanoTime() - nanoTime));
        return view;
    }

    public void dispatchOnBindEvent(String str, JSONObject jSONObject, View view) {
        DynamicTemplateEngine dynamicTemplateEngine = this.engine;
        if (dynamicTemplateEngine == null || !dynamicTemplateEngine.isAttached) {
            return;
        }
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if (jSONObject != null) {
                jSONObject.put("_dyn_bind_flag_", "1");
            }
            Iterator<String> it = com.jd.dynamic.lib.utils.s.i(str).iterator();
            while (it.hasNext()) {
                com.jd.dynamic.lib.utils.s.b(it.next(), view, this.engine, view);
            }
        } catch (Exception unused) {
        }
    }

    public void dispatchOnLoadEvent(String str, View view) {
        DynamicTemplateEngine dynamicTemplateEngine = this.engine;
        if (dynamicTemplateEngine == null || !dynamicTemplateEngine.isAttached) {
            return;
        }
        try {
            if (TextUtils.isEmpty(str) || view == null) {
                return;
            }
            if (this.onLoadDispatchFinish.containsKey(Integer.valueOf(view.hashCode())) && this.onLoadDispatchFinish.get(Integer.valueOf(view.hashCode())).booleanValue()) {
                return;
            }
            this.onLoadDispatchFinish.put(Integer.valueOf(view.hashCode()), Boolean.TRUE);
            Iterator<String> it = com.jd.dynamic.lib.utils.s.i(str).iterator();
            while (it.hasNext()) {
                com.jd.dynamic.lib.utils.s.b(it.next(), view, this.engine, view);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void dispatchTagViewEvent(final JSONObject jSONObject, final View view) {
        DynamicTemplateEngine dynamicTemplateEngine = this.engine;
        if (dynamicTemplateEngine == null || !dynamicTemplateEngine.isAttached || this.tagViewOnBind.isEmpty()) {
            return;
        }
        Observable.from(this.tagViewOnBind.entrySet()).forEach(new Action1() { // from class: com.jd.dynamic.lib.views.k
            {
                ItemView.this = this;
            }

            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ItemView.this.h(view, jSONObject, (Map.Entry) obj);
            }
        });
    }

    public Context getContext() {
        return this.context;
    }

    public Object getTag() {
        return Integer.valueOf(this.mItemType);
    }

    public View parse() {
        return parse(false);
    }

    public View parse(boolean z) {
        ViewNode viewNode = this.viewNode;
        if (viewNode == null) {
            return null;
        }
        View view = (View) com.jd.dynamic.lib.viewparse.f.b(viewNode.getViewName(), this.viewNode.getAttributes(), this.engine, false, true, z).a(this.viewNode, getContext());
        view.setTag(R.id.dynamic_item_view, com.jd.dynamic.b.c.a.b);
        return view;
    }

    public void refreshItemData(View view, JSONObject jSONObject, int i2) {
        if (this.engine.getUnbindMap() != null) {
            com.jd.dynamic.lib.viewparse.c.v vVar = new com.jd.dynamic.lib.viewparse.c.v();
            vVar.c(jSONObject);
            vVar.b(this.engine);
            for (Map.Entry<ViewNode, HashMap<String, String>> entry : this.engine.getUnbindMap().entrySet()) {
                ViewNode key = entry.getKey();
                if (key != null && i2 == key.viewId) {
                    HashMap<String, String> value = entry.getValue();
                    View findViewById = view.findViewById(i2);
                    if (findViewById != null) {
                        for (Map.Entry<String, String> entry2 : value.entrySet()) {
                            this.engine.newBindDataWithEL(key, jSONObject, entry2.getValue(), entry2.getKey());
                        }
                        vVar.a(key.getELAttributes(), findViewById);
                    }
                }
            }
        }
    }

    public void setTag(int i2) {
        this.mItemType = i2;
    }
}
