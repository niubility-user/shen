package com.jingdong.common.recommend.forlist;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.entity.RecommendHomeTabTemp;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class RecommendHomeTabTempViewHolder extends BaseRecommendAdViewHolder {
    private String bgUrl;
    private int from;
    private String imgUrl;
    private View.OnClickListener jumpClickListener;
    private TextView lableTv;
    private SimpleDraweeView recommendBgIV;
    private SimpleDraweeView recommendImgIV;
    private LinearLayout recommendLabelContainer;
    private TextView recommendNameTV;
    private TextView recommendSubTitleTV;
    private TextView recommendTitleTV;

    public RecommendHomeTabTempViewHolder(View view) {
        super(view);
        this.jumpClickListener = new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendHomeTabTempViewHolder.2
            {
                RecommendHomeTabTempViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RecommendHomeTabTemp recommendHomeTabTemp = (RecommendHomeTabTemp) view2.getTag();
                if (recommendHomeTabTemp != null) {
                    RecommendHomeTabTempViewHolder recommendHomeTabTempViewHolder = RecommendHomeTabTempViewHolder.this;
                    if (recommendHomeTabTempViewHolder.clickedListener != null) {
                        RecommendMtaUtils.aggregationClickMtaRealize(recommendHomeTabTempViewHolder.itemView.getContext(), recommendHomeTabTemp.sourceValue, RecommendHomeTabTempViewHolder.this.from, recommendHomeTabTemp.extension_id);
                        RecommendHomeTabTempViewHolder.this.clickedListener.onRecommendJump(recommendHomeTabTemp.channelJumpUrl, recommendHomeTabTemp.isOpenApp);
                        RecommendHomeTabTempViewHolder.this.onAdClick(recommendHomeTabTemp.client_click_url);
                    }
                }
            }
        };
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.recommend_template_one_bg);
        this.recommendBgIV = simpleDraweeView;
        simpleDraweeView.setAspectRatio(0.617f);
        SimpleDraweeView simpleDraweeView2 = (SimpleDraweeView) view.findViewById(R.id.recommend_template_one_img);
        this.recommendImgIV = simpleDraweeView2;
        simpleDraweeView2.setAspectRatio(1.0f);
        this.recommendNameTV = (TextView) view.findViewById(R.id.recommend_template_one_name);
        this.recommendTitleTV = (TextView) view.findViewById(R.id.recommend_template_one_title);
        this.recommendSubTitleTV = (TextView) view.findViewById(R.id.recommend_template_one_subtitle);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.recommend_template_label);
        this.recommendLabelContainer = linearLayout;
        linearLayout.setVisibility(0);
        TextView textView = new TextView(view.getContext());
        this.lableTv = textView;
        textView.setTextSize(10.0f);
        this.lableTv.setLayoutParams(new LinearLayout.LayoutParams(-2, DPIUtil.dip2px(18.0f)));
        this.lableTv.setGravity(16);
        this.lableTv.setLines(1);
        int dip2px = DPIUtil.dip2px(5.0f);
        this.lableTv.setPadding(dip2px, 0, dip2px, 0);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(DPIUtil.dip2px(9.0f));
        gradientDrawable.setColor(Color.parseColor("#0E4965F2"));
        if (Build.VERSION.SDK_INT >= 16) {
            this.lableTv.setBackground(gradientDrawable);
        } else {
            this.lableTv.setBackgroundDrawable(gradientDrawable);
        }
        this.recommendLabelContainer.addView(this.lableTv);
    }

    protected int generateColor(String str, String str2) {
        try {
            if (!TextUtils.isEmpty(str)) {
                return Color.parseColor(str);
            }
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
        try {
            return Color.parseColor(str2);
        } catch (Exception e3) {
            if (OKLog.D) {
                e3.printStackTrace();
                return 0;
            }
            return 0;
        }
    }

    protected void generateGradientDrawable(View view, float f2, int[] iArr) {
        try {
            if (Build.VERSION.SDK_INT >= 16 && (view.getBackground() instanceof GradientDrawable)) {
                ((GradientDrawable) view.getBackground().mutate()).setColors(iArr);
                return;
            }
            GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR, iArr);
            gradientDrawable.setCornerRadius(f2);
            view.setBackgroundDrawable(gradientDrawable);
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
    }

    protected void resetContent() {
        this.recommendNameTV.setText("");
        this.recommendTitleTV.setText("");
        this.recommendSubTitleTV.setText("");
    }

    public void setData(RecommendHomeTabTemp recommendHomeTabTemp, JDDisplayImageOptions jDDisplayImageOptions, ExpoDataStore expoDataStore, int i2) {
        String str;
        String str2;
        this.itemView.setTag(recommendHomeTabTemp);
        if (recommendHomeTabTemp == null) {
            return;
        }
        this.from = i2;
        resetContent();
        if (this.recommendBgIV.getDrawable() == null || (str2 = this.bgUrl) == null || !str2.equals(recommendHomeTabTemp.nonWareBgImgUrl)) {
            String str3 = recommendHomeTabTemp.nonWareBgImgUrl;
            this.bgUrl = str3;
            JDImageUtils.displayImage(str3, this.recommendBgIV, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendHomeTabTempViewHolder.1
                {
                    RecommendHomeTabTempViewHolder.this = this;
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str4, View view) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str4, View view, Bitmap bitmap) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str4, View view, JDFailReason jDFailReason) {
                    RecommendHomeTabTempViewHolder.this.recommendBgIV.setImageResource(R.drawable.recommend_template_bg);
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingStarted(String str4, View view) {
                }
            });
        }
        List<RecommendProduct> list = recommendHomeTabTemp.wareList;
        RecommendProduct recommendProduct = (list == null || list.isEmpty()) ? null : recommendHomeTabTemp.wareList.get(0);
        if ((this.recommendImgIV.getDrawable() == null || (str = this.imgUrl) == null || (recommendProduct != null && !str.equals(recommendProduct.imgUrl))) && recommendProduct != null) {
            String str4 = recommendProduct.imgUrl;
            this.imgUrl = str4;
            JDImageUtils.displayImage(str4, this.recommendImgIV, jDDisplayImageOptions);
        }
        if (TextUtils.isEmpty(recommendHomeTabTemp.nonWareTypeName)) {
            this.recommendNameTV.setVisibility(8);
        } else {
            this.recommendNameTV.setVisibility(0);
            this.recommendNameTV.setText(recommendHomeTabTemp.nonWareTypeName);
            this.recommendNameTV.setTextColor(generateColor(recommendHomeTabTemp.nonWareTypeNameFontColor, JDDarkUtil.COLOR_1A1A1A));
            int[] iArr = recommendHomeTabTemp.firstTitleColors;
            if (iArr != null && iArr.length > 0) {
                generateGradientDrawable(this.recommendNameTV, DPIUtil.dip2px(10.0f), recommendHomeTabTemp.firstTitleColors);
            } else {
                this.recommendNameTV.setBackgroundDrawable(null);
            }
        }
        if (TextUtils.isEmpty(recommendHomeTabTemp.wname)) {
            this.recommendTitleTV.setVisibility(8);
        } else {
            this.recommendTitleTV.setVisibility(0);
            this.recommendTitleTV.setText(recommendHomeTabTemp.wname);
            this.recommendTitleTV.setTextColor(generateColor(recommendHomeTabTemp.wnameFontColor, JDDarkUtil.COLOR_1A1A1A));
        }
        if (TextUtils.isEmpty(recommendHomeTabTemp.description)) {
            this.recommendSubTitleTV.setVisibility(8);
        } else {
            this.recommendSubTitleTV.setVisibility(0);
            this.recommendSubTitleTV.setText(recommendHomeTabTemp.description);
            this.recommendSubTitleTV.setTextColor(generateColor(recommendHomeTabTemp.descriptionFontColor, "#4965F2"));
        }
        this.lableTv.setText("\u7acb\u5373\u67e5\u770b");
        this.lableTv.setTextColor(generateColor(recommendHomeTabTemp.tagFontColor, "#FF4965F2"));
        if (this.lableTv.getBackground() instanceof GradientDrawable) {
            ((GradientDrawable) this.lableTv.getBackground()).setColor(generateColor(recommendHomeTabTemp.tagBgColor, "#0E4965F2"));
        }
        setAdData(recommendHomeTabTemp);
        if (expoDataStore != null) {
            if (!TextUtils.isEmpty(recommendHomeTabTemp.exposureJsonSourceValue)) {
                expoDataStore.putExpoJsonDada(recommendHomeTabTemp.exposureJsonSourceValue);
            } else if (!TextUtils.isEmpty(recommendHomeTabTemp.exposureSourceValue)) {
                expoDataStore.putExpoData(recommendHomeTabTemp.exposureSourceValue);
            }
        }
        this.itemView.setOnClickListener(this.jumpClickListener);
    }
}
