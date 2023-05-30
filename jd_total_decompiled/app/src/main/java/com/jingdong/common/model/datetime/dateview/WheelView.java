package com.jingdong.common.model.datetime.dateview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.app.mall.R;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes5.dex */
public class WheelView extends View {
    private static final int ADDITIONAL_ITEMS_SPACE = 0;
    private static final int ADDITIONAL_ITEM_HEIGHT = 15;
    private static final int DEF_VISIBLE_ITEMS = 5;
    private static final int ITEMS_TEXT_COLOR = -257974369;
    private static final int ITEM_OFFSET = 0;
    private static final int LABEL_OFFSET = 0;
    private static final int MESSAGE_JUSTIFY = 1;
    private static final int MESSAGE_SCROLL = 0;
    private static final int MIN_DELTA_FOR_SCROLLING = 1;
    private static final int PADDING = 0;
    private static final int SCROLLING_DURATION = 400;
    private static final int[] SHADOWS_COLORS = {11184810, 11184810, 11184810};
    private static final int VALUE_TEXT_COLOR = -251698333;
    public int TEXT_SIZE;
    private WheelAdapter adapter;
    private Handler animationHandler;
    private GradientDrawable bottomShadow;
    private Drawable centerDrawable;
    private List<OnWheelChangedListener> changingListeners;
    public int currentItem;
    private GestureDetector gestureDetector;
    private GestureDetector.SimpleOnGestureListener gestureListener;
    boolean isCyclic;
    private boolean isScrollingPerformed;
    private int itemHeight;
    private StaticLayout itemsLayout;
    private TextPaint itemsPaint;
    private int itemsWidth;
    private String label;
    private StaticLayout labelLayout;
    public int labelWidth;
    private int lastScrollY;
    private Scroller scroller;
    private List<OnWheelScrollListener> scrollingListeners;
    private int scrollingOffset;
    private GradientDrawable topShadow;
    private StaticLayout valueLayout;
    private TextPaint valuePaint;
    public int visibleItems;

    public WheelView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.adapter = null;
        this.currentItem = 0;
        this.itemsWidth = 0;
        this.labelWidth = 0;
        this.visibleItems = 5;
        this.itemHeight = 0;
        this.isCyclic = false;
        this.changingListeners = new LinkedList();
        this.scrollingListeners = new LinkedList();
        this.gestureListener = new GestureDetector.SimpleOnGestureListener() { // from class: com.jingdong.common.model.datetime.dateview.WheelView.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                if (WheelView.this.isScrollingPerformed) {
                    WheelView.this.scroller.forceFinished(true);
                    WheelView.this.clearMessages();
                    return true;
                }
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
                WheelView wheelView = WheelView.this;
                wheelView.lastScrollY = (wheelView.currentItem * wheelView.getItemHeight()) + WheelView.this.scrollingOffset;
                WheelView wheelView2 = WheelView.this;
                int itemsCount = wheelView2.isCyclic ? Integer.MAX_VALUE : wheelView2.adapter.getItemsCount() * WheelView.this.getItemHeight();
                WheelView wheelView3 = WheelView.this;
                wheelView3.scroller.fling(0, WheelView.this.lastScrollY, 0, ((int) (-f3)) / 2, 0, 0, wheelView3.isCyclic ? -itemsCount : 0, itemsCount);
                WheelView.this.setNextMessage(0);
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
                WheelView.this.startScrolling();
                WheelView.this.doScroll((int) (-f3));
                return true;
            }
        };
        this.animationHandler = new Handler() { // from class: com.jingdong.common.model.datetime.dateview.WheelView.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                WheelView.this.scroller.computeScrollOffset();
                int currY = WheelView.this.scroller.getCurrY();
                int i3 = WheelView.this.lastScrollY - currY;
                WheelView.this.lastScrollY = currY;
                if (i3 != 0) {
                    WheelView.this.doScroll(i3);
                }
                if (Math.abs(currY - WheelView.this.scroller.getFinalY()) < 1) {
                    WheelView.this.scroller.forceFinished(true);
                }
                if (!WheelView.this.scroller.isFinished()) {
                    WheelView.this.animationHandler.sendEmptyMessage(message.what);
                } else if (message.what == 0) {
                    WheelView.this.justify();
                } else {
                    WheelView.this.finishScrolling();
                }
            }
        };
        initData(context);
    }

    private String buildText(boolean z) {
        String textItem;
        StringBuilder sb = new StringBuilder();
        int i2 = (this.visibleItems >> 1) + 1;
        int i3 = this.currentItem - i2;
        while (true) {
            int i4 = this.currentItem;
            if (i3 <= i4 + i2) {
                if ((z || i3 != i4) && (textItem = getTextItem(i3)) != null) {
                    sb.append(textItem);
                }
                if (i3 < this.currentItem + i2) {
                    sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                }
                i3++;
            } else {
                return sb.toString();
            }
        }
    }

    private int calculateLayoutWidth(int i2, int i3) {
        initResourcesIfNecessary();
        int maxTextLength = getMaxTextLength();
        if (maxTextLength > 0) {
            this.itemsWidth = (int) (maxTextLength * ((float) Math.ceil(Layout.getDesiredWidth("0", this.itemsPaint))));
        } else {
            this.itemsWidth = 0;
        }
        this.itemsWidth += 0;
        this.labelWidth = 0;
        String str = this.label;
        if (str != null && str.length() > 0) {
            this.labelWidth = (int) Math.ceil(Layout.getDesiredWidth(this.label, this.valuePaint));
        }
        boolean z = true;
        if (i3 != 1073741824) {
            int i4 = this.itemsWidth;
            int i5 = this.labelWidth;
            int i6 = i4 + i5 + 0;
            if (i5 > 0) {
                i6 += 0;
            }
            int max = Math.max(i6, getSuggestedMinimumWidth());
            if (i3 != Integer.MIN_VALUE || i2 >= max) {
                i2 = max;
                z = false;
            }
        }
        if (z) {
            int i7 = (i2 + 0) - 0;
            if (i7 <= 0) {
                this.labelWidth = 0;
                this.itemsWidth = 0;
            }
            int i8 = this.labelWidth;
            if (i8 > 0) {
                int i9 = this.itemsWidth;
                double d = i9;
                double d2 = i7;
                Double.isNaN(d);
                Double.isNaN(d2);
                double d3 = i9 + i8;
                Double.isNaN(d3);
                int i10 = (int) ((d * d2) / d3);
                this.itemsWidth = i10;
                this.labelWidth = i7 - i10;
            } else {
                this.itemsWidth = i7 + 0;
            }
        }
        int i11 = this.itemsWidth;
        if (i11 > 0) {
            createLayouts(i11, this.labelWidth);
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMessages() {
        this.animationHandler.removeMessages(0);
        this.animationHandler.removeMessages(1);
    }

    private void createLayouts(int i2, int i3) {
        Layout.Alignment alignment;
        StaticLayout staticLayout;
        Layout.Alignment alignment2;
        StaticLayout staticLayout2 = this.itemsLayout;
        if (staticLayout2 != null && staticLayout2.getWidth() <= i2) {
            this.itemsLayout.increaseWidthTo(i2);
        } else {
            String buildText = buildText(this.isScrollingPerformed);
            TextPaint textPaint = this.itemsPaint;
            if (i3 > 0) {
                alignment = Layout.Alignment.ALIGN_OPPOSITE;
            } else {
                alignment = Layout.Alignment.ALIGN_CENTER;
            }
            this.itemsLayout = new StaticLayout(buildText, textPaint, i2, alignment, 1.0f, 15.0f, false);
        }
        if (!this.isScrollingPerformed && ((staticLayout = this.valueLayout) == null || staticLayout.getWidth() > i2)) {
            String item = getAdapter() != null ? getAdapter().getItem(this.currentItem) : null;
            if (item == null) {
                item = "";
            }
            String str = item;
            TextPaint textPaint2 = this.valuePaint;
            if (i3 > 0) {
                alignment2 = Layout.Alignment.ALIGN_OPPOSITE;
            } else {
                alignment2 = Layout.Alignment.ALIGN_CENTER;
            }
            this.valueLayout = new StaticLayout(str, textPaint2, i2, alignment2, 1.0f, 15.0f, false);
        } else if (this.isScrollingPerformed) {
            this.valueLayout = null;
        } else {
            this.valueLayout.increaseWidthTo(i2);
        }
        if (i3 > 0) {
            StaticLayout staticLayout3 = this.labelLayout;
            if (staticLayout3 != null && staticLayout3.getWidth() <= i3) {
                this.labelLayout.increaseWidthTo(i3);
            } else {
                this.labelLayout = new StaticLayout(this.label, this.valuePaint, i3, Layout.Alignment.ALIGN_NORMAL, 1.0f, 15.0f, false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doScroll(int i2) {
        int i3 = this.scrollingOffset + i2;
        this.scrollingOffset = i3;
        int itemHeight = i3 / getItemHeight();
        int i4 = this.currentItem - itemHeight;
        if (this.isCyclic && this.adapter.getItemsCount() > 0) {
            while (i4 < 0) {
                i4 += this.adapter.getItemsCount();
            }
            i4 %= this.adapter.getItemsCount();
        } else if (!this.isScrollingPerformed) {
            i4 = Math.min(Math.max(i4, 0), this.adapter.getItemsCount() - 1);
        } else if (i4 < 0) {
            itemHeight = this.currentItem;
            i4 = 0;
        } else if (i4 >= this.adapter.getItemsCount()) {
            itemHeight = (this.currentItem - this.adapter.getItemsCount()) + 1;
            i4 = this.adapter.getItemsCount() - 1;
        }
        int i5 = this.scrollingOffset;
        if (i4 != this.currentItem) {
            setCurrentItem(i4, false);
        } else {
            invalidate();
        }
        int itemHeight2 = i5 - (itemHeight * getItemHeight());
        this.scrollingOffset = itemHeight2;
        if (itemHeight2 > getHeight()) {
            this.scrollingOffset = (this.scrollingOffset % getHeight()) + getHeight();
        }
    }

    private void drawCenterRect(Canvas canvas) {
    }

    private void drawItems(Canvas canvas) {
        canvas.save();
        canvas.translate(0.0f, (-this.itemsLayout.getLineTop(1)) + this.scrollingOffset);
        this.itemsPaint.setColor(ITEMS_TEXT_COLOR);
        this.itemsPaint.drawableState = getDrawableState();
        this.itemsLayout.draw(canvas);
        canvas.restore();
    }

    private void drawShadows(Canvas canvas) {
        this.topShadow.setBounds(0, 0, getWidth(), getHeight() / this.visibleItems);
        this.topShadow.draw(canvas);
        this.bottomShadow.setBounds(0, getHeight() - (getHeight() / this.visibleItems), getWidth(), getHeight());
        this.bottomShadow.draw(canvas);
    }

    private void drawValue(Canvas canvas) {
        this.valuePaint.setColor(VALUE_TEXT_COLOR);
        this.valuePaint.drawableState = getDrawableState();
        this.itemsLayout.getLineBounds(this.visibleItems >> 1, new Rect());
        if (this.labelLayout != null) {
            canvas.save();
            canvas.translate(this.itemsLayout.getWidth() + 0, r0.top);
            this.labelLayout.draw(canvas);
            canvas.restore();
        }
        if (this.valueLayout != null) {
            canvas.save();
            canvas.translate(0.0f, r0.top + this.scrollingOffset);
            this.valueLayout.draw(canvas);
            canvas.restore();
        }
    }

    private int getDesiredHeight(Layout layout) {
        if (layout == null) {
            return 0;
        }
        return Math.max(((getItemHeight() * this.visibleItems) - 0) - 15, getSuggestedMinimumHeight());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getItemHeight() {
        int i2 = this.itemHeight;
        if (i2 != 0) {
            return i2;
        }
        StaticLayout staticLayout = this.itemsLayout;
        if (staticLayout != null && staticLayout.getLineCount() > 2) {
            int lineTop = this.itemsLayout.getLineTop(2) - this.itemsLayout.getLineTop(1);
            this.itemHeight = lineTop;
            return lineTop;
        }
        return getHeight() / this.visibleItems;
    }

    private int getMaxTextLength() {
        WheelAdapter adapter = getAdapter();
        if (adapter == null) {
            return 0;
        }
        int maximumLength = adapter.getMaximumLength();
        if (maximumLength > 0) {
            return maximumLength;
        }
        String str = null;
        for (int max = Math.max(this.currentItem - (this.visibleItems >> 1), 0); max < Math.min(this.currentItem + this.visibleItems, adapter.getItemsCount()); max++) {
            String item = adapter.getItem(max);
            if (item != null && (str == null || str.length() < item.length())) {
                str = item;
            }
        }
        if (str != null) {
            return str.length();
        }
        return 0;
    }

    private String getTextItem(int i2) {
        WheelAdapter wheelAdapter = this.adapter;
        if (wheelAdapter == null || wheelAdapter.getItemsCount() == 0) {
            return null;
        }
        int itemsCount = this.adapter.getItemsCount();
        if ((i2 < 0 || i2 >= itemsCount) && !this.isCyclic) {
            return null;
        }
        while (i2 < 0) {
            i2 += itemsCount;
        }
        return this.adapter.getItem(i2 % itemsCount);
    }

    private void initData(Context context) {
        GestureDetector gestureDetector = new GestureDetector(context, this.gestureListener);
        this.gestureDetector = gestureDetector;
        gestureDetector.setIsLongpressEnabled(false);
        this.scroller = new Scroller(context);
        dp2Px(context);
    }

    private void initResourcesIfNecessary() {
        if (this.itemsPaint == null) {
            TextPaint textPaint = new TextPaint(1);
            this.itemsPaint = textPaint;
            textPaint.setTextSize(this.TEXT_SIZE);
        }
        if (this.valuePaint == null) {
            TextPaint textPaint2 = new TextPaint(5);
            this.valuePaint = textPaint2;
            textPaint2.setTextSize(this.TEXT_SIZE);
            this.valuePaint.setShadowLayer(0.1f, 0.0f, 0.1f, -4144960);
        }
        if (this.centerDrawable == null) {
            this.centerDrawable = getContext().getResources().getDrawable(R.drawable.flight_wheel_val);
        }
        if (this.topShadow == null) {
            this.topShadow = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, SHADOWS_COLORS);
        }
        if (this.bottomShadow == null) {
            this.bottomShadow = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, SHADOWS_COLORS);
        }
    }

    private void invalidateLayouts() {
        this.itemsLayout = null;
        this.valueLayout = null;
        this.scrollingOffset = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void justify() {
        if (this.adapter == null) {
            return;
        }
        boolean z = false;
        this.lastScrollY = 0;
        int i2 = this.scrollingOffset;
        int itemHeight = getItemHeight();
        if (i2 <= 0 ? this.currentItem > 0 : this.currentItem < this.adapter.getItemsCount()) {
            z = true;
        }
        if ((this.isCyclic || z) && Math.abs(i2) > itemHeight / 2.0f) {
            i2 = i2 < 0 ? i2 + itemHeight + 1 : i2 - (itemHeight + 1);
        }
        int i3 = i2;
        if (Math.abs(i3) > 1) {
            this.scroller.startScroll(0, 0, 0, i3, 400);
            setNextMessage(1);
            return;
        }
        finishScrolling();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNextMessage(int i2) {
        clearMessages();
        this.animationHandler.sendEmptyMessage(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startScrolling() {
        if (this.isScrollingPerformed) {
            return;
        }
        this.isScrollingPerformed = true;
        notifyScrollingListenersAboutStart();
    }

    public void addChangingListener(OnWheelChangedListener onWheelChangedListener) {
        this.changingListeners.add(onWheelChangedListener);
    }

    public void addScrollingListener(OnWheelScrollListener onWheelScrollListener) {
        this.scrollingListeners.add(onWheelScrollListener);
    }

    public void dp2Px(Context context) {
        this.TEXT_SIZE = (int) ((BaseInfo.getDensity() * 17.0f) + 0.5f);
    }

    void finishScrolling() {
        if (this.isScrollingPerformed) {
            notifyScrollingListenersAboutEnd();
            this.isScrollingPerformed = false;
        }
        invalidateLayouts();
        invalidate();
    }

    public WheelAdapter getAdapter() {
        return this.adapter;
    }

    protected void notifyChangingListeners(int i2, int i3) {
        Iterator<OnWheelChangedListener> it = this.changingListeners.iterator();
        while (it.hasNext()) {
            it.next().onChanged(this, i2, i3);
        }
    }

    protected void notifyScrollingListenersAboutEnd() {
        Iterator<OnWheelScrollListener> it = this.scrollingListeners.iterator();
        while (it.hasNext()) {
            it.next().onScrollingFinished(this);
        }
    }

    protected void notifyScrollingListenersAboutStart() {
        Iterator<OnWheelScrollListener> it = this.scrollingListeners.iterator();
        while (it.hasNext()) {
            it.next().onScrollingStarted(this);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.itemsLayout == null) {
            int i2 = this.itemsWidth;
            if (i2 == 0) {
                calculateLayoutWidth(getWidth(), 1073741824);
            } else {
                createLayouts(i2, this.labelWidth);
            }
        }
        if (this.itemsWidth > 0) {
            canvas.save();
            canvas.translate(0.0f, 0.0f);
            drawItems(canvas);
            drawValue(canvas);
            canvas.restore();
        }
        drawCenterRect(canvas);
        drawShadows(canvas);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        int calculateLayoutWidth = calculateLayoutWidth(size, mode);
        if (mode2 != 1073741824) {
            int desiredHeight = getDesiredHeight(this.itemsLayout);
            size2 = mode2 == Integer.MIN_VALUE ? Math.min(desiredHeight, size2) : desiredHeight;
        }
        setMeasuredDimension(calculateLayoutWidth, size2);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (getAdapter() != null && !this.gestureDetector.onTouchEvent(motionEvent) && motionEvent.getAction() == 1) {
            justify();
        }
        return true;
    }

    public void removeChangingListener(OnWheelChangedListener onWheelChangedListener) {
        this.changingListeners.remove(onWheelChangedListener);
    }

    public void removeScrollingListener(OnWheelScrollListener onWheelScrollListener) {
        this.scrollingListeners.remove(onWheelScrollListener);
    }

    public void scroll(int i2, int i3) {
        this.scroller.forceFinished(true);
        this.lastScrollY = this.scrollingOffset;
        int itemHeight = i2 * getItemHeight();
        Scroller scroller = this.scroller;
        int i4 = this.lastScrollY;
        scroller.startScroll(0, i4, 0, itemHeight - i4, i3);
        setNextMessage(0);
        startScrolling();
    }

    public void setAdapter(WheelAdapter wheelAdapter) {
        this.adapter = wheelAdapter;
        invalidateLayouts();
        invalidate();
    }

    public void setCurrentItem(int i2, boolean z) {
        WheelAdapter wheelAdapter = this.adapter;
        if (wheelAdapter == null || wheelAdapter.getItemsCount() == 0) {
            return;
        }
        if (i2 < 0 || i2 >= this.adapter.getItemsCount()) {
            if (!this.isCyclic) {
                return;
            }
            while (i2 < 0) {
                i2 += this.adapter.getItemsCount();
            }
            i2 %= this.adapter.getItemsCount();
        }
        int i3 = this.currentItem;
        if (i2 != i3) {
            if (z) {
                scroll(i2 - i3, 400);
                return;
            }
            invalidateLayouts();
            int i4 = this.currentItem;
            this.currentItem = i2;
            notifyChangingListeners(i4, i2);
            invalidate();
        }
    }

    public void setCyclic(boolean z) {
        this.isCyclic = z;
        invalidate();
        invalidateLayouts();
    }

    public void setInterpolator(Interpolator interpolator) {
        this.scroller.forceFinished(true);
        this.scroller = new Scroller(getContext(), interpolator);
    }

    public void setLabel(String str) {
        String str2 = this.label;
        if (str2 == null || !str2.equals(str)) {
            this.label = str;
            this.labelLayout = null;
            invalidate();
        }
    }

    public void setVisibleItems(int i2) {
        this.visibleItems = i2;
        invalidate();
    }

    public void setCurrentItem(int i2) {
        setCurrentItem(i2, false);
    }

    public WheelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.adapter = null;
        this.currentItem = 0;
        this.itemsWidth = 0;
        this.labelWidth = 0;
        this.visibleItems = 5;
        this.itemHeight = 0;
        this.isCyclic = false;
        this.changingListeners = new LinkedList();
        this.scrollingListeners = new LinkedList();
        this.gestureListener = new GestureDetector.SimpleOnGestureListener() { // from class: com.jingdong.common.model.datetime.dateview.WheelView.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                if (WheelView.this.isScrollingPerformed) {
                    WheelView.this.scroller.forceFinished(true);
                    WheelView.this.clearMessages();
                    return true;
                }
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
                WheelView wheelView = WheelView.this;
                wheelView.lastScrollY = (wheelView.currentItem * wheelView.getItemHeight()) + WheelView.this.scrollingOffset;
                WheelView wheelView2 = WheelView.this;
                int itemsCount = wheelView2.isCyclic ? Integer.MAX_VALUE : wheelView2.adapter.getItemsCount() * WheelView.this.getItemHeight();
                WheelView wheelView3 = WheelView.this;
                wheelView3.scroller.fling(0, WheelView.this.lastScrollY, 0, ((int) (-f3)) / 2, 0, 0, wheelView3.isCyclic ? -itemsCount : 0, itemsCount);
                WheelView.this.setNextMessage(0);
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
                WheelView.this.startScrolling();
                WheelView.this.doScroll((int) (-f3));
                return true;
            }
        };
        this.animationHandler = new Handler() { // from class: com.jingdong.common.model.datetime.dateview.WheelView.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                WheelView.this.scroller.computeScrollOffset();
                int currY = WheelView.this.scroller.getCurrY();
                int i3 = WheelView.this.lastScrollY - currY;
                WheelView.this.lastScrollY = currY;
                if (i3 != 0) {
                    WheelView.this.doScroll(i3);
                }
                if (Math.abs(currY - WheelView.this.scroller.getFinalY()) < 1) {
                    WheelView.this.scroller.forceFinished(true);
                }
                if (!WheelView.this.scroller.isFinished()) {
                    WheelView.this.animationHandler.sendEmptyMessage(message.what);
                } else if (message.what == 0) {
                    WheelView.this.justify();
                } else {
                    WheelView.this.finishScrolling();
                }
            }
        };
        initData(context);
    }

    public WheelView(Context context) {
        super(context);
        this.adapter = null;
        this.currentItem = 0;
        this.itemsWidth = 0;
        this.labelWidth = 0;
        this.visibleItems = 5;
        this.itemHeight = 0;
        this.isCyclic = false;
        this.changingListeners = new LinkedList();
        this.scrollingListeners = new LinkedList();
        this.gestureListener = new GestureDetector.SimpleOnGestureListener() { // from class: com.jingdong.common.model.datetime.dateview.WheelView.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                if (WheelView.this.isScrollingPerformed) {
                    WheelView.this.scroller.forceFinished(true);
                    WheelView.this.clearMessages();
                    return true;
                }
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
                WheelView wheelView = WheelView.this;
                wheelView.lastScrollY = (wheelView.currentItem * wheelView.getItemHeight()) + WheelView.this.scrollingOffset;
                WheelView wheelView2 = WheelView.this;
                int itemsCount = wheelView2.isCyclic ? Integer.MAX_VALUE : wheelView2.adapter.getItemsCount() * WheelView.this.getItemHeight();
                WheelView wheelView3 = WheelView.this;
                wheelView3.scroller.fling(0, WheelView.this.lastScrollY, 0, ((int) (-f3)) / 2, 0, 0, wheelView3.isCyclic ? -itemsCount : 0, itemsCount);
                WheelView.this.setNextMessage(0);
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
                WheelView.this.startScrolling();
                WheelView.this.doScroll((int) (-f3));
                return true;
            }
        };
        this.animationHandler = new Handler() { // from class: com.jingdong.common.model.datetime.dateview.WheelView.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                WheelView.this.scroller.computeScrollOffset();
                int currY = WheelView.this.scroller.getCurrY();
                int i3 = WheelView.this.lastScrollY - currY;
                WheelView.this.lastScrollY = currY;
                if (i3 != 0) {
                    WheelView.this.doScroll(i3);
                }
                if (Math.abs(currY - WheelView.this.scroller.getFinalY()) < 1) {
                    WheelView.this.scroller.forceFinished(true);
                }
                if (!WheelView.this.scroller.isFinished()) {
                    WheelView.this.animationHandler.sendEmptyMessage(message.what);
                } else if (message.what == 0) {
                    WheelView.this.justify();
                } else {
                    WheelView.this.finishScrolling();
                }
            }
        };
        initData(context);
    }
}
