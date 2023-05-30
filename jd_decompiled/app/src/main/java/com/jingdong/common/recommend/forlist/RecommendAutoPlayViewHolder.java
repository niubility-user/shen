package com.jingdong.common.recommend.forlist;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import androidx.cardview.widget.CardView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.entity.RecommendDna;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.List;

/* loaded from: classes6.dex */
public class RecommendAutoPlayViewHolder extends BaseRecommendViewHolder {
    private SimpleDraweeView autoPlayImg;
    private View.OnClickListener clickListener;
    private View dot;
    private int from;
    private String imageUrl;
    private View leftBottomDot;
    private CardView rootView;

    public RecommendAutoPlayViewHolder(View view) {
        super(view);
        this.imageUrl = null;
        this.clickListener = new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendAutoPlayViewHolder.1
            {
                RecommendAutoPlayViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RecommendDna recommendDna = (RecommendDna) view2.getTag();
                if (recommendDna != null) {
                    RecommendAutoPlayViewHolder recommendAutoPlayViewHolder = RecommendAutoPlayViewHolder.this;
                    if (recommendAutoPlayViewHolder.clickedListener != null) {
                        RecommendMtaUtils.aggregationClickMtaRealize(recommendAutoPlayViewHolder.itemView.getContext(), recommendDna.sourceValue, RecommendAutoPlayViewHolder.this.from, recommendDna.extension_id);
                        if (!TextUtils.isEmpty(recommendDna.jump)) {
                            RecommendAutoPlayViewHolder.this.clickedListener.onRecommendJump(recommendDna.jump, recommendDna.isOpenApp);
                        } else if (!TextUtils.isEmpty(recommendDna.channelJumpUrl)) {
                            RecommendAutoPlayViewHolder.this.clickedListener.onRecommendJump(recommendDna.channelJumpUrl, recommendDna.isOpenApp);
                        }
                        if (TextUtils.isEmpty(recommendDna.client_click_url)) {
                            return;
                        }
                        RecommendAutoPlayViewHolder.this.clickedListener.onRecommendMoneyExpo(recommendDna.client_click_url);
                    }
                }
            }
        };
        this.rootView = (CardView) view.findViewById(R.id.recommend_auto_play_root);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.recommend_auto_play_img);
        this.autoPlayImg = simpleDraweeView;
        simpleDraweeView.setAspectRatio(0.6666667f);
        this.dot = view.findViewById(R.id.recommend_dot);
        this.leftBottomDot = view.findViewById(R.id.recommend_left_dot);
    }

    public void setData(RecommendDna recommendDna, JDDisplayImageOptions jDDisplayImageOptions, ExpoDataStore expoDataStore, int i2) {
        RecommendProduct recommendProduct;
        String str;
        if (recommendDna != null) {
            this.from = i2;
            setFrom(i2);
            if (jDDisplayImageOptions == null) {
                jDDisplayImageOptions = new JDDisplayImageOptions().bitmapConfig(Bitmap.Config.RGB_565).setReferer(RecommendUtils.HTTP_REFER);
            }
            List<RecommendProduct> list = recommendDna.wareList;
            if (list == null || list.size() <= 0) {
                recommendProduct = null;
            } else {
                recommendProduct = recommendDna.wareList.get(0);
                if (recommendProduct != null && (this.autoPlayImg.getDrawable() == null || (str = this.imageUrl) == null || !str.equals(recommendProduct.imgUrl))) {
                    String str2 = recommendProduct.imgUrl;
                    this.imageUrl = str2;
                    JDImageUtils.displayImage(str2, this.autoPlayImg, jDDisplayImageOptions);
                }
            }
            int i3 = 8;
            this.dot.setVisibility((!recommendDna.isShowDot() || recommendDna.isMonetized) ? 8 : 0);
            View view = this.leftBottomDot;
            if (recommendDna.isShowDot() && recommendDna.isMonetized) {
                i3 = 0;
            }
            view.setVisibility(i3);
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
            if (recommendDna.opmShowTimes <= 0 || !recommendDna.expoNumAdd || TextUtils.isEmpty(recommendDna.dnaId)) {
                return;
            }
            recommendDna.expoNumAdd = false;
            String[] expoID_Num = RecommendUtils.getExpoID_Num();
            if (recommendDna.dnaId.equals(expoID_Num[0])) {
                try {
                    RecommendUtils.saveExpoID_Num(recommendDna.dnaId, Integer.parseInt(expoID_Num[1]) + 1);
                    return;
                } catch (Exception e2) {
                    if (OKLog.D) {
                        e2.printStackTrace();
                        return;
                    }
                    return;
                }
            }
            RecommendUtils.saveExpoID_Num(recommendDna.dnaId, 1);
        }
    }

    public void setTemplateEightData(RecommendDna recommendDna, JDDisplayImageOptions jDDisplayImageOptions, ExpoDataStore expoDataStore, int i2) {
        String str;
        if (recommendDna != null) {
            this.from = i2;
            setFrom(i2);
            if (this.autoPlayImg.getDrawable() == null || (str = this.imageUrl) == null || !str.equals(recommendDna.img)) {
                String str2 = recommendDna.img;
                this.imageUrl = str2;
                JDImageUtils.displayImage(str2, this.autoPlayImg, jDDisplayImageOptions);
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
            realExpo(recommendDna.client_exposal_url, recommendDna.wareId);
        }
    }
}
