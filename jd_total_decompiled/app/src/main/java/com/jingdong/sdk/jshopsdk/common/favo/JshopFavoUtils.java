package com.jingdong.sdk.jshopsdk.common.favo;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.jshopsdk.R;
import com.jingdong.sdk.oklog.OKLog;
import de.greenrobot.event.EventBus;

/* loaded from: classes7.dex */
public class JshopFavoUtils {
    public static final String FOLLOW_GIFT_HAVE = "FOLLD";
    public static final String FOLLOW_GIFT_NO = "NFOLL";
    public static final int FOLLOW_HAVE = 1001;
    public static final int FOLLOW_SUCCESS = 1000;
    public static final String HAS_CONCERNED = "F0402";
    public static final String MAX_VAL = "F0410";
    public static final String OPT_ERR = "F0409";
    public static final String OPT_SUCCESS = "F10000";
    public static final String PARAM_ERR = "F10001";
    private static String TAG = "JshopFavoUtils";
    public static final String TIPS = JdSdk.getInstance().getApplicationContext().getString(R.string.jshop_followed);
    public static final String UNLOGIN = "F10002";
    private JShopFavToast favToast;
    private boolean isShowToast;
    private IMyActivity mActivity;
    public int mFollowCode;
    public String mFollowGiftOptCode;
    private boolean showAnimationToast;
    private JShopUnfavToast unfavToast;

    public JshopFavoUtils(IMyActivity iMyActivity) {
        this.mFollowCode = -1;
        this.mFollowGiftOptCode = "";
        this.isShowToast = true;
        this.showAnimationToast = false;
        this.mActivity = iMyActivity;
    }

    public JshopFavoUtils(IMyActivity iMyActivity, boolean z) {
        this.mFollowCode = -1;
        this.mFollowGiftOptCode = "";
        this.isShowToast = true;
        this.showAnimationToast = false;
        this.mActivity = iMyActivity;
        this.showAnimationToast = z;
        if (z) {
            Activity activity = (Activity) iMyActivity;
            this.favToast = new JShopFavToast(activity);
            this.unfavToast = new JShopUnfavToast(activity);
        }
    }

    public JshopFavoUtils(IMyActivity iMyActivity, boolean z, boolean z2) {
        this.mFollowCode = -1;
        this.mFollowGiftOptCode = "";
        this.isShowToast = true;
        this.showAnimationToast = false;
        this.mActivity = iMyActivity;
        this.showAnimationToast = z;
        this.isShowToast = z2;
        if (z) {
            Activity activity = (Activity) iMyActivity;
            this.favToast = new JShopFavToast(activity);
            this.unfavToast = new JShopUnfavToast(activity);
        }
    }

    public static void addProductFavorite(String str, long j2, String str2, final IMyActivity iMyActivity) {
        if (j2 < 0) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("addFavorite");
        httpSetting.setHost(str);
        httpSetting.putJsonParam("wareId", "" + j2);
        httpSetting.putJsonParam("isNewText", Boolean.TRUE);
        httpSetting.putJsonParam("pin", str2);
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.sdk.jshopsdk.common.favo.JshopFavoUtils.2
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                Boolean booleanOrNull = httpResponse.getJSONObject().getBooleanOrNull("flag");
                if (booleanOrNull == null || !booleanOrNull.booleanValue()) {
                    return;
                }
                ToastUtils.shortToast((Activity) IMyActivity.this, JdSdk.getInstance().getApplicationContext().getString(R.string.jshop_product_follow_success));
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        httpSetting.setNotifyUser(true);
        iMyActivity.getHttpGroupaAsynPool().add(httpSetting);
    }

    private void getFavoStatus(String str, final View view, final boolean z, final boolean z2, final String str2, boolean z3, final JshopFavoListener jshopFavoListener, Object obj) {
        Object valueOf;
        if (view != null) {
            view.setEnabled(false);
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(str);
        httpSetting.setFunctionId("followShop");
        httpSetting.putJsonParam("shopId", str2);
        String str3 = "follow";
        if (z2) {
            httpSetting.putJsonParam("follow", Boolean.TRUE);
            str3 = "award";
            valueOf = DYConstants.DY_TRUE;
        } else {
            valueOf = Boolean.valueOf(z);
        }
        httpSetting.putJsonParam(str3, valueOf);
        httpSetting.setNotifyUser(false);
        httpSetting.setEffect(z3 ? 1 : 0);
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.sdk.jshopsdk.common.favo.JshopFavoUtils.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                IMyActivity iMyActivity;
                Runnable runnable;
                JshopFavoUtils jshopFavoUtils;
                int i2;
                OKLog.d(JshopFavoUtils.TAG, "=======onEnd=============");
                JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                if (z2) {
                    return;
                }
                JshopFavoUtils.this.mFollowCode = -1;
                String optString = jSONObject.optString("optCode");
                String optString2 = jSONObject.optString("msg");
                String optString3 = jSONObject.optString("subMsg");
                final String string = TextUtils.isEmpty(optString2) ? JdSdk.getInstance().getApplicationContext().getString(R.string.jshop_request_exception) : optString2;
                if ("F10000".equals(optString) || JshopFavoUtils.TIPS.equals(optString2) || "F0402".equals(optString)) {
                    if (z) {
                        if (JshopFavoUtils.TIPS.equals(optString2) || "F0402".equals(optString)) {
                            jshopFavoUtils = JshopFavoUtils.this;
                            i2 = 1001;
                        } else {
                            jshopFavoUtils = JshopFavoUtils.this;
                            i2 = 1000;
                        }
                        jshopFavoUtils.mFollowCode = i2;
                    }
                    JshopFavoListener jshopFavoListener2 = jshopFavoListener;
                    if (jshopFavoListener2 != null) {
                        jshopFavoListener2.onFavoStatus(z);
                    }
                    EventBus.getDefault().post(new JShopFavStatusEvent(str2, z));
                    if (JshopFavoUtils.this.showAnimationToast && JshopFavoUtils.this.isShowToast) {
                        if (z) {
                            JshopFavoUtils.this.favToast.show(string, optString3);
                        } else {
                            JshopFavoUtils.this.unfavToast.show(string);
                        }
                    }
                    iMyActivity = JshopFavoUtils.this.mActivity;
                    runnable = new Runnable() { // from class: com.jingdong.sdk.jshopsdk.common.favo.JshopFavoUtils.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            JshopFavoUtils.this.showFollowToast(string, true);
                        }
                    };
                } else if ("F10001".equals(optString)) {
                    JshopFavoListener jshopFavoListener3 = jshopFavoListener;
                    if (jshopFavoListener3 != null) {
                        jshopFavoListener3.onError();
                    }
                    iMyActivity = JshopFavoUtils.this.mActivity;
                    runnable = new Runnable() { // from class: com.jingdong.sdk.jshopsdk.common.favo.JshopFavoUtils.1.3
                        @Override // java.lang.Runnable
                        public void run() {
                            JshopFavoUtils.this.showFollowToast(string, false);
                        }
                    };
                } else if ("F0410".equals(optString)) {
                    JshopFavoListener jshopFavoListener4 = jshopFavoListener;
                    if (jshopFavoListener4 != null) {
                        jshopFavoListener4.onError();
                    }
                    iMyActivity = JshopFavoUtils.this.mActivity;
                    runnable = new Runnable() { // from class: com.jingdong.sdk.jshopsdk.common.favo.JshopFavoUtils.1.4
                        @Override // java.lang.Runnable
                        public void run() {
                            JshopFavoUtils.this.showFollowToast(string, false);
                        }
                    };
                } else if ("F0409".equals(optString)) {
                    JshopFavoListener jshopFavoListener5 = jshopFavoListener;
                    if (jshopFavoListener5 != null) {
                        jshopFavoListener5.onError();
                    }
                    iMyActivity = JshopFavoUtils.this.mActivity;
                    runnable = new Runnable() { // from class: com.jingdong.sdk.jshopsdk.common.favo.JshopFavoUtils.1.5
                        @Override // java.lang.Runnable
                        public void run() {
                            JshopFavoUtils.this.showFollowToast(string, false);
                        }
                    };
                } else {
                    iMyActivity = JshopFavoUtils.this.mActivity;
                    runnable = new Runnable() { // from class: com.jingdong.sdk.jshopsdk.common.favo.JshopFavoUtils.1.6
                        @Override // java.lang.Runnable
                        public void run() {
                            JshopFavoUtils.this.showFollowToast(string, false);
                        }
                    };
                }
                iMyActivity.post(runnable);
                JshopFavoUtils.this.mActivity.post(new Runnable() { // from class: com.jingdong.sdk.jshopsdk.common.favo.JshopFavoUtils.1.7
                    @Override // java.lang.Runnable
                    public void run() {
                        View view2 = view;
                        if (view2 != null) {
                            view2.setEnabled(true);
                        }
                    }
                }, 500);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(final HttpError httpError) {
                OKLog.d(JshopFavoUtils.TAG, "======onerror=====");
                JshopFavoUtils.this.mActivity.post(new Runnable() { // from class: com.jingdong.sdk.jshopsdk.common.favo.JshopFavoUtils.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        String string;
                        View view2 = view;
                        if (view2 != null) {
                            view2.setEnabled(true);
                        }
                        if (z2) {
                            HttpError httpError2 = httpError;
                            string = (httpError2 == null || httpError2.getHttpResponse() == null || httpError.getHttpResponse().getJSONObject() == null) ? "" : httpError.getHttpResponse().getJSONObject().optString("msg");
                            if (TextUtils.isEmpty(string)) {
                                string = ((Activity) JshopFavoUtils.this.mActivity).getString(R.string.jshop_follow_gift_fail);
                            }
                        } else {
                            string = JdSdk.getInstance().getApplicationContext().getString(R.string.jshop_request_exception);
                        }
                        JshopFavoUtils.this.showFollowToast(string, false);
                    }
                });
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
                OKLog.d(JshopFavoUtils.TAG, "onStart");
            }
        });
        this.mActivity.getHttpGroupaAsynPool().add(httpSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFollowToast(String str, boolean z) {
        if (this.isShowToast) {
            if (!z) {
                ToastUtils.showToastInCenter((Context) ((Activity) this.mActivity), (byte) 1, str, 1);
            } else if (this.showAnimationToast) {
            } else {
                ToastUtils.showToastInCenter((Context) ((Activity) this.mActivity), (byte) 2, str, 0);
            }
        }
    }

    @Deprecated
    public void getFavoStatus(String str, View view, String str2, JshopFavoListener jshopFavoListener, Object obj) {
        OKLog.d(TAG, "getFavoStatus");
        getFavoStatus(str, view, true, true, str2, false, jshopFavoListener, obj);
    }

    public void getFavoStatus(String str, View view, String str2, boolean z, boolean z2, JshopFavoListener jshopFavoListener, Object obj) {
        OKLog.d(TAG, "getFavoStatus");
        getFavoStatus(str, view, z, z2, str2, false, jshopFavoListener, obj);
    }

    public void getFavoStatus(String str, View view, String str2, boolean z, boolean z2, boolean z3, JshopFavoListener jshopFavoListener, Object obj) {
        OKLog.d(TAG, "getFavoStatus");
        getFavoStatus(str, view, z, z2, str2, z3, jshopFavoListener, obj);
    }

    public void getFavoStatus(String str, View view, boolean z, String str2, JshopFavoListener jshopFavoListener) {
        OKLog.d(TAG, "getFavoStatus");
        getFavoStatus(str, view, z, str2, false, jshopFavoListener);
    }

    public void getFavoStatus(String str, View view, boolean z, String str2, boolean z2, JshopFavoListener jshopFavoListener) {
        getFavoStatus(str, view, z, false, str2, z2, jshopFavoListener, (Object) null);
    }

    public void setShowAnimationToast(boolean z) {
        this.showAnimationToast = z;
    }

    public void setShowToast(boolean z) {
        this.isShowToast = z;
    }
}
