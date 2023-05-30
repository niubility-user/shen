package com.jingdong.common.lbs.jdlocation;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public interface JDLocationInnerListener {
    void onFail(JDLocationError jDLocationError);

    void onSuccess(JDLocation jDLocation);
}
