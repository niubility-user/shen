package com.jingdong.common.widget.custom;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.entity.JDReminderNewEntity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.utils.JDReminderNewUtils;
import com.jingdong.jdsdk.constant.JDReminderConstant;
import com.jingdong.sdk.oklog.OKLog;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes12.dex */
public class LiveListReminderUtil {
    private ConcurrentHashMap<String, Boolean> predictItemReminders;

    /* loaded from: classes12.dex */
    private static class SingletonHolder {
        private static final LiveListReminderUtil INSTANCE = new LiveListReminderUtil();

        private SingletonHolder() {
        }
    }

    public static final LiveListReminderUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private String getJumpString(String str) {
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("des", (Object) JumpUtil.VALUE_DES_FIND_LIVE_PRE);
        JDJSONObject jDJSONObject2 = new JDJSONObject();
        jDJSONObject2.put("position", (Object) "0");
        jDJSONObject2.put("id", (Object) (str + ""));
        jDJSONObject.put("params", (Object) jDJSONObject2.toJSONString());
        return jDJSONObject.toJSONString();
    }

    public boolean cancel(String str, long j2) {
        boolean cancelReminder = JDReminderNewUtils.cancelReminder(JDReminderConstant.BUSINESS_TYPE_JDLIVE, str, j2);
        if (cancelReminder) {
            update(str, false);
        } else {
            update(str, true);
        }
        return cancelReminder;
    }

    public boolean check(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (this.predictItemReminders.containsKey(str)) {
            return this.predictItemReminders.get(str).booleanValue();
        }
        update(str, false);
        return false;
    }

    public void destroy() {
        ConcurrentHashMap<String, Boolean> concurrentHashMap = this.predictItemReminders;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
            if (OKLog.D) {
                OKLog.d("HHH", "LiveListReminderUtil --> destroy.");
            }
        }
    }

    public synchronized void init() {
        this.predictItemReminders.clear();
        Iterator<JDReminderNewEntity> it = JDReminderNewUtils.getAllRemindersByBusinessTypeAndTime(JDReminderConstant.BUSINESS_TYPE_JDLIVE, System.currentTimeMillis() - 86400000).iterator();
        while (it.hasNext()) {
            this.predictItemReminders.put(it.next().getIdentificationId(), Boolean.TRUE);
        }
        if (OKLog.D) {
            OKLog.d("HHH", "LiveListReminderUtil --> DB init(), size: " + this.predictItemReminders.size());
        }
    }

    public boolean set(String str, String str2, String str3, long j2) {
        boolean reminder = JDReminderNewUtils.setReminder(new JDReminderNewEntity.ReminderBuilder(JDReminderConstant.BUSINESS_TYPE_JDLIVE, str, str2, str3, j2, getJumpString(str2)).build());
        if (reminder) {
            update(str2, true);
        }
        return reminder;
    }

    public void update(String str, boolean z) {
        this.predictItemReminders.put(str, Boolean.valueOf(z));
    }

    private LiveListReminderUtil() {
        this.predictItemReminders = new ConcurrentHashMap<>();
    }
}
