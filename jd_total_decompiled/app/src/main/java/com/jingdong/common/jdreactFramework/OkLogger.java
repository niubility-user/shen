package com.jingdong.common.jdreactFramework;

import com.jingdong.common.jdreactFramework.helper.Logger;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class OkLogger implements Logger {
    @Override // com.jingdong.common.jdreactFramework.helper.Logger
    public void d(String str, String str2) {
        OKLog.d(str, str2);
    }

    @Override // com.jingdong.common.jdreactFramework.helper.Logger
    public void e(String str, String str2) {
        OKLog.e(str, str2);
    }

    @Override // com.jingdong.common.jdreactFramework.helper.Logger
    public void i(String str, String str2) {
        OKLog.i(str, str2);
    }

    @Override // com.jingdong.common.jdreactFramework.helper.Logger
    public void w(String str, String str2) {
        OKLog.w(str, str2);
    }

    @Override // com.jingdong.common.jdreactFramework.helper.Logger
    public void d(String str, Throwable th) {
        OKLog.d(str, th);
    }

    @Override // com.jingdong.common.jdreactFramework.helper.Logger
    public void e(String str, Throwable th) {
        OKLog.e(str, th);
    }

    @Override // com.jingdong.common.jdreactFramework.helper.Logger
    public void i(String str, Throwable th) {
        OKLog.i(str, th);
    }

    @Override // com.jingdong.common.jdreactFramework.helper.Logger
    public void w(String str, Throwable th) {
        OKLog.w(str, th);
    }
}
