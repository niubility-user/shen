package jd.wjweblogin.common;

import jd.wjweblogin.model.WJNetInitObject;

/* loaded from: classes11.dex */
public interface WJNetworkParamsProxy {
    String getNativeCookie();

    WJNetInitObject getNetInitParams();

    void initJDHttpTookit();

    void initJma();
}
