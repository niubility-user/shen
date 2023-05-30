package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.ctrl.f;
import com.jingdong.app.mall.home.floor.model.entity.TongLanSchoolEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.TongLanSchoolEngine;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.r.f.a.y;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class MallFloorTongLanSchool extends BaseMallFloor<y> {
    public MallFloorTongLanSchool(Context context) {
        super(context);
    }

    public void initViewData() {
        removeAllViews();
        int R = ((y) this.mPresenter).R();
        HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        if (R == 1) {
            e.m(homeDraweeView, ((y) this.mPresenter).S(), new ColorDrawable(-1));
        } else {
            e.l(((y) this.mPresenter).S(), homeDraweeView);
        }
        addView(homeDraweeView, new RelativeLayout.LayoutParams(-1, -1));
        setOnClickListener(homeDraweeView, ((y) this.mPresenter).P());
        if (((y) this.mPresenter).R() == 1) {
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(0);
            linearLayout.setGravity(16);
            TextView textView = new TextView(getContext());
            textView.setTextSize(0, d.d(26));
            textView.setTextColor(-11250604);
            textView.setText(((y) this.mPresenter).T());
            textView.setSingleLine(true);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setMaxWidth(d.d(R2.attr.blendSrc));
            linearLayout.addView(textView, new LinearLayout.LayoutParams(-2, -2));
            String Q = ((y) this.mPresenter).Q();
            if (!TextUtils.isEmpty(Q)) {
                HomeDraweeView homeDraweeView2 = new HomeDraweeView(getContext());
                JDDisplayImageOptions a = f.a();
                int i2 = R.drawable.home_title_arrow_def;
                f.e(Q, homeDraweeView2, a.showImageOnFail(i2).showImageOnLoading(i2));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(d.d(26), d.d(26));
                layoutParams.leftMargin = d.d(10);
                linearLayout.addView(homeDraweeView2, layoutParams);
            }
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(13);
            addView(linearLayout, layoutParams2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public y createPresenter() {
        return new y(TongLanSchoolEntity.class, TongLanSchoolEngine.class);
    }
}
