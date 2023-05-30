package com.jingdong.manto.m.g1;

import android.os.Bundle;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.sdk.threadpool.ThreadManager;
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
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r3 = this;
                android.os.Bundle r0 = new android.os.Bundle
                r0.<init>()
                android.content.Context r1 = com.jingdong.manto.c.a()     // Catch: java.io.IOException -> L1a
                android.content.res.AssetManager r1 = r1.getAssets()     // Catch: java.io.IOException -> L1a
                java.lang.String r2 = "manto_address"
                java.io.InputStream r1 = r1.open(r2)     // Catch: java.io.IOException -> L1a
                if (r1 == 0) goto L1e
                java.lang.String r1 = com.jingdong.manto.utils.MantoStringUtils.convertStreamToString(r1)     // Catch: java.io.IOException -> L1a
                goto L1f
            L1a:
                r1 = move-exception
                r1.printStackTrace()
            L1e:
                r1 = 0
            L1f:
                boolean r2 = com.jingdong.manto.utils.MantoStringUtils.isEmpty(r1)
                if (r2 == 0) goto L2b
                com.jingdong.manto.jsapi.openmodule.MantoResultCallBack r1 = r3.a
                r1.onFailed(r0)
                return
            L2b:
                java.lang.String r2 = "data"
                r0.putString(r2, r1)
                com.jingdong.manto.jsapi.openmodule.MantoResultCallBack r1 = r3.a
                r1.onSuccess(r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.m.g1.b.a.run():void");
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
