package com.jingdong.app.mall.home.floor.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.view.adapter.TrendAdapter;
import com.jingdong.app.mall.home.r.e.k.e;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.recommend.RecommendMtaUtils;

/* loaded from: classes4.dex */
public class TrendLayout extends RelativeLayout {

    /* renamed from: g */
    private Paint f10168g;

    /* renamed from: h */
    private Path f10169h;

    /* renamed from: i */
    private int f10170i;

    /* renamed from: j */
    private Context f10171j;

    /* renamed from: k */
    private TextView f10172k;

    /* renamed from: l */
    private f f10173l;

    /* renamed from: m */
    private f f10174m;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ e f10175g;

        a(e eVar) {
            TrendLayout.this = r1;
            this.f10175g = eVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            JumpEntity a;
            if (l.k() || (a = this.f10175g.a()) == null) {
                return;
            }
            l.e(TrendLayout.this.f10171j, a);
            com.jingdong.app.mall.home.r.c.a.t("Home_TrendFloor", "", a.getSrvJson(), RecommendMtaUtils.Home_PageId);
        }
    }

    public TrendLayout(Context context) {
        super(context);
        this.f10168g = new Paint(1);
        this.f10169h = new Path();
        this.f10171j = context;
        this.f10173l = new f(-2, -1);
        this.f10174m = new f(-2, -2);
        this.f10168g.setColor(-8355712);
        setBackgroundColor(-1);
        com.jingdong.app.mall.home.n.h.e.d(this, d.d(12));
    }

    public void b(e eVar, int i2) {
        if (eVar == null) {
            return;
        }
        if (i2 == TrendAdapter.f9732c) {
            this.f10173l.E(20, 0, 12, 0);
        } else if (i2 == TrendAdapter.d) {
            this.f10173l.E(0, 0, 12, 0);
        } else {
            this.f10173l.E(0, 0, 20, 0);
        }
        this.f10173l.J(12, 0, 32, 0);
        setLayoutParams(this.f10173l.u(this));
        setOnClickListener(new a(eVar));
        TextView textView = this.f10172k;
        if (textView == null) {
            TextView textView2 = new TextView(this.f10171j);
            this.f10172k = textView2;
            RelativeLayout.LayoutParams u = this.f10174m.u(textView2);
            u.addRule(15);
            addView(this.f10172k, u);
        } else {
            f.c(textView, this.f10174m);
        }
        this.f10172k.setTextColor(-15066598);
        this.f10172k.setTextSize(0, d.d(28));
        this.f10172k.setText(eVar.b());
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        if (this.f10169h.isEmpty() || this.f10170i != width) {
            this.f10169h.reset();
            this.f10170i = width;
            float d = width - d.d(14);
            float height = getHeight() / 2.0f;
            this.f10169h.moveTo(d, height);
            this.f10169h.lineTo(d - d.d(8), height - d.d(8));
            this.f10169h.quadTo(d - d.d(10), height - d.d(8), d - d.d(10), height - d.d(6));
            this.f10169h.lineTo(d - d.d(5), height);
            this.f10169h.lineTo(d - d.d(10), d.d(6) + height);
            this.f10169h.quadTo(d - d.d(10), d.d(8) + height, d - d.d(8), d.d(8) + height);
            this.f10169h.lineTo(d, height);
        }
        canvas.drawPath(this.f10169h, this.f10168g);
    }
}
