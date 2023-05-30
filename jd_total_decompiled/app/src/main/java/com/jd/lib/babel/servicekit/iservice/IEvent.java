package com.jd.lib.babel.servicekit.iservice;

import android.content.Context;
import com.jd.lib.babel.servicekit.model.MtaData;

/* loaded from: classes13.dex */
public interface IEvent {
    void onClickMta(Context context, MtaData mtaData);

    void sendExpo(Context context, MtaData mtaData);
}
