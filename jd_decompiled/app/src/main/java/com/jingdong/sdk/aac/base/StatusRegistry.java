package com.jingdong.sdk.aac.base;

import android.text.TextUtils;
import com.jingdong.sdk.aac.util.SyncEventBus;

/* loaded from: classes7.dex */
public class StatusRegistry implements SyncEventBus.EventBusListener {
    private String actionName;
    private boolean isValid = true;
    private EventListener mListener;

    public StatusRegistry(String str, String str2, EventListener eventListener) {
        this.actionName = "";
        checkParams(str, str2);
        this.actionName = str2;
        this.mListener = eventListener;
        SyncEventBus.getInstances(str).register(this);
    }

    private void checkParams(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new NullPointerException("managerKey = " + str + " or actionName = " + str2 + " can not be null!");
        }
    }

    @Override // com.jingdong.sdk.aac.util.SyncEventBus.EventBusListener
    public String getActionName() {
        return this.actionName;
    }

    @Override // com.jingdong.sdk.aac.util.SyncEventBus.EventBusListener
    public Object getData(String str) {
        return null;
    }

    @Override // com.jingdong.sdk.aac.util.SyncEventBus.EventBusListener
    public boolean isValid() {
        return this.isValid;
    }

    public void onDestroy() {
        setValid(false);
        this.mListener = null;
    }

    @Override // com.jingdong.sdk.aac.util.SyncEventBus.EventBusListener
    public void onEvent(String str, Object obj) {
        EventListener eventListener = this.mListener;
        if (eventListener != null) {
            eventListener.onEvent(str, obj);
        }
    }

    public void setValid(boolean z) {
        this.isValid = z;
    }
}
