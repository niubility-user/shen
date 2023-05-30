package com.jingdong.sdk.platform.base;

import android.text.TextUtils;
import com.jingdong.jdsdk.constant.CartConstant;

/* loaded from: classes9.dex */
public class BaseEntity extends UnProguard {
    public String bId;
    public String backgroundColor;
    public boolean listenEdgeChange;
    public Object mData;
    public String mId;
    public String tId;
    public boolean canUseDefaultBgColor = true;
    public boolean isShow = false;
    public boolean isValid = true;
    public boolean wrapHeight = false;

    public BaseEntity(String str) {
        this.mId = str;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof BaseEntity)) {
            return false;
        }
        BaseEntity baseEntity = (BaseEntity) obj;
        if (baseEntity != null) {
            if (TextUtils.equals(baseEntity.mId, this.mId)) {
                return (TextUtils.isEmpty(baseEntity.bId) && TextUtils.isEmpty(this.bId)) || TextUtils.equals(baseEntity.bId, this.bId);
            }
            return false;
        }
        return super.equals(obj);
    }

    public String getMtaParam() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mId);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append((TextUtils.isEmpty(this.bId) || TextUtils.equals(this.bId, this.mId)) ? "-100" : this.bId);
        return sb.toString();
    }

    public BaseEntity(String str, String str2) {
        this.mId = str;
        this.bId = str2;
    }

    public BaseEntity() {
    }
}
