package com.jingdong.common.utils;

import android.os.SystemClock;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.perfmonitor.PerfMonitor;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u000e\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0004J\u000f\u0010\u0003\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0004J!\u0010\b\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005H\u0007\u00a2\u0006\u0004\b\b\u0010\tJ!\u0010\n\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005H\u0007\u00a2\u0006\u0004\b\n\u0010\tR\u0016\u0010\f\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\f\u0010\rR\u0016\u0010\u000e\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\rR\u0016\u0010\u000f\u001a\u00020\u00058\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0011\u001a\u00020\u00058\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0010R\u0016\u0010\u0012\u001a\u00020\u00058\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0010R\u0016\u0010\u0013\u001a\u00020\u00058\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0010R\u0016\u0010\u0014\u001a\u00020\u00058\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0010R\u0016\u0010\u0015\u001a\u00020\u00058\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0010R\u0016\u0010\u0016\u001a\u00020\u00058\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0010R\u0016\u0010\u0017\u001a\u00020\u00058\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0010\u00a8\u0006\u0019"}, d2 = {"Lcom/jingdong/common/utils/TraceCustomUtil;", "", "", XView2Constants.XVIEW2_ACTION_INIT, "()V", "", "customKey", "menuStaticSource", "addTraceTime", "(Ljava/lang/String;Ljava/lang/String;)V", "stop", "", "totalStart", "J", "startTime", "TIME_STATIC_CONFIG_CACHE", "Ljava/lang/String;", "TIME_NET_END_CALLBACK", "TIME_TOTAL", "MENU_SOURCE_TYPE_DYNAMIC", "TIME_NET_END", "TAG", "TIME_CREATE_HTTP_SETTING", "pageKey", "<init>", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class TraceCustomUtil {
    public static final TraceCustomUtil INSTANCE = new TraceCustomUtil();
    private static final String MENU_SOURCE_TYPE_DYNAMIC = "0";
    private static final String TAG = "TraceCustomUtil";
    @NotNull
    public static final String TIME_CREATE_HTTP_SETTING = "time_create_http_setting";
    @NotNull
    public static final String TIME_NET_END = "time_net_end";
    @NotNull
    public static final String TIME_NET_END_CALLBACK = "time_net_end_callback";
    @NotNull
    public static final String TIME_STATIC_CONFIG_CACHE = "time_static_config_cache";
    @NotNull
    public static final String TIME_TOTAL = "time_total";
    private static final String pageKey = "JDPersonalFragment";
    private static long startTime;
    private static long totalStart;

    private TraceCustomUtil() {
    }

    @JvmStatic
    public static final void addTraceTime(@NotNull String customKey, @Nullable String menuStaticSource) {
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if ((!Intrinsics.areEqual(menuStaticSource, "0")) == true) {
                return;
            }
            PerfMonitor.getInstance().addTraceTime("JDPersonalFragment", customKey, elapsedRealtime - startTime);
            startTime = elapsedRealtime;
        } catch (Throwable th) {
            if (OKLog.D) {
                OKLog.d(TAG, "TraceCustomUtil addTraceTime exception: " + th);
            }
        }
    }

    @JvmStatic
    public static final void init() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        startTime = elapsedRealtime;
        totalStart = elapsedRealtime;
    }

    @JvmStatic
    public static final void stop(@NotNull String customKey, @Nullable String menuStaticSource) {
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if ((!Intrinsics.areEqual(menuStaticSource, "0")) == true) {
                return;
            }
            PerfMonitor.getInstance().addTraceTime("JDPersonalFragment", customKey, elapsedRealtime - totalStart);
        } catch (Throwable th) {
            if (OKLog.D) {
                OKLog.d(TAG, "TraceCustomUtil stop exception: " + th);
            }
        }
    }
}
