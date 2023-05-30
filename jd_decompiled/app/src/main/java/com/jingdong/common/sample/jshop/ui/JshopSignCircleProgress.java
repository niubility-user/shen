package com.jingdong.common.sample.jshop.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.jingdong.app.mall.R;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes6.dex */
public class JshopSignCircleProgress extends FrameLayout {
    private static final String TAG = "JshopSignCircleProgress";
    private AnimationSet animationSet;
    private Animation.AnimationListener circleAnimationListener;
    private boolean isColorAnimationEnded;
    private boolean isInitAnimationEnded;
    private RelativeLayout jshop_sign_day_view;
    private TextView jshop_sign_ok_days;
    private ImageView jshop_sign_ok_indicator;
    private ImageView jshop_sign_ok_mask;
    private TextView jshop_sign_ok_text;
    private RelativeLayout jshop_sign_ok_view;
    private ImageView jshop_sign_present;
    private ImageView mBgBase;
    private JDSignCircleProgressView mCircleProgressButton;
    private Context mContext;
    private ImageView mSignCircle;
    private ImageView mSignText;
    private boolean setCircleAnimationEnabled;
    private SignButtonListener signButtonListener;
    private Toast toast;

    /* loaded from: classes6.dex */
    public interface SignButtonListener {
        void signButtonClicked();
    }

    public JshopSignCircleProgress(Context context) {
        this(context, null);
        this.mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(this.mContext).inflate(R.layout.jshop_sign_circleprogress_frame_layout, (ViewGroup) this, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean protectFastClick() {
        if (this.isColorAnimationEnded) {
            return false;
        }
        Toast toast = this.toast;
        if (toast != null) {
            toast.show();
        } else {
            Toast makeText = Toast.makeText(this.mContext.getApplicationContext(), "\u7b49\u5f85\u52a8\u753b\u7ed3\u675f\u540e\u518d\u8fdb\u884c\u7b7e\u5230!", 0);
            this.toast = makeText;
            makeText.show();
        }
        return true;
    }

    public void initAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(2.0f, 1.0f, 2.0f, 1.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(500L);
        this.mSignText.setAnimation(scaleAnimation);
        this.mSignText.setVisibility(0);
        scaleAnimation.startNow();
        final ScaleAnimation scaleAnimation2 = new ScaleAnimation(0.8f, 1.0f, 0.8f, 1.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation2.setDuration(1000L);
        this.jshop_sign_present.setVisibility(8);
        final TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, -100.0f, 0.0f);
        translateAnimation.setDuration(200L);
        this.jshop_sign_present.setAnimation(translateAnimation);
        scaleAnimation2.setAnimationListener(new Animation.AnimationListener() { // from class: com.jingdong.common.sample.jshop.ui.JshopSignCircleProgress.2
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                JshopSignCircleProgress.this.mSignCircle.setVisibility(0);
                JshopSignCircleProgress.this.mSignCircle.setAnimation(JshopSignCircleProgress.this.animationSet);
                JshopSignCircleProgress.this.animationSet.start();
                JshopSignCircleProgress.this.jshop_sign_present.setVisibility(0);
                translateAnimation.startNow();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.jingdong.common.sample.jshop.ui.JshopSignCircleProgress.3
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                JshopSignCircleProgress.this.mBgBase.setVisibility(0);
                JshopSignCircleProgress.this.mBgBase.setAnimation(scaleAnimation2);
                scaleAnimation2.start();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        Log.d(TAG, "onDetachedFromWindow()");
        super.onAttachedToWindow();
    }

    public void onDestroy() {
        this.circleAnimationListener = null;
        this.mCircleProgressButton.onDestroy();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        Log.d(TAG, "onDetachedFromWindow()");
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d(TAG, "onFinishInflate");
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.jshop_sign_ok_view);
        this.jshop_sign_ok_view = relativeLayout;
        relativeLayout.setVisibility(4);
        this.jshop_sign_ok_text = (TextView) findViewById(R.id.jshop_sign_ok_text);
        RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.jshop_sign_ok_text_view);
        this.jshop_sign_day_view = relativeLayout2;
        relativeLayout2.setVisibility(8);
        this.jshop_sign_ok_days = (TextView) findViewById(R.id.jshop_sign_ok_days);
        this.jshop_sign_ok_indicator = (ImageView) findViewById(R.id.jshop_sign_ok_indicator);
        this.jshop_sign_ok_mask = (ImageView) findViewById(R.id.jshop_sign_ok_indicator_mask);
        this.jshop_sign_present = (ImageView) findViewById(R.id.jshop_sign_present);
        JDSignCircleProgressView jDSignCircleProgressView = (JDSignCircleProgressView) findViewById(R.id.jshop_sign_circleprogressbar);
        this.mCircleProgressButton = jDSignCircleProgressView;
        jDSignCircleProgressView.setOnTouchListener(new View.OnTouchListener() { // from class: com.jingdong.common.sample.jshop.ui.JshopSignCircleProgress.4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action != 0) {
                    if (action == 1) {
                        JshopSignCircleProgress.this.mCircleProgressButton.curState = 2002;
                        JshopSignCircleProgress.this.mCircleProgressButton.invalidateUi();
                        if (JshopSignCircleProgress.this.signButtonListener != null) {
                            JshopSignCircleProgress.this.signButtonListener.signButtonClicked();
                        }
                    } else if (action == 3) {
                        JshopSignCircleProgress.this.mCircleProgressButton.curState = 2002;
                        JshopSignCircleProgress.this.mCircleProgressButton.invalidateUi();
                    }
                } else if (!JshopSignCircleProgress.this.protectFastClick()) {
                    JshopSignCircleProgress.this.mCircleProgressButton.curState = 2001;
                    JshopSignCircleProgress.this.mCircleProgressButton.invalidateUi();
                }
                return true;
            }
        });
        this.mSignText = (ImageView) findViewById(R.id.jshop_sign_text);
        this.mBgBase = (ImageView) findViewById(R.id.jshop_sign_bg_base);
        this.mSignCircle = (ImageView) findViewById(R.id.jshop_sign_circle);
        this.animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f, 1, 0.5f, 1, 0.5f);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.1f);
        this.animationSet.setDuration(1000L);
        this.animationSet.addAnimation(scaleAnimation);
        this.animationSet.addAnimation(alphaAnimation);
        Animation.AnimationListener animationListener = new Animation.AnimationListener() { // from class: com.jingdong.common.sample.jshop.ui.JshopSignCircleProgress.5
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                JshopSignCircleProgress.this.animationSet.setAnimationListener(this);
                JshopSignCircleProgress.this.mSignCircle.setAnimation(JshopSignCircleProgress.this.animationSet);
                if (JshopSignCircleProgress.this.setCircleAnimationEnabled) {
                    JshopSignCircleProgress.this.animationSet.setStartTime(AnimationUtils.currentAnimationTimeMillis() + 400);
                }
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        };
        this.circleAnimationListener = animationListener;
        this.animationSet.setAnimationListener(animationListener);
    }

    public void setCircleAnimationEnabled(boolean z) {
        this.setCircleAnimationEnabled = z;
        if (z) {
            if (this.animationSet != null) {
                Log.d(TAG, "animationSet " + this.animationSet + " animationSet has started " + this.animationSet.hasStarted() + " animationSet has ended: " + this.animationSet.hasEnded());
            }
            AnimationSet animationSet = this.animationSet;
            if (animationSet != null && !animationSet.hasStarted()) {
                this.animationSet.setAnimationListener(this.circleAnimationListener);
                this.mSignCircle.setAnimation(this.animationSet);
                this.mSignCircle.setVisibility(0);
                this.animationSet.start();
                return;
            }
            AnimationSet animationSet2 = this.animationSet;
            if (animationSet2 != null && animationSet2.hasEnded()) {
                Log.d(TAG, "animationSet " + this.animationSet + " animationSet has ended " + this.animationSet.hasStarted());
                this.mSignCircle.setVisibility(0);
                this.mSignCircle.setAnimation(this.animationSet);
                this.animationSet.start();
            } else if (this.animationSet == null) {
                this.animationSet = new AnimationSet(true);
                ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f, 1, 0.5f, 1, 0.5f);
                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.1f);
                this.animationSet.setDuration(1000L);
                this.animationSet.addAnimation(scaleAnimation);
                this.animationSet.addAnimation(alphaAnimation);
                this.animationSet.setAnimationListener(new Animation.AnimationListener() { // from class: com.jingdong.common.sample.jshop.ui.JshopSignCircleProgress.1
                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationEnd(Animation animation) {
                        JshopSignCircleProgress.this.animationSet.setAnimationListener(this);
                        JshopSignCircleProgress.this.mSignCircle.setAnimation(JshopSignCircleProgress.this.animationSet);
                        if (JshopSignCircleProgress.this.setCircleAnimationEnabled) {
                            JshopSignCircleProgress.this.animationSet.setStartTime(AnimationUtils.currentAnimationTimeMillis() + 400);
                        }
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationStart(Animation animation) {
                    }
                });
                this.animationSet.start();
            }
        }
    }

    public void setCircleAnimationStopped() {
        AnimationSet animationSet = this.animationSet;
        if (animationSet != null) {
            animationSet.cancel();
        }
    }

    public void setSignButtonListener(SignButtonListener signButtonListener) {
        this.signButtonListener = signButtonListener;
    }

    public void viewOnClicked(int i2, int i3, int i4) {
        Log.d("viewOnClicked", "11111");
        if (this.isInitAnimationEnded && this.isColorAnimationEnded) {
            Log.d("viewOnClicked", "2222");
            this.isColorAnimationEnded = false;
            this.setCircleAnimationEnabled = false;
            this.mBgBase.setVisibility(0);
            this.jshop_sign_ok_view.setVisibility(0);
            this.mCircleProgressButton.drawArc = true;
            this.jshop_sign_ok_mask.setVisibility(0);
            this.jshop_sign_present.setVisibility(0);
            this.mSignText.setVisibility(8);
            this.jshop_sign_day_view.setVisibility(0);
            this.jshop_sign_ok_text.setVisibility(8);
            this.jshop_sign_ok_text.setText("\u8fde\u7b7e" + i4 + "\u5929\u66f4\u591a\u5956\u52b1");
            TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 100.0f, 0.0f);
            translateAnimation.setDuration(900L);
            this.jshop_sign_ok_text.setAnimation(translateAnimation);
            translateAnimation.startNow();
            this.jshop_sign_ok_text.setVisibility(0);
            this.jshop_sign_ok_days.setVisibility(0);
            ValueAnimator ofInt = ValueAnimator.ofInt(0, i3);
            ofInt.setDuration(500L);
            ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.sample.jshop.ui.JshopSignCircleProgress.9
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    JshopSignCircleProgress.this.jshop_sign_ok_days.setText("" + intValue + "");
                }
            });
            ofInt.start();
            this.jshop_sign_ok_indicator.setVisibility(0);
            TranslateAnimation translateAnimation2 = new TranslateAnimation(0.0f, 100.0f, 0.0f, 0.0f);
            translateAnimation2.setDuration(900L);
            this.jshop_sign_ok_mask.setAnimation(translateAnimation2);
            translateAnimation2.startNow();
            this.jshop_sign_ok_mask.setVisibility(4);
            translateAnimation2.setAnimationListener(new Animation.AnimationListener() { // from class: com.jingdong.common.sample.jshop.ui.JshopSignCircleProgress.10
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    JshopSignCircleProgress.this.isColorAnimationEnded = true;
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }
            });
            if (i2 > 0) {
                if (i2 > 100) {
                    i2 = 100;
                }
                Log.d("processanimation", "process is " + i2);
                ValueAnimator ofInt2 = ValueAnimator.ofInt(0, i2);
                ofInt2.setDuration(800L);
                ofInt2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.sample.jshop.ui.JshopSignCircleProgress.11
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        JshopSignCircleProgress.this.mCircleProgressButton.setProgress(((Integer) valueAnimator.getAnimatedValue()).intValue());
                    }
                });
                ofInt2.start();
            }
            setCircleAnimationEnabled(true);
        }
    }

    public synchronized void viewOnClickedAddContinueDay(int i2, int i3) {
        if (this.isInitAnimationEnded && this.isColorAnimationEnded) {
            this.isColorAnimationEnded = false;
            this.setCircleAnimationEnabled = false;
            this.mBgBase.setVisibility(0);
            this.jshop_sign_ok_view.setVisibility(0);
            this.mCircleProgressButton.drawArc = true;
            this.jshop_sign_ok_mask.setVisibility(0);
            this.mSignText.setVisibility(8);
            this.jshop_sign_day_view.setVisibility(0);
            this.jshop_sign_ok_text.setVisibility(8);
            this.jshop_sign_ok_text.setText("\u8fde\u7b7e" + i3 + "\u5929\u66f4\u591a\u5956\u52b1");
            TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 100.0f, 0.0f);
            translateAnimation.setDuration(900L);
            this.jshop_sign_ok_text.setAnimation(translateAnimation);
            translateAnimation.startNow();
            this.jshop_sign_ok_text.setVisibility(0);
            this.jshop_sign_ok_days.setVisibility(0);
            ValueAnimator ofInt = ValueAnimator.ofInt(0, i3);
            ofInt.setDuration(500L);
            ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.sample.jshop.ui.JshopSignCircleProgress.6
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    JshopSignCircleProgress.this.jshop_sign_ok_days.setText("" + intValue + "");
                }
            });
            ofInt.start();
            this.jshop_sign_ok_indicator.setVisibility(0);
            TranslateAnimation translateAnimation2 = new TranslateAnimation(0.0f, 100.0f, 0.0f, 0.0f);
            translateAnimation2.setDuration(900L);
            this.jshop_sign_ok_mask.setAnimation(translateAnimation2);
            translateAnimation2.startNow();
            this.jshop_sign_ok_mask.setVisibility(4);
            translateAnimation2.setAnimationListener(new Animation.AnimationListener() { // from class: com.jingdong.common.sample.jshop.ui.JshopSignCircleProgress.7
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    JshopSignCircleProgress.this.isColorAnimationEnded = true;
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }
            });
            float f2 = i2;
            int i4 = (int) ((((float) (i3 - 1)) * 100.0f) / f2);
            int i5 = (int) ((i3 * 100.0f) / f2);
            if (i4 > 0 && i5 > 0) {
                Log.d("processanimation", "process is " + i5);
                ValueAnimator ofInt2 = ValueAnimator.ofInt(0, i5);
                ofInt2.setDuration(500L);
                ofInt2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.sample.jshop.ui.JshopSignCircleProgress.8
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        JshopSignCircleProgress.this.mCircleProgressButton.setProgress(((Integer) valueAnimator.getAnimatedValue()).intValue());
                    }
                });
                ofInt2.start();
            }
            setCircleAnimationEnabled(true);
            return;
        }
        Log.d(TAG, "isInitAnimationEnded: " + this.isInitAnimationEnded + " isColorAnimationEnded: " + this.isColorAnimationEnded);
    }

    public JshopSignCircleProgress(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isInitAnimationEnded = true;
        this.isColorAnimationEnded = true;
        this.setCircleAnimationEnabled = true;
        this.mContext = context;
        init();
    }

    public JshopSignCircleProgress(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.isInitAnimationEnded = true;
        this.isColorAnimationEnded = true;
        this.setCircleAnimationEnabled = true;
        this.mContext = context;
        init();
    }
}
