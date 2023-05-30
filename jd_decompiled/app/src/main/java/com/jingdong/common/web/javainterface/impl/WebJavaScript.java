package com.jingdong.common.web.javainterface.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.tech.IsoDep;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.ActivityNumController;
import com.jingdong.common.deeplinkhelper.DeepLinkRecoderHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkScanHelper;
import com.jingdong.common.utils.JDReminderUtils;
import com.jingdong.common.utils.ReadContactsUtil;
import com.jingdong.common.utils.VoiceUtil;
import com.jingdong.common.utils.pay.CashDeskConfig;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.entity.JsBridgeEntity;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.util.IntentUtils;
import com.jingdong.common.web.util.WebUnifiedMtaUtil;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import java.io.IOException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public final class WebJavaScript extends BaseWebComponent implements IJavaInterface {
    private static final String TAG = "WebJavaScript";
    private String configJson;
    private String dialogTips;
    private boolean improveUserInformationPageFinished;
    private boolean isPreRender;
    private JsBridgeEntity jsBridgeEntity;
    private boolean mPayCompleted;
    private String pageIndex;
    private JDWebView webView;

    public WebJavaScript(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.mPayCompleted = false;
        this.jsBridgeEntity = iWebUiBinder.getWebEntity().jsBridgeEntity;
    }

    private String ByteArrayToHexString(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] cArr2 = new char[bArr.length * 2];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            int i3 = bArr[i2] & 255;
            int i4 = i2 * 2;
            cArr2[i4] = cArr[i3 >>> 4];
            cArr2[i4 + 1] = cArr[i3 & 15];
        }
        return new String(cArr2);
    }

    private static byte[] HexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i2 = 0; i2 < length; i2 += 2) {
            bArr[i2 / 2] = (byte) ((Character.digit(str.charAt(i2), 16) << 4) + Character.digit(str.charAt(i2 + 1), 16));
        }
        return bArr;
    }

    @JavascriptInterface
    public void MDataToAppForResult(String str) {
        if (!TextUtils.isEmpty(str)) {
            Intent intent = new Intent();
            intent.putExtra("MData", str);
            this.webUiBinder.getBaseActivity().setResult(-1, intent);
            this.webUiBinder.getBaseActivity().finish();
        }
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "WebJavaScript-MDataToAppForResult");
    }

    @JavascriptInterface
    public void MtoSamScan(String str) {
        Log.d(TAG, "MtoSamScan callback:" + str);
        this.jsBridgeEntity.jsCallback = str;
        DeepLinkScanHelper.startCaptureActivityForResultFromSam(this.webUiBinder.getBaseActivity(), 6667);
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "WebJavaScript-MtoSamScan");
    }

    @JavascriptInterface
    public void barcodeCallBack() {
        if (Log.D) {
            Log.d(TAG, " barcodeCallBack ---- ");
        }
        DeepLinkScanHelper.startCaptureActivity(this.webUiBinder.getBaseActivity(), null);
        Bundle bundle = new Bundle();
        bundle.putString("SCAN_FORMATS", "EAN_13,EAN_8,QR_CODE");
        DeepLinkScanHelper.startCaptureActivityForResult(this.webUiBinder.getBaseActivity(), bundle, 1235, false);
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "WebJavaScript-barcodeCallBack");
    }

    @JavascriptInterface
    public void callContacts() {
        if (Log.D) {
            Log.d(TAG, " callContacts ---- ");
        }
        ReadContactsUtil.readContacts(this.webUiBinder.getBaseActivity());
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "WebJavaScript-callContacts");
    }

    @JavascriptInterface
    public void callbackForImproveUserInformation(boolean z) {
        this.improveUserInformationPageFinished = z;
    }

    @JavascriptInterface
    public void cancelJDReminder(String str, long j2, long j3) {
        JDReminderUtils.cancelReminder(JDReminderUtils.Type.getType(str), j2, j3);
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "WebJavaScript_cancelJDReminder");
    }

    @JavascriptInterface
    public void cancelJDReminderWithURL(String str, long j2, String str2) {
        JDReminderUtils.cancelReminder(JDReminderUtils.Type.getType(str), j2, str2);
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "WebJavaScript_cancelJDReminderWithURL");
    }

    @JavascriptInterface
    public void checkReminder(String str, long j2, long j3, String str2) {
        boolean checkReminder = JDReminderUtils.checkReminder(JDReminderUtils.Type.getType(str), j2, j3);
        try {
            if (this.webUiBinder.getJdWebView() != null) {
                this.webUiBinder.getJdWebView().injectJs("javascript:checkReminderCallback('" + checkReminder + "','" + str2 + "');");
            }
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
            }
        }
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "WebJavaScript_checkReminder");
    }

    @JavascriptInterface
    public void checkReminderWithURL(String str, long j2, String str2, String str3) {
        boolean checkReminder = JDReminderUtils.checkReminder(JDReminderUtils.Type.getType(str), j2, str2);
        try {
            JDWebView jDWebView = this.webView;
            if (jDWebView != null && this.isPreRender) {
                jDWebView.injectJs("javascript:checkReminderCallback('" + checkReminder + "','" + str3 + "');");
            } else if (this.webUiBinder.getJdWebView() != null) {
                this.webUiBinder.getJdWebView().injectJs("javascript:checkReminderCallback('" + checkReminder + "','" + str3 + "');");
            }
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
            }
        }
        JDWebView jDWebView2 = this.webView;
        if (jDWebView2 != null && this.isPreRender) {
            WebUnifiedMtaUtil.functionReport(jDWebView2, "WebJavaScript_checkReminderWithURL");
        } else {
            WebUnifiedMtaUtil.functionReport(this.webUiBinder, "WebJavaScript_checkReminderWithURL");
        }
    }

    @JavascriptInterface
    public void exitActivity(int i2, int i3) {
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "WebJavaScript-exitActivity");
        ActivityNumController.removeActivity(i2, i3);
    }

    @JavascriptInterface
    public void finishWebActivity() {
        if (this.webUiBinder.getBaseActivity() != null) {
            this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.WebJavaScript.2
                {
                    WebJavaScript.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    ((BaseWebComponent) WebJavaScript.this).webUiBinder.getBaseActivity().finish();
                }
            });
        }
    }

    @JavascriptInterface
    public String getApduResult(String str) {
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "WebJavaScript-getApduResult");
        try {
            return processAPDU(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public CashDeskConfig getCashDeskConfig() {
        try {
            if (TextUtils.isEmpty(this.configJson)) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(this.configJson);
            CashDeskConfig cashDeskConfig = new CashDeskConfig();
            try {
                cashDeskConfig.setDialogSwitch(jSONObject.optInt("dialogSwitch"));
            } catch (Exception unused) {
            }
            return cashDeskConfig;
        } catch (Exception unused2) {
            return null;
        }
    }

    @JavascriptInterface
    public String getDialogTips() {
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "WebJavaScript-getDialogTips");
        return this.dialogTips;
    }

    @Override // com.jingdong.common.web.javainterface.IJavaInterface
    public String getName() {
        return WebUiConstans.JavaInterfaceNames.WEBJAVASCRIPT;
    }

    @JavascriptInterface
    public String getPageIndex() {
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "WebJavaScript-getPageIndex");
        return this.pageIndex;
    }

    @JavascriptInterface
    public boolean getPayCompleted() {
        return this.mPayCompleted;
    }

    @JavascriptInterface
    public boolean hasNFC() {
        NfcAdapter defaultAdapter;
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "WebJavaScript-hasNFC");
        Context applicationContext = this.webUiBinder.getBaseActivity().getApplicationContext();
        return (applicationContext == null || (defaultAdapter = ((NfcManager) applicationContext.getSystemService("nfc")).getDefaultAdapter()) == null || !defaultAdapter.isEnabled()) ? false : true;
    }

    public boolean isImproveUserInformationPageFinished() {
        return this.improveUserInformationPageFinished;
    }

    @JavascriptInterface
    public boolean isNFCEnabled() {
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "WebJavaScript-isNFCEnabled");
        return SharedPreferencesUtil.getBoolean("isNfcEnabled", false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:72:?, code lost:
        return;
     */
    @android.webkit.JavascriptInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void notifyMessageToNative(java.lang.String r6) {
        /*
            r5 = this;
            com.jingdong.common.web.uibinder.IWebUiBinder r0 = r5.webUiBinder
            if (r0 == 0) goto L9d
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            if (r0 == 0) goto Lc
            goto L9d
        Lc:
            boolean r0 = com.jingdong.sdk.oklog.OKLog.D     // Catch: java.lang.Exception -> L99
            java.lang.String r1 = "WebJavaScript"
            if (r0 == 0) goto L26
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L99
            r0.<init>()     // Catch: java.lang.Exception -> L99
            java.lang.String r2 = "JDAppUnite-->> notifyMessageToNative = jsonString:  "
            r0.append(r2)     // Catch: java.lang.Exception -> L99
            r0.append(r6)     // Catch: java.lang.Exception -> L99
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> L99
            com.jingdong.sdk.oklog.OKLog.d(r1, r0)     // Catch: java.lang.Exception -> L99
        L26:
            java.lang.Class<com.jingdong.common.web.entity.JsInputEntity> r0 = com.jingdong.common.web.entity.JsInputEntity.class
            java.lang.Object r6 = com.jd.framework.json.JDJSON.parseObject(r6, r0)     // Catch: java.lang.Exception -> L99
            com.jingdong.common.web.entity.JsInputEntity r6 = (com.jingdong.common.web.entity.JsInputEntity) r6     // Catch: java.lang.Exception -> L99
            java.lang.String r0 = r6.businessType     // Catch: java.lang.Exception -> L99
            java.lang.String r6 = r6.callBackName     // Catch: java.lang.Exception -> L99
            r2 = -1
            int r3 = r0.hashCode()     // Catch: java.lang.Exception -> L99
            r4 = 548553333(0x20b24275, float:3.019835E-19)
            if (r3 == r4) goto L3d
            goto L47
        L3d:
            java.lang.String r3 = "unionFingerPrint"
            boolean r0 = r0.equals(r3)     // Catch: java.lang.Exception -> L99
            if (r0 == 0) goto L47
            r2 = 0
        L47:
            if (r2 == 0) goto L4a
            goto L9d
        L4a:
            boolean r0 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Exception -> L99
            if (r0 != 0) goto L9d
            java.lang.String r0 = com.jingdong.common.login.DeviceFingerUtil.getUnionFingerPrint()     // Catch: java.lang.Exception -> L99
            r2 = 0
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> L99
            if (r3 != 0) goto L8b
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: java.lang.Exception -> L99
            r2.<init>(r0)     // Catch: java.lang.Exception -> L99
            com.jingdong.common.web.uibinder.IWebUiBinder r0 = r5.webUiBinder     // Catch: java.lang.Exception -> L99
            com.jingdong.common.BaseActivity r0 = r0.getBaseActivity()     // Catch: java.lang.Exception -> L99
            android.content.Context r0 = r0.getApplicationContext()     // Catch: java.lang.Exception -> L99
            java.lang.String r3 = "jdkey"
            java.lang.String r0 = com.jd.stat.security.jma.JMA.getJDKey(r0)     // Catch: java.lang.Exception -> L99
            r2.put(r3, r0)     // Catch: java.lang.Exception -> L99
            boolean r0 = com.jingdong.sdk.oklog.OKLog.D     // Catch: java.lang.Exception -> L99
            if (r0 == 0) goto L8b
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L99
            r0.<init>()     // Catch: java.lang.Exception -> L99
            java.lang.String r3 = "[sgact]webview get jdkey+"
            r0.append(r3)     // Catch: java.lang.Exception -> L99
            r0.append(r2)     // Catch: java.lang.Exception -> L99
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> L99
            com.jingdong.sdk.oklog.OKLog.d(r1, r0)     // Catch: java.lang.Exception -> L99
        L8b:
            com.jingdong.common.web.uibinder.IWebUiBinder r0 = r5.webUiBinder     // Catch: java.lang.Exception -> L99
            java.lang.String r1 = "0"
            java.lang.String r3 = ""
            java.lang.String r1 = com.jingdong.common.web.util.WebUtils.stringfyJSonData(r1, r2, r3)     // Catch: java.lang.Exception -> L99
            com.jingdong.common.web.util.WebUtils.sendJSONStr2M(r0, r6, r1)     // Catch: java.lang.Exception -> L99
            goto L9d
        L99:
            r6 = move-exception
            r6.printStackTrace()
        L9d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.web.javainterface.impl.WebJavaScript.notifyMessageToNative(java.lang.String):void");
    }

    @JavascriptInterface
    public void phoneDial(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            WebUtils.sendJSONStr2M(this.webUiBinder, str2, WebUtils.stringfyJSonData("-1"));
        }
        IntentUtils.jumpDial(this.webUiBinder.getBaseActivity(), str);
        WebUtils.sendJSONStr2M(this.webUiBinder, str2, WebUtils.stringfyJSonData(true));
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "WebJavaScript-phoneDial");
    }

    public String processAPDU(String str) {
        String[] split;
        int i2;
        String[] split2;
        IsoDep isoDep = IsoDep.get(this.webUiBinder.getWebEntity().yct_tag);
        if (isoDep != null && str != null && (split = str.split(DYConstants.DY_REGEX_VERTICAL_LINE)) != null && split.length > 0) {
            int length = split.length;
            String[] strArr = new String[length];
            int i3 = 0;
            while (true) {
                if (i3 >= split.length) {
                    break;
                }
                String str2 = split[i3];
                if (str2 != null && (split2 = str2.split(":")) != null && split2.length > 2) {
                    try {
                        strArr[i3] = split2[0] + ":" + ByteArrayToHexString(isoDep.transceive(HexStringToByteArray(split2[1])));
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        return null;
                    }
                }
                i3++;
            }
            if (length > 0) {
                String str3 = strArr[0];
                for (i2 = 1; i2 < length; i2++) {
                    str3 = str3 + "|" + strArr[i2];
                }
                return str3;
            }
        }
        return null;
    }

    @JavascriptInterface
    public void requestEvent(boolean z) {
        JDWebView jDWebView = this.webView;
        if (jDWebView != null && this.isPreRender) {
            jDWebView.requestDisallowInterceptTouchEvent(z);
            return;
        }
        IWebUiBinder iWebUiBinder = this.webUiBinder;
        if (iWebUiBinder == null || iWebUiBinder.getJdWebView() == null) {
            return;
        }
        this.webUiBinder.getJdWebView().requestDisallowInterceptTouchEvent(z);
    }

    @JavascriptInterface
    public void satisfactionCallBack(final boolean z) {
        if (Log.D) {
            Log.d(TAG, " satisfactionCallBack -->> isSuccess : " + z);
        }
        if (this.webUiBinder.getBaseActivity() != null) {
            this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.WebJavaScript.3
                {
                    WebJavaScript.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (z) {
                        ((BaseWebComponent) WebJavaScript.this).webUiBinder.getBaseActivity().setResult(-1);
                        ((BaseWebComponent) WebJavaScript.this).webUiBinder.finishUi();
                    }
                }
            });
        }
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "WebJavaScript-satisfactionCallBack");
    }

    @JavascriptInterface
    public void setConfigJson(String str) {
        if (Log.I) {
            Log.i(TAG, "configJson -->>" + str);
        }
        this.configJson = str;
    }

    @JavascriptInterface
    public void setDialogTips(String str) {
        if (Log.I) {
            Log.i(TAG, "dialogTips -->>" + str);
        }
        this.dialogTips = str;
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "WebJavaScript-dialogTips");
    }

    public void setImproveUserInformationPageFinished(boolean z) {
        this.improveUserInformationPageFinished = z;
    }

    @JavascriptInterface
    public void setJDReminder(String str, long j2, String str2, long j3, String str3) {
        JDReminderUtils.setReminder(JDReminderUtils.Type.getType(str), j2, str2, j3, str3);
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "WebJavaScript_setJDReminder");
    }

    @JavascriptInterface
    public void setJDReminderWithURL(String str, String str2, long j2, String str3) {
        JDReminderUtils.setReminder(JDReminderUtils.Type.getType(str), str2, j2, str3);
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "WebJavaScript_setJDReminderWithURL");
    }

    @JavascriptInterface
    public void setNFCEnabled(boolean z) {
        PackageManager packageManager = this.webUiBinder.getBaseActivity().getPackageManager();
        ComponentName componentName = new ComponentName(this.webUiBinder.getBaseActivity().getApplicationContext(), "com.jd.lib.charge.nfc.NfcActivity");
        int i2 = z ? 1 : 2;
        SharedPreferencesUtil.putBoolean("isNfcEnabled", z);
        try {
            packageManager.setComponentEnabledSetting(componentName, i2, 1);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "WebJavaScript-setNFCEnabled");
    }

    @JavascriptInterface
    public void setPageIndex(String str) {
        if (Log.I) {
            Log.i(TAG, "pageIndex -->>" + str);
        }
        this.pageIndex = str;
    }

    @JavascriptInterface
    public void setPayCompleted() {
        this.mPayCompleted = true;
    }

    @JavascriptInterface
    public void startRecoder() {
        DeepLinkRecoderHelper.startRecoderForResult(this.webUiBinder.getBaseActivity());
        WebUnifiedMtaUtil.permissionReport(this.webUiBinder, "WebJavaScript_startRecoder");
    }

    @JavascriptInterface
    public void takeCouponCallBack() {
        if (this.webUiBinder.getBaseActivity() != null) {
            this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.WebJavaScript.1
                {
                    WebJavaScript.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    ((BaseWebComponent) WebJavaScript.this).webUiBinder.getBaseActivity().finish();
                }
            });
        }
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "WebJavaScript-takeCouponCallBack");
    }

    @JavascriptInterface
    public void voiceCallBack() {
        if (Log.D) {
            Log.d(TAG, " voiceCallBack ---- ");
        }
        VoiceUtil.showVoiceDialog(this.webUiBinder.getBaseActivity());
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "WebJavaScript-voiceCallBack");
    }

    public void setPayCompleted(boolean z) {
        this.mPayCompleted = z;
    }

    public WebJavaScript(JDWebView jDWebView) {
        this.mPayCompleted = false;
        this.webView = jDWebView;
        this.isPreRender = true;
    }
}
