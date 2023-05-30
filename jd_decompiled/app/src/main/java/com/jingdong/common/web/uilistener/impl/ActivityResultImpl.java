package com.jingdong.common.web.uilistener.impl;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.entity.BarcodeRecord;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.utils.FileUtils;
import com.jingdong.common.utils.ReadContactsUtil;
import com.jingdong.common.utils.VoiceUtil;
import com.jingdong.common.utils.pay.CashierDeskMtaIDs;
import com.jingdong.common.utils.pay.JumpUtils;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.entity.JsBridgeEntity;
import com.jingdong.common.web.javainterface.impl.AndroidUploadImg;
import com.jingdong.common.web.javainterface.impl.MobileLogin;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.uibinder.impl.CommonWebUiBinder;
import com.jingdong.common.web.uilistener.IActivityResult;
import com.jingdong.common.web.util.MultiMedia;
import com.jingdong.common.web.util.WebUnifiedMtaUtil;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.corelib.utils.Log;
import com.tencent.tauth.Tencent;
import java.io.File;

/* loaded from: classes12.dex */
public class ActivityResultImpl extends BaseWebComponent implements IActivityResult {
    final String TAG;
    private JsBridgeEntity jsBridgeEntity;

    public ActivityResultImpl(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.TAG = ActivityResultImpl.class.getSimpleName();
        this.jsBridgeEntity = iWebUiBinder.getWebEntity().jsBridgeEntity;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.jingdong.common.web.uilistener.IActivityResult
    public void onActivityResult(int i2, int i3, Intent intent) {
        String str;
        if (Log.I) {
            Log.i(this.TAG, "onActivityResult");
        }
        IWebUiBinder iWebUiBinder = this.webUiBinder;
        CommonWebUiBinder commonWebUiBinder = iWebUiBinder instanceof CommonWebUiBinder ? (CommonWebUiBinder) iWebUiBinder : null;
        if (intent != null) {
            if (1009 == i2 && i3 == -1) {
                WebUtils.sendJSONStr2M(this.webUiBinder, WebUtils.CALLBACK_NAME, WebUtils.getPhoneNumber(iWebUiBinder.getBaseActivity(), intent.getData()));
            }
            if (1024 == i3 && 10 == i2) {
                String stringExtra = intent.getStringExtra("jdpay_Result");
                if (Log.I) {
                    Log.i(this.TAG, "jdPay.pay_result=" + stringExtra);
                }
                String parserJDPayResult = JumpUtils.parserJDPayResult(stringExtra);
                if ("JDP_PAY_SUCCESS".equals(parserJDPayResult)) {
                    if (commonWebUiBinder != null) {
                        commonWebUiBinder.onClickEvent(CashierDeskMtaIDs.JDCHECKOUT_JD_PAYRRESULT, this.webUiBinder.getWebEntity().payID + "_0");
                    }
                    WebUtils.forwardSuccessPage(this.webUiBinder);
                    return;
                } else if ("JDP_PAY_CANCEL".equals(parserJDPayResult)) {
                    if (commonWebUiBinder != null) {
                        commonWebUiBinder.onClickEvent(CashierDeskMtaIDs.JDCHECKOUT_JD_PAYRRESULT, this.webUiBinder.getWebEntity().payID + "_1");
                        return;
                    }
                    return;
                } else {
                    if (commonWebUiBinder != null) {
                        commonWebUiBinder.onClickEvent(CashierDeskMtaIDs.JDCHECKOUT_JD_PAYRRESULT, this.webUiBinder.getWebEntity().payID + "_2");
                    }
                    WebUtils.doPayFail(this.webUiBinder, "12");
                    return;
                }
            }
            if ((3000 == i3 || 1024 == i3) && 3000 == i2) {
                try {
                    String stringExtra2 = intent.getStringExtra("jdpay_Result");
                    if (Log.D) {
                        Log.d(this.TAG, "SMALL_FREE/BAI_TIAO/ZHONG_CHOU_JD_PAY_RESULT= " + stringExtra2);
                    }
                    if (this.webUiBinder.getJdWebView() != null && !TextUtils.isEmpty(this.jsBridgeEntity.jdPayAccountCallBack) && !TextUtils.isEmpty(stringExtra2)) {
                        this.webUiBinder.getJdWebView().injectJs("javascript:" + this.jsBridgeEntity.jdPayAccountCallBack + "('" + stringExtra2 + "')");
                    }
                } catch (Exception e2) {
                    if (Log.D) {
                        Log.d(this.TAG, "SMALL_FREE_JD_PAY_RESULT.Exception->->" + e2.getMessage());
                    }
                }
            }
            if (1024 == i3 && 4000 == i2) {
                try {
                    String stringExtra3 = intent.getStringExtra("jdpay_Result");
                    if (Log.D) {
                        Log.d(this.TAG, "JD_SCANCODE_PAY_RESULT= " + stringExtra3);
                    }
                    JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_APPHOME, this.webUiBinder.getBaseActivity(), null);
                } catch (Exception e3) {
                    if (Log.D) {
                        Log.d(this.TAG, "JD_SCANCODE_PAY_RESULT.Exception->->" + e3.getMessage());
                    }
                }
            }
            if (5000 == i2) {
                String stringExtra4 = intent.getStringExtra("jdpay_Result");
                JsBridgeEntity.JdPayParam jdPayParam = this.jsBridgeEntity.jdPayParam;
                if (jdPayParam != null) {
                    if ("openAPP".equals(jdPayParam.returnType)) {
                        try {
                            new Intent().setData(Uri.parse("jdpauth" + this.webUiBinder.getWebEntity().thirdApp_key + "://?parameterKey=" + stringExtra4));
                            this.webUiBinder.startActivity(intent);
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    } else if ("callJS".equals(this.jsBridgeEntity.jdPayParam.returnType)) {
                        if (!TextUtils.isEmpty(this.jsBridgeEntity.jdPayParam.callback)) {
                            this.webUiBinder.getJdWebView().injectJs("javascript:" + this.jsBridgeEntity.jdPayParam.callback + "('" + stringExtra4 + "')");
                        }
                    } else if ("closeWeb".equals(this.jsBridgeEntity.jdPayParam.returnType)) {
                        this.webUiBinder.finishUi();
                    }
                } else {
                    try {
                        this.webUiBinder.getWebEntity().thirdPayStatus = true;
                        if (Log.D) {
                            Log.d(this.TAG, "THIRD_PAY_RESULT= " + stringExtra4);
                        }
                        new Intent().setData(Uri.parse("jdpauth" + this.webUiBinder.getWebEntity().thirdApp_key + "://?parameterKey=" + stringExtra4));
                        try {
                            this.webUiBinder.startActivity(intent);
                        } catch (Exception e5) {
                            e5.printStackTrace();
                        }
                        this.webUiBinder.finishUi();
                    } catch (Exception e6) {
                        e6.printStackTrace();
                        if (Log.D) {
                            Log.d(this.TAG, "THIRD_PAY_RESULT.Exception->->" + e6.getMessage());
                        }
                    }
                }
            }
            Bundle extras = intent.getExtras();
            if (extras != null) {
                try {
                    str = extras.getString("pay_result");
                } catch (Throwable th) {
                    if (Log.E) {
                        Log.e(this.TAG, th.getMessage(), th);
                    }
                    str = null;
                }
                if (Log.I) {
                    Log.i(this.TAG, "unpay.pay_result=" + str);
                }
                if ("success".equalsIgnoreCase(str)) {
                    WebUtils.forwardSuccessPage(this.webUiBinder);
                    return;
                } else if ("fail".equalsIgnoreCase(str)) {
                    WebUtils.doPayFail(this.webUiBinder, "4");
                    return;
                } else if ("cancel".equalsIgnoreCase(str)) {
                    return;
                }
            }
        }
        if (i2 == 1234) {
            if (-1 == i3) {
                VoiceUtil.handleVoiceResult(this.webUiBinder.getBaseActivity(), intent, this.webUiBinder.getJdWebView().getWebView());
            }
        } else if (i2 == 1235) {
            if (-1 == i3) {
                if (Log.D) {
                    Log.d(this.TAG, " handleBarcodeResult -->> ");
                }
                if (intent == null || this.webUiBinder.getJdWebView().getWebView() == null) {
                    return;
                }
                BarcodeRecord barcodeRecord = new BarcodeRecord();
                barcodeRecord.content = intent.getStringExtra("SCAN_RESULT");
                barcodeRecord.format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                if (Log.D) {
                    Log.d(this.TAG, " -->>getContent: " + barcodeRecord.content);
                }
                String str2 = "javascript:scanCallBack('" + barcodeRecord.content + "')";
                if (this.webUiBinder.getJdWebView() != null) {
                    this.webUiBinder.getJdWebView().injectJs(str2);
                }
            }
        } else {
            switch (i2) {
                case 10:
                    this.webUiBinder.getJdWebView().selectFileBack(intent, i3, -1, false);
                    return;
                case 19:
                    break;
                case 291:
                    this.webUiBinder.getJdWebView().selectImageBack(intent, i3, -1, false);
                    return;
                case 293:
                    this.webUiBinder.getJdWebView().selectImageBack(intent, i3, -1, true);
                    return;
                case 295:
                    this.webUiBinder.getJdWebView().selectVideoBack(intent, i3, -1, false);
                    return;
                case 297:
                    this.webUiBinder.getJdWebView().selectVideoBack(intent, i3, -1, true);
                    return;
                case 1100:
                    if (-1 == i3) {
                        ReadContactsUtil.handleContacts(this.webUiBinder.getBaseActivity(), intent, this.webUiBinder.getJdWebView().getWebView());
                        return;
                    }
                    return;
                case 4001:
                    if (intent != null) {
                        try {
                            String stringExtra5 = intent.getStringExtra("jdpay_Result");
                            if (Log.D) {
                                Log.d(this.TAG, "JD_SCANCODE_NEWPATHWAYS_PAY_RESULT= " + stringExtra5);
                            }
                            if (this.webUiBinder.getJdWebView() == null || TextUtils.isEmpty(this.jsBridgeEntity.jdPayScanCodeCallBack) || TextUtils.isEmpty(stringExtra5)) {
                                return;
                            }
                            this.webUiBinder.getJdWebView().injectJs("javascript:" + this.jsBridgeEntity.jdPayScanCodeCallBack + "('" + stringExtra5 + "')");
                            return;
                        } catch (Exception e7) {
                            if (Log.D) {
                                Log.d(this.TAG, "JD_SCANCODE_NEWPATHWAYS_PAY_RESULT.Exception->->" + e7.getMessage());
                                return;
                            }
                            return;
                        }
                    }
                    return;
                case 6667:
                    if (-1 == i3) {
                        String stringExtra6 = intent.getStringExtra("content");
                        if (TextUtils.isEmpty(stringExtra6)) {
                            stringExtra6 = "scan_failure";
                        }
                        if (this.webUiBinder.getJdWebView() != null) {
                            this.webUiBinder.getJdWebView().injectJs("javascript:" + this.jsBridgeEntity.jsCallback + "('" + stringExtra6 + "')");
                            return;
                        }
                        return;
                    }
                    return;
                case 10102:
                case 11101:
                    MobileLogin mobileLogin = (MobileLogin) this.webUiBinder.getJavaInterfaceObj(WebUiConstans.JavaInterfaceNames.MOBILE_LOGIN);
                    if (mobileLogin != null) {
                        Tencent.onActivityResultData(i2, i3, intent, mobileLogin.getQQListener());
                        return;
                    }
                    return;
                case 12316:
                    if (-1 == i3) {
                        String stringExtra7 = intent.getStringExtra("recoderResult");
                        String str3 = "javascript:audioUpload('" + FileUtils.encodeBase64File(stringExtra7) + "')";
                        if (Log.D) {
                            Log.d("recoderResult", str3);
                        }
                        this.webUiBinder.getJdWebView().injectJs(str3);
                        File file = new File(stringExtra7);
                        if (file.exists() && file.isFile()) {
                            file.delete();
                            return;
                        }
                        return;
                    }
                    return;
                default:
                    switch (i2) {
                        case 12:
                            this.webUiBinder.getJdWebView().selectFileBack(intent, i3, -1, true);
                            return;
                        case 13:
                            this.webUiBinder.getJdWebView().recordMediaBack(intent, false, i3, -1);
                            return;
                        case 14:
                        case 15:
                            AndroidUploadImg.handleActivityResultAfterImageSelect(this.webUiBinder, this.jsBridgeEntity, i2, i3, intent);
                            WebUnifiedMtaUtil.albumReport(this.webUiBinder, "ActivityResultImpl-onActivityResult", String.valueOf(i2));
                            return;
                        case 16:
                            break;
                        case 17:
                            this.webUiBinder.getJdWebView().recordMediaBack(intent, true, i3, -1);
                            return;
                        default:
                            return;
                    }
            }
            MultiMedia.processResultIntent(this.webUiBinder, intent, i2);
            WebUnifiedMtaUtil.albumReport(this.webUiBinder, "ActivityResultImpl-multiMedia", String.valueOf(i2));
        }
    }
}
