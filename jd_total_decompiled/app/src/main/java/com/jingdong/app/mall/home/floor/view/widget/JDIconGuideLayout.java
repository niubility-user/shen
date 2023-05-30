package com.jingdong.app.mall.home.floor.view.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.e;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.r.f.a.o;

/* loaded from: classes4.dex */
public class JDIconGuideLayout extends LinearLayout {
    private SimpleDraweeView mBottomIcon;
    private TextView mBottomText;
    private o mPresenter;
    private SimpleDraweeView mTopIcon;
    private TextView mTopText;

    public JDIconGuideLayout(Context context, o oVar) {
        super(context);
        this.mPresenter = oVar;
        setOrientation(1);
        setGravity(1);
        f fVar = new f(80, 80);
        fVar.E(0, 10, 0, 0);
        f fVar2 = new f(120, 41);
        HomeDraweeView homeDraweeView = new HomeDraweeView(context);
        this.mTopIcon = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        View view = this.mTopIcon;
        addView(view, fVar.i(view));
        TextView createText = createText(context);
        this.mTopText = createText;
        addView(createText, fVar2.i(createText));
        fVar.E(0, 16, 0, 0);
        HomeDraweeView homeDraweeView2 = new HomeDraweeView(context);
        this.mBottomIcon = homeDraweeView2;
        homeDraweeView2.setScaleType(ImageView.ScaleType.FIT_XY);
        View view2 = this.mBottomIcon;
        addView(view2, fVar.i(view2));
        TextView createText2 = createText(context);
        this.mBottomText = createText2;
        addView(createText2, fVar2.i(createText2));
    }

    private TextView createText(Context context) {
        TextView textView = new TextView(context);
        textView.setIncludeFontPadding(false);
        textView.setGravity(17);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setTextColor(-8092023);
        textView.setTextSize(0, d.d(e.b().c(this.mPresenter.w0())));
        textView.setSingleLine();
        return textView;
    }

    public boolean bindItem() {
        com.jingdong.app.mall.home.r.e.k.c d0 = this.mPresenter.d0(1, 0);
        com.jingdong.app.mall.home.r.e.k.c d02 = this.mPresenter.d0(1, 5);
        if (d0 == null || d02 == null) {
            return false;
        }
        com.jingdong.app.mall.home.floor.ctrl.e.i(d0.f10727n, this.mTopIcon, null, false);
        this.mTopText.setText(d0.f10726m);
        this.mTopText.setTextColor(this.mPresenter.u0(0));
        com.jingdong.app.mall.home.floor.ctrl.e.i(d02.f10727n, this.mBottomIcon, null, false);
        this.mBottomText.setText(d02.f10726m);
        this.mBottomText.setTextColor(this.mPresenter.u0(0));
        return true;
    }
}
