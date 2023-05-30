package com.jingdong.sdk.lib.isvmonitor;

import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.sdk.jdcrashreport.JdCrashReport;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes8.dex */
public final class a {
    public static final a a = new a();

    private a() {
    }

    public final void a(@NotNull Map<String, ? extends Object> map) {
        if (Intrinsics.areEqual("1", JDMobileConfig.getInstance().getConfig("JDISVExceptionMsg", "JDISVExceptionConfig", "isvRequestFlag"))) {
            com.jingdong.sdk.lib.isvmonitor.b.a.a.b(map);
        }
    }

    public final void b(@NotNull Exception exc, @NotNull String str, @NotNull Map<String, ? extends Object> map) {
        if (Intrinsics.areEqual("1", JDMobileConfig.getInstance().getConfig("JDISVExceptionMsg", "JDISVExceptionConfig", "isvRequestFlag"))) {
            try {
                throw new RuntimeException(map.toString(), exc);
            } catch (Exception e2) {
                JdCrashReport.postCaughtException(e2, "JDISV-" + str);
                com.jingdong.sdk.lib.isvmonitor.b.a.a.b(map);
            }
        }
    }
}
