package com.jingdong.common.babelrn.utils;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.babelrn.entity.BabelNativeInfo;
import com.jingdong.common.babelrn.view.BabelRNFragment;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.WebViewHelper;
import com.jingdong.common.web.MKeyNames;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.net.URLDecoder;

/* loaded from: classes5.dex */
public class BabelCheckNativeUtil {
    private static String TAG = "BabelCheckNativeUtil";

    /* loaded from: classes5.dex */
    public interface OnCheckNativeListener {
        void onFailure();

        void onFinish(BabelNativeInfo babelNativeInfo);
    }

    public static void checkM2Native(Context context, String str, OnCheckNativeListener onCheckNativeListener) {
        Log.d(TAG, "checkM2Native:" + str);
        try {
            String decode = URLDecoder.decode(str, "utf-8");
            String babelActivityId = WebViewHelper.getBabelActivityId(decode);
            if (TextUtils.isEmpty(babelActivityId)) {
                if (onCheckNativeListener != null) {
                    onCheckNativeListener.onFailure();
                }
            } else if (decode.contains("has_native")) {
                if (decode.contains("has_native=0")) {
                    if (onCheckNativeListener != null) {
                        onCheckNativeListener.onFailure();
                    }
                } else if (decode.contains("has_native=1")) {
                    Log.d(TAG, "M2Native \u901a\u5929\u5854");
                    BabelNativeInfo babelNativeInfo = new BabelNativeInfo();
                    babelNativeInfo.url = decode;
                    babelNativeInfo.isNative = "1";
                    if (onCheckNativeListener != null) {
                        onCheckNativeListener.onFinish(babelNativeInfo);
                    }
                } else {
                    queryNative(context, babelActivityId, decode, onCheckNativeListener);
                }
            } else {
                queryNative(context, babelActivityId, decode, onCheckNativeListener);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (onCheckNativeListener != null) {
                onCheckNativeListener.onFailure();
            }
        }
    }

    private static boolean isForceShowM(String str) {
        try {
            return TextUtils.equals("0", Uri.parse(str).getQueryParameter("has_native"));
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    private static void queryNative(final Context context, String str, final String str2, final OnCheckNativeListener onCheckNativeListener) {
        Log.d(TAG, "queryNative,activityId=" + str + "  url=" + str2);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId(MKeyNames.SWITCH_QUERY_NATIVE);
        httpSetting.setHost(Configuration.getNgwHost());
        httpSetting.setEffect(0);
        httpSetting.setNotifyUser(false);
        httpSetting.setConnectTimeout(5000);
        httpSetting.putJsonParam("activityId", str);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.putJsonParam("dogeVersion", AbstractJDReactInitialHelper.getJDReactFrameworkVersion());
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.babelrn.utils.BabelCheckNativeUtil.1
            private long endTime;
            private long startTime;

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                if (fastJsonObject == null) {
                    OnCheckNativeListener onCheckNativeListener2 = OnCheckNativeListener.this;
                    if (onCheckNativeListener2 != null) {
                        onCheckNativeListener2.onFailure();
                        return;
                    }
                    return;
                }
                this.endTime = System.currentTimeMillis();
                try {
                    String string = fastJsonObject.getString("code");
                    String string2 = fastJsonObject.getString("subCode");
                    if (string.equals("0") && string2.equals("0")) {
                        BabelCheckNativeUtil.sendQueryNativeMtaData(context, str2, String.valueOf(this.startTime), String.valueOf(this.endTime), CartConstant.KEY_CART_TEXTINFO_FINISH);
                        BabelNativeInfo babelNativeInfo = new BabelNativeInfo();
                        babelNativeInfo.url = str2;
                        babelNativeInfo.isNative = fastJsonObject.optString("isNative", "");
                        babelNativeInfo.setDoge(fastJsonObject.optString(BabelRNFragment.DOGE, ""));
                        OnCheckNativeListener onCheckNativeListener3 = OnCheckNativeListener.this;
                        if (onCheckNativeListener3 != null) {
                            onCheckNativeListener3.onFinish(babelNativeInfo);
                            return;
                        }
                        return;
                    }
                    BabelCheckNativeUtil.sendQueryNativeMtaData(context, str2, String.valueOf(this.startTime), String.valueOf(this.endTime), "fail");
                    OnCheckNativeListener onCheckNativeListener4 = OnCheckNativeListener.this;
                    if (onCheckNativeListener4 != null) {
                        onCheckNativeListener4.onFailure();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                Log.d(BabelCheckNativeUtil.TAG, "queryNative error:" + httpError);
                this.endTime = System.currentTimeMillis();
                BabelCheckNativeUtil.sendQueryNativeMtaData(context, str2, String.valueOf(this.startTime), String.valueOf(this.endTime), "fail");
                OnCheckNativeListener onCheckNativeListener2 = OnCheckNativeListener.this;
                if (onCheckNativeListener2 != null) {
                    onCheckNativeListener2.onFailure();
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                this.startTime = System.currentTimeMillis();
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void sendQueryNativeMtaData(Context context, String str, String str2, String str3, String str4) {
        JDMtaUtils.sendWebviewLoadData(context, "CommonMFragment", "", "querynative", str, str2, str3, str4);
    }
}
