package com.jingdong.common.shop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkJShopHomeHelper;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.ui.JDCheckDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.jingdong.union.common.config.UnionConstants;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class JshopTakeCouponUtils {
    private static final boolean DBG = Log.E;
    public static final String REFER_FOLLOW_DECORATION = "decoration";
    public static final String REFER_FOLLOW_SHOPS = "followShops";
    public static final String REFER_SHOP_HOME = "shophome";
    public static final String SOURCE_RPC_ACTIVITY_COUPON = "shop_app_activity_coupon";
    public static final String SOURCE_RPC_COUPON_LIST = "shop_app_coupon_list";
    public static final String SOURCE_RPC_FOLLOW_COUPON = "shop_app_follow_coupon";
    public static final String SOURCE_RPC_HOME_COUPON = "shop_app_home_coupon";
    public static final String SOURCE_RPC_HOME_DECORATION = "shop_app_home_decoration";
    public static final String SOURCE_RPC_PROMOTION_COUPON = "shop_app_promotion_coupon";
    private static final String TAG = "JshopTakeCouponUtils";
    public static final HashMap<Integer, String> errorMessages;
    private static JshopTakeCouponUtils takeCouponUtils;
    private String couponId;
    private JshopCoupon mCoupon;
    private int myResultRequestCode;
    private String venderId;
    private String act = "";
    private String mShopId = "";

    static {
        HashMap<Integer, String> hashMap = new HashMap<>();
        errorMessages = hashMap;
        hashMap.put(3, "\u4e3a\u4e86\u4fdd\u969c\u60a8\u7684\u8d26\u6237\u5b89\u5168\uff0c \u8bf7\u524d\u5f80'\u5728\u8d26\u6237\u5b89\u5168'\u5f00\u542f\u652f\u4ed8\u5bc6\u7801\u518d\u9886\u5238\uff01");
        hashMap.put(5, "\u8c8c\u4f3c\u9886\u4e0d\u4e86\u54df\uff0c\u770b\u4e00\u4e0b\u5176\u4ed6\u6d3b\u52a8\u5427~");
        hashMap.put(6, "\u6ca1\u6709\u627e\u5230\u8be5\u94fe\u63a5\u7684\u6d3b\u52a8\uff0c\u8bf7\u60a8\u770b\u4e00\u4e0b\u5176\u4ed6\u6d3b\u52a8\u5427~");
        hashMap.put(7, "\u6ca1\u6709\u627e\u5230\u8be5\u94fe\u63a5\u7684\u6d3b\u52a8\uff0c\u8bf7\u770b\u4e00\u4e0b\u5176\u4ed6\u6d3b\u52a8\u5427~");
        hashMap.put(8, "\u60a8\u6765\u592a\u665a\u4e86\uff0c\u6d3b\u52a8\u5df2\u7ecf\u7ed3\u675f\u4e86\u54df~");
        hashMap.put(9, "\u60a8\u6765\u65e9\u4e86\uff0c\u6d3b\u52a8\u8fd8\u6ca1\u5f00\u59cb\u54df\uff0c\u8bf7\u7a0d\u540e\u518d\u6765~");
        hashMap.put(10, "\u60a8\u6765\u65e9\u4e86\uff0c\u4eca\u5929\u7684\u6d3b\u52a8\u8fd8\u672a\u5f00\u59cb\u54df\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5~");
        hashMap.put(11, "\u60a8\u6765\u592a\u665a\u4e86\uff0c\u4eca\u5929\u6d3b\u52a8\u5df2\u7ecf\u7ed3\u675f\u4e86\u54df\uff0c\u8c22\u8c22\u60a8\u7684\u5173\u6ce8~");
        hashMap.put(12, "\u60a8\u7684\u8d26\u6237\u7ea7\u522b\u7a0d\u5fae\u6709\u70b9\u4f4e\uff0c\u9700\u518d\u63a5\u518d\u5389\u54df~");
        hashMap.put(13, "\u8c8c\u4f3c\u6709\u70b9\u5c0f\u95ee\u9898\uff0c\u8bf7\u60a830\u79d2\u540e\u518d\u6b21\u5c1d\u8bd5~");
        hashMap.put(14, "\u60a8\u5df2\u7ecf\u53c2\u52a0\u8fc7\u6b64\u6d3b\u52a8\uff0c\u522b\u592a\u8d2a\u5fc3\u54df\uff0c\u4e0b\u6b21\u518d\u6765~");
        hashMap.put(15, "\u60a8\u4eca\u5929\u5df2\u7ecf\u53c2\u52a0\u8fc7\u6b64\u6d3b\u52a8\uff0c\u522b\u592a\u8d2a\u5fc3\u54df\uff0c\u660e\u5929\u518d\u6765~");
        hashMap.put(16, "\u6b64\u5238\u4eca\u65e5\u5df2\u7ecf\u88ab\u9886\u5b8c\uff0c\u8bf7\u60a8\u660e\u65e5\u518d\u6765~");
        hashMap.put(17, "\u6b64\u5238\u5df2\u7ecf\u88ab\u9886\u5b8c\u4e86\uff0c\u4e0b\u6b21\u8bb0\u5f97\u65e9\u70b9\u6765\u54df~");
        hashMap.put(18, "\u60a8\u63d0\u4ea4\u8fc7\u4e8e\u9891\u7e41\uff0c\u8bf730\u79d2\u540e\u518d\u8bd5\u3002");
        hashMap.put(19, "\u8c8c\u4f3c\u6709\u70b9\u5c0f\u95ee\u9898\uff0c\u60a8\u53ef\u4ee530\u79d2\u540e\u518d\u8bd5\u4e00\u4e0b\u54df~");
        hashMap.put(21, "\u60a8\u63d0\u4ea4\u7684\u8fc7\u4e8e\u9891\u7e41\uff0c\u8bf750\u79d2\u7a0d\u540e\u91cd\u8bd5\u3002");
        hashMap.put(22, "\u5feb\u62a2\u4f18\u60e0\u5238\u6709\u4e00\u5b9a\u7684\u968f\u673a\u6bd4\u4f8b\uff0c\u53ef\u80fd\u5b58\u5728\u62a2\u4e0d\u5230\u7684\u60c5\u51b5\u54df\uff01");
        hashMap.put(23, "\u652f\u4ed8\u5bc6\u7801\u73b0\u5728\u8c8c\u4f3c\u6709\u70b9\u5c0f\u95ee\u9898\uff0c\u60a8\u53ef\u4ee530\u79d2\u540e\u518d\u8bd5\u4e00\u4e0b\u54df~");
        hashMap.put(24, "\u8c8c\u4f3c\u6709\u70b9\u5c0f\u95ee\u9898\u54df\uff0c\u8bf7\u60a830\u79d2\u540e\u518d\u6b21\u5c1d\u8bd5\u5427~");
        hashMap.put(25, "\u8c8c\u4f3c\u6709\u70b9\u5c0f\u95ee\u9898\u5440\uff0c\u8bf7\u60a830\u79d2\u540e\u518d\u6b21\u5c1d\u8bd5\u5427~");
    }

    public static JshopTakeCouponUtils getInstance() {
        if (takeCouponUtils == null) {
            takeCouponUtils = new JshopTakeCouponUtils();
        }
        return takeCouponUtils;
    }

    public static void goToMWithUrl(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("url", str);
        DeepLinkCommonHelper.startWebActivity(context, bundle, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlerCouponToast(final IMyActivity iMyActivity, JDJSONObject jDJSONObject, String str, byte b) {
        String optString = jDJSONObject.optString("processStatus");
        final String optString2 = jDJSONObject.optString("url");
        boolean switchBooleanValue = SwitchQueryFetcher.getSwitchBooleanValue("jshop_disable_couponCertification", false);
        boolean z = TextUtils.equals("54", optString) && !TextUtils.isEmpty(optString2);
        boolean z2 = !switchBooleanValue && ((TextUtils.equals("38", optString) || TextUtils.equals("124", optString) || TextUtils.equals("104", optString)) && !TextUtils.isEmpty(optString2));
        if (!z && !z2) {
            ToastUtils.showToastInCenter(iMyActivity.getThisActivity().getApplicationContext(), b, str, 0);
        } else if (TextUtils.isEmpty(str)) {
            goToMWithUrl((Context) iMyActivity, optString2);
        } else {
            final JDCheckDialog createJdDialogWithStyle6 = JDDialogFactory.getInstance().createJdDialogWithStyle6((Context) iMyActivity, "", str, z ? "\u53d6\u6d88" : "\u6682\u4e0d\u8ba4\u8bc1", z ? "\u53bb\u5b9e\u540d\u8ba4\u8bc1" : "\u7acb\u5373\u524d\u5f80\u8ba4\u8bc1");
            createJdDialogWithStyle6.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.shop.JshopTakeCouponUtils.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    JshopTakeCouponUtils.goToMWithUrl((Context) iMyActivity, optString2);
                    createJdDialogWithStyle6.cancel();
                }
            });
            createJdDialogWithStyle6.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startTakeCouponActivity(final IMyActivity iMyActivity, final Intent intent, final int i2) {
        iMyActivity.post(new Runnable() { // from class: com.jingdong.common.shop.JshopTakeCouponUtils.2
            @Override // java.lang.Runnable
            public void run() {
                if (i2 > 0) {
                    DeepLinkJShopHomeHelper.gotoJShopTakeCouponForResult(iMyActivity.getThisActivity(), intent.getExtras(), i2);
                } else {
                    DeepLinkJShopHomeHelper.gotoJShopTakeCoupon(iMyActivity.getThisActivity(), intent.getExtras());
                }
            }
        });
    }

    private void takeCouponHttpReq(IMyActivity iMyActivity, Runnable runnable, Runnable runnable2, Intent intent, String str, String str2, String str3) {
        takeCouponHttpReq(iMyActivity, null, runnable, runnable2, intent, true, str, str2, str3);
    }

    private void takeCrmCouponHttpReq(IMyActivity iMyActivity, Runnable runnable, Runnable runnable2, Intent intent, String str, String str2) {
        takeCrmCouponHttpReq(iMyActivity, null, runnable, runnable2, intent, true, str, str2);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:3|4|(3:5|6|(1:8))|9|10|11|(1:13)|14|15) */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0043, code lost:
        r8 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0044, code lost:
        com.jingdong.corelib.utils.Log.e(com.jingdong.common.shop.JshopTakeCouponUtils.TAG, "\u83b7\u53d6\u6307\u7eb9\u4fe1\u606f-shshshfpb\u5931\u8d25\uff1a" + r8.getMessage());
        r8.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0079  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void addRMParams(android.content.Context r8, com.jingdong.jdsdk.network.toolbox.HttpSetting r9, java.lang.String r10) {
        /*
            r7 = this;
            java.lang.String r0 = "JshopTakeCouponUtils"
            java.lang.String r1 = ""
            java.lang.String r2 = "-1"
            if (r9 == 0) goto L7f
            com.jd.sec.LogoManager r3 = com.jd.sec.LogoManager.getInstance(r8)     // Catch: java.lang.Exception -> L21
            r3.init()     // Catch: java.lang.Exception -> L21
            com.jd.sec.LogoManager r3 = com.jd.sec.LogoManager.getInstance(r8)     // Catch: java.lang.Exception -> L21
            java.lang.String r3 = r3.getLogo()     // Catch: java.lang.Exception -> L21
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Exception -> L1f
            if (r4 == 0) goto L3e
            r3 = r2
            goto L3e
        L1f:
            r4 = move-exception
            goto L23
        L21:
            r4 = move-exception
            r3 = r1
        L23:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "\u83b7\u53d6\u6307\u7eb9\u4fe1\u606f-EID\u5931\u8d25\uff1a"
            r5.append(r6)
            java.lang.String r6 = r4.getMessage()
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            com.jingdong.corelib.utils.Log.e(r0, r5)
            r4.printStackTrace()
        L3e:
            java.lang.String r1 = com.jd.stat.security.jma.JMA.getSoftFingerprint(r8)     // Catch: java.lang.Exception -> L43
            goto L5f
        L43:
            r8 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "\u83b7\u53d6\u6307\u7eb9\u4fe1\u606f-shshshfpb\u5931\u8d25\uff1a"
            r4.append(r5)
            java.lang.String r5 = r8.getMessage()
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            com.jingdong.corelib.utils.Log.e(r0, r4)
            r8.printStackTrace()
        L5f:
            java.lang.String r8 = "eid"
            r9.putJsonParam(r8, r3)
            java.lang.String r8 = "shshshfp"
            r9.putJsonParam(r8, r2)
            java.lang.String r8 = "shshshfpb"
            r9.putJsonParam(r8, r1)
            java.lang.String r8 = "shshshfpa"
            r9.putJsonParam(r8, r2)
            boolean r8 = android.text.TextUtils.isEmpty(r10)
            if (r8 == 0) goto L7a
            r10 = r2
        L7a:
            java.lang.String r8 = "pageClickkey"
            r9.putJsonParam(r8, r10)
        L7f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.shop.JshopTakeCouponUtils.addRMParams(android.content.Context, com.jingdong.jdsdk.network.toolbox.HttpSetting, java.lang.String):void");
    }

    public void executeReceiveCoupon(final IMyActivity iMyActivity, final JshopCoupon jshopCoupon, final Runnable runnable, final Runnable runnable2, String str, final Intent intent, int i2, final boolean z, final String str2, final String str3, final String str4) {
        this.couponId = intent.getStringExtra("couponId");
        this.act = intent.getStringExtra("act");
        this.mShopId = intent.getStringExtra("shopId");
        this.mCoupon = jshopCoupon;
        this.venderId = intent.getStringExtra("venderId");
        this.myResultRequestCode = i2;
        if (Thread.currentThread() == BaseApplication.getUiThread()) {
            if (intent.getBooleanExtra("crmCoupon", false)) {
                takeCrmCouponHttpReq(iMyActivity, jshopCoupon, runnable, runnable2, intent, z, str3, str4);
                return;
            } else {
                takeCouponHttpReq(iMyActivity, jshopCoupon, runnable, runnable2, intent, z, str2, str3, str4);
                return;
            }
        }
        iMyActivity.post(new Runnable() { // from class: com.jingdong.common.shop.JshopTakeCouponUtils.1
            @Override // java.lang.Runnable
            public void run() {
                if (intent.getBooleanExtra("crmCoupon", false)) {
                    JshopTakeCouponUtils.this.takeCrmCouponHttpReq(iMyActivity, jshopCoupon, runnable, runnable2, intent, z, str3, str4);
                } else {
                    JshopTakeCouponUtils.this.takeCouponHttpReq(iMyActivity, jshopCoupon, runnable, runnable2, intent, z, str2, str3, str4);
                }
            }
        });
    }

    public void executeTakeCouponRunnable(IMyActivity iMyActivity, JshopCoupon jshopCoupon, Runnable runnable, String str, Intent intent, int i2, String str2, String str3, String str4) {
        executeReceiveCoupon(iMyActivity, jshopCoupon, runnable, null, str, intent, i2, true, str2, str3, str4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void takeCouponHttpReq(final IMyActivity iMyActivity, final JshopCoupon jshopCoupon, final Runnable runnable, final Runnable runnable2, final Intent intent, final boolean z, String str, String str2, String str3) {
        boolean z2;
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setNotifyUser(true);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setHost(Configuration.getJshopHost());
        if (!TextUtils.isEmpty(this.couponId)) {
            httpSetting.setFunctionId("receiveShopCoupon");
            httpSetting.putJsonParam("couponId", this.couponId);
            httpSetting.putJsonParam("act", this.act);
            httpSetting.putJsonParam("source", "app-shop");
            if (!TextUtils.isEmpty(str2)) {
                httpSetting.putJsonParam("sourceRpc", str2);
            }
            if (!TextUtils.isEmpty(str3)) {
                httpSetting.putJsonParam(UnionConstants.BUNDLE_REFER, str3);
            }
            httpSetting.putJsonParam("operation", "3");
            if (jshopCoupon != null && (z2 = jshopCoupon.financeCoupon)) {
                httpSetting.putJsonParam("financeCoupon", Boolean.valueOf(z2));
                httpSetting.putJsonParam("venderId", this.venderId);
            }
            addRMParams(iMyActivity.getThisActivity().getBaseContext(), httpSetting, str);
        }
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.shop.JshopTakeCouponUtils.4
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                final JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                if (Log.D) {
                    Log.d("TAG", " -->> json:" + fastJsonObject);
                }
                iMyActivity.post(new Runnable() { // from class: com.jingdong.common.shop.JshopTakeCouponUtils.4.2
                    /* JADX WARN: Removed duplicated region for block: B:53:0x03a5  */
                    /* JADX WARN: Removed duplicated region for block: B:56:0x03ae  */
                    /* JADX WARN: Removed duplicated region for block: B:67:? A[RETURN, SYNTHETIC] */
                    @Override // java.lang.Runnable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public void run() {
                        /*
                            Method dump skipped, instructions count: 978
                            To view this dump change 'Code comments level' option to 'DEBUG'
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.shop.JshopTakeCouponUtils.AnonymousClass4.AnonymousClass2.run():void");
                    }
                });
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(final HttpError httpError) {
                iMyActivity.post(new Runnable() { // from class: com.jingdong.common.shop.JshopTakeCouponUtils.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        String message = httpError.getMessage();
                        if (message != null) {
                            ToastUtils.showToastInCenter(iMyActivity.getThisActivity().getApplicationContext(), (byte) 1, message, 0);
                        }
                        Runnable runnable3 = runnable2;
                        if (runnable3 != null) {
                            runnable3.run();
                        }
                    }
                });
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        if (jshopCoupon != null && jshopCoupon.financeCoupon && jshopCoupon.takeType == 2) {
            goToMWithUrl((Context) iMyActivity, jshopCoupon.takeUrl);
        } else {
            iMyActivity.getHttpGroupaAsynPool().add(httpSetting);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void takeCrmCouponHttpReq(final IMyActivity iMyActivity, final JshopCoupon jshopCoupon, final Runnable runnable, final Runnable runnable2, final Intent intent, final boolean z, String str, String str2) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setNotifyUser(true);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setHost(Configuration.getJshopHost());
        httpSetting.setFunctionId("receiveCrmCoupon");
        httpSetting.putJsonParam("crmCouponId", intent.getStringExtra("crmCouponId"));
        httpSetting.putJsonParam("source", "app-shop");
        if (!TextUtils.isEmpty(str)) {
            httpSetting.putJsonParam("sourceRpc", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            httpSetting.putJsonParam(UnionConstants.BUNDLE_REFER, str2);
        }
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.shop.JshopTakeCouponUtils.3
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                final JDJSONObject optJSONObject = httpResponse.getFastJsonObject().optJSONObject("result");
                if (Log.D) {
                    Log.d("TAG", " -->> json:" + optJSONObject);
                }
                iMyActivity.post(new Runnable() { // from class: com.jingdong.common.shop.JshopTakeCouponUtils.3.2
                    /* JADX WARN: Removed duplicated region for block: B:43:0x031f  */
                    /* JADX WARN: Removed duplicated region for block: B:46:0x0328  */
                    /* JADX WARN: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
                    @Override // java.lang.Runnable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public void run() {
                        /*
                            Method dump skipped, instructions count: 844
                            To view this dump change 'Code comments level' option to 'DEBUG'
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.shop.JshopTakeCouponUtils.AnonymousClass3.AnonymousClass2.run():void");
                    }
                });
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(final HttpError httpError) {
                iMyActivity.post(new Runnable() { // from class: com.jingdong.common.shop.JshopTakeCouponUtils.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        String message = httpError.getMessage();
                        if (message != null) {
                            ToastUtils.showToastInCenter(iMyActivity.getThisActivity().getApplicationContext(), (byte) 1, message, 0);
                        }
                        Runnable runnable3 = runnable2;
                        if (runnable3 != null) {
                            runnable3.run();
                        }
                    }
                });
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        iMyActivity.getHttpGroupaAsynPool().add(httpSetting);
    }

    public void executeTakeCouponRunnable(IMyActivity iMyActivity, JshopCoupon jshopCoupon, Runnable runnable, Runnable runnable2, String str, Intent intent, int i2, String str2, String str3, String str4) {
        executeReceiveCoupon(iMyActivity, jshopCoupon, runnable, runnable2, str, intent, i2, true, str2, str3, str4);
    }

    public void executeTakeCouponRunnable(IMyActivity iMyActivity, JshopCoupon jshopCoupon, Runnable runnable, Runnable runnable2, String str, Intent intent, int i2, boolean z, String str2, String str3, String str4) {
        executeReceiveCoupon(iMyActivity, jshopCoupon, runnable, runnable2, str, intent, i2, z, str2, str3, str4);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:3|4|(3:5|6|(1:8))|9|10|12|13|(1:15)|16|18) */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0043, code lost:
        r8 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0044, code lost:
        com.jingdong.corelib.utils.Log.e(com.jingdong.common.shop.JshopTakeCouponUtils.TAG, "\u83b7\u53d6\u6307\u7eb9\u4fe1\u606f-shshshfpb\u5931\u8d25\uff1a" + r8.getMessage());
        r8.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x007b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void addRMParams(android.content.Context r8, org.json.JSONObject r9, java.lang.String r10) {
        /*
            r7 = this;
            java.lang.String r0 = "JshopTakeCouponUtils"
            java.lang.String r1 = ""
            java.lang.String r2 = "-1"
            if (r9 == 0) goto L84
            com.jd.sec.LogoManager r3 = com.jd.sec.LogoManager.getInstance(r8)     // Catch: java.lang.Exception -> L21
            r3.init()     // Catch: java.lang.Exception -> L21
            com.jd.sec.LogoManager r3 = com.jd.sec.LogoManager.getInstance(r8)     // Catch: java.lang.Exception -> L21
            java.lang.String r3 = r3.getLogo()     // Catch: java.lang.Exception -> L21
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Exception -> L1f
            if (r4 == 0) goto L3e
            r3 = r2
            goto L3e
        L1f:
            r4 = move-exception
            goto L23
        L21:
            r4 = move-exception
            r3 = r1
        L23:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "\u83b7\u53d6\u6307\u7eb9\u4fe1\u606f-EID\u5931\u8d25\uff1a"
            r5.append(r6)
            java.lang.String r6 = r4.getMessage()
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            com.jingdong.corelib.utils.Log.e(r0, r5)
            r4.printStackTrace()
        L3e:
            java.lang.String r1 = com.jd.stat.security.jma.JMA.getSoftFingerprint(r8)     // Catch: java.lang.Exception -> L43
            goto L5f
        L43:
            r8 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "\u83b7\u53d6\u6307\u7eb9\u4fe1\u606f-shshshfpb\u5931\u8d25\uff1a"
            r4.append(r5)
            java.lang.String r5 = r8.getMessage()
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            com.jingdong.corelib.utils.Log.e(r0, r4)
            r8.printStackTrace()
        L5f:
            java.lang.String r8 = "eid"
            r9.put(r8, r3)     // Catch: org.json.JSONException -> L80
            java.lang.String r8 = "shshshfp"
            r9.put(r8, r2)     // Catch: org.json.JSONException -> L80
            java.lang.String r8 = "shshshfpb"
            r9.put(r8, r1)     // Catch: org.json.JSONException -> L80
            java.lang.String r8 = "shshshfpa"
            r9.put(r8, r2)     // Catch: org.json.JSONException -> L80
            java.lang.String r8 = "pageClickkey"
            boolean r0 = android.text.TextUtils.isEmpty(r10)     // Catch: org.json.JSONException -> L80
            if (r0 == 0) goto L7c
            r10 = r2
        L7c:
            r9.put(r8, r10)     // Catch: org.json.JSONException -> L80
            goto L84
        L80:
            r8 = move-exception
            r8.printStackTrace()
        L84:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.shop.JshopTakeCouponUtils.addRMParams(android.content.Context, org.json.JSONObject, java.lang.String):void");
    }
}
