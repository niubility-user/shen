package com.jd.lib.flexcube.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.entity.PageInfo;
import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jd.lib.babel.servicekit.imagekit.ImageLoad;
import com.jd.lib.babel.servicekit.model.BaseEvent;
import com.jd.lib.babel.servicekit.model.MtaData;
import com.jd.lib.babel.task.viewkit.EventModelKey;
import com.jd.lib.flexcube.R;
import com.jd.lib.flexcube.canvas.entity.CanvasConfig;
import com.jd.lib.flexcube.canvas.entity.common.BgInfo;
import com.jd.lib.flexcube.help.FlexAutoPlayInterface;
import com.jd.lib.flexcube.help.IExpoInterface;
import com.jd.lib.flexcube.help.MsgActionImp;
import com.jd.lib.flexcube.help.MsgActionInterface;
import com.jd.lib.flexcube.help.MsgInterface;
import com.jd.lib.flexcube.iwidget.b.a;
import com.jd.lib.flexcube.iwidget.b.c;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jd.lib.flexcube.iwidget.entity.material.ClickEvent;
import com.jd.lib.flexcube.iwidget.entity.material.ExposureInfo;
import com.jd.lib.flexcube.iwidget.entity.material.MaterialModel;
import com.jd.lib.flexcube.iwidget.ui.IWidget;
import com.jd.lib.flexcube.iwidget.ui.IWidgetCommunication;
import com.jd.lib.flexcube.pool.WidgetPool;
import com.jd.lib.flexcube.widgets.FlexBoxView;
import com.jd.lib.flexcube.widgets.FlexSubView;
import com.jd.lib.flexcube.widgets.FlexVideoView;
import com.jd.lib.flexcube.widgets.b.e;
import com.jd.lib.flexcube.widgets.c.a;
import com.jd.lib.flexcube.widgets.entity.common.CfInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class FlexViewGroup extends ViewGroup implements IWidgetCommunication, FlexAutoPlayInterface, MsgInterface {

    /* renamed from: g  reason: collision with root package name */
    private List<IWidget> f4215g;

    /* renamed from: h  reason: collision with root package name */
    private List<Rect> f4216h;

    /* renamed from: i  reason: collision with root package name */
    private Context f4217i;

    /* renamed from: j  reason: collision with root package name */
    private e f4218j;

    /* renamed from: k  reason: collision with root package name */
    private ImageView f4219k;

    /* renamed from: l  reason: collision with root package name */
    private String f4220l;

    /* renamed from: m  reason: collision with root package name */
    public float f4221m;

    /* renamed from: n  reason: collision with root package name */
    private MaterialModel f4222n;
    private BabelScope o;
    private boolean p;
    private boolean q;

    public FlexViewGroup(Context context) {
        super(context);
        this.f4215g = new ArrayList();
        this.f4216h = new ArrayList();
        this.p = true;
        this.q = false;
        this.f4217i = context;
        ImageView newImageView = ImageLoad.newImageView(context);
        this.f4219k = newImageView;
        newImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.f4218j = new e(this);
    }

    private void b() {
        for (IWidget iWidget : this.f4215g) {
            if (iWidget instanceof View) {
                iWidget.clear();
                WidgetPool.e((View) iWidget);
            }
        }
        this.f4215g.clear();
    }

    private void c() {
        setLayoutParams(new FrameLayout.LayoutParams(-1, 1));
    }

    private void j(BabelScope babelScope, BgInfo bgInfo) {
        PageInfo pageInfo;
        int i2 = 0;
        if (bgInfo != null && babelScope != null) {
            if ("1".equals(bgInfo.isGradient) && !TextUtils.isEmpty(bgInfo.gradientColor)) {
                int[] iArr = {a.a(bgInfo.color, 0), a.a(bgInfo.gradientColor, 0)};
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setShape(0);
                gradientDrawable.setColors(iArr);
                gradientDrawable.setGradientType(0);
                if (!TextUtils.isEmpty(bgInfo.gradientColorDirection) && !"0".equals(bgInfo.gradientColorDirection)) {
                    if ("1".equals(bgInfo.gradientColorDirection)) {
                        gradientDrawable.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
                    } else if ("2".equals(bgInfo.gradientColorDirection)) {
                        gradientDrawable.setOrientation(GradientDrawable.Orientation.TL_BR);
                    } else {
                        gradientDrawable.setOrientation(GradientDrawable.Orientation.TR_BL);
                    }
                } else {
                    gradientDrawable.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
                }
                gradientDrawable.setDither(true);
                gradientDrawable.setSize(20, 20);
                this.f4219k.setBackground(gradientDrawable);
                return;
            } else if ("1".equals(bgInfo.type) && c.d(bgInfo.imgUrl)) {
                setBackgroundColor(0);
                this.f4219k.setVisibility(0);
                this.f4219k.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                ImageLoad.with(this.f4219k).load(bgInfo.imgUrl);
                return;
            } else if ("1".equals(bgInfo.sameColor)) {
                if (babelScope != null && (pageInfo = babelScope.pageInfo) != null) {
                    i2 = a.a(pageInfo.pageBgColor, 0);
                }
                this.f4219k.setVisibility(8);
                setBackgroundColor(i2);
                return;
            } else if ("0".equals(bgInfo.type)) {
                this.f4219k.setVisibility(8);
                setBackgroundColor(a.a(bgInfo.color, 0));
                return;
            } else {
                this.f4219k.setVisibility(8);
                setBackgroundColor(0);
                return;
            }
        }
        this.f4219k.setVisibility(8);
        setBackgroundColor(0);
    }

    public void a(@NonNull List<BaseWidgetEntity> list, float f2, String str) {
        if (!TextUtils.isEmpty(this.f4220l) && this.f4220l.equals(str)) {
            float f3 = this.f4221m;
            if (f3 > 0.0f && f3 == f2) {
                return;
            }
        }
        this.f4220l = str;
        this.f4221m = f2;
        removeAllViews();
        b();
        this.f4216h.clear();
        if (list == null || list.isEmpty()) {
            return;
        }
        addView(this.f4219k);
        this.f4216h.add(new Rect());
        for (BaseWidgetEntity baseWidgetEntity : list) {
            if (baseWidgetEntity != null) {
                View a = WidgetPool.a(baseWidgetEntity.type, this.f4217i);
                if (a instanceof IWidget) {
                    a.setTag(R.id.type, baseWidgetEntity.type);
                    Rect a2 = com.jd.lib.flexcube.widgets.b.c.a(baseWidgetEntity, this.f4221m);
                    if (a2 != null) {
                        IWidget iWidget = (IWidget) a;
                        iWidget.updateStyle(baseWidgetEntity, this.f4221m);
                        this.f4215g.add(iWidget);
                        if (this.q) {
                            ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(iWidget.getLayoutParamsWidth(), iWidget.getLayoutParamsHeight());
                            marginLayoutParams.leftMargin = a2.left;
                            marginLayoutParams.topMargin = a2.top;
                            a.setLayoutParams(marginLayoutParams);
                        } else {
                            a.setLayoutParams(new ViewGroup.LayoutParams(iWidget.getLayoutParamsWidth(), iWidget.getLayoutParamsHeight()));
                        }
                        addView(a);
                        this.f4216h.add(a2);
                    }
                }
            }
        }
    }

    @Override // com.jd.lib.flexcube.help.FlexAutoPlayInterface
    public boolean autoPlay(boolean z, boolean z2) {
        boolean z3 = false;
        try {
            List<IWidget> list = this.f4215g;
            if (list != null && list.size() > 0) {
                for (IWidget iWidget : this.f4215g) {
                    if (iWidget instanceof FlexVideoView) {
                        z3 = ((FlexVideoView) iWidget).h();
                    } else if (iWidget instanceof FlexSubView) {
                        z3 = ((FlexSubView) iWidget).autoPlay(z, z2);
                    } else if (iWidget instanceof FlexBoxView) {
                        z3 = ((FlexBoxView) iWidget).autoPlay(z, z2);
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

    public void d() {
        ExposureInfo exposureInfo;
        BabelScope babelScope;
        MaterialModel materialModel = this.f4222n;
        if (materialModel == null || (exposureInfo = materialModel.exposureInfo) == null || (babelScope = this.o) == null) {
            return;
        }
        if (babelScope.iFloorUI != null && !TextUtils.isEmpty(exposureInfo.eventId) && !TextUtils.isEmpty(exposureInfo.srv)) {
            try {
                this.o.iFloorUI.sendExposureData(MtaData.Builder.from(exposureInfo.eventId, exposureInfo.srv).jsonParam(exposureInfo.srvData).split(true).build());
            } catch (Exception unused) {
            }
        }
        BabelScope babelScope2 = this.o;
        IExpoInterface iExpoInterface = babelScope2 != null ? (IExpoInterface) babelScope2.getService(IExpoInterface.class) : null;
        if (iExpoInterface != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("eventId", exposureInfo.eventId);
                jSONObject.put("expo", exposureInfo.expo);
                jSONObject.put("expoJson", exposureInfo.expoJson);
                jSONObject.put(EventModelKey.SRV, exposureInfo.srv);
                iExpoInterface.sendExposureData(jSONObject);
            } catch (Exception unused2) {
            }
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        this.f4218j.b(canvas);
        super.draw(canvas);
    }

    public void e(CfInfo cfInfo, float f2) {
        this.f4218j.i(cfInfo, f2);
    }

    public void f(boolean z) {
        this.p = z;
    }

    public void g(boolean z) {
        this.q = z;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidgetCommunication
    public BabelScope getBabelScope() {
        return this.o;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidgetCommunication
    public MaterialModel getMaterialModel() {
        return this.f4222n;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidgetCommunication
    public String getPageName() {
        BabelScope babelScope = this.o;
        return babelScope != null ? babelScope.getPageName() : "";
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidgetCommunication
    public String getPageParam() {
        BabelScope babelScope = this.o;
        return babelScope != null ? babelScope.getPageParam() : "";
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidgetCommunication
    public Bundle getStateBundle() {
        MaterialModel materialModel = this.f4222n;
        if (materialModel != null) {
            return materialModel.getStateBundle();
        }
        return null;
    }

    public void h(@NonNull MaterialModel materialModel, @NonNull BabelScope babelScope) {
        if (materialModel == null) {
            return;
        }
        this.f4222n = materialModel;
        this.o = babelScope;
        HashMap<String, String> hashMap = materialModel.flexData;
        if (hashMap != null) {
            Iterator<IWidget> it = this.f4215g.iterator();
            while (it.hasNext()) {
                it.next().updateContent(hashMap, this);
            }
        }
        ClickEvent clickEvent = materialModel.clickEvent;
        if (clickEvent != null) {
            a.b bVar = new a.b(this.f4217i, clickEvent);
            bVar.a(this.o);
            setOnClickListener(bVar.b());
        } else {
            setOnClickListener(null);
        }
        if (this.p) {
            d();
        }
    }

    public void i(BabelScope babelScope, CanvasConfig canvasConfig, float f2) {
        this.o = babelScope;
        if (canvasConfig != null && canvasConfig.w >= 1) {
            j(babelScope, canvasConfig.bgInfo);
            e(canvasConfig.cfInfo, f2);
            return;
        }
        c();
    }

    @Override // android.view.View
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        this.f4218j.h(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        List<Rect> list;
        int i6;
        int measuredHeight;
        int i7;
        int i8;
        int childCount = getChildCount();
        if (childCount <= 0 || (list = this.f4216h) == null || childCount != list.size()) {
            return;
        }
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            ImageView imageView = this.f4219k;
            if (childAt == imageView) {
                imageView.layout(0, 0, i4 - i2, i5 - i3);
            } else {
                Rect rect = this.f4216h.get(i9);
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    i6 = marginLayoutParams.leftMargin;
                    i7 = marginLayoutParams.topMargin;
                    int i10 = layoutParams.width;
                    if (i10 <= 0) {
                        i10 = childAt.getMeasuredWidth();
                    }
                    i8 = i10 + i6;
                    int i11 = layoutParams.height;
                    if (i11 <= 0) {
                        i11 = childAt.getMeasuredHeight();
                    }
                    measuredHeight = i11 + i7;
                } else {
                    i6 = rect.left;
                    int i12 = rect.top;
                    int measuredWidth = (layoutParams == null || layoutParams.width > 0) ? rect.right : childAt.getMeasuredWidth() + rect.left;
                    measuredHeight = (layoutParams == null || layoutParams.height > 0) ? rect.bottom : childAt.getMeasuredHeight() + rect.top;
                    i7 = i12;
                    i8 = measuredWidth;
                }
                childAt.layout(i6, i7, i8, measuredHeight);
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        setMeasuredDimension(View.MeasureSpec.getSize(i2), View.MeasureSpec.getSize(i3));
        measureChildren(0, 0);
    }

    @Override // com.jd.lib.flexcube.help.MsgInterface
    public void pushAction(Class<? extends MsgActionInterface> cls, Object obj) {
        MaterialModel materialModel;
        if (this.f4215g == null) {
            return;
        }
        if ((!(obj instanceof MsgActionImp) || obj == null) && (obj instanceof MsgActionInterface) && obj != null && (materialModel = this.f4222n) != null && materialModel.exposureInfo != null) {
            try {
                ArrayList arrayList = new ArrayList();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("eventId", this.f4222n.exposureInfo.eventId);
                jSONObject.put("expo", this.f4222n.exposureInfo.expo);
                jSONObject.put("expoJson", this.f4222n.exposureInfo.expoJson);
                jSONObject.put(EventModelKey.SRV, this.f4222n.exposureInfo.srv);
                arrayList.add(jSONObject);
                cls.cast(obj).handleAction(arrayList);
            } catch (Exception unused) {
            }
        }
        for (IWidget iWidget : this.f4215g) {
            if (iWidget instanceof MsgInterface) {
                ((MsgInterface) iWidget).pushAction(cls, obj);
            }
        }
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidgetCommunication
    public void sendEvent(BaseEvent baseEvent) {
        MaterialModel materialModel;
        ClickEvent clickEvent;
        if (baseEvent == null || TextUtils.isEmpty(baseEvent.getType())) {
            return;
        }
        String type = baseEvent.getType();
        type.hashCode();
        if (type.equals("jumpToNext") && (materialModel = this.f4222n) != null && (clickEvent = materialModel.clickEvent) != null && "jump".equals(clickEvent.type)) {
            CommonServiceUtil.execJump(this.f4217i, this.f4222n.clickEvent.jump);
        }
    }

    @Override // android.view.View
    public void setScaleX(float f2) {
        super.setScaleX(f2);
        this.f4218j.n(f2);
        if (Build.VERSION.SDK_INT < 24) {
            this.f4218j.k(0.0f);
        }
    }

    @Override // android.view.View
    public void setScaleY(float f2) {
        super.setScaleY(f2);
        this.f4218j.o(f2);
        if (Build.VERSION.SDK_INT < 24) {
            this.f4218j.k(0.0f);
        }
    }
}
