package com.jingdong.manto.m.t0.c;

import android.os.Build;
import android.widget.Toast;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.m.t0.b;
import com.jingdong.manto.sdk.MantoSdkManager;
import com.jingdong.manto.sdk.api.IPermission;
import com.jingdong.manto.utils.MantoPermission;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.unionpay.tsmservice.mi.data.Constant;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class l extends d0 {

    /* loaded from: classes15.dex */
    class a implements Runnable {
        final /* synthetic */ com.jingdong.manto.h a;
        final /* synthetic */ String[] b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f13597c;
        final /* synthetic */ int d;

        /* renamed from: com.jingdong.manto.m.t0.c.l$a$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class C0621a implements IPermission.PermissionCallBack {
            C0621a() {
            }

            @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
            public void onDenied() {
                Toast.makeText(com.jingdong.manto.c.a(), "\u4f4d\u7f6e\u3001\u84dd\u7259\u6743\u9650\u5c1a\u672a\u5f00\u542f,\u8bf7\u5f00\u542f\u540e\u91cd\u8bd5", 0).show();
                HashMap hashMap = new HashMap();
                com.jingdong.manto.m.t0.d.d.e eVar = com.jingdong.manto.m.t0.d.d.e.w;
                hashMap.put("errCode", Integer.valueOf(eVar.a));
                a aVar = a.this;
                aVar.a.a(aVar.d, l.this.putErrMsg(eVar.b, hashMap));
            }

            @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
            public void onGranted() {
                a aVar = a.this;
                l.this.a(aVar.f13597c, aVar.a, aVar.d);
            }
        }

        a(com.jingdong.manto.h hVar, String[] strArr, String str, int i2) {
            this.a = hVar;
            this.b = strArr;
            this.f13597c = str;
            this.d = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            MantoPermission.requestPermissions(this.a.p(), this.b, new C0621a());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class b implements Runnable {
        final /* synthetic */ com.jingdong.manto.h a;
        final /* synthetic */ String[] b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f13599c;
        final /* synthetic */ int d;

        /* loaded from: classes15.dex */
        class a implements IPermission.PermissionCallBack {
            a() {
            }

            @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
            public void onDenied() {
                Toast.makeText(com.jingdong.manto.c.a(), "\u84dd\u7259\u6743\u9650\u5c1a\u672a\u5f00\u542f,\u8bf7\u5f00\u542f\u540e\u91cd\u8bd5", 0).show();
                HashMap hashMap = new HashMap();
                com.jingdong.manto.m.t0.d.d.e eVar = com.jingdong.manto.m.t0.d.d.e.w;
                hashMap.put("errCode", Integer.valueOf(eVar.a));
                b bVar = b.this;
                bVar.a.a(bVar.d, l.this.putErrMsg(eVar.b, hashMap));
            }

            @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
            public void onGranted() {
                b bVar = b.this;
                l.this.b(bVar.f13599c, bVar.a, bVar.d);
            }
        }

        b(com.jingdong.manto.h hVar, String[] strArr, String str, int i2) {
            this.a = hVar;
            this.b = strArr;
            this.f13599c = str;
            this.d = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            MantoPermission.requestPermissions(this.a.p(), this.b, new a());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class c implements Runnable {
        final /* synthetic */ com.jingdong.manto.h a;
        final /* synthetic */ String[] b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f13601c;
        final /* synthetic */ int d;

        /* loaded from: classes15.dex */
        class a implements IPermission.PermissionCallBack {
            a() {
            }

            @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
            public void onDenied() {
                Toast.makeText(com.jingdong.manto.c.a(), "\u84dd\u7259\u6743\u9650\u5c1a\u672a\u5f00\u542f,\u8bf7\u5f00\u542f\u540e\u91cd\u8bd5", 0).show();
                HashMap hashMap = new HashMap();
                com.jingdong.manto.m.t0.d.d.e eVar = com.jingdong.manto.m.t0.d.d.e.w;
                hashMap.put("errCode", Integer.valueOf(eVar.a));
                c cVar = c.this;
                cVar.a.a(cVar.d, l.this.putErrMsg(eVar.b, hashMap));
            }

            @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
            public void onGranted() {
                c cVar = c.this;
                l.this.c(cVar.f13601c, cVar.a, cVar.d);
            }
        }

        c(com.jingdong.manto.h hVar, String[] strArr, String str, int i2) {
            this.a = hVar;
            this.b = strArr;
            this.f13601c = str;
            this.d = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            MantoPermission.requestPermissions(this.a.p(), this.b, new a());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class d implements b.d {
        final /* synthetic */ com.jingdong.manto.h a;

        d(l lVar, com.jingdong.manto.h hVar) {
            this.a = hVar;
        }

        @Override // com.jingdong.manto.m.t0.b.d
        public void a(boolean z) {
            i.a(this.a, z, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class e implements com.jingdong.manto.m.t0.d.d.a {
        final /* synthetic */ com.jingdong.manto.h a;

        e(l lVar, com.jingdong.manto.h hVar) {
            this.a = hVar;
        }

        @Override // com.jingdong.manto.m.t0.d.d.a
        public void a(String str, boolean z) {
            h.a(this.a, str, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class f implements com.jingdong.manto.m.t0.d.d.g {
        final /* synthetic */ com.jingdong.manto.h a;

        f(l lVar, com.jingdong.manto.h hVar) {
            this.a = hVar;
        }

        @Override // com.jingdong.manto.m.t0.d.d.g
        public void a(String str, String str2, String str3, String str4) {
            g.a(this.a, str, str2, str3, str4);
        }

        @Override // com.jingdong.manto.m.t0.d.d.g
        public void b(String str, String str2, String str3, String str4) {
            g.a(this.a, str, str2, str3, str4);
        }
    }

    /* loaded from: classes15.dex */
    static class g extends com.jingdong.manto.m.d {
        private static g d = new g();

        /* renamed from: e  reason: collision with root package name */
        private static JSONObject f13603e = new JSONObject();

        private g() {
        }

        public static synchronized void a(com.jingdong.manto.h hVar, String str, String str2, String str3, String str4) {
            synchronized (g.class) {
                synchronized (g.class) {
                    if (hVar != null) {
                        try {
                            f13603e.remove("value");
                            f13603e.put("value", str4);
                            f13603e.remove("deviceId");
                            f13603e.put("deviceId", str);
                            f13603e.remove("serviceId");
                            f13603e.put("serviceId", str2);
                            f13603e.remove("characteristicId");
                            f13603e.put("characteristicId", str3);
                        } catch (Throwable unused) {
                        }
                        if (hVar != null) {
                            d.a(hVar).a(f13603e.toString()).a();
                        }
                    }
                }
            }
        }

        @Override // com.jingdong.manto.m.a
        public String getJsApiName() {
            return "onBLECharacteristicValueChange";
        }
    }

    /* loaded from: classes15.dex */
    static class h extends com.jingdong.manto.m.d {
        private static JSONObject d = new JSONObject();

        /* renamed from: e  reason: collision with root package name */
        private static h f13604e = new h();

        private h() {
        }

        public static synchronized void a(com.jingdong.manto.h hVar, String str, boolean z) {
            synchronized (h.class) {
                synchronized (h.class) {
                    if (hVar != null) {
                        try {
                            d.remove("deviceId");
                            d.put("deviceId", str);
                            d.remove("connected");
                            d.put("connected", z);
                        } catch (Throwable unused) {
                        }
                        if (hVar != null) {
                            f13604e.a(hVar).a(d.toString()).a();
                        }
                    }
                }
            }
        }

        @Override // com.jingdong.manto.m.a
        public String getJsApiName() {
            return "onBLEConnectionStateChanged";
        }
    }

    /* loaded from: classes15.dex */
    public static class i extends com.jingdong.manto.m.d {
        private static JSONObject d = new JSONObject();

        /* renamed from: e  reason: collision with root package name */
        private static i f13605e = new i();

        public static synchronized void a(com.jingdong.manto.h hVar, boolean z, boolean z2) {
            synchronized (i.class) {
                synchronized (i.class) {
                    if (hVar != null) {
                        try {
                            d.remove(Constant.KEY_PROMOTION_AVAILABLE);
                            d.put(Constant.KEY_PROMOTION_AVAILABLE, z);
                            d.remove("discovering");
                            d.put("discovering", z2);
                        } catch (Throwable unused) {
                        }
                        if (hVar != null) {
                            f13605e.a(hVar).a(d.toString()).a();
                        }
                    }
                }
            }
        }

        @Override // com.jingdong.manto.m.a
        public String getJsApiName() {
            return "onBluetoothAdapterStateChange";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, com.jingdong.manto.h hVar, int i2) {
        if (Build.VERSION.SDK_INT >= 31) {
            IPermission iPermission = (IPermission) MantoSdkManager.instanceOf(IPermission.class);
            String[] strArr = {"android.permission.BLUETOOTH_CONNECT", "android.permission.BLUETOOTH_ADVERTISE"};
            if (iPermission != null && !iPermission.hasPermissions(strArr)) {
                MantoThreadUtils.runOnUIThread(new b(hVar, strArr, str, i2));
                return;
            }
        }
        b(str, hVar, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, com.jingdong.manto.h hVar, int i2) {
        if (Build.VERSION.SDK_INT >= 31) {
            IPermission iPermission = (IPermission) MantoSdkManager.instanceOf(IPermission.class);
            String[] strArr = {"android.permission.BLUETOOTH_ADVERTISE"};
            if (iPermission != null && !iPermission.hasPermissions(strArr)) {
                MantoThreadUtils.runOnUIThread(new c(hVar, strArr, str, i2));
                return;
            }
        }
        c(str, hVar, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, com.jingdong.manto.h hVar, int i2) {
        String putErrMsg;
        String str2;
        com.jingdong.manto.m.t0.d.d.e a2 = com.jingdong.manto.m.t0.a.a(str, new d(this, hVar), new e(this, hVar), new f(this, hVar));
        HashMap hashMap = new HashMap();
        int i3 = a2.a;
        if (i3 != 0) {
            if (i3 == 10001) {
                hashMap.put("errCode", 10001);
                str2 = "fail:not available";
            } else if (i3 != 10009) {
                hashMap.put("errCode", Integer.valueOf(i3));
                str2 = a2.b;
            } else {
                hashMap.put("errCode", 10009);
                str2 = "fail:system not support";
            }
            putErrMsg = putErrMsg(str2, hashMap);
        } else {
            putErrMsg = putErrMsg(IMantoBaseModule.SUCCESS);
        }
        hVar.a(i2, putErrMsg);
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        super.exec(hVar, jSONObject, i2, str);
        String a2 = hVar.a();
        if (Build.VERSION.SDK_INT >= 31) {
            IPermission iPermission = (IPermission) MantoSdkManager.instanceOf(IPermission.class);
            String[] strArr = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.BLUETOOTH_SCAN", "android.permission.BLUETOOTH_CONNECT", "android.permission.BLUETOOTH_ADVERTISE"};
            if (iPermission != null && !iPermission.hasPermissions(strArr)) {
                MantoThreadUtils.runOnUIThread(new a(hVar, strArr, a2, i2));
                return;
            }
        }
        a(a2, hVar, i2);
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "openBluetoothAdapter";
    }
}
