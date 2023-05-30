package com.jingdong.app.mall.update.entity;

import java.io.Serializable;

/* loaded from: classes4.dex */
public class DownloadEntity implements Serializable {
    public String cancel;
    public String confirm;
    public String error;
    public String progress;
    public String text;
    public String title;
    public String wlan;

    public String toString() {
        return "DownloadEntity{title='" + this.title + "', text='" + this.text + "', confirm='" + this.confirm + "', cancel='" + this.cancel + "', progress='" + this.progress + "', error='" + this.error + "', wlan='" + this.wlan + "'}";
    }
}
