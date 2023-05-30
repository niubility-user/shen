package com.jingdong.app.mall.bundle.dolphinlib.common.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.jd.lib.babel.servicekit.imagekit.ImageWraper;
import com.jd.lib.babel.servicekit.util.DPIUtil;
import com.jingdong.app.mall.bundle.dolphinlib.R;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes19.dex */
public class LiveStreamPraiseContainer extends FrameLayout {
    private static final float ICON_HALF_SIZE;
    private static final int ICON_SIZE;
    private static final AccelerateDecelerateInterpolator mInterpolator;
    private ImageWraper mImagePraise;
    private PointHandler mPH;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes19.dex */
    public class BezierEvaluator implements TypeEvaluator<PointF> {
        private PointF p1;
        private PointF p2;

        public BezierEvaluator(PointF pointF, PointF pointF2) {
            this.p1 = pointF;
            this.p2 = pointF2;
        }

        @Override // android.animation.TypeEvaluator
        public PointF evaluate(float f2, PointF pointF, PointF pointF2) {
            PointF pointF3 = new PointF();
            float f3 = 1.0f - f2;
            PointF pointF4 = this.p1;
            float f4 = (pointF.x * f3 * f3 * f3) + (pointF4.x * 3.0f * f2 * f3 * f3);
            PointF pointF5 = this.p2;
            pointF3.x = f4 + (pointF5.x * 3.0f * f2 * f2 * f3) + (pointF2.x * f2 * f2 * f2);
            pointF3.y = (pointF.y * f3 * f3 * f3) + (pointF4.y * 3.0f * f2 * f3 * f3) + (pointF5.y * 3.0f * f2 * f2 * f3) + (pointF2.y * f2 * f2 * f2);
            return pointF3;
        }
    }

    /* loaded from: classes19.dex */
    private final class ImagePraiseClickImpl implements View.OnClickListener {
        private Point vPoint;

        private ImagePraiseClickImpl() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.vPoint == null) {
                this.vPoint = new Point((view.getWidth() / 2) + view.getLeft(), (view.getHeight() / 2) + view.getTop());
            }
            LiveStreamPraiseContainer.this.addPraise(new Point(this.vPoint));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes19.dex */
    public final class PointHandler extends Handler {
        static final int MSG_ADD_POINT = 10;
        static final int TOTAL_COUNT = 6;
        private AtomicInteger counter;

        private PointHandler() {
            this.counter = new AtomicInteger();
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            if (message.what == 10 && this.counter.decrementAndGet() <= 6) {
                LiveStreamPraiseContainer.this.onHandlePoint((Point) message.obj);
            }
        }

        @Override // android.os.Handler
        public boolean sendMessageAtTime(@NonNull Message message, long j2) {
            if (message.what == 10 && this.counter.incrementAndGet() > 6) {
                return false;
            }
            return super.sendMessageAtTime(message, j2);
        }
    }

    static {
        int dip2px = DPIUtil.dip2px(20.0f);
        ICON_SIZE = dip2px;
        ICON_HALF_SIZE = dip2px / 2.0f;
        mInterpolator = new AccelerateDecelerateInterpolator();
    }

    public LiveStreamPraiseContainer(Context context) {
        super(context);
    }

    private PointF[] createBezierPoint(ImageView imageView) {
        int width = getWidth();
        int height = getHeight();
        int i2 = (height / 2) - ICON_SIZE;
        Random random = new Random();
        return new PointF[]{new PointF(imageView.getX(), imageView.getY()), new PointF(random.nextInt(width), random.nextInt(i2) + i2 + r2), new PointF(random.nextInt(width), random.nextInt(i2) + r2), new PointF(random.nextInt(width), -r2)};
    }

    private AnimatorSet makeAnimatorSet(final ImageView imageView) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(imageView, "alpha", 0.1f, 1.0f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(imageView, "scaleX", 0.1f, 1.0f);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(imageView, "scaleY", 0.1f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat, ofFloat2, ofFloat3);
        animatorSet.setDuration(500L);
        PointF[] createBezierPoint = createBezierPoint(imageView);
        ValueAnimator ofObject = ValueAnimator.ofObject(new BezierEvaluator(createBezierPoint[1], createBezierPoint[2]), createBezierPoint[0], createBezierPoint[3]);
        ofObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.app.mall.bundle.dolphinlib.common.view.LiveStreamPraiseContainer.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                PointF pointF = (PointF) valueAnimator.getAnimatedValue();
                imageView.setX(pointF.x);
                imageView.setY(pointF.y);
            }
        });
        ofObject.setTarget(imageView);
        ofObject.setDuration(1000L);
        ofObject.setInterpolator(mInterpolator);
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playTogether(animatorSet, ofObject);
        animatorSet2.setTarget(imageView);
        return animatorSet2;
    }

    private ImageView makeIcon(String str, Point point2) {
        ImageView imageView = new ImageView(getContext());
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        if (layoutParams == null) {
            int i2 = ICON_SIZE;
            layoutParams = new ViewGroup.LayoutParams(i2, i2);
        }
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.drawable.dolphin_subsidy_btn_bg);
        float f2 = ICON_HALF_SIZE;
        imageView.setX(point2.x - f2);
        imageView.setY((point2.y - ICON_SIZE) - f2);
        return imageView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onHandlePoint(Point point2) {
        final ImageView makeIcon = makeIcon(null, point2);
        addView(makeIcon);
        AnimatorSet makeAnimatorSet = makeAnimatorSet(makeIcon);
        makeAnimatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.jingdong.app.mall.bundle.dolphinlib.common.view.LiveStreamPraiseContainer.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                LiveStreamPraiseContainer.this.removeView(makeIcon);
            }
        });
        makeAnimatorSet.start();
    }

    public final void addPraise(Point point2) {
        if (point2 == null) {
            return;
        }
        this.mPH.sendMessage(this.mPH.obtainMessage(10, point2));
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mPH = new PointHandler();
        ImageWraper imageWraper = (ImageWraper) findViewById(R.id.imagePraise);
        this.mImagePraise = imageWraper;
        imageWraper.setOnClickListener(new ImagePraiseClickImpl());
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i3) / 2, View.MeasureSpec.getMode(i3)));
    }

    public LiveStreamPraiseContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
