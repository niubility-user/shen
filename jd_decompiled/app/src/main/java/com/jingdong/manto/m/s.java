package com.jingdong.manto.m;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.lib.cashier.sdk.complete.entity.CashierCustomMessage;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoUtils;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class s extends d0 {

    /* loaded from: classes15.dex */
    class a implements MantoResultCallBack {
        final /* synthetic */ com.jingdong.manto.h a;
        final /* synthetic */ int b;

        a(com.jingdong.manto.h hVar, int i2) {
            this.a = hVar;
            this.b = i2;
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoResultCallBack
        public void onCancel(Bundle bundle) {
            this.a.a(this.b, s.this.putErrMsg("cancel", null));
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoResultCallBack
        public void onFailed(Bundle bundle) {
            this.a.a(this.b, s.this.putErrMsg("fail:error", null));
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoResultCallBack
        public void onSuccess(Bundle bundle) {
            this.a.a(this.b, s.this.putErrMsg(IMantoBaseModule.SUCCESS, MantoUtils.formatBundle(bundle)));
        }
    }

    @Override // com.jingdong.manto.m.d0
    public final void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        String optString = jSONObject.optString("data");
        if (!TextUtils.isEmpty(optString)) {
            MantoLog.d("JsApiSetClipboardData", String.format("set clipBoardData: %s", optString));
            ClipboardManager clipboardManager = (ClipboardManager) hVar.p().getSystemService(CashierCustomMessage.KEY.CHANNEL_CLIP_BOARD);
            if (clipboardManager != null) {
                clipboardManager.setPrimaryClip(ClipData.newPlainText("text", optString));
                hVar.a(i2, putErrMsg(IMantoBaseModule.SUCCESS, null, str));
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("duration", 1000);
                    jSONObject2.put("title", "\u5185\u5bb9\u5df2\u590d\u5236");
                    jSONObject2.put("icon", "success");
                    jSONObject2.put("mask", false);
                    Bundle bundle = new Bundle();
                    bundle.putInt(IMantoBaseModule.COMPONENT_HASHCODE, c0.getPageView(hVar).hashCode());
                    bundle.putString("params", jSONObject2.toString());
                    new com.jingdong.manto.m.p1.a().handleMethod("showToast", getCore(hVar), bundle, new a(hVar, i2));
                    return;
                } catch (Throwable th) {
                    th.printStackTrace();
                    return;
                }
            }
            MantoLog.i("JsApiSetClipboardData", "getSystemService(CLIPBOARD_SERVICE) failed.");
        }
        hVar.a(i2, putErrMsg("fail", null, str));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "setClipboardData";
    }
}
