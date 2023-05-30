package com.jd.cashier.app.jdlibcutter.impl.ui.push;

import com.jd.cashier.app.jdlibcutter.protocol.ui.push.IPush;
import com.jd.cashier.app.jdlibcutter.protocol.ui.push.SettingPushOpenListener;
import com.jingdong.common.messagepop.JDMessageNoticeManager;
import com.jingdong.common.messagepop.PushOpenListener;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes13.dex */
public class JDPush implements IPush {
    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.push.IPush
    public void setClickOpenButtonListener(final SettingPushOpenListener settingPushOpenListener) {
        try {
            JDMessageNoticeManager.getInstance().isNotificationEnable(new PushOpenListener() { // from class: com.jd.cashier.app.jdlibcutter.impl.ui.push.JDPush.1
                @Override // com.jingdong.common.messagepop.PushOpenListener
                public void pushOpenClicked() {
                    SettingPushOpenListener settingPushOpenListener2 = settingPushOpenListener;
                    if (settingPushOpenListener2 != null) {
                        settingPushOpenListener2.pushOpenClicked();
                    }
                }
            });
        } catch (Exception e2) {
            if (OKLog.E) {
                e2.printStackTrace();
            }
        }
    }
}
