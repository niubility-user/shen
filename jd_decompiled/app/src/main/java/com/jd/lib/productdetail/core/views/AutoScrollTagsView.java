package com.jd.lib.productdetail.core.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.jd.lib.productdetail.core.R;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes15.dex */
public class AutoScrollTagsView extends RelativeLayout {
    private static final String TAG = "AutoScrollTagsView2";
    private int index;
    private boolean isPlaying;
    private Context mContext;
    private int mDuration;
    private boolean mIsDestroy;
    private float mSpacing;
    private float mTagHeight;
    private ArrayList<ValueAnimator> mValueAnimators;
    private String[] texts;

    public AutoScrollTagsView(@NonNull Context context) {
        super(context);
        this.isPlaying = false;
        init(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getText() {
        String str = "";
        try {
            String[] strArr = this.texts;
            if (strArr != null && strArr.length > 0) {
                int i2 = this.index;
                str = strArr[i2 % strArr.length];
                this.index = (i2 % strArr.length) + 1;
            }
            Context context = this.mContext;
            return context != null ? context.getString(R.string.lib_pd_core_comment_tags_txt, str) : str;
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
            return "";
        }
    }

    private void init(Context context) {
        this.mContext = context;
        this.mSpacing = PDUtils.dip2px(12.0f);
        this.mTagHeight = PDUtils.dip2px(30.0f);
        this.mDuration = 9000;
        this.mValueAnimators = new ArrayList<>();
    }

    private TextView obtainTextView() {
        TextView textView = new TextView(this.mContext);
        textView.setPadding(PDUtils.dip2px(12.0f), 0, PDUtils.dip2px(12.0f), 0);
        textView.setTextColor(ContextCompat.getColor(this.mContext, R.color.lib_pd_core_white));
        textView.setTextSize(2, 11.0f);
        textView.setBackgroundResource(R.drawable.lib_pd_core_shape_comment_tag_bg);
        textView.setIncludeFontPadding(false);
        textView.setGravity(17);
        textView.setAlpha(0.0f);
        return textView;
    }

    public void addTag(int i2) {
        TextView obtainTextView = obtainTextView();
        obtainTextView.setText(getText());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, (int) this.mTagHeight);
        layoutParams.addRule(12);
        layoutParams.addRule(11);
        addView(obtainTextView, layoutParams);
        Keyframe ofFloat = Keyframe.ofFloat(0.0f, 0.0f);
        Keyframe ofFloat2 = Keyframe.ofFloat(0.33333334f, -(this.mSpacing + this.mTagHeight));
        ofFloat2.setInterpolator(new DecelerateInterpolator(2.5f));
        Keyframe ofFloat3 = Keyframe.ofFloat(0.6666667f, (-(this.mSpacing + this.mTagHeight)) * 2.0f);
        ofFloat3.setInterpolator(new DecelerateInterpolator(1.8f));
        Keyframe ofFloat4 = Keyframe.ofFloat(0.9f, (-(this.mSpacing + this.mTagHeight)) * 3.0f);
        ofFloat4.setInterpolator(new DecelerateInterpolator(1.5f));
        PropertyValuesHolder ofKeyframe = PropertyValuesHolder.ofKeyframe("translationY", ofFloat, ofFloat2, ofFloat3, ofFloat4, Keyframe.ofFloat(1.0f, (-(this.mSpacing + this.mTagHeight)) * 3.0f));
        Keyframe ofFloat5 = Keyframe.ofFloat(0.0f, 0.0f);
        Keyframe ofFloat6 = Keyframe.ofFloat(0.33333334f, 1.0f);
        ofFloat6.setInterpolator(new DecelerateInterpolator(2.0f));
        ObjectAnimator duration = ObjectAnimator.ofPropertyValuesHolder(obtainTextView, ofKeyframe, PropertyValuesHolder.ofKeyframe("alpha", ofFloat5, ofFloat6, Keyframe.ofFloat(0.6666667f, 0.3f), Keyframe.ofFloat(0.7f, 0.0f), Keyframe.ofFloat(1.0f, 0.0f))).setDuration(this.mDuration);
        duration.addListener(new AnimatorListenerAdapter() { // from class: com.jd.lib.productdetail.core.views.AutoScrollTagsView.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
                super.onAnimationRepeat(animator);
                if (Log.D) {
                    Log.d(AutoScrollTagsView.TAG, "onAnimationRepeat----------");
                }
                if (!AutoScrollTagsView.this.mIsDestroy && (animator instanceof ObjectAnimator)) {
                    Object target = ((ObjectAnimator) animator).getTarget();
                    if (target instanceof TextView) {
                        ((TextView) target).setText(AutoScrollTagsView.this.getText());
                    }
                }
            }
        });
        duration.setInterpolator(new LinearInterpolator());
        duration.setRepeatCount(-1);
        duration.setStartDelay(i2);
        duration.start();
        ArrayList<ValueAnimator> arrayList = this.mValueAnimators;
        if (arrayList != null) {
            arrayList.add(duration);
        }
    }

    public void onDestroy() {
        this.mIsDestroy = true;
        stop();
        this.mValueAnimators = null;
    }

    public void pause() {
        if (getVisibility() == 0) {
            stop();
        }
    }

    public void resume() {
        if (getVisibility() == 0) {
            start();
        }
    }

    public void setData(String[] strArr) {
        this.index = 0;
        this.texts = strArr;
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        if (i2 == 0) {
            start();
        } else {
            stop();
        }
    }

    public void start() {
        if (this.isPlaying) {
            return;
        }
        this.isPlaying = true;
        addTag(0);
        addTag(this.mDuration / 3);
        addTag((this.mDuration * 2) / 3);
    }

    public void stop() {
        this.isPlaying = false;
        ArrayList<ValueAnimator> arrayList = this.mValueAnimators;
        if (arrayList != null) {
            Iterator<ValueAnimator> it = arrayList.iterator();
            while (it.hasNext()) {
                ValueAnimator next = it.next();
                if (next != null) {
                    next.cancel();
                }
            }
            this.mValueAnimators.clear();
        }
        removeAllViews();
        this.index = 0;
    }

    public AutoScrollTagsView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isPlaying = false;
        init(context);
    }
}
