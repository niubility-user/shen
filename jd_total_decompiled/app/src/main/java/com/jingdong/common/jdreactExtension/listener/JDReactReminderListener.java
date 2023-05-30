package com.jingdong.common.jdreactExtension.listener;

import com.jingdong.common.entity.JDReminderNewEntity;
import com.jingdong.common.jdreactExtension.modules.JDReactReminderModule;
import com.jingdong.common.utils.JDReminderNewUtils;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class JDReactReminderListener implements JDReactReminderModule.NativeReminderListener {
    @Override // com.jingdong.common.jdreactExtension.modules.JDReactReminderModule.NativeReminderListener
    public ArrayList<JDReminderNewEntity> getAllRemindersByTime(long j2) {
        return JDReminderNewUtils.getAllRemindersAfterTargetTime(j2);
    }

    @Override // com.jingdong.common.jdreactExtension.modules.JDReactReminderModule.NativeReminderListener
    public void removeRemindersEarlierThanDate(long j2) {
        JDReminderNewUtils.deleteRemindersBeforeTargetTime(j2);
    }
}
