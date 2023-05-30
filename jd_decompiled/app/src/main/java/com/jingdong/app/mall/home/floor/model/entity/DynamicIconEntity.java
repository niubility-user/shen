package com.jingdong.app.mall.home.floor.model.entity;

import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.app.mall.home.r.e.k.b;
import com.jingdong.app.mall.home.state.dark.a;
import com.jingdong.common.utils.JDJSONObjectWithDefaultUtils;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes4.dex */
public class DynamicIconEntity extends FloorEntity {
    public static final int CC1000_ALPHA_12 = 536870911;
    public static final int CC1000_DARK_STYLE_SEL = -381927;
    public static final int CC1000_DARK_STYLE_STROKE = 219222032;
    public static final int CC1000_DARK_STYLE_UN = -12830150;
    public static final int CC1000_LIGHT_STYLE_SEL = -381927;
    public static final int CC1000_LIGHT_STYLE_STROKE = 704643071;
    public static final int CC1000_LIGHT_STYLE_UN = -1842205;
    private static final int DEF_DARK_TEXT_COLOR = -6514022;
    private static final int DEF_TEXT_COLOR = -8092023;
    private static final ReadWriteLock mItemListLock = new ReentrantReadWriteLock();
    private int mDarkPointerSelectedColor;
    private int mDarkPointerUnselectedColor;
    private int mHoldScreenType;
    private List<b> mItemList;
    private List<List<b>> mItemPageList;
    private int mPageCount;
    private int mPointerSelectedColor;
    private int mPointerUnselectedColor;
    private boolean mUseClip;
    private ViewConfig mViewConfig;
    private String mBgUrl = "";
    private int mBgColor = IconFloorEntity.BGCOLOR_DEFAULT;
    private int textColor = DEF_TEXT_COLOR;
    private int darkTextColor = DEF_DARK_TEXT_COLOR;

    /* loaded from: classes4.dex */
    public static class ViewConfig {
        public int clipDegree;
        public int clipPadding;
        public String configId;
        public int containerLPadding;
        public int containerRPadding;
        public int containerTopPadding;
        public int cursorSelectWidth;
        public int cursorSpaceWidth;
        public int cursorUnSelectWidth;
        public int height;
        public int heightWithoutIndicator;
        public int iconContainerHeight;
        public int iconContainerWidth;
        public int iconCountPerPage;
        public int iconCountPerRow;
        public int iconHeight;
        public boolean iconTextBold;
        public int iconTextBottomMargin;
        public boolean iconTextScaleSwitch;
        public int iconTextSize;
        public int iconTopMargin;
        public int iconWidth;
        public int indicatorBottomMargin;
        public int indicatorHeight;
        public boolean isLineType;
        public boolean isValid;
        public int lottieMarginLeft;
        public int minIconCount;
        public int nAlpha = 255;
        public int showLines;
        public int tagColor;
        public int tagFont;
        public int tagHeight;
        public int tagMarginLeft;
        public int tagTop;

        public void parseViewConfig(JDJSONObject jDJSONObject) {
            boolean z = false;
            if (jDJSONObject == null) {
                this.isValid = false;
                return;
            }
            this.configId = Md5Encrypt.md5(jDJSONObject.toString());
            int optInt = jDJSONObject.optInt("height");
            this.height = optInt;
            this.heightWithoutIndicator = optInt - jDJSONObject.optInt("hiddenPadding");
            this.clipPadding = jDJSONObject.optInt("clipPadding");
            this.clipDegree = jDJSONObject.optInt("clipDegree");
            this.isLineType = jDJSONObject.optInt("slideType") == 1;
            this.showLines = jDJSONObject.optInt("showLines");
            this.minIconCount = jDJSONObject.optInt("minIconCount");
            if (this.isLineType) {
                this.showLines = Math.min(this.showLines, 2);
            }
            int optInt2 = jDJSONObject.optInt("iconCount");
            this.iconCountPerRow = optInt2;
            this.iconCountPerPage = this.showLines * optInt2;
            this.containerLPadding = jDJSONObject.optInt("cardPaddingLeft");
            this.containerRPadding = jDJSONObject.optInt("cardPaddingRight");
            this.containerTopPadding = jDJSONObject.optInt("cardTop");
            this.iconContainerWidth = this.isLineType ? jDJSONObject.optInt("channelWidth") : -1;
            this.iconContainerHeight = jDJSONObject.optInt("channelHeight");
            this.indicatorBottomMargin = jDJSONObject.optInt("indicatorBottom");
            JDJSONObject jSONObject = jDJSONObject.getJSONObject("channel");
            if (jSONObject == null) {
                this.isValid = false;
                return;
            }
            this.iconWidth = jSONObject.optInt("iconWidth");
            this.iconHeight = jSONObject.optInt("iconHeight");
            this.iconTopMargin = jSONObject.optInt("iconTop");
            this.iconTextSize = jSONObject.optInt("textFont");
            this.iconTextBold = jSONObject.optInt("fontBold") > 0;
            this.iconTextBottomMargin = jSONObject.optInt("textBottom");
            this.iconTextScaleSwitch = jSONObject.optInt("textScaleSwitch") == 1;
            int optInt3 = jSONObject.optInt("tagTop");
            this.tagTop = optInt3;
            this.tagTop = this.iconTopMargin - optInt3;
            this.tagMarginLeft = jSONObject.optInt("tagMarginLeft");
            this.lottieMarginLeft = jSONObject.optInt("lottieMarginLeft");
            this.tagHeight = jSONObject.optInt("tagHeight");
            this.tagFont = jSONObject.optInt("tagFont");
            this.tagColor = m.j(jSONObject.optString("tagColor"), -1);
            JDJSONObject jSONObject2 = jDJSONObject.getJSONObject("indicator");
            if (jSONObject2 != null) {
                this.indicatorHeight = jSONObject2.optInt("height");
                this.cursorSelectWidth = jSONObject2.optInt("selectWidth");
                this.cursorUnSelectWidth = jSONObject2.optInt("unselectWidth");
                this.cursorSpaceWidth = jSONObject2.optInt("space");
                this.nAlpha = Math.min(255, Math.max((int) (jSONObject2.optDouble("alpha", 1.0d) * 255.0d), 0));
            }
            if (this.showLines > 0 && this.iconCountPerRow > 0 && this.minIconCount >= this.iconCountPerPage && this.iconWidth > 0 && this.iconHeight > 0) {
                z = true;
            }
            this.isValid = z;
        }
    }

    public static int getFloorHeight750(h hVar) {
        ViewConfig viewConfig = getViewConfig(hVar);
        boolean z = false;
        if (viewConfig.isValid) {
            JDJSONObject a = hVar.a();
            JDJSONArray jSONArray = a != null ? a.getJSONArray("data") : null;
            int size = jSONArray != null ? jSONArray.size() : 0;
            int i2 = viewConfig.iconCountPerPage;
            float f2 = size / i2;
            int i3 = size / i2;
            if (!viewConfig.isLineType ? i3 > 1 : f2 > 1.0f) {
                z = true;
            }
            return d.d(z ? viewConfig.height : viewConfig.heightWithoutIndicator);
        }
        return 0;
    }

    public static boolean isLineType(h hVar) {
        return getViewConfig(hVar).isLineType;
    }

    private void parseLineItemList(List<b> list) {
        int i2;
        if (this.mViewConfig.showLines == 2) {
            ArrayList arrayList = new ArrayList();
            if (list.size() >= this.mViewConfig.minIconCount) {
                int i3 = 0;
                while (true) {
                    i2 = this.mViewConfig.iconCountPerRow;
                    if (i3 >= i2) {
                        break;
                    }
                    arrayList.add(list.get(i3));
                    arrayList.add(list.get(this.mViewConfig.iconCountPerRow + i3));
                    i3++;
                }
                for (int i4 = i2 * 2; i4 < list.size(); i4++) {
                    arrayList.add(list.get(i4));
                }
            }
            setItemList(arrayList);
        }
    }

    private void parsePageItemList(List<b> list) {
        this.mPageCount = list.size() / this.mViewConfig.iconCountPerPage;
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.mPageCount; i2++) {
            ArrayList arrayList2 = new ArrayList();
            int i3 = this.mViewConfig.iconCountPerPage * i2;
            for (int i4 = 0; i4 < this.mViewConfig.iconCountPerPage; i4++) {
                arrayList2.add(list.get(i3 + i4));
            }
            arrayList.add(arrayList2);
        }
        setItemPageList(arrayList);
    }

    private void setItemList(List<b> list) {
        ReadWriteLock readWriteLock = mItemListLock;
        readWriteLock.writeLock().lock();
        try {
            if (this.mItemList == null) {
                this.mItemList = new ArrayList();
            }
            this.mItemList.clear();
            if (list != null) {
                this.mItemList.addAll(list);
            }
            readWriteLock.writeLock().unlock();
        } catch (Throwable th) {
            mItemListLock.writeLock().unlock();
            throw th;
        }
    }

    private void setItemPageList(List<List<b>> list) {
        ReadWriteLock readWriteLock = mItemListLock;
        readWriteLock.writeLock().lock();
        try {
            if (this.mItemPageList == null) {
                this.mItemPageList = new ArrayList();
            }
            this.mItemPageList.clear();
            if (list != null) {
                this.mItemPageList.addAll(list);
            }
            readWriteLock.writeLock().unlock();
        } catch (Throwable th) {
            mItemListLock.writeLock().unlock();
            throw th;
        }
    }

    public int getBgColor() {
        return this.mBgColor;
    }

    public String getBgUrl() {
        if (a.h()) {
            return com.jingdong.app.mall.home.floor.ctrl.h.N().U() ? this.mBgUrl : "";
        }
        return this.mBgUrl;
    }

    public int getIconSizePerPage(int i2) {
        List<b> list;
        int size;
        ReadWriteLock readWriteLock = mItemListLock;
        readWriteLock.readLock().lock();
        if (i2 >= 0) {
            try {
                if (i2 < this.mItemPageList.size() && (list = this.mItemPageList.get(i2)) != null) {
                    size = list.size();
                    readWriteLock.readLock().unlock();
                    return size;
                }
            } catch (Throwable th) {
                mItemListLock.readLock().unlock();
                throw th;
            }
        }
        size = 0;
        readWriteLock.readLock().unlock();
        return size;
    }

    public b getItemByPos(int i2) {
        ReadWriteLock readWriteLock = mItemListLock;
        readWriteLock.readLock().lock();
        try {
            List<b> list = this.mItemList;
            b bVar = (list == null || list.size() <= i2) ? null : this.mItemList.get(i2);
            readWriteLock.readLock().unlock();
            return bVar;
        } catch (Throwable th) {
            mItemListLock.readLock().unlock();
            throw th;
        }
    }

    public int getItemCount() {
        ReadWriteLock readWriteLock = mItemListLock;
        readWriteLock.readLock().lock();
        try {
            List<b> list = this.mItemList;
            int size = list != null ? list.size() : 0;
            readWriteLock.readLock().unlock();
            return size;
        } catch (Throwable th) {
            mItemListLock.readLock().unlock();
            throw th;
        }
    }

    public int getPageCount() {
        return this.mPageCount;
    }

    public int getPointerSelectedColor() {
        return a.h() ? this.mDarkPointerSelectedColor : this.mPointerSelectedColor;
    }

    public int getPointerSpaceColor() {
        return a.h() ? 219222032 : 704643071;
    }

    public int getPointerUnselectColor() {
        return a.h() ? this.mDarkPointerUnselectedColor : this.mPointerUnselectedColor;
    }

    public int getTextColor() {
        return a.h() ? this.darkTextColor : this.textColor;
    }

    public ViewConfig getViewConfig() {
        return this.mViewConfig;
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public boolean isValid() {
        ViewConfig viewConfig = this.mViewConfig;
        return viewConfig != null && viewConfig.isValid && getItemCount() >= this.mViewConfig.minIconCount;
    }

    public void parseConfigData(JDJSONObject jDJSONObject) {
        ViewConfig viewConfig = new ViewConfig();
        this.mViewConfig = viewConfig;
        if (jDJSONObject != null) {
            viewConfig.parseViewConfig(jDJSONObject.getJSONObject("viewConfig"));
        }
    }

    public void parseIconData(h hVar, JDJSONObject jDJSONObject) {
        ViewConfig viewConfig = this.mViewConfig;
        if (viewConfig == null || !viewConfig.isValid || hVar == null || jDJSONObject == null) {
            return;
        }
        String jsonString = hVar.getJsonString("ynWaveEffect", "0");
        String jSONStringWithDefault = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "tagTextBgImg", "");
        JDJSONArray jSONArray = jDJSONObject.getJSONArray("data");
        if (jSONArray == null || jSONArray.size() == 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < jSONArray.size(); i2++) {
            JDJSONObject jSONObject = jSONArray.getJSONObject(i2);
            if (jSONObject != null) {
                b bVar = new b(jSONObject, jsonString, hVar.Z);
                bVar.f10719i = jSONStringWithDefault;
                arrayList.add(bVar);
            }
        }
        setItemList(arrayList);
        this.mPageCount = 0;
        if (this.mViewConfig.isLineType) {
            parseLineItemList(arrayList);
        } else {
            parsePageItemList(arrayList);
        }
    }

    public void parseViewData(h hVar, JDJSONObject jDJSONObject) {
        ViewConfig viewConfig = this.mViewConfig;
        if (viewConfig == null || !viewConfig.isValid) {
            return;
        }
        this.mHoldScreenType = JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "holdScreenType", 0);
        this.mBgUrl = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "bgPic", "");
        this.mBgColor = m.j(hVar.getJsonString(DYConstants.DY_BG_COLOR), 0);
        this.textColor = m.j(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "fontColor", ""), DEF_TEXT_COLOR);
        this.darkTextColor = m.j(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "darkFontColor", ""), DEF_DARK_TEXT_COLOR);
        this.mPointerSelectedColor = m.j(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "pointerSelectedColor", ""), -381927);
        int j2 = m.j(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "pointerUnselectedColor", ""), -1842205);
        this.mPointerUnselectedColor = j2;
        ViewConfig viewConfig2 = this.mViewConfig;
        this.mPointerUnselectedColor = com.jingdong.app.mall.home.floor.view.b.h.a.b(j2, viewConfig2 == null ? 255 : viewConfig2.nAlpha);
        this.mDarkPointerSelectedColor = m.j(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "darkPointerSelectedColor", ""), -381927);
        int j3 = m.j(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "darkPointerUnselectedColor", ""), -12830150);
        this.mDarkPointerUnselectedColor = j3;
        ViewConfig viewConfig3 = this.mViewConfig;
        this.mDarkPointerUnselectedColor = com.jingdong.app.mall.home.floor.view.b.h.a.b(j3, viewConfig3 != null ? viewConfig3.nAlpha : 255);
        this.mUseClip = JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "turnScreenType", 0) == 1;
    }

    public boolean useClip() {
        return this.mUseClip;
    }

    private static ViewConfig getViewConfig(h hVar) {
        JDJSONObject a;
        ViewConfig viewConfig = new ViewConfig();
        if (hVar != null && (a = hVar.a()) != null) {
            viewConfig.parseViewConfig(a.getJSONObject("viewConfig"));
        }
        return viewConfig;
    }

    public b getItemByPos(int i2, int i3) {
        List<b> list;
        ReadWriteLock readWriteLock = mItemListLock;
        readWriteLock.readLock().lock();
        try {
            List<List<b>> list2 = this.mItemPageList;
            b bVar = (list2 == null || i2 < 0 || i2 >= list2.size() || (list = this.mItemPageList.get(i2)) == null || list.size() <= i3) ? null : list.get(i3);
            readWriteLock.readLock().unlock();
            return bVar;
        } catch (Throwable th) {
            mItemListLock.readLock().unlock();
            throw th;
        }
    }
}
