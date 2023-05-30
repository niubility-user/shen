package com.jingdong.manto.widget.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import androidx.annotation.Nullable;
import com.jingdong.manto.m.u0.c;
import com.jingdong.manto.m.u0.m;
import com.jingdong.manto.widget.canvas.a;
import java.util.LinkedHashSet;
import java.util.Set;
import org.json.JSONArray;

/* loaded from: classes16.dex */
public class CanvasView extends View implements com.jingdong.manto.widget.canvas.a {
    public final com.jingdong.manto.m.u0.a a;
    private final Set<View.OnAttachStateChangeListener> b;

    /* renamed from: c  reason: collision with root package name */
    private Bitmap f14343c;
    private com.jingdong.manto.m.u1.a d;

    /* loaded from: classes16.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            ViewParent parent = CanvasView.this.getParent();
            do {
                ((View) parent).forceLayout();
                parent = parent.getParent();
            } while (parent instanceof View);
            if (parent != null) {
                parent.requestLayout();
                CanvasView.this.invalidate();
            }
        }
    }

    public CanvasView(Context context) {
        super(context);
        this.a = new com.jingdong.manto.m.u0.a(this);
        this.b = new LinkedHashSet();
    }

    public CanvasView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new com.jingdong.manto.m.u0.a(this);
        this.b = new LinkedHashSet();
    }

    public CanvasView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.a = new com.jingdong.manto.m.u0.a(this);
        this.b = new LinkedHashSet();
    }

    public static boolean a(View view) {
        return Build.VERSION.SDK_INT >= 19 ? view.isLaidOut() : view.getWidth() > 0 && view.getHeight() > 0;
    }

    @Override // com.jingdong.manto.widget.canvas.a
    public void a() {
        if (a(this)) {
            postInvalidate();
        } else {
            post(new a());
        }
    }

    public void a(int i2, String str, String str2) {
        com.jingdong.manto.m.u1.a aVar = new com.jingdong.manto.m.u1.a(this.a.b().f13728c, i2, false, str, true, str2, false);
        this.d = aVar;
        setOnTouchListener(aVar);
    }

    @Override // com.jingdong.manto.widget.canvas.a
    public void a(Runnable runnable) {
        post(runnable);
    }

    @Override // com.jingdong.manto.widget.canvas.a
    public void a(JSONArray jSONArray, a.InterfaceC0691a interfaceC0691a) {
        this.a.a(jSONArray, interfaceC0691a);
    }

    @Override // com.jingdong.manto.widget.canvas.a
    public final boolean a(Canvas canvas) {
        return this.a.a(canvas);
    }

    @Override // android.view.View
    public void addOnAttachStateChangeListener(View.OnAttachStateChangeListener onAttachStateChangeListener) {
        if (this.b.contains(onAttachStateChangeListener)) {
            return;
        }
        this.b.add(onAttachStateChangeListener);
        super.addOnAttachStateChangeListener(onAttachStateChangeListener);
    }

    public void b() {
        this.a.c();
    }

    public void b(JSONArray jSONArray, a.InterfaceC0691a interfaceC0691a) {
        this.a.b(jSONArray, interfaceC0691a);
    }

    public final void c() {
        Bitmap bitmap = this.f14343c;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        this.f14343c.recycle();
    }

    public c getDrawContext() {
        return this.a.b();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (measuredWidth <= 0 || measuredHeight <= 0) {
            return;
        }
        Bitmap bitmap = this.f14343c;
        if (bitmap == null || bitmap.isRecycled() || this.f14343c.getWidth() != measuredWidth || this.f14343c.getHeight() != measuredHeight) {
            c();
            try {
                this.f14343c = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.RGB_565);
            } catch (OutOfMemoryError unused) {
                this.f14343c = null;
            }
        }
        Bitmap bitmap2 = this.f14343c;
        if (bitmap2 == null) {
            return;
        }
        bitmap2.eraseColor(0);
        m mVar = new m(this.f14343c);
        a(mVar);
        try {
            canvas.drawBitmap(mVar.a, 0.0f, 0.0f, (Paint) null);
        } catch (Throwable unused2) {
            a(canvas);
        }
    }

    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        c();
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (measuredWidth <= 0 || measuredHeight <= 0) {
            return;
        }
        try {
            this.f14343c = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_4444);
        } catch (OutOfMemoryError unused) {
            this.f14343c = null;
        }
    }

    @Override // android.view.View
    public void removeOnAttachStateChangeListener(View.OnAttachStateChangeListener onAttachStateChangeListener) {
        this.b.remove(onAttachStateChangeListener);
        super.removeOnAttachStateChangeListener(onAttachStateChangeListener);
    }

    public void setEmbeddedCanvas(boolean z) {
        com.jingdong.manto.m.u1.a aVar = this.d;
        if (aVar != null) {
            aVar.a(z);
        }
    }
}
