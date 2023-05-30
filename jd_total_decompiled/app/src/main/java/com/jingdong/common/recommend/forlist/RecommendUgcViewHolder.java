package com.jingdong.common.recommend.forlist;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.entity.RecommendVideo;
import com.jingdong.common.ui.JDCircleImageView;
import com.jingdong.common.utils.JDImageUtils;

/* loaded from: classes6.dex */
public class RecommendUgcViewHolder extends BaseRecommendViewHolder {
    private JDCircleImageView authorIv;
    private TextView authorNameTv;
    private View dot;
    private int from;
    private SimpleDraweeView imageView;
    private View leftBottomDot;
    private View.OnClickListener onItemClickListener;
    private TextView ugcDesTv;

    public RecommendUgcViewHolder(View view) {
        super(view);
        this.onItemClickListener = new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendUgcViewHolder.1
            {
                RecommendUgcViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RecommendVideo recommendVideo = (RecommendVideo) view2.getTag();
                if (recommendVideo != null) {
                    RecommendUgcViewHolder recommendUgcViewHolder = RecommendUgcViewHolder.this;
                    if (recommendUgcViewHolder.clickedListener != null) {
                        RecommendMtaUtils.aggregationClickMtaRealize(recommendUgcViewHolder.itemView.getContext(), recommendVideo.sourceValue, RecommendUgcViewHolder.this.from, recommendVideo.extension_id);
                        RecommendUgcViewHolder.this.clickedListener.onRecommendJump(recommendVideo.channelJumpUrl, recommendVideo.isOpenApp);
                    }
                }
            }
        };
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.recommend_ugc_image);
        this.imageView = simpleDraweeView;
        simpleDraweeView.setAspectRatio(1.0f);
        this.ugcDesTv = (TextView) view.findViewById(R.id.recommend_ugc_des);
        this.authorIv = (JDCircleImageView) view.findViewById(R.id.recommend_ugc_pic);
        this.authorNameTv = (TextView) view.findViewById(R.id.recommend_ugc_author_name);
        this.dot = view.findViewById(R.id.recommend_dot);
        this.leftBottomDot = view.findViewById(R.id.recommend_left_dot);
    }

    public void setData(RecommendVideo recommendVideo, ExpoDataStore expoDataStore, int i2) {
        if (recommendVideo == null) {
            return;
        }
        this.from = i2;
        this.ugcDesTv.setText(recommendVideo.wname);
        JDImageUtils.displayImage(recommendVideo.imageurl, this.imageView);
        JDImageUtils.displayImage(recommendVideo.authorPic, this.authorIv);
        this.authorNameTv.setText(recommendVideo.authorName);
        this.dot.setVisibility((!recommendVideo.isShowDot() || recommendVideo.isMonetized) ? 8 : 0);
        this.leftBottomDot.setVisibility((recommendVideo.isShowDot() && recommendVideo.isMonetized) ? 0 : 8);
        if (expoDataStore != null) {
            if (!TextUtils.isEmpty(recommendVideo.exposureJsonSourceValue)) {
                expoDataStore.putExpoJsonDada(recommendVideo.exposureJsonSourceValue);
            } else if (!TextUtils.isEmpty(recommendVideo.exposureSourceValue)) {
                expoDataStore.putExpoData(recommendVideo.exposureSourceValue);
            }
        }
        this.itemView.setTag(recommendVideo);
        this.itemView.setOnClickListener(this.onItemClickListener);
    }
}
