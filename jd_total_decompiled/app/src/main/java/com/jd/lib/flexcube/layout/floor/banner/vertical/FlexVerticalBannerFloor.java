package com.jd.lib.flexcube.layout.floor.banner.vertical;

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
import com.jd.lib.flexcube.iwidget.b.a;
import com.jd.lib.flexcube.iwidget.b.b;
import com.jd.lib.flexcube.iwidget.b.c;
import com.jd.lib.flexcube.iwidget.entity.material.MaterialModel;
import com.jd.lib.flexcube.layout.entity.FlexCubeModel;
import com.jd.lib.flexcube.layout.entity.FloorConfig;
import com.jd.lib.flexcube.layout.entity.common.BgInfo;
import com.jd.lib.flexcube.layout.entity.common.FloorStyle;
import com.jd.lib.flexcube.layout.entity.common.ViewStyle;
import com.jd.lib.flexcube.widgets.entity.common.CfInfo;
import java.util.List;

/* loaded from: classes14.dex */
public class FlexVerticalBannerFloor extends FrameLayout implements IFloorView<FlexCubeModel>, FlexAutoPlayInterface {

    /* renamed from: g  reason: collision with root package name */
    private Context f4360g;

    /* renamed from: h  reason: collision with root package name */
    private ImageView f4361h;

    /* renamed from: i  reason: collision with root package name */
    private FlexVerticalBannerLayout f4362i;

    /* renamed from: j  reason: collision with root package name */
    private FlexCubeModel f4363j;

    /* renamed from: k  reason: collision with root package name */
    private int f4364k;

    /* renamed from: l  reason: collision with root package name */
    private float f4365l;

    /* renamed from: m  reason: collision with root package name */
    private int f4366m;

    /* renamed from: n  reason: collision with root package name */
    private int f4367n;

    public FlexVerticalBannerFloor(@NonNull Context context) {
        super(context);
        this.f4364k = 1;
        this.f4360g = context;
        ImageView newImageView = ImageLoad.newImageView(context);
        this.f4361h = newImageView;
        newImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(this.f4361h);
        FlexVerticalBannerLayout flexVerticalBannerLayout = new FlexVerticalBannerLayout(context);
        this.f4362i = flexVerticalBannerLayout;
        addView(flexVerticalBannerLayout);
    }

    private void a() {
        this.f4362i.setLayoutParams(new FrameLayout.LayoutParams(-1, 1));
    }

    public static int b(List<MaterialModel> list, int i2, int i3) {
        if (list != null) {
            if (i3 == 0) {
                i3 = 1;
            }
            return Math.min(i2, list.size() / i3);
        }
        return i2;
    }

    private void d(BgInfo bgInfo, BabelScope babelScope) {
        PageInfo pageInfo;
        int i2 = 0;
        if (bgInfo != null && babelScope != null) {
            if ("1".equals(bgInfo.type) && c.d(bgInfo.imgUrl)) {
                setBackgroundColor(0);
                this.f4361h.setVisibility(0);
                this.f4361h.setLayoutParams(new FrameLayout.LayoutParams(this.f4366m, this.f4367n));
                ImageLoad.with(this.f4361h).load(bgInfo.imgUrl);
                return;
            } else if ("1".equals(bgInfo.sameColor)) {
                if (babelScope != null && (pageInfo = babelScope.pageInfo) != null) {
                    i2 = a.a(pageInfo.pageBgColor, 0);
                }
                this.f4361h.setVisibility(8);
                setBackgroundColor(i2);
                return;
            } else if ("0".equals(bgInfo.type)) {
                this.f4361h.setVisibility(8);
                setBackgroundColor(a.a(bgInfo.color, 0));
                return;
            } else {
                this.f4361h.setVisibility(8);
                setBackgroundColor(0);
                return;
            }
        }
        this.f4361h.setVisibility(8);
        setBackgroundColor(0);
    }

    private void e(ViewStyle viewStyle, float f2) {
        if (viewStyle == null) {
            this.f4362i.n(new CfInfo(0.0f, 0.0f, 0.0f, 0.0f), f2);
        } else {
            this.f4362i.n(viewStyle, f2);
        }
    }

    private void f(FloorConfig floorConfig, CanvasConfig canvasConfig, float f2) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        if (floorConfig != null && floorConfig.rows != 0 && floorConfig.w >= 1 && canvasConfig != null) {
            int i9 = canvasConfig.f4223h;
            FloorStyle floorStyle = floorConfig.floorStyle;
            if (floorStyle != null) {
                i3 = floorStyle.leftPadding;
                i4 = floorStyle.rightPadding;
                i5 = floorStyle.topPadding;
                i6 = floorStyle.bottomPadding;
                i2 = floorStyle.cardVPadding;
            } else {
                i2 = 0;
                i3 = 0;
                i4 = 0;
                i5 = 0;
                i6 = 0;
            }
            ViewStyle viewStyle = floorConfig.viewStyle;
            if (viewStyle != null) {
                i8 = viewStyle.topPadding;
                i7 = viewStyle.bottomPadding;
            } else {
                i7 = 0;
                i8 = 0;
            }
            int d = b.d(i3, f2);
            int d2 = b.d(i5, f2);
            int d3 = b.d(i4, f2);
            int d4 = b.d(i6, f2);
            int d5 = (b.d(floorConfig.w, f2) - d) - d3;
            int i10 = d5 >= 0 ? d5 : 0;
            int d6 = (b.d(i9, f2) * this.f4364k) + (b.d(i2, f2) * (this.f4364k - 1)) + b.d(i8, f2) + b.d(i7, f2);
            this.f4366m = d + i10 + d3;
            this.f4367n = d2 + d6 + d4;
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i10, d6);
            layoutParams.setMargins(d, d2, d3, d4);
            this.f4362i.setLayoutParams(layoutParams);
            return;
        }
        a();
    }

    @Override // com.jd.lib.flexcube.help.FlexAutoPlayInterface
    public boolean autoPlay(boolean z, boolean z2) {
        return false;
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public void update(@NonNull BabelScope babelScope, @NonNull FlexCubeModel flexCubeModel, int i2) {
        FloorConfig floorConfig;
        if (flexCubeModel != null && (floorConfig = flexCubeModel.floorConfig) != null && floorConfig.w >= 1) {
            if (this.f4363j == flexCubeModel && flexCubeModel.getMultiple() == this.f4365l) {
                return;
            }
            this.f4363j = flexCubeModel;
            this.f4365l = flexCubeModel.getMultiple();
            FloorConfig floorConfig2 = flexCubeModel.floorConfig;
            this.f4364k = floorConfig2.rows;
            int i3 = floorConfig2.columns;
            List<MaterialModel> materialListByFloorNum = flexCubeModel.getMaterialListByFloorNum(i2);
            int b = b(materialListByFloorNum, this.f4364k, i3);
            this.f4364k = b;
            if (b != 0) {
                FloorConfig floorConfig3 = flexCubeModel.floorConfig;
                if (floorConfig3.rows != 0) {
                    this.f4362i.r = b;
                    f(floorConfig3, flexCubeModel.canvasConfig, this.f4365l);
                    d(flexCubeModel.floorConfig.bgInfo, babelScope);
                    e(flexCubeModel.floorConfig.viewStyle, this.f4365l);
                    this.f4362i.q(babelScope, flexCubeModel, materialListByFloorNum, 0);
                    return;
                }
            }
            a();
            return;
        }
        a();
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    public void initView(String str) {
        this.f4362i.m(str);
    }
}
