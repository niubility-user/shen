package com.jingdong.common.ordercenter;

import com.tencent.mm.opensdk.modelmsg.ShowMessageFromWX;

/* loaded from: classes5.dex */
public interface WeiXinObserver {
    void onWeiXinReq(ShowMessageFromWX.Req req);
}
