package com.jingdong.manto.m.x0;

import android.content.Intent;
import android.net.Uri;
import com.jingdong.manto.MantoActivityResult;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoStringUtils;
import com.tencent.smtt.sdk.WebView;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class a extends d0 {

    /* renamed from: com.jingdong.manto.m.x0.a$a  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class C0634a implements MantoActivityResult.ResultCallback {
        final /* synthetic */ int a;
        final /* synthetic */ h b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f13817c;
        final /* synthetic */ String d;

        C0634a(int i2, h hVar, int i3, String str) {
            this.a = i2;
            this.b = hVar;
            this.f13817c = i3;
            this.d = str;
        }

        @Override // com.jingdong.manto.MantoActivityResult.ResultCallback
        public void onActivityResult(int i2, int i3, Intent intent) {
            if (i2 == this.a) {
                this.b.a(this.f13817c, a.this.putErrMsg(IMantoBaseModule.SUCCESS, null, this.d));
            }
        }
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        String putErrMsg;
        String optString = jSONObject.optString("phoneNumber");
        if (MantoStringUtils.isEmpty(optString)) {
            putErrMsg = putErrMsg("fail", null, str);
        } else {
            MantoCore core = getCore(hVar);
            if (core != null && core.getActivityResultImpl() != null && core.getActivity() != null) {
                int hashCode = hashCode() & 65535;
                core.getActivityResultImpl().setResultCallback(new C0634a(hashCode, hVar, i2, str));
                Intent intent = new Intent("android.intent.action.DIAL");
                intent.setData(Uri.parse(WebView.SCHEME_TEL + Uri.encode(optString)));
                try {
                    core.getActivity().startActivityForResult(intent, hashCode);
                    return;
                } catch (Exception unused) {
                    MantoLog.e("JsApiMakePhoneCall", "startActivity failed");
                    hVar.a(i2, putErrMsg("fail", null, str));
                    return;
                }
            }
            putErrMsg = putErrMsg("fail", null, str);
        }
        hVar.a(i2, putErrMsg);
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "makePhoneCall";
    }
}
