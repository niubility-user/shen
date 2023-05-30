package com.jingdong.app.mall.home.floor.view.view.subitem;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.model.entity.BubbleBannerEntity;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.floor.view.view.IBubbleBannerSmall;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class BubbleBannerLeftRightLayout extends RelativeLayout implements IBubbleBannerSmall {
    private f currentElement;
    private SimpleDraweeView mBgView;
    private int mPreWidth;
    private com.jingdong.app.mall.home.floor.common.f mSkuSize;
    private SimpleDraweeView mSkuView;
    private LinearLayout mTitleParent;
    private com.jingdong.app.mall.home.floor.common.f mTitleParentSize;
    private com.jingdong.app.mall.home.floor.common.f mTitleSize;
    private GradientTextView mTitleView;
    private View mWhiteBg;
    private com.jingdong.app.mall.home.floor.common.f mWhiteSize;

    public BubbleBannerLeftRightLayout(int i2, Context context, int i3) {
        super(context);
        this.mWhiteBg = new View(context);
        if (i2 == BubbleBannerEntity.TYPE_08005) {
            com.jingdong.app.mall.home.floor.common.f fVar = new com.jingdong.app.mall.home.floor.common.f(R2.anim.lib_cashier_sdk_pop_out_animation_bottom, R2.anim.lib_cashier_sdk_pop_out_animation_bottom);
            this.mWhiteSize = fVar;
            fVar.F(new Rect(15, 43, 15, 0));
        } else if (i2 == BubbleBannerEntity.TYPE_08009) {
            com.jingdong.app.mall.home.floor.common.f fVar2 = new com.jingdong.app.mall.home.floor.common.f(134, 134);
            this.mWhiteSize = fVar2;
            fVar2.F(new Rect(18, 24, 18, 0));
        } else {
            com.jingdong.app.mall.home.floor.common.f fVar3 = new com.jingdong.app.mall.home.floor.common.f(160, R2.anim.slide_in_from_top);
            this.mWhiteSize = fVar3;
            fVar3.F(new Rect(10, 12, 10, 0));
        }
        RelativeLayout.LayoutParams u = this.mWhiteSize.u(this.mWhiteBg);
        u.addRule(i3 == 0 ? 11 : 9);
        addView(this.mWhiteBg, u);
        HomeDraweeView homeDraweeView = new HomeDraweeView(context);
        this.mSkuView = homeDraweeView;
        int i4 = R.string.home_obstacle_free;
        homeDraweeView.setContentDescription(context.getString(i4));
        this.mSkuView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.subitem.BubbleBannerLeftRightLayout.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BubbleBannerLeftRightLayout.this.onItemClick(1);
            }
        });
        this.mSkuView.setScaleType(ImageView.ScaleType.FIT_XY);
        if (i2 == BubbleBannerEntity.TYPE_08005) {
            com.jingdong.app.mall.home.floor.common.f fVar4 = new com.jingdong.app.mall.home.floor.common.f(130, 130);
            this.mSkuSize = fVar4;
            fVar4.F(new Rect(20, 47, 20, 0));
        } else if (i2 == BubbleBannerEntity.TYPE_08009) {
            com.jingdong.app.mall.home.floor.common.f fVar5 = new com.jingdong.app.mall.home.floor.common.f(126, 126);
            this.mSkuSize = fVar5;
            fVar5.F(new Rect(22, 28, 22, 0));
        } else {
            com.jingdong.app.mall.home.floor.common.f fVar6 = new com.jingdong.app.mall.home.floor.common.f(R2.anim.message_center_dialog_out, R2.anim.message_center_dialog_out);
            this.mSkuSize = fVar6;
            fVar6.F(new Rect(17, 12, 17, 0));
        }
        RelativeLayout.LayoutParams u2 = this.mSkuSize.u(this.mSkuView);
        u2.addRule(i3 == 0 ? 11 : 9);
        addView(this.mSkuView, u2);
        HomeDraweeView homeDraweeView2 = new HomeDraweeView(context);
        this.mBgView = homeDraweeView2;
        homeDraweeView2.setContentDescription(context.getString(i4));
        this.mBgView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(this.mBgView, new RelativeLayout.LayoutParams(-1, -1));
        LinearLayout linearLayout = new LinearLayout(context);
        this.mTitleParent = linearLayout;
        linearLayout.setGravity(17);
        this.mTitleParent.setOrientation(0);
        if (i2 == BubbleBannerEntity.TYPE_08005) {
            com.jingdong.app.mall.home.floor.common.f fVar7 = new com.jingdong.app.mall.home.floor.common.f(130, 72);
            this.mTitleParentSize = fVar7;
            fVar7.F(new Rect(20, 0, 20, 0));
        } else if (i2 == BubbleBannerEntity.TYPE_08009) {
            com.jingdong.app.mall.home.floor.common.f fVar8 = new com.jingdong.app.mall.home.floor.common.f(130, 58);
            this.mTitleParentSize = fVar8;
            fVar8.F(new Rect(20, 0, 20, 0));
        } else {
            com.jingdong.app.mall.home.floor.common.f fVar9 = new com.jingdong.app.mall.home.floor.common.f(160, 82);
            this.mTitleParentSize = fVar9;
            fVar9.F(new Rect(10, 0, 10, 8));
        }
        RelativeLayout.LayoutParams u3 = this.mTitleParentSize.u(this.mTitleParent);
        u3.addRule(i3 == 0 ? 11 : 9);
        u3.addRule(12);
        addView(this.mTitleParent, u3);
        GradientTextView gradientTextView = new GradientTextView(context);
        this.mTitleView = gradientTextView;
        com.jingdong.app.mall.home.floor.common.f.y(gradientTextView, true);
        this.mTitleView.setGravity(16);
        com.jingdong.app.mall.home.floor.common.f fVar10 = new com.jingdong.app.mall.home.floor.common.f(-2, -1);
        this.mTitleSize = fVar10;
        LinearLayout linearLayout2 = this.mTitleParent;
        GradientTextView gradientTextView2 = this.mTitleView;
        linearLayout2.addView(gradientTextView2, fVar10.i(gradientTextView2));
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.subitem.BubbleBannerLeftRightLayout.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BubbleBannerLeftRightLayout.this.onItemClick(0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onItemClick(int i2) {
        f fVar;
        JumpEntity jump;
        if (l.k() || (fVar = this.currentElement) == null || (jump = fVar.getJump()) == null) {
            return;
        }
        b c2 = b.c(jump.srvJson);
        c2.a("skuposition", i2 + "");
        l.onClickJsonEvent(getContext(), jump, "", jump.getSrv(), c2.toString(), i2);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.IBubbleBannerSmall
    public void onViewBind(f fVar, int i2) {
        if (this.mPreWidth != d.f9279g) {
            this.mPreWidth = d.f9279g;
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(-1);
            gradientDrawable.setCornerRadius(d.d(4));
            this.mWhiteBg.setBackgroundDrawable(gradientDrawable);
            this.mTitleView.setMaxWidth(this.mTitleParentSize.v());
            com.jingdong.app.mall.home.floor.common.f.O(this.mTitleView, 22);
            com.jingdong.app.mall.home.floor.common.f.c(this.mSkuView, this.mSkuSize);
            com.jingdong.app.mall.home.floor.common.f.c(this.mTitleParent, this.mTitleParentSize);
            com.jingdong.app.mall.home.floor.common.f.c(this.mWhiteBg, this.mWhiteSize);
            com.jingdong.app.mall.home.floor.common.f.c(this.mTitleView, this.mTitleSize);
        }
        this.currentElement = fVar;
        if (fVar == null) {
            return;
        }
        boolean z = fVar.t() == 0;
        String u = z ? fVar.u() : fVar.G();
        setBackgroundColor(e.a);
        e.p(this.mBgView, u, e.b, new e.b() { // from class: com.jingdong.app.mall.home.floor.view.view.subitem.BubbleBannerLeftRightLayout.3
            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onFailed(String str, View view) {
            }

            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onStart(String str, View view) {
            }

            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onSuccess(String str, View view) {
                BubbleBannerLeftRightLayout.this.setBackgroundColor(0);
            }
        });
        if (z) {
            this.mWhiteBg.setVisibility(8);
            this.mTitleView.setVisibility(8);
            this.mSkuView.setVisibility(8);
            return;
        }
        this.mTitleView.setTextGradient(GradientTextView.GradientType.LeftToRight, m.o(fVar.D(false), -16777216));
        this.mWhiteBg.setVisibility(0);
        this.mTitleView.setVisibility(0);
        this.mSkuView.setVisibility(0);
        this.mTitleView.setText(fVar.O());
        e.l(fVar.u(), this.mSkuView);
    }
}
