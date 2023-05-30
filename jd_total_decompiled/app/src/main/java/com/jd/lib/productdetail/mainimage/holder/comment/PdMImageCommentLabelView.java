package com.jd.lib.productdetail.mainimage.holder.comment;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentInfo;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.core.views.PdAutoChangeTextSize;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.old.PdMCommentLabelFlowLayout;
import com.jingdong.jdsdk.utils.FontsUtil;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMImageCommentLabelView extends ConstraintLayout {

    /* renamed from: g  reason: collision with root package name */
    public PdMCommentLabelFlowLayout f4718g;

    public PdMImageCommentLabelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a(List<PdCommentInfo.CommentTag> list) {
        if (list != null) {
            this.f4718g.removeAllViews();
            for (PdCommentInfo.CommentTag commentTag : list) {
                if (!TextUtils.isEmpty(commentTag.name)) {
                    PdAutoChangeTextSize pdAutoChangeTextSize = new PdAutoChangeTextSize(getContext());
                    pdAutoChangeTextSize.setGravity(17);
                    pdAutoChangeTextSize.setTextSize(2, 12.0f);
                    pdAutoChangeTextSize.setTextColor(getResources().getColor(R.color.lib_pd_image_color_FA2C19));
                    int dip2px = PDUtils.dip2px(12.0f);
                    int dip2px2 = PDUtils.dip2px(6.0f);
                    pdAutoChangeTextSize.setPadding(dip2px, dip2px2, dip2px, dip2px2 - 1);
                    pdAutoChangeTextSize.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
                    pdAutoChangeTextSize.setText(commentTag.name + commentTag.count);
                    FontsUtil.changeTextFont(pdAutoChangeTextSize);
                    ViewCompat.setBackground(pdAutoChangeTextSize, getResources().getDrawable(com.jd.lib.productdetail.core.R.drawable.lib_pd_core_comment_tag_bg));
                    this.f4718g.addView(pdAutoChangeTextSize);
                }
            }
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        PdMCommentLabelFlowLayout pdMCommentLabelFlowLayout = (PdMCommentLabelFlowLayout) findViewById(R.id.lib_pd_holder_topimage_item_comment_label_container);
        this.f4718g = pdMCommentLabelFlowLayout;
        if (pdMCommentLabelFlowLayout != null) {
            int dip2px = PDUtils.dip2px(12.0f);
            PdMCommentLabelFlowLayout pdMCommentLabelFlowLayout2 = this.f4718g;
            pdMCommentLabelFlowLayout2.f4988h = dip2px;
            pdMCommentLabelFlowLayout2.f4989i = dip2px;
            pdMCommentLabelFlowLayout2.a(1);
        }
    }
}
