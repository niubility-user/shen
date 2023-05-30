package com.jingdong.app.mall.utils.pay;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.app.mall.utils.CommonUtilEx;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkOrderCenterHelper;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.CommonBridge;
import com.jingdong.common.utils.CommonUtil;
import com.jingdong.common.utils.pay.IJump;
import com.jingdong.common.utils.pay.PayUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.utils.URLParamMap;

/* loaded from: classes4.dex */
public class JumpImpl implements IJump {
    private static final String TAG = "JumpImpl";

    @Override // com.jingdong.common.utils.pay.IJump
    public void doPayFinishForward(String str, CommonBase.BrowserNewUrlListener browserNewUrlListener) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            PayUtils.doPayFinishForward(str, browserNewUrlListener);
        } catch (Exception e2) {
            if (Log.E) {
                Log.e(TAG, e2.getMessage());
            }
        }
    }

    @Override // com.jingdong.common.utils.pay.IJump
    public void getJumpToken(Activity activity, Bundle bundle, CommonBase.BrowserNewUrlListener browserNewUrlListener) {
        try {
            String string = bundle.getString("toUrl");
            if (TextUtils.isEmpty(string)) {
                return;
            }
            URLParamMap uRLParamMap = new URLParamMap();
            uRLParamMap.put(RemoteMessageConst.TO, string);
            CommonBase.queryBrowserUrl(RemoteMessageConst.TO, uRLParamMap, browserNewUrlListener, true);
        } catch (Exception e2) {
            if (Log.E) {
                Log.e(TAG, e2.getMessage());
            }
        }
    }

    @Override // com.jingdong.common.utils.pay.IJump
    public void jumpToHomeActivity(Activity activity, Bundle bundle) {
        if (activity != null) {
            try {
                CommonUtilEx.getInstance().backToHomePage(activity);
            } catch (Exception e2) {
                if (Log.E) {
                    Log.e(TAG, e2.getMessage());
                }
            }
        }
    }

    @Override // com.jingdong.common.utils.pay.IJump
    public void jumpToInterfaceActivity(Activity activity, Bundle bundle) {
        if (activity == null || bundle == null) {
            return;
        }
        try {
            OpenAppJumpController.dispatchJumpRequestInApp(activity, Uri.parse(bundle.getString(CartConstant.KEY_JUMPURL)));
        } catch (Exception e2) {
            if (Log.E) {
                Log.e(TAG, e2.getMessage());
            }
        }
    }

    @Override // com.jingdong.common.utils.pay.IJump
    public void jumpToOrderListActivity(Activity activity, Bundle bundle) {
        if (activity != null) {
            try {
                DeepLinkOrderCenterHelper.startOrderList(activity);
            } catch (Exception e2) {
                if (Log.E) {
                    Log.e(TAG, e2.getMessage());
                }
            }
        }
    }

    @Override // com.jingdong.common.utils.pay.IJump
    public void jumpToWebActivity(BaseActivity baseActivity, Bundle bundle) {
        if (baseActivity == null || bundle == null) {
            return;
        }
        try {
            CommonBridge.goToMWithUrl(baseActivity, bundle.getString(CartConstant.KEY_JUMPURL));
        } catch (Exception e2) {
            if (Log.E) {
                Log.e(TAG, e2.getMessage());
            }
        }
    }

    @Override // com.jingdong.common.utils.pay.IJump
    public void reDoJDPay(Activity activity) {
        try {
            CommonUtilEx.getInstance().reDoJDPay(activity);
        } catch (Exception e2) {
            if (Log.E) {
                Log.e(TAG, e2.getMessage());
            }
        }
    }

    @Override // com.jingdong.common.utils.pay.IJump
    public void reDoUnionPay(Activity activity) {
        try {
            CommonUtilEx commonUtilEx = CommonUtilEx.getInstance();
            String unpayTN = CommonUtil.getUnpayTN();
            String seType = CommonUtil.getSeType();
            if (!TextUtils.isEmpty(seType)) {
                CommonUtil.androidPay(activity, unpayTN, seType);
            } else {
                commonUtilEx.doPay(activity, unpayTN);
            }
        } catch (Exception e2) {
            if (Log.E) {
                Log.e(TAG, e2.getMessage());
            }
        }
    }

    @Override // com.jingdong.common.utils.pay.IJump
    public void unionAndWeiXinPay(Activity activity, Bundle bundle, CommonBase.ProgresslListener progresslListener) {
        if (bundle != null) {
            try {
                CommonUtilEx.getInstance().unionAndWeiXinPay(activity, bundle, progresslListener);
            } catch (Exception e2) {
                if (Log.E) {
                    Log.e(TAG, e2.getMessage());
                }
            }
        }
    }

    @Override // com.jingdong.common.utils.pay.IJump
    public void doPayFinishForward(String str, CommonBase.BrowserCashierUrlListener browserCashierUrlListener) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            PayUtils.doPayFinishForward(str, browserCashierUrlListener);
        } catch (Exception e2) {
            if (Log.E) {
                Log.e(TAG, e2.getMessage());
            }
        }
    }
}
