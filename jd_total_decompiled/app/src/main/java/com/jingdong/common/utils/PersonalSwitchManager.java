package com.jingdong.common.utils;

import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.utils.personal.JDPersonalSharedPreferencesUtil;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u001f\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b/\u00100J\u000f\u0010\u0003\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tH\u0007\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u000e\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b\u0010\u0010\u0004J\u0017\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b\u0011\u0010\bJ\u001b\u0010\u0014\u001a\u00020\u00062\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0007\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b\u0016\u0010\u0004J\u000f\u0010\u0017\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b\u0017\u0010\u0004J\u0017\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b\u0018\u0010\bJ\u000f\u0010\u0019\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b\u0019\u0010\u0004J\u0017\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b\u001b\u0010\bJ\u000f\u0010\u001c\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b\u001c\u0010\u0004J\u0017\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b\u001e\u0010\bJ\u000f\u0010\u001f\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b\u001f\u0010\u0004J\u0017\u0010 \u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b \u0010\bJ\u000f\u0010!\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b!\u0010\u0004J\u0017\u0010\"\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b\"\u0010\bR\u0016\u0010#\u001a\u00020\t8\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b#\u0010$R\u0016\u0010%\u001a\u00020\t8\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b%\u0010$R\u0016\u0010&\u001a\u00020\t8\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b&\u0010$R\u0016\u0010'\u001a\u00020\t8\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b'\u0010$R\u0016\u0010(\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b(\u0010)R\u0016\u0010*\u001a\u00020\t8\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b*\u0010$R\u0016\u0010+\u001a\u00020\t8\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b+\u0010$R\u0016\u0010,\u001a\u00020\t8\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b,\u0010$R\u0016\u0010-\u001a\u00020\t8\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b-\u0010$R\u0016\u0010.\u001a\u00020\t8\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b.\u0010$\u00a8\u00061"}, d2 = {"Lcom/jingdong/common/utils/PersonalSwitchManager;", "", "", "getAsyncInflateSwitch", "()Z", PersonalSwitchManager.ASYNC_INFLATE_KEY, "", "putAsyncInflateSwitch", "(Z)V", "", "key", "getSwitch", "(Ljava/lang/String;)Z", "value", "putSwitch", "(Ljava/lang/String;Z)V", "homeAsyncInflateIsOpen", "putHomeAsyncInflateSwitch", "", PersonalSwitchManager.FORCE_LOGIN_KEY, "putForceLogin", "(Ljava/lang/Integer;)V", "isForceLogin", "getAsyncInflateFontScale", "putAsyncInflateFontScale", "getPreLoaderSwitch", RemoteMessageConst.Notification.SOUND, "putPreLoaderSwitch", "getCoreAPIFetchSwitch", "result", "putCoreAPIFetchSwitch", "getCoreDataProcessSwitch", "putCoreDataProcessSwitch", "getPreTakeNumberSwitch", "putPreTakeNumberSwitch", "PRE_TAKE_NUMBER", "Ljava/lang/String;", "CORE_API_FETCH", "FONT_SCALE_ASYNC_INFLATE_KEY", "ASYNC_INFLATE_KEY", "forceLoginSwitch", "Z", "HOME_ASYNC_INFLATE_KEY", "FORCE_LOGIN_KEY", "PRE_LOADER", "RX_JAVA_IO_SCHEDULER_KEY", "CORE_DATA_PROCESS", "<init>", "()V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class PersonalSwitchManager {
    @NotNull
    public static final String ASYNC_INFLATE_KEY = "asyncInflate";
    @NotNull
    public static final String CORE_API_FETCH = "coreAPIFetch";
    @NotNull
    public static final String CORE_DATA_PROCESS = "coreDataProcess";
    @NotNull
    public static final String FONT_SCALE_ASYNC_INFLATE_KEY = "fontScaleAsyncInflate";
    @NotNull
    public static final String FORCE_LOGIN_KEY = "forceLogin";
    @NotNull
    public static final String HOME_ASYNC_INFLATE_KEY = "homeAsyncInflate";
    public static final PersonalSwitchManager INSTANCE = new PersonalSwitchManager();
    @NotNull
    public static final String PRE_LOADER = "isPreLoader";
    @NotNull
    public static final String PRE_TAKE_NUMBER = "oneLoginSwitch";
    @NotNull
    public static final String RX_JAVA_IO_SCHEDULER_KEY = "rxJavaIOScheduler";
    private static boolean forceLoginSwitch;

    private PersonalSwitchManager() {
    }

    @JvmStatic
    public static final boolean getAsyncInflateFontScale() {
        return JDPersonalSharedPreferencesUtil.getSharedPreferences().getBoolean(FONT_SCALE_ASYNC_INFLATE_KEY, false);
    }

    @JvmStatic
    public static final boolean getAsyncInflateSwitch() {
        return JDPersonalSharedPreferencesUtil.getSharedPreferences().getBoolean(ASYNC_INFLATE_KEY, false);
    }

    @JvmStatic
    public static final boolean getCoreAPIFetchSwitch() {
        return JDPersonalSharedPreferencesUtil.getSharedPreferences().getBoolean(CORE_API_FETCH, false);
    }

    @JvmStatic
    public static final boolean getCoreDataProcessSwitch() {
        return JDPersonalSharedPreferencesUtil.getSharedPreferences().getBoolean(CORE_DATA_PROCESS, false);
    }

    @JvmStatic
    public static final boolean getPreLoaderSwitch() {
        return JDPersonalSharedPreferencesUtil.getSharedPreferences().getBoolean(PRE_LOADER, false);
    }

    @JvmStatic
    public static final boolean getPreTakeNumberSwitch() {
        return JDPersonalSharedPreferencesUtil.getSharedPreferences().getBoolean(PRE_TAKE_NUMBER, false);
    }

    @JvmStatic
    public static final boolean getSwitch(@NotNull String key) {
        return JDPersonalSharedPreferencesUtil.getSharedPreferences().getBoolean(key, false);
    }

    @JvmStatic
    public static final boolean homeAsyncInflateIsOpen() {
        return JDPersonalSharedPreferencesUtil.getSharedPreferences().getBoolean(HOME_ASYNC_INFLATE_KEY, false);
    }

    @JvmStatic
    public static final boolean isForceLogin() {
        return forceLoginSwitch;
    }

    @JvmStatic
    public static final void putAsyncInflateFontScale(boolean asyncInflate) {
        JDPersonalSharedPreferencesUtil.getSharedPreferences().edit().putBoolean(FONT_SCALE_ASYNC_INFLATE_KEY, asyncInflate).apply();
    }

    @JvmStatic
    public static final void putAsyncInflateSwitch(boolean asyncInflate) {
        JDPersonalSharedPreferencesUtil.getSharedPreferences().edit().putBoolean(ASYNC_INFLATE_KEY, asyncInflate).apply();
    }

    @JvmStatic
    public static final void putCoreAPIFetchSwitch(boolean result) {
        JDPersonalSharedPreferencesUtil.getSharedPreferences().edit().putBoolean(CORE_API_FETCH, result).apply();
    }

    @JvmStatic
    public static final void putCoreDataProcessSwitch(boolean result) {
        JDPersonalSharedPreferencesUtil.getSharedPreferences().edit().putBoolean(CORE_DATA_PROCESS, result).apply();
    }

    @JvmStatic
    public static final void putForceLogin(@Nullable Integer forceLogin) {
        forceLoginSwitch = forceLogin != null && forceLogin.intValue() == 1;
    }

    public static /* synthetic */ void putForceLogin$default(Integer num, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            num = 0;
        }
        putForceLogin(num);
    }

    @JvmStatic
    public static final void putHomeAsyncInflateSwitch(boolean asyncInflate) {
        JDPersonalSharedPreferencesUtil.getSharedPreferences().edit().putBoolean(HOME_ASYNC_INFLATE_KEY, asyncInflate).apply();
    }

    @JvmStatic
    public static final void putPreLoaderSwitch(boolean sound) {
        JDPersonalSharedPreferencesUtil.getSharedPreferences().edit().putBoolean(PRE_LOADER, sound).apply();
    }

    @JvmStatic
    public static final void putPreTakeNumberSwitch(boolean result) {
        JDPersonalSharedPreferencesUtil.getSharedPreferences().edit().putBoolean(PRE_TAKE_NUMBER, result).apply();
    }

    @JvmStatic
    public static final void putSwitch(@NotNull String key, boolean value) {
        JDPersonalSharedPreferencesUtil.getSharedPreferences().edit().putBoolean(key, value).apply();
    }
}
