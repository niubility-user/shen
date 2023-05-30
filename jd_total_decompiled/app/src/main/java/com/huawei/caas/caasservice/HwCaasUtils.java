package com.huawei.caas.caasservice;

/* loaded from: classes12.dex */
public class HwCaasUtils {
    public static final int ABILITY_NOT_SUPPORT = 2001;
    public static final int CAAS_NOT_SUPPORT = 2000;
    public static final int CALLVIEW = 2;
    public static final int CONTACTVIEW = 1;
    public static final int HICALL_NOT_ENABLE = 2004;
    public static final int HIDE = 1;
    public static final int HIDE_CONTACTS = 3;
    public static final int HIDE_NUMBER_TYPE = 1;
    public static final int ILLEGAL_HANDLER_TYPE = 2005;
    public static final int INIT_CAMERA_FAIL = 1000;
    public static final int LANDSCAPE = 0;
    public static final int NORMAL_CALL_TYPE = 3;
    public static final int PERMISSION_FAIL = 2002;
    public static final int POINT_LEFTANDDOWN = 2;
    public static final int POINT_LEFTANDUP = 0;
    public static final int POINT_RIGHTANDDOWN = 3;
    public static final int POINT_RIGHTANDUP = 1;
    public static final int PORTRAIT = 1;
    public static final int SCREEN_SHARING_PAUSE = 4;
    public static final int SCREEN_SHARING_RESUME = 5;
    public static final int SCREEN_SHARING_TYPE = 2;
    public static final int SERVICE_EXCEPTION = 2003;
    public static final int SHOW = 0;
    public static final int SHOW_CONTACTS = 2;
    public static final int STARTVIEW = 0;
    public static final int VIDEOVIEW = 3;
    public static final int VIRTUAL_CAMERA_TYPE = 0;

    /* loaded from: classes12.dex */
    public enum CallAbilityType {
        NORMAL_CALL,
        HIDE_NUMBER
    }

    /* loaded from: classes12.dex */
    public enum CallState {
        INVALID,
        NO_CALL,
        OUTGOING_CALL,
        ACTIVE_CALL
    }

    /* loaded from: classes12.dex */
    public enum CallType {
        VIDEO_CALL,
        VOICE_CALL
    }

    /* loaded from: classes12.dex */
    public enum ContactsType {
        NORMAL_CONTACTS,
        SCREEN_SHARING_CONTACTS
    }

    /* loaded from: classes12.dex */
    public enum ContactsViewStyle {
        FLOAT_VIEW,
        FULL_SCREEN
    }

    private HwCaasUtils() {
    }
}
