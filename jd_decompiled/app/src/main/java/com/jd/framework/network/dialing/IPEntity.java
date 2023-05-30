package com.jd.framework.network.dialing;

/* loaded from: classes13.dex */
public class IPEntity {
    public static final int TYPE_BUILD_IN = 0;
    public static final int TYPE_HTTPDNS = 1;
    public static final int TYPE_HTTPDNS_BACKUP = 2;
    public static final int TYPE_UNKNOWN = -1;
    public boolean isV6;
    public String key;
    public long time;
    public int type = -1;
    public String updateTime;

    public String toString() {
        return "\n IPEntity : {\n    type : " + this.type + "\n    isV6 : " + this.isV6 + "\n    key  : " + this.key + "\n    time : " + this.time + "\n    updateTime : " + this.updateTime + " \n } ";
    }
}
