package com.jd.verify.View;

import com.jd.verify.CallBack;
import com.jd.verify.model.IninVerifyInfo;

/* loaded from: classes18.dex */
public interface IView {
    void checkFinish(int i2, String str);

    void setCurrentType(int i2);

    void setDialg(e eVar);

    void setFinishListener(CallBack callBack);

    void setInfo(IninVerifyInfo ininVerifyInfo);

    void setNotifyListener(com.jd.verify.common.a aVar);

    void setVisibility(int i2);
}
