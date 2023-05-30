package com.jingdong.sdk.bmode.util;

import android.app.Activity;
import com.jingdong.sdk.eldermode.util.JDElderModeManager;
import com.jingdong.sdk.oklog.OKLog;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\"\u0010\bJ\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0007\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0004H\u0007\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\u000b\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0007\u00a2\u0006\u0004\b\u000b\u0010\fJ!\u0010\u000f\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000e\u001a\u00020\rH\u0007\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0019\u0010\u0012\u001a\u00020\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\tH\u0007\u00a2\u0006\u0004\b\u0012\u0010\fJ\u0019\u0010\u0013\u001a\u00020\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\tH\u0007\u00a2\u0006\u0004\b\u0013\u0010\fJ\u0019\u0010\u0014\u001a\u00020\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\tH\u0007\u00a2\u0006\u0004\b\u0014\u0010\fJ\u0011\u0010\u0015\u001a\u0004\u0018\u00010\tH\u0007\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\tH\u0007\u00a2\u0006\u0004\b\u0017\u0010\u0016J\u000f\u0010\u0018\u001a\u00020\tH\u0007\u00a2\u0006\u0004\b\u0018\u0010\u0016J\u000f\u0010\u0019\u001a\u00020\tH\u0007\u00a2\u0006\u0004\b\u0019\u0010\u0016J\u000f\u0010\u001a\u001a\u00020\tH\u0007\u00a2\u0006\u0004\b\u001a\u0010\u0016J\u000f\u0010\u001b\u001a\u00020\tH\u0007\u00a2\u0006\u0004\b\u001b\u0010\u0016J\u000f\u0010\u001c\u001a\u00020\rH\u0007\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0019\u0010 \u001a\u00020\u00042\b\u0010\u001f\u001a\u0004\u0018\u00010\u001eH\u0007\u00a2\u0006\u0004\b \u0010!\u00a8\u0006#"}, d2 = {"Lcom/jingdong/sdk/bmode/util/JDBModeUtils;", "", "Lcom/jingdong/sdk/bmode/util/RequestModeCallback;", "callBack", "", "requestModeWithLogin", "(Lcom/jingdong/sdk/bmode/util/RequestModeCallback;)V", "requestModeWithOffSite", "()V", "", "json", "handleMajorResponse", "(Ljava/lang/String;)V", "", "callFromLogin", "handleMajorResponseWithLogin", "(Ljava/lang/String;Z)V", "auto", "changeToBMode", "changeToElderMode", "changeToNormalMode", "getRequestBodyParam", "()Ljava/lang/String;", "getCurrentMode", "getAdviseVersion", "getBuriedExpLabel", "getPopulationType", "getForbiddenVersionSwitch", "needShowNormalModeDialog", "()Z", "Landroid/app/Activity;", "activity", "showNormalModeDialog", "(Landroid/app/Activity;)V", "<init>", "eldermodelib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class JDBModeUtils {
    public static final JDBModeUtils INSTANCE = new JDBModeUtils();

    private JDBModeUtils() {
    }

    @JvmStatic
    public static final void changeToBMode(@Nullable String auto) {
        JDBModeManager jDBModeManager = JDBModeManager.INSTANCE;
        if (auto == null) {
            auto = "1";
        }
        JDBModeManager.switchMode$default(jDBModeManager, "2", auto, false, 4, null);
    }

    @JvmStatic
    public static final void changeToElderMode(@Nullable String auto) {
        JDBModeManager jDBModeManager = JDBModeManager.INSTANCE;
        if (auto == null) {
            auto = "1";
        }
        JDBModeManager.switchMode$default(jDBModeManager, "1", auto, false, 4, null);
    }

    @JvmStatic
    public static final void changeToNormalMode(@Nullable String auto) {
        JDBModeManager jDBModeManager = JDBModeManager.INSTANCE;
        if (auto == null) {
            auto = "1";
        }
        JDBModeManager.switchMode$default(jDBModeManager, "0", auto, false, 4, null);
    }

    @JvmStatic
    @NotNull
    public static final String getAdviseVersion() {
        return JDBModeManager.INSTANCE.getAdviseVersion();
    }

    @JvmStatic
    @NotNull
    public static final String getBuriedExpLabel() {
        return JDBModeManager.INSTANCE.getBuriedExpLabel();
    }

    @JvmStatic
    @NotNull
    public static final String getCurrentMode() {
        return JDBModeManager.INSTANCE.getCurrentMode();
    }

    @JvmStatic
    @NotNull
    public static final String getForbiddenVersionSwitch() {
        return JDBModeManager.INSTANCE.getForbiddenVersionSwitch();
    }

    @JvmStatic
    @NotNull
    public static final String getPopulationType() {
        return JDBModeManager.INSTANCE.getPopulationType();
    }

    @JvmStatic
    @Nullable
    public static final String getRequestBodyParam() {
        return JDBModeManager.INSTANCE.getRequestBodyParamJson();
    }

    @JvmStatic
    public static final void handleMajorResponse(@Nullable String json) {
        if (OKLog.D) {
            OKLog.d("JDBModeUtils", "handleMajorResponse:" + json);
        }
        JDElderModeManager.INSTANCE.handleResponse(json);
        JDBModeManager.handleMajorResponse$default(JDBModeManager.INSTANCE, json, null, false, 6, null);
    }

    @JvmStatic
    public static final void handleMajorResponseWithLogin(@Nullable String json, boolean callFromLogin) {
        if (OKLog.D) {
            OKLog.d("JDBModeUtils", "handleMajorResponseWithLogin:" + json);
        }
        JDElderModeManager.INSTANCE.handleResponse(json);
        JDBModeManager.handleMajorResponse$default(JDBModeManager.INSTANCE, json, null, callFromLogin, 2, null);
    }

    @JvmStatic
    public static final boolean needShowNormalModeDialog() {
        return JDBModeManager.INSTANCE.needShowNormalModeDialog();
    }

    @JvmStatic
    public static final void requestModeWithLogin(@Nullable RequestModeCallback callBack) {
        JDBModeManager.requestCallFromLogin$default(JDBModeManager.INSTANCE, callBack, 0, null, 6, null);
    }

    @JvmStatic
    public static final void requestModeWithOffSite() {
        JDBModeManager.INSTANCE.requestModeWithOffSite$eldermodelib();
    }

    @JvmStatic
    public static final void showNormalModeDialog(@Nullable Activity activity) {
        JDBModeManager.INSTANCE.showNormalModeDialog(activity);
    }
}
