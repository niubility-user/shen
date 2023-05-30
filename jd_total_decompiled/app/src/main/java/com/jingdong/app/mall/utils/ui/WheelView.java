package com.jingdong.app.mall.utils.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.os.Handler;
import android.os.Message;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.internal.view.SupportMenu;
import com.jingdong.app.mall.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class WheelView extends View {
    private boolean A;
    private boolean B;
    @SuppressLint({"HandlerLeak"})
    Handler C;

    /* renamed from: g  reason: collision with root package name */
    private float f11909g;

    /* renamed from: h  reason: collision with root package name */
    private float f11910h;

    /* renamed from: i  reason: collision with root package name */
    private ArrayList<d> f11911i;

    /* renamed from: j  reason: collision with root package name */
    private List<String> f11912j;

    /* renamed from: k  reason: collision with root package name */
    private int f11913k;

    /* renamed from: l  reason: collision with root package name */
    private long f11914l;

    /* renamed from: m  reason: collision with root package name */
    private long f11915m;

    /* renamed from: n  reason: collision with root package name */
    private int f11916n;
    private Paint o;
    private int p;
    private float q;
    private float r;
    private float s;
    private int t;
    private int u;
    private int v;
    private int w;
    private float x;
    private e y;
    private boolean z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f11917g;

        a(int i2) {
            this.f11917g = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i2 = 0;
            while (i2 < WheelView.this.t * 5) {
                try {
                    Thread.sleep(5L);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                WheelView.this.o(this.f11917g > 0 ? i2 : i2 * (-1));
                i2 += 10;
            }
            WheelView.this.p(this.f11917g > 0 ? i2 - 10 : (i2 * (-1)) + 10);
            WheelView.this.x();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f11919g;

        b(int i2) {
            this.f11919g = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i2 = this.f11919g;
            int i3 = i2 > 0 ? i2 : i2 * (-1);
            int i4 = i2 > 0 ? 1 : -1;
            while (true) {
                i3--;
                if (i3 <= 0) {
                    break;
                }
                Iterator it = WheelView.this.f11911i.iterator();
                while (it.hasNext()) {
                    ((d) it.next()).f(1 * i4);
                }
                Message message = new Message();
                message.what = 1;
                WheelView.this.C.sendMessage(message);
                try {
                    Thread.sleep(2L);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
            Iterator it2 = WheelView.this.f11911i.iterator();
            while (it2.hasNext()) {
                ((d) it2.next()).f(i3 * i4);
            }
            Message message2 = new Message();
            message2.what = 1;
            WheelView.this.C.sendMessage(message2);
            try {
                Thread.sleep(2L);
            } catch (InterruptedException e3) {
                e3.printStackTrace();
            }
            Iterator it3 = WheelView.this.f11911i.iterator();
            while (it3.hasNext()) {
                d dVar = (d) it3.next();
                if (dVar.c()) {
                    if (WheelView.this.y != null) {
                        WheelView.this.y.endSelect(dVar.a, dVar.b);
                        return;
                    }
                    return;
                }
            }
        }
    }

    /* loaded from: classes4.dex */
    class c extends Handler {
        c() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 1) {
                return;
            }
            WheelView.this.invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class d {
        public int a = 0;
        public String b = "";

        /* renamed from: c  reason: collision with root package name */
        public int f11921c = 0;
        public int d = 0;

        /* renamed from: e  reason: collision with root package name */
        public int f11922e = 0;

        /* renamed from: f  reason: collision with root package name */
        private TextPaint f11923f;

        /* renamed from: g  reason: collision with root package name */
        private Rect f11924g;

        public d() {
        }

        public void a(Canvas canvas, int i2) {
            if (this.f11923f == null) {
                TextPaint textPaint = new TextPaint();
                this.f11923f = textPaint;
                textPaint.setAntiAlias(true);
            }
            if (this.f11924g == null) {
                this.f11924g = new Rect();
            }
            if (c()) {
                this.f11923f.setColor(WheelView.this.w);
                float e2 = e();
                if (e2 <= 0.0f) {
                    e2 *= -1.0f;
                }
                this.f11923f.setTextSize(WheelView.this.r + ((WheelView.this.s - WheelView.this.r) * (1.0f - (e2 / WheelView.this.t))));
            } else {
                this.f11923f.setColor(WheelView.this.v);
                this.f11923f.setTextSize(WheelView.this.r);
            }
            String str = (String) TextUtils.ellipsize(this.b, this.f11923f, i2, TextUtils.TruncateAt.END);
            this.b = str;
            this.f11923f.getTextBounds(str, 0, str.length(), this.f11924g);
            if (b()) {
                canvas.drawText(this.b, (this.f11921c + (WheelView.this.f11909g / 2.0f)) - (this.f11924g.width() >> 1), this.d + this.f11922e + ((WheelView.this.t + this.f11924g.height()) >> 1), this.f11923f);
            }
        }

        public boolean b() {
            return ((float) (this.d + this.f11922e)) <= WheelView.this.f11910h && (this.d + this.f11922e) + ((WheelView.this.t + this.f11924g.height()) >> 1) >= 0;
        }

        public boolean c() {
            if (this.d + this.f11922e < ((WheelView.this.f11910h / 2.0f) - (WheelView.this.t >> 1)) + WheelView.this.q || this.d + this.f11922e > ((WheelView.this.f11910h / 2.0f) + (WheelView.this.t >> 1)) - WheelView.this.q) {
                if (this.d + this.f11922e + WheelView.this.t < ((WheelView.this.f11910h / 2.0f) - (WheelView.this.t >> 1)) + WheelView.this.q || this.d + this.f11922e + WheelView.this.t > ((WheelView.this.f11910h / 2.0f) + (WheelView.this.t >> 1)) - WheelView.this.q) {
                    return ((float) (this.d + this.f11922e)) <= ((WheelView.this.f11910h / 2.0f) - ((float) (WheelView.this.t >> 1))) + WheelView.this.q && ((float) ((this.d + this.f11922e) + WheelView.this.t)) >= ((WheelView.this.f11910h / 2.0f) + ((float) (WheelView.this.t >> 1))) - WheelView.this.q;
                }
                return true;
            }
            return true;
        }

        public void d(int i2) {
            this.f11922e = i2;
        }

        public float e() {
            return ((WheelView.this.f11910h / 2.0f) - (WheelView.this.t >> 1)) - (this.d + this.f11922e);
        }

        public void f(int i2) {
            this.f11922e = 0;
            this.d += i2;
        }
    }

    /* loaded from: classes4.dex */
    public interface e {
        void endSelect(int i2, String str);

        void selecting(int i2, String str);
    }

    public WheelView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f11911i = new ArrayList<>();
        this.f11912j = new ArrayList();
        this.f11914l = 0L;
        this.f11915m = 200L;
        this.f11916n = 100;
        this.p = -16777216;
        this.q = 2.0f;
        this.r = 14.0f;
        this.s = 22.0f;
        this.t = 50;
        this.u = 7;
        this.v = -16777216;
        this.w = SupportMenu.CATEGORY_MASK;
        this.x = 48.0f;
        this.z = true;
        this.A = true;
        this.B = false;
        this.C = new c();
        v(context, attributeSet);
        w();
    }

    private void n(int i2) {
        Iterator<d> it = this.f11911i.iterator();
        while (it.hasNext()) {
            it.next().d(i2);
        }
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o(int i2) {
        Iterator<d> it = this.f11911i.iterator();
        while (it.hasNext()) {
            it.next().d(i2);
        }
        Message message = new Message();
        message.what = 1;
        this.C.sendMessage(message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(int i2) {
        int e2;
        if (i2 > 0) {
            for (int i3 = 0; i3 < this.f11911i.size(); i3++) {
                if (this.f11911i.get(i3).c()) {
                    e2 = (int) this.f11911i.get(i3).e();
                    e eVar = this.y;
                    if (eVar != null) {
                        eVar.endSelect(this.f11911i.get(i3).a, this.f11911i.get(i3).b);
                    }
                }
            }
            e2 = 0;
        } else {
            for (int size = this.f11911i.size() - 1; size >= 0; size--) {
                if (this.f11911i.get(size).c()) {
                    e2 = (int) this.f11911i.get(size).e();
                    e eVar2 = this.y;
                    if (eVar2 != null) {
                        eVar2.endSelect(this.f11911i.get(size).a, this.f11911i.get(size).b);
                    }
                }
            }
            e2 = 0;
        }
        Iterator<d> it = this.f11911i.iterator();
        while (it.hasNext()) {
            it.next().f(i2 + 0);
        }
        z(e2);
        Message message = new Message();
        message.what = 1;
        this.C.sendMessage(message);
    }

    private void q(int i2) {
        Iterator<d> it = this.f11911i.iterator();
        while (it.hasNext()) {
            it.next().f(i2);
        }
        Message message = new Message();
        message.what = 1;
        this.C.sendMessage(message);
    }

    private void r(Canvas canvas) {
        if (this.o == null) {
            Paint paint = new Paint();
            this.o = paint;
            paint.setColor(this.p);
            this.o.setAntiAlias(true);
            this.o.setStrokeWidth(this.q);
        }
        float f2 = this.f11910h;
        int i2 = this.t;
        float f3 = this.q;
        canvas.drawLine(0.0f, ((f2 / 2.0f) - (i2 >> 1)) + f3, this.f11909g, ((f2 / 2.0f) - (i2 >> 1)) + f3, this.o);
        float f4 = this.f11910h;
        int i3 = this.t;
        float f5 = this.q;
        canvas.drawLine(0.0f, ((f4 / 2.0f) + (i3 >> 1)) - f5, this.f11909g, ((f4 / 2.0f) + (i3 >> 1)) - f5, this.o);
    }

    private synchronized void s(Canvas canvas) {
        if (this.B) {
            return;
        }
        try {
            Iterator<d> it = this.f11911i.iterator();
            while (it.hasNext()) {
                it.next().a(canvas, getMeasuredWidth());
            }
        } catch (Exception unused) {
        }
    }

    private void t(Canvas canvas) {
        LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, this.x, 15921906, 15921906, Shader.TileMode.MIRROR);
        Paint paint = new Paint();
        paint.setShader(linearGradient);
        canvas.drawRect(0.0f, 0.0f, this.f11909g, this.x, paint);
        float f2 = this.f11910h;
        LinearGradient linearGradient2 = new LinearGradient(0.0f, f2 - this.x, 0.0f, f2, 15921906, 15921906, Shader.TileMode.MIRROR);
        Paint paint2 = new Paint();
        paint2.setShader(linearGradient2);
        float f3 = this.f11910h;
        canvas.drawRect(0.0f, f3 - this.x, this.f11909g, f3, paint2);
    }

    private synchronized void u(int i2) {
        new Thread(new a(i2)).start();
    }

    private void v(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.WheelView);
        this.t = (int) obtainStyledAttributes.getDimension(10, this.t);
        this.u = obtainStyledAttributes.getInt(2, this.u);
        this.r = obtainStyledAttributes.getDimension(7, this.r);
        this.s = obtainStyledAttributes.getDimension(9, this.s);
        this.v = obtainStyledAttributes.getColor(6, this.v);
        this.w = obtainStyledAttributes.getColor(8, this.w);
        this.p = obtainStyledAttributes.getColor(3, this.p);
        this.q = obtainStyledAttributes.getDimension(0, this.q);
        this.x = obtainStyledAttributes.getDimension(4, this.x);
        this.A = obtainStyledAttributes.getBoolean(5, true);
        this.z = obtainStyledAttributes.getBoolean(1, true);
        obtainStyledAttributes.recycle();
        this.f11910h = this.u * this.t;
    }

    private void w() {
        this.B = true;
        this.f11911i.clear();
        for (int i2 = 0; i2 < this.f11912j.size(); i2++) {
            d dVar = new d();
            dVar.a = i2;
            dVar.b = this.f11912j.get(i2);
            dVar.f11921c = 0;
            dVar.d = this.t * i2;
            this.f11911i.add(dVar);
        }
        this.B = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        if (this.A) {
            Iterator<d> it = this.f11911i.iterator();
            while (it.hasNext()) {
                if (it.next().c()) {
                    return;
                }
            }
            int e2 = (int) this.f11911i.get(0).e();
            if (e2 < 0) {
                q(e2);
            } else {
                q((int) this.f11911i.get(r0.size() - 1).e());
            }
            Iterator<d> it2 = this.f11911i.iterator();
            while (it2.hasNext()) {
                d next = it2.next();
                if (next.c()) {
                    e eVar = this.y;
                    if (eVar != null) {
                        eVar.endSelect(next.a, next.b);
                        return;
                    }
                    return;
                }
            }
        }
    }

    private void y() {
        if (this.y == null) {
            return;
        }
        Iterator<d> it = this.f11911i.iterator();
        while (it.hasNext()) {
            d next = it.next();
            if (next.c()) {
                this.y.selecting(next.a, next.b);
            }
        }
    }

    private synchronized void z(int i2) {
        new Thread(new b(i2)).start();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        r(canvas);
        s(canvas);
        t(canvas);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        float measuredWidth = getMeasuredWidth();
        this.f11909g = measuredWidth;
        if (measuredWidth != 0.0f) {
            setMeasuredDimension(getMeasuredWidth(), this.u * this.t);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.z) {
            int y = (int) motionEvent.getY();
            int action = motionEvent.getAction();
            if (action == 0) {
                this.f11913k = (int) motionEvent.getY();
                this.f11914l = System.currentTimeMillis();
            } else if (action == 1) {
                int abs = Math.abs(y - this.f11913k);
                if (System.currentTimeMillis() - this.f11914l < this.f11915m && abs > this.f11916n) {
                    u(y - this.f11913k);
                } else {
                    p(y - this.f11913k);
                    x();
                }
            } else if (action == 2) {
                n(y - this.f11913k);
                y();
            }
            return true;
        }
        return true;
    }

    public WheelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f11911i = new ArrayList<>();
        this.f11912j = new ArrayList();
        this.f11914l = 0L;
        this.f11915m = 200L;
        this.f11916n = 100;
        this.p = -16777216;
        this.q = 2.0f;
        this.r = 14.0f;
        this.s = 22.0f;
        this.t = 50;
        this.u = 7;
        this.v = -16777216;
        this.w = SupportMenu.CATEGORY_MASK;
        this.x = 48.0f;
        this.z = true;
        this.A = true;
        this.B = false;
        this.C = new c();
        v(context, attributeSet);
        w();
    }
}
