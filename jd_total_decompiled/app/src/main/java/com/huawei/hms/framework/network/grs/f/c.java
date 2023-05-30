package com.huawei.hms.framework.network.grs.f;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.tencent.mapsdk.internal.i2;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class c extends a {
    public c(Context context, boolean z) {
        this.f1302e = z;
        if (a("grs_sdk_global_route_config.json", context) == 0) {
            this.d = true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:94:0x006e A[Catch: JSONException -> 0x0088, LOOP:1: B:92:0x0068->B:94:0x006e, LOOP_END, TryCatch #0 {JSONException -> 0x0088, blocks: (B:75:0x0007, B:76:0x0012, B:78:0x0018, B:80:0x0043, B:86:0x0059, B:88:0x0060, B:92:0x0068, B:94:0x006e, B:95:0x007a, B:96:0x0081, B:82:0x0049, B:84:0x004f, B:85:0x0054), top: B:102:0x0007 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private List<com.huawei.hms.framework.network.grs.local.model.b> a(JSONObject jSONObject) {
        JSONArray jSONArray;
        HashSet hashSet;
        int i2;
        try {
            ArrayList arrayList = new ArrayList(16);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                com.huawei.hms.framework.network.grs.local.model.b bVar = new com.huawei.hms.framework.network.grs.local.model.b();
                bVar.b(next);
                JSONObject jSONObject2 = jSONObject.getJSONObject(next);
                bVar.c(jSONObject2.getString("name"));
                bVar.a(jSONObject2.getString("description"));
                JSONArray jSONArray2 = null;
                if (jSONObject2.has("countriesOrAreas")) {
                    jSONArray = jSONObject2.getJSONArray("countriesOrAreas");
                } else if (!jSONObject2.has("countries")) {
                    Logger.w("LocalManagerV1", "current country or area group has not config countries or areas.");
                    hashSet = new HashSet(16);
                    if (jSONArray2 != null && jSONArray2.length() != 0) {
                        for (i2 = 0; i2 < jSONArray2.length(); i2++) {
                            hashSet.add((String) jSONArray2.get(i2));
                        }
                        bVar.a(hashSet);
                        arrayList.add(bVar);
                    }
                    return new ArrayList();
                } else {
                    jSONArray = jSONObject2.getJSONArray("countries");
                }
                jSONArray2 = jSONArray;
                hashSet = new HashSet(16);
                if (jSONArray2 != null) {
                    while (i2 < jSONArray2.length()) {
                    }
                    bVar.a(hashSet);
                    arrayList.add(bVar);
                }
                return new ArrayList();
            }
            return arrayList;
        } catch (JSONException e2) {
            Logger.w("LocalManagerV1", "parse countryGroups failed maybe json style is wrong. %s", StringUtils.anonymizeMessage(e2.getMessage()));
            return new ArrayList();
        }
    }

    @Override // com.huawei.hms.framework.network.grs.f.a
    public int a(String str) {
        this.a = new com.huawei.hms.framework.network.grs.local.model.a();
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject("application");
            String string = jSONObject.getString("name");
            long j2 = jSONObject.getLong("cacheControl");
            JSONArray jSONArray = jSONObject.getJSONArray(i2.d);
            this.a.b(string);
            this.a.a(j2);
            if (jSONArray != null) {
                if (jSONArray.length() != 0) {
                    return 0;
                }
            }
            return -1;
        } catch (JSONException e2) {
            Logger.w("LocalManagerV1", "parse appbean failed maybe json style is wrong. %s", StringUtils.anonymizeMessage(e2.getMessage()));
            return -1;
        }
    }

    public List<com.huawei.hms.framework.network.grs.local.model.b> a(JSONArray jSONArray, JSONObject jSONObject) {
        return (jSONObject == null || jSONObject.length() == 0) ? new ArrayList() : a(jSONObject);
    }

    @Override // com.huawei.hms.framework.network.grs.f.a
    public int b(String str) {
        this.b = new ArrayList(16);
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = null;
            if (jSONObject.has("countryOrAreaGroups")) {
                jSONObject2 = jSONObject.getJSONObject("countryOrAreaGroups");
            } else if (jSONObject.has("countryGroups")) {
                jSONObject2 = jSONObject.getJSONObject("countryGroups");
            } else {
                Logger.e("LocalManagerV1", "maybe local config json is wrong because the default countryOrAreaGroups isn't config.");
            }
            if (jSONObject2 == null) {
                return -1;
            }
            if (jSONObject2.length() != 0) {
                this.b.addAll(a(jSONObject2));
            }
            return 0;
        } catch (JSONException e2) {
            Logger.w("LocalManagerV1", "parse countrygroup failed maybe json style is wrong. %s", StringUtils.anonymizeMessage(e2.getMessage()));
            return -1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:159:0x00a2 A[Catch: JSONException -> 0x0120, TryCatch #0 {JSONException -> 0x0120, blocks: (B:137:0x000c, B:138:0x001d, B:140:0x0023, B:142:0x0039, B:144:0x0042, B:145:0x0056, B:147:0x005c, B:149:0x006d, B:156:0x0086, B:157:0x009c, B:159:0x00a2, B:161:0x00b6, B:163:0x00bc, B:165:0x00cd, B:150:0x0072, B:152:0x0078, B:154:0x007f, B:166:0x00e1, B:168:0x00ec, B:172:0x00fb, B:174:0x0105, B:176:0x010c, B:177:0x0113, B:169:0x00f1, B:171:0x00f7, B:173:0x0100), top: B:184:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:176:0x010c A[Catch: JSONException -> 0x0120, TryCatch #0 {JSONException -> 0x0120, blocks: (B:137:0x000c, B:138:0x001d, B:140:0x0023, B:142:0x0039, B:144:0x0042, B:145:0x0056, B:147:0x005c, B:149:0x006d, B:156:0x0086, B:157:0x009c, B:159:0x00a2, B:161:0x00b6, B:163:0x00bc, B:165:0x00cd, B:150:0x0072, B:152:0x0078, B:154:0x007f, B:166:0x00e1, B:168:0x00ec, B:172:0x00fb, B:174:0x0105, B:176:0x010c, B:177:0x0113, B:169:0x00f1, B:171:0x00f7, B:173:0x0100), top: B:184:0x000c }] */
    @Override // com.huawei.hms.framework.network.grs.f.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int e(String str) {
        JSONObject jSONObject;
        String str2;
        String string;
        Iterator<String> keys;
        String str3 = "countryGroup";
        String str4 = "countryOrAreaGroup";
        try {
            JSONObject jSONObject2 = new JSONObject(str).getJSONObject(i2.d);
            Iterator<String> keys2 = jSONObject2.keys();
            while (keys2.hasNext()) {
                String next = keys2.next();
                com.huawei.hms.framework.network.grs.local.model.c cVar = new com.huawei.hms.framework.network.grs.local.model.c();
                cVar.b(next);
                if (!this.f1304g.contains(next)) {
                    this.f1304g.add(next);
                    if (this.f1302e) {
                        JSONObject jSONObject3 = jSONObject2.getJSONObject(next);
                        cVar.c(jSONObject3.getString("routeBy"));
                        JSONArray jSONArray = jSONObject3.getJSONArray("servings");
                        int i2 = 0;
                        while (i2 < jSONArray.length()) {
                            JSONObject jSONObject4 = (JSONObject) jSONArray.get(i2);
                            com.huawei.hms.framework.network.grs.local.model.d dVar = new com.huawei.hms.framework.network.grs.local.model.d();
                            if (jSONObject4.has(str4)) {
                                string = jSONObject4.getString(str4);
                            } else if (jSONObject4.has(str3)) {
                                string = jSONObject4.getString(str3);
                            } else {
                                Logger.v("LocalManagerV1", "maybe this service routeBy is unconditional.");
                                str2 = "no-country";
                                dVar.a(str2);
                                JSONObject jSONObject5 = jSONObject4.getJSONObject("addresses");
                                String str5 = str3;
                                ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(16);
                                keys = jSONObject5.keys();
                                while (keys.hasNext()) {
                                    Iterator<String> it = keys;
                                    String next2 = keys.next();
                                    String string2 = jSONObject5.getString(next2);
                                    if (TextUtils.isEmpty(next2) || TextUtils.isEmpty(string2)) {
                                        keys = it;
                                    } else {
                                        concurrentHashMap.put(next2, jSONObject5.getString(next2));
                                        keys = it;
                                        str4 = str4;
                                    }
                                }
                                dVar.a(concurrentHashMap);
                                cVar.a(dVar.b(), dVar);
                                i2++;
                                str3 = str5;
                                str4 = str4;
                            }
                            str2 = string;
                            dVar.a(str2);
                            JSONObject jSONObject52 = jSONObject4.getJSONObject("addresses");
                            String str52 = str3;
                            ConcurrentHashMap concurrentHashMap2 = new ConcurrentHashMap(16);
                            keys = jSONObject52.keys();
                            while (keys.hasNext()) {
                            }
                            dVar.a(concurrentHashMap2);
                            cVar.a(dVar.b(), dVar);
                            i2++;
                            str3 = str52;
                            str4 = str4;
                        }
                        String str6 = str3;
                        String str7 = str4;
                        List<com.huawei.hms.framework.network.grs.local.model.b> list = null;
                        if (jSONObject3.has("countryOrAreaGroups")) {
                            jSONObject = jSONObject3.getJSONObject("countryOrAreaGroups");
                        } else if (jSONObject3.has("countryGroups")) {
                            jSONObject = jSONObject3.getJSONObject("countryGroups");
                        } else {
                            Logger.v("LocalManagerV1", "service use default countryOrAreaGroup");
                            cVar.a(list);
                            if (this.a == null) {
                                this.a = new com.huawei.hms.framework.network.grs.local.model.a();
                            }
                            this.a.a(next, cVar);
                            str3 = str6;
                            str4 = str7;
                        }
                        list = a((JSONArray) null, jSONObject);
                        cVar.a(list);
                        if (this.a == null) {
                        }
                        this.a.a(next, cVar);
                        str3 = str6;
                        str4 = str7;
                    }
                }
            }
            return 0;
        } catch (JSONException e2) {
            Logger.w("LocalManagerV1", "parse 1.0 services failed maybe because of json style.please check! %s", StringUtils.anonymizeMessage(e2.getMessage()));
            return -1;
        }
    }
}
