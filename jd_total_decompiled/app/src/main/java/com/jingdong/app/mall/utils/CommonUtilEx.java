package com.jingdong.app.mall.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.PopupWindow;
import androidx.core.app.NotificationCompat;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.basic.JDTaskClearActivity;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.deeplinkhelper.DeepLinkCartHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkOrderCenterHelper;
import com.jingdong.common.frame.IMainActivity;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.AccessibilityHelper;
import com.jingdong.common.login.BusinessRegistObserverManager;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.login.SafetyManager;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.runTimeConfig.ConfigUtil;
import com.jingdong.common.ui.DialogController;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.CommonUtil;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.common.utils.pay.JumpUtils;
import com.jingdong.common.weixin.WeiXinEntity;
import com.jingdong.common.weixin.WeiXinPayUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.config.HostConstants;
import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpGroupSetting;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.tencent.smtt.sdk.WebView;
import java.lang.reflect.Field;
import jd.wjlogin_sdk.common.WJLoginHelper;
import jd.wjlogin_sdk.common.listener.OnCommonCallback;
import jd.wjlogin_sdk.model.ErrorResult;
import jd.wjlogin_sdk.model.FailResult;

/* loaded from: classes4.dex */
public final class CommonUtilEx extends CommonUtil {
    private static final String LOCAL_NOTIFICATION_COUNT = "local_notification_count";
    private static final String TAG = "CommonUtilEx";
    private static CommonUtilEx commonUtilEx;
    private static long lastToClientTimeMillis;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends OnCommonCallback {
        final /* synthetic */ String a;
        final /* synthetic */ WJLoginHelper b;

        a(String str, WJLoginHelper wJLoginHelper) {
            this.a = str;
            this.b = wJLoginHelper;
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onError(ErrorResult errorResult) {
            String str;
            if (errorResult != null) {
                try {
                    str = errorResult.getErrorMsg();
                } catch (Exception e2) {
                    e2.printStackTrace();
                    str = "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86\uff01";
                }
            } else {
                str = "";
            }
            ToastUtils.showToast(com.jingdong.app.mall.e.b().a(), str);
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onFail(FailResult failResult) {
            String message = failResult.getMessage();
            if (failResult.getReplyCode() == 8) {
                BaseActivity a = com.jingdong.app.mall.e.b().a();
                if (a == null) {
                    return;
                }
                CommonUtilEx.this.showOneBtnDialog(a, message, a.getString(R.string.regist_confirm));
                return;
            }
            ToastUtils.showToast(com.jingdong.app.mall.e.b().a(), message);
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onSuccess() {
            if (Log.D) {
                Log.d(CommonUtilEx.TAG, "toClient()  -->> bindLogin  tokenKey :  " + this.a + " , pin : " + this.b.getPin());
            }
            String string = SafetyManager.getString(LoginConstans.LOGIN_LAST_WAY_TEMP, "");
            if (LoginConstans.LOGIN_LAST_WAY_QQ_BIND.equals(string)) {
                if (AccessibilityHelper.isAccessibilityEnabled(JdSdk.getInstance().getApplication())) {
                    JDMtaUtils.onClickWithPageId(JdSdk.getInstance().getApplication(), "NewLogin_QQBindSuccess", CommonUtilEx.TAG, "1", "NewLogin");
                } else {
                    JDMtaUtils.onClickWithPageId(JdSdk.getInstance().getApplication(), "NewLogin_QQBindSuccess", CommonUtilEx.TAG, "0", "NewLogin");
                }
                SafetyManager.putString(LoginConstans.LOGIN_LAST_WAY, LoginConstans.LOGIN_LAST_WAY_QQ);
            } else if (LoginConstans.LOGIN_LAST_WAY_WX_BIND.equals(string)) {
                if (AccessibilityHelper.isAccessibilityEnabled(JdSdk.getInstance().getApplication())) {
                    JDMtaUtils.onClickWithPageId(JdSdk.getInstance().getApplication(), "NewLogin_WXBindSuccess", CommonUtilEx.TAG, "1", "NewLogin");
                } else {
                    JDMtaUtils.onClickWithPageId(JdSdk.getInstance().getApplication(), "NewLogin_WXBindSuccess", CommonUtilEx.TAG, "0", "NewLogin");
                }
                SafetyManager.putString(LoginConstans.LOGIN_LAST_WAY, LoginConstans.LOGIN_LAST_WAY_WX);
            }
            SafetyManager.putString(LoginConstans.LOGIN_LAST_WAY_TEMP, "");
            LoginUserBase.saveInfoAfterLogin();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends OnCommonCallback {
        final /* synthetic */ String a;
        final /* synthetic */ WJLoginHelper b;

        b(String str, WJLoginHelper wJLoginHelper) {
            this.a = str;
            this.b = wJLoginHelper;
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onError(ErrorResult errorResult) {
            String str;
            BusinessRegistObserverManager.getInstance().failRegistListener();
            if (errorResult != null) {
                try {
                    str = errorResult.getErrorMsg();
                } catch (Exception e2) {
                    e2.printStackTrace();
                    str = "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86\uff01";
                }
            } else {
                str = "";
            }
            ToastUtils.showToast(com.jingdong.app.mall.e.b().a(), str);
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onFail(FailResult failResult) {
            BusinessRegistObserverManager.getInstance().failRegistListener();
            ToastUtils.showToast(com.jingdong.app.mall.e.b().a(), failResult.getMessage());
        }

        @Override // jd.wjlogin_sdk.common.listener.OnCommonCallback
        public void onSuccess() {
            if (Log.D) {
                Log.d(CommonUtilEx.TAG, "toClient()  -->> h5ToApp  tokenKey :  " + this.a + " , pin : " + this.b.getPin());
            }
            BusinessRegistObserverManager.getInstance().notifyRegistSuccess();
            String string = SafetyManager.getString(LoginConstans.LOGIN_LAST_WAY_TEMP, "");
            if (LoginConstans.LOGIN_LAST_WAY_QQ_SENDMSG.equals(string)) {
                if (AccessibilityHelper.isAccessibilityEnabled(JdSdk.getInstance().getApplication())) {
                    CommonUtilEx.this.sendBaoguangAction("NewLogin_QQRiskLogSucExpo", "MessageBind_MessageNumber", "1");
                } else {
                    CommonUtilEx.this.sendBaoguangAction("NewLogin_QQRiskLogSucExpo", "MessageBind_MessageNumber", "0");
                }
                SafetyManager.putString(LoginConstans.LOGIN_LAST_WAY, LoginConstans.LOGIN_LAST_WAY_QQ);
            } else if (LoginConstans.LOGIN_LAST_WAY_WX_SENDMSG.equals(string)) {
                if (AccessibilityHelper.isAccessibilityEnabled(JdSdk.getInstance().getApplication())) {
                    CommonUtilEx.this.sendBaoguangAction("NewLogin_WXRiskLogSucExpo", "MessageBind_MessageNumber", "1");
                } else {
                    CommonUtilEx.this.sendBaoguangAction("NewLogin_WXRiskLogSucExpo", "MessageBind_MessageNumber", "0");
                }
                SafetyManager.putString(LoginConstans.LOGIN_LAST_WAY, LoginConstans.LOGIN_LAST_WAY_WX);
            } else if (LoginConstans.LOGIN_LAST_WAY_FACE_SENDMSG.equals(string)) {
                if (AccessibilityHelper.isAccessibilityEnabled(JdSdk.getInstance().getApplication())) {
                    CommonUtilEx.this.sendBaoguangAction("NewLogin_FaceRiskSucExpo", "NewLogin_FaceLogin", "1");
                } else {
                    CommonUtilEx.this.sendBaoguangAction("NewLogin_FaceRiskSucExpo", "NewLogin_FaceLogin", "0");
                }
                SafetyManager.putString(LoginConstans.LOGIN_LAST_WAY, LoginConstans.LOGIN_LAST_WAY_FACE);
            } else if (LoginConstans.LOGIN_LAST_WAY_PHONENUM.equals(string)) {
                if (AccessibilityHelper.isAccessibilityEnabled(JdSdk.getInstance().getApplication())) {
                    CommonUtilEx.this.sendBaoguangAction("NewLogin_CodeRiskSucExpo", "PhoneLogin_MsgCode", "1");
                } else {
                    CommonUtilEx.this.sendBaoguangAction("NewLogin_CodeRiskSucExpo", "PhoneLogin_MsgCode", "0");
                }
                SafetyManager.putString(LoginConstans.LOGIN_LAST_WAY, LoginConstans.LOGIN_LAST_WAY_PHONENUM);
            } else if (LoginConstans.LOGIN_LAST_WAY_CHINAMOBILE_VOICE_VERIFICATION.equals(string)) {
                String string2 = SafetyManager.getString(LoginConstans.LOGIN_LAST_WAY_TELECOM_OPERATERTYPE, "");
                if (AccessibilityHelper.isAccessibilityEnabled(JdSdk.getInstance().getApplication())) {
                    if ("CT".equals(string2)) {
                        CommonUtilEx.this.sendBaoguangAction("NewLogin_OneClickRiskExpo_CTCC", "PhoneLogin_CTCC", "1");
                        if (Log.D) {
                            Log.d(CommonUtilEx.TAG, "chinamobile = NewLogin_OneClickRiskExpo_CTCC");
                        }
                    } else if ("CU".equals(string2)) {
                        CommonUtilEx.this.sendBaoguangAction("NewLogin_OneClickRiskExpo_CUCC", "PhoneLogin_CUCC", "1");
                        if (Log.D) {
                            Log.d(CommonUtilEx.TAG, "chinamobile = NewLogin_OneClickRiskExpo_CUCC");
                        }
                    } else {
                        CommonUtilEx.this.sendBaoguangAction("NewLogin_OneClickRiskExpo_CMCC", "PhoneLogin_CMCC", "1");
                        if (Log.D) {
                            Log.d(CommonUtilEx.TAG, "chinamobile = NewLogin_OneClickRiskExpo_CMCC");
                        }
                    }
                } else if ("CT".equals(string2)) {
                    CommonUtilEx.this.sendBaoguangAction("NewLogin_OneClickRiskExpo_CTCC", "PhoneLogin_CTCC", "0");
                    if (Log.D) {
                        Log.d(CommonUtilEx.TAG, "onClickWithPageId = NewLogin_OneClickRiskExpo_CTCC");
                    }
                } else if ("CU".equals(string2)) {
                    CommonUtilEx.this.sendBaoguangAction("NewLogin_OneClickRiskExpo_CUCC", "PhoneLogin_CUCC", "0");
                    if (Log.D) {
                        Log.d(CommonUtilEx.TAG, "onClickWithPageId = NewLogin_OneClickRiskExpo_CUCC");
                    }
                } else {
                    CommonUtilEx.this.sendBaoguangAction("NewLogin_OneClickRiskExpo_CMCC", "PhoneLogin_CMCC", "0");
                    if (Log.D) {
                        Log.d(CommonUtilEx.TAG, "onClickWithPageId = NewLogin_OneClickRiskExpo_CMCC");
                    }
                }
                SafetyManager.putString(LoginConstans.LOGIN_LAST_WAY, LoginConstans.LOGIN_LAST_WAY_MOBILE);
            } else if (LoginConstans.LOGIN_LAST_WAY_ONEKEY_DAILOG.equals(string)) {
                String string3 = SafetyManager.getString(LoginConstans.LOGIN_LAST_WAY_TELECOM_OPERATERTYPE, "");
                if (AccessibilityHelper.isAccessibilityEnabled(JdSdk.getInstance().getApplication())) {
                    if ("CT".equals(string3)) {
                        CommonUtilEx.this.sendBaoguangAction("NewLogin_RiskSucExpo_CTCC", "DialogLogin", "1");
                        if (Log.D) {
                            Log.d(CommonUtilEx.TAG, "chinamobile = NewLogin_OneClickRiskExpo_CTCC");
                        }
                    } else if ("CU".equals(string3)) {
                        CommonUtilEx.this.sendBaoguangAction("NewLogin_RiskSucExpo_CUCC", "DialogLogin", "1");
                        if (Log.D) {
                            Log.d(CommonUtilEx.TAG, "chinamobile = NewLogin_OneClickRiskExpo_CUCC");
                        }
                    } else {
                        CommonUtilEx.this.sendBaoguangAction("NewLogin_RiskSucExpo_CMCC", "DialogLogin", "1");
                        if (Log.D) {
                            Log.d(CommonUtilEx.TAG, "chinamobile = NewLogin_OneClickRiskExpo_CMCC");
                        }
                    }
                } else if ("CT".equals(string3)) {
                    CommonUtilEx.this.sendBaoguangAction("NewLogin_RiskSucExpo_CTCC", "DialogLogin", "0");
                    if (Log.D) {
                        Log.d(CommonUtilEx.TAG, "onClickWithPageId = NewLogin_OneClickRiskExpo_CTCC");
                    }
                } else if ("CU".equals(string3)) {
                    CommonUtilEx.this.sendBaoguangAction("NewLogin_RiskSucExpo_CUCC", "DialogLogin", "0");
                    if (Log.D) {
                        Log.d(CommonUtilEx.TAG, "onClickWithPageId = NewLogin_OneClickRiskExpo_CUCC");
                    }
                } else {
                    CommonUtilEx.this.sendBaoguangAction("NewLogin_RiskSucExpo_CMCC", "DialogLogin", "0");
                    if (Log.D) {
                        Log.d(CommonUtilEx.TAG, "onClickWithPageId = NewLogin_OneClickRiskExpo_CMCC");
                    }
                }
                SafetyManager.putString(LoginConstans.LOGIN_LAST_WAY, LoginConstans.LOGIN_LAST_WAY_MOBILE);
            } else if (LoginConstans.LOGIN_LAST_WAY_SMS_DAILOG.equals(string)) {
                if (AccessibilityHelper.isAccessibilityEnabled(JdSdk.getInstance().getApplication())) {
                    CommonUtilEx.this.sendBaoguangAction("NewLogin_CodeRiskSucToastExpo", "PhoneLogin_MsgCode", "1");
                } else {
                    CommonUtilEx.this.sendBaoguangAction("NewLogin_CodeRiskSucToastExpo", "PhoneLogin_MsgCode", "0");
                }
                SafetyManager.putString(LoginConstans.LOGIN_LAST_WAY, LoginConstans.LOGIN_LAST_WAY_PHONENUM);
            } else if (LoginConstans.LOGIN_LAST_WAY_QQ_BIND.equals(string)) {
                if (AccessibilityHelper.isAccessibilityEnabled(JdSdk.getInstance().getApplication())) {
                    CommonUtilEx.this.sendBaoguangAction("NewLogin_QQRiskBindSucExpo", "NativeUnionBind", "1");
                } else {
                    CommonUtilEx.this.sendBaoguangAction("NewLogin_QQRiskBindSucExpo", "NativeUnionBind", "0");
                }
                SafetyManager.putString(LoginConstans.LOGIN_LAST_WAY, LoginConstans.LOGIN_LAST_WAY_QQ);
            } else if (LoginConstans.LOGIN_LAST_WAY_WX_BIND.equals(string)) {
                if (AccessibilityHelper.isAccessibilityEnabled(JdSdk.getInstance().getApplication())) {
                    CommonUtilEx.this.sendBaoguangAction("NewLogin_WXRiskBindSucExpo", "NativeUnionBind", "1");
                } else {
                    CommonUtilEx.this.sendBaoguangAction("NewLogin_WXRiskBindSucExpo", "NativeUnionBind", "0");
                }
                SafetyManager.putString(LoginConstans.LOGIN_LAST_WAY, LoginConstans.LOGIN_LAST_WAY_WX);
            } else {
                if (AccessibilityHelper.isAccessibilityEnabled(JdSdk.getInstance().getApplication())) {
                    CommonUtilEx.this.sendBaoguangAction("NewLogin_PassRiskSucExpo", "NewLogin", "1");
                } else {
                    CommonUtilEx.this.sendBaoguangAction("NewLogin_PassRiskSucExpo", "NewLogin", "0");
                }
                SafetyManager.putString(LoginConstans.LOGIN_LAST_WAY, LoginConstans.LOGIN_LAST_WAY_ACCOUNT);
            }
            SafetyManager.putString(LoginConstans.LOGIN_LAST_WAY_TEMP, "");
            LoginUserBase.saveInfoAfterLogin();
        }
    }

    /* loaded from: classes4.dex */
    class c implements HttpGroup.OnCommonListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f11737g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ ExceptionReporter f11738h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ HttpGroup f11739i;

        /* loaded from: classes4.dex */
        class a implements HttpGroup.OnCommonListener {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ String f11741g;

            /* renamed from: h  reason: collision with root package name */
            final /* synthetic */ String f11742h;

            /* renamed from: i  reason: collision with root package name */
            final /* synthetic */ String f11743i;

            /* renamed from: com.jingdong.app.mall.utils.CommonUtilEx$c$a$a  reason: collision with other inner class name */
            /* loaded from: classes4.dex */
            class C0382a extends DialogController {
                C0382a() {
                }

                @Override // com.jingdong.common.ui.DialogController, android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    if (i2 != -3) {
                        return;
                    }
                    CommonUtilEx.this.startGoodsOrderList();
                }
            }

            /* loaded from: classes4.dex */
            class b implements Runnable {

                /* renamed from: g  reason: collision with root package name */
                final /* synthetic */ DialogController f11746g;

                b(a aVar, DialogController dialogController) {
                    this.f11746g = dialogController;
                }

                @Override // java.lang.Runnable
                public void run() {
                    this.f11746g.show();
                }
            }

            a(String str, String str2, String str3) {
                this.f11741g = str;
                this.f11742h = str2;
                this.f11743i = str3;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                String stringOrNull = jSONObject.getStringOrNull("error");
                String stringOrNull2 = jSONObject.getStringOrNull("message");
                if (TextUtils.isEmpty(stringOrNull2)) {
                    if (!TextUtils.isEmpty(stringOrNull)) {
                        C0382a c0382a = new C0382a();
                        c0382a.setTitle(JdSdk.getInstance().getApplication().getString(R.string.k0));
                        c0382a.setMessage(stringOrNull);
                        c0382a.setNeutralButton(JdSdk.getInstance().getApplication().getString(R.string.ig));
                        c0382a.init(com.jingdong.app.mall.e.b().a().getThisActivity());
                        com.jingdong.app.mall.e.b().a().post(new b(this, c0382a));
                    }
                    c.this.f11738h.reportHttpBusinessException(httpResponse);
                    return;
                }
                CommonUtilEx.this.doPay(com.jingdong.app.mall.e.b().a(), stringOrNull2);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                if (!TextUtils.isEmpty(this.f11741g)) {
                    httpSettingParams.putJsonParam("pin", this.f11741g);
                }
                if (!TextUtils.isEmpty(this.f11742h)) {
                    httpSettingParams.putJsonParam("orderId", this.f11742h);
                }
                if (!TextUtils.isEmpty(this.f11743i)) {
                    httpSettingParams.putJsonParam("plat", this.f11743i);
                } else {
                    httpSettingParams.putJsonParam("plat", "mobile");
                }
            }
        }

        /* loaded from: classes4.dex */
        class b implements HttpGroup.OnCommonListener {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ String f11747g;

            /* renamed from: h  reason: collision with root package name */
            final /* synthetic */ String f11748h;

            /* loaded from: classes4.dex */
            class a extends DialogController {
                a() {
                }

                @Override // com.jingdong.common.ui.DialogController, android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    if (i2 != -3) {
                        return;
                    }
                    CommonUtilEx.this.startGoodsOrderList();
                }
            }

            /* renamed from: com.jingdong.app.mall.utils.CommonUtilEx$c$b$b  reason: collision with other inner class name */
            /* loaded from: classes4.dex */
            class RunnableC0383b implements Runnable {

                /* renamed from: g  reason: collision with root package name */
                final /* synthetic */ DialogController f11751g;

                RunnableC0383b(b bVar, DialogController dialogController) {
                    this.f11751g = dialogController;
                }

                @Override // java.lang.Runnable
                public void run() {
                    this.f11751g.show();
                }
            }

            b(String str, String str2) {
                this.f11747g = str;
                this.f11748h = str2;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                BaseActivity a2;
                if (Log.D) {
                    Log.d(CommonUtilEx.TAG, "weixinpay onEnd() -->> ");
                }
                JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                if (Log.D) {
                    Log.d(CommonUtilEx.TAG, "weixinpay onEnd() -->> jsonObject = " + jSONObject);
                }
                String stringOrNull = jSONObject.getStringOrNull("message");
                JSONObjectProxy jSONObjectOrNull = jSONObject.getJSONObjectOrNull("body");
                if (Log.D) {
                    Log.d(CommonUtilEx.TAG, "weixinpay onEnd() -->>  message = " + stringOrNull);
                    Log.d(CommonUtilEx.TAG, "weixinpay onEnd() -->>  json = " + jSONObjectOrNull);
                }
                if (jSONObjectOrNull == null) {
                    if (TextUtils.isEmpty(stringOrNull) || (a2 = com.jingdong.app.mall.e.b().a()) == null) {
                        return;
                    }
                    a aVar = new a();
                    aVar.setTitle(JdSdk.getInstance().getApplication().getString(R.string.k0));
                    aVar.setMessage(stringOrNull);
                    aVar.setNeutralButton(JdSdk.getInstance().getApplication().getString(R.string.ig));
                    aVar.init(a2.getThisActivity());
                    a2.post(new RunnableC0383b(this, aVar));
                    c.this.f11738h.reportHttpBusinessException(httpResponse);
                    return;
                }
                WeiXinEntity weiXinEntity = new WeiXinEntity(jSONObjectOrNull);
                WeiXinPayUtil.setWeiXinInfo(weiXinEntity);
                WeiXinPayUtil.doWeiXinPay(weiXinEntity);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                if (Log.D) {
                    Log.d(CommonUtilEx.TAG, "weixinpay onError() -->> ");
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                if (Log.D) {
                    Log.d(CommonUtilEx.TAG, "weixinpay onReady() -->> orderId = " + this.f11747g + " pinFinal = " + this.f11748h);
                }
                if (!TextUtils.isEmpty(this.f11747g)) {
                    httpSettingParams.putJsonParam("orderId", this.f11747g);
                }
                if (TextUtils.isEmpty(this.f11748h)) {
                    return;
                }
                httpSettingParams.putJsonParam("pin", this.f11748h);
            }
        }

        c(String str, ExceptionReporter exceptionReporter, HttpGroup httpGroup) {
            this.f11737g = str;
            this.f11738h = exceptionReporter;
            this.f11739i = httpGroup;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            JSONObjectProxy jSONObjectOrNull = httpResponse.getJSONObject().getJSONObjectOrNull("tokenValue");
            if (jSONObjectOrNull != null) {
                String stringOrNull = jSONObjectOrNull.getStringOrNull("action");
                if (Log.D) {
                    Log.d(CommonUtilEx.TAG, "onEnd() -->> action = " + stringOrNull);
                }
                if (stringOrNull == null) {
                    this.f11738h.reportHttpBusinessException(httpResponse);
                    return;
                }
                String stringOrNull2 = jSONObjectOrNull.getStringOrNull("pin");
                String loginUserName = LoginUserBase.getLoginUserName();
                if (stringOrNull2 == null) {
                    stringOrNull2 = "";
                }
                if (loginUserName == null) {
                    loginUserName = "";
                }
                if (!loginUserName.equals(stringOrNull2)) {
                    if (Log.D) {
                        Log.d(CommonUtilEx.TAG, "toClient() pin change -->> old:" + loginUserName + ",new:" + stringOrNull2);
                    }
                    BaseActivity a2 = com.jingdong.app.mall.e.b().a();
                    if (Log.D) {
                        Log.d(CommonUtilEx.TAG, "toClient() pin change -->> myActivity :" + a2);
                    }
                    if (TextUtils.isEmpty(stringOrNull2)) {
                        com.jingdong.app.mall.n.c.b(false);
                        LoginUserBase.setUserStateOff(true);
                        Constants.clearOrderInfo();
                        UserUtil.cleanData(a2);
                    } else if (!"thirdLogin".equals(stringOrNull) && !"weixin".equals(stringOrNull) && !JumpUtil.VALUE_DES_CPS_UNION.equals(stringOrNull)) {
                        com.jingdong.app.mall.n.c.b(false);
                    }
                }
                if (!"thirdLogin".equals(stringOrNull) && !"weixin".equals(stringOrNull) && !JumpUtil.VALUE_DES_CPS_UNION.equals(stringOrNull)) {
                    if ("pay".equals(stringOrNull)) {
                        if (Log.D) {
                            Log.d(CommonUtilEx.TAG, "toClient() pay -->> ");
                        }
                        BaseActivity baseActivity = (BaseActivity) BaseFrameUtil.getInstance().getCurrentMyActivity();
                        if (!(baseActivity instanceof IMainActivity) || com.jingdong.app.mall.n.c.a) {
                            JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_APPHOME, baseActivity, null);
                            return;
                        }
                        return;
                    } else if ("unionpay".equals(stringOrNull)) {
                        if (Log.D) {
                            Log.d(CommonUtilEx.TAG, "toClient() unionpay -->> ");
                        }
                        if (CommonUtilEx.this.checkSDKForPay()) {
                            String stringOrNull3 = jSONObjectOrNull.getStringOrNull("orderId");
                            String stringOrNull4 = jSONObjectOrNull.getStringOrNull("plat");
                            HttpSetting httpSetting = new HttpSetting();
                            httpSetting.setFunctionId("jdMPay");
                            this.f11738h.attachHttpSetting(httpSetting);
                            httpSetting.setListener(new a(stringOrNull2, stringOrNull3, stringOrNull4));
                            httpSetting.setNotifyUser(true);
                            this.f11739i.add(httpSetting);
                            return;
                        }
                        return;
                    } else if ("weixinpay".equals(stringOrNull)) {
                        String stringOrNull5 = jSONObjectOrNull.getStringOrNull("orderId");
                        HttpSetting httpSetting2 = new HttpSetting();
                        httpSetting2.setHost(HostConfig.getInstance().getHost(HostConstants.PERSONAL_HOST));
                        httpSetting2.setFunctionId(JumpUtils.FUNCTION_ID_WEIXINPAY);
                        httpSetting2.setNotifyUser(true);
                        this.f11738h.attachHttpSetting(httpSetting2);
                        httpSetting2.setListener(new b(stringOrNull5, stringOrNull2));
                        this.f11739i.add(httpSetting2);
                        return;
                    } else {
                        return;
                    }
                }
                if (Log.D) {
                    Log.d(CommonUtilEx.TAG, "toClient()  -->>action :  " + stringOrNull + " , pin : " + stringOrNull2);
                }
                Log.e(CommonUtilEx.TAG, "toClient()  -->>action :  " + stringOrNull + " , pin : " + stringOrNull2);
                if (!TextUtils.isEmpty(stringOrNull2)) {
                    LoginUserBase.saveInfoAfterLogin();
                    return;
                } else {
                    this.f11738h.reportHttpBusinessException(httpResponse);
                    return;
                }
            }
            this.f11738h.reportHttpBusinessException(httpResponse);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            httpSettingParams.putJsonParam("tokenKey", this.f11737g);
        }
    }

    /* loaded from: classes4.dex */
    class d implements ViewTreeObserver.OnScrollChangedListener {
        final /* synthetic */ Field a;
        final /* synthetic */ PopupWindow b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ ViewTreeObserver.OnScrollChangedListener f11752c;

        d(Field field, PopupWindow popupWindow, ViewTreeObserver.OnScrollChangedListener onScrollChangedListener) {
            this.a = field;
            this.b = popupWindow;
            this.f11752c = onScrollChangedListener;
        }

        @Override // android.view.ViewTreeObserver.OnScrollChangedListener
        public void onScrollChanged() {
            try {
                if (((View) this.a.get(this.b)) == null) {
                    return;
                }
                this.f11752c.onScrollChanged();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* loaded from: classes4.dex */
    class e implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ BaseActivity f11753g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f11754h;

        /* loaded from: classes4.dex */
        class a implements View.OnClickListener {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ com.jingdong.app.mall.utils.ui.b f11755g;

            a(com.jingdong.app.mall.utils.ui.b bVar) {
                this.f11755g = bVar;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                this.f11755g.dismiss();
                e.this.f11753g.finish();
            }
        }

        e(BaseActivity baseActivity, String str) {
            this.f11753g = baseActivity;
            this.f11754h = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.app.mall.utils.ui.b bVar = new com.jingdong.app.mall.utils.ui.b(this.f11753g);
            try {
                bVar.show();
            } catch (Throwable th) {
                if (Log.E) {
                    th.printStackTrace();
                }
            }
            bVar.a(this.f11753g.getString(R.string.ig), new a(bVar));
            bVar.b(this.f11754h);
        }
    }

    /* loaded from: classes4.dex */
    class f implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ BaseActivity f11757g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f11758h;

        /* loaded from: classes4.dex */
        class a implements View.OnClickListener {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ com.jingdong.app.mall.utils.ui.b f11759g;

            a(f fVar, com.jingdong.app.mall.utils.ui.b bVar) {
                this.f11759g = bVar;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                this.f11759g.dismiss();
            }
        }

        f(BaseActivity baseActivity, String str) {
            this.f11757g = baseActivity;
            this.f11758h = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.app.mall.utils.ui.b bVar = new com.jingdong.app.mall.utils.ui.b(this.f11757g);
            try {
                bVar.show();
            } catch (Throwable th) {
                if (Log.E) {
                    th.printStackTrace();
                }
            }
            bVar.a(this.f11757g.getString(R.string.ig), new a(this, bVar));
            bVar.b(this.f11758h);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class g implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ BaseActivity f11760g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f11761h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ String f11762i;

        /* loaded from: classes4.dex */
        class a implements View.OnClickListener {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ com.jingdong.app.mall.utils.ui.b f11763g;

            a(g gVar, com.jingdong.app.mall.utils.ui.b bVar) {
                this.f11763g = bVar;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                this.f11763g.dismiss();
            }
        }

        g(CommonUtilEx commonUtilEx, BaseActivity baseActivity, String str, String str2) {
            this.f11760g = baseActivity;
            this.f11761h = str;
            this.f11762i = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.app.mall.utils.ui.b bVar = new com.jingdong.app.mall.utils.ui.b(this.f11760g);
            try {
                bVar.show();
            } catch (Throwable th) {
                if (Log.E) {
                    th.printStackTrace();
                }
            }
            bVar.b(this.f11761h);
            bVar.a(this.f11762i, new a(this, bVar));
        }
    }

    private CommonUtilEx() {
    }

    public static void ShowMsg(BaseActivity baseActivity, String str) {
        baseActivity.post(new f(baseActivity, str));
    }

    private void bindLogin(String str) {
        WJLoginHelper wJLoginHelper = UserUtil.getWJLoginHelper();
        if (Log.D) {
            Log.d(TAG, "toClient()  -->> bindLogin  tokenKey :  " + str + " , pin : " + wJLoginHelper.getPin());
        }
        wJLoginHelper.bindAccountLogin(str, new a(str, wJLoginHelper));
    }

    public static void exitCurrentPage(BaseActivity baseActivity, String str) {
        baseActivity.post(new e(baseActivity, str));
    }

    public static void fixPopWindowBug(PopupWindow popupWindow) {
        if (Build.VERSION.SDK_INT < 14) {
            try {
                Field declaredField = PopupWindow.class.getDeclaredField("mAnchor");
                declaredField.setAccessible(true);
                Field declaredField2 = PopupWindow.class.getDeclaredField("mOnScrollChangedListener");
                declaredField2.setAccessible(true);
                declaredField2.set(popupWindow, new d(declaredField, popupWindow, (ViewTreeObserver.OnScrollChangedListener) declaredField2.get(popupWindow)));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void forwardLogin(BaseActivity baseActivity) {
        if (Log.D) {
            Log.d(TAG, " forwardLogin -->> ");
        }
        Bundle bundle = new Bundle();
        bundle.putInt("com.360buy:navigationDisplayFlag", -1);
        DeepLinkLoginHelper.startLoginActivity(baseActivity, bundle);
        ToastUtils.shortToast((int) R.string.login_first);
    }

    public static int getColor(int i2) {
        return JdSdk.getInstance().getApplication().getResources().getColor(i2);
    }

    public static CommonUtilEx getInstance() {
        if (commonUtilEx == null) {
            commonUtilEx = new CommonUtilEx();
        }
        return commonUtilEx;
    }

    public static int getLocalNotificationCount() {
        return CommonBase.getJdSharedPreferences().getInt("local_notification_count", 0);
    }

    public static String getString(int i2) {
        return JdSdk.getInstance().getApplication().getResources().getString(i2);
    }

    private void h5ToApp(String str) {
        WJLoginHelper wJLoginHelper = UserUtil.getWJLoginHelper();
        if (Log.D) {
            Log.d(TAG, "toClient()  -->> h5ToApp  tokenKey :  " + str + " , pin : " + wJLoginHelper.getPin());
        }
        wJLoginHelper.h5BackToApp(str, new b(str, wJLoginHelper));
    }

    public static boolean isShowJdNewIcon() {
        int i2;
        try {
            i2 = Integer.parseInt(ConfigUtil.getStringFromPreference(ConfigUtil.NEW_ICON, "0"));
        } catch (NumberFormatException unused) {
            i2 = 0;
        }
        return i2 > CommonBase.getIntFromPreference("jdNewIcon", 0);
    }

    public static void putLocalNotificationCount(int i2) {
        CommonBase.getJdSharedPreferences().edit().putInt("local_notification_count", i2).apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendBaoguangAction(String str, String str2, String str3) {
        if (Log.D) {
            Log.e(TAG, "sendAction  eventId===" + str + " eventParam=" + str3);
        }
        JDMtaUtils.sendExposureDataWithExt(com.jingdong.app.mall.e.b().a(), str, "", "", str2, TAG, "", null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showOneBtnDialog(BaseActivity baseActivity, String str, String str2) {
        baseActivity.post(new g(this, baseActivity, str, str2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startGoodsOrderList() {
        BaseActivity a2 = com.jingdong.app.mall.e.b().a();
        if (a2 == null) {
            return;
        }
        DeepLinkOrderCenterHelper.startOrderList(a2);
    }

    public static long[] toHMS(long j2) {
        long j3 = (((j2 / 1000) / 60) / 60) / 24;
        long j4 = 24 * j3;
        long j5 = j2 - (((j4 * 1000) * 60) * 60);
        long j6 = ((j5 / 1000) / 60) / 60;
        long j7 = j6 * 60 * 60 * 1000;
        long j8 = ((j5 - j7) / 1000) / 60;
        long j9 = (((j2 - (((j4 * 60) * 60) * 1000)) - j7) - ((60 * j8) * 1000)) / 1000;
        if (j3 < 0) {
            j3 = 0;
        }
        if (j6 < 0) {
            j6 = 0;
        }
        if (j8 < 0) {
            j8 = 0;
        }
        if (j9 < 0) {
            j9 = 0;
        }
        return new long[]{j3, j6, j8, j9};
    }

    private void toLogin() {
        BaseActivity a2 = com.jingdong.app.mall.e.b().a();
        if (a2 == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("com.360buy:navigationDisplayFlag", -1);
        DeepLinkLoginHelper.startLoginActivity(a2, bundle);
        if (LoginConstans.LOGINACTIVITY_PATH.equals(a2.getClass().getName())) {
            a2.finish();
        }
        if (Log.D) {
            System.out.println("commonUtilEx \u76d1\u542c\u70b9\u51fb\u53bb\u767b\u9646\u6309\u94ae+++++toLogin finish");
        }
    }

    public static void updateJdNewIcon() {
        int i2;
        try {
            i2 = Integer.parseInt(ConfigUtil.getStringFromPreference(ConfigUtil.NEW_ICON, "0"));
        } catch (NumberFormatException unused) {
            i2 = 0;
        }
        if (i2 > CommonBase.getIntFromPreference("jdNewIcon", 0)) {
            CommonBase.putIntToPreference("jdNewIcon", i2);
        }
    }

    @Override // com.jingdong.common.utils.ICommon
    public void backToHomePage(Context context) {
        if (goToMainFrameActivity(context) != null) {
            com.jingdong.app.mall.n.c.c();
        }
    }

    @Override // com.jingdong.common.utils.ICommon
    public boolean checkSDKForPay() {
        int i2;
        try {
            i2 = Integer.parseInt(Build.VERSION.SDK);
        } catch (Throwable th) {
            th.printStackTrace();
            i2 = 7;
        }
        if (i2 < 7) {
            showNoticeDialogStyle1(JdSdk.getInstance().getApplication().getString(R.string.ne));
            return false;
        }
        return true;
    }

    @Override // com.jingdong.common.utils.ICommon
    @Deprecated
    public void forwardWebActivity(IMyActivity iMyActivity, String str) {
        forwardWebActivity(iMyActivity, str, new URLParamMap(), false);
    }

    @Override // com.jingdong.common.utils.ICommon
    @Deprecated
    public void forwardWebActivity(IMyActivity iMyActivity, String str, URLParamMap uRLParamMap) {
    }

    @Override // com.jingdong.common.utils.ICommon
    @Deprecated
    public void forwardWebActivity(IMyActivity iMyActivity, String str, URLParamMap uRLParamMap, boolean z) {
    }

    @Override // com.jingdong.common.utils.ICommon
    @Deprecated
    public void forwardWebActivityForAction(Context context, String str, URLParamMap uRLParamMap) {
    }

    public IMainActivity goToMainFrameActivity(IMyActivity iMyActivity) {
        return goToMainFrameActivity(iMyActivity.getThisActivity());
    }

    @Override // com.jingdong.common.utils.ICommon
    public void goToShoppingCartPage(IMyActivity iMyActivity, boolean z) {
        if (Log.D) {
            Log.d(TAG, " -->> goToShoppingCartPage " + iMyActivity.toString());
        }
        if (BaseFrameUtil.getInstance().getMainFrameActivity() == null) {
            JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_GO_CART, iMyActivity.getThisActivity(), null);
            iMyActivity.getThisActivity().finish();
            return;
        }
        goToShoppingCartPage(iMyActivity.getThisActivity(), z);
        iMyActivity.getThisActivity().finish();
    }

    @Override // com.jingdong.common.utils.ICommon
    public void goToShoppingCartPageSingle(IMyActivity iMyActivity) {
        if (Log.D) {
            Log.d(TAG, " -->> goToShoppingCartPage " + iMyActivity.toString());
        }
        DeepLinkCartHelper.startCartMain(iMyActivity.getThisActivity(), null);
    }

    public void gotoHomePage(Context context) {
        if (goToMainFrameActivity(context) != null) {
            com.jingdong.app.mall.n.c.c();
        }
    }

    public void gotoMainFrameClearAllTask(Activity activity) {
        Intent intent = new Intent(activity, JDTaskClearActivity.class);
        intent.setFlags(67108864);
        activity.startActivity(intent);
        activity.finish();
    }

    public void gotoPersonalPage(Context context) {
        if (goToMainFrameActivity(context) != null) {
            com.jingdong.app.mall.n.c.d();
        }
    }

    public void phoneCall(String str) {
        try {
            Intent intent = new Intent("android.intent.action.DIAL", Uri.parse(WebView.SCHEME_TEL + str));
            intent.setFlags(268435456);
            JdSdk.getInstance().getApplication().startActivity(intent);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public CharSequence renderNameAndAdword(String str, String str2, int i2) {
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(i2);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        int length = spannableStringBuilder.length();
        spannableStringBuilder.append((CharSequence) str2);
        spannableStringBuilder.setSpan(foregroundColorSpan, length, spannableStringBuilder.length(), 17);
        return spannableStringBuilder;
    }

    @Override // com.jingdong.common.utils.ICommon
    public void startActivityInFrame(Context context, Intent intent) {
        context.startActivity(intent);
    }

    @Override // com.jingdong.common.utils.ICommon
    public void toClient(String str, String str2, String str3) {
        Log.d(TAG, "toClient-->> tokenKey");
        if (System.currentTimeMillis() - lastToClientTimeMillis < 3000) {
            return;
        }
        lastToClientTimeMillis = System.currentTimeMillis();
        if (!TextUtils.isEmpty(str2) && IExceptionHandler.DynamicExceptionData.TYPE_BIND.equals(str2)) {
            bindLogin(str);
        } else if (!TextUtils.isEmpty(str2) && "toMSM".equals(str2)) {
            h5ToApp(str);
        } else if (!TextUtils.isEmpty(str2) && LoginConstans.FREGMENT_LOGIN_FLAG.equals(str2)) {
            if (Log.D) {
                System.out.println("commonUtilEx \u76d1\u542c\u70b9\u51fb\u53bb\u767b\u9646\u6309\u94ae+++++action:" + str2);
            }
            toLogin();
        } else if (!TextUtils.isEmpty(str2) && NotificationCompat.CATEGORY_CALL.equals(str2)) {
            if (Log.D) {
                System.out.println("commonUtilEx \u76d1\u542c\u70b9\u51fb\u53bb\u767b\u9646\u6309\u94ae+++++action:" + str2);
            }
            phoneCall(str3);
        } else {
            HttpGroupSetting createNewSettings = HttpGroupUtils.createNewSettings();
            createNewSettings.setType(1000);
            createNewSettings.setMyActivity((Activity) BaseFrameUtil.getInstance().getCurrentMyActivity());
            HttpGroup httpGroup = HttpGroup.getHttpGroup(createNewSettings);
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setNotifyUser(true);
            httpSetting.setFunctionId("getToken");
            httpSetting.setListener(new c(str, new ExceptionReporter(httpSetting), httpGroup));
            httpGroup.add(httpSetting);
        }
    }

    private void goToShoppingCartPage(Context context, boolean z) {
        if (goToMainFrameActivity(context) != null) {
            com.jingdong.app.mall.n.c.e();
        }
    }
}
