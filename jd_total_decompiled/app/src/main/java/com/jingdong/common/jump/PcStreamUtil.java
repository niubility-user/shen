package com.jingdong.common.jump;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.jingdong.common.R;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.login.ILogin;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class PcStreamUtil {
    private static final String TAG = "PcStreamUtil";
    private Context context;

    public PcStreamUtil(Context context) {
        this.context = context;
    }

    public void jumpToHome() {
        try {
            DeepLinkCommonHelper.startActivity(this.context, DeepLinkCommonHelper.HOST_JD_TASK_CLEAR_ACTIVITY, null, true, 67108864, false, "");
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
            Context context = this.context;
            if (context instanceof Activity) {
                ((Activity) context).finish();
            }
        }
    }

    public void jumpToLogin(final Bundle bundle, final String str, final String str2) {
        final String str3 = "PCforward" + str2;
        DeepLinkLoginHelper.startLoginActivity(this.context, null, new ILogin() { // from class: com.jingdong.common.jump.PcStreamUtil.3
            {
                PcStreamUtil.this = this;
            }

            @Override // com.jingdong.common.login.ILogin
            public void onSuccess(String str4) {
                if (str3.equals(str4)) {
                    PcStreamUtil.this.pcTokenVerify(bundle, str, str2);
                }
            }
        }, str3);
    }

    public void mta(Bundle bundle, String str, String str2) {
        String string = bundle.getString("des");
        String string2 = bundle.getString("orderId");
        StringBuilder sb = new StringBuilder();
        sb.append(string);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        if (TextUtils.isEmpty(string2)) {
            string2 = "100";
        }
        sb.append(string2);
        JDMtaUtils.sendCommonData(this.context, str, sb.toString(), str2, "", "", "", "", "PCScantoAPP");
    }

    public void pcTokenVerify(final Bundle bundle, final String str, final String str2) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("pcTokenVerify");
        httpSetting.setHost(Configuration.getNgwHost());
        httpSetting.putJsonParam("token", str);
        httpSetting.putJsonParam("des", str2);
        httpSetting.setNotifyUser(false);
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.jump.PcStreamUtil.2
            {
                PcStreamUtil.this = this;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                try {
                    int i2 = httpResponse.getJSONObject().getInt("errcode");
                    if (OKLog.D) {
                        OKLog.d(PcStreamUtil.TAG, "errcode:" + i2);
                    }
                    if (i2 == 0) {
                        JumpUtil.toTargetDes(str2, PcStreamUtil.this.context, bundle);
                    } else if (i2 == 181) {
                        PcStreamUtil.this.mta(bundle, "PCScantoAPP_SwitchAccountPopup", "");
                        final JDDialog createJdDialogWithStyle2 = JDDialogFactory.getInstance().createJdDialogWithStyle2(PcStreamUtil.this.context, PcStreamUtil.this.context.getResources().getString(R.string.openApp_change_user), PcStreamUtil.this.context.getResources().getString(R.string.openApp_change_user_cancel), PcStreamUtil.this.context.getResources().getString(R.string.openApp_change_user_ok));
                        createJdDialogWithStyle2.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jump.PcStreamUtil.2.1
                            {
                                AnonymousClass2.this = this;
                            }

                            @Override // android.view.View.OnClickListener
                            public void onClick(View view) {
                                AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                                PcStreamUtil.this.mta(bundle, "PCScantoAPP_SwitchAccountCancel", "onClick");
                                PcStreamUtil.this.jumpToHome();
                                createJdDialogWithStyle2.dismiss();
                            }
                        });
                        createJdDialogWithStyle2.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jump.PcStreamUtil.2.2
                            {
                                AnonymousClass2.this = this;
                            }

                            @Override // android.view.View.OnClickListener
                            public void onClick(View view) {
                                AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                                PcStreamUtil.this.mta(bundle, "PCScantoAPP_SwitchAccountConfirm", "onClick");
                                LoginUserBase.logoutOnlineInfo();
                                AnonymousClass2 anonymousClass22 = AnonymousClass2.this;
                                PcStreamUtil.this.jumpToLogin(bundle, str, str2);
                                createJdDialogWithStyle2.dismiss();
                            }
                        });
                        createJdDialogWithStyle2.show();
                    } else {
                        ToastUtils.showToast(PcStreamUtil.this.context, PcStreamUtil.this.context.getResources().getString(R.string.openApp_error));
                        PcStreamUtil.this.jumpToHome();
                    }
                } catch (JSONException e2) {
                    OKLog.e(PcStreamUtil.TAG, e2);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                if (OKLog.E) {
                    OKLog.e(PcStreamUtil.TAG, "ErrorCode:" + httpError.getErrorCode());
                }
                if (httpError.getErrorCode() == 33) {
                    PcStreamUtil.this.jumpToLogin(bundle, str, str2);
                    return;
                }
                ToastUtils.showToast(PcStreamUtil.this.context, PcStreamUtil.this.context.getResources().getString(R.string.openApp_error));
                PcStreamUtil.this.jumpToHome();
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public void accessPCStream(final String str, final Bundle bundle) {
        String string = bundle.getString("token");
        String string2 = bundle.getString("loginType");
        final String str2 = "PCforward" + str;
        if ("1".equals(string2)) {
            DeepLinkLoginHelper.startLoginActivity(this.context, null, new ILogin() { // from class: com.jingdong.common.jump.PcStreamUtil.1
                {
                    PcStreamUtil.this = this;
                }

                @Override // com.jingdong.common.login.ILogin
                public void onSuccess(String str3) {
                    if (str2.equals(str3)) {
                        JumpUtil.toTargetDes(str, PcStreamUtil.this.context, bundle);
                    }
                }
            }, str2);
        } else if ("2".equals(string2)) {
            pcTokenVerify(bundle, string, str);
        } else if ("3".equals(string2)) {
            JumpUtil.toTargetDes(str, this.context, bundle);
        }
    }
}
