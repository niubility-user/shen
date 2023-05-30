package com.jingdong.sdk.aac.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes7.dex */
public class MulBaseValue implements IValue {
    private List<BaseLiveData<MulBaseValue>> mLiveDatas;

    private synchronized void checkDatas() {
        if (this.mLiveDatas == null) {
            this.mLiveDatas = Collections.synchronizedList(new ArrayList(2));
        }
    }

    @Override // com.jingdong.sdk.aac.data.IValue
    public void notifyChange() {
        List<BaseLiveData<MulBaseValue>> list = this.mLiveDatas;
        if (list == null || list.isEmpty()) {
            return;
        }
        for (BaseLiveData<MulBaseValue> baseLiveData : this.mLiveDatas) {
            if (baseLiveData != null) {
                baseLiveData.postValue(this);
            }
        }
    }

    @Override // com.jingdong.sdk.aac.data.IValue
    public void onDestroy() {
        removeLiveData();
    }

    @Override // com.jingdong.sdk.aac.data.IValue
    public void removeLiveData() {
        List<BaseLiveData<MulBaseValue>> list = this.mLiveDatas;
        if (list != null) {
            list.clear();
            this.mLiveDatas = null;
        }
    }

    @Override // com.jingdong.sdk.aac.data.IValue
    public void setLiveData(BaseLiveData baseLiveData) {
        if (baseLiveData != null) {
            checkDatas();
            if (this.mLiveDatas.contains(baseLiveData)) {
                return;
            }
            this.mLiveDatas.add(baseLiveData);
        }
    }
}
