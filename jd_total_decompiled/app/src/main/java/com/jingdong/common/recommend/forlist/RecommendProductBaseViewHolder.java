package com.jingdong.common.recommend.forlist;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.common.recommend.RecommendConstant;
import com.jingdong.common.recommend.RecommendFontUtils;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.entity.RecommendIcon;
import com.jingdong.common.recommend.entity.RecommendLabel;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.recommend.ui.AutoWarpTextView;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;
import com.jingdong.jdsdk.utils.DPIUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class RecommendProductBaseViewHolder extends BaseRecommendViewHolder {
    protected AutoWarpTextView name;
    protected int nameViewWidth;
    protected RecomLayoutMaxLines recommendInfoLayout;
    protected int recommendItemWidth;
    protected RecomLayoutMaxLines topRecommendInfoLayout;

    public RecommendProductBaseViewHolder(View view) {
        super(view);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public void addNRecommendLabel(List<RecommendLabel> list, RecommendProduct recommendProduct, int i2) {
        TextView textView;
        if (list == null || list.isEmpty()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        RecomLayoutMaxLines recomLayoutMaxLines = recommendProduct.isRecomInfoDown ? this.recommendInfoLayout : this.topRecommendInfoLayout;
        for (int i3 = 0; i3 < list.size(); i3++) {
            RecommendLabel recommendLabel = list.get(i3);
            if (recommendLabel != null && !TextUtils.isEmpty(recommendLabel.resourceCode) && !TextUtils.isEmpty(recommendLabel.key)) {
                if (TextUtils.isEmpty(recommendLabel.labelTitle)) {
                    ImageView imageView = new ImageView(this.itemView.getContext());
                    imageView.setImageDrawable(UnIconConfigHelper.getDrawable(recommendLabel.resourceCode));
                    textView = imageView;
                } else {
                    TextView textView2 = getTextView(recommendLabel.resourceCode, recommendLabel.labelTitle);
                    textView2.setLines(1);
                    textView = textView2;
                }
                i2 = (int) (i2 - (DPIUtil.dip2px(5.0f) + RecommendViewUtil.getViewMeasureWidth(textView)));
                if (i2 > 0) {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.rightMargin = DPIUtil.dip2px(5.0f);
                    layoutParams.gravity = 16;
                    textView.setLayoutParams(layoutParams);
                    if (recomLayoutMaxLines != null) {
                        recomLayoutMaxLines.addView(textView);
                    }
                    RecommendUtils.joinLabelValue(sb, recommendProduct, recommendLabel);
                }
            }
        }
        RecommendUtils.handleLabelValueMtaJson(sb.toString(), recommendProduct);
        recommendProduct.isMtaValueChanged = true;
        if (recomLayoutMaxLines != null) {
            recomLayoutMaxLines.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addNameIcons(RecommendProduct recommendProduct) {
        List arrayList = new ArrayList();
        List<RecommendIcon> list = recommendProduct.icon2List;
        if (list != null && !list.isEmpty()) {
            arrayList = recommendProduct.icon2List;
        } else {
            String str = recommendProduct.icon1;
            if (str != null) {
                arrayList.add(new RecommendIcon(str));
            }
            String str2 = recommendProduct.icon2;
            if (str2 != null) {
                arrayList.add(new RecommendIcon(str2));
            }
        }
        RecommendViewUtil.setTextViewSpanString(arrayList, recommendProduct.getName(), this.name, this.nameViewWidth);
        this.name.setTextColor(getColor_262626());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void clickMtaJson(RecommendProduct recommendProduct) {
        if (TextUtils.isEmpty(recommendProduct.sourceValue) || recommendProduct.sourceValue.contains(RecommendConstant.RECOM_SKU_HEIGHT)) {
            return;
        }
        RecommendMtaUtils.appHeightToExpo_product(recommendProduct, this.itemView.getHeight());
        recommendProduct.sourceValue = RecommendMtaUtils.addKeyToMtaJson(recommendProduct.appendMtaJson_real, recommendProduct.sourceValue);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void dealExpoSourceValue(RecommendProduct recommendProduct) {
        if (!TextUtils.isEmpty(recommendProduct.sourceValue) && !recommendProduct.sourceValue.contains(RecommendConstant.RECOM_SUBPRICE_YPE)) {
            recommendProduct.sourceValue = RecommendMtaUtils.addKeyToMtaJson(recommendProduct.appendMtaJson, recommendProduct.sourceValue);
        }
        if (TextUtils.isEmpty(recommendProduct.exposureJsonSourceValue) || recommendProduct.exposureJsonSourceValue.contains(RecommendConstant.RECOM_SUBPRICE_YPE)) {
            return;
        }
        recommendProduct.exposureJsonSourceValue = RecommendMtaUtils.addKeyToMtaJson(recommendProduct.appendMtaJson, recommendProduct.exposureJsonSourceValue);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TextView getRecommendTextView(RecommendProduct recommendProduct) {
        TextView textView = getTextView(recommendProduct.recomReasonTab, recommendProduct.recomReason);
        RecommendFontUtils.changeFont(textView, 4099);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setLines(1);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        return textView;
    }
}
