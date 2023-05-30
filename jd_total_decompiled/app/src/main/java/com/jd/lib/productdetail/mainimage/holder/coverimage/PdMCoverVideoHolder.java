package com.jd.lib.productdetail.mainimage.holder.coverimage;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBuinessUnitMainImageBizDataEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.bean.GoToBigEntnty;
import com.jd.lib.productdetail.mainimage.holder.video.PdMVideoViewHolder;
import com.jd.lib.productdetail.mainimage.old.k;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jd.lib.productdetail.mainimage.presenter.PdMainStaticData;
import com.jd.lib.productdetail.mainimage.presenter.PdVideoContainer;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMCoverVideoHolder extends PdMVideoViewHolder {
    public View N;
    public SimpleDraweeView O;
    public SimpleDraweeView P;
    public SimpleDraweeView Q;
    public JDDisplayImageOptions R;

    /* loaded from: classes15.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PdVideoContainer pdVideoContainer;
            PdVideoContainer pdVideoContainer2;
            k kVar;
            PdMCoverVideoHolder pdMCoverVideoHolder = PdMCoverVideoHolder.this;
            WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = pdMCoverVideoHolder.r;
            if (wareBusinessMagicHeadPicInfoEntity != null) {
                pdMCoverVideoHolder.l(wareBusinessMagicHeadPicInfoEntity.anchorType, 0);
            }
            PdMCoverVideoHolder pdMCoverVideoHolder2 = PdMCoverVideoHolder.this;
            PdMainImagePresenter pdMainImagePresenter = pdMCoverVideoHolder2.f4654n;
            if (pdMainImagePresenter != null && (pdVideoContainer2 = pdMainImagePresenter.pdVideoContainer) != null && (kVar = pdMCoverVideoHolder2.E) != null && kVar.o) {
                pdVideoContainer2.isClickVideoPlay.setValue(Boolean.TRUE);
                PdMCoverVideoHolder.this.O();
                return;
            }
            k kVar2 = pdMCoverVideoHolder2.E;
            if (kVar2 != null && kVar2.C()) {
                PdMCoverVideoHolder.this.E.y(false);
                PdMCoverVideoHolder.this.m(true, true, false);
                return;
            }
            PdMainImagePresenter pdMainImagePresenter2 = PdMCoverVideoHolder.this.f4654n;
            if (pdMainImagePresenter2 != null && (pdVideoContainer = pdMainImagePresenter2.pdVideoContainer) != null) {
                pdVideoContainer.isClickVideoPlay.setValue(Boolean.TRUE);
            }
            PdMCoverVideoHolder.this.b(false);
        }
    }

    /* loaded from: classes15.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PdMCoverVideoHolder pdMCoverVideoHolder = PdMCoverVideoHolder.this;
            WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = pdMCoverVideoHolder.r;
            if (wareBusinessMagicHeadPicInfoEntity != null) {
                pdMCoverVideoHolder.l(wareBusinessMagicHeadPicInfoEntity.anchorType, 1);
                PdMCoverVideoHolder pdMCoverVideoHolder2 = PdMCoverVideoHolder.this;
                pdMCoverVideoHolder2.U(pdMCoverVideoHolder2.r.bizData.imageComponent.get(1).index);
            }
        }
    }

    /* loaded from: classes15.dex */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PdMCoverVideoHolder pdMCoverVideoHolder = PdMCoverVideoHolder.this;
            WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = pdMCoverVideoHolder.r;
            if (wareBusinessMagicHeadPicInfoEntity != null) {
                pdMCoverVideoHolder.l(wareBusinessMagicHeadPicInfoEntity.anchorType, 2);
                PdMCoverVideoHolder pdMCoverVideoHolder2 = PdMCoverVideoHolder.this;
                pdMCoverVideoHolder2.U(pdMCoverVideoHolder2.r.bizData.imageComponent.get(2).index);
            }
        }
    }

    public PdMCoverVideoHolder(@NonNull View view, View view2) {
        super(view, view2);
        JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
        this.R = jDDisplayImageOptions;
        jDDisplayImageOptions.setRoundingParams(RoundingParams.fromCornersRadii(PDUtils.dip2px(12.0f), PDUtils.dip2px(12.0f), PDUtils.dip2px(12.0f), PDUtils.dip2px(12.0f)));
    }

    public void U(int i2) {
        if (this.f4654n != null) {
            GoToBigEntnty goToBigEntnty = new GoToBigEntnty();
            goToBigEntnty.fromBigImage = true;
            goToBigEntnty.autoPlay = false;
            goToBigEntnty.pureMode = false;
            goToBigEntnty.position = i2;
            this.f4654n.toBigEntntyMutableLiveData.setValue(goToBigEntnty);
        }
    }

    public final boolean V() {
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        List<WareBuinessUnitMainImageBizDataEntity.ImageComponentEntity> list;
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = this.r;
        return (wareBusinessMagicHeadPicInfoEntity == null || (wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity.bizData) == null || (list = wareBuinessUnitMainImageBizDataEntity.imageComponent) == null || list.size() <= 2) ? false : true;
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.video.PdMVideoViewHolder, com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void d(View view) {
        this.I = (ImageView) view.findViewById(R.id.pd_topimage_playbuttonimage);
        this.H = (FrameLayout) view.findViewById(R.id.lib_pd_hoder_topimage_video_container);
        this.N = view.findViewById(R.id.lib_pd_atlas_image_root_Layout);
        this.O = (SimpleDraweeView) view.findViewById(R.id.lib_pd_atlas_image_1);
        this.P = (SimpleDraweeView) view.findViewById(R.id.lib_pd_atlas_image_2);
        this.Q = (SimpleDraweeView) view.findViewById(R.id.lib_pd_atlas_image_3);
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void n() {
        if (V()) {
            this.N.setVisibility(0);
            if (!this.f4654n.getMainImageParams().isDark) {
                this.N.setBackgroundColor(PDUtils.parseColor(JDDarkUtil.COLOR_F6F6F6));
            } else {
                this.N.setBackgroundColor(PDUtils.parseColor("#0A0A0A"));
            }
            if (this.r.bizData.imageComponent.get(0) != null) {
                JDImageLoader.display(this.r.bizData.imageComponent.get(0).small, this.O, null);
                this.O.setOnClickListener(new a());
            }
            if (this.r.bizData.imageComponent.get(1) != null) {
                JDImageLoader.display(this.r.bizData.imageComponent.get(1).small, this.P, this.R);
                this.P.setOnClickListener(new b());
            }
            if (this.r.bizData.imageComponent.get(2) != null) {
                JDImageLoader.display(this.r.bizData.imageComponent.get(2).small, this.Q, this.R);
                this.Q.setOnClickListener(new c());
                return;
            }
            return;
        }
        this.N.setVisibility(8);
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public int v() {
        if (V()) {
            return super.v() - PDUtils.dip2px(this.f4649i, PdMainStaticData.ANCHOR_LAYOUT_HEIGHT);
        }
        return super.v();
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public boolean y() {
        return V();
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void z() {
        if (V()) {
            return;
        }
        super.z();
    }
}
