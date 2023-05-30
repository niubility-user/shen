package com.jingdong.manto.m.b1;

import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.launch.LaunchParam;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class a extends d0 {
    @Override // com.jingdong.manto.m.d0
    public void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        String str2;
        String jsApiName;
        super.exec(hVar, jSONObject, i2, str);
        try {
            PkgDetailEntity pkgDetailEntity = hVar.h().f13016h;
            if (pkgDetailEntity == null) {
                str2 = "fail:no pkg detail";
                jsApiName = getJsApiName();
            } else if (!pkgDetailEntity.isSwitchOpen(2)) {
                hVar.a(i2, putErrMsg("fail:permission is not granted", null, getJsApiName()));
                return;
            } else if (hVar.h().x()) {
                hVar.h().f();
                return;
            } else {
                LaunchParam launchParam = new LaunchParam();
                launchParam.appId = hVar.a();
                launchParam.debugType = String.valueOf(hVar.f13034e);
                new com.jingdong.manto.launch.e(launchParam).e();
                str2 = IMantoBaseModule.SUCCESS;
                jsApiName = getJsApiName();
            }
            hVar.a(i2, putErrMsg(str2, null, jsApiName));
        } catch (Throwable th) {
            hVar.a(i2, putErrMsg("fail:" + th.getMessage(), null, getJsApiName()));
        }
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "closeMiniProgram";
    }
}
