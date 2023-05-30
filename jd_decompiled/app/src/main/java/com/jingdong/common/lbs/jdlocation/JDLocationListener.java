package com.jingdong.common.lbs.jdlocation;

/* loaded from: classes.dex */
public interface JDLocationListener {
    void onFail(JDLocationError jDLocationError);

    void onSuccess(JDLocation jDLocation);
}
