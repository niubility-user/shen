package com.tencent.mapsdk.shell.events;

import androidx.annotation.Keep;
import java.util.HashMap;

@Keep
/* loaded from: classes9.dex */
public class NetFlowEvent extends ReportEvent {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetFlowEvent(NetFlowEventModel netFlowEventModel) {
        super("cm_nf", null);
        netFlowEventModel.getClass();
        HashMap hashMap = new HashMap();
        this.params = hashMap;
        hashMap.put("up", netFlowEventModel.uploadFlow + "");
        this.params.put("dw", netFlowEventModel.downloadFlow + "");
        this.params.put("up_time", netFlowEventModel.uploadTime + "");
        this.params.put("dw_time", netFlowEventModel.downloadTime + "");
        this.params.put("rt", (netFlowEventModel.downloadTime - netFlowEventModel.uploadTime) + "");
        this.params.put("biz_type", netFlowEventModel.bizType);
        this.params.put("url", netFlowEventModel.url);
        this.params.put("err", netFlowEventModel.errorCode + "");
    }
}
