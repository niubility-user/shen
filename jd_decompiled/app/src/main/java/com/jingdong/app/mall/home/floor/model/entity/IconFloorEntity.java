package com.jingdong.app.mall.home.floor.model.entity;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.app.mall.home.r.e.k.c;
import com.jingdong.app.mall.home.state.dark.a;
import com.jingdong.common.utils.JDJSONObjectWithDefaultUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes4.dex */
public class IconFloorEntity extends FloorEntity implements ICursorContentViewPresenter {
    public static final int BGCOLOR_DEFAULT = -855310;
    public static final int CC1000_ALPHA_12 = 536870911;
    public static final int CC1000_DARK_STYLE_SEL = -381927;
    public static final int CC1000_DARK_STYLE_STROKE = 219222032;
    public static final int CC1000_DARK_STYLE_UN = -12830150;
    public static final int CC1000_LIGHT_STYLE_SEL = -381927;
    public static final int CC1000_LIGHT_STYLE_STROKE = 704643071;
    public static final int CC1000_LIGHT_STYLE_UN = -1842205;
    public static final int ICON_FLOOR_STYLE_V80 = 2;
    private static final String NEED_UPDATE = "1";
    private static final int TEXT_COLOR_DEFAULT = -8092023;
    private static final int TEXT_ELDER_DEFAULT = -13421773;
    private static ReadWriteLock mEntryListLock = new ReentrantReadWriteLock();
    private int iconPadding;
    private boolean isBVersion;
    private boolean isCompress;
    private boolean isDoubleScroll;
    private boolean isElder;
    private boolean isHigh;
    private String isNeedUpdate;
    private boolean isSingleScroll;
    private boolean isSpread;
    private int mClipType;
    private String mDarkFontColor;
    private int mDarkPointerSelectedColor;
    private int mDarkPointerUnselectedColor;
    private JDJSONObject mDataJson;
    private int mGridViewLeftRightPadding;
    private int mGridViewTopPadding;
    private int mHoldScreenType;
    private int mInitFloorHeight;
    protected int mItemHeight;
    private int mPageCount;
    private int mPointerSelectedColor;
    private int mPointerStyle;
    private int mPointerUnselectedColor;
    private int mSecondPageRows;
    private int mShowBi;
    private boolean useAlpha;
    private int mImageWidth = d.d(80);
    private int mImageHeight = d.d(80);
    private int mImageTopMargin = 0;
    private int mTextHeight = -2;
    private int mTextSizePx = 24;
    protected int mItemWidth = -1;
    private int mOnlineTextColor = TEXT_COLOR_DEFAULT;
    private int mLabelTopMargin = 0;
    private int mItemCountPreRow = 5;
    private int mShowLines = 2;
    private int mScrollType = 0;
    private String mBgUrl = "";
    protected List<c> mList = null;
    private final List<List<c>> mIconList = new ArrayList();
    protected int mBgColor = BGCOLOR_DEFAULT;
    private int mBgShadeColor = 0;
    private int mCursorMarginBottom = d.d(20);
    private int mCursorWidthSelect = d.d(10);
    private int mCursorWidthUnSelect = d.d(10);
    private int mCursorHeight = d.d(10);
    private int mCursorSpace = d.d(10);
    private boolean isNeedUpdateView = true;
    private int mStyle = 2;

    public static int getHoldScreenType(h hVar) {
        JDJSONObject a;
        if (hVar == null || (a = hVar.a()) == null) {
            return 0;
        }
        return JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(a, "holdScreenType", 0);
    }

    public static boolean isCompressVer(h hVar) {
        return hVar != null && 5 == hVar.getJsonInt("scrollType", 0);
    }

    public static boolean isHighVersion(h hVar) {
        return hVar != null && 11 == hVar.getJsonInt("scrollType", 0);
    }

    public static boolean isMoreThanOnePage(h hVar) {
        JDJSONObject a;
        JDJSONArray jSONArray;
        return (hVar == null || (a = hVar.a()) == null || (jSONArray = a.getJSONArray("data")) == null || jSONArray.size() <= 5) ? false : true;
    }

    public static boolean isSingleLine(h hVar) {
        if (isSingleScroll(hVar)) {
            return true;
        }
        return (isDoubleScroll(hVar) || isHighVersion(hVar) || hVar == null || 1 != hVar.getJsonInt("showLines", 2)) ? false : true;
    }

    public c getAppEntryByPos(int i2) {
        mEntryListLock.readLock().lock();
        try {
            List<c> list = this.mList;
            return (list == null || list.size() <= i2) ? null : this.mList.get(i2);
        } finally {
            mEntryListLock.readLock().unlock();
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getBannerCursorColor() {
        return -1;
    }

    public int getBgColor() {
        return this.mBgColor;
    }

    public int getBgShadeColor() {
        return this.mBgShadeColor;
    }

    public String getBgUrl() {
        if (a.h()) {
            return com.jingdong.app.mall.home.floor.ctrl.h.N().U() ? this.mBgUrl : "";
        }
        return this.mBgUrl;
    }

    public int getClipType() {
        return this.mClipType;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorHeight() {
        return this.mCursorHeight;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorMarginBottom() {
        if (!this.isElder && !this.isCompress && !this.isSpread && !this.isBVersion && !this.isHigh) {
            if (this.mHoldScreenType == 2 && this.mShowLines == 2) {
                return d.d(20) + this.mCursorMarginBottom;
            }
            return this.mCursorMarginBottom;
        }
        return this.mCursorMarginBottom;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorSelectColor() {
        return -1;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorSpace() {
        return this.mCursorSpace;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorSpaceColor() {
        return -1;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorWidthUnSelect() {
        return this.mCursorWidthUnSelect;
    }

    public String getDarkFontColor() {
        return this.mDarkFontColor;
    }

    public int getDarkPointerSelectedColor() {
        return this.mDarkPointerSelectedColor;
    }

    public int getDarkPointerStrokeColor() {
        return 219222032;
    }

    public int getDarkPointerUnselectedColor() {
        return this.mDarkPointerUnselectedColor;
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public int getDividerHorizontalOffset() {
        return 0;
    }

    public List<c> getEntryList() {
        return this.mList;
    }

    public int getGridViewLeftRightPadding() {
        return this.mGridViewLeftRightPadding;
    }

    public int getGridViewTopPadding() {
        return this.mGridViewTopPadding;
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public int getHorizontalItemDividerHeight() {
        return getItemDividerWidth();
    }

    public c getIconData(int i2, int i3) {
        List<c> list;
        c cVar;
        mEntryListLock.readLock().lock();
        if (i2 >= 0) {
            try {
                if (i2 < this.mIconList.size() && (list = this.mIconList.get(i2)) != null && list.size() > i3) {
                    cVar = list.get(i3);
                    return cVar;
                }
            } finally {
                mEntryListLock.readLock().unlock();
            }
        }
        cVar = null;
        return cVar;
    }

    public int getIconFloorStyle() {
        return this.mStyle;
    }

    public int getIconPadding() {
        return this.iconPadding;
    }

    public int getIconRealCount() {
        mEntryListLock.readLock().lock();
        try {
            List<c> list = this.mList;
            return list != null ? list.size() : 0;
        } finally {
            mEntryListLock.readLock().unlock();
        }
    }

    public int getIconSizePerPage(int i2) {
        List<c> list;
        int size;
        mEntryListLock.readLock().lock();
        if (i2 >= 0) {
            try {
                if (i2 < this.mIconList.size() && (list = this.mIconList.get(i2)) != null) {
                    size = list.size();
                    return size;
                }
            } finally {
                mEntryListLock.readLock().unlock();
            }
        }
        size = 0;
        return size;
    }

    public int getImageHeight() {
        return this.mImageHeight;
    }

    public int getImageTopMargin() {
        return this.mImageTopMargin;
    }

    public int getImageWidth() {
        return this.mImageWidth;
    }

    public int getInitFloorHeight() {
        return this.mInitFloorHeight;
    }

    public boolean getIsNeedUpdate() {
        return "1".equals(this.isNeedUpdate);
    }

    public int getItemCountPreRow() {
        return this.mItemCountPreRow;
    }

    public int getItemHeight() {
        return this.mItemHeight;
    }

    public int getItemWidth() {
        return this.mItemWidth;
    }

    public int getLabelTopMargin() {
        return this.mLabelTopMargin;
    }

    public int getMaxIconItemCount() {
        if (this.isDoubleScroll) {
            return 12;
        }
        return this.mItemCountPreRow * this.mShowLines;
    }

    public int getPageCount() {
        return this.mPageCount;
    }

    public int getPointerSelectedColor() {
        return this.mPointerSelectedColor;
    }

    public int getPointerStrokeColor() {
        return 704643071;
    }

    public int getPointerUnselectedColor() {
        return this.mPointerUnselectedColor;
    }

    public int getScrollType() {
        return this.mScrollType;
    }

    public int getSecondPageRows() {
        return this.mSecondPageRows;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getSelectWidth() {
        return this.mCursorWidthSelect;
    }

    public int getShowBi() {
        return this.mShowBi;
    }

    public int getShowLines() {
        return this.mShowLines;
    }

    public int getTextColor() {
        return this.mOnlineTextColor;
    }

    public int getTextHeight() {
        return this.mTextHeight;
    }

    public int getTextSizePx() {
        return this.mTextSizePx;
    }

    public boolean isBVersion() {
        return this.isBVersion;
    }

    public boolean isCompress() {
        return this.isCompress;
    }

    public boolean isDoubleScroll() {
        return this.isDoubleScroll;
    }

    public boolean isElder() {
        return this.isElder;
    }

    public boolean isHigh() {
        return this.isHigh;
    }

    public boolean isIconDataValid() {
        boolean z;
        mEntryListLock.readLock().lock();
        try {
            List<c> list = this.mList;
            if (list != null) {
                if (list.size() >= getMaxIconItemCount()) {
                    z = true;
                    return z;
                }
            }
            z = false;
            return z;
        } finally {
            mEntryListLock.readLock().unlock();
        }
    }

    public boolean isNeedUpdateView() {
        return this.isNeedUpdateView;
    }

    public boolean isNewPointerStyle() {
        return this.mPointerStyle == 1;
    }

    public boolean isSingleScroll() {
        return this.isSingleScroll;
    }

    public boolean isSpread() {
        return this.isSpread;
    }

    public boolean isSupportLabel() {
        return this.isCompress || this.isBVersion || this.isSingleScroll || this.isDoubleScroll || this.isHigh;
    }

    public boolean needUpDate(JDJSONObject jDJSONObject) {
        return this.mDataJson == null || jDJSONObject == null || !TextUtils.equals(jDJSONObject.toString(), this.mDataJson.toString());
    }

    public void setAppEntryList(List<c> list) {
        mEntryListLock.writeLock().lock();
        try {
            if (this.mList == null) {
                this.mList = new ArrayList();
            }
            this.mList.clear();
            if (list != null) {
                this.mList.addAll(list);
            }
        } finally {
            mEntryListLock.writeLock().unlock();
        }
    }

    public void setBgColor(int i2) {
        if (!this.isSingleScroll && !this.isDoubleScroll) {
            if (a.h() && this.mHoldScreenType == 0) {
                i2 = 0;
            }
            this.mBgColor = i2;
            return;
        }
        this.mBgColor = i2;
    }

    public void setBgShadeColor(int i2) {
        this.mBgShadeColor = i2;
    }

    public void setBgUrl(String str) {
        this.mBgUrl = m.g(str);
    }

    public void setClipType(int i2) {
        this.mClipType = i2;
    }

    public void setCursorSize() {
        this.mCursorWidthSelect = d.d(18);
        this.mCursorWidthUnSelect = d.d(10);
        int i2 = 8;
        this.mCursorHeight = d.d((this.isCompress || this.isSpread) ? 8 : 10);
        this.mCursorSpace = d.d(6);
        if (!this.isCompress && !this.isSpread && !this.isBVersion) {
            i2 = 12;
        }
        this.mCursorMarginBottom = d.d(i2);
    }

    public void setDarkFontColor(String str) {
        this.mDarkFontColor = str;
    }

    public void setDarkPointerSelectedColor(String str) {
        this.mDarkPointerSelectedColor = m.j(str, -381927);
    }

    public void setDarkPointerUnselectedColor(String str) {
        this.mDarkPointerUnselectedColor = m.j(str, -12830150);
        if (usePointerAlpha()) {
            this.mDarkPointerUnselectedColor = 536870911 & this.mDarkPointerUnselectedColor;
        }
    }

    public void setDataJson(JDJSONObject jDJSONObject) {
        this.mDataJson = jDJSONObject;
    }

    public void setHoldScreenType(int i2) {
        this.mHoldScreenType = i2;
    }

    public void setIconFloorBVersionStyle() {
        this.mImageWidth = d.d(80);
        this.mImageHeight = d.d(80);
        this.mTextSizePx = 24;
        this.mTextHeight = d.d(32);
        this.mItemHeight = d.d(112);
        this.mGridViewLeftRightPadding = d.d(20);
        this.mGridViewTopPadding = d.d(8);
        this.mCursorWidthSelect = d.d(16);
        this.mCursorWidthUnSelect = d.d(8);
        this.mCursorHeight = d.d(8);
        this.mCursorSpace = d.d(8);
        this.mCursorMarginBottom = d.d(8);
        this.mImageTopMargin = d.d(0);
        this.mLabelTopMargin = 0;
    }

    public void setIconFloorDefaultStyle() {
        this.mImageWidth = d.d(80);
        this.mImageHeight = d.d(80);
        this.mTextSizePx = 24;
        this.mTextHeight = d.d((this.isCompress || this.isSpread) ? 32 : 53);
        this.mItemHeight = d.d((this.isCompress || this.isSpread) ? 118 : R2.anim.lib_cashier_sdk_pop_in_animation_bottom);
        this.mGridViewLeftRightPadding = d.d(9);
        int i2 = 4;
        this.mGridViewTopPadding = d.d((this.isCompress || this.isSpread) ? 4 : 0);
        this.mCursorWidthSelect = d.d(10);
        this.mCursorWidthUnSelect = d.d(10);
        this.mCursorHeight = d.d(10);
        this.mCursorSpace = d.d(10);
        this.mCursorMarginBottom = d.d((this.isCompress || this.isSpread) ? 8 : 12);
        if (!this.isCompress && !this.isSpread) {
            i2 = 10;
        }
        this.mImageTopMargin = d.d(i2);
        this.mLabelTopMargin = 0;
    }

    public void setIconFloorDoubleScrollStyle() {
        this.mImageWidth = d.d(80);
        this.mImageHeight = d.d(80);
        this.mTextSizePx = 24;
        this.mTextHeight = d.d(32);
        this.mItemHeight = d.d(R2.anim.lib_cashier_sdk_fragment_right_out);
        this.mItemWidth = d.d(132);
        this.mGridViewLeftRightPadding = d.d(16);
        this.mGridViewTopPadding = d.d(4);
        this.mCursorMarginBottom = d.d(8);
        this.mImageTopMargin = d.d(20);
        this.mLabelTopMargin = 12;
    }

    public void setIconFloorElderStyle() {
        this.mTextSizePx = 36;
        this.mTextHeight = d.d(57);
        this.mItemHeight = d.d(R2.anim.pop_right_top_in);
        this.mCursorWidthSelect = d.d(0);
        this.mCursorWidthUnSelect = d.d(0);
        this.mCursorHeight = d.d(0);
        this.mCursorSpace = d.d(0);
        this.mCursorMarginBottom = d.d(0);
        this.mImageWidth = d.d(106);
        this.mImageHeight = d.d(106);
        this.mImageTopMargin = d.d(11);
        this.mGridViewLeftRightPadding = d.d(this.mScrollType == 3 ? 9 : 15);
        this.mGridViewTopPadding = 0;
    }

    public void setIconFloorHighStyle() {
        this.mImageWidth = d.d(80);
        this.mImageHeight = d.d(80);
        this.mTextSizePx = 24;
        this.mTextHeight = d.d(40);
        this.mItemHeight = d.d(128);
        this.mGridViewLeftRightPadding = d.d(26);
        this.mGridViewTopPadding = d.d(16);
        this.mCursorMarginBottom = d.d(12);
        this.mCursorWidthSelect = d.d(20);
        this.mCursorWidthUnSelect = d.d(8);
        this.mCursorHeight = d.d(8);
        this.mCursorSpace = d.d(8);
        this.mImageTopMargin = d.d(8);
        this.mLabelTopMargin = 0;
    }

    public void setIconFloorSingleScrollStyle() {
        this.mImageWidth = d.d(80);
        this.mImageHeight = d.d(80);
        this.mTextSizePx = 26;
        this.mTextHeight = d.d(32);
        this.mItemHeight = d.d(132);
        this.mItemWidth = d.d(R2.anim.manto_translate_dialog_in);
        this.mGridViewLeftRightPadding = d.d(20);
        this.mGridViewTopPadding = 0;
        this.mCursorMarginBottom = d.d(8);
        this.mImageTopMargin = d.d(18);
        this.mLabelTopMargin = 10;
    }

    public void setIconFloorStyle() {
        if (this.isElder) {
            setIconFloorElderStyle();
            setItemCountPreRow(this.mScrollType != 3 ? 4 : 3);
        } else if (this.isBVersion) {
            setIconFloorBVersionStyle();
        } else if (this.isSingleScroll) {
            setIconFloorSingleScrollStyle();
        } else if (this.isDoubleScroll) {
            setIconFloorDoubleScrollStyle();
        } else if (this.isHigh) {
            setIconFloorHighStyle();
        } else {
            setIconFloorDefaultStyle();
        }
    }

    public void setIconList(List<List<c>> list) {
        mEntryListLock.writeLock().lock();
        try {
            this.mIconList.clear();
            if (list != null) {
                this.mIconList.addAll(list);
            }
        } finally {
            mEntryListLock.writeLock().unlock();
        }
    }

    public void setIconPadding(h hVar) {
        this.iconPadding = hVar.getJsonInt("iconPadding", this.isSingleScroll ? 2 : 8);
    }

    public void setInitFloorHeight(int i2) {
        this.mInitFloorHeight = i2;
    }

    public void setIsNeedUpdate(String str) {
        this.isNeedUpdate = str;
    }

    public void setItemCountPreRow(int i2) {
        if (i2 < 1) {
            return;
        }
        this.mItemCountPreRow = i2;
    }

    public void setNeedUpdateView(boolean z) {
        this.isNeedUpdateView = z;
    }

    public void setOnlineTextColor(String str) {
        this.mOnlineTextColor = m.j(str, this.isElder ? TEXT_ELDER_DEFAULT : TEXT_COLOR_DEFAULT);
    }

    public void setPageCount(int i2) {
        this.mPageCount = i2;
    }

    public void setPointerSelectedColor(String str) {
        this.mPointerSelectedColor = m.j(str, -381927);
    }

    public void setPointerStyle(int i2) {
        this.mPointerStyle = i2;
    }

    public void setPointerUnselectedColor(String str) {
        this.mPointerUnselectedColor = m.j(str, -1842205);
        if (usePointerAlpha()) {
            this.mPointerUnselectedColor = 536870911 & this.mPointerUnselectedColor;
        }
    }

    public void setScrollType(int i2) {
        switch (i2) {
            case 3:
            case 4:
            case 5:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                this.mScrollType = i2;
                break;
            case 6:
            default:
                this.mScrollType = 0;
                break;
        }
        this.isElder = i2 == 3 || i2 == 4;
        boolean z = i2 == 5;
        this.isCompress = z;
        boolean z2 = i2 == 7;
        this.isSpread = z2;
        boolean z3 = i2 == 8;
        this.isBVersion = z3;
        boolean z4 = i2 == 9;
        this.isSingleScroll = z4;
        boolean z5 = i2 == 10;
        this.isDoubleScroll = z5;
        boolean z6 = i2 == 11;
        this.isHigh = z6;
        this.useAlpha = this.mScrollType == 0 || z || z2 || z3 || z4 || z5 || z6;
    }

    public void setSecondPageRows(int i2) {
        this.mSecondPageRows = i2;
    }

    public void setShowBi(int i2) {
        this.mShowBi = i2;
    }

    public void setShowLines(int i2) {
        this.mShowLines = i2;
    }

    public boolean usePointerAlpha() {
        return isNewPointerStyle() && this.useAlpha;
    }

    public static boolean isBVersion(h hVar) {
        return hVar != null && 8 == hVar.getJsonInt("scrollType", 0);
    }

    public static boolean isDoubleScroll(h hVar) {
        return hVar != null && 10 == hVar.getJsonInt("scrollType", 0);
    }

    public static boolean isSingleScroll(h hVar) {
        return hVar != null && 9 == hVar.getJsonInt("scrollType", 0);
    }

    public int getHoldScreenType() {
        return this.mHoldScreenType;
    }
}
