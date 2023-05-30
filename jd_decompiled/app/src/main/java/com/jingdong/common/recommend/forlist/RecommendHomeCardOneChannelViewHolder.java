package com.jingdong.common.recommend.forlist;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendImageUtils;
import com.jingdong.common.recommend.RecommendJumpUtils;
import com.jingdong.common.recommend.RecommendMaskViewHelper;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.entity.RecommendHomeCardBean;
import com.jingdong.common.recommend.ui.homerecommend.HomeCardMainTitleLayout;
import com.jingdong.common.recommend.ui.homerecommend.HomeCardSkuLayout;
import com.jingdong.common.recommend.ui.homerecommend.HomeCardSubTitleLayout;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class RecommendHomeCardOneChannelViewHolder extends BaseRecommendViewHolder {
    private BaseActivity baseActivity;
    private RecommendHomeCardBean.SubWareList channelBean;
    private LinearLayout header;
    private ConstraintLayout layout_container;
    private RecommendMaskViewHelper maskViewHelper;
    private HomeCardSkuLayout sku1;
    private HomeCardSkuLayout sku2;
    private HomeCardSubTitleLayout subTitle;
    private HomeCardMainTitleLayout title;

    public RecommendHomeCardOneChannelViewHolder(BaseActivity baseActivity, View view) {
        super(view);
        this.baseActivity = baseActivity;
        findViews(view);
    }

    /* renamed from: b */
    public /* synthetic */ void c(View view) {
        RecommendHomeCardBean.SubWareList subWareList = this.channelBean;
        RecommendJumpUtils.addJumpParam(subWareList.jump, "innerSkuImgUrl", subWareList.img);
        JumpUtil.execJump(this.itemView.getContext(), this.channelBean.jump, 1);
        Context context = this.itemView.getContext();
        RecommendHomeCardBean.SubWareList subWareList2 = this.channelBean;
        RecommendMtaUtils.doHomeChannelCardClick(context, 0, subWareList2.jump, subWareList2);
    }

    /* renamed from: d */
    public /* synthetic */ void e(View view) {
        RecommendHomeCardBean.SubWareList subWareList = this.channelBean;
        RecommendJumpUtils.addJumpParam(subWareList.jump, "innerSkuImgUrl", subWareList.img);
        JumpUtil.execJump(this.itemView.getContext(), this.channelBean.jump, 1);
        Context context = this.itemView.getContext();
        RecommendHomeCardBean.SubWareList subWareList2 = this.channelBean;
        RecommendMtaUtils.doHomeChannelCardClick(context, 1, subWareList2.jump, subWareList2);
    }

    /* renamed from: f */
    public /* synthetic */ void g(View view) {
        RecommendHomeCardBean.SubWareList subWareList = this.channelBean;
        RecommendJumpUtils.addJumpParam(subWareList.jump2, "innerSkuImgUrl", subWareList.img2);
        JumpUtil.execJump(this.itemView.getContext(), this.channelBean.jump2, 1);
        Context context = this.itemView.getContext();
        RecommendHomeCardBean.SubWareList subWareList2 = this.channelBean;
        RecommendMtaUtils.doHomeChannelCardClick(context, 2, subWareList2.jump2, subWareList2);
    }

    private void findViews(View view) {
        this.layout_container = (ConstraintLayout) view.findViewById(R.id.layout_container);
        this.header = (LinearLayout) view.findViewById(R.id.header);
        this.title = (HomeCardMainTitleLayout) view.findViewById(R.id.title);
        this.subTitle = (HomeCardSubTitleLayout) view.findViewById(R.id.subtitle);
        this.sku1 = (HomeCardSkuLayout) view.findViewById(R.id.sku1);
        this.sku2 = (HomeCardSkuLayout) view.findViewById(R.id.sku2);
    }

    private void setViewLayout() {
        ViewGroup.LayoutParams layoutParams = this.itemView.getLayoutParams();
        layoutParams.height = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 204);
        this.itemView.setLayoutParams(layoutParams);
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) this.header.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams2).width = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 316);
        ((ViewGroup.MarginLayoutParams) layoutParams2).height = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 52);
        ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 16);
        ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 0);
        this.header.setLayoutParams(layoutParams2);
        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.title.getLayoutParams();
        layoutParams3.width = -2;
        this.title.setLayoutParams(layoutParams3);
        LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) this.subTitle.getLayoutParams();
        layoutParams4.height = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 28);
        layoutParams4.leftMargin = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 8);
        this.subTitle.setLayoutParams(layoutParams4);
        ConstraintLayout.LayoutParams layoutParams5 = (ConstraintLayout.LayoutParams) this.sku1.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams5).width = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 140);
        ((ViewGroup.MarginLayoutParams) layoutParams5).height = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 140);
        ((ViewGroup.MarginLayoutParams) layoutParams5).leftMargin = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 20);
        ((ViewGroup.MarginLayoutParams) layoutParams5).topMargin = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 52);
        this.sku1.setLayoutParams(layoutParams5);
        ConstraintLayout.LayoutParams layoutParams6 = (ConstraintLayout.LayoutParams) this.sku2.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams6).width = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 140);
        ((ViewGroup.MarginLayoutParams) layoutParams6).height = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 140);
        ((ViewGroup.MarginLayoutParams) layoutParams6).leftMargin = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 16);
        ((ViewGroup.MarginLayoutParams) layoutParams6).topMargin = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 52);
        this.sku2.setLayoutParams(layoutParams6);
    }

    @Override // com.jingdong.common.recommend.forlist.BaseRecommendViewHolder
    public void exposurePercent(double d) {
        super.exposurePercent(d);
        if (d > 0.0d) {
            try {
                StringBuilder sb = new StringBuilder();
                RecommendHomeCardBean.SubWareList subWareList = this.channelBean;
                if (subWareList != null && !subWareList.hasExpoView && !TextUtils.isEmpty(subWareList.exposureJsonSourceValue)) {
                    this.channelBean.hasExpoView = true;
                    sb.append("[");
                    sb.append(this.channelBean.exposureJsonSourceValue);
                    sb.append("]");
                }
                if (sb.length() > 0) {
                    RecommendMtaUtils.sendHomeChannelCardExpo(this.itemView.getContext(), sb.toString());
                }
            } catch (Exception unused) {
            }
        }
    }

    public void setData(RecommendHomeCardBean recommendHomeCardBean, JDDisplayImageOptions jDDisplayImageOptions) {
        List<RecommendHomeCardBean.SubWareList> list;
        if (recommendHomeCardBean == null || (list = recommendHomeCardBean.subWareList) == null || list.size() <= 0) {
            return;
        }
        setViewLayout();
        RecommendHomeCardBean.SubWareList subWareList = recommendHomeCardBean.subWareList.get(0);
        this.channelBean = subWareList;
        if (subWareList != null) {
            if (!TextUtils.isEmpty(subWareList.bgImg)) {
                RecommendImageUtils.downloadAnddisplay(this.channelBean.bgImg, this.layout_container);
            }
            this.title.bind(this.baseActivity, HomeCardMainTitleLayout.Info.builder().setShowNameImg(this.channelBean.showNameImg).setShowName(this.channelBean.showName).setMaintitleColor(this.channelBean.maintitleColor).setShowNameImgWidth(this.channelBean.showNameImgWidth));
            HomeCardSubTitleLayout.Info sloganTagImg = HomeCardSubTitleLayout.Info.builder().setSlogan(this.channelBean.slogan).setSloganColor(this.channelBean.sloganColor).setSloganTagImg(this.channelBean.sloganTagImg);
            if (!TextUtils.isEmpty(this.channelBean.showNameImgWidth)) {
                try {
                    sloganTagImg.setMaxWidth(308 - Integer.parseInt(this.channelBean.showNameImgWidth));
                } catch (Exception unused) {
                }
            }
            this.subTitle.bind(this.baseActivity, sloganTagImg);
            this.sku1.bind(this.baseActivity, HomeCardSkuLayout.Info.builder().setSkuImg(this.channelBean.img).setSkuTagImg(this.channelBean.skuTagImg).setPrice(this.channelBean.price1).setPriceColor(this.channelBean.priceColor));
            this.sku2.bind(this.baseActivity, HomeCardSkuLayout.Info.builder().setSkuImg(this.channelBean.img2).setSkuTagImg(this.channelBean.skuTagImg).setPrice(this.channelBean.price2).setPriceColor(this.channelBean.priceColor));
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.h
                {
                    RecommendHomeCardOneChannelViewHolder.this = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RecommendHomeCardOneChannelViewHolder.this.c(view);
                }
            });
            if (this.maskViewHelper == null) {
                this.maskViewHelper = new RecommendMaskViewHelper();
            }
            this.maskViewHelper.maskView((ViewGroup) this.itemView, this.channelBean.markedImg);
            this.sku1.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.i
                {
                    RecommendHomeCardOneChannelViewHolder.this = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RecommendHomeCardOneChannelViewHolder.this.e(view);
                }
            });
            this.sku2.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.g
                {
                    RecommendHomeCardOneChannelViewHolder.this = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RecommendHomeCardOneChannelViewHolder.this.g(view);
                }
            });
        }
    }
}
