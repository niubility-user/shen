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
import com.jd.dynamic.entity.MtaTimePair;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.viewparse.c.e.g0;
import com.jd.dynamic.yoga.android.YogaLayout;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import java.util.ArrayList;
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

    /* JADX WARN: Removed duplicated region for block: B:161:0x0170 A[Catch: Exception -> 0x01ef, TryCatch #0 {Exception -> 0x01ef, blocks: (B:131:0x0006, B:133:0x000e, B:134:0x002e, B:136:0x0034, B:138:0x0047, B:140:0x004b, B:142:0x0058, B:145:0x008a, B:147:0x0098, B:149:0x00c7, B:151:0x00d4, B:152:0x00dd, B:154:0x00ea, B:156:0x0116, B:155:0x0105, B:158:0x0161, B:159:0x016a, B:161:0x0170, B:163:0x017c, B:165:0x0188, B:166:0x0190, B:167:0x01a9, B:169:0x01ba, B:170:0x01d5), top: B:178:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:169:0x01ba A[Catch: Exception -> 0x01ef, TryCatch #0 {Exception -> 0x01ef, blocks: (B:131:0x0006, B:133:0x000e, B:134:0x002e, B:136:0x0034, B:138:0x0047, B:140:0x004b, B:142:0x0058, B:145:0x008a, B:147:0x0098, B:149:0x00c7, B:151:0x00d4, B:152:0x00dd, B:154:0x00ea, B:156:0x0116, B:155:0x0105, B:158:0x0161, B:159:0x016a, B:161:0x0170, B:163:0x017c, B:165:0x0188, B:166:0x0190, B:167:0x01a9, B:169:0x01ba, B:170:0x01d5), top: B:178:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:170:0x01d5 A[Catch: Exception -> 0x01ef, TRY_LEAVE, TryCatch #0 {Exception -> 0x01ef, blocks: (B:131:0x0006, B:133:0x000e, B:134:0x002e, B:136:0x0034, B:138:0x0047, B:140:0x004b, B:142:0x0058, B:145:0x008a, B:147:0x0098, B:149:0x00c7, B:151:0x00d4, B:152:0x00dd, B:154:0x00ea, B:156:0x0116, B:155:0x0105, B:158:0x0161, B:159:0x016a, B:161:0x0170, B:163:0x017c, B:165:0x0188, B:166:0x0190, B:167:0x01a9, B:169:0x01ba, B:170:0x01d5), top: B:178:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void bindData(ViewNode viewNode, View view, JSONObject jSONObject) {
        com.jd.dynamic.lib.viewparse.c.v vVar;
        Iterator<Map.Entry<ViewNode, HashMap<String, String>>> it;
        Object obj;
        com.jd.dynamic.lib.viewparse.c.v vVar2;
        String str;
        Object obj2;
        try {
            if (this.engine.getUnbindMap() != null) {
                com.jd.dynamic.lib.viewparse.c.v vVar3 = new com.jd.dynamic.lib.viewparse.c.v();
                vVar3.c(jSONObject);
                vVar3.b(this.engine);
                this.engine.updateScreenWidth();
                Iterator<Map.Entry<ViewNode, HashMap<String, String>>> it2 = this.engine.getUnbindMap().entrySet().iterator();
                while (it2.hasNext()) {
                    Map.Entry<ViewNode, HashMap<String, String>> next = it2.next();
                    ViewNode key = next.getKey();
                    int i2 = viewNode.viewId;
                    if (key != null && i2 == key.viewId) {
                        HashMap<String, String> value = next.getValue();
                        View findViewById = view.findViewById(i2);
                        if (findViewById != null) {
                            int i3 = R.id.dynamic_view_need_rebind;
                            String str2 = null;
                            findViewById.setTag(i3, null);
                            int i4 = R.id.dynamic_collection_view;
                            findViewById.setTag(i4, view.getTag(i4));
                            int i5 = R.id.dynamic_item_position;
                            findViewById.setTag(i5, view.getTag(i5));
                            findViewById.setTag(R.id.dynamic_item_data, jSONObject);
                            DynamicTemplateEngine dynamicTemplateEngine = this.engine;
                            dynamicTemplateEngine.calculateVisibilityFromRoot(key, jSONObject, findViewById, dynamicTemplateEngine);
                            if (com.jd.dynamic.b.a.b.o().y()) {
                                String str3 = value.get("visibility");
                                if (!TextUtils.isEmpty(str3)) {
                                    key.getELAttributes().clear();
                                    it = it2;
                                    str = "visibility";
                                    obj = "onBind";
                                    vVar2 = vVar3;
                                    obj2 = "onLoad";
                                    this.engine.newBindDataWithELWithView(key, jSONObject, str3, "visibility", findViewById);
                                    str2 = key.getELAttributes().get(str);
                                    if (TextUtils.equals(str2, "2")) {
                                        com.jd.dynamic.lib.utils.m.r(findViewById, 8);
                                        if (findViewById.getParent() instanceof YogaLayout) {
                                            ((YogaLayout) findViewById.getParent()).invalidate(findViewById);
                                        }
                                        findViewById.setTag(i3, com.jd.dynamic.b.c.a.b);
                                        if (view.getTag(R.id.dynamic_tag_view_item) == null) {
                                            dispatchOnBindEvent(viewNode.getAttributes().get(obj), jSONObject, findViewById);
                                            dispatchOnLoadEvent(viewNode.getAttributes().get(obj2), findViewById);
                                        } else {
                                            this.tagViewOnBind.put(Integer.valueOf(findViewById.getId()), viewNode.getAttributes());
                                        }
                                        com.jd.dynamic.lib.utils.t.e("ViewRefresh", "binditem", "only change gone child = " + findViewById.getId(), "map size = " + value.size());
                                        vVar3 = vVar2;
                                        it2 = it;
                                    }
                                    String str4 = str2;
                                    for (Map.Entry<String, String> entry : value.entrySet()) {
                                        if (TextUtils.isEmpty(str4) || !TextUtils.equals(entry.getKey(), str)) {
                                            this.engine.newBindDataWithELWithView(key, jSONObject, entry.getValue(), entry.getKey(), findViewById);
                                        } else {
                                            key.getELAttributes().put(str, str4);
                                        }
                                    }
                                    vVar = vVar2;
                                    vVar.a(key.getELAttributes(), findViewById);
                                    if (view.getTag(R.id.dynamic_tag_view_item) != null) {
                                        dispatchOnBindEvent(viewNode.getAttributes().get(obj), jSONObject, findViewById);
                                        dispatchOnLoadEvent(viewNode.getAttributes().get(obj2), findViewById);
                                    } else {
                                        this.tagViewOnBind.put(Integer.valueOf(findViewById.getId()), viewNode.getAttributes());
                                    }
                                    vVar3 = vVar;
                                    it2 = it;
                                }
                            }
                            obj = "onBind";
                            vVar2 = vVar3;
                            it = it2;
                            str = "visibility";
                            obj2 = "onLoad";
                            String str42 = str2;
                            while (r13.hasNext()) {
                            }
                            vVar = vVar2;
                            vVar.a(key.getELAttributes(), findViewById);
                            if (view.getTag(R.id.dynamic_tag_view_item) != null) {
                            }
                            vVar3 = vVar;
                            it2 = it;
                        }
                    }
                    vVar = vVar3;
                    it = it2;
                    vVar3 = vVar;
                    it2 = it;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
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

    /* JADX WARN: Removed duplicated region for block: B:421:0x031c  */
    /* JADX WARN: Removed duplicated region for block: B:443:0x02c4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean bindViewUseCache(View view, String str, JSONObject jSONObject) {
        int i2;
        long j2;
        JSONArray jSONArray;
        long j3;
        View view2;
        View view3;
        String str2;
        HashMap<String, String> hashMap;
        String str3;
        Iterator<Map.Entry<String, String>> it;
        long j4;
        JSONObject jSONObject2;
        View view4;
        String str4;
        String str5;
        View view5 = view;
        JSONObject jSONObject3 = jSONObject;
        DynamicTemplateEngine dynamicTemplateEngine = this.engine;
        boolean z = false;
        if (dynamicTemplateEngine == null || dynamicTemplateEngine.currentData == null) {
            return false;
        }
        long nanoTime = System.nanoTime();
        JSONObject optJSONObject = this.engine.currentData.optJSONObject("dynamic_item_private_data");
        if (optJSONObject == null) {
            return false;
        }
        JSONArray optJSONArray = optJSONObject.optJSONArray(str);
        if (com.jd.dynamic.lib.utils.m.K(optJSONArray)) {
            MtaTimePair.newInstance().startRecord();
            com.jd.dynamic.lib.viewparse.c.v vVar = new com.jd.dynamic.lib.viewparse.c.v();
            vVar.c(jSONObject3);
            vVar.b(this.engine);
            long j5 = 0;
            long j6 = 0;
            int i3 = 0;
            while (i3 < optJSONArray.length()) {
                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i3);
                if (optJSONObject2 == null) {
                    return z;
                }
                int optInt = optJSONObject2.optInt("layoutId");
                JSONObject optJSONObject3 = optJSONObject2.optJSONObject("attrs");
                if (optJSONObject3 == null) {
                    return z;
                }
                long nanoTime2 = System.nanoTime();
                View findViewById = view5.findViewById(optInt);
                if (findViewById == null) {
                    return z;
                }
                int i4 = R.id.dynamic_collection_view;
                findViewById.setTag(i4, view5.getTag(i4));
                int i5 = R.id.dynamic_item_position;
                findViewById.setTag(i5, view5.getTag(i5));
                findViewById.setTag(R.id.dynamic_item_data, jSONObject3);
                int i6 = R.id.dynamic_view_need_rebind;
                findViewById.setTag(i6, null);
                long nanoTime3 = j5 + (System.nanoTime() - nanoTime2);
                if (this.gson == null) {
                    this.gson = new Gson();
                }
                HashMap<String, String> hashMap2 = (HashMap) this.gson.fromJson(optJSONObject3.toString(), HashMap.class);
                if (com.jd.dynamic.lib.utils.m.J(hashMap2) && com.jd.dynamic.lib.utils.m.E(this.engine.getElAttrMapping())) {
                    if (this.engine.isAttached) {
                        com.jd.dynamic.lib.utils.m.A(hashMap2, findViewById);
                    }
                    HashMap<String, String> hashMap3 = this.engine.getElAttrMapping().get(optInt);
                    String str6 = hashMap3.get(DYConstants.DYN_KEEP_ATTRS);
                    if (com.jd.dynamic.lib.utils.m.J(hashMap3)) {
                        String str7 = "$dark(";
                        if (com.jd.dynamic.b.a.b.o().y()) {
                            i2 = i3;
                            jSONArray = optJSONArray;
                            String str8 = hashMap2.get("visibility");
                            j2 = nanoTime;
                            String str9 = hashMap3.get("visibility");
                            if (((!TextUtils.isEmpty(str9) && !str9.contains("$dark(")) || (!TextUtils.isEmpty(str6) && !str6.contains("visibility"))) && TextUtils.equals(str8, "2")) {
                                com.jd.dynamic.lib.utils.m.r(findViewById, 8);
                                if (findViewById.getParent() instanceof YogaLayout) {
                                    ((YogaLayout) findViewById.getParent()).invalidate(findViewById);
                                }
                                if (view5.getTag(R.id.dynamic_tag_view_item) == null) {
                                    com.jd.dynamic.lib.utils.m.s(findViewById, this.engine);
                                } else {
                                    this.tagViewOnBind.put(Integer.valueOf(findViewById.getId()), hashMap2);
                                }
                                findViewById.setTag(i6, com.jd.dynamic.b.c.a.b);
                                com.jd.dynamic.lib.utils.t.e("ViewRefresh", "binditem cache", "only refresh Gone child = " + findViewById.getId() + " size = " + hashMap2.size() + " continue");
                                view3 = view5;
                                i3 = i2 + 1;
                                jSONObject3 = jSONObject;
                                view5 = view3;
                                j5 = nanoTime3;
                                optJSONArray = jSONArray;
                                nanoTime = j2;
                                z = false;
                            }
                        } else {
                            i2 = i3;
                            j2 = nanoTime;
                            jSONArray = optJSONArray;
                        }
                        Iterator<Map.Entry<String, String>> it2 = hashMap2.entrySet().iterator();
                        while (it2.hasNext()) {
                            Map.Entry<String, String> next = it2.next();
                            String key = next.getKey();
                            next.getValue();
                            String str10 = hashMap3.get(key);
                            if (TextUtils.isEmpty(str10) || !str10.contains(str7) || DynamicSdk.getEngine().getDynamicDark() == null) {
                                str2 = str6;
                                hashMap = hashMap3;
                                str3 = str7;
                                it = it2;
                                j4 = j6;
                            } else {
                                String optString = optJSONObject3.optString(key + "_dark");
                                String str11 = str6;
                                HashMap<String, String> hashMap4 = hashMap3;
                                com.jd.dynamic.lib.utils.t.e("itemDarkAttr", key, str10, optString);
                                boolean isDarkMode = DynamicSdk.getEngine().getDynamicDark().isDarkMode();
                                try {
                                    JSONObject jSONObject4 = !TextUtils.isEmpty(optString) ? new JSONObject(optString) : new JSONObject();
                                    String str12 = isDarkMode ? CustomThemeConstance.NAVI_IMAGE_DARK_TAG : "unDark";
                                    try {
                                        str5 = jSONObject4.getString(str12);
                                    } catch (Exception unused) {
                                        str5 = null;
                                    }
                                    if (str5 == null) {
                                        ViewNode viewNode = new ViewNode();
                                        str2 = str11;
                                        str3 = str7;
                                        it = it2;
                                        j4 = j6;
                                        jSONObject2 = optJSONObject3;
                                        view4 = findViewById;
                                        JSONObject jSONObject5 = jSONObject4;
                                        hashMap = hashMap4;
                                        try {
                                            this.engine.newBindDataWithELWithView(viewNode, jSONObject, str10, key, view4);
                                            str5 = viewNode.getELAttributes().remove(key);
                                            jSONObject5.put(str12, str5);
                                            jSONObject2.put(key + "_dark", jSONObject5.toString());
                                        } catch (Exception unused2) {
                                        }
                                    } else {
                                        str2 = str11;
                                        str3 = str7;
                                        it = it2;
                                        j4 = j6;
                                        hashMap = hashMap4;
                                        jSONObject2 = optJSONObject3;
                                        view4 = findViewById;
                                    }
                                    if (str5 == null) {
                                        str5 = "";
                                    }
                                    next.setValue(str5);
                                } catch (Exception unused3) {
                                    str2 = str11;
                                    str3 = str7;
                                    it = it2;
                                    j4 = j6;
                                    hashMap = hashMap4;
                                }
                                if (TextUtils.isEmpty(str2)) {
                                    try {
                                        str4 = str2;
                                        try {
                                            JSONArray jSONArray2 = new JSONArray(str4);
                                            if (this.gson == null) {
                                                this.gson = new Gson();
                                            }
                                            ArrayList arrayList = (ArrayList) this.gson.fromJson(jSONArray2.toString(), ArrayList.class);
                                            if (com.jd.dynamic.lib.utils.m.I(arrayList) && arrayList.contains(key)) {
                                                ViewNode viewNode2 = new ViewNode();
                                                this.engine.newBindDataWithELWithView(viewNode2, jSONObject, str10, key, view4);
                                                String remove = viewNode2.getELAttributes().remove(key);
                                                if (remove == null) {
                                                    remove = "";
                                                }
                                                next.setValue(remove);
                                            }
                                        } catch (Exception e2) {
                                            e = e2;
                                            e.printStackTrace();
                                            str6 = str4;
                                            optJSONObject3 = jSONObject2;
                                            findViewById = view4;
                                            hashMap3 = hashMap;
                                            it2 = it;
                                            j6 = j4;
                                            str7 = str3;
                                        }
                                    } catch (Exception e3) {
                                        e = e3;
                                        str4 = str2;
                                    }
                                } else {
                                    str4 = str2;
                                }
                                str6 = str4;
                                optJSONObject3 = jSONObject2;
                                findViewById = view4;
                                hashMap3 = hashMap;
                                it2 = it;
                                j6 = j4;
                                str7 = str3;
                            }
                            jSONObject2 = optJSONObject3;
                            view4 = findViewById;
                            if (TextUtils.isEmpty(str2)) {
                            }
                            str6 = str4;
                            optJSONObject3 = jSONObject2;
                            findViewById = view4;
                            hashMap3 = hashMap;
                            it2 = it;
                            j6 = j4;
                            str7 = str3;
                        }
                    } else {
                        i2 = i3;
                        j2 = nanoTime;
                        jSONArray = optJSONArray;
                    }
                    j3 = j6;
                    view2 = findViewById;
                } else {
                    i2 = i3;
                    j2 = nanoTime;
                    jSONArray = optJSONArray;
                    j3 = j6;
                    view2 = findViewById;
                    com.jd.dynamic.lib.utils.t.e("ViewRefresh", "cache attrs is empty or elMap isEmpty continue view = " + view2.getId());
                }
                long nanoTime4 = System.nanoTime();
                View view6 = view2;
                if (com.jd.dynamic.lib.utils.m.J(hashMap2)) {
                    vVar.a(hashMap2, view6);
                }
                view3 = view;
                if (view3.getTag(R.id.dynamic_tag_view_item) == null) {
                    com.jd.dynamic.lib.utils.m.s(view6, this.engine);
                } else {
                    this.tagViewOnBind.put(Integer.valueOf(view6.getId()), hashMap2);
                }
                j6 = j3 + (System.nanoTime() - nanoTime4);
                i3 = i2 + 1;
                jSONObject3 = jSONObject;
                view5 = view3;
                j5 = nanoTime3;
                optJSONArray = jSONArray;
                nanoTime = j2;
                z = false;
            }
            com.jd.dynamic.lib.utils.t.e("BindItemView", "WithCache", " bind time = " + DynamicMtaUtil.getCurMicroseconds(j6), "findTime = " + DynamicMtaUtil.getCurMicroseconds(j5), "all time = " + DynamicMtaUtil.getCurMicroseconds(System.nanoTime() - nanoTime));
            return true;
        }
        return false;
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

    /* JADX WARN: Removed duplicated region for block: B:92:0x00b6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void bindDataWithCache(View view, JSONObject jSONObject) {
        JSONObject jSONObject2;
        String a;
        Object itemLock;
        boolean z = view.getTag(R.id.dynamic_tag_view_item) != null;
        if (this.viewNode == null) {
            return;
        }
        view.setTag(R.id.dynamic_item_type, com.jd.dynamic.b.c.a.b);
        long nanoTime = System.nanoTime();
        try {
            jSONObject2 = new JSONObject(jSONObject.toString());
            try {
                jSONObject2.remove("_dyn_bind_flag_");
                jSONObject2.remove("_dyn_vertical_visibility_change_flag_");
                jSONObject2.remove("_dyn_horizontal_visibility_change_flag_");
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                a = com.jd.dynamic.lib.utils.p.a(jSONObject2.toString());
                itemLock = this.engine.getItemLock(a, false);
                this.engine.updateScreenWidth();
                if (itemLock != null) {
                }
                JSONArray jSONArray = new JSONArray();
                HashMap<View, HashMap<String, String>> hashMap = new HashMap<>();
                bindDataWithCache(this.viewNode, view, jSONObject, hashMap);
                bindChildWithCache(this.viewNode.getChilds(), view, jSONObject, hashMap);
                if (this.engine.getItemLock(a, false) == null) {
                }
                com.jd.dynamic.lib.utils.t.e("BindItemViewTime", "isTagView = " + z, "bindDataWithCache", view.getTag(R.id.dynamic_item_position), DynamicMtaUtil.getCurMicroseconds(System.nanoTime() - nanoTime));
            }
        } catch (Exception e3) {
            e = e3;
            jSONObject2 = jSONObject;
        }
        a = com.jd.dynamic.lib.utils.p.a(jSONObject2.toString());
        itemLock = this.engine.getItemLock(a, false);
        this.engine.updateScreenWidth();
        if (itemLock != null && bindViewUseCache(view, a, jSONObject)) {
            com.jd.dynamic.lib.utils.t.e("BindItemViewTime", "isTagView = " + z, "bindViewUseCache", view.getTag(R.id.dynamic_item_position), DynamicMtaUtil.getCurMicroseconds(System.nanoTime() - nanoTime));
            return;
        }
        JSONArray jSONArray2 = new JSONArray();
        HashMap<View, HashMap<String, String>> hashMap2 = new HashMap<>();
        bindDataWithCache(this.viewNode, view, jSONObject, hashMap2);
        bindChildWithCache(this.viewNode.getChilds(), view, jSONObject, hashMap2);
        if (this.engine.getItemLock(a, false) == null) {
            com.jd.dynamic.lib.utils.t.e("handCache", "withLock", view.getTag(R.id.dynamic_item_position));
            handCache(a, jSONArray2, hashMap2);
        }
        com.jd.dynamic.lib.utils.t.e("BindItemViewTime", "isTagView = " + z, "bindDataWithCache", view.getTag(R.id.dynamic_item_position), DynamicMtaUtil.getCurMicroseconds(System.nanoTime() - nanoTime));
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
