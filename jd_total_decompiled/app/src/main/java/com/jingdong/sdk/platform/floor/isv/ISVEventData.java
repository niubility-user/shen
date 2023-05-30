package com.jingdong.sdk.platform.floor.isv;

/* loaded from: classes10.dex */
public class ISVEventData {
    Object mData;
    String mEventId;
    Object mExtraData;
    String mManagerKey;

    public ISVEventData(String str, String str2, Object obj, Object obj2) {
        this.mManagerKey = str;
        this.mEventId = str2;
        this.mData = obj;
        this.mExtraData = obj2;
    }

    public ISVEventData(String str, String str2, Object obj) {
        this(str, str2, obj, null);
    }
}
