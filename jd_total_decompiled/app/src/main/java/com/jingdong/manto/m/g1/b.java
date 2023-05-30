package com.jingdong.manto.m.g1;

import android.os.Bundle;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.sdk.threadpool.ThreadManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class b extends AbstractMantoModule {

    /* loaded from: classes15.dex */
    class a implements Runnable {
        final /* synthetic */ MantoResultCallBack a;

        a(b bVar, MantoResultCallBack mantoResultCallBack) {
            this.a = mantoResultCallBack;
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x0025  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x002b  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void run() {
            String str;
            InputStream open;
            Bundle bundle = new Bundle();
            try {
                open = com.jingdong.manto.c.a().getAssets().open("manto_address");
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            if (open != null) {
                str = MantoStringUtils.convertStreamToString(open);
                if (!MantoStringUtils.isEmpty(str)) {
                    this.a.onFailed(bundle);
                    return;
                }
                bundle.putString("data", str);
                this.a.onSuccess(bundle);
                return;
            }
            str = null;
            if (!MantoStringUtils.isEmpty(str)) {
            }
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "regionData";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        ThreadManager.heavy().post(new a(this, mantoResultCallBack));
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle handleMethodSync(String str, MantoCore mantoCore, Bundle bundle) {
        return null;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        return null;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("getRegionData", 1));
    }
}
