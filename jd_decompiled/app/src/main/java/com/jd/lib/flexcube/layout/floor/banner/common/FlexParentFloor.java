package com.jd.lib.flexcube.layout.floor.banner.common;

import android.content.Context;
import android.graphics.Rect;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.entity.PageInfo;
import com.jd.lib.babel.ifloor.ui.IFloorView;
import com.jd.lib.babel.servicekit.imagekit.ImageLoad;
import com.jd.lib.flexcube.canvas.entity.CanvasConfig;
import com.jd.lib.flexcube.iwidget.b.b;
import com.jd.lib.flexcube.iwidget.b.c;
import com.jd.lib.flexcube.iwidget.entity.material.MaterialModel;
import com.jd.lib.flexcube.layout.entity.FlexCubeModel;
import com.jd.lib.flexcube.layout.entity.FloorConfig;
import com.jd.lib.flexcube.layout.entity.common.BgInfo;
import com.jd.lib.flexcube.layout.entity.common.FloorStyle;
import com.jd.lib.flexcube.layout.entity.common.ViewStyle;
import java.util.List;

/* loaded from: classes14.dex */
public abstract class FlexParentFloor extends FrameLayout implements IFloorView<FlexCubeModel> {

    /* renamed from: g  reason: collision with root package name */
    protected Context f4270g;

    /* renamed from: h  reason: collision with root package name */
    private ImageView f4271h;

    /* renamed from: i  reason: collision with root package name */
    protected FlexCubeModel f4272i;

    /* renamed from: j  reason: collision with root package name */
    protected float f4273j;

    /* renamed from: k  reason: collision with root package name */
    protected int f4274k;

    /* renamed from: l  reason: collision with root package name */
    protected int f4275l;

    public FlexParentFloor(@NonNull Context context) {
        super(context);
        this.f4270g = context;
        ImageView newImageView = ImageLoad.newImageView(context);
        this.f4271h = newImageView;
        newImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(this.f4271h);
    }

    private void d(BgInfo bgInfo, BabelScope babelScope) {
        PageInfo pageInfo;
        int i2 = 0;
        if (bgInfo != null && babelScope != null) {
            if ("1".equals(bgInfo.type) && c.d(bgInfo.imgUrl)) {
                setBackgroundColor(0);
                this.f4271h.setVisibility(0);
                this.f4271h.setLayoutParams(new FrameLayout.LayoutParams(this.f4274k, this.f4275l));
                ImageLoad.with(this.f4271h).load(bgInfo.imgUrl);
                return;
            } else if ("1".equals(bgInfo.sameColor)) {
                if (babelScope != null && (pageInfo = babelScope.pageInfo) != null) {
                    i2 = com.jd.lib.flexcube.iwidget.b.a.a(pageInfo.pageBgColor, 0);
                }
                this.f4271h.setVisibility(8);
                setBackgroundColor(i2);
                return;
            } else if ("0".equals(bgInfo.type)) {
                this.f4271h.setVisibility(8);
                setBackgroundColor(com.jd.lib.flexcube.iwidget.b.a.a(bgInfo.color, 0));
                return;
            } else {
                this.f4271h.setVisibility(8);
                setBackgroundColor(0);
                return;
            }
        }
        this.f4271h.setVisibility(8);
        setBackgroundColor(0);
    }

    public abstract void a();

    @Override // com.jd.lib.babel.ifloor.ui.IView
    /* renamed from: b  reason: merged with bridge method [inline-methods] */
    public void update(@NonNull BabelScope babelScope, @NonNull FlexCubeModel flexCubeModel, int i2) {
        FloorConfig floorConfig;
        if (flexCubeModel != null && (floorConfig = flexCubeModel.floorConfig) != null && floorConfig.w >= 1 && floorConfig.rows != 0) {
            FlexCubeModel flexCubeModel2 = this.f4272i;
            if (flexCubeModel2 == flexCubeModel && flexCubeModel2.getMultiple() == this.f4273j) {
                return;
            }
            this.f4272i = flexCubeModel;
            float multiple = flexCubeModel.getMultiple();
            this.f4273j = multiple;
            f(flexCubeModel.floorConfig, flexCubeModel.canvasConfig, multiple, i2);
            d(flexCubeModel.floorConfig.bgInfo, babelScope);
            List<MaterialModel> materialListByFloorNum = flexCubeModel.getMaterialListByFloorNum(0);
            if (materialListByFloorNum != null && materialListByFloorNum.size() != 0) {
                c(babelScope, flexCubeModel, materialListByFloorNum, 0);
                return;
            } else {
                a();
                return;
            }
        }
        a();
    }

    public abstract void c(@NonNull BabelScope babelScope, @NonNull FlexCubeModel flexCubeModel, List<MaterialModel> list, int i2);

    public abstract void e(int i2, int i3, Rect rect, Rect rect2);

    protected void f(FloorConfig floorConfig, CanvasConfig canvasConfig, float f2, int i2) {
        int i3;
        Rect rect;
        Rect rect2;
        if (floorConfig != null && floorConfig.rows != 0 && (i3 = floorConfig.w) >= 1 && canvasConfig != null) {
            this.f4274k = b.d(i3, f2);
            int d = b.d(canvasConfig.f4223h, f2);
            int d2 = b.d(canvasConfig.w, f2);
            FloorStyle floorStyle = floorConfig.floorStyle;
            if (floorStyle != null) {
                rect = floorStyle.getFloorPadding(f2);
            } else {
                rect = new Rect(0, 0, 0, 0);
            }
            ViewStyle viewStyle = floorConfig.viewStyle;
            if (viewStyle != null) {
                rect2 = viewStyle.getViewPadding(f2);
            } else {
                rect2 = new Rect(0, 0, 0, 0);
            }
            e(d2, d, rect, rect2);
            return;
        }
        a();
    }
}
