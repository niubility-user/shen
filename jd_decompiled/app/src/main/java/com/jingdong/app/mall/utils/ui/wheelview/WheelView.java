package com.jingdong.app.mall.utils.ui.wheelview;

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
import android.widget.Scroller;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.app.mall.R;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes4.dex */
public class WheelView extends View {
    private static final int[] H = {-15658735, 11184810, 11184810};
    private Scroller A;
    private int B;
    boolean C;
    private List<com.jingdong.app.mall.utils.ui.wheelview.a> D;
    private List<com.jingdong.app.mall.utils.ui.wheelview.b> E;
    private GestureDetector.SimpleOnGestureListener F;
    private Handler G;

    /* renamed from: g  reason: collision with root package name */
    public int f12009g;

    /* renamed from: h  reason: collision with root package name */
    private final int f12010h;

    /* renamed from: i  reason: collision with root package name */
    private c f12011i;

    /* renamed from: j  reason: collision with root package name */
    private int f12012j;

    /* renamed from: k  reason: collision with root package name */
    private int f12013k;

    /* renamed from: l  reason: collision with root package name */
    private int f12014l;

    /* renamed from: m  reason: collision with root package name */
    private int f12015m;

    /* renamed from: n  reason: collision with root package name */
    private int f12016n;
    private TextPaint o;
    private TextPaint p;
    private StaticLayout q;
    private StaticLayout r;
    private StaticLayout s;
    private String t;
    private Drawable u;
    private GradientDrawable v;
    private GradientDrawable w;
    private boolean x;
    private int y;
    private GestureDetector z;

    /* loaded from: classes4.dex */
    class a extends GestureDetector.SimpleOnGestureListener {
        a() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            if (WheelView.this.x) {
                WheelView.this.A.forceFinished(true);
                WheelView.this.q();
                return true;
            }
            return false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            WheelView wheelView = WheelView.this;
            wheelView.B = (wheelView.f12012j * WheelView.this.A()) + WheelView.this.y;
            WheelView wheelView2 = WheelView.this;
            int itemsCount = wheelView2.C ? Integer.MAX_VALUE : wheelView2.f12011i.getItemsCount() * WheelView.this.A();
            WheelView wheelView3 = WheelView.this;
            wheelView3.A.fling(0, WheelView.this.B, 0, ((int) (-f3)) / 2, 0, 0, wheelView3.C ? -itemsCount : 0, itemsCount);
            WheelView.this.M(0);
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            WheelView.this.N();
            WheelView.this.s((int) (-f3));
            return true;
        }
    }

    /* loaded from: classes4.dex */
    class b extends Handler {
        b() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            WheelView.this.A.computeScrollOffset();
            int currY = WheelView.this.A.getCurrY();
            int i2 = WheelView.this.B - currY;
            WheelView.this.B = currY;
            if (i2 != 0) {
                WheelView.this.s(i2);
            }
            if (Math.abs(currY - WheelView.this.A.getFinalY()) < 1) {
                WheelView.this.A.forceFinished(true);
            }
            if (!WheelView.this.A.isFinished()) {
                WheelView.this.G.sendEmptyMessage(message.what);
            } else if (message.what == 0) {
                WheelView.this.G();
            } else {
                WheelView.this.x();
            }
        }
    }

    public WheelView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f12009g = 36;
        this.f12010h = 36 / 5;
        this.f12011i = null;
        this.f12012j = 0;
        this.f12013k = 0;
        this.f12014l = 0;
        this.f12015m = 5;
        this.f12016n = 0;
        this.C = false;
        this.D = new LinkedList();
        this.E = new LinkedList();
        this.F = new a();
        this.G = new b();
        D(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int A() {
        int i2 = this.f12016n;
        if (i2 != 0) {
            return i2;
        }
        StaticLayout staticLayout = this.q;
        if (staticLayout != null && staticLayout.getLineCount() > 2) {
            int lineTop = this.q.getLineTop(2) - this.q.getLineTop(1);
            this.f12016n = lineTop;
            return lineTop;
        }
        return getHeight() / this.f12015m;
    }

    private int B() {
        c y = y();
        if (y == null) {
            return 0;
        }
        int maximumLength = y.getMaximumLength();
        if (maximumLength > 0) {
            return maximumLength;
        }
        String str = null;
        for (int max = Math.max(this.f12012j - (this.f12015m >> 1), 0); max < Math.min(this.f12012j + this.f12015m, y.getItemsCount()); max++) {
            String item = y.getItem(max);
            if (item != null && (str == null || str.length() < item.length())) {
                str = item;
            }
        }
        if (str != null) {
            return str.length();
        }
        return 0;
    }

    private String C(int i2) {
        c cVar = this.f12011i;
        if (cVar == null || cVar.getItemsCount() == 0) {
            return null;
        }
        int itemsCount = this.f12011i.getItemsCount();
        if ((i2 < 0 || i2 >= itemsCount) && !this.C) {
            return null;
        }
        while (i2 < 0) {
            i2 += itemsCount;
        }
        return this.f12011i.getItem(i2 % itemsCount);
    }

    private void D(Context context) {
        GestureDetector gestureDetector = new GestureDetector(context, this.F);
        this.z = gestureDetector;
        gestureDetector.setIsLongpressEnabled(false);
        this.A = new Scroller(context);
    }

    private void E() {
        if (this.o == null) {
            TextPaint textPaint = new TextPaint(33);
            this.o = textPaint;
            textPaint.setTextSize(this.f12009g);
        }
        if (this.p == null) {
            TextPaint textPaint2 = new TextPaint(37);
            this.p = textPaint2;
            textPaint2.setTextSize(this.f12009g);
            this.p.setShadowLayer(0.1f, 0.0f, 0.1f, -4144960);
        }
        if (this.u == null) {
            this.u = getContext().getResources().getDrawable(R.drawable.wheel_val);
        }
        if (this.v == null) {
            this.v = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, H);
        }
        if (this.w == null) {
            this.w = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, H);
        }
        setBackgroundResource(R.drawable.wheel_bg);
    }

    private void F() {
        this.q = null;
        this.s = null;
        this.y = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G() {
        if (this.f12011i == null) {
            return;
        }
        boolean z = false;
        this.B = 0;
        int i2 = this.y;
        int A = A();
        if (i2 <= 0 ? this.f12012j > 0 : this.f12012j < this.f12011i.getItemsCount()) {
            z = true;
        }
        if ((this.C || z) && Math.abs(i2) > A / 2.0f) {
            i2 = i2 < 0 ? i2 + A + 1 : i2 - (A + 1);
        }
        int i3 = i2;
        if (Math.abs(i3) > 1) {
            this.A.startScroll(0, 0, 0, i3, 400);
            M(1);
            return;
        }
        x();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M(int i2) {
        q();
        this.G.sendEmptyMessage(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void N() {
        if (this.x) {
            return;
        }
        this.x = true;
        J();
    }

    private String o(boolean z) {
        String C;
        StringBuilder sb = new StringBuilder();
        int i2 = (this.f12015m >> 1) + 1;
        int i3 = this.f12012j - i2;
        while (true) {
            int i4 = this.f12012j;
            if (i3 <= i4 + i2) {
                if ((z || i3 != i4) && (C = C(i3)) != null) {
                    sb.append(C);
                }
                if (i3 < this.f12012j + i2) {
                    sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                }
                i3++;
            } else {
                return sb.toString();
            }
        }
    }

    private int p(int i2, int i3) {
        E();
        int B = B();
        if (B > 0) {
            this.f12013k = (int) (B * ((float) Math.ceil(Layout.getDesiredWidth("0", this.o))));
        } else {
            this.f12013k = 0;
        }
        this.f12013k += 10;
        this.f12014l = 0;
        String str = this.t;
        if (str != null && str.length() > 0) {
            this.f12014l = (int) Math.ceil(Layout.getDesiredWidth(this.t, this.p));
        }
        boolean z = true;
        if (i3 != 1073741824) {
            int i4 = this.f12013k;
            int i5 = this.f12014l;
            int i6 = i4 + i5 + 20;
            if (i5 > 0) {
                i6 += 8;
            }
            int max = Math.max(i6, getSuggestedMinimumWidth());
            if (i3 != Integer.MIN_VALUE || i2 >= max) {
                i2 = max;
                z = false;
            }
        }
        if (z) {
            int i7 = (i2 - 8) - 20;
            if (i7 <= 0) {
                this.f12014l = 0;
                this.f12013k = 0;
            }
            int i8 = this.f12014l;
            if (i8 > 0) {
                int i9 = this.f12013k;
                double d = i9;
                double d2 = i7;
                Double.isNaN(d);
                Double.isNaN(d2);
                double d3 = i9 + i8;
                Double.isNaN(d3);
                int i10 = (int) ((d * d2) / d3);
                this.f12013k = i10;
                this.f12014l = i7 - i10;
            } else {
                this.f12013k = i7 + 8;
            }
        }
        int i11 = this.f12013k;
        if (i11 > 0) {
            r(i11, this.f12014l);
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        this.G.removeMessages(0);
        this.G.removeMessages(1);
    }

    private void r(int i2, int i3) {
        Layout.Alignment alignment;
        StaticLayout staticLayout;
        Layout.Alignment alignment2;
        StaticLayout staticLayout2 = this.q;
        if (staticLayout2 != null && staticLayout2.getWidth() <= i2) {
            this.q.increaseWidthTo(i2);
        } else {
            String o = o(this.x);
            TextPaint textPaint = this.o;
            if (i3 > 0) {
                alignment = Layout.Alignment.ALIGN_OPPOSITE;
            } else {
                alignment = Layout.Alignment.ALIGN_CENTER;
            }
            this.q = new StaticLayout(o, textPaint, i2, alignment, 1.0f, 15.0f, false);
        }
        if (!this.x && ((staticLayout = this.s) == null || staticLayout.getWidth() > i2)) {
            String item = y() != null ? y().getItem(this.f12012j) : null;
            if (item == null) {
                item = "";
            }
            String str = item;
            TextPaint textPaint2 = this.p;
            if (i3 > 0) {
                alignment2 = Layout.Alignment.ALIGN_OPPOSITE;
            } else {
                alignment2 = Layout.Alignment.ALIGN_CENTER;
            }
            this.s = new StaticLayout(str, textPaint2, i2, alignment2, 1.0f, 15.0f, false);
        } else if (this.x) {
            this.s = null;
        } else {
            this.s.increaseWidthTo(i2);
        }
        if (i3 > 0) {
            StaticLayout staticLayout3 = this.r;
            if (staticLayout3 != null && staticLayout3.getWidth() <= i3) {
                this.r.increaseWidthTo(i3);
            } else {
                this.r = new StaticLayout(this.t, this.p, i3, Layout.Alignment.ALIGN_NORMAL, 1.0f, 15.0f, false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s(int i2) {
        int i3 = this.y + i2;
        this.y = i3;
        int A = i3 / A();
        int i4 = this.f12012j - A;
        if (this.C && this.f12011i.getItemsCount() > 0) {
            while (i4 < 0) {
                i4 += this.f12011i.getItemsCount();
            }
            i4 %= this.f12011i.getItemsCount();
        } else if (!this.x) {
            i4 = Math.min(Math.max(i4, 0), this.f12011i.getItemsCount() - 1);
        } else if (i4 < 0) {
            A = this.f12012j;
            i4 = 0;
        } else if (i4 >= this.f12011i.getItemsCount()) {
            A = (this.f12012j - this.f12011i.getItemsCount()) + 1;
            i4 = this.f12011i.getItemsCount() - 1;
        }
        int i5 = this.y;
        if (i4 != this.f12012j) {
            L(i4, false);
        } else {
            invalidate();
        }
        int A2 = i5 - (A * A());
        this.y = A2;
        if (A2 > getHeight()) {
            this.y = (this.y % getHeight()) + getHeight();
        }
    }

    private void t(Canvas canvas) {
        int height = getHeight() >> 1;
        int A = A() >> 1;
        this.u.setBounds(0, height - A, getWidth(), height + A);
        this.u.draw(canvas);
    }

    private void u(Canvas canvas) {
        canvas.save();
        canvas.translate(0.0f, (-this.q.getLineTop(1)) + this.y);
        this.o.setColor(-16777216);
        this.o.drawableState = getDrawableState();
        this.q.draw(canvas);
        canvas.restore();
    }

    private void v(Canvas canvas) {
        this.v.setBounds(0, 0, getWidth(), getHeight() / this.f12015m);
        this.v.draw(canvas);
        this.w.setBounds(0, getHeight() - (getHeight() / this.f12015m), getWidth(), getHeight());
        this.w.draw(canvas);
    }

    private void w(Canvas canvas) {
        this.p.setColor(-268435456);
        this.p.drawableState = getDrawableState();
        this.q.getLineBounds(this.f12015m >> 1, new Rect());
        if (this.r != null) {
            canvas.save();
            canvas.translate(this.q.getWidth() + 8, r0.top);
            this.r.draw(canvas);
            canvas.restore();
        }
        if (this.s != null) {
            canvas.save();
            canvas.translate(0.0f, r0.top + this.y);
            this.s.draw(canvas);
            canvas.restore();
        }
    }

    private int z(Layout layout) {
        if (layout == null) {
            return 0;
        }
        return Math.max(((A() * this.f12015m) - (this.f12010h * 2)) - 15, getSuggestedMinimumHeight());
    }

    protected void H(int i2, int i3) {
        Iterator<com.jingdong.app.mall.utils.ui.wheelview.a> it = this.D.iterator();
        while (it.hasNext()) {
            it.next().a(this, i2, i3);
        }
    }

    protected void I() {
        Iterator<com.jingdong.app.mall.utils.ui.wheelview.b> it = this.E.iterator();
        while (it.hasNext()) {
            it.next().b(this);
        }
    }

    protected void J() {
        Iterator<com.jingdong.app.mall.utils.ui.wheelview.b> it = this.E.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
    }

    public void K(int i2, int i3) {
        this.A.forceFinished(true);
        this.B = this.y;
        int A = i2 * A();
        Scroller scroller = this.A;
        int i4 = this.B;
        scroller.startScroll(0, i4, 0, A - i4, i3);
        M(0);
        N();
    }

    public void L(int i2, boolean z) {
        c cVar = this.f12011i;
        if (cVar == null || cVar.getItemsCount() == 0) {
            return;
        }
        if (i2 < 0 || i2 >= this.f12011i.getItemsCount()) {
            if (!this.C) {
                return;
            }
            while (i2 < 0) {
                i2 += this.f12011i.getItemsCount();
            }
            i2 %= this.f12011i.getItemsCount();
        }
        int i3 = this.f12012j;
        if (i2 != i3) {
            if (z) {
                K(i2 - i3, 400);
                return;
            }
            F();
            int i4 = this.f12012j;
            this.f12012j = i2;
            H(i4, i2);
            invalidate();
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.q == null) {
            int i2 = this.f12013k;
            if (i2 == 0) {
                p(getWidth(), 1073741824);
            } else {
                r(i2, this.f12014l);
            }
        }
        if (this.f12013k > 0) {
            canvas.save();
            canvas.translate(10.0f, -this.f12010h);
            u(canvas);
            w(canvas);
            canvas.restore();
        }
        t(canvas);
        v(canvas);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        int p = p(size, mode);
        if (mode2 != 1073741824) {
            int z = z(this.q);
            size2 = mode2 == Integer.MIN_VALUE ? Math.min(z, size2) : z;
        }
        setMeasuredDimension(p, size2);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (y() != null && !this.z.onTouchEvent(motionEvent) && motionEvent.getAction() == 1) {
            G();
        }
        return true;
    }

    void x() {
        if (this.x) {
            I();
            this.x = false;
        }
        F();
        invalidate();
    }

    public c y() {
        return this.f12011i;
    }

    public WheelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f12009g = 36;
        this.f12010h = 36 / 5;
        this.f12011i = null;
        this.f12012j = 0;
        this.f12013k = 0;
        this.f12014l = 0;
        this.f12015m = 5;
        this.f12016n = 0;
        this.C = false;
        this.D = new LinkedList();
        this.E = new LinkedList();
        this.F = new a();
        this.G = new b();
        D(context);
    }
}
