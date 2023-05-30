package com.jd.mobile.image.config;

import com.jingdong.app.util.image.assist.JDFailReason;

/* loaded from: classes17.dex */
public interface IExceptionReportHandler {
    void reportBitmapException(String str, JDFailReason jDFailReason, String str2, int i2);

    void reportCDNDowngradeData(String str, String str2, String str3, boolean z, int i2, String str4);

    void reportClearTextRequest(String str);

    void reportDpgPicMta(String str);
}
