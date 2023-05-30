package com.jingdong.common.recommend.forlist;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.entity.RecommendDna;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.text.ScaleModeConstants;
import com.jingdong.common.utils.text.TextScaleModeHelper;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class RecommendMonetizationViewHolder extends BaseRecommendViewHolder implements View.OnClickListener {
    private int[] colors;
    private TextView descriptionMoreTV;
    private TextView descriptionTV;
    private View dot;
    private int from;
    private String imageUrl;
    private View itemView;
    private View leftBottomDot;
    private SimpleDraweeView productBg;
    private SimpleDraweeView productImage;
    private TextView wnameTV;

    public RecommendMonetizationViewHolder(View view) {
        super(view);
        this.colors = new int[2];
        this.itemView = view;
        this.dot = view.findViewById(R.id.recommend_dot);
        this.productImage = (SimpleDraweeView) view.findViewById(R.id.recommend_monetization_product);
        this.productBg = (SimpleDraweeView) view.findViewById(R.id.recommend_monetization_bg);
        this.wnameTV = (TextView) view.findViewById(R.id.recommend_monetization_wname);
        this.descriptionMoreTV = (TextView) view.findViewById(R.id.recommend_monetization_description_more);
        this.descriptionTV = (TextView) view.findViewById(R.id.recommend_monetization_description);
        this.leftBottomDot = view.findViewById(R.id.recommend_left_dot);
    }

    private static GradientDrawable getDrawable(View view, int[] iArr) {
        GradientDrawable gradientDrawable;
        if (Build.VERSION.SDK_INT >= 16) {
            if (view.getBackground() == null) {
                gradientDrawable = new GradientDrawable();
                gradientDrawable.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
                gradientDrawable.setCornerRadius(DPIUtil.dip2px(12.5f));
                gradientDrawable.setGradientType(0);
            } else {
                gradientDrawable = view.getBackground() instanceof GradientDrawable ? (GradientDrawable) view.getBackground() : null;
            }
            if (gradientDrawable != null) {
                gradientDrawable.setColors(iArr);
                return gradientDrawable;
            }
            return gradientDrawable;
        }
        GradientDrawable gradientDrawable2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, iArr);
        gradientDrawable2.setCornerRadius(DPIUtil.dip2px(12.5f));
        gradientDrawable2.setGradientType(0);
        return gradientDrawable2;
    }

    private void initTextSize() {
        if (ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode())) {
            this.wnameTV.setTextSize(16.0f);
            this.descriptionTV.setTextSize(18.0f);
            this.descriptionMoreTV.setTextSize(18.0f);
            return;
        }
        this.wnameTV.setTextSize(13.0f);
        this.descriptionTV.setTextSize(15.0f);
        this.descriptionMoreTV.setTextSize(15.0f);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        RecommendDna recommendDna = (RecommendDna) view.getTag();
        if (recommendDna == null || this.clickedListener == null) {
            return;
        }
        RecommendMtaUtils.aggregationClickMtaRealize(this.itemView.getContext(), recommendDna.sourceValue, this.from, recommendDna.extension_id);
        this.clickedListener.onRecommendChannelJump(recommendDna);
    }

    public void setDna(RecommendDna recommendDna, JDDisplayImageOptions jDDisplayImageOptions, ExpoDataStore expoDataStore, ExpoDataStore expoDataStore2, int i2) {
        RecommendProduct recommendProduct;
        String str;
        if (recommendDna == null) {
            return;
        }
        this.itemView.setTag(recommendDna);
        this.itemView.setOnClickListener(this);
        this.from = i2;
        setFrom(i2);
        initTextSize();
        List<RecommendProduct> list = recommendDna.wareList;
        if (list == null || list.isEmpty()) {
            recommendProduct = null;
        } else {
            recommendProduct = recommendDna.wareList.get(0);
            if (recommendProduct != null && (this.productImage.getDrawable() == null || (str = this.imageUrl) == null || !str.equals(recommendProduct.imgUrl))) {
                String str2 = recommendProduct.imgUrl;
                this.imageUrl = str2;
                JDImageUtils.displayImage(str2, this.productImage, jDDisplayImageOptions);
            }
        }
        if (TextUtils.isEmpty(recommendDna.mergePicUrl)) {
            this.productBg.setImageResource(R.drawable.recommend_monetization);
        } else {
            JDImageUtils.displayImage(recommendDna.mergePicUrl, this.productBg, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendMonetizationViewHolder.1
                {
                    RecommendMonetizationViewHolder.this = this;
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str3, View view) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str3, View view, Bitmap bitmap) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str3, View view, JDFailReason jDFailReason) {
                    RecommendMonetizationViewHolder.this.productBg.setImageResource(R.drawable.recommend_monetization);
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingStarted(String str3, View view) {
                }
            });
        }
        if (TextUtils.isEmpty(recommendDna.dnaName)) {
            this.wnameTV.setVisibility(8);
        } else {
            this.wnameTV.setVisibility(0);
            this.wnameTV.setText(recommendDna.dnaName);
            try {
                if (!TextUtils.isEmpty(recommendDna.themeBgcolorStart) && !TextUtils.isEmpty(recommendDna.themeBgcolorEnd)) {
                    this.colors[0] = Color.parseColor(recommendDna.themeBgcolorStart);
                    this.colors[1] = Color.parseColor(recommendDna.themeBgcolorEnd);
                    TextView textView = this.wnameTV;
                    textView.setBackgroundDrawable(getDrawable(textView, this.colors));
                }
            } catch (Exception e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                }
            }
        }
        this.descriptionTV.setText(recommendDna.description);
        this.descriptionMoreTV.setText(recommendDna.descriptionMore);
        if (!TextUtils.isEmpty(recommendDna.descriptionMore) && !TextUtils.isEmpty(recommendDna.fontColor)) {
            try {
                this.descriptionMoreTV.setTextColor(Color.parseColor(recommendDna.fontColor));
            } catch (Exception e3) {
                if (OKLog.D) {
                    e3.printStackTrace();
                }
            }
        }
        this.dot.setVisibility((!recommendDna.isShowDot() || recommendDna.isMonetized) ? 8 : 0);
        this.leftBottomDot.setVisibility((recommendDna.isShowDot() && recommendDna.isMonetized) ? 0 : 8);
        if (expoDataStore2 != null) {
            if (!TextUtils.isEmpty(recommendDna.exposureJsonSourceValue)) {
                expoDataStore2.putExpoJsonDada(recommendDna.exposureJsonSourceValue);
            } else if (!TextUtils.isEmpty(recommendDna.exposureSourceValue)) {
                expoDataStore2.putExpoData(recommendDna.exposureSourceValue);
            }
        }
        realExpo(recommendDna.client_exposal_url, recommendProduct != null ? recommendProduct.wareId : null);
    }
}
