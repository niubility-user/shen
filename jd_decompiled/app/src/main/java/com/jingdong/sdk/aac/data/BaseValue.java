package com.jingdong.sdk.aac.data;

/* loaded from: classes7.dex */
public class BaseValue implements IValue {
    private BaseLiveData<BaseValue> mLiveData;

    @Override // com.jingdong.sdk.aac.data.IValue
    public synchronized void notifyChange() {
        BaseLiveData<BaseValue> baseLiveData = this.mLiveData;
        if (baseLiveData != null) {
            baseLiveData.postValue(this);
        }
    }

    @Override // com.jingdong.sdk.aac.data.IValue
    public void onDestroy() {
        removeLiveData();
    }

    @Override // com.jingdong.sdk.aac.data.IValue
    public synchronized void removeLiveData() {
        this.mLiveData = null;
    }

    @Override // com.jingdong.sdk.aac.data.IValue
    public synchronized void setLiveData(BaseLiveData baseLiveData) {
        if (this.mLiveData != baseLiveData) {
            this.mLiveData = baseLiveData;
        }
    }
}
