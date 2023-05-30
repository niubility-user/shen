package com.jingdong.manto.m.t0.c;

import android.location.LocationManager;
import android.os.Build;
import android.os.ParcelUuid;
import android.widget.Toast;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.manto.jsapi.bluetooth.sdk.util.BTHelper;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.m.t0.c.l;
import com.jingdong.manto.m.t0.d.a;
import com.jingdong.manto.m.t0.d.e.d;
import com.jingdong.manto.sdk.MantoSdkManager;
import com.jingdong.manto.sdk.api.IPermission;
import com.jingdong.manto.utils.MantoPermission;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.tencent.mapsdk.internal.i2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class o extends d0 {

    /* loaded from: classes15.dex */
    class a implements com.jingdong.manto.m.t0.d.d.d {
        final /* synthetic */ com.jingdong.manto.h a;
        final /* synthetic */ int b;

        a(com.jingdong.manto.h hVar, int i2) {
            this.a = hVar;
            this.b = i2;
        }

        @Override // com.jingdong.manto.m.t0.d.d.d
        public void a(com.jingdong.manto.m.t0.d.d.e eVar) {
            if (eVar.a != 0) {
                HashMap hashMap = new HashMap();
                hashMap.put("errCode", Integer.valueOf(eVar.a));
                hashMap.put("isDiscovering", Boolean.FALSE);
                this.a.a(this.b, o.this.putErrMsg(eVar.b, hashMap));
                return;
            }
            HashMap hashMap2 = new HashMap();
            hashMap2.put("errCode", 0);
            hashMap2.put("isDiscovering", Boolean.TRUE);
            this.a.a(this.b, o.this.putErrMsg(IMantoBaseModule.SUCCESS, hashMap2));
        }
    }

    /* loaded from: classes15.dex */
    class b implements com.jingdong.manto.m.t0.d.d.i {
        final /* synthetic */ com.jingdong.manto.h a;

        b(o oVar, com.jingdong.manto.h hVar) {
            this.a = hVar;
        }

        @Override // com.jingdong.manto.m.t0.d.d.i
        public void a(com.jingdong.manto.m.t0.d.d.h hVar) {
            e.a(this.a, hVar);
        }

        @Override // com.jingdong.manto.m.t0.d.d.i
        public void a(List<com.jingdong.manto.m.t0.d.d.h> list) {
            e.a(this.a, list);
        }
    }

    /* loaded from: classes15.dex */
    class c implements Runnable {
        final /* synthetic */ com.jingdong.manto.h a;
        final /* synthetic */ com.jingdong.manto.m.t0.b b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ com.jingdong.manto.m.t0.d.a f13609c;
        final /* synthetic */ com.jingdong.manto.m.t0.d.d.d d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ com.jingdong.manto.m.t0.d.d.i f13610e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ ArrayList f13611f;

        /* loaded from: classes15.dex */
        class a implements IPermission.PermissionCallBack {
            a() {
            }

            @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
            public void onDenied() {
                Toast.makeText(com.jingdong.manto.c.a(), "\u4f4d\u7f6e\u3001\u84dd\u7259\u6743\u9650\u5c1a\u672a\u5f00\u542f", 0).show();
                c cVar = c.this;
                cVar.b.a(cVar.f13609c, cVar.d, cVar.f13610e, cVar.f13611f);
                l.i.a(c.this.a, true, true);
            }

            @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
            public void onGranted() {
                c cVar = c.this;
                cVar.b.a(cVar.f13609c, cVar.d, cVar.f13610e, cVar.f13611f);
                l.i.a(c.this.a, true, true);
            }
        }

        c(o oVar, com.jingdong.manto.h hVar, com.jingdong.manto.m.t0.b bVar, com.jingdong.manto.m.t0.d.a aVar, com.jingdong.manto.m.t0.d.d.d dVar, com.jingdong.manto.m.t0.d.d.i iVar, ArrayList arrayList) {
            this.a = hVar;
            this.b = bVar;
            this.f13609c = aVar;
            this.d = dVar;
            this.f13610e = iVar;
            this.f13611f = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            MantoPermission.requestPermissions(this.a.p(), new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.BLUETOOTH_CONNECT", "android.permission.BLUETOOTH_SCAN", "android.permission.BLUETOOTH_ADVERTISE"}, new a());
        }
    }

    /* loaded from: classes15.dex */
    class d implements Runnable {
        final /* synthetic */ com.jingdong.manto.h a;
        final /* synthetic */ com.jingdong.manto.m.t0.b b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ com.jingdong.manto.m.t0.d.a f13612c;
        final /* synthetic */ com.jingdong.manto.m.t0.d.d.d d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ com.jingdong.manto.m.t0.d.d.i f13613e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ ArrayList f13614f;

        /* loaded from: classes15.dex */
        class a implements IPermission.PermissionCallBack {
            a() {
            }

            @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
            public void onDenied() {
                Toast.makeText(com.jingdong.manto.c.a(), "\u5b9a\u4f4d\u3001\u84dd\u7259\u6743\u9650\u5c1a\u672a\u5f00\u542f", 0).show();
                d dVar = d.this;
                dVar.b.a(dVar.f13612c, dVar.d, dVar.f13613e, dVar.f13614f);
                l.i.a(d.this.a, true, true);
            }

            @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
            public void onGranted() {
                d dVar = d.this;
                dVar.b.a(dVar.f13612c, dVar.d, dVar.f13613e, dVar.f13614f);
                l.i.a(d.this.a, true, true);
            }
        }

        d(o oVar, com.jingdong.manto.h hVar, com.jingdong.manto.m.t0.b bVar, com.jingdong.manto.m.t0.d.a aVar, com.jingdong.manto.m.t0.d.d.d dVar, com.jingdong.manto.m.t0.d.d.i iVar, ArrayList arrayList) {
            this.a = hVar;
            this.b = bVar;
            this.f13612c = aVar;
            this.d = dVar;
            this.f13613e = iVar;
            this.f13614f = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            MantoPermission.requestPermissions(this.a.p(), new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, new a());
        }
    }

    /* loaded from: classes15.dex */
    static class e extends com.jingdong.manto.m.d {
        private static JSONObject d = new JSONObject();

        /* renamed from: e  reason: collision with root package name */
        private static e f13615e = new e();

        e() {
        }

        public static synchronized void a(com.jingdong.manto.h hVar, com.jingdong.manto.m.t0.d.d.h hVar2) {
            synchronized (e.class) {
                synchronized (e.class) {
                    if (hVar != null) {
                        JSONArray jSONArray = new JSONArray();
                        if (hVar2 != null) {
                            try {
                                jSONArray.put(hVar2.a());
                            } catch (Throwable unused) {
                            }
                        }
                        try {
                            d.remove("devices");
                            d.put("devices", jSONArray);
                        } catch (Throwable unused2) {
                        }
                        if (hVar != null) {
                            f13615e.a(hVar).a(d.toString()).a();
                        }
                    }
                }
            }
        }

        public static synchronized void a(com.jingdong.manto.h hVar, List<com.jingdong.manto.m.t0.d.d.h> list) {
            synchronized (e.class) {
                synchronized (e.class) {
                    if (hVar != null) {
                        JSONArray jSONArray = new JSONArray();
                        for (com.jingdong.manto.m.t0.d.d.h hVar2 : list) {
                            if (hVar2 != null) {
                                try {
                                    jSONArray.put(hVar2.a());
                                } catch (Throwable unused) {
                                }
                            }
                        }
                        try {
                            d.remove("devices");
                            d.put("devices", jSONArray);
                        } catch (Throwable unused2) {
                        }
                        if (hVar != null) {
                            f13615e.a(hVar).a(d.toString()).a();
                        }
                    }
                }
            }
        }

        @Override // com.jingdong.manto.m.a
        public String getJsApiName() {
            return "onBluetoothDeviceFound";
        }
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        ArrayList<com.jingdong.manto.m.t0.d.e.d> arrayList;
        Runnable dVar;
        String putErrMsg;
        HashMap hashMap;
        String str2;
        HashMap hashMap2;
        String str3;
        super.exec(hVar, jSONObject, i2, str);
        if (jSONObject != null) {
            com.jingdong.manto.m.t0.b a2 = com.jingdong.manto.m.t0.a.a(hVar.a());
            if (a2 == null) {
                hashMap2 = new HashMap();
                hashMap2.put("errCode", 10000);
                str3 = "fail:not init";
            } else if (BTHelper.btEnabled()) {
                boolean optBoolean = jSONObject.optBoolean("allowDuplicatesKey");
                int optInt = jSONObject.optInt("interval");
                String optString = jSONObject.optString("powerLevel", "medium");
                if (jSONObject.has(i2.d)) {
                    ArrayList<com.jingdong.manto.m.t0.d.e.d> arrayList2 = new ArrayList<>();
                    try {
                        JSONArray jSONArray = new JSONArray(jSONObject.optString(i2.d));
                        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                            arrayList2.add(new d.b().a(ParcelUuid.fromString(jSONArray.getString(i3).toUpperCase())).a());
                        }
                        arrayList = arrayList2;
                    } catch (Throwable unused) {
                        hashMap = new HashMap();
                        hashMap.put("isDiscovering", Boolean.FALSE);
                        hashMap.put("errCode", 10004);
                        str2 = "fail:no service";
                    }
                } else {
                    arrayList = null;
                }
                a.C0622a c0622a = new a.C0622a();
                c0622a.b = optInt;
                c0622a.f13627c = optBoolean;
                c0622a.f13630g = optString;
                com.jingdong.manto.m.t0.d.a a3 = c0622a.a();
                a aVar = new a(hVar, i2);
                b bVar = new b(this, hVar);
                LocationManager locationManager = (LocationManager) com.jingdong.manto.c.a().getSystemService(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID);
                boolean isProviderEnabled = locationManager.isProviderEnabled("gps");
                boolean isProviderEnabled2 = locationManager.isProviderEnabled("network");
                if (isProviderEnabled || isProviderEnabled2) {
                    if (Build.VERSION.SDK_INT >= 31) {
                        IPermission iPermission = (IPermission) MantoSdkManager.instanceOf(IPermission.class);
                        if (iPermission != null && hVar.p() != null && !hVar.p().isFinishing() && !iPermission.hasPermissions(new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.BLUETOOTH_CONNECT", "android.permission.BLUETOOTH_SCAN", "android.permission.BLUETOOTH_ADVERTISE"})) {
                            dVar = new c(this, hVar, a2, a3, aVar, bVar, arrayList);
                            MantoThreadUtils.runOnUIThread(dVar);
                            return;
                        }
                        a2.a(a3, aVar, bVar, arrayList);
                        l.i.a(hVar, true, true);
                        return;
                    } else {
                        IPermission iPermission2 = (IPermission) MantoSdkManager.instanceOf(IPermission.class);
                        if (iPermission2 != null && hVar.p() != null && !hVar.p().isFinishing() && !iPermission2.hasPermissions(new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"})) {
                            dVar = new d(this, hVar, a2, a3, aVar, bVar, arrayList);
                            MantoThreadUtils.runOnUIThread(dVar);
                            return;
                        }
                        a2.a(a3, aVar, bVar, arrayList);
                        l.i.a(hVar, true, true);
                        return;
                    }
                }
                Toast.makeText(com.jingdong.manto.c.a(), "GPS\u5c1a\u672a\u6253\u5f00", 0).show();
                putErrMsg = putErrMsg("fail:gps was closed.", null);
                hVar.a(i2, putErrMsg);
            } else {
                hashMap2 = new HashMap();
                hashMap2.put("errCode", 10001);
                str3 = "fail:not available";
            }
            hVar.a(i2, putErrMsg(str3, hashMap2));
            return;
        }
        hashMap = new HashMap();
        hashMap.put("errCode", 10013);
        str2 = "fail:invalid data";
        putErrMsg = putErrMsg(str2, hashMap);
        hVar.a(i2, putErrMsg);
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "startBluetoothDevicesDiscovery";
    }
}
