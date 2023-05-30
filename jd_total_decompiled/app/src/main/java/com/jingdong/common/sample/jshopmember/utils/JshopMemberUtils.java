package com.jingdong.common.sample.jshopmember.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.common.controller.ShoppingBaseController;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.sample.jshopmember.JshopMemberActivity;
import com.jingdong.common.sample.jshopmember.entity.CouponForPoint;
import com.jingdong.common.ui.JDCircleImageView;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.widget.ToastUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JshopMemberUtils {
    public static final String TAG = "JshopMemberUtils";

    public static void gotoMainFrameClearAllTask(Activity activity) {
        Intent intent = new Intent();
        intent.setFlags(67108864);
        DeepLinkCommonHelper.startActivity(activity, DeepLinkCommonHelper.HOST_JD_TASK_CLEAR_ACTIVITY, intent.getExtras(), true, 67108864, false, "");
        activity.finish();
    }

    public static void gotoShoppingCart(final JshopMemberActivity jshopMemberActivity, String str) {
        if (jshopMemberActivity == null) {
            return;
        }
        ShoppingBaseController.addProductForProductList(jshopMemberActivity, str, 1, new Runnable() { // from class: com.jingdong.common.sample.jshopmember.utils.JshopMemberUtils.3
            @Override // java.lang.Runnable
            public void run() {
                jshopMemberActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshopmember.utils.JshopMemberUtils.3.1
                    {
                        AnonymousClass3.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        ToastUtils.showToastInCenter(jshopMemberActivity.getApplicationContext(), (byte) 1, jshopMemberActivity.getString(R.string.video_pw_add_cart_fail), 0);
                    }
                });
            }
        }, new Runnable() { // from class: com.jingdong.common.sample.jshopmember.utils.JshopMemberUtils.4
            @Override // java.lang.Runnable
            public void run() {
                jshopMemberActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshopmember.utils.JshopMemberUtils.4.1
                    {
                        AnonymousClass4.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        ToastUtils.showToastInCenter(jshopMemberActivity.getApplicationContext(), (byte) 2, jshopMemberActivity.getString(R.string.jshop_add_cart_success), 0);
                        jshopMemberActivity.showAddCartMenu();
                    }
                });
            }
        }, null);
    }

    public static boolean isFollowGift(int i2) {
        return i2 == 4 || i2 == 2;
    }

    public static void sendHttpRequest(MyActivity myActivity, String str, JSONObject jSONObject, boolean z, final HttpGroup.OnAllListener onAllListener) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(Configuration.getJshopHost());
        if (z) {
            httpSetting.setEffect(1);
        } else {
            httpSetting.setEffect(0);
        }
        httpSetting.setFunctionId(str);
        httpSetting.setJsonParams(jSONObject);
        httpSetting.setNotifyUser(false);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setUseCookies(true);
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.sample.jshopmember.utils.JshopMemberUtils.2
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                Log.d(JshopMemberUtils.TAG, " onEnd " + httpResponse.getJSONObject());
                HttpGroup.OnAllListener onAllListener2 = onAllListener;
                if (onAllListener2 != null) {
                    onAllListener2.onEnd(httpResponse);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                Log.d(JshopMemberUtils.TAG, "onError");
                HttpGroup.OnAllListener onAllListener2 = onAllListener;
                if (onAllListener2 != null) {
                    onAllListener2.onError(httpError);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
                HttpGroup.OnAllListener onAllListener2 = onAllListener;
                if (onAllListener2 != null) {
                    onAllListener2.onProgress(i2, i3);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
                HttpGroup.OnAllListener onAllListener2 = onAllListener;
                if (onAllListener2 != null) {
                    onAllListener2.onStart();
                }
            }
        });
        if (myActivity != null) {
            myActivity.getHttpGroupaAsynPool().add(httpSetting);
        }
    }

    public static void sendTakeCouponRequest(final MyActivity myActivity, CouponForPoint couponForPoint, String str) {
        Log.d(TAG, "sendTakeCouponRequest coupon = " + couponForPoint.batchId);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("venderId", str);
            jSONObject.put("couponBatchKey", "" + couponForPoint.batchKey);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        final long j2 = couponForPoint.points;
        if (myActivity instanceof JshopMemberActivity) {
            sendHttpRequest(myActivity, "points2Coupon", jSONObject, true, new HttpGroup.OnAllListener() { // from class: com.jingdong.common.sample.jshopmember.utils.JshopMemberUtils.7
                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(final HttpResponse httpResponse) {
                    myActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshopmember.utils.JshopMemberUtils.7.1
                        {
                            AnonymousClass7.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            JDJSONObject optJSONObject = httpResponse.getFastJsonObject().optJSONObject("result");
                            if (optJSONObject != null) {
                                int optInt = optJSONObject.optInt("code");
                                JshopMemberUtils.showAlert(myActivity, optJSONObject.optString("msg"));
                                if (optInt == 0) {
                                    AnonymousClass7 anonymousClass7 = AnonymousClass7.this;
                                    ((JshopMemberActivity) myActivity).updateMemberScore(j2);
                                    return;
                                }
                                return;
                            }
                            Log.d(JshopMemberUtils.TAG, "error!");
                        }
                    });
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    myActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshopmember.utils.JshopMemberUtils.7.2
                        {
                            AnonymousClass7.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            JshopMemberUtils.showAlert(myActivity, "\u5151\u6362\u5931\u8d25~");
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
        }
    }

    public static void setUserImage(MyActivity myActivity, JDCircleImageView jDCircleImageView, String str) {
        if (myActivity == null || jDCircleImageView == null || TextUtils.isEmpty(str) || jDCircleImageView == null) {
            return;
        }
        JDImageUtils.displayImage(str, jDCircleImageView, JDDisplayImageOptions.createSimple().showImageOnLoading(R.drawable.jshop_default_portrait).showImageOnFail(R.drawable.jshop_default_portrait));
    }

    public static void showAlert(Context context, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            final JDDialog createJdDialogWithStyle1 = JDDialogFactory.getInstance().createJdDialogWithStyle1(context, str, "\u6211\u77e5\u9053\u4e86");
            createJdDialogWithStyle1.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshopmember.utils.JshopMemberUtils.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    createJdDialogWithStyle1.dismiss();
                }
            });
            createJdDialogWithStyle1.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshopmember.utils.JshopMemberUtils.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    createJdDialogWithStyle1.dismiss();
                }
            });
            createJdDialogWithStyle1.show();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void showChannelImage(final MyActivity myActivity, final ImageView imageView, String str) {
        if (myActivity == null || imageView == null) {
            return;
        }
        imageView.setVisibility(0);
        if (!TextUtils.isEmpty(str) && str.startsWith("http")) {
            JDImageUtils.displayImage(str, imageView, null, false, new JDSimpleImageLoadingListener() { // from class: com.jingdong.common.sample.jshopmember.utils.JshopMemberUtils.1
                @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                    myActivity.post(new Runnable() { // from class: com.jingdong.common.sample.jshopmember.utils.JshopMemberUtils.1.1
                        {
                            AnonymousClass1.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            imageView.setBackgroundResource(R.drawable.jshop_title_text_member_center);
                        }
                    });
                }
            }, null);
        } else {
            imageView.setBackgroundResource(R.drawable.jshop_title_text_member_center);
        }
    }

    public static void takeCoupon(final JshopMemberActivity jshopMemberActivity, final CouponForPoint couponForPoint, final String str) {
        Log.d(TAG, "takeCoupon");
        try {
            final String str2 = couponForPoint.batchId + CartConstant.KEY_YB_INFO_LINK + couponForPoint.points + CartConstant.KEY_YB_INFO_LINK + couponForPoint.condition + CartConstant.KEY_YB_INFO_LINK + couponForPoint.discount;
            JDMtaUtils.sendCommonData(jshopMemberActivity, "ShopVIP_Coupon", str2, "", jshopMemberActivity, jshopMemberActivity.getMemberLevel(), "", "", "Shop_VIP", jshopMemberActivity.mShopId);
            final JDDialog createJdDialogWithStyle2 = JDDialogFactory.getInstance().createJdDialogWithStyle2(jshopMemberActivity, jshopMemberActivity.getString(R.string.jshop_coupon_exchange_tips, new Object[]{couponForPoint.points + ""}), "\u53d6\u6d88", "\u786e\u8ba4");
            createJdDialogWithStyle2.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshopmember.utils.JshopMemberUtils.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    JshopMemberActivity jshopMemberActivity2 = jshopMemberActivity;
                    JDMtaUtils.sendCommonData(jshopMemberActivity2, "ShopVIP_CancelConfirm", str2, "", jshopMemberActivity2, jshopMemberActivity2.getMemberLevel(), "", "", "Shop_VIP", jshopMemberActivity.mShopId);
                    Log.d(JshopMemberUtils.TAG, "left btn");
                    createJdDialogWithStyle2.dismiss();
                }
            });
            createJdDialogWithStyle2.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshopmember.utils.JshopMemberUtils.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Log.d(JshopMemberUtils.TAG, "right btn");
                    JshopMemberActivity jshopMemberActivity2 = jshopMemberActivity;
                    JDMtaUtils.sendCommonData(jshopMemberActivity2, "ShopVIP_CouponConfirm", str2, "", jshopMemberActivity2, jshopMemberActivity2.getMemberLevel(), "", "", "Shop_VIP", jshopMemberActivity.mShopId);
                    JshopMemberUtils.sendTakeCouponRequest(jshopMemberActivity, couponForPoint, str);
                    createJdDialogWithStyle2.dismiss();
                }
            });
            createJdDialogWithStyle2.show();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
