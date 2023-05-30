package com.jingdong.jdsdk.network.dependency;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.jd.framework.json.JDJSONObject;
import com.jd.framework.network.toolbox.JDNetworkStatisticTool;
import com.jingdong.common.network.IpModel;
import com.jingdong.common.utils.JDGetWayQueueTools;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.dependency.ILoginUserController;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.oklog.OKLog;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes14.dex */
public class DefaultDependencyFactory {
    public static INetworkEventDataReporter defaultNetworkEventDataReporter() {
        return new INetworkEventDataReporter() { // from class: com.jingdong.jdsdk.network.dependency.DefaultDependencyFactory.13
            @Override // com.jingdong.jdsdk.network.dependency.INetworkEventDataReporter
            public boolean enable() {
                return false;
            }

            @Override // com.jingdong.jdsdk.network.dependency.INetworkEventDataReporter
            public String generateRequestIdentity(URL url, HashMap<String, String> hashMap) {
                return "";
            }

            @Override // com.jingdong.jdsdk.network.dependency.INetworkEventDataReporter
            public void report(HashMap<String, String> hashMap) {
            }

            @Override // com.jingdong.jdsdk.network.dependency.INetworkEventDataReporter
            public void reportException(HashMap<String, String> hashMap) {
            }
        };
    }

    public static IBusinessJsonCodeEventListener getBusinessJsonCodeEventListener() {
        return new IBusinessJsonCodeEventListener() { // from class: com.jingdong.jdsdk.network.dependency.DefaultDependencyFactory.8
            @Override // com.jingdong.jdsdk.network.dependency.IBusinessJsonCodeEventListener
            public void onJsonCodeReceive(String str, HttpSetting httpSetting, HttpResponse httpResponse) {
            }
        };
    }

    public static IAppProxy getDefaultAppProxy() {
        return new IAppProxy() { // from class: com.jingdong.jdsdk.network.dependency.DefaultDependencyFactory.10
            @Override // com.jingdong.jdsdk.network.dependency.IAppProxy
            public void clearCacheFiles() {
            }

            @Override // com.jingdong.jdsdk.network.dependency.IAppProxy
            public void exitApp() {
            }

            @Override // com.jingdong.jdsdk.network.dependency.IAppProxy
            public Activity getCurrentMyActivity() {
                return null;
            }
        };
    }

    public static ICustomUIComponent getDefaultCustomUIComponent() {
        return new ICustomUIComponent() { // from class: com.jingdong.jdsdk.network.dependency.DefaultDependencyFactory.9
            @Override // com.jingdong.jdsdk.network.dependency.ICustomUIComponent
            public Dialog createJdDialogNewStyle(Context context, JDGetWayQueueTools.JdDialogParam jdDialogParam, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, DialogInterface.OnCancelListener onCancelListener) {
                return null;
            }

            @Override // com.jingdong.jdsdk.network.dependency.ICustomUIComponent
            public Dialog createJdDialogWithStyleTimer(Context context, String str, String str2, int i2, View.OnClickListener onClickListener, DialogInterface.OnCancelListener onCancelListener) {
                return null;
            }

            @Override // com.jingdong.jdsdk.network.dependency.ICustomUIComponent
            public Dialog createJdDialogWithStyleTimer(Context context, String str, String str2, String str3, int i2, String str4, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, DialogInterface.OnCancelListener onCancelListener) {
                return null;
            }

            @Override // com.jingdong.jdsdk.network.dependency.ICustomUIComponent
            public View createProgressBar() {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(13);
                ProgressBar progressBar = new ProgressBar(JDHttpTookit.getEngine().getApplicationContext());
                progressBar.setLayoutParams(layoutParams);
                return progressBar;
            }

            @Override // com.jingdong.jdsdk.network.dependency.ICustomUIComponent
            public void releaseResource(View view) {
            }

            @Override // com.jingdong.jdsdk.network.dependency.ICustomUIComponent
            public void startTimeCountNew(Dialog dialog) {
            }

            @Override // com.jingdong.jdsdk.network.dependency.ICustomUIComponent
            public void updateCountDown(Dialog dialog, int i2) {
            }

            @Override // com.jingdong.jdsdk.network.dependency.ICustomUIComponent
            public void updateTick(Dialog dialog, long j2) {
            }
        };
    }

    public static IDownloadDomainsResolver getDefaultDownloadDomainResolver() {
        return new IDownloadDomainsResolver() { // from class: com.jingdong.jdsdk.network.dependency.DefaultDependencyFactory.15
            @Override // com.jingdong.jdsdk.network.dependency.IDownloadDomainsResolver
            public String getConfig() {
                return "";
            }
        };
    }

    public static IExceptionReportHandler getDefaultExceptionReporter() {
        return new IExceptionReportHandler() { // from class: com.jingdong.jdsdk.network.dependency.DefaultDependencyFactory.5
            @Override // com.jingdong.jdsdk.network.dependency.IExceptionReportHandler
            public void reportDownloadDowngradeData(String str, String str2, String str3, boolean z, int i2, String str4) {
            }

            @Override // com.jingdong.jdsdk.network.dependency.IExceptionReportHandler
            public void reportDuplicatePicException(String str) {
            }

            @Override // com.jingdong.jdsdk.network.dependency.IExceptionReportHandler
            public void reportHttp2PingTimeoutException(String str, String str2) {
            }

            @Override // com.jingdong.jdsdk.network.dependency.IExceptionReportHandler
            public void reportHttpBusinessException(HttpSetting httpSetting, HttpResponse httpResponse) {
            }

            @Override // com.jingdong.jdsdk.network.dependency.IExceptionReportHandler
            public void reportHttpException(String str, HttpSetting httpSetting, HttpError httpError, String str2) {
            }

            @Override // com.jingdong.jdsdk.network.dependency.IExceptionReportHandler
            public void reportHttpsErrorToServer(String str, HttpSetting httpSetting, Throwable th) {
            }

            @Override // com.jingdong.jdsdk.network.dependency.IExceptionReportHandler
            public void sendMtaCommonData(Context context, String str, String str2, String str3, Object obj, String str4, String str5, String str6, String str7) {
            }

            @Override // com.jingdong.jdsdk.network.dependency.IExceptionReportHandler
            public void sendPropertyData(Context context, String str, String str2, String str3, String str4) {
            }
        };
    }

    public static IExternalDebugConfig getDefaultExternalDebugConfig() {
        return new IExternalDebugConfig() { // from class: com.jingdong.jdsdk.network.dependency.DefaultDependencyFactory.7
            @Override // com.jingdong.jdsdk.network.dependency.IExternalDebugConfig
            public void addMockerIdName(HttpSetting httpSetting) {
            }

            @Override // com.jingdong.jdsdk.network.dependency.IExternalDebugConfig
            public boolean isForceHttpDownGrade() {
                return false;
            }
        };
    }

    public static IHttpDnsController getDefaultIDns() {
        return new IHttpDnsController() { // from class: com.jingdong.jdsdk.network.dependency.DefaultDependencyFactory.12
            @Override // com.jingdong.jdsdk.network.dependency.IHttpDnsController
            public boolean canUseHttpDns(String str) {
                return false;
            }

            @Override // com.jingdong.jdsdk.network.dependency.IHttpDnsController
            public IpModel getIpModelByHost(String str, boolean z) {
                return null;
            }

            @Override // com.jingdong.jdsdk.network.dependency.IHttpDnsController
            public boolean isOpenDnsControl() {
                return false;
            }

            @Override // com.jingdong.jdsdk.network.dependency.IHttpDnsController
            public void onHttpDnsReceived(IpModel ipModel) {
            }
        };
    }

    public static ILoginUserController getDefaultLoginUserController() {
        return new ILoginUserController() { // from class: com.jingdong.jdsdk.network.dependency.DefaultDependencyFactory.4
            @Override // com.jingdong.jdsdk.network.dependency.ILoginUserController
            public String getCookie() {
                return "";
            }

            @Override // com.jingdong.jdsdk.network.dependency.ILoginUserController
            public void logoutOnlineInfo(ILoginUserController.ILoginStateChecker iLoginStateChecker) {
                iLoginStateChecker.onFailure();
            }

            @Override // com.jingdong.jdsdk.network.dependency.ILoginUserController
            public void reportCode3(String str) {
            }
        };
    }

    public static INetworkController getDefaultNetworkController() {
        return new INetworkController() { // from class: com.jingdong.jdsdk.network.dependency.DefaultDependencyFactory.6
            @Override // com.jingdong.jdsdk.network.dependency.INetworkController
            public void autoNetDiagnose() {
            }

            @Override // com.jingdong.jdsdk.network.dependency.INetworkController
            public boolean isAllowNetworkConnection() {
                return true;
            }
        };
    }

    public static IPHCEncryptionPlugin getDefaultPHCEncryptionPlugin() {
        return new IPHCEncryptionPlugin() { // from class: com.jingdong.jdsdk.network.dependency.DefaultDependencyFactory.11
            @Override // com.jingdong.jdsdk.network.dependency.IPHCEncryptionPlugin
            public String getEncryptBodyParamStr(HttpSetting httpSetting, JDJSONObject jDJSONObject) {
                return jDJSONObject.toString();
            }

            @Override // com.jingdong.jdsdk.network.dependency.IPHCEncryptionPlugin
            public void reportDecryptError(Throwable th) {
            }

            @Override // com.jingdong.jdsdk.network.dependency.IPHCEncryptionPlugin
            public void reportEncryptError(String str, Throwable th) {
            }

            @Override // com.jingdong.jdsdk.network.dependency.IPHCEncryptionPlugin
            public void reportGateWayDecryptError(String str, String str2) {
            }

            @Override // com.jingdong.jdsdk.network.dependency.IPHCEncryptionPlugin
            public void reportInitError(String str, String str2) {
            }

            @Override // com.jingdong.jdsdk.network.dependency.IPHCEncryptionPlugin
            public void resendEncryptError(String str) {
            }

            @Override // com.jingdong.jdsdk.network.dependency.IPHCEncryptionPlugin
            public void resendServer731Error(String str, String str2) {
            }
        };
    }

    public static IRetrofitExceptionReporter getDefaultRetrofitExceptionReporter() {
        return new IRetrofitExceptionReporter() { // from class: com.jingdong.jdsdk.network.dependency.DefaultDependencyFactory.14
            @Override // com.jingdong.jdsdk.network.dependency.IRetrofitExceptionReporter
            public void reportNetworkError(String str, String str2, String str3, int i2, Throwable th, String str4, int i3, boolean z) {
                if (OKLog.D) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("[Retrofit Error] errCode ==> ");
                    sb.append(str);
                    sb.append("\n URL ==> ");
                    sb.append(str2);
                    sb.append("\n FunctionId ==> ");
                    sb.append(str3);
                    sb.append("\n HttpCode ==> ");
                    sb.append(i2);
                    sb.append("\n ErrorMsg ==> ");
                    sb.append(th != null ? th.getMessage() : "");
                    sb.append("\n Response ==> ");
                    sb.append(str4);
                    sb.append("\n requestId ==> ");
                    sb.append(i3);
                    sb.append("\n isDowngrade ==> ");
                    sb.append(z);
                    OKLog.d("RetrofitLog", sb.toString());
                }
            }
        };
    }

    public static IRuntimeConfig getDefaultRuntimeConfig() {
        return new IRuntimeConfig() { // from class: com.jingdong.jdsdk.network.dependency.DefaultDependencyFactory.1
            @Override // com.jingdong.jdsdk.network.dependency.IRuntimeConfig
            public boolean getBoolean(String str, boolean z) {
                return z;
            }

            @Override // com.jingdong.jdsdk.network.dependency.IRuntimeConfig
            public String getDataFromMobileConfig(String str, String str2) {
                return str2;
            }

            @Override // com.jingdong.jdsdk.network.dependency.IRuntimeConfig
            public String getDataFromSwitchQuery(String str, String str2) {
                return str2;
            }

            @Override // com.jingdong.jdsdk.network.dependency.IRuntimeConfig
            public String getStringFromPreference(String str) {
                return "";
            }

            @Override // com.jingdong.jdsdk.network.dependency.IRuntimeConfig
            public boolean isUseHttpsDuringX() {
                return true;
            }

            @Override // com.jingdong.jdsdk.network.dependency.IRuntimeConfig
            public boolean isXTime() {
                return false;
            }
        };
    }

    public static ISignatureHandler getDefaultSignatureHandler() {
        return new ISignatureHandler() { // from class: com.jingdong.jdsdk.network.dependency.DefaultDependencyFactory.3
            @Override // com.jingdong.jdsdk.network.dependency.ISignatureHandler
            public byte[] decodeFromJni(byte[] bArr) {
                return null;
            }

            @Override // com.jingdong.jdsdk.network.dependency.ISignatureHandler
            public void networkSettingsPreSignature() {
            }

            @Override // com.jingdong.jdsdk.network.dependency.ISignatureHandler
            public String signature(Context context, String str, String str2, String str3, String str4, String str5) {
                return "";
            }
        };
    }

    public static IStatInfoConfig getDefaultStatInfoConfig() {
        return new IStatInfoConfig() { // from class: com.jingdong.jdsdk.network.dependency.DefaultDependencyFactory.2
            @Override // com.jingdong.jdsdk.network.dependency.IStatInfoConfig
            public boolean canUseReferer() {
                return false;
            }

            @Override // com.jingdong.jdsdk.network.dependency.IStatInfoConfig
            public String encryptBody(String str) {
                return str;
            }

            @Override // com.jingdong.jdsdk.network.dependency.IStatInfoConfig
            public Map<String, String> getColorStatParamStr(boolean z, boolean z2, boolean z3, Map<String, String> map, String str) {
                return new HashMap();
            }

            @Override // com.jingdong.jdsdk.network.dependency.IStatInfoConfig
            public String getDeviceUUID(String str, boolean z) {
                return "unknown";
            }

            @Override // com.jingdong.jdsdk.network.dependency.IStatInfoConfig
            public String getDeviceUUID(boolean z) {
                return "unknown";
            }

            @Override // com.jingdong.jdsdk.network.dependency.IStatInfoConfig
            public String getJdv() {
                return null;
            }

            @Override // com.jingdong.jdsdk.network.dependency.IStatInfoConfig
            public String getStatisticReportString(String str, boolean z, boolean z2, boolean z3, Map<String, String> map, String str2) {
                return "";
            }

            @Override // com.jingdong.jdsdk.network.dependency.IStatInfoConfig
            public Map<String, String> getUniformHeaderField(boolean z, boolean z2) {
                return new HashMap();
            }

            @Override // com.jingdong.jdsdk.network.dependency.IStatInfoConfig
            public String getVersionName() {
                return "1.0.0";
            }

            @Override // com.jingdong.jdsdk.network.dependency.IStatInfoConfig
            public void reportTlsHandshakeStatData(JDNetworkStatisticTool.TlsStatEntry tlsStatEntry) {
            }

            @Override // com.jingdong.jdsdk.network.dependency.IStatInfoConfig
            public void saveNetworkStatistic(HashMap<String, Integer> hashMap) {
            }
        };
    }
}
