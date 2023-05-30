package com.jingdong.manto.jsapi.openmodule;

import android.os.Bundle;
import com.jingdong.manto.MantoCore;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes15.dex */
public abstract class AbstractMantoModuleGen2 extends AbstractMantoModule {
    private Map<String, ApiWorker> workerMap = new ConcurrentHashMap();

    private ApiWorker getWorker(String str) {
        ApiWorker apiWorker = this.workerMap.get(str);
        if (apiWorker == null) {
            ApiWorker genApiWorker = genApiWorker(this, str);
            this.workerMap.put(str, genApiWorker);
            return genApiWorker;
        }
        return apiWorker;
    }

    public void clean() {
        this.workerMap.clear();
    }

    protected abstract ApiWorker genApiWorker(AbstractMantoModuleGen2 abstractMantoModuleGen2, String str);

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        getWorker(bundle.getString("appid", "")).handleMethod(str, mantoCore, bundle, mantoResultCallBack);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle handleMethodSync(String str, MantoCore mantoCore, Bundle bundle) {
        return getWorker(bundle.getString("appid", "")).handleMethodSync(str, mantoCore, bundle);
    }

    public void removeWorker(String str) {
        this.workerMap.remove(str);
    }
}
