package com.jingdong.common.recommend.forlist;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.entity.RecommendDna;
import com.jingdong.common.utils.JDImageUtils;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class RecommendPDCouponViewHolder extends BaseRecommendViewHolder {
    private BaseActivity baseActivity;
    private TextView couponNum;
    private int from;
    private SimpleDraweeView goCoupon;
    private SimpleDraweeView image;
    private View.OnClickListener onClickListener;
    private TextView title;

    public RecommendPDCouponViewHolder(BaseActivity baseActivity, View view) {
        super(view);
        this.onClickListener = new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendPDCouponViewHolder.2
            {
                RecommendPDCouponViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RecommendDna recommendDna = (RecommendDna) view2.getTag();
                if (recommendDna != null) {
                    RecommendPDCouponViewHolder recommendPDCouponViewHolder = RecommendPDCouponViewHolder.this;
                    if (recommendPDCouponViewHolder.clickedListener != null) {
                        RecommendMtaUtils.addCartRecyclingClickMta(recommendPDCouponViewHolder.itemView.getContext(), recommendDna.sourceValue, RecommendPDCouponViewHolder.this.from, recommendDna.extension_id);
                        RecommendPDCouponViewHolder.this.clickedListener.onRecommendJump(recommendDna.channelJumpUrl, recommendDna.isOpenApp);
                    }
                }
            }
        };
        this.baseActivity = baseActivity;
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.recommend_coupon_img);
        this.image = simpleDraweeView;
        simpleDraweeView.setAspectRatio(1.0f);
        this.title = (TextView) view.findViewById(R.id.recommend_coupon_title);
        this.couponNum = (TextView) view.findViewById(R.id.recommend_coupon_num);
        this.goCoupon = (SimpleDraweeView) view.findViewById(R.id.recommend_get_coupon);
    }

    private void setTitle(RecommendDna recommendDna) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(recommendDna.couponIcon);
        this.title.setText(getSpannableString(arrayList, recommendDna.dnaName, this.title));
    }

    public void setData(RecommendDna recommendDna, JDDisplayImageOptions jDDisplayImageOptions, ExpoDataStore expoDataStore, int i2) {
        if (recommendDna != null) {
            this.from = i2;
            JDImageUtils.displayImage(recommendDna.imageurl, this.image, jDDisplayImageOptions);
            setTitle(recommendDna);
            this.couponNum.setText(recommendDna.couponNumText);
            if (!TextUtils.isEmpty(recommendDna.goCouponBtnImg)) {
                JDImageUtils.displayImage(recommendDna.goCouponBtnImg, this.goCoupon, jDDisplayImageOptions, false, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendPDCouponViewHolder.1
                    {
                        RecommendPDCouponViewHolder.this = this;
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingCancelled(String str, View view) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                        JDImageUtils.displayImage("res:///" + R.drawable.recommend_pd_get_coupon, RecommendPDCouponViewHolder.this.goCoupon);
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingStarted(String str, View view) {
                    }
                }, null);
            } else {
                JDImageUtils.displayImage("res:///" + R.drawable.recommend_pd_get_coupon, this.goCoupon);
            }
            if (expoDataStore != null) {
                if (!TextUtils.isEmpty(recommendDna.exposureJsonSourceValue)) {
                    expoDataStore.putExpoJsonDada(recommendDna.exposureJsonSourceValue);
                } else if (!TextUtils.isEmpty(recommendDna.exposureSourceValue)) {
                    expoDataStore.putExpoData(recommendDna.exposureSourceValue);
                }
            }
            this.itemView.setTag(recommendDna);
            this.itemView.setOnClickListener(this.onClickListener);
        }
    }
}
