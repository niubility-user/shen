package com.jingdong.common.httpdns;

/* loaded from: classes5.dex */
public class IPEntity {
    public static final int TYPE_BUILD_IN = 0;
    public static final int TYPE_HTTPDNS_BACKUP = 2;
    public static final int TYPE_HTTPDNS_MASTER = 1;
    public static final int TYPE_UNKNOWN = -1;
    public boolean isV6;
    public String key;
    public long time;
    public int type = -1;
    public String updateTime;

    public String getType() {
        int i2 = this.type;
        return i2 != 1 ? i2 != 2 ? "\u672a\u77e5" : "\u5907\u9009IP" : "\u4e3bIP";
    }

    public String toString() {
        return "\n IPEntity : {\n    type : " + this.type + "\n    isV6 : " + this.isV6 + "\n    key  : " + this.key + "\n    time : " + this.time + "\n    updateTime : " + this.updateTime + " \n } ";
    }
}
