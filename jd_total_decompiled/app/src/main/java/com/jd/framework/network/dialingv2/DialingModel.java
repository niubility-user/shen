package com.jd.framework.network.dialingv2;

import android.text.TextUtils;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.android.volley.utils.TimeUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;

/* loaded from: classes13.dex */
public class DialingModel {
    public String ipAddress;
    public boolean isIPv6;
    public long time;
    public String ttl;
    public String updateTime;
    public int port = 443;
    public Source from = Source.SOURCE_UNKNOWN;

    /* loaded from: classes13.dex */
    public enum Source {
        SOURCE_FROM_LOCAL_DNS,
        SOURCE_FROM_HTTPDNS_MASTER,
        SOURCE_FROM_HTTPDNS_BACKUP,
        SOURCE_FROM_BUILD_IN,
        SOURCE_UNKNOWN
    }

    public static DialingModel newInstance() {
        return new DialingModel();
    }

    public boolean isExpire() {
        if (TextUtils.isEmpty(this.updateTime)) {
            return false;
        }
        long distanceMins = TimeUtils.getDistanceMins(this.updateTime, TimeUtils.getCurrentTime());
        return distanceMins >= 0 && !TextUtils.isEmpty(this.ttl) && ((float) distanceMins) > ((float) Integer.parseInt(this.ttl)) * 0.75f;
    }

    public String toString() {
        try {
            return JDJSON.toJSONString((JDJSONObject) JDJSON.toJSON(this), SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
        } catch (Throwable th) {
            th.printStackTrace();
            return super.toString();
        }
    }
}
