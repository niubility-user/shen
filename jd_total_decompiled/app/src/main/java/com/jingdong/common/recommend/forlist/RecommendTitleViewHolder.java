package com.jingdong.common.recommend.forlist;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkJShopHomeHelper;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendConfig;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.entity.RecommendPdTitle;
import com.jingdong.common.recommend.entity.RecommendProduct;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class RecommendTitleViewHolder extends BaseRecommendViewHolder {
    private BaseActivity activity;
    private Drawable arrowDrawable;
    private GradientDrawable groupBgDrawable;
    private View newStyleView;
    private View oldStyleView;
    private TextView shopName;
    private ImageView titleLeftIcon;
    private TextView titleName;
    private ImageView titleRightIcon;
    private Drawable titleVerticalNormalDrawable;
    private TextView topTitle;

    public RecommendTitleViewHolder(BaseActivity baseActivity, View view) {
        super(view);
        this.activity = baseActivity;
        this.topTitle = (TextView) view.findViewById(R.id.recommend_top_title);
        this.shopName = (TextView) view.findViewById(R.id.recommend_shop_name);
        this.oldStyleView = view.findViewById(R.id.recom_pd_title_old);
        this.newStyleView = view.findViewById(R.id.recom_pd_title_new);
        this.titleName = (TextView) view.findViewById(R.id.recom_pd_title);
        this.titleLeftIcon = (ImageView) view.findViewById(R.id.pd_title_icon_left);
        this.titleRightIcon = (ImageView) view.findViewById(R.id.pd_title_icon_right);
    }

    private boolean isDark() {
        RecommendConfig recommendConfig = this.mRecommendConfig;
        return recommendConfig.isPdFeedDark || recommendConfig.isDarkTheme();
    }

    private void showNewView(RecommendPdTitle recommendPdTitle) {
        RecommendViewUtil.visible(this.newStyleView);
        RecommendViewUtil.gone(this.oldStyleView);
        ((CardView) this.itemView).setCardBackgroundColor(0);
        if (this.mRecommendConfig != null) {
            if (isDark()) {
                this.titleName.setTextColor(-1250068);
            } else {
                this.titleName.setTextColor(-15066598);
            }
            this.titleRightIcon.setImageResource(isDark() ? R.drawable.recom_pd_title_right : R.drawable.recom_pd_title_right_dark);
            this.titleLeftIcon.setImageResource(isDark() ? R.drawable.recom_pd_title_left : R.drawable.recom_pd_title_left_dark);
        }
        if (!TextUtils.isEmpty(recommendPdTitle.titleTop)) {
            this.titleName.setText(recommendPdTitle.titleTop);
        } else {
            this.titleName.setText(this.activity.getResources().getText(R.string.recommend_title));
        }
    }

    private void showOldView(final RecommendPdTitle recommendPdTitle) {
        RecommendViewUtil.visible(this.oldStyleView);
        RecommendViewUtil.gone(this.newStyleView);
        if (!TextUtils.isEmpty(recommendPdTitle.titleTop)) {
            this.topTitle.setText(recommendPdTitle.titleTop);
        } else {
            this.topTitle.setText(this.activity.getResources().getText(R.string.recommend_title));
        }
        if (!TextUtils.isEmpty(recommendPdTitle.shopName) && !TextUtils.isEmpty(recommendPdTitle.reasonDesc) && recommendPdTitle.reasonDesc.equals("501")) {
            this.shopName.setVisibility(0);
            this.shopName.setText(recommendPdTitle.shopName);
        } else {
            this.shopName.setVisibility(8);
        }
        this.shopName.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTitleViewHolder.1
            {
                RecommendTitleViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!TextUtils.isEmpty(recommendPdTitle.shopId)) {
                    ArrayList arrayList = new ArrayList();
                    String str = "";
                    ArrayList arrayList2 = (ArrayList) recommendPdTitle.wareInfoList;
                    if (arrayList2 != null && arrayList2.size() > 0) {
                        for (int i2 = 0; i2 < arrayList2.size() && (arrayList2.get(i2) instanceof RecommendProduct); i2++) {
                            RecommendProduct recommendProduct = (RecommendProduct) arrayList2.get(i2);
                            if (i2 == 0) {
                                str = recommendProduct.wareId;
                            }
                            arrayList.add(recommendProduct.wareId);
                        }
                    }
                    RecommendPdTitle recommendPdTitle2 = recommendPdTitle;
                    DeepLinkJShopHomeHelper.JShopBundleBuilder from = DeepLinkJShopHomeHelper.JShopBundleBuilder.from(recommendPdTitle2.shopId, recommendPdTitle2.venderId, recommendPdTitle2.shopName);
                    from.addSource(new SourceEntity(SourceEntity.SOURCE_TYPE_UNIFIED_RECOMMEND, null)).addTargetPage("allProduct").addSkuList(str, arrayList);
                    DeepLinkJShopHomeHelper.gotoJShopHome(RecommendTitleViewHolder.this.activity, from.build());
                }
                RecommendMtaUtils.shopNameClickMat(RecommendTitleViewHolder.this.activity, recommendPdTitle.enterShopSourceValue);
            }
        });
        ((CardView) this.itemView).setCardBackgroundColor(getColor_FFFFFF());
        this.topTitle.setTextColor(getColor_262626());
        this.shopName.setTextColor(getColor_262626());
        Drawable drawable = this.activity.getResources().getDrawable(isDeepDark() ? R.drawable.recommend_pd_right_arrow_dark_theme : R.drawable.recommend_pd_title_right_more);
        this.arrowDrawable = drawable;
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), this.arrowDrawable.getMinimumHeight());
            this.shopName.setCompoundDrawables(null, null, this.arrowDrawable, null);
        }
        Drawable drawable2 = this.activity.getResources().getDrawable(isDeepDark() ? R.drawable.recommend_pd_left_red_line_dark : R.drawable.recommend_pd_title_left_icon);
        this.titleVerticalNormalDrawable = drawable2;
        if (drawable2 != null) {
            drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), this.titleVerticalNormalDrawable.getMinimumHeight());
            this.topTitle.setCompoundDrawables(this.titleVerticalNormalDrawable, null, null, null);
        }
    }

    public void setData(RecommendPdTitle recommendPdTitle, int i2, ExpoDataStore expoDataStore, int i3) {
        if (recommendPdTitle != null) {
            setFrom(i3);
            if (recommendPdTitle.showStyle == 2) {
                showNewView(recommendPdTitle);
            } else {
                showOldView(recommendPdTitle);
            }
        }
    }
}
