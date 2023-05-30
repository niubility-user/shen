package com.jd.lib.flexcube.layout.floor.scroll;

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
import com.jd.lib.flexcube.help.MsgActionInterface;
import com.jd.lib.flexcube.help.MsgInterface;
import com.jd.lib.flexcube.iwidget.b.a;
import com.jd.lib.flexcube.iwidget.b.b;
import com.jd.lib.flexcube.iwidget.b.c;
import com.jd.lib.flexcube.iwidget.entity.material.MaterialModel;
import com.jd.lib.flexcube.layout.entity.FlexCubeModel;
import com.jd.lib.flexcube.layout.entity.FloorConfig;
import com.jd.lib.flexcube.layout.entity.common.BgInfo;
import com.jd.lib.flexcube.layout.entity.common.FloorStyle;
import com.jd.lib.flexcube.layout.entity.common.ScrollConfig;
import com.jd.lib.flexcube.layout.entity.common.ViewStyle;
import com.jd.lib.flexcube.widgets.entity.common.CfInfo;
import com.jd.lib.flexcube.widgets.entity.subview.SubViewFlexCubeModel;
import java.util.List;

/* loaded from: classes14.dex */
public class FlexScrollFloor extends FrameLayout implements IFloorView<FlexCubeModel>, FlexAutoPlayInterface, MsgInterface {
    public static int o = 4;

    /* renamed from: g */
    private Context f4395g;

    /* renamed from: h */
    private ImageView f4396h;

    /* renamed from: i */
    private FlexScrollLayout f4397i;

    /* renamed from: j */
    private FlexCubeModel f4398j;

    /* renamed from: k */
    private float f4399k;

    /* renamed from: l */
    private int f4400l;

    /* renamed from: m */
    private int f4401m;

    /* renamed from: n */
    private int f4402n;

    public FlexScrollFloor(@NonNull Context context) {
        super(context);
        this.f4400l = 1;
        this.f4395g = context;
        ImageView newImageView = ImageLoad.newImageView(context);
        this.f4396h = newImageView;
        newImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(this.f4396h);
        FlexScrollLayout flexScrollLayout = new FlexScrollLayout(context);
        this.f4397i = flexScrollLayout;
        addView(flexScrollLayout);
    }

    private int a(FloorConfig floorConfig, CanvasConfig canvasConfig, float f2, int i2, int i3, int i4) {
        FlexCubeModel flexCubeModel;
        FloorConfig floorConfig2;
        ScrollConfig scrollConfig;
        if (floorConfig == null || (flexCubeModel = this.f4398j) == null || (floorConfig2 = flexCubeModel.floorConfig) == null || floorConfig.floorStyle == null || floorConfig.viewStyle == null || (scrollConfig = floorConfig2.scrollConfig) == null || !"1".equals(scrollConfig.showBar)) {
            return i3;
        }
        ViewStyle viewStyle = floorConfig.viewStyle;
        int d = b.d(floorConfig.floorStyle.cardHPadding, f2);
        int d2 = b.d(viewStyle.leftPadding, f2);
        int d3 = b.d(viewStyle.rightPadding, f2);
        boolean equals = "1".equals(this.f4398j.floorConfig.showMore);
        int d4 = canvasConfig != null ? b.d(canvasConfig.w, f2) : 0;
        int i5 = this.f4398j.floorConfig.rows;
        if (i5 < 1) {
            i5 = 1;
        }
        int i6 = o;
        if (i5 > i6) {
            i5 = i6;
        }
        int i7 = d2 + d4 + (((i2 / i5) - 1) * (d4 + d));
        if (equals) {
            d3 += FlexScrollLayout.N + d;
        }
        double d5 = i4;
        Double.isNaN(d5);
        return ((double) (i7 + d3)) > d5 * 1.1d ? b.d(54, f2) : i3;
    }

    private void b() {
        this.f4397i.setLayoutParams(new FrameLayout.LayoutParams(-1, 1));
    }

    public static int c(List<MaterialModel> list, int i2) {
        return i2;
    }

    private void e(BgInfo bgInfo, BabelScope babelScope) {
        PageInfo pageInfo;
        int i2 = 0;
        if (bgInfo != null && babelScope != null) {
            if ("1".equals(bgInfo.type) && c.d(bgInfo.imgUrl)) {
                setBackgroundColor(0);
                this.f4396h.setVisibility(0);
                this.f4396h.setLayoutParams(new FrameLayout.LayoutParams(this.f4401m, this.f4402n));
                ImageLoad.with(this.f4396h).load(bgInfo.imgUrl);
                return;
            } else if ("1".equals(bgInfo.sameColor)) {
                if (babelScope != null && (pageInfo = babelScope.pageInfo) != null) {
                    i2 = a.a(pageInfo.pageBgColor, 0);
                }
                this.f4396h.setVisibility(8);
                setBackgroundColor(i2);
                return;
            } else if ("0".equals(bgInfo.type)) {
                this.f4396h.setVisibility(8);
                setBackgroundColor(a.a(bgInfo.color, 0));
                return;
            } else {
                this.f4396h.setVisibility(8);
                setBackgroundColor(0);
                return;
            }
        }
        this.f4396h.setVisibility(8);
        setBackgroundColor(0);
    }

    private void f(ViewStyle viewStyle, float f2, int i2, int i3) {
        if (viewStyle == null) {
            this.f4397i.E(new CfInfo(0.0f, 0.0f, 0.0f, 0.0f), f2);
        } else {
            this.f4397i.E(viewStyle, f2);
        }
    }

    private void g(FloorConfig floorConfig, CanvasConfig canvasConfig, float f2, float f3, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        if (floorConfig != null && floorConfig.rows != 0 && floorConfig.w >= 1 && canvasConfig != null) {
            int i12 = canvasConfig.f4223h;
            FloorStyle floorStyle = floorConfig.floorStyle;
            if (floorStyle != null) {
                i6 = floorStyle.leftPadding;
                i7 = floorStyle.rightPadding;
                i8 = floorStyle.topPadding;
                int i13 = floorStyle.bottomPadding;
                i9 = floorStyle.cardVPadding;
                i5 = i13;
            } else {
                i5 = 0;
                i6 = 0;
                i7 = 0;
                i8 = 0;
                i9 = 0;
            }
            ViewStyle viewStyle = floorConfig.viewStyle;
            if (viewStyle != null) {
                i11 = viewStyle.topPadding;
                i10 = viewStyle.bottomPadding;
            } else {
                i10 = 0;
                i11 = 0;
            }
            int d = b.d(i6, f2);
            int d2 = b.d(i8, f2);
            int d3 = b.d(i7, f2);
            int d4 = b.d(i5, f2);
            int d5 = (b.d(floorConfig.w, f2) - d) - d3;
            int i14 = d5 < 0 ? 0 : d5;
            int a = a(floorConfig, canvasConfig, f2, i3, 0, i14);
            this.f4397i.H = a > 0;
            int d6 = (b.d(i12, f3) * this.f4400l) + (b.d(i9, f2) * (this.f4400l - 1)) + b.d(i11, f2) + b.d(i10, f2) + a;
            int i15 = i14;
            this.f4401m = d + i15 + d3;
            this.f4402n = d2 + d6 + d4;
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i15, d6);
            layoutParams.setMargins(d, d2, d3, d4);
            this.f4397i.setLayoutParams(layoutParams);
            return;
        }
        b();
    }

    @Override // com.jd.lib.flexcube.help.FlexAutoPlayInterface
    public boolean autoPlay(boolean z, boolean z2) {
        FlexCubeModel flexCubeModel;
        if (z && (flexCubeModel = this.f4398j) != null && flexCubeModel.hasVideo) {
            return this.f4397i.autoPlay(z, z2);
        }
        return false;
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    /* renamed from: d */
    public void update(@NonNull BabelScope babelScope, @NonNull FlexCubeModel flexCubeModel, int i2) {
        FloorConfig floorConfig;
        if (flexCubeModel != null && (floorConfig = flexCubeModel.floorConfig) != null && floorConfig.w >= 1 && floorConfig.rows != 0) {
            if (!(flexCubeModel instanceof SubViewFlexCubeModel) && this.f4398j == flexCubeModel && flexCubeModel.getMultiple() == this.f4399k) {
                return;
            }
            this.f4398j = flexCubeModel;
            int i3 = flexCubeModel.floorConfig.rows;
            int i4 = o;
            if (i3 > i4) {
                i3 = i4;
            }
            this.f4400l = i3;
            List<MaterialModel> materialListByFloorNum = flexCubeModel.getMaterialListByFloorNum(i2);
            int i5 = this.f4400l;
            c(materialListByFloorNum, i5);
            this.f4400l = i5;
            int size = materialListByFloorNum == null ? 0 : materialListByFloorNum.size();
            this.f4399k = flexCubeModel.getMultiple();
            int totalCount = flexCubeModel.getTotalCount();
            FloorConfig floorConfig2 = flexCubeModel.floorConfig;
            CanvasConfig canvasConfig = flexCubeModel.canvasConfig;
            float f2 = this.f4399k;
            g(floorConfig2, canvasConfig, f2, f2, totalCount, size, i2);
            e(flexCubeModel.floorConfig.bgInfo, babelScope);
            f(flexCubeModel.floorConfig.viewStyle, this.f4399k, totalCount, i2);
            if (materialListByFloorNum != null && materialListByFloorNum.size() != 0) {
                this.f4397i.H(babelScope, flexCubeModel, materialListByFloorNum, i2);
                return;
            } else {
                b();
                return;
            }
        }
        b();
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    public void initView(String str) {
        this.f4397i.C(str);
    }

    @Override // com.jd.lib.flexcube.help.MsgInterface
    public void pushAction(Class<? extends MsgActionInterface> cls, Object obj) {
    }
}
