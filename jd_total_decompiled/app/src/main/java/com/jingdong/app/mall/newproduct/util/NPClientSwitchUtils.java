package com.jingdong.app.mall.newproduct.util;

import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.utils.SwitchQueryFetcher;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0007J\u000f\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\r\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007R\"\u0010\b\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u0004\"\u0004\b\u000b\u0010\fR\u0016\u0010\u000e\u001a\u00020\r8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0011"}, d2 = {"Lcom/jingdong/app/mall/newproduct/util/NPClientSwitchUtils;", "", "", "closeNpMemoryLeak", "()Z", "", XView2Constants.XVIEW2_ACTION_INIT, "()V", "closeNPMemoryLeakOpt", "Z", "getCloseNPMemoryLeakOpt", "setCloseNPMemoryLeakOpt", "(Z)V", "", "KEY_CLOSE_NP_MEMORY_LEAK", "Ljava/lang/String;", "<init>", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes4.dex */
public final class NPClientSwitchUtils {
    public static final NPClientSwitchUtils INSTANCE = new NPClientSwitchUtils();
    private static final String KEY_CLOSE_NP_MEMORY_LEAK = "npCloseMemoryLeakOPt";
    private static boolean closeNPMemoryLeakOpt;

    private NPClientSwitchUtils() {
    }

    private final boolean closeNpMemoryLeak() {
        return SwitchQueryFetcher.getSwitchBooleanValue(KEY_CLOSE_NP_MEMORY_LEAK, false);
    }

    public final boolean getCloseNPMemoryLeakOpt() {
        return closeNPMemoryLeakOpt;
    }

    public final void init() {
        closeNPMemoryLeakOpt = closeNpMemoryLeak();
    }

    public final void setCloseNPMemoryLeakOpt(boolean z) {
        closeNPMemoryLeakOpt = z;
    }
}
