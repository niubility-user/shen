package com.jingdong.common.ntask;

/* loaded from: classes5.dex */
public class NTaskEvent {
    public boolean isSuccess;
    public NTaskModel nTaskModel;

    public NTaskEvent(boolean z, NTaskModel nTaskModel) {
        this.isSuccess = z;
        this.nTaskModel = nTaskModel;
    }
}
