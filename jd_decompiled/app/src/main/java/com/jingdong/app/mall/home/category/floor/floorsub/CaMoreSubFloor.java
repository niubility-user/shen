package com.jingdong.app.mall.home.category.floor.floorsub;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jingdong.app.mall.home.category.floor.CaRecycleLinearFloor;
import com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.n.g.x.l;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public class CaMoreSubFloor extends BaseCaRecycleItem<l> {
    private static int v = 124;
    private TextView q;
    private f r;
    private TextView s;
    private AtomicBoolean t;
    private Paint u;

    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {
        a() {
            CaMoreSubFloor.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ViewGroup c2 = ((BaseCaRecycleItem) CaMoreSubFloor.this).f8680n.c();
            if (c2 instanceof CaRecycleLinearFloor) {
                ((CaRecycleLinearFloor) c2).Z((l) ((BaseCaRecycleItem) CaMoreSubFloor.this).f8679m, true);
            }
        }
    }

    public CaMoreSubFloor(Context context) {
        super(context);
        this.t = new AtomicBoolean(false);
        Paint paint = new Paint(1);
        this.u = paint;
        paint.setColor(-328966);
        TextView textView = new TextView(context);
        this.q = textView;
        textView.setTextColor(-7566196);
        this.q.setText("\u5de6\u6ed1\n\u67e5\u770b\n\u66f4\u591a");
        this.q.setGravity(17);
        this.q.setLineSpacing(0.0f, 1.25f);
        f fVar = new f(100, 200);
        this.r = fVar;
        fVar.F(new Rect(12, 50, 0, 0));
        View view = this.q;
        addView(view, this.r.u(view));
        TextView textView2 = new TextView(context);
        this.s = textView2;
        addView(textView2);
    }

    private void q(boolean z) {
        if (this.t.get() == z) {
            return;
        }
        this.t.set(z);
        this.q.setText(this.t.get() ? "\u91ca\u653e\n\u67e5\u770b\n\u66f4\u591a" : "\u5de6\u6ed1\n\u67e5\u770b\n\u66f4\u591a");
    }

    @Override // com.jingdong.app.mall.home.category.widget.CaRoundBgLayout, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        int d = d.d(6);
        int d2 = d.d(24);
        if (Build.VERSION.SDK_INT >= 21) {
            float f2 = d2;
            canvas.drawRoundRect(this.r.l(), this.r.n(), getWidth() - d, this.r.n() + this.r.h(), f2, f2, this.u);
        } else {
            canvas.drawRect(this.r.l(), this.r.n(), getWidth() - d, this.r.n() + this.r.h(), this.u);
        }
        super.dispatchDraw(canvas);
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem
    /* renamed from: p */
    public void e(@NotNull l lVar) {
        lVar.u(this.r);
        this.u.setColor(lVar.v());
        int w = lVar.w(v);
        v = w;
        this.s.setMinWidth(d.d(w));
        this.s.setMaxWidth(d.d(v));
        this.r.u(this.q);
        f.c(this.q, this.r);
        this.q.setTextSize(0, d.d(24));
        this.q.setOnClickListener(new a());
        this.q.postInvalidate();
    }

    @Override // android.view.View
    public void setPadding(int i2, int i3, int i4, int i5) {
        super.setPadding(i2, i3, i4, i5);
        q(i4 > d.d(60));
    }
}
