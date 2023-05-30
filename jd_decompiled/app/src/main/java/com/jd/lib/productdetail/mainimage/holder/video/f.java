package com.jd.lib.productdetail.mainimage.holder.video;

import android.view.View;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdVideoSymbolList;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMaterVideoInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessTopVideoControl;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.core.utils.VideoPlayUtil;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jd.lib.productdetail.mainimage.presenter.PdVideoContainer;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.aac.util.SyncEventBus;
import java.util.List;

/* loaded from: classes15.dex */
public class f implements View.OnClickListener {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ PdMVideoViewHolder f4972g;

    /* loaded from: classes15.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jd.lib.productdetail.mainimage.old.k kVar;
            PdMVideoViewHolder pdMVideoViewHolder = f.this.f4972g;
            if (pdMVideoViewHolder.f4651k || (kVar = pdMVideoViewHolder.E) == null) {
                return;
            }
            kVar.H(true);
        }
    }

    public f(PdMVideoViewHolder pdMVideoViewHolder) {
        this.f4972g = pdMVideoViewHolder;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        String str;
        List<PdVideoSymbolList> list;
        WareBusinessTopVideoControl wareBusinessTopVideoControl = this.f4972g.r.bizData.videoBizData.videoControl;
        if (wareBusinessTopVideoControl.optimize) {
            str = wareBusinessTopVideoControl.autoPlay ? "2" : "1";
        } else {
            str = "0";
        }
        String str2 = str + CartConstant.KEY_YB_INFO_LINK + this.f4972g.q.position;
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("videoid", (Object) this.f4972g.M());
        WareBusinessMaterVideoInfo wareBusinessMaterVideoInfo = this.f4972g.r.bizData.videoBizData.videoControl.masterVideo;
        if (wareBusinessMaterVideoInfo != null && (list = wareBusinessMaterVideoInfo.videoSymbolList) != null && !list.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            for (int i2 = 0; i2 < this.f4972g.r.bizData.videoBizData.videoControl.masterVideo.videoSymbolList.size(); i2++) {
                PdVideoSymbolList pdVideoSymbolList = this.f4972g.r.bizData.videoBizData.videoControl.masterVideo.videoSymbolList.get(i2);
                if (pdVideoSymbolList != null) {
                    sb.append(pdVideoSymbolList.symbolId);
                    sb2.append(pdVideoSymbolList.name);
                    if (i2 != this.f4972g.r.bizData.videoBizData.videoControl.masterVideo.videoSymbolList.size() - 1) {
                        sb.append(CartConstant.KEY_YB_INFO_LINK);
                        sb2.append(CartConstant.KEY_YB_INFO_LINK);
                    }
                }
            }
            jDJSONObject.put("lableid", (Object) sb);
            jDJSONObject.put("lablename", (Object) sb2);
        }
        PdMainImagePresenter pdMainImagePresenter = this.f4972g.f4654n;
        if (pdMainImagePresenter != null && pdMainImagePresenter.getMainImageParams() != null) {
            PDUtils.setFloorPriceJson(jDJSONObject, this.f4972g.f4654n.getMainImageParams().floorPriceMta);
            PDUtils.setCardInfo(jDJSONObject, this.f4972g.f4654n.getMainImageParams().brandId, "bpMainImage", this.f4972g.H);
        }
        PdMVideoViewHolder pdMVideoViewHolder = this.f4972g;
        PDUtils.setFloorCid(jDJSONObject, pdMVideoViewHolder.B, pdMVideoViewHolder.C, pdMVideoViewHolder.D);
        this.f4972g.f4654n.mtaClick("Productdetail_MainPicVideo", jDJSONObject.toJSONString(), str2, true);
        PdMVideoViewHolder pdMVideoViewHolder2 = this.f4972g;
        WareBusinessMaterVideoInfo wareBusinessMaterVideoInfo2 = pdMVideoViewHolder2.r.bizData.videoBizData.videoControl.masterVideo;
        if (wareBusinessMaterVideoInfo2 != null && wareBusinessMaterVideoInfo2.playBackFlag) {
            pdMVideoViewHolder2.f4654n.mtaClick("Productdetail_PhotoVideoReply", "", wareBusinessMaterVideoInfo2.videoId);
        }
        if (this.f4972g.r.bizData.videoBizData.videoControl.isHasMasterVideo()) {
            com.jd.lib.productdetail.mainimage.old.k kVar = this.f4972g.E;
            if (kVar != null && kVar.z()) {
                PdMVideoViewHolder pdMVideoViewHolder3 = this.f4972g;
                if (pdMVideoViewHolder3.E.o) {
                    PdVideoContainer pdVideoContainer = pdMVideoViewHolder3.f4654n.pdVideoContainer;
                    if (pdVideoContainer != null) {
                        pdVideoContainer.isClickVideoPlay.setValue(Boolean.TRUE);
                    }
                    this.f4972g.O();
                    return;
                }
                return;
            }
            PdMVideoViewHolder pdMVideoViewHolder4 = this.f4972g;
            WareBusinessTopVideoControl wareBusinessTopVideoControl2 = pdMVideoViewHolder4.r.bizData.videoBizData.videoControl;
            WareBusinessMaterVideoInfo wareBusinessMaterVideoInfo3 = wareBusinessTopVideoControl2.masterVideo;
            if (wareBusinessTopVideoControl2.optimize) {
                pdMVideoViewHolder4.G = false;
                PdVideoContainer pdVideoContainer2 = this.f4972g.f4654n.pdVideoContainer;
                if (pdVideoContainer2 != null) {
                    pdVideoContainer2.isClickVideoPlay.setValue(Boolean.TRUE);
                }
                PdMVideoViewHolder pdMVideoViewHolder5 = this.f4972g;
                pdMVideoViewHolder5.b(pdMVideoViewHolder5.G);
                PdMVideoViewHolder pdMVideoViewHolder6 = this.f4972g;
                a aVar = new a();
                pdMVideoViewHolder6.getClass();
                SyncEventBus.postToMainThread(aVar, 300);
            } else if (wareBusinessMaterVideoInfo3 != null && !pdMVideoViewHolder4.f4651k) {
                new VideoPlayUtil(pdMVideoViewHolder4.f4649i).play(new g(pdMVideoViewHolder4, wareBusinessMaterVideoInfo3));
            }
        }
    }
}
