package com.jingdong.common.web.javainterface.impl;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistance;
import com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.util.WebUnifiedMtaUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class JavaScriptCallIntelligentAssistance extends BaseWebComponent implements IJavaInterface {
    private static final String STATE_FAIL = "0";
    private static final String STATE_OK = "1";
    private boolean ifGainMicrophonePermission;
    private IntelligentAssistance intelligentAssistance;
    private String startRecordCallbackName;
    private String startSpeechSynthesisCallbackName;

    public JavaScriptCallIntelligentAssistance(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callBackH5(final String str, final String str2) {
        IWebUiBinder iWebUiBinder = this.webUiBinder;
        if (iWebUiBinder == null || iWebUiBinder.getBaseActivity() == null || TextUtils.isEmpty(str)) {
            return;
        }
        this.webUiBinder.getBaseActivity().runOnUiThread(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.JavaScriptCallIntelligentAssistance.3
            @Override // java.lang.Runnable
            public void run() {
                if (((BaseWebComponent) JavaScriptCallIntelligentAssistance.this).webUiBinder.getJdWebView() == null) {
                    return;
                }
                ((BaseWebComponent) JavaScriptCallIntelligentAssistance.this).webUiBinder.getJdWebView().injectJs("javascript:" + str + "('" + str2 + "')");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject stringToJson(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(XView2Constants.STATE, str);
            jSONObject.put("data", str2);
            jSONObject.put("msg", str3);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    @Override // com.jingdong.common.web.javainterface.IJavaInterface
    public String getName() {
        return WebUiConstans.JavaInterfaceNames.INTELLIGENT_ASSISTANTSDK;
    }

    @JavascriptInterface
    public void initIAVoice(String str, final String str2) {
        if (this.intelligentAssistance == null) {
            this.intelligentAssistance = IntelligentAssistance.getInstance();
        }
        boolean hasGrantedRecordAudio = PermissionHelper.hasGrantedRecordAudio(this.webUiBinder.getBaseActivity(), PermissionHelper.generateBundle("JavaScriptCallIntelligentAssistance", getClass().getSimpleName(), "initIAVoice"), new PermissionHelper.PermissionResultCallBack() { // from class: com.jingdong.common.web.javainterface.impl.JavaScriptCallIntelligentAssistance.1
            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onCanceled() {
                super.onCanceled();
                JavaScriptCallIntelligentAssistance.this.ifGainMicrophonePermission = false;
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onDenied() {
                super.onDenied();
                JavaScriptCallIntelligentAssistance.this.ifGainMicrophonePermission = false;
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onGranted() {
                super.onGranted();
                JavaScriptCallIntelligentAssistance.this.ifGainMicrophonePermission = true;
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onIgnored() {
                super.onIgnored();
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onOpenSetting() {
                super.onOpenSetting();
            }
        });
        this.ifGainMicrophonePermission = hasGrantedRecordAudio;
        if (!hasGrantedRecordAudio) {
            callBackH5(str2, stringToJson("0", "", "").toString());
        }
        this.intelligentAssistance.initSdk(str, new IntelligentAssistanceCallBack() { // from class: com.jingdong.common.web.javainterface.impl.JavaScriptCallIntelligentAssistance.2
            @Override // com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack
            public void changVolum(byte b) {
            }

            @Override // com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack
            public void initComplete(byte b) {
                if (b == 0) {
                    JavaScriptCallIntelligentAssistance javaScriptCallIntelligentAssistance = JavaScriptCallIntelligentAssistance.this;
                    javaScriptCallIntelligentAssistance.callBackH5(str2, javaScriptCallIntelligentAssistance.stringToJson("1", "", "").toString());
                    return;
                }
                JavaScriptCallIntelligentAssistance javaScriptCallIntelligentAssistance2 = JavaScriptCallIntelligentAssistance.this;
                javaScriptCallIntelligentAssistance2.callBackH5(str2, javaScriptCallIntelligentAssistance2.stringToJson("0", "", "").toString());
            }

            @Override // com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack
            public void phoneticRecognitionResult(String str3, boolean z) {
                if (str3 != null) {
                    JavaScriptCallIntelligentAssistance javaScriptCallIntelligentAssistance = JavaScriptCallIntelligentAssistance.this;
                    javaScriptCallIntelligentAssistance.callBackH5(javaScriptCallIntelligentAssistance.startRecordCallbackName, JavaScriptCallIntelligentAssistance.this.stringToJson("1", str3, "").toString());
                    return;
                }
                JavaScriptCallIntelligentAssistance javaScriptCallIntelligentAssistance2 = JavaScriptCallIntelligentAssistance.this;
                javaScriptCallIntelligentAssistance2.callBackH5(javaScriptCallIntelligentAssistance2.startRecordCallbackName, JavaScriptCallIntelligentAssistance.this.stringToJson("0", "", "").toString());
            }

            @Override // com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack
            public void phoneticRecognitionStart() {
            }

            @Override // com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack
            public void recognitionError(byte b) {
                JavaScriptCallIntelligentAssistance javaScriptCallIntelligentAssistance = JavaScriptCallIntelligentAssistance.this;
                javaScriptCallIntelligentAssistance.callBackH5(javaScriptCallIntelligentAssistance.startRecordCallbackName, JavaScriptCallIntelligentAssistance.this.stringToJson("0", "", "").toString());
            }

            @Override // com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack
            public void speakStop() {
                JavaScriptCallIntelligentAssistance javaScriptCallIntelligentAssistance = JavaScriptCallIntelligentAssistance.this;
                javaScriptCallIntelligentAssistance.callBackH5(javaScriptCallIntelligentAssistance.startRecordCallbackName, JavaScriptCallIntelligentAssistance.this.stringToJson("1", "", "").toString());
            }

            @Override // com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack
            public void speechSynthesisStop(int i2) {
                if (i2 == 0) {
                    JavaScriptCallIntelligentAssistance javaScriptCallIntelligentAssistance = JavaScriptCallIntelligentAssistance.this;
                    javaScriptCallIntelligentAssistance.callBackH5(javaScriptCallIntelligentAssistance.startSpeechSynthesisCallbackName, JavaScriptCallIntelligentAssistance.this.stringToJson("1", "", "").toString());
                    return;
                }
                JavaScriptCallIntelligentAssistance javaScriptCallIntelligentAssistance2 = JavaScriptCallIntelligentAssistance.this;
                javaScriptCallIntelligentAssistance2.callBackH5(javaScriptCallIntelligentAssistance2.startSpeechSynthesisCallbackName, JavaScriptCallIntelligentAssistance.this.stringToJson("0", "", "").toString());
            }
        });
        WebUnifiedMtaUtil.permissionReport(this.webUiBinder, "AndroidSound-initIAVoice");
    }

    @JavascriptInterface
    public void startRecord(String str) {
        IntelligentAssistance intelligentAssistance = this.intelligentAssistance;
        if (intelligentAssistance != null) {
            intelligentAssistance.startSpeak();
        }
        if (str != null) {
            this.startRecordCallbackName = str;
        } else {
            this.startRecordCallbackName = "";
        }
    }

    @JavascriptInterface
    public void startSpeechSynthesis(String str, String str2) {
        IntelligentAssistance intelligentAssistance;
        if (!TextUtils.isEmpty(str) && (intelligentAssistance = this.intelligentAssistance) != null) {
            intelligentAssistance.startSpeechSynthesizer(str);
        }
        if (str2 != null) {
            this.startSpeechSynthesisCallbackName = str2;
        } else {
            this.startSpeechSynthesisCallbackName = "";
        }
    }

    @JavascriptInterface
    public void stopRecord() {
        IntelligentAssistance intelligentAssistance = this.intelligentAssistance;
        if (intelligentAssistance != null) {
            intelligentAssistance.stopSpeak();
        }
    }

    @JavascriptInterface
    public void stopSpeechSynthesis() {
        IntelligentAssistance intelligentAssistance = this.intelligentAssistance;
        if (intelligentAssistance != null) {
            intelligentAssistance.cancelSpeechSynthesizer();
        }
    }

    public JavaScriptCallIntelligentAssistance() {
    }
}
