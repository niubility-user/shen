package com.jingdong.sdk.eldermode.util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import com.google.gson.Gson;
import com.jdpay.bury.SessionPack;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.utils.text.ScaleModeConstants;
import com.jingdong.common.utils.text.SetTextScaleModeHelper;
import com.jingdong.sdk.bmode.util.JDBModeManager;
import com.jingdong.sdk.eldermode.entity.ElderModePopupRuleInfo;
import com.jingdong.sdk.eldermode.entity.ElderModeResponse;
import com.jingdong.sdk.eldermode.entity.ElderModeResponseOthers;
import com.jingdong.sdk.eldermode.helper.IElderModeHelper;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt__StringNumberConversionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 Q2\u00020\u0001:\u0001QB\u0011\u0012\b\u0010I\u001a\u0004\u0018\u00010H\u00a2\u0006\u0004\bO\u0010PJ\u000f\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\b\u0010\u0007J\u0011\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0002\u00a2\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u000b\u0010\u0007J\u000f\u0010\f\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\f\u0010\u0007J\u000f\u0010\r\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\r\u0010\u0007J\u000f\u0010\u000e\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u0007J\u000f\u0010\u000f\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0007J\u000f\u0010\u0010\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0007J\u000f\u0010\u0011\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0007J\u000f\u0010\u0012\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0007J\u000f\u0010\u0014\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0019\u0010\u0018\u001a\u00020\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0019\u0010\u001c\u001a\u00020\u00022\b\u0010\u001b\u001a\u0004\u0018\u00010\u001aH\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0019\u0010\u001f\u001a\u00020\u001e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001aH\u0002\u00a2\u0006\u0004\b\u001f\u0010 J\r\u0010!\u001a\u00020\u0005\u00a2\u0006\u0004\b!\u0010\u0007J\u0019\u0010$\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020#0\"\u00a2\u0006\u0004\b$\u0010%J!\u0010)\u001a\u00020\u00052\b\u0010'\u001a\u0004\u0018\u00010&2\b\b\u0002\u0010(\u001a\u00020\u0002\u00a2\u0006\u0004\b)\u0010*J\r\u0010+\u001a\u00020\u0013\u00a2\u0006\u0004\b+\u0010\u0015J\u0015\u0010-\u001a\u00020\u00052\u0006\u0010,\u001a\u00020\u0002\u00a2\u0006\u0004\b-\u0010.J\u0019\u00100\u001a\u0004\u0018\u00010&2\b\u0010/\u001a\u0004\u0018\u00010#\u00a2\u0006\u0004\b0\u00101J\u0015\u00102\u001a\u00020\u00052\u0006\u0010,\u001a\u00020\u0002\u00a2\u0006\u0004\b2\u0010.J\u0015\u00103\u001a\u00020\u00052\u0006\u0010,\u001a\u00020\u0002\u00a2\u0006\u0004\b3\u0010.J\u0015\u00104\u001a\u00020\u00052\u0006\u0010,\u001a\u00020\u0002\u00a2\u0006\u0004\b4\u0010.J\r\u00105\u001a\u00020\u0013\u00a2\u0006\u0004\b5\u0010\u0015J\r\u00106\u001a\u00020\u0013\u00a2\u0006\u0004\b6\u0010\u0015J\r\u00107\u001a\u00020\u0005\u00a2\u0006\u0004\b7\u0010\u0007J\r\u00108\u001a\u00020\u0005\u00a2\u0006\u0004\b8\u0010\u0007J\u0017\u0010;\u001a\u00020\u00052\b\u0010:\u001a\u0004\u0018\u000109\u00a2\u0006\u0004\b;\u0010<R$\u0010'\u001a\u0004\u0018\u00010&8\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b'\u0010=\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u001d\u0010G\u001a\u00020B8B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\bC\u0010D\u001a\u0004\bE\u0010FR\u0018\u0010I\u001a\u0004\u0018\u00010H8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bI\u0010JR\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u001a8B@\u0002X\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bK\u0010LR\u0018\u0010M\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bM\u0010N\u00a8\u0006R"}, d2 = {"Lcom/jingdong/sdk/eldermode/util/JDElderModeDataHelper;", "", "", "getOperateType", "()I", "", "degradeToNormalMode", "()V", "saveElderModeServerEnableToCache", "getSwitchModeFromCache", "()Ljava/lang/Integer;", "clearSwitchModeCache", "saveTextSizeModeToCache", "setTextSizeToLargeMode", "setTextSizeToStandardMode", "restoreTextSizeModeFromCache", "saveDarkModeToCache", "closeDarkMode", "restoreDarkModeFromCache", "", "isSystemDarkMode", "()Z", "Lcom/jingdong/sdk/eldermode/entity/ElderModePopupRuleInfo;", "popupRuleInfo", "needShowElderModeDialogByRule", "(Lcom/jingdong/sdk/eldermode/entity/ElderModePopupRuleInfo;)Z", "Landroid/content/Context;", AnnoConst.Constructor_Context, "getSystemSettingEaseMode", "(Landroid/content/Context;)I", "", "getSystemSettingFontScale", "(Landroid/content/Context;)F", "requestMixMode", "", "", "getRequestBodyParamMap", "()Ljava/util/Map;", "Lcom/jingdong/sdk/eldermode/entity/ElderModeResponse;", "response", "operateType", "handleResponse", "(Lcom/jingdong/sdk/eldermode/entity/ElderModeResponse;I)V", "getElderModeServerEnableFromCache", "mode", "saveSwitchModeToCache", "(I)V", "responseStr", "parseElderModeResponse", "(Ljava/lang/String;)Lcom/jingdong/sdk/eldermode/entity/ElderModeResponse;", "initStateByElderMode", "handleLargeSize", "handleDarkMode", "isNeedShowNormalModeDialog", "isNeedShowElderModeDialog", "onShowElderModeDialog", "clear", "Landroid/content/res/Configuration;", "newConfig", "onConfigurationChanged", "(Landroid/content/res/Configuration;)V", "Lcom/jingdong/sdk/eldermode/entity/ElderModeResponse;", "getResponse$eldermodelib", "()Lcom/jingdong/sdk/eldermode/entity/ElderModeResponse;", "setResponse$eldermodelib", "(Lcom/jingdong/sdk/eldermode/entity/ElderModeResponse;)V", "Landroid/os/Handler;", "mainHandler$delegate", "Lkotlin/Lazy;", "getMainHandler", "()Landroid/os/Handler;", "mainHandler", "Lcom/jingdong/sdk/eldermode/helper/IElderModeHelper;", "helper", "Lcom/jingdong/sdk/eldermode/helper/IElderModeHelper;", "getContext", "()Landroid/content/Context;", "systemUiMode", "Ljava/lang/Integer;", "<init>", "(Lcom/jingdong/sdk/eldermode/helper/IElderModeHelper;)V", "Companion", "eldermodelib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final class JDElderModeDataHelper {
    @NotNull
    public static final String APP_SOURCE_JINGDONG = "1";
    private static final String CACHE_KEY_ELDER_DIALOG_LAST_SHOW_TIME = "elder_dialog_last_show_time";
    private static final String CACHE_KEY_ELDER_DIALOG_LAST_VERSION = "elder_dialog_last_version";
    private static final String CACHE_KEY_ELDER_DIALOG_SHOW_TIMES = "elder_dialog_show_times";
    private static final String CACHE_KEY_ELDER_DIFFERENCE_DIALOG_SHOWED = "elder_difference_dialog_showed";
    private static final String CACHE_KEY_ELDER_LAST_DARK_MODE = "elder_last_dark_mode";
    private static final String CACHE_KEY_ELDER_LAST_DARK_MODE_FOLLOW_SYSTEM = "elder_last_dark_mode_follow_system";
    private static final String CACHE_KEY_ELDER_LAST_DARK_MODE_SET = "elder_last_dark_mode_set";
    @NotNull
    public static final String CACHE_KEY_ELDER_LAST_TEXT_SIZE_MODE = "elder_last_text_size_mode";
    private static final String CACHE_KEY_ELDER_REPORT_SWITCH_MODE = "elder_report_switch_mode";
    private static final String CACHE_KEY_ELDER_SERVER_ENABLE = "elder_server_enable";
    private static final String CACHE_KEY_NORMAL_DIFFERENCE_DIALOG_SHOWED = "normal_difference_dialog_showed";
    private static final int CALL_TIME_OUT_DEFAULT = -1;
    @NotNull
    public static final String FUNCTION_ID_USER_CARING_PATTERN = "userCaringPattern";
    private static final int OPERATE_TYPE_QUERY = 1;
    private static final int OPERATE_TYPE_REPORT_AND_QUERY = 2;
    @NotNull
    public static final String SOURCE_CODE_LOGIN = "348";
    private static final String SOURCE_CODE_OTHER = "3";
    private static final String SYSTEM_SETTING_EASE_MODE = "ease_mode";
    private final IElderModeHelper helper;

    /* renamed from: mainHandler$delegate  reason: from kotlin metadata */
    private final Lazy mainHandler;
    @Nullable
    private ElderModeResponse response;
    private Integer systemUiMode;

    public JDElderModeDataHelper(@Nullable IElderModeHelper iElderModeHelper) {
        Lazy lazy;
        this.helper = iElderModeHelper;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<Handler>() { // from class: com.jingdong.sdk.eldermode.util.JDElderModeDataHelper$mainHandler$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final Handler invoke() {
                return new Handler(Looper.getMainLooper());
            }
        });
        this.mainHandler = lazy;
    }

    private final void clearSwitchModeCache() {
        IElderModeHelper iElderModeHelper = this.helper;
        if (iElderModeHelper != null) {
            iElderModeHelper.putString(CACHE_KEY_ELDER_REPORT_SWITCH_MODE, "");
        }
        IElderModeHelper iElderModeHelper2 = this.helper;
        if (iElderModeHelper2 != null) {
            iElderModeHelper2.log("\u6e05\u7a7a\u4e0a\u62a5\u6a21\u5f0f\u7f13\u5b58");
        }
    }

    private final void closeDarkMode() {
        IElderModeHelper iElderModeHelper = this.helper;
        if (iElderModeHelper != null) {
            iElderModeHelper.log("\u5173\u95ed\u6697\u9ed1");
        }
        IElderModeHelper iElderModeHelper2 = this.helper;
        if (iElderModeHelper2 != null) {
            iElderModeHelper2.setDarkModeFollowSystem(false);
        }
        IElderModeHelper iElderModeHelper3 = this.helper;
        if (iElderModeHelper3 != null) {
            iElderModeHelper3.setDarkMode(false);
        }
    }

    private final void degradeToNormalMode() {
        JDElderModeManager jDElderModeManager = JDElderModeManager.INSTANCE;
        if (jDElderModeManager.isElderMode()) {
            IElderModeHelper iElderModeHelper = this.helper;
            if (iElderModeHelper != null) {
                iElderModeHelper.log("\u5f53\u524d\u662f\u957f\u8f88\u7248\uff0c\u964d\u7ea7\u4e3a\u6807\u51c6\u7248");
            }
            JDBModeManager.setModeSwitchSpecialBroadcast$default(JDBModeManager.INSTANCE, false, false, 3, null);
            JDElderModeManager.switchElderModeAndRequest$default(jDElderModeManager, 0, true, false, 4, null);
        } else {
            IElderModeHelper iElderModeHelper2 = this.helper;
            if (iElderModeHelper2 != null) {
                iElderModeHelper2.log("\u5f53\u524d\u5df2\u7ecf\u662f\u6807\u51c6\u7248\uff0c\u65e0\u9700\u964d\u7ea7");
            }
        }
        jDElderModeManager.setServerDegrade$eldermodelib(true);
    }

    private final Context getContext() {
        IElderModeHelper iElderModeHelper = this.helper;
        if (iElderModeHelper != null) {
            return iElderModeHelper.getContext();
        }
        return null;
    }

    public final Handler getMainHandler() {
        return (Handler) this.mainHandler.getValue();
    }

    private final int getOperateType() {
        IElderModeHelper iElderModeHelper = this.helper;
        if (iElderModeHelper != null) {
            if (iElderModeHelper.hasLogin()) {
                Integer switchModeFromCache = getSwitchModeFromCache();
                if (switchModeFromCache != null) {
                    int intValue = switchModeFromCache.intValue();
                    IElderModeHelper helper = JDElderModeManager.INSTANCE.getHelper();
                    if (helper != null) {
                        helper.log("\u83b7\u53d6\u5230\u7f13\u5b58\u7684\u5207\u6362\u6a21\u5f0f " + intValue + ",\u8fdb\u884c\u4e0a\u62a5");
                    }
                    return 2;
                }
                IElderModeHelper helper2 = JDElderModeManager.INSTANCE.getHelper();
                if (helper2 != null) {
                    helper2.log("\u6ca1\u6709\u7f13\u5b58\u7684\u5207\u6362\u6a21\u5f0f");
                    return 1;
                }
                return 1;
            }
            IElderModeHelper iElderModeHelper2 = this.helper;
            if (iElderModeHelper2 != null) {
                iElderModeHelper2.log("\u672a\u767b\u5f55\uff0c\u4e0d\u68c0\u67e5\u5207\u6362\u6a21\u5f0f");
                return 1;
            }
            return 1;
        }
        return 1;
    }

    private final Integer getSwitchModeFromCache() {
        String string;
        Integer intOrNull;
        IElderModeHelper iElderModeHelper = this.helper;
        if (iElderModeHelper == null || (string = iElderModeHelper.getString(CACHE_KEY_ELDER_REPORT_SWITCH_MODE)) == null) {
            return null;
        }
        intOrNull = StringsKt__StringNumberConversionsKt.toIntOrNull(string);
        return intOrNull;
    }

    private final int getSystemSettingEaseMode(Context r3) {
        if (r3 != null) {
            try {
                return Settings.System.getInt(r3.getContentResolver(), SYSTEM_SETTING_EASE_MODE, 0);
            } catch (Exception e2) {
                IElderModeHelper iElderModeHelper = this.helper;
                if (iElderModeHelper != null) {
                    iElderModeHelper.handleException(e2);
                    return 0;
                }
                return 0;
            }
        }
        return 0;
    }

    private final float getSystemSettingFontScale(Context r3) {
        if (r3 != null) {
            try {
                return Settings.System.getFloat(r3.getContentResolver(), "font_scale", 1.0f);
            } catch (Exception e2) {
                IElderModeHelper iElderModeHelper = this.helper;
                if (iElderModeHelper != null) {
                    iElderModeHelper.handleException(e2);
                    return 1.0f;
                }
                return 1.0f;
            }
        }
        return 1.0f;
    }

    public static /* synthetic */ void handleResponse$default(JDElderModeDataHelper jDElderModeDataHelper, ElderModeResponse elderModeResponse, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = jDElderModeDataHelper.getOperateType();
        }
        jDElderModeDataHelper.handleResponse(elderModeResponse, i2);
    }

    private final boolean isSystemDarkMode() {
        Resources resources;
        Configuration configuration;
        Integer num = this.systemUiMode;
        if (num == null) {
            Context context = getContext();
            num = (context == null || (resources = context.getResources()) == null || (configuration = resources.getConfiguration()) == null) ? null : Integer.valueOf(configuration.uiMode);
        }
        return num != null && (num.intValue() & 48) == 32;
    }

    private final boolean needShowElderModeDialogByRule(ElderModePopupRuleInfo popupRuleInfo) {
        if (popupRuleInfo != null) {
            IElderModeHelper iElderModeHelper = this.helper;
            long j2 = iElderModeHelper != null ? iElderModeHelper.getLong(CACHE_KEY_ELDER_DIALOG_LAST_VERSION) : 0L;
            Integer periodId = popupRuleInfo.getPeriodId();
            long intValue = periodId != null ? periodId.intValue() : 0;
            if (intValue > j2) {
                IElderModeHelper iElderModeHelper2 = this.helper;
                if (iElderModeHelper2 != null) {
                    iElderModeHelper2.log("\u89c4\u5219 id \u53d8\u66f4\uff0c\u6e05\u7a7a\u663e\u793a\u6b21\u6570");
                }
                IElderModeHelper iElderModeHelper3 = this.helper;
                if (iElderModeHelper3 != null) {
                    iElderModeHelper3.putLong(CACHE_KEY_ELDER_DIALOG_SHOW_TIMES, 0L);
                }
                IElderModeHelper iElderModeHelper4 = this.helper;
                if (iElderModeHelper4 != null) {
                    iElderModeHelper4.log("\u8bb0\u5f55\u89c4\u5219 id");
                }
                IElderModeHelper iElderModeHelper5 = this.helper;
                if (iElderModeHelper5 != null) {
                    iElderModeHelper5.putLong(CACHE_KEY_ELDER_DIALOG_LAST_VERSION, intValue);
                }
            }
            IElderModeHelper iElderModeHelper6 = this.helper;
            long j3 = iElderModeHelper6 != null ? iElderModeHelper6.getLong(CACHE_KEY_ELDER_DIALOG_SHOW_TIMES) : 0L;
            if (j3 >= (popupRuleInfo.getTotalTimes() != null ? r5.intValue() : 0)) {
                IElderModeHelper iElderModeHelper7 = this.helper;
                if (iElderModeHelper7 != null) {
                    iElderModeHelper7.log("\u5f53\u524d\u5df2\u663e\u793a " + j3 + " \u6b21\uff0c\u8d85\u51fa\u89c4\u5219\u7684 " + popupRuleInfo.getTotalTimes() + " \u6b21\uff0c\u8fd4\u56de false");
                }
                return false;
            }
            IElderModeHelper iElderModeHelper8 = this.helper;
            long j4 = iElderModeHelper8 != null ? iElderModeHelper8.getLong(CACHE_KEY_ELDER_DIALOG_LAST_SHOW_TIME) : 0L;
            long currentTimeMillis = System.currentTimeMillis() - j4;
            long intValue2 = (popupRuleInfo.getIntervalTime() != null ? r11.intValue() : 0) * 1000;
            if (currentTimeMillis <= intValue2) {
                IElderModeHelper iElderModeHelper9 = this.helper;
                if (iElderModeHelper9 != null) {
                    iElderModeHelper9.log("\u4e0a\u6b21\u663e\u793a\u65f6\u95f4\u4e3a " + j4 + ",\u65f6\u95f4\u95f4\u9694\u4e3a " + currentTimeMillis + ",\u5c0f\u4e8e\u89c4\u5219\u7684 " + intValue2 + ",\u8fd4\u56de false");
                }
                return false;
            }
            IElderModeHelper iElderModeHelper10 = this.helper;
            if (iElderModeHelper10 != null) {
                iElderModeHelper10.log("\u4e0a\u6b21\u663e\u793a\u65f6\u95f4\u4e3a " + j4 + ",\u65f6\u95f4\u95f4\u9694\u4e3a " + currentTimeMillis + ",\u5927\u4e8e\u89c4\u5219\u7684 " + intValue2 + ",\u8fd4\u56de true");
                return true;
            }
            return true;
        }
        return false;
    }

    private final void restoreDarkModeFromCache() {
        Resources resources;
        Configuration configuration;
        IElderModeHelper iElderModeHelper = this.helper;
        if (iElderModeHelper != null) {
            if (iElderModeHelper.getString(CACHE_KEY_ELDER_LAST_DARK_MODE_SET).length() == 0) {
                this.helper.log("\u6ca1\u6709\u7f13\u5b58\u7684\u6697\u9ed1\u6a21\u5f0f\uff0c\u65e0\u9700\u8fd8\u539f");
                return;
            }
            this.helper.putString(CACHE_KEY_ELDER_LAST_DARK_MODE_SET, "");
            boolean z = this.helper.getInt(CACHE_KEY_ELDER_LAST_DARK_MODE_FOLLOW_SYSTEM) == 1;
            this.helper.setDarkModeFollowSystem(z);
            this.helper.log("\u8fd8\u539f\u6697\u9ed1\u6a21\u5f0f\u8ddf\u968f\u7cfb\u7edf\u4e3a\u3010" + z + '\u3011');
            if (z) {
                IElderModeHelper iElderModeHelper2 = this.helper;
                StringBuilder sb = new StringBuilder();
                sb.append("\u5f53\u524d\u7f13\u5b58\u7684\u7cfb\u7edf\u6a21\u5f0f\u4e3a ");
                sb.append(this.systemUiMode);
                sb.append(",context \u7684\u6a21\u5f0f\u4e3a ");
                Context context = getContext();
                sb.append((context == null || (resources = context.getResources()) == null || (configuration = resources.getConfiguration()) == null) ? null : Integer.valueOf(configuration.uiMode));
                iElderModeHelper2.log(sb.toString());
                boolean isSystemDarkMode = isSystemDarkMode();
                this.helper.setDarkMode(isSystemDarkMode);
                this.helper.log("\u8fd8\u539f\u6697\u9ed1\u6a21\u5f0f\u4e3a\u3010" + isSystemDarkMode + "\u3011\uff0c\u8ddf\u968f\u7cfb\u7edf");
                return;
            }
            boolean z2 = this.helper.getInt(CACHE_KEY_ELDER_LAST_DARK_MODE) == 1;
            this.helper.setDarkMode(z2);
            this.helper.log("\u8fd8\u539f\u6697\u9ed1\u6a21\u5f0f\u4e3a\u7f13\u5b58\u7684\u3010" + z2 + "\u3011\uff0c\u672a\u8ddf\u968f\u7cfb\u7edf");
        }
    }

    private final void restoreTextSizeModeFromCache() {
        String str;
        IElderModeHelper iElderModeHelper = this.helper;
        if (iElderModeHelper == null || (str = iElderModeHelper.getString(CACHE_KEY_ELDER_LAST_TEXT_SIZE_MODE)) == null) {
            str = "";
        }
        if (str.length() > 0) {
            IElderModeHelper iElderModeHelper2 = this.helper;
            if (iElderModeHelper2 != null) {
                iElderModeHelper2.log("\u8fd8\u539f\u7f13\u5b58\u7684\u5b57\u53f7\u6a21\u5f0f \u3010" + str + '\u3011');
            }
            IElderModeHelper iElderModeHelper3 = this.helper;
            if (iElderModeHelper3 != null) {
                iElderModeHelper3.setTextSizeScaleMode(str);
            }
            IElderModeHelper iElderModeHelper4 = this.helper;
            if (iElderModeHelper4 != null) {
                iElderModeHelper4.putString(CACHE_KEY_ELDER_LAST_TEXT_SIZE_MODE, "");
                return;
            }
            return;
        }
        IElderModeHelper iElderModeHelper5 = this.helper;
        if (iElderModeHelper5 != null) {
            iElderModeHelper5.log("\u6ca1\u6709\u7f13\u5b58\u7684\u5b57\u53f7\u6a21\u5f0f\uff0c\u65e0\u9700\u8fd8\u539f");
        }
    }

    private final void saveDarkModeToCache() {
        IElderModeHelper iElderModeHelper = this.helper;
        int i2 = 0;
        int i3 = (iElderModeHelper == null || !iElderModeHelper.isDarkMode()) ? 0 : 1;
        IElderModeHelper iElderModeHelper2 = this.helper;
        if (iElderModeHelper2 != null) {
            iElderModeHelper2.putInt(CACHE_KEY_ELDER_LAST_DARK_MODE, i3);
        }
        IElderModeHelper iElderModeHelper3 = this.helper;
        if (iElderModeHelper3 != null && iElderModeHelper3.isDarkModeFollowSystem()) {
            i2 = 1;
        }
        IElderModeHelper iElderModeHelper4 = this.helper;
        if (iElderModeHelper4 != null) {
            iElderModeHelper4.putInt(CACHE_KEY_ELDER_LAST_DARK_MODE_FOLLOW_SYSTEM, i2);
        }
        IElderModeHelper iElderModeHelper5 = this.helper;
        if (iElderModeHelper5 != null) {
            iElderModeHelper5.log("\u7f13\u5b58\u5f53\u524d\u6697\u9ed1\u6a21\u5f0f\u3010" + i3 + ", " + i2 + '\u3011');
        }
        IElderModeHelper iElderModeHelper6 = this.helper;
        if (iElderModeHelper6 != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(i3);
            sb.append('-');
            sb.append(i2);
            iElderModeHelper6.putString(CACHE_KEY_ELDER_LAST_DARK_MODE_SET, sb.toString());
        }
    }

    private final void saveElderModeServerEnableToCache() {
        ElderModeResponseOthers others;
        Integer caringSwitch;
        ElderModeResponse elderModeResponse = this.response;
        if (elderModeResponse == null || (others = elderModeResponse.getOthers()) == null || (caringSwitch = others.getCaringSwitch()) == null) {
            return;
        }
        int intValue = caringSwitch.intValue();
        IElderModeHelper iElderModeHelper = this.helper;
        if (iElderModeHelper != null) {
            iElderModeHelper.log("\u8bb0\u5f55\u5207\u91cf\u5f00\u5173\u4e3a " + intValue);
        }
        IElderModeHelper iElderModeHelper2 = this.helper;
        if (iElderModeHelper2 != null) {
            iElderModeHelper2.putString(CACHE_KEY_ELDER_SERVER_ENABLE, String.valueOf(intValue));
        }
    }

    private final void saveTextSizeModeToCache() {
        String str;
        IElderModeHelper iElderModeHelper = this.helper;
        if (iElderModeHelper == null || (str = iElderModeHelper.getTextSizeScaleMode()) == null) {
            str = "";
        }
        IElderModeHelper iElderModeHelper2 = this.helper;
        if (iElderModeHelper2 != null) {
            iElderModeHelper2.putString(CACHE_KEY_ELDER_LAST_TEXT_SIZE_MODE, str);
        }
        IElderModeHelper iElderModeHelper3 = this.helper;
        if (iElderModeHelper3 != null) {
            iElderModeHelper3.log("\u7f13\u5b58\u5f53\u524d\u5b57\u53f7\u6a21\u5f0f\u3010" + str + '\u3011');
        }
    }

    private final void setTextSizeToLargeMode() {
        IElderModeHelper iElderModeHelper = this.helper;
        if (iElderModeHelper != null) {
            iElderModeHelper.log("\u8bbe\u7f6e\u4e3a\u5927\u5b57\u53f7");
        }
        IElderModeHelper iElderModeHelper2 = this.helper;
        if (iElderModeHelper2 != null) {
            iElderModeHelper2.setTextSizeToLargeMode();
        }
    }

    private final void setTextSizeToStandardMode() {
        IElderModeHelper iElderModeHelper = this.helper;
        if (iElderModeHelper != null) {
            iElderModeHelper.log("\u8bbe\u7f6e\u4e3a\u6807\u51c6\u5b57\u53f7");
        }
        SetTextScaleModeHelper.setTextSizeScaleMode(ScaleModeConstants.TEXT_SCALE_MODE_STANDARD);
    }

    public final void clear() {
    }

    public final boolean getElderModeServerEnableFromCache() {
        IElderModeHelper iElderModeHelper = this.helper;
        return Intrinsics.areEqual(iElderModeHelper != null ? iElderModeHelper.getString(CACHE_KEY_ELDER_SERVER_ENABLE, "1") : null, "1");
    }

    @NotNull
    public final Map<String, String> getRequestBodyParamMap() {
        Map<String, String> emptyMap;
        Map<String, String> mutableMapOf;
        if (this.helper != null) {
            Pair[] pairArr = new Pair[4];
            IElderModeHelper helper = JDElderModeManager.INSTANCE.getHelper();
            int i2 = 1;
            pairArr[0] = TuplesKt.to("bigFontSize", (helper == null || !helper.isLargeSizeMode()) ? "0" : "1");
            pairArr[1] = TuplesKt.to("sysBigFontSize", String.valueOf(getSystemSettingFontScale(getContext())));
            pairArr[2] = TuplesKt.to("goldenType", String.valueOf(getSystemSettingEaseMode(getContext())));
            pairArr[3] = TuplesKt.to(SessionPack.KEY_APP_SOURCE, "1");
            mutableMapOf = MapsKt__MapsKt.mutableMapOf(pairArr);
            if (this.helper.hasLogin()) {
                Integer switchModeFromCache = getSwitchModeFromCache();
                if (switchModeFromCache != null) {
                    int intValue = switchModeFromCache.intValue();
                    this.helper.log("\u83b7\u53d6\u5230\u7f13\u5b58\u7684\u5207\u6362\u6a21\u5f0f " + intValue + ",\u8fdb\u884c\u4e0a\u62a5");
                    mutableMapOf.put("openCaringPattern", String.valueOf(intValue));
                    i2 = 2;
                } else {
                    this.helper.log("\u6ca1\u6709\u7f13\u5b58\u7684\u5207\u6362\u6a21\u5f0f");
                }
            } else {
                this.helper.log("\u672a\u767b\u5f55\uff0c\u4e0d\u68c0\u67e5\u5207\u6362\u6a21\u5f0f");
            }
            mutableMapOf.put("operateType", String.valueOf(i2));
            return mutableMapOf;
        }
        emptyMap = MapsKt__MapsKt.emptyMap();
        return emptyMap;
    }

    @Nullable
    /* renamed from: getResponse$eldermodelib  reason: from getter */
    public final ElderModeResponse getResponse() {
        return this.response;
    }

    public final void handleDarkMode(int mode) {
        if (mode != 1 && !Intrinsics.areEqual(String.valueOf(mode), "2")) {
            restoreDarkModeFromCache();
            return;
        }
        saveDarkModeToCache();
        closeDarkMode();
    }

    public final void handleLargeSize(int mode) {
        if (mode == 1) {
            saveTextSizeModeToCache();
            setTextSizeToLargeMode();
        } else if (Intrinsics.areEqual(String.valueOf(mode), "2")) {
            saveTextSizeModeToCache();
            setTextSizeToStandardMode();
        } else {
            restoreTextSizeModeFromCache();
        }
    }

    public final void handleResponse(@Nullable ElderModeResponse response, int operateType) {
        if (response != null) {
            if ((!Intrinsics.areEqual(response.getCode(), "0")) != false) {
                IElderModeHelper iElderModeHelper = this.helper;
                if (iElderModeHelper != null) {
                    iElderModeHelper.log("code \u5931\u8d25");
                    return;
                }
                return;
            }
            this.response = response;
            saveElderModeServerEnableToCache();
            if (operateType == 2) {
                Integer operateResult = response.getOperateResult();
                if (operateResult != null && operateResult.intValue() == 0) {
                    IElderModeHelper iElderModeHelper2 = this.helper;
                    if (iElderModeHelper2 != null) {
                        iElderModeHelper2.log("\u4e0a\u62a5\u6a21\u5f0f\u6210\u529f");
                    }
                    clearSwitchModeCache();
                } else if (operateResult != null && operateResult.intValue() == -1) {
                    IElderModeHelper iElderModeHelper3 = this.helper;
                    if (iElderModeHelper3 != null) {
                        iElderModeHelper3.log("\u4e0a\u62a5\u6a21\u5f0f\u5931\u8d25");
                    }
                } else if (operateResult != null && operateResult.intValue() == 3) {
                    IElderModeHelper iElderModeHelper4 = this.helper;
                    if (iElderModeHelper4 != null) {
                        iElderModeHelper4.log("\u4e0a\u62a5\u6a21\u5f0f\u88ab\u62d2\u7edd");
                    }
                    clearSwitchModeCache();
                }
            } else {
                IElderModeHelper iElderModeHelper5 = this.helper;
                if (iElderModeHelper5 != null) {
                    iElderModeHelper5.log("\u8bf7\u6c42\u957f\u8f88\u7248\u6570\u636e\u5b8c\u6210");
                }
            }
            Integer caringPatternTurn = response.getCaringPatternTurn();
            if (caringPatternTurn != null && caringPatternTurn.intValue() == 3) {
                IElderModeHelper iElderModeHelper6 = this.helper;
                if (iElderModeHelper6 != null) {
                    iElderModeHelper6.log("\u670d\u52a1\u7aef\u4e0b\u53d1\u6a21\u5f0f\u4e3a\u7981\u7528\uff0c\u8fdb\u884c\u964d\u7ea7");
                }
                degradeToNormalMode();
                return;
            }
            return;
        }
        IElderModeHelper iElderModeHelper7 = this.helper;
        if (iElderModeHelper7 != null) {
            iElderModeHelper7.log("response \u4e3a null");
        }
    }

    public final void initStateByElderMode(int mode) {
        IElderModeHelper iElderModeHelper = this.helper;
        if (iElderModeHelper != null) {
            iElderModeHelper.log("\u6839\u636e\u542f\u52a8\u65f6\u7684\u7248\u6a21\u5f0f " + mode + " \u521d\u59cb\u5316\u72b6\u6001");
        }
        if (mode == 1) {
            setTextSizeToLargeMode();
            closeDarkMode();
        } else if (Intrinsics.areEqual(String.valueOf(mode), "2")) {
            setTextSizeToStandardMode();
            closeDarkMode();
        } else {
            restoreTextSizeModeFromCache();
            restoreDarkModeFromCache();
        }
    }

    public final boolean isNeedShowElderModeDialog() {
        ElderModeResponseOthers others;
        String toastInfo;
        IElderModeHelper iElderModeHelper = this.helper;
        if (iElderModeHelper != null) {
            iElderModeHelper.log("\u68c0\u67e5\u662f\u5426\u9700\u8981\u957f\u8f88\u7248\u5f39\u7a97");
        }
        ElderModeResponse elderModeResponse = this.response;
        if (elderModeResponse != null && (others = elderModeResponse.getOthers()) != null && (toastInfo = others.getToastInfo()) != null) {
            if (!(toastInfo.length() > 0)) {
                toastInfo = null;
            }
            if (toastInfo != null) {
                IElderModeHelper iElderModeHelper2 = this.helper;
                if (iElderModeHelper2 != null) {
                    iElderModeHelper2.log("toastInfo \u4e0d\u4e3a\u7a7a\uff0c\u8fd4\u56de false");
                }
                return false;
            }
        }
        JDElderModeManager jDElderModeManager = JDElderModeManager.INSTANCE;
        if (!jDElderModeManager.getElderModeEnable()) {
            IElderModeHelper iElderModeHelper3 = this.helper;
            if (iElderModeHelper3 != null) {
                iElderModeHelper3.log("\u5df2\u964d\u7ea7\uff0c\u8fd4\u56de false");
            }
            return false;
        }
        ElderModeResponse elderModeResponse2 = this.response;
        if (elderModeResponse2 != null) {
            if (elderModeResponse2.getCaringInfo() != null) {
                IElderModeHelper iElderModeHelper4 = this.helper;
                if (iElderModeHelper4 != null) {
                    iElderModeHelper4.log("\u672c\u5730\u6a21\u5f0f\u662f " + jDElderModeManager.isElderMode());
                }
                IElderModeHelper iElderModeHelper5 = this.helper;
                if (iElderModeHelper5 != null) {
                    iElderModeHelper5.log("\u670d\u52a1\u7aef\u6a21\u5f0f\u662f " + elderModeResponse2.getElderMode());
                }
                if (jDElderModeManager.isElderMode()) {
                    IElderModeHelper iElderModeHelper6 = this.helper;
                    if (iElderModeHelper6 != null) {
                        iElderModeHelper6.log("\u672c\u5730\u5df2\u662f\u957f\u8f88\u7248\uff0c\u8fd4\u56de false");
                    }
                    return false;
                }
                Integer elderMode = elderModeResponse2.getElderMode();
                if (elderMode != null && elderMode.intValue() == 1) {
                    IElderModeHelper iElderModeHelper7 = this.helper;
                    if (iElderModeHelper7 != null && iElderModeHelper7.getInt(CACHE_KEY_ELDER_DIFFERENCE_DIALOG_SHOWED) == 1) {
                        this.helper.log("\u672c\u5730\u662f\u6807\u51c6\u7248\uff0c\u670d\u52a1\u7aef\u662f\u957f\u8f88\u7248\uff0c\u867d\u4e0d\u4e00\u81f4\uff0c\u4f46\u5df2\u5f39\u51fa\u8fc7");
                    } else {
                        IElderModeHelper iElderModeHelper8 = this.helper;
                        if (iElderModeHelper8 != null) {
                            iElderModeHelper8.log("\u672c\u5730\u662f\u6807\u51c6\u7248\uff0c\u670d\u52a1\u7aef\u662f\u957f\u8f88\u7248\uff0c\u56e0\u4e0d\u4e00\u81f4\uff0c\u8fd4\u56de true");
                        }
                        IElderModeHelper iElderModeHelper9 = this.helper;
                        if (iElderModeHelper9 != null) {
                            iElderModeHelper9.putInt(CACHE_KEY_ELDER_DIFFERENCE_DIALOG_SHOWED, 1);
                        }
                        return true;
                    }
                }
                Integer potentialElder = elderModeResponse2.getPotentialElder();
                if (potentialElder == null || potentialElder.intValue() != 1) {
                    IElderModeHelper iElderModeHelper10 = this.helper;
                    if (iElderModeHelper10 != null) {
                        iElderModeHelper10.log("\u4e0d\u662f\u9ad8\u6f5c\u7528\u6237\uff0c\u8fd4\u56de false");
                    }
                    return false;
                }
                IElderModeHelper iElderModeHelper11 = this.helper;
                if (iElderModeHelper11 != null) {
                    iElderModeHelper11.log("\u5224\u65ad\u75b2\u52b3\u673a\u5236");
                }
                return needShowElderModeDialogByRule(elderModeResponse2.getPopupRuleInfo());
            }
            IElderModeHelper iElderModeHelper12 = this.helper;
            if (iElderModeHelper12 != null) {
                iElderModeHelper12.log("\u957f\u8f88\u7248\u5f39\u7a97\u4fe1\u606f\u4e3a\u7a7a\uff0c\u8fd4\u56de false");
            }
            return false;
        }
        IElderModeHelper iElderModeHelper13 = this.helper;
        if (iElderModeHelper13 != null) {
            iElderModeHelper13.log("\u54cd\u5e94\u4e3a\u7a7a\uff0c\u8fd4\u56de false");
        }
        return false;
    }

    public final boolean isNeedShowNormalModeDialog() {
        IElderModeHelper iElderModeHelper = this.helper;
        if (iElderModeHelper != null) {
            iElderModeHelper.log("\u68c0\u67e5\u662f\u5426\u9700\u8981\u6807\u51c6\u7248\u5f39\u7a97");
        }
        JDElderModeManager jDElderModeManager = JDElderModeManager.INSTANCE;
        if (!jDElderModeManager.getElderModeEnable()) {
            IElderModeHelper iElderModeHelper2 = this.helper;
            if (iElderModeHelper2 != null) {
                iElderModeHelper2.log("\u5df2\u964d\u7ea7\uff0c\u8fd4\u56de false");
            }
            return false;
        }
        ElderModeResponse elderModeResponse = this.response;
        if (elderModeResponse != null) {
            if (elderModeResponse.getStandardInfo() != null) {
                IElderModeHelper iElderModeHelper3 = this.helper;
                if (iElderModeHelper3 != null) {
                    iElderModeHelper3.log("\u672c\u5730\u6a21\u5f0f\u662f " + jDElderModeManager.isElderMode());
                }
                IElderModeHelper iElderModeHelper4 = this.helper;
                if (iElderModeHelper4 != null) {
                    iElderModeHelper4.log("\u670d\u52a1\u7aef\u6a21\u5f0f\u662f " + elderModeResponse.getElderMode());
                }
                if (!jDElderModeManager.isElderMode()) {
                    IElderModeHelper iElderModeHelper5 = this.helper;
                    if (iElderModeHelper5 != null) {
                        iElderModeHelper5.log("\u672c\u5730\u5df2\u662f\u6807\u51c6\u7248\uff0c\u8fd4\u56de false");
                    }
                    return false;
                }
                Integer elderMode = elderModeResponse.getElderMode();
                if (elderMode != null && elderMode.intValue() == 0) {
                    IElderModeHelper iElderModeHelper6 = this.helper;
                    if (iElderModeHelper6 != null && iElderModeHelper6.getInt(CACHE_KEY_NORMAL_DIFFERENCE_DIALOG_SHOWED) == 1) {
                        this.helper.log("\u672c\u5730\u662f\u957f\u8f88\u7248\uff0c\u670d\u52a1\u7aef\u662f\u6807\u51c6\u7248\uff0c\u4f46\u662f\u5df2\u5f39\u51fa\u8fc7\uff0c\u8fd4\u56de false");
                        return false;
                    }
                    IElderModeHelper iElderModeHelper7 = this.helper;
                    if (iElderModeHelper7 != null) {
                        iElderModeHelper7.log("\u672c\u5730\u662f\u957f\u8f88\u7248\uff0c\u670d\u52a1\u7aef\u662f\u6807\u51c6\u7248\uff0c\u8fd4\u56de true");
                    }
                    IElderModeHelper iElderModeHelper8 = this.helper;
                    if (iElderModeHelper8 != null) {
                        iElderModeHelper8.putInt(CACHE_KEY_NORMAL_DIFFERENCE_DIALOG_SHOWED, 1);
                    }
                    return true;
                }
                IElderModeHelper iElderModeHelper9 = this.helper;
                if (iElderModeHelper9 != null) {
                    iElderModeHelper9.log("\u672c\u5730\u662f\u957f\u8f88\u7248\uff0c\u670d\u52a1\u7aef\u4e0d\u662f\u6807\u51c6\u7248\uff0c\u8fd4\u56de false");
                }
                return false;
            }
            IElderModeHelper iElderModeHelper10 = this.helper;
            if (iElderModeHelper10 != null) {
                iElderModeHelper10.log("\u6807\u51c6\u7248\u5f39\u7a97\u4fe1\u606f\u4e3a\u7a7a\uff0c\u8fd4\u56de false");
            }
            return false;
        }
        IElderModeHelper iElderModeHelper11 = this.helper;
        if (iElderModeHelper11 != null) {
            iElderModeHelper11.log("\u54cd\u5e94\u4e3a\u7a7a\uff0c\u8fd4\u56de false");
        }
        return false;
    }

    public final void onConfigurationChanged(@Nullable Configuration newConfig) {
        if (newConfig != null) {
            this.systemUiMode = Integer.valueOf(newConfig.uiMode);
            IElderModeHelper iElderModeHelper = this.helper;
            if (iElderModeHelper != null) {
                iElderModeHelper.log("\u8bb0\u5f55\u5f53\u524d\u7cfb\u7edf ui \u6a21\u5f0f\u4e3a " + this.systemUiMode);
            }
        }
    }

    public final void onShowElderModeDialog() {
        ElderModePopupRuleInfo popupRuleInfo;
        Integer periodId;
        IElderModeHelper iElderModeHelper = this.helper;
        if (iElderModeHelper != null) {
            ElderModeResponse elderModeResponse = this.response;
            iElderModeHelper.putLong(CACHE_KEY_ELDER_DIALOG_LAST_VERSION, (elderModeResponse == null || (popupRuleInfo = elderModeResponse.getPopupRuleInfo()) == null || (periodId = popupRuleInfo.getPeriodId()) == null) ? 0L : periodId.intValue());
        }
        IElderModeHelper iElderModeHelper2 = this.helper;
        long j2 = iElderModeHelper2 != null ? iElderModeHelper2.getLong(CACHE_KEY_ELDER_DIALOG_SHOW_TIMES) : 0L;
        IElderModeHelper iElderModeHelper3 = this.helper;
        if (iElderModeHelper3 != null) {
            iElderModeHelper3.putLong(CACHE_KEY_ELDER_DIALOG_SHOW_TIMES, j2 + 1);
        }
        IElderModeHelper iElderModeHelper4 = this.helper;
        if (iElderModeHelper4 != null) {
            iElderModeHelper4.log("\u663e\u793a\u5bf9\u8bdd\u6846\uff0c\u8bb0\u5f55\u89c4\u5219id\u3001\u663e\u793a\u6b21\u6570(" + (j2 + 1) + ")\u548c\u65f6\u95f4");
        }
        IElderModeHelper iElderModeHelper5 = this.helper;
        if (iElderModeHelper5 != null) {
            iElderModeHelper5.putLong(CACHE_KEY_ELDER_DIALOG_LAST_SHOW_TIME, System.currentTimeMillis());
        }
    }

    @Nullable
    public final ElderModeResponse parseElderModeResponse(@Nullable String responseStr) {
        ElderModeResponse elderModeResponse = null;
        if (responseStr != null) {
            try {
                return (ElderModeResponse) new Gson().fromJson(responseStr, ElderModeResponse.class);
            } catch (Throwable th) {
                IElderModeHelper iElderModeHelper = this.helper;
                if (iElderModeHelper != null) {
                    iElderModeHelper.handleException(new IllegalArgumentException("fromJson fail first time", th));
                }
                try {
                    elderModeResponse = (ElderModeResponse) new Gson().fromJson(responseStr, ElderModeResponse.class);
                } catch (Throwable th2) {
                    IElderModeHelper iElderModeHelper2 = this.helper;
                    if (iElderModeHelper2 != null) {
                        iElderModeHelper2.handleException(new IllegalArgumentException("fromJson fail second time", th2));
                    }
                }
                return elderModeResponse;
            }
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [T, java.util.Map] */
    /* JADX WARN: Type inference failed for: r2v5, types: [T, java.util.Map] */
    public final void requestMixMode() {
        ?? plus;
        final int i2;
        Map mutableMapOf;
        ?? plus2;
        Integer switchModeFromCache;
        if (this.helper != null) {
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            plus = MapsKt__MapsKt.plus(getRequestBodyParamMap(), JDBModeManager.INSTANCE.getRequestBodyParamMap());
            objectRef.element = plus;
            if (!this.helper.hasLogin() || (switchModeFromCache = getSwitchModeFromCache()) == null) {
                i2 = 1;
            } else {
                switchModeFromCache.intValue();
                i2 = 2;
            }
            mutableMapOf = MapsKt__MapsKt.mutableMapOf(TuplesKt.to("sourceCode", "3"));
            plus2 = MapsKt__MapsKt.plus((Map) objectRef.element, mutableMapOf);
            objectRef.element = plus2;
            this.helper.log("\u5f00\u59cb\u8bf7\u6c42\u6570\u636e, \u8bf7\u6c42\u5165\u53c2\uff1a" + ((Map) objectRef.element));
            this.helper.request(FUNCTION_ID_USER_CARING_PATTERN, (Map) objectRef.element, -1, new Function1<String, Unit>() { // from class: com.jingdong.sdk.eldermode.util.JDElderModeDataHelper$requestMixMode$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(@Nullable final String str) {
                    Handler mainHandler;
                    try {
                        mainHandler = JDElderModeDataHelper.this.getMainHandler();
                        mainHandler.post(new Runnable() { // from class: com.jingdong.sdk.eldermode.util.JDElderModeDataHelper$requestMixMode$1.1
                            @Override // java.lang.Runnable
                            public final void run() {
                                IElderModeHelper iElderModeHelper;
                                iElderModeHelper = JDElderModeDataHelper.this.helper;
                                iElderModeHelper.log("\u8bf7\u6c42\u7ed3\u679c\n" + str);
                                try {
                                    JDElderModeDataHelper jDElderModeDataHelper = JDElderModeDataHelper.this;
                                    jDElderModeDataHelper.handleResponse(jDElderModeDataHelper.parseElderModeResponse(str), i2);
                                    JDBModeManager jDBModeManager = JDBModeManager.INSTANCE;
                                    String str2 = str;
                                    String str3 = (String) ((Map) objectRef.element).get("slimOperateType");
                                    JDBModeManager.handleMajorResponse$default(jDBModeManager, str2, str3 != null ? Integer.valueOf(Integer.parseInt(str3)) : null, false, 4, null);
                                } catch (Throwable th) {
                                    JDElderModeUtils.tryHandleException(th);
                                }
                            }
                        });
                    } catch (Throwable th) {
                        JDElderModeUtils.tryHandleException(th);
                    }
                }
            }, new Function1<Throwable, Unit>() { // from class: com.jingdong.sdk.eldermode.util.JDElderModeDataHelper$requestMixMode$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                    invoke2(th);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(@Nullable Throwable th) {
                }
            });
        }
    }

    public final void saveSwitchModeToCache(int mode) {
        IElderModeHelper iElderModeHelper = this.helper;
        if (iElderModeHelper != null) {
            iElderModeHelper.putString(CACHE_KEY_ELDER_REPORT_SWITCH_MODE, String.valueOf(mode));
        }
        IElderModeHelper iElderModeHelper2 = this.helper;
        if (iElderModeHelper2 != null) {
            iElderModeHelper2.log("\u7f13\u5b58\u5207\u6362\u6a21\u5f0f " + mode);
        }
    }

    public final void setResponse$eldermodelib(@Nullable ElderModeResponse elderModeResponse) {
        this.response = elderModeResponse;
    }
}
