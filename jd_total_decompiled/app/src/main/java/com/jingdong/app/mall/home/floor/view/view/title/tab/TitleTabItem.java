package com.jingdong.app.mall.home.floor.view.view.title.tab;

import android.text.TextUtils;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.floor.common.i.u;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;
import com.jingdong.app.mall.home.floor.view.b.f.d;
import com.jingdong.app.mall.home.floor.view.view.title.tabbridge.TitleTabPop;
import com.jingdong.app.mall.home.n.h.c;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.k;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.jdsdk.constant.JshopConst;

/* loaded from: classes4.dex */
public class TitleTabItem {
    public static final int CUSTOM_POSITION = -2;
    private static final String DEFAULT_CUSTOM = "\u5e7f\u573a";
    private static final String DEFAULT_HOME = "\u9996\u9875";
    private static final String DEFAULT_HOURLY_GO = "\u9644\u8fd1";
    public static final int HOME_POSITION = 0;
    public static final int HOURLY_GO_POSITION = -1;
    public static final int PROMOTION_POSITION = -3;
    public static final String TYPE_CUSTOM = "2";
    public static final String TYPE_HOME = "0";
    public static final String TYPE_HOURLY_GO = "1";
    public static final String TYPE_PROMOTION = "3";
    public static final int UN_KNOWN_POSITION = 1;
    private static String sHourlyGoName;
    private String clkLog;
    private int dynamicCount;
    private String expoLog;
    private String guideId;
    private boolean isAtmosphereImg;
    private boolean isAutoJump;
    private boolean isHomeTab;
    private boolean isHourNative;
    private boolean isInit;
    private boolean isParseAsync;
    private boolean isShow;
    private String labelFloorId;
    private CategoryEntity.CaItem mCaItem;
    private String mLabelImg;
    private boolean needRefresh;
    private String order;
    private String playCountKey;
    private String selectImg;
    private int selectPosition;
    private String selectSpreadImg;
    private String tabBgUrl;
    private int tabIndex;
    private String tabName;
    private String tabUrl;
    private String unSelectImg;
    private String unSelectSpreadImg;
    private boolean useLabelAnimation;
    private b expoJson = b.c("");
    private b mClickJson = b.c("");
    private TitleTabPop mPopInfo = new TitleTabPop();

    private void initClickJson(String str) {
        b c2 = b.c(str);
        this.mClickJson = c2;
        c2.a("sourcevalue", "-100");
    }

    private void parseHourlyGo(com.jingdong.app.mall.home.r.e.b bVar, boolean z) {
        this.selectImg = bVar.getJsonString("tabSelectImg");
        this.unSelectImg = bVar.getJsonString("tabUnSelectImg");
        this.selectSpreadImg = bVar.getJsonString("spreadImg");
        this.unSelectSpreadImg = bVar.getJsonString("spreadUnImg");
        String jsonString = bVar.getJsonString(JshopConst.JSHOP_DYNAMIC_TAB_NAME);
        if (TextUtils.isEmpty(jsonString)) {
            jsonString = DEFAULT_HOURLY_GO;
        }
        this.tabName = jsonString;
        this.playCountKey = bVar.getJsonString("id");
        this.useLabelAnimation = TextUtils.equals("1", bVar.getJsonString("animationSwitch"));
        this.dynamicCount = c.h(bVar.getJsonString("dynamicCount"), -1);
        String jsonString2 = bVar.getJsonString("expoJson");
        this.expoJson = b.c(jsonString2);
        initClickJson(jsonString2);
    }

    public static boolean setHourlyGoName(String str) {
        float Y = f.Y(str);
        if (Y > 2.0f || Y < 1.0f) {
            str = "";
        }
        boolean z = !TextUtils.equals(str, sHourlyGoName);
        sHourlyGoName = str;
        return z;
    }

    public void addPlayTimes() {
        d.c(this.playCountKey, this.dynamicCount);
    }

    public CategoryEntity.CaItem getCaItem() {
        return this.mCaItem;
    }

    public b getClickJson() {
        this.mClickJson.put("tabstyle", TitleTabManager.getInstance().useSpread() ? "2" : "1");
        return this.mClickJson;
    }

    public String getClkLog() {
        return this.clkLog;
    }

    public b getExpoJson() {
        this.expoJson.put("tabstyle", TitleTabManager.getInstance().useSpread() ? "2" : "1");
        return this.expoJson;
    }

    public String getExpoLog() {
        return this.expoLog;
    }

    public String getLabelFloorId() {
        return this.labelFloorId;
    }

    public String getLabelImg() {
        return this.mLabelImg;
    }

    public String getOrder() {
        return this.order;
    }

    public TitleTabPop getPopInfo() {
        return this.mPopInfo;
    }

    public String getSelectImg(boolean z) {
        return z ? this.selectSpreadImg : this.selectImg;
    }

    public int getSelectPosition() {
        return this.selectPosition;
    }

    public String getTabBgUrl() {
        CategoryEntity.CaItem caItem = this.mCaItem;
        String headSkinUrl = (caItem == null || !this.isAtmosphereImg) ? "" : caItem.getHeadSkinUrl();
        return TextUtils.isEmpty(headSkinUrl) ? this.tabBgUrl : headSkinUrl;
    }

    public String getTabName() {
        String str = this.tabName;
        if (this.selectPosition == -1 && TitleTabManager.getInstance().useCityName() && !TextUtils.isEmpty(sHourlyGoName)) {
            str = sHourlyGoName;
        }
        return f.j(2, str);
    }

    public String getUnSelectImg(boolean z) {
        return z ? this.unSelectSpreadImg : this.unSelectImg;
    }

    public boolean hasPlayTimes() {
        return d.g(this.playCountKey, this.dynamicCount);
    }

    public boolean isCanShow() {
        return this.isHomeTab || (this.isInit && this.isShow && (!TextUtils.isEmpty(this.tabUrl) || this.isHourNative));
    }

    public boolean isInit() {
        return this.isInit;
    }

    public boolean isNeedRefresh() {
        return this.needRefresh;
    }

    public boolean isRightTab() {
        return isCanShow() && TitleTabManager.getInstance().getTitleTabInfo().getInitCount() == this.tabIndex + 1;
    }

    public boolean isUseLabelAnimation() {
        return this.useLabelAnimation && hasPlayTimes();
    }

    public void onPostClick() {
        this.isAutoJump = false;
    }

    public boolean parseHomeItem(com.jingdong.app.mall.home.r.e.b bVar, int i2) {
        if (this.isInit) {
            return false;
        }
        this.order = bVar.getJsonString("order");
        this.selectImg = bVar.getJsonString("tabSelectImg");
        this.unSelectImg = bVar.getJsonString("tabUnSelectImg");
        this.selectSpreadImg = bVar.getJsonString("spreadImg");
        this.unSelectSpreadImg = bVar.getJsonString("spreadUnImg");
        this.tabIndex = i2;
        this.isHomeTab = true;
        this.selectPosition = 0;
        this.mCaItem = u.d();
        String jsonString = bVar.getJsonString(JshopConst.JSHOP_DYNAMIC_TAB_NAME);
        if (TextUtils.isEmpty(jsonString)) {
            jsonString = "\u9996\u9875";
        }
        this.tabName = jsonString;
        if (this.mCaItem == null) {
            this.mCaItem = new CategoryEntity.CaItem(0);
        }
        String jsonString2 = bVar.getJsonString("expoJson");
        this.expoJson = b.c(jsonString2);
        initClickJson(jsonString2);
        this.isShow = true;
        this.isInit = true;
        return true;
    }

    public void parseHourlyGoAsync(com.jingdong.app.mall.home.r.e.b bVar) {
        this.isParseAsync = true;
        parseHourlyGo(bVar, true);
        String jsonString = bVar.getJsonString("labelImg");
        this.labelFloorId = bVar.getJsonString("labelFloorId");
        this.clkLog = bVar.getJsonString("clkLog");
        this.expoLog = bVar.getJsonString("expoLog");
        this.mPopInfo.initHome(bVar);
        if (TitleTabPop.hasPopInfo()) {
            this.mLabelImg = "";
        } else if (!TextUtils.isEmpty(jsonString) && TextUtils.isEmpty(this.labelFloorId) && !TitleTabPop.hasPopInfo()) {
            this.mLabelImg = jsonString;
        } else {
            this.mLabelImg = "";
        }
    }

    public boolean parseHourlyGoItem(com.jingdong.app.mall.home.r.e.b bVar, int i2) {
        if (this.isInit) {
            return false;
        }
        this.order = bVar.getJsonString("order");
        this.tabIndex = i2;
        this.selectPosition = -1;
        if (!this.isParseAsync) {
            parseHourlyGo(bVar, false);
        }
        if (this.mCaItem == null) {
            this.mCaItem = new CategoryEntity.CaItem(-1);
            this.isHourNative = true;
        }
        this.mCaItem.setPreTab(null);
        if (k.w()) {
            this.isHourNative = false;
            this.tabUrl = "https://m.jd.com";
        }
        this.mCaItem.buildTopTab(bVar, this.tabUrl, 0, this.isHourNative);
        this.needRefresh = TextUtils.equals("1", bVar.getJsonString("refreshSwitch"));
        this.isInit = true;
        return true;
    }

    public boolean parseItem(com.jingdong.app.mall.home.r.e.b bVar, int i2, int i3) {
        if (this.isInit) {
            return false;
        }
        this.order = bVar.getJsonString("order");
        this.tabIndex = i3;
        this.selectPosition = i2;
        this.tabBgUrl = bVar.getJsonString("bgImg");
        this.selectImg = bVar.getJsonString("tabSelectImg");
        this.unSelectImg = bVar.getJsonString("tabUnSelectImg");
        this.selectSpreadImg = bVar.getJsonString("spreadImg");
        this.unSelectSpreadImg = bVar.getJsonString("spreadUnImg");
        this.isAtmosphereImg = TextUtils.equals(bVar.getJsonString("isAtmosphereImg"), "1");
        String jsonString = bVar.getJsonString("url");
        this.mCaItem = new CategoryEntity.CaItem(i2);
        if (TextUtils.isEmpty(jsonString)) {
            return false;
        }
        this.mCaItem.buildTopTab(bVar, jsonString, bVar.getJsonInt("urlType", 0), false);
        this.mCaItem.setPreTab(null);
        this.tabUrl = jsonString;
        String jsonString2 = bVar.getJsonString(JshopConst.JSHOP_DYNAMIC_TAB_NAME);
        if (TextUtils.isEmpty(jsonString2)) {
            jsonString2 = DEFAULT_CUSTOM;
        }
        this.tabName = jsonString2;
        String jsonString3 = bVar.getJsonString("expoJson");
        this.expoJson = b.c(jsonString3);
        initClickJson(jsonString3);
        this.isShow = true;
        this.isInit = true;
        return true;
    }

    public void reset() {
        this.needRefresh = false;
        this.isInit = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void setSelectType(JDHomeFragment jDHomeFragment, boolean z) {
        String str;
        if (!z) {
            try {
                if (!this.isAutoJump) {
                    str = "";
                    if (TextUtils.isEmpty(str)) {
                        str = "-100";
                    }
                    this.mClickJson.a("sourcevalue", str);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        this.isAutoJump = true;
        str = jDHomeFragment.thisActivity.getIntent().getStringExtra("sourceValue");
        if (TextUtils.isEmpty(str)) {
        }
        this.mClickJson.a("sourcevalue", str);
    }

    public void setShow(boolean z) {
        this.isShow = z;
    }

    public boolean useTabImg(boolean z) {
        return z ? (TextUtils.isEmpty(this.selectSpreadImg) || TextUtils.isEmpty(this.unSelectSpreadImg)) ? false : true : (TextUtils.isEmpty(this.selectImg) || TextUtils.isEmpty(this.unSelectImg)) ? false : true;
    }
}
