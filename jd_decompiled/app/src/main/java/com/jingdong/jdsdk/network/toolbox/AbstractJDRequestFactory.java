package com.jingdong.jdsdk.network.toolbox;

import com.jd.framework.network.JDResponseListener;
import com.jd.framework.network.request.JDRequest;

/* loaded from: classes14.dex */
public abstract class AbstractJDRequestFactory {
    public int convertCacheToJDRequestCache(int i2) {
        if (i2 != 0) {
            if (i2 == 1) {
                return 2;
            }
            if (i2 == 2) {
                return 3;
            }
            if (i2 == 3) {
                return 4;
            }
            if (i2 == 4) {
                return 1;
            }
        }
        return 0;
    }

    public JDRequest.Priority convertPriorityToJDRequestPriority(int i2) {
        if (i2 != 500) {
            if (i2 != 1000) {
                if (i2 != 5000) {
                    if (i2 != 10000) {
                        return JDRequest.Priority.LOW;
                    }
                    return JDRequest.Priority.IMMEDIATE;
                }
                return JDRequest.Priority.HIGH;
            }
            return JDRequest.Priority.NORMAL;
        }
        return JDRequest.Priority.LOW;
    }

    public abstract JDRequest createJDRequest(HttpGroup httpGroup, HttpRequest httpRequest, HttpSetting httpSetting, String str);

    public abstract JDResponseListener createResponseListener(HttpGroup httpGroup, HttpSetting httpSetting, HttpRequest httpRequest, JDRequest jDRequest);
}
