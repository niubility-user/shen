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
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.old.PdMImageGradientRatingBar;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.util.image.JDDisplayImageOptions;

/* loaded from: classes15.dex */
public class PdMImageCommentItemNewView extends ConstraintLayout {

    /* renamed from: g  reason: collision with root package name */
    public SimpleDraweeView f4707g;

    /* renamed from: h  reason: collision with root package name */
    public TextView f4708h;

    /* renamed from: i  reason: collision with root package name */
    public PdMImageGradientRatingBar f4709i;

    /* renamed from: j  reason: collision with root package name */
    public TextView f4710j;

    public PdMImageCommentItemNewView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a(PdCommentItemInfo pdCommentItemInfo, int i2) {
        this.f4708h.setText(pdCommentItemInfo.userNickName);
        this.f4710j.setText(PDUtils.fromHtml(pdCommentItemInfo.commentData));
        ViewGroup.LayoutParams layoutParams = this.f4707g.getLayoutParams();
        if (i2 == 2) {
            this.f4710j.setMaxLines(1);
            this.f4710j.setMinLines(1);
            this.f4708h.setTextSize(2, 14.0f);
            if (getContext() != null) {
                this.f4710j.setTextColor(getContext().getResources().getColor(R.color.lib_pd_image_color_1A1A1A));
            }
            layoutParams.height = PDUtils.dip2px(27.0f);
            layoutParams.width = PDUtils.dip2px(27.0f);
        } else {
            this.f4710j.setMaxLines(2);
            this.f4710j.setMinLines(2);
            this.f4708h.setTextSize(2, 12.0f);
            if (getContext() != null) {
                this.f4710j.setTextColor(getContext().getResources().getColor(R.color.lib_pd_image_color_808080));
            }
            layoutParams.height = PDUtils.dip2px(24.0f);
            layoutParams.width = PDUtils.dip2px(24.0f);
        }
        JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
        jDDisplayImageOptions.setRoundingParams(new RoundingParams().setRoundAsCircle(true));
        int i3 = R.drawable.lib_pd_mainimage_holder_item_comment_user_pic_default_bg;
        jDDisplayImageOptions.showImageOnFail(i3);
        jDDisplayImageOptions.showImageForEmptyUri(i3);
        jDDisplayImageOptions.showImageOnLoading(i3);
        JDImageLoader.display(pdCommentItemInfo.userImgUrl, this.f4707g, jDDisplayImageOptions);
        if (!TextUtils.isEmpty(pdCommentItemInfo.commentScore)) {
            this.f4709i.setVisibility(0);
            float parseFloat = Float.parseFloat(pdCommentItemInfo.commentScore);
            PdMImageGradientRatingBar pdMImageGradientRatingBar = this.f4709i;
            if (parseFloat < 0.0f) {
                parseFloat = 5.0f;
            }
            pdMImageGradientRatingBar.setRating(parseFloat);
            this.f4709i.setVisibility(0);
            return;
        }
        this.f4709i.setVisibility(8);
    }

    public void b(boolean z) {
        if (z) {
            this.f4707g.setBackgroundResource(R.drawable.lib_pd_mainimage_holder_item_comment_user_pic_default_bg);
            this.f4709i.setVisibility(8);
            this.f4708h.setMinWidth(PDUtils.dip2px(120.0f));
            TextView textView = this.f4708h;
            int i2 = R.drawable.lib_pd_mainimage_holder_item_comment_default_bg;
            textView.setBackgroundResource(i2);
            this.f4710j.setBackgroundResource(i2);
            return;
        }
        this.f4707g.setBackgroundResource(0);
        this.f4709i.setVisibility(0);
        this.f4708h.setMinWidth(1);
        this.f4708h.setBackgroundResource(0);
        this.f4710j.setBackgroundResource(0);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f4707g = (SimpleDraweeView) findViewById(R.id.lib_pd_holder_topimage_item_comment_item_icon_new);
        this.f4708h = (TextView) findViewById(R.id.lib_pd_holder_topimage_item_comment_item_user_name_new);
        this.f4709i = (PdMImageGradientRatingBar) findViewById(R.id.lib_pd_holder_topimage_item_comment_item_rating_new);
        this.f4710j = (TextView) findViewById(R.id.lib_pd_holder_topimage_item_comment_item_detail_new);
        this.f4709i.setVisibility(8);
    }
}
