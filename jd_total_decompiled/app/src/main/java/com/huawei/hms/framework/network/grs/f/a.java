package com.huawei.hms.framework.network.grs.f;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.network.grs.GrsApp;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.tencent.mapsdk.internal.i2;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public abstract class a {
    protected com.huawei.hms.framework.network.grs.local.model.a a;
    protected List<com.huawei.hms.framework.network.grs.local.model.b> b;

    /* renamed from: c */
    protected Map<String, String> f1301c = new ConcurrentHashMap(16);
    protected boolean d = false;

    /* renamed from: e */
    protected boolean f1302e = false;

    /* renamed from: f */
    protected boolean f1303f = false;

    /* renamed from: g */
    protected Set<String> f1304g = new HashSet(16);

    private Map<String, String> a(List<com.huawei.hms.framework.network.grs.local.model.b> list, GrsBaseInfo grsBaseInfo, String str) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(16);
        concurrentHashMap.put("no_route_country", "no-country");
        for (com.huawei.hms.framework.network.grs.local.model.b bVar : list) {
            if (bVar.a().contains(grsBaseInfo.getIssueCountry())) {
                concurrentHashMap.put(grsBaseInfo.getIssueCountry(), bVar.b());
            }
            if (bVar.a().contains(grsBaseInfo.getRegCountry())) {
                concurrentHashMap.put(grsBaseInfo.getRegCountry(), bVar.b());
            }
            if (bVar.a().contains(grsBaseInfo.getSerCountry())) {
                concurrentHashMap.put(grsBaseInfo.getSerCountry(), bVar.b());
            }
            if (bVar.a().contains(str)) {
                Logger.v("AbstractLocalManager", "get countryGroupID from geoIp");
                concurrentHashMap.put(str, bVar.b());
            }
        }
        return concurrentHashMap;
    }

    private int b(String str, Context context) {
        if (f(com.huawei.hms.framework.network.grs.h.c.a(str, context)) == 0) {
            Logger.i("AbstractLocalManager", "load APP_CONFIG_FILE success{%s}.", str);
            return 0;
        }
        return -1;
    }

    private int f(String str) {
        int b;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (!this.f1302e || (b = b(str)) == 0) {
            int a = a(str);
            return a != 0 ? a : e(str);
        }
        return b;
    }

    private int g(String str) {
        List<com.huawei.hms.framework.network.grs.local.model.b> list;
        int c2;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        return (!this.f1302e || !((list = this.b) == null || list.isEmpty()) || (c2 = c(str)) == 0) ? d(str) : c2;
    }

    public abstract int a(String str);

    public int a(String str, Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append(GrsApp.getInstance().getBrand("/"));
        sb.append(str);
        return b(sb.toString(), context) != 0 ? -1 : 0;
    }

    public String a(Context context, com.huawei.hms.framework.network.grs.e.a aVar, GrsBaseInfo grsBaseInfo, String str, String str2, boolean z) {
        Map<String, String> a = a(context, aVar, grsBaseInfo, str, z);
        if (a == null) {
            Logger.w("AbstractLocalManager", "addresses not found by routeby in local config{%s}", str);
            return null;
        }
        return a.get(str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:113:0x0075 A[Catch: JSONException -> 0x0091, LOOP:1: B:111:0x006f->B:113:0x0075, LOOP_END, TryCatch #0 {JSONException -> 0x0091, blocks: (B:94:0x0011, B:95:0x0019, B:97:0x001f, B:99:0x004a, B:105:0x0060, B:107:0x0067, B:111:0x006f, B:113:0x0075, B:114:0x0081, B:115:0x008a, B:101:0x0050, B:103:0x0056, B:104:0x005b), top: B:123:0x0011 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public List<com.huawei.hms.framework.network.grs.local.model.b> a(JSONArray jSONArray) {
        JSONArray jSONArray2;
        HashSet hashSet;
        int i2;
        if (jSONArray == null || jSONArray.length() == 0) {
            return new ArrayList();
        }
        try {
            ArrayList arrayList = new ArrayList(16);
            for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i3);
                com.huawei.hms.framework.network.grs.local.model.b bVar = new com.huawei.hms.framework.network.grs.local.model.b();
                bVar.b(jSONObject.getString("id"));
                bVar.c(jSONObject.getString("name"));
                bVar.a(jSONObject.getString("description"));
                JSONArray jSONArray3 = null;
                if (jSONObject.has("countriesOrAreas")) {
                    jSONArray2 = jSONObject.getJSONArray("countriesOrAreas");
                } else if (!jSONObject.has("countries")) {
                    Logger.w("AbstractLocalManager", "current country or area group has not config countries or areas.");
                    hashSet = new HashSet(16);
                    if (jSONArray3 != null && jSONArray3.length() != 0) {
                        for (i2 = 0; i2 < jSONArray3.length(); i2++) {
                            hashSet.add((String) jSONArray3.get(i2));
                        }
                        bVar.a(hashSet);
                        arrayList.add(bVar);
                    }
                    return new ArrayList();
                } else {
                    jSONArray2 = jSONObject.getJSONArray("countries");
                }
                jSONArray3 = jSONArray2;
                hashSet = new HashSet(16);
                if (jSONArray3 != null) {
                    while (i2 < jSONArray3.length()) {
                    }
                    bVar.a(hashSet);
                    arrayList.add(bVar);
                }
                return new ArrayList();
            }
            return arrayList;
        } catch (JSONException e2) {
            Logger.w("AbstractLocalManager", "parse countrygroup failed maybe json style is wrong. %s", StringUtils.anonymizeMessage(e2.getMessage()));
            return new ArrayList();
        }
    }

    public Map<String, String> a(Context context, com.huawei.hms.framework.network.grs.e.a aVar, GrsBaseInfo grsBaseInfo, String str, boolean z) {
        com.huawei.hms.framework.network.grs.local.model.a aVar2 = this.a;
        if (aVar2 == null) {
            Logger.w("AbstractLocalManager", "application data is null.");
            return null;
        }
        com.huawei.hms.framework.network.grs.local.model.c a = aVar2.a(str);
        if (a == null) {
            Logger.w("AbstractLocalManager", "service not found in local config{%s}", str);
            return null;
        }
        String b = e.b(context, aVar, a.b(), grsBaseInfo, z);
        if (b == null) {
            Logger.w("AbstractLocalManager", "country not found by routeby in local config{%s}", a.b());
            return null;
        }
        List<com.huawei.hms.framework.network.grs.local.model.b> a2 = a.a();
        com.huawei.hms.framework.network.grs.local.model.d a3 = a.a(((a2 == null || a2.size() == 0) ? this.f1301c : a(a2, grsBaseInfo, b)).get(b));
        if (a3 == null) {
            return null;
        }
        return a3.a();
    }

    public void a() {
        com.huawei.hms.framework.network.grs.local.model.a aVar = this.a;
        if (aVar != null) {
            aVar.a();
            this.f1303f = true;
        }
    }

    public void a(Context context, List<String> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        for (String str : list) {
            if (Pattern.matches("^grs_sdk_global_route_config_[a-zA-Z]+\\.json$", str)) {
                if (g(com.huawei.hms.framework.network.grs.h.c.a(GrsApp.getInstance().getBrand("/") + str, context)) == 0) {
                    Logger.i("AbstractLocalManager", "load SDK_CONFIG_FILE: %s, sucess.", str);
                } else {
                    Logger.i("AbstractLocalManager", "load SDK_CONFIG_FILE: %s, failure.", str);
                }
            }
        }
    }

    public void a(GrsBaseInfo grsBaseInfo) {
        this.f1301c.put("no_route_country", "no-country");
        List<com.huawei.hms.framework.network.grs.local.model.b> list = this.b;
        if (list == null || list.isEmpty()) {
            return;
        }
        for (com.huawei.hms.framework.network.grs.local.model.b bVar : this.b) {
            if (bVar.a().contains(grsBaseInfo.getIssueCountry())) {
                this.f1301c.put(grsBaseInfo.getIssueCountry(), bVar.b());
            }
            if (bVar.a().contains(grsBaseInfo.getRegCountry())) {
                this.f1301c.put(grsBaseInfo.getRegCountry(), bVar.b());
            }
            if (bVar.a().contains(grsBaseInfo.getSerCountry())) {
                this.f1301c.put(grsBaseInfo.getSerCountry(), bVar.b());
            }
        }
        this.b = null;
    }

    public abstract int b(String str);

    public com.huawei.hms.framework.network.grs.local.model.a b() {
        return this.a;
    }

    /* JADX WARN: Removed duplicated region for block: B:144:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x00e6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void b(JSONArray jSONArray) {
        String str;
        Iterator<String> keys;
        if (jSONArray == null || jSONArray.length() == 0) {
            return;
        }
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i2);
            com.huawei.hms.framework.network.grs.local.model.c cVar = new com.huawei.hms.framework.network.grs.local.model.c();
            String string = jSONObject.getString("name");
            cVar.b(string);
            if (!this.f1304g.contains(string)) {
                this.f1304g.add(string);
                if (this.f1302e) {
                    cVar.c(jSONObject.getString("routeBy"));
                    JSONArray jSONArray2 = jSONObject.getJSONArray("servings");
                    for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                        JSONObject jSONObject2 = (JSONObject) jSONArray2.get(i3);
                        com.huawei.hms.framework.network.grs.local.model.d dVar = new com.huawei.hms.framework.network.grs.local.model.d();
                        String str2 = "countryOrAreaGroup";
                        if (!jSONObject2.has("countryOrAreaGroup")) {
                            str2 = "countryGroup";
                            if (!jSONObject2.has("countryGroup")) {
                                Logger.v("AbstractLocalManager", "maybe this service{%s} routeBy is unconditional.", string);
                                str = "no-country";
                                dVar.a(str);
                                JSONObject jSONObject3 = jSONObject2.getJSONObject("addresses");
                                ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(16);
                                keys = jSONObject3.keys();
                                while (keys.hasNext()) {
                                    String next = keys.next();
                                    String string2 = jSONObject3.getString(next);
                                    if (!TextUtils.isEmpty(next) && !TextUtils.isEmpty(string2)) {
                                        concurrentHashMap.put(next, jSONObject3.getString(next));
                                    }
                                }
                                dVar.a(concurrentHashMap);
                                cVar.a(dVar.b(), dVar);
                            }
                        }
                        str = jSONObject2.getString(str2);
                        dVar.a(str);
                        JSONObject jSONObject32 = jSONObject2.getJSONObject("addresses");
                        ConcurrentHashMap concurrentHashMap2 = new ConcurrentHashMap(16);
                        keys = jSONObject32.keys();
                        while (keys.hasNext()) {
                        }
                        dVar.a(concurrentHashMap2);
                        cVar.a(dVar.b(), dVar);
                    }
                    List<com.huawei.hms.framework.network.grs.local.model.b> list = null;
                    String str3 = "countryOrAreaGroups";
                    if (!jSONObject.has("countryOrAreaGroups")) {
                        str3 = "countryGroups";
                        if (!jSONObject.has("countryGroups")) {
                            Logger.i("AbstractLocalManager", "service use default countryOrAreaGroup");
                            cVar.a(list);
                            if (this.a == null) {
                                this.a = new com.huawei.hms.framework.network.grs.local.model.a();
                            }
                            this.a.a(string, cVar);
                        }
                    }
                    list = a(jSONObject.getJSONArray(str3));
                    cVar.a(list);
                    if (this.a == null) {
                    }
                    this.a.a(string, cVar);
                }
            }
        }
    }

    public int c(String str) {
        this.b = new ArrayList(16);
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONArray jSONArray = null;
            if (jSONObject.has("countryOrAreaGroups")) {
                jSONArray = jSONObject.getJSONArray("countryOrAreaGroups");
            } else if (jSONObject.has("countryGroups")) {
                jSONArray = jSONObject.getJSONArray("countryGroups");
            } else {
                Logger.e("AbstractLocalManager", "maybe local config json is wrong because the default countryOrAreaGroups isn't config.");
            }
            if (jSONArray == null) {
                return -1;
            }
            this.b.addAll(a(jSONArray));
            return 0;
        } catch (JSONException e2) {
            Logger.w("AbstractLocalManager", "parse countrygroup failed maybe json style is wrong. %s", StringUtils.anonymizeMessage(e2.getMessage()));
            return -1;
        }
    }

    public Set<String> c() {
        return this.f1304g;
    }

    public int d(String str) {
        try {
            b(new JSONObject(str).getJSONArray(i2.d));
            return 0;
        } catch (JSONException e2) {
            Logger.w("AbstractLocalManager", "parse 2.0 services failed maybe because of json style.please check! %s", StringUtils.anonymizeMessage(e2.getMessage()));
            return -1;
        }
    }

    public boolean d() {
        return this.f1303f;
    }

    public abstract int e(String str);

    public boolean e() {
        return this.d;
    }
}
