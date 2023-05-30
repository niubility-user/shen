package com.jd.cashier.app.jdlibcutter.impl.notification;

import com.jd.cashier.app.jdlibcutter.protocol.notification.INotification;
import com.jingdong.common.messagepop.JDMessagePopManager;

/* loaded from: classes13.dex */
public class JDNotificationImpl implements INotification {
    @Override // com.jd.cashier.app.jdlibcutter.protocol.notification.INotification
    public void activePopNotification() {
        try {
            JDMessagePopManager.getInstance().activePopView();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.notification.INotification
    public void shieldActivePopNotification() {
        try {
            JDMessagePopManager.getInstance().shieldActivePopView();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
