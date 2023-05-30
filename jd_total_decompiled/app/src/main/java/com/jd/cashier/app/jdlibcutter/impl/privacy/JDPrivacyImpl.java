package com.jd.cashier.app.jdlibcutter.impl.privacy;

import android.content.Context;
import android.util.DisplayMetrics;
import com.jd.cashier.app.jdlibcutter.protocol.privacy.IPrivacy;
import com.jingdong.common.utils.ProcessUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes13.dex */
public class JDPrivacyImpl implements IPrivacy {
    @Override // com.jd.cashier.app.jdlibcutter.protocol.privacy.IPrivacy
    public DisplayMetrics getDisplayMetrics() {
        return BaseInfo.getDisplayMetricsObject();
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.privacy.IPrivacy
    public String getPackageName() {
        return BaseInfo.getAppPackageName();
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.privacy.IPrivacy
    public boolean isNFCAvailable(Context context) {
        return BaseInfo.isNFCAvailable(context);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.privacy.IPrivacy
    public boolean isPackageInstalled(Context context, String str) {
        return BaseInfo.isPkgInstalled(context, str);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.privacy.IPrivacy
    public boolean isProcessForeground() {
        return ProcessUtil.isForeground();
    }
}
