package com.jingdong.common.web.javainterface.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.common.utils.QQUtil;
import com.jingdong.common.utils.WeixinUtil;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class MobileLogin extends BaseWebComponent implements IJavaInterface {
    private final String TAG;
    private boolean hasRegisterd;
    private String mChannel;
    private String mJsCallback;
    private IUiListener mQQListener;
    private BroadcastReceiver mWXReceiver;

    public MobileLogin(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.TAG = MobileLogin.class.getSimpleName();
        this.hasRegisterd = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyH5(String str) {
        if (this.webUiBinder.getJdWebView() == null || TextUtils.isEmpty(this.mJsCallback)) {
            return;
        }
        this.webUiBinder.getJdWebView().injectJs("javascript:" + this.mJsCallback + "('" + str + "');");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String packData(boolean z, Object obj, Intent intent) {
        if (Log.D) {
            Log.d(this.TAG, "packData:" + z + "  " + obj + "  " + intent);
        }
        JSONObject jSONObject = new JSONObject();
        if (z) {
            if (intent != null) {
                try {
                    jSONObject.put("authPlatform", 1);
                    String stringExtra = intent.getStringExtra("code");
                    String stringExtra2 = intent.getStringExtra(XView2Constants.STATE);
                    int intExtra = intent.getIntExtra("type", -1);
                    int intExtra2 = intent.getIntExtra("errCode", -1);
                    if (intExtra == 1 && LoginConstans.WX_LOGIN_BY_H5.equals(stringExtra2) && intExtra2 == 0 && !TextUtils.isEmpty(stringExtra)) {
                        jSONObject.put("wxCode", stringExtra);
                        jSONObject.put("authResult", 1);
                    } else {
                        if (intExtra2 == -2) {
                            jSONObject.put("authResult", 2);
                        } else {
                            jSONObject.put("authResult", 0);
                        }
                        jSONObject.put("authErrMsg", "code is NULL or errorCode=" + intExtra2);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } else if (obj instanceof JSONObject) {
            JSONObject jSONObject2 = (JSONObject) obj;
            try {
                jSONObject.put("authPlatform", 0);
                if (jSONObject2.has("ret") && jSONObject2.has("openid") && jSONObject2.has(Constants.PARAM_ACCESS_TOKEN)) {
                    String string = jSONObject2.getString("openid");
                    String string2 = jSONObject2.getString(Constants.PARAM_ACCESS_TOKEN);
                    if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                        jSONObject.put("qqOpenId", string);
                        jSONObject.put("qqAccessToken", string2);
                        jSONObject.put("authResult", 1);
                    } else {
                        jSONObject.put("authResult", 0);
                        jSONObject.put("authErrMsg", "openid or access_token is NULL");
                    }
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        return jSONObject.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String packDataForQQError(boolean z, UiError uiError) {
        if (Log.D) {
            Log.d(this.TAG, "packDataForQQError,isCancel:" + z + "  uierror:" + uiError);
        }
        JSONObject jSONObject = new JSONObject();
        if (z) {
            try {
                jSONObject.put("authPlatform", 0);
                jSONObject.put("authResult", 2);
                jSONObject.put("authErrMsg", "user cancel");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else {
            try {
                jSONObject.put("authPlatform", 0);
                jSONObject.put("authResult", 0);
                jSONObject.put("authErrMsg", uiError == null ? "" : uiError.errorMessage);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        return jSONObject.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String packDataForQQWarning() {
        if (Log.D) {
            Log.d(this.TAG, "packDataForQQWarning,Warning:");
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("authPlatform", 0);
            jSONObject.put("authResult", 0);
            jSONObject.put("authErrMsg", "onWarning: \u8bf7\u6388\u6743\u624bQ\u8bbf\u95ee\u5206\u4eab\u7684\u6587\u4ef6\u7684\u8bfb\u53d6\u6743\u9650!");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    @JavascriptInterface
    public void bindSocialAccountWithJsonString(String str) {
        Log.d(this.TAG, "bindSocialAccountWithJsonString:" + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.mChannel = jSONObject.optString("channelName", "");
            this.mJsCallback = jSONObject.optString("callBackName", "");
            if (Constants.SOURCE_QQ.equals(this.mChannel)) {
                if (this.mQQListener == null) {
                    this.mQQListener = new IUiListener() { // from class: com.jingdong.common.web.javainterface.impl.MobileLogin.1
                        @Override // com.tencent.tauth.IUiListener
                        public void onCancel() {
                            MobileLogin.this.notifyH5(MobileLogin.this.packDataForQQError(true, null));
                        }

                        @Override // com.tencent.tauth.IUiListener
                        public void onComplete(Object obj) {
                            MobileLogin.this.notifyH5(MobileLogin.this.packData(false, obj, null));
                        }

                        @Override // com.tencent.tauth.IUiListener
                        public void onError(UiError uiError) {
                            MobileLogin.this.notifyH5(MobileLogin.this.packDataForQQError(false, uiError));
                        }

                        @Override // com.tencent.tauth.IUiListener
                        public void onWarning(int i2) {
                            if (i2 == -19) {
                                MobileLogin.this.notifyH5(MobileLogin.this.packDataForQQWarning());
                            }
                        }
                    };
                }
                QQUtil.qqOpenSDKLogin(this.webUiBinder.getBaseActivity(), this.mQQListener);
            } else if ("WX".equals(this.mChannel)) {
                if (!WeixinUtil.check()) {
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put("authPlatform", 1);
                        jSONObject2.put("authResult", -99);
                        jSONObject2.put("authErrMsg", "vx not install or version not available");
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                    notifyH5(jSONObject2.toString());
                    return;
                }
                if (this.mWXReceiver == null) {
                    this.mWXReceiver = new BroadcastReceiver() { // from class: com.jingdong.common.web.javainterface.impl.MobileLogin.2
                        @Override // android.content.BroadcastReceiver
                        public void onReceive(Context context, Intent intent) {
                            MobileLogin.this.notifyH5(MobileLogin.this.packData(true, null, intent));
                        }
                    };
                }
                if (!this.hasRegisterd) {
                    this.webUiBinder.getBaseActivity().registerReceiver(this.mWXReceiver, new IntentFilter(Configuration.BROADCAST_FROM_WXLOGIN), Configuration.SLEF_BROADCAST_PERMISSION, null);
                    this.hasRegisterd = true;
                }
                WeixinUtil.wxLogin(LoginConstans.WX_LOGIN_BY_H5);
            }
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
    }

    @Override // com.jingdong.common.web.javainterface.IJavaInterface
    public String getName() {
        return WebUiConstans.JavaInterfaceNames.MOBILE_LOGIN;
    }

    public IUiListener getQQListener() {
        return this.mQQListener;
    }

    public void onDestroy() {
        if (this.mWXReceiver != null) {
            this.webUiBinder.getBaseActivity().unregisterReceiver(this.mWXReceiver);
        }
        this.hasRegisterd = false;
    }

    public MobileLogin() {
        this.TAG = MobileLogin.class.getSimpleName();
        this.hasRegisterd = false;
    }
}
