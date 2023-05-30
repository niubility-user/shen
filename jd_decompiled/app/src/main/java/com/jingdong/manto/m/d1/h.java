package com.jingdong.manto.m.d1;

import android.content.Intent;
import android.os.Bundle;
import com.jingdong.manto.MantoActivityResult;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.m.e0;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class h extends c {

    /* loaded from: classes15.dex */
    class a implements MantoActivityResult.ResultCallback {
        final /* synthetic */ int a;
        final /* synthetic */ boolean b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ MantoCore f13339c;
        final /* synthetic */ e0 d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ int f13340e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ String f13341f;

        /* renamed from: com.jingdong.manto.m.d1.h$a$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class C0559a implements MantoResultCallBack {
            C0559a() {
            }

            @Override // com.jingdong.manto.jsapi.openmodule.MantoResultCallBack
            public void onCancel(Bundle bundle) {
                String string = bundle.getString("message", "error");
                bundle.remove("message");
                a aVar = a.this;
                aVar.d.a(aVar.f13340e, h.this.putErrMsg("cancel:" + string, h.this.a(bundle), a.this.f13341f));
            }

            @Override // com.jingdong.manto.jsapi.openmodule.MantoResultCallBack
            public void onFailed(Bundle bundle) {
                String string = bundle.getString("message", "error");
                bundle.remove("message");
                a aVar = a.this;
                aVar.d.a(aVar.f13340e, h.this.putErrMsg("fail:" + string, h.this.a(bundle), a.this.f13341f));
            }

            @Override // com.jingdong.manto.jsapi.openmodule.MantoResultCallBack
            public void onSuccess(Bundle bundle) {
                a aVar = a.this;
                e0 e0Var = aVar.d;
                int i2 = aVar.f13340e;
                h hVar = h.this;
                e0Var.a(i2, hVar.putErrMsg(IMantoBaseModule.SUCCESS, hVar.a(bundle), a.this.f13341f));
            }
        }

        a(int i2, boolean z, MantoCore mantoCore, e0 e0Var, int i3, String str) {
            this.a = i2;
            this.b = z;
            this.f13339c = mantoCore;
            this.d = e0Var;
            this.f13340e = i3;
            this.f13341f = str;
        }

        @Override // com.jingdong.manto.MantoActivityResult.ResultCallback
        public void onActivityResult(int i2, int i3, Intent intent) {
            e0 e0Var;
            int i4;
            String putErrMsg;
            if (this.a == i2) {
                if (this.b) {
                    h.this.a.c().handleResultWithCallback(h.this.a.a(), this.f13339c, intent, i3, i2, new C0559a());
                } else {
                    Bundle handleResult = h.this.a.c().handleResult(h.this.a.a(), this.f13339c, intent, i3, i2);
                    if (handleResult == null) {
                        this.d.a(this.f13340e, h.this.putErrMsg("fail", null, this.f13341f));
                        return;
                    }
                    String string = handleResult.getString(IMantoBaseModule.ERROR_CODE, "0");
                    String string2 = handleResult.getString("message", "error");
                    Map<String, ? extends Object> a = h.this.a(handleResult);
                    if (a == null) {
                        a = new HashMap<>(1);
                    }
                    a.remove(IMantoBaseModule.ERROR_CODE);
                    if ("1".equals(string)) {
                        e0Var = this.d;
                        i4 = this.f13340e;
                        putErrMsg = h.this.putErrMsg(IMantoBaseModule.SUCCESS, a, this.f13341f);
                    } else if ("0".equals(string)) {
                        a.remove("message");
                        e0Var = this.d;
                        i4 = this.f13340e;
                        putErrMsg = h.this.putErrMsg("fail:" + string2, a, this.f13341f);
                    } else {
                        boolean equals = "-1".equals(string);
                        a.remove("message");
                        if (equals) {
                            this.d.a(this.f13340e, h.this.putErrMsg("cancel", a, this.f13341f));
                        } else {
                            this.d.a(this.f13340e, h.this.putErrMsg("" + handleResult.getString("result", "fail") + ":" + string2, a, this.f13341f));
                        }
                    }
                }
                this.f13339c.getActivityResultImpl().restoreResultCallback();
            }
            e0Var = this.d;
            i4 = this.f13340e;
            putErrMsg = h.this.putErrMsg("fail:no matched", null, this.f13341f);
            e0Var.a(i4, putErrMsg);
            this.f13339c.getActivityResultImpl().restoreResultCallback();
        }
    }

    /* loaded from: classes15.dex */
    class b implements MantoResultCallBack {
        final /* synthetic */ e0 a;
        final /* synthetic */ int b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f13343c;

        b(e0 e0Var, int i2, String str) {
            this.a = e0Var;
            this.b = i2;
            this.f13343c = str;
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoResultCallBack
        public void onCancel(Bundle bundle) {
            this.a.a(this.b, h.this.putErrMsg("cancel", null, this.f13343c));
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoResultCallBack
        public void onFailed(Bundle bundle) {
            String string = bundle.getString("message", "error");
            this.a.a(this.b, h.this.putErrMsg("fail:" + string, null, this.f13343c));
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoResultCallBack
        public void onSuccess(Bundle bundle) {
            e0 e0Var = this.a;
            int i2 = this.b;
            h hVar = h.this;
            e0Var.a(i2, hVar.putErrMsg(IMantoBaseModule.SUCCESS, hVar.a(bundle), this.f13343c));
        }
    }

    public h(i iVar) {
        super(iVar);
    }

    private Bundle a(String str, MantoCore mantoCore, JSONObject jSONObject) {
        return this.a.c().initData(str, mantoCore, jSONObject);
    }

    @Override // com.jingdong.manto.m.d1.c
    protected void a(e0 e0Var, JSONObject jSONObject, int i2, int i3, String str) {
        MantoCore core = getCore(e0Var);
        if (core == null) {
            e0Var.a(i2, putErrMsg("fail", null, str));
            return;
        }
        Bundle a2 = a(this.a.a(), core, jSONObject);
        a2.putString("appid", e0Var.a());
        a2.putString(IMantoBaseModule.APP_UNIQUEID_ID_KEY, e0Var.c());
        if (e0Var.h().f13016h != null) {
            a2.putString("type", e0Var.h().f13016h.type);
        }
        int i4 = a2.getInt("requestCode", 10001);
        boolean z = a2.getBoolean(IMantoBaseModule.HANDLERESULT_WITHCALLBACK, false);
        if (a2 == null) {
            a2 = new Bundle();
        }
        a2.putInt(IMantoBaseModule.COMPONENT_HASHCODE, e0Var.hashCode());
        if (a2.getBoolean(IMantoBaseModule.ADD_EXTRAS_DATA)) {
            a2.putString(IMantoBaseModule.EXTRAS_DATA, e0Var.h().r.f13090m);
        }
        core.getActivityResultImpl().setResultCallback(new a(i4, z, core, e0Var, i2, str));
        this.a.c().handleMethod(this.a.a(), core, a2, new b(e0Var, i2, str));
    }
}
