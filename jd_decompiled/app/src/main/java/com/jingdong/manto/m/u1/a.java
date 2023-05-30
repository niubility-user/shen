package com.jingdong.manto.m.u1;

import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.manto.m.e0;
import com.jingdong.manto.utils.MantoDensityUtils;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class a implements View.OnTouchListener {
    private Handler a = new Handler(Looper.getMainLooper());
    Runnable b;

    /* renamed from: c */
    private e0 f13789c;
    private int d;

    /* renamed from: e */
    private boolean f13790e;

    /* renamed from: f */
    private String f13791f;

    /* renamed from: g */
    private RectF f13792g;

    /* renamed from: h */
    private float f13793h;

    /* renamed from: i */
    private float f13794i;

    /* renamed from: j */
    private boolean f13795j;

    /* renamed from: k */
    private boolean f13796k;

    /* renamed from: l */
    private String f13797l;

    /* renamed from: com.jingdong.manto.m.u1.a$a */
    /* loaded from: classes15.dex */
    class RunnableC0627a implements Runnable {
        final /* synthetic */ View a;
        final /* synthetic */ MotionEvent b;

        /* renamed from: c */
        final /* synthetic */ int f13798c;

        RunnableC0627a(View view, MotionEvent motionEvent, int i2) {
            a.this = r1;
            this.a = view;
            this.b = motionEvent;
            this.f13798c = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.a("touchLongPress", this.a, this.b, this.f13798c, 1);
        }
    }

    public a(e0 e0Var, int i2, boolean z, String str, boolean z2, String str2, boolean z3) {
        this.f13796k = false;
        this.f13789c = e0Var;
        this.d = i2;
        this.f13790e = z;
        this.f13791f = str;
        this.f13795j = z2;
        this.f13797l = str2;
        this.f13796k = z3;
    }

    private RectF a(View view) {
        if (this.f13792g == null) {
            view.getLocationOnScreen(new int[2]);
            this.f13792g = new RectF(r0[0], r0[1], r0[0] + view.getWidth(), r0[1] + view.getHeight());
        }
        return this.f13792g;
    }

    private JSONObject a(View view, MotionEvent motionEvent, int i2) {
        float y;
        float rawY;
        float rawY2;
        double pixel2dip;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", String.valueOf(motionEvent.getPointerId(i2)));
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            if (this.f13796k) {
                jSONObject.put(JshopConst.JSHOP_PROMOTIO_X, MantoDensityUtils.pixel2dip(motionEvent.getX()));
                jSONObject.put(JshopConst.JSHOP_PROMOTIO_Y, MantoDensityUtils.pixel2dip(motionEvent.getY()));
                jSONObject.put("clientX", MantoDensityUtils.pixel2dip(motionEvent.getRawX()));
                jSONObject.put("clientY", MantoDensityUtils.pixel2dip(motionEvent.getRawY()));
                jSONObject.put("pageX", MantoDensityUtils.pixel2dip(motionEvent.getRawX()) + scrollX);
                rawY2 = MantoDensityUtils.pixel2dip(motionEvent.getRawY());
            } else {
                if (this.f13795j) {
                    jSONObject.put(JshopConst.JSHOP_PROMOTIO_X, MantoDensityUtils.pixel2dip(motionEvent.getX(i2)));
                    y = MantoDensityUtils.pixel2dip(motionEvent.getY(i2));
                } else {
                    jSONObject.put(JshopConst.JSHOP_PROMOTIO_X, motionEvent.getX(i2));
                    y = motionEvent.getY(i2);
                }
                jSONObject.put(JshopConst.JSHOP_PROMOTIO_Y, y);
                if (Build.VERSION.SDK_INT >= 29) {
                    if (this.f13795j) {
                        jSONObject.put("clientX", MantoDensityUtils.pixel2dip(motionEvent.getRawX(i2)));
                        rawY = MantoDensityUtils.pixel2dip(motionEvent.getRawY(i2));
                    } else {
                        jSONObject.put("clientX", motionEvent.getRawX(i2));
                        rawY = motionEvent.getRawY(i2);
                    }
                } else if (this.f13795j) {
                    jSONObject.put("clientX", MantoDensityUtils.pixel2dip(motionEvent.getRawX()));
                    rawY = MantoDensityUtils.pixel2dip(motionEvent.getRawY());
                } else {
                    jSONObject.put("clientX", motionEvent.getRawX());
                    rawY = motionEvent.getRawY();
                }
                jSONObject.put("clientY", rawY);
                if (this.f13795j) {
                    jSONObject.put("pageX", MantoDensityUtils.pixel2dip(motionEvent.getRawX() + scrollX));
                    pixel2dip = MantoDensityUtils.pixel2dip(motionEvent.getRawY() + scrollY);
                    jSONObject.put("pageY", pixel2dip);
                    return jSONObject;
                }
                jSONObject.put("pageX", motionEvent.getRawX() + scrollX);
                rawY2 = motionEvent.getRawY();
            }
            pixel2dip = rawY2 + scrollY;
            jSONObject.put("pageY", pixel2dip);
            return jSONObject;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x00b5, code lost:
        if (r10.equals("touchLongPress") == false) goto L90;
     */
    /* JADX WARN: Removed duplicated region for block: B:103:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:121:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x009f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(java.lang.String r10, android.view.View r11, android.view.MotionEvent r12, int r13, int r14) {
        /*
            Method dump skipped, instructions count: 268
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.m.u1.a.a(java.lang.String, android.view.View, android.view.MotionEvent, int, int):void");
    }

    private boolean a(float f2, float f3) {
        float f4 = f2 - this.f13793h;
        float f5 = f3 - this.f13794i;
        return Math.sqrt((double) ((f4 * f4) + (f5 * f5))) > 10.0d;
    }

    public void a(boolean z) {
        this.f13796k = z;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        String str;
        RectF a = a(view);
        float rawX = motionEvent.getRawX();
        float rawY = motionEvent.getRawY();
        if (this.f13796k) {
            rawX = MantoDensityUtils.pixel2dip(rawX);
            rawY = MantoDensityUtils.pixel2dip(rawY);
        }
        boolean contains = a.contains(rawX, rawY);
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        int pointerCount = motionEvent.getPointerCount();
        if (!contains) {
            Runnable runnable = this.b;
            if (runnable != null) {
                this.a.removeCallbacks(runnable);
            }
            return false;
        }
        switch (actionMasked) {
            case 0:
                if (1 == pointerCount) {
                    if (this.b == null) {
                        this.b = new RunnableC0627a(view, motionEvent, actionIndex);
                    }
                    int longPressTimeout = ViewConfiguration.getLongPressTimeout();
                    if (longPressTimeout <= 0) {
                        longPressTimeout = 400;
                    }
                    this.a.postDelayed(this.b, longPressTimeout);
                } else {
                    this.a.removeCallbacks(this.b);
                }
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                if (this.f13796k) {
                    x = MantoDensityUtils.pixel2dip(x);
                    y = MantoDensityUtils.pixel2dip(y);
                }
                this.f13793h = x;
                this.f13794i = y;
                str = "touchStart";
                break;
            case 1:
            case 6:
                this.a.removeCallbacks(this.b);
                str = "touchEnd";
                break;
            case 2:
                float x2 = motionEvent.getX();
                float y2 = motionEvent.getY();
                if (this.f13796k) {
                    x2 = MantoDensityUtils.pixel2dip(x2);
                    y2 = MantoDensityUtils.pixel2dip(y2);
                }
                if (a(x2, y2)) {
                    this.f13793h = x2;
                    this.f13794i = y2;
                    this.a.removeCallbacks(this.b);
                    a("touchMove", view, motionEvent, actionIndex, pointerCount);
                }
                return true;
            case 3:
            case 4:
                this.a.removeCallbacks(this.b);
                str = "touchCancel";
                break;
            case 5:
                this.a.removeCallbacks(this.b);
                str = "touchStart";
                break;
            default:
                return false;
        }
        a(str, view, motionEvent, actionIndex, pointerCount);
        return true;
    }
}
