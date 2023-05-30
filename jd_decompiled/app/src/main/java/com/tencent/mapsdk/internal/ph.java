package com.tencent.mapsdk.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.facebook.cache.disk.DefaultDiskStorage;
import com.tencent.map.tools.net.NetManager;
import com.tencent.map.tools.net.NetResponse;
import com.tencent.mapsdk.internal.ba;
import com.tencent.mapsdk.internal.ca;
import com.tencent.mapsdk.internal.o4;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes9.dex */
public class ph extends m4 {
    private static final float[] l0 = {1000000.0f, 500000.0f, 200000.0f, 100000.0f, 50000.0f, 25000.0f, 20000.0f, 10000.0f, 5000.0f, 2000.0f, 1000.0f, 500.0f, 200.0f, 100.0f, 50.0f, 20.0f, 10.0f, 5.0f, 2.0f, 1.0f};
    private static final int m0 = 6;
    private static final float n0 = 0.7f;
    private static final float o0 = 1.3f;
    private float[] A;
    private int B;
    private int C;
    private int D;
    private int E;
    private int F;
    private int G;
    private final List<th> H;
    private final ConcurrentHashMap<String, Bitmap> I;
    private final List<String> J;
    private String K;
    private int L;
    private float M;
    private String N;
    private int O;
    private int P;
    private float Q;
    private int R;
    private double S;
    private k T;
    private boolean U;
    private boolean V;
    private LinearLayout W;
    private LinearLayout X;
    private final int Y;
    private final int Z;
    private float a0;
    private Animation b0;
    private List<i> c0;
    private int d0;
    private int e0;

    /* renamed from: f */
    private Bitmap f16983f;
    private int f0;

    /* renamed from: g */
    private Rect f16984g;
    private int g0;

    /* renamed from: h */
    private boolean f16985h;
    private dg h0;

    /* renamed from: i */
    private volatile boolean f16986i;
    private ca.a i0;

    /* renamed from: j */
    private v5 f16987j;
    private a1 j0;

    /* renamed from: k */
    private boolean f16988k;
    private boolean k0;

    /* renamed from: l */
    private Context f16989l;

    /* renamed from: m */
    private ImageView f16990m;

    /* renamed from: n */
    private Bitmap f16991n;
    private Bitmap o;
    private ViewGroup s;
    private int[] w;
    private int[] x;
    private float[] y;
    private float[] z;
    private final int d = 500;

    /* renamed from: e */
    private final int f16982e = 1000;
    private o4.b p = o4.b.RIGHT_BOTTOM;
    private o4.b q = o4.b.LEFT_BOTTOM;
    private boolean r = true;
    private float[] t = {-1.0f, -1.0f, -1.0f, -1.0f};
    private int[] u = {-1, -1, -1, -1};
    private int[] v = {-1, -1, -1, -1};

    /* loaded from: classes9.dex */
    public class a implements View.OnClickListener {
        public a() {
            ph.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (ph.this.i0 == null) {
                ph phVar = ph.this;
                phVar.i0 = ca.a(phVar.f16989l);
            }
            ma.a(ph.this.f16989l, ph.this.i0);
        }
    }

    /* loaded from: classes9.dex */
    public class b implements View.OnLongClickListener {
        public final /* synthetic */ xi a;

        public b(xi xiVar) {
            ph.this = r1;
            this.a = xiVar;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            return f6.a(this.a.getMapContext());
        }
    }

    /* loaded from: classes9.dex */
    public class c implements Animation.AnimationListener {
        public c() {
            ph.this = r1;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            ph.this.d(false);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            ph.this.d(true);
        }
    }

    /* loaded from: classes9.dex */
    public class d implements Runnable {
        public final /* synthetic */ boolean a;

        public d(boolean z) {
            ph.this = r1;
            this.a = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ph.this.T == null) {
                return;
            }
            ph.this.T.a(this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class e implements Runnable {
        public e() {
            ph.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            ph.this.j0.b(ph.this.M);
        }
    }

    /* loaded from: classes9.dex */
    public static /* synthetic */ class f {
        public static final /* synthetic */ int[] a;

        static {
            o4.b.values();
            int[] iArr = new int[6];
            a = iArr;
            try {
                o4.b bVar = o4.b.LEFT_BOTTOM;
                iArr[0] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                int[] iArr2 = a;
                o4.b bVar2 = o4.b.CENTER_BOTTOM;
                iArr2[1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                int[] iArr3 = a;
                o4.b bVar3 = o4.b.RIGHT_BOTTOM;
                iArr3[2] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                int[] iArr4 = a;
                o4.b bVar4 = o4.b.LEFT_TOP;
                iArr4[3] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                int[] iArr5 = a;
                o4.b bVar5 = o4.b.CENTER_TOP;
                iArr5[4] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                int[] iArr6 = a;
                o4.b bVar6 = o4.b.RIGHT_TOP;
                iArr6[5] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* loaded from: classes9.dex */
    public static class g extends ba.i<Bitmap> {
        private WeakReference<ph> b;

        /* renamed from: c */
        private String f16992c;
        private String d;

        public g(ph phVar, String str, String str2) {
            this.b = new WeakReference<>(phVar);
            this.f16992c = str;
            this.d = str2;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Bitmap call() {
            ph phVar;
            WeakReference<ph> weakReference = this.b;
            Bitmap bitmap = null;
            if (weakReference != null && (phVar = weakReference.get()) != null) {
                File file = new File(phVar.c(this.d));
                ma.c(la.v, "Logo[" + this.d + "] request url[" + this.f16992c + "]...");
                ma.c(la.v, "Logo[" + this.d + "] save to[" + file + "]...");
                NetResponse downloadTo = NetManager.getInstance().builder().url(this.f16992c).downloadTo(file);
                if (downloadTo != null && downloadTo.available()) {
                    byte[] bArr = downloadTo.data;
                    int length = bArr.length;
                    ma.c(la.v, "Logo[" + this.d + "] request url ok! bitmap size[" + length + "]...");
                    bitmap = BitmapFactory.decodeByteArray(bArr, 0, length);
                    if (bitmap != null) {
                        phVar.I.put(this.d, bitmap);
                    }
                }
            }
            return bitmap;
        }
    }

    /* loaded from: classes9.dex */
    public static class h extends ba.c<Bitmap> {
        private WeakReference<ph> a;
        private String b;

        public h(ph phVar, String str) {
            this.a = new WeakReference<>(phVar);
            this.b = str;
        }

        @Override // com.tencent.mapsdk.internal.ba.c, com.tencent.map.tools.Callback
        /* renamed from: a */
        public void callback(Bitmap bitmap) {
            WeakReference<ph> weakReference;
            ph phVar;
            if (bitmap == null || (weakReference = this.a) == null || (phVar = weakReference.get()) == null) {
                return;
            }
            fa.b(new File(phVar.c(this.b)), new File(phVar.b(this.b)));
            if (this.b.equals(phVar.K)) {
                ma.c(la.v, "Logo[" + this.b + "] set from net");
                phVar.a(bitmap);
            }
            phVar.J.remove(this.b);
        }
    }

    /* loaded from: classes9.dex */
    public interface i {
        void a(View view, Rect rect, boolean z);

        void a(ph phVar);

        void b(View view, Rect rect, boolean z);

        void b(ph phVar);
    }

    /* loaded from: classes9.dex */
    public enum j {
        WORLD,
        TENCENT
    }

    /* loaded from: classes9.dex */
    public class k extends View {

        /* renamed from: l */
        private static final int f16994l = -16777216;

        /* renamed from: m */
        private static final int f16995m = -7368817;

        /* renamed from: n */
        private static final int f16996n = 35;

        /* renamed from: g */
        private Paint f16997g;

        /* renamed from: h */
        private Paint f16998h;

        /* renamed from: i */
        private Paint f16999i;

        /* renamed from: j */
        private int f17000j;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public k(Context context) {
            super(context);
            ph.this = r4;
            this.f17000j = -16777216;
            Paint paint = new Paint();
            this.f16997g = paint;
            paint.setAntiAlias(true);
            this.f16997g.setStrokeWidth(r4.Q * 1.0f);
            this.f16997g.setStyle(Paint.Style.STROKE);
            Paint paint2 = new Paint();
            this.f16998h = paint2;
            paint2.setTextSize(r4.Q * 12.0f);
            this.f16998h.setAntiAlias(true);
            Paint paint3 = new Paint(65);
            this.f16999i = paint3;
            paint3.setStyle(Paint.Style.FILL);
            this.f16999i.setColor(0);
        }

        private void a(Canvas canvas, int i2) {
            int i3 = (int) (ph.this.Q * 6.0f);
            int i4 = (i2 / 2) + ((int) (ph.this.Q * 7.0f));
            float measureText = this.f16998h.measureText(ph.this.N);
            canvas.drawPaint(this.f16999i);
            float f2 = i3;
            float f3 = i4;
            canvas.drawText(ph.this.N, ((ph.this.P / 2.0f) + f2) - (measureText / 2.0f), f3 - (ph.this.Q * 6.0f), this.f16998h);
            canvas.drawLine(f2, f3, ph.this.P + i3, f3, this.f16997g);
            canvas.drawLine(f2, f3 - (ph.this.Q * 3.0f), f2, f3 + (ph.this.Q * 0.5f), this.f16997g);
            canvas.drawLine(ph.this.P + i3, f3 - (ph.this.Q * 3.0f), i3 + ph.this.P, f3 + (ph.this.Q * 0.5f), this.f16997g);
        }

        public void a(boolean z) {
            int i2 = z ? f16995m : -16777216;
            if (i2 != this.f17000j) {
                this.f17000j = i2;
            }
        }

        @Override // android.view.View
        public void draw(Canvas canvas) {
            super.draw(canvas);
            this.f16997g.setColor(this.f17000j);
            this.f16998h.setColor(this.f17000j);
            a(canvas, getHeight());
        }

        @Override // android.view.View
        public void onMeasure(int i2, int i3) {
            super.onMeasure(i2, i3);
            setMeasuredDimension(Math.min(Math.round(ph.this.P + (ph.this.Q * 12.0f)), ph.this.B / 2), Math.round(ph.this.O * ph.this.Q));
        }
    }

    /* loaded from: classes9.dex */
    public static class l implements Runnable {
        private WeakReference<ph> a;

        public l(ph phVar) {
            this.a = new WeakReference<>(phVar);
        }

        @Override // java.lang.Runnable
        public void run() {
            ph phVar;
            WeakReference<ph> weakReference = this.a;
            if (weakReference == null || (phVar = weakReference.get()) == null) {
                return;
            }
            phVar.a(phVar.s, (Bundle) null);
        }
    }

    public ph(Context context, xi xiVar, int i2) {
        o4.a.values();
        this.w = new int[4];
        o4.a.values();
        this.x = new int[4];
        this.y = new float[]{0.02f, 0.02f, 0.012f, 0.012f};
        this.z = new float[]{0.022f, 0.022f, 0.0125f, 0.0125f};
        this.A = new float[]{0.0185f, 0.0185f, 0.0104f, 0.0104f};
        this.B = 0;
        this.C = 0;
        this.D = 0;
        this.E = 0;
        this.F = 0;
        this.G = 0;
        this.H = new CopyOnWriteArrayList();
        this.I = new ConcurrentHashMap<>();
        this.J = new CopyOnWriteArrayList();
        this.K = null;
        this.L = 0;
        this.M = 0.0f;
        this.N = "50\u7c73";
        this.O = 26;
        this.P = 109;
        this.Q = 1.0f;
        this.U = true;
        this.V = false;
        this.Z = 18;
        this.a0 = Float.MIN_VALUE;
        this.c0 = new ArrayList();
        this.d0 = -1;
        this.e0 = -1;
        this.f0 = -1;
        this.g0 = -1;
        this.k0 = true;
        this.f16989l = context;
        this.j0 = xiVar;
        this.Y = i2;
        this.Q = context.getResources().getDisplayMetrics().density;
        this.f16990m = new ImageView(context);
        this.T = new k(this.f16989l);
        if (this.Q <= 0.0f) {
            this.Q = 1.0f;
        }
        LinearLayout linearLayout = new LinearLayout(this.f16989l);
        this.W = linearLayout;
        linearLayout.setOrientation(1);
        this.W.setGravity(16);
        this.W.setOnClickListener(new a());
        this.W.setOnLongClickListener(new b(xiVar));
        LinearLayout linearLayout2 = new LinearLayout(this.f16989l);
        this.X = linearLayout2;
        linearLayout2.setOrientation(1);
        this.X.setGravity(16);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 3;
        this.X.addView(this.T, layoutParams);
        this.X.setVisibility(8);
        k();
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 3;
        this.W.addView(this.f16990m, layoutParams2);
        uh u = xiVar.u();
        if (u != null) {
            a(u.h());
        }
    }

    private Bitmap a(String str) {
        FileInputStream fileInputStream;
        try {
            File file = new File(b(str));
            if (!file.exists()) {
                ga.a((Closeable) null);
                return null;
            }
            fileInputStream = new FileInputStream(file);
            try {
                Bitmap decodeStream = BitmapFactory.decodeStream(fileInputStream);
                ga.a((Closeable) fileInputStream);
                return decodeStream;
            } catch (Throwable unused) {
                ga.a((Closeable) fileInputStream);
                return null;
            }
        } catch (Throwable unused2) {
            fileInputStream = null;
        }
    }

    private void a(float f2) {
        if (this.M != f2) {
            this.M = f2;
            ba.b(new e());
        }
    }

    public void a(Bitmap bitmap) {
        try {
            this.f16991n = bitmap;
            if (bitmap != null) {
                this.D = bitmap.getWidth();
                this.E = this.f16991n.getHeight();
                this.f16986i = true;
            }
            p();
            r();
        } catch (Exception unused) {
        }
    }

    private void a(String str, String str2) {
        if (this.J.contains(str2)) {
            ma.c(la.v, "Logo[" + str2 + "] is downloading.");
            return;
        }
        this.J.add(str2);
        ma.c(la.v, "Logo[" + str2 + "] start download..");
        ba.a((ba.i) new g(this, str, str2)).a((ba.d.b) null, (ba.c<ba.d.b>) new h(this, str2));
    }

    private FrameLayout.LayoutParams b(int i2, int i3) {
        int i4;
        int i5;
        dg dgVar;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        if (i2 != 0 && i3 != 0) {
            int ordinal = this.p.ordinal();
            if (ordinal != 0) {
                if (ordinal == 1) {
                    layoutParams.gravity = 81;
                    int i6 = this.w[o4.a.BOTTOM.a];
                    layoutParams.bottomMargin = i6;
                    this.e0 = (this.C - i6) - i3;
                } else if (ordinal == 2) {
                    layoutParams.gravity = 85;
                    int[] iArr = this.w;
                    layoutParams.bottomMargin = iArr[o4.a.BOTTOM.a];
                    layoutParams.rightMargin = iArr[o4.a.RIGHT.a];
                    if (li.f16848c.equals(ConstantsAPI.Token.WX_TOKEN_PLATFORMID_VALUE) && (dgVar = this.h0) != null) {
                        dgVar.a(layoutParams.bottomMargin + (i3 * 2));
                    }
                    this.e0 = (this.C - layoutParams.bottomMargin) - i3;
                    i5 = (this.B - layoutParams.rightMargin) - i2;
                    this.d0 = i5;
                } else if (ordinal == 3) {
                    layoutParams.gravity = 51;
                    int[] iArr2 = this.w;
                    int i7 = iArr2[o4.a.TOP.a];
                    layoutParams.topMargin = i7;
                    i4 = iArr2[o4.a.LEFT.a];
                    layoutParams.leftMargin = i4;
                    this.e0 = i7;
                } else if (ordinal == 4) {
                    layoutParams.gravity = 49;
                    int i8 = this.w[o4.a.TOP.a];
                    layoutParams.topMargin = i8;
                    this.e0 = i8;
                } else if (ordinal != 5) {
                    ma.b("Unknown position:" + this.p);
                } else {
                    layoutParams.gravity = 53;
                    int[] iArr3 = this.w;
                    int i9 = iArr3[o4.a.TOP.a];
                    layoutParams.topMargin = i9;
                    int i10 = iArr3[o4.a.RIGHT.a];
                    layoutParams.rightMargin = i10;
                    this.e0 = i9;
                    this.d0 = (this.B - i10) - i2;
                }
                i5 = (this.B - i2) / 2;
                this.d0 = i5;
            } else {
                layoutParams.gravity = 83;
                int[] iArr4 = this.w;
                int i11 = iArr4[o4.a.BOTTOM.a];
                layoutParams.bottomMargin = i11;
                i4 = iArr4[o4.a.LEFT.a];
                layoutParams.leftMargin = i4;
                this.e0 = (this.C - i11) - i3;
            }
            this.d0 = i4;
        }
        return layoutParams;
    }

    public String b(String str) {
        String g2 = g();
        ga.b(g2);
        return g2 + "/" + str;
    }

    public String c(String str) {
        return b(str) + DefaultDiskStorage.FileType.TEMP;
    }

    private int[] c(int i2, int i3) {
        int[] iArr = new int[2];
        float f2 = this.a0;
        if (f2 == Float.MIN_VALUE) {
            int i4 = this.L;
            f2 = i4 != -3 ? i4 != -2 ? i4 != -1 ? i4 != 1 ? 1.0f : 1.2f : 0.8333333f : 0.8f : n0;
        }
        iArr[0] = (int) (i2 * f2);
        iArr[1] = (int) (i3 * f2);
        return iArr;
    }

    private void d(int i2, int i3) {
        String str;
        float[] fArr = l0;
        int length = fArr.length;
        int i4 = this.R - this.Y;
        if (i4 < 0) {
            i4 = 0;
        }
        if (i4 >= length) {
            i4 = length - 1;
        }
        float f2 = fArr[i4];
        a(f2);
        float f3 = i2;
        double d2 = this.S;
        if (d2 != 0.0d) {
            double d3 = f2;
            Double.isNaN(d3);
            f3 = (float) (d3 / d2);
        }
        int round = Math.round(f3);
        this.P = round;
        if (round > i3) {
            this.P = i3;
        } else if (round < i2) {
            this.P = i2;
        }
        if (f2 >= 1000.0f) {
            f2 /= 1000.0f;
            str = "\u516c\u91cc";
        } else {
            str = "\u7c73";
        }
        this.N = ((int) f2) + str;
    }

    public void d(boolean z) {
        LinearLayout linearLayout = this.X;
        if (linearLayout != null) {
            linearLayout.setVisibility(z ? 0 : 8);
            this.X.requestLayout();
            this.X.invalidate();
        }
        k kVar = this.T;
        if (kVar != null) {
            kVar.invalidate();
        }
    }

    private String g() {
        return this.f16989l.getFilesDir().getAbsolutePath() + "/tencentMapSdk/logos";
    }

    private String h() {
        return this.f16989l.getFilesDir().getAbsolutePath() + "/tencentMapSdk/oldLogos";
    }

    private FrameLayout.LayoutParams j() {
        int i2;
        int i3;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        LinearLayout linearLayout = this.X;
        if (linearLayout == null) {
            return layoutParams;
        }
        int measuredWidth = linearLayout.getMeasuredWidth();
        int measuredHeight = this.X.getMeasuredHeight();
        int ordinal = this.q.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                layoutParams.gravity = 81;
                int i4 = this.x[o4.a.BOTTOM.a];
                layoutParams.bottomMargin = i4;
                this.g0 = (this.C - i4) - measuredHeight;
            } else if (ordinal == 2) {
                layoutParams.gravity = 85;
                int[] iArr = this.x;
                int i5 = iArr[o4.a.BOTTOM.a];
                layoutParams.bottomMargin = i5;
                int i6 = iArr[o4.a.RIGHT.a];
                layoutParams.rightMargin = i6;
                this.g0 = (this.C - i5) - measuredHeight;
                i3 = (this.B - i6) - measuredWidth;
                this.f0 = i3;
                return layoutParams;
            } else if (ordinal == 3) {
                layoutParams.gravity = 51;
                int[] iArr2 = this.x;
                int i7 = iArr2[o4.a.TOP.a];
                layoutParams.topMargin = i7;
                i2 = iArr2[o4.a.LEFT.a];
                layoutParams.leftMargin = i2;
                this.g0 = i7;
            } else if (ordinal != 4) {
                if (ordinal != 5) {
                    ma.b("Unknown positionScale:" + this.q);
                } else {
                    layoutParams.gravity = 53;
                    int[] iArr3 = this.x;
                    int i8 = iArr3[o4.a.TOP.a];
                    layoutParams.topMargin = i8;
                    int i9 = iArr3[o4.a.RIGHT.a];
                    layoutParams.rightMargin = i9;
                    this.g0 = i8;
                    this.f0 = (this.B - i9) - measuredWidth;
                }
                return layoutParams;
            } else {
                layoutParams.gravity = 49;
                int i10 = this.x[o4.a.TOP.a];
                layoutParams.topMargin = i10;
                this.g0 = i10;
            }
            i3 = (this.B - measuredWidth) / 2;
            this.f0 = i3;
            return layoutParams;
        }
        layoutParams.gravity = 83;
        int[] iArr4 = this.x;
        int i11 = iArr4[o4.a.BOTTOM.a];
        layoutParams.bottomMargin = i11;
        i2 = iArr4[o4.a.LEFT.a];
        layoutParams.leftMargin = i2;
        this.g0 = (this.C - i11) - measuredHeight;
        this.f0 = i2;
        return layoutParams;
    }

    private void k() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        this.b0 = alphaAnimation;
        alphaAnimation.setDuration(1000L);
        this.b0.setStartOffset(500L);
        this.b0.setAnimationListener(new c());
    }

    private void o() {
        this.I.clear();
    }

    private void p() {
        if (this.B == 0 || this.C == 0) {
            return;
        }
        float f2 = this.Q;
        int i2 = (int) ((this.D * f2) / 3.0f);
        int i3 = (int) ((this.E * f2) / 3.0f);
        int[] c2 = c(i2, i3);
        if (this.F != c2[0] || this.G != c2[1]) {
            this.F = c2[0];
            this.G = c2[1];
            this.f16986i = true;
        }
        float[] fArr = this.y;
        int i4 = this.B;
        if (i4 >= 1080) {
            fArr = this.A;
        } else if (i4 >= 720) {
            fArr = this.z;
        }
        int i5 = o4.a.LEFT.a;
        float f3 = fArr[i5];
        float[] fArr2 = this.t;
        if (fArr2[i5] >= 0.0f) {
            f3 = fArr2[i5];
        }
        int[] iArr = this.w;
        float f4 = i4;
        iArr[i5] = (int) (f3 * f4);
        if (this.k0) {
            this.v[o4.a.BOTTOM.a] = i3;
        }
        int[] iArr2 = this.u;
        if (iArr2[i5] >= 0 && iArr2[i5] < i4 - i2) {
            iArr[i5] = iArr2[i5];
        }
        int i6 = o4.a.RIGHT.a;
        float f5 = fArr[i6];
        if (fArr2[i6] >= 0.0f) {
            f5 = fArr2[i6];
        }
        iArr[i6] = (int) (f4 * f5);
        if (iArr2[i6] >= 0 && iArr2[i6] < i4 - i2) {
            iArr[i6] = iArr2[i6];
        }
        int i7 = o4.a.BOTTOM.a;
        float f6 = fArr[i7];
        if (fArr2[i7] >= 0.0f) {
            f6 = fArr2[i7];
        }
        int i8 = this.C;
        float f7 = i8;
        iArr[i7] = (int) (f6 * f7);
        if (iArr2[i7] >= 0 && iArr2[i7] < i8 - i3) {
            iArr[i7] = iArr2[i7];
        }
        int i9 = o4.a.TOP.a;
        float f8 = fArr[i9];
        if (fArr2[i9] >= 0.0f) {
            f8 = fArr2[i9];
        }
        iArr[i9] = (int) (f7 * f8);
        if (iArr2[i9] >= 0 && iArr2[i9] < i8 - i3) {
            iArr[i9] = iArr2[i9];
        }
        y();
    }

    private void r() {
        if (this.B == 0 || this.C == 0) {
            return;
        }
        int measuredHeight = this.X.getMeasuredHeight();
        int measuredWidth = this.X.getMeasuredWidth();
        float[] fArr = this.y;
        int i2 = this.B;
        if (i2 >= 1080) {
            fArr = this.A;
        } else if (i2 >= 720) {
            fArr = this.z;
        }
        int i3 = o4.a.LEFT.a;
        float f2 = fArr[i3];
        float[] fArr2 = this.t;
        if (fArr2[i3] >= 0.0f) {
            f2 = fArr2[i3];
        }
        int[] iArr = this.x;
        float f3 = i2;
        iArr[i3] = (int) (f2 * f3);
        int[] iArr2 = this.v;
        if (iArr2[i3] >= 0 && iArr2[i3] < i2 - measuredWidth) {
            iArr[i3] = iArr2[i3];
        }
        int i4 = o4.a.RIGHT.a;
        float f4 = fArr[i4];
        if (fArr2[i4] >= 0.0f) {
            f4 = fArr2[i4];
        }
        iArr[i4] = (int) (f3 * f4);
        if (iArr2[i4] >= 0 && iArr2[i4] < i2 - measuredWidth) {
            iArr[i4] = iArr2[i4];
        }
        int i5 = o4.a.BOTTOM.a;
        float f5 = fArr[i5];
        if (fArr2[i5] >= 0.0f) {
            f5 = fArr2[i5];
        }
        int i6 = this.C;
        float f6 = i6;
        iArr[i5] = (int) (f5 * f6);
        if (iArr2[i5] >= 0 && iArr2[i5] < i6 - measuredHeight) {
            iArr[i5] = iArr2[i5];
        }
        int i7 = o4.a.TOP.a;
        float f7 = fArr[i7];
        if (fArr2[i7] >= 0.0f) {
            f7 = fArr2[i7];
        }
        iArr[i7] = (int) (f6 * f7);
        if (iArr2[i7] >= 0 && iArr2[i7] < i6 - measuredHeight) {
            iArr[i7] = iArr2[i7];
        }
        y();
    }

    private void t() {
        int width = this.f16990m.getWidth();
        if (width <= 0) {
            width = 1000;
        }
        double ceil = Math.ceil((this.B * 3.0f) / 8.0f);
        double d2 = this.Q * 6.0f;
        Double.isNaN(d2);
        d((int) Math.ceil(width / 4.0f), (int) (ceil - d2));
    }

    private void v() {
        w();
        this.X.startAnimation(this.b0);
    }

    private void w() {
        LinearLayout linearLayout = this.X;
        if (linearLayout == null || this.b0 == null) {
            return;
        }
        linearLayout.clearAnimation();
        this.b0.reset();
    }

    private void x() {
        if (!this.U) {
            d(false);
            return;
        }
        boolean z = this.V;
        d(true);
        if (z) {
            w();
        } else {
            v();
        }
    }

    private void y() {
        ba.b(new l(this));
    }

    public int a(o4.a aVar) {
        return this.u[aVar.a];
    }

    @Override // com.tencent.mapsdk.internal.o4
    public void a() {
        Iterator<Map.Entry<String, Bitmap>> it = this.I.entrySet().iterator();
        while (it.hasNext()) {
            ga.a(it.next().getValue());
        }
        ga.a(this.f16991n);
        ga.a(this.o);
    }

    @Deprecated
    public void a(int i2) {
        this.L = i2;
        this.a0 = Float.MIN_VALUE;
        q();
    }

    public void a(int i2, double d2) {
        this.R = i2;
        this.S = d2;
        t();
        y();
    }

    @Override // com.tencent.mapsdk.internal.h5
    public void a(int i2, int i3) {
        this.B = i2;
        this.C = i3;
        p();
        r();
    }

    public void a(dg dgVar) {
        this.h0 = dgVar;
    }

    public void a(o4.a aVar, float f2) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        this.t[aVar.a] = f2;
        p();
    }

    public void a(o4.a aVar, int i2) {
        this.u[aVar.a] = i2;
    }

    @Override // com.tencent.mapsdk.internal.o4
    public void a(o4.b bVar) {
        if (this.p != bVar) {
            y();
        }
        this.p = bVar;
    }

    public void a(i iVar) {
        List<i> list = this.c0;
        if (list == null || iVar == null) {
            return;
        }
        list.add(iVar);
    }

    public void a(v5 v5Var, boolean z) {
        StringBuilder sb;
        String str;
        if (this.f16991n == null || v5Var.a(this.f16987j) || this.f16988k != z) {
            this.f16987j = v5Var.clone();
            this.f16988k = z;
            int d2 = (int) v5Var.d();
            if (d2 > 18) {
                d2 = 18;
            }
            th thVar = null;
            for (th thVar2 : this.H) {
                if (d2 >= thVar2.b() && d2 <= thVar2.a()) {
                    Object[] a2 = thVar2.a(v5Var, z);
                    if (a2 != null) {
                        String str2 = (String) a2[0];
                        String str3 = (String) a2[1];
                        Bitmap bitmap = (Bitmap) a2[2];
                        if (bitmap != null) {
                            a(bitmap);
                            this.K = str2;
                        } else if ((!e7.c(str2, this.K)) != false) {
                            ma.c(la.v, "Logo[" + str2 + "] changed! old=" + this.K + "|dark=" + z + "|level=" + d2);
                            Bitmap bitmap2 = this.I.get(str2);
                            if (bitmap2 != null) {
                                if (!bitmap2.isRecycled()) {
                                    a(bitmap2);
                                    this.K = str2;
                                    sb = new StringBuilder();
                                    sb.append("Logo[");
                                    sb.append(str2);
                                    str = "] set from mem cache";
                                    sb.append(str);
                                    ma.c(la.v, sb.toString());
                                    return;
                                }
                                this.I.remove(str2);
                            }
                            Bitmap a3 = a(str2);
                            if (a3 != null) {
                                this.K = str2;
                                this.I.put(str2, a3);
                                a(a3);
                                sb = new StringBuilder();
                                sb.append("Logo[");
                                sb.append(str2);
                                str = "] set from file cache";
                                sb.append(str);
                                ma.c(la.v, sb.toString());
                                return;
                            }
                            this.K = null;
                            a(str3, str2);
                        }
                        thVar = thVar2;
                        break;
                    }
                    thVar = thVar2;
                }
            }
            if (thVar == null) {
                Bitmap bitmap3 = this.f16983f;
                if (bitmap3 == null || bitmap3.isRecycled()) {
                    this.f16983f = a7.c(this.f16989l, "default_tencent_map_logo.png");
                }
                Bitmap bitmap4 = this.f16983f;
                if (bitmap4 != null) {
                    a(bitmap4);
                }
            }
        }
    }

    public void a(List<yh> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        this.H.clear();
        for (int i2 = 0; i2 < list.size(); i2++) {
            yh yhVar = list.get(i2);
            int[] a2 = yhVar.a();
            this.H.add(new th(a2[0], a2[1], yhVar.b()));
        }
    }

    public void a(boolean z) {
        this.r = z;
        ImageView imageView = this.f16990m;
        if (imageView != null) {
            imageView.setVisibility(z ? 0 : 4);
        }
    }

    @Override // com.tencent.mapsdk.internal.o4
    public boolean a(ViewGroup viewGroup, Bundle bundle) {
        boolean z;
        boolean z2 = false;
        if (viewGroup == null) {
            return false;
        }
        this.s = viewGroup;
        if (this.f16986i) {
            ga.a(this.o);
            Bitmap a2 = a7.a(this.f16991n, this.f16989l, this.F, this.G);
            this.o = a2;
            this.f16990m.setImageBitmap(a2);
            z = true;
        } else {
            z = false;
        }
        FrameLayout.LayoutParams b2 = b(this.F, this.G);
        if (viewGroup.indexOfChild(this.W) < 0) {
            viewGroup.addView(this.W, b2);
        } else {
            viewGroup.updateViewLayout(this.W, b2);
        }
        FrameLayout.LayoutParams j2 = j();
        if (viewGroup.indexOfChild(this.X) < 0) {
            viewGroup.addView(this.X, j2);
        } else {
            viewGroup.updateViewLayout(this.X, j2);
        }
        k kVar = this.T;
        if (kVar != null) {
            this.X.updateViewLayout(kVar, kVar.getLayoutParams());
            x();
        }
        this.f16990m.setVisibility(this.r ? 0 : 4);
        if (this.c0 != null) {
            this.W.requestLayout();
            this.X.requestLayout();
            for (i iVar : this.c0) {
                if (this.f16984g != null && !this.f16986i && this.f16985h == this.r) {
                    Rect rect = this.f16984g;
                    int i2 = rect.left;
                    int i3 = this.d0;
                    if (i2 == i3 && rect.top == this.e0 && rect.right == i3 + this.W.getMeasuredWidth() && this.f16984g.bottom == this.e0 + this.W.getMeasuredHeight()) {
                        iVar.a(this.X, new Rect(this.f0, this.g0, 0, 0), this.U);
                    }
                }
                this.f16985h = this.r;
                int i4 = this.d0;
                Rect rect2 = new Rect(i4, this.e0, this.W.getMeasuredWidth() + i4, this.e0 + this.W.getMeasuredHeight());
                this.f16984g = rect2;
                iVar.b(this.W, rect2, this.r);
                iVar.a(this.X, new Rect(this.f0, this.g0, 0, 0), this.U);
            }
        }
        if (!z && this.f16986i) {
            z2 = true;
        }
        this.f16986i = z2;
        viewGroup.requestLayout();
        return true;
    }

    public float b(o4.a aVar) {
        return this.t[aVar.a];
    }

    public void b(float f2) {
        if (f2 > o0) {
            f2 = o0;
        }
        if (f2 < n0) {
            f2 = n0;
        }
        this.L = 0;
        this.a0 = f2;
        q();
    }

    public void b(o4.a aVar, int i2) {
        if (this.k0) {
            this.k0 = false;
        }
        this.v[aVar.a] = i2;
        r();
    }

    public void b(o4.b bVar) {
        if (this.q != bVar) {
            y();
        }
        this.q = bVar;
    }

    public void b(boolean z) {
        if (this.U != z) {
            this.U = z;
            List<i> list = this.c0;
            if (list != null) {
                Iterator<i> it = list.iterator();
                while (it.hasNext()) {
                    it.next().a(this.X, new Rect(this.f0, this.g0, 0, 0), this.U);
                }
            }
        }
        x();
    }

    public int c(o4.a aVar) {
        return this.v[aVar.a];
    }

    public void c(boolean z) {
        this.V = !z;
        x();
    }

    @Override // com.tencent.mapsdk.internal.m4
    public View[] c() {
        return new View[]{this.W, this.X};
    }

    public int d(o4.a aVar) {
        return this.w[aVar.a];
    }

    @Override // com.tencent.mapsdk.internal.m4, com.tencent.mapsdk.internal.o4
    public Rect d() {
        Rect rect = new Rect();
        LinearLayout linearLayout = this.W;
        if (linearLayout != null) {
            rect.left = linearLayout.getLeft();
            rect.bottom = this.W.getBottom();
            rect.right = this.W.getRight();
            rect.top = this.W.getTop();
        }
        return rect;
    }

    public void e() {
        ma.c(la.v, "clearLogoCache..");
        o();
        this.J.clear();
        try {
            File file = new File(g());
            if (file.exists()) {
                File file2 = new File(h());
                ga.a(file.renameTo(file2) ? file2.getAbsolutePath() : file.getAbsolutePath());
            }
        } catch (Throwable unused) {
        }
    }

    public void e(boolean z) {
        ba.b(new d(z));
    }

    public Bitmap f() {
        Drawable drawable;
        ImageView imageView = this.f16990m;
        if (imageView == null || (drawable = imageView.getDrawable()) == null) {
            return null;
        }
        return ((BitmapDrawable) drawable).getBitmap();
    }

    @Override // com.tencent.mapsdk.internal.o4
    public o4.b getPosition() {
        return this.p;
    }

    public o4.b i() {
        return this.q;
    }

    public boolean l() {
        return this.U;
    }

    public void m() {
        List<i> list = this.c0;
        if (list != null) {
            Iterator<i> it = list.iterator();
            while (it.hasNext()) {
                it.next().a(this);
            }
        }
    }

    public void n() {
        List<i> list = this.c0;
        if (list != null) {
            Iterator<i> it = list.iterator();
            while (it.hasNext()) {
                it.next().b(this);
            }
        }
    }

    public void q() {
        p();
    }

    public void s() {
        r();
    }

    public boolean u() {
        return e7.b(this.K) || this.K.contains("tencent") || this.K.contains(sh.f17257k);
    }
}
