package com.jingdong.app.mall.bundle.jd_component.bubble;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.jingdong.app.mall.bundle.jd_component.R;
import com.jingdong.app.mall.bundle.jd_component.bubble.BubbleDrawable;

/* loaded from: classes2.dex */
public class BubbleLinearLayout extends LinearLayout {
    private int bubbleColor;
    private BubbleDrawable bubbleDrawable;
    private float mAngle;
    private float mArrowHeight;
    private BubbleDrawable.ArrowLocation mArrowLocation;
    private float mArrowPosition;
    private BubbleDrawable.ArrowStart mArrowStart;
    private float mArrowWidth;

    public BubbleLinearLayout(Context context) {
        super(context);
        initView(null);
    }

    private void initView(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.BubbleView);
            this.mArrowWidth = obtainStyledAttributes.getDimension(R.styleable.BubbleView_arrowWidth, BubbleDrawable.Builder.DEFAULT_ARROW_WITH);
            this.mArrowHeight = obtainStyledAttributes.getDimension(R.styleable.BubbleView_arrowHeight, BubbleDrawable.Builder.DEFAULT_ARROW_HEIGHT);
            this.mAngle = obtainStyledAttributes.getDimension(R.styleable.BubbleView_angle, BubbleDrawable.Builder.DEFAULT_ANGLE);
            this.mArrowPosition = obtainStyledAttributes.getDimension(R.styleable.BubbleView_arrowPosition, BubbleDrawable.Builder.DEFAULT_ARROW_POSITION);
            this.bubbleColor = obtainStyledAttributes.getColor(R.styleable.BubbleView_bubbleColor, BubbleDrawable.Builder.DEFAULT_BUBBLE_COLOR);
            this.mArrowLocation = BubbleDrawable.ArrowLocation.mapIntToValue(obtainStyledAttributes.getInt(R.styleable.BubbleView_arrowLocation, 0));
            this.mArrowStart = BubbleDrawable.ArrowStart.mapIntToValue(obtainStyledAttributes.getInt(R.styleable.BubbleView_arrowStartPosition, 0));
            obtainStyledAttributes.recycle();
        }
    }

    private void setUp(int i2, int i3, int i4, int i5) {
        if (i3 < i2 || i5 < i4) {
            return;
        }
        this.bubbleDrawable = new BubbleDrawable.Builder().rect(new RectF(i2, i4, i3, i5)).arrowLocation(this.mArrowLocation).bubbleType(BubbleDrawable.BubbleType.COLOR).angle(this.mAngle).arrowHeight(this.mArrowHeight).arrowWidth(this.mArrowWidth).arrowPosition(this.mArrowPosition).bubbleColor(this.bubbleColor).arrowStart(this.mArrowStart).build();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 <= 0 || i3 <= 0) {
            return;
        }
        setUp(i2, i3);
    }

    public void setUpBubbleDrawable() {
        setBackgroundDrawable(null);
        post(new Runnable() { // from class: com.jingdong.app.mall.bundle.jd_component.bubble.BubbleLinearLayout.1
            @Override // java.lang.Runnable
            public void run() {
                BubbleLinearLayout bubbleLinearLayout = BubbleLinearLayout.this;
                bubbleLinearLayout.setUp(bubbleLinearLayout.getWidth(), BubbleLinearLayout.this.getHeight());
            }
        });
    }

    public BubbleLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUp(int i2, int i3) {
        setUp(getPaddingLeft(), i2 - getPaddingRight(), getPaddingTop(), i3 - getPaddingBottom());
        setBackgroundDrawable(this.bubbleDrawable);
    }
}
