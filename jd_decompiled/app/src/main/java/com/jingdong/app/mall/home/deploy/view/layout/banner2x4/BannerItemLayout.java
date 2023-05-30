package com.jingdong.app.mall.home.deploy.view.layout.banner2x4;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.ctrl.f;
import com.jingdong.app.mall.home.floor.view.view.module.MallFloorBannerItem;
import com.jingdong.app.mall.home.m.a;
import com.jingdong.app.mall.home.state.dark.DarkMaskImageView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.app.util.image.placeholder.JDPlaceholderDrawable;
import com.jingdong.common.utils.JDImageUtils;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class BannerItemLayout extends RelativeLayout {

    /* renamed from: l  reason: collision with root package name */
    private static final int f8909l = R.id.image_last_url;

    /* renamed from: g  reason: collision with root package name */
    private AtomicBoolean f8910g;

    /* renamed from: h  reason: collision with root package name */
    private JDDisplayImageOptions f8911h;

    /* renamed from: i  reason: collision with root package name */
    private int f8912i;

    /* renamed from: j  reason: collision with root package name */
    private DarkMaskImageView f8913j;

    /* renamed from: k  reason: collision with root package name */
    private SimpleDraweeView f8914k;

    public BannerItemLayout(Context context) {
        super(context);
        this.f8910g = new AtomicBoolean(false);
    }

    private void d(MallFloorBannerItem mallFloorBannerItem) {
        SimpleDraweeView simpleDraweeView = this.f8914k;
        if (simpleDraweeView != null) {
            simpleDraweeView.setAlpha(0.0f);
        }
        if (mallFloorBannerItem.isUseMask()) {
            if (this.f8914k == null) {
                HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
                this.f8914k = homeDraweeView;
                homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
                addView(this.f8914k, new RelativeLayout.LayoutParams(-1, -1));
            }
            e.u(this.f8914k, mallFloorBannerItem.getMaskUrl());
        }
    }

    private void f() {
        DarkMaskImageView darkMaskImageView = new DarkMaskImageView(getContext());
        this.f8913j = darkMaskImageView;
        darkMaskImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(this.f8913j, new RelativeLayout.LayoutParams(-1, -1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        SimpleDraweeView simpleDraweeView = this.f8914k;
        if (simpleDraweeView != null) {
            simpleDraweeView.setAlpha(1.0f);
        }
    }

    public void c(MallFloorBannerItem mallFloorBannerItem, int i2) {
        if (this.f8913j == null || mallFloorBannerItem == null) {
            return;
        }
        d(mallFloorBannerItem);
        if (this.f8911h == null || this.f8912i != d.f9279g) {
            this.f8912i = d.f9279g;
            this.f8911h = f.a().resetViewBeforeLoading(true).showImageForEmptyUri(new JDPlaceholderDrawable(21)).showImageOnLoading(new JDPlaceholderDrawable(21)).showImageOnFail(new JDPlaceholderDrawable(21)).setPlaceholder(21);
        }
        this.f8911h.bitmapConfig(Bitmap.Config.RGB_565);
        this.f8911h.isScale(false);
        String h2 = com.jingdong.app.mall.home.o.a.f.h(mallFloorBannerItem.horizontalImg);
        DarkMaskImageView darkMaskImageView = this.f8913j;
        int i3 = f8909l;
        Object tag = darkMaskImageView.getTag(i3);
        Object tag2 = this.f8913j.getTag(JDImageUtils.STATUS_TAG);
        this.f8910g.set(false);
        if (h2 != null && h2.equals(tag) && tag2 != null && tag2.equals(2)) {
            this.f8910g.set(true);
            h();
        }
        this.f8913j.setTag(i3, h2);
        JDSimpleImageLoadingListener jDSimpleImageLoadingListener = new JDSimpleImageLoadingListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.banner2x4.BannerItemLayout.1
            @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                BannerItemLayout.this.f8910g.set(bitmap != null);
                BannerItemLayout.this.h();
            }
        };
        String b = a.b(h2);
        String str = !TextUtils.isEmpty(b) ? b : h2;
        if (tag != null && i2 == 0) {
            f.h(str, this.f8913j, this.f8911h, false, jDSimpleImageLoadingListener, null);
        } else {
            f.h(str, this.f8913j, this.f8911h, true, jDSimpleImageLoadingListener, null);
        }
    }

    public DarkMaskImageView e() {
        return this.f8913j;
    }

    public void g() {
        f();
    }
}
