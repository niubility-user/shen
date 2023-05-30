package com.jingdong.app.mall.home.floor.model.entity;

import android.graphics.Paint;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.common.entity.JumpEntity;
import com.un.lib.popup.JDTopPopupWindowHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public class CategoryEntity extends FloorEntity {
    public static DecorateInfo sDecorateInfo;
    private static float sMaxWidth;
    private static Paint sPaint = new Paint(1);
    private int animation;
    private int animationCount;
    private int animationPlayCount;
    private boolean mAsyncState;
    private boolean mHideSmileLine;
    private b mSrvJson;
    private List<CaItem> mTabList = new ArrayList();
    private int mTabMargin;
    private String rightImg;
    private JumpEntity rightJump;
    private String rightText;
    private int rightWidth;
    private int selectColor;
    private int selectSize;
    private int smileColor;
    private int unSelectColor;
    private int unSelectSize;

    /* loaded from: classes4.dex */
    public static class DecorateInfo extends com.jingdong.app.mall.home.r.e.b {
        private int[] colors;
        private volatile HashMap<String, FloorDecorateInfo> decorateMap;
        private int mBgHeight;
        private String mBgImg;
        private int mTextColor;

        public DecorateInfo(JDJSONObject jDJSONObject) {
            super(jDJSONObject);
            this.colors = new int[]{IconFloorEntity.BGCOLOR_DEFAULT, IconFloorEntity.BGCOLOR_DEFAULT};
            int[] o = m.o(getJsonString("color"), 0);
            if (o.length == 1) {
                int[] iArr = this.colors;
                int i2 = o[0];
                iArr[1] = i2;
                iArr[0] = i2;
            } else if (o.length == 2) {
                this.colors = o;
            }
            this.mTextColor = m.i(getJsonString("fontColor"), -7566196);
            String jsonString = getJsonString("img");
            this.mBgImg = jsonString;
            this.mBgImg = TextUtils.isEmpty(jsonString) ? "https://emptyUrl" : this.mBgImg;
            int jsonInt = getJsonInt("height");
            this.mBgHeight = jsonInt;
            this.mBgHeight = Math.min(Math.max(jsonInt, 240), 650);
            try {
                JDJSONObject jsonObject = getJsonObject("floorBackground");
                if (jsonObject != null) {
                    this.decorateMap = (HashMap) new Gson().fromJson(jsonObject.toJSONString(), new TypeToken<HashMap<String, FloorDecorateInfo>>() { // from class: com.jingdong.app.mall.home.floor.model.entity.CategoryEntity.DecorateInfo.1
                    }.getType());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        public int[] getBgColors() {
            return this.colors;
        }

        public int getBgHeight() {
            return this.mBgHeight;
        }

        public String getDecorateBgUrl() {
            return this.mBgImg;
        }

        public FloorDecorateInfo getDecorateByType(String str) {
            try {
                if (this.decorateMap == null) {
                    return null;
                }
                return this.decorateMap.get(str);
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }

        public int getTextColor() {
            return this.mTextColor;
        }
    }

    /* loaded from: classes4.dex */
    public static class FloorDecorateInfo {
        String bottomImg;
        String icon;
        String titleColor;
        String topImg;

        public String getBottomDecorateUrl() {
            return this.bottomImg;
        }

        public String getDecorateIcon() {
            return this.icon;
        }

        public int[] getTextColor(int i2) {
            return m.o(this.titleColor, i2);
        }

        public String getTopDecorateUrl() {
            return this.topImg;
        }
    }

    public void addItem(CaItem caItem, int i2, String str) {
        if (i2 == 0) {
            this.mTabList.clear();
        }
        if (i2 == 0) {
            this.mSrvJson = caItem.getSrvJson();
        }
        if (caItem.isValid()) {
            this.mTabList.add(caItem);
        }
    }

    public int getAnimation() {
        return this.animation;
    }

    public int getAnimationCount() {
        return this.animationCount;
    }

    public int getAnimationPlayCount() {
        return this.animationPlayCount;
    }

    public boolean getAsyncState() {
        return this.mAsyncState;
    }

    public List<CaItem> getItemList() {
        return this.mTabList;
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public int getLayoutHeight() {
        return super.getLayoutHeight();
    }

    public String getRightImg() {
        return this.rightImg;
    }

    public JumpEntity getRightJump() {
        return this.rightJump;
    }

    public String getRightText() {
        return this.rightText;
    }

    public int getRightWidth() {
        return this.rightWidth;
    }

    public int getSelectColor() {
        return this.selectColor;
    }

    public int getSelectSize() {
        return this.selectSize;
    }

    public int getSmileColor() {
        return this.smileColor;
    }

    public String getSrvStr() {
        b bVar = this.mSrvJson;
        return bVar == null ? "" : bVar.toString();
    }

    public int getTabMargin() {
        return this.mTabMargin;
    }

    public int getUnSelectColor() {
        return this.unSelectColor;
    }

    public int getUnSelectSize() {
        return this.unSelectSize;
    }

    void initMeasurePaint() {
        sPaint.setTextSize(d.d(Math.max(this.selectSize, this.unSelectSize)));
        sPaint.setFakeBoldText(true);
        sMaxWidth = sPaint.measureText("\u6700\u5927\u957f\u5ea6\u554a");
    }

    public boolean isHideSmileLine() {
        return this.mHideSmileLine;
    }

    public boolean isShowAllBtn() {
        JumpEntity jumpEntity;
        return (TextUtils.isEmpty(this.rightText) || (jumpEntity = this.rightJump) == null || TextUtils.isEmpty(jumpEntity.des)) ? false : true;
    }

    public void setAnimation(int i2) {
        this.animation = i2;
    }

    public void setAnimationCount(int i2) {
        this.animationCount = i2;
    }

    public void setAnimationPlayCount(int i2) {
        this.animationPlayCount = i2;
    }

    public void setAsyncState(boolean z) {
        this.mAsyncState = z;
    }

    public void setHideSmileLine(boolean z) {
        this.mHideSmileLine = z;
    }

    public void setRightImg(String str) {
        this.rightImg = str;
    }

    public void setRightJump(JumpEntity jumpEntity) {
        this.rightJump = jumpEntity;
    }

    public void setRightText(String str) {
        this.rightText = str;
    }

    public void setRightWidth(int i2) {
        this.rightWidth = i2;
    }

    public void setSelectColor(int i2) {
        this.selectColor = i2;
    }

    public void setSmileColor(int i2) {
        this.smileColor = i2;
    }

    public void setTabMargin(int i2) {
        this.mTabMargin = i2;
    }

    public void setTextSize(int i2, int i3) {
        this.selectSize = i2;
        this.unSelectSize = i3;
        initMeasurePaint();
    }

    public void setUnSelectColor(int i2) {
        this.unSelectColor = i2;
    }

    /* loaded from: classes4.dex */
    public static class CaItem {
        public static final int TYPE_BABEL = 2;
        public static final int TYPE_HOURLY_GO = 3;
        public static final int TYPE_WEB = 1;
        public String clkLog;
        private int dynamicType;
        public String expoLog;
        private String iconImg;
        private int imgAnimType;
        private int imgType;
        private boolean isBottom;
        private boolean isDirect;
        private AtomicBoolean isFirstChanged;
        private boolean isHourNative;
        private boolean isSelect;
        private boolean isShowImg;
        private boolean isTopTab;
        private String mCaName;
        public CategoryEntity mCategoryEntity;
        private b mExpoJson;
        private String mHeadSkinUrl;
        private String mHeadTabId;
        private AtomicInteger mPage;
        private String mPcId;
        private String mPmId;
        private String mPool;
        private int mPosition;
        private b mPvJson;
        private String mSelf;
        private String mSort;
        private b mSrvJson;
        private String mSrvString;
        private String pName;
        private int playCount;
        private CaItem preTabInfo;
        private String selectImg;
        private String showCity;
        private String unSelectImg1;
        private String unSelectImg2;
        private int urlType;
        public String webUrl;
        private String words1;
        private String words2;

        public CaItem(JDJSONObject jDJSONObject, int i2, CategoryEntity categoryEntity) {
            int i3 = 1;
            this.mPage = new AtomicInteger(1);
            this.isBottom = false;
            this.isFirstChanged = new AtomicBoolean(true);
            this.mCategoryEntity = categoryEntity;
            this.mPosition = i2;
            this.mPcId = com.jingdong.app.mall.home.r.e.b.getJsonString(jDJSONObject, "pcid", "");
            String jsonString = com.jingdong.app.mall.home.r.e.b.getJsonString(jDJSONObject, "cName", "");
            this.mCaName = jsonString;
            if (i2 == 0 && TextUtils.isEmpty(jsonString)) {
                this.mCaName = JDTopPopupWindowHelper.BASE_HOME;
            }
            String jsonString2 = com.jingdong.app.mall.home.r.e.b.getJsonString(jDJSONObject, "srvJson", "{}");
            this.mSrvString = jsonString2;
            this.mSrvJson = b.c(jsonString2);
            this.mExpoJson = b.c(this.mSrvString);
            this.mPvJson = b.c(this.mSrvString);
            this.mPool = com.jingdong.app.mall.home.r.e.b.getJsonString(jDJSONObject, "pool", "");
            this.mCaName = checkText(this.mCaName);
            this.isSelect = i2 == 0;
            this.selectImg = com.jingdong.app.mall.home.r.e.b.getJsonString(jDJSONObject, "cateTabImage", "");
            this.unSelectImg1 = com.jingdong.app.mall.home.r.e.b.getJsonString(jDJSONObject, "unCateImage1", "");
            this.unSelectImg2 = com.jingdong.app.mall.home.r.e.b.getJsonString(jDJSONObject, "unCateImage2", "");
            this.iconImg = com.jingdong.app.mall.home.r.e.b.getJsonString(jDJSONObject, "icon", "");
            this.isShowImg = com.jingdong.app.mall.home.r.e.b.getJsonInt(jDJSONObject, "showImage", 0) == 1;
            this.words1 = com.jingdong.app.mall.home.r.e.b.getJsonString(jDJSONObject, "unTabText", "");
            this.words2 = com.jingdong.app.mall.home.r.e.b.getJsonString(jDJSONObject, "tabText", "");
            this.expoLog = com.jingdong.app.mall.home.r.e.b.getJsonString(jDJSONObject, "expoLog", "");
            this.clkLog = com.jingdong.app.mall.home.r.e.b.getJsonString(jDJSONObject, "clkLog", "");
            this.webUrl = com.jingdong.app.mall.home.r.e.b.getJsonString(jDJSONObject, "innerUrl", "");
            this.urlType = com.jingdong.app.mall.home.r.e.b.getJsonInt(jDJSONObject, "urlType", 0);
            this.imgType = this.isShowImg ? com.jingdong.app.mall.home.r.e.b.getJsonInt(jDJSONObject, "imageType", 0) : 0;
            this.imgAnimType = categoryEntity.getAnimation() < 1 ? 0 : com.jingdong.app.mall.home.r.e.b.getJsonInt(jDJSONObject, "mAnimation", 0);
            this.dynamicType = com.jingdong.app.mall.home.r.e.b.getJsonInt(jDJSONObject, "dynamicType", 0);
            if (categoryEntity.getAnimationPlayCount() < 1) {
                i3 = 0;
            } else if (this.imgAnimType != 3) {
                i3 = categoryEntity.getAnimationPlayCount();
            }
            this.playCount = i3;
            this.mSrvJson.a("isdynamic", "0");
            this.mExpoJson.a("isdynamic", "0");
            this.mPmId = com.jingdong.app.mall.home.r.e.b.getJsonString(jDJSONObject, "pmid", "");
            this.showCity = com.jingdong.app.mall.home.r.e.b.getJsonString(jDJSONObject, "showCity", "0");
            this.isDirect = "1".equals(com.jingdong.app.mall.home.r.e.b.getJsonString(jDJSONObject, "isDirect", "0"));
        }

        private String checkText(String str) {
            return TextUtils.isEmpty(str) ? "" : CategoryEntity.sPaint.measureText(str) < CategoryEntity.sMaxWidth ? str : checkText(str.substring(0, str.length() - 1));
        }

        public void buildTopTab(com.jingdong.app.mall.home.r.e.b bVar, String str, int i2, boolean z) {
            this.webUrl = str;
            this.isHourNative = z;
            this.mHeadTabId = bVar.getJsonString("id");
            this.urlType = i2;
        }

        public void clearRequest() {
            this.mSort = "0";
            this.mSelf = null;
            this.mPage.set(1);
            this.isBottom = false;
        }

        public int getDynamicType() {
            return this.dynamicType;
        }

        public b getExpoJson() {
            return this.mExpoJson;
        }

        public String getHeadSkinUrl() {
            return this.mHeadSkinUrl;
        }

        public String getIconImg() {
            return this.iconImg;
        }

        public int getImgAnimType() {
            return this.imgAnimType;
        }

        public int getImgType() {
            return this.imgType;
        }

        public int getPage() {
            return Math.max(this.mPage.get(), 1);
        }

        public String getPcId() {
            return this.mPcId;
        }

        public int getPlayCount() {
            return this.playCount;
        }

        public String getPmId() {
            return this.mPmId;
        }

        public String getPool() {
            return this.mPool;
        }

        public int getPosition(int i2) {
            if (i2 < 0) {
                return i2;
            }
            if (this.isHourNative) {
                return 3;
            }
            return isBabelType() ? 2 : 1;
        }

        public CaItem getPreTabInfo() {
            return this.preTabInfo;
        }

        public String getPvParams() {
            return this.mSrvJson.toString();
        }

        public String getSelectImg() {
            return this.selectImg;
        }

        public String getSelf() {
            return this.mSelf;
        }

        public String getSort() {
            return this.mSort;
        }

        public b getSrvJson() {
            return this.mSrvJson;
        }

        public String getSrvString() {
            return this.mSrvString;
        }

        public String getTabName() {
            return (this.mPosition != 0 || TextUtils.isEmpty(this.pName)) ? this.mCaName : this.pName;
        }

        public String getUnSelectImg1() {
            return this.unSelectImg1;
        }

        public String getUnSelectImg2() {
            return this.unSelectImg2;
        }

        public String getWords1() {
            return TextUtils.isEmpty(this.words1) ? getTabName() : this.words1;
        }

        public String getWords2() {
            return TextUtils.isEmpty(this.words2) ? getTabName() : this.words2;
        }

        public boolean isAnimType() {
            int i2 = this.imgAnimType;
            return i2 == 1 || i2 == 2 || i2 == 3;
        }

        public boolean isBabelType() {
            return this.urlType == 1;
        }

        public boolean isBottom() {
            return this.isBottom;
        }

        public boolean isCityBuyTab() {
            return "1".equals(this.showCity) || "2".equals(this.showCity);
        }

        public boolean isDirect() {
            return this.isDirect;
        }

        public boolean isHourNative() {
            return this.isHourNative;
        }

        public boolean isImageType() {
            int i2 = this.imgType;
            return i2 == 1 || i2 == 2 || i2 == 3;
        }

        public boolean isSameInfo(CaItem caItem) {
            boolean z = caItem != null && TextUtils.equals(this.mHeadTabId, caItem.mHeadTabId) && this.mPosition == caItem.mPosition && TextUtils.equals(this.webUrl, caItem.webUrl) && this.isHourNative == caItem.isHourNative && this.urlType == caItem.urlType;
            if (caItem != null && TextUtils.equals(this.webUrl, caItem.webUrl)) {
                setHeadSkinUrl(caItem.getHeadSkinUrl());
            }
            return z;
        }

        public boolean isSelect() {
            return this.isSelect;
        }

        public boolean isShowImg() {
            return this.isShowImg;
        }

        public boolean isTopTab() {
            return this.isTopTab;
        }

        boolean isValid() {
            if (this.mPosition == 0) {
                return true;
            }
            boolean z = !TextUtils.isEmpty(this.mCaName);
            if (z && isWebTab()) {
                return !this.mCategoryEntity.isDataFromCache();
            }
            return z && !TextUtils.isEmpty(this.mPcId);
        }

        public boolean isWebTab() {
            return !TextUtils.isEmpty(this.webUrl) || this.isHourNative;
        }

        public void onTagChanged() {
            this.isFirstChanged.set(true);
        }

        public void sendPvExpoParams(JDJSONObject jDJSONObject) {
            if (jDJSONObject == null) {
                return;
            }
            String optString = jDJSONObject.optString("pid");
            if (this.isFirstChanged.getAndSet(false)) {
                this.mPvJson.put("pid", optString);
                com.jingdong.app.mall.home.n.g.v.b.g("Category_Main_PV_Expo", this.mPvJson.toString());
            }
        }

        public void setBottom(boolean z) {
            if (this.isBottom) {
                return;
            }
            this.isBottom = z;
        }

        public void setDirect(boolean z) {
            this.isDirect = z;
        }

        public void setHeadSkinUrl(String str) {
            this.mHeadSkinUrl = str;
        }

        public void setPName(String str) {
            this.pName = checkText(str);
        }

        public void setPage(int i2) {
            this.mPage.set(i2);
        }

        public void setPlayCount(int i2) {
            this.playCount = i2;
        }

        public void setPreTab(CaItem caItem) {
            this.preTabInfo = caItem;
        }

        public void setSelect(boolean z) {
            this.isSelect = z;
        }

        public void setSelf(String str) {
            if (str == null) {
                return;
            }
            this.mSelf = str;
        }

        public void setSort(String str) {
            if (str == null) {
                return;
            }
            this.mSort = str;
        }

        public int getPosition() {
            return this.mPosition;
        }

        public CaItem(int i2) {
            this.mPage = new AtomicInteger(1);
            this.isBottom = false;
            this.isFirstChanged = new AtomicBoolean(true);
            this.isTopTab = true;
            this.mPosition = i2;
            this.mSrvJson = b.c("");
        }
    }
}
