package com.jingdong.app.mall.home.r.f.a;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Pair;
import com.facebook.react.animated.InterpolationAnimatedNode;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.model.entity.IconFloorEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.IconFloorEngine;
import com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter;
import com.jingdong.app.mall.home.floor.view.view.MallFloorIcon;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.JsonParser;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class o extends com.jingdong.app.mall.home.r.f.a.b<IconFloorEntity, IconFloorEngine, MallFloorIcon> implements ICursorContentViewPresenter {

    /* renamed from: h */
    private volatile String f10775h;

    /* renamed from: i */
    private Pair<String, Bitmap> f10776i;

    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.t.a {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        a(String str, String str2) {
            o.this = r1;
            this.a = str;
            this.b = str2;
        }

        @Override // com.jingdong.app.mall.home.t.a
        public void onBitmapWithUiNull(Bitmap bitmap) {
            if (o.this.f10775h.compareToIgnoreCase(this.a) != 0) {
                return;
            }
            try {
                o.this.Q0();
                if (bitmap == null) {
                    o.this.O0(null, null);
                } else {
                    o.this.f10776i = new Pair(this.b, bitmap);
                    bitmap.setHasAlpha(true);
                    o.this.N0(this.a, bitmap);
                }
            } catch (Throwable th) {
                com.jingdong.app.mall.home.o.a.f.s0(this, th);
            }
        }
    }

    /* loaded from: classes4.dex */
    public class b implements HttpGroup.OnCommonListener {

        /* loaded from: classes4.dex */
        class a extends com.jingdong.app.mall.home.o.a.b {

            /* renamed from: g */
            final /* synthetic */ com.jingdong.app.mall.home.r.e.h f10779g;

            a(com.jingdong.app.mall.home.r.e.h hVar) {
                b.this = r1;
                this.f10779g = hVar;
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            public void safeRun() {
                MallFloorIcon mallFloorIcon = (MallFloorIcon) o.this.c();
                if (mallFloorIcon == null) {
                    return;
                }
                mallFloorIcon.beforeNewAppCenterInfoParse();
                o oVar = o.this;
                ((IconFloorEngine) oVar.f10738e).i(this.f10779g, (IconFloorEntity) oVar.d, false, true);
                mallFloorIcon.onRefreshViewInMainThread(false);
            }
        }

        b() {
            o.this = r1;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            JDJSONObject fastJsonObject;
            if (httpResponse == null || (fastJsonObject = httpResponse.getFastJsonObject()) == null) {
                return;
            }
            JDJSONObject jSONObject = fastJsonObject.getJSONObject("result");
            String string = fastJsonObject.getString("code");
            if (jSONObject == null || !string.equals("0") || jSONObject.isEmpty()) {
                return;
            }
            com.jingdong.app.mall.home.o.a.f.E0(new a(new com.jingdong.app.mall.home.r.e.h(jSONObject)));
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    public o(Class<IconFloorEntity> cls, Class<IconFloorEngine> cls2) {
        super(cls, cls2);
        this.f10775h = "";
    }

    private boolean L0() {
        return !((IconFloorEntity) this.d).isElder();
    }

    public void N0(String str, Bitmap bitmap) {
        MallFloorIcon mallFloorIcon = (MallFloorIcon) c();
        if (mallFloorIcon == null) {
            return;
        }
        mallFloorIcon.onLoadingBgComplete(str, bitmap);
    }

    public void O0(String str, JDFailReason jDFailReason) {
        MallFloorIcon mallFloorIcon = (MallFloorIcon) c();
        if (mallFloorIcon == null) {
            return;
        }
        mallFloorIcon.onLoadingBgFailed(str, jDFailReason);
    }

    public synchronized void Q0() {
        Object obj;
        Pair<String, Bitmap> pair = this.f10776i;
        if (pair != null && (obj = pair.second) != null && !((Bitmap) obj).isRecycled()) {
            ((Bitmap) this.f10776i.second).recycle();
        }
    }

    private void S0() {
        MallFloorIcon mallFloorIcon = (MallFloorIcon) c();
        if (mallFloorIcon == null) {
            return;
        }
        if (!L0()) {
            mallFloorIcon.onLoadingBgFailed(null, null);
            return;
        }
        String b2 = com.jingdong.app.mall.home.m.a.b(((IconFloorEntity) this.d).getBgUrl());
        this.f10775h = b2;
        if (!TextUtils.isEmpty(this.f10775h) && ((IconFloorEntity) this.d).getShowLines() == 2) {
            String md5 = Md5Encrypt.md5(this.f10775h);
            Bitmap X = X(md5);
            if (X != null && !X.isRecycled()) {
                X.setHasAlpha(true);
                N0(b2, X);
                return;
            }
            com.jingdong.app.mall.home.floor.ctrl.f.i(b2, new a(b2, md5));
        } else if (this.f10775h.compareToIgnoreCase(b2) != 0) {
        } else {
            Q0();
            O0(null, null);
        }
    }

    private boolean U0() {
        MallFloorIcon mallFloorIcon = (MallFloorIcon) c();
        return mallFloorIcon != null && mallFloorIcon.useDarkTextColor();
    }

    private Bitmap X(String str) {
        Object obj;
        Pair<String, Bitmap> pair = this.f10776i;
        if (pair == null || (obj = pair.second) == null || ((Bitmap) obj).isRecycled()) {
            return null;
        }
        if (TextUtils.isEmpty(str) || str.equals(this.f10776i.first)) {
            return (Bitmap) this.f10776i.second;
        }
        return null;
    }

    private String s0() {
        JSONObject jSONObject = new JSONObject();
        com.jingdong.app.mall.home.o.a.f.y(jSONObject);
        try {
            String u = com.jingdong.app.mall.home.o.a.f.u("HOMEPOZ", "");
            if (!TextUtils.isEmpty(u)) {
                try {
                    jSONObject.put("poz", new JSONObject(u));
                } catch (Exception e2) {
                    com.jingdong.app.mall.home.o.a.f.s0(this, e2);
                }
            }
            jSONObject.put("fQueryStamp", com.jingdong.app.mall.home.b.f8602m + "");
            jSONObject.put(InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY);
            jSONObject.put("allLastTime", com.jingdong.app.mall.home.o.a.f.u("APP_CENTER_UPDATETIME", "0"));
            jSONObject.put("tSTimes", String.valueOf(com.jingdong.app.mall.home.tips.c.j()));
            jSONObject.put("fringe", "1");
            jSONObject.put("barHeight", String.valueOf(com.jingdong.app.mall.home.floor.ctrl.h.A));
            jSONObject.put("cycFirstTimeStamp", com.jingdong.app.mall.home.o.a.f.u("HOME_CYCFIRSTTIMESTAMP", ""));
            jSONObject.put("cycNum", com.jingdong.app.mall.home.o.a.f.t("HOME_CYCNUM", 0));
            jSONObject.put("displayVersion", com.jingdong.app.mall.home.a.k());
            jSONObject.put("homeAreaCode", String.valueOf(com.jingdong.app.mall.home.u.a.w().v()));
            jSONObject.put("showLines", ((IconFloorEntity) this.d).getShowLines());
            jSONObject.put("scrollType", ((IconFloorEntity) this.d).getScrollType());
            jSONObject.put("showBi", ((IconFloorEntity) this.d).getShowBi());
            jSONObject.put("userCategory", com.jingdong.app.mall.home.floor.common.i.l.f());
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
        return jSONObject.toString();
    }

    public boolean A0() {
        return ((IconFloorEntity) this.d).isDoubleScroll();
    }

    public boolean B0() {
        return ((IconFloorEntity) this.d).isElder();
    }

    public boolean C0() {
        return ((IconFloorEntity) this.d).isHigh();
    }

    public boolean D0() {
        return ((IconFloorEntity) this.d).isIconDataValid();
    }

    public boolean E0() {
        MallFloorIcon mallFloorIcon = (MallFloorIcon) c();
        return mallFloorIcon != null && mallFloorIcon.isLineIcon();
    }

    public boolean F0() {
        return ((IconFloorEntity) this.d).getIsNeedUpdate();
    }

    public boolean G0() {
        return ((IconFloorEntity) this.d).isNeedUpdateView();
    }

    public boolean H0() {
        return ((IconFloorEntity) this.d).isNewPointerStyle();
    }

    public boolean I0() {
        return ((IconFloorEntity) this.d).isPlayLottie();
    }

    public boolean J0() {
        return ((IconFloorEntity) this.d).isSingleScroll();
    }

    public boolean K0() {
        return ((IconFloorEntity) this.d).isSpread();
    }

    public boolean M0() {
        return ((IconFloorEntity) this.d).isSupportLabel();
    }

    public void P0() {
        MallFloorIcon mallFloorIcon = (MallFloorIcon) c();
        if (mallFloorIcon == null) {
            return;
        }
        mallFloorIcon.onLoadView(true);
    }

    public void R0() {
        if (com.jingdong.app.mall.home.o.a.f.j0()) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setFunctionId("newAppCenterInfo");
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setPost(true);
        httpSetting.setEffect(0);
        String s0 = s0();
        if (!TextUtils.isEmpty(s0)) {
            httpSetting.setJsonParams(JsonParser.parseParamsJsonFromString(s0));
        }
        httpSetting.setListener(new b());
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public boolean T0() {
        return 1 == ((IconFloorEntity) this.d).getClipType();
    }

    public com.jingdong.app.mall.home.r.e.k.c U(int i2) {
        return ((IconFloorEntity) this.d).getAppEntryByPos(i2);
    }

    public int V() {
        return ((IconFloorEntity) this.d).getBgColor();
    }

    public int W() {
        return ((IconFloorEntity) this.d).getBgShadeColor();
    }

    public int Y() {
        int secondPageRows = ((IconFloorEntity) this.d).getSecondPageRows();
        if (secondPageRows <= 2) {
            return 0;
        }
        return com.jingdong.app.mall.home.floor.common.d.d((secondPageRows - 2) * 118);
    }

    public int Z() {
        return ((IconFloorEntity) this.d).getGridViewLeftRightPadding();
    }

    public int a0() {
        return ((IconFloorEntity) this.d).getGridViewTopPadding();
    }

    public int b0() {
        return ((IconFloorEntity) this.d).getHoldScreenType();
    }

    public int c0() {
        return ((IconFloorEntity) this.d).getIconFloorStyle();
    }

    public com.jingdong.app.mall.home.r.e.k.c d0(int i2, int i3) {
        return ((IconFloorEntity) this.d).getIconData(i2, i3);
    }

    public int e0() {
        return ((IconFloorEntity) this.d).getIconPadding();
    }

    public int f0() {
        return ((IconFloorEntity) this.d).getIconRealCount();
    }

    public int g0(int i2) {
        if (E0()) {
            return f0();
        }
        return ((IconFloorEntity) this.d).getIconSizePerPage(i2);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getBannerCursorColor() {
        return U0() ? ((IconFloorEntity) this.d).getDarkPointerUnselectedColor() : ((IconFloorEntity) this.d).getPointerUnselectedColor();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorHeight() {
        return ((IconFloorEntity) this.d).getCursorHeight();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorMarginBottom() {
        MallFloorIcon mallFloorIcon = (MallFloorIcon) c();
        if (mallFloorIcon != null && mallFloorIcon.inVideoFloor()) {
            return com.jingdong.app.mall.home.floor.common.d.d(12);
        }
        return ((IconFloorEntity) this.d).getCursorMarginBottom();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorSelectColor() {
        return U0() ? ((IconFloorEntity) this.d).getDarkPointerSelectedColor() : ((IconFloorEntity) this.d).getPointerSelectedColor();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorSpace() {
        return ((IconFloorEntity) this.d).getCursorSpace();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorSpaceColor() {
        if (H0()) {
            return U0() ? ((IconFloorEntity) this.d).getDarkPointerStrokeColor() : ((IconFloorEntity) this.d).getPointerStrokeColor();
        }
        return 0;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorWidthUnSelect() {
        return ((IconFloorEntity) this.d).getCursorWidthUnSelect();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getSelectWidth() {
        return ((IconFloorEntity) this.d).getSelectWidth();
    }

    public int h0() {
        return ((IconFloorEntity) this.d).getImageHeight();
    }

    public int i0() {
        return ((IconFloorEntity) this.d).getImageTopMargin();
    }

    public int j0() {
        return ((IconFloorEntity) this.d).getImageWidth();
    }

    public int k0() {
        float iconRealCount = ((IconFloorEntity) this.d).getIconRealCount() / (((IconFloorEntity) this.d).getShowLines() * 5.0f);
        if (iconRealCount <= 1.0f) {
            return -1;
        }
        if (iconRealCount <= 2.0f) {
            return 0;
        }
        return iconRealCount <= 4.0f ? 1 : 2;
    }

    public int l0() {
        return ((IconFloorEntity) this.d).getInitFloorHeight();
    }

    public int m0() {
        return ((IconFloorEntity) this.d).getItemCountPreRow();
    }

    public int n0() {
        return ((IconFloorEntity) this.d).getItemHeight();
    }

    public int o0() {
        if (!((IconFloorEntity) this.d).isSingleScroll() && !((IconFloorEntity) this.d).isDoubleScroll()) {
            return ((((IconFloorEntity) this.d).getLayoutInnerWidth() - (((IconFloorEntity) this.d).getItemDividerWidth() * 2)) - (((IconFloorEntity) this.d).getGridViewLeftRightPadding() * 2)) / m0();
        }
        return ((IconFloorEntity) this.d).getItemWidth();
    }

    @Override // com.jingdong.app.mall.home.r.f.a.b, com.jingdong.app.mall.home.r.f.a.c
    public void onEvent(BaseEvent baseEvent) {
        super.onEvent(baseEvent);
    }

    public int p0() {
        return ((IconFloorEntity) this.d).getItemWidth();
    }

    public int q0() {
        return ((IconFloorEntity) this.d).getLabelTopMargin();
    }

    public int r0() {
        return ((IconFloorEntity) this.d).getPageCount();
    }

    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void t(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar) {
        super.t(hVar, dVar);
    }

    public int t0() {
        return ((IconFloorEntity) this.d).getShowLines();
    }

    public int u0(int i2) {
        return U0() ? com.jingdong.app.mall.home.floor.common.i.m.j(((IconFloorEntity) this.d).getDarkFontColor(), -6514022) : ((IconFloorEntity) this.d).getTextColor();
    }

    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void v(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar) {
        MallFloorIcon mallFloorIcon = (MallFloorIcon) c();
        if (mallFloorIcon == null) {
            return;
        }
        if (!((IconFloorEntity) this.d).isIconDataValid()) {
            mallFloorIcon.onSetVisible(false);
            return;
        }
        P0();
        S0();
        mallFloorIcon.onSetVisible(true);
    }

    public int v0() {
        return ((IconFloorEntity) this.d).getTextHeight();
    }

    public int w0() {
        return ((IconFloorEntity) this.d).getTextSizePx();
    }

    public void x0(int i2) {
        ((IconFloorEntity) this.d).setItemCountPreRow(i2);
    }

    public boolean y0() {
        return ((IconFloorEntity) this.d).isBVersion();
    }

    public boolean z0() {
        return ((IconFloorEntity) this.d).isCompress();
    }
}
