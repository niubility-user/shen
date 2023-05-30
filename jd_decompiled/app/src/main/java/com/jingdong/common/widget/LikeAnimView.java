package com.jingdong.common.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.common.R;

/* loaded from: classes12.dex */
public class LikeAnimView extends LinearLayout {
    private AnimationDrawable likeAnimDrawable;

    public LikeAnimView(Context context) {
        this(context, null);
    }

    private void init() {
        setOrientation(1);
        setGravity(1);
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(getContext());
        simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        simpleDraweeView.setImageResource(R.drawable.video_live_anim_fx_like);
        this.likeAnimDrawable = (AnimationDrawable) simpleDraweeView.getDrawable();
        addView(simpleDraweeView, new LinearLayout.LayoutParams(-2, -2));
        SimpleDraweeView simpleDraweeView2 = new SimpleDraweeView(getContext());
        simpleDraweeView2.setScaleType(ImageView.ScaleType.FIT_XY);
        simpleDraweeView2.setImageResource(R.drawable.live_list_thumbup);
        addView(simpleDraweeView2, new LinearLayout.LayoutParams(-2, -2));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        AnimationDrawable animationDrawable = this.likeAnimDrawable;
        if (animationDrawable != null) {
            animationDrawable.start();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AnimationDrawable animationDrawable = this.likeAnimDrawable;
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
    }

    public LikeAnimView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }
}
