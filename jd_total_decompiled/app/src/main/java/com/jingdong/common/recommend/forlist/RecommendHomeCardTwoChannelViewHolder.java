package com.jingdong.common.recommend.forlist;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jd.dynamic.DYConstants;
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
import com.jingdong.sdk.utils.DPIUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class RecommendHomeCardTwoChannelViewHolder extends BaseRecommendViewHolder {
    private BaseActivity baseActivity;
    private ConstraintLayout channel1;
    private RecommendHomeCardBean.SubWareList channel1Bean;
    private ConstraintLayout channel2;
    private RecommendHomeCardBean.SubWareList channel2Bean;
    private ArrayList<String> expoArray;
    private RecommendMaskViewHelper maskViewHelper;
    private HomeCardSkuLayout sku1;
    private HomeCardSkuLayout sku2;
    private HomeCardMainTitleLayout title1;
    private HomeCardMainTitleLayout title2;

    public RecommendHomeCardTwoChannelViewHolder(BaseActivity baseActivity, View view) {
        super(view);
        this.expoArray = new ArrayList<>();
        this.baseActivity = baseActivity;
        findViews(view);
    }

    /* renamed from: b */
    public /* synthetic */ void c(View view) {
        RecommendHomeCardBean.SubWareList subWareList = this.channel1Bean;
        RecommendJumpUtils.addJumpParam(subWareList.jump, "innerSkuImgUrl", subWareList.img);
        JumpUtil.execJump(this.itemView.getContext(), this.channel1Bean.jump, 1);
        Context context = this.itemView.getContext();
        RecommendHomeCardBean.SubWareList subWareList2 = this.channel1Bean;
        RecommendMtaUtils.doHomeChannelCardClick(context, 1, subWareList2.jump, subWareList2);
    }

    /* renamed from: d */
    public /* synthetic */ void e(View view) {
        RecommendHomeCardBean.SubWareList subWareList = this.channel2Bean;
        RecommendJumpUtils.addJumpParam(subWareList.jump, "innerSkuImgUrl", subWareList.img);
        JumpUtil.execJump(this.itemView.getContext(), this.channel2Bean.jump, 1);
        Context context = this.itemView.getContext();
        RecommendHomeCardBean.SubWareList subWareList2 = this.channel2Bean;
        RecommendMtaUtils.doHomeChannelCardClick(context, 2, subWareList2.jump, subWareList2);
    }

    private void findViews(View view) {
        this.channel1 = (ConstraintLayout) view.findViewById(R.id.channel1);
        this.title1 = (HomeCardMainTitleLayout) view.findViewById(R.id.mainTitle1);
        this.sku1 = (HomeCardSkuLayout) view.findViewById(R.id.sku1);
        this.channel2 = (ConstraintLayout) view.findViewById(R.id.channel2);
        this.title2 = (HomeCardMainTitleLayout) view.findViewById(R.id.mainTitle2);
        this.sku2 = (HomeCardSkuLayout) view.findViewById(R.id.sku2);
    }

    private void setViewLayout() {
        ViewGroup.LayoutParams layoutParams = this.itemView.getLayoutParams();
        layoutParams.height = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 204);
        this.itemView.setLayoutParams(layoutParams);
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) this.title1.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams2).width = -2;
        ((ViewGroup.MarginLayoutParams) layoutParams2).height = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 52);
        ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 16);
        ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 0);
        this.title1.setLayoutParams(layoutParams2);
        ConstraintLayout.LayoutParams layoutParams3 = (ConstraintLayout.LayoutParams) this.sku1.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams3).width = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 140);
        ((ViewGroup.MarginLayoutParams) layoutParams3).height = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 140);
        ((ViewGroup.MarginLayoutParams) layoutParams3).leftMargin = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 20);
        ((ViewGroup.MarginLayoutParams) layoutParams3).topMargin = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 52);
        this.sku1.setLayoutParams(layoutParams3);
        ConstraintLayout.LayoutParams layoutParams4 = (ConstraintLayout.LayoutParams) this.title2.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams4).width = -2;
        ((ViewGroup.MarginLayoutParams) layoutParams4).height = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 52);
        ((ViewGroup.MarginLayoutParams) layoutParams4).leftMargin = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 10);
        ((ViewGroup.MarginLayoutParams) layoutParams4).topMargin = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 0);
        this.title2.setLayoutParams(layoutParams4);
        ConstraintLayout.LayoutParams layoutParams5 = (ConstraintLayout.LayoutParams) this.sku2.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams5).width = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 140);
        ((ViewGroup.MarginLayoutParams) layoutParams5).height = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 140);
        ((ViewGroup.MarginLayoutParams) layoutParams5).leftMargin = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 16);
        ((ViewGroup.MarginLayoutParams) layoutParams5).topMargin = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, 52);
        this.sku2.setLayoutParams(layoutParams5);
    }

    @Override // com.jingdong.common.recommend.forlist.BaseRecommendViewHolder
    public void exposurePercent(double d) {
        RecommendHomeCardBean.SubWareList subWareList;
        RecommendHomeCardBean.SubWareList subWareList2;
        super.exposurePercent(d);
        if (d > 0.0d) {
            try {
                ArrayList<String> arrayList = this.expoArray;
                if (arrayList == null || arrayList.size() <= 0 || (subWareList = this.channel1Bean) == null || subWareList.hasExpoView || (subWareList2 = this.channel2Bean) == null || subWareList2.hasExpoView) {
                    return;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                int size = this.expoArray.size();
                for (int i2 = 0; i2 < size; i2++) {
                    if (i2 > 0) {
                        sb.append(DYConstants.DY_REGEX_COMMA);
                    }
                    sb.append(this.expoArray.get(i2));
                }
                sb.append("]");
                this.channel1Bean.hasExpoView = true;
                this.channel2Bean.hasExpoView = true;
                RecommendMtaUtils.sendHomeChannelCardExpo(this.itemView.getContext(), sb.toString());
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
        this.channel1Bean = subWareList;
        if (subWareList != null) {
            this.expoArray.add(subWareList.exposureJsonSourceValue);
            if (!TextUtils.isEmpty(this.channel1Bean.bgImg)) {
                RecommendImageUtils.downloadAnddisplay(this.channel1Bean.bgImg, this.channel1);
            }
            this.title1.bind(this.baseActivity, HomeCardMainTitleLayout.Info.builder().setShowNameImg(this.channel1Bean.showNameImg).setShowName(this.channel1Bean.showName).setMaintitleColor(this.channel1Bean.maintitleColor).setShowNameImgWidth(this.channel1Bean.showNameImgWidth));
            this.sku1.bind(this.baseActivity, HomeCardSkuLayout.Info.builder().setSkuImg(this.channel1Bean.img).setSkuTagImg(this.channel1Bean.skuTagImg).setPrice(this.channel1Bean.price1).setPriceColor(this.channel1Bean.priceColor));
            if (this.maskViewHelper == null) {
                this.maskViewHelper = new RecommendMaskViewHelper();
            }
            this.maskViewHelper.maskView(this.channel1, this.channel1Bean.markedImg);
            this.channel1.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.j
                {
                    RecommendHomeCardTwoChannelViewHolder.this = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RecommendHomeCardTwoChannelViewHolder.this.c(view);
                }
            });
        }
        RecommendHomeCardBean.SubWareList subWareList2 = recommendHomeCardBean.subWareList.size() > 1 ? recommendHomeCardBean.subWareList.get(1) : null;
        this.channel2Bean = subWareList2;
        if (subWareList2 != null) {
            this.expoArray.add(subWareList2.exposureJsonSourceValue);
            if (!TextUtils.isEmpty(this.channel2Bean.bgImg)) {
                RecommendImageUtils.downloadAnddisplay(this.channel2Bean.bgImg, this.channel2);
            }
            this.title2.bind(this.baseActivity, HomeCardMainTitleLayout.Info.builder().setShowNameImg(this.channel2Bean.showNameImg).setShowName(this.channel2Bean.showName).setMaintitleColor(this.channel2Bean.maintitleColor).setShowNameImgWidth(this.channel2Bean.showNameImgWidth));
            this.sku2.bind(this.baseActivity, HomeCardSkuLayout.Info.builder().setSkuImg(this.channel2Bean.img).setSkuTagImg(this.channel2Bean.skuTagImg).setPrice(this.channel2Bean.price1).setPriceColor(this.channel2Bean.priceColor));
            if (this.maskViewHelper == null) {
                this.maskViewHelper = new RecommendMaskViewHelper();
            }
            this.maskViewHelper.maskView(this.channel2, this.channel2Bean.markedImg);
            this.channel2.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.k
                {
                    RecommendHomeCardTwoChannelViewHolder.this = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RecommendHomeCardTwoChannelViewHolder.this.e(view);
                }
            });
        }
    }
}
