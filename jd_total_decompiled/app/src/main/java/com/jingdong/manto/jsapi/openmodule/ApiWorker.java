package com.jingdong.manto.jsapi.openmodule;

import android.os.Bundle;
import com.jingdong.manto.AppLifeCycle;
import com.jingdong.manto.MantoCore;

/* loaded from: classes15.dex */
public abstract class ApiWorker extends AppLifeCycle.Listener {
    protected String appId;
    protected AbstractMantoModuleGen2 moduleGen2;

    public ApiWorker(AbstractMantoModuleGen2 abstractMantoModuleGen2, String str) {
        this.moduleGen2 = abstractMantoModuleGen2;
        this.appId = str;
    }

    public abstract void doMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack);

    public abstract Bundle doMethodSync(String str, MantoCore mantoCore, Bundle bundle);

    public final void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        String string = bundle.getString("appid", "");
        this.appId = string;
        AppLifeCycle.add(string, this);
        doMethod(str, mantoCore, bundle, mantoResultCallBack);
    }

    public final Bundle handleMethodSync(String str, MantoCore mantoCore, Bundle bundle) {
        String string = bundle.getString("appid", "");
        this.appId = string;
        AppLifeCycle.add(string, this);
        return doMethodSync(str, mantoCore, bundle);
    }

    @Override // com.jingdong.manto.AppLifeCycle.Listener
    public void onAppDestroy() {
        onTaskEnd();
    }

    public void onTaskEnd() {
        AbstractMantoModuleGen2 abstractMantoModuleGen2 = this.moduleGen2;
        if (abstractMantoModuleGen2 != null) {
            abstractMantoModuleGen2.removeWorker(this.appId);
        }
    }
}
