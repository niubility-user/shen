package com.jingdong.manto.network.mantorequests;

import com.jingdong.manto.network.mantorequests.MantoBaseRequest;

/* loaded from: classes16.dex */
public class z extends MantoJDApiRequest {
    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public String getFunctionId() {
        return "jdaRecommend";
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoJDApiRequest, com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public String getHost() {
        return com.jingdong.a.a ? getBetaHost() : super.getHost();
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public MantoBaseRequest.RequestMethod getRequestMethod() {
        return MantoBaseRequest.RequestMethod.GET;
    }
}
