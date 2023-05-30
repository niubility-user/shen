package com.jingdong.common.ordercenter;

import com.jingdong.sdk.oklog.OKLog;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelmsg.ShowMessageFromWX;

/* loaded from: classes5.dex */
public class WeiXinManager {
    private static final String TAG = "WeiXinManager";
    private static BaseReq baseReq;
    private static WeiXinManager weiXinManager;
    private static WeiXinObserver wxObserver;

    private WeiXinManager() {
    }

    public static WeiXinManager getInstance() {
        if (weiXinManager == null) {
            synchronized (WeiXinManager.class) {
                if (weiXinManager == null) {
                    weiXinManager = new WeiXinManager();
                }
            }
        }
        return weiXinManager;
    }

    public void deregisterOrderCenterObserver() {
        wxObserver = null;
    }

    public void notifyObserver() {
        if (wxObserver == null) {
            if (OKLog.D) {
                OKLog.d(TAG, "observer is null");
                return;
            }
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "observer is available");
        }
        wxObserver.onWeiXinReq((ShowMessageFromWX.Req) baseReq);
    }

    public void receivedReq(BaseReq baseReq2) {
        if (baseReq2 == null) {
            return;
        }
        baseReq = baseReq2;
        notifyObserver();
    }

    public void registerOrderCenterObserver(WeiXinObserver weiXinObserver) {
        if (weiXinObserver != null) {
            wxObserver = weiXinObserver;
        }
    }
}
