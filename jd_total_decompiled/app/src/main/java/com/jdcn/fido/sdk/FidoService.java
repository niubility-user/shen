package com.jdcn.fido.sdk;

import android.app.Activity;
import android.os.Bundle;
import com.jdcn.fido.http.HttpUrlUtil;
import com.jdcn.fido.service.FidoServiceImpl;

/* loaded from: classes18.dex */
public class FidoService {
    public FidoService() {
    }

    public FidoService(String str) {
        HttpUrlUtil.setHostUrl(str);
    }

    public static void interrupt() {
        FidoServiceImpl.interrupt();
    }

    public void getDeviceId(Activity activity, Bundle bundle, IFidoCallback iFidoCallback) {
        FidoServiceImpl.getDeviceId(activity, bundle, iFidoCallback);
    }

    public void isFingerPayOpen(Activity activity, Bundle bundle, IFidoCallback iFidoCallback) {
        FidoServiceImpl.isFingerPayOpen(activity, bundle, iFidoCallback);
    }

    public void regist(Activity activity, Bundle bundle, IFidoCallback iFidoCallback) {
        FidoServiceImpl.regist(activity, bundle, iFidoCallback);
    }

    public void transport(Activity activity, Bundle bundle, IFidoCallback iFidoCallback) {
        FidoServiceImpl.transport(activity, bundle, iFidoCallback);
    }

    public void unRegist(Activity activity, Bundle bundle, IFidoCallback iFidoCallback) {
        FidoServiceImpl.unregister(activity, bundle, iFidoCallback);
    }
}
