package com.jingdong.manto.r;

import android.text.TextUtils;
import android.util.SparseArray;
import com.jingdong.jdma.minterface.BaseEvent;
import com.jingdong.manto.network.common.IMantoHttpListener;
import com.jingdong.manto.network.common.MantoJDHttpHandler;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.sdk.api.IMantoReport;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoTrack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class f {
    protected static final SparseArray<a> a = new SparseArray<>();
    protected static final SparseArray<a> b = new SparseArray<>();

    /* renamed from: c */
    protected static final SparseArray<a> f14153c = new SparseArray<>();

    /* loaded from: classes16.dex */
    public static class a {
        public String a;
        public String b;

        /* renamed from: c */
        public List<Map> f14154c = new ArrayList();
        public int d;

        /* renamed from: e */
        public boolean f14155e;

        /* renamed from: com.jingdong.manto.r.f$a$a */
        /* loaded from: classes16.dex */
        public class C0668a implements IMantoReport.IMantoReportCallback {
            C0668a(a aVar) {
            }

            @Override // com.jingdong.manto.sdk.api.IMantoReport.IMantoReportCallback
            public void onError(JSONObject jSONObject, Throwable th) {
            }

            @Override // com.jingdong.manto.sdk.api.IMantoReport.IMantoReportCallback
            public void onSuccess(JSONObject jSONObject) {
            }
        }

        /* loaded from: classes16.dex */
        public class b extends IMantoHttpListener {
            b(a aVar) {
            }

            @Override // com.jingdong.manto.network.common.IMantoHttpListener
            public void onError(JSONObject jSONObject, Throwable th) {
                MantoLog.e("perfReport", th.getMessage());
            }

            @Override // com.jingdong.manto.network.common.IMantoHttpListener
            public void onSuccess(JSONObject jSONObject) {
                MantoLog.d("perfReport", Integer.valueOf(jSONObject.optInt("code", -1)));
            }
        }

        public a(String str, String str2, int i2, boolean z) {
            this.a = str;
            this.b = str2;
            this.d = i2;
            this.f14155e = z;
        }

        private void a(String str, String str2, JSONArray jSONArray) {
            com.jingdong.manto.network.mantorequests.c cVar = new com.jingdong.manto.network.mantorequests.c(str, str2, jSONArray, 1);
            IMantoReport iMantoReport = (IMantoReport) com.jingdong.a.n(IMantoReport.class);
            if (iMantoReport != null) {
                iMantoReport.reportData(cVar.getFunctionId(), cVar.getPostBody(), new C0668a(this));
            } else {
                MantoJDHttpHandler.commit(cVar, new b(this));
            }
        }

        void a() {
            this.f14154c.clear();
        }

        public void a(String str, long j2, boolean z) {
            HashMap hashMap = new HashMap();
            hashMap.put("type", str);
            hashMap.put("cost", Long.valueOf(j2));
            hashMap.put("time", Long.valueOf(System.currentTimeMillis()));
            this.f14154c.add(hashMap);
            a(z);
        }

        void a(boolean z) {
            if (this.f14154c.size() >= this.d || z) {
                try {
                    JSONArray jSONArray = new JSONArray();
                    Iterator<Map> it = this.f14154c.iterator();
                    while (it.hasNext()) {
                        jSONArray.put(new JSONObject(it.next()));
                    }
                    PkgDetailEntity k2 = com.jingdong.a.k(this.a, this.b);
                    if (k2 == null) {
                        return;
                    }
                    a(this.a, k2.versionName, jSONArray);
                    if (this.f14155e) {
                        this.f14154c.clear();
                    }
                } catch (Exception unused) {
                }
            }
        }
    }

    public static void a(com.jingdong.manto.f fVar, String str, long j2) {
        String str2 = fVar.f13017i;
        String str3 = fVar.r.f13082e;
        SparseArray<a> sparseArray = a;
        a aVar = sparseArray.get(str2.hashCode());
        if (aVar == null) {
            aVar = new a(str2, str3, 20, false);
            sparseArray.put(str2.hashCode(), aVar);
        }
        aVar.a(str, j2, TextUtils.equals("launchTime", str));
    }

    public static void a(PkgDetailEntity pkgDetailEntity, String str, String str2, String str3) {
        if (pkgDetailEntity == null) {
            return;
        }
        try {
            String str4 = pkgDetailEntity.appId;
            String str5 = pkgDetailEntity.type;
            String str6 = pkgDetailEntity.versionName;
            JSONObject jSONObject = new JSONObject();
            if (TextUtils.isEmpty(str5)) {
                str5 = "1";
            }
            jSONObject.put("vapp_type", str5);
            jSONObject.put("appId", str4);
            jSONObject.put("version", str6);
            if (TextUtils.isEmpty(str)) {
                str = "0";
            }
            jSONObject.put(BaseEvent.SCENE, str);
            MantoTrack.sendCommonDataWithExt(com.jingdong.a.g(), str2, str3, str4, "", "", jSONObject.toString(), "", null);
        } catch (JSONException unused) {
        }
    }

    public static void a(String str) {
        int hashCode = str.hashCode();
        SparseArray<a> sparseArray = a;
        a aVar = sparseArray.get(hashCode);
        SparseArray<a> sparseArray2 = f14153c;
        a aVar2 = sparseArray2.get(hashCode);
        SparseArray<a> sparseArray3 = b;
        a aVar3 = sparseArray3.get(hashCode);
        if (aVar != null) {
            sparseArray.remove(hashCode);
            aVar.a();
        }
        if (aVar2 != null) {
            sparseArray2.remove(hashCode);
            aVar2.a();
        }
        if (aVar3 != null) {
            sparseArray3.remove(hashCode);
            aVar3.a();
        }
    }

    public static void b(com.jingdong.manto.f fVar, String str, long j2) {
        String str2 = fVar.f13017i;
        String str3 = fVar.r.f13082e;
        SparseArray<a> sparseArray = f14153c;
        a aVar = sparseArray.get(str2.hashCode());
        if (aVar == null) {
            aVar = new a(str2, str3, 10, true);
            sparseArray.put(str2.hashCode(), aVar);
        }
        aVar.a(str, j2, false);
    }
}
