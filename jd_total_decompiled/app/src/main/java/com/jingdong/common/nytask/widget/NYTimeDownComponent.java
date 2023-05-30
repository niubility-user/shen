package com.jingdong.common.nytask.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.R;
import com.jingdong.common.nytask.NYTaskFinishEntity;
import com.jingdong.common.nytask.inter.INYTimeDownGroup;
import com.jingdong.common.nytask.widget.NYTimeDownView;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.sdk.utils.DPIUtil;
import java.lang.ref.WeakReference;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* loaded from: classes5.dex */
public class NYTimeDownComponent extends DraggableView implements INYTimeDownGroup {
    public static final String TAG = "NYTimeDownComponent";
    private FrameAnimEndHandler mAnimEndHandler;
    private Context mContext;
    private ImageView mFinishIV;
    private SimpleDraweeView mGifIV;
    private ImageView mImageView;
    private NYTimeDownView mNYTimeDownView;
    private TextView mTextView;

    /* loaded from: classes5.dex */
    public static class FrameAnimEndHandler extends Handler {
        public static final int ANIM_END = 1;
        private WeakReference<NYTimeDownComponent> mNYTimeDownComponentWr;

        public FrameAnimEndHandler(NYTimeDownComponent nYTimeDownComponent) {
            this.mNYTimeDownComponentWr = new WeakReference<>(nYTimeDownComponent);
        }

        private void animEnd() {
            WeakReference<NYTimeDownComponent> weakReference = this.mNYTimeDownComponentWr;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.mNYTimeDownComponentWr.get().animEnd();
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            if (message.what != 1) {
                return;
            }
            animEnd();
        }
    }

    public NYTimeDownComponent(@NonNull Context context) {
        this(context, null);
    }

    private int getAnimDuring(AnimationDrawable animationDrawable) {
        int i2 = 0;
        for (int i3 = 0; i3 < animationDrawable.getNumberOfFrames(); i3++) {
            i2 += animationDrawable.getDuration(i3);
        }
        return i2;
    }

    private void init() {
        this.mAnimEndHandler = new FrameAnimEndHandler(this);
        this.mNYTimeDownView = new NYTimeDownView(this.mContext);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(DPIUtil.dip2px(40.0f), DPIUtil.dip2px(40.0f));
        layoutParams.gravity = 17;
        addView(this.mNYTimeDownView, layoutParams);
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this.mContext);
        this.mImageView = simpleDraweeView;
        simpleDraweeView.setImageResource(R.drawable.icon_time_down_bg);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(DPIUtil.dip2px(21.0f), DPIUtil.dip2px(21.0f));
        layoutParams2.gravity = 17;
        addView(this.mImageView, layoutParams2);
        SimpleDraweeView simpleDraweeView2 = new SimpleDraweeView(this.mContext);
        this.mGifIV = simpleDraweeView2;
        simpleDraweeView2.setBackgroundResource(R.drawable.banger_animation);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(DPIUtil.dip2px(56.0f), DPIUtil.dip2px(56.0f));
        layoutParams3.gravity = 17;
        addView(this.mGifIV, layoutParams3);
        this.mGifIV.setVisibility(4);
        TextView textView = new TextView(this.mContext);
        this.mTextView = textView;
        textView.setTextSize(2, 14.0f);
        this.mTextView.setTextColor(Color.parseColor("#FF0909"));
        FrameLayout.LayoutParams layoutParams4 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams4.gravity = 17;
        addView(this.mTextView, layoutParams4);
        this.mTextView.setVisibility(4);
        ImageView imageView = new ImageView(this.mContext);
        this.mFinishIV = imageView;
        imageView.setImageResource(R.drawable.icon_ny_task_finish);
        FrameLayout.LayoutParams layoutParams5 = new FrameLayout.LayoutParams(DPIUtil.dip2px(14.0f), DPIUtil.dip2px(14.0f));
        layoutParams5.bottomMargin = DPIUtil.dip2px(5.0f);
        layoutParams5.rightMargin = DPIUtil.dip2px(11.0f);
        layoutParams5.gravity = 85;
        addView(this.mFinishIV, layoutParams5);
        this.mFinishIV.setVisibility(4);
        setExtraHorizontalSpace(DPIUtil.dip2px(7.0f));
        setExtraVerticalSpace(DPIUtil.dip2px(20.0f));
    }

    void animEnd() {
        SimpleDraweeView simpleDraweeView = this.mGifIV;
        if (simpleDraweeView != null) {
            simpleDraweeView.setVisibility(8);
        }
        ImageView imageView = this.mImageView;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        ImageView imageView2 = this.mFinishIV;
        if (imageView2 != null) {
            imageView2.setVisibility(0);
        }
        TextView textView = this.mTextView;
        if (textView != null) {
            textView.setVisibility(0);
        }
    }

    @Override // com.jingdong.common.nytask.widget.DraggableView
    protected void drawContent(Canvas canvas) {
    }

    @Override // com.jingdong.common.nytask.inter.INYTimeDownGroup
    public void finishTaskOkay(NYTaskFinishEntity nYTaskFinishEntity) {
        NYTaskFinishEntity.Data data;
        int i2;
        TextView textView;
        animEnd();
        if (nYTaskFinishEntity == null || (data = nYTaskFinishEntity.data) == null || (i2 = data.familyTaskScore) <= 0 || (textView = this.mTextView) == null) {
            return;
        }
        textView.setText(MqttTopic.SINGLE_LEVEL_WILDCARD + i2);
    }

    public void gravity(int i2) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            ((FrameLayout.LayoutParams) layoutParams).gravity = i2;
        }
    }

    public void initBgImg(String str) {
        if (this.mImageView != null) {
            JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
            int i2 = R.drawable.icon_time_down_bg;
            jDDisplayImageOptions.showImageForEmptyUri(i2);
            jDDisplayImageOptions.showImageOnLoading(i2);
            jDDisplayImageOptions.showImageOnFail(i2);
            JDImageUtils.displayImage(str, this.mImageView, jDDisplayImageOptions);
        }
    }

    public void pauseTimeDown(boolean z) {
        NYTimeDownView nYTimeDownView = this.mNYTimeDownView;
        if (nYTimeDownView != null) {
            nYTimeDownView.pauseTimeDown(z);
        }
    }

    public void releaseResource() {
        FrameAnimEndHandler frameAnimEndHandler = this.mAnimEndHandler;
        if (frameAnimEndHandler != null) {
            frameAnimEndHandler.removeMessages(1);
            this.mAnimEndHandler.removeCallbacksAndMessages(null);
            this.mAnimEndHandler = null;
        }
        SimpleDraweeView simpleDraweeView = this.mGifIV;
        if (simpleDraweeView != null) {
            Drawable drawable = simpleDraweeView.getDrawable();
            if (drawable instanceof AnimationDrawable) {
                ((AnimationDrawable) drawable).stop();
            }
        }
        NYTimeDownView nYTimeDownView = this.mNYTimeDownView;
        if (nYTimeDownView != null) {
            nYTimeDownView.releaseResource();
        }
    }

    public void resumeTimeDown(boolean z) {
        NYTimeDownView nYTimeDownView = this.mNYTimeDownView;
        if (nYTimeDownView != null) {
            nYTimeDownView.resumeTimeDown(z);
        }
    }

    public void setTimeDownListener(NYTimeDownView.TimeDownListener timeDownListener) {
        NYTimeDownView nYTimeDownView = this.mNYTimeDownView;
        if (nYTimeDownView != null) {
            nYTimeDownView.setTimeDownListener(timeDownListener);
        }
    }

    public void startTimeDown(long j2) {
        NYTimeDownView nYTimeDownView = this.mNYTimeDownView;
        if (nYTimeDownView != null) {
            nYTimeDownView.startTimeDown(j2);
        }
    }

    public NYTimeDownComponent(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NYTimeDownComponent(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mContext = context;
        init();
    }
}
