package com.jingdong.common.phonecharge.game;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.app.mall.utils.s;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.phonecharge.model.ChargeOrder;
import com.jingdong.common.phonecharge.model.FlowOrderDetail;
import com.jingdong.common.phonecharge.model.GameOrder;
import com.jingdong.common.phonecharge.phone.Constant;
import com.jingdong.common.phonecharge.phone.HttpAdress;
import com.jingdong.common.phonecharge.phone.ParseJsonToData;
import com.jingdong.common.utils.pay.PayCallBackAllListener;
import com.jingdong.common.utils.pay.PayUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.jdsdk.widget.ToastUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.codec.BuildConfig;

/* loaded from: classes5.dex */
public class UnitTransformUtil {
    public static final String detailErrorStr = "\u518d\u6b21\u8d2d\u4e70\u5931\u8d25\uff0c\u8bf7\u81f3\u5145\u503c\u4e2d\u5fc3\u5145\u503c";

    public static byte[] desEncrypt(byte[] bArr) throws Exception {
        return null;
    }

    public static String getDate(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long.parseLong(str)));
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getMoney(String str) {
        if (str.length() >= 3) {
            return str.substring(0, str.length() - 2) + OrderISVUtil.MONEY_DECIMAL + str.substring(str.length() - 2, str.length());
        } else if (str.length() == 2) {
            return "0." + str;
        } else if (str.length() == 1) {
            return "0.0" + str;
        } else {
            return "0.00";
        }
    }

    public static void postHttpFlowDetail(final Context context, String str, final int i2) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setHost(Configuration.getVirtualHost());
        httpSetting.setFunctionId(HttpAdress.getRscFlowOrderDetail);
        httpSetting.putJsonParam("orderId", str);
        httpSetting.setNotifyUser(true);
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.phonecharge.game.UnitTransformUtil.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(final HttpResponse httpResponse) {
                Context context2 = context;
                if (context2 instanceof BaseActivity) {
                    ((BaseActivity) context2).post(new Runnable() { // from class: com.jingdong.common.phonecharge.game.UnitTransformUtil.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                            if (!"".equals(Constant.getErrorString(fastJsonObject.getString("code"), fastJsonObject.getString("errorCode"), fastJsonObject.getString("errorMessage")))) {
                                ToastUtils.shortToast(UnitTransformUtil.detailErrorStr);
                            } else if (fastJsonObject.optJSONObject("flowOrder") != null) {
                                FlowOrderDetail flowOrderDetail = new FlowOrderDetail(fastJsonObject.optJSONObject("flowOrder"));
                                fastJsonObject.optString("payback");
                                if (i2 == 0) {
                                    JDJSONObject jDJSONObject = new JDJSONObject();
                                    try {
                                        jDJSONObject.put("orderId", (Object) (flowOrderDetail.orderId + ""));
                                        jDJSONObject.put("orderTypeCode", (Object) "0");
                                        jDJSONObject.put("orderType", (Object) HttpAdress.orderType_flow);
                                        jDJSONObject.put("payablePrice", (Object) (flowOrderDetail.onlinePay + ""));
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                    PayUtils.doPay((BaseActivity) context, jDJSONObject, new PayCallBackAllListener() { // from class: com.jingdong.common.phonecharge.game.UnitTransformUtil.1.2.1
                                        @Override // com.jingdong.common.utils.pay.PayCallBackAllListener
                                        public void failed() {
                                            Log.d("############", " pay fail !!! ");
                                        }

                                        @Override // com.jingdong.common.utils.pay.PayCallbackListener
                                        public void succeed() {
                                            Log.d("############", " pay success !!! ");
                                        }
                                    });
                                }
                            }
                        }
                    });
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                if (httpError == null) {
                    return;
                }
                Context context2 = context;
                if (context2 instanceof BaseActivity) {
                    ((BaseActivity) context2).post(new Runnable() { // from class: com.jingdong.common.phonecharge.game.UnitTransformUtil.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (NetUtils.isNetworkAvailable()) {
                                ToastUtils.shortToast(UnitTransformUtil.detailErrorStr);
                            }
                        }
                    });
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i3, int i4) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        if (context instanceof BaseActivity) {
            ((BaseActivity) context).getHttpGroupaAsynPool().add(httpSetting);
        }
    }

    public static void postHttpPhoneDetail(final Context context, String str, final int i2) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setHost(Configuration.getVirtualHost());
        httpSetting.setFunctionId(HttpAdress.recharge_queryOrderInfo);
        httpSetting.putJsonParam("orderId", str);
        httpSetting.setConnectTimeout(120000);
        httpSetting.setListener(new HttpGroup.CustomOnAllListener() { // from class: com.jingdong.common.phonecharge.game.UnitTransformUtil.2
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                final JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                if (Log.D) {
                    Log.d("TAG", " -->> json:" + fastJsonObject);
                }
                Context context2 = context;
                if (context2 instanceof BaseActivity) {
                    ((BaseActivity) context2).post(new Runnable() { // from class: com.jingdong.common.phonecharge.game.UnitTransformUtil.2.2
                        @Override // java.lang.Runnable
                        public void run() {
                            JDJSONObject jDJSONObject = fastJsonObject;
                            if (jDJSONObject != null) {
                                if (!"".equals(Constant.getErrorString(jDJSONObject.getString("code"), fastJsonObject.getString("errorCode"), fastJsonObject.getString("errorMessage")))) {
                                    ToastUtils.shortToast(UnitTransformUtil.detailErrorStr);
                                    return;
                                }
                                ChargeOrder parseJSONtoChargeOrder = ParseJsonToData.parseJSONtoChargeOrder(fastJsonObject);
                                if (parseJSONtoChargeOrder != null) {
                                    int i3 = i2;
                                    if (i3 != 0) {
                                        if (i3 != 1 || parseJSONtoChargeOrder == null) {
                                            return;
                                        }
                                        UnitTransformUtil.getMoney(String.valueOf(parseJSONtoChargeOrder.facePrice)).replace(".00", "");
                                        return;
                                    }
                                    JDJSONObject jDJSONObject2 = new JDJSONObject();
                                    try {
                                        jDJSONObject2.put("orderId", (Object) parseJSONtoChargeOrder.orderId);
                                        jDJSONObject2.put("orderTypeCode", (Object) "0");
                                        jDJSONObject2.put("orderType", (Object) "37");
                                        jDJSONObject2.put("payablePrice", (Object) (parseJSONtoChargeOrder.money + ""));
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                    PayUtils.doPay((BaseActivity) context, jDJSONObject2, new PayCallBackAllListener() { // from class: com.jingdong.common.phonecharge.game.UnitTransformUtil.2.2.1
                                        @Override // com.jingdong.common.utils.pay.PayCallBackAllListener
                                        public void failed() {
                                            Log.d("############", " pay fail !!! ");
                                        }

                                        @Override // com.jingdong.common.utils.pay.PayCallbackListener
                                        public void succeed() {
                                            Log.d("############", " pay success !!! ");
                                        }
                                    });
                                }
                            }
                        }
                    });
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                Context context2 = context;
                if (context2 instanceof BaseActivity) {
                    ((BaseActivity) context2).post(new Runnable() { // from class: com.jingdong.common.phonecharge.game.UnitTransformUtil.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (NetUtils.isNetworkAvailable()) {
                                ToastUtils.shortToast(UnitTransformUtil.detailErrorStr);
                            }
                        }
                    });
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i3, int i4) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        if (context instanceof BaseActivity) {
            ((BaseActivity) context).getHttpGroupaAsynPool().add(httpSetting);
        }
    }

    public static void postHttpQqGameDetail(final Context context, String str, final int i2) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setHost(Configuration.getVirtualHost());
        httpSetting.setFunctionId(GameHttpAddr.SUBMIT_QRECG_DETAIL);
        httpSetting.putJsonParam("orderId", str);
        httpSetting.putJsonParam(PairKey.APP_KEY, "android");
        httpSetting.putJsonParam("version", BuildConfig.VERSION_NAME);
        httpSetting.setEffect(1);
        httpSetting.setListener(new HttpGroup.CustomOnAllListener() { // from class: com.jingdong.common.phonecharge.game.UnitTransformUtil.3
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                final JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                if (Log.D) {
                    Log.d("TAG", " -->> json:" + fastJsonObject);
                }
                Context context2 = context;
                if (context2 instanceof BaseActivity) {
                    ((BaseActivity) context2).post(new Runnable() { // from class: com.jingdong.common.phonecharge.game.UnitTransformUtil.3.2
                        @Override // java.lang.Runnable
                        public void run() {
                            JDJSONObject jDJSONObject = fastJsonObject;
                            if (jDJSONObject != null) {
                                if (!"".equals(Constant.getErrorString(jDJSONObject.getString("code"), fastJsonObject.getString("errorCode"), fastJsonObject.getString("errorMessage")))) {
                                    ToastUtils.shortToast(UnitTransformUtil.detailErrorStr);
                                    return;
                                }
                                try {
                                    GameOrder gameOrder = GameOrder.getGameOrder(fastJsonObject.getJSONObject("result"));
                                    if (gameOrder != null) {
                                        int i3 = i2;
                                        if (i3 != 0) {
                                            if (i3 == 1) {
                                                s.k((BaseActivity) context, Long.valueOf(gameOrder.skuId), gameOrder.title, 0, new SourceEntity("game_charge", "game_charge__order_all"));
                                                return;
                                            }
                                            return;
                                        }
                                        JDJSONObject jDJSONObject2 = new JDJSONObject();
                                        try {
                                            jDJSONObject2.put("orderId", (Object) (gameOrder.orderId + ""));
                                            jDJSONObject2.put("orderTypeCode", (Object) "0");
                                            jDJSONObject2.put("orderType", (Object) "34");
                                            jDJSONObject2.put("payablePrice", (Object) UnitTransformUtil.getMoney(String.valueOf((int) gameOrder.onlinePay)));
                                        } catch (Exception e2) {
                                            e2.printStackTrace();
                                        }
                                        PayUtils.doPay((BaseActivity) context, jDJSONObject2, new PayCallBackAllListener() { // from class: com.jingdong.common.phonecharge.game.UnitTransformUtil.3.2.1
                                            @Override // com.jingdong.common.utils.pay.PayCallBackAllListener
                                            public void failed() {
                                                Log.d("############", " pay fail !!! ");
                                            }

                                            @Override // com.jingdong.common.utils.pay.PayCallbackListener
                                            public void succeed() {
                                                Log.d("############", " pay success !!! ");
                                            }
                                        });
                                    }
                                } catch (Exception unused) {
                                    if (NetUtils.isNetworkAvailable()) {
                                        ToastUtils.shortToast(GameChargeConstant.ERROR);
                                    }
                                }
                            }
                        }
                    });
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                Context context2 = context;
                if (context2 instanceof BaseActivity) {
                    ((BaseActivity) context2).post(new Runnable() { // from class: com.jingdong.common.phonecharge.game.UnitTransformUtil.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (NetUtils.isNetworkAvailable()) {
                                ToastUtils.shortToast(UnitTransformUtil.detailErrorStr);
                            }
                        }
                    });
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i3, int i4) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        if (context instanceof BaseActivity) {
            ((BaseActivity) context).getHttpGroupaAsynPool().add(httpSetting);
        }
    }

    public static void showToast(Context context, String str, int i2) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        Toast makeText = Toast.makeText(context, str, 0);
        makeText.setGravity(i2, 0, 0);
        makeText.show();
    }

    public static void startActivity(IMyActivity iMyActivity, Intent intent) {
        iMyActivity.startActivityInFrameWithNoNavigation(intent);
    }

    public static void startPhoneOrFlowCharge(Context context, String str, int i2) {
        try {
            if (i2 == 0) {
                postHttpPhoneDetail(context, str, 1);
            } else if (i2 != 1) {
            } else {
                postHttpFlowDetail(context, str, 1);
            }
        } catch (Exception unused) {
        }
    }

    public static void startPhoneOrFlowCharge(Context context, String str, String str2, int i2) {
        try {
            if ("37".equals(str2)) {
                postHttpPhoneDetail(context, str, i2);
            } else if (HttpAdress.orderType_flow.equals(str2)) {
                postHttpFlowDetail(context, str, i2);
            } else if ("34".equals(str2)) {
                postHttpQqGameDetail(context, str, i2);
            }
        } catch (Exception unused) {
        }
    }
}
