package com.jd.manto.membercode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class a extends AbstractMantoModule {
    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "memberCode";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        super.handleMethod(str, mantoCore, bundle, mantoResultCallBack);
        Bundle bundle2 = new Bundle();
        boolean hasLogin = LoginUserBase.hasLogin();
        Activity activity = mantoCore != null ? mantoCore.getActivity() : null;
        if (mantoCore == null || activity == null) {
            mantoResultCallBack.onFailed(bundle2);
        } else if (!hasLogin) {
            bundle2.putString("message", "not login");
            mantoResultCallBack.onFailed(bundle2);
        } else if (TextUtils.equals("showMembersCode", str)) {
            activity.startActivity(new Intent(activity, MantoMemberCodeActivity.class));
            mantoResultCallBack.onSuccess(bundle2);
        } else {
            mantoResultCallBack.onFailed(bundle2);
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        return null;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("showMembersCode", 1));
    }
}
