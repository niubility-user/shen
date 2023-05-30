package com.jd.dynamic.lib.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.dynamic.R;
import com.jd.dynamic.lib.utils.DPIUtil;
import com.jd.dynamic.yoga.YogaConstants;
import com.jd.dynamic.yoga.android.YogaLayout;

/* loaded from: classes13.dex */
public class CornerSimpleDraweeView extends SimpleDraweeView {

    /* renamed from: g  reason: collision with root package name */
    private Path f2479g;

    /* renamed from: h  reason: collision with root package name */
    private int f2480h;

    /* renamed from: i  reason: collision with root package name */
    private int f2481i;

    /* renamed from: j  reason: collision with root package name */
    private int f2482j;

    /* renamed from: k  reason: collision with root package name */
    private int f2483k;

    /* renamed from: l  reason: collision with root package name */
    private int f2484l;

    /* renamed from: m  reason: collision with root package name */
    private Paint f2485m;

    /* renamed from: n  reason: collision with root package name */
    private boolean f2486n;
    private boolean o;
    private boolean p;
    private String q;
    private String r;
    private String s;

    public CornerSimpleDraweeView(Context context) {
        super(context);
        this.q = null;
        init();
    }

    public CornerSimpleDraweeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.q = null;
        init();
    }

    public CornerSimpleDraweeView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.q = null;
        init();
    }

    public CornerSimpleDraweeView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.q = null;
        init();
    }

    public CornerSimpleDraweeView(Context context, GenericDraweeHierarchy genericDraweeHierarchy) {
        super(context, genericDraweeHierarchy);
        this.q = null;
        init();
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0054, code lost:
        if (r0.getStringAttribute(com.jd.dynamic.R.styleable.yoga_yg_height).endsWith("%") != false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x006b, code lost:
        if (r0.getStringAttribute(com.jd.dynamic.R.styleable.yoga_yg_height).endsWith("%") != false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0073, code lost:
        if (((android.view.ViewGroup.MarginLayoutParams) r0).height > 0) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0078, code lost:
        if (((android.view.ViewGroup.MarginLayoutParams) r0).width >= 0) goto L6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x007c, code lost:
        if (((android.view.ViewGroup.MarginLayoutParams) r0).height >= 0) goto L6;
     */
    /* JADX WARN: Removed duplicated region for block: B:43:0x008f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void a(final float f2) {
        boolean z;
        Object tag = getTag(R.id.dynamic_layout_params);
        final boolean z2 = false;
        if (tag instanceof YogaLayout.LayoutParams) {
            YogaLayout.LayoutParams layoutParams = (YogaLayout.LayoutParams) tag;
            float numericAttribute = layoutParams.getNumericAttribute(R.styleable.yoga_yg_aspectRatio);
            if (!TextUtils.equals("1", this.q)) {
                if (!TextUtils.equals("0", this.q) && TextUtils.isEmpty(this.q) && (((ViewGroup.MarginLayoutParams) layoutParams).width <= 0 || ((ViewGroup.MarginLayoutParams) layoutParams).height <= 0)) {
                    int i2 = R.styleable.yoga_yg_width;
                    if (layoutParams.getStringAttribute(i2).endsWith("%")) {
                    }
                    if (!layoutParams.getStringAttribute(i2).endsWith("%")) {
                    }
                    if (((ViewGroup.MarginLayoutParams) layoutParams).width <= 0) {
                    }
                }
                z = false;
                if ((!YogaConstants.isUndefined(numericAttribute) || numericAttribute == 0.0f) && z) {
                    z2 = true;
                }
                if (z2) {
                    setAspectRatio(f2);
                }
            }
            z = true;
            if (!YogaConstants.isUndefined(numericAttribute)) {
            }
            z2 = true;
            if (z2) {
            }
        }
        post(new Runnable() { // from class: com.jd.dynamic.lib.views.i
            @Override // java.lang.Runnable
            public final void run() {
                CornerSimpleDraweeView.this.b(z2, f2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(boolean z, float f2) {
        if (getParent() instanceof YogaLayout) {
            if (z) {
                ((YogaLayout) getParent()).getYogaNodeForView(this).setAspectRatio(f2);
            }
            ((YogaLayout) getParent()).invalidate(this);
        }
        requestLayout();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        if (this.f2480h == 0 && !this.o) {
            super.draw(canvas);
        } else if (Build.VERSION.SDK_INT > 26) {
            canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
            canvas.clipPath(this.f2479g);
            super.draw(canvas);
        } else {
            canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), this.f2485m, 31);
            canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
            super.draw(canvas);
            this.f2485m.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            canvas.drawPath(this.f2479g, this.f2485m);
            this.f2485m.setXfermode(null);
            canvas.restore();
        }
    }

    public String getLoadFailedFun() {
        return this.s;
    }

    public String getLoadSuccessFun() {
        return this.r;
    }

    public void init() {
        this.f2485m = new Paint();
        this.f2479g = new Path();
        this.f2480h = DPIUtil.dip2px(0.0f);
        this.f2481i = DPIUtil.dip2px(0.0f);
        this.f2482j = DPIUtil.dip2px(0.0f);
        this.f2483k = DPIUtil.dip2px(0.0f);
        this.f2484l = DPIUtil.dip2px(0.0f);
        setWillNotDraw(false);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.f2479g.reset();
        if (this.o) {
            Path path = this.f2479g;
            RectF rectF = new RectF(0.0f, 0.0f, getWidth(), getHeight());
            int i6 = this.f2481i;
            int i7 = this.f2482j;
            int i8 = this.f2484l;
            int i9 = this.f2483k;
            path.addRoundRect(rectF, new float[]{i6, i6, i7, i7, i8, i8, i9, i9}, Path.Direction.CCW);
        } else if (this.f2486n) {
            Path path2 = this.f2479g;
            RectF rectF2 = new RectF(0.0f, 0.0f, getWidth(), getHeight());
            int i10 = this.f2480h;
            path2.addRoundRect(rectF2, new float[]{i10, i10, i10, i10, i10, i10, i10, i10}, Path.Direction.CCW);
        } else if (this.p) {
            Path path3 = this.f2479g;
            RectF rectF3 = new RectF(0.0f, 0.0f, getWidth(), getHeight());
            int i11 = this.f2480h;
            path3.addRoundRect(rectF3, new float[]{i11, i11, i11, i11, 0.0f, 0.0f, 0.0f, 0.0f}, Path.Direction.CCW);
        } else {
            Path path4 = this.f2479g;
            RectF rectF4 = new RectF(0.0f, 0.0f, getWidth(), getHeight());
            int i12 = this.f2480h;
            path4.addRoundRect(rectF4, new float[]{0.0f, 0.0f, 0.0f, 0.0f, i12, i12, i12, i12}, Path.Direction.CCW);
        }
    }

    public void setAllCorner(int i2, int i3, int i4, int i5) {
        if (i2 != -1) {
            this.f2481i = i2;
        }
        if (i3 != -1) {
            this.f2482j = i3;
        }
        if (i4 != -1) {
            this.f2483k = i4;
        }
        if (i5 != -1) {
            this.f2484l = i5;
        }
        this.f2479g.reset();
        this.f2486n = false;
        this.o = true;
        this.p = false;
        Path path = this.f2479g;
        RectF rectF = new RectF(0.0f, 0.0f, getWidth(), getHeight());
        int i6 = this.f2481i;
        int i7 = this.f2482j;
        int i8 = this.f2484l;
        int i9 = this.f2483k;
        path.addRoundRect(rectF, new float[]{i6, i6, i7, i7, i8, i8, i9, i9}, Path.Direction.CCW);
        invalidate();
    }

    public void setBottomCorner(int i2) {
        this.f2480h = i2;
        this.f2479g.reset();
        this.f2486n = false;
        this.p = false;
        this.o = false;
        Path path = this.f2479g;
        RectF rectF = new RectF(0.0f, 0.0f, getWidth(), getHeight());
        int i3 = this.f2480h;
        path.addRoundRect(rectF, new float[]{0.0f, 0.0f, 0.0f, 0.0f, i3, i3, i3, i3}, Path.Direction.CCW);
        invalidate();
    }

    public void setCorner(int i2) {
        this.f2480h = i2;
        this.f2479g.reset();
        this.f2486n = true;
        this.o = false;
        Path path = this.f2479g;
        RectF rectF = new RectF(0.0f, 0.0f, getWidth(), getHeight());
        int i3 = this.f2480h;
        path.addRoundRect(rectF, new float[]{i3, i3, i3, i3, i3, i3, i3, i3}, Path.Direction.CCW);
        invalidate();
    }

    @Override // com.facebook.drawee.view.DraweeView, android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            a(bitmap.getWidth() / bitmap.getHeight());
        }
        super.setImageBitmap(bitmap);
    }

    @Override // com.facebook.drawee.view.DraweeView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        if (drawable != null) {
            a(drawable.getIntrinsicWidth() / drawable.getIntrinsicHeight());
        }
        super.setImageDrawable(drawable);
    }

    @Override // com.facebook.drawee.view.SimpleDraweeView, com.facebook.drawee.view.DraweeView, android.widget.ImageView
    public void setImageResource(int i2) {
        if (i2 > 0) {
            setImageDrawable(getResources().getDrawable(i2));
        }
    }

    public void setLoadFailedFun(String str) {
        this.s = str;
    }

    public void setLoadSuccessFun(String str) {
        this.r = str;
    }

    public void setTopCorner(int i2) {
        this.f2480h = i2;
        this.f2479g.reset();
        this.f2486n = false;
        this.o = false;
        this.p = true;
        Path path = this.f2479g;
        RectF rectF = new RectF(0.0f, 0.0f, getWidth(), getHeight());
        int i3 = this.f2480h;
        path.addRoundRect(rectF, new float[]{i3, i3, i3, i3, 0.0f, 0.0f, 0.0f, 0.0f}, Path.Direction.CCW);
        invalidate();
    }

    public void setUseAspectRatio(String str) {
        this.q = str;
    }
}
