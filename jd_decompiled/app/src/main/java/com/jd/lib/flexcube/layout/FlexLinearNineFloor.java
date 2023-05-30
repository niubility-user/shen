package com.jd.lib.flexcube.layout;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.entity.PageInfo;
import com.jd.lib.babel.ifloor.ui.IFloorView;
import com.jd.lib.babel.ifloor.utils.FlexActionListener;
import com.jd.lib.babel.servicekit.imagekit.ImageLoad;
import com.jd.lib.flexcube.canvas.entity.CanvasConfig;
import com.jd.lib.flexcube.help.FlexAutoPlayInterface;
import com.jd.lib.flexcube.help.MsgActionImp;
import com.jd.lib.flexcube.help.MsgActionInterface;
import com.jd.lib.flexcube.help.MsgInterface;
import com.jd.lib.flexcube.iwidget.b.c;
import com.jd.lib.flexcube.iwidget.entity.material.MaterialModel;
import com.jd.lib.flexcube.layout.entity.FlexCubeModel;
import com.jd.lib.flexcube.layout.entity.FloorConfig;
import com.jd.lib.flexcube.layout.entity.MaterialGroup;
import com.jd.lib.flexcube.layout.entity.common.BgInfo;
import com.jd.lib.flexcube.layout.entity.common.FloorStyle;
import com.jd.lib.flexcube.layout.entity.common.ViewStyle;
import com.jd.lib.flexcube.widgets.entity.common.CfInfo;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes14.dex */
public class FlexLinearNineFloor extends FrameLayout implements IFloorView<FlexCubeModel>, FlexAutoPlayInterface, MsgInterface {

    /* renamed from: g */
    private Context f4242g;

    /* renamed from: h */
    private ImageView f4243h;

    /* renamed from: i */
    private MarginLinearNineLayout f4244i;

    /* renamed from: j */
    private FlexCubeModel f4245j;

    /* renamed from: k */
    private int f4246k;

    /* renamed from: l */
    private int f4247l;

    /* renamed from: m */
    private int f4248m;

    /* renamed from: n */
    private FlexActionListener f4249n;
    private BabelScope o;

    /* loaded from: classes14.dex */
    public class a implements FlexActionListener {
        a() {
            FlexLinearNineFloor.this = r1;
        }

        @Override // com.jd.lib.babel.ifloor.utils.FlexActionListener
        public void onDarkModeChange(boolean z) {
            FlexLinearNineFloor.this.g(z);
        }
    }

    /* loaded from: classes14.dex */
    public class b extends MsgActionImp {
        b(FlexLinearNineFloor flexLinearNineFloor) {
        }
    }

    public FlexLinearNineFloor(Context context) {
        super(context);
        this.f4242g = context;
        ImageView newImageView = ImageLoad.newImageView(context);
        this.f4243h = newImageView;
        newImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(this.f4243h);
        MarginLinearNineLayout marginLinearNineLayout = new MarginLinearNineLayout(this.f4242g);
        this.f4244i = marginLinearNineLayout;
        addView(marginLinearNineLayout);
    }

    private void b() {
        BabelScope babelScope = this.o;
        if (babelScope == null) {
            return;
        }
        if (this.f4249n == null) {
            this.f4249n = new a();
        }
        babelScope.addActionListener(this.f4249n);
    }

    public static int c(FloorConfig floorConfig, CanvasConfig canvasConfig, float f2, int i2) {
        int i3;
        int i4;
        if (floorConfig == null || canvasConfig == null || canvasConfig.w == 0) {
            return 0;
        }
        FloorStyle floorStyle = floorConfig.floorStyle;
        if (floorStyle != null) {
            i4 = com.jd.lib.flexcube.iwidget.b.b.d(floorStyle.leftPadding, f2) + com.jd.lib.flexcube.iwidget.b.b.d(floorStyle.rightPadding, f2);
            i3 = com.jd.lib.flexcube.iwidget.b.b.d(floorStyle.cardHPadding * (i2 - 1), f2);
        } else {
            i3 = 0;
            i4 = 0;
        }
        ViewStyle viewStyle = floorConfig.viewStyle;
        return ((com.jd.lib.flexcube.iwidget.b.b.d(floorConfig.w, f2) - (viewStyle != null ? com.jd.lib.flexcube.iwidget.b.b.d(viewStyle.leftPadding, f2) + com.jd.lib.flexcube.iwidget.b.b.d(viewStyle.rightPadding, f2) : 0)) - i4) - i3;
    }

    public static float d(FloorConfig floorConfig, CanvasConfig canvasConfig, int i2, int i3) {
        int i4;
        if (floorConfig == null || canvasConfig == null || (i4 = canvasConfig.w) == 0 || i2 <= 0 || i3 <= 0) {
            return 0.0f;
        }
        return (i2 * 1.0f) / (i4 * i3);
    }

    public static int e(FlexCubeModel flexCubeModel, FloorConfig floorConfig, List<MaterialGroup> list, int i2) {
        if (floorConfig == null || floorConfig.columns <= 0 || list == null || list.size() <= 0 || i2 <= 0 || list.get(0) == null || list.get(0).groupInfoList == null) {
            return 1;
        }
        int size = list.get(0).groupInfoList.size();
        int i3 = size / i2;
        return i2 * i3 < size ? i3 + 1 : i3;
    }

    private void f() {
        this.f4244i.setLayoutParams(new FrameLayout.LayoutParams(-1, 1));
    }

    public void g(boolean z) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("type", "darkModeChange");
            hashMap.put("darkMode", Boolean.valueOf(z));
            pushAction(MsgActionImp.class, new b(this).setHashMap(hashMap));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void i(BgInfo bgInfo, BabelScope babelScope) {
        PageInfo pageInfo;
        int i2 = 0;
        if (bgInfo != null && babelScope != null) {
            if ("1".equals(bgInfo.type) && c.d(bgInfo.imgUrl)) {
                setBackgroundColor(0);
                this.f4243h.setVisibility(0);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.f4246k, this.f4247l);
                layoutParams.topMargin = -this.f4248m;
                this.f4243h.setLayoutParams(layoutParams);
                ImageLoad.with(this.f4243h).load(bgInfo.imgUrl);
                return;
            } else if ("1".equals(bgInfo.sameColor)) {
                if (babelScope != null && (pageInfo = babelScope.pageInfo) != null) {
                    i2 = com.jd.lib.flexcube.iwidget.b.a.a(pageInfo.pageBgColor, 0);
                }
                this.f4243h.setVisibility(8);
                setBackgroundColor(i2);
                return;
            } else if ("0".equals(bgInfo.type)) {
                this.f4243h.setVisibility(8);
                setBackgroundColor(com.jd.lib.flexcube.iwidget.b.a.a(bgInfo.color, 0));
                return;
            } else {
                this.f4243h.setVisibility(8);
                setBackgroundColor(0);
                return;
            }
        }
        this.f4243h.setVisibility(8);
        setBackgroundColor(0);
    }

    private void j(ViewStyle viewStyle, float f2) {
        if (viewStyle == null) {
            this.f4244i.d(new CfInfo(0.0f, 0.0f, 0.0f, 0.0f), f2);
        } else if (viewStyle.isEmpty()) {
        } else {
            this.f4244i.d(new CfInfo(viewStyle.radiusLT, viewStyle.radiusRT, viewStyle.radiusLB, viewStyle.radiusRB), f2);
        }
    }

    private void k(FloorConfig floorConfig, CanvasConfig canvasConfig, float f2, float f3, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        if (floorConfig != null && floorConfig.w >= 1) {
            FloorStyle floorStyle = floorConfig.floorStyle;
            if (floorStyle != null) {
                i4 = floorStyle.leftPadding;
                i5 = floorStyle.rightPadding;
                i6 = floorStyle.topPadding;
                i7 = floorStyle.bottomPadding;
                int i10 = floorStyle.cardHPadding;
                i3 = floorStyle.cardVPadding;
            } else {
                i3 = 0;
                i4 = 0;
                i5 = 0;
                i6 = 0;
                i7 = 0;
            }
            ViewStyle viewStyle = floorConfig.viewStyle;
            if (viewStyle != null) {
                int i11 = viewStyle.leftPadding;
                int i12 = viewStyle.rightPadding;
                i9 = viewStyle.topPadding;
                i8 = viewStyle.bottomPadding;
            } else {
                i8 = 0;
                i9 = 0;
            }
            int i13 = canvasConfig != null ? canvasConfig.f4223h : 0;
            int d = com.jd.lib.flexcube.iwidget.b.b.d(i4, f2);
            int d2 = com.jd.lib.flexcube.iwidget.b.b.d(i6, f2);
            int d3 = com.jd.lib.flexcube.iwidget.b.b.d(i5, f2);
            int d4 = com.jd.lib.flexcube.iwidget.b.b.d(i7, f2);
            int d5 = (com.jd.lib.flexcube.iwidget.b.b.d(floorConfig.w, f2) - com.jd.lib.flexcube.iwidget.b.b.d(i4, f2)) - com.jd.lib.flexcube.iwidget.b.b.d(i5, f2);
            int i14 = d5 >= 0 ? d5 : 0;
            int d6 = com.jd.lib.flexcube.iwidget.b.b.d(i9 + i8 + (i3 * (i2 - 1)), f2) + com.jd.lib.flexcube.iwidget.b.b.d(i13 * i2, f3);
            this.f4246k = i14 + d + d3;
            this.f4247l = d6 + d2 + d4;
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i14, d6);
            layoutParams.setMargins(d, d2 - this.f4248m, d3, d4);
            this.f4244i.setLayoutParams(layoutParams);
            return;
        }
        f();
    }

    @Override // com.jd.lib.flexcube.help.FlexAutoPlayInterface
    public boolean autoPlay(boolean z, boolean z2) {
        FlexCubeModel flexCubeModel;
        if (z && (flexCubeModel = this.f4245j) != null && flexCubeModel.hasVideo) {
            return this.f4244i.autoPlay(z, z2);
        }
        return false;
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    /* renamed from: h */
    public void update(@NonNull BabelScope babelScope, @NonNull FlexCubeModel flexCubeModel, int i2) {
        FloorConfig floorConfig;
        if (flexCubeModel != null && (floorConfig = flexCubeModel.floorConfig) != null && floorConfig.w >= 1) {
            this.f4248m = 0;
            if (flexCubeModel.getAdapterPosition() == 0 && i2 == 0) {
                this.f4248m = flexCubeModel.floorConfig.cutHeadPx;
            }
            this.o = babelScope;
            this.f4245j = flexCubeModel;
            float multiple = flexCubeModel.getMultiple();
            float canvasMultiple = flexCubeModel.getCanvasMultiple();
            String str = "multiple=" + multiple + " canvasMultiple=" + canvasMultiple;
            int totalRow = flexCubeModel.getTotalRow();
            k(flexCubeModel.floorConfig, flexCubeModel.canvasConfig, multiple, canvasMultiple, totalRow);
            i(flexCubeModel.floorConfig.bgInfo, babelScope);
            j(flexCubeModel.floorConfig.viewStyle, multiple);
            List<MaterialModel> materialListByFloorNum = flexCubeModel.getMaterialListByFloorNum(i2);
            if (materialListByFloorNum != null && materialListByFloorNum.size() != 0) {
                this.f4244i.e(babelScope, flexCubeModel, materialListByFloorNum, totalRow);
                b();
                return;
            }
            f();
            return;
        }
        f();
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    public void initView(String str) {
        this.f4244i.b(str);
    }

    @Override // com.jd.lib.flexcube.help.MsgInterface
    public void pushAction(Class<? extends MsgActionInterface> cls, Object obj) {
        MarginLinearNineLayout marginLinearNineLayout = this.f4244i;
        if (marginLinearNineLayout != null) {
            marginLinearNineLayout.pushAction(cls, obj);
        }
    }
}
