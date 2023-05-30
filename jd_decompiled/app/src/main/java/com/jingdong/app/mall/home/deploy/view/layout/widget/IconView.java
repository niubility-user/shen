package com.jingdong.app.mall.home.deploy.view.layout.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconLabel;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.n.h.g;
import javax.annotation.Nullable;

/* loaded from: classes4.dex */
public class IconView extends HomeDraweeView {

    /* renamed from: g */
    private int f9050g;

    /* renamed from: h */
    private Paint f9051h;

    /* renamed from: i */
    private Path f9052i;

    /* renamed from: j */
    private IconLabel.Info f9053j;

    public IconView(Context context) {
        super(context);
        this.f9051h = new Paint(1);
        this.f9052i = new Path();
    }

    private void b(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        if (this.f9052i.isEmpty() || this.f9050g != width) {
            c(width, new float[]{0.0f, 0.4f, 1.0f});
            this.f9050g = width;
            float d = d.d(4);
            float f2 = height;
            float f3 = f2 / 2.0f;
            float f4 = 0.5522848f * f3;
            this.f9052i.reset();
            this.f9052i.moveTo(f3, 0.0f);
            float f5 = width;
            this.f9052i.lineTo(f5 - d, 0.0f);
            g.f(0.0f, f5, d, d * 0.5522848f, this.f9052i);
            this.f9052i.lineTo(f5, f3 + 0.0f);
            g.d(f5, f2, f3, f4, this.f9052i);
            this.f9052i.lineTo(f3, f2);
            g.a(f2, 0.0f, f3, f4, this.f9052i);
            g.c(0.0f, 0.0f, f3, f4, this.f9052i);
            this.f9052i.close();
        }
        canvas.drawPath(this.f9052i, this.f9051h);
    }

    private void c(int i2, @Nullable float[] fArr) {
        int[] iArr;
        IconLabel.Info info = this.f9053j;
        if (info == null || (iArr = info.f9040e) == null) {
            return;
        }
        this.f9051h.setStyle(Paint.Style.FILL);
        if (iArr.length == 1) {
            this.f9051h.setColor(iArr[0]);
            this.f9051h.setShader(null);
        } else if (iArr.length > 1) {
            this.f9051h.setShader(new LinearGradient(0.0f, 0.0f, i2, 0.0f, iArr, (fArr == null || iArr.length == fArr.length) ? fArr : null, Shader.TileMode.CLAMP));
        }
    }

    public void a(IconLabel.Info info) {
        this.f9053j = info;
        Rect rect = info.f9041f;
        if (rect != null) {
            setPadding(rect.left, rect.top, rect.right, rect.bottom);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        IconLabel.Info info = this.f9053j;
        if (info != null && info.d) {
            b(canvas);
        }
        super.onDraw(canvas);
    }
}
