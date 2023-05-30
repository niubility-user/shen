package com.jingdong.sdk.jdupgrade;

import java.io.Serializable;

/* loaded from: classes7.dex */
public class VersionEntity implements Serializable {
    public String build;
    public String downloadCancel;
    public String downloadConfirm;
    public String downloadText;
    public String downloadTitle;
    public String downloadWlan;
    public String extreme;
    public String fileMd5;
    public String icon;
    public String installCancel;
    public String installConfirm;
    public String installText;
    public String installTitle;
    public boolean isAutoCheck;
    public boolean isAutoDownload;
    public String md5;
    public boolean myJdSettings;
    public boolean myJdUserSettings;
    public String packageList;
    public int requestInterval;
    public long size;
    public int state;
    public String toast;
    public String url;
    public String version;

    public String toString() {
        return "VersionEntity{extreme='" + this.extreme + "', state=" + this.state + ", version='" + this.version + "', build=" + this.build + "', size=" + this.size + ", url='" + this.url + "', md5='" + this.md5 + "', fileMd5='" + this.fileMd5 + "', packageList='" + this.packageList + "', icon='" + this.icon + "', installTitle='" + this.installTitle + "', installText='" + this.installText + "', installConfirm='" + this.installConfirm + "', installCancel='" + this.installCancel + "', downloadTitle='" + this.downloadTitle + "', downloadText='" + this.downloadText + "', downloadConfirm='" + this.downloadConfirm + "', downloadCancel='" + this.downloadCancel + "', downloadWlan='" + this.downloadWlan + "', isAutoDownload=" + this.isAutoDownload + ", toast='" + this.toast + "', myJdSettings=" + this.myJdSettings + ", myJdUserSettings=" + this.myJdUserSettings + ", isAutoCheck=" + this.isAutoCheck + ", requestInterval=" + this.requestInterval + '}';
    }
}
