package com.jingdong.manto.m.b1;

import android.content.DialogInterface;
import android.text.TextUtils;
import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.b1.e;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.network.common.IMantoHttpListener;
import com.jingdong.manto.network.common.MantoJDHttpHandler;
import com.jingdong.manto.network.mantorequests.t;
import com.jingdong.manto.utils.MantoThreadUtils;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class d extends d0 {
    private static Set<String> a;

    /* loaded from: classes15.dex */
    class a extends IMantoHttpListener {
        final /* synthetic */ h a;
        final /* synthetic */ int b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f13297c;
        final /* synthetic */ String d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ int f13298e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ String f13299f;

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ JSONObject f13300g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f13301h;

        /* renamed from: com.jingdong.manto.m.b1.d$a$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class DialogInterfaceOnClickListenerC0548a implements DialogInterface.OnClickListener {

            /* renamed from: com.jingdong.manto.m.b1.d$a$a$a  reason: collision with other inner class name */
            /* loaded from: classes15.dex */
            class C0549a implements e.a {
                C0549a() {
                }

                @Override // com.jingdong.manto.m.b1.e.a
                public void a(boolean z) {
                    d.a.remove(a.this.f13297c);
                    a aVar = a.this;
                    aVar.a.a(aVar.b, d.this.putErrMsg(z ? IMantoBaseModule.SUCCESS : "fail", null, aVar.f13301h));
                }
            }

            DialogInterfaceOnClickListenerC0548a() {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                d.a.add(a.this.f13297c);
                a aVar = a.this;
                e.a(aVar.a, aVar.f13297c, aVar.d, aVar.f13298e, aVar.f13299f, aVar.f13300g, new C0549a());
            }
        }

        /* loaded from: classes15.dex */
        class b implements DialogInterface.OnClickListener {
            b() {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                a aVar = a.this;
                aVar.a.a(aVar.b, d.this.putErrMsg("fail:cancel"));
            }
        }

        /* loaded from: classes15.dex */
        class c implements DialogInterface.OnCancelListener {
            c() {
            }

            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                a aVar = a.this;
                aVar.a.a(aVar.b, d.this.putErrMsg("cancel:cancel"));
            }
        }

        /* renamed from: com.jingdong.manto.m.b1.d$a$d  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class RunnableC0550d implements Runnable {
            final /* synthetic */ String a;
            final /* synthetic */ DialogInterface.OnClickListener b;

            /* renamed from: c  reason: collision with root package name */
            final /* synthetic */ DialogInterface.OnClickListener f13303c;
            final /* synthetic */ DialogInterface.OnCancelListener d;

            RunnableC0550d(String str, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2, DialogInterface.OnCancelListener onCancelListener) {
                this.a = str;
                this.b = onClickListener;
                this.f13303c = onClickListener2;
                this.d = onCancelListener;
            }

            @Override // java.lang.Runnable
            public void run() {
                a aVar = a.this;
                com.jingdong.manto.widget.dialog.a.a(d.this.getCore(aVar.a).getActivity(), null, this.a, "\u5141\u8bb8", "\u53d6\u6d88", this.b, this.f13303c, this.d, null, null).show();
            }
        }

        a(h hVar, int i2, String str, String str2, int i3, String str3, JSONObject jSONObject, String str4) {
            this.a = hVar;
            this.b = i2;
            this.f13297c = str;
            this.d = str2;
            this.f13298e = i3;
            this.f13299f = str3;
            this.f13300g = jSONObject;
            this.f13301h = str4;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            super.onError(jSONObject, th);
            this.a.a(this.b, d.this.putErrMsg("fail:target appId getinfo error"));
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            String optString = jSONObject.optString("code");
            if (!TextUtils.equals("0", optString)) {
                this.a.a(this.b, d.this.putErrMsg("fail:code " + optString));
                return;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("data");
            if (optJSONObject == null) {
                this.a.a(this.b, d.this.putErrMsg("fail:mini app info data is null"));
                return;
            }
            JSONObject optJSONObject2 = optJSONObject.optJSONObject("info");
            if (optJSONObject2 == null) {
                this.a.a(this.b, d.this.putErrMsg("fail:mini app info is null"));
                return;
            }
            String optString2 = optJSONObject2.optString("name");
            if (TextUtils.isEmpty(optString2)) {
                this.a.a(this.b, d.this.putErrMsg("fail:mini app name is null"));
                return;
            }
            MantoThreadUtils.runOnUIThread(new RunnableC0550d("\u5373\u5c06\u6253\u5f00\"" + optString2 + "\"\u5c0f\u7a0b\u5e8f", new DialogInterfaceOnClickListenerC0548a(), new b(), new c()));
        }
    }

    @Override // com.jingdong.manto.m.d0
    public final void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        String optString = jSONObject.optString("appId");
        if (optString == null || hVar.h().f13017i.equals(optString)) {
            hVar.a(i2, putErrMsg("fail:target appId is null or is the same as the caller appId"));
            return;
        }
        String optString2 = jSONObject.optString("path");
        JSONObject optJSONObject = jSONObject.optJSONObject("extraData");
        String optString3 = jSONObject.optString("envVersion", "release");
        int optInt = jSONObject.optInt("sourcetype");
        String str2 = TextUtils.equals("release", optString3) ? "1" : "2";
        if (a == null) {
            a = new HashSet();
        }
        if (a.contains(optString)) {
            hVar.a(i2, putErrMsg("fail:target appId is launching, please wait"));
        } else {
            MantoJDHttpHandler.commit(new t(optString, str2), new a(hVar, i2, optString, str2, optInt, optString2, optJSONObject, str));
        }
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "navigateToMiniProgram";
    }
}
