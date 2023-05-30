package com.jingdong.app.mall.bundle.dolphinlib.common.presenter;

import android.app.Activity;
import android.content.Context;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinCouponResponseEntity analysisData(com.jd.framework.json.JDJSONObject r4) {
        /*
            r3 = this;
            java.lang.String r0 = "result"
            r1 = 0
            if (r4 == 0) goto L13
            java.lang.String r2 = r4.optString(r0)     // Catch: java.lang.Exception -> L11
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Exception -> L11
            if (r2 != 0) goto L13
            r2 = 1
            goto L14
        L11:
            r4 = move-exception
            goto L24
        L13:
            r2 = 0
        L14:
            if (r2 == 0) goto L27
            java.lang.String r4 = r4.optString(r0)     // Catch: java.lang.Exception -> L11
            java.lang.Class<com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinCouponResponseEntity> r0 = com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinCouponResponseEntity.class
            java.lang.Object r4 = com.jd.lib.babel.ifloor.utils.CommonServiceUtil.parseObject(r4, r0)     // Catch: java.lang.Exception -> L11
            com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinCouponResponseEntity r4 = (com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinCouponResponseEntity) r4     // Catch: java.lang.Exception -> L11
            r1 = r4
            goto L27
        L24:
            r4.printStackTrace()
        L27:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.bundle.dolphinlib.common.presenter.DolphinCouponInteractor.analysisData(com.jd.framework.json.JDJSONObject):com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinCouponResponseEntity");
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
