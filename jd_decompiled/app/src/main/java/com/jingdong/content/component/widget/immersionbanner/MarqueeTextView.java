package com.jingdong.content.component.widget.immersionbanner;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.Nullable;

/* loaded from: classes12.dex */
public class MarqueeTextView extends TextView implements Runnable {
    private static final int SCROLL_DELAYED = 4000;
    private static final String TAG = "MarqueeTextView";
    private int currentScrollX;
    private int delayed;
    private int endX;
    private int firstScrollX;
    private boolean isFirstDraw;
    private boolean isStop;
    private int mWidth;
    private int speed;
    private int textWidth;

    public MarqueeTextView(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.isFirstDraw) {
            int scrollX = getScrollX();
            this.firstScrollX = scrollX;
            this.currentScrollX = scrollX;
            int width = getWidth();
            this.mWidth = width;
            this.endX = (this.firstScrollX + this.textWidth) - (width / 2);
            this.isFirstDraw = false;
        }
    }

    @Override // android.widget.TextView
    protected void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        super.onTextChanged(charSequence, i2, i3, i4);
        this.isStop = true;
        removeCallbacks(this);
        int i5 = this.firstScrollX;
        this.currentScrollX = i5;
        scrollTo(i5, 0);
        super.onTextChanged(charSequence, i2, i3, i4);
        this.isFirstDraw = true;
        this.isStop = false;
        postDelayed(this, 4000L);
    }

    @Override // java.lang.Runnable
    public void run() {
        int i2 = this.currentScrollX + this.speed;
        this.currentScrollX = i2;
        scrollTo(i2, 0);
        if (this.isStop) {
            return;
        }
        if (this.currentScrollX >= this.endX) {
            scrollTo(this.firstScrollX, 0);
            this.currentScrollX = this.firstScrollX;
            postDelayed(this, 4000L);
            return;
        }
        postDelayed(this, this.delayed);
    }

    public void setDelayed(int i2) {
        this.delayed = i2;
    }

    public void setSpeed(int i2) {
        this.speed = i2;
    }

    public void startFor() {
        this.currentScrollX = 0;
        startScroll();
    }

    public void startScroll() {
        this.isStop = false;
        removeCallbacks(this);
        postDelayed(this, 4000L);
    }

    public void stopScroll() {
        this.isStop = true;
    }

    public MarqueeTextView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.currentScrollX = 0;
        this.firstScrollX = 0;
        this.isStop = false;
        this.mWidth = 0;
        this.speed = 2;
        this.delayed = 1000;
        this.isFirstDraw = true;
    }
}
