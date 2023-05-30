package com.jd.lib.productdetail.mainimage.holder.gyroscope;

import android.graphics.Bitmap;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBuinessUnitMainImageBizDataEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicPicItems;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessWareImageEntity;
import com.jd.lib.productdetail.core.utils.OpenAppUtils;
import com.jd.lib.productdetail.core.utils.PDBaseDeepLinkHelper;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder;
import com.jd.lib.productdetail.mainimage.holder.gyroscope.a;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.utils.JDImageUtils;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMImageGyroscopeViewHolder extends PdMainImageBaseHolder {
    public ImageView B;
    public ImageView C;
    public PdMImageGyroscopeImageView D;
    public float E;
    public Handler F;

    /* loaded from: classes15.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g */
        public final /* synthetic */ WareBusinessMagicPicItems f4864g;

        public a(WareBusinessMagicPicItems wareBusinessMagicPicItems) {
            PdMImageGyroscopeViewHolder.this = r1;
            this.f4864g = wareBusinessMagicPicItems;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PDUtils.repeatClick()) {
                WareBusinessMagicPicItems wareBusinessMagicPicItems = this.f4864g;
                if (wareBusinessMagicPicItems != null) {
                    PdMImageGyroscopeViewHolder.this.l(wareBusinessMagicPicItems.anchorType, -100);
                }
                WareBusinessMagicPicItems wareBusinessMagicPicItems2 = this.f4864g;
                if (wareBusinessMagicPicItems2 == null || TextUtils.isEmpty(wareBusinessMagicPicItems2.jumpUrl)) {
                    return;
                }
                if (this.f4864g.jumpUrl.startsWith("http")) {
                    PDBaseDeepLinkHelper.gotoMWithUrl(view.getContext(), this.f4864g.jumpUrl);
                } else {
                    OpenAppUtils.openAppForInner(view.getContext(), this.f4864g.jumpUrl);
                }
            }
        }
    }

    /* loaded from: classes15.dex */
    public class b implements View.OnClickListener {

        /* renamed from: g */
        public final /* synthetic */ WareBusinessMagicPicItems f4866g;

        public b(WareBusinessMagicPicItems wareBusinessMagicPicItems) {
            PdMImageGyroscopeViewHolder.this = r1;
            this.f4866g = wareBusinessMagicPicItems;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PDUtils.repeatClick()) {
                WareBusinessMagicPicItems wareBusinessMagicPicItems = this.f4866g;
                if (wareBusinessMagicPicItems != null) {
                    PdMImageGyroscopeViewHolder.this.l(wareBusinessMagicPicItems.anchorType, -100);
                }
                WareBusinessMagicPicItems wareBusinessMagicPicItems2 = this.f4866g;
                if (wareBusinessMagicPicItems2 == null || TextUtils.isEmpty(wareBusinessMagicPicItems2.jumpUrl)) {
                    return;
                }
                if (this.f4866g.jumpUrl.startsWith("http")) {
                    PDBaseDeepLinkHelper.gotoMWithUrl(view.getContext(), this.f4866g.jumpUrl);
                } else {
                    OpenAppUtils.openAppForInner(view.getContext(), this.f4866g.jumpUrl);
                }
            }
        }
    }

    public PdMImageGyroscopeViewHolder(@NonNull View view, View view2) {
        super(view, view2);
        this.F = new Handler(Looper.getMainLooper());
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public boolean A() {
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        WareBuinessUnitMainImageBizDataEntity.PdArVrBizData pdArVrBizData;
        List<WareBusinessMagicPicItems> list;
        ImageView imageView;
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = this.r;
        if (wareBusinessMagicHeadPicInfoEntity != null && (wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity.bizData) != null && (pdArVrBizData = wareBuinessUnitMainImageBizDataEntity.arVrBizData) != null && (list = pdArVrBizData.items) != null && list.size() == 1 && (imageView = this.B) != null && imageView.getVisibility() == 0 && this.C.getVisibility() == 8) {
            WareBusinessMagicPicItems wareBusinessMagicPicItems = this.r.bizData.arVrBizData.items.get(0);
            if (wareBusinessMagicPicItems != null) {
                l(wareBusinessMagicPicItems.anchorType, -100);
            }
            if (wareBusinessMagicPicItems != null && !TextUtils.isEmpty(wareBusinessMagicPicItems.jumpUrl)) {
                if (wareBusinessMagicPicItems.jumpUrl.startsWith("http")) {
                    PdMainImagePresenter pdMainImagePresenter = this.f4654n;
                    if (pdMainImagePresenter != null) {
                        pdMainImagePresenter.jumpToPage.setValue(Boolean.TRUE);
                    }
                    PDBaseDeepLinkHelper.gotoMWithUrl(this.B.getContext(), wareBusinessMagicPicItems.jumpUrl);
                    return true;
                }
                PdMainImagePresenter pdMainImagePresenter2 = this.f4654n;
                if (pdMainImagePresenter2 != null) {
                    pdMainImagePresenter2.jumpToPage.setValue(Boolean.TRUE);
                }
                OpenAppUtils.openAppForInner(this.B.getContext(), wareBusinessMagicPicItems.jumpUrl);
                return true;
            }
        }
        return false;
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void E() {
        com.jd.lib.productdetail.mainimage.holder.gyroscope.a aVar = a.C0157a.a;
        SensorManager sensorManager = aVar.f4869h;
        if (sensorManager != null) {
            sensorManager.unregisterListener(aVar);
            aVar.f4869h = null;
        }
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void F() {
        a.C0157a.a.a((BaseActivity) this.f4649i);
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void a(boolean z) {
        this.f4650j = z;
        if (z) {
            a.C0157a.a.a((BaseActivity) this.f4649i);
            return;
        }
        com.jd.lib.productdetail.mainimage.holder.gyroscope.a aVar = a.C0157a.a;
        SensorManager sensorManager = aVar.f4869h;
        if (sensorManager != null) {
            sensorManager.unregisterListener(aVar);
            aVar.f4869h = null;
        }
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void d(View view) {
        this.B = (ImageView) view.findViewById(R.id.pd_topimage_gif_btn_1);
        this.C = (ImageView) view.findViewById(R.id.pd_topimage_gif_btn_2);
        this.D = (PdMImageGyroscopeImageView) view.findViewById(R.id.pd_topimage_imageView_gyroscope);
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void n() {
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        WareBuinessUnitMainImageBizDataEntity.PdArVrBizData pdArVrBizData;
        List<WareBusinessMagicPicItems> list;
        WareBusinessWareImageEntity wareBusinessWareImageEntity;
        if (this.r != null) {
            this.B.setVisibility(8);
            this.C.setVisibility(8);
            float f2 = this.f4654n.appImageWidth;
            this.E = f2;
            this.D.e((int) (f2 * 1.2f));
            this.D.setOnClickListener(this);
            this.D.setVisibility(0);
            this.f4652l.setVisibility(4);
            WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = this.r;
            String str = (wareBusinessMagicHeadPicInfoEntity == null || (wareBusinessWareImageEntity = wareBusinessMagicHeadPicInfoEntity.wareImage) == null) ? "" : wareBusinessWareImageEntity.small;
            JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
            jDDisplayImageOptions.setBitmapConfig(Bitmap.Config.ARGB_8888);
            JDImageLoader.getBitmap(str, jDDisplayImageOptions, new e(this));
            WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity2 = this.r;
            if (wareBusinessMagicHeadPicInfoEntity2 == null || (wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity2.bizData) == null || (pdArVrBizData = wareBuinessUnitMainImageBizDataEntity.arVrBizData) == null || (list = pdArVrBizData.items) == null) {
                return;
            }
            if (list.size() > 0) {
                this.B.setVisibility(0);
                WareBusinessMagicPicItems wareBusinessMagicPicItems = this.r.bizData.arVrBizData.items.get(0);
                JDImageUtils.displayImage(wareBusinessMagicPicItems.icon, this.B);
                this.B.setOnClickListener(new a(wareBusinessMagicPicItems));
            }
            if (this.r.bizData.arVrBizData.items.size() > 1) {
                this.C.setVisibility(0);
                WareBusinessMagicPicItems wareBusinessMagicPicItems2 = this.r.bizData.arVrBizData.items.get(1);
                JDImageUtils.displayImage(wareBusinessMagicPicItems2.icon, this.C);
                this.C.setOnClickListener(new b(wareBusinessMagicPicItems2));
            }
        }
    }
}
