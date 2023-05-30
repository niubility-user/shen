package com.jingdong.common.eldermode;

import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.eldermode.entity.ElderModeMtaInfo;
import com.jingdong.sdk.eldermode.helper.IElderModeMtaSender;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes5.dex */
public class JDElderModeMtaSenderImpl implements IElderModeMtaSender {
    @Override // com.jingdong.sdk.eldermode.helper.IElderModeMtaSender
    public void sendMtaInfo(@Nullable ElderModeMtaInfo elderModeMtaInfo) {
        if (elderModeMtaInfo == null) {
            return;
        }
        if ((elderModeMtaInfo.getMtaType() != null ? elderModeMtaInfo.getMtaType().intValue() : 0) == 1) {
            JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplication(), elderModeMtaInfo.getEventId(), elderModeMtaInfo.getEventParam(), elderModeMtaInfo.getPageId(), elderModeMtaInfo.getPageName(), elderModeMtaInfo.getPageParam(), elderModeMtaInfo.getJsonParam(), null);
        } else {
            JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplication(), elderModeMtaInfo.getEventId(), elderModeMtaInfo.getEventParam(), "", elderModeMtaInfo.getPageId(), elderModeMtaInfo.getPageName(), elderModeMtaInfo.getPageParam(), "", elderModeMtaInfo.getJsonParam(), null);
        }
    }
}
