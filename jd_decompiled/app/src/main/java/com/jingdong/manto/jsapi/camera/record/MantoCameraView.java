package com.jingdong.manto.jsapi.camera.record;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.jingdong.manto.R;
import com.jingdong.manto.jsapi.camera.record.b;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import kotlinx.coroutines.DebugKt;

/* loaded from: classes15.dex */
public class MantoCameraView extends FrameLayout implements SurfaceHolder.Callback, b.c {
    static final /* synthetic */ boolean p = true;
    private int a;
    private Context b;

    /* renamed from: c */
    private int f13149c;
    private float d;

    /* renamed from: e */
    private int f13150e;

    /* renamed from: f */
    private com.jingdong.manto.jsapi.camera.record.h.a f13151f;

    /* renamed from: g */
    private VideoView f13152g;

    /* renamed from: h */
    private FocusView f13153h;

    /* renamed from: i */
    private MediaPlayer f13154i;

    /* renamed from: j */
    private Bitmap f13155j;

    /* renamed from: k */
    private com.jingdong.manto.jsapi.camera.record.g.a f13156k;

    /* renamed from: l */
    private boolean f13157l;

    /* renamed from: m */
    private final com.jingdong.manto.jsapi.camera.record.d f13158m;

    /* renamed from: n */
    private boolean f13159n;
    private float o;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a extends com.jingdong.manto.jsapi.camera.record.d {
        a(MantoCameraView mantoCameraView, Context context) {
            super(context);
        }

        @Override // com.jingdong.manto.jsapi.camera.record.d
        public void b(int i2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class b implements Runnable {
        b() {
            MantoCameraView.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            MantoCameraView.this.f13151f.a(MantoCameraView.this.f13152g.getHolder(), MantoCameraView.this.d);
        }
    }

    /* loaded from: classes15.dex */
    class c extends Thread {
        c() {
            MantoCameraView.this = r1;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            com.jingdong.manto.jsapi.camera.record.b e2 = com.jingdong.manto.jsapi.camera.record.b.e();
            MantoCameraView mantoCameraView = MantoCameraView.this;
            e2.a(mantoCameraView, mantoCameraView.f13157l);
        }
    }

    /* loaded from: classes15.dex */
    public class d implements b.d {
        d() {
            MantoCameraView.this = r1;
        }

        @Override // com.jingdong.manto.jsapi.camera.record.b.d
        public void a() {
            MantoCameraView.this.f13153h.setVisibility(4);
        }
    }

    public MantoCameraView(@NonNull Context context) {
        this(context, null);
    }

    public MantoCameraView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MantoCameraView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.a = 3;
        this.d = com.jingdong.manto.jsapi.camera.record.a.b(16, 9).d();
        this.f13150e = 0;
        this.f13157l = true;
        this.f13159n = true;
        this.o = 0.0f;
        this.b = context;
        int dMWidthPixels = MantoDensityUtils.getDMWidthPixels();
        this.f13149c = dMWidthPixels;
        this.f13150e = (int) (dMWidthPixels / 16.0f);
        this.f13151f = new com.jingdong.manto.jsapi.camera.record.h.a(context, this);
        setWillNotDraw(false);
        View inflate = LayoutInflater.from(this.b).inflate(R.layout.manto_record_view, this);
        this.f13152g = (VideoView) inflate.findViewById(R.id.video_preview);
        this.f13153h = (FocusView) inflate.findViewById(R.id.fouce_view);
        this.f13152g.getHolder().addCallback(this);
        this.f13158m = new a(this, context);
    }

    private void b(float f2, float f3) {
        this.f13151f.a(f2, f3, new d());
    }

    @Override // com.jingdong.manto.jsapi.camera.record.b.c
    public void a() {
        com.jingdong.manto.jsapi.camera.record.b.e().a(this.f13152g.getHolder(), this.d);
    }

    public void a(int i2) {
        if (i2 != 2) {
            if (i2 != 4) {
                return;
            }
            this.f13152g.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            return;
        }
        g();
        this.f13152g.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.f13151f.a(this.f13152g.getHolder(), this.d);
    }

    public void a(Bitmap bitmap, boolean z) {
        this.f13155j = bitmap;
        com.jingdong.manto.jsapi.camera.record.g.a aVar = this.f13156k;
        if (aVar != null) {
            aVar.a(bitmap);
        }
    }

    public void a(String str) {
        com.jingdong.manto.jsapi.camera.record.g.a aVar = this.f13156k;
        if (aVar != null) {
            aVar.a(str);
        }
    }

    public final void a(boolean z) {
        this.f13151f.a(this.f13152g.getHolder(), this.d, z);
    }

    public boolean a(float f2, float f3) {
        this.f13153h.setVisibility(0);
        if (f2 < this.f13153h.getWidth() / 2) {
            f2 = this.f13153h.getWidth() / 2;
        }
        if (f2 > this.f13149c - (this.f13153h.getWidth() / 2)) {
            f2 = this.f13149c - (this.f13153h.getWidth() / 2);
        }
        if (f3 < this.f13153h.getWidth() / 2) {
            f3 = this.f13153h.getWidth() / 2;
        }
        this.f13153h.setX(f2 - (r0.getWidth() / 2));
        this.f13153h.setY(f3 - (r4.getHeight() / 2));
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f13153h, "scaleX", 1.0f, 0.6f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.f13153h, "scaleY", 1.0f, 0.6f);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.f13153h, "alpha", 1.0f, 0.4f, 1.0f, 0.4f, 1.0f, 0.4f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat).with(ofFloat2).before(ofFloat3);
        animatorSet.setDuration(400L);
        animatorSet.start();
        return true;
    }

    public final void b() {
        this.f13151f.a();
    }

    public void c() {
        this.f13151f.stop();
        g();
        a(1);
        com.jingdong.manto.jsapi.camera.record.b.e().a(false);
        com.jingdong.manto.jsapi.camera.record.b.e().a(false);
    }

    public void d() {
        a(4);
        com.jingdong.manto.jsapi.camera.record.b.e().c(this.b);
        this.f13152g.post(new b());
    }

    public final void e() {
        this.f13151f.b();
    }

    public final void f() {
        this.f13151f.a(this.f13152g.getHolder().getSurface());
    }

    public void g() {
        MediaPlayer mediaPlayer = this.f13154i;
        if (mediaPlayer == null || !mediaPlayer.isPlaying()) {
            return;
        }
        this.f13154i.stop();
        this.f13154i.release();
        this.f13154i = null;
    }

    public final int getFlashMode() {
        return this.a;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isInEditMode()) {
            return;
        }
        this.f13158m.a(ViewCompat.getDisplay(this));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        com.jingdong.manto.jsapi.camera.record.b.e().a();
        if (isInEditMode()) {
            return;
        }
        this.f13158m.a();
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        boolean isInEditMode = isInEditMode();
        super.onMeasure(i2, i3);
        if (isInEditMode) {
            return;
        }
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        com.jingdong.manto.jsapi.camera.record.a b2 = com.jingdong.manto.jsapi.camera.record.a.b(16, 9);
        if (this.f13158m.b() % 180 == 0) {
            b2 = b2.c();
        }
        if (!p && b2 == null) {
            throw new AssertionError();
        }
        if (measuredHeight < (b2.b() * measuredWidth) / b2.a()) {
            this.f13152g.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), View.MeasureSpec.makeMeasureSpec((measuredWidth * b2.b()) / b2.a(), 1073741824));
        } else {
            this.f13152g.measure(View.MeasureSpec.makeMeasureSpec((b2.a() * measuredHeight) / b2.b(), 1073741824), View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824));
        }
    }

    @Override // com.jingdong.manto.jsapi.camera.record.b.c
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        com.jingdong.manto.jsapi.camera.record.g.a aVar = this.f13156k;
        if (aVar != null) {
            aVar.onPreviewFrame(bArr, camera);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            if (motionEvent.getPointerCount() == 1) {
                b(motionEvent.getX(), motionEvent.getY());
            }
            motionEvent.getPointerCount();
        } else if (action == 1) {
            this.f13159n = true;
        } else if (action == 2) {
            if (motionEvent.getPointerCount() == 1) {
                this.f13159n = true;
            }
            if (motionEvent.getPointerCount() == 2) {
                float x = motionEvent.getX(0);
                float y = motionEvent.getY(0);
                float sqrt = (float) Math.sqrt(Math.pow(x - motionEvent.getX(1), 2.0d) + Math.pow(y - motionEvent.getY(1), 2.0d));
                if (this.f13159n) {
                    this.o = sqrt;
                    this.f13159n = false;
                }
                float f2 = sqrt - this.o;
                if (((int) f2) / this.f13150e != 0) {
                    this.f13159n = true;
                    this.f13151f.a(f2, R2.anim.message_center_dialog_out);
                }
            }
        }
        return true;
    }

    public void setCameraListener(com.jingdong.manto.jsapi.camera.record.g.a aVar) {
        this.f13156k = aVar;
    }

    public void setErrorListener(com.jingdong.manto.jsapi.camera.record.g.b bVar) {
        com.jingdong.manto.jsapi.camera.record.b.e().a(bVar);
    }

    public final void setFlashMode(int i2) {
        com.jingdong.manto.jsapi.camera.record.h.a aVar;
        String str;
        this.a = i2;
        if (i2 == 1) {
            aVar = this.f13151f;
            str = "on";
        } else if (i2 == 2) {
            aVar = this.f13151f;
            str = DebugKt.DEBUG_PROPERTY_VALUE_OFF;
        } else if (i2 != 3) {
            return;
        } else {
            aVar = this.f13151f;
            str = "auto";
        }
        aVar.a(str);
    }

    public void setMediaQuality(int i2) {
        com.jingdong.manto.jsapi.camera.record.b.e().b(i2);
    }

    public final void setUseBackCamera(boolean z) {
        this.f13157l = z;
    }

    public final void setVideoFileFullPath(String str) {
        com.jingdong.manto.jsapi.camera.record.b.e().b(str);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        new c().start();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        surfaceHolder.removeCallback(this);
        com.jingdong.manto.jsapi.camera.record.b.e().a();
    }
}
