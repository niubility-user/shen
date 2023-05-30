package com.jingdong.manto.m.i1;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.m.i1.b;
import com.jingdong.manto.network.common.IMantoHttpListener;
import com.jingdong.manto.network.common.MantoJDHttpHandler;
import com.jingdong.manto.network.mantorequests.w;
import com.jingdong.manto.network.mantorequests.y;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.utils.MantoThreadUtils;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class a extends AbstractMantoModule {
    private Reference<com.jingdong.manto.m.i1.b> a;

    /* renamed from: com.jingdong.manto.m.i1.a$a  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class C0565a extends IMantoHttpListener {
        final /* synthetic */ MantoResultCallBack a;

        C0565a(a aVar, MantoResultCallBack mantoResultCallBack) {
            this.a = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            super.onError(jSONObject, th);
            Bundle bundle = new Bundle();
            bundle.putString("message", "request error, " + jSONObject);
            this.a.onFailed(bundle);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            if (jSONObject.optInt("code", -1) == 0) {
                this.a.onSuccess(new Bundle());
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("message", "request resp code error");
            this.a.onFailed(bundle);
        }
    }

    /* loaded from: classes15.dex */
    class b extends IMantoHttpListener {
        final /* synthetic */ MantoResultCallBack a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f13369c;
        final /* synthetic */ MantoCore d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ List f13370e;

        /* renamed from: com.jingdong.manto.m.i1.a$b$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class RunnableC0566a implements Runnable {
            final /* synthetic */ List a;
            final /* synthetic */ PkgDetailEntity b;

            /* renamed from: com.jingdong.manto.m.i1.a$b$a$a  reason: collision with other inner class name */
            /* loaded from: classes15.dex */
            class C0567a implements b.InterfaceC0571b {

                /* renamed from: com.jingdong.manto.m.i1.a$b$a$a$a  reason: collision with other inner class name */
                /* loaded from: classes15.dex */
                class C0568a extends IMantoHttpListener {
                    C0568a() {
                    }

                    @Override // com.jingdong.manto.network.common.IMantoHttpListener
                    public void onError(JSONObject jSONObject, Throwable th) {
                        super.onError(jSONObject, th);
                        Bundle bundle = new Bundle();
                        bundle.putString("message", "submit request resp error, " + jSONObject);
                        b.this.a.onFailed(bundle);
                    }

                    @Override // com.jingdong.manto.network.common.IMantoHttpListener
                    public void onSuccess(JSONObject jSONObject) {
                        if (jSONObject.optInt("code", -1) == 0) {
                            b.this.a.onSuccess(new Bundle());
                            return;
                        }
                        Bundle bundle = new Bundle();
                        bundle.putString("message", "request resp code error");
                        b.this.a.onFailed(bundle);
                    }
                }

                C0567a() {
                }

                @Override // com.jingdong.manto.m.i1.b.InterfaceC0571b
                public void a(boolean z, List<d> list) {
                    JSONArray jSONArray = new JSONArray();
                    ArrayList arrayList = new ArrayList();
                    try {
                        for (d dVar : list) {
                            if (dVar.f13384c) {
                                if (z) {
                                    arrayList.add(dVar.b);
                                }
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("templateId", dVar.b);
                                jSONObject.put("status", true);
                                jSONArray.put(jSONObject);
                            }
                        }
                        Iterator it = b.this.f13370e.iterator();
                        while (it.hasNext()) {
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("templateId", ((d) it.next()).b);
                            jSONObject2.put("status", true);
                            jSONArray.put(jSONObject2);
                        }
                    } catch (Throwable unused) {
                    }
                    if (z) {
                        c.a(true, b.this.b, arrayList);
                    }
                    MantoJDHttpHandler.commit(new w(b.this.b, jSONArray), new C0568a());
                }

                @Override // com.jingdong.manto.m.i1.b.InterfaceC0571b
                public void b(boolean z, List<d> list) {
                    Bundle bundle = new Bundle();
                    bundle.putString("message", "user reject");
                    b.this.a.onFailed(bundle);
                    if (z) {
                        ArrayList arrayList = new ArrayList();
                        try {
                            for (d dVar : list) {
                                if (dVar.f13384c) {
                                    arrayList.add(dVar.b);
                                }
                            }
                        } catch (Throwable unused) {
                        }
                        c.a(false, b.this.b, arrayList);
                    }
                }

                @Override // com.jingdong.manto.m.i1.b.InterfaceC0571b
                public void onCancel() {
                    Bundle bundle = new Bundle();
                    bundle.putString("message", "user cancel");
                    b.this.a.onFailed(bundle);
                }
            }

            RunnableC0566a(List list, PkgDetailEntity pkgDetailEntity) {
                this.a = list;
                this.b = pkgDetailEntity;
            }

            @Override // java.lang.Runnable
            public void run() {
                Activity activity = b.this.d.getActivity();
                if (activity == null || activity.isFinishing()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("message", "no activity error");
                    b.this.a.onFailed(bundle);
                    return;
                }
                com.jingdong.manto.m.i1.b bVar = a.this.a != null ? (com.jingdong.manto.m.i1.b) a.this.a.get() : null;
                if (bVar != null && bVar.isShowing()) {
                    bVar.cancel();
                }
                List list = this.a;
                PkgDetailEntity pkgDetailEntity = this.b;
                com.jingdong.manto.m.i1.b bVar2 = new com.jingdong.manto.m.i1.b(activity, list, pkgDetailEntity.name, pkgDetailEntity.logo, new C0567a());
                bVar2.show();
                c.e(b.this.b);
                a.this.a = new WeakReference(bVar2);
            }
        }

        b(MantoResultCallBack mantoResultCallBack, String str, String str2, MantoCore mantoCore, List list) {
            this.a = mantoResultCallBack;
            this.b = str;
            this.f13369c = str2;
            this.d = mantoCore;
            this.f13370e = list;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            super.onError(jSONObject, th);
            Bundle bundle = new Bundle();
            bundle.putString("message", "request template info error, " + jSONObject);
            this.a.onFailed(bundle);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            if (jSONObject.optInt("code", -1) != 0) {
                Bundle bundle = new Bundle();
                bundle.putString("message", "request resp code none 0 error");
                this.a.onFailed(bundle);
                return;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("result");
            if (optJSONObject == null) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("message", "request resp error, no result");
                this.a.onFailed(bundle2);
                return;
            }
            JSONArray optJSONArray = optJSONObject.optJSONArray("authPushList");
            if (optJSONArray == null || optJSONArray.length() == 0) {
                Bundle bundle3 = new Bundle();
                bundle3.putString("message", "request resp error, no authList");
                this.a.onFailed(bundle3);
                return;
            }
            PkgDetailEntity c2 = com.jingdong.manto.b.k().c(this.b, this.f13369c);
            if (c2 == null) {
                Bundle bundle4 = new Bundle();
                bundle4.putString("message", "pkg detail error");
                this.a.onFailed(bundle4);
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                d dVar = new d();
                dVar.b = optJSONObject2.optString("templateId");
                dVar.a = optJSONObject2.optString("name");
                arrayList.add(dVar);
            }
            MantoThreadUtils.runOnUIThread(new RunnableC0566a(arrayList, c2));
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "push";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(bundle.getString("params"));
        } catch (Exception unused) {
            jSONObject = null;
        }
        if (jSONObject == null) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("message", "params error");
            mantoResultCallBack.onFailed(bundle2);
            return;
        }
        String string = bundle.getString("appid");
        String string2 = bundle.getString("type");
        if (!TextUtils.equals("requestSubscribeMessage", str)) {
            Bundle bundle3 = new Bundle();
            bundle3.putString("message", "unKnown method error, " + str);
            mantoResultCallBack.onFailed(bundle3);
            return;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("authPushTemplateList");
        if (optJSONArray == null || optJSONArray.length() == 0) {
            Bundle bundle4 = new Bundle();
            bundle4.putString("message", "params error, list empty");
            mantoResultCallBack.onFailed(bundle4);
            return;
        }
        JSONArray jSONArray = new JSONArray();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
            d dVar = new d();
            String optString = optJSONObject.optString("templateId");
            dVar.b = optString;
            String a = c.a(string, optString);
            dVar.d = a;
            if (!c.c(a)) {
                jSONArray.put(optJSONObject);
            } else if (c.b(dVar.d)) {
                arrayList.add(dVar);
            }
        }
        if (jSONArray.length() != 0) {
            MantoJDHttpHandler.commit(new y(string, jSONArray), new b(mantoResultCallBack, string, string2, mantoCore, arrayList));
        } else if (arrayList.size() == 0) {
            Bundle bundle5 = new Bundle();
            bundle5.putString("message", "not ask again");
            mantoResultCallBack.onFailed(bundle5);
        } else {
            JSONArray jSONArray2 = new JSONArray();
            try {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("templateId", ((d) it.next()).b);
                    jSONObject2.put("status", true);
                    jSONArray2.put(jSONObject2);
                }
            } catch (Throwable unused2) {
            }
            MantoJDHttpHandler.commit(new w(string, jSONArray2), new C0565a(this, mantoResultCallBack));
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle(1);
        bundle.putString("params", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("requestSubscribeMessage", 1));
    }
}
