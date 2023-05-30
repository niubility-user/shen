package com.jingdong.common.widget.custom.livewidget.bean;

import android.text.TextUtils;
import java.io.Serializable;
import java.util.HashMap;

/* loaded from: classes12.dex */
public class VideoPerfEntity implements Serializable {
    public static final String BUSINESS_TYPE = "businessType";
    public static final String FIELD_CH_ID = "chId";
    public static final String FIELD_DURATION = "duration";
    public static final String FIELD_END_TIME = "endTime";
    public static final String FIELD_ERRCODE = "errCode";
    public static final String FIELD_OCCUR_TIME = "occurTime";
    public static final String FIELD_PRV = "prv";
    public static final String FIELD_ROOM_ID = "roomId";
    public static final String FIELD_SOURCE_TYPE = "souceType";
    public static final String FIELD_SPEED = "speed";
    public static final String FIELD_START_TIME = "startTime";
    public static final String FIELD_STATUS = "status";
    public static final String FIELD_STREAM_IP = "streamIp";
    public static final String FIELD_TOTAL = "total";
    public static final String FIELD_TYPE_ID = "typeId";
    private static final long serialVersionUID = -4922364696259457330L;
    private HashMap<String, String> reportData = new HashMap<>();

    public void clear() {
        HashMap<String, String> hashMap = this.reportData;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public boolean containsKey(String str) {
        HashMap<String, String> hashMap = this.reportData;
        if (hashMap != null) {
            return hashMap.containsKey(str);
        }
        return false;
    }

    public HashMap<String, String> getReportData() {
        return this.reportData;
    }

    public void put(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (this.reportData == null) {
            this.reportData = new HashMap<>();
        }
        this.reportData.put(str, str2);
    }

    public void put(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (this.reportData == null) {
            this.reportData = new HashMap<>();
        }
        this.reportData.put(str, String.valueOf(i2));
    }

    public void put(String str, long j2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (this.reportData == null) {
            this.reportData = new HashMap<>();
        }
        this.reportData.put(str, String.valueOf(j2));
    }
}
