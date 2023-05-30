package com.jd.lib.productdetail.mainimage.old;

import androidx.lifecycle.Observer;
import com.handmark.pulltorefresh.library.PullToRefreshLoadMoreRecyclerView;
import com.jd.lib.productdetail.core.entitys.PdDpgListLayerInfo;
import com.jd.lib.productdetail.core.entitys.PdDpgListMoreInfo;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.util.List;

/* loaded from: classes15.dex */
public class g implements Observer<PdBaseProtocolLiveData.Result<PdDpgListMoreInfo>> {

    /* renamed from: g */
    public final /* synthetic */ PdMDpgLayerView f5153g;

    public g(PdMDpgLayerView pdMDpgLayerView) {
        this.f5153g = pdMDpgLayerView;
    }

    public /* synthetic */ void a() {
        PullToRefreshLoadMoreRecyclerView pullToRefreshLoadMoreRecyclerView;
        try {
            PdMDpgLayerView pdMDpgLayerView = this.f5153g;
            if (!pdMDpgLayerView.G) {
                PdMDpgLayerView.k(pdMDpgLayerView);
                PdMDpgLayerView.c(this.f5153g);
                PdMDpgLayerView.h(this.f5153g);
                try {
                    PdMDpgLayerView pdMDpgLayerView2 = this.f5153g;
                    if (pdMDpgLayerView2.u < pdMDpgLayerView2.f5015h && (pullToRefreshLoadMoreRecyclerView = pdMDpgLayerView2.f5016i) != null) {
                        pullToRefreshLoadMoreRecyclerView.setReachEndInvisible();
                    }
                } catch (Exception e2) {
                    ExceptionReporter.reportExceptionToBugly(e2);
                }
            }
        } catch (Exception unused) {
        }
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: b */
    public void onChanged(PdBaseProtocolLiveData.Result<PdDpgListMoreInfo> result) {
        PdDpgListMoreInfo pdDpgListMoreInfo;
        if (result == null || (pdDpgListMoreInfo = result.mData) == null) {
            return;
        }
        PdBaseProtocolLiveData.Result.DataStatus dataStatus = result.mStatus;
        if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.SUCCESS) {
            if ((pdDpgListMoreInfo instanceof PdDpgListMoreInfo) && pdDpgListMoreInfo.code == 0) {
                this.f5153g.f5016i.onRefreshComplete();
                this.f5153g.t++;
                List<PdDpgListLayerInfo.DetailBean> list = result.mData.detail;
                if (list != null && list.size() > 0) {
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        PdMDpgLayerView.d(this.f5153g, list.get(i2));
                    }
                    this.f5153g.u = list.size();
                    this.f5153g.E.post(new Runnable() { // from class: com.jd.lib.productdetail.mainimage.old.w
                        {
                            g.this = this;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            g.this.a();
                        }
                    });
                    return;
                }
                this.f5153g.u = 0;
                return;
            }
            this.f5153g.f5016i.setLoadingMoreFailed();
        } else if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FAIL) {
            this.f5153g.f5016i.setLoadingMoreFailed();
        }
    }
}
