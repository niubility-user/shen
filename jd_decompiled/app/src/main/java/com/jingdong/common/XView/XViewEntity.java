package com.jingdong.common.XView;

/* loaded from: classes5.dex */
public class XViewEntity {
    @Deprecated
    public boolean enableHybrid;
    public String url;
    public boolean isIntercepted = true;
    public long autoRemoveDelayTime = 0;
    public boolean needAutoDisplay = true;
    public boolean needAutoClose = true;
    public boolean needNavi = false;
    public boolean needCloseButton = true;
    public boolean subPageToWebActivity = false;
    public boolean isFragment = true;
    public int height = 0;
    public int width = 0;

    public String toString() {
        return "XViewEntity{url='" + this.url + "', isIntercepted=" + this.isIntercepted + ", autoRemoveDelayTime=" + this.autoRemoveDelayTime + ", needAutoDisplay=" + this.needAutoDisplay + ", needAutoClose=" + this.needAutoClose + ", needNavi=" + this.needNavi + ", needCloseButton=" + this.needCloseButton + ", subPageToWebActivity=" + this.subPageToWebActivity + ", isFragment=" + this.isFragment + ", height=" + this.height + ", width=" + this.width + '}';
    }
}
