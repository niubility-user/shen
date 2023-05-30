package com.jingdong.app.mall.home.floor.view.view.title.tab;

import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.a;
import com.jingdong.app.mall.home.b;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.u;
import com.jingdong.app.mall.home.floor.ctrl.t.q;
import com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle;
import com.jingdong.app.mall.home.floor.view.view.title.tabbridge.HourlyGoBridge;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.g;
import com.jingdong.app.mall.home.o.a.i;
import com.jingdong.app.mall.home.o.a.n;
import com.jingdong.app.mall.home.r.b.c;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.lbs.businessAddress.JDBusinessAddress;
import com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager;
import com.jingdong.common.lbs.businessAddress.JDNearbyEntranceAddress;
import com.jingdong.common.lbs.businessAddress.JDNearbyEntranceAddressListener;
import com.jingdong.common.lbs.businessAddress.JDYFAddress;
import com.jingdong.common.lbs.jdlocation.JDLocationError;
import com.jingdong.common.login.LoginEvent;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.pdj.libcore.home.HourlyGoFragment;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class TitleTabManager {
    private static final AtomicBoolean sRequestOnceA = new AtomicBoolean(false);
    private static final AtomicBoolean sRequestOnceB = new AtomicBoolean(false);
    private boolean hasLocationPermission;
    private boolean isAgreeScene;
    private boolean isHourlyGoEnter;
    private boolean isRequesting;
    private boolean isTopLbsOpen;
    private String mFloorId;
    private IHomeTitle mHomeTitle;
    private final TitleTabInfo mTitleTabInfo;
    private q mView2Builder;
    private boolean needRequest;
    private String preRequestPin;

    /* loaded from: classes4.dex */
    public static class Instance {
        static TitleTabManager sInstance = new TitleTabManager();

        Instance() {
        }
    }

    public void checkTabShow() {
        if (!this.mTitleTabInfo.isCanShow()) {
            hideTabView();
        } else {
            showTabView();
        }
        if (this.mTitleTabInfo.getTabHourlyGo().isCanShow()) {
            HourlyGoBridge.saveHourlyDirect();
        }
    }

    public static TitleTabManager getInstance() {
        return Instance.sInstance;
    }

    private String getRequestParams(i iVar, i iVar2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("userCategory", l.f());
            jSONObject.put("floorModuleId", this.mFloorId);
            jSONObject.put("order", this.mTitleTabInfo.getTabHourlyGo().getOrder());
            jSONObject.put("tabLbs", "1");
            f.y(jSONObject);
            f.e0(jSONObject, iVar);
            f.d0(jSONObject, iVar2);
            jSONObject.put("fQueryStamp", b.f8602m + "");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    public void hideTabView() {
        if (f.p0()) {
            f.E0(new com.jingdong.app.mall.home.o.a.b() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabManager.4
                {
                    TitleTabManager.this = this;
                }

                @Override // com.jingdong.app.mall.home.o.a.b
                protected void safeRun() {
                    TitleTabManager.this.hideTabView();
                }
            });
            return;
        }
        checkHomeTabName();
        IHomeTitle iHomeTitle = this.mHomeTitle;
        if (iHomeTitle != null) {
            iHomeTitle.hideTopTab();
        }
    }

    private boolean interceptRefresh() {
        if (this.mTitleTabInfo.getTabHourlyGo().isInit()) {
            boolean z = !this.mTitleTabInfo.getTabHourlyGo().isNeedRefresh();
            if (!this.mTitleTabInfo.getTabHourlyGo().isCanShow() && z && a.o() && HourlyGoBridge.isHourlyDirected()) {
                this.mTitleTabInfo.getTabHourlyGo().setShow(true);
                checkTabShow();
            }
            return z;
        }
        return true;
    }

    public void onRequestEnd(i iVar, JDJSONObject jDJSONObject) {
        JDJSONArray optJSONArray;
        JDYFAddress defaultYFAddress;
        boolean z = true;
        boolean z2 = jDJSONObject != null && TextUtils.equals("1", jDJSONObject.optString("isDirect"));
        q qVar = this.mView2Builder;
        if (qVar != null) {
            if (z2) {
                qVar.j();
            } else {
                qVar.f();
            }
        }
        com.jingdong.app.mall.home.r.e.b bVar = null;
        this.mView2Builder = null;
        if (n.d() && z2 && iVar != null && ((defaultYFAddress = JDBusinessAddressManager.getInstance().getDefaultYFAddress()) == null || defaultYFAddress.getLat() == defaultYFAddress.getLng())) {
            JDBusinessAddressManager.getInstance().updateDefaultYFAddress(iVar.d());
        }
        if (jDJSONObject != null && (optJSONArray = jDJSONObject.optJSONArray("data")) != null) {
            bVar = new com.jingdong.app.mall.home.r.e.b(optJSONArray.getJSONObject(0));
        }
        this.isTopLbsOpen = a.o();
        boolean isHourlyDirected = HourlyGoBridge.isHourlyDirected();
        if (!z2 && (!this.isTopLbsOpen || !isHourlyDirected)) {
            z = false;
        }
        if (bVar != null) {
            this.mTitleTabInfo.parseHourlyGoAsync(bVar);
        }
        setHourlyGoState(z);
    }

    private void requestLbs(boolean z) {
        this.hasLocationPermission = n.a();
        this.isAgreeScene = n.c();
        this.isHourlyGoEnter = HourlyGoFragment.HOURLY_GO_IS_ENTER_IN;
        this.preRequestPin = LoginUserBase.getUserPin();
        if (this.isHourlyGoEnter) {
            HourlyGoBridge.saveHourlyDirect();
        }
        if (this.isRequesting) {
            return;
        }
        g.f(z ? 0L : 300000L, new g.b() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabManager.1
            {
                TitleTabManager.this = this;
            }

            @Override // com.jingdong.app.mall.home.o.a.g.b
            protected void onFail(JDLocationError jDLocationError) {
                if (SwitchQueryFetcher.getSwitchBooleanValue("mp_close_geo_nearby", false)) {
                    TitleTabManager.this.requestTabState(null, null);
                } else {
                    TitleTabManager.this.retryRequestLbs();
                }
            }

            @Override // com.jingdong.app.mall.home.o.a.g.b
            protected void onLbsChanged(i iVar) {
                TitleTabManager.this.requestTabState(iVar, null);
            }
        });
    }

    public void requestTabState(final i iVar, i iVar2) {
        HourlyGoBridge.setLbsInfo(iVar, iVar2);
        this.isRequesting = true;
        TitleTabExpoUtil.postLocationExpo(iVar);
        f.C0("topTabApi", getRequestParams(iVar, iVar2), new f.d() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabManager.3
            {
                TitleTabManager.this = this;
            }

            @Override // com.jingdong.app.mall.home.o.a.f.d
            public void onEnd(JDJSONObject jDJSONObject) {
                TitleTabManager.this.isRequesting = false;
                TitleTabManager.this.onRequestEnd(iVar, jDJSONObject);
            }

            @Override // com.jingdong.app.mall.home.o.a.f.d
            public void onError(HttpError httpError) {
                TitleTabManager.this.isRequesting = false;
                TitleTabManager.this.checkTabShow();
            }

            @Override // com.jingdong.app.mall.home.o.a.f.d
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }
        });
    }

    public void retryRequestLbs() {
        JDBusinessAddressManager.getInstance().getJDNearbyEntranceAddress(g.i(), new JDNearbyEntranceAddressListener() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabManager.2
            {
                TitleTabManager.this = this;
            }

            @Override // com.jingdong.common.lbs.businessAddress.JDNearbyEntranceAddressListener
            public void onEnd(JDNearbyEntranceAddress jDNearbyEntranceAddress) {
                i iVar;
                JDBusinessAddress defaultAddress = jDNearbyEntranceAddress.getDefaultAddress();
                JDBusinessAddress globalAddress = jDNearbyEntranceAddress.getGlobalAddress();
                if (defaultAddress != null) {
                    g.l("\u5b9a\u4f4d\u8865\u507f-\u9ed8\u8ba4\u6536\u8d27\u5730\u5740\u4fe1\u606f\uff1a" + defaultAddress.getJsonStr());
                    iVar = new i(defaultAddress);
                } else {
                    iVar = null;
                }
                if ((iVar == null || iVar.e()) && globalAddress != null) {
                    g.l("\u5b9a\u4f4d\u8865\u507f-\u5168\u7ad9\u7f13\u5b58\u5730\u5740\u4fe1\u606f\uff1a" + globalAddress.getJsonStr());
                    iVar = new i(globalAddress);
                }
                TitleTabManager.this.requestTabState(null, iVar);
            }
        });
    }

    private void setHourlyGoState(boolean z) {
        this.mTitleTabInfo.getTabHourlyGo().setShow(z);
        checkTabShow();
    }

    public void showTabView() {
        if (f.p0()) {
            f.E0(new com.jingdong.app.mall.home.o.a.b() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabManager.5
                {
                    TitleTabManager.this = this;
                }

                @Override // com.jingdong.app.mall.home.o.a.b
                protected void safeRun() {
                    TitleTabManager.this.showTabView();
                }
            });
            return;
        }
        if (this.mTitleTabInfo.getTabHourlyGo().isCanShow()) {
            a.D();
        }
        checkHomeTabName();
        u.a();
        IHomeTitle iHomeTitle = this.mHomeTitle;
        if (iHomeTitle != null) {
            iHomeTitle.addTopTab();
        }
    }

    public boolean canShowCustomTab() {
        return this.mTitleTabInfo.getTabCustom().isCanShow();
    }

    public boolean canShowHourlyGoTab() {
        return this.mTitleTabInfo.getTabHourlyGo().isCanShow();
    }

    public boolean canShowPromotion() {
        return this.mTitleTabInfo.getTabPromotion().isCanShow();
    }

    public void checkHomeTabName() {
        u.a();
    }

    public String getHomeName() {
        return isShowTab() ? this.mTitleTabInfo.getHomeName() : "";
    }

    public TitleTabInfo getTitleTabInfo() {
        return this.mTitleTabInfo;
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager;
        if (view == null || (inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method")) == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 2);
    }

    public void initView2Builder(c cVar, h hVar, d dVar) {
        if (com.jingdong.app.mall.home.i.f() > 1) {
            this.mView2Builder = null;
        } else {
            this.mView2Builder = new q(cVar, hVar, dVar);
        }
    }

    public boolean isShowTab() {
        return this.mTitleTabInfo.isCanShow();
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        if (baseEvent instanceof LoginEvent) {
            if (TextUtils.equals(this.preRequestPin, LoginUserBase.getUserPin()) || interceptRefresh()) {
                return;
            }
            requestLbs(true);
        }
    }

    public void onLbsStateChanged() {
        if (interceptRefresh()) {
            return;
        }
        boolean o = a.o();
        boolean z = o && HourlyGoBridge.isHourlyDirected() && !this.mTitleTabInfo.getTabHourlyGo().isCanShow();
        boolean z2 = HourlyGoFragment.HOURLY_GO_IS_ENTER_IN && !this.isHourlyGoEnter;
        boolean z3 = (n.a() ^ this.hasLocationPermission) && n.a();
        boolean z4 = (n.c() ^ this.isAgreeScene) && n.c();
        if (this.mHomeTitle != null) {
            if ((this.isTopLbsOpen ^ o) || z || z2 || z3 || z4 || this.needRequest) {
                this.needRequest = false;
                this.isTopLbsOpen = o;
                requestLbs(true);
            }
        }
    }

    public void onSplashClose() {
        q qVar = this.mView2Builder;
        if (qVar != null) {
            qVar.i();
        }
    }

    public void safeCheckTabState(h hVar, IHomeTitle iHomeTitle) {
        if (hVar != null && iHomeTitle != null) {
            f.r0(this, "load safeCheckTabState");
            this.mHomeTitle = iHomeTitle;
            this.mFloorId = hVar.A;
            this.mTitleTabInfo.parseFloorData(hVar);
            boolean isValid = this.mTitleTabInfo.isValid();
            if (isValid) {
                TitleTabExpoUtil.postTopTabExpoNow();
            }
            if (isValid && this.mTitleTabInfo.showTabNow()) {
                checkTabShow();
                return;
            }
            f.G0(this);
            if (HourlyGoBridge.isHourlyDirected()) {
                a.D();
            }
            this.isTopLbsOpen = a.o();
            if (this.mTitleTabInfo.getTabHourlyGo().isCanShow()) {
                checkTabShow();
                return;
            } else if (interceptRefresh()) {
                return;
            } else {
                boolean e2 = com.jingdong.app.mall.home.v.d.a.e();
                if (!((e2 && sRequestOnceB.get()) || (!e2 && sRequestOnceA.get())) || n.a()) {
                    if (e2) {
                        sRequestOnceB.set(true);
                    } else {
                        sRequestOnceA.set(true);
                    }
                    requestLbs(false);
                    return;
                }
                return;
            }
        }
        checkTabShow();
    }

    public void setNeedRequest(boolean z) {
        this.needRequest = z;
    }

    public boolean useCityName() {
        return this.mTitleTabInfo.isUseCityName();
    }

    public boolean useSpread() {
        return this.mTitleTabInfo.isUseSpread();
    }

    private TitleTabManager() {
        this.mTitleTabInfo = new TitleTabInfo();
    }

    public void checkHomeTabName(h hVar, IHomeTitle iHomeTitle) {
        try {
            safeCheckTabState(hVar, iHomeTitle);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
