package com.jd.viewkit.thirdinterface;

import android.content.Context;
import com.jd.viewkit.thirdinterface.model.JDViewKitEventCallBack;
import com.jd.viewkit.thirdinterface.model.JDViewKitEventModel;

/* loaded from: classes18.dex */
public interface JDViewKitEventService {
    void clickEvent(JDViewKitEventModel jDViewKitEventModel, Context context);

    void clickEvent(JDViewKitEventModel jDViewKitEventModel, Context context, JDViewKitEventCallBack jDViewKitEventCallBack);

    String getI18nString();

    boolean isLogin();
}
