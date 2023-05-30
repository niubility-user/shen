package com.jd.lib.babel.task.viewkit;

import android.content.Context;
import com.jd.viewkit.thirdinterface.model.JDViewKitEventCallBack;
import com.jd.viewkit.thirdinterface.model.JDViewKitEventModel;

/* loaded from: classes14.dex */
public class JDVKitFloatEventServiceImpl extends JDVKitEventServiceBase {
    @Override // com.jd.lib.babel.task.viewkit.JDVKitEventServiceBase, com.jd.viewkit.thirdinterface.JDViewKitEventService
    public void clickEvent(JDViewKitEventModel jDViewKitEventModel, Context context, JDViewKitEventCallBack jDViewKitEventCallBack) {
        VKEventModelShell obtainEventModelShell = VKEventUtil.obtainEventModelShell(jDViewKitEventModel);
        if (obtainEventModelShell.isValid()) {
            int clickEventType = VKEventUtil.clickEventType(obtainEventModelShell.getDes(), obtainEventModelShell.getJumpParams());
            if (clickEventType == 1) {
                VKEventUtil.clickCallBack(context, obtainEventModelShell, jDViewKitEventCallBack);
            } else if (clickEventType != 2) {
                VKEventUtil.execJump(context, obtainEventModelShell.getJumpEntity(), jDViewKitEventCallBack);
            } else {
                VKEventUtil.clickBackPreviousPage(context, obtainEventModelShell);
            }
        }
    }
}
