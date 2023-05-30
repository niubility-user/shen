package com.jingdong.app.mall.home.floor.view.widget.newcomer;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.model.entity.NewcomerFloorEntity;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class NewcomerRedPacketItem extends NewcomerBaseItem {

    /* renamed from: g  reason: collision with root package name */
    private Context f10281g;

    /* renamed from: h  reason: collision with root package name */
    private NewcomerFloorEntity.NewcomerRedPacketModel f10282h;

    /* renamed from: i  reason: collision with root package name */
    private SimpleDraweeView f10283i;

    /* renamed from: j  reason: collision with root package name */
    private TextView f10284j;

    /* renamed from: k  reason: collision with root package name */
    private TextView f10285k;

    /* renamed from: l  reason: collision with root package name */
    private GradientTextView f10286l;

    /* renamed from: m  reason: collision with root package name */
    private final f f10287m;

    /* renamed from: n  reason: collision with root package name */
    private final f f10288n;
    private final f o;
    private final f p;

    /* loaded from: classes4.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NewcomerFloorEntity.onItemClick(NewcomerRedPacketItem.this.f10281g, NewcomerRedPacketItem.this.f10282h);
        }
    }

    public NewcomerRedPacketItem(Context context) {
        super(context);
        this.f10287m = new f(-1, -1);
        this.f10288n = new f(-2, -2);
        this.o = new f(-2, -2);
        this.p = new f(-2, 34);
        this.f10281g = context;
    }

    private void d() {
        SimpleDraweeView simpleDraweeView = this.f10283i;
        if (simpleDraweeView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(this.f10281g);
            this.f10283i = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            RelativeLayout.LayoutParams u = this.f10287m.u(this.f10283i);
            u.addRule(13);
            addView(this.f10283i, u);
        } else {
            f.c(simpleDraweeView, this.f10287m);
        }
        e.m(this.f10283i, this.f10282h.img, e.f9398c);
    }

    private void e() {
        if (this.f10282h.positionType == 4) {
            return;
        }
        this.f10288n.F(new Rect(0, 2, 0, 0));
        TextView textView = this.f10284j;
        if (textView == null) {
            g gVar = new g(this.f10281g, true);
            gVar.f(1);
            gVar.d(16);
            GradientTextView b = gVar.b();
            this.f10284j = b;
            RelativeLayout.LayoutParams u = this.f10288n.u(b);
            u.addRule(14);
            addView(this.f10284j, u);
        } else {
            f.d(textView, this.f10288n, true);
        }
        this.f10284j.setTypeface(FontsUtil.getTypeFace(getContext(), 4097));
        this.f10284j.setTextColor(this.f10282h.selectedTextColor);
        g.o(this.f10284j, 56);
        TextView textView2 = this.f10284j;
        NewcomerFloorEntity.NewcomerRedPacketModel newcomerRedPacketModel = this.f10282h;
        textView2.setText(NewcomerFloorEntity.getPriceSpan(newcomerRedPacketModel.info, newcomerRedPacketModel.infoDetail, 0.54f, 0.36f));
        this.o.F(new Rect(0, 66, 0, 0));
        TextView textView3 = this.f10285k;
        if (textView3 == null) {
            g gVar2 = new g(this.f10281g, true);
            gVar2.f(1);
            gVar2.d(16);
            GradientTextView b2 = gVar2.b();
            this.f10285k = b2;
            RelativeLayout.LayoutParams u2 = this.o.u(b2);
            u2.addRule(14);
            addView(this.f10285k, u2);
        } else {
            f.d(textView3, this.o, true);
        }
        this.f10285k.setTextColor(this.f10282h.selectedTextColor);
        g.o(this.f10285k, 22);
        TextView textView4 = this.f10285k;
        textView4.setText(com.jingdong.app.mall.home.o.a.f.l(textView4, d.d(R2.anim.pickerview_dialog_scale_in), this.f10282h.infoText));
        this.p.F(new Rect(0, 116, 0, 0));
        GradientTextView gradientTextView = this.f10286l;
        if (gradientTextView == null) {
            g gVar3 = new g(this.f10281g, true);
            gVar3.f(1);
            gVar3.d(16);
            GradientTextView b3 = gVar3.b();
            this.f10286l = b3;
            RelativeLayout.LayoutParams u3 = this.p.u(b3);
            u3.addRule(14);
            addView(this.f10286l, u3);
        } else {
            f.d(gradientTextView, this.p, true);
        }
        this.f10286l.setTextGradient(GradientTextView.GradientType.LeftToRight, this.f10282h.btnColor);
        this.f10286l.setMaxWidth(d.d(134));
        g.o(this.f10286l, 22);
        g.k(this.f10286l, true);
        GradientTextView gradientTextView2 = this.f10286l;
        gradientTextView2.setText(com.jingdong.app.mall.home.o.a.f.l(gradientTextView2, d.d(134), this.f10282h.btnText));
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.newcomer.NewcomerBaseItem
    public void a(NewcomerFloorEntity.NewcomerBaseModel newcomerBaseModel) {
        if (newcomerBaseModel == null) {
            return;
        }
        com.jingdong.app.mall.home.o.a.f.n(newcomerBaseModel);
        this.f10282h = (NewcomerFloorEntity.NewcomerRedPacketModel) newcomerBaseModel;
        d();
        e();
        setOnClickListener(new a());
    }
}
