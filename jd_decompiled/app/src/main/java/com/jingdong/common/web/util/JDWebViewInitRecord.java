package com.jingdong.common.web.util;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.sdk.oklog.OKLog;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes12.dex */
public class JDWebViewInitRecord {
    private final long mOriginStartTime;
    private long mPhaseEndTime;
    private long mPhaseStartTime;
    private LinkedHashMap<String, Long> mTimeRecord;

    /* loaded from: classes12.dex */
    public static class RecordHelper {
        public static void markPhaseEnd(JDWebViewInitRecord jDWebViewInitRecord, String str) {
            if (jDWebViewInitRecord == null) {
                return;
            }
            jDWebViewInitRecord.markPhaseEnd(str);
        }
    }

    public JDWebViewInitRecord(long j2) {
        this.mOriginStartTime = j2;
        this.mPhaseStartTime = j2;
        this.mPhaseEndTime = j2;
    }

    private void ensureTimeRecord() {
        if (this.mTimeRecord == null) {
            this.mTimeRecord = new LinkedHashMap<>(6, 1.0f);
        }
    }

    public long getElapsedTime() {
        long j2 = this.mPhaseEndTime;
        long j3 = this.mOriginStartTime;
        if (j2 >= j3) {
            return j2 - j3;
        }
        return 0L;
    }

    public JDJSONObject getRecordJson() {
        LinkedHashMap<String, Long> linkedHashMap = this.mTimeRecord;
        if (linkedHashMap == null || linkedHashMap.isEmpty()) {
            return null;
        }
        JDJSONObject jDJSONObject = new JDJSONObject();
        try {
            jDJSONObject.putAll(this.mTimeRecord);
        } catch (Exception e2) {
            OKLog.e("JDWebViewInitRecord", e2);
        }
        return jDJSONObject;
    }

    public String getRecordJsonString() {
        LinkedHashMap<String, Long> linkedHashMap = this.mTimeRecord;
        if (linkedHashMap != null && !linkedHashMap.isEmpty()) {
            try {
                JDJSONObject jDJSONObject = new JDJSONObject();
                jDJSONObject.putAll(this.mTimeRecord);
                return jDJSONObject.toJSONString();
            } catch (Exception e2) {
                OKLog.e("JDWebViewInitRecord", e2);
            }
        }
        return "";
    }

    public String getRecordJsonStringWithExtra(Map<String, Object> map) {
        LinkedHashMap<String, Long> linkedHashMap = this.mTimeRecord;
        if ((linkedHashMap != null && !linkedHashMap.isEmpty()) || (map != null && !map.isEmpty())) {
            try {
                JDJSONObject jDJSONObject = new JDJSONObject();
                LinkedHashMap<String, Long> linkedHashMap2 = this.mTimeRecord;
                if (linkedHashMap2 != null && !linkedHashMap2.isEmpty()) {
                    jDJSONObject.putAll(this.mTimeRecord);
                }
                if (map != null && !map.isEmpty()) {
                    jDJSONObject.putAll(map);
                }
                return jDJSONObject.toJSONString();
            } catch (Exception e2) {
                OKLog.e("JDWebViewInitRecord", e2);
            }
        }
        return "";
    }

    public JDJSONObject getRecordJsonWithExtra(Map<String, Object> map) {
        LinkedHashMap<String, Long> linkedHashMap = this.mTimeRecord;
        if ((linkedHashMap == null || linkedHashMap.isEmpty()) && (map == null || map.isEmpty())) {
            return null;
        }
        JDJSONObject jDJSONObject = new JDJSONObject();
        try {
            LinkedHashMap<String, Long> linkedHashMap2 = this.mTimeRecord;
            if (linkedHashMap2 != null && !linkedHashMap2.isEmpty()) {
                jDJSONObject.putAll(this.mTimeRecord);
            }
            if (map != null && !map.isEmpty()) {
                jDJSONObject.putAll(map);
            }
        } catch (Exception e2) {
            OKLog.e("JDWebViewInitRecord", e2);
        }
        return jDJSONObject;
    }

    public String getRecordString() {
        LinkedHashMap<String, Long> linkedHashMap = this.mTimeRecord;
        if (linkedHashMap == null || linkedHashMap.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String str : this.mTimeRecord.keySet()) {
            sb.append(str);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(this.mTimeRecord.get(str));
            sb.append("|");
        }
        return sb.toString();
    }

    public Map getTimeRecord() {
        return this.mTimeRecord;
    }

    public void markPhaseEnd(String str) {
        this.mPhaseEndTime = System.currentTimeMillis();
        if (!TextUtils.isEmpty(str)) {
            ensureTimeRecord();
            this.mTimeRecord.put(str, Long.valueOf(this.mPhaseEndTime - this.mPhaseStartTime));
        }
        this.mPhaseStartTime = this.mPhaseEndTime;
    }
}
