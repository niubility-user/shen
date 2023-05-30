package com.jingdong.app.mall.utils.ui.seekbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.TypedValue;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.meizu.cloud.pushsdk.platform.message.BasicPushStatus;
import com.tencent.connect.common.Constants;
import com.unionpay.tsmservice.data.Constant;

/* loaded from: classes4.dex */
class a {
    private final Paint a;
    private final float b;

    /* renamed from: c  reason: collision with root package name */
    private final float f11944c;
    private final float d;

    /* renamed from: e  reason: collision with root package name */
    private int f11945e;

    /* renamed from: f  reason: collision with root package name */
    private float f11946f;

    /* renamed from: g  reason: collision with root package name */
    private final float f11947g;

    /* renamed from: h  reason: collision with root package name */
    private final float f11948h;

    /* renamed from: i  reason: collision with root package name */
    private final float f11949i;

    /* renamed from: j  reason: collision with root package name */
    private float f11950j;

    /* renamed from: k  reason: collision with root package name */
    private float f11951k;

    /* renamed from: l  reason: collision with root package name */
    private int f11952l;

    /* renamed from: m  reason: collision with root package name */
    private String[] f11953m = {"0", Constant.TRANS_TYPE_LOAD, BasicPushStatus.SUCCESS_CODE, "500", Constants.DEFAULT_UIN, "3000", "\u221e"};

    /* renamed from: n  reason: collision with root package name */
    private int f11954n;
    private int o;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(Context context, float f2, float f3, float f4, int i2, float f5, float f6, int i3, float f7) {
        this.f11954n = -1;
        this.o = -1;
        this.b = f2;
        this.f11944c = f2 + f4;
        this.d = f3;
        this.f11952l = i3;
        int i4 = i2 - 1;
        this.f11945e = i4;
        this.f11946f = f4 / i4;
        float applyDimension = TypedValue.applyDimension(1, f5, context.getResources().getDisplayMetrics());
        this.f11947g = applyDimension;
        this.f11948h = f3 - (applyDimension / 2.0f);
        this.f11949i = f3 + (applyDimension / 2.0f);
        Paint paint = new Paint();
        this.a = paint;
        paint.setColor(i3);
        paint.setStrokeWidth(f6);
        paint.setAntiAlias(true);
        this.f11950j = f7;
        this.f11951k = f7;
        paint.setTextSize(f7);
        this.f11954n = 0;
        try {
            this.o = this.f11945e;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void b(Canvas canvas) {
        int i2;
        this.a.setTextSize(this.f11950j);
        this.a.setColor(this.f11952l);
        float f2 = this.f11944c;
        canvas.drawLine(f2, this.f11948h, f2, this.f11949i, this.a);
        int i3 = 0;
        while (true) {
            int i4 = -961709;
            if (i3 >= this.f11945e) {
                break;
            }
            int i5 = this.f11952l;
            try {
                int i6 = this.f11954n;
                if ((i6 > i3 && i6 >= 0) || ((i2 = this.o) < i3 && i2 >= 0)) {
                    i4 = i5;
                }
                i5 = i4;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.a.setColor(i5);
            float f3 = (i3 * this.f11946f) + this.b;
            if (i3 != 0 && i3 != this.f11945e) {
                float f4 = this.f11949i;
                canvas.drawLine(f3, f4 - ((f4 - this.f11948h) / 2.0f), f3, f4, this.a);
            } else {
                canvas.drawLine(f3, this.f11948h, f3, this.f11949i, this.a);
            }
            String[] strArr = this.f11953m;
            if (strArr != null && i3 < strArr.length) {
                String str = strArr[i3];
                canvas.drawText(str, f3 - (this.a.measureText(str) / 2.0f), this.f11949i + DPIUtil.dip2px(20.0f), this.a);
            }
            i3++;
        }
        String[] strArr2 = this.f11953m;
        if (strArr2 != null) {
            try {
                int length = strArr2.length - 1;
                if (length == this.o) {
                    this.a.setColor(-961709);
                } else {
                    this.a.setColor(this.f11952l);
                }
                float f5 = (length * this.f11946f) + this.b;
                String str2 = this.f11953m[length];
                this.a.setTextSize(this.f11951k);
                canvas.drawText(str2, f5 - (this.a.measureText(str2) / 2.0f), this.f11949i + DPIUtil.dip2px(20.0f), this.a);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        this.a.setColor(this.f11952l);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Canvas canvas) {
        float f2 = this.b;
        float f3 = this.d;
        canvas.drawLine(f2, f3, this.f11944c, f3, this.a);
        b(canvas);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float c() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float d(c cVar) {
        return this.b + (e(cVar) * this.f11946f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int e(c cVar) {
        float f2 = cVar.f11962j - this.b;
        float f3 = this.f11946f;
        return (int) ((f2 + (f3 / 2.0f)) / f3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float f() {
        return this.f11944c;
    }

    public void g(int i2) {
        this.f11954n = i2;
    }

    public void h(int i2) {
        this.o = i2;
    }
}
