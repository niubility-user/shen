package com.jd.verify.View.gif;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import java.io.InputStream;

/* loaded from: classes18.dex */
public class GifView extends View implements com.jd.verify.View.gif.a {
    private com.jd.verify.View.gif.b a;
    private Bitmap b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f7139c;
    private boolean d;

    /* renamed from: e  reason: collision with root package name */
    private int f7140e;

    /* renamed from: f  reason: collision with root package name */
    private Rect f7141f;

    /* renamed from: g  reason: collision with root package name */
    private c f7142g;

    /* renamed from: h  reason: collision with root package name */
    private d f7143h;

    /* renamed from: i  reason: collision with root package name */
    private Handler f7144i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f7145j;

    /* loaded from: classes18.dex */
    class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            GifView.this.invalidate();
        }
    }

    /* loaded from: classes18.dex */
    static /* synthetic */ class b {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[d.values().length];
            a = iArr;
            try {
                iArr[d.WAIT_FINISH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[d.COVER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[d.SYNC_DECODER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes18.dex */
    private class c extends Thread {
        private c() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            if (GifView.this.a == null) {
                return;
            }
            GifView.this.a.e();
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            while (GifView.this.f7139c) {
                try {
                    if (!GifView.this.d) {
                        com.jd.verify.View.gif.c h2 = GifView.this.a.h();
                        if (GifView.this.f7145j && GifView.this.a.d() == GifView.this.a.e() - 1) {
                            GifView.this.f7142g = null;
                            return;
                        }
                        GifView.this.b = h2.a;
                        long j2 = h2.b;
                        if (GifView.this.f7144i == null) {
                            return;
                        }
                        GifView.this.f7144i.sendMessage(GifView.this.f7144i.obtainMessage());
                        SystemClock.sleep(j2);
                    } else {
                        SystemClock.sleep(10L);
                    }
                } catch (Exception unused) {
                    com.jd.verify.j.d.b("DrawThread Exception!");
                    return;
                }
            }
        }

        /* synthetic */ c(GifView gifView, a aVar) {
            this();
        }
    }

    /* loaded from: classes18.dex */
    public enum d {
        WAIT_FINISH(0),
        SYNC_DECODER(1),
        COVER(2);

        d(int i2) {
        }
    }

    public GifView(Context context) {
        super(context);
        this.a = null;
        this.b = null;
        this.f7139c = true;
        this.d = false;
        this.f7140e = -1;
        this.f7141f = null;
        this.f7142g = null;
        this.f7143h = d.SYNC_DECODER;
        this.f7144i = new a();
        this.f7145j = false;
    }

    private void setGifDecoderImage(byte[] bArr) {
        a();
        this.f7145j = false;
        com.jd.verify.View.gif.b bVar = new com.jd.verify.View.gif.b(bArr, this);
        this.a = bVar;
        bVar.start();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        com.jd.verify.View.gif.b bVar = this.a;
        if (bVar == null) {
            return;
        }
        if (this.b == null) {
            this.b = bVar.f();
        }
        if (this.b == null) {
            return;
        }
        int saveCount = canvas.getSaveCount();
        canvas.save();
        canvas.translate(getPaddingLeft(), getPaddingTop());
        if (this.f7140e == -1) {
            canvas.drawBitmap(this.b, 0.0f, 0.0f, (Paint) null);
        } else {
            canvas.drawBitmap(this.b, (Rect) null, this.f7141f, (Paint) null);
        }
        canvas.restoreToCount(saveCount);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int i4;
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        com.jd.verify.View.gif.b bVar = this.a;
        int i5 = 1;
        if (bVar == null) {
            i4 = 1;
        } else {
            int i6 = bVar.f7147c;
            i5 = bVar.d;
            i4 = i6;
        }
        setMeasuredDimension(View.resolveSize(Math.max(i4 + paddingLeft + paddingRight, getSuggestedMinimumWidth()), i2), View.resolveSize(Math.max(i5 + paddingTop + paddingBottom, getSuggestedMinimumHeight()), i3));
    }

    public void setGifImage(byte[] bArr) {
        setGifDecoderImage(bArr);
    }

    public void setGifImageOnce(int i2) {
        setGifImage(i2);
        this.f7145j = true;
    }

    public void setGifImageType(d dVar) {
        if (this.a == null) {
            this.f7143h = dVar;
        }
    }

    private void b() {
        Handler handler = this.f7144i;
        if (handler != null) {
            this.f7144i.sendMessage(handler.obtainMessage());
        }
    }

    public void setGifImage(InputStream inputStream) {
        setGifDecoderImage(inputStream);
    }

    public void setGifImage(int i2) {
        setGifDecoderImage(getResources().openRawResource(i2));
    }

    public void a() {
        com.jd.verify.View.gif.b bVar = this.a;
        if (bVar != null) {
            bVar.c();
            this.a = null;
        }
    }

    private void setGifDecoderImage(InputStream inputStream) {
        a();
        this.f7145j = false;
        com.jd.verify.View.gif.b bVar = new com.jd.verify.View.gif.b(inputStream, this);
        this.a = bVar;
        bVar.start();
    }

    public void a(int i2, int i3) {
        if (i2 <= 0 || i3 <= 0) {
            return;
        }
        this.f7140e = i2;
        Rect rect = new Rect();
        this.f7141f = rect;
        rect.left = 0;
        rect.top = 0;
        rect.right = i2;
        rect.bottom = i3;
    }

    public GifView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GifView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.a = null;
        this.b = null;
        this.f7139c = true;
        this.d = false;
        this.f7140e = -1;
        this.f7141f = null;
        this.f7142g = null;
        this.f7143h = d.SYNC_DECODER;
        this.f7144i = new a();
        this.f7145j = false;
    }

    @Override // com.jd.verify.View.gif.a
    public void a(boolean z, int i2) {
        if (!z || this.a == null) {
            return;
        }
        int i3 = b.a[this.f7143h.ordinal()];
        a aVar = null;
        if (i3 == 1) {
            if (i2 == -1) {
                if (this.a.e() > 1) {
                    new c(this, aVar).start();
                } else {
                    b();
                }
            }
        } else if (i3 != 2) {
            if (i3 != 3) {
                return;
            }
            if (i2 == 1) {
                this.b = this.a.f();
                b();
            } else if (i2 == -1) {
                b();
            } else if (this.f7142g == null) {
                c cVar = new c(this, aVar);
                this.f7142g = cVar;
                cVar.start();
            }
        } else if (i2 == 1) {
            this.b = this.a.f();
            b();
        } else if (i2 == -1) {
            if (this.a.e() > 1) {
                if (this.f7142g == null) {
                    c cVar2 = new c(this, aVar);
                    this.f7142g = cVar2;
                    cVar2.start();
                    return;
                }
                return;
            }
            b();
        }
    }
}
