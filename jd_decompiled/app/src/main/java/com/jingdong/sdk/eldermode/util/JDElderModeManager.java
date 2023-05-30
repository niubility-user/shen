package com.jingdong.sdk.eldermode.util;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Looper;
import android.view.View;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.jdcn.biz.client.BankCardConstants;
import com.jingdong.common.cart.clean.CartCleanConstants;
import com.jingdong.sdk.bmode.util.JDBModeManager;
import com.jingdong.sdk.bmode.util.JDBModeUtils;
import com.jingdong.sdk.eldermode.entity.ElderModeResponse;
import com.jingdong.sdk.eldermode.entity.ElderModeResponseOthers;
import com.jingdong.sdk.eldermode.helper.IElderModeHelper;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010$\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\bs\u0010\u0004J\u000f\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\t\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\t\u0010\bJ\u0017\u0010\n\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\n\u0010\bJ\u000f\u0010\u000b\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u000b\u0010\u0004J\u0017\u0010\u000e\u001a\u00020\u00022\b\u0010\r\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0013\u001a\u00020\u0012\u00a2\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0015\u0010\u0011J\r\u0010\u0016\u001a\u00020\u0012\u00a2\u0006\u0004\b\u0016\u0010\u0014J\r\u0010\u0017\u001a\u00020\u0012\u00a2\u0006\u0004\b\u0017\u0010\u0014J5\u0010\u001f\u001a\u00020\u00122\b\u0010\u0019\u001a\u0004\u0018\u00010\u00182\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001c\u00a2\u0006\u0004\b\u001f\u0010 J\r\u0010!\u001a\u00020\u0002\u00a2\u0006\u0004\b!\u0010\u0004J5\u0010\"\u001a\u00020\u00122\b\u0010\u0019\u001a\u0004\u0018\u00010\u00182\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001c\u00a2\u0006\u0004\b\"\u0010 J\u0015\u0010#\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b#\u0010\bJ\u001f\u0010#\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010$\u001a\u00020\u0012\u00a2\u0006\u0004\b#\u0010%J'\u0010'\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010&\u001a\u00020\u00122\b\b\u0002\u0010$\u001a\u00020\u0012\u00a2\u0006\u0004\b'\u0010(J\u0019\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a0)\u00a2\u0006\u0004\b*\u0010+J\u0015\u0010,\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b,\u0010\bJ\r\u0010-\u001a\u00020\u0002\u00a2\u0006\u0004\b-\u0010\u0004J\r\u0010.\u001a\u00020\u0002\u00a2\u0006\u0004\b.\u0010\u0004J\u0017\u00100\u001a\u00020\u00022\b\u0010/\u001a\u0004\u0018\u00010\u001a\u00a2\u0006\u0004\b0\u00101J\r\u00102\u001a\u00020\u0002\u00a2\u0006\u0004\b2\u0010\u0004J\r\u00103\u001a\u00020\u0002\u00a2\u0006\u0004\b3\u0010\u0004J\r\u00104\u001a\u00020\u0002\u00a2\u0006\u0004\b4\u0010\u0004J\u0017\u00107\u001a\u00020\u00022\b\u00106\u001a\u0004\u0018\u000105\u00a2\u0006\u0004\b7\u00108J\u0017\u0010;\u001a\u00020\u00022\u0006\u00109\u001a\u00020\u0005H\u0000\u00a2\u0006\u0004\b:\u0010\bJ\u0017\u0010>\u001a\u00020\u00022\b\u0010=\u001a\u0004\u0018\u00010<\u00a2\u0006\u0004\b>\u0010?J\u0017\u0010@\u001a\u00020\u00022\b\u0010=\u001a\u0004\u0018\u00010<\u00a2\u0006\u0004\b@\u0010?J'\u0010E\u001a\u00020\u00022\b\u0010B\u001a\u0004\u0018\u00010A2\u000e\u0010D\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010C\u00a2\u0006\u0004\bE\u0010FJ\u001d\u0010G\u001a\u00020\u00022\u000e\u0010D\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010C\u00a2\u0006\u0004\bG\u0010HR\u0013\u0010J\u001a\u00020\u00128F@\u0006\u00a2\u0006\u0006\u001a\u0004\bI\u0010\u0014R\u0016\u0010K\u001a\u00020\u00058\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bK\u0010LR\u001c\u0010N\u001a\b\u0012\u0004\u0012\u00020\u00050M8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bN\u0010OR\u0016\u0010P\u001a\u00020\u00058\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bP\u0010LR\u0016\u0010Q\u001a\u00020\u00058\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bQ\u0010LR\u0016\u0010R\u001a\u00020\u001a8\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bR\u0010SR\u0015\u0010W\u001a\u0004\u0018\u00010T8F@\u0006\u00a2\u0006\u0006\u001a\u0004\bU\u0010VR\u0016\u0010X\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bX\u0010YR\u0016\u0010Z\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bZ\u0010YR\u001d\u0010`\u001a\u00020[8B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\\\u0010]\u001a\u0004\b^\u0010_R\u0016\u0010b\u001a\u00020a8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bb\u0010cR\u001e\u0010e\u001a\n\u0012\u0004\u0012\u00020<\u0018\u00010d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\be\u0010fR\u0016\u0010g\u001a\u00020\u00058\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bg\u0010LR\"\u0010h\u001a\u00020\u00128\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bh\u0010Y\u001a\u0004\bh\u0010\u0014\"\u0004\bi\u0010jR\u0016\u0010k\u001a\u00020\u001a8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bk\u0010SR$\u0010\r\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\r\u0010l\u001a\u0004\bm\u0010n\"\u0004\bo\u0010\u000fR\"\u0010p\u001a\u00020\u00128\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\bp\u0010Y\u001a\u0004\bq\u0010\u0014\"\u0004\br\u0010j\u00a8\u0006t"}, d2 = {"Lcom/jingdong/sdk/eldermode/util/JDElderModeManager;", "", "", "initModeFromCache", "()V", "", "mode", "setElderMode", "(I)V", "saveElderMode", "notifyElderModeChanged", "listObservers", "Lcom/jingdong/sdk/eldermode/helper/IElderModeHelper;", "helper", "initialize", "(Lcom/jingdong/sdk/eldermode/helper/IElderModeHelper;)V", "getElderMode", "()I", "", "isElderMode", "()Z", "getRealElderUser", "isNeedShowElderModeDialog", "isNeedShowNormalModeDialog", "Landroid/app/Activity;", "activity", "", "source", "Landroid/view/View$OnClickListener;", "onOkButtonClickListener", "onCancelButtonClickListener", "showElderModeDialog", "(Landroid/app/Activity;Ljava/lang/String;Landroid/view/View$OnClickListener;Landroid/view/View$OnClickListener;)Z", "onShowElderModeDialog", "showNormalModeDialog", "switchElderMode", "needSkipRequest", "(IZ)V", "degrade", "switchElderModeAndRequest", "(IZZ)V", "", "getRequestBodyParamMap", "()Ljava/util/Map;", "bModeReuseLargeSizeAndDarkMode", "requestElderMode", "requestMixMode", "json", "handleResponse", "(Ljava/lang/String;)V", "onLoginIn", "onLoginOut", "onDestroy", "Landroid/content/res/Configuration;", "newConfig", "onConfigurationChanged", "(Landroid/content/res/Configuration;)V", "area", "onOverseasAreaChanged$eldermodelib", "onOverseasAreaChanged", "Lcom/jingdong/sdk/eldermode/util/OnElderModeChangeListener;", CartCleanConstants.CART_CLEAN_DIALOG_LISTENER, "addElderModeChangeListener", "(Lcom/jingdong/sdk/eldermode/util/OnElderModeChangeListener;)V", "removeElderModeChangeListener", "Landroidx/lifecycle/LifecycleOwner;", BankCardConstants.KEY_OWNER, "Landroidx/lifecycle/Observer;", "observer", "addElderModeObserver", "(Landroidx/lifecycle/LifecycleOwner;Landroidx/lifecycle/Observer;)V", "removeElderModeObserver", "(Landroidx/lifecycle/Observer;)V", "getElderModeEnable", "elderModeEnable", "MODE_ELDER", "I", "Landroidx/lifecycle/MutableLiveData;", "liveData", "Landroidx/lifecycle/MutableLiveData;", "MODE_FORBIDDEN", "MODE_NORMAL", "CACHE_KEY_ELDER_MODE", "Ljava/lang/String;", "Lcom/jingdong/sdk/eldermode/entity/ElderModeResponse;", "getResponse", "()Lcom/jingdong/sdk/eldermode/entity/ElderModeResponse;", "response", "initialized", "Z", "cachedServerEnable", "Lcom/jingdong/sdk/eldermode/util/JDElderModeDataHelper;", "dataHelper$delegate", "Lkotlin/Lazy;", "getDataHelper", "()Lcom/jingdong/sdk/eldermode/util/JDElderModeDataHelper;", "dataHelper", "Ljava/util/concurrent/atomic/AtomicInteger;", "currentMode", "Ljava/util/concurrent/atomic/AtomicInteger;", "Ljava/util/concurrent/CopyOnWriteArrayList;", "listeners", "Ljava/util/concurrent/CopyOnWriteArrayList;", "MODE_UNKNOWN", "isOverseasMode", "setOverseasMode", "(Z)V", "TOAST_WHEN_DIALOG_INFO_EMPTY", "Lcom/jingdong/sdk/eldermode/helper/IElderModeHelper;", "getHelper", "()Lcom/jingdong/sdk/eldermode/helper/IElderModeHelper;", "setHelper", "isServerDegrade", "isServerDegrade$eldermodelib", "setServerDegrade$eldermodelib", "<init>", "eldermodelib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class JDElderModeManager {
    @NotNull
    public static final String CACHE_KEY_ELDER_MODE = "elder_mode";
    public static final int MODE_ELDER = 1;
    public static final int MODE_FORBIDDEN = 3;
    public static final int MODE_NORMAL = 0;
    public static final int MODE_UNKNOWN = 2;
    private static final String TOAST_WHEN_DIALOG_INFO_EMPTY = "\u5207\u6362\u5931\u8d25\uff0c\u8bf7\u60a8\u91cd\u542fAPP\u540e\u518d\u6b21\u5c1d\u8bd5\u5207\u6362\u54e6";
    private static boolean cachedServerEnable;

    /* renamed from: dataHelper$delegate  reason: from kotlin metadata */
    private static final Lazy dataHelper;
    @Nullable
    private static IElderModeHelper helper;
    private static boolean initialized;
    private static boolean isOverseasMode;
    private static boolean isServerDegrade;
    private static CopyOnWriteArrayList<OnElderModeChangeListener> listeners;
    public static final JDElderModeManager INSTANCE = new JDElderModeManager();
    private static final AtomicInteger currentMode = new AtomicInteger();
    private static final MutableLiveData<Integer> liveData = new MutableLiveData<>();

    static {
        Lazy lazy;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<JDElderModeDataHelper>() { // from class: com.jingdong.sdk.eldermode.util.JDElderModeManager$dataHelper$2
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final JDElderModeDataHelper invoke() {
                return new JDElderModeDataHelper(JDElderModeManager.INSTANCE.getHelper());
            }
        });
        dataHelper = lazy;
    }

    private JDElderModeManager() {
    }

    private final JDElderModeDataHelper getDataHelper() {
        return (JDElderModeDataHelper) dataHelper.getValue();
    }

    private final void initModeFromCache() {
        cachedServerEnable = getDataHelper().getElderModeServerEnableFromCache();
        IElderModeHelper iElderModeHelper = helper;
        int i2 = 0;
        onOverseasAreaChanged$eldermodelib(iElderModeHelper != null ? iElderModeHelper.getOverseasArea() : 0);
        IElderModeHelper iElderModeHelper2 = helper;
        if (iElderModeHelper2 != null) {
            iElderModeHelper2.log("\u5168\u5c40\u5f00\u5173 = " + getElderModeEnable() + ", \u5176\u4e2d cachedServerEnable = " + cachedServerEnable + ", isOverseasMode = " + isOverseasMode);
        }
        int parseInt = Integer.parseInt(JDBModeUtils.getCurrentMode());
        if (parseInt != 1 || getElderModeEnable()) {
            i2 = parseInt;
        } else {
            JDBModeManager.INSTANCE.setCurrentMode("0");
        }
        IElderModeHelper iElderModeHelper3 = helper;
        if (iElderModeHelper3 != null) {
            iElderModeHelper3.log("initModeFromCache mode = " + i2);
        }
        setElderMode(i2);
        getDataHelper().initStateByElderMode(i2);
        if (Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
            liveData.setValue(Integer.valueOf(i2));
        } else {
            liveData.postValue(Integer.valueOf(i2));
        }
    }

    private final void listObservers() {
        Field field;
        Unit unit;
        IElderModeHelper iElderModeHelper = helper;
        if (iElderModeHelper == null || !iElderModeHelper.isDebug()) {
            return;
        }
        if (liveData.hasObservers()) {
            try {
                field = LiveData.class.getDeclaredField("mObservers");
            } catch (Throwable th) {
                IElderModeHelper iElderModeHelper2 = helper;
                if (iElderModeHelper2 != null) {
                    iElderModeHelper2.log("\u83b7\u53d6 mObservers \u5931\u8d25 " + th);
                }
                field = null;
            }
            if (field != null) {
                field.setAccessible(true);
            }
            Object obj = field != null ? field.get(liveData) : null;
            if (!(obj instanceof Iterable)) {
                obj = null;
            }
            Iterable iterable = (Iterable) obj;
            if (iterable != null) {
                for (Object obj2 : iterable) {
                    Map.Entry entry = (Map.Entry) (!(obj2 instanceof Map.Entry) ? null : obj2);
                    if (entry != null) {
                        IElderModeHelper iElderModeHelper3 = helper;
                        if (iElderModeHelper3 != null) {
                            iElderModeHelper3.log("\u89c2\u5bdf\u8005\u6709 " + entry.getKey());
                            unit = Unit.INSTANCE;
                        } else {
                            unit = null;
                        }
                        r0 = unit != null ? r0 + 1 : 0;
                    }
                    IElderModeHelper iElderModeHelper4 = helper;
                    if (iElderModeHelper4 != null) {
                        iElderModeHelper4.log("\u89c2\u5bdf\u8005\u8fed\u4ee3\u7c7b\u578b\u9519\u8bef " + obj2);
                        Unit unit2 = Unit.INSTANCE;
                    }
                }
            }
        }
        IElderModeHelper iElderModeHelper5 = helper;
        if (iElderModeHelper5 != null) {
            iElderModeHelper5.log("\u5171\u6709\u89c2\u5bdf\u8005 " + r0 + " \u4e2a");
        }
    }

    private final void notifyElderModeChanged(int mode) {
        IElderModeHelper iElderModeHelper = helper;
        if (iElderModeHelper != null) {
            iElderModeHelper.log("\u5206\u53d1\u957f\u8f88\u7248\u6a21\u5f0f " + mode);
        }
        listObservers();
        liveData.postValue(Integer.valueOf(mode));
        IElderModeHelper iElderModeHelper2 = helper;
        if (iElderModeHelper2 != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("\u5171\u6709\u76d1\u542c\u5668 ");
            CopyOnWriteArrayList<OnElderModeChangeListener> copyOnWriteArrayList = listeners;
            sb.append(copyOnWriteArrayList != null ? copyOnWriteArrayList.size() : 0);
            sb.append(" \u4e2a");
            iElderModeHelper2.log(sb.toString());
        }
        CopyOnWriteArrayList<OnElderModeChangeListener> copyOnWriteArrayList2 = listeners;
        if (copyOnWriteArrayList2 != null) {
            for (OnElderModeChangeListener onElderModeChangeListener : copyOnWriteArrayList2) {
                try {
                    IElderModeHelper iElderModeHelper3 = helper;
                    if (iElderModeHelper3 != null) {
                        iElderModeHelper3.log("\u5206\u53d1\u7ed9 " + onElderModeChangeListener);
                    }
                    onElderModeChangeListener.onElderModeChanged(mode);
                } catch (Throwable th) {
                    IElderModeHelper iElderModeHelper4 = helper;
                    if (iElderModeHelper4 != null) {
                        iElderModeHelper4.handleException(th);
                    }
                }
            }
        }
    }

    private final void saveElderMode(int mode) {
        IElderModeHelper iElderModeHelper = helper;
        if (iElderModeHelper != null) {
            iElderModeHelper.log("\u5185\u5b58\u8bbe\u7f6e\u6a21\u5f0f " + mode);
        }
        setElderMode(mode);
        IElderModeHelper iElderModeHelper2 = helper;
        if (iElderModeHelper2 != null) {
            iElderModeHelper2.putInt(CACHE_KEY_ELDER_MODE, mode);
        }
    }

    private final void setElderMode(int mode) {
        currentMode.set(mode);
    }

    public static /* synthetic */ void switchElderMode$default(JDElderModeManager jDElderModeManager, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            z = false;
        }
        jDElderModeManager.switchElderMode(i2, z);
    }

    public static /* synthetic */ void switchElderModeAndRequest$default(JDElderModeManager jDElderModeManager, int i2, boolean z, boolean z2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            z2 = false;
        }
        jDElderModeManager.switchElderModeAndRequest(i2, z, z2);
    }

    public final void addElderModeChangeListener(@Nullable OnElderModeChangeListener r4) {
        if (r4 != null) {
            IElderModeHelper iElderModeHelper = helper;
            if (iElderModeHelper != null) {
                iElderModeHelper.log("\u6dfb\u52a0\u76d1\u542c\u5668 " + r4);
            }
            CopyOnWriteArrayList<OnElderModeChangeListener> copyOnWriteArrayList = listeners;
            if (copyOnWriteArrayList == null) {
                copyOnWriteArrayList = new CopyOnWriteArrayList<>();
                listeners = copyOnWriteArrayList;
            }
            copyOnWriteArrayList.add(r4);
        }
    }

    public final void addElderModeObserver(@Nullable LifecycleOwner r4, @Nullable Observer<Integer> observer) {
        if (r4 == null || observer == null) {
            return;
        }
        IElderModeHelper iElderModeHelper = helper;
        if (iElderModeHelper != null) {
            iElderModeHelper.log("\u6dfb\u52a0\u89c2\u5bdf\u8005 " + observer);
        }
        liveData.observe(r4, observer);
    }

    public final void bModeReuseLargeSizeAndDarkMode(int mode) {
        getDataHelper().handleLargeSize(mode);
        getDataHelper().handleDarkMode(mode);
    }

    public final int getElderMode() {
        if (getElderModeEnable()) {
            return currentMode.get();
        }
        return 0;
    }

    public final boolean getElderModeEnable() {
        return cachedServerEnable & (!isServerDegrade) & (!isOverseasMode);
    }

    @Nullable
    public final IElderModeHelper getHelper() {
        return helper;
    }

    public final int getRealElderUser() {
        Integer realElderUser;
        ElderModeResponse response = getResponse();
        if (response == null || (realElderUser = response.getRealElderUser()) == null) {
            return 2;
        }
        return realElderUser.intValue();
    }

    @NotNull
    public final Map<String, String> getRequestBodyParamMap() {
        Map<String, String> emptyMap;
        Map<String, String> requestBodyParamMap;
        JDElderModeDataHelper dataHelper2 = getDataHelper();
        if (dataHelper2 == null || (requestBodyParamMap = dataHelper2.getRequestBodyParamMap()) == null) {
            emptyMap = MapsKt__MapsKt.emptyMap();
            return emptyMap;
        }
        return requestBodyParamMap;
    }

    @Nullable
    public final ElderModeResponse getResponse() {
        return getDataHelper().getResponse();
    }

    public final void handleResponse(@Nullable String json) {
        JDElderModeDataHelper.handleResponse$default(getDataHelper(), getDataHelper().parseElderModeResponse(json), 0, 2, null);
    }

    public final void initialize(@Nullable IElderModeHelper helper2) {
        if (helper2 != null) {
            helper2.log("\u5f00\u59cb\u521d\u59cb\u5316");
        }
        if (helper2 != null) {
            helper = helper2;
            if (initialized) {
                return;
            }
            initialized = true;
            initModeFromCache();
            helper2.log("\u521d\u59cb\u5316\u5b8c\u6210");
            return;
        }
        throw new IllegalArgumentException("engine is null");
    }

    public final boolean isElderMode() {
        return getElderMode() == 1;
    }

    public final boolean isNeedShowElderModeDialog() {
        return getDataHelper().isNeedShowElderModeDialog();
    }

    public final boolean isNeedShowNormalModeDialog() {
        return getDataHelper().isNeedShowNormalModeDialog();
    }

    public final boolean isOverseasMode() {
        return isOverseasMode;
    }

    public final boolean isServerDegrade$eldermodelib() {
        return isServerDegrade;
    }

    public final void onConfigurationChanged(@Nullable Configuration newConfig) {
        getDataHelper().onConfigurationChanged(newConfig);
    }

    public final void onDestroy() {
        CopyOnWriteArrayList<OnElderModeChangeListener> copyOnWriteArrayList = listeners;
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.clear();
        }
        getDataHelper().clear();
    }

    public final void onLoginIn() {
        IElderModeHelper iElderModeHelper = helper;
        if (iElderModeHelper != null) {
            iElderModeHelper.log("\u767b\u5f55\u6210\u529f\uff0c\u68c0\u67e5\u5e76\u8bf7\u6c42\u6570\u636e");
        }
        getDataHelper().requestMixMode();
    }

    public final void onLoginOut() {
        ElderModeResponseOthers others;
        ElderModeResponse response = getResponse();
        if (response != null && (others = response.getOthers()) != null) {
            others.setToastInfo(null);
        }
        ElderModeResponse response2 = getResponse();
        if (response2 != null) {
            response2.setRealElderUser(2);
        }
        isServerDegrade = false;
        IElderModeHelper iElderModeHelper = helper;
        if (iElderModeHelper != null) {
            iElderModeHelper.log("\u9000\u51fa\u767b\u5f55\uff0c\u6e05\u7a7a toastInfo \u548c realElderUser");
        }
    }

    public final void onOverseasAreaChanged$eldermodelib(int area) {
        boolean z = area > 0;
        if (z) {
            JDElderModeManager jDElderModeManager = INSTANCE;
            if (jDElderModeManager.isElderMode()) {
                IElderModeHelper iElderModeHelper = helper;
                if (iElderModeHelper != null) {
                    iElderModeHelper.log("\u6d77\u5916\u7248\u5207\u4e3a " + area + ", \u6d77\u5916\u7248\u7f6e\u4e3a " + z + ", \u5f53\u524d\u662f\u957f\u8f88\u7248, \u964d\u7ea7\u4e3a\u6807\u51c6\u7248");
                }
                JDBModeManager.setModeSwitchSpecialBroadcast$default(JDBModeManager.INSTANCE, false, false, 3, null);
                switchElderModeAndRequest$default(jDElderModeManager, 0, true, false, 4, null);
            } else {
                IElderModeHelper iElderModeHelper2 = helper;
                if (iElderModeHelper2 != null) {
                    iElderModeHelper2.log("\u6d77\u5916\u7248\u5207\u4e3a " + area + ", \u6d77\u5916\u7248\u7f6e\u4e3a " + z + ", \u5f53\u524d\u5df2\u7ecf\u662f\u6807\u51c6\u7248\uff0c\u65e0\u9700\u964d\u7ea7");
                }
            }
        } else {
            IElderModeHelper iElderModeHelper3 = helper;
            if (iElderModeHelper3 != null) {
                iElderModeHelper3.log("\u6d77\u5916\u7248\u5207\u4e3a " + area + ", \u6d77\u5916\u7248\u7f6e\u4e3a " + z);
            }
        }
        isOverseasMode = z;
    }

    public final void onShowElderModeDialog() {
        getDataHelper().onShowElderModeDialog();
    }

    public final void removeElderModeChangeListener(@Nullable OnElderModeChangeListener r4) {
        if (r4 != null) {
            IElderModeHelper iElderModeHelper = helper;
            if (iElderModeHelper != null) {
                iElderModeHelper.log("\u79fb\u9664\u76d1\u542c\u5668 " + r4);
            }
            CopyOnWriteArrayList<OnElderModeChangeListener> copyOnWriteArrayList = listeners;
            if (copyOnWriteArrayList != null) {
                copyOnWriteArrayList.remove(r4);
            }
        }
    }

    public final void removeElderModeObserver(@Nullable Observer<Integer> observer) {
        if (observer != null) {
            IElderModeHelper iElderModeHelper = helper;
            if (iElderModeHelper != null) {
                iElderModeHelper.log("\u79fb\u9664\u89c2\u5bdf\u8005 " + observer);
            }
            liveData.removeObserver(observer);
        }
    }

    public final void requestElderMode() {
        IElderModeHelper iElderModeHelper = helper;
        if (iElderModeHelper != null) {
            iElderModeHelper.log("\u5f00\u59cb\u8bf7\u6c42\u6570\u636e");
        }
        requestMixMode();
        IElderModeHelper iElderModeHelper2 = helper;
        if (iElderModeHelper2 != null) {
            iElderModeHelper2.log("\u8bf7\u6c42\u53d1\u9001\u5b8c\u6210");
        }
    }

    public final void requestMixMode() {
        getDataHelper().requestMixMode();
    }

    public final void setHelper(@Nullable IElderModeHelper iElderModeHelper) {
        helper = iElderModeHelper;
    }

    public final void setOverseasMode(boolean z) {
        isOverseasMode = z;
    }

    public final void setServerDegrade$eldermodelib(boolean z) {
        isServerDegrade = z;
    }

    public final boolean showElderModeDialog(@Nullable Activity activity, @Nullable String source, @Nullable View.OnClickListener onOkButtonClickListener, @Nullable View.OnClickListener onCancelButtonClickListener) {
        ElderModeResponseOthers others;
        String toastInfo;
        ElderModeResponse response = getResponse();
        if (response != null && (others = response.getOthers()) != null && (toastInfo = others.getToastInfo()) != null) {
            if (!(toastInfo.length() > 0)) {
                toastInfo = null;
            }
            if (toastInfo != null) {
                IElderModeHelper iElderModeHelper = helper;
                if (iElderModeHelper != null) {
                    iElderModeHelper.showToast(toastInfo);
                }
                return false;
            }
        }
        ElderModeResponse response2 = getResponse();
        if (response2 != null && response2.getCaringInfo() != null) {
            if (getElderModeEnable()) {
                JDElderModeDialogMtaUtils.INSTANCE.setSource$eldermodelib(source);
                IElderModeHelper iElderModeHelper2 = helper;
                if (iElderModeHelper2 != null) {
                    iElderModeHelper2.log("\u663e\u793a\u957f\u8f88\u7248\u5f39\u7a97\uff0c\u6765\u6e90\u4e3a\u3010" + source + '\u3011');
                }
                IElderModeHelper iElderModeHelper3 = helper;
                if (iElderModeHelper3 != null) {
                    return iElderModeHelper3.showElderModeDialog(activity, onOkButtonClickListener, onCancelButtonClickListener);
                }
                return false;
            }
            return false;
        }
        IElderModeHelper iElderModeHelper4 = helper;
        if (iElderModeHelper4 != null) {
            iElderModeHelper4.showToast(TOAST_WHEN_DIALOG_INFO_EMPTY);
        }
        return false;
    }

    public final boolean showNormalModeDialog(@Nullable Activity activity, @Nullable String source, @Nullable View.OnClickListener onOkButtonClickListener, @Nullable View.OnClickListener onCancelButtonClickListener) {
        ElderModeResponse response = getResponse();
        if (response != null && response.getStandardInfo() != null) {
            JDElderModeDialogMtaUtils.INSTANCE.setSource$eldermodelib(source);
            IElderModeHelper iElderModeHelper = helper;
            if (iElderModeHelper != null) {
                iElderModeHelper.log("\u663e\u793a\u6807\u51c6\u7248\u5f39\u7a97\uff0c\u6765\u6e90\u4e3a\u3010" + source + '\u3011');
            }
            if (getElderModeEnable()) {
                IElderModeHelper iElderModeHelper2 = helper;
                return iElderModeHelper2 != null ? iElderModeHelper2.showNormalModeDialog(activity, onOkButtonClickListener, onCancelButtonClickListener) : false;
            }
            return false;
        }
        IElderModeHelper iElderModeHelper3 = helper;
        if (iElderModeHelper3 != null) {
            iElderModeHelper3.showToast(TOAST_WHEN_DIALOG_INFO_EMPTY);
        }
        return false;
    }

    public final void switchElderMode(int mode) {
        switchElderMode(mode, false);
    }

    public final void switchElderModeAndRequest(int mode, boolean degrade, boolean needSkipRequest) {
        IElderModeHelper iElderModeHelper = helper;
        if (iElderModeHelper != null) {
            iElderModeHelper.log("\u5f00\u59cb\u5207\u6362\u6a21\u5f0f " + mode);
        }
        bModeReuseLargeSizeAndDarkMode(mode);
        getDataHelper().saveSwitchModeToCache(mode);
        saveElderMode(mode);
        JDBModeManager.INSTANCE.setCurrentMode(String.valueOf(mode));
        notifyElderModeChanged(mode);
        if (!degrade && !needSkipRequest) {
            getDataHelper().requestMixMode();
            return;
        }
        IElderModeHelper iElderModeHelper2 = helper;
        if (iElderModeHelper2 != null) {
            iElderModeHelper2.log("\u964d\u7ea7\u6a21\u5f0f\u4e0d\u7528\u8bf7\u6c42\u6570\u636e");
        }
    }

    public final void switchElderMode(int mode, boolean needSkipRequest) {
        switchElderModeAndRequest(mode, false, needSkipRequest);
    }
}
