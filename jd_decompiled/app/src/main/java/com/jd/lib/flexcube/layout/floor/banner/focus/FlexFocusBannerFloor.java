package com.jd.lib.flexcube.layout.floor.banner.focus;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.entity.PageInfo;
import com.jd.lib.babel.ifloor.ui.IFloorView;
import com.jd.lib.babel.servicekit.imagekit.ImageLoad;
import com.jd.lib.flexcube.canvas.entity.CanvasConfig;
import com.jd.lib.flexcube.help.FlexAutoPlayInterface;
import com.jd.lib.flexcube.iwidget.b.b;
import com.jd.lib.flexcube.iwidget.b.c;
import com.jd.lib.flexcube.iwidget.entity.material.MaterialModel;
import com.jd.lib.flexcube.layout.entity.FlexCubeModel;
import com.jd.lib.flexcube.layout.entity.FloorConfig;
import com.jd.lib.flexcube.layout.entity.banner.BannerConfig;
import com.jd.lib.flexcube.layout.entity.common.BgInfo;
import com.jd.lib.flexcube.layout.entity.common.FloorStyle;
import com.jd.lib.flexcube.layout.entity.common.ViewStyle;
import com.jd.lib.flexcube.layout.floor.banner.common.BannerIndicator;
import com.jd.lib.flexcube.layout.floor.banner.common.a;
import com.jd.lib.flexcube.widgets.entity.common.CfInfo;
import java.util.List;

/* loaded from: classes14.dex */
public class FlexFocusBannerFloor extends FrameLayout implements a, IFloorView<FlexCubeModel>, FlexAutoPlayInterface {

    /* renamed from: g  reason: collision with root package name */
    private Context f4276g;

    /* renamed from: h  reason: collision with root package name */
    private ImageView f4277h;

    /* renamed from: i  reason: collision with root package name */
    private FlexFocusBannerLayout f4278i;

    /* renamed from: j  reason: collision with root package name */
    private FlexCubeModel f4279j;

    /* renamed from: k  reason: collision with root package name */
    private BannerIndicator f4280k;

    /* renamed from: l  reason: collision with root package name */
    private String f4281l;

    /* renamed from: m  reason: collision with root package name */
    private String f4282m;

    /* renamed from: n  reason: collision with root package name */
    private float f4283n;
    private int o;
    private int p;

    public FlexFocusBannerFloor(@NonNull Context context) {
        super(context);
        this.f4276g = context;
        ImageView newImageView = ImageLoad.newImageView(context);
        this.f4277h = newImageView;
        newImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(this.f4277h);
        FlexFocusBannerLayout flexFocusBannerLayout = new FlexFocusBannerLayout(context);
        this.f4278i = flexFocusBannerLayout;
        flexFocusBannerLayout.w(this);
        addView(this.f4278i);
        this.f4280k = new BannerIndicator(this.f4276g);
    }

    private void c() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, 1);
        this.f4278i.setLayoutParams(layoutParams);
        this.f4280k.setLayoutParams(layoutParams);
    }

    private boolean d() {
        return "1".equals(this.f4282m);
    }

    private void f(BannerConfig bannerConfig) {
        this.f4281l = "ex";
        this.f4282m = "1";
        if (bannerConfig != null && c.d(bannerConfig.showAnchor) && "0".equals(bannerConfig.showAnchor)) {
            this.f4282m = "0";
        }
    }

    private void g(BgInfo bgInfo, BabelScope babelScope) {
        PageInfo pageInfo;
        int i2 = 0;
        if (bgInfo != null && babelScope != null) {
            if ("1".equals(bgInfo.type) && c.d(bgInfo.imgUrl)) {
                setBackgroundColor(0);
                this.f4277h.setVisibility(0);
                this.f4277h.setLayoutParams(new FrameLayout.LayoutParams(this.o, this.p));
                ImageLoad.with(this.f4277h).load(bgInfo.imgUrl);
                return;
            } else if ("1".equals(bgInfo.sameColor)) {
                if (babelScope != null && (pageInfo = babelScope.pageInfo) != null) {
                    i2 = com.jd.lib.flexcube.iwidget.b.a.a(pageInfo.pageBgColor, 0);
                }
                this.f4277h.setVisibility(8);
                setBackgroundColor(i2);
                return;
            } else if ("0".equals(bgInfo.type)) {
                this.f4277h.setVisibility(8);
                setBackgroundColor(com.jd.lib.flexcube.iwidget.b.a.a(bgInfo.color, 0));
                return;
            } else {
                this.f4277h.setVisibility(8);
                setBackgroundColor(0);
                return;
            }
        }
        this.f4277h.setVisibility(8);
        setBackgroundColor(0);
    }

    private void h(ViewStyle viewStyle, float f2, int i2) {
        if (viewStyle == null) {
            this.f4278i.v(new CfInfo(0.0f, 0.0f, 0.0f, 0.0f), f2);
        } else {
            this.f4278i.v(viewStyle, f2);
        }
    }

    private void i(FloorConfig floorConfig, CanvasConfig canvasConfig, float f2, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        if (floorConfig != null && floorConfig.rows != 0 && floorConfig.w >= 1 && canvasConfig != null) {
            int d = b.d(canvasConfig.f4223h, f2);
            FloorStyle floorStyle = floorConfig.floorStyle;
            int i10 = 0;
            if (floorStyle != null) {
                i4 = b.d(floorConfig.w, f2);
                i5 = b.d(floorStyle.leftPadding, f2);
                i6 = b.d(floorStyle.rightPadding, f2);
                i7 = b.d(floorStyle.topPadding, f2);
                i3 = b.d(floorStyle.bottomPadding, f2);
            } else {
                i3 = 0;
                i4 = 0;
                i5 = 0;
                i6 = 0;
                i7 = 0;
            }
            ViewStyle viewStyle = floorConfig.viewStyle;
            if (viewStyle != null) {
                i9 = b.d(viewStyle.topPadding, f2);
                i8 = b.d(viewStyle.bottomPadding, f2);
            } else {
                i8 = 0;
                i9 = 0;
            }
            int i11 = (i4 - i5) - i6;
            if (d()) {
                if (indexOfChild(this.f4280k) == -1) {
                    addView(this.f4280k);
                }
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i11, b.d(BannerIndicator.BannerWidthHeight, f2));
                if (BannerConfig.Indicator_Ex.equals(this.f4281l)) {
                    layoutParams.topMargin = i7 + i9 + d + b.d(BannerIndicator.BannerCardSpace, f2);
                    i10 = b.d(BannerIndicator.BannerCardSpace + BannerIndicator.BannerWidthHeight, f2);
                } else {
                    layoutParams.topMargin = ((i7 + i9) + d) - b.d(BannerIndicator.BannerCardSpace + BannerIndicator.BannerWidthHeight, f2);
                }
                layoutParams.leftMargin = i5;
                this.f4280k.setLayoutParams(layoutParams);
            } else if (indexOfChild(this.f4280k) > -1) {
                removeView(this.f4280k);
            }
            int i12 = i9 + i8 + d + i10;
            this.o = i5 + i11 + i6;
            this.p = i7 + i12 + i3;
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(i11, i12);
            layoutParams2.setMargins(i5, i7, i6, i3);
            this.f4278i.setLayoutParams(layoutParams2);
            return;
        }
        c();
    }

    @Override // com.jd.lib.flexcube.layout.floor.banner.common.a
    public void a(int i2, float f2, int i3, int i4) {
        this.f4280k.initIndicatorCount(i2, f2, i3, i4);
    }

    @Override // com.jd.lib.flexcube.help.FlexAutoPlayInterface
    public boolean autoPlay(boolean z, boolean z2) {
        FlexCubeModel flexCubeModel;
        if (z && (flexCubeModel = this.f4279j) != null && flexCubeModel.hasVideo) {
            return this.f4278i.autoPlay(z, z2);
        }
        return false;
    }

    @Override // com.jd.lib.flexcube.layout.floor.banner.common.a
    public void b(int i2) {
        this.f4280k.selectIndicator(i2);
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    /* renamed from: e  reason: merged with bridge method [inline-methods] */
    public void update(@NonNull BabelScope babelScope, @NonNull FlexCubeModel flexCubeModel, int i2) {
        FloorConfig floorConfig;
        if (flexCubeModel != null && (floorConfig = flexCubeModel.floorConfig) != null && floorConfig.w >= 1 && floorConfig.rows != 0) {
            FlexCubeModel flexCubeModel2 = this.f4279j;
            if (flexCubeModel2 == flexCubeModel && flexCubeModel2.getMultiple() == this.f4283n) {
                return;
            }
            this.f4279j = flexCubeModel;
            this.f4283n = flexCubeModel.getMultiple();
            f(flexCubeModel.floorConfig.bannerConfig);
            i(flexCubeModel.floorConfig, flexCubeModel.canvasConfig, this.f4283n, i2);
            g(flexCubeModel.floorConfig.bgInfo, babelScope);
            h(flexCubeModel.floorConfig.viewStyle, this.f4283n, i2);
            List<MaterialModel> materialListByFloorNum = flexCubeModel.getMaterialListByFloorNum(0);
            if (materialListByFloorNum != null && materialListByFloorNum.size() != 0) {
                this.f4278i.z(babelScope, flexCubeModel, materialListByFloorNum, 0);
                return;
            } else {
                c();
                return;
            }
        }
        c();
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    public void initView(String str) {
        this.f4278i.t(str);
    }
}
