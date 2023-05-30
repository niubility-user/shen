package com.jd.lib.productdetail.core.views;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.R;
import com.jd.lib.productdetail.core.utils.PDDataUtil;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes15.dex */
public class PDAuthCodeDialog {
    private static final String SUB_CODE_0 = "0";
    private static final String SUB_CODE_2 = "2";
    private HttpGroup httpGroup;
    private HttpSetting httpSettingInterrupted;
    private IMyActivity mContext;
    private boolean isDestroy = false;
    private JDDialog authCodeDialog = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.lib.productdetail.core.views.PDAuthCodeDialog$6  reason: invalid class name */
    /* loaded from: classes15.dex */
    public class AnonymousClass6 implements HttpGroup.OnCommonListener {
        final /* synthetic */ String val$bsid;

        AnonymousClass6(String str) {
            this.val$bsid = str;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (PDAuthCodeDialog.this.isDestroy) {
                return;
            }
            final Bitmap parseBitmapFromResponse = PDDataUtil.parseBitmapFromResponse(httpResponse);
            if (parseBitmapFromResponse != null) {
                if (PDAuthCodeDialog.this.mContext != null) {
                    PDAuthCodeDialog.this.mContext.post(new Runnable() { // from class: com.jd.lib.productdetail.core.views.PDAuthCodeDialog.6.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (PDAuthCodeDialog.this.mContext != null) {
                                PDAuthCodeDialog.this.setAuthCodeDialog(parseBitmapFromResponse, new AuthCodeDialogListener() { // from class: com.jd.lib.productdetail.core.views.PDAuthCodeDialog.6.1.1
                                    @Override // com.jd.lib.productdetail.core.views.PDAuthCodeDialog.AuthCodeDialogListener
                                    public void cancleBtn() {
                                        PDAuthCodeDialog.this.mContext.getThisActivity().finish();
                                    }

                                    @Override // com.jd.lib.productdetail.core.views.PDAuthCodeDialog.AuthCodeDialogListener
                                    public void okBtn(String str) {
                                        if (TextUtils.isEmpty(str)) {
                                            return;
                                        }
                                        AnonymousClass6 anonymousClass6 = AnonymousClass6.this;
                                        PDAuthCodeDialog.this.checkAuthCode(anonymousClass6.val$bsid, str);
                                    }

                                    @Override // com.jd.lib.productdetail.core.views.PDAuthCodeDialog.AuthCodeDialogListener
                                    public void updateAuthCodeImg() {
                                        PDAuthCodeDialog.this.getAuthCodeSession();
                                    }
                                });
                                PDAuthCodeDialog.this.showDialog();
                            }
                        }
                    });
                    return;
                }
                return;
            }
            if (Log.D) {
                Log.d("authCodeAuth-response: ", "bitmap = ");
            }
            PDAuthCodeDialog.this.handleCheckAuthCodeException();
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            PDAuthCodeDialog.this.handleCheckAuthCodeException();
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* loaded from: classes15.dex */
    public interface AuthCodeDialogListener {
        void cancleBtn();

        void okBtn(String str);

        void updateAuthCodeImg();
    }

    public PDAuthCodeDialog(IMyActivity iMyActivity, HttpSetting httpSetting, HttpGroup httpGroup) {
        this.mContext = iMyActivity;
        this.httpSettingInterrupted = httpSetting;
        this.httpGroup = httpGroup;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAuthCode(String str, String str2) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("checkAuthCode");
        httpSetting.putJsonParam("bsid", str);
        httpSetting.putJsonParam("code", str2);
        httpSetting.putJsonParam("retk", "1");
        httpSetting.setHost(Configuration.getWareHost());
        httpSetting.setCacheMode(2);
        httpSetting.setEffect(1);
        httpSetting.setNotifyUser(true);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jd.lib.productdetail.core.views.PDAuthCodeDialog.7
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (PDAuthCodeDialog.this.isDestroy) {
                    return;
                }
                JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                String optString = fastJsonObject.optString("subcode");
                if (TextUtils.equals(optString, "2")) {
                    PDAuthCodeDialog.this.getAuthCodeSession();
                    PDAuthCodeDialog.this.clearInput();
                    return;
                }
                if (TextUtils.equals(optString, "0")) {
                    PDDataUtil.saveAuthSucTokenParam(fastJsonObject.optString("pdtk"));
                    PDDataUtil.saveAuthCodePDBPParm("0");
                } else {
                    PDDataUtil.saveAuthSucTokenParam("");
                    PDDataUtil.saveAuthCodePDBPParm("1");
                }
                PDAuthCodeDialog.this.continueQuery();
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                PDAuthCodeDialog.this.handleCheckAuthCodeException();
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }
        });
        this.httpGroup.add(httpSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearInput() {
        IMyActivity iMyActivity = this.mContext;
        if (iMyActivity != null) {
            iMyActivity.post(new Runnable() { // from class: com.jd.lib.productdetail.core.views.PDAuthCodeDialog.4
                @Override // java.lang.Runnable
                public void run() {
                    if (PDAuthCodeDialog.this.authCodeDialog == null || PDAuthCodeDialog.this.authCodeDialog.editText == null) {
                        return;
                    }
                    PDAuthCodeDialog.this.authCodeDialog.editText.setText((CharSequence) null);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void continueQuery() {
        this.httpSettingInterrupted.setUrl(null);
        this.httpSettingInterrupted.putJsonParam("token", PDDataUtil.getAuthCodeTokenParam());
        this.httpSettingInterrupted.putJsonParam("pdbp", PDDataUtil.getAuthCodePDBPParm());
        this.httpGroup.add(this.httpSettingInterrupted);
        dismissDialog();
    }

    private void dismissDialog() {
        JDDialog jDDialog = this.authCodeDialog;
        if (jDDialog == null || !jDDialog.isShowing()) {
            return;
        }
        this.authCodeDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getAuthCode(String str) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("authCodeAuth");
        httpSetting.setHost(Configuration.getWareHost());
        httpSetting.setPost(false);
        httpSetting.putJsonParam("bsid", str);
        httpSetting.setType(5000);
        httpSetting.setPriority(5000);
        httpSetting.setCacheMode(2);
        httpSetting.setListener(new AnonymousClass6(str));
        this.httpGroup.add(httpSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCheckAuthCodeException() {
        PDDataUtil.saveAuthSucTokenParam("");
        PDDataUtil.saveAuthCodePDBPParm("1");
        continueQuery();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAuthCodeDialog(Bitmap bitmap, final AuthCodeDialogListener authCodeDialogListener) {
        if (this.authCodeDialog == null) {
            this.authCodeDialog = JDDialogFactory.getInstance().createJdDialogWithStyle3(this.mContext.getThisActivity(), this.mContext.getThisActivity().getResources().getString(R.string.lib_pd_core_authcode_title), this.mContext.getThisActivity().getResources().getString(R.string.lib_pd_core_authcode_input_desc), "", this.mContext.getThisActivity().getResources().getString(R.string.lib_pd_core_cancel), this.mContext.getThisActivity().getResources().getString(R.string.lib_pd_core_ok));
        }
        ImageView imageView = this.authCodeDialog.imageView;
        if (imageView != null) {
            imageView.setImageBitmap(bitmap);
            this.authCodeDialog.imageView.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.core.views.PDAuthCodeDialog.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    authCodeDialogListener.updateAuthCodeImg();
                }
            });
        }
        this.authCodeDialog.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.core.views.PDAuthCodeDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PDAuthCodeDialog.this.authCodeDialog.dismiss();
                authCodeDialogListener.cancleBtn();
            }
        });
        this.authCodeDialog.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.core.views.PDAuthCodeDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                authCodeDialogListener.okBtn(PDAuthCodeDialog.this.authCodeDialog.editText.getText().toString());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDialog() {
        JDDialog jDDialog = this.authCodeDialog;
        if (jDDialog == null || jDDialog.isShowing()) {
            return;
        }
        this.authCodeDialog.show();
    }

    public void getAuthCodeSession() {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("authCodeSession");
        httpSetting.putJsonParam("businessId", "pcp.m.jd.com");
        httpSetting.setHost(Configuration.getWareHost());
        httpSetting.setCacheMode(2);
        httpSetting.setEffect(1);
        httpSetting.setNotifyUser(true);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jd.lib.productdetail.core.views.PDAuthCodeDialog.5
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JDJSONObject optJSONObject;
                if (PDAuthCodeDialog.this.isDestroy) {
                    return;
                }
                try {
                    JDJSONArray optJSONArray = httpResponse.getFastJsonObject().optJSONArray("sec_comp");
                    if (optJSONArray == null || (optJSONObject = optJSONArray.optJSONObject(0)) == null) {
                        return;
                    }
                    String optString = optJSONObject.optString("bsid");
                    if (TextUtils.isEmpty(optString)) {
                        return;
                    }
                    PDAuthCodeDialog.this.getAuthCode(optString);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                PDAuthCodeDialog.this.handleCheckAuthCodeException();
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }
        });
        this.httpGroup.add(httpSetting);
    }

    public void onDestroy() {
        this.isDestroy = true;
        this.httpSettingInterrupted = null;
        this.httpGroup = null;
    }
}
