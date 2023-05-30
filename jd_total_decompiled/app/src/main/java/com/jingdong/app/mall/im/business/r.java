package com.jingdong.app.mall.im.business;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.jdsdk.mta.ExposureRvUtils;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.service.callback.ExposureStateListener;
import com.jingdong.service.impl.IMMta;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class r extends IMMta {
    private static final String a = "r";

    /* loaded from: classes4.dex */
    class a implements ExposureRvUtils.ExposureStateListener {
        final /* synthetic */ ExposureStateListener a;

        a(r rVar, ExposureStateListener exposureStateListener) {
            this.a = exposureStateListener;
        }

        @Override // com.jingdong.jdsdk.mta.ExposureRvUtils.ExposureStateListener
        public void onExposure(int i2, long j2) {
            ExposureStateListener exposureStateListener = this.a;
            if (exposureStateListener != null) {
                exposureStateListener.onExposure(i2, j2);
            }
        }

        @Override // com.jingdong.jdsdk.mta.ExposureRvUtils.ExposureStateListener
        public void onHide(int i2, long j2, long j3, long j4) {
            ExposureStateListener exposureStateListener = this.a;
            if (exposureStateListener != null) {
                exposureStateListener.onHide(i2, j2, j3, j4);
            }
        }
    }

    /* loaded from: classes4.dex */
    class b extends RecyclerView.OnScrollListener {
        final /* synthetic */ ExposureRvUtils a;

        b(r rVar, ExposureRvUtils exposureRvUtils) {
            this.a = exposureRvUtils;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
            this.a.listen();
        }
    }

    @Override // com.jingdong.service.impl.IMMta, com.jingdong.service.service.MtaService
    public boolean enableAccurateEp() {
        return true;
    }

    @Override // com.jingdong.service.impl.IMMta, com.jingdong.service.service.MtaService
    public void initRVExp(int i2, View view, int i3, ExposureStateListener exposureStateListener) {
        OKLog.d("bundleicssdkservice", a + "---initRVExp");
        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            try {
                recyclerView.addOnScrollListener(new b(this, new ExposureRvUtils(1, recyclerView, 1, new a(this, exposureStateListener))));
            } catch (Exception e2) {
                OKLog.d("bundleicssdkservice", a + e2.getMessage());
            }
        }
    }

    @Override // com.jingdong.service.impl.IMMta, com.jingdong.service.service.MtaService
    public void onClickWithPageId(Context context, String str, String str2, String str3, String str4, String str5) {
        OKLog.d("bundleicssdkservice", a + "---onClickWithPageId");
        JDMtaUtils.onClickWithPageId(context, str, str2, str3, str4, str5);
    }

    @Override // com.jingdong.service.impl.IMMta, com.jingdong.service.service.MtaService
    public void sendClickDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, HashMap<String, String> hashMap) {
        OKLog.d("bundleicssdkservice", a + "---sendClickDataWithExt");
        JDMtaUtils.sendClickDataWithExt(context, str, str2, str3, str4, str5, str6, str7, str8, hashMap);
    }

    @Override // com.jingdong.service.impl.IMMta, com.jingdong.service.service.MtaService
    public void sendCommonData(Context context, String str, String str2, String str3, Object obj, String str4, String str5, String str6, String str7, String str8) {
        OKLog.d("bundleicssdkservice", a + "---sendCommonData");
        JDMtaUtils.sendCommonData(context, str, str2, str3, obj, str4, str5, str6, str7, str8);
    }

    @Override // com.jingdong.service.impl.IMMta, com.jingdong.service.service.MtaService
    public void sendEpData(Context context, Object obj, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        OKLog.d("bundleicssdkservice", a + "---sendEpData");
        JDMtaUtils.sendEpData(context, obj, str, str2, str3, str4, str5, str6, str7);
    }

    @Override // com.jingdong.service.impl.IMMta, com.jingdong.service.service.MtaService
    public void sendExposureData(Context context, Object obj, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        OKLog.d("bundleicssdkservice", a + "---sendExposureData");
        JDMtaUtils.sendExposureData(context, obj, str, str2, str3, str4, str5, str6, str7);
    }

    @Override // com.jingdong.service.impl.IMMta, com.jingdong.service.service.MtaService
    public void sendExposureDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, HashMap<String, String> hashMap) {
        OKLog.d("bundleicssdkservice", a + "---sendExposureDataWithExt");
        JDMtaUtils.sendExposureDataWithExt(context, str, str2, str3, str4, str5, str6, hashMap);
    }

    @Override // com.jingdong.service.impl.IMMta, com.jingdong.service.service.MtaService
    public void sendPagePv(Context context, Object obj, String str, String str2, String str3) {
        OKLog.d("bundleicssdkservice", a + "---sendPagePv");
        JDMtaUtils.sendPagePv(context, obj, str, str2, str3, "", "");
    }

    @Override // com.jingdong.service.impl.IMMta, com.jingdong.service.service.MtaService
    public void sendEpData(Context context, String str, String str2, String str3, String str4, String str5, String str6, HashMap<String, String> hashMap) {
        OKLog.d("bundleicssdkservice", a + "---sendEpData");
        JDMtaUtils.sendEpData(context, str, str2, str3, str4, str5, str6, hashMap);
    }
}
