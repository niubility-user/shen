package com.jingdong.app.mall.update.entity;

import java.io.Serializable;

/* loaded from: classes4.dex */
public class VersionEntity implements Serializable {
    public DownloadEntity download;
    public String fileMd5;
    public String icon;
    public InstallEntity install;
    public boolean isAutoCheck;
    public String md5;
    public String newest;
    public String packageList;
    public PositionEntity position;
    public long requestInterval;
    public long size;
    public int state;
    public String toast;
    public String url;
    public String version;

    public String toString() {
        return "VersionEntity{state=" + this.state + ", version='" + this.version + "', size=" + this.size + ", url='" + this.url + "', md5='" + this.md5 + "', fileMd5='" + this.fileMd5 + "', toast='" + this.toast + "', newest='" + this.newest + "', packageList='" + this.packageList + "', icon='" + this.icon + "', install=" + this.install + ", download=" + this.download + ", position=" + this.position + ", isAutoCheck=" + this.isAutoCheck + ", requestInterval=" + this.requestInterval + '}';
    }
}
