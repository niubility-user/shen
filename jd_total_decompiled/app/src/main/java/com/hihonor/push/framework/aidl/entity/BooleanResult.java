package com.hihonor.push.framework.aidl.entity;

import com.hihonor.push.framework.aidl.IMessageEntity;
import com.hihonor.push.framework.aidl.annotation.Packed;

/* loaded from: classes12.dex */
public class BooleanResult implements IMessageEntity {
    @Packed
    private boolean status;

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean z) {
        this.status = z;
    }
}
