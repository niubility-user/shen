package com.jd.lib.productdetail.mainimage.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessWareImageEntity;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.bean.PdMainImagePagerEntity;
import com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder;
import com.jd.lib.productdetail.mainimage.holder.ask.PdMAskView;
import com.jd.lib.productdetail.mainimage.holder.ask.PdMAskViewHolder;
import com.jd.lib.productdetail.mainimage.holder.cf.PdMCfViewHolder;
import com.jd.lib.productdetail.mainimage.holder.comment.PdMCommentNewViewHolder;
import com.jd.lib.productdetail.mainimage.holder.comment.PdMImageCommentNewRootView;
import com.jd.lib.productdetail.mainimage.holder.coverimage.PdMCoverImageHolder;
import com.jd.lib.productdetail.mainimage.holder.coverimage.PdMCoverVideoHolder;
import com.jd.lib.productdetail.mainimage.holder.dpg.PdMDPGViewHolder;
import com.jd.lib.productdetail.mainimage.holder.dym.PdMCooperViewHolder;
import com.jd.lib.productdetail.mainimage.holder.gif.PdMGifViewHolder;
import com.jd.lib.productdetail.mainimage.holder.gift.PdMImageGiftView;
import com.jd.lib.productdetail.mainimage.holder.gift.PdMImageGiftViewHolder;
import com.jd.lib.productdetail.mainimage.holder.gyroscope.PdMImageGyroscopeViewHolder;
import com.jd.lib.productdetail.mainimage.holder.icons.PdMIconViewHolder;
import com.jd.lib.productdetail.mainimage.holder.iconsnew.PdMIconNewViewHolder;
import com.jd.lib.productdetail.mainimage.holder.recommend.PdMImageRecommendNewView;
import com.jd.lib.productdetail.mainimage.holder.recommend.PdMImageRecommendNewViewHolder;
import com.jd.lib.productdetail.mainimage.holder.recommend.PdMImageRecommendView;
import com.jd.lib.productdetail.mainimage.holder.recommend.PdMImageRecommendViewHolder;
import com.jd.lib.productdetail.mainimage.holder.suit.PdTopImageSuitView;
import com.jd.lib.productdetail.mainimage.holder.suit.PdTopImageSuitViewHolder;
import com.jd.lib.productdetail.mainimage.holder.video.PdMVideoViewHolder;
import com.jd.lib.productdetail.mainimage.holder.ypsms.PdMYpsmsView;
import com.jd.lib.productdetail.mainimage.holder.ypsms.PdMYpsmsViewHolder;
import com.jd.lib.productdetail.mainimage.old.k;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jd.lib.productdetail.mainimage.view.PdImageFromType;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMainImageAdapter extends RecyclerView.Adapter<PdMainImageBaseHolder> {
    public PdMainImagePresenter a;
    public WareBusinessUnitMainImageEntity b;

    /* renamed from: c  reason: collision with root package name */
    public List<WareBusinessMagicHeadPicInfoEntity> f4548c;
    public Context d;

    /* renamed from: e  reason: collision with root package name */
    public PdMVideoViewHolder f4549e;

    public PdMainImageAdapter(PdMainImagePresenter pdMainImagePresenter) {
        this.a = pdMainImagePresenter;
    }

    public PdMainImagePagerEntity a(int i2) {
        List<WareBusinessMagicHeadPicInfoEntity> list;
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity;
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = this.b;
        if (wareBusinessUnitMainImageEntity == null || wareBusinessUnitMainImageEntity.extMap == null || (list = this.f4548c) == null || i2 <= -1 || i2 >= list.size() || (wareBusinessMagicHeadPicInfoEntity = this.f4548c.get(i2)) == null) {
            return null;
        }
        PdMainImagePagerEntity pdMainImagePagerEntity = new PdMainImagePagerEntity();
        pdMainImagePagerEntity.position = i2;
        WareBusinessWareImageEntity wareBusinessWareImageEntity = wareBusinessMagicHeadPicInfoEntity.wareImage;
        if (wareBusinessWareImageEntity != null) {
            pdMainImagePagerEntity.imageUrl = wareBusinessWareImageEntity.small;
        }
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity2 = this.b;
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity = wareBusinessUnitMainImageEntity2.extMap;
        pdMainImagePagerEntity.skuId = extMapEntity.skuId;
        pdMainImagePagerEntity.magicHeadPicData = wareBusinessMagicHeadPicInfoEntity;
        pdMainImagePagerEntity.mIsDefault = extMapEntity.mIsDefault;
        pdMainImagePagerEntity.topImageEntity = wareBusinessUnitMainImageEntity2;
        return pdMainImagePagerEntity;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<WareBusinessMagicHeadPicInfoEntity> list = this.f4548c;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        if (this.f4548c.get(i2).iViewType > 0) {
            return -821837990;
        }
        if (TextUtils.isEmpty(this.f4548c.get(i2).anchorType)) {
            return -2026296098;
        }
        return this.f4548c.get(i2).anchorType.hashCode();
    }

    public void h(WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity, Context context) {
        PdMainImagePresenter pdMainImagePresenter;
        k kVar;
        PdMainImagePresenter pdMainImagePresenter2;
        this.d = context;
        PdMVideoViewHolder pdMVideoViewHolder = this.f4549e;
        if (pdMVideoViewHolder != null && (pdMainImagePresenter = this.a) != null && pdMainImagePresenter.imageFromType != PdImageFromType.PRODUCTDETAIL_MINI && (kVar = pdMVideoViewHolder.E) != null && (pdMainImagePresenter2 = pdMVideoViewHolder.f4654n) != null) {
            kVar.n(pdMainImagePresenter2.getMainImageParams().mManagerKey);
            pdMVideoViewHolder.E = null;
        }
        this.b = wareBusinessUnitMainImageEntity;
        this.f4548c = wareBusinessUnitMainImageEntity.magicHeadPicInfo;
        notifyDataSetChanged();
        this.a.changeSku(context);
    }

    public boolean l(int i2) {
        return i2 == 3001893 || i2 == 93043258 || i2 == 3001922 || i2 == 3001942 || i2 == 3001944 || i2 == 112439551 || i2 == 49203567 || i2 == -1410592619 || i2 == 93064423;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull PdMainImageBaseHolder pdMainImageBaseHolder, int i2) {
        try {
            pdMainImageBaseHolder.i(a(i2));
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:70:0x016f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public PdMainImageBaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        PdMainImageBaseHolder pdTopImageSuitViewHolder;
        PdMainImageBaseHolder pdMainImageBaseHolder;
        PdMainImageBaseHolder pdMainImageBaseHolder2 = null;
        if (this.f4548c != null) {
            LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
            View inflate = from.inflate(R.layout.lib_pd_mainimage_holder_base_shadow, viewGroup, false);
            if (i2 == -821837990) {
                pdTopImageSuitViewHolder = new PdMCooperViewHolder(inflate, from.inflate(R.layout.lib_pd_mainimage_holder_base, viewGroup, false));
            } else {
                if (i2 == 112202875) {
                    if (this.f4549e == null) {
                        this.f4549e = new PdMVideoViewHolder(inflate, from.inflate(R.layout.lib_pd_mainimage_holder_topimage_item_video, viewGroup, false));
                    }
                    pdMainImageBaseHolder = this.f4549e;
                } else if (i2 == 112439551) {
                    pdTopImageSuitViewHolder = new PdMDPGViewHolder(inflate, from.inflate(R.layout.lib_pd_mainimage_holder_topimage_item_dpg, viewGroup, false));
                } else if (l(i2)) {
                    pdTopImageSuitViewHolder = new PdMGifViewHolder(inflate, from.inflate(R.layout.lib_pd_mainimage_holder_topimage_item_gif, viewGroup, false));
                } else if (i2 == 679133821) {
                    pdTopImageSuitViewHolder = new PdMImageGyroscopeViewHolder(inflate, from.inflate(R.layout.lib_pd_mainimage_holder_topimage_item_gyroscope, viewGroup, false));
                } else if (i2 == -778693460) {
                    pdTopImageSuitViewHolder = new PdMCfViewHolder(inflate, from.inflate(R.layout.lib_pd_mainimage_holder_topimage_item_newcf, viewGroup, false));
                } else if (i2 == 3226745) {
                    pdTopImageSuitViewHolder = new PdMIconViewHolder(inflate, from.inflate(R.layout.lib_pd_mainimage_holder_topimage_item_icon, viewGroup, false));
                } else if (i2 == 1638757991) {
                    pdTopImageSuitViewHolder = new PdMIconNewViewHolder(inflate, from.inflate(R.layout.lib_pd_mainimage_holder_topimage_item_icon_new, viewGroup, false));
                } else if (i2 == 3541773 || i2 == 1317326115) {
                    pdTopImageSuitViewHolder = new PdTopImageSuitViewHolder(inflate, (PdTopImageSuitView) LayoutInflater.from(this.d).inflate(R.layout.lib_pd_mainimage_item_suit_view, viewGroup, false));
                } else if (i2 == 899134145) {
                    pdTopImageSuitViewHolder = new PdMCommentNewViewHolder(inflate, (PdMImageCommentNewRootView) from.inflate(R.layout.lib_pd_mainimage_holder_topimage_item_comment_new_root, viewGroup, false));
                } else if (i2 == 115196642) {
                    pdTopImageSuitViewHolder = new PdMYpsmsViewHolder(inflate, (PdMYpsmsView) from.inflate(R.layout.lib_pd_mainimage_holder_topimage_item_ypsms_layout, viewGroup, false));
                } else if (i2 == -973913164) {
                    pdTopImageSuitViewHolder = new PdMImageRecommendViewHolder(inflate, (PdMImageRecommendView) from.inflate(R.layout.lib_pd_mainimage_holder_topimage_item_recommend_layout, viewGroup, false));
                } else if (i2 == 3600) {
                    pdTopImageSuitViewHolder = new PdMAskViewHolder(inflate, (PdMAskView) from.inflate(R.layout.lib_pd_mainimage_holder_topimage_item_ask_layout, viewGroup, false));
                } else if (i2 == -337200583) {
                    pdTopImageSuitViewHolder = new PdMImageRecommendNewViewHolder(inflate, (PdMImageRecommendNewView) from.inflate(R.layout.lib_pd_mainimage_holder_topimage_item_recommend_new_layout, viewGroup, false));
                } else if (i2 == 3172656) {
                    pdTopImageSuitViewHolder = new PdMImageGiftViewHolder(inflate, (PdMImageGiftView) from.inflate(R.layout.lib_pd_mainimage_holder_topimage_item_gift_layout, viewGroup, false));
                } else if (i2 == 93144203) {
                    pdTopImageSuitViewHolder = new PdMCoverImageHolder(inflate, from.inflate(R.layout.lib_pd_mainimage_holder_topimage_item_atlas_layout, viewGroup, false));
                } else if (i2 == 1715207856) {
                    pdTopImageSuitViewHolder = new PdMCoverVideoHolder(inflate, from.inflate(R.layout.lib_pd_mainimage_holder_topimage_item_atlas_layout, viewGroup, false));
                } else {
                    pdMainImageBaseHolder = new PdMainImageBaseHolder(inflate, null);
                }
                pdMainImageBaseHolder2 = pdMainImageBaseHolder;
                if (pdMainImageBaseHolder2 != null) {
                    try {
                        pdMainImageBaseHolder2.j(this.a);
                    } catch (Exception e2) {
                        ExceptionReporter.reportExceptionToBugly(e2);
                    }
                }
            }
            pdMainImageBaseHolder2 = pdTopImageSuitViewHolder;
            if (pdMainImageBaseHolder2 != null) {
            }
        }
        return pdMainImageBaseHolder2;
    }
}
