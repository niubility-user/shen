package com.jd.android.sdk.coreinfo.deviceUtil;

import android.text.TextUtils;
import com.jd.android.sdk.coreinfo.util.CommandUtil;
import com.jd.android.sdk.coreinfo.util.FileReaderUtil;

/* loaded from: classes.dex */
public final class b {
    public static String a() {
        try {
            String read = FileReaderUtil.read("/proc/version", false);
            try {
                return TextUtils.isEmpty(read) ? CommandUtil.handlerCommand("uname -a") : read;
            } catch (Throwable unused) {
                return read;
            }
        } catch (Throwable unused2) {
            return "";
        }
    }
}
