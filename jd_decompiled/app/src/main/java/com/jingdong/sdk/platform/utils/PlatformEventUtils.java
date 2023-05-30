package com.jingdong.sdk.platform.utils;

import com.jingdong.sdk.aac.util.SyncEventBus;
import com.jingdong.sdk.platform.base.PlatformEvent;
import com.jingdong.sdk.platform.constant.PlatformActionEvents;
import com.jingdong.sdk.platform.mta.MtaParams;

/* loaded from: classes10.dex */
public class PlatformEventUtils extends PlatformActionEvents {
    public static void sendDialogHideStatus(String str) {
        sendEventToOwner(str, PlatformActionEvents.EVENT_PLATFORM_SDK_DIALOG_HIDE);
    }

    public static void sendDialogShowStatus(String str) {
        sendEventToOwner(str, PlatformActionEvents.EVENT_PLATFORM_SDK_DIALOG_SHOW);
    }

    public static void sendEventToOwner(String str, String str2) {
        SyncEventBus.getInstances(str).post(PlatformActionEvents.ACTION_PLATFORM_SDK_OWNER, str2);
    }

    public static void sendEventToSdk(String str, String str2, Object obj) {
        SyncEventBus.getInstances(str).post(PlatformActionEvents.ACTION_PLATFORM_SDK, str2, obj);
    }

    public static void sendMtaEvent(String str, MtaParams mtaParams) {
        sendEventToOwner(str, PlatformActionEvents.EVENT_PLATFORM_SDK_MTA, mtaParams);
    }

    public static void sendEventToOwner(String str, String str2, Object obj) {
        SyncEventBus.getInstances(str).post(PlatformActionEvents.ACTION_PLATFORM_SDK_OWNER, str2, obj);
    }

    public static void sendEventToSdk(String str, String str2) {
        SyncEventBus.getInstances(str).post(PlatformActionEvents.ACTION_PLATFORM_SDK, str2);
    }

    public static void sendEventToOwner(String str, PlatformEvent platformEvent) {
        SyncEventBus.getInstances(str).post(PlatformActionEvents.ACTION_PLATFORM_SDK_OWNER, PlatformActionEvents.EVENT_PLATFORM_SDK_COMMON_EVENT, platformEvent);
    }

    public static void sendEventToSdk(String str, PlatformEvent platformEvent) {
        SyncEventBus.getInstances(str).post(PlatformActionEvents.ACTION_PLATFORM_SDK, PlatformActionEvents.EVENT_PLATFORM_SDK_COMMON_EVENT, platformEvent);
    }
}
