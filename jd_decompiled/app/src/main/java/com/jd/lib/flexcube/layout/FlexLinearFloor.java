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
import com.jd.lib.babel.servicekit.util.DPIUtil;
import com.jd.lib.flexcube.canvas.entity.CanvasConfig;
import com.jd.lib.flexcube.help.FlexAutoPlayInterface;
import com.jd.lib.flexcube.help.MsgActionImp;
import com.jd.lib.flexcube.help.MsgActionInterface;
import com.jd.lib.flexcube.help.MsgInterface;
import com.jd.lib.flexcube.iwidget.b.c;
import com.jd.lib.flexcube.iwidget.entity.material.MaterialModel;
import com.jd.lib.flexcube.layout.entity.FlatConfig;
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
public class FlexLinearFloor extends FrameLayout implements IFloorView<FlexCubeModel>, FlexAutoPlayInterface, MsgInterface {

    /* renamed from: g */
    private Context f4234g;

    /* renamed from: h */
    private ImageView f4235h;

    /* renamed from: i */
    private MarginLinearLayout f4236i;

    /* renamed from: j */
    private FlexCubeModel f4237j;

    /* renamed from: k */
    private int f4238k;

    /* renamed from: l */
    private int f4239l;

    /* renamed from: m */
    private int f4240m;

    /* renamed from: n */
    private FlexActionListener f4241n;
    private BabelScope o;
    boolean p;
    private boolean q;

    /* loaded from: classes14.dex */
    public class a implements FlexActionListener {
        a() {
            FlexLinearFloor.this = r1;
        }

        @Override // com.jd.lib.babel.ifloor.utils.FlexActionListener
        public void onDarkModeChange(boolean z) {
            FlexLinearFloor.this.h(z);
        }
    }

    /* loaded from: classes14.dex */
    public class b extends MsgActionImp {
        b(FlexLinearFloor flexLinearFloor) {
        }
    }

    public FlexLinearFloor(Context context) {
        super(context);
        this.p = false;
        this.f4234g = context;
        ImageView newImageView = ImageLoad.newImageView(context);
        this.f4235h = newImageView;
        newImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(this.f4235h);
        MarginLinearLayout marginLinearLayout = new MarginLinearLayout(this.f4234g);
        this.f4236i = marginLinearLayout;
        addView(marginLinearLayout);
    }

    private void b() {
        BabelScope babelScope = this.o;
        if (babelScope == null) {
            return;
        }
        if (this.f4241n == null) {
            this.f4241n = new a();
        }
        babelScope.addActionListener(this.f4241n);
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

    public static List<MaterialModel> e(FloorConfig floorConfig, List<MaterialGroup> list, int i2) {
        if (floorConfig == null || floorConfig.columns <= 0 || list == null || list.size() <= 0 || list.get(0) == null || list.get(0).groupInfoList == null) {
            return null;
        }
        int i3 = floorConfig.columns;
        int i4 = i2 * i3;
        int i5 = i3 + i4;
        if ("1".equals(floorConfig.showHalfLine) && i5 > list.get(0).groupInfoList.size()) {
            i5 = list.get(0).groupInfoList.size();
        }
        if (i4 > list.get(0).groupInfoList.size() || i5 > list.get(0).groupInfoList.size()) {
            return null;
        }
        return list.get(0).groupInfoList.subList(i4, i5);
    }

    public static int f(FlexCubeModel flexCubeModel, FloorConfig floorConfig, List<MaterialGroup> list, int i2) {
        if (floorConfig == null || floorConfig.columns <= 0 || list == null || list.size() <= 0 || i2 <= 0 || list.get(0) == null || list.get(0).groupInfoList == null) {
            return 1;
        }
        int size = list.get(0).groupInfoList.size();
        if ("1".equals(floorConfig.showHalfLine)) {
            int i3 = size % i2;
            int i4 = size / i2;
            if (i3 != 0) {
                i4++;
            }
            return i4;
        }
        return size / i2;
    }

    private void g() {
        this.f4236i.setLayoutParams(new FrameLayout.LayoutParams(-1, 1));
    }

    public void h(boolean z) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("type", "darkModeChange");
            hashMap.put("darkMode", Boolean.valueOf(z));
            pushAction(MsgActionImp.class, new b(this).setHashMap(hashMap));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void j(BgInfo bgInfo, BabelScope babelScope) {
        PageInfo pageInfo;
        int i2 = 0;
        if (bgInfo != null && babelScope != null) {
            if ("1".equals(bgInfo.type) && c.d(bgInfo.imgUrl)) {
                setBackgroundColor(0);
                this.f4235h.setVisibility(0);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.f4238k, this.f4239l);
                layoutParams.topMargin = -this.f4240m;
                this.f4235h.setLayoutParams(layoutParams);
                ImageLoad.with(this.f4235h).load(bgInfo.imgUrl);
                return;
            } else if ("1".equals(bgInfo.sameColor)) {
                if (babelScope != null && (pageInfo = babelScope.pageInfo) != null) {
                    i2 = com.jd.lib.flexcube.iwidget.b.a.a(pageInfo.pageBgColor, 0);
                }
                this.f4235h.setVisibility(8);
                setBackgroundColor(i2);
                return;
            } else if ("0".equals(bgInfo.type)) {
                this.f4235h.setVisibility(8);
                setBackgroundColor(com.jd.lib.flexcube.iwidget.b.a.a(bgInfo.color, 0));
                return;
            } else {
                this.f4235h.setVisibility(8);
                setBackgroundColor(0);
                return;
            }
        }
        this.f4235h.setVisibility(8);
        setBackgroundColor(0);
    }

    private void k(ViewStyle viewStyle, float f2, int i2, int i3) {
        if (viewStyle == null) {
            this.f4236i.d(new CfInfo(0.0f, 0.0f, 0.0f, 0.0f), f2);
        } else if (viewStyle.isEmpty()) {
        } else {
            CfInfo cfInfo = null;
            if (i2 == 1) {
                cfInfo = new CfInfo(viewStyle.radiusLT, viewStyle.radiusRT, viewStyle.radiusLB, viewStyle.radiusRB);
            } else if (i3 == 0) {
                cfInfo = new CfInfo(viewStyle.radiusLT, viewStyle.radiusRT, 0.0f, 0.0f);
            } else {
                int i4 = i3 + 1;
                if (i4 < i2) {
                    cfInfo = new CfInfo(0.0f, 0.0f, 0.0f, 0.0f);
                } else if (i4 == i2) {
                    cfInfo = new CfInfo(0.0f, 0.0f, viewStyle.radiusLB, viewStyle.radiusRB);
                }
            }
            this.f4236i.d(cfInfo, f2);
        }
    }

    private void l(FloorConfig floorConfig, CanvasConfig canvasConfig, float f2, float f3, int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        FlatConfig flatConfig;
        if (floorConfig != null && floorConfig.w >= 1) {
            FloorStyle floorStyle = floorConfig.floorStyle;
            if (floorStyle != null) {
                i5 = floorStyle.leftPadding;
                i6 = floorStyle.rightPadding;
                i7 = floorStyle.topPadding;
                i8 = floorStyle.bottomPadding;
                int i11 = floorStyle.cardHPadding;
                i4 = floorStyle.cardVPadding;
            } else {
                i4 = 0;
                i5 = 0;
                i6 = 0;
                i7 = 0;
                i8 = 0;
            }
            ViewStyle viewStyle = floorConfig.viewStyle;
            if (viewStyle != null) {
                int i12 = viewStyle.leftPadding;
                int i13 = viewStyle.rightPadding;
                i10 = viewStyle.topPadding;
                i9 = viewStyle.bottomPadding;
            } else {
                i9 = 0;
                i10 = 0;
            }
            if (i2 == 1) {
                this.p = true;
                i4 = i10;
            } else {
                if (i3 == 0) {
                    this.p = false;
                    i4 = i10;
                } else {
                    int i14 = i3 + 1;
                    if (i14 < i2) {
                        this.p = false;
                    } else if (i14 == i2) {
                        this.p = true;
                        i7 = 0;
                    } else {
                        i4 = 0;
                    }
                    i7 = 0;
                }
                i8 = 0;
                i9 = 0;
            }
            int i15 = canvasConfig != null ? canvasConfig.f4223h : 0;
            if (this.p && (flatConfig = floorConfig.flatConfig) != null && flatConfig.showMoreClickEvent != null && "1".equals(flatConfig.showMore) && !"1".equals(floorConfig.flatConfig.showFinish)) {
                this.q = true;
            } else {
                this.q = false;
            }
            int d = com.jd.lib.flexcube.iwidget.b.b.d(i15, f3) + (this.q ? DPIUtil.getWidthByDesignValue1125(this.f4234g, 114) : 0) + com.jd.lib.flexcube.iwidget.b.b.d(i4, f2) + com.jd.lib.flexcube.iwidget.b.b.d(i9, f2);
            int d2 = (com.jd.lib.flexcube.iwidget.b.b.d(floorConfig.w, f2) - com.jd.lib.flexcube.iwidget.b.b.d(i5, f2)) - com.jd.lib.flexcube.iwidget.b.b.d(i6, f2);
            int i16 = d2 >= 0 ? d2 : 0;
            int d3 = com.jd.lib.flexcube.iwidget.b.b.d(i5, f2);
            int d4 = com.jd.lib.flexcube.iwidget.b.b.d(i7, f2);
            int d5 = com.jd.lib.flexcube.iwidget.b.b.d(i6, f2);
            int d6 = com.jd.lib.flexcube.iwidget.b.b.d(i8, f2);
            this.f4238k = i16 + d3 + d5;
            this.f4239l = d + d4 + d6;
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i16, d);
            layoutParams.setMargins(d3, d4 - this.f4240m, d5, d6);
            this.f4236i.setLayoutParams(layoutParams);
            this.f4236i.e(this.q);
            return;
        }
        g();
    }

    @Override // com.jd.lib.flexcube.help.FlexAutoPlayInterface
    public boolean autoPlay(boolean z, boolean z2) {
        FlexCubeModel flexCubeModel;
        if (z && (flexCubeModel = this.f4237j) != null && flexCubeModel.hasVideo) {
            return this.f4236i.autoPlay(z, z2);
        }
        return false;
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    /* renamed from: i */
    public void update(@NonNull BabelScope babelScope, @NonNull FlexCubeModel flexCubeModel, int i2) {
        FloorConfig floorConfig;
        if (flexCubeModel != null && (floorConfig = flexCubeModel.floorConfig) != null && floorConfig.w >= 1) {
            this.f4240m = 0;
            if (flexCubeModel.getAdapterPosition() == 0 && i2 == 0) {
                this.f4240m = flexCubeModel.floorConfig.cutHeadPx;
            }
            this.o = babelScope;
            this.f4237j = flexCubeModel;
            float multiple = flexCubeModel.getMultiple();
            float canvasMultiple = flexCubeModel.getCanvasMultiple();
            int totalCount = flexCubeModel.getTotalCount();
            l(flexCubeModel.floorConfig, flexCubeModel.canvasConfig, multiple, canvasMultiple, totalCount, i2);
            j(flexCubeModel.floorConfig.bgInfo, babelScope);
            k(flexCubeModel.floorConfig.viewStyle, multiple, totalCount, i2);
            List<MaterialModel> materialListByFloorNum = flexCubeModel.getMaterialListByFloorNum(i2);
            if (materialListByFloorNum != null && materialListByFloorNum.size() != 0) {
                this.f4236i.f(babelScope, flexCubeModel, materialListByFloorNum, i2);
                b();
                return;
            }
            g();
            return;
        }
        g();
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    public void initView(String str) {
        this.f4236i.c(str);
    }

    @Override // com.jd.lib.flexcube.help.MsgInterface
    public void pushAction(Class<? extends MsgActionInterface> cls, Object obj) {
        MarginLinearLayout marginLinearLayout = this.f4236i;
        if (marginLinearLayout != null) {
            marginLinearLayout.pushAction(cls, obj);
        }
    }
}
