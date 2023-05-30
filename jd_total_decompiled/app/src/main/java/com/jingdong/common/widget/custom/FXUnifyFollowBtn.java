package com.jingdong.common.widget.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.jingdong.common.R;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes12.dex */
public class FXUnifyFollowBtn extends FaXianFollowBtn {
    private final GradientDrawable followBGSrc;
    private final View follow_gift;
    private final GradientDrawable followedBGSrc;

    public FXUnifyFollowBtn(Context context) {
        this(context, null);
    }

    private GradientDrawable buildFollowGradientDrawable() {
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{Color.parseColor("#fa4454"), Color.parseColor("#eb1d5a")});
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(DPIUtil.dip2px(12.5f));
        gradientDrawable.setGradientType(0);
        return gradientDrawable;
    }

    private GradientDrawable buildFollowedGradientDrawable() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(DPIUtil.dip2px(12.5f));
        gradientDrawable.setStroke(2, Color.parseColor("#9b9b9b"));
        return gradientDrawable;
    }

    @Override // com.jingdong.common.widget.custom.FaXianFollowBtn
    public void setFollowed() {
        super.setFollowed();
        this.follow_gift.setVisibility(8);
        setBackgroundDrawable(this.followedBGSrc);
    }

    @Override // com.jingdong.common.widget.custom.FaXianFollowBtn
    public void setNoFollow() {
        super.setNoFollow();
        if (this.needFollowGift == 1) {
            this.follow.setVisibility(8);
            this.follow_gift.setVisibility(0);
        } else {
            this.follow_gift.setVisibility(8);
        }
        setBackgroundDrawable(this.followBGSrc);
    }

    public FXUnifyFollowBtn(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        removeAllViews();
        View inflate = ImageUtil.inflate(context, R.layout.layout_faxian_unify_follow, this, true);
        this.follow = inflate.findViewById(R.id.follow);
        this.followed = inflate.findViewById(R.id.followed);
        this.follow_gift = inflate.findViewById(R.id.follow_gift);
        GradientDrawable buildFollowGradientDrawable = buildFollowGradientDrawable();
        this.followBGSrc = buildFollowGradientDrawable;
        GradientDrawable buildFollowedGradientDrawable = buildFollowedGradientDrawable();
        this.followedBGSrc = buildFollowedGradientDrawable;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FXUnifyFollowBtnAttrs);
            if (obtainStyledAttributes.getInteger(R.styleable.FXUnifyFollowBtnAttrs_textStyle, 0) == 1) {
                ((TextView) inflate.findViewById(R.id.follow_text)).setTextSize(1, 13.0f);
                ((TextView) this.followed).setTextSize(1, 12.0f);
                buildFollowGradientDrawable.setCornerRadius(DPIUtil.dip2px(30.0f));
                buildFollowedGradientDrawable.setCornerRadius(DPIUtil.dip2px(30.0f));
            }
            obtainStyledAttributes.recycle();
        }
    }
}
