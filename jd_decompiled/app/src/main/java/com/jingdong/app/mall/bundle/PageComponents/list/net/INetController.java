package com.jingdong.app.mall.bundle.PageComponents.list.net;

import com.jingdong.app.mall.bundle.PageComponents.list.inter.IGeneralData;

/* loaded from: classes19.dex */
public interface INetController extends IGeneralData {
    void bottomIncrease(IFinalDataCallBack iFinalDataCallBack);

    void firstFetch(IFinalDataCallBack iFinalDataCallBack);

    void fullLoad(IFinalDataCallBack iFinalDataCallBack);

    void topIncrease(IFinalDataCallBack iFinalDataCallBack);
}
