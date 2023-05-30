package com.jd.manto.c;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.moutaibuy.lib.MouTaiScannerActivity;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class a extends AbstractMantoModule {
    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "moutaiRecognition";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        JSONObject jSONObject;
        Bundle bundle2 = new Bundle();
        if ("imgRecognition".equals(str)) {
            try {
                jSONObject = new JSONObject(bundle.getString("timeParam"));
            } catch (JSONException e2) {
                e2.printStackTrace();
                jSONObject = null;
            }
            Activity activity = mantoCore != null ? mantoCore.getActivity() : null;
            if (mantoCore == null || activity == null) {
                if (mantoResultCallBack != null) {
                    mantoResultCallBack.onFailed(bundle2);
                    return;
                }
                return;
            }
            Intent intent = new Intent(activity, MouTaiScannerActivity.class);
            intent.putExtra("continuityInterval", jSONObject.optInt("continuityInterval", 1));
            intent.putExtra("timeList", jSONObject.optString("timeList"));
            activity.startActivityForResult(intent, R2.drawable.app_brand_actionbar_capsule_video_dark);
            return;
        }
        bundle2.putString("message", "method name error:" + str);
        mantoResultCallBack.onFailed(bundle2);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle handleResult(String str, MantoCore mantoCore, Intent intent, int i2, int i3) {
        Bundle bundle = new Bundle();
        if ("imgRecognition".equals(str) && i3 == 7693) {
            if (i2 == -1) {
                int i4 = intent.getExtras().getInt("code");
                int i5 = intent.getExtras().getInt("step");
                MantoLog.d("moutaiRecognition", "Code " + i4 + ", step " + i5);
                if (i4 == 9000) {
                    MantoLog.d("moutaiRecognition", "Success! Code " + i4);
                    bundle.putString(IMantoBaseModule.ERROR_CODE, "1");
                    bundle.putInt("errCode", i4);
                } else {
                    MantoLog.d("moutaiRecognition", "Failed! Code " + i4 + ", step " + i5);
                    bundle.putString(IMantoBaseModule.ERROR_CODE, "0");
                    bundle.putInt("errCode", i4);
                    bundle.putInt("step", i5);
                }
            } else if (i2 == 0) {
                MantoLog.d("moutaiRecognition", "RESULT_CANCELED");
                bundle.putString("message", "use cancel");
                bundle.putString(IMantoBaseModule.ERROR_CODE, "0");
            } else {
                MantoLog.d("moutaiRecognition", "resultCode " + i2);
                bundle.putString("message", "other error");
                bundle.putString(IMantoBaseModule.ERROR_CODE, "0");
            }
        }
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        if ("imgRecognition".equals(str)) {
            bundle.putString("timeParam", jSONObject.optJSONObject("params").toString());
            bundle.putInt("requestCode", R2.drawable.app_brand_actionbar_capsule_video_dark);
        }
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("imgRecognition", 2));
    }
}
