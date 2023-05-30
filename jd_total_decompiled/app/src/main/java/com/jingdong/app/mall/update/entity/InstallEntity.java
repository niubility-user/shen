package com.jingdong.app.mall.update.entity;

import java.io.Serializable;

/* loaded from: classes4.dex */
public class InstallEntity implements Serializable {
    public String cancel;
    public String confirm;
    public String text;
    public String title;

    public String toString() {
        return "InstallEntity{title='" + this.title + "', text='" + this.text + "', confirm='" + this.confirm + "', cancel='" + this.cancel + "'}";
    }
}
