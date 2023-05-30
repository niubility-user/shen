package com.jingdong.sdk.platform.utils;

import com.jingdong.sdk.aac.util.SyncEventBus;
import com.jingdong.sdk.platform.base.PlatformEvent;
import com.jingdong.sdk.platform.constant.PlatformActionEvents;
import com.jingdong.sdk.platform.mta.MtaParams;

/* loaded from: classes10.dex */
public abstract class PlatformEventHandler {
    OwnerEventBusListener mOwnerEventBusListener = new OwnerEventBusListener();

    /* loaded from: classes10.dex */
    class OwnerEventBusListener implements SyncEventBus.EventBusListener {
        OwnerEventBusListener() {
        }

        @Override // com.jingdong.sdk.aac.util.SyncEventBus.EventBusListener
        public String getActionName() {
            return PlatformActionEvents.ACTION_PLATFORM_SDK_OWNER;
        }

        @Override // com.jingdong.sdk.aac.util.SyncEventBus.EventBusListener
        public Object getData(String str) {
            return null;
        }

        @Override // com.jingdong.sdk.aac.util.SyncEventBus.EventBusListener
        public boolean isValid() {
            return true;
        }

        @Override // com.jingdong.sdk.aac.util.SyncEventBus.EventBusListener
        public void onEvent(String str, Object obj) {
            if (PlatformActionEvents.EVENT_PLATFORM_SDK_MTA.equals(str)) {
                if (obj == null || !(obj instanceof MtaParams)) {
                    return;
                }
                PlatformEventHandler.this.onMtaEvent((MtaParams) obj);
            } else if (PlatformActionEvents.EVENT_PLATFORM_SDK_DIALOG_SHOW.equals(str)) {
                PlatformEventHandler.this.onDialogShowed();
            } else if (PlatformActionEvents.EVENT_PLATFORM_SDK_DIALOG_HIDE.equals(str)) {
                PlatformEventHandler.this.onDialogHide();
            } else if (PlatformActionEvents.EVENT_PLATFORM_SDK_COMMON_BUSINESS_CLICK_TYPE_OTHER.equals(str)) {
                PlatformEventHandler.this.onCommonBusinessClickTypeOther(obj);
            } else if (PlatformActionEvents.EVENT_PLATFORM_SDK_COMMON_EVENT.equals(str) && obj != null && (obj instanceof PlatformEvent)) {
                PlatformEventHandler.this.onCommonEvent((PlatformEvent) obj);
            }
        }
    }

    public PlatformEventHandler(String str) {
        SyncEventBus.getInstances(str).register(this.mOwnerEventBusListener);
    }

    public void onCommonBusinessClickTypeOther(Object obj) {
    }

    public void onCommonEvent(PlatformEvent platformEvent) {
    }

    public void onDestroy() {
        this.mOwnerEventBusListener = null;
    }

    public void onDialogHide() {
    }

    public void onDialogShowed() {
    }

    public abstract void onMtaEvent(MtaParams mtaParams);
}
