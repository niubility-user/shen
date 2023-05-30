package com.jingdong.sdk.bmode.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.gson.Gson;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.bmode.util.JDBModeCallFromLoginUtil;
import com.jingdong.sdk.eldermode.entity.ElderModeResponse;
import com.jingdong.sdk.eldermode.helper.IElderModeHelper;
import com.jingdong.sdk.eldermode.util.JDElderModeDataHelper;
import com.jingdong.sdk.eldermode.util.JDElderModeManager;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import java.util.HashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rx.Subscriber;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\bv\u0010\u0004J\u000f\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\t\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u000b\u0010\u0007J\u000f\u0010\f\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\f\u0010\u0004J3\u0010\u0011\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J#\u0010\u0013\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0017\u001a\u00020\u00022\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\u0019\u0010\u0007J\u0019\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u001a\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0015\u0010\u001d\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0005\u00a2\u0006\u0004\b\u001d\u0010\nJ\r\u0010\u001e\u001a\u00020\u0005\u00a2\u0006\u0004\b\u001e\u0010\u0007Jc\u0010'\u001a\u00020\u00022\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010\"\u001a\u00020!2>\b\u0002\u0010&\u001a8\u0012\u0004\u0012\u00020\u0015\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050$\u0012\u000e\u0012\f\u0012\b\b\u0000\u0012\u0004\u0018\u00010\u00050%\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u0002\u0018\u00010#\u00a2\u0006\u0004\b'\u0010(J\u000f\u0010*\u001a\u00020\u0002H\u0000\u00a2\u0006\u0004\b)\u0010\u0004J-\u0010-\u001a\u00020\u00022\b\u0010+\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010,\u001a\u0004\u0018\u00010!2\b\b\u0002\u0010\u0010\u001a\u00020\u000e\u00a2\u0006\u0004\b-\u0010.J'\u0010/\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u000e\u00a2\u0006\u0004\b/\u00100J\r\u00101\u001a\u00020\u0005\u00a2\u0006\u0004\b1\u0010\u0007J\u0015\u00102\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0005\u00a2\u0006\u0004\b2\u0010\nJ\r\u00103\u001a\u00020\u0002\u00a2\u0006\u0004\b3\u0010\u0004J\r\u00104\u001a\u00020\u000e\u00a2\u0006\u0004\b4\u00105J\u0017\u00108\u001a\u00020\u00022\b\u00107\u001a\u0004\u0018\u000106\u00a2\u0006\u0004\b8\u00109J\r\u0010:\u001a\u00020\u0005\u00a2\u0006\u0004\b:\u0010\u0007J\u0015\u0010<\u001a\u00020\u00022\u0006\u0010;\u001a\u00020\u0005\u00a2\u0006\u0004\b<\u0010\nJ\r\u0010=\u001a\u00020\u0005\u00a2\u0006\u0004\b=\u0010\u0007J\r\u0010>\u001a\u00020\u0005\u00a2\u0006\u0004\b>\u0010\u0007J\r\u0010?\u001a\u00020\u0005\u00a2\u0006\u0004\b?\u0010\u0007J!\u0010@\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e\u00a2\u0006\u0004\b@\u0010\u0014J\r\u0010A\u001a\u00020\u0002\u00a2\u0006\u0004\bA\u0010\u0004R\u0016\u0010B\u001a\u00020\u00058\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bB\u0010CR\u0016\u0010D\u001a\u00020\u00058\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bD\u0010CR\u0016\u0010E\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bE\u0010CR\u0016\u0010F\u001a\u00020\u00058\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bF\u0010CR\u0016\u0010G\u001a\u00020\u00058\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bG\u0010CR\u001d\u0010M\u001a\u00020H8B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\bI\u0010J\u001a\u0004\bK\u0010LR\u0016\u0010N\u001a\u00020\u00058\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bN\u0010CR\u0018\u0010O\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bO\u0010CR\u0016\u0010P\u001a\u00020\u00058\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bP\u0010CR\u0016\u0010Q\u001a\u00020\u00058\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bQ\u0010CR$\u0010\u0016\u001a\u0004\u0018\u00010\u00158\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0016\u0010R\u001a\u0004\bS\u0010T\"\u0004\bU\u0010\u0018R\u0018\u0010V\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bV\u0010CR\u0016\u0010W\u001a\u00020\u00058\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bW\u0010CR\u0016\u0010X\u001a\u00020\u00058\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bX\u0010CR\u0016\u0010Y\u001a\u00020\u00058\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bY\u0010CR\u001d\u0010^\u001a\u00020Z8B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b[\u0010J\u001a\u0004\b\\\u0010]R\u0016\u0010_\u001a\u00020\u00058\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b_\u0010CR\u0016\u0010`\u001a\u00020\u00058\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b`\u0010CR\u0016\u0010a\u001a\u00020\u00058\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\ba\u0010CR\u0016\u0010b\u001a\u00020\u00058\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bb\u0010CR\u0015\u0010f\u001a\u0004\u0018\u00010c8F@\u0006\u00a2\u0006\u0006\u001a\u0004\bd\u0010eR\u0016\u0010g\u001a\u00020\u00058\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bg\u0010CR\u0016\u0010h\u001a\u00020\u00058\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bh\u0010CR\u0016\u0010i\u001a\u00020\u00058\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bi\u0010CR\u0016\u0010j\u001a\u00020\u00058\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bj\u0010CR\u0016\u0010k\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bk\u0010lR\u0016\u0010m\u001a\u00020\u00058\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bm\u0010CR\u0016\u0010n\u001a\u00020\u00058\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bn\u0010CR\u001d\u0010s\u001a\u00020o8B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\bp\u0010J\u001a\u0004\bq\u0010rR\u0018\u0010t\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bt\u0010CR\u0016\u0010u\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bu\u0010l\u00a8\u0006w"}, d2 = {"Lcom/jingdong/sdk/bmode/util/JDBModeManager;", "", "", "initModeFromCache", "()V", "", "getReportAutoModeFormCache", "()Ljava/lang/String;", "auto", "setLocalAutoMode", "(Ljava/lang/String;)V", "getShieldData", "handleSyncMtaData", "mode", "", "needSkipCurrentModeSwitch", "callFromLogin", "switchModeAndRequest", "(Ljava/lang/String;Ljava/lang/String;ZZ)V", "setModeSwitchBroadcast", "(ZZ)V", "Lcom/jingdong/sdk/eldermode/helper/IElderModeHelper;", "helper", "initialize", "(Lcom/jingdong/sdk/eldermode/helper/IElderModeHelper;)V", "getRequestBodyParamJson", "", "getRequestBodyParamMap", "()Ljava/util/Map;", "setReportAutoMode", "getLocalAutoModeFormCache", "Lcom/jingdong/sdk/bmode/util/RequestModeCallback;", "callBack", "", "delay", "Lkotlin/Function4;", "", "Lrx/Subscriber;", "function", "requestCallFromLogin", "(Lcom/jingdong/sdk/bmode/util/RequestModeCallback;ILkotlin/jvm/functions/Function4;)V", "requestModeWithOffSite$eldermodelib", "requestModeWithOffSite", "json", "operateType", "handleMajorResponse", "(Ljava/lang/String;Ljava/lang/Integer;Z)V", "switchMode", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getCurrentMode", "setCurrentMode", "onDestroy", "needShowNormalModeDialog", "()Z", "Landroid/app/Activity;", "activity", "showNormalModeDialog", "(Landroid/app/Activity;)V", "getAdviseVersion", "version", "setAdviseVersion", "getBuriedExpLabel", "getPopulationType", "getForbiddenVersionSwitch", "setModeSwitchSpecialBroadcast", "setBModeDidClickConfirmButtonBroadcast", "KEY_EXTRA_MAP_PARAM_TO_FINAL", "Ljava/lang/String;", "CACHE_KEY_MODE_BURIED_EXPLABEL", "currentMode", "KEY_EXTRA_MAP_PARAM_BEFORE_LOGIN", "SWITCH_MODE_MANUAL", "Landroid/os/Handler;", "mainHandler$delegate", "Lkotlin/Lazy;", "getMainHandler", "()Landroid/os/Handler;", "mainHandler", "CACHE_KEY_MODE_SWITCH_DIALOG_HAS_SHOW", "reportAutoModeCache", "MODE_ELDER", "CACHE_KEY_MODE_LOCAL_AUTO_MODE", "Lcom/jingdong/sdk/eldermode/helper/IElderModeHelper;", "getHelper", "()Lcom/jingdong/sdk/eldermode/helper/IElderModeHelper;", "setHelper", "localAutoModeCache", "OPERATE_TYPE_QUERY", "ACTION_B_MODE_DID_CLICK_CONFIRM_BUTTON_NOTI", "ACTION_MODE_SWITCH_SPECIAL", "Lcom/jingdong/sdk/bmode/util/JDBModeDataHelper;", "dataHelper$delegate", "getDataHelper", "()Lcom/jingdong/sdk/bmode/util/JDBModeDataHelper;", "dataHelper", "OPERATE_TYPE_REPORT_AND_QUERY", "SWITCH_MODE_AUTO", "MODE_NORMAL", "CACHE_KEY_MODE_ADVISE_VERSION", "Lcom/jingdong/sdk/eldermode/entity/ElderModeResponse;", "getResponse", "()Lcom/jingdong/sdk/eldermode/entity/ElderModeResponse;", "response", "CACHE_KEY_MODE_POPULATION_TYPE", "CACHE_KEY_MODE_REPORT_AUTO_MODE", "ACTION_MODE_SWITCH", "CACHE_KEY_MODE", "initialized", "Z", "MODE_B", "KEY_EXTRA", "Lcom/jingdong/sdk/bmode/util/JDBModeDialogHelper;", "dialogHelper$delegate", "getDialogHelper", "()Lcom/jingdong/sdk/bmode/util/JDBModeDialogHelper;", "dialogHelper", "adviseVersion", "requestModeFirstCalled", "<init>", "eldermodelib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class JDBModeManager {
    @NotNull
    public static final String ACTION_B_MODE_DID_CLICK_CONFIRM_BUTTON_NOTI = "myjd_mode_switch_action_did_click_confirm_button_noti";
    @NotNull
    public static final String ACTION_MODE_SWITCH = "myjd_mode_switch_action";
    @NotNull
    public static final String ACTION_MODE_SWITCH_SPECIAL = "myjd_mode_switch_action_to_final";
    private static final String CACHE_KEY_MODE = "current_mode";
    private static final String CACHE_KEY_MODE_ADVISE_VERSION = "mode_advise_version";
    @NotNull
    public static final String CACHE_KEY_MODE_BURIED_EXPLABEL = "mode_buried_explabel";
    private static final String CACHE_KEY_MODE_LOCAL_AUTO_MODE = "mode_local_auto_mode";
    @NotNull
    public static final String CACHE_KEY_MODE_POPULATION_TYPE = "mode_population_type";
    private static final String CACHE_KEY_MODE_REPORT_AUTO_MODE = "mode_report_auto_mode";
    @NotNull
    public static final String CACHE_KEY_MODE_SWITCH_DIALOG_HAS_SHOW = "mode_switch_dialog_has_show";
    @NotNull
    public static final String KEY_EXTRA = "myjd_mode_switch_extra";
    @NotNull
    public static final String KEY_EXTRA_MAP_PARAM_BEFORE_LOGIN = "isLoginNotiAfter";
    @NotNull
    public static final String KEY_EXTRA_MAP_PARAM_TO_FINAL = "isFinailNoti";
    @NotNull
    public static final String MODE_B = "2";
    @NotNull
    public static final String MODE_ELDER = "1";
    @NotNull
    public static final String MODE_NORMAL = "0";
    private static final String OPERATE_TYPE_QUERY = "1";
    private static final String OPERATE_TYPE_REPORT_AND_QUERY = "2";
    @NotNull
    public static final String SWITCH_MODE_AUTO = "0";
    @NotNull
    public static final String SWITCH_MODE_MANUAL = "1";
    private static String adviseVersion;

    /* renamed from: dataHelper$delegate  reason: from kotlin metadata */
    private static final Lazy dataHelper;

    /* renamed from: dialogHelper$delegate  reason: from kotlin metadata */
    private static final Lazy dialogHelper;
    @Nullable
    private static IElderModeHelper helper;
    private static boolean initialized;
    private static String localAutoModeCache;

    /* renamed from: mainHandler$delegate  reason: from kotlin metadata */
    private static final Lazy mainHandler;
    private static String reportAutoModeCache;
    private static boolean requestModeFirstCalled;
    public static final JDBModeManager INSTANCE = new JDBModeManager();
    private static String currentMode = "0";

    static {
        Lazy lazy;
        Lazy lazy2;
        Lazy lazy3;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<JDBModeDataHelper>() { // from class: com.jingdong.sdk.bmode.util.JDBModeManager$dataHelper$2
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final JDBModeDataHelper invoke() {
                return new JDBModeDataHelper(JDBModeManager.INSTANCE.getHelper());
            }
        });
        dataHelper = lazy;
        lazy2 = LazyKt__LazyJVMKt.lazy(new Function0<Handler>() { // from class: com.jingdong.sdk.bmode.util.JDBModeManager$mainHandler$2
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final Handler invoke() {
                return new Handler(Looper.getMainLooper());
            }
        });
        mainHandler = lazy2;
        lazy3 = LazyKt__LazyJVMKt.lazy(new Function0<JDBModeDialogHelper>() { // from class: com.jingdong.sdk.bmode.util.JDBModeManager$dialogHelper$2
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final JDBModeDialogHelper invoke() {
                return new JDBModeDialogHelper();
            }
        });
        dialogHelper = lazy3;
        requestModeFirstCalled = true;
    }

    private JDBModeManager() {
    }

    private final JDBModeDataHelper getDataHelper() {
        return (JDBModeDataHelper) dataHelper.getValue();
    }

    private final JDBModeDialogHelper getDialogHelper() {
        return (JDBModeDialogHelper) dialogHelper.getValue();
    }

    private final Handler getMainHandler() {
        return (Handler) mainHandler.getValue();
    }

    private final String getReportAutoModeFormCache() {
        if (reportAutoModeCache == null) {
            IElderModeHelper iElderModeHelper = helper;
            reportAutoModeCache = iElderModeHelper != null ? iElderModeHelper.getString(CACHE_KEY_MODE_REPORT_AUTO_MODE) : null;
        }
        String str = reportAutoModeCache;
        return str != null ? str : "";
    }

    private final String getShieldData() {
        StringBuilder sb = new StringBuilder();
        sb.append(JDElderModeManager.INSTANCE.isOverseasMode() ? 1 : 0);
        sb.append('-');
        JdSdk jdSdk = JdSdk.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(jdSdk, "JdSdk.getInstance()");
        sb.append(!JDPrivacyHelper.isAcceptPrivacy(jdSdk.getApplicationContext()) ? 1 : 0);
        sb.append('-');
        sb.append(JDElderModeUtils.getElderMode());
        return sb.toString();
    }

    public static /* synthetic */ void handleMajorResponse$default(JDBModeManager jDBModeManager, String str, Integer num, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            num = 1;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        jDBModeManager.handleMajorResponse(str, num, z);
    }

    private final void handleSyncMtaData() {
        JDMtaUtils.setMaIsSparse(getAdviseVersion());
        JDMtaUtils.setMaBGroup(getPopulationType());
        JDMtaUtils.setMaAbTest(getBuriedExpLabel());
    }

    private final void initModeFromCache() {
        String string;
        IElderModeHelper iElderModeHelper = helper;
        String str = (iElderModeHelper == null || iElderModeHelper.getInt(JDElderModeManager.CACHE_KEY_ELDER_MODE) != 1) ? "0" : "1";
        IElderModeHelper iElderModeHelper2 = helper;
        if (iElderModeHelper2 != null && (string = iElderModeHelper2.getString(CACHE_KEY_MODE, str)) != null) {
            str = string;
        }
        setCurrentMode(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void requestCallFromLogin$default(JDBModeManager jDBModeManager, RequestModeCallback requestModeCallback, int i2, Function4 function4, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            requestModeCallback = null;
        }
        if ((i3 & 2) != 0) {
            i2 = 2000;
        }
        if ((i3 & 4) != 0) {
            function4 = new JDBModeCallFromLoginUtil.RequestFunction();
        }
        jDBModeManager.requestCallFromLogin(requestModeCallback, i2, function4);
    }

    private final void setLocalAutoMode(String auto) {
        localAutoModeCache = auto;
        IElderModeHelper iElderModeHelper = helper;
        if (iElderModeHelper != null) {
            iElderModeHelper.putString(CACHE_KEY_MODE_LOCAL_AUTO_MODE, auto);
        }
    }

    private final void setModeSwitchBroadcast(boolean needSkipCurrentModeSwitch, boolean callFromLogin) {
        Intent intent = new Intent();
        intent.setAction(ACTION_MODE_SWITCH);
        HashMap hashMap = new HashMap();
        hashMap.put(KEY_EXTRA_MAP_PARAM_TO_FINAL, needSkipCurrentModeSwitch ? "0" : "1");
        hashMap.put(KEY_EXTRA_MAP_PARAM_BEFORE_LOGIN, callFromLogin ? "1" : "0");
        intent.putExtra(KEY_EXTRA, hashMap);
        JdSdk jdSdk = JdSdk.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(jdSdk, "JdSdk.getInstance()");
        LocalBroadcastManager.getInstance(jdSdk.getApplicationContext()).sendBroadcast(intent);
        IElderModeHelper iElderModeHelper = helper;
        if (iElderModeHelper != null) {
            iElderModeHelper.log("\u53d1\u9001B\u7248\u6a21\u5f0f\u5207\u6362\u5e7f\u64ad");
        }
    }

    static /* synthetic */ void setModeSwitchBroadcast$default(JDBModeManager jDBModeManager, boolean z, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = false;
        }
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        jDBModeManager.setModeSwitchBroadcast(z, z2);
    }

    public static /* synthetic */ void setModeSwitchSpecialBroadcast$default(JDBModeManager jDBModeManager, boolean z, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = false;
        }
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        jDBModeManager.setModeSwitchSpecialBroadcast(z, z2);
    }

    public static /* synthetic */ void switchMode$default(JDBModeManager jDBModeManager, String str, String str2, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        jDBModeManager.switchMode(str, str2, z);
    }

    private final void switchModeAndRequest(String mode, String auto, boolean needSkipCurrentModeSwitch, boolean callFromLogin) {
        setCurrentMode(mode);
        setReportAutoMode(auto);
        setLocalAutoMode(auto);
        try {
            JDElderModeManager.INSTANCE.bModeReuseLargeSizeAndDarkMode(Integer.parseInt(mode));
        } catch (NumberFormatException unused) {
        }
        setModeSwitchBroadcast(needSkipCurrentModeSwitch, callFromLogin);
        if (needSkipCurrentModeSwitch) {
            return;
        }
        JDElderModeManager.INSTANCE.requestMixMode();
    }

    public static /* synthetic */ void switchModeAndRequest$default(JDBModeManager jDBModeManager, String str, String str2, boolean z, boolean z2, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        if ((i2 & 8) != 0) {
            z2 = false;
        }
        jDBModeManager.switchModeAndRequest(str, str2, z, z2);
    }

    @NotNull
    public final String getAdviseVersion() {
        String str;
        if (adviseVersion == null) {
            IElderModeHelper iElderModeHelper = helper;
            if (iElderModeHelper == null || (str = iElderModeHelper.getString(CACHE_KEY_MODE_ADVISE_VERSION, "999")) == null) {
                str = "999";
            }
            adviseVersion = str;
        }
        String str2 = adviseVersion;
        return str2 != null ? str2 : "999";
    }

    @NotNull
    public final String getBuriedExpLabel() {
        String string;
        IElderModeHelper iElderModeHelper = helper;
        return (iElderModeHelper == null || (string = iElderModeHelper.getString(CACHE_KEY_MODE_BURIED_EXPLABEL)) == null) ? "" : string;
    }

    @NotNull
    public final String getCurrentMode() {
        return currentMode;
    }

    @NotNull
    public final String getForbiddenVersionSwitch() {
        return getDataHelper().getForbiddenVersionSwitch();
    }

    @Nullable
    public final IElderModeHelper getHelper() {
        return helper;
    }

    @NotNull
    public final String getLocalAutoModeFormCache() {
        if (localAutoModeCache == null) {
            IElderModeHelper iElderModeHelper = helper;
            localAutoModeCache = iElderModeHelper != null ? iElderModeHelper.getString(CACHE_KEY_MODE_LOCAL_AUTO_MODE, "0") : null;
        }
        String str = localAutoModeCache;
        return str != null ? str : "0";
    }

    @NotNull
    public final String getPopulationType() {
        String string;
        IElderModeHelper iElderModeHelper = helper;
        return (iElderModeHelper == null || (string = iElderModeHelper.getString(CACHE_KEY_MODE_POPULATION_TYPE, "999")) == null) ? "999" : string;
    }

    @Nullable
    public final String getRequestBodyParamJson() {
        Map plus;
        requestModeFirstCalled = false;
        IElderModeHelper iElderModeHelper = helper;
        if (iElderModeHelper != null) {
            iElderModeHelper.log("\u9996\u9875\u51b7\u542f\u52a8\u6d41\u7a0b\u83b7\u53d6\u7248\u672c\u670d\u52a1\u8bf7\u6c42\u5165\u53c2\u3002");
        }
        Gson gson = new Gson();
        plus = MapsKt__MapsKt.plus(getRequestBodyParamMap(), JDElderModeManager.INSTANCE.getRequestBodyParamMap());
        return gson.toJson(plus);
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0014, code lost:
        if (r0 != null) goto L46;
     */
    @NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Map<String, String> getRequestBodyParamMap() {
        String str;
        Map<String, String> mutableMapOf;
        boolean isBlank;
        String reportAutoModeFormCache = getReportAutoModeFormCache();
        if (reportAutoModeFormCache != null) {
            isBlank = StringsKt__StringsJVMKt.isBlank(reportAutoModeFormCache);
            if ((!isBlank) == false) {
                reportAutoModeFormCache = null;
            }
            if (reportAutoModeFormCache != null) {
                str = "2";
            }
        }
        str = "1";
        reportAutoModeFormCache = getLocalAutoModeFormCache();
        mutableMapOf = MapsKt__MapsKt.mutableMapOf(TuplesKt.to("slimOperateType", str), TuplesKt.to("currentVersion", getCurrentMode()), TuplesKt.to("currentMode", reportAutoModeFormCache), TuplesKt.to("shieldData", getShieldData()));
        return mutableMapOf;
    }

    @Nullable
    public final ElderModeResponse getResponse() {
        return getDataHelper().getResponse();
    }

    public final void handleMajorResponse(@Nullable String json, @Nullable Integer operateType, boolean callFromLogin) {
        getDataHelper().handleMajorResponse(json, operateType, callFromLogin);
        handleSyncMtaData();
    }

    public final void initialize(@Nullable IElderModeHelper helper2) {
        if (helper2 != null) {
            helper = helper2;
            helper2.log("B\u7248\u5f00\u59cb\u521d\u59cb\u5316");
            if (initialized) {
                return;
            }
            initialized = true;
            initModeFromCache();
            helper2.log("B\u7248\u521d\u59cb\u5316\u5b8c\u6210");
            return;
        }
        throw new IllegalArgumentException("engine is null");
    }

    public final boolean needShowNormalModeDialog() {
        return getDataHelper().needShowNormalModeDialog();
    }

    public final void onDestroy() {
        getDataHelper().clear();
    }

    public final void requestCallFromLogin(@Nullable RequestModeCallback callBack, int delay, @Nullable Function4<? super IElderModeHelper, ? super Map<String, String>, ? super Subscriber<? super String>, ? super Integer, Unit> function) {
        Map plus;
        Map mutableMapOf;
        Map plus2;
        plus = MapsKt__MapsKt.plus(JDElderModeManager.INSTANCE.getRequestBodyParamMap(), getRequestBodyParamMap());
        mutableMapOf = MapsKt__MapsKt.mutableMapOf(TuplesKt.to("sourceCode", JDElderModeDataHelper.SOURCE_CODE_LOGIN));
        plus2 = MapsKt__MapsKt.plus(plus, mutableMapOf);
        JDBModeCallFromLoginUtil.request(helper, plus2, callBack, delay, function);
        IElderModeHelper iElderModeHelper = helper;
        if (iElderModeHelper != null) {
            iElderModeHelper.log("\u767b\u5f55\u94fe\u8def\u5f00\u59cb\u8bf7\u6c42\u6570\u636e callFromLogin, \u8bf7\u6c42\u5165\u53c2\uff1a" + plus2);
        }
    }

    public final void requestModeWithOffSite$eldermodelib() {
        IElderModeHelper iElderModeHelper = helper;
        if (iElderModeHelper != null) {
            iElderModeHelper.log("\u5916\u6295\u573a\u666f\uff0c\u751f\u547d\u5468\u671f\u5185\u9996\u6b21\u8c03\u7528:" + requestModeFirstCalled);
        }
        if (requestModeFirstCalled) {
            requestModeFirstCalled = false;
            JDElderModeManager.INSTANCE.requestMixMode();
            IElderModeHelper iElderModeHelper2 = helper;
            if (iElderModeHelper2 != null) {
                iElderModeHelper2.log("\u5916\u6295\u573a\u666f\uff0c\u6267\u884c\u7248\u672c\u670d\u52a1\u66f4\u65b0\u64cd\u4f5c");
            }
        }
    }

    public final void setAdviseVersion(@NotNull String version) {
        adviseVersion = version;
        IElderModeHelper iElderModeHelper = helper;
        if (iElderModeHelper != null) {
            iElderModeHelper.putString(CACHE_KEY_MODE_ADVISE_VERSION, version);
        }
    }

    public final void setBModeDidClickConfirmButtonBroadcast() {
        Intent intent = new Intent();
        intent.setAction(ACTION_B_MODE_DID_CLICK_CONFIRM_BUTTON_NOTI);
        JdSdk jdSdk = JdSdk.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(jdSdk, "JdSdk.getInstance()");
        LocalBroadcastManager.getInstance(jdSdk.getApplicationContext()).sendBroadcast(intent);
        IElderModeHelper iElderModeHelper = helper;
        if (iElderModeHelper != null) {
            iElderModeHelper.log("\u53d1\u9001\u9996\u9875\u6807\u51c6\u7248\u5f39\u7a97\u786e\u5b9a\u6309\u94ae\u70b9\u51fb\u5e7f\u64ad");
        }
    }

    public final void setCurrentMode(@NotNull String mode) {
        currentMode = mode;
        IElderModeHelper iElderModeHelper = helper;
        if (iElderModeHelper != null) {
            iElderModeHelper.putString(CACHE_KEY_MODE, mode);
        }
        JDMtaUtils.setModeTag(getCurrentMode());
    }

    public final void setHelper(@Nullable IElderModeHelper iElderModeHelper) {
        helper = iElderModeHelper;
    }

    public final void setModeSwitchSpecialBroadcast(boolean needSkipCurrentModeSwitch, boolean callFromLogin) {
        Intent intent = new Intent();
        intent.setAction(ACTION_MODE_SWITCH_SPECIAL);
        HashMap hashMap = new HashMap();
        hashMap.put(KEY_EXTRA_MAP_PARAM_TO_FINAL, needSkipCurrentModeSwitch ? "0" : "1");
        hashMap.put(KEY_EXTRA_MAP_PARAM_BEFORE_LOGIN, callFromLogin ? "1" : "0");
        intent.putExtra(KEY_EXTRA, hashMap);
        JdSdk jdSdk = JdSdk.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(jdSdk, "JdSdk.getInstance()");
        LocalBroadcastManager.getInstance(jdSdk.getApplicationContext()).sendBroadcast(intent);
        IElderModeHelper iElderModeHelper = helper;
        if (iElderModeHelper != null) {
            iElderModeHelper.log("\u7279\u6b8a\u573a\u666f\u53d1\u9001\u5207\u6362\u6a21\u5f0f\u5e7f\u64ad");
        }
    }

    public final void setReportAutoMode(@NotNull String auto) {
        reportAutoModeCache = auto;
        IElderModeHelper iElderModeHelper = helper;
        if (iElderModeHelper != null) {
            iElderModeHelper.putString(CACHE_KEY_MODE_REPORT_AUTO_MODE, auto);
        }
    }

    public final void showNormalModeDialog(@Nullable Activity activity) {
        if (getDataHelper().getResponse() != null) {
            IElderModeHelper iElderModeHelper = helper;
            if (iElderModeHelper != null) {
                iElderModeHelper.putInt(CACHE_KEY_MODE_SWITCH_DIALOG_HAS_SHOW, 1);
            }
            getDialogHelper().showDialog(activity);
        }
    }

    public final void switchMode(@NotNull final String mode, @NotNull final String auto, final boolean callFromLogin) {
        if (Intrinsics.areEqual(getCurrentMode(), mode)) {
            IElderModeHelper iElderModeHelper = helper;
            if (iElderModeHelper != null) {
                iElderModeHelper.log("\u5f53\u524d\u6a21\u5f0f" + getCurrentMode() + "\u4e0e\u9009\u4e2d\u5207\u6362\u6a21\u5f0f" + mode + "\u76f8\u540c\uff0c\u65e0\u9700\u6267\u884c\u6a21\u5f0f\u5207\u6362");
                return;
            }
            return;
        }
        switch (mode.hashCode()) {
            case 48:
                if (mode.equals("0")) {
                    if (Intrinsics.areEqual(getCurrentMode(), "1")) {
                        setModeSwitchSpecialBroadcast$default(this, false, callFromLogin, 1, null);
                        getMainHandler().post(new Runnable() { // from class: com.jingdong.sdk.bmode.util.JDBModeManager$switchMode$1
                            @Override // java.lang.Runnable
                            public final void run() {
                                JDElderModeManager.INSTANCE.switchElderMode(0);
                            }
                        });
                        return;
                    } else if (Intrinsics.areEqual(getCurrentMode(), "2")) {
                        switchModeAndRequest$default(this, mode, auto, false, callFromLogin, 4, null);
                        return;
                    } else {
                        return;
                    }
                }
                return;
            case 49:
                if (mode.equals("1")) {
                    if (Intrinsics.areEqual(getCurrentMode(), "2")) {
                        switchModeAndRequest("0", auto, true, callFromLogin);
                    }
                    setModeSwitchSpecialBroadcast$default(this, false, callFromLogin, 1, null);
                    getMainHandler().post(new Runnable() { // from class: com.jingdong.sdk.bmode.util.JDBModeManager$switchMode$2
                        @Override // java.lang.Runnable
                        public final void run() {
                            JDElderModeManager.INSTANCE.switchElderMode(1);
                        }
                    });
                    return;
                }
                return;
            case 50:
                if (mode.equals("2")) {
                    if (Intrinsics.areEqual(getCurrentMode(), "1")) {
                        setModeSwitchSpecialBroadcast(true, callFromLogin);
                        getMainHandler().post(new Runnable() { // from class: com.jingdong.sdk.bmode.util.JDBModeManager$switchMode$3
                            @Override // java.lang.Runnable
                            public final void run() {
                                JDElderModeManager.INSTANCE.switchElderMode(0, true);
                            }
                        });
                    }
                    getMainHandler().post(new Runnable() { // from class: com.jingdong.sdk.bmode.util.JDBModeManager$switchMode$4
                        @Override // java.lang.Runnable
                        public final void run() {
                            JDBModeManager.switchModeAndRequest$default(JDBModeManager.INSTANCE, mode, auto, false, callFromLogin, 4, null);
                        }
                    });
                    return;
                }
                return;
            default:
                return;
        }
    }
}
