package jd.wjweblogin.common.listener;

import com.jingdong.jdsdk.network.toolbox.HttpError;
import jd.wjweblogin.model.WJErrorResult;
import jd.wjweblogin.model.WJFailResult;

/* loaded from: classes11.dex */
public interface WJReqWebCookieCallBack {
    void onFail(WJFailResult wJFailResult);

    void onHttpTaskError(HttpError httpError);

    void onLocalError(WJErrorResult wJErrorResult);

    void onSuccess(String str);

    void onWithinTheValidity(String str);
}
