package com.jingdong.app.mall.bundle.dolphinlib.common.presenter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jingdong.app.mall.bundle.dolphinlib.common.listener.ICouponListener;
import com.jingdong.app.mall.bundle.dolphinlib.common.util.LogUtil;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinCouponResponseEntity;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes19.dex */
public class DolphinCouponInteractor {
    private Context mContext;
    private ICouponListener mListener;

    public DolphinCouponInteractor(Context context, ICouponListener iCouponListener) {
        this.mContext = context;
        this.mListener = iCouponListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0016 A[Catch: Exception -> 0x0011, TRY_LEAVE, TryCatch #0 {Exception -> 0x0011, blocks: (B:4:0x0005, B:11:0x0016), top: B:15:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public DolphinCouponResponseEntity analysisData(JDJSONObject jDJSONObject) {
        boolean z;
        if (jDJSONObject != null) {
            try {
                if (!TextUtils.isEmpty(jDJSONObject.optString("result"))) {
                    z = true;
                    if (z) {
                        return null;
                    }
                    return (DolphinCouponResponseEntity) CommonServiceUtil.parseObject(jDJSONObject.optString("result"), DolphinCouponResponseEntity.class);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
        z = false;
        if (z) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callBack(final boolean z, final DolphinCouponResponseEntity dolphinCouponResponseEntity) {
        Context context = this.mContext;
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(new Runnable() { // from class: com.jingdong.app.mall.bundle.dolphinlib.common.presenter.DolphinCouponInteractor.2
                @Override // java.lang.Runnable
                public void run() {
                    if (DolphinCouponInteractor.this.mListener != null) {
                        if (z) {
                            DolphinCouponInteractor.this.mListener.getSuccess(dolphinCouponResponseEntity);
                            return;
                        }
                        ICouponListener iCouponListener = DolphinCouponInteractor.this.mListener;
                        DolphinCouponResponseEntity dolphinCouponResponseEntity2 = dolphinCouponResponseEntity;
                        iCouponListener.getFail(dolphinCouponResponseEntity2 != null ? dolphinCouponResponseEntity2.msg : "\u9886\u53d6\u5931\u8d25");
                    }
                }
            });
        }
    }

    public void getData(String str, String str2, String str3, final int i2) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("sendGlobalCoupon");
        httpSetting.setEffect(0);
        httpSetting.putJsonParam("activeKey", str);
        httpSetting.putJsonParam("ruleId", str2);
        httpSetting.putJsonParam(JshopConst.JSKEY_BATCH_ID, str3);
        httpSetting.setHost(Configuration.getNgwHost());
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.app.mall.bundle.dolphinlib.common.presenter.DolphinCouponInteractor.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                try {
                    DolphinCouponResponseEntity analysisData = DolphinCouponInteractor.this.analysisData(httpResponse.getFastJsonObject());
                    if (!(analysisData != null && "0".equals(analysisData.code))) {
                        DolphinCouponInteractor.this.callBack(false, analysisData);
                        return;
                    }
                    analysisData.position = i2;
                    DolphinCouponInteractor.this.callBack(true, analysisData);
                } catch (Exception unused) {
                    DolphinCouponInteractor.this.callBack(false, null);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                LogUtil.d("asdf", "\u9886\u53d6\u4f18\u60e0\u5238\u63a5\u53e3\u8bf7\u6c42\u51fa\u9519\uff01");
                DolphinCouponInteractor.this.callBack(false, null);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i3, int i4) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
                LogUtil.d("asdf", "\u9886\u53d6\u4f18\u60e0\u5238\u8bf7\u6c42\u5f00\u59cb\uff01");
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }
}
