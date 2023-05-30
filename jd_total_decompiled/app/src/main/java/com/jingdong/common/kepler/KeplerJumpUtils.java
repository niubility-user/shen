package com.jingdong.common.kepler;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.ActivityManagerUtil;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.web.entity.WebEntity;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.MyCountdownTimer;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.reflect.Method;

/* loaded from: classes5.dex */
public class KeplerJumpUtils {
    private static final int BACK_FLAG = 1;
    private static final int CLOSE_FLAG = 2;
    public static final long COUNTDOWN_INTERVAL_TIME = 1000;
    public static final int COUNTDOWN_KEPLER = 4;
    public static final long COUNTDOWN_TOTAL_TIME = 600000;
    private static final int EXPO_FLAG = 0;
    public static final String KEY_IS_JUMP_FROM = "self_params_isKeplerJump";
    private static final String TAG = "KeplerJumpUtils";
    private static boolean hasShownRetainView;
    public static KeplerJumpInfo info;
    private static KeplerJumpUtils instance;
    public static boolean isOpeningMiniProductPage;
    private static boolean isRequestOpenRetainView;
    public static String keplerID;
    private static MyCountdownTimer mMyCountdownTimer;
    private static ShowOrHideCallback mShowOrHideCallback;
    private static int maxHeight;
    public static KeplerRetainInfo retainInfo;
    private static int thirdOneHeight;
    public static String urlWithoutParam;

    /* loaded from: classes5.dex */
    public interface ShowOrHideCallback {
        void showOrHide(boolean z);
    }

    private KeplerJumpUtils() {
        if (mMyCountdownTimer == null) {
            mMyCountdownTimer = new MyCountdownTimer(600000L, 1000L, 4) { // from class: com.jingdong.common.kepler.KeplerJumpUtils.1
                {
                    KeplerJumpUtils.this = this;
                }

                @Override // com.jingdong.jdsdk.utils.MyCountdownTimer
                public void onFinish(int i2) {
                    KeplerJumpUtils.cancelShow();
                    if (KeplerJumpUtils.mShowOrHideCallback != null) {
                        KeplerJumpUtils.mShowOrHideCallback.showOrHide(false);
                    }
                }

                @Override // com.jingdong.jdsdk.utils.MyCountdownTimer
                public void onTick(long j2, int i2) {
                }
            };
        }
    }

    public static void backToThirdApp(Activity activity, ViewGroup viewGroup, DragView dragView) {
        Intent launchIntentForPackage;
        if (info != null) {
            try {
                if (activity != null) {
                    boolean z = false;
                    if (OKLog.D) {
                        OKLog.d(TAG, "info.protocol:" + info.protocol);
                    }
                    if (!TextUtils.isEmpty(info.protocol)) {
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.setData(Uri.parse(info.protocol));
                        intent.setFlags(805306368);
                        try {
                            activity.startActivity(intent);
                            z = true;
                        } catch (ActivityNotFoundException e2) {
                            if (OKLog.E) {
                                OKLog.e(TAG, e2);
                            }
                        }
                    }
                    if (!z && (launchIntentForPackage = activity.getPackageManager().getLaunchIntentForPackage(info.packageName)) != null) {
                        activity.startActivity(launchIntentForPackage);
                    }
                } else {
                    hideFlowBackView(viewGroup, dragView);
                }
            } catch (Exception e3) {
                OKLog.e(TAG, e3);
            }
            cancelShow();
        }
    }

    public static boolean canShownRetainView() {
        if (retainInfo != null) {
            return !TextUtils.isEmpty(r0.url);
        }
        return false;
    }

    public static void cancelShow() {
        KeplerJumpInfo keplerJumpInfo = info;
        if (keplerJumpInfo != null) {
            keplerJumpInfo.isShow = false;
        }
        MyCountdownTimer myCountdownTimer = mMyCountdownTimer;
        if (myCountdownTimer != null) {
            myCountdownTimer.cancel(4);
        }
    }

    public static void clearData() {
        keplerID = "";
        urlWithoutParam = "";
        info = null;
    }

    public static void closeRetainView(Activity activity) {
        if (activity == null || !(activity instanceof BaseActivity)) {
            return;
        }
        try {
            Class<?> cls = Class.forName("com.jingdong.common.XView.RetainXViewHelper");
            cls.getMethod("closeRetainXView", new Class[0]).invoke(cls, new Object[0]);
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(TAG, "Exception:" + e2.toString());
            }
        }
    }

    public static void createInfo(String str, int i2, String str2, String str3, String str4, String str5) {
        KeplerJumpInfo keplerJumpInfo = new KeplerJumpInfo();
        info = keplerJumpInfo;
        keplerJumpInfo.keplerID = keplerID;
        keplerJumpInfo.appName = str;
        keplerJumpInfo.isShow = i2 - 1 == 0;
        keplerJumpInfo.packageName = str2;
        keplerJumpInfo.protocol = str3;
        keplerJumpInfo.isMtaReport = false;
        keplerJumpInfo.picLogo = str4;
        keplerJumpInfo.backPic = str5;
    }

    public static void createRetentionInfo(String str, int i2, String str2, String str3, String str4) {
        KeplerRetainInfo keplerRetainInfo = new KeplerRetainInfo();
        retainInfo = keplerRetainInfo;
        keplerRetainInfo.url = str;
        keplerRetainInfo.floorId = i2;
        keplerRetainInfo.expo = str2;
        keplerRetainInfo.clkUrl = str3;
        keplerRetainInfo.expoUrl = str4;
    }

    public static synchronized KeplerJumpUtils getInstance() {
        KeplerJumpUtils keplerJumpUtils;
        synchronized (KeplerJumpUtils.class) {
            if (instance == null) {
                instance = new KeplerJumpUtils();
            }
            int height = DPIUtil.getHeight();
            maxHeight = height;
            thirdOneHeight = height / 3;
            maxHeight = height - 50;
            keplerJumpUtils = instance;
        }
        return keplerJumpUtils;
    }

    public static boolean hasShownRetainView() {
        return hasShownRetainView;
    }

    public static synchronized void hideFlowBackView(ViewGroup viewGroup, DragView dragView) {
        synchronized (KeplerJumpUtils.class) {
            if (OKLog.D) {
                OKLog.d(TAG, "hideFlowBackView() rootView = " + viewGroup);
                OKLog.d(TAG, "hideFlowBackView() dragView = " + dragView);
            }
            if (dragView != null) {
                dragView.setVisibility(8);
                removeFlowBackView(viewGroup, dragView);
            }
        }
    }

    public static void initData(Bundle bundle) {
        keplerID = bundle.getString("keplerID");
        urlWithoutParam = bundle.getString("urlWithoutParam");
    }

    private DragView initFlowBackView(final Activity activity, final ViewGroup viewGroup) {
        final DragView dragView = null;
        if (activity != null && viewGroup != null) {
            if (TextUtils.isEmpty(info.backPic) && TextUtils.isEmpty(info.picLogo) && TextUtils.isEmpty(info.appName)) {
                return null;
            }
            dragView = new DragView(activity);
            KeplerJumpInfo keplerJumpInfo = info;
            int i2 = keplerJumpInfo.posY;
            if (i2 < 50 || i2 > maxHeight) {
                keplerJumpInfo.posY = thirdOneHeight;
            }
            dragView.setInfo(activity, keplerJumpInfo);
            dragView.setY(info.posY);
            dragView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.kepler.KeplerJumpUtils.5
                {
                    KeplerJumpUtils.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    KeplerJumpUtils.this.keplerMta(activity, 1);
                    if (KeplerJumpUtils.canShownRetainView()) {
                        if (!KeplerJumpUtils.hasShownRetainView && !KeplerJumpUtils.isRequestOpenRetainView) {
                            KeplerJumpUtils.openRetainView(activity, viewGroup, dragView);
                            return;
                        } else {
                            KeplerJumpUtils.backToThirdApp(activity, viewGroup, dragView);
                            return;
                        }
                    }
                    KeplerJumpUtils.backToThirdApp(activity, viewGroup, dragView);
                }
            });
            dragView.setDragViewCallBack(new DragViewCallBack() { // from class: com.jingdong.common.kepler.KeplerJumpUtils.6
                {
                    KeplerJumpUtils.this = this;
                }

                @Override // com.jingdong.common.kepler.DragViewCallBack
                public void hide() {
                    KeplerJumpUtils.cancelShow();
                    KeplerJumpUtils.hideFlowBackView(viewGroup, dragView);
                }
            });
            dragView.setRootView(viewGroup);
        }
        return dragView;
    }

    public static boolean isIsRequestOpenRetainView() {
        return isRequestOpenRetainView;
    }

    public void keplerMta(Activity activity, int i2) {
        KeplerJumpInfo keplerJumpInfo = info;
        String str = keplerJumpInfo == null ? "" : keplerJumpInfo.appName;
        if (i2 == 0) {
            if (keplerJumpInfo.isMtaReport) {
                return;
            }
            JDMtaUtils.sendCommonData(activity, "KeplerBack_TagExpo", str, "", "", "", "", "", "Kepler_BackTag");
            info.isMtaReport = true;
        } else if (i2 == 1) {
            JDMtaUtils.sendCommonData(activity, "KeplerBack_Back", str, "onClick", "", "", "", "", "Kepler_BackTag");
        } else if (i2 != 2) {
        } else {
            JDMtaUtils.sendCommonData(activity, "KeplerBack_CloseTag", str, "onClick", "", "", "", "", "Kepler_BackTag");
        }
    }

    public static void openRetainView(Activity activity, ViewGroup viewGroup, DragView dragView) {
        KeplerRetainInfo keplerRetainInfo;
        if (activity == null || !(activity instanceof BaseActivity) || (keplerRetainInfo = retainInfo) == null || TextUtils.isEmpty(keplerRetainInfo.url)) {
            return;
        }
        if (Log.D) {
            Log.d(TAG, "openRetainView");
        }
        isRequestOpenRetainView = true;
        try {
            Class<?> cls = Class.forName("com.jingdong.common.XView.RetainXViewHelper");
            Method method = cls.getMethod("showRetainXView", BaseActivity.class, ViewGroup.class, DragView.class, String.class, String.class);
            KeplerRetainInfo keplerRetainInfo2 = retainInfo;
            method.invoke(cls, activity, viewGroup, dragView, keplerRetainInfo2.url, keplerRetainInfo2.expoUrl);
        } catch (Exception e2) {
            Log.d(TAG, "Exception:" + e2.toString());
        }
    }

    private static String parseKeplerBackParam(String str) {
        int i2;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String encodedQuery = Uri.parse(str).getEncodedQuery();
        if (TextUtils.isEmpty(encodedQuery)) {
            return "";
        }
        int length = encodedQuery.length();
        int indexOf = encodedQuery.indexOf("params=");
        return (indexOf < 0 || (i2 = indexOf + 7) > length) ? "" : encodedQuery.substring(i2, length);
    }

    private static synchronized void removeFlowBackView(ViewGroup viewGroup, DragView dragView) {
        synchronized (KeplerJumpUtils.class) {
            if (dragView != null && viewGroup != null) {
                viewGroup.removeView(dragView);
                OKLog.d(TAG, "removeFlowBackView() dragView = " + ((Object) null));
            }
        }
    }

    public static void setHasShownRetainView(boolean z) {
        hasShownRetainView = z;
    }

    public static void setIsRequestOpenRetainView(boolean z) {
        isRequestOpenRetainView = z;
    }

    private synchronized DragView showFlowBackView(Activity activity, ViewGroup viewGroup) {
        KeplerJumpInfo keplerJumpInfo = info;
        if (keplerJumpInfo != null && keplerJumpInfo.isShow && viewGroup != null) {
            DragView initFlowBackView = initFlowBackView(activity, viewGroup);
            if (initFlowBackView == null) {
                return null;
            }
            initFlowBackView.setY(info.posY);
            initFlowBackView.setVisibility(0);
            viewGroup.addView(initFlowBackView, new ViewGroup.LayoutParams(-2, -2));
            MyCountdownTimer myCountdownTimer = mMyCountdownTimer;
            if (myCountdownTimer != null && !myCountdownTimer.mStart) {
                myCountdownTimer.reset(600000L, 1000L, 4);
            }
            keplerMta(activity, 0);
            return initFlowBackView;
        }
        return null;
    }

    public static void showKeplerDragView() {
        final Activity currentActivity = ActivityManagerUtil.getScreenManager().currentActivity();
        if (currentActivity instanceof BaseActivity) {
            currentActivity.runOnUiThread(new Runnable() { // from class: com.jingdong.common.kepler.KeplerJumpUtils.2
                @Override // java.lang.Runnable
                public void run() {
                    ((BaseActivity) currentActivity).showKeplerDragView();
                }
            });
        }
    }

    public static void tryGetKeplerData(HttpGroup httpGroup, Intent intent, final KeplerDataCallBack keplerDataCallBack) {
        if (httpGroup == null || intent == null) {
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "tryGotKeplerData  KeplerJumpUtils.keplerID:" + keplerID);
        }
        if (OKLog.D) {
            OKLog.d(TAG, "tryGetKeplerData KeplerJumpUtils.urlWithoutParam:" + urlWithoutParam);
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("getConfigKvNew");
        httpSetting.setHost(Configuration.getNgwHost());
        httpSetting.setEffect(0);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.putJsonParam("buildId", PackageInfoUtil.getVersionCode() + "");
        if (TextUtils.equals(keplerID, WebEntity.VALUE_ONEKEYLOGIN_KEPLER)) {
            httpSetting.putJsonParam("type", parseKeplerBackParam(urlWithoutParam));
        } else {
            httpSetting.putJsonParam("type", keplerID);
        }
        httpSetting.setListener(new HttpGroup.CustomOnAllListener() { // from class: com.jingdong.common.kepler.KeplerJumpUtils.4
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JDJSONObject optJSONObject;
                JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                if (fastJsonObject != null && fastJsonObject.size() > 0 && (optJSONObject = fastJsonObject.optJSONObject("configs")) != null && optJSONObject.size() > 0) {
                    String optString = optJSONObject.optString("name");
                    String optString2 = optJSONObject.optString("bundleID");
                    int optInt = optJSONObject.optInt("type");
                    String optString3 = optJSONObject.optString("protocol");
                    String optString4 = optJSONObject.optString("piclogo");
                    String optString5 = optJSONObject.optString("backpic");
                    KeplerJumpUtils.createInfo(optString, optInt, optString2, optString3, optString4, optString5);
                    if (OKLog.D) {
                        OKLog.d(KeplerJumpUtils.TAG, "tryGotKeplerData name:" + optString);
                        OKLog.d(KeplerJumpUtils.TAG, "tryGotKeplerData packageName:" + optString2);
                        OKLog.d(KeplerJumpUtils.TAG, "tryGotKeplerData type:" + optInt);
                        OKLog.d(KeplerJumpUtils.TAG, "tryGotKeplerData protocol:" + optString3);
                        OKLog.d(KeplerJumpUtils.TAG, "tryGotKeplerData picLogo:" + optString4);
                        OKLog.d(KeplerJumpUtils.TAG, "tryGotKeplerData backPic:" + optString5);
                    }
                    KeplerDataCallBack keplerDataCallBack2 = keplerDataCallBack;
                    if (keplerDataCallBack2 != null) {
                        keplerDataCallBack2.onDataSuccess();
                        return;
                    }
                    return;
                }
                KeplerDataCallBack keplerDataCallBack3 = keplerDataCallBack;
                if (keplerDataCallBack3 != null) {
                    keplerDataCallBack3.onDataFail();
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                KeplerDataCallBack keplerDataCallBack2 = keplerDataCallBack;
                if (keplerDataCallBack2 != null) {
                    keplerDataCallBack2.onDataFail();
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        httpGroup.add(httpSetting);
    }

    public static void tryGetRetainInfo(HttpGroup httpGroup) {
        if (httpGroup == null) {
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "tryGetRetainInfo");
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("xViewInfo");
        httpSetting.setHost(Configuration.getNgwHost());
        httpSetting.setEffect(0);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setListener(new HttpGroup.CustomOnAllListener() { // from class: com.jingdong.common.kepler.KeplerJumpUtils.3
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JDJSONObject optJSONObject;
                JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                if (fastJsonObject != null && (optJSONObject = fastJsonObject.optJSONObject("data")) != null) {
                    String optString = optJSONObject.optString("url");
                    int optInt = optJSONObject.optInt("floorId");
                    String optString2 = optJSONObject.optString("expo");
                    String optString3 = optJSONObject.optString("clkUrl");
                    String optString4 = optJSONObject.optString("expoUrl");
                    if (OKLog.D) {
                        OKLog.d(KeplerJumpUtils.TAG, "tryGetXViewInfo url:" + optString);
                        OKLog.d(KeplerJumpUtils.TAG, "tryGetXViewInfo floorId:" + optInt);
                        OKLog.d(KeplerJumpUtils.TAG, "tryGetXViewInfo expo:" + optString2);
                        OKLog.d(KeplerJumpUtils.TAG, "tryGetXViewInfo clkUrl:" + optString3);
                        OKLog.d(KeplerJumpUtils.TAG, "tryGetXViewInfo expoUrl:" + optString4);
                    }
                    Activity currentActivity = ActivityManagerUtil.getScreenManager().currentActivity();
                    if (currentActivity != null) {
                        JDMtaUtils.sendCommonData(currentActivity, "KeplerBack_ToastCheck", TextUtils.isEmpty(optString) ? "0" : optString, "", "", "", "", "", "MbackFlow");
                    }
                    KeplerJumpUtils.createRetentionInfo(optString, optInt, optString2, optString3, optString4);
                }
                KeplerJumpUtils.showKeplerDragView();
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                if (Log.D) {
                    Log.d(KeplerJumpUtils.TAG, "tryGetRetainInfo --- onError");
                }
                Activity currentActivity = ActivityManagerUtil.getScreenManager().currentActivity();
                if (currentActivity != null) {
                    JDMtaUtils.sendCommonData(currentActivity, "KeplerBack_ToastCheck", "-100", "", "", "", "", "", "MbackFlow");
                }
                KeplerJumpUtils.showKeplerDragView();
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        httpGroup.add(httpSetting);
    }

    public static synchronized void tryHideFlowBackView(ViewGroup viewGroup, DragView dragView) {
        synchronized (KeplerJumpUtils.class) {
            if (OKLog.D) {
                OKLog.d(TAG, "tryHideFlowBackView() info = " + info);
                OKLog.d(TAG, "tryHideFlowBackView() dragView = " + dragView);
            }
            if (info != null && dragView != null) {
                if (instance == null) {
                    instance = getInstance();
                }
                hideFlowBackView(viewGroup, dragView);
            }
        }
    }

    public static DragView tryShowFlowBackView(Activity activity, ViewGroup viewGroup, ShowOrHideCallback showOrHideCallback) {
        if (OKLog.D) {
            OKLog.d(TAG, "tryShowFlowBackView() activity = " + activity + " -->>  rootView " + viewGroup);
            OKLog.d(TAG, "tryShowFlowBackView() instance = " + instance + " -->>  info " + info);
        }
        if (info != null) {
            if (instance == null) {
                instance = getInstance();
            }
            mShowOrHideCallback = showOrHideCallback;
            return instance.showFlowBackView(activity, viewGroup);
        }
        return null;
    }

    public void setInfoPosY(DragView dragView, int i2) {
        KeplerJumpInfo keplerJumpInfo = info;
        if (keplerJumpInfo != null) {
            keplerJumpInfo.posY = i2;
            if (dragView != null) {
                dragView.setY(i2);
            }
        }
    }
}
