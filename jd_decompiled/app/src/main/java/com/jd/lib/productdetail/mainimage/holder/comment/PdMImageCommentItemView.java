package com.jd.lib.productdetail.mainimage.holder.comment;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentItemInfo;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentPictureInfo;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.old.PdMImageGradientRatingBar;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMImageCommentItemView extends ConstraintLayout {

    /* renamed from: g  reason: collision with root package name */
    public SimpleDraweeView f4711g;

    /* renamed from: h  reason: collision with root package name */
    public TextView f4712h;

    /* renamed from: i  reason: collision with root package name */
    public PdMImageGradientRatingBar f4713i;

    /* renamed from: j  reason: collision with root package name */
    public TextView f4714j;

    /* renamed from: k  reason: collision with root package name */
    public SimpleDraweeView f4715k;

    /* renamed from: l  reason: collision with root package name */
    public TextView f4716l;

    public PdMImageCommentItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a(PdCommentItemInfo pdCommentItemInfo, int i2) {
        this.f4712h.setText(pdCommentItemInfo.userNickName);
        ViewGroup.LayoutParams layoutParams = this.f4711g.getLayoutParams();
        if (i2 == 2) {
            this.f4712h.setTextSize(2, 14.0f);
            if (getContext() != null) {
                this.f4714j.setTextColor(getContext().getResources().getColor(R.color.lib_pd_image_color_1A1A1A));
            }
            this.f4714j.setTextSize(2, 14.0f);
            layoutParams.height = PDUtils.dip2px(27.0f);
            layoutParams.width = PDUtils.dip2px(27.0f);
        } else {
            this.f4712h.setTextSize(2, 12.0f);
            this.f4714j.setTextSize(2, 12.0f);
            if (getContext() != null) {
                this.f4714j.setTextColor(getContext().getResources().getColor(R.color.lib_pd_image_color_808080));
            }
            layoutParams.height = PDUtils.dip2px(22.0f);
            layoutParams.width = PDUtils.dip2px(22.0f);
        }
        this.f4714j.setText(PDUtils.fromHtml(pdCommentItemInfo.commentData));
        JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
        jDDisplayImageOptions.setRoundingParams(new RoundingParams().setRoundAsCircle(true));
        int i3 = R.drawable.lib_pd_mainimage_holder_item_comment_user_pic_default_bg;
        jDDisplayImageOptions.showImageOnFail(i3);
        jDDisplayImageOptions.showImageForEmptyUri(i3);
        jDDisplayImageOptions.showImageOnLoading(i3);
        JDImageLoader.display(pdCommentItemInfo.userImgUrl, this.f4711g, jDDisplayImageOptions);
        if (!TextUtils.isEmpty(pdCommentItemInfo.commentScore)) {
            this.f4713i.setVisibility(0);
            float parseFloat = Float.parseFloat(pdCommentItemInfo.commentScore);
            PdMImageGradientRatingBar pdMImageGradientRatingBar = this.f4713i;
            if (parseFloat < 0.0f) {
                parseFloat = 5.0f;
            }
            pdMImageGradientRatingBar.setRating(parseFloat);
            this.f4713i.setVisibility(0);
        } else {
            this.f4713i.setVisibility(8);
        }
        List<PdCommentPictureInfo> list = pdCommentItemInfo.pictureInfoList;
        if (list != null && list.size() > 0) {
            this.f4715k.setVisibility(0);
            JDDisplayImageOptions jDDisplayImageOptions2 = new JDDisplayImageOptions();
            float dip2px = PDUtils.dip2px(6.0f);
            jDDisplayImageOptions2.setRoundingParams(RoundingParams.fromCornersRadii(dip2px, dip2px, dip2px, dip2px));
            int i4 = R.drawable.lib_pd_mainimage_holder_item_comment_default_bg;
            jDDisplayImageOptions2.showImageOnFail(i4);
            jDDisplayImageOptions2.showImageForEmptyUri(i4);
            jDDisplayImageOptions2.showImageOnLoading(i4);
            JDImageLoader.display(pdCommentItemInfo.pictureInfoList.get(0).picURL, this.f4715k, jDDisplayImageOptions2);
            if (pdCommentItemInfo.pictureInfoList.size() > 1) {
                this.f4716l.setText(getResources().getString(R.string.lib_pd_image_topimage_item_holder_comment_pic_count, (pdCommentItemInfo.pictureInfoList.size() - 1) + ""));
                this.f4716l.setVisibility(0);
                return;
            }
            this.f4716l.setVisibility(8);
            return;
        }
        this.f4715k.setVisibility(8);
        this.f4716l.setVisibility(8);
    }

    public void b(boolean z) {
        if (z) {
            this.f4711g.setBackgroundResource(R.drawable.lib_pd_mainimage_holder_item_comment_user_pic_default_bg);
            this.f4713i.setVisibility(8);
            this.f4712h.setMinWidth(PDUtils.dip2px(120.0f));
            TextView textView = this.f4712h;
            int i2 = R.drawable.lib_pd_mainimage_holder_item_comment_default_bg;
            textView.setBackgroundResource(i2);
            this.f4714j.setBackgroundResource(i2);
            return;
        }
        this.f4711g.setBackgroundResource(0);
        this.f4713i.setVisibility(0);
        this.f4712h.setMinWidth(1);
        this.f4712h.setBackgroundResource(0);
        this.f4714j.setBackgroundResource(0);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f4711g = (SimpleDraweeView) findViewById(R.id.lib_pd_holder_topimage_item_comment_item_icon);
        this.f4712h = (TextView) findViewById(R.id.lib_pd_holder_topimage_item_comment_item_user_name);
        this.f4713i = (PdMImageGradientRatingBar) findViewById(R.id.lib_pd_holder_topimage_item_comment_item_rating);
        this.f4714j = (TextView) findViewById(R.id.lib_pd_holder_topimage_item_comment_item_detail);
        this.f4715k = (SimpleDraweeView) findViewById(R.id.lib_pd_holder_topimage_item_comment_item_pic);
        this.f4716l = (TextView) findViewById(R.id.lib_pd_holder_topimage_item_comment_item_pic_count);
        this.f4713i.setVisibility(8);
        this.f4716l.setVisibility(8);
    }
}
