package com.jingdong.common.entity;

/* loaded from: classes5.dex */
public class JDReminderNewEntity {
    private final String businessType;
    private final String extra;
    private final String identificationId;
    private final long insertTime;
    private final String jump;
    private final String more;
    private final long notificationTimeMillis;
    private final String reminderImgUrl;
    private final String reminderShowTag;
    private final String reminderTitle;
    private final long startTimeMillis;

    /* loaded from: classes5.dex */
    public static final class ReminderBuilder {
        private final String businessType;
        private String extra;
        private final String identificationId;
        private long insertTime;
        private final String jump;
        private String more;
        private long notificationTimeMillis;
        private String reminderImgUrl;
        private final String reminderShowTag;
        private final String reminderTitle;
        private final long startTimeMillis;

        public ReminderBuilder(String str, String str2, String str3, String str4, long j2, String str5) {
            this.businessType = str;
            this.reminderShowTag = str2;
            this.identificationId = str3;
            this.reminderTitle = str4;
            this.startTimeMillis = j2;
            this.jump = str5;
        }

        public JDReminderNewEntity build() {
            return new JDReminderNewEntity(this);
        }

        public ReminderBuilder extra(String str) {
            this.extra = str;
            return this;
        }

        public ReminderBuilder insertTime(long j2) {
            this.insertTime = j2;
            return this;
        }

        public ReminderBuilder more(String str) {
            this.more = str;
            return this;
        }

        public ReminderBuilder notificationTimeMillis(long j2) {
            this.notificationTimeMillis = j2;
            return this;
        }

        public ReminderBuilder reminderImgUrl(String str) {
            this.reminderImgUrl = str;
            return this;
        }
    }

    public String getBusinessType() {
        return this.businessType;
    }

    public String getExtra() {
        return this.extra;
    }

    public String getIdentificationId() {
        return this.identificationId;
    }

    public long getInsertTime() {
        return this.insertTime;
    }

    public String getJump() {
        return this.jump;
    }

    public String getMore() {
        return this.more;
    }

    public long getNotificationTimeMillis() {
        return this.notificationTimeMillis;
    }

    public String getReminderImgUrl() {
        return this.reminderImgUrl;
    }

    public String getReminderShowTag() {
        return this.reminderShowTag;
    }

    public String getReminderTitle() {
        return this.reminderTitle;
    }

    public long getStartTimeMillis() {
        return this.startTimeMillis;
    }

    private JDReminderNewEntity(ReminderBuilder reminderBuilder) {
        this.businessType = reminderBuilder.businessType;
        this.reminderShowTag = reminderBuilder.reminderShowTag;
        this.identificationId = reminderBuilder.identificationId;
        this.reminderTitle = reminderBuilder.reminderTitle;
        this.reminderImgUrl = reminderBuilder.reminderImgUrl;
        this.startTimeMillis = reminderBuilder.startTimeMillis;
        this.notificationTimeMillis = reminderBuilder.notificationTimeMillis;
        this.insertTime = reminderBuilder.insertTime;
        this.jump = reminderBuilder.jump;
        this.extra = reminderBuilder.extra;
        this.more = reminderBuilder.more;
    }
}
