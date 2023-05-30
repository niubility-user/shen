package com.jingdong.common.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.common.R;
import com.jingdong.common.ui.RoundConerDrawable;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.FontsUtil;

/* loaded from: classes12.dex */
public class LiveStatusTagLayout extends RelativeLayout {
    public static final int STATUS_LIVING = 1;
    public static final int STATUS_PREDICT = 0;
    public static final int STATUS_REPLAY = 2;
    private AnimationDrawable mAnimationDrawable;
    private int mStatus;
    private static final int TEXT_SIZE_PX = DPIUtil.getWidthByDesignValue750(20);
    private static final int INNER_PADDING = DPIUtil.getWidthByDesignValue750(14);
    private static final int LIVING_TAG_WIDTH = DPIUtil.getWidthByDesignValue750(88);
    private static final int PREDICT_REPLAY_TAG_WIDTH = DPIUtil.getWidthByDesignValue750(69);
    private static final int HEIGHT = DPIUtil.getWidthByDesignValue750(32);
    private static final int ANIM_WIDTH = DPIUtil.getWidthByDesignValue750(17);
    private static final int ANIM_HEIGHT = DPIUtil.getWidthByDesignValue750(16);

    public LiveStatusTagLayout(Context context) {
        this(context, null);
    }

    private RelativeLayout getLivingTagView() {
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        int i2 = INNER_PADDING;
        relativeLayout.setPadding(i2, 0, i2, 0);
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{-375478, -48001});
        gradientDrawable.setCornerRadius(HEIGHT / 2);
        relativeLayout.setBackgroundDrawable(gradientDrawable);
        TextView textView = new TextView(getContext());
        textView.setText(getContext().getResources().getString(R.string.live_status_tag_live));
        textView.setIncludeFontPadding(false);
        textView.setTextSize(0, TEXT_SIZE_PX);
        textView.setTextColor(-1);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(9);
        layoutParams.addRule(15);
        textView.setLayoutParams(layoutParams);
        relativeLayout.addView(textView);
        int[] iArr = {R.drawable.anim_live_tag1, R.drawable.anim_live_tag2, R.drawable.anim_live_tag3, R.drawable.anim_live_tag4};
        this.mAnimationDrawable = new AnimationDrawable();
        for (int i3 = 0; i3 < 4; i3++) {
            this.mAnimationDrawable.addFrame(getResources().getDrawable(iArr[i3]), 200);
        }
        this.mAnimationDrawable.setOneShot(false);
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(getContext());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(ANIM_WIDTH, ANIM_HEIGHT);
        layoutParams2.addRule(11);
        layoutParams2.addRule(15);
        simpleDraweeView.setLayoutParams(layoutParams2);
        simpleDraweeView.setImageDrawable(this.mAnimationDrawable);
        relativeLayout.addView(simpleDraweeView);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(LIVING_TAG_WIDTH, HEIGHT));
        return relativeLayout;
    }

    private TextView getPredictOrReplayTagView(int i2) {
        String string;
        TextView textView = new TextView(getContext());
        int[] iArr = {-375478, -48001};
        if (i2 == 0) {
            iArr[0] = -13580226;
            iArr[1] = -15675480;
        } else {
            iArr[0] = -11908534;
            iArr[1] = -11908534;
        }
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, iArr);
        int i3 = HEIGHT;
        gradientDrawable.setCornerRadius(i3 / 2);
        textView.setBackgroundDrawable(gradientDrawable);
        textView.setGravity(17);
        if (i2 == 0) {
            string = getContext().getResources().getString(R.string.jdlive_status_tag_predict);
        } else {
            string = getContext().getResources().getString(R.string.jdlive_status_tag_replay);
        }
        textView.setText(string);
        textView.setIncludeFontPadding(false);
        textView.setTextSize(0, TEXT_SIZE_PX);
        textView.setTextColor(-1);
        textView.setLayoutParams(new RelativeLayout.LayoutParams(PREDICT_REPLAY_TAG_WIDTH, i3));
        return textView;
    }

    private TextView getRightTextView(String str) {
        TextView textView = new TextView(getContext());
        int i2 = HEIGHT;
        textView.setBackgroundDrawable(new RoundConerDrawable(1275068416, i2 / 2, 0));
        textView.setGravity(17);
        textView.setText(str);
        textView.setIncludeFontPadding(false);
        textView.setTextSize(0, TEXT_SIZE_PX);
        textView.setTextColor(-1);
        int i3 = INNER_PADDING;
        textView.setPadding((i3 / 2) + i2, 0, i3, 0);
        textView.setLayoutParams(new RelativeLayout.LayoutParams(-2, i2));
        return textView;
    }

    public void init(int i2, String str) {
        int i3;
        this.mStatus = i2;
        if (getChildCount() > 0) {
            removeAllViews();
        }
        if (i2 == 0) {
            addView(getPredictOrReplayTagView(0));
            i3 = PREDICT_REPLAY_TAG_WIDTH;
        } else if (i2 == 1) {
            addView(getLivingTagView());
            i3 = LIVING_TAG_WIDTH;
        } else if (i2 != 2) {
            if (getChildCount() > 0) {
                removeAllViews();
            }
            i3 = 0;
        } else {
            addView(getPredictOrReplayTagView(1));
            i3 = PREDICT_REPLAY_TAG_WIDTH;
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        int i4 = HEIGHT;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, i4);
        layoutParams.setMargins(i3 - i4, 0, 0, 0);
        TextView rightTextView = getRightTextView(str);
        FontsUtil.changeTextFont(rightTextView, 4099);
        rightTextView.setLayoutParams(layoutParams);
        addView(rightTextView, 0);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        AnimationDrawable animationDrawable;
        super.onAttachedToWindow();
        if (this.mStatus != 1 || (animationDrawable = this.mAnimationDrawable) == null) {
            return;
        }
        animationDrawable.start();
    }

    public void startAnim() {
        AnimationDrawable animationDrawable;
        if (this.mStatus != 1 || (animationDrawable = this.mAnimationDrawable) == null) {
            return;
        }
        animationDrawable.start();
    }

    public LiveStatusTagLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
