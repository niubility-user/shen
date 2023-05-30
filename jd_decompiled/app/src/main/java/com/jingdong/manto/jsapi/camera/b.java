package com.jingdong.manto.jsapi.camera;

import android.content.Context;
import android.view.View;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.manto.AppLifeCycle;
import com.jingdong.manto.jsapi.coverview.CoverViewContainer;
import com.jingdong.manto.q.n;
import com.jingdong.manto.sdk.api.IPermission;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoPermission;
import com.jingdong.manto.utils.MantoThreadUtils;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class b extends com.jingdong.manto.jsapi.base.a {

    /* loaded from: classes15.dex */
    class a extends AppLifeCycle.Listener {
        final /* synthetic */ n a;
        final /* synthetic */ MantoCameraViewContainer b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f13142c;

        a(b bVar, n nVar, MantoCameraViewContainer mantoCameraViewContainer, int i2) {
            this.a = nVar;
            this.b = mantoCameraViewContainer;
            this.f13142c = i2;
        }

        @Override // com.jingdong.manto.AppLifeCycle.Listener
        public void onAppDestroy() {
            super.onAppDestroy();
            this.a.b((n.c0) this.b);
            this.a.b((n.f0) this.b);
            this.a.b((n.g0) this.b);
            this.a.b((n.d0) this.b);
            this.a.b((n.e0) this.b);
            j.a().a(Integer.valueOf(this.f13142c));
            AppLifeCycle.remove(this.a.a(), this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.jsapi.camera.b$b  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    public class RunnableC0522b implements Runnable {
        final /* synthetic */ MantoCameraViewContainer a;

        RunnableC0522b(b bVar, MantoCameraViewContainer mantoCameraViewContainer) {
            this.a = mantoCameraViewContainer;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.g();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class c implements IPermission.PermissionCallBack {
        final /* synthetic */ MantoCameraViewContainer a;
        final /* synthetic */ n b;

        /* loaded from: classes15.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                c.this.a.g();
            }
        }

        c(b bVar, MantoCameraViewContainer mantoCameraViewContainer, n nVar) {
            this.a = mantoCameraViewContainer;
            this.b = nVar;
        }

        @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
        public void onDenied() {
            HashMap hashMap = new HashMap();
            hashMap.put("cameraId", Integer.valueOf(this.a.getCameraId()));
            String jSONObject = new JSONObject(hashMap).toString();
            com.jingdong.manto.m.d a2 = new g().a(this.b);
            a2.f13315c = jSONObject;
            a2.a();
            j.a().a = false;
            j.a().b = false;
        }

        @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
        public void onGranted() {
            j.a().a = true;
            j.a().b = true;
            j.a().f13146c = true;
            MantoThreadUtils.runOnUIThread(new a());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(Context context, JSONObject jSONObject, String str, int i2) {
        if (jSONObject == null) {
            return i2;
        }
        try {
            return Math.round(MantoDensityUtils.getDensity(context) * ((float) jSONObject.getDouble(str)));
        } catch (Exception unused) {
            return i2;
        }
    }

    @Override // com.jingdong.manto.jsapi.base.d
    public int a(JSONObject jSONObject) {
        return jSONObject.optInt("cameraId");
    }

    @Override // com.jingdong.manto.jsapi.base.a
    public View a(n nVar, JSONObject jSONObject) {
        Context context = nVar.f14071i;
        MantoCameraViewContainer mantoCameraViewContainer = new MantoCameraViewContainer(context);
        int optInt = jSONObject.optInt("cameraId");
        String optString = jSONObject.optString("mode", "normal");
        String optString2 = jSONObject.optString("devicePosition", ThemeTitleConstant.TITLE_BACK_DRAWABLE_ID);
        String optString3 = jSONObject.optString("flash", "auto");
        boolean optBoolean = jSONObject.optBoolean("needOutput", false);
        mantoCameraViewContainer.setAppUniqueId(nVar.c());
        mantoCameraViewContainer.setMode(optString);
        mantoCameraViewContainer.setPage(nVar);
        mantoCameraViewContainer.setCameraId(optInt);
        mantoCameraViewContainer.a(optString2, true);
        mantoCameraViewContainer.setFlash(optString3);
        mantoCameraViewContainer.setNeedOutput(optBoolean);
        JSONObject optJSONObject = jSONObject.optJSONObject("position");
        int a2 = a(context, optJSONObject, "width", 0);
        int a3 = a(context, optJSONObject, "height", 0);
        if (a2 != 0 && a3 != 0) {
            mantoCameraViewContainer.a(a2, a3);
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("scanArea");
        if (optJSONArray == null || optJSONArray.length() != 4) {
            mantoCameraViewContainer.a(0, 0, a2, a3);
        } else {
            int density = (int) MantoDensityUtils.getDensity(context);
            mantoCameraViewContainer.a(optJSONArray.optInt(0) * density, optJSONArray.optInt(1) * density, optJSONArray.optInt(2) * density, density * optJSONArray.optInt(3));
        }
        mantoCameraViewContainer.setScanFreq(jSONObject.optInt("scanFreq"));
        a(nVar, mantoCameraViewContainer);
        return new CoverViewContainer(context, mantoCameraViewContainer);
    }

    @Override // com.jingdong.manto.jsapi.base.a
    public void a(n nVar, int i2, View view, JSONObject jSONObject) {
        super.a(nVar, i2, view, jSONObject);
        MantoCameraViewContainer mantoCameraViewContainer = (MantoCameraViewContainer) ((CoverViewContainer) view).convertTo(MantoCameraViewContainer.class);
        if (mantoCameraViewContainer == null) {
            return;
        }
        j.a().d.put(Integer.valueOf(mantoCameraViewContainer.getCameraId()), mantoCameraViewContainer);
        nVar.a((n.g0) mantoCameraViewContainer);
        nVar.a((n.f0) mantoCameraViewContainer);
        nVar.a((n.c0) mantoCameraViewContainer);
        nVar.a((n.d0) mantoCameraViewContainer);
        nVar.a((n.e0) mantoCameraViewContainer);
        AppLifeCycle.add(nVar.a(), new a(this, nVar, mantoCameraViewContainer, i2));
    }

    final boolean a(n nVar, MantoCameraViewContainer mantoCameraViewContainer) {
        if (!MantoPermission.hasPermissions(new String[]{"android.permission.CAMERA", "android.permission.RECORD_AUDIO"})) {
            MantoPermission.requestPermissions(nVar.h().i(), new String[]{"android.permission.CAMERA", "android.permission.RECORD_AUDIO"}, new c(this, mantoCameraViewContainer, nVar));
            return true;
        }
        j.a().a = true;
        j.a().b = true;
        j.a().f13146c = true;
        MantoThreadUtils.runOnUIThread(new RunnableC0522b(this, mantoCameraViewContainer));
        return true;
    }

    @Override // com.jingdong.manto.jsapi.base.a
    public boolean b() {
        return true;
    }

    @Override // com.jingdong.manto.jsapi.base.d, com.jingdong.manto.m.a
    public String getJsApiName() {
        return "insertCamera";
    }
}
