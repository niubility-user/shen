package com.jingdong.common.jdreactFramework;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import androidx.collection.ArrayMap;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.jd.framework.json.JDJSON;
import com.jingdong.common.jdreactFramework.download.JDReactHttpSetting;
import com.jingdong.common.jdreactFramework.download.ReactNativeUpdate;
import com.jingdong.common.jdreactFramework.helper.ElderModeHelper;
import com.jingdong.common.jdreactFramework.helper.ExceptionReporter;
import com.jingdong.common.jdreactFramework.helper.InitErrorCustomizer;
import com.jingdong.common.jdreactFramework.helper.UIModeHelper;
import com.jingdong.common.jdreactFramework.lifecycle.RNLifeCycleObserver;
import com.jingdong.common.jdreactFramework.listener.DataReportListener;
import com.jingdong.common.jdreactFramework.track.TrackListener;
import com.jingdong.common.jdreactFramework.utils.JDReactCommonException;
import com.jingdong.common.jdreactFramework.utils.JLog;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JDReactHelper {
    private static String TAG = "JDReactHelper";
    private static int isBackground = 0;
    private static boolean isIndependent = false;
    private static Application mApplication = null;
    private static Context mApplicationContext = null;
    private static InitErrorCustomizer mInitErrorCustomizer = null;
    private static JDReactHelper mJDReactHelper = null;
    private static JDReactHelperCallback mJDReactHelperCallback = null;
    private static String mMetaData = "getMetaData";
    private static String mNativeVerionAPI = "getReactNativeVersion";
    private static TrackListener mTrackListener;
    private DataReportListener mDataReportListener;
    private ExceptionReporter mExceptionReporter;
    public boolean mIsDebug;
    private int netCountDown = 0;

    /* loaded from: classes5.dex */
    public interface AddressCallBack {
        void suc(HashMap<String, Object> hashMap);
    }

    /* loaded from: classes5.dex */
    public interface JDReactHelperCallback {
        void changeDebug(boolean z);

        void changeUserRNTools(boolean z);

        String getAndroidId();

        int getCacheMode(String str);

        Activity getCurrentMyActivity();

        float getDensity(Context context);

        String getDeprecatedVersion(String str);

        String getDisplayMetrics();

        DisplayMetrics getDisplayMetricsObject(Context context);

        int getEffect(String str);

        ElderModeHelper getElderModeHelper();

        String getIpAddressFromWifiInfo(Context context);

        JDReactHttpSetting.AbstractJDReactHttpSetting getJDreactHttpSetting();

        View getLoadingLottieView();

        String[] getLocation();

        void getLocationLatLng(String str, String str2, AddressCallBack addressCallBack);

        String getNetAddressesForIPv4();

        String getNetworkOperator(Context context);

        String getNetworkOperatorName(Context context);

        String getNetworkType();

        float getScaledDensity(Context context);

        int getScreenHeight();

        int getScreenWidth();

        int getSpace(String str);

        int getType(String str);

        UIModeHelper getUIModeHelper();

        String getUnableAnimationKey();

        String getVirtualHost(String str);

        boolean isAgreedPrivacy();

        boolean isBeta();

        boolean isDebug();

        boolean isDiffUpdate();

        boolean isPreloadCommon();

        boolean isUserRNTools();

        void jumpWithOpenapp(String str, Context context);

        void jumptoWebPage(Context context, String str, Intent intent);

        boolean launchLogin(Context context, JDReactLoginCallback jDReactLoginCallback);

        void postException(Exception exc, ArrayMap arrayMap);

        void postException(String str);

        void postException(Throwable th);

        void postRNMonitorData(HashMap hashMap);

        void recycleLoadingLottieView(View view);

        void requestPermission(Activity activity, HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

        void sendCommonData(String str, String str2);

        void sendMsgToJs(Context context, String str, HashMap hashMap);

        void setExposureMta(HashMap hashMap, String str);

        boolean showBundleInfo();

        void showErrorRetryView(View.OnClickListener onClickListener, ViewGroup viewGroup);

        void showLongToast(String str);

        void showShortToast(String str);

        void startActivityInFrameWithNoNavigation(Intent intent, Context context);

        boolean useDownloadQueue();

        boolean useHttp();
    }

    /* loaded from: classes5.dex */
    public interface JDReactLoginCallback {
        void onSuccess(String str);
    }

    public static int getBackground() {
        return isBackground;
    }

    public static JDReactHelper newInstance() {
        if (mJDReactHelper == null) {
            mJDReactHelper = new JDReactHelper();
        }
        return mJDReactHelper;
    }

    public static void setBackground(int i2) {
        isBackground = i2;
    }

    public void changeDebug(boolean z) {
        JDReactHelperCallback jDReactHelperCallback = mJDReactHelperCallback;
        if (jDReactHelperCallback != null) {
            jDReactHelperCallback.changeDebug(z);
        }
    }

    public void changeUserRNTools(boolean z) {
        JDReactHelperCallback jDReactHelperCallback = mJDReactHelperCallback;
        if (jDReactHelperCallback != null) {
            jDReactHelperCallback.changeUserRNTools(z);
        }
    }

    public void clearCache() {
        ReactNativeUpdate.getInstance().reactUnzipSo();
    }

    public String getAndroidId() {
        JDReactHelperCallback jDReactHelperCallback = mJDReactHelperCallback;
        return jDReactHelperCallback == null ? "" : jDReactHelperCallback.getAndroidId();
    }

    public Application getApplication() {
        return mApplication;
    }

    public Context getApplicationContext() {
        StringBuilder sb = new StringBuilder();
        sb.append("setted");
        sb.append(mApplicationContext == null);
        sb.toString();
        return mApplicationContext;
    }

    public int getCacheMode(String str) {
        return mJDReactHelperCallback.getCacheMode(str);
    }

    public Activity getCurrentMyActivity() {
        return mJDReactHelperCallback.getCurrentMyActivity();
    }

    public DataReportListener getDataReportListener() {
        return this.mDataReportListener;
    }

    public float getDensity(Context context) {
        JDReactHelperCallback jDReactHelperCallback = mJDReactHelperCallback;
        if (jDReactHelperCallback == null) {
            return 0.0f;
        }
        return jDReactHelperCallback.getDensity(context);
    }

    public String getDeprecatedVersion(String str) {
        JDReactHelperCallback jDReactHelperCallback = mJDReactHelperCallback;
        return jDReactHelperCallback != null ? jDReactHelperCallback.getDeprecatedVersion(str) : "";
    }

    public String getDisplayMetrics() {
        JDReactHelperCallback jDReactHelperCallback = mJDReactHelperCallback;
        return jDReactHelperCallback == null ? "" : jDReactHelperCallback.getDisplayMetrics();
    }

    public DisplayMetrics getDisplayMetricsObject(Context context) {
        JDReactHelperCallback jDReactHelperCallback = mJDReactHelperCallback;
        if (jDReactHelperCallback == null) {
            return null;
        }
        return jDReactHelperCallback.getDisplayMetricsObject(context);
    }

    public int getEffect(String str) {
        return mJDReactHelperCallback.getEffect(str);
    }

    public ElderModeHelper getElderModeHelper() {
        JDReactHelperCallback jDReactHelperCallback = mJDReactHelperCallback;
        if (jDReactHelperCallback == null) {
            return null;
        }
        return jDReactHelperCallback.getElderModeHelper();
    }

    public ExceptionReporter getExceptionReporter() {
        return this.mExceptionReporter;
    }

    public JDReactHttpSetting getHttpSetting() {
        JDReactHttpSetting jDReactHttpSetting = new JDReactHttpSetting();
        jDReactHttpSetting.setAbstractJDReactHttpSetting(mJDReactHelperCallback.getJDreactHttpSetting());
        return jDReactHttpSetting;
    }

    public InitErrorCustomizer getInitErrorCustomizer() {
        return mInitErrorCustomizer;
    }

    public String getIpAddressFromWifiInfo(Context context) {
        JDReactHelperCallback jDReactHelperCallback = mJDReactHelperCallback;
        return jDReactHelperCallback == null ? "" : jDReactHelperCallback.getIpAddressFromWifiInfo(context);
    }

    public View getLoadingLottieView() {
        return mJDReactHelperCallback.getLoadingLottieView();
    }

    public String[] getLocation() {
        return mJDReactHelperCallback.getLocation();
    }

    public void getLocationLatLng(String str, String str2, AddressCallBack addressCallBack) {
        JDReactHelperCallback jDReactHelperCallback = mJDReactHelperCallback;
        if (jDReactHelperCallback != null) {
            jDReactHelperCallback.getLocationLatLng(str, str2, addressCallBack);
        }
    }

    public String getNativeMetaData() {
        return mMetaData;
    }

    public String getNativeVerionAPI() {
        return mNativeVerionAPI;
    }

    public String getNetAddressesForIPv4() {
        JDReactHelperCallback jDReactHelperCallback = mJDReactHelperCallback;
        if (jDReactHelperCallback == null) {
            return null;
        }
        return jDReactHelperCallback.getNetAddressesForIPv4();
    }

    public int getNetCountDownMills() {
        return this.netCountDown;
    }

    public String getNetworkOperator(Context context) {
        JDReactHelperCallback jDReactHelperCallback = mJDReactHelperCallback;
        return jDReactHelperCallback == null ? "" : jDReactHelperCallback.getNetworkOperator(context);
    }

    public String getNetworkOperatorName(Context context) {
        JDReactHelperCallback jDReactHelperCallback = mJDReactHelperCallback;
        return jDReactHelperCallback == null ? "" : jDReactHelperCallback.getNetworkOperatorName(context);
    }

    public String getNetworkType() {
        JDReactHelperCallback jDReactHelperCallback = mJDReactHelperCallback;
        return jDReactHelperCallback != null ? jDReactHelperCallback.getNetworkType() : "";
    }

    public float getScaledDensity(Context context) {
        JDReactHelperCallback jDReactHelperCallback = mJDReactHelperCallback;
        if (jDReactHelperCallback == null) {
            return 0.0f;
        }
        return jDReactHelperCallback.getScaledDensity(context);
    }

    public int getScreenHeight() {
        JDReactHelperCallback jDReactHelperCallback = mJDReactHelperCallback;
        if (jDReactHelperCallback == null) {
            return 0;
        }
        return jDReactHelperCallback.getScreenHeight();
    }

    public int getScreenWidth() {
        JDReactHelperCallback jDReactHelperCallback = mJDReactHelperCallback;
        if (jDReactHelperCallback == null) {
            return 0;
        }
        return jDReactHelperCallback.getScreenWidth();
    }

    public int getSpace(String str) {
        return mJDReactHelperCallback.getSpace(str);
    }

    public TrackListener getTrackListener() {
        return mTrackListener;
    }

    public int getType(String str) {
        return mJDReactHelperCallback.getType(str);
    }

    public UIModeHelper getUIModeHelper() {
        JDReactHelperCallback jDReactHelperCallback = mJDReactHelperCallback;
        if (jDReactHelperCallback == null) {
            return null;
        }
        return jDReactHelperCallback.getUIModeHelper();
    }

    public String getUnableAnimationKey() {
        return mJDReactHelperCallback.getUnableAnimationKey();
    }

    public String getVirtualHost(String str) {
        return mJDReactHelperCallback.getVirtualHost(str);
    }

    public void init(Application application, boolean z) {
        mApplication = application;
        if (application != null) {
            mApplicationContext = application.getApplicationContext();
        }
        this.mIsDebug = z;
        JDJSON.init(z);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(new RNLifeCycleObserver());
    }

    public boolean isAgreedPrivacy() {
        JDReactHelperCallback jDReactHelperCallback = mJDReactHelperCallback;
        if (jDReactHelperCallback == null) {
            return false;
        }
        return jDReactHelperCallback.isAgreedPrivacy();
    }

    public boolean isBeta() {
        return mJDReactHelperCallback.isBeta();
    }

    public boolean isDebug() {
        JDReactHelperCallback jDReactHelperCallback = mJDReactHelperCallback;
        if (jDReactHelperCallback == null) {
            return false;
        }
        return jDReactHelperCallback.isDebug();
    }

    public boolean isDiffUpdate() {
        JDReactHelperCallback jDReactHelperCallback = mJDReactHelperCallback;
        if (jDReactHelperCallback != null) {
            return jDReactHelperCallback.isDiffUpdate();
        }
        return false;
    }

    public boolean isIndependent() {
        return isIndependent;
    }

    public boolean isPreloadCommon() {
        JDReactHelperCallback jDReactHelperCallback = mJDReactHelperCallback;
        if (jDReactHelperCallback == null) {
            return false;
        }
        return jDReactHelperCallback.isPreloadCommon();
    }

    public boolean isUserRNTools() {
        JDReactHelperCallback jDReactHelperCallback = mJDReactHelperCallback;
        if (jDReactHelperCallback == null) {
            return false;
        }
        return jDReactHelperCallback.isUserRNTools();
    }

    public void jumpWithOpenapp(String str, Context context) {
        mJDReactHelperCallback.jumpWithOpenapp(str, context);
    }

    public void jumptoWebPage(Context context, String str, Intent intent) {
        mJDReactHelperCallback.jumptoWebPage(context, str, intent);
    }

    public boolean launchLogin(Context context, JDReactLoginCallback jDReactLoginCallback) {
        return mJDReactHelperCallback.launchLogin(context, jDReactLoginCallback);
    }

    public void postException(Exception exc, ArrayMap arrayMap) {
        ExceptionReporter exceptionReporter = this.mExceptionReporter;
        if (exceptionReporter != null) {
            exceptionReporter.report(exc, arrayMap);
        } else {
            mJDReactHelperCallback.postException(exc, arrayMap);
        }
    }

    public void postRNMonitorData(HashMap hashMap) {
        mJDReactHelperCallback.postRNMonitorData(hashMap);
    }

    public void recycleLoadingLottieView(View view) {
        mJDReactHelperCallback.recycleLoadingLottieView(view);
    }

    public void requestPermission(Activity activity, HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        JDReactHelperCallback jDReactHelperCallback = mJDReactHelperCallback;
        if (jDReactHelperCallback != null) {
            jDReactHelperCallback.requestPermission(activity, hashMap, jDCallback, jDCallback2);
        }
    }

    public void sendMsgToJs(Context context, String str, HashMap hashMap) {
        mJDReactHelperCallback.sendMsgToJs(context, str, hashMap);
    }

    public void sendMtaData(String str, String str2) {
        mJDReactHelperCallback.sendCommonData(str, str2);
        if (newInstance().isDebug()) {
            JLog.d(TAG, "Send mta data in rn:   event_id=>" + str + " event_param=>" + str2 + " event_func=> page=> page_param=> next_class_name=> next_page_param=> page_id=> shop_id=>");
        }
    }

    public void setApplication(Application application) {
        mApplication = application;
    }

    public void setApplicationContext(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append("setted11111");
        sb.append(mApplicationContext == null);
        sb.toString();
        mApplicationContext = context;
    }

    public void setDataReportListener(DataReportListener dataReportListener) {
        this.mDataReportListener = dataReportListener;
    }

    public void setExceptionReporter(ExceptionReporter exceptionReporter) {
        this.mExceptionReporter = exceptionReporter;
    }

    public void setExposureMta(HashMap hashMap, String str) {
        mJDReactHelperCallback.setExposureMta(hashMap, str);
    }

    public void setIndependent(boolean z) {
        isIndependent = z;
    }

    public void setInitErrorCustomizer(InitErrorCustomizer initErrorCustomizer) {
        mInitErrorCustomizer = initErrorCustomizer;
    }

    public void setJDReactHelperCallback(JDReactHelperCallback jDReactHelperCallback) {
        mJDReactHelperCallback = jDReactHelperCallback;
    }

    public void setNativeMetaData(String str) {
        mMetaData = str;
    }

    public void setNativeVerionAPI(String str) {
        mNativeVerionAPI = str;
    }

    public void setNetCountDownMills(int i2) {
        this.netCountDown = i2;
    }

    public void setTrackListener(TrackListener trackListener) {
        mTrackListener = trackListener;
    }

    public boolean showBundleInfo() {
        JDReactHelperCallback jDReactHelperCallback = mJDReactHelperCallback;
        if (jDReactHelperCallback == null) {
            return false;
        }
        return jDReactHelperCallback.showBundleInfo();
    }

    public void showErrorRetryView(View.OnClickListener onClickListener, ViewGroup viewGroup) {
        mJDReactHelperCallback.showErrorRetryView(onClickListener, viewGroup);
    }

    public void showLongToast(String str) {
        mJDReactHelperCallback.showLongToast(str);
    }

    public void showShortToast(String str) {
        mJDReactHelperCallback.showShortToast(str);
    }

    public void startActivityInFrameWithNoNavigation(Intent intent, Context context) {
        mJDReactHelperCallback.startActivityInFrameWithNoNavigation(intent, context);
    }

    public boolean useDownloadQueue() {
        JDReactHelperCallback jDReactHelperCallback = mJDReactHelperCallback;
        if (jDReactHelperCallback == null) {
            return false;
        }
        return jDReactHelperCallback.useDownloadQueue();
    }

    public boolean useHttp() {
        JDReactHelperCallback jDReactHelperCallback = mJDReactHelperCallback;
        if (jDReactHelperCallback == null) {
            return false;
        }
        return jDReactHelperCallback.useHttp();
    }

    public void postException(Throwable th) {
        ExceptionReporter exceptionReporter = this.mExceptionReporter;
        if (exceptionReporter != null) {
            exceptionReporter.report(th, null);
        } else {
            mJDReactHelperCallback.postException(th);
        }
    }

    public void postException(String str) {
        ExceptionReporter exceptionReporter = this.mExceptionReporter;
        if (exceptionReporter != null) {
            exceptionReporter.report(new JDReactCommonException(str), null);
        } else {
            mJDReactHelperCallback.postException(str);
        }
    }
}
