package com.jingdong.sdk.aac.data;

/* loaded from: classes7.dex */
public interface IValue {
    void notifyChange();

    void onDestroy();

    void removeLiveData();

    void setLiveData(BaseLiveData baseLiveData);
}
