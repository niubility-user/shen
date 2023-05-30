package com.jd.lib.flexcube.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jd.lib.babel.servicekit.imagekit.ImageLoad;
import com.jd.lib.flexcube.iwidget.b.a;
import com.jd.lib.flexcube.iwidget.b.c;
import com.jd.lib.flexcube.iwidget.entity.material.ClickEvent;
import com.jd.lib.flexcube.iwidget.entity.material.PaddingInfo;
import com.jd.lib.flexcube.iwidget.ui.IKnowWidget;
import com.jd.lib.flexcube.iwidget.ui.IWidget;
import com.jd.lib.flexcube.iwidget.ui.IWidgetCommunication;
import com.jd.lib.flexcube.widgets.b.b;
import com.jd.lib.flexcube.widgets.b.e;
import com.jd.lib.flexcube.widgets.c.a;
import com.jd.lib.flexcube.widgets.entity.GraphicEntity;
import com.jd.lib.flexcube.widgets.entity.TextEntity;
import com.jd.lib.flexcube.widgets.entity.text.GraphicConfig;
import com.jd.lib.flexcube.widgets.entity.text.GraphicDataPath;
import java.io.Serializable;
import java.util.Map;

/* loaded from: classes15.dex */
public class FlexGraphicView extends FrameLayout implements IWidget<GraphicEntity>, IKnowWidget<GraphicEntity> {

    /* renamed from: g  reason: collision with root package name */
    private LinearLayout f4444g;

    /* renamed from: h  reason: collision with root package name */
    private ImageView f4445h;

    /* renamed from: i  reason: collision with root package name */
    private e f4446i;

    /* renamed from: j  reason: collision with root package name */
    private ImageView f4447j;

    /* renamed from: k  reason: collision with root package name */
    private FlexTextView f4448k;

    /* renamed from: l  reason: collision with root package name */
    private GraphicEntity f4449l;

    /* renamed from: m  reason: collision with root package name */
    private String f4450m;

    /* renamed from: n  reason: collision with root package name */
    private int f4451n;
    private int o;

    public FlexGraphicView(Context context) {
        super(context);
        LinearLayout linearLayout = new LinearLayout(context);
        this.f4444g = linearLayout;
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.f4444g.setOrientation(0);
        addView(this.f4444g);
        this.f4446i = new e(this);
    }

    private void a(ImageView.ScaleType scaleType) {
        if (this.f4445h == null) {
            ImageView newImageView = ImageLoad.newImageView(getContext());
            this.f4445h = newImageView;
            newImageView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            addView(this.f4445h, 0);
        }
        this.f4445h.setScaleType(scaleType);
        if (this.f4445h.getParent() == null) {
            addView(this.f4445h, 0);
        }
    }

    private ImageView b() {
        ImageView newImageView = ImageLoad.newImageView(getContext());
        newImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return newImageView;
    }

    private FlexTextView c() {
        FlexTextView flexTextView = new FlexTextView(getContext());
        flexTextView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        flexTextView.g(true);
        return flexTextView;
    }

    private void e(GraphicConfig graphicConfig) {
        if ("2".equals(graphicConfig.xAlign)) {
            if ("2".equals(graphicConfig.yAlign)) {
                this.f4444g.setGravity(17);
            } else if ("3".equals(graphicConfig.yAlign)) {
                this.f4444g.setGravity(81);
            } else {
                this.f4444g.setGravity(1);
            }
        } else if ("3".equals(graphicConfig.xAlign)) {
            if ("2".equals(graphicConfig.yAlign)) {
                this.f4444g.setGravity(21);
            } else if ("3".equals(graphicConfig.yAlign)) {
                this.f4444g.setGravity(85);
            } else {
                this.f4444g.setGravity(5);
            }
        } else if ("2".equals(graphicConfig.yAlign)) {
            this.f4444g.setGravity(16);
        } else if ("3".equals(graphicConfig.yAlign)) {
            this.f4444g.setGravity(80);
        } else {
            this.f4444g.setGravity(48);
        }
    }

    private void f(GraphicConfig graphicConfig) {
        if (graphicConfig != null) {
            if ("1".equals(graphicConfig.bgType) && c.d(graphicConfig.bgImage) && !"1".equals(graphicConfig.autoFitType)) {
                a(ImageView.ScaleType.FIT_XY);
                CommonServiceUtil.displayImage(graphicConfig.bgImage, this.f4445h);
                return;
            }
            ImageView imageView = this.f4445h;
            if (imageView != null && imageView.getParent() != null) {
                removeView(this.f4445h);
            }
            setBackgroundColor(a.a(graphicConfig.bgColor, 0));
        }
    }

    private void g(GraphicConfig graphicConfig, float f2) {
        if (graphicConfig != null) {
            this.f4446i.j(graphicConfig.cfInfo, f2, getLayoutParamsHeight() >> 1);
        } else {
            this.f4446i.i(null, 0.0f);
        }
    }

    private void h(GraphicConfig graphicConfig, float f2) {
        GraphicEntity graphicEntity;
        if (graphicConfig != null && (graphicEntity = this.f4449l) != null && graphicEntity.getTextEntity() != null && this.f4449l.getTextEntity().config != null) {
            this.f4446i.m(graphicConfig.frameInfo, a.a(this.f4449l.getTextEntity().config.color, 0), f2);
        } else {
            this.f4446i.m(null, 0, 0.0f);
        }
    }

    private void i(Map<String, String> map, IWidgetCommunication iWidgetCommunication) {
        if (this.f4449l == null) {
            return;
        }
        Serializable serializable = iWidgetCommunication.getStateBundle().getSerializable(this.f4449l.dataPath.clickEvent);
        ClickEvent clickEvent = serializable instanceof ClickEvent ? (ClickEvent) serializable : null;
        if (clickEvent == null) {
            clickEvent = b.a(map, this.f4449l.dataPath.clickEvent);
        }
        if (clickEvent != null) {
            a.b bVar = new a.b(getContext(), clickEvent);
            bVar.a(iWidgetCommunication.getBabelScope());
            setOnClickListener(bVar.b());
            iWidgetCommunication.getStateBundle().putSerializable(this.f4449l.dataPath.clickEvent, clickEvent);
            return;
        }
        setClickable(false);
    }

    private void j(PaddingInfo paddingInfo, float f2) {
        FrameLayout.LayoutParams layoutParams;
        if (paddingInfo == null || (layoutParams = (FrameLayout.LayoutParams) this.f4444g.getLayoutParams()) == null) {
            return;
        }
        Rect padding = paddingInfo.getPadding(f2);
        layoutParams.leftMargin = padding.left;
        layoutParams.topMargin = padding.top;
        layoutParams.rightMargin = padding.right;
        layoutParams.bottomMargin = padding.bottom;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void clear() {
        setBackgroundColor(0);
        this.f4444g.removeAllViews();
        setClickable(false);
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IKnowWidget
    /* renamed from: d  reason: merged with bridge method [inline-methods] */
    public GraphicEntity getWidgetEntity() {
        return this.f4449l;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        this.f4446i.b(canvas);
        super.draw(canvas);
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsHeight() {
        return this.o;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsWidth() {
        if ("1".equals(this.f4450m)) {
            return -2;
        }
        return this.f4451n;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public void updateStyle(@NonNull GraphicEntity graphicEntity, float f2) {
        this.f4449l = graphicEntity;
        if (graphicEntity != null && graphicEntity.getConfig() != null) {
            this.f4450m = graphicEntity.getConfig().autoFitType;
            this.f4451n = com.jd.lib.flexcube.iwidget.b.b.d((int) graphicEntity.getConfig().w, f2);
            this.o = com.jd.lib.flexcube.iwidget.b.b.d((int) graphicEntity.getConfig().f4225h, f2);
            j(graphicEntity.getConfig().paddingInfo, f2);
            this.f4444g.removeAllViews();
            String str = graphicEntity.getConfig().imgTextOrder;
            str.hashCode();
            if (str.equals("1")) {
                this.f4447j = b();
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(graphicEntity.getConfig().getImgConfig().getW(f2), graphicEntity.getConfig().getImgConfig().getH(f2));
                layoutParams.rightMargin = (int) (graphicEntity.getConfig().imgTextDistance * f2);
                this.f4444g.addView(this.f4447j, layoutParams);
                FlexTextView c2 = c();
                this.f4448k = c2;
                this.f4444g.addView(c2);
            } else if (!str.equals("2")) {
                FlexTextView c3 = c();
                this.f4448k = c3;
                this.f4444g.addView(c3);
            } else {
                FlexTextView c4 = c();
                this.f4448k = c4;
                this.f4444g.addView(c4);
                this.f4447j = b();
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(graphicEntity.getConfig().getImgConfig().getW(f2), graphicEntity.getConfig().getImgConfig().getH(f2));
                layoutParams2.leftMargin = (int) (graphicEntity.getConfig().imgTextDistance * f2);
                this.f4444g.addView(this.f4447j, layoutParams2);
            }
            e(graphicEntity.getConfig());
            f(graphicEntity.getConfig());
            g(graphicEntity.getConfig(), f2);
            h(graphicEntity.getConfig(), f2);
            TextEntity textEntity = graphicEntity.getTextEntity();
            if (textEntity != null) {
                this.f4448k.updateStyle(textEntity, f2);
            }
            int f3 = com.jd.lib.flexcube.iwidget.b.b.f(graphicEntity.getConfig().getTextConfig().maxRowNum, 1);
            if (f3 > 0) {
                this.f4448k.setMaxLines(f3);
                return;
            }
            return;
        }
        clear();
    }

    @Override // android.view.View
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        this.f4446i.h(canvas);
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void updateContent(@NonNull Map<String, String> map, IWidgetCommunication iWidgetCommunication) {
        GraphicDataPath graphicDataPath;
        GraphicEntity graphicEntity;
        GraphicConfig graphicConfig;
        GraphicEntity graphicEntity2 = this.f4449l;
        if (graphicEntity2 != null && (graphicDataPath = graphicEntity2.dataPath) != null) {
            String d = b.d(map, graphicDataPath.text);
            String d2 = b.d(map, this.f4449l.dataPath.degradeSrc);
            if (!c.d(d) && !c.d(d2)) {
                setVisibility(8);
                return;
            }
            setVisibility(0);
            if (c.d(d)) {
                this.f4444g.setVisibility(0);
                ImageView imageView = this.f4445h;
                if (imageView != null && imageView.getScaleType() != ImageView.ScaleType.FIT_XY && (graphicEntity = this.f4449l) != null && (graphicConfig = graphicEntity.config) != null) {
                    f(graphicConfig);
                }
                this.f4448k.updateContent(map, iWidgetCommunication);
                ImageView imageView2 = this.f4447j;
                if (imageView2 != null && imageView2.getParent() != null) {
                    String d3 = b.d(map, this.f4449l.dataPath.src);
                    if (c.d(d3)) {
                        this.f4447j.setVisibility(0);
                        ImageLoad.with(this.f4447j).needImageOnFail(false).load(d3);
                    } else {
                        this.f4447j.setVisibility(8);
                    }
                }
            } else if (c.d(d2)) {
                this.f4444g.setVisibility(8);
                a(ImageView.ScaleType.FIT_CENTER);
                CommonServiceUtil.displayImage(d2, this.f4445h);
            }
            if (TextUtils.isEmpty(this.f4449l.dataPath.clickEvent)) {
                setClickable(false);
                return;
            } else {
                i(map, iWidgetCommunication);
                return;
            }
        }
        clear();
    }
}
