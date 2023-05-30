package com.jingdong.common.recommend.forlist;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
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
import com.jingdong.sdk.utils.DPIUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class RecommendContentMaterialViewHolder extends BaseRecommendViewHolder {
    private View.OnClickListener clickListener;
    private View dot;
    private int from;
    private String imageUrl;
    private View leftBottomDot;
    private SimpleDraweeView materialBg;
    private SimpleDraweeView materialButton;
    private TextView materialDescription;
    private TextView materialDescriptionMore;
    private TextView materialName;
    private SimpleDraweeView productImg;
    private View rootView;
    private int themeColorEnd;
    private int themeColorStart;

    public RecommendContentMaterialViewHolder(View view) {
        super(view);
        this.imageUrl = null;
        this.clickListener = new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendContentMaterialViewHolder.1
            {
                RecommendContentMaterialViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RecommendDna recommendDna = (RecommendDna) view2.getTag();
                if (recommendDna != null) {
                    RecommendContentMaterialViewHolder recommendContentMaterialViewHolder = RecommendContentMaterialViewHolder.this;
                    if (recommendContentMaterialViewHolder.clickedListener != null) {
                        RecommendMtaUtils.aggregationClickMtaRealize(recommendContentMaterialViewHolder.itemView.getContext(), recommendDna.sourceValue, RecommendContentMaterialViewHolder.this.from, recommendDna.extension_id);
                        RecommendContentMaterialViewHolder.this.clickedListener.onRecommendJump(recommendDna.channelJumpUrl, recommendDna.isOpenApp);
                        if (TextUtils.isEmpty(recommendDna.client_click_url)) {
                            return;
                        }
                        RecommendContentMaterialViewHolder.this.clickedListener.onRecommendMoneyExpo(recommendDna.client_click_url);
                    }
                }
            }
        };
        this.rootView = view;
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.recommend_content_material_bg);
        this.materialBg = simpleDraweeView;
        simpleDraweeView.setAspectRatio(this.bgAspectRatio);
        SimpleDraweeView simpleDraweeView2 = (SimpleDraweeView) view.findViewById(R.id.recommend_content_material_img);
        this.productImg = simpleDraweeView2;
        simpleDraweeView2.setAspectRatio(0.98f);
        this.materialName = (TextView) view.findViewById(R.id.recommend_content_material_name);
        this.materialButton = (SimpleDraweeView) view.findViewById(R.id.recommend_content_material_button);
        this.materialDescription = (TextView) view.findViewById(R.id.recommend_content_material_description);
        this.materialDescriptionMore = (TextView) view.findViewById(R.id.recommend_content_material_description_more);
        this.dot = view.findViewById(R.id.recommend_dot);
        this.leftBottomDot = view.findViewById(R.id.recommend_left_dot);
    }

    private void initTextSize() {
        if (ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode())) {
            this.materialName.setTextSize(13.0f);
            this.materialDescription.setTextSize(18.0f);
            this.materialDescriptionMore.setTextSize(16.0f);
            return;
        }
        this.materialName.setTextSize(11.0f);
        this.materialDescription.setTextSize(15.0f);
        this.materialDescriptionMore.setTextSize(13.0f);
    }

    private void setMaterialBackground(SimpleDraweeView simpleDraweeView, String str, JDDisplayImageOptions jDDisplayImageOptions) {
        if (!TextUtils.isEmpty(str)) {
            JDImageUtils.displayImage(str, simpleDraweeView, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendContentMaterialViewHolder.2
                {
                    RecommendContentMaterialViewHolder.this = this;
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str2, View view) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                    RecommendContentMaterialViewHolder.this.materialBg.setImageResource(R.drawable.recommend_content_material_bg);
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingStarted(String str2, View view) {
                }
            });
        } else {
            this.materialBg.setImageResource(R.drawable.recommend_content_material_bg);
        }
    }

    private void setMaterialButton(SimpleDraweeView simpleDraweeView, String str) {
        if (!TextUtils.isEmpty(str)) {
            JDImageUtils.displayImage(str, simpleDraweeView, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendContentMaterialViewHolder.3
                {
                    RecommendContentMaterialViewHolder.this = this;
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str2, View view) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                    RecommendContentMaterialViewHolder.this.materialButton.setImageResource(R.drawable.recommend_content_material_button);
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingStarted(String str2, View view) {
                }
            });
        } else {
            this.materialButton.setImageResource(R.drawable.recommend_content_material_button);
        }
    }

    private void setMaterialGradient() {
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[]{this.themeColorStart, this.themeColorEnd});
        gradientDrawable.setCornerRadius(DPIUtil.dip2px(10.0f));
        this.materialName.setBackgroundDrawable(gradientDrawable);
    }

    public void setData(RecommendDna recommendDna, JDDisplayImageOptions jDDisplayImageOptions, ExpoDataStore expoDataStore, int i2) {
        RecommendProduct recommendProduct;
        String str;
        if (recommendDna != null) {
            this.from = i2;
            initTextSize();
            if (jDDisplayImageOptions == null) {
                jDDisplayImageOptions = new JDDisplayImageOptions().bitmapConfig(Bitmap.Config.RGB_565);
            }
            List<RecommendProduct> list = recommendDna.wareList;
            if (list == null || list.size() <= 0) {
                recommendProduct = null;
            } else {
                recommendProduct = recommendDna.wareList.get(0);
                if (recommendProduct != null && (this.productImg.getDrawable() == null || (str = this.imageUrl) == null || !str.equals(recommendProduct.imgUrl))) {
                    String str2 = recommendProduct.imgUrl;
                    this.imageUrl = str2;
                    JDImageUtils.displayImage(str2, this.productImg, jDDisplayImageOptions);
                }
            }
            if (!TextUtils.isEmpty(recommendDna.themeBgcolorStart) && !TextUtils.isEmpty(recommendDna.themeBgcolorEnd)) {
                try {
                    this.themeColorStart = Color.parseColor(recommendDna.themeBgcolorStart);
                    this.themeColorEnd = Color.parseColor(recommendDna.themeBgcolorEnd);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    this.themeColorStart = Color.parseColor("#9AE4D4");
                    this.themeColorEnd = Color.parseColor("#5CE3C8");
                }
            } else {
                this.themeColorStart = Color.parseColor("#9AE4D4");
                this.themeColorEnd = Color.parseColor("#5CE3C8");
            }
            setMaterialBackground(this.materialBg, recommendDna.mergePicUrl, jDDisplayImageOptions);
            setMaterialButton(this.materialButton, recommendDna.buttonImg);
            if (!TextUtils.isEmpty(recommendDna.dnaName)) {
                this.materialName.setText(recommendDna.dnaName);
            }
            setMaterialGradient();
            if (!TextUtils.isEmpty(recommendDna.description)) {
                this.materialDescription.setText(recommendDna.description);
            }
            if (!TextUtils.isEmpty(recommendDna.descriptionMore)) {
                this.materialDescriptionMore.setText(recommendDna.descriptionMore);
            }
            if (!TextUtils.isEmpty(recommendDna.fontColor)) {
                try {
                    this.materialDescriptionMore.setTextColor(Color.parseColor(recommendDna.fontColor));
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            this.dot.setVisibility((!recommendDna.isShowDot() || recommendDna.isMonetized) ? 8 : 0);
            this.leftBottomDot.setVisibility((recommendDna.isShowDot() && recommendDna.isMonetized) ? 0 : 8);
            this.rootView.setTag(recommendDna);
            this.rootView.setOnClickListener(this.clickListener);
            if (expoDataStore != null) {
                if (!TextUtils.isEmpty(recommendDna.exposureJsonSourceValue)) {
                    expoDataStore.putExpoJsonDada(recommendDna.exposureJsonSourceValue);
                } else if (!TextUtils.isEmpty(recommendDna.exposureSourceValue)) {
                    expoDataStore.putExpoData(recommendDna.exposureSourceValue);
                }
            }
            realExpo(recommendDna.client_exposal_url, recommendProduct != null ? recommendProduct.wareId : null);
        }
    }
}
