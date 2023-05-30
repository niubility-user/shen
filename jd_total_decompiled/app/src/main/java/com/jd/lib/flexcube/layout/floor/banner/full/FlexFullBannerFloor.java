package com.jd.lib.flexcube.layout.floor.banner.full;

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
public class FlexFullBannerFloor extends FrameLayout implements a, IFloorView<FlexCubeModel>, FlexAutoPlayInterface {

    /* renamed from: g */
    private Context f4308g;

    /* renamed from: h */
    private ImageView f4309h;

    /* renamed from: i */
    private FlexFullBannerLayout f4310i;

    /* renamed from: j */
    private FlexCubeModel f4311j;

    /* renamed from: k */
    private BannerIndicator f4312k;

    /* renamed from: l */
    private String f4313l;

    /* renamed from: m */
    private float f4314m;

    /* renamed from: n */
    private int f4315n;
    private int o;

    public FlexFullBannerFloor(@NonNull Context context) {
        super(context);
        this.f4308g = context;
        ImageView newImageView = ImageLoad.newImageView(context);
        this.f4309h = newImageView;
        newImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(this.f4309h);
        FlexFullBannerLayout flexFullBannerLayout = new FlexFullBannerLayout(context);
        this.f4310i = flexFullBannerLayout;
        flexFullBannerLayout.A(this);
        addView(this.f4310i);
        BannerIndicator bannerIndicator = new BannerIndicator(this.f4308g);
        this.f4312k = bannerIndicator;
        addView(bannerIndicator);
    }

    public static float c(FloorConfig floorConfig, CanvasConfig canvasConfig, float f2) {
        int i2;
        int i3;
        int i4;
        if (floorConfig == null || canvasConfig == null || canvasConfig.w == 0) {
            return 0.0f;
        }
        FloorStyle floorStyle = floorConfig.floorStyle;
        int i5 = 0;
        if (floorStyle != null) {
            i2 = b.d(floorStyle.leftPadding, f2);
            i3 = b.d(floorStyle.rightPadding, f2);
            b.d(floorStyle.cardVPadding, f2);
        } else {
            i2 = 0;
            i3 = 0;
        }
        ViewStyle viewStyle = floorConfig.viewStyle;
        if (viewStyle != null) {
            i5 = b.d(viewStyle.leftPadding, f2);
            i4 = b.d(viewStyle.rightPadding, f2);
        } else {
            i4 = 0;
        }
        int d = (((b.d(floorConfig.w, f2) - i2) - i3) - i5) - i4;
        if (d > 0) {
            return (d * 1.0f) / canvasConfig.w;
        }
        return 0.0f;
    }

    private void d() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, 1);
        this.f4310i.setLayoutParams(layoutParams);
        this.f4312k.setLayoutParams(layoutParams);
    }

    private void f(BannerConfig bannerConfig) {
        if (bannerConfig == null) {
            this.f4313l = "ex";
        } else if (c.d(bannerConfig.position)) {
            this.f4313l = bannerConfig.position;
        } else {
            this.f4313l = "ex";
        }
    }

    private void g(BgInfo bgInfo, BabelScope babelScope) {
        PageInfo pageInfo;
        int i2 = 0;
        if (bgInfo != null && babelScope != null) {
            if ("1".equals(bgInfo.type) && c.d(bgInfo.imgUrl)) {
                setBackgroundColor(0);
                this.f4309h.setVisibility(0);
                this.f4309h.setLayoutParams(new FrameLayout.LayoutParams(this.f4315n, this.o));
                ImageLoad.with(this.f4309h).load(bgInfo.imgUrl);
                return;
            } else if ("1".equals(bgInfo.sameColor)) {
                if (babelScope != null && (pageInfo = babelScope.pageInfo) != null) {
                    i2 = com.jd.lib.flexcube.iwidget.b.a.a(pageInfo.pageBgColor, 0);
                }
                this.f4309h.setVisibility(8);
                setBackgroundColor(i2);
                return;
            } else if ("0".equals(bgInfo.type)) {
                this.f4309h.setVisibility(8);
                setBackgroundColor(com.jd.lib.flexcube.iwidget.b.a.a(bgInfo.color, 0));
                return;
            } else {
                this.f4309h.setVisibility(8);
                setBackgroundColor(0);
                return;
            }
        }
        this.f4309h.setVisibility(8);
        setBackgroundColor(0);
    }

    private void h(ViewStyle viewStyle, float f2, int i2) {
        if (viewStyle == null) {
            this.f4310i.z(new CfInfo(0.0f, 0.0f, 0.0f, 0.0f), f2);
        } else {
            this.f4310i.z(viewStyle, f2);
        }
    }

    private void i(FloorConfig floorConfig, CanvasConfig canvasConfig, float f2, float f3, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        if (floorConfig != null && floorConfig.rows != 0 && floorConfig.w >= 1 && canvasConfig != null) {
            int d = b.d(canvasConfig.f4223h, f3);
            int d2 = b.d(canvasConfig.w, f3);
            FloorStyle floorStyle = floorConfig.floorStyle;
            int i11 = 0;
            if (floorStyle != null) {
                i3 = b.d(floorStyle.leftPadding, f2);
                i4 = b.d(floorStyle.rightPadding, f2);
                i5 = b.d(floorStyle.topPadding, f2);
                i6 = b.d(floorStyle.bottomPadding, f2);
                b.d(floorStyle.cardVPadding, f2);
            } else {
                i3 = 0;
                i4 = 0;
                i5 = 0;
                i6 = 0;
            }
            ViewStyle viewStyle = floorConfig.viewStyle;
            if (viewStyle != null) {
                i8 = b.d(viewStyle.topPadding, f2);
                i9 = b.d(viewStyle.bottomPadding, f2);
                i10 = b.d(viewStyle.leftPadding, f2);
                i7 = b.d(viewStyle.rightPadding, f2);
            } else {
                i7 = 0;
                i8 = 0;
                i9 = 0;
                i10 = 0;
            }
            int i12 = i10 + i7 + d2;
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i12, b.d(BannerIndicator.BannerWidthHeight, f2));
            if (BannerConfig.Indicator_Ex.equals(this.f4313l)) {
                layoutParams.topMargin = i5 + i8 + d + b.d(BannerIndicator.BannerCardSpace, f2);
                i11 = b.d(BannerIndicator.BannerCardSpace + BannerIndicator.BannerWidthHeight, f2);
            } else {
                layoutParams.topMargin = ((i5 + i8) + d) - b.d(BannerIndicator.BannerCardSpace + BannerIndicator.BannerWidthHeight, f2);
            }
            layoutParams.leftMargin = i3;
            this.f4312k.setLayoutParams(layoutParams);
            int i13 = i8 + i9 + d + i11;
            this.f4315n = i3 + i12 + i4;
            this.o = i5 + i13 + i6;
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(i12, i13);
            layoutParams2.setMargins(i3, i5, i4, i6);
            this.f4310i.setLayoutParams(layoutParams2);
            return;
        }
        d();
    }

    @Override // com.jd.lib.flexcube.layout.floor.banner.common.a
    public void a(int i2, float f2, int i3, int i4) {
        this.f4312k.initIndicatorCount(i2, f2, i3, i4);
    }

    @Override // com.jd.lib.flexcube.help.FlexAutoPlayInterface
    public boolean autoPlay(boolean z, boolean z2) {
        FlexCubeModel flexCubeModel;
        if (z && (flexCubeModel = this.f4311j) != null && flexCubeModel.hasVideo) {
            return this.f4310i.autoPlay(z, z2);
        }
        return false;
    }

    @Override // com.jd.lib.flexcube.layout.floor.banner.common.a
    public void b(int i2) {
        this.f4312k.selectIndicator(i2);
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    /* renamed from: e */
    public void update(@NonNull BabelScope babelScope, @NonNull FlexCubeModel flexCubeModel, int i2) {
        FloorConfig floorConfig;
        if (flexCubeModel != null && (floorConfig = flexCubeModel.floorConfig) != null && floorConfig.w >= 1 && floorConfig.rows != 0) {
            FlexCubeModel flexCubeModel2 = this.f4311j;
            if (flexCubeModel2 == flexCubeModel && flexCubeModel2.getMultiple() == this.f4314m) {
                return;
            }
            this.f4311j = flexCubeModel;
            this.f4314m = flexCubeModel.getMultiple();
            float canvasMultiple = flexCubeModel.getCanvasMultiple();
            f(flexCubeModel.floorConfig.bannerConfig);
            i(flexCubeModel.floorConfig, flexCubeModel.canvasConfig, this.f4314m, canvasMultiple, i2);
            g(flexCubeModel.floorConfig.bgInfo, babelScope);
            h(flexCubeModel.floorConfig.viewStyle, this.f4314m, i2);
            List<MaterialModel> materialListByFloorNum = flexCubeModel.getMaterialListByFloorNum(0);
            if (materialListByFloorNum != null && materialListByFloorNum.size() != 0) {
                this.f4310i.D(babelScope, flexCubeModel, materialListByFloorNum, 0);
                return;
            } else {
                d();
                return;
            }
        }
        d();
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    public void initView(String str) {
        this.f4310i.x(str);
    }
}
