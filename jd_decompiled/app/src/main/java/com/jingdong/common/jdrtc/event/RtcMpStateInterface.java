package com.jingdong.common.jdrtc.event;

import com.jingdong.common.jdrtc.bean.RtcMpUserInfo;

/* loaded from: classes5.dex */
public interface RtcMpStateInterface {
    void onRtcCreate(String str);

    void onRtcInvite(String str, RtcMpUserInfo rtcMpUserInfo, boolean z, String str2);
}
