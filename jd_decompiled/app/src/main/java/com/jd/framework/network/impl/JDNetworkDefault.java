package com.jd.framework.network.impl;

import android.content.Context;
import com.android.volley.VolleyLog;
import com.jd.framework.network.JDNetwork;
import com.jd.framework.network.JDRequestQueue;
import com.jd.framework.network.dialing.DNSManager;
import com.jd.framework.network.request.JDRequest;

/* loaded from: classes13.dex */
public class JDNetworkDefault implements JDNetwork {
    private final Context mContext;
    private boolean mNeedVerifySignatureFlag;

    /* loaded from: classes13.dex */
    public static class Builder {
        private DNSManager.IBuildInIPBackUpConfig buildInIPBackUpConfig;
        private Context context;
        private boolean isPrintLog;
        private boolean needVerifySignatureFlag = true;

        private Builder(Context context) {
            this.context = context;
        }

        public static Builder newBuilder(Context context) {
            return new Builder(context);
        }

        public JDNetworkDefault build() {
            return new JDNetworkDefault(this);
        }

        public Builder buildInIPBackUpConfig(DNSManager.IBuildInIPBackUpConfig iBuildInIPBackUpConfig) {
            this.buildInIPBackUpConfig = iBuildInIPBackUpConfig;
            return this;
        }

        public Builder isPrintLog(boolean z) {
            this.isPrintLog = z;
            return this;
        }

        public Builder needVerifySignature(boolean z) {
            this.needVerifySignatureFlag = z;
            return this;
        }
    }

    @Override // com.jd.framework.network.JDNetwork
    public JDRequestQueue addToJDRequestQueue(JDRequest<?> jDRequest) {
        JDRequestQueue newJDRequestQueue = newJDRequestQueue();
        newJDRequestQueue.add(jDRequest);
        return newJDRequestQueue;
    }

    public boolean needVerifySignature() {
        return this.mNeedVerifySignatureFlag;
    }

    @Override // com.jd.framework.network.JDNetwork
    public JDRequestQueue newJDRequestQueue() {
        return new JDRequestQueueDefault(this.mContext);
    }

    public JDNetworkDefault(Context context, boolean z) {
        this.mContext = context;
        VolleyLog.DEBUG = z;
    }

    private JDNetworkDefault(Builder builder) {
        this(builder.context, builder.isPrintLog);
        DNSManager.getInstance().setBuildInIPBackUpConfig(builder.buildInIPBackUpConfig);
        this.mNeedVerifySignatureFlag = builder.needVerifySignatureFlag;
    }
}
