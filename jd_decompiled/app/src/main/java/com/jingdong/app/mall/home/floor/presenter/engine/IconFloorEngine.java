package com.jingdong.app.mall.home.floor.presenter.engine;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.common.i.t;
import com.jingdong.app.mall.home.floor.model.entity.IconFloorEntity;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.app.mall.home.r.e.k.c;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.JDJSONObjectWithDefaultUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class IconFloorEngine extends FloorEngine<IconFloorEntity> {
    public void i(h hVar, IconFloorEntity iconFloorEntity, boolean z, boolean z2) {
        if (hVar == null || iconFloorEntity == null) {
            return;
        }
        boolean z3 = !"0".equals(hVar.getJsonString("ynWaveEffect", "1"));
        iconFloorEntity.setPlayLottie(z3);
        JDJSONObject a = hVar.a();
        if (a == null) {
            return;
        }
        a.put("time", "");
        JDJSONArray jSONArray = a.getJSONArray("data");
        if (jSONArray == null || jSONArray.size() <= 0) {
            return;
        }
        List<c> s = c.s(jSONArray, z3, z, iconFloorEntity);
        int i2 = 0;
        if (s.size() < iconFloorEntity.getMaxIconItemCount()) {
            iconFloorEntity.setNeedUpdateView(false);
            return;
        }
        boolean needUpDate = iconFloorEntity.needUpDate(a);
        iconFloorEntity.setNeedUpdateView(needUpDate);
        iconFloorEntity.setDataJson(a);
        if (needUpDate) {
            iconFloorEntity.setAppEntryList(s);
            int maxIconItemCount = iconFloorEntity.getMaxIconItemCount();
            int iconRealCount = iconFloorEntity.getIconRealCount() / maxIconItemCount;
            int itemCountPreRow = iconFloorEntity.getItemCountPreRow();
            ArrayList arrayList = new ArrayList();
            if (iconFloorEntity.isSpread()) {
                iconRealCount = Math.min(2, iconRealCount);
                if (iconRealCount >= 1) {
                    ArrayList arrayList2 = new ArrayList();
                    for (int i3 = 0; i3 < maxIconItemCount; i3++) {
                        arrayList2.add(s.get(i3));
                    }
                    arrayList.add(arrayList2);
                }
                if (iconRealCount == 2) {
                    int size = (s.size() - maxIconItemCount) / itemCountPreRow;
                    ArrayList arrayList3 = new ArrayList();
                    while (i2 < size * itemCountPreRow) {
                        arrayList3.add(s.get(maxIconItemCount + i2));
                        i2++;
                    }
                    arrayList.add(arrayList3);
                    i2 = size;
                }
                iconFloorEntity.setSecondPageRows(i2);
            } else if (!iconFloorEntity.isSingleScroll()) {
                if (iconFloorEntity.isDoubleScroll()) {
                    ArrayList arrayList4 = new ArrayList();
                    while (i2 < 5) {
                        arrayList4.add(s.get(i2));
                        arrayList4.add(s.get(i2 + 5));
                        i2++;
                    }
                    for (int i4 = 10; i4 < s.size(); i4++) {
                        arrayList4.add(s.get(i4));
                    }
                    iconFloorEntity.setAppEntryList(arrayList4);
                } else {
                    if (iconFloorEntity.isElder()) {
                        iconRealCount = Math.min(1, iconRealCount);
                    }
                    for (int i5 = 0; i5 < iconRealCount; i5++) {
                        ArrayList arrayList5 = new ArrayList();
                        int i6 = i5 * maxIconItemCount;
                        for (int i7 = 0; i7 < maxIconItemCount; i7++) {
                            arrayList5.add(s.get(i6 + i7));
                        }
                        arrayList.add(arrayList5);
                    }
                }
            }
            iconFloorEntity.setPageCount(iconRealCount);
            iconFloorEntity.setIconList(arrayList);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine
    /* renamed from: j */
    public void e(h hVar, d dVar, IconFloorEntity iconFloorEntity) {
        if (hVar == null) {
            return;
        }
        com.jingdong.app.mall.home.floor.ctrl.h.N().l0(hVar.Y, false);
        d(hVar, iconFloorEntity);
        iconFloorEntity.setScrollType(hVar.getJsonInt("scrollType"));
        iconFloorEntity.setIconFloorStyle();
        super.e(hVar, dVar, iconFloorEntity);
        JDJSONObject a = hVar.a();
        if (a == null) {
            return;
        }
        iconFloorEntity.setInitFloorHeight(dVar.mFloorHeight);
        String jSONStringWithDefault = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(a, "poz", "");
        if (!TextUtils.isEmpty(jSONStringWithDefault)) {
            SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
            edit.putString("HOMEPOZ", jSONStringWithDefault);
            edit.apply();
        }
        t tVar = dVar.q;
        if (tVar != t.ICON_SCROLL_SINGLE && tVar != t.ICON_SCROLL_DOUBLE) {
            iconFloorEntity.setFloorId("appcenter");
        } else {
            iconFloorEntity.setFloorId("appcenterScroll");
        }
        iconFloorEntity.setIconPadding(hVar);
        iconFloorEntity.setHoldScreenType(JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(a, "holdScreenType", 0));
        iconFloorEntity.setShowLines(IconFloorEntity.isSingleLine(hVar) ? 1 : 2);
        iconFloorEntity.setShowBi(hVar.getJsonInt("showBi"));
        iconFloorEntity.setIsNeedUpdate(hVar.getJsonString("needUpdate", "1"));
        String jSONStringWithDefault2 = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(a, "bgPic", "");
        String jSONStringWithDefault3 = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(a, "compressBgPic", "");
        if (iconFloorEntity.isCompress() || iconFloorEntity.isSpread()) {
            jSONStringWithDefault2 = jSONStringWithDefault3;
        }
        iconFloorEntity.setBgUrl(jSONStringWithDefault2);
        iconFloorEntity.setBgColor(m.j(hVar.getJsonString(DYConstants.DY_BG_COLOR), 0));
        iconFloorEntity.setBgShadeColor(m.j(hVar.getJsonString("shadeColor"), 0));
        iconFloorEntity.setOnlineTextColor(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(a, "fontColor", ""));
        iconFloorEntity.setDarkFontColor(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(a, "darkFontColor", ""));
        iconFloorEntity.setPointerStyle(JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(a, "pointerStyle", 0));
        iconFloorEntity.setPointerSelectedColor(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(a, "pointerSelectedColor", ""));
        iconFloorEntity.setPointerUnselectedColor(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(a, "pointerUnselectedColor", ""));
        iconFloorEntity.setDarkPointerSelectedColor(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(a, "darkPointerSelectedColor", ""));
        iconFloorEntity.setDarkPointerUnselectedColor(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(a, "darkPointerUnselectedColor", ""));
        iconFloorEntity.setClipType(JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(a, "turnScreenType", 0));
        if (iconFloorEntity.isNewPointerStyle()) {
            iconFloorEntity.setCursorSize();
        }
        i(hVar, iconFloorEntity, hVar.Z, false);
    }

    @Override // com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine
    /* renamed from: k */
    public void f(h hVar, d dVar, IconFloorEntity iconFloorEntity, int i2) {
        iconFloorEntity.setItemDividerWidth(0);
        iconFloorEntity.setItemDividerColor(0);
        iconFloorEntity.setLayoutLeftRightMarginBy750Design(0);
    }
}
