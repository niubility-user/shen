package com.jingdong.app.mall.home.floor.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.os.Build;
import android.text.SpannableString;
import android.util.Pair;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.jdsdk.utils.FontsUtil;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class PriceLabelView extends AppCompatTextView {

    /* renamed from: g  reason: collision with root package name */
    Path f10124g;

    /* renamed from: h  reason: collision with root package name */
    Paint f10125h;

    /* renamed from: i  reason: collision with root package name */
    Paint f10126i;

    /* renamed from: j  reason: collision with root package name */
    Pair<Float, Integer> f10127j;

    /* renamed from: k  reason: collision with root package name */
    private int f10128k;

    /* renamed from: l  reason: collision with root package name */
    private int f10129l;

    /* renamed from: m  reason: collision with root package name */
    private int f10130m;

    /* renamed from: n  reason: collision with root package name */
    private int[] f10131n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private AtomicBoolean v;

    public PriceLabelView(Context context) {
        super(context);
        this.f10124g = new Path();
        this.f10125h = new Paint(1);
        this.f10126i = new Paint(1);
        this.f10131n = new int[2];
        this.v = new AtomicBoolean(false);
        FontsUtil.changeTextFont(this, 4099);
        setGravity(17);
        setTextColor(-1);
        setMaxLines(1);
    }

    private void a() {
        int height = getHeight();
        int width = getWidth();
        if (this.v.get() || height <= 0 || this.f10124g.isEmpty() || this.o != height || this.p != width) {
            b();
            this.v.set(false);
            this.p = width;
            this.o = height;
            int i2 = height >> 1;
            int i3 = this.f10130m >> 1;
            int i4 = i3 >> 1;
            float f2 = width;
            int i5 = (this.u << 24) | ViewCompat.MEASURED_SIZE_MASK;
            int[] iArr = this.f10131n;
            this.f10125h.setShader(new LinearGradient(0.0f, 0.0f, f2, 0.0f, i5 & iArr[0], iArr[1], Shader.TileMode.CLAMP));
            this.f10124g.reset();
            double d = i2;
            double d2 = i3;
            Double.isNaN(d2);
            double d3 = (d2 * 3.141592653589793d) / 180.0d;
            double tan = Math.tan(d3);
            Double.isNaN(d);
            double d4 = d / tan;
            if (Build.VERSION.SDK_INT >= 21) {
                double d5 = this.f10128k;
                double d6 = i4;
                Double.isNaN(d6);
                double tan2 = Math.tan((d6 * 3.141592653589793d) / 180.0d);
                Double.isNaN(d5);
                float f3 = (float) (d4 + (d5 * tan2));
                this.f10124g.moveTo(f3, 0.0f);
                Path path = this.f10124g;
                int i6 = this.f10128k;
                path.arcTo(width - (i6 << 1), 0.0f, f2, i6 << 1, 270.0f, 90.0f, false);
                Path path2 = this.f10124g;
                int i7 = this.f10128k;
                float f4 = height;
                path2.arcTo(width - (i7 << 1), height - (i7 << 1), f2, f4, 0.0f, 90.0f, false);
                Path path3 = this.f10124g;
                int i8 = this.f10128k;
                float f5 = f3 + i8;
                float f6 = i3;
                path3.arcTo(f3 - i8, height - (i8 << 1), f5, f4, 90.0f, f6, false);
                double d7 = this.f10128k;
                double sin = Math.sin(d3);
                Double.isNaN(d7);
                float f7 = (float) (d7 / sin);
                Path path4 = this.f10124g;
                int i9 = this.f10128k;
                path4.arcTo(f7 - i9, i2 - i9, i9 + f7, i9 + i2, 180 - i3, this.f10130m, false);
                Path path5 = this.f10124g;
                int i10 = this.f10128k;
                path5.arcTo(f3 - i10, 0.0f, f3 + i10, i10 << 1, 270 - i3, f6, false);
                Path path6 = new Path();
                int i11 = this.f10129l;
                path6.addOval(f3 - (i11 << 1), i2 - i11, f3, i2 + i11, Path.Direction.CCW);
                this.f10124g.op(path6, Path.Op.DIFFERENCE);
                this.f10124g.close();
                return;
            }
            this.f10126i.setColor(-1);
            this.f10126i.setStrokeWidth(this.f10129l << 1);
            float f8 = (float) d4;
            this.f10124g.moveTo(f8, 0.0f);
            this.f10124g.lineTo(f2, 0.0f);
            float f9 = height;
            this.f10124g.lineTo(f2, f9);
            this.f10124g.lineTo(f8, f9);
            this.f10124g.lineTo(0.0f, i2);
            this.f10124g.lineTo(f8, 0.0f);
            this.f10124g.close();
            this.f10127j = new Pair<>(Float.valueOf(f8 - (this.f10129l >> 1)), Integer.valueOf(i2));
        }
    }

    public void b() {
        setTextSize(0, d.d(this.q));
        this.f10128k = d.d(7);
        this.f10129l = d.d(2);
        this.f10130m = 100;
        setPadding(d.d(this.r), d.d(this.s), d.d(this.t), -d.d(2));
    }

    public void c(int i2, int i3, int i4, int i5) {
        this.q = i2;
        this.r = i3;
        this.s = i4;
        this.t = i5;
    }

    public void d(int[] iArr, SpannableString spannableString, int i2) {
        int[] iArr2 = this.f10131n;
        iArr2[0] = iArr.length <= 1 ? -243846 : iArr[0];
        iArr2[1] = iArr.length <= 1 ? -907508 : iArr[1];
        this.u = i2;
        this.v.set(true);
        setText(spannableString);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        a();
        canvas.drawPath(this.f10124g, this.f10125h);
        Pair<Float, Integer> pair = this.f10127j;
        if (pair != null) {
            canvas.drawCircle(((Float) pair.first).floatValue(), ((Integer) this.f10127j.second).intValue(), this.f10129l, this.f10126i);
        }
        super.onDraw(canvas);
    }
}
