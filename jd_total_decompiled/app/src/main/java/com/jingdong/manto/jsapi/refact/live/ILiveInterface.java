package com.jingdong.manto.jsapi.refact.live;

import android.os.Bundle;

/* loaded from: classes15.dex */
public interface ILiveInterface {
    public static final String onLivePlayerEvent = "onLivePlayerEvent";
    public static final String onLivePlayerFullScreenChange = "onLivePlayerFullScreenChange";
    public static final String onLivePlayerNetStatus = "onLivePlayerNetStatus";

    void onLivePlayerEvent(int i2, int i3);

    void onLivePlayerFullScreenChange(int i2, boolean z, String str);

    void onLivePlayerNetStatus(int i2, Bundle bundle);
}
