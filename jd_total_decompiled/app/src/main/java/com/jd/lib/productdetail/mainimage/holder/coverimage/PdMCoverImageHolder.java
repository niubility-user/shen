package com.jd.lib.productdetail.mainimage.holder.coverimage;

import android.view.View;
import androidx.annotation.NonNull;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBuinessUnitMainImageBizDataEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.bean.GoToBigEntnty;
import com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder;
import com.jd.lib.productdetail.mainimage.presenter.PdMainStaticData;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMCoverImageHolder extends PdMainImageBaseHolder {
    public View B;
    public SimpleDraweeView C;
    public SimpleDraweeView D;
    public SimpleDraweeView E;
    public JDDisplayImageOptions F;

    /* loaded from: classes15.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PdMCoverImageHolder pdMCoverImageHolder = PdMCoverImageHolder.this;
            WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = pdMCoverImageHolder.r;
            if (wareBusinessMagicHeadPicInfoEntity != null) {
                pdMCoverImageHolder.l(wareBusinessMagicHeadPicInfoEntity.anchorType, 0);
            }
            PdMCoverImageHolder pdMCoverImageHolder2 = PdMCoverImageHolder.this;
            pdMCoverImageHolder2.G(pdMCoverImageHolder2.r.bizData.imageComponent.get(0).index);
        }
    }

    /* loaded from: classes15.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PdMCoverImageHolder pdMCoverImageHolder = PdMCoverImageHolder.this;
            WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = pdMCoverImageHolder.r;
            if (wareBusinessMagicHeadPicInfoEntity != null) {
                pdMCoverImageHolder.l(wareBusinessMagicHeadPicInfoEntity.anchorType, 1);
            }
            PdMCoverImageHolder pdMCoverImageHolder2 = PdMCoverImageHolder.this;
            pdMCoverImageHolder2.G(pdMCoverImageHolder2.r.bizData.imageComponent.get(1).index);
        }
    }

    /* loaded from: classes15.dex */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PdMCoverImageHolder pdMCoverImageHolder = PdMCoverImageHolder.this;
            WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = pdMCoverImageHolder.r;
            if (wareBusinessMagicHeadPicInfoEntity != null) {
                pdMCoverImageHolder.l(wareBusinessMagicHeadPicInfoEntity.anchorType, 2);
            }
            PdMCoverImageHolder pdMCoverImageHolder2 = PdMCoverImageHolder.this;
            pdMCoverImageHolder2.G(pdMCoverImageHolder2.r.bizData.imageComponent.get(2).index);
        }
    }

    public PdMCoverImageHolder(@NonNull View view, View view2) {
        super(view, view2);
        JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
        this.F = jDDisplayImageOptions;
        jDDisplayImageOptions.setRoundingParams(RoundingParams.fromCornersRadii(PDUtils.dip2px(12.0f), PDUtils.dip2px(12.0f), PDUtils.dip2px(12.0f), PDUtils.dip2px(12.0f)));
    }

    public void G(int i2) {
        if (this.f4654n != null) {
            GoToBigEntnty goToBigEntnty = new GoToBigEntnty();
            goToBigEntnty.fromBigImage = true;
            goToBigEntnty.autoPlay = false;
            goToBigEntnty.pureMode = false;
            goToBigEntnty.position = i2;
            this.f4654n.toBigEntntyMutableLiveData.setValue(goToBigEntnty);
        }
    }

    public final boolean H() {
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        List<WareBuinessUnitMainImageBizDataEntity.ImageComponentEntity> list;
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = this.r;
        return (wareBusinessMagicHeadPicInfoEntity == null || (wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity.bizData) == null || (list = wareBuinessUnitMainImageBizDataEntity.imageComponent) == null || list.size() <= 2) ? false : true;
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void d(View view) {
        this.B = view.findViewById(R.id.lib_pd_atlas_image_root_Layout);
        this.C = (SimpleDraweeView) view.findViewById(R.id.lib_pd_atlas_image_1);
        this.D = (SimpleDraweeView) view.findViewById(R.id.lib_pd_atlas_image_2);
        this.E = (SimpleDraweeView) view.findViewById(R.id.lib_pd_atlas_image_3);
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void n() {
        if (H()) {
            this.B.setVisibility(0);
            if (!this.f4654n.getMainImageParams().isDark) {
                this.B.setBackgroundColor(PDUtils.parseColor(JDDarkUtil.COLOR_F6F6F6));
            } else {
                this.B.setBackgroundColor(PDUtils.parseColor("#0A0A0A"));
            }
            if (this.r.bizData.imageComponent.get(0) != null) {
                JDImageLoader.display(this.r.bizData.imageComponent.get(0).small, this.C, this.F);
                this.C.setOnClickListener(new a());
            }
            if (this.r.bizData.imageComponent.get(1) != null) {
                JDImageLoader.display(this.r.bizData.imageComponent.get(1).small, this.D, this.F);
                this.D.setOnClickListener(new b());
            }
            if (this.r.bizData.imageComponent.get(2) != null) {
                JDImageLoader.display(this.r.bizData.imageComponent.get(2).small, this.E, this.F);
                this.E.setOnClickListener(new c());
                return;
            }
            return;
        }
        this.B.setVisibility(8);
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public int v() {
        if (H()) {
            return super.v() - PDUtils.dip2px(this.f4649i, PdMainStaticData.ANCHOR_LAYOUT_HEIGHT);
        }
        return super.v();
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public boolean y() {
        return H();
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void z() {
        if (H()) {
            return;
        }
        super.z();
    }
}
