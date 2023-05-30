package com.jd.lib.flexcube.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jd.lib.babel.servicekit.imagekit.BabelImageKitServer;
import com.jd.lib.babel.servicekit.imagekit.ImageArr;
import com.jd.lib.babel.servicekit.imagekit.ImageLoad;
import com.jd.lib.flexcube.help.MsgActionImp;
import com.jd.lib.flexcube.help.MsgActionInterface;
import com.jd.lib.flexcube.help.MsgInterface;
import com.jd.lib.flexcube.iwidget.b.a;
import com.jd.lib.flexcube.iwidget.b.c;
import com.jd.lib.flexcube.iwidget.entity.material.ClickEvent;
import com.jd.lib.flexcube.iwidget.ui.IKnowWidget;
import com.jd.lib.flexcube.iwidget.ui.IWidget;
import com.jd.lib.flexcube.iwidget.ui.IWidgetCommunication;
import com.jd.lib.flexcube.widgets.b.b;
import com.jd.lib.flexcube.widgets.b.e;
import com.jd.lib.flexcube.widgets.c.a;
import com.jd.lib.flexcube.widgets.entity.ImageEntity;
import com.jd.lib.flexcube.widgets.entity.text.ImageConfig;
import com.jd.lib.flexcube.widgets.entity.text.ImageDataPath;
import java.io.Serializable;
import java.util.Map;

/* loaded from: classes15.dex */
public class FlexImageView extends FrameLayout implements IWidget<ImageEntity>, IKnowWidget<ImageEntity>, MsgInterface {

    /* renamed from: g */
    protected ImageView f4452g;

    /* renamed from: h */
    protected ImageEntity f4453h;

    /* renamed from: i */
    protected float f4454i;

    /* renamed from: j */
    protected String f4455j;

    /* renamed from: k */
    protected e f4456k;

    /* renamed from: l */
    protected int f4457l;

    /* renamed from: m */
    private boolean f4458m;

    /* renamed from: n */
    private String f4459n;
    private String o;
    private String p;
    private int q;
    private int r;

    public FlexImageView(Context context) {
        super(context);
        this.f4456k = new e(this);
        ImageView newImageView = ImageLoad.newImageView(context);
        this.f4452g = newImageView;
        addView(newImageView);
    }

    private int a(@NonNull Map map, String str, int i2) {
        if (!TextUtils.isEmpty(str) && this.f4453h.config != null) {
            String d = b.d(map, str);
            if (!TextUtils.isEmpty(d)) {
                return a.a(d, 0);
            }
        }
        return i2;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IKnowWidget
    /* renamed from: b */
    public ImageEntity getWidgetEntity() {
        return this.f4453h;
    }

    public void c(@NonNull Map map) {
        this.q = a(map, this.o, this.q);
        this.r = a(map, this.p, this.r);
        this.f4459n = e() ? this.p : this.o;
        this.f4457l = e() ? this.r : this.q;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void clear() {
        this.f4452g.setImageDrawable(null);
        setBackgroundDrawable(null);
        setClickable(false);
    }

    public void d() {
        ImageConfig imageConfig;
        ImageEntity imageEntity = this.f4453h;
        if (imageEntity == null || (imageConfig = imageEntity.config) == null) {
            return;
        }
        if (!TextUtils.isEmpty(imageConfig.darkColor) && this.f4453h.config.darkColor.startsWith("$")) {
            this.p = this.f4453h.config.darkColor;
        } else {
            this.r = a.a(this.f4453h.config.darkColor, 0);
        }
        if (!TextUtils.isEmpty(this.f4453h.config.bgColor) && this.f4453h.config.bgColor.startsWith("$")) {
            this.o = this.f4453h.config.bgColor;
        } else {
            this.q = a.a(this.f4453h.config.bgColor, 0);
        }
        this.f4459n = e() ? this.p : this.o;
        this.f4457l = e() ? this.r : this.q;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        this.f4456k.b(canvas);
        super.draw(canvas);
    }

    public boolean e() {
        ImageConfig imageConfig;
        ImageEntity imageEntity = this.f4453h;
        return (imageEntity == null || (imageConfig = imageEntity.config) == null || !this.f4458m || TextUtils.isEmpty(imageConfig.darkColor)) ? false : true;
    }

    public boolean f() {
        ImageConfig imageConfig;
        ImageEntity imageEntity = this.f4453h;
        return (imageEntity == null || (imageConfig = imageEntity.config) == null || TextUtils.isEmpty(imageConfig.darkColor)) ? false : true;
    }

    public void g(Map<String, String> map, IWidgetCommunication iWidgetCommunication) {
        Serializable serializable = iWidgetCommunication.getStateBundle().getSerializable(this.f4453h.dataPath.clickEvent);
        ClickEvent clickEvent = serializable instanceof ClickEvent ? (ClickEvent) serializable : null;
        if (clickEvent == null) {
            clickEvent = b.a(map, this.f4453h.dataPath.clickEvent);
        }
        if (clickEvent != null) {
            a.b bVar = new a.b(getContext(), clickEvent);
            bVar.a(iWidgetCommunication.getBabelScope());
            setOnClickListener(bVar.b());
            iWidgetCommunication.getStateBundle().putSerializable(this.f4453h.dataPath.clickEvent, clickEvent);
            return;
        }
        setClickable(false);
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsHeight() {
        ImageEntity imageEntity = this.f4453h;
        if (imageEntity == null || imageEntity.getConfig() == null) {
            return 0;
        }
        return this.f4453h.getConfig().getH(this.f4454i);
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsWidth() {
        ImageEntity imageEntity = this.f4453h;
        if (imageEntity == null || imageEntity.getConfig() == null) {
            return 0;
        }
        return this.f4453h.getConfig().getW(this.f4454i);
    }

    public void h(boolean z) {
        if (this.f4458m == z) {
            return;
        }
        this.f4458m = z;
        this.f4459n = e() ? this.p : this.o;
        int i2 = e() ? this.r : this.q;
        this.f4457l = i2;
        setBackgroundColor(i2);
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    /* renamed from: i */
    public void updateStyle(@NonNull ImageEntity imageEntity, float f2) {
        this.f4453h = imageEntity;
        this.f4454i = f2;
        if (imageEntity != null) {
            ImageConfig imageConfig = imageEntity.config;
            if (imageConfig != null) {
                d();
                if (TextUtils.isEmpty(this.f4459n)) {
                    setBackgroundColor(this.f4457l);
                }
                if (this.f4452g != null) {
                    if ("1".equals(imageConfig.autoFitType)) {
                        this.f4452g.setScaleType(ImageView.ScaleType.FIT_XY);
                    } else if ("2".equals(imageConfig.autoFitType)) {
                        this.f4452g.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    } else {
                        this.f4452g.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    }
                }
                this.f4456k.j(imageConfig.cfInfo, f2, getLayoutParamsHeight() >> 1);
            }
            ImageDataPath imageDataPath = imageEntity.dataPath;
            if (imageDataPath != null) {
                String str = imageDataPath.src;
                if (c.d(str)) {
                    this.f4455j = str;
                } else {
                    this.f4455j = null;
                }
            }
        }
    }

    @Override // com.jd.lib.flexcube.help.MsgInterface
    public void pushAction(Class<? extends MsgActionInterface> cls, Object obj) {
        if (!(obj instanceof MsgActionImp) || obj == null) {
            return;
        }
        try {
            if ("darkModeChange".equals((String) ((MsgActionImp) obj).hashMap.get("type"))) {
                h(((Boolean) ((MsgActionImp) obj).hashMap.get("darkMode")).booleanValue());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void updateContent(@NonNull Map map, IWidgetCommunication iWidgetCommunication) {
        ImageEntity imageEntity = this.f4453h;
        if (imageEntity != null && imageEntity.dataPath != null) {
            String d = b.d(map, this.f4455j);
            if (c.d(d)) {
                setVisibility(0);
                BabelImageKitServer babelImageKitServer = (iWidgetCommunication == null || iWidgetCommunication.getBabelScope() == null) ? null : (BabelImageKitServer) iWidgetCommunication.getBabelScope().getService(BabelImageKitServer.class);
                if (babelImageKitServer != null) {
                    babelImageKitServer.displayImage(ImageArr.Builder.create(this.f4452g).setUrl(d).setNeedImageOnFail(true).setNeedImageOnLoading(true).setScale(false).buid());
                } else {
                    CommonServiceUtil.displayImageWithScale(d, this.f4452g, false);
                }
                this.f4458m = iWidgetCommunication.getBabelScope() == null ? false : iWidgetCommunication.getBabelScope().isDark();
                c(map);
                if (f()) {
                    setBackgroundColor(this.f4457l);
                } else if (!TextUtils.isEmpty(this.f4459n)) {
                    setBackgroundColor(this.f4457l);
                }
                if (TextUtils.isEmpty(this.f4453h.dataPath.clickEvent)) {
                    setClickable(false);
                    return;
                } else {
                    g(map, iWidgetCommunication);
                    return;
                }
            }
            setVisibility(8);
            clear();
            return;
        }
        clear();
    }
}
