package com.jingdong.app.mall.home.floor.view.view.title.tab;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.view.b.h.a;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.r.e.b;
import com.jingdong.app.mall.home.r.e.h;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public class TitleTabInfo {
    private static AtomicBoolean sInitOnce = new AtomicBoolean(false);
    private static boolean useSpread;
    private boolean isScrollFixedOpen;
    private String mHomeName;
    private int mTabLineColor;
    private int mustShowCount;
    private int tabCount;
    private int[] tabSelectColor;
    private int tabSpreadColor;
    private int tabSpreadTextSize;
    private int tabStrokeColor;
    private int tabTextColor;
    private int tabTextSize;
    private int tabUnSelectColor;
    private int tabUnSelectSpreadTextColor;
    private int tabUnSelectSpreadTextSize;
    private int tabUnSelectTextColor;
    private int tabUnSelectTextSize;
    private boolean useCityName;
    private final TitleTabItem mTabEmpty = new TitleTabItem();
    private final TitleTabItem mTabCustom = new TitleTabItem();
    private final TitleTabItem mTabPromotion = new TitleTabItem();
    private final TitleTabItem mTabHome = new TitleTabItem();
    private final TitleTabItem mTabHourlyGo = new TitleTabItem();
    private final List<TitleTabItem> mTabList = new CopyOnWriteArrayList();

    private void parseTabInfo(@NotNull b bVar) {
        String jsonString = bVar.getJsonString("recommendPageName");
        this.mHomeName = jsonString;
        if (TextUtils.isEmpty(jsonString)) {
            this.mHomeName = "\u63a8\u8350";
        }
        this.isScrollFixedOpen = TextUtils.equals(bVar.getJsonString("isCeiling"), "1");
        this.tabTextColor = a.d(bVar.getJsonString("tabFontColor"), -381927);
        this.tabTextSize = bVar.getJsonInt("tabFontSize", 30);
        this.tabSpreadColor = a.d(bVar.getJsonString("spreadFontColor"), this.tabTextColor);
        this.tabSpreadTextSize = bVar.getJsonInt("spreadFontSize", 40);
        this.tabUnSelectTextColor = a.d(bVar.getJsonString("tabUnFontColor"), -15066598);
        this.tabUnSelectTextSize = bVar.getJsonInt("tabFontUnSize", 30);
        this.tabUnSelectSpreadTextColor = a.d(bVar.getJsonString("spreadUnFontColor"), this.tabUnSelectTextColor);
        this.tabUnSelectSpreadTextSize = bVar.getJsonInt("spreadFontUnSize", 32);
        this.tabSelectColor = a.f(m.o(bVar.getJsonString("tabSelectColor"), -1), transformAlpha(a.g(bVar.getJsonString("tabTransparency"), 90, 80, 90)));
        this.tabUnSelectColor = a.c(a.d(bVar.getJsonString("tabUnSelectColor"), -381927), bVar.getJsonInt("tabUnTransparency", 6), 100);
        this.tabStrokeColor = a.c(a.d(bVar.getJsonString("tabFrameColor"), -16777216), bVar.getJsonInt("tabFrameTransparency", 0), 100);
        this.mTabLineColor = a.c(a.d(bVar.getJsonString("splitLineColor"), -381927), bVar.getJsonInt("splitLineTransparency", 20), 100);
        this.useCityName = TextUtils.equals("1", bVar.getJsonString("nameSource"));
        if (sInitOnce.getAndSet(true)) {
            return;
        }
        useSpread = TextUtils.equals("1", bVar.getJsonString("topTabStyle"));
    }

    private int[] transformAlpha(int[] iArr) {
        int[] iArr2 = new int[iArr.length];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr2[i2] = (iArr[i2] * 255) / 100;
        }
        return iArr2;
    }

    public String getHomeName() {
        return this.mHomeName;
    }

    public int getInitCount() {
        return this.tabCount;
    }

    public int getMastShowCount() {
        return this.mustShowCount;
    }

    public TitleTabItem getTabAt(int i2) {
        try {
            return this.mTabList.size() > i2 ? this.mTabList.get(i2) : this.mTabEmpty;
        } catch (Exception e2) {
            e2.printStackTrace();
            return this.mTabEmpty;
        }
    }

    public TitleTabItem getTabCustom() {
        return this.mTabCustom;
    }

    public TitleTabItem getTabHome() {
        return this.mTabHome;
    }

    public TitleTabItem getTabHourlyGo() {
        return this.mTabHourlyGo;
    }

    public int getTabLineColor() {
        return this.mTabLineColor;
    }

    public TitleTabItem getTabPromotion() {
        return this.mTabPromotion;
    }

    public int[] getTabSelectColor() {
        return this.tabSelectColor;
    }

    public int getTabStrokeColor() {
        return this.tabStrokeColor;
    }

    public int getTabTextColor(boolean z) {
        return z ? this.tabSpreadColor : this.tabTextColor;
    }

    public int getTabTextSize(boolean z) {
        return z ? this.tabSpreadTextSize : this.tabTextSize;
    }

    public int getTabUnSelectColor() {
        return this.tabUnSelectColor;
    }

    public int getTabUnSelectTextColor(boolean z) {
        return z ? this.tabUnSelectSpreadTextColor : this.tabUnSelectTextColor;
    }

    public int getTabUnSelectTextSize(boolean z) {
        return z ? this.tabUnSelectSpreadTextSize : this.tabUnSelectTextSize;
    }

    public boolean isCanShow() {
        return this.mTabCustom.isCanShow() || this.mTabPromotion.isCanShow() || this.mTabHourlyGo.isCanShow();
    }

    public boolean isScrollFixedOpen() {
        return this.isScrollFixedOpen;
    }

    public boolean isUseCityName() {
        return this.useCityName;
    }

    public boolean isUseSpread() {
        return useSpread;
    }

    public boolean isValid() {
        return this.tabCount > 1;
    }

    public boolean parseFloorData(h hVar) {
        reset();
        b bVar = new b(hVar.a());
        JDJSONArray jsonArr = bVar.getJsonArr("data");
        if (jsonArr == null || jsonArr.size() < 2) {
            return false;
        }
        f.r0(this, "load parseFloorData");
        for (int i2 = 0; i2 < jsonArr.size(); i2++) {
            b bVar2 = new b(jsonArr.getJSONObject(i2));
            String jsonString = bVar2.getJsonString("tabType");
            if (TextUtils.equals("2", jsonString) && this.mTabCustom.parseItem(bVar2, -2, i2)) {
                this.mTabList.add(this.mTabCustom);
                this.tabCount++;
                this.mustShowCount++;
            } else if (TextUtils.equals("3", jsonString) && this.mTabPromotion.parseItem(bVar2, -3, i2)) {
                this.mTabList.add(this.mTabPromotion);
                this.tabCount++;
                this.mustShowCount++;
            } else if (TextUtils.equals("0", jsonString) && this.mTabHome.parseHomeItem(bVar2, i2)) {
                this.mTabList.add(this.mTabHome);
                this.tabCount++;
                this.mustShowCount++;
            } else if (TextUtils.equals("1", jsonString) && this.mTabHourlyGo.parseHourlyGoItem(bVar2, i2)) {
                this.mTabList.add(this.mTabHourlyGo);
                this.tabCount++;
            }
        }
        if (this.mTabHome.parseHomeItem(new b(null), this.tabCount)) {
            this.mTabList.add(this.mTabHome);
            this.tabCount++;
        }
        parseTabInfo(bVar);
        return this.tabCount > 1;
    }

    public void parseHourlyGoAsync(b bVar) {
        this.mTabHourlyGo.parseHourlyGoAsync(bVar);
    }

    public void reset() {
        this.tabCount = 0;
        this.mustShowCount = 0;
        this.mTabList.clear();
        this.mTabCustom.reset();
        this.mTabPromotion.reset();
        this.mTabHome.reset();
        this.mTabHourlyGo.reset();
    }

    public boolean showTabNow() {
        return !this.mTabHourlyGo.isInit() && this.tabCount > 1;
    }
}
