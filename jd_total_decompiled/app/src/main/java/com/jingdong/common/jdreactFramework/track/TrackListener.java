package com.jingdong.common.jdreactFramework.track;

/* loaded from: classes5.dex */
public interface TrackListener {
    void onLoadFail(int i2, String str);

    void onLoadFinish(String str);

    void onLoadStart(String str);

    void onUpdateNode(int i2, int i3, String str);
}
