package com.jingdong.app.mall.home.floor.common.i;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.ImageView;
import androidx.annotation.IntRange;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import com.jd.libs.hybrid.offlineload.utils.OfflineFileUtils;
import com.jingdong.app.mall.home.floor.common.i.p;
import com.jingdong.app.mall.home.floor.model.entity.BBannerEntity;
import com.jingdong.app.mall.home.floor.model.entity.BubbleBannerEntity;
import com.jingdong.app.mall.home.floor.model.entity.DynamicIconEntity;
import com.jingdong.app.mall.home.floor.model.entity.FloorEntity;
import com.jingdong.app.mall.home.floor.model.entity.IconFloorEntity;
import com.jingdong.app.mall.home.floor.model.entity.LiveVideoElderEntity;
import com.jingdong.app.mall.home.floor.model.entity.MarketFloorEntity;
import com.jingdong.app.mall.home.floor.model.entity.NewcomerFloorEntity;
import com.jingdong.app.mall.home.floor.model.entity.SecKillElderEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.LinearFloorEngine;
import com.jingdong.app.mall.home.floor.view.special.MallFloorDivider;
import com.jingdong.app.mall.home.floor.view.special.MallFloorError;
import com.jingdong.app.mall.home.floor.view.special.MallFloorTitle;
import com.jingdong.app.mall.home.floor.view.view.DynamicIconRy;
import com.jingdong.app.mall.home.floor.view.view.DynamicIconVp;
import com.jingdong.app.mall.home.floor.view.view.MallFloorBBanner;
import com.jingdong.app.mall.home.floor.view.view.MallFloorBanner;
import com.jingdong.app.mall.home.floor.view.view.MallFloorBanner09018;
import com.jingdong.app.mall.home.floor.view.view.MallFloorBubbleBanner;
import com.jingdong.app.mall.home.floor.view.view.MallFloorBubbleBannerV9;
import com.jingdong.app.mall.home.floor.view.view.MallFloorBubbleBannerV936;
import com.jingdong.app.mall.home.floor.view.view.MallFloorBubbleDynamic;
import com.jingdong.app.mall.home.floor.view.view.MallFloorCategory;
import com.jingdong.app.mall.home.floor.view.view.MallFloorDebug;
import com.jingdong.app.mall.home.floor.view.view.MallFloorDeploy;
import com.jingdong.app.mall.home.floor.view.view.MallFloorEmpty;
import com.jingdong.app.mall.home.floor.view.view.MallFloorFlexCube;
import com.jingdong.app.mall.home.floor.view.view.MallFloorIcon;
import com.jingdong.app.mall.home.floor.view.view.MallFloorIconElder;
import com.jingdong.app.mall.home.floor.view.view.MallFloorIconLine;
import com.jingdong.app.mall.home.floor.view.view.MallFloorIconNormal;
import com.jingdong.app.mall.home.floor.view.view.MallFloorIconSpread;
import com.jingdong.app.mall.home.floor.view.view.MallFloorLine1To4;
import com.jingdong.app.mall.home.floor.view.view.MallFloorLine1To4GroupBuying;
import com.jingdong.app.mall.home.floor.view.view.MallFloorLineMore;
import com.jingdong.app.mall.home.floor.view.view.MallFloorLinearLayout;
import com.jingdong.app.mall.home.floor.view.view.MallFloorLinearWithCenterIcon;
import com.jingdong.app.mall.home.floor.view.view.MallFloorLiveVideoElder;
import com.jingdong.app.mall.home.floor.view.view.MallFloorMaiDian;
import com.jingdong.app.mall.home.floor.view.view.MallFloorMarket;
import com.jingdong.app.mall.home.floor.view.view.MallFloorNewcomer;
import com.jingdong.app.mall.home.floor.view.view.MallFloorPanic;
import com.jingdong.app.mall.home.floor.view.view.MallFloorSecKillElder;
import com.jingdong.app.mall.home.floor.view.view.MallFloorTongLanSchool;
import com.jingdong.app.mall.home.floor.view.view.MallFloorTrend;
import com.jingdong.app.mall.home.floor.view.view.MallFloorWithBgFloor;
import com.jingdong.app.mall.home.floor.view.view.MallFloorWithSubFloor;
import com.jingdong.app.mall.home.floor.view.view.title.tabnotice.TitleTabNoticeInfo;
import com.jingdong.app.mall.home.widget.HomeRecyclerAdapter;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.common.sample.jshop.utils.DataCompassUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public class t {
    public int mFloorHeight;
    private final int mFloorIntType;
    private final String[] mFloorStrType;
    protected com.jingdong.app.mall.home.widget.b mLastCreateView;
    protected com.jingdong.app.mall.home.r.e.h mLastModel;
    private final AtomicInteger mPreInitCount;
    private final LinkedBlockingQueue<WeakReference<com.jingdong.app.mall.home.widget.b>> mPreQueue;
    private final boolean useRoundBg;
    public static final t UNKNOWN = new k("UNKNOWN", 0, 0, "88001");
    public static final t FLOOR_ERROR = new t("FLOOR_ERROR", 1, 0, "88007") { // from class: com.jingdong.app.mall.home.floor.common.i.t.v
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public boolean isNormalFloor() {
            return false;
        }
    };
    public static final t FLOOR_RECOMMEND = new t("FLOOR_RECOMMEND", 2, 1, "88008") { // from class: com.jingdong.app.mall.home.floor.common.i.t.g0
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public boolean isNormalFloor() {
            return false;
        }
    };
    public static final t PRODUCT = new t("PRODUCT", 3, 1, "recommend", "recommendMultiTab") { // from class: com.jingdong.app.mall.home.floor.common.i.t.r0
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public boolean isNormalFloor() {
            return false;
        }
    };
    public static final t LOCAL_DEBUG = new t("LOCAL_DEBUG", 4, 240, true, "localDebug") { // from class: com.jingdong.app.mall.home.floor.common.i.t.c1
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorDebug(context);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public boolean isNormalFloor() {
            return false;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            dVar.mFloorHeight = com.jingdong.app.mall.home.floor.common.d.d(dVar.mParentModel.getJsonInt("height"));
        }
    };
    public static final t FLOOR_DIVIDER = new t("FLOOR_DIVIDER", 5, 0, "88005") { // from class: com.jingdong.app.mall.home.floor.common.i.t.n1
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorDivider(context);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public boolean isNormalFloor() {
            return false;
        }
    };
    public static final t FLOOR_MAI_DIAN = new t("FLOOR_MAI_DIAN", 6, 0, "88006") { // from class: com.jingdong.app.mall.home.floor.common.i.t.s1
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorMaiDian(context);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public boolean isNormalFloor() {
            return false;
        }
    };
    public static final t TITLE_FLOOR = new t("TITLE_FLOOR", 7, 70, "88004") { // from class: com.jingdong.app.mall.home.floor.common.i.t.t1
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorTitle(context);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public boolean isNormalFloor() {
            return false;
        }
    };
    public static final t LOCAL_ICON = new t("LOCAL_ICON", 8, 290, "88002") { // from class: com.jingdong.app.mall.home.floor.common.i.t.u1
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorIconNormal(context, 5);
        }
    };
    public static final t ICON = new t("ICON", 9, 260, "appcenter") { // from class: com.jingdong.app.mall.home.floor.common.i.t.a
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public t convertType(com.jingdong.app.mall.home.r.e.h hVar) {
            int jsonInt = hVar.getJsonInt("scrollType", 0);
            if (jsonInt == 3 || jsonInt == 4) {
                return t.ICON_ELDER;
            }
            if (jsonInt != 7) {
                if (jsonInt != 9) {
                    if (jsonInt != 10) {
                        return super.convertType(hVar);
                    }
                    return t.ICON_SCROLL_DOUBLE;
                }
                return t.ICON_SCROLL_SINGLE;
            }
            return t.ICON_SPREAD;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorIcon(context, 5);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            boolean isSingleLine = IconFloorEntity.isSingleLine(dVar.mParentModel);
            int i2 = IconFloorEntity.isCompressVer(dVar.mParentModel) ? isSingleLine ? 144 : 260 : isSingleLine ? R2.anim.pickerview_dialog_scale_in : IconFloorEntity.getHoldScreenType(dVar.mParentModel) == 2 ? 318 : 290;
            if (IconFloorEntity.isBVersion(dVar.mParentModel)) {
                i2 = 252;
            }
            if (IconFloorEntity.isHighVersion(dVar.mParentModel)) {
                i2 = 296;
            }
            dVar.mFloorHeight = com.jingdong.app.mall.home.floor.common.d.d(i2);
        }
    };
    public static final t ICON_SPREAD = new t("ICON_SPREAD", 10, 260, "appcenterSpread") { // from class: com.jingdong.app.mall.home.floor.common.i.t.b
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorIconSpread(context, 5);
        }
    };
    public static final t ICON_ELDER = new t("ICON_ELDER", 11, R2.attr.arrowShaftLength, "appcenterElder") { // from class: com.jingdong.app.mall.home.floor.common.i.t.c
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorIconElder(context);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            dVar.mFloorHeight = com.jingdong.app.mall.home.floor.common.d.d(IconFloorEntity.isSingleLine(dVar.mParentModel) ? 224 : R2.attr.arrowShaftLength);
        }
    };
    public static final t ICON_SCROLL_SINGLE = new t("ICON_SCROLL_SINGLE", 12, R2.anim.mtrl_bottom_sheet_slide_in, "appcenterScroll_Single") { // from class: com.jingdong.app.mall.home.floor.common.i.t.d
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorIconLine(context, 5, true);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            dVar.mFloorHeight = com.jingdong.app.mall.home.floor.common.d.d(IconFloorEntity.isMoreThanOnePage(dVar.mParentModel) ? R2.anim.out_to_bottom_slow : R2.anim.mtrl_bottom_sheet_slide_in);
        }
    };
    public static final t ICON_SCROLL_DOUBLE = new t("ICON_SCROLL_DOUBLE", 13, 304, "appcenterScroll_Double") { // from class: com.jingdong.app.mall.home.floor.common.i.t.e
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorIconLine(context, 5, false);
        }
    };
    public static final t DYNAMIC_ICON = new t("DYNAMIC_ICON", 14, 260, "dynamicIcon") { // from class: com.jingdong.app.mall.home.floor.common.i.t.f
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public t convertType(com.jingdong.app.mall.home.r.e.h hVar) {
            return DynamicIconEntity.isLineType(hVar) ? t.DYNAMIC_ICON_RY : t.DYNAMIC_ICON_VP;
        }
    };
    public static final t DYNAMIC_ICON_RY = new t("DYNAMIC_ICON_RY", 15, 260, "dynamicIcon_ry") { // from class: com.jingdong.app.mall.home.floor.common.i.t.g
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new DynamicIconRy(context);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            dVar.mFloorHeight = DynamicIconEntity.getFloorHeight750(dVar.mParentModel);
        }
    };
    public static final t DYNAMIC_ICON_VP = new t("DYNAMIC_ICON_VP", 16, 260, "dynamicIcon_vp") { // from class: com.jingdong.app.mall.home.floor.common.i.t.h
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new DynamicIconVp(context);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            dVar.mFloorHeight = DynamicIconEntity.getFloorHeight750(dVar.mParentModel);
        }
    };
    public static final t PANIC = new t("PANIC", 17, R2.attr.SimpleFooterTriggerRate, true, "15") { // from class: com.jingdong.app.mall.home.floor.common.i.t.i
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorPanic(context);
        }
    };
    public static final t LINEARLAYOUT_3_240H_2Pic = new t("LINEARLAYOUT_3_240H_2Pic", 18, 240, true, "06011") { // from class: com.jingdong.app.mall.home.floor.common.i.t.j
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public t convertType(com.jingdong.app.mall.home.r.e.h hVar) {
            if (com.jingdong.app.mall.home.floor.common.i.m.D(hVar)) {
                return t.FLOOR_LINE_06011;
            }
            return super.convertType(hVar);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(2);
            arrayList.add(1);
            arrayList.add(1);
            return new MallFloorLinearLayout(context, 3, arrayList, 4);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            super.parseFloorInfo(dVar);
            if (com.jingdong.app.mall.home.floor.common.i.m.C(dVar.mParentModel)) {
                dVar.mFloorHeight = com.jingdong.app.mall.home.floor.common.d.d(280);
            }
            com.jingdong.app.mall.home.floor.common.i.p.b(dVar, this);
            com.jingdong.app.mall.home.floor.common.i.p.a(dVar);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseSeparationParams(com.jingdong.app.mall.home.r.e.d dVar, com.jingdong.app.mall.home.r.e.f fVar, FloorEntity floorEntity, int i2) {
            p.a moduleParamsAt = floorEntity.getModuleParamsAt(i2);
            if (moduleParamsAt == null) {
                return;
            }
            boolean C = com.jingdong.app.mall.home.floor.common.i.m.C(dVar.mParentModel);
            moduleParamsAt.q = C;
            moduleParamsAt.D(C);
            moduleParamsAt.o(com.jingdong.app.mall.home.floor.common.i.b.LEFT_BOTTOM);
            moduleParamsAt.m(new Point(0, 0));
            moduleParamsAt.k(com.jingdong.app.mall.home.floor.common.i.a.CENTER_BOTTOM);
            moduleParamsAt.E(new Point(com.jingdong.app.mall.home.floor.common.d.d(20), com.jingdong.app.mall.home.floor.common.d.d(5)));
            moduleParamsAt.y(new Point(com.jingdong.app.mall.home.floor.common.d.d(20), com.jingdong.app.mall.home.floor.common.d.d(25)));
            if (i2 == 0) {
                moduleParamsAt.c(2);
                if (C) {
                    moduleParamsAt.r(new Point(com.jingdong.app.mall.home.floor.common.d.d(6), com.jingdong.app.mall.home.floor.common.d.d(24)));
                    moduleParamsAt.e(2, com.jingdong.app.mall.home.floor.common.d.d(R2.anim.miaosha_dropdown_in), com.jingdong.app.mall.home.floor.common.d.d(R2.anim.miaosha_dropdown_in), com.jingdong.app.mall.home.floor.common.d.d(22));
                    moduleParamsAt.j(new Point(com.jingdong.app.mall.home.floor.common.d.d(20), com.jingdong.app.mall.home.floor.common.d.d(30)));
                    return;
                }
                moduleParamsAt.e(2, com.jingdong.app.mall.home.floor.common.d.d(130), com.jingdong.app.mall.home.floor.common.d.d(130), 30);
                moduleParamsAt.j(new Point(com.jingdong.app.mall.home.floor.common.d.d(42), com.jingdong.app.mall.home.floor.common.d.d(10)));
                return;
            }
            moduleParamsAt.c(1);
            moduleParamsAt.n(new Point(com.jingdong.app.mall.home.floor.common.d.d(5), 0));
            if (C) {
                moduleParamsAt.l(com.jingdong.app.mall.home.floor.common.d.d(R2.anim.miaosha_dropdown_in), com.jingdong.app.mall.home.floor.common.d.d(R2.anim.miaosha_dropdown_in));
                moduleParamsAt.j(new Point(com.jingdong.app.mall.home.floor.common.d.d(16), com.jingdong.app.mall.home.floor.common.d.d(30)));
                moduleParamsAt.d(new Point(com.jingdong.app.mall.home.floor.common.d.d(51), com.jingdong.app.mall.home.floor.common.d.d(51)));
                return;
            }
            moduleParamsAt.l(com.jingdong.app.mall.home.floor.common.d.d(130), com.jingdong.app.mall.home.floor.common.d.d(130));
            moduleParamsAt.j(new Point(com.jingdong.app.mall.home.floor.common.d.d(28), com.jingdong.app.mall.home.floor.common.d.d(10)));
            moduleParamsAt.d(new Point(com.jingdong.app.mall.home.floor.common.d.d(43), com.jingdong.app.mall.home.floor.common.d.d(43)));
        }
    };
    public static final t LINEARLAYOUT_3_200H = new t("LINEARLAYOUT_3_200H", 19, 200, "06018") { // from class: com.jingdong.app.mall.home.floor.common.i.t.l
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorLinearLayout(context, 3);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.widget.b getFloorViewByCache(Context context) {
            com.jingdong.app.mall.home.widget.b floorViewByCache = super.getFloorViewByCache(context);
            this.mLastCreateView = floorViewByCache;
            return floorViewByCache;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            super.parseFloorInfo(dVar);
            com.jingdong.app.mall.home.floor.common.i.p.b(dVar, this);
            com.jingdong.app.mall.home.floor.ctrl.h.N().o0(this.mFloorHeight, dVar);
        }
    };
    public static final t LINEARLAYOUT_4_240H_v702 = new t("LINEARLAYOUT_4_240H_v702", 20, 240, "06056") { // from class: com.jingdong.app.mall.home.floor.common.i.t.m
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorLinearLayout(context, 4);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.floor.common.i.r getModelTypeEnum(int i2) {
            return com.jingdong.app.mall.home.floor.common.i.r.MODULE_ANIMATE_SKU;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            super.parseFloorInfo(dVar);
            com.jingdong.app.mall.home.floor.common.i.p.b(dVar, this);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseSeparationParams(com.jingdong.app.mall.home.r.e.d dVar, com.jingdong.app.mall.home.r.e.f fVar, FloorEntity floorEntity, int i2) {
            p.a moduleParamsAt = floorEntity.getModuleParamsAt(i2);
            if (moduleParamsAt == null || fVar == null) {
                return;
            }
            moduleParamsAt.f(true);
            moduleParamsAt.o(com.jingdong.app.mall.home.floor.common.i.b.GONE);
            moduleParamsAt.l(com.jingdong.app.mall.home.floor.common.d.d(130), com.jingdong.app.mall.home.floor.common.d.d(130));
            moduleParamsAt.g(com.jingdong.app.mall.home.floor.common.d.d(130), com.jingdong.app.mall.home.floor.common.d.d(130), 0);
            moduleParamsAt.k(com.jingdong.app.mall.home.floor.common.i.a.CENTER_TOP);
            floorEntity.setSeparationSingleBgImgScaleType(ImageView.ScaleType.FIT_XY);
            moduleParamsAt.q(true);
            moduleParamsAt.C(com.jingdong.app.mall.home.floor.common.d.d(140));
            moduleParamsAt.B(com.jingdong.app.mall.home.floor.common.d.d(28));
            if (LinearFloorEngine.i(dVar.mParentModel) && !TextUtils.isEmpty(fVar.v()) && !TextUtils.isEmpty(fVar.u())) {
                moduleParamsAt.c(2);
                return;
            }
            if (TextUtils.isEmpty(fVar.u())) {
                fVar.p0(fVar.v());
            }
            moduleParamsAt.c(1);
        }
    };
    public static final t CAROUSELFIGURE_BANNER = new t("CAROUSELFIGURE_BANNER", 21, R2.attr.alertDialogStyle, "banner") { // from class: com.jingdong.app.mall.home.floor.common.i.t.n
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorBanner(context);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.widget.b getFloorViewByCache(Context context) {
            com.jingdong.app.mall.home.widget.b floorViewByCache = super.getFloorViewByCache(context);
            this.mLastCreateView = floorViewByCache;
            return floorViewByCache;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            com.jingdong.app.mall.home.r.e.h hVar = dVar.mParentModel;
            if (hVar == null) {
                return;
            }
            if (com.jingdong.app.mall.home.floor.ctrl.h.C && hVar.f10692c <= 0 && hVar.X < com.jingdong.app.mall.home.floor.common.d.d(R2.attr.backgroundColor)) {
                hVar.f10692c = 20;
            }
            hVar.b = 0;
            com.jingdong.app.mall.home.floor.ctrl.h.N().n0(dVar);
            dVar.mFloorHeight = com.jingdong.app.mall.home.floor.common.d.d(com.jingdong.app.mall.home.floor.ctrl.h.v);
        }
    };
    public static final t CAROUSELFIGURE_DYNAMIC = new t("CAROUSELFIGURE_DYNAMIC", 22, 0, "dynamic") { // from class: com.jingdong.app.mall.home.floor.common.i.t.o
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public t convertType(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.g gVar) {
            if (gVar.f10683e == 0) {
                ArrayList<com.jingdong.app.mall.home.r.e.f> a2 = gVar.a();
                if (!gVar.f10691m && a2 != null && a2.size() > 0 && a2.get(0) != null) {
                    gVar.f10691m = true;
                    com.jingdong.app.mall.home.r.c.a.y("Home_UserLabel", "", a2.get(0).l());
                }
                return t.UNKNOWN;
            }
            return super.convertType(hVar, gVar);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorBanner(context);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.widget.b getFloorViewByCache(Context context) {
            com.jingdong.app.mall.home.widget.b floorViewByCache = super.getFloorViewByCache(context);
            this.mLastCreateView = floorViewByCache;
            return floorViewByCache;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            int i2 = dVar.f10683e;
            this.mFloorHeight = i2;
            dVar.mFloorHeight = com.jingdong.app.mall.home.floor.common.d.d(i2);
            com.jingdong.app.mall.home.floor.ctrl.h.N().o0(this.mFloorHeight, dVar);
        }
    };
    public static final t FLOOR_TRANSPARENT = new t("FLOOR_TRANSPARENT", 23, 230, "88003") { // from class: com.jingdong.app.mall.home.floor.common.i.t.p
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorEmpty(context);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            super.parseFloorInfo(dVar);
            int i2 = dVar.f10683e;
            if (i2 > 0) {
                dVar.mFloorHeight = com.jingdong.app.mall.home.floor.common.d.d(i2) + com.jingdong.app.mall.home.floor.ctrl.h.A;
            }
        }
    };
    public static final t LINEARLAYOUT_V618_1_3 = new t("LINEARLAYOUT_V618_1_3", 24, 200, "08002") { // from class: com.jingdong.app.mall.home.floor.common.i.t.q
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorLinearLayout(context, 3);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.widget.b getFloorViewByCache(Context context) {
            com.jingdong.app.mall.home.widget.b floorViewByCache = super.getFloorViewByCache(context);
            this.mLastCreateView = floorViewByCache;
            return floorViewByCache;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.floor.common.i.r getModelTypeEnum(int i2) {
            return com.jingdong.app.mall.home.floor.common.i.r.MODULE_V618_1_3;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            super.parseFloorInfo(dVar);
            parseLocalMargin(dVar, true, 0);
            com.jingdong.app.mall.home.floor.common.i.p.b(dVar, this);
            com.jingdong.app.mall.home.floor.ctrl.h.N().o0(this.mFloorHeight, dVar);
        }
    };
    public static final t TONGLAN_SCHOOL = new t("TONGLAN_SCHOOL", 25, 80, true, "08003") { // from class: com.jingdong.app.mall.home.floor.common.i.t.r
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorTongLanSchool(context);
        }
    };
    public static final t BUBBLE_BANNER = new t("BUBBLE_BANNER", 26, 230, "08005") { // from class: com.jingdong.app.mall.home.floor.common.i.t.s
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorBubbleBanner(context, BubbleBannerEntity.TYPE_08005);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            super.parseFloorInfo(dVar);
            parseLocalMargin(dVar, false, 0);
            com.jingdong.app.mall.home.floor.ctrl.h.N().o0(this.mFloorHeight, dVar);
        }
    };
    public static final t BUBBLE_BANNERV9 = new t("BUBBLE_BANNERV9", 27, 288, "08008") { // from class: com.jingdong.app.mall.home.floor.common.i.t.t
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public t convertType(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.g gVar) {
            if (BubbleBannerEntity.isNewUI(gVar)) {
                return t.BUBBLE_BANNER_V936;
            }
            return super.convertType(hVar, gVar);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorBubbleBannerV9(context, BubbleBannerEntity.TYPE_08008);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            super.parseFloorInfo(dVar);
            parseLocalMargin(dVar, false, 0);
            com.jingdong.app.mall.home.floor.ctrl.h.N().o0(this.mFloorHeight, dVar);
        }
    };
    public static final t BUBBLE_BANNER_V936 = new t("BUBBLE_BANNER_V936", 28, 288, "08008V936") { // from class: com.jingdong.app.mall.home.floor.common.i.t.u
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorBubbleBannerV9(context, BubbleBannerEntity.TYPE_08008_V936);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            super.parseFloorInfo(dVar);
            parseLocalMargin(dVar, false, 0);
            com.jingdong.app.mall.home.floor.ctrl.h.N().o0(this.mFloorHeight, dVar);
        }
    };
    public static final t BUBBLE_BANNERV914 = new t("BUBBLE_BANNERV914", 29, 200, "08009") { // from class: com.jingdong.app.mall.home.floor.common.i.t.w
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorBubbleBanner(context, BubbleBannerEntity.TYPE_08009);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            super.parseFloorInfo(dVar);
            parseLocalMargin(dVar, false, 0);
            com.jingdong.app.mall.home.floor.ctrl.h.N().o0(this.mFloorHeight, dVar);
        }
    };
    public static final t BUBBLE_BANNER_09008 = new t("BUBBLE_BANNER_09008", 30, 286, "09008") { // from class: com.jingdong.app.mall.home.floor.common.i.t.x
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorBubbleBannerV936(context);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            super.parseFloorInfo(dVar);
            ArrayList<com.jingdong.app.mall.home.r.e.f> arrayList = dVar.f10682c;
            if (arrayList != null && arrayList.size() > 0) {
                int jsonInt = dVar.getJsonInt("showRow", 1);
                int size = arrayList.size();
                if (jsonInt > 0 && size >= jsonInt * 4) {
                    int min = Math.min(Math.min(size / 4, jsonInt), 3);
                    if (min == 1) {
                        dVar.mFloorHeight = com.jingdong.app.mall.home.floor.common.d.d(286);
                    } else if (min == 2) {
                        dVar.mFloorHeight = com.jingdong.app.mall.home.floor.common.d.d(R2.attr.borderRound);
                    } else if (min == 3) {
                        dVar.mFloorHeight = com.jingdong.app.mall.home.floor.common.d.d(R2.attr.colorControlActivated);
                    } else {
                        dVar.mFloorHeight = 0;
                        return;
                    }
                    parseLocalMargin(dVar, false, 0);
                    return;
                }
                dVar.mFloorHeight = 0;
                return;
            }
            dVar.mFloorHeight = 0;
        }
    };
    public static final t BUBBLE_DYNAMIC = new t("BUBBLE_DYNAMIC", 31, 200, "09011") { // from class: com.jingdong.app.mall.home.floor.common.i.t.y
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorBubbleDynamic(context);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            super.parseFloorInfo(dVar);
            int jsonInt = dVar.getJsonInt("floorHeight");
            if (jsonInt <= 0) {
                dVar.w(false);
                return;
            }
            this.mFloorHeight = jsonInt;
            dVar.mFloorHeight = com.jingdong.app.mall.home.floor.common.d.d(jsonInt);
            parseLocalMargin(dVar, true, 0);
            com.jingdong.app.mall.home.floor.ctrl.h.N().o0(this.mFloorHeight, dVar);
        }
    };
    public static final t BANNER_09018 = new t("BANNER_09018", 32, 200, "09018") { // from class: com.jingdong.app.mall.home.floor.common.i.t.z
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorBanner09018(context);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            super.parseFloorInfo(dVar);
            dVar.mFloorHeight = com.jingdong.app.mall.home.floor.common.d.d(this.mFloorHeight);
            parseLocalMargin(dVar, true, 0);
            com.jingdong.app.mall.home.floor.ctrl.h.N().o0(this.mFloorHeight, dVar);
        }
    };
    public static final t FLOOR_CATEGORY = new t("FLOOR_CATEGORY", 33, 72, "topTab") { // from class: com.jingdong.app.mall.home.floor.common.i.t.a0
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            MallFloorCategory mallFloorCategory = new MallFloorCategory(context);
            this.mLastCreateView = mallFloorCategory;
            return mallFloorCategory;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            super.parseFloorInfo(dVar);
            this.mLastModel = dVar.mParentModel;
            int i2 = com.jingdong.app.mall.home.v.d.a.f() ? 84 : 72;
            this.mFloorHeight = i2;
            dVar.mFloorHeight = com.jingdong.app.mall.home.floor.common.d.d(i2);
        }
    };
    public static final t WITHSUBFLOOR = new t("WITHSUBFLOOR", 34, -2, OfflineFileUtils.HYBRID_OFFLINE_ROOT_DIR) { // from class: com.jingdong.app.mall.home.floor.common.i.t.b0
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorWithSubFloor(context);
        }
    };
    public static final t LINE_1_TO_4 = new t("LINE_1_TO_4", 35, 240, true, "223") { // from class: com.jingdong.app.mall.home.floor.common.i.t.c0
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public t convertType(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.g gVar) {
            if (gVar != null && com.jingdong.app.mall.home.floor.view.b.g.f.b0(gVar)) {
                return t.LINE_1_TO_4_GROUP_BUYING;
            }
            return super.convertType(hVar, gVar);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorLine1To4(context, getLineType());
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.floor.view.b.c getLineType() {
            return com.jingdong.app.mall.home.floor.view.b.c.OTHERS;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.floor.view.b.a[] getLineTypeEnumArr() {
            com.jingdong.app.mall.home.floor.view.b.a aVar = com.jingdong.app.mall.home.floor.view.b.a.LINE_OTHER_1;
            return new com.jingdong.app.mall.home.floor.view.b.a[]{aVar, aVar, aVar, aVar};
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            super.parseFloorInfo(dVar);
            com.jingdong.app.mall.home.r.e.h hVar = dVar.mParentModel;
            if (hVar == null) {
                return;
            }
            dVar.w(com.jingdong.app.mall.home.floor.view.b.g.f.d0(dVar));
            boolean z2 = hVar.getJsonInt(DataCompassUtils.MODULE_TYPE_HEAD, 0) == 1;
            hVar.f10700l = "0";
            hVar.M = z2;
            dVar.mFloorHeight = com.jingdong.app.mall.home.floor.common.d.d(z2 ? 310 : 240);
        }
    };
    public static final t LINE_1_TO_4_GROUP_BUYING = new t("LINE_1_TO_4_GROUP_BUYING", 36, 262, true, "223group") { // from class: com.jingdong.app.mall.home.floor.common.i.t.d0
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorLine1To4GroupBuying(context, getLineType());
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.floor.view.b.c getLineType() {
            return com.jingdong.app.mall.home.floor.view.b.c.OTHERS;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.floor.view.b.a[] getLineTypeEnumArr() {
            com.jingdong.app.mall.home.floor.view.b.a aVar = com.jingdong.app.mall.home.floor.view.b.a.LINE_OTHER_1;
            return new com.jingdong.app.mall.home.floor.view.b.a[]{aVar, aVar, aVar, aVar};
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            super.parseFloorInfo(dVar);
            com.jingdong.app.mall.home.r.e.h hVar = dVar.mParentModel;
            if (hVar == null) {
                return;
            }
            dVar.w(com.jingdong.app.mall.home.floor.view.b.g.f.d0(dVar));
            hVar.f10700l = "0";
            hVar.M = true;
        }
    };
    public static final t LINE_MARKET = new t("LINE_MARKET", 37, 226, true, DYConstants.DY_I_225) { // from class: com.jingdong.app.mall.home.floor.common.i.t.e0
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorMarket(context);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            super.parseFloorInfo(dVar);
            com.jingdong.app.mall.home.r.e.h hVar = dVar.mParentModel;
            if (hVar == null) {
                return;
            }
            parseLocalMargin(dVar, true, hVar.d);
            dVar.w(MarketFloorEntity.isValid(dVar));
        }
    };
    public static final t FLOOR_NEWCOMER = new t("FLOOR_NEWCOMER", 38, 336, "NC000", "NC001", "NC002", "NC003") { // from class: com.jingdong.app.mall.home.floor.common.i.t.f0
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorNewcomer(context);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            super.parseFloorInfo(dVar);
            if (dVar.mParentModel == null) {
                return;
            }
            parseLocalMargin(dVar, true, 20);
            dVar.w(NewcomerFloorEntity.isValid(dVar));
        }
    };
    public static final t FLOOR_LINE_06006 = new t("FLOOR_LINE_06006", 39, 288, true, "06006") { // from class: com.jingdong.app.mall.home.floor.common.i.t.h0
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorLineMore(context, getLineType());
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.floor.view.b.c getLineType() {
            return com.jingdong.app.mall.home.floor.view.b.c.NORMAL;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.floor.view.b.a[] getLineTypeEnumArr() {
            com.jingdong.app.mall.home.floor.view.b.a aVar = com.jingdong.app.mall.home.floor.view.b.a.LINE_WEIGHT_2;
            return new com.jingdong.app.mall.home.floor.view.b.a[]{aVar, aVar};
        }
    };
    public static final t FLOOR_LINE_06011 = new t("FLOOR_LINE_06011", 40, 288, true, "LINE_06011") { // from class: com.jingdong.app.mall.home.floor.common.i.t.i0
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorLineMore(context, getLineType());
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.floor.view.b.c getLineType() {
            return com.jingdong.app.mall.home.floor.view.b.c.NORMAL;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.floor.view.b.a[] getLineTypeEnumArr() {
            com.jingdong.app.mall.home.floor.view.b.a aVar = com.jingdong.app.mall.home.floor.view.b.a.LINE_WEIGHT_1;
            return new com.jingdong.app.mall.home.floor.view.b.a[]{com.jingdong.app.mall.home.floor.view.b.a.LINE_WEIGHT_2, aVar, aVar};
        }
    };
    public static final t FLOOR_LINE_06013 = new t("FLOOR_LINE_06013", 41, 288, true, "06013") { // from class: com.jingdong.app.mall.home.floor.common.i.t.j0
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorLineMore(context, getLineType());
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.floor.view.b.c getLineType() {
            return com.jingdong.app.mall.home.floor.view.b.c.NORMAL;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.floor.view.b.a[] getLineTypeEnumArr() {
            com.jingdong.app.mall.home.floor.view.b.a aVar = com.jingdong.app.mall.home.floor.view.b.a.LINE_WEIGHT_1;
            return new com.jingdong.app.mall.home.floor.view.b.a[]{aVar, aVar, aVar, aVar};
        }
    };
    public static final t FLOOR_LINE_06052 = new t("FLOOR_LINE_06052", 42, 288, true, "06052") { // from class: com.jingdong.app.mall.home.floor.common.i.t.k0
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorLineMore(context, getLineType());
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.floor.view.b.c getLineType() {
            return com.jingdong.app.mall.home.floor.view.b.c.NORMAL;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.floor.view.b.a[] getLineTypeEnumArr() {
            return new com.jingdong.app.mall.home.floor.view.b.a[]{com.jingdong.app.mall.home.floor.view.b.a.LINE_SALE, com.jingdong.app.mall.home.floor.view.b.a.LINE_WEIGHT_2};
        }
    };
    public static final t FLOOR_LINE_06057 = new t("FLOOR_LINE_06057", 43, 288, true, "06057") { // from class: com.jingdong.app.mall.home.floor.common.i.t.l0
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorLineMore(context, getLineType());
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.floor.view.b.c getLineType() {
            return com.jingdong.app.mall.home.floor.view.b.c.NORMAL;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.floor.view.b.a[] getLineTypeEnumArr() {
            com.jingdong.app.mall.home.floor.view.b.a aVar = com.jingdong.app.mall.home.floor.view.b.a.LINE_WEIGHT_1;
            return new com.jingdong.app.mall.home.floor.view.b.a[]{aVar, aVar, com.jingdong.app.mall.home.floor.view.b.a.LINE_WEIGHT_2};
        }
    };
    public static final t BANNER_1_SPLIT_2 = new t("BANNER_1_SPLIT_2", 44, 192, true, "06069") { // from class: com.jingdong.app.mall.home.floor.common.i.t.m0
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorLineMore(context, getLineType());
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.floor.view.b.c getLineType() {
            return com.jingdong.app.mall.home.floor.view.b.c.OTHERS;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.floor.view.b.a[] getLineTypeEnumArr() {
            com.jingdong.app.mall.home.floor.view.b.a aVar = com.jingdong.app.mall.home.floor.view.b.a.LINE_1_SPLIT_2_CARD;
            return new com.jingdong.app.mall.home.floor.view.b.a[]{aVar, aVar};
        }
    };
    public static final t FLOOR_LINE_09010 = new t("FLOOR_LINE_09010", 45, 288, true, "09010") { // from class: com.jingdong.app.mall.home.floor.common.i.t.n0
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorLineMore(context, getLineType());
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.floor.view.b.c getLineType() {
            return com.jingdong.app.mall.home.floor.view.b.c.NORMAL;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.floor.view.b.a[] getLineTypeEnumArr() {
            com.jingdong.app.mall.home.floor.view.b.a aVar = com.jingdong.app.mall.home.floor.view.b.a.LINE_WEIGHT_1;
            return new com.jingdong.app.mall.home.floor.view.b.a[]{com.jingdong.app.mall.home.floor.view.b.a.LINE_SALE, aVar, aVar};
        }
    };
    public static final t FLOOR_TREND = new t("FLOOR_TREND", 46, 56, "10002") { // from class: com.jingdong.app.mall.home.floor.common.i.t.o0
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorTrend(context);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            super.parseFloorInfo(dVar);
            if (dVar.mParentModel == null) {
                return;
            }
            parseLocalMargin(dVar, false, 0);
        }
    };
    public static final t SEC_KILL_ELDER = new t("SEC_KILL_ELDER", 47, R2.attr.appTheme, true, "0000") { // from class: com.jingdong.app.mall.home.floor.common.i.t.p0
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorSecKillElder(context);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            super.parseFloorInfo(dVar);
            if (dVar.mParentModel == null) {
                return;
            }
            parseLocalMargin(dVar, true, 24);
            dVar.w(SecKillElderEntity.isValid(dVar));
        }
    };
    public static final t LIVE_VIDEO_ELDER = new t("LIVE_VIDEO_ELDER", 48, R2.attr.behavior_autoHide, true, "0001") { // from class: com.jingdong.app.mall.home.floor.common.i.t.q0
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorLiveVideoElder(context);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            super.parseFloorInfo(dVar);
            if (dVar.mParentModel == null) {
                return;
            }
            parseLocalMargin(dVar, true, 24);
            dVar.w(LiveVideoElderEntity.isValid(dVar));
        }
    };
    public static final t B_VERSION_SEC_KILL = new t("B_VERSION_SEC_KILL", 49, 210, "N1000") { // from class: com.jingdong.app.mall.home.floor.common.i.t.s0
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorBBanner(context, 1);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            super.parseFloorInfo(dVar);
            if (dVar.mParentModel == null) {
                return;
            }
            parseLocalMargin(dVar, true, 20);
            dVar.w(BBannerEntity.isValid(1, dVar));
        }
    };
    public static final t B_VERSION_LIVE = new t("B_VERSION_LIVE", 50, R2.anim.slide_in_from_top, "N1001_temp") { // from class: com.jingdong.app.mall.home.floor.common.i.t.t0
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorBBanner(context, 2);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            super.parseFloorInfo(dVar);
            if (dVar.mParentModel == null) {
                return;
            }
            parseLocalMargin(dVar, true, 20);
            dVar.w(BBannerEntity.isValid(2, dVar));
        }
    };
    public static final t B_VERSION_9_9 = new t("B_VERSION_9_9", 51, 210, "N1002") { // from class: com.jingdong.app.mall.home.floor.common.i.t.u0
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorBBanner(context, 3);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            super.parseFloorInfo(dVar);
            if (dVar.mParentModel == null) {
                return;
            }
            parseLocalMargin(dVar, true, 20);
            dVar.w(BBannerEntity.isValid(3, dVar));
        }
    };
    public static final t MIL_ALLOWANCE = new t("MIL_ALLOWANCE", 52, 204, "N1003") { // from class: com.jingdong.app.mall.home.floor.common.i.t.v0
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorBBanner(context, 4);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            super.parseFloorInfo(dVar);
            com.jingdong.app.mall.home.r.e.h hVar = dVar.mParentModel;
            if (hVar == null) {
                return;
            }
            parseLocalMargin(dVar, true, hVar.d);
            dVar.w(BBannerEntity.isValid(4, dVar));
        }
    };
    public static final t DEPLOY_FLOOR = new t("DEPLOY_FLOOR", 53, 232, "deploy") { // from class: com.jingdong.app.mall.home.floor.common.i.t.w0
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public t convertType(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.g gVar) {
            return super.convertType(hVar, gVar);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorDeploy(context);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            super.parseFloorInfo(dVar);
            b(dVar);
        }
    };
    public static final t WITH_BG_FLOOR = new t("WITH_BG_FLOOR", 54, -2, "bgHybrid") { // from class: com.jingdong.app.mall.home.floor.common.i.t.x0
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorWithBgFloor(context);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            super.parseFloorInfo(dVar);
            parseLocalMargin(dVar, false, 0);
        }
    };
    public static final t ALMOSTTOP_FULLIMG = new t("ALMOSTTOP_FULLIMG", 55, com.jingdong.app.mall.home.floor.ctrl.a.d, "photoCeiling") { // from class: com.jingdong.app.mall.home.floor.common.i.t.y0
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorLinearWithCenterIcon(context);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public boolean isSpecial() {
            return true;
        }
    };
    public static final t SEARCHBARICON_RIGHTSPECIAL = new t("SEARCHBARICON_RIGHTSPECIAL", 56, 0, "searchIcon") { // from class: com.jingdong.app.mall.home.floor.common.i.t.z0
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public boolean isSpecial() {
            return true;
        }
    };
    public static final t SEARCHBARICON_LEFT = new t("SEARCHBARICON_LEFT", 57, 0, "saoasaoIcon") { // from class: com.jingdong.app.mall.home.floor.common.i.t.a1
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public boolean isSpecial() {
            return true;
        }
    };
    public static final t LOGIN = new t("LOGIN", 58, 0, LoginConstans.FREGMENT_LOGIN_FLAG) { // from class: com.jingdong.app.mall.home.floor.common.i.t.b1
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public boolean isSpecial() {
            return true;
        }
    };
    public static final t FLOAT = new t("FLOAT", 59, 0, "float") { // from class: com.jingdong.app.mall.home.floor.common.i.t.d1
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public boolean isSpecial() {
            return true;
        }
    };
    public static final t FLOAT_NEW = new t("FLOAT_NEW", 60, 0, "newFloat") { // from class: com.jingdong.app.mall.home.floor.common.i.t.e1
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public boolean isSpecial() {
            return true;
        }
    };
    public static final t SEARCH_BOX = new t("SEARCH_BOX", 61, 0, "searchBox") { // from class: com.jingdong.app.mall.home.floor.common.i.t.f1
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public boolean isSpecial() {
            return true;
        }
    };
    public static final t TITLE_B = new t("TITLE_B", 62, 0, "searchB") { // from class: com.jingdong.app.mall.home.floor.common.i.t.g1
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public boolean isSpecial() {
            return true;
        }
    };
    public static final t TOPROTATE = new t("TOPROTATE", 63, 0, "topRotate") { // from class: com.jingdong.app.mall.home.floor.common.i.t.h1
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public boolean isSpecial() {
            return true;
        }
    };
    public static final t LBS_TOP_TAB = new t("LBS_TOP_TAB", 64, 0, "multTopTab") { // from class: com.jingdong.app.mall.home.floor.common.i.t.i1
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public boolean isSpecial() {
            return true;
        }
    };
    public static final t BOTTOM_NAVI_LINK = new t("BOTTOM_NAVI_LINK", 65, 0, "tabBarAtmosphere") { // from class: com.jingdong.app.mall.home.floor.common.i.t.j1
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public boolean isSpecial() {
            return true;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            super.parseFloorInfo(dVar);
            com.jingdong.app.mall.home.floor.bottompop.b.f(dVar);
        }
    };
    public static final t NEARBY_BY_NOTICE = new t("NEARBY_BY_NOTICE", 66, 0, "nearbyIsland") { // from class: com.jingdong.app.mall.home.floor.common.i.t.k1
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public boolean isSpecial() {
            return true;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            super.parseFloorInfo(dVar);
            TitleTabNoticeInfo.getInstance().initHome(dVar);
        }
    };
    public static final t BOTTOM_TIME_FLOAT = new t("BOTTOM_TIME_FLOAT", 67, 0, "bottomBanner") { // from class: com.jingdong.app.mall.home.floor.common.i.t.l1
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public boolean isSpecial() {
            return true;
        }
    };
    public static final t BOTTOM_X_VIEW2 = new t("BOTTOM_X_VIEW2", 68, 0, "bottomXview") { // from class: com.jingdong.app.mall.home.floor.common.i.t.m1
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public boolean isSpecial() {
            return true;
        }
    };
    public static final t SHAKEFLOOR = new t("SHAKEFLOOR", 69, 0, "shakeFloorNew") { // from class: com.jingdong.app.mall.home.floor.common.i.t.o1
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public boolean isSpecial() {
            return true;
        }
    };
    public static final t AUTO_JUMP_VIEW = new t("AUTO_JUMP_VIEW", 70, 0, "topXview") { // from class: com.jingdong.app.mall.home.floor.common.i.t.p1
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public boolean isSpecial() {
            return true;
        }
    };
    public static final t FLEX_CUBE = new t("FLEX_CUBE", 71, 0, "flexCube") { // from class: com.jingdong.app.mall.home.floor.common.i.t.q1
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
            return new MallFloorFlexCube(context);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
            int i2 = dVar.f10683e;
            this.mFloorHeight = i2;
            dVar.mFloorHeight = com.jingdong.app.mall.home.floor.common.d.d(i2);
        }
    };
    public static final t RULE_FLOAT = new t("RULE_FLOAT", 72, 0, "ruleFloat") { // from class: com.jingdong.app.mall.home.floor.common.i.t.r1
        {
            k kVar = null;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public boolean isSpecial() {
            return true;
        }
    };

    /* renamed from: h */
    private static final /* synthetic */ t[] f9367h = {UNKNOWN, FLOOR_ERROR, FLOOR_RECOMMEND, PRODUCT, LOCAL_DEBUG, FLOOR_DIVIDER, FLOOR_MAI_DIAN, TITLE_FLOOR, LOCAL_ICON, ICON, ICON_SPREAD, ICON_ELDER, ICON_SCROLL_SINGLE, ICON_SCROLL_DOUBLE, DYNAMIC_ICON, DYNAMIC_ICON_RY, DYNAMIC_ICON_VP, PANIC, LINEARLAYOUT_3_240H_2Pic, LINEARLAYOUT_3_200H, LINEARLAYOUT_4_240H_v702, CAROUSELFIGURE_BANNER, CAROUSELFIGURE_DYNAMIC, FLOOR_TRANSPARENT, LINEARLAYOUT_V618_1_3, TONGLAN_SCHOOL, BUBBLE_BANNER, BUBBLE_BANNERV9, BUBBLE_BANNER_V936, BUBBLE_BANNERV914, BUBBLE_BANNER_09008, BUBBLE_DYNAMIC, BANNER_09018, FLOOR_CATEGORY, WITHSUBFLOOR, LINE_1_TO_4, LINE_1_TO_4_GROUP_BUYING, LINE_MARKET, FLOOR_NEWCOMER, FLOOR_LINE_06006, FLOOR_LINE_06011, FLOOR_LINE_06013, FLOOR_LINE_06052, FLOOR_LINE_06057, BANNER_1_SPLIT_2, FLOOR_LINE_09010, FLOOR_TREND, SEC_KILL_ELDER, LIVE_VIDEO_ELDER, B_VERSION_SEC_KILL, B_VERSION_LIVE, B_VERSION_9_9, MIL_ALLOWANCE, DEPLOY_FLOOR, WITH_BG_FLOOR, ALMOSTTOP_FULLIMG, SEARCHBARICON_RIGHTSPECIAL, SEARCHBARICON_LEFT, LOGIN, FLOAT, FLOAT_NEW, SEARCH_BOX, TITLE_B, TOPROTATE, LBS_TOP_TAB, BOTTOM_NAVI_LINK, NEARBY_BY_NOTICE, BOTTOM_TIME_FLOAT, BOTTOM_X_VIEW2, SHAKEFLOOR, AUTO_JUMP_VIEW, FLEX_CUBE, RULE_FLOAT};

    /* renamed from: g */
    private static final String f9366g = t.class.getSimpleName();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public enum k extends t {
        k(String str, int i2, int i3, String... strArr) {
            super(str, i2, i3, strArr, (k) null);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public boolean isNormalFloor() {
            return false;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.t
        public boolean isSpecial() {
            return true;
        }
    }

    /* synthetic */ t(String str, int i2, int i3, boolean z2, String[] strArr, k kVar) {
        this(str, i2, i3, z2, strArr);
    }

    private static int a() {
        int i2 = HomeRecyclerAdapter.f11020k;
        HomeRecyclerAdapter.f11020k = i2 + 1;
        return i2;
    }

    public static t valueOf(String str) {
        return (t) Enum.valueOf(t.class, str);
    }

    public static t[] values() {
        return (t[]) f9367h.clone();
    }

    protected void b(com.jingdong.app.mall.home.r.e.d dVar) {
        JDJSONObject jDJSONObject = dVar.f10687i;
        int optInt = jDJSONObject == null ? 0 : jDJSONObject.optInt("height");
        boolean z2 = (jDJSONObject == null ? 0 : jDJSONObject.optInt("hType")) == 2;
        dVar.mFloorHeight = com.jingdong.app.mall.home.floor.common.d.d(optInt);
        com.jingdong.app.mall.home.r.e.h hVar = dVar.mParentModel;
        if (hVar == null) {
            return;
        }
        int i2 = hVar.f10694f == 1 ? hVar.f10693e : 0;
        hVar.f10694f = 1;
        hVar.d = z2 ? 16 : 20;
        hVar.v = "";
        if (hVar.k()) {
            int i3 = hVar.f10692c;
            if (i3 <= 0) {
                i3 = 116;
            }
            hVar.f10692c = i3;
            int i4 = hVar.b;
            hVar.b = i4 > 0 ? i4 : 16;
            if (i2 <= 0) {
                i2 = 8;
            }
            hVar.f10693e = i2;
            return;
        }
        if (i2 <= 0) {
            i2 = 16;
        }
        hVar.f10693e = i2;
    }

    public void checkAlignSkin(com.jingdong.app.mall.home.r.e.d dVar) {
        if (isNormalFloor()) {
            com.jingdong.app.mall.home.floor.ctrl.h.N().E(this.mFloorHeight, dVar);
        }
    }

    public t convertType(com.jingdong.app.mall.home.r.e.h hVar) {
        return this;
    }

    public t convertType(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.g gVar) {
        return convertType(hVar);
    }

    public int getFloorIntType() {
        return this.mFloorIntType;
    }

    protected com.jingdong.app.mall.home.widget.b getFloorView(Context context) {
        return new MallFloorError(context);
    }

    public com.jingdong.app.mall.home.widget.b getFloorViewByCache(Context context) {
        WeakReference<com.jingdong.app.mall.home.widget.b> poll;
        com.jingdong.app.mall.home.widget.b bVar = (this.mPreQueue.size() <= 0 || (poll = this.mPreQueue.poll()) == null) ? null : poll.get();
        if (bVar == null) {
            bVar = getFloorView(context);
        }
        bVar.onUseView();
        return bVar;
    }

    public com.jingdong.app.mall.home.widget.b getLastCreateView() {
        return this.mLastCreateView;
    }

    public com.jingdong.app.mall.home.r.e.h getLastModel() {
        return this.mLastModel;
    }

    public com.jingdong.app.mall.home.floor.view.b.c getLineType() {
        return null;
    }

    public com.jingdong.app.mall.home.floor.view.b.a[] getLineTypeEnumArr() {
        return null;
    }

    public com.jingdong.app.mall.home.floor.common.i.r getModelTypeEnum(int i2) {
        return com.jingdong.app.mall.home.floor.common.i.r.MODULE_UNKNOWN;
    }

    public boolean isNormalFloor() {
        return true;
    }

    public boolean isSpecial() {
        return false;
    }

    public void parseFloorInfo(com.jingdong.app.mall.home.r.e.d dVar) {
        dVar.useRoundBg = this.useRoundBg;
        int i2 = this.mFloorHeight;
        if (i2 > 0) {
            i2 = com.jingdong.app.mall.home.floor.common.d.d(i2);
        }
        dVar.mFloorHeight = i2;
        com.jingdong.app.mall.home.floor.view.b.c lineType = getLineType();
        if (lineType != null) {
            lineType.parseFloorInfo(dVar, 20);
        }
    }

    public final void parseFloorType(Map<String, t> map, SparseArray<t> sparseArray) {
        for (String str : this.mFloorStrType) {
            if (map.containsKey(str)) {
                com.jingdong.app.mall.home.o.a.f.r0(f9366g, "Error ", str, " is already register, please change strType on", str);
                return;
            }
            map.put(str, this);
        }
        sparseArray.put(this.mFloorIntType, this);
    }

    public void parseLocalMargin(com.jingdong.app.mall.home.r.e.d dVar, boolean z2, int i2) {
        if (dVar == null) {
            return;
        }
        com.jingdong.app.mall.home.r.e.h hVar = dVar.mParentModel;
        if (z2 && hVar != null) {
            hVar.d = i2;
        }
        int d2 = com.jingdong.app.mall.home.floor.common.d.d(i2);
        dVar.mPaddingRect = new Rect(d2, 0, d2, 0);
    }

    public void parseSeparationParams(com.jingdong.app.mall.home.r.e.d dVar, com.jingdong.app.mall.home.r.e.f fVar, FloorEntity floorEntity, int i2) {
    }

    public void preInitFloorView(Context context, com.jingdong.app.mall.home.r.e.d dVar) {
        if (context == null || dVar == null || dVar.isCacheData) {
            return;
        }
        try {
            com.jingdong.app.mall.home.widget.b floorView = getFloorView(context);
            WeakReference<com.jingdong.app.mall.home.widget.b> weakReference = new WeakReference<>(floorView);
            boolean z2 = this.mPreInitCount.get() < 2;
            floorView.onPreInitView(dVar, z2);
            if (z2) {
                this.mPreInitCount.incrementAndGet();
                this.mPreQueue.offer(weakReference);
            } else {
                floorView.onReleaseView();
            }
        } catch (Exception e2) {
            com.jingdong.app.mall.home.o.a.f.s0(this, e2);
        }
    }

    /* synthetic */ t(String str, int i2, int i3, String[] strArr, k kVar) {
        this(str, i2, i3, strArr);
    }

    private t(@IntRange(from = -2, to = 2147483647L) String str, int i2, int i3, String... strArr) {
        this(str, i2, i3, false, strArr);
    }

    private t(@IntRange(from = -2, to = 2147483647L) String str, int i2, int i3, boolean z2, String... strArr) {
        super(str, i2);
        this.mPreQueue = new LinkedBlockingQueue<>(2);
        this.mPreInitCount = new AtomicInteger();
        this.mFloorStrType = strArr;
        this.mFloorIntType = a();
        this.mFloorHeight = i3;
        this.useRoundBg = z2;
    }
}
