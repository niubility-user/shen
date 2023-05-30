package com.jingdong.jdsdk.network.toolbox;

import android.text.TextUtils;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes14.dex */
public class ParamBuilderForThirdApp extends HttpSettingTool {
    private static String TAG = "ParamBuilderForThirdApp";
    private static AtomicLong sServerTimeOffset = new AtomicLong(0);

    private static Map<String, String> getCustomBodyParam(HttpSetting httpSetting) {
        HashMap hashMap = new HashMap();
        if (httpSetting.getCustomMapParam() != null) {
            hashMap.putAll(httpSetting.getCustomMapParam());
        }
        if (httpSetting.getCustomEncryptMapParam() != null) {
            hashMap.putAll(httpSetting.getCustomEncryptMapParam());
        }
        return hashMap;
    }

    public static void setServerTimeOffset(long j2) {
        sServerTimeOffset.set(j2);
    }

    /* JADX WARN: Removed duplicated region for block: B:148:0x045f A[Catch: all -> 0x04bc, TryCatch #5 {all -> 0x04bc, blocks: (B:146:0x0454, B:148:0x045f, B:149:0x0466, B:151:0x046c, B:153:0x0472, B:154:0x0479, B:156:0x047f, B:157:0x0487, B:159:0x048d, B:161:0x0499, B:163:0x04a5), top: B:190:0x0454 }] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x047f A[Catch: all -> 0x04bc, TryCatch #5 {all -> 0x04bc, blocks: (B:146:0x0454, B:148:0x045f, B:149:0x0466, B:151:0x046c, B:153:0x0472, B:154:0x0479, B:156:0x047f, B:157:0x0487, B:159:0x048d, B:161:0x0499, B:163:0x04a5), top: B:190:0x0454 }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0284 A[Catch: all -> 0x02bb, TryCatch #4 {all -> 0x02bb, blocks: (B:73:0x0262, B:75:0x0268, B:77:0x0272, B:78:0x027e, B:80:0x0284, B:82:0x0290, B:84:0x02a0), top: B:188:0x0262 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void setupParams(com.jingdong.jdsdk.network.toolbox.HttpRequest r18) {
        /*
            Method dump skipped, instructions count: 1254
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdsdk.network.toolbox.ParamBuilderForThirdApp.setupParams(com.jingdong.jdsdk.network.toolbox.HttpRequest):void");
    }

    private static String signatureFromJava(HttpSetting httpSetting, String str) {
        String functionId = httpSetting.getFunctionId();
        String secretKey = httpSetting.getSecretKey();
        if (TextUtils.isEmpty(secretKey)) {
            secretKey = JDHttpTookit.getEngine().getSecretKey();
        }
        if (!TextUtils.isEmpty(functionId) && !TextUtils.isEmpty(secretKey)) {
            if (OKLog.D) {
                OKLog.d(TAG, "id:" + httpSetting.getId() + "- ..functionId -->> " + functionId);
            }
            try {
                String url = httpSetting.getUrl();
                String signature2 = GatewaySignatureHelper.signature2(url, str, secretKey, getCustomBodyParam(httpSetting));
                if (OKLog.D) {
                    OKLog.d(TAG, "id:" + httpSetting.getId() + "- ..url str -->> " + url);
                    OKLog.d(TAG, "id:" + httpSetting.getId() + "- ..sign str -->> " + signature2);
                }
                return signature2;
            } catch (Exception unused) {
            }
        }
        return "";
    }
}
