package com.jd.lib.productdetail.mainimage.old;

import androidx.lifecycle.Observer;
import com.handmark.pulltorefresh.library.PullToRefreshLoadMoreRecyclerView;
import com.jd.lib.productdetail.core.entitys.PdDpgListLayerInfo;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;

/* loaded from: classes15.dex */
public class f implements Observer<PdBaseProtocolLiveData.Result<PdDpgListLayerInfo>> {

    /* renamed from: g */
    public final /* synthetic */ PdMDpgLayerView f5151g;

    public f(PdMDpgLayerView pdMDpgLayerView) {
        this.f5151g = pdMDpgLayerView;
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(PdBaseProtocolLiveData.Result<PdDpgListLayerInfo> result) {
        PdDpgListLayerInfo pdDpgListLayerInfo;
        PdDpgListLayerInfo.DetailBean.InfoBean infoBean;
        PdBaseProtocolLiveData.Result<PdDpgListLayerInfo> result2 = result;
        if (result2 == null || (pdDpgListLayerInfo = result2.mData) == null) {
            return;
        }
        PdBaseProtocolLiveData.Result.DataStatus dataStatus = result2.mStatus;
        if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.SUCCESS) {
            if ((pdDpgListLayerInfo instanceof PdDpgListLayerInfo) && pdDpgListLayerInfo.code == 0) {
                PdMDpgLayerView pdMDpgLayerView = this.f5151g;
                int i2 = PdMDpgLayerView.M;
                pdMDpgLayerView.a();
                PdDpgListLayerInfo.DetailBean detailBean = result2.mData.detail;
                PdMDpgLayerView.d(this.f5151g, detailBean);
                PdMDpgLayerView pdMDpgLayerView2 = this.f5151g;
                int i3 = pdMDpgLayerView2.t;
                if (i3 == 0 && detailBean != null && (infoBean = detailBean.info) != null) {
                    pdMDpgLayerView2.t = i3 + 1;
                    String str = infoBean.shopId;
                    pdMDpgLayerView2.v = str;
                    pdMDpgLayerView2.i(str);
                    return;
                }
                PullToRefreshLoadMoreRecyclerView pullToRefreshLoadMoreRecyclerView = pdMDpgLayerView2.f5016i;
                if (pullToRefreshLoadMoreRecyclerView != null) {
                    pullToRefreshLoadMoreRecyclerView.onRefreshComplete();
                    return;
                }
                return;
            }
            PdMDpgLayerView pdMDpgLayerView3 = this.f5151g;
            int i4 = PdMDpgLayerView.M;
            pdMDpgLayerView3.f(true);
        } else if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FAIL) {
            PdMDpgLayerView pdMDpgLayerView4 = this.f5151g;
            int i5 = PdMDpgLayerView.M;
            pdMDpgLayerView4.f(true);
        }
    }
}
