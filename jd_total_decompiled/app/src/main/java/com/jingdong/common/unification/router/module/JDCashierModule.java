package com.jingdong.common.unification.router.module;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import androidx.annotation.RequiresApi;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.utils.pay.CashierRouterParamMap;
import com.jingdong.common.cashiernative.CashierQuickPayProtocol;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDCashierModule {
    private static final String TAG = "JDCashierModule";

    private void parseRequestCode(JSONObject jSONObject, Bundle bundle) {
        if (jSONObject == null || bundle == null) {
            return;
        }
        int i2 = 404;
        try {
            String optString = jSONObject.optString("requestCode");
            if (!TextUtils.isEmpty(optString)) {
                i2 = Integer.parseInt(optString);
            }
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
            }
        }
        bundle.putInt("requestCode", i2);
    }

    @RequiresApi(api = 8)
    public void executePayService(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        try {
            if (context == null || jSONObject == null) {
                if (callBackListener != null) {
                    callBackListener.onError(703);
                    return;
                }
                return;
            }
            String optString = jSONObject.optString(CashierQuickPayProtocol.QUICK_PAY_JSON_PARAM);
            if (TextUtils.isEmpty(optString) && callBackListener != null) {
                callBackListener.onError(703);
                com.jingdong.app.mall.utils.pay.c.a("JDCashierModule.executePayService()", "encodeJsonParam is empty = " + optString);
                return;
            }
            String str = new String(Base64.decode(optString, 0));
            if (TextUtils.isEmpty(str) && callBackListener != null) {
                callBackListener.onError(703);
                com.jingdong.app.mall.utils.pay.c.a("JDCashierModule.executePayService()", "decodeJsonParam is empty = " + str);
                return;
            }
            if (Log.D) {
                Log.d("JDCashierModule", "executePayService:\tdecodeJsonParam =" + str);
            }
            JSONObject jSONObject2 = new JSONObject(str);
            if (bundle == null) {
                bundle = new Bundle();
                parseRequestCode(jSONObject2, bundle);
            }
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                String optString2 = jSONObject2.optString(next);
                bundle.putString(next, optString2);
                if (Log.D) {
                    Log.d("JDCashierModule", "executePayService:\t" + next + ContainerUtils.KEY_VALUE_DELIMITER + optString2);
                }
            }
            com.jingdong.app.mall.utils.pay.b.a(R2.color.bright_foreground_disabled_material_dark, context, bundle, bundle.getInt("requestCode", 404));
            if (callBackListener != null) {
                callBackListener.onComplete();
            }
        } catch (Exception e2) {
            if (callBackListener != null) {
                callBackListener.onError(701);
            }
            com.jingdong.app.mall.utils.pay.c.a("JDCashierModule.executePayService()", "execute pay service on exception errorMsg = " + e2.getMessage());
            if (Log.E) {
                e2.printStackTrace();
            }
        }
    }

    public void openCashier(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        String str;
        CashierRouterParamMap cashierRouterParamMap;
        try {
            if (context != null && jSONObject != null) {
                if (!jSONObject.has(CashierRouterParamMap.PARAM_KEY)) {
                    JDRouterUtil.callBackError(callBackListener, 703);
                    return;
                }
                try {
                    str = jSONObject.getString(CashierRouterParamMap.PARAM_KEY);
                } catch (JSONException e2) {
                    if (Log.E || Log.W) {
                        e2.printStackTrace();
                    }
                    str = null;
                }
                if (str == null) {
                    JDRouterUtil.callBackError(callBackListener, 703);
                    return;
                }
                try {
                    cashierRouterParamMap = (CashierRouterParamMap) JDJSON.parseObject(str, CashierRouterParamMap.class);
                    if (cashierRouterParamMap == null) {
                        cashierRouterParamMap = new CashierRouterParamMap();
                    }
                } catch (Exception e3) {
                    if (Log.E || Log.W) {
                        e3.printStackTrace();
                    }
                    cashierRouterParamMap = new CashierRouterParamMap();
                }
                String str2 = cashierRouterParamMap.appId;
                String str3 = cashierRouterParamMap.payId;
                String str4 = cashierRouterParamMap.orderId;
                if (str2 == null) {
                    str2 = "";
                }
                if (str3 == null) {
                    str3 = "";
                }
                if (str4 == null) {
                    str4 = "";
                }
                if (bundle == null) {
                    bundle = new Bundle();
                    parseRequestCode(jSONObject, bundle);
                }
                bundle.putString("appId", str2);
                bundle.putString("payId", str3);
                bundle.putString("orderId", str4);
                com.jingdong.app.mall.utils.pay.b.a(1024, context, bundle, bundle.getInt("requestCode", 404));
                JDRouterUtil.callBackComplete(callBackListener);
                return;
            }
            JDRouterUtil.callBackError(callBackListener, 702);
        } catch (Exception unused) {
            JDRouterUtil.callBackError(callBackListener, 701);
        }
    }

    public void show(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        try {
            if (context == null || jSONObject == null) {
                if (callBackListener != null) {
                    callBackListener.onError(703);
                    return;
                }
                return;
            }
            if (bundle == null) {
                bundle = new Bundle();
                parseRequestCode(jSONObject, bundle);
            }
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                String optString = jSONObject.optString(next);
                bundle.putString(next, optString);
                if (Log.D) {
                    Log.d("JDCashierModule", "show:\t" + next + ContainerUtils.KEY_VALUE_DELIMITER + optString);
                }
            }
            com.jingdong.app.mall.utils.pay.a.a(context, bundle);
            if (callBackListener != null) {
                callBackListener.onComplete();
            }
        } catch (Exception e2) {
            if (callBackListener != null) {
                callBackListener.onError(701);
            }
            if (Log.E) {
                e2.printStackTrace();
            }
        }
    }

    public void showCreditPay(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        try {
            if (context == null || jSONObject == null) {
                if (callBackListener != null) {
                    callBackListener.onError(703);
                    return;
                }
                return;
            }
            if (bundle == null) {
                bundle = new Bundle();
                parseRequestCode(jSONObject, bundle);
            }
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                String optString = jSONObject.optString(next);
                bundle.putString(next, optString);
                if (Log.D) {
                    Log.d("JDCashierModule", "showCreditPay:\t" + next + ContainerUtils.KEY_VALUE_DELIMITER + optString);
                }
            }
            com.jingdong.app.mall.utils.pay.b.a(4096, context, bundle, bundle.getInt("requestCode", 404));
            if (callBackListener != null) {
                callBackListener.onComplete();
            }
        } catch (Exception e2) {
            if (callBackListener != null) {
                callBackListener.onError(701);
            }
            if (Log.E) {
                e2.printStackTrace();
            }
        }
    }

    public void showPopupPage(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        try {
            if (context == null || jSONObject == null) {
                if (callBackListener != null) {
                    callBackListener.onError(703);
                    return;
                }
                return;
            }
            if (bundle == null) {
                bundle = new Bundle();
                parseRequestCode(jSONObject, bundle);
            }
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                String optString = jSONObject.optString(next);
                bundle.putString(next, optString);
                if (Log.D) {
                    Log.d("JDCashierModule", "showPopupPage:\t" + next + ContainerUtils.KEY_VALUE_DELIMITER + optString);
                }
            }
            bundle.putString(PairKey.CASHIER_SHOW_STYLE, "1");
            com.jingdong.app.mall.utils.pay.a.a(context, bundle);
            if (callBackListener != null) {
                callBackListener.onComplete();
            }
        } catch (Exception e2) {
            if (callBackListener != null) {
                callBackListener.onError(701);
            }
            if (Log.E) {
                e2.printStackTrace();
            }
        }
    }
}
