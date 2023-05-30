package com.jingdong.common.entity;

import android.text.TextUtils;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class UserInfoJDBeanEntity implements Serializable {
    private boolean isRealName;
    private boolean needJdBeanRealName;
    private boolean needRedPacketRealName;
    public String plusFlag;
    private String userFlagInfo;

    public String getUserFlagInfo() {
        return TextUtils.isEmpty(this.userFlagInfo) ? "" : this.userFlagInfo;
    }

    public boolean isNeedJdBeanRealName() {
        return this.needJdBeanRealName;
    }

    public boolean isNeedRedPacketRealName() {
        return this.needRedPacketRealName;
    }

    public boolean isRealName() {
        return this.isRealName;
    }

    public void setIsRealName(boolean z) {
        this.isRealName = z;
    }

    public void setNeedJdBeanRealName(boolean z) {
        this.needJdBeanRealName = z;
    }

    public void setNeedRedPacketRealName(boolean z) {
        this.needRedPacketRealName = z;
    }

    public void setUserFlagInfo(String str) {
        this.userFlagInfo = str;
    }
}
