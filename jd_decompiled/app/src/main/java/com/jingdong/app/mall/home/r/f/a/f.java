package com.jingdong.app.mall.home.r.f.a;

import android.text.TextUtils;
import com.jingdong.app.mall.home.floor.common.MallFloorEvent;
import com.jingdong.app.mall.home.floor.model.entity.BannerFloorEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.BannerFloorEngine;
import com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter;
import com.jingdong.app.mall.home.floor.view.baseui.IMallBannerFloorUI;
import com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI;
import com.jingdong.app.mall.home.floor.view.view.MallFloorBanner;
import com.jingdong.app.mall.home.floor.view.view.module.MallFloorBannerItem;
import com.jingdong.app.mall.utils.ui.view.CarouseFigureImagePagerAdapter;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.utils.StringUtil;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class f extends b<BannerFloorEntity, BannerFloorEngine, IMallBannerFloorUI> implements CarouseFigureImagePagerAdapter.CarouseFigureImageAdapterListener, ICursorContentViewPresenter {

    /* renamed from: h  reason: collision with root package name */
    private boolean f10744h;

    /* renamed from: i  reason: collision with root package name */
    private long f10745i;

    /* renamed from: j  reason: collision with root package name */
    private ConcurrentHashMap<Integer, BannerFloorEntity.VariaModel> f10746j;

    /* renamed from: k  reason: collision with root package name */
    private boolean f10747k;

    /* renamed from: l  reason: collision with root package name */
    private com.jingdong.app.mall.home.r.c.b f10748l;

    public f(Class<BannerFloorEntity> cls, Class<BannerFloorEngine> cls2) {
        super(cls, cls2);
        this.f10744h = false;
        this.f10745i = -1L;
        this.f10746j = new ConcurrentHashMap<>();
        this.f10748l = com.jingdong.app.mall.home.r.c.b.c("");
    }

    private void c0() {
        if (g0()) {
            int size = ((BannerFloorEntity) this.d).mCommercialList.size();
            for (int i2 = 0; i2 < size; i2++) {
                BannerFloorEntity.VariaModel variaModel = new BannerFloorEntity.VariaModel();
                variaModel.startDisplayTime = 0L;
                variaModel.displayRatio = 0.0f;
                this.f10746j.put(Integer.valueOf(i2), variaModel);
            }
        }
    }

    private void q0() {
        if (g0()) {
            for (int i2 = 0; i2 < this.f10746j.size(); i2++) {
                BannerFloorEntity.VariaModel variaModel = this.f10746j.get(Integer.valueOf(i2));
                if (variaModel != null) {
                    variaModel.startDisplayTime = 0L;
                    variaModel.allDisplayTime = 0L;
                    variaModel.displayRatio = 0.0f;
                }
            }
        }
    }

    private void w0(int i2, float f2) {
        int size = this.f10746j.size();
        if (size > 0) {
            u0(((i2 + size) - 1) % size, f2);
            v0(i2, f2);
        }
    }

    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void H(IMallFloorUI iMallFloorUI) {
        super.H(iMallFloorUI);
        if ((iMallFloorUI instanceof MallFloorBanner) && g0()) {
            u0(((MallFloorBanner) iMallFloorUI).getCurrentPos(), 0.0f);
            x0();
        }
    }

    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void I(IMallFloorUI iMallFloorUI) {
        super.I(iMallFloorUI);
        if ((iMallFloorUI instanceof MallFloorBanner) && g0()) {
            MallFloorBanner mallFloorBanner = (MallFloorBanner) iMallFloorUI;
            n0(mallFloorBanner.getCurrentPos());
            u0(mallFloorBanner.getCurrentPos(), mallFloorBanner.getDisplayRatio());
            x0();
        }
    }

    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void K(IMallFloorUI iMallFloorUI, MallFloorEvent mallFloorEvent) {
        q0();
        super.K(iMallFloorUI, mallFloorEvent);
    }

    public void P(int i2) {
        String str;
        float f2;
        IMallBannerFloorUI iMallBannerFloorUI = (IMallBannerFloorUI) c();
        str = "0";
        if (iMallBannerFloorUI instanceof MallFloorBanner) {
            MallFloorBanner mallFloorBanner = (MallFloorBanner) iMallBannerFloorUI;
            com.jingdong.app.mall.home.floor.ctrl.p newCarouselFigureViewCtrl = mallFloorBanner.getNewCarouselFigureViewCtrl();
            str = newCarouselFigureViewCtrl != null ? newCarouselFigureViewCtrl.e(i2) : "0";
            f2 = mallFloorBanner.getDisplayRatio();
        } else {
            f2 = 0.0f;
        }
        w0(i2, f2);
        com.jingdong.app.mall.home.r.c.b srvJson = ((BannerFloorEntity) this.d).getSrvJson(i2);
        if (srvJson != null) {
            srvJson.a(DeeplinkProductDetailHelper.LAYER_STYLE, str);
        }
        com.jingdong.app.mall.home.r.c.a.i().E(((BannerFloorEntity) this.d).getFloorId(), ((BannerFloorEntity) this.d).getSourceValue(i2) + CartConstant.KEY_YB_INFO_LINK + str, srvJson);
    }

    public boolean Q() {
        return ((BannerFloorEntity) this.d).getChangeUrl();
    }

    public com.jingdong.app.mall.home.r.c.b R() {
        return this.f10748l;
    }

    public float S() {
        float[] r = r();
        if (r != null && r.length > 0) {
            Arrays.sort(r);
            return r[r.length - 1];
        }
        ((BannerFloorEntity) this.d).getClass();
        return com.jingdong.app.mall.home.floor.common.d.d(24);
    }

    public ArrayList<MallFloorBannerItem> T() {
        return ((BannerFloorEntity) this.d).mCommercialList;
    }

    public String U(int i2) {
        return ((BannerFloorEntity) this.d).getExpoExtensionId(i2);
    }

    public String V(int i2) {
        return ((BannerFloorEntity) this.d).getExpoSalUrl(i2);
    }

    public int W() {
        return ((BannerFloorEntity) this.d).getExpoSalUrlSize();
    }

    public String X() {
        return ((BannerFloorEntity) this.d).getImg2();
    }

    public String Y() {
        return ((BannerFloorEntity) this.d).getImg3();
    }

    public String Z() {
        return ((BannerFloorEntity) this.d).getModelId();
    }

    public String a0() {
        return ((BannerFloorEntity) this.d).getSlideEventId();
    }

    public int b0() {
        return ((BannerFloorEntity) this.d).getViewChangeInterval();
    }

    public boolean d0() {
        return (TextUtils.isEmpty(((BannerFloorEntity) this.d).getImg2()) || TextUtils.isEmpty(((BannerFloorEntity) this.d).getImg3())) ? false : true;
    }

    public boolean e0() {
        return d0() && ((BannerFloorEntity) this.d).getEntranceAnimation() == 1;
    }

    public boolean f0() {
        return ((BannerFloorEntity) this.d).isAutoPlay();
    }

    public boolean g0() {
        String i2 = i();
        if (StringUtil.isEmpty(i2)) {
            return false;
        }
        return "banner".equals(i2);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getBannerCursorColor() {
        return ((BannerFloorEntity) this.d).getBannerCursorColor();
    }

    @Override // com.jingdong.app.mall.utils.ui.view.CarouseFigureImagePagerAdapter.CarouseFigureImageAdapterListener
    public int getCount() {
        return ((BannerFloorEntity) this.d).getItemListSize();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorHeight() {
        return g0() ? ((BannerFloorEntity) this.d).getBannerCursorHeight() : ((BannerFloorEntity) this.d).getCursorHeight();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorMarginBottom() {
        return ((BannerFloorEntity) this.d).getBannerCursorMarginBottom();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorSelectColor() {
        return ((BannerFloorEntity) this.d).getCursorSelectColor();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorSpace() {
        return g0() ? ((BannerFloorEntity) this.d).getBannerCursorSpace() : ((BannerFloorEntity) this.d).getCursorSpace();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorSpaceColor() {
        return ((BannerFloorEntity) this.d).getCursorSpaceColor();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorWidthUnSelect() {
        return g0() ? ((BannerFloorEntity) this.d).getBannerCursorWidth() : ((BannerFloorEntity) this.d).getCursorWidthUnSelect();
    }

    @Override // com.jingdong.app.mall.utils.ui.view.CarouseFigureImagePagerAdapter.CarouseFigureImageAdapterListener
    public String getImageUrl(int i2) {
        com.jingdong.app.mall.home.r.e.f itemByPosition = ((BannerFloorEntity) this.d).getItemByPosition(i2);
        if (itemByPosition == null) {
            return null;
        }
        String u = itemByPosition.u();
        if (g0()) {
            return u;
        }
        String jsonString = itemByPosition.getJsonString("darkModeImg");
        return (!com.jingdong.app.mall.home.state.dark.a.h() || TextUtils.isEmpty(jsonString)) ? u : jsonString;
    }

    @Override // com.jingdong.app.mall.utils.ui.view.CarouseFigureImagePagerAdapter.CarouseFigureImageAdapterListener
    public JDDisplayImageOptions getJDDisplayImageOptions() {
        return com.jingdong.app.mall.home.floor.ctrl.e.v();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getSelectWidth() {
        return g0() ? ((BannerFloorEntity) this.d).getBannerSelectWidth() : ((BannerFloorEntity) this.d).getCursorWidthUnSelect();
    }

    public boolean h0() {
        return this.f10747k;
    }

    public boolean i0() {
        return ((BannerFloorEntity) this.d).isCarousel();
    }

    public boolean j0() {
        return ((BannerFloorEntity) this.d).isFirstExpoed;
    }

    public boolean k0() {
        return ((BannerFloorEntity) this.d).isNewIndicatorType;
    }

    public boolean l0() {
        return ((BannerFloorEntity) this.d).isOpen103();
    }

    public void m0(int i2) {
        ((BannerFloorEntity) this.d).postExpoLog(i2);
    }

    public void n0(int i2) {
        if (!g0() || this.f10744h || this.f10745i == -1) {
            return;
        }
        com.jingdong.app.mall.home.r.c.b bVar = new com.jingdong.app.mall.home.r.c.b();
        bVar.a("fgstart", Long.valueOf(this.f10745i));
        bVar.a("fgend", Long.valueOf(System.currentTimeMillis()));
        bVar.a("frame", Integer.valueOf(i2));
        com.jingdong.app.mall.home.r.c.a.y("Home_FocusPic_ExpoTs", "", bVar.toString());
        this.f10745i = -1L;
        this.f10744h = true;
    }

    public void o0() {
        ((BannerFloorEntity) this.d).resetItemListFromTmp();
    }

    @Override // com.jingdong.app.mall.utils.ui.view.CarouseFigureImagePagerAdapter.CarouseFigureImageAdapterListener
    public void onClick(int i2) {
        com.jingdong.app.mall.home.r.e.f itemByPosition;
        IMallBannerFloorUI iMallBannerFloorUI = (IMallBannerFloorUI) c();
        if (iMallBannerFloorUI == null || (itemByPosition = ((BannerFloorEntity) this.d).getItemByPosition(i2)) == null) {
            return;
        }
        iMallBannerFloorUI.onClick(itemByPosition, i2);
    }

    public void p0() {
        if (this.f10745i == -1) {
            this.f10745i = System.currentTimeMillis();
        }
        if (this.f10744h) {
            this.f10744h = false;
        }
    }

    public void r0(boolean z) {
        if (z) {
            ((BannerFloorEntity) this.d).setFloorId("banner");
        }
    }

    public void s0(boolean z) {
        ((BannerFloorEntity) this.d).isFirstExpoed = z;
    }

    public float t0(com.jingdong.app.mall.home.r.c.b bVar, String str, long j2) {
        IMallBannerFloorUI iMallBannerFloorUI = (IMallBannerFloorUI) c();
        if ((iMallBannerFloorUI instanceof MallFloorBanner) && g0()) {
            float displayRatio = ((MallFloorBanner) iMallBannerFloorUI).getDisplayRatio();
            com.jingdong.app.mall.home.o.a.f.c(bVar, h0());
            com.jingdong.app.mall.home.o.a.f.b(bVar);
            com.jingdong.app.mall.home.o.a.f.d(bVar);
            bVar.a("frame", MallFloorBanner.getCurrentBannerFrameIndex() + "");
            bVar.a("urlcheck", TextUtils.isEmpty(str) ? "0" : "1");
            bVar.a("fpicrate", String.valueOf(displayRatio));
            bVar.a("showtimegap", j2 + "");
            return displayRatio;
        }
        return 0.0f;
    }

    public void u0(int i2, float f2) {
        long currentTimeMillis = System.currentTimeMillis();
        BannerFloorEntity.VariaModel variaModel = this.f10746j.get(Integer.valueOf(i2));
        if (variaModel != null) {
            long j2 = variaModel.startDisplayTime;
            if (j2 > 0) {
                variaModel.allDisplayTime += currentTimeMillis - j2;
                variaModel.displayRatio = Math.max(f2, variaModel.displayRatio);
                variaModel.startDisplayTime = 0L;
                String str = i2 + "-----" + variaModel.allDisplayTime;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void v(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar) {
        super.v(hVar, dVar);
        this.f10747k = hVar.Z;
        IMallBannerFloorUI iMallBannerFloorUI = (IMallBannerFloorUI) c();
        if (iMallBannerFloorUI == null) {
            return;
        }
        this.f10746j.clear();
        c0();
        iMallBannerFloorUI.addFloorMaiDianData(((BannerFloorEntity) this.d).getFloorId(), ((BannerFloorEntity) this.d).getEventId(), ((BannerFloorEntity) this.d).getExtensionId());
        w(((BannerFloorEntity) this.d).getTmpItemListSize());
        iMallBannerFloorUI.initViewData(((BannerFloorEntity) this.d).getLayoutHeight(), ((BannerFloorEntity) this.d).getCursorMarginBottom(), ((BannerFloorEntity) this.d).getScrollDuration());
        if (d0()) {
            iMallBannerFloorUI.initAnimView(e0());
        } else {
            iMallBannerFloorUI.clearEntryAnim();
        }
    }

    public void v0(int i2, float f2) {
        long currentTimeMillis = System.currentTimeMillis();
        BannerFloorEntity.VariaModel variaModel = this.f10746j.get(Integer.valueOf(i2));
        if (variaModel != null) {
            variaModel.startDisplayTime = currentTimeMillis;
            variaModel.displayRatio = Math.max(f2, variaModel.displayRatio);
            String str = i2 + "-----" + variaModel.startDisplayTime;
        }
    }

    public void x0() {
        com.jingdong.app.mall.home.r.c.a.i().F(((BannerFloorEntity) this.d).getFloorId(), this.f10746j, ((BannerFloorEntity) this.d).getSrvJsonList());
    }
}
