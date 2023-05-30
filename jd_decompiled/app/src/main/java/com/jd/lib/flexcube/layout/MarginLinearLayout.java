package com.jd.lib.flexcube.layout;

import android.content.Context;
import android.graphics.Canvas;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.entity.PageInfo;
import com.jd.lib.babel.servicekit.imagekit.ImageLoad;
import com.jd.lib.babel.servicekit.util.DPIUtil;
import com.jd.lib.flexcube.FlexCube;
import com.jd.lib.flexcube.canvas.FlexViewGroup;
import com.jd.lib.flexcube.canvas.entity.CanvasConfig;
import com.jd.lib.flexcube.help.FlexAutoPlayInterface;
import com.jd.lib.flexcube.help.MsgActionInterface;
import com.jd.lib.flexcube.help.MsgInterface;
import com.jd.lib.flexcube.iwidget.b.a;
import com.jd.lib.flexcube.iwidget.b.b;
import com.jd.lib.flexcube.iwidget.b.c;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jd.lib.flexcube.iwidget.entity.material.MaterialModel;
import com.jd.lib.flexcube.iwidget.ui.IUserSide;
import com.jd.lib.flexcube.layout.entity.FlexCubeModel;
import com.jd.lib.flexcube.layout.entity.FloorConfig;
import com.jd.lib.flexcube.layout.entity.common.BgInfo;
import com.jd.lib.flexcube.layout.entity.common.FloorStyle;
import com.jd.lib.flexcube.layout.entity.common.ViewStyle;
import com.jd.lib.flexcube.layout.floor.linear.ClickMoreView;
import com.jd.lib.flexcube.widgets.b.e;
import com.jd.lib.flexcube.widgets.entity.common.CfInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes14.dex */
public class MarginLinearLayout extends FrameLayout implements FlexAutoPlayInterface, MsgInterface {

    /* renamed from: g  reason: collision with root package name */
    private Context f4250g;

    /* renamed from: h  reason: collision with root package name */
    private ImageView f4251h;

    /* renamed from: i  reason: collision with root package name */
    public int f4252i;

    /* renamed from: j  reason: collision with root package name */
    private FlexCubeModel f4253j;

    /* renamed from: k  reason: collision with root package name */
    private List<FlexViewGroup> f4254k;

    /* renamed from: l  reason: collision with root package name */
    private e f4255l;

    /* renamed from: m  reason: collision with root package name */
    private ClickMoreView f4256m;

    /* renamed from: n  reason: collision with root package name */
    private boolean f4257n;

    public MarginLinearLayout(Context context) {
        super(context);
        this.f4254k = new ArrayList();
        this.f4250g = context;
        ImageView newImageView = ImageLoad.newImageView(context);
        this.f4251h = newImageView;
        newImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(this.f4251h);
        this.f4255l = new e(this);
    }

    private void a(BabelScope babelScope, FloorConfig floorConfig) {
        ClickMoreView clickMoreView;
        ClickMoreView clickMoreView2;
        if (this.f4257n && ((clickMoreView2 = this.f4256m) == null || clickMoreView2.getParent() == null)) {
            ClickMoreView clickMoreView3 = new ClickMoreView(this.f4250g);
            this.f4256m = clickMoreView3;
            addView(clickMoreView3);
        }
        if (!this.f4257n && (clickMoreView = this.f4256m) != null && clickMoreView.getParent() != null) {
            removeView(this.f4256m);
            this.f4256m = null;
        }
        ClickMoreView clickMoreView4 = this.f4256m;
        if (clickMoreView4 == null || clickMoreView4.getParent() == null) {
            return;
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(getLayoutParams().width, DPIUtil.getWidthByDesignValue1125(this.f4250g, 114));
        layoutParams.topMargin = getLayoutParams().height - DPIUtil.getWidthByDesignValue1125(this.f4250g, 114);
        this.f4256m.setLayoutParams(layoutParams);
        this.f4256m.updateStyle(this.f4253j, floorConfig, babelScope);
    }

    private void b() {
        setLayoutParams(new FrameLayout.LayoutParams(-1, 1));
    }

    private void g(ViewStyle viewStyle, BabelScope babelScope) {
        BgInfo bgInfo;
        PageInfo pageInfo;
        int i2 = 0;
        if (viewStyle != null && (bgInfo = viewStyle.bgInfo) != null && babelScope != null) {
            if ("1".equals(bgInfo.type) && c.d(bgInfo.imgUrl)) {
                setBackgroundColor(0);
                this.f4251h.setVisibility(0);
                this.f4251h.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                ImageLoad.with(this.f4251h).load(bgInfo.imgUrl);
                return;
            } else if ("1".equals(bgInfo.sameColor)) {
                if (babelScope != null && (pageInfo = babelScope.pageInfo) != null) {
                    i2 = a.a(pageInfo.pageBgColor, 0);
                }
                this.f4251h.setVisibility(8);
                setBackgroundColor(i2);
                return;
            } else if ("0".equals(bgInfo.type)) {
                this.f4251h.setVisibility(8);
                setBackgroundColor(a.a(bgInfo.color, 0));
                return;
            } else {
                this.f4251h.setVisibility(8);
                setBackgroundColor(0);
                return;
            }
        }
        this.f4251h.setVisibility(8);
        setBackgroundColor(0);
    }

    private void h(BabelScope babelScope, FloorConfig floorConfig, CanvasConfig canvasConfig, float f2, float f3, int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        if (floorConfig != null && floorConfig.w >= 1) {
            ViewStyle viewStyle = floorConfig.viewStyle;
            if (viewStyle != null) {
                i5 = viewStyle.leftPadding;
                i4 = viewStyle.topPadding;
            } else {
                i4 = 0;
                i5 = 0;
            }
            FloorStyle floorStyle = floorConfig.floorStyle;
            if (floorStyle != null) {
                i7 = b.d(floorStyle.cardHPadding, f2);
                i6 = b.d(floorStyle.cardVPadding, f2);
            } else {
                i6 = 0;
                i7 = 0;
            }
            if (canvasConfig != null) {
                i8 = canvasConfig.w;
                i9 = canvasConfig.f4223h;
            } else {
                i8 = 0;
                i9 = 0;
            }
            int d = b.d(i5, f2);
            int d2 = b.d(i4, f2);
            int d3 = b.d(i8, f3);
            int d4 = b.d(i9, f3);
            if (this.f4254k.size() > 0) {
                for (int i10 = 0; i10 < this.f4254k.size(); i10++) {
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(d3, d4);
                    if (i10 / this.f4252i == i3) {
                        layoutParams.topMargin = d2;
                    } else {
                        layoutParams.topMargin = i6;
                    }
                    layoutParams.leftMargin = ((d3 + i7) * i10) + d;
                    FlexViewGroup flexViewGroup = this.f4254k.get(i10);
                    flexViewGroup.i(babelScope, canvasConfig, f3);
                    flexViewGroup.setLayoutParams(layoutParams);
                }
                List<FlexViewGroup> list = this.f4254k;
                FlexViewGroup flexViewGroup2 = list.get(list.size() - 1);
                ViewGroup.LayoutParams layoutParams2 = flexViewGroup2.getLayoutParams();
                layoutParams2.width = i2 - (d3 * (this.f4254k.size() - 1));
                flexViewGroup2.i(babelScope, canvasConfig, f3);
                flexViewGroup2.setLayoutParams(layoutParams2);
                return;
            }
            return;
        }
        b();
    }

    @Override // com.jd.lib.flexcube.help.FlexAutoPlayInterface
    public boolean autoPlay(boolean z, boolean z2) {
        List<FlexViewGroup> list;
        if (z) {
            try {
                FlexCubeModel flexCubeModel = this.f4253j;
                if (flexCubeModel == null || !flexCubeModel.hasVideo || (list = this.f4254k) == null || list.size() <= 0) {
                    return false;
                }
                Iterator<FlexViewGroup> it = this.f4254k.iterator();
                while (it.hasNext()) {
                    if (it.next().autoPlay(z, z2)) {
                        return true;
                    }
                }
                return false;
            } catch (Throwable unused) {
                return false;
            }
        }
        return false;
    }

    public void c(String str) {
        if (this.f4254k.size() > 0) {
            removeAllViews();
            this.f4254k.clear();
        }
        this.f4252i = b.f(FlexCube.getSubInfo(str), 1);
        for (int i2 = 0; i2 < this.f4252i; i2++) {
            FlexViewGroup flexViewGroup = new FlexViewGroup(this.f4250g);
            this.f4254k.add(flexViewGroup);
            addView(flexViewGroup);
        }
    }

    public void d(CfInfo cfInfo, float f2) {
        this.f4255l.i(cfInfo, f2);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        this.f4255l.b(canvas);
        super.draw(canvas);
    }

    public void e(boolean z) {
        this.f4257n = z;
    }

    public void f(@NonNull BabelScope babelScope, @NonNull FlexCubeModel flexCubeModel, List<MaterialModel> list, int i2) {
        FloorConfig floorConfig;
        if (flexCubeModel != null && flexCubeModel.widgetList != null && list != null && list.size() != 0 && (floorConfig = flexCubeModel.floorConfig) != null && floorConfig.w >= 1) {
            this.f4253j = flexCubeModel;
            float multiple = flexCubeModel.getMultiple();
            float canvasMultiple = flexCubeModel.getCanvasMultiple();
            int allCanvasWidth = flexCubeModel.getAllCanvasWidth();
            g(flexCubeModel.floorConfig.viewStyle, babelScope);
            h(babelScope, flexCubeModel.floorConfig, flexCubeModel.canvasConfig, multiple, canvasMultiple, allCanvasWidth, i2);
            a(babelScope, flexCubeModel.floorConfig);
            List<BaseWidgetEntity> list2 = flexCubeModel.widgetList;
            if (list2 != null && list2.size() != 0 && this.f4254k.size() != 0) {
                IUserSide iUserSide = babelScope != null ? (IUserSide) babelScope.getService(IUserSide.class) : null;
                boolean z = iUserSide != null && iUserSide.getUserSide() == 1;
                int size = list.size();
                for (int i3 = 0; i3 <= this.f4254k.size() - 1; i3++) {
                    if (i3 < size) {
                        MaterialModel materialModel = list.get(i3);
                        if (materialModel != null) {
                            FlexViewGroup flexViewGroup = this.f4254k.get(i3);
                            if (flexViewGroup.getVisibility() != 0) {
                                flexViewGroup.setVisibility(0);
                            }
                            flexViewGroup.g(z);
                            CanvasConfig canvasConfig = flexCubeModel.canvasConfig;
                            flexViewGroup.a(list2, canvasMultiple, canvasConfig != null ? canvasConfig.getUuid() : null);
                            flexViewGroup.h(materialModel, babelScope);
                        }
                    } else {
                        this.f4254k.get(i3).setVisibility(8);
                    }
                }
                return;
            }
            b();
            return;
        }
        b();
    }

    @Override // android.view.View
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        this.f4255l.h(canvas);
    }

    @Override // com.jd.lib.flexcube.help.MsgInterface
    public void pushAction(Class<? extends MsgActionInterface> cls, Object obj) {
        List<FlexViewGroup> list = this.f4254k;
        if (list == null) {
            return;
        }
        Iterator<FlexViewGroup> it = list.iterator();
        while (it.hasNext()) {
            it.next().pushAction(cls, obj);
        }
    }
}
