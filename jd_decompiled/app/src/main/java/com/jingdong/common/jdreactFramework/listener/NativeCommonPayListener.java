package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.jingdong.common.jdreactFramework.JDCallback;
import java.util.HashMap;

/* loaded from: classes5.dex */
public interface NativeCommonPayListener {
    void doJDPay(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void doJDPayApp(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void doUnionPay(Activity activity, HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    boolean doWeiXinPay(HashMap hashMap, Context context);

    boolean isWeiXinInstalled();

    boolean isWeiXinSupport();

    boolean isWeixinPaySupported();

    void onActivityResult(Activity activity, int i2, int i3, Intent intent);

    void openWeixinNoPwdPay(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);
}
