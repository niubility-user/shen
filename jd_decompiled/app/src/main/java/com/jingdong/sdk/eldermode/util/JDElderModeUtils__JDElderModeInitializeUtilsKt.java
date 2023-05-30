package com.jingdong.sdk.eldermode.util;

import android.content.Context;
import android.content.res.Configuration;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.e;
import com.jingdong.sdk.bmode.util.JDBModeManager;
import com.jingdong.sdk.eldermode.helper.IElderModeHelper;
import com.jingdong.sdk.eldermode.helper.impl.JDElderModeHelper;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0004\u001a\u0015\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000\u00a2\u0006\u0004\b\u0003\u0010\u0004\u001a\u0015\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\b\u0010\t\u001a\r\u0010\n\u001a\u00020\u0007\u00a2\u0006\u0004\b\n\u0010\u000b\u001a\r\u0010\f\u001a\u00020\u0007\u00a2\u0006\u0004\b\f\u0010\u000b\u001a\r\u0010\r\u001a\u00020\u0007\u00a2\u0006\u0004\b\r\u0010\u000b\u001a\u0015\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u0001\u001a\u00020\u0000\u00a2\u0006\u0004\b\u000e\u0010\u000f\u001a\u0015\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0001\u001a\u00020\u0000\u00a2\u0006\u0004\b\u0010\u0010\u000f\u001a\r\u0010\u0011\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0011\u0010\u000b\u001a\u0017\u0010\u0014\u001a\u00020\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u00a2\u0006\u0004\b\u0014\u0010\u0015\u001a\u0017\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0016H\u0000\u00a2\u0006\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001a"}, d2 = {"Landroid/content/Context;", AnnoConst.Constructor_Context, "Lcom/jingdong/sdk/eldermode/helper/impl/JDElderModeHelper$Builder;", "newBuilder", "(Landroid/content/Context;)Lcom/jingdong/sdk/eldermode/helper/impl/JDElderModeHelper$Builder;", "Lcom/jingdong/sdk/eldermode/helper/impl/JDElderModeHelper;", "helper", "", "initialize", "(Lcom/jingdong/sdk/eldermode/helper/impl/JDElderModeHelper;)V", "requestElderMode", "()V", "onLoginIn", "onLoginOut", "registerLoginReceiver", "(Landroid/content/Context;)V", "unregisterLoginReceiver", "onDestroy", "Landroid/content/res/Configuration;", "newConfig", "onConfigurationChanged", "(Landroid/content/res/Configuration;)V", "", e.a, "tryHandleException", "(Ljava/lang/Throwable;)V", "eldermodelib"}, k = 5, mv = {1, 4, 0}, xs = "com/jingdong/sdk/eldermode/util/JDElderModeUtils")
/* loaded from: classes7.dex */
public final /* synthetic */ class JDElderModeUtils__JDElderModeInitializeUtilsKt {
    public static final void initialize(@NotNull JDElderModeHelper jDElderModeHelper) {
        try {
            JDBModeManager.INSTANCE.initialize(jDElderModeHelper);
            JDElderModeManager.INSTANCE.initialize(jDElderModeHelper);
        } catch (Throwable th) {
            JDElderModeUtils.tryHandleException(th);
        }
    }

    @NotNull
    public static final JDElderModeHelper.Builder newBuilder(@NotNull Context context) {
        return new JDElderModeHelper.Builder(context);
    }

    public static final void onConfigurationChanged(@Nullable Configuration configuration) {
        try {
            JDElderModeManager.INSTANCE.onConfigurationChanged(configuration);
        } catch (Throwable th) {
            JDElderModeUtils.tryHandleException(th);
        }
    }

    public static final void onDestroy() {
        try {
            JDElderModeManager.INSTANCE.onDestroy();
            JDBModeManager.INSTANCE.onDestroy();
        } catch (Throwable th) {
            JDElderModeUtils.tryHandleException(th);
        }
    }

    public static final void onLoginIn() {
        try {
            JDElderModeManager.INSTANCE.onLoginIn();
        } catch (Throwable th) {
            JDElderModeUtils.tryHandleException(th);
        }
    }

    public static final void onLoginOut() {
        try {
            JDElderModeManager.INSTANCE.onLoginOut();
        } catch (Throwable th) {
            JDElderModeUtils.tryHandleException(th);
        }
    }

    public static final void registerLoginReceiver(@NotNull Context context) {
        try {
            IElderModeHelper helper = JDElderModeManager.INSTANCE.getHelper();
            if (helper != null) {
                helper.registerLoginReceiver(context);
            }
        } catch (Throwable th) {
            JDElderModeUtils.tryHandleException(th);
        }
    }

    public static final void requestElderMode() {
        try {
            JDElderModeManager.INSTANCE.requestElderMode();
        } catch (Throwable th) {
            JDElderModeUtils.tryHandleException(th);
        }
    }

    public static final void tryHandleException(@NotNull Throwable th) {
        try {
            IElderModeHelper helper = JDElderModeManager.INSTANCE.getHelper();
            if (helper != null) {
                helper.handleException(th);
            }
        } catch (Exception unused) {
        }
    }

    public static final void unregisterLoginReceiver(@NotNull Context context) {
        try {
            IElderModeHelper helper = JDElderModeManager.INSTANCE.getHelper();
            if (helper != null) {
                helper.unregisterLoginReceiver(context);
            }
        } catch (Throwable th) {
            JDElderModeUtils.tryHandleException(th);
        }
    }
}
