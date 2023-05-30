package com.jd.lib.babel.task.viewkit;

import android.content.Context;
import com.jd.lib.babel.servicekit.util.BabelServiceUtils;
import com.jd.viewkit.thirdinterface.JDViewKitEventService;
import com.jd.viewkit.thirdinterface.model.JDViewKitEventCallBack;
import com.jd.viewkit.thirdinterface.model.JDViewKitEventModel;

/* loaded from: classes14.dex */
public abstract class JDVKitEventServiceBase implements JDViewKitEventService {
    @Override // com.jd.viewkit.thirdinterface.JDViewKitEventService
    public void clickEvent(JDViewKitEventModel jDViewKitEventModel, Context context) {
        clickEvent(jDViewKitEventModel, context, null);
    }

    @Override // com.jd.viewkit.thirdinterface.JDViewKitEventService
    public abstract void clickEvent(JDViewKitEventModel jDViewKitEventModel, Context context, JDViewKitEventCallBack jDViewKitEventCallBack);

    @Override // com.jd.viewkit.thirdinterface.JDViewKitEventService
    public String getI18nString() {
        return BabelServiceUtils.getLanguage();
    }

    @Override // com.jd.viewkit.thirdinterface.JDViewKitEventService
    public boolean isLogin() {
        return BabelServiceUtils.hasLogin();
    }
}
