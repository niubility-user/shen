package com.jingdong.manto.m;

import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.utils.MantoLog;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class b0 extends AbstractMantoModule {
    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "vibrate";
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x003d -> B:20:0x0042). Please submit an issue!!! */
    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        Vibrator vibrator = (Vibrator) com.jingdong.a.g().getSystemService("vibrator");
        if (vibrator != null) {
            str.hashCode();
            long j2 = !str.equals("vibrateLong") ? !str.equals("vibrateShort") ? 200L : 100L : 400L;
            try {
                if (Build.VERSION.SDK_INT >= 26) {
                    vibrator.vibrate(VibrationEffect.createOneShot(j2, -1));
                } else {
                    vibrator.vibrate(j2);
                }
            } catch (Exception e2) {
                MantoLog.e("Vibrator", e2);
            }
        }
        mantoResultCallBack.onSuccess(null);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putString("json", jSONObject != null ? jSONObject.toString() : "");
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("vibrateShort", 1));
        list.add(new JsApiMethod("vibrateLong", 1));
    }
}
