package com.jingdong.common.entity;

import com.jingdong.common.utils.JDReminderUtils;

/* loaded from: classes5.dex */
public class JDReminder {
    public long insertTime;
    public long reminderId;
    public long startTime;
    public String targetURL;
    public String title;
    public JDReminderUtils.Type type;

    public JDReminder(JDReminderUtils.Type type, long j2, String str, long j3, long j4, String str2) {
        this.type = type;
        this.reminderId = j2;
        this.title = str;
        this.startTime = j3;
        this.insertTime = j4;
        this.targetURL = str2;
    }
}
