package com.jingdong.app.mall.update.entity;

import java.io.Serializable;

/* loaded from: classes4.dex */
public class PositionEntity implements Serializable {
    public boolean myJdSettings;
    public boolean myJdUserSettings;
    public boolean toast;

    public String toString() {
        return "PositionEntity{toast=" + this.toast + ", myJdSettings=" + this.myJdSettings + ", myJdUserSettings=" + this.myJdUserSettings + '}';
    }
}
