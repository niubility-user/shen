package com.jd.lib.babel.servicekit.impl;

import android.content.Context;
import com.jd.lib.babel.servicekit.iservice.IEvent;
import com.jd.lib.babel.servicekit.model.MtaData;
import com.jingdong.jdsdk.mta.JDMtaUtils;

/* loaded from: classes13.dex */
public class EventUtil implements IEvent {
    @Override // com.jd.lib.babel.servicekit.iservice.IEvent
    public void onClickMta(Context context, MtaData mtaData) {
        if (mtaData == null) {
            return;
        }
        JDMtaUtils.sendClickDataWithExt(context, mtaData.getEventId(), mtaData.getEventParam(), mtaData.getEventFunc(), mtaData.getPageId(), mtaData.getPageName(), mtaData.getPageParam(), mtaData.getNextClassName(), mtaData.getJsonParam(), mtaData.getExt());
    }

    @Override // com.jd.lib.babel.servicekit.iservice.IEvent
    public void sendExpo(Context context, MtaData mtaData) {
        if (mtaData == null) {
            return;
        }
        JDMtaUtils.sendExposureData(context, mtaData.getPageName(), "", mtaData.getPageId(), mtaData.getEventId(), mtaData.getEventParam(), "", "", "");
    }
}
