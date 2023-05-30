package jd.wjweblogin.common;

import org.json.JSONObject;

/* loaded from: classes11.dex */
public interface WJWebLoginExtendProxy {
    String getKoWhiteList();

    String getPin();

    String getWhiteList();

    String getWsKey();

    boolean isOpenNewReqWebCookie();

    void jmaReport(String str, String str2, JSONObject jSONObject);

    int requestTimeout();
}
