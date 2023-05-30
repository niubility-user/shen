package com.jingdong.manto.m.t0.c;

import android.os.Build;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import com.jingdong.manto.jsapi.bluetooth.sdk.util.BTHelper;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.sdk.MantoSdkManager;
import com.jingdong.manto.sdk.api.IPermission;
import com.jingdong.manto.utils.MantoPermission;
import com.jingdong.manto.utils.MantoThreadUtils;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class c extends d0 {

    /* loaded from: classes15.dex */
    class a implements Runnable {
        final /* synthetic */ com.jingdong.manto.h a;
        final /* synthetic */ com.jingdong.manto.m.t0.b b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f13590c;
        final /* synthetic */ com.jingdong.manto.m.t0.d.c.g.b d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ int f13591e;

        /* renamed from: com.jingdong.manto.m.t0.c.c$a$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class C0619a implements IPermission.PermissionCallBack {

            /* renamed from: com.jingdong.manto.m.t0.c.c$a$a$a  reason: collision with other inner class name */
            /* loaded from: classes15.dex */
            class C0620a implements com.jingdong.manto.m.t0.d.d.d {
                C0620a() {
                }

                @Override // com.jingdong.manto.m.t0.d.d.d
                public final void a(com.jingdong.manto.m.t0.d.d.e eVar) {
                    if (eVar.a != 0) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("errCode", Integer.valueOf(eVar.a));
                        a aVar = a.this;
                        aVar.a.a(aVar.f13591e, c.this.putErrMsg(eVar.b, hashMap));
                        return;
                    }
                    HashMap hashMap2 = new HashMap();
                    hashMap2.put("errCode", 0);
                    a aVar2 = a.this;
                    aVar2.a.a(aVar2.f13591e, c.this.putErrMsg(IMantoBaseModule.SUCCESS, hashMap2));
                }
            }

            C0619a() {
            }

            @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
            public void onDenied() {
                Toast.makeText(com.jingdong.manto.c.a(), "\u84dd\u7259\u6743\u9650\u5c1a\u672a\u5f00\u542f", 0).show();
                HashMap hashMap = new HashMap();
                com.jingdong.manto.m.t0.d.d.e eVar = com.jingdong.manto.m.t0.d.d.e.w;
                hashMap.put("errCode", Integer.valueOf(eVar.a));
                a aVar = a.this;
                aVar.a.a(aVar.f13591e, c.this.putErrMsg(eVar.b, hashMap));
            }

            @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
            public void onGranted() {
                a aVar = a.this;
                aVar.b.a(aVar.f13590c, aVar.d, new C0620a());
            }
        }

        a(com.jingdong.manto.h hVar, com.jingdong.manto.m.t0.b bVar, String str, com.jingdong.manto.m.t0.d.c.g.b bVar2, String str2, int i2) {
            this.a = hVar;
            this.b = bVar;
            this.f13590c = str;
            this.d = bVar2;
            this.f13591e = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            MantoPermission.requestPermission(this.a.p(), "android.permission.BLUETOOTH_CONNECT", new C0619a());
        }
    }

    /* loaded from: classes15.dex */
    class b implements com.jingdong.manto.m.t0.d.d.d {
        final /* synthetic */ com.jingdong.manto.h a;
        final /* synthetic */ int b;

        b(String str, com.jingdong.manto.h hVar, int i2) {
            this.a = hVar;
            this.b = i2;
        }

        @Override // com.jingdong.manto.m.t0.d.d.d
        public final void a(com.jingdong.manto.m.t0.d.d.e eVar) {
            if (eVar.a != 0) {
                HashMap hashMap = new HashMap();
                hashMap.put("errCode", Integer.valueOf(eVar.a));
                this.a.a(this.b, c.this.putErrMsg(eVar.b, hashMap));
                return;
            }
            HashMap hashMap2 = new HashMap();
            hashMap2.put("errCode", 0);
            this.a.a(this.b, c.this.putErrMsg(IMantoBaseModule.SUCCESS, hashMap2));
        }
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        IPermission iPermission;
        HashMap hashMap;
        String str2;
        super.exec(hVar, jSONObject, i2, str);
        if (jSONObject == null) {
            HashMap hashMap2 = new HashMap();
            hashMap2.put("errCode", 10013);
            hVar.a(i2, putErrMsg("fail:invalid data", hashMap2));
            return;
        }
        String a2 = hVar.a();
        com.jingdong.manto.m.t0.b a3 = com.jingdong.manto.m.t0.a.a(a2);
        if (a3 == null) {
            hashMap = new HashMap();
            hashMap.put("errCode", 10000);
            str2 = "fail:not init";
        } else if (BTHelper.btEnabled()) {
            boolean optBoolean = jSONObject.optBoolean("debug", false);
            boolean optBoolean2 = jSONObject.optBoolean("mainThread", true);
            boolean optBoolean3 = jSONObject.optBoolean("serial", true);
            long optLong = jSONObject.optLong("timeout", 20000L);
            boolean optBoolean4 = jSONObject.optBoolean("autoConnect", false);
            String optString = jSONObject.optString(NotificationCompat.CATEGORY_TRANSPORT, "LE");
            long optLong2 = jSONObject.optLong("discoverDelay", 0L);
            String optString2 = jSONObject.optString("deviceId");
            com.jingdong.manto.m.t0.d.c.g.b bVar = new com.jingdong.manto.m.t0.d.c.g.b(optString2);
            bVar.a = optBoolean;
            bVar.d = optBoolean2;
            bVar.f13655e = optBoolean3;
            bVar.f13660j = optLong;
            bVar.o = optBoolean4;
            bVar.p = optString;
            bVar.q = optLong2;
            if (Build.VERSION.SDK_INT < 31 || (iPermission = (IPermission) MantoSdkManager.instanceOf(IPermission.class)) == null || hVar.p() == null || hVar.p().isFinishing() || iPermission.hasPermission("android.permission.BLUETOOTH_CONNECT")) {
                a3.a(optString2, bVar, new b(a2, hVar, i2));
                return;
            } else {
                MantoThreadUtils.runOnUIThread(new a(hVar, a3, optString2, bVar, a2, i2));
                return;
            }
        } else {
            hashMap = new HashMap();
            hashMap.put("errCode", 10001);
            str2 = "fail:not available";
        }
        hVar.a(i2, putErrMsg(str2, hashMap));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "createBLEConnection";
    }
}
