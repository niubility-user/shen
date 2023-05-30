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
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class b extends d0 {

    /* loaded from: classes15.dex */
    class a implements MantoActivityResult.ResultCallback {
        final /* synthetic */ int a;
        final /* synthetic */ h b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f13819c;
        final /* synthetic */ String d;

        a(int i2, h hVar, int i3, String str) {
            this.a = i2;
            this.b = hVar;
            this.f13819c = i3;
            this.d = str;
        }

        @Override // com.jingdong.manto.MantoActivityResult.ResultCallback
        public void onActivityResult(int i2, int i3, Intent intent) {
            if (i2 == this.a) {
                this.b.a(this.f13819c, b.this.putErrMsg(IMantoBaseModule.SUCCESS, null, this.d));
            }
        }
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        String optString = jSONObject.optString("phoneNumber");
        String optString2 = jSONObject.optString("smsBody");
        if (optString2 == null) {
            optString2 = "";
        }
        String str2 = optString2;
        if (MantoStringUtils.isEmpty(optString)) {
            hVar.a(i2, putErrMsg("fail", null, str));
            return;
        }
        MantoCore core = getCore(hVar);
        if (core == null || core.getActivityResultImpl() == null || core.getActivity() == null) {
            hVar.a(i2, putErrMsg("fail", null, str));
            return;
        }
        int hashCode = hashCode() & 65535;
        core.getActivityResultImpl().setResultCallback(new a(hashCode, hVar, i2, str));
        Intent intent = new Intent("android.intent.action.SENDTO");
        intent.setData(Uri.parse("smsto:" + optString));
        intent.putExtra("sms_body", str2);
        try {
            core.getActivity().startActivityForResult(intent, hashCode);
        } catch (Exception unused) {
            MantoLog.e("JsApiSendSms", "startActivity failed");
            hVar.a(i2, putErrMsg("fail", null, str));
        }
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "sendSms";
    }
}
