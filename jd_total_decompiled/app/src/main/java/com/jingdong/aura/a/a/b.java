package com.jingdong.aura.a.a;

import android.content.Context;
import com.jingdong.aura.a.a.a;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.tencent.open.SocialConstants;
import com.vivo.push.PushClientConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class b {
    private static final com.jingdong.aura.core.util.l.b a = com.jingdong.aura.core.util.l.c.a("BundleParser");

    public static void a(Context context, boolean z) {
        List<a.C0391a> list;
        List<a.C0391a> list2 = null;
        try {
            list = a(context.getAssets().open("aura.json"));
        } catch (Exception e2) {
            e2.printStackTrace();
            list = null;
        }
        a.c().b(list);
        if (z) {
            a.c().a(list);
            return;
        }
        try {
            File file = new File(com.jingdong.aura.core.util.d.a().getAbsolutePath() + "/aura/updateAura.json");
            if (file.exists()) {
                list2 = a(new FileInputStream(file));
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        ArrayList arrayList = new ArrayList();
        if (list2 != null && list2.size() > 0) {
            if (list != null && list.size() > 0) {
                arrayList.addAll(a(list, list2));
            } else {
                arrayList.addAll(list2);
            }
        } else if (list != null && list.size() > 0) {
            arrayList.addAll(list);
        }
        if (arrayList.size() > 0) {
            a.c().a(arrayList);
        }
    }

    private static void b(List<a.C0391a.C0392a> list, JSONObject jSONObject, String str) {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return;
        }
        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
            try {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                list.add(new a.C0391a.C0392a(jSONObject2.optString("name"), jSONObject2.optString("path"), jSONObject2.optString(ApkDownloadTable.FIELD_SIZE), jSONObject2.optString("md5")));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        a.a("parseSoInfoList:" + list);
    }

    static List<a.C0391a> a(List<a.C0391a> list, List<a.C0391a> list2) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<a.C0391a> it = list.iterator();
        while (true) {
            boolean z = false;
            if (!it.hasNext()) {
                break;
            }
            a.C0391a next = it.next();
            for (a.C0391a c0391a : list2) {
                if (next.f12073f.equals(c0391a.f12073f)) {
                    if (next.f12077j >= c0391a.f12077j) {
                        arrayList.add(next);
                    } else {
                        arrayList.add(c0391a);
                    }
                    z = true;
                }
            }
            if (!z) {
                arrayList.add(next);
            }
        }
        arrayList2.addAll(arrayList);
        for (a.C0391a c0391a2 : list2) {
            Iterator it2 = arrayList.iterator();
            boolean z2 = false;
            while (it2.hasNext()) {
                if (c0391a2.f12073f.equals(((a.C0391a) it2.next()).f12073f)) {
                    z2 = true;
                }
            }
            if (!z2) {
                arrayList2.add(c0391a2);
            }
        }
        return arrayList2;
    }

    public static List<a.C0391a> a(InputStream inputStream) {
        byte[] bArr;
        if (inputStream != null) {
            try {
                bArr = new byte[inputStream.available()];
                inputStream.read(bArr);
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        } else {
            bArr = null;
        }
        return a(bArr);
    }

    private static List<a.C0391a> a(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArray = new JSONArray(new String(bArr));
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i2);
            a.C0391a c0391a = new a.C0391a();
            c0391a.f12073f = optJSONObject.optString(PushClientConstants.TAG_PKG_NAME);
            c0391a.f12076i = optJSONObject.optString("verName");
            c0391a.f12077j = optJSONObject.optLong("verCode");
            c0391a.f12074g = optJSONObject.optBoolean("hasSO");
            c0391a.f12078k = optJSONObject.optString("app", null);
            c0391a.f12079l = optJSONObject.optString("md5", null);
            c0391a.f12080m = optJSONObject.optString("md5_asec", null);
            c0391a.f12075h = optJSONObject.optLong(ApkDownloadTable.FIELD_SIZE);
            c0391a.f12081n = optJSONObject.optInt("bundleType");
            c0391a.o = optJSONObject.optString("downloadUrl");
            ArrayList arrayList2 = new ArrayList();
            c0391a.a = arrayList2;
            a(arrayList2, optJSONObject, "activity");
            a(c0391a.a, optJSONObject, SocialConstants.PARAM_RECEIVER);
            a(c0391a.a, optJSONObject, "service");
            a(c0391a.a, optJSONObject, "provider");
            ArrayList arrayList3 = new ArrayList();
            c0391a.f12071c = arrayList3;
            b(arrayList3, optJSONObject, "soInfo");
            ArrayList arrayList4 = new ArrayList();
            c0391a.s = arrayList4;
            a(arrayList4, optJSONObject, SocialConstants.PARAM_RECEIVER);
            ArrayList arrayList5 = new ArrayList();
            c0391a.p = arrayList5;
            a(arrayList5, optJSONObject, "activity");
            ArrayList arrayList6 = new ArrayList();
            c0391a.q = arrayList6;
            a(arrayList6, optJSONObject, "service");
            ArrayList arrayList7 = new ArrayList();
            c0391a.r = arrayList7;
            a(arrayList7, optJSONObject, "provider");
            ArrayList arrayList8 = new ArrayList();
            c0391a.b = arrayList8;
            a(arrayList8, optJSONObject, "manualComponents");
            ArrayList arrayList9 = new ArrayList();
            c0391a.d = arrayList9;
            a(arrayList9, optJSONObject, "dependency");
            ArrayList arrayList10 = new ArrayList();
            c0391a.f12072e = arrayList10;
            a(arrayList10, optJSONObject, "auraDependentSo");
            arrayList.add(c0391a);
        }
        return arrayList;
    }

    private static void a(List<String> list, JSONObject jSONObject, String str) {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return;
        }
        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
            try {
                list.add(optJSONArray.getString(i2));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }
}
