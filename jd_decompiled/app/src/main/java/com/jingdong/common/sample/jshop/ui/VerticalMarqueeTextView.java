package com.jingdong.common.sample.jshop.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class VerticalMarqueeTextView extends TextView {
    float absloutHeight;
    float currentY;
    private int delayTime;
    private int exactlyHeight;
    private int exactlyWidth;
    Handler handler;
    private float index;
    ArrayList<String> lineStrings;
    private Rect mTextBound;
    private float middleY;
    boolean needStop;
    public String scrollText;
    boolean scrolling;
    float speed;
    private int stopTime;
    private int viewHeight;
    private int viewWidth;

    public VerticalMarqueeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.handler = new Handler() { // from class: com.jingdong.common.sample.jshop.ui.VerticalMarqueeTextView.1
            private void resetCurrentY() {
                VerticalMarqueeTextView verticalMarqueeTextView = VerticalMarqueeTextView.this;
                float f2 = verticalMarqueeTextView.currentY;
                float f3 = verticalMarqueeTextView.absloutHeight;
                if (f2 >= f3 || f2 <= (-f3) || verticalMarqueeTextView.getHeight() <= 0) {
                    VerticalMarqueeTextView.this.currentY = 0.0f;
                }
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (VerticalMarqueeTextView.this.absloutHeight <= r0.getHeight()) {
                    VerticalMarqueeTextView verticalMarqueeTextView = VerticalMarqueeTextView.this;
                    verticalMarqueeTextView.currentY = 0.0f;
                    verticalMarqueeTextView.stop();
                    return;
                }
                int i2 = message.what;
                if (i2 != 0) {
                    if (i2 == 2) {
                        VerticalMarqueeTextView verticalMarqueeTextView2 = VerticalMarqueeTextView.this;
                        verticalMarqueeTextView2.index = verticalMarqueeTextView2.index + (VerticalMarqueeTextView.this.absloutHeight / r1.lineStrings.size());
                        VerticalMarqueeTextView.this.stop();
                        VerticalMarqueeTextView.this.handler.sendEmptyMessageDelayed(0, r8.delayTime);
                        return;
                    } else if (i2 != 3) {
                        return;
                    } else {
                        VerticalMarqueeTextView verticalMarqueeTextView3 = VerticalMarqueeTextView.this;
                        verticalMarqueeTextView3.currentY = 0.0f;
                        verticalMarqueeTextView3.index = verticalMarqueeTextView3.absloutHeight / verticalMarqueeTextView3.lineStrings.size();
                        VerticalMarqueeTextView.this.stop();
                        VerticalMarqueeTextView.this.handler.sendEmptyMessageDelayed(0, r8.delayTime);
                        return;
                    }
                }
                VerticalMarqueeTextView verticalMarqueeTextView4 = VerticalMarqueeTextView.this;
                float f2 = verticalMarqueeTextView4.currentY - verticalMarqueeTextView4.speed;
                verticalMarqueeTextView4.currentY = f2;
                boolean z = true;
                float f3 = verticalMarqueeTextView4.absloutHeight;
                if (f2 >= f3 || f2 <= (-f3)) {
                    verticalMarqueeTextView4.handler.sendEmptyMessageDelayed(3, verticalMarqueeTextView4.stopTime);
                    z = false;
                }
                if (z) {
                    VerticalMarqueeTextView verticalMarqueeTextView5 = VerticalMarqueeTextView.this;
                    if (verticalMarqueeTextView5.currentY < verticalMarqueeTextView5.index) {
                        VerticalMarqueeTextView verticalMarqueeTextView6 = VerticalMarqueeTextView.this;
                        if (verticalMarqueeTextView6.currentY > (-verticalMarqueeTextView6.index) && VerticalMarqueeTextView.this.getHeight() > 0) {
                            VerticalMarqueeTextView.this.handler.sendEmptyMessageDelayed(0, r8.delayTime);
                        }
                    }
                    VerticalMarqueeTextView.this.handler.sendEmptyMessageDelayed(2, r8.stopTime);
                }
                VerticalMarqueeTextView.this.invalidate();
            }
        };
        this.scrollText = "";
        this.exactlyWidth = -1;
        this.exactlyHeight = -1;
        this.index = 0.0f;
        this.scrolling = false;
        this.absloutHeight = 0.0f;
        this.delayTime = 50;
        this.stopTime = 2000;
        this.speed = getLineHeight() / 4;
        init();
    }

    private int MeasureHeight(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i3);
        ArrayList<String> arrayList = this.lineStrings;
        int size2 = (arrayList == null || arrayList.size() <= 0) ? 0 : this.lineStrings.size();
        float lineHeight = (getLineHeight() * size2) + getPaddingBottom() + getPaddingTop();
        this.absloutHeight = lineHeight;
        this.index = lineHeight / size2;
        if (mode == Integer.MIN_VALUE) {
            Math.min(lineHeight, size);
            this.exactlyHeight = -1;
        } else if (mode == 1073741824) {
            this.exactlyHeight = size;
        }
        this.exactlyHeight = getLineHeight() * 3;
        int lineHeight2 = getLineHeight() * 3;
        Paint.FontMetrics fontMetrics = getPaint().getFontMetrics();
        this.middleY = ((lineHeight2 * 0.5f) - fontMetrics.descent) + ((fontMetrics.bottom - fontMetrics.top) * 0.5f);
        return lineHeight2;
    }

    private int MeasureWidth(int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == Integer.MIN_VALUE) {
            size = Math.min((int) Math.rint(getPaint().measureText(this.scrollText)), size);
            this.exactlyWidth = -1;
        }
        if (mode == 1073741824) {
            this.exactlyWidth = size;
        }
        this.viewWidth = this.exactlyWidth;
        return size;
    }

    private void reset() {
        stop();
        this.currentY = 0.0f;
        this.absloutHeight = 0.0f;
        setText("");
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeMessages(0);
            this.handler.removeMessages(1);
            this.handler.removeMessages(2);
            this.handler.removeMessages(3);
        }
        requestLayout();
        invalidate();
    }

    @Override // android.widget.TextView
    public int getLineHeight() {
        return super.getLineHeight() << 1;
    }

    public void goOn() {
        play();
        this.needStop = false;
    }

    void init() {
        ArrayList<String> arrayList = this.lineStrings;
        if (arrayList != null) {
            arrayList.clear();
        }
        this.mTextBound = new Rect();
        invalidate();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        goOn();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float lineHeight = getLineHeight();
        float textSize = getPaint().getTextSize();
        getPaint().setAlpha(200);
        ArrayList<String> arrayList = this.lineStrings;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        this.absloutHeight = this.lineStrings.size() * lineHeight;
        if (this.lineStrings.size() == 1) {
            getPaint().getTextBounds(this.lineStrings.get(0), 0, this.lineStrings.get(0).length(), this.mTextBound);
            ArrayList<String> arrayList2 = this.lineStrings;
            arrayList2.set(0, TextUtils.ellipsize(arrayList2.get(0), getPaint(), this.viewWidth, TextUtils.TruncateAt.END).toString());
            canvas.drawText(this.lineStrings.get(0), getWidth() - this.mTextBound.width() >= 0 ? (getWidth() - this.mTextBound.width()) >> 1 : 0.0f, this.middleY, getPaint());
        } else if (this.lineStrings.size() == 2) {
            getPaint().getTextBounds(this.lineStrings.get(0), 0, this.lineStrings.get(0).length(), this.mTextBound);
            float width = getWidth() - this.mTextBound.width() < 0 ? 0.0f : (getWidth() - this.mTextBound.width()) >> 1;
            ArrayList<String> arrayList3 = this.lineStrings;
            arrayList3.set(0, TextUtils.ellipsize(arrayList3.get(0), getPaint(), this.viewWidth, TextUtils.TruncateAt.END).toString());
            float f2 = lineHeight * 0.5f;
            canvas.drawText(this.lineStrings.get(0), width, this.middleY - f2, getPaint());
            getPaint().getTextBounds(this.lineStrings.get(1), 0, this.lineStrings.get(1).length(), this.mTextBound);
            float width2 = getWidth() - this.mTextBound.width() >= 0 ? (getWidth() - this.mTextBound.width()) >> 1 : 0.0f;
            ArrayList<String> arrayList4 = this.lineStrings;
            arrayList4.set(1, TextUtils.ellipsize(arrayList4.get(1), getPaint(), this.viewWidth, TextUtils.TruncateAt.END).toString());
            canvas.drawText(this.lineStrings.get(1), width2, this.middleY + f2, getPaint());
        } else {
            for (int i2 = 0; i2 < this.lineStrings.size(); i2++) {
                float f3 = (((float) (i2 - 1)) * lineHeight) + this.middleY + this.currentY;
                getPaint().getTextBounds(this.lineStrings.get(i2), 0, this.lineStrings.get(i2).length(), this.mTextBound);
                int i3 = this.exactlyHeight;
                float min = i3 > -1 ? Math.min(0.0f, i3 - this.absloutHeight) : 0.0f;
                float width3 = getWidth() - this.mTextBound.width() < 0 ? 0.0f : (getWidth() - this.mTextBound.width()) >> 1;
                ArrayList<String> arrayList5 = this.lineStrings;
                arrayList5.set(i2, TextUtils.ellipsize(arrayList5.get(i2), getPaint(), this.viewWidth, TextUtils.TruncateAt.END).toString());
                if (f3 < min) {
                    f3 += this.absloutHeight;
                } else if (f3 >= min && f3 < min + textSize) {
                    canvas.drawText(this.lineStrings.get(i2), width3, this.absloutHeight + f3, getPaint());
                }
                if (f3 >= this.absloutHeight) {
                    canvas.drawText(this.lineStrings.get(i2), width3, f3, getPaint());
                    f3 -= this.absloutHeight;
                }
                canvas.drawText(this.lineStrings.get(i2), width3, f3, getPaint());
            }
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i2, int i3) {
        int MeasureWidth = MeasureWidth(i2);
        int MeasureHeight = MeasureHeight(MeasureWidth, i3);
        this.viewHeight = MeasureHeight;
        setMeasuredDimension(MeasureWidth, MeasureHeight);
        this.currentY = 0.0f;
        if (this.viewHeight < this.absloutHeight) {
            play();
        } else {
            stop();
        }
    }

    public void pause() {
        stop();
        this.needStop = true;
    }

    public void play() {
        if (this.scrolling) {
            return;
        }
        this.handler.sendEmptyMessageDelayed(0, this.stopTime);
        this.scrolling = true;
    }

    public void setArrayList(List<String> list) {
        this.lineStrings = new ArrayList<>();
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.lineStrings.add(list.get(i2));
        }
        reset();
    }

    public void setScrollText(String str) {
        this.scrollText = str;
        reset();
    }

    public void stop() {
        if (this.handler.hasMessages(0)) {
            this.handler.removeMessages(0);
        }
        if (this.handler.hasMessages(1)) {
            this.handler.removeMessages(1);
        }
        if (this.handler.hasMessages(2)) {
            this.handler.removeMessages(2);
        }
        if (this.handler.hasMessages(3)) {
            this.handler.removeMessages(3);
        }
        this.scrolling = false;
    }

    public void updateScrollStatus() {
        if (this.scrolling) {
            stop();
        } else {
            play();
        }
    }

    public VerticalMarqueeTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.handler = new Handler() { // from class: com.jingdong.common.sample.jshop.ui.VerticalMarqueeTextView.1
            private void resetCurrentY() {
                VerticalMarqueeTextView verticalMarqueeTextView = VerticalMarqueeTextView.this;
                float f2 = verticalMarqueeTextView.currentY;
                float f3 = verticalMarqueeTextView.absloutHeight;
                if (f2 >= f3 || f2 <= (-f3) || verticalMarqueeTextView.getHeight() <= 0) {
                    VerticalMarqueeTextView.this.currentY = 0.0f;
                }
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (VerticalMarqueeTextView.this.absloutHeight <= r0.getHeight()) {
                    VerticalMarqueeTextView verticalMarqueeTextView = VerticalMarqueeTextView.this;
                    verticalMarqueeTextView.currentY = 0.0f;
                    verticalMarqueeTextView.stop();
                    return;
                }
                int i22 = message.what;
                if (i22 != 0) {
                    if (i22 == 2) {
                        VerticalMarqueeTextView verticalMarqueeTextView2 = VerticalMarqueeTextView.this;
                        verticalMarqueeTextView2.index = verticalMarqueeTextView2.index + (VerticalMarqueeTextView.this.absloutHeight / r1.lineStrings.size());
                        VerticalMarqueeTextView.this.stop();
                        VerticalMarqueeTextView.this.handler.sendEmptyMessageDelayed(0, r8.delayTime);
                        return;
                    } else if (i22 != 3) {
                        return;
                    } else {
                        VerticalMarqueeTextView verticalMarqueeTextView3 = VerticalMarqueeTextView.this;
                        verticalMarqueeTextView3.currentY = 0.0f;
                        verticalMarqueeTextView3.index = verticalMarqueeTextView3.absloutHeight / verticalMarqueeTextView3.lineStrings.size();
                        VerticalMarqueeTextView.this.stop();
                        VerticalMarqueeTextView.this.handler.sendEmptyMessageDelayed(0, r8.delayTime);
                        return;
                    }
                }
                VerticalMarqueeTextView verticalMarqueeTextView4 = VerticalMarqueeTextView.this;
                float f2 = verticalMarqueeTextView4.currentY - verticalMarqueeTextView4.speed;
                verticalMarqueeTextView4.currentY = f2;
                boolean z = true;
                float f3 = verticalMarqueeTextView4.absloutHeight;
                if (f2 >= f3 || f2 <= (-f3)) {
                    verticalMarqueeTextView4.handler.sendEmptyMessageDelayed(3, verticalMarqueeTextView4.stopTime);
                    z = false;
                }
                if (z) {
                    VerticalMarqueeTextView verticalMarqueeTextView5 = VerticalMarqueeTextView.this;
                    if (verticalMarqueeTextView5.currentY < verticalMarqueeTextView5.index) {
                        VerticalMarqueeTextView verticalMarqueeTextView6 = VerticalMarqueeTextView.this;
                        if (verticalMarqueeTextView6.currentY > (-verticalMarqueeTextView6.index) && VerticalMarqueeTextView.this.getHeight() > 0) {
                            VerticalMarqueeTextView.this.handler.sendEmptyMessageDelayed(0, r8.delayTime);
                        }
                    }
                    VerticalMarqueeTextView.this.handler.sendEmptyMessageDelayed(2, r8.stopTime);
                }
                VerticalMarqueeTextView.this.invalidate();
            }
        };
        this.scrollText = "";
        this.exactlyWidth = -1;
        this.exactlyHeight = -1;
        this.index = 0.0f;
        this.scrolling = false;
        this.absloutHeight = 0.0f;
        this.delayTime = 50;
        this.stopTime = 2000;
        this.speed = getLineHeight() / 4;
        init();
    }

    public VerticalMarqueeTextView(Context context) {
        super(context);
        this.handler = new Handler() { // from class: com.jingdong.common.sample.jshop.ui.VerticalMarqueeTextView.1
            private void resetCurrentY() {
                VerticalMarqueeTextView verticalMarqueeTextView = VerticalMarqueeTextView.this;
                float f2 = verticalMarqueeTextView.currentY;
                float f3 = verticalMarqueeTextView.absloutHeight;
                if (f2 >= f3 || f2 <= (-f3) || verticalMarqueeTextView.getHeight() <= 0) {
                    VerticalMarqueeTextView.this.currentY = 0.0f;
                }
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (VerticalMarqueeTextView.this.absloutHeight <= r0.getHeight()) {
                    VerticalMarqueeTextView verticalMarqueeTextView = VerticalMarqueeTextView.this;
                    verticalMarqueeTextView.currentY = 0.0f;
                    verticalMarqueeTextView.stop();
                    return;
                }
                int i22 = message.what;
                if (i22 != 0) {
                    if (i22 == 2) {
                        VerticalMarqueeTextView verticalMarqueeTextView2 = VerticalMarqueeTextView.this;
                        verticalMarqueeTextView2.index = verticalMarqueeTextView2.index + (VerticalMarqueeTextView.this.absloutHeight / r1.lineStrings.size());
                        VerticalMarqueeTextView.this.stop();
                        VerticalMarqueeTextView.this.handler.sendEmptyMessageDelayed(0, r8.delayTime);
                        return;
                    } else if (i22 != 3) {
                        return;
                    } else {
                        VerticalMarqueeTextView verticalMarqueeTextView3 = VerticalMarqueeTextView.this;
                        verticalMarqueeTextView3.currentY = 0.0f;
                        verticalMarqueeTextView3.index = verticalMarqueeTextView3.absloutHeight / verticalMarqueeTextView3.lineStrings.size();
                        VerticalMarqueeTextView.this.stop();
                        VerticalMarqueeTextView.this.handler.sendEmptyMessageDelayed(0, r8.delayTime);
                        return;
                    }
                }
                VerticalMarqueeTextView verticalMarqueeTextView4 = VerticalMarqueeTextView.this;
                float f2 = verticalMarqueeTextView4.currentY - verticalMarqueeTextView4.speed;
                verticalMarqueeTextView4.currentY = f2;
                boolean z = true;
                float f3 = verticalMarqueeTextView4.absloutHeight;
                if (f2 >= f3 || f2 <= (-f3)) {
                    verticalMarqueeTextView4.handler.sendEmptyMessageDelayed(3, verticalMarqueeTextView4.stopTime);
                    z = false;
                }
                if (z) {
                    VerticalMarqueeTextView verticalMarqueeTextView5 = VerticalMarqueeTextView.this;
                    if (verticalMarqueeTextView5.currentY < verticalMarqueeTextView5.index) {
                        VerticalMarqueeTextView verticalMarqueeTextView6 = VerticalMarqueeTextView.this;
                        if (verticalMarqueeTextView6.currentY > (-verticalMarqueeTextView6.index) && VerticalMarqueeTextView.this.getHeight() > 0) {
                            VerticalMarqueeTextView.this.handler.sendEmptyMessageDelayed(0, r8.delayTime);
                        }
                    }
                    VerticalMarqueeTextView.this.handler.sendEmptyMessageDelayed(2, r8.stopTime);
                }
                VerticalMarqueeTextView.this.invalidate();
            }
        };
        this.scrollText = "";
        this.exactlyWidth = -1;
        this.exactlyHeight = -1;
        this.index = 0.0f;
        this.scrolling = false;
        this.absloutHeight = 0.0f;
        this.delayTime = 50;
        this.stopTime = 2000;
        this.speed = getLineHeight() / 4;
        init();
    }
}
