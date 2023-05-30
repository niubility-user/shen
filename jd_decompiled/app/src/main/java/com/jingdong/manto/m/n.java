package com.jingdong.manto.m;

import android.app.Activity;
import android.content.Intent;
import com.jingdong.manto.MantoActivityResult;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.ui.MantoSettingActivity;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoThreadUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class n extends d0 {
    private static final int a = n.class.hashCode() & 65535;

    /* loaded from: classes15.dex */
    class a implements MantoActivityResult.ResultCallback {
        final /* synthetic */ com.jingdong.manto.h a;
        final /* synthetic */ int b;

        /* renamed from: c */
        final /* synthetic */ MantoActivityResult f13453c;

        a(com.jingdong.manto.h hVar, int i2, MantoActivityResult mantoActivityResult) {
            n.this = r1;
            this.a = hVar;
            this.b = i2;
            this.f13453c = mantoActivityResult;
        }

        @Override // com.jingdong.manto.MantoActivityResult.ResultCallback
        public void onActivityResult(int i2, int i3, Intent intent) {
            JSONArray jSONArray;
            if (i2 == n.a) {
                try {
                    jSONArray = new JSONArray(intent != null ? intent.getStringExtra("key_app_authorize_state") : "");
                } catch (JSONException unused) {
                    jSONArray = new JSONArray();
                }
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("errMsg", n.this.getJsApiName() + ":ok");
                    jSONObject.put("authSetting", jSONArray);
                } catch (Throwable unused2) {
                    MantoLog.e("JsApiOpenSetting", "set json error!");
                }
                this.a.a(this.b, jSONObject.toString());
            }
            this.f13453c.setResultCallback(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class b implements Runnable {
        final /* synthetic */ com.jingdong.manto.h a;
        final /* synthetic */ MantoCore b;

        /* loaded from: classes15.dex */
        class a implements Runnable {
            final /* synthetic */ PkgDetailEntity a;

            a(PkgDetailEntity pkgDetailEntity) {
                b.this = r1;
                this.a = pkgDetailEntity;
            }

            @Override // java.lang.Runnable
            public void run() {
                Activity activity = b.this.b.getActivity();
                PkgDetailEntity pkgDetailEntity = this.a;
                MantoSettingActivity.a(activity, pkgDetailEntity.appId, pkgDetailEntity.name, pkgDetailEntity.versionName, n.a);
            }
        }

        b(n nVar, com.jingdong.manto.h hVar, MantoCore mantoCore) {
            this.a = hVar;
            this.b = mantoCore;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.manto.i.c cVar;
            com.jingdong.manto.f h2 = this.a.h();
            PkgDetailEntity c2 = com.jingdong.manto.b.k().c(this.a.a(), (h2 == null || (cVar = h2.r) == null) ? "1" : cVar.f13082e);
            if (c2 == null) {
                return;
            }
            MantoThreadUtils.runOnUIThread(new a(c2));
        }
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        super.exec(hVar, jSONObject, i2, str);
        if (hVar == null || hVar.h() == null) {
            hVar.a(i2, putErrMsg("fail", null, str));
        } else if (hVar.h().s == null) {
            MantoLog.e("JsApiOpenSetting", "config is null!");
            hVar.a(i2, putErrMsg("fail", null, str));
        } else {
            MantoCore core = getCore(hVar);
            if (core == null) {
                hVar.a(i2, putErrMsg("fail", null, str));
                return;
            }
            MantoActivityResult activityResultImpl = core.getActivityResultImpl();
            if (activityResultImpl == null) {
                hVar.a(i2, putErrMsg("fail", null, str));
                MantoLog.e("JsApiOpenSetting", "Activity is null, invoke fail!");
                return;
            }
            activityResultImpl.setResultCallback(new a(hVar, i2, activityResultImpl));
            com.jingdong.manto.b.d().diskIO().execute(new b(this, hVar, core));
        }
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "openSetting";
    }
}
