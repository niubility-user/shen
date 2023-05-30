package com.jd.lib.flexcube.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.facebook.yoga.YogaNode;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.entity.PageInfo;
import com.jd.lib.babel.servicekit.imagekit.ImageLoad;
import com.jd.lib.flexcube.R;
import com.jd.lib.flexcube.canvas.entity.CanvasConfig;
import com.jd.lib.flexcube.canvas.entity.common.BgInfo;
import com.jd.lib.flexcube.help.FlexAutoPlayInterface;
import com.jd.lib.flexcube.iwidget.b.c;
import com.jd.lib.flexcube.iwidget.entity.BaseConfig;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jd.lib.flexcube.iwidget.entity.material.ClickEvent;
import com.jd.lib.flexcube.iwidget.ui.IKnowWidget;
import com.jd.lib.flexcube.iwidget.ui.IWidget;
import com.jd.lib.flexcube.iwidget.ui.IWidgetCommunication;
import com.jd.lib.flexcube.layout.entity.FloorConfig;
import com.jd.lib.flexcube.layout.entity.common.ViewStyle;
import com.jd.lib.flexcube.layout.layout.YogaLayout;
import com.jd.lib.flexcube.pool.WidgetPool;
import com.jd.lib.flexcube.widgets.b.b;
import com.jd.lib.flexcube.widgets.b.e;
import com.jd.lib.flexcube.widgets.c.a;
import com.jd.lib.flexcube.widgets.entity.FlexBoxEntity;
import com.jd.lib.flexcube.widgets.entity.FlexBoxStyleConfig;
import com.jd.lib.flexcube.widgets.entity.common.CfInfo;
import com.jd.lib.flexcube.widgets.entity.flexbox.BoxPath;
import com.jd.lib.flexcube.widgets.entity.flexbox.FlexBoxConfig;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes15.dex */
public class FlexBoxView extends FrameLayout implements IWidget<FlexBoxEntity>, FlexAutoPlayInterface, IKnowWidget<FlexBoxEntity> {

    /* renamed from: g  reason: collision with root package name */
    private FlexBoxStyleConfig f4436g;

    /* renamed from: h  reason: collision with root package name */
    private float f4437h;

    /* renamed from: i  reason: collision with root package name */
    private Context f4438i;

    /* renamed from: j  reason: collision with root package name */
    private FlexBoxEntity f4439j;

    /* renamed from: k  reason: collision with root package name */
    private FlexBoxConfig f4440k;

    /* renamed from: l  reason: collision with root package name */
    public CanvasConfig f4441l;

    /* renamed from: m  reason: collision with root package name */
    private List<IWidget> f4442m;

    /* renamed from: n  reason: collision with root package name */
    private YogaLayout f4443n;
    private ImageView o;
    private YogaNode p;
    private e q;
    private Map<IWidget, BaseConfig> r;

    public FlexBoxView(Context context) {
        super(context);
        this.f4438i = context;
        this.q = new e(this);
        ImageView newImageView = ImageLoad.newImageView(this.f4438i);
        this.o = newImageView;
        addView(newImageView, new FrameLayout.LayoutParams(-1, -1));
        YogaLayout yogaLayout = new YogaLayout(this.f4438i);
        this.f4443n = yogaLayout;
        this.p = yogaLayout.e();
        addView(this.f4443n);
    }

    private void c(float f2) {
        CfInfo cfInfo = this.f4441l.cfInfo;
        if (cfInfo != null) {
            this.q.i(cfInfo, f2);
        }
    }

    private void d(Map<String, String> map, IWidgetCommunication iWidgetCommunication) {
        BoxPath boxPath;
        FlexBoxEntity flexBoxEntity = this.f4439j;
        if (flexBoxEntity == null || (boxPath = flexBoxEntity.dataPath) == null || boxPath.clickEvent == null) {
            return;
        }
        Serializable serializable = iWidgetCommunication.getStateBundle().getSerializable(this.f4439j.dataPath.clickEvent);
        ClickEvent clickEvent = serializable instanceof ClickEvent ? (ClickEvent) serializable : null;
        if (clickEvent == null) {
            clickEvent = b.a(map, this.f4439j.dataPath.clickEvent);
        }
        if (clickEvent != null) {
            a.b bVar = new a.b(getContext(), clickEvent);
            bVar.a(iWidgetCommunication.getBabelScope());
            setOnClickListener(bVar.b());
            iWidgetCommunication.getStateBundle().putSerializable(this.f4439j.dataPath.clickEvent, clickEvent);
            return;
        }
        setClickable(false);
    }

    private void f(BabelScope babelScope, CanvasConfig canvasConfig) {
        BgInfo bgInfo;
        PageInfo pageInfo;
        int i2 = 0;
        if (canvasConfig != null && (bgInfo = canvasConfig.bgInfo) != null && babelScope != null) {
            if ("1".equals(bgInfo.type) && c.d(bgInfo.imgUrl)) {
                setBackgroundColor(0);
                this.o.setVisibility(0);
                this.o.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                ImageLoad.with(this.o).load(bgInfo.imgUrl);
                return;
            } else if ("1".equals(bgInfo.sameColor)) {
                if (babelScope != null && (pageInfo = babelScope.pageInfo) != null) {
                    i2 = com.jd.lib.flexcube.iwidget.b.a.a(pageInfo.pageBgColor, 0);
                }
                this.o.setVisibility(8);
                setBackgroundColor(i2);
                return;
            } else if ("0".equals(bgInfo.type)) {
                this.o.setVisibility(8);
                setBackgroundColor(com.jd.lib.flexcube.iwidget.b.a.a(bgInfo.color, 0));
                return;
            } else {
                this.o.setVisibility(8);
                setBackgroundColor(0);
                return;
            }
        }
        this.o.setVisibility(8);
        setBackgroundColor(0);
    }

    public List<IWidget> a() {
        if (this.f4442m == null) {
            this.f4442m = new ArrayList();
        }
        return this.f4442m;
    }

    @Override // com.jd.lib.flexcube.help.FlexAutoPlayInterface
    public boolean autoPlay(boolean z, boolean z2) {
        boolean z3 = false;
        try {
            List<IWidget> list = this.f4442m;
            if (list != null && list.size() > 0) {
                for (IWidget iWidget : this.f4442m) {
                    if (iWidget instanceof FlexVideoView) {
                        z3 = ((FlexVideoView) iWidget).h();
                    }
                    if (z3) {
                        break;
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return z3;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IKnowWidget
    /* renamed from: b  reason: merged with bridge method [inline-methods] */
    public FlexBoxEntity getWidgetEntity() {
        return this.f4439j;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void clear() {
        ImageView imageView = this.o;
        if (imageView != null) {
            imageView.setVisibility(8);
            setBackgroundColor(0);
        }
        List<IWidget> list = this.f4442m;
        if (list != null && !list.isEmpty()) {
            for (IWidget iWidget : this.f4442m) {
                if (iWidget instanceof View) {
                    iWidget.clear();
                    WidgetPool.e((View) iWidget);
                }
            }
            this.f4442m.clear();
        }
        Map<IWidget, BaseConfig> map = this.r;
        if (map != null) {
            map.clear();
        }
        YogaLayout yogaLayout = this.f4443n;
        if (yogaLayout != null) {
            yogaLayout.removeAllViews();
        }
        setClickable(false);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        this.q.b(canvas);
        super.draw(canvas);
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    /* renamed from: e  reason: merged with bridge method [inline-methods] */
    public void updateStyle(@NonNull FlexBoxEntity flexBoxEntity, float f2) {
        FlexBoxConfig flexBoxConfig;
        ViewStyle viewStyle;
        String str = "updateStyle=" + hashCode();
        this.f4437h = f2;
        this.f4439j = flexBoxEntity;
        this.f4441l = flexBoxEntity.canvasConfig;
        if (flexBoxEntity != null && (flexBoxConfig = flexBoxEntity.config) != null) {
            this.f4440k = flexBoxConfig;
            FlexBoxStyleConfig flexBoxStyleConfig = flexBoxConfig.linearLayoutStyle;
            this.f4436g = flexBoxStyleConfig;
            if (flexBoxStyleConfig == null) {
                clear();
                return;
            }
            flexBoxEntity.parseElementList();
            List<BaseWidgetEntity> list = flexBoxEntity.widgetList;
            if (list != null && !list.isEmpty()) {
                FlexBoxStyleConfig flexBoxStyleConfig2 = this.f4436g;
                CanvasConfig canvasConfig = this.f4441l;
                flexBoxStyleConfig2.width = canvasConfig.w;
                flexBoxStyleConfig2.height = canvasConfig.f4223h;
                FloorConfig floorConfig = flexBoxEntity.floorConfig;
                if (floorConfig != null && (viewStyle = floorConfig.viewStyle) != null) {
                    flexBoxStyleConfig2.viewStyle = viewStyle.copyStyle();
                }
                com.jd.lib.flexcube.layout.layout.a.i(this.p, this.f4440k, this.f4437h);
                c(f2);
                this.r = new HashMap();
                for (BaseWidgetEntity baseWidgetEntity : flexBoxEntity.widgetList) {
                    if (baseWidgetEntity != null) {
                        View a = WidgetPool.a(baseWidgetEntity.type, this.f4438i);
                        if (a instanceof IWidget) {
                            a.setTag(R.id.type, baseWidgetEntity.type);
                            IWidget iWidget = (IWidget) a;
                            iWidget.updateStyle(baseWidgetEntity, this.f4437h);
                            a().add(iWidget);
                            this.f4443n.addView(a, new ViewGroup.LayoutParams(iWidget.getLayoutParamsWidth(), iWidget.getLayoutParamsHeight()));
                            YogaNode f3 = this.f4443n.f(a);
                            if (baseWidgetEntity.getBaseConfig() != null && f3 != null) {
                                if (baseWidgetEntity instanceof FlexBoxEntity) {
                                    com.jd.lib.flexcube.layout.layout.a.i(f3, (FlexBoxConfig) baseWidgetEntity.getBaseConfig(), this.f4437h);
                                } else {
                                    this.r.put(iWidget, baseWidgetEntity.getBaseConfig());
                                    com.jd.lib.flexcube.layout.layout.a.j(f3, baseWidgetEntity.getBaseConfig(), this.f4437h);
                                }
                            }
                        }
                    }
                }
                return;
            }
            clear();
            return;
        }
        clear();
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsHeight() {
        YogaNode yogaNode;
        FlexBoxStyleConfig flexBoxStyleConfig = this.f4436g;
        if (flexBoxStyleConfig != null && (yogaNode = this.p) != null) {
            try {
                int i2 = flexBoxStyleConfig.height;
                boolean z = true;
                if (flexBoxStyleConfig.heightAuto != 1) {
                    z = false;
                }
                return com.jd.lib.flexcube.layout.layout.a.m(yogaNode, i2, z, this.f4437h);
            } catch (Exception e2) {
                if (com.jd.lib.flexcube.a.a) {
                    e2.printStackTrace();
                }
            }
        }
        return 0;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsWidth() {
        YogaNode yogaNode;
        FlexBoxStyleConfig flexBoxStyleConfig = this.f4436g;
        if (flexBoxStyleConfig != null && (yogaNode = this.p) != null) {
            try {
                int i2 = flexBoxStyleConfig.width;
                boolean z = true;
                if (flexBoxStyleConfig.widthAuto != 1) {
                    z = false;
                }
                return com.jd.lib.flexcube.layout.layout.a.n(yogaNode, i2, z, this.f4437h);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return 0;
    }

    @Override // android.view.View
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        this.q.h(canvas);
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void updateContent(@NonNull Map<String, String> map, IWidgetCommunication iWidgetCommunication) {
        if (map != null && this.f4439j != null && this.f4440k != null && this.f4436g != null) {
            if (iWidgetCommunication != null) {
                f(iWidgetCommunication.getBabelScope(), this.f4441l);
            }
            for (IWidget iWidget : a()) {
                iWidget.updateContent(map, iWidgetCommunication);
                if (iWidget instanceof View) {
                    View view = (View) iWidget;
                    YogaNode f2 = this.f4443n.f(view);
                    if (view.getVisibility() == 0) {
                        if (iWidget instanceof FlexBoxView) {
                            iWidget.getLayoutParamsWidth();
                            iWidget.getLayoutParamsHeight();
                        } else {
                            Map<IWidget, BaseConfig> map2 = this.r;
                            if (map2 != null) {
                                com.jd.lib.flexcube.layout.layout.a.j(f2, map2.get(iWidget), this.f4437h);
                            }
                            if (com.jd.lib.flexcube.layout.layout.a.g(f2, iWidget.getLayoutParamsWidth(), true) == -2) {
                                f2.setWidth(Float.NaN);
                            }
                            com.jd.lib.flexcube.layout.layout.a.g(f2, iWidget.getLayoutParamsHeight(), false);
                            if (iWidget.getLayoutParamsHeight() == -2) {
                                f2.setHeight(Float.NaN);
                            }
                        }
                    } else {
                        com.jd.lib.flexcube.layout.layout.a.c(f2);
                        if (iWidget instanceof FlexBoxView) {
                            com.jd.lib.flexcube.layout.layout.a.b(f2);
                        }
                        com.jd.lib.flexcube.layout.layout.a.a(f2);
                    }
                }
            }
            d(map, iWidgetCommunication);
            return;
        }
        clear();
    }
}
