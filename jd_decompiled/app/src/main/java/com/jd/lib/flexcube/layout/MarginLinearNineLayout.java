package com.jd.lib.flexcube.layout;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.entity.PageInfo;
import com.jd.lib.babel.servicekit.imagekit.ImageLoad;
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
import com.jd.lib.flexcube.layout.entity.FlexCubeModel;
import com.jd.lib.flexcube.layout.entity.FloorConfig;
import com.jd.lib.flexcube.layout.entity.common.BgInfo;
import com.jd.lib.flexcube.layout.entity.common.FloorStyle;
import com.jd.lib.flexcube.layout.entity.common.ViewStyle;
import com.jd.lib.flexcube.widgets.b.e;
import com.jd.lib.flexcube.widgets.entity.common.CfInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes14.dex */
public class MarginLinearNineLayout extends FrameLayout implements FlexAutoPlayInterface, MsgInterface {

    /* renamed from: g  reason: collision with root package name */
    private Context f4258g;

    /* renamed from: h  reason: collision with root package name */
    private ImageView f4259h;

    /* renamed from: i  reason: collision with root package name */
    public int f4260i;

    /* renamed from: j  reason: collision with root package name */
    public int f4261j;

    /* renamed from: k  reason: collision with root package name */
    private FlexCubeModel f4262k;

    /* renamed from: l  reason: collision with root package name */
    private List<FlexViewGroup> f4263l;

    /* renamed from: m  reason: collision with root package name */
    private e f4264m;

    /* renamed from: n  reason: collision with root package name */
    private String f4265n;

    public MarginLinearNineLayout(Context context) {
        super(context);
        this.f4263l = new ArrayList();
        this.f4258g = context;
        ImageView newImageView = ImageLoad.newImageView(context);
        this.f4259h = newImageView;
        newImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(this.f4259h);
        this.f4264m = new e(this);
    }

    private void a() {
        setLayoutParams(new FrameLayout.LayoutParams(-1, 1));
    }

    private void c(@NonNull FlexCubeModel flexCubeModel) {
        if (flexCubeModel.getId().equals(this.f4265n)) {
            return;
        }
        String id = flexCubeModel.getId();
        this.f4265n = id;
        this.f4260i = b.f(FlexCube.getSubInfo(id, 1), 1);
        int f2 = b.f(FlexCube.getSubInfo(this.f4265n, 2), 1);
        this.f4261j = f2;
        List<FlexViewGroup> list = this.f4263l;
        int size = f2 - (list == null ? 0 : list.size());
        if (size > 0) {
            for (int i2 = 0; i2 < size; i2++) {
                FlexViewGroup flexViewGroup = new FlexViewGroup(this.f4258g);
                this.f4263l.add(flexViewGroup);
                addView(flexViewGroup);
            }
        } else if (size < 0) {
            for (int size2 = this.f4263l.size() - 1; size2 >= this.f4261j; size2--) {
                removeView(this.f4263l.get(size2));
                this.f4263l.remove(size2);
            }
        }
    }

    private void f(ViewStyle viewStyle, BabelScope babelScope) {
        BgInfo bgInfo;
        PageInfo pageInfo;
        int i2 = 0;
        if (viewStyle != null && (bgInfo = viewStyle.bgInfo) != null && babelScope != null) {
            if ("1".equals(bgInfo.type) && c.d(bgInfo.imgUrl)) {
                setBackgroundColor(0);
                this.f4259h.setVisibility(0);
                this.f4259h.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                ImageLoad.with(this.f4259h).load(bgInfo.imgUrl);
                return;
            } else if ("1".equals(bgInfo.sameColor)) {
                if (babelScope != null && (pageInfo = babelScope.pageInfo) != null) {
                    i2 = a.a(pageInfo.pageBgColor, 0);
                }
                this.f4259h.setVisibility(8);
                setBackgroundColor(i2);
                return;
            } else if ("0".equals(bgInfo.type)) {
                this.f4259h.setVisibility(8);
                setBackgroundColor(a.a(bgInfo.color, 0));
                return;
            } else {
                this.f4259h.setVisibility(8);
                setBackgroundColor(0);
                return;
            }
        }
        this.f4259h.setVisibility(8);
        setBackgroundColor(0);
    }

    private void g(BabelScope babelScope, FloorConfig floorConfig, CanvasConfig canvasConfig, float f2, float f3, int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
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
            int[] iArr = new int[i3];
            if (this.f4263l.size() > 0) {
                for (int i12 = 0; i12 < this.f4263l.size() && (i11 = i12 / (i10 = this.f4260i)) < i3; i12++) {
                    int i13 = i12 - (i10 * i11);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(d3, d4);
                    if (iArr[i11] == 0) {
                        if (i11 == 0) {
                            iArr[i11] = d2;
                        } else {
                            iArr[i11] = iArr[i11 - 1] + d4 + i6;
                        }
                    }
                    layoutParams.topMargin = iArr[i11];
                    layoutParams.leftMargin = ((d3 + i7) * i13) + d;
                    FlexViewGroup flexViewGroup = this.f4263l.get(i12);
                    int i14 = this.f4260i;
                    if (((i11 * i14) + i14) - 1 == i12) {
                        layoutParams.width = i2 - ((i14 - 1) * d3);
                    }
                    flexViewGroup.i(babelScope, canvasConfig, f3);
                    flexViewGroup.setLayoutParams(layoutParams);
                }
                return;
            }
            return;
        }
        a();
    }

    @Override // com.jd.lib.flexcube.help.FlexAutoPlayInterface
    public boolean autoPlay(boolean z, boolean z2) {
        List<FlexViewGroup> list;
        if (z) {
            try {
                FlexCubeModel flexCubeModel = this.f4262k;
                if (flexCubeModel == null || !flexCubeModel.hasVideo || (list = this.f4263l) == null || list.size() <= 0) {
                    return false;
                }
                Iterator<FlexViewGroup> it = this.f4263l.iterator();
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

    public void b(String str) {
        this.f4265n = str;
        if (this.f4263l.size() > 0) {
            removeAllViews();
            this.f4263l.clear();
        }
        this.f4260i = b.f(FlexCube.getSubInfo(str, 1), 1);
        this.f4261j = b.f(FlexCube.getSubInfo(str, 2), 1);
        for (int i2 = 0; i2 < this.f4261j; i2++) {
            FlexViewGroup flexViewGroup = new FlexViewGroup(this.f4258g);
            this.f4263l.add(flexViewGroup);
            addView(flexViewGroup);
        }
    }

    public void d(CfInfo cfInfo, float f2) {
        this.f4264m.i(cfInfo, f2);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        this.f4264m.b(canvas);
        super.draw(canvas);
    }

    public void e(@NonNull BabelScope babelScope, @NonNull FlexCubeModel flexCubeModel, List<MaterialModel> list, int i2) {
        FloorConfig floorConfig;
        if (flexCubeModel != null && flexCubeModel.widgetList != null && list != null && list.size() != 0 && (floorConfig = flexCubeModel.floorConfig) != null && floorConfig.w >= 1) {
            this.f4262k = flexCubeModel;
            float multiple = flexCubeModel.getMultiple();
            float canvasMultiple = flexCubeModel.getCanvasMultiple();
            int allCanvasWidth = flexCubeModel.getAllCanvasWidth();
            c(flexCubeModel);
            f(flexCubeModel.floorConfig.viewStyle, babelScope);
            g(babelScope, flexCubeModel.floorConfig, flexCubeModel.canvasConfig, multiple, canvasMultiple, allCanvasWidth, i2);
            List<BaseWidgetEntity> list2 = flexCubeModel.widgetList;
            if (list2 != null && list2.size() != 0 && this.f4263l.size() != 0) {
                for (int i3 = 0; i3 < this.f4263l.size() && i3 < list.size(); i3++) {
                    MaterialModel materialModel = list.get(i3);
                    if (materialModel != null) {
                        FlexViewGroup flexViewGroup = this.f4263l.get(i3);
                        CanvasConfig canvasConfig = flexCubeModel.canvasConfig;
                        flexViewGroup.a(list2, canvasMultiple, canvasConfig != null ? canvasConfig.getUuid() : null);
                        flexViewGroup.h(materialModel, babelScope);
                    }
                }
                return;
            }
            a();
            return;
        }
        a();
    }

    @Override // android.view.View
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        this.f4264m.h(canvas);
    }

    @Override // com.jd.lib.flexcube.help.MsgInterface
    public void pushAction(Class<? extends MsgActionInterface> cls, Object obj) {
        List<FlexViewGroup> list = this.f4263l;
        if (list == null) {
            return;
        }
        Iterator<FlexViewGroup> it = list.iterator();
        while (it.hasNext()) {
            it.next().pushAction(cls, obj);
        }
    }
}
