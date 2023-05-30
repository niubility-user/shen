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
import android.util.AttributeSet;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.dynamic.lib.utils.DPIUtil;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(final float r8) {
        /*
            r7 = this;
            int r0 = com.jd.dynamic.R.id.dynamic_layout_params
            java.lang.Object r0 = r7.getTag(r0)
            boolean r1 = r0 instanceof com.jd.dynamic.yoga.android.YogaLayout.LayoutParams
            r2 = 0
            if (r1 == 0) goto L92
            com.jd.dynamic.yoga.android.YogaLayout$LayoutParams r0 = (com.jd.dynamic.yoga.android.YogaLayout.LayoutParams) r0
            int r1 = com.jd.dynamic.R.styleable.yoga_yg_aspectRatio
            float r1 = r0.getNumericAttribute(r1)
            java.lang.String r3 = r7.q
            java.lang.String r4 = "1"
            boolean r3 = android.text.TextUtils.equals(r4, r3)
            r4 = 1
            if (r3 == 0) goto L20
        L1e:
            r0 = 1
            goto L7f
        L20:
            java.lang.String r3 = r7.q
            java.lang.String r5 = "0"
            boolean r3 = android.text.TextUtils.equals(r5, r3)
            if (r3 == 0) goto L2b
            goto L7e
        L2b:
            java.lang.String r3 = r7.q
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L7e
            int r3 = r0.width
            if (r3 <= 0) goto L3c
            int r3 = r0.height
            if (r3 <= 0) goto L3c
            goto L7e
        L3c:
            int r3 = com.jd.dynamic.R.styleable.yoga_yg_width
            java.lang.String r5 = r0.getStringAttribute(r3)
            java.lang.String r6 = "%"
            boolean r5 = r5.endsWith(r6)
            if (r5 == 0) goto L57
            int r5 = com.jd.dynamic.R.styleable.yoga_yg_height
            java.lang.String r5 = r0.getStringAttribute(r5)
            boolean r5 = r5.endsWith(r6)
            if (r5 == 0) goto L57
            goto L7e
        L57:
            java.lang.String r3 = r0.getStringAttribute(r3)
            boolean r3 = r3.endsWith(r6)
            if (r3 != 0) goto L6d
            int r3 = com.jd.dynamic.R.styleable.yoga_yg_height
            java.lang.String r3 = r0.getStringAttribute(r3)
            boolean r3 = r3.endsWith(r6)
            if (r3 == 0) goto L76
        L6d:
            int r3 = r0.width
            if (r3 > 0) goto L7e
            int r3 = r0.height
            if (r3 <= 0) goto L76
            goto L7e
        L76:
            int r3 = r0.width
            if (r3 >= 0) goto L1e
            int r0 = r0.height
            if (r0 >= 0) goto L1e
        L7e:
            r0 = 0
        L7f:
            boolean r3 = com.jd.dynamic.yoga.YogaConstants.isUndefined(r1)
            if (r3 != 0) goto L8a
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L8d
        L8a:
            if (r0 == 0) goto L8d
            r2 = 1
        L8d:
            if (r2 == 0) goto L92
            r7.setAspectRatio(r8)
        L92:
            com.jd.dynamic.lib.views.i r0 = new com.jd.dynamic.lib.views.i
            r0.<init>()
            r7.post(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.views.CornerSimpleDraweeView.a(float):void");
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
