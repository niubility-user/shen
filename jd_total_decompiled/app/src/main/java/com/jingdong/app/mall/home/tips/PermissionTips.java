package com.jingdong.app.mall.home.tips;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class PermissionTips extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    private Context f10884g;

    /* renamed from: h  reason: collision with root package name */
    private SimpleDraweeView f10885h;

    /* renamed from: i  reason: collision with root package name */
    private View f10886i;

    /* renamed from: j  reason: collision with root package name */
    private View f10887j;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f10888g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ SimpleDraweeView f10889h;

        a(PermissionTips permissionTips, String str, SimpleDraweeView simpleDraweeView) {
            this.f10888g = str;
            this.f10889h = simpleDraweeView;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            e.l(this.f10888g, this.f10889h);
        }
    }

    public PermissionTips(Context context) {
        super(context);
        this.f10884g = context;
    }

    private void b(String str, SimpleDraweeView simpleDraweeView) {
        f.E0(new a(this, str, simpleDraweeView));
    }

    public boolean a(c cVar) {
        if (cVar == null || TextUtils.isEmpty(cVar.d())) {
            return false;
        }
        com.jingdong.app.mall.home.floor.common.f fVar = new com.jingdong.app.mall.home.floor.common.f(R2.attr.decimalNumber, R2.anim.lib_cashier_sdk_fragment_right_out);
        SimpleDraweeView simpleDraweeView = this.f10885h;
        if (simpleDraweeView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(this.f10884g);
            this.f10885h = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            View view = this.f10885h;
            addView(view, fVar.u(view));
        } else {
            com.jingdong.app.mall.home.floor.common.f.c(simpleDraweeView, fVar);
        }
        b(cVar.d(), this.f10885h);
        this.f10885h.setOnClickListener(null);
        com.jingdong.app.mall.home.floor.common.f fVar2 = new com.jingdong.app.mall.home.floor.common.f(144, 56);
        fVar2.E(0, 52, 36, 0);
        View view2 = this.f10886i;
        if (view2 == null) {
            View view3 = new View(this.f10884g);
            this.f10886i = view3;
            RelativeLayout.LayoutParams u = fVar2.u(view3);
            u.addRule(11);
            addView(this.f10886i, u);
        } else {
            com.jingdong.app.mall.home.floor.common.f.c(view2, fVar2);
        }
        com.jingdong.app.mall.home.floor.common.f fVar3 = new com.jingdong.app.mall.home.floor.common.f(32, 32);
        fVar3.E(0, 8, 12, 0);
        View view4 = this.f10887j;
        if (view4 == null) {
            View view5 = new View(this.f10884g);
            this.f10887j = view5;
            RelativeLayout.LayoutParams u2 = fVar3.u(view5);
            u2.addRule(11);
            addView(this.f10887j, u2);
            return true;
        }
        com.jingdong.app.mall.home.floor.common.f.c(view4, fVar3);
        return true;
    }

    public void c(View.OnClickListener onClickListener) {
        View view = this.f10886i;
        if (view == null || onClickListener == null) {
            return;
        }
        view.setOnClickListener(onClickListener);
    }

    public void d(View.OnClickListener onClickListener) {
        View view = this.f10887j;
        if (view == null || onClickListener == null) {
            return;
        }
        view.setOnClickListener(onClickListener);
    }
}
