package com.jd.lib.babel.ifloor.ui;

import com.jd.lib.babel.servicekit.model.BaseEvent;
import com.jd.lib.babel.servicekit.model.MtaData;

/* loaded from: classes13.dex */
public interface IFloorUI {
    void notifyDataSetChanged();

    void sendEvent(BaseEvent baseEvent);

    void sendExposureData(MtaData mtaData);
}
