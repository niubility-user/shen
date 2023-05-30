package com.jd.framework.network;

import com.jd.framework.network.filedown.JDFileDownloader;
import com.jd.framework.network.request.JDFileRequest;
import com.jd.framework.network.request.JDRequest;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes13.dex */
public class JDNetworkHelper {
    private static JDNetwork mJDNetwork;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class RequestQueueHolder {
        public static JDRequestQueue mGlobalJDRequestQueue;

        static {
            if (JDNetworkHelper.mJDNetwork != null) {
                mGlobalJDRequestQueue = JDNetworkHelper.mJDNetwork.newJDRequestQueue();
            }
        }

        private RequestQueueHolder() {
        }
    }

    public static <T> void execute(JDRequest<T> jDRequest) {
        if (jDRequest instanceof JDFileRequest) {
            JDFileDownloader.getInstance(JDHttpTookit.getEngine().getApplicationContext()).execute((JDFileRequest) jDRequest);
        } else {
            OKLog.e("JDHttpTookit", "execute\u53ea\u652f\u6301\u6587\u4ef6\u8bf7\u6c42\u7c7b\u578b.");
        }
    }

    public static JDRequestQueue getGlobalJDRequestQueue() {
        return RequestQueueHolder.mGlobalJDRequestQueue;
    }

    public static JDNetwork getJDNetwork() {
        return mJDNetwork;
    }

    public static void setup(JDNetwork jDNetwork) {
        if (mJDNetwork == null) {
            mJDNetwork = jDNetwork;
        }
    }
}
