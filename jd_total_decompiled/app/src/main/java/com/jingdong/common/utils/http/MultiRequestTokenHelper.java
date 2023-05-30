package com.jingdong.common.utils.http;

import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.Map;

/* loaded from: classes6.dex */
public class MultiRequestTokenHelper {
    private static final String TOKEN = "multi_request_helper_token";
    private String currenToken = "";

    public void bindToken(HttpSetting httpSetting) {
        if (httpSetting == null) {
            return;
        }
        Map<String, Object> moreParams = httpSetting.getMoreParams();
        String str = System.currentTimeMillis() + "";
        this.currenToken = str;
        moreParams.put(TOKEN, str);
        httpSetting.setMoreParams(moreParams);
    }

    public boolean hasPermission(HttpResponse httpResponse) {
        if (httpResponse == null) {
            return false;
        }
        return this.currenToken.equals((String) httpResponse.getMoreParams().get(TOKEN));
    }
}
