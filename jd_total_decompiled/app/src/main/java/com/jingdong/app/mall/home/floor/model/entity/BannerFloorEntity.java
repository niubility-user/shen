package com.jingdong.app.mall.home.floor.model.entity;

import android.text.TextUtils;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter;
import com.jingdong.app.mall.home.floor.view.view.module.MallFloorBannerItem;
import com.jingdong.app.mall.home.q.a;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.app.mall.home.r.e.f;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
public class BannerFloorEntity extends ListItemFloorEntity<f> implements ICursorContentViewPresenter {
    public static final int BANNER_CURSOR_HEIGHT_V100 = 12;
    public static final int BANNER_CURSOR_SELECT_WIDTH_V100 = 24;
    public static final int BANNER_CURSOR_SPACE_V100 = 8;
    public static final int BANNER_CURSOR_WIDTH_V100 = 12;
    public static final String BANNER_ID = "banner";
    private static final int VIEW_CHANGE_INTERVAL_MIN_DEFAULT = 2000;
    private int entranceAnimation;
    protected String img2;
    protected String img3;
    public boolean isNewIndicatorType;
    private boolean mChangeUrl;
    public ArrayList<MallFloorBannerItem> mCommercialList;
    private String mExtensionId;
    protected String mModelId;
    private int mTestTag;
    private int newIndicatorSelectColor;
    private int newIndicatorUnSelectColor;
    private final int BANNER_CURSOR_WIDTH = 16;
    private final int BANNER_SELECT_WIDTH = 24;
    private final int BANNER_CURSOR_HEIGHT = 5;
    private final int BANNER_CURSOR_SPACE = 5;
    public final int DEF_CAROUSEL_FIGURE_RADIUS = 24;
    private int mCursorWidth = 10;
    private int mCursorHeight = 10;
    private int mCursorSpace = 6;
    private int mBannerCursorWidth = d.d(16);
    private int mBannerCursorHeight = d.d(5);
    private int mBannerCursorSpace = d.d(5);
    public boolean isFirstExpoed = false;
    private int viewChangeInterval = 4000;
    protected boolean mIsCarousel = true;
    protected boolean mIsAutoPlay = true;
    protected int mScrollDuration = 0;
    protected String eventId = "";
    protected String mSlideEventId = "";
    private CopyOnWriteArrayList<String> mSourceValues = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<b> mSrvJsonList = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<a> mExpoList = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<String> mExpoSalUrls = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<String> mExtensionIds = new CopyOnWriteArrayList<>();

    /* loaded from: classes4.dex */
    public static class VariaModel {
        public long allDisplayTime;
        public float displayRatio;
        public long startDisplayTime;
    }

    public static int getBannerCoveredHeight() {
        return 0;
    }

    public void addExpoJson(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mJsonExposData.add(b.c(str));
    }

    public synchronized void checkStyle() {
        this.mCoveredHeightDefault = getBannerCoveredHeight();
        this.mBannerCursorWidth = d.d(10);
        this.mBannerCursorHeight = d.d(10);
        this.mBannerCursorSpace = d.d(12);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getBannerCursorColor() {
        if (this.isNewIndicatorType) {
            return this.newIndicatorUnSelectColor;
        }
        return 1442840575;
    }

    public int getBannerCursorHeight() {
        return this.isNewIndicatorType ? d.d(12) : this.mBannerCursorHeight;
    }

    public int getBannerCursorMarginBottom() {
        return d.d(this.isNewIndicatorType ? 22 : 20);
    }

    public int getBannerCursorSpace() {
        return this.isNewIndicatorType ? d.d(8) : this.mBannerCursorSpace;
    }

    public int getBannerCursorWidth() {
        return this.isNewIndicatorType ? d.d(12) : this.mBannerCursorWidth;
    }

    public int getBannerSelectWidth() {
        return this.isNewIndicatorType ? d.d(24) : this.mBannerCursorWidth;
    }

    public boolean getChangeUrl() {
        return true;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorHeight() {
        return d.d(this.mCursorHeight);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorMarginBottom() {
        return d.d(10);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorSelectColor() {
        if (this.isNewIndicatorType) {
            return this.newIndicatorSelectColor;
        }
        return -381927;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorSpace() {
        return d.d(this.mCursorSpace);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorSpaceColor() {
        return this.isNewIndicatorType ? 704643071 : 0;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorWidthUnSelect() {
        return d.d(this.mCursorWidth);
    }

    public int getEntranceAnimation() {
        return this.entranceAnimation;
    }

    public String getEventId() {
        return this.eventId;
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public ArrayList<String> getExpoData() {
        if (this.mLsItems == null) {
            return super.getExpoData();
        }
        this.mExposData.clear();
        for (int i2 = 0; i2 < this.mLsItems.size(); i2++) {
            this.mExposData.add(((f) this.mLsItems.get(i2)).j());
        }
        return this.mExposData;
    }

    public String getExpoExtensionId(int i2) {
        return (i2 < 0 || i2 >= this.mExtensionIds.size()) ? "" : this.mExtensionIds.get(i2);
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public ArrayList<b> getExpoJson() {
        return this.mJsonExposData;
    }

    public String getExpoSalUrl(int i2) {
        if (i2 >= 0) {
            try {
                return i2 < this.mExpoSalUrls.size() ? this.mExpoSalUrls.get(i2) : "";
            } catch (Exception e2) {
                com.jingdong.app.mall.home.o.a.f.s0(this, e2);
                return "";
            }
        }
        return "";
    }

    public int getExpoSalUrlSize() {
        CopyOnWriteArrayList<String> copyOnWriteArrayList = this.mExpoSalUrls;
        if (copyOnWriteArrayList == null) {
            return 0;
        }
        return copyOnWriteArrayList.size();
    }

    public String getExtensionId() {
        return this.mExtensionId;
    }

    public String getImg2() {
        return this.img2;
    }

    public String getImg3() {
        return this.img3;
    }

    public String getModelId() {
        return this.mModelId;
    }

    public int getScrollDuration() {
        return this.mScrollDuration;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getSelectWidth() {
        return d.d(this.mCursorWidth);
    }

    public String getSlideEventId() {
        return this.mSlideEventId;
    }

    public String getSourceValue(int i2) {
        if (i2 >= 0) {
            try {
                return i2 < this.mSourceValues.size() ? this.mSourceValues.get(i2) : "";
            } catch (Exception e2) {
                com.jingdong.app.mall.home.o.a.f.s0(this, e2);
                return "";
            }
        }
        return "";
    }

    public b getSrvJson(int i2) {
        if (i2 >= 0) {
            try {
                if (i2 < this.mSrvJsonList.size()) {
                    return this.mSrvJsonList.get(i2);
                }
                return null;
            } catch (Exception e2) {
                com.jingdong.app.mall.home.o.a.f.s0(this, e2);
                return null;
            }
        }
        return null;
    }

    public CopyOnWriteArrayList<b> getSrvJsonList() {
        return this.mSrvJsonList;
    }

    public int getViewChangeInterval() {
        int i2 = this.viewChangeInterval;
        if (i2 < 2000) {
            return 2000;
        }
        return i2;
    }

    public boolean isAutoPlay() {
        return this.mIsAutoPlay;
    }

    public boolean isCarousel() {
        return this.mIsCarousel;
    }

    public boolean isOpen103() {
        return true;
    }

    public void postExpoLog(int i2) {
        try {
            if (!isDataFromCache() && i2 >= 0 && i2 < this.mExpoList.size()) {
                this.mExpoList.get(i2).b();
            }
        } catch (Exception e2) {
            com.jingdong.app.mall.home.o.a.f.s0(this, e2);
        }
    }

    public void setCommercialList(ArrayList<MallFloorBannerItem> arrayList) {
        this.mCommercialList = arrayList;
    }

    public void setEntranceAnimation(int i2) {
        this.entranceAnimation = i2;
    }

    public void setEventId(String str) {
        this.eventId = m.g(str);
    }

    public void setExpoExtensionIds(List<String> list) {
        this.mExtensionIds.clear();
        this.mExtensionIds.addAll(list);
    }

    public void setExpoLogs(ArrayList<a> arrayList) {
        this.mExpoList.clear();
        this.mExpoList.addAll(arrayList);
    }

    public void setExpoSalUrls(ArrayList<String> arrayList) {
        this.mExpoSalUrls.clear();
        this.mExpoSalUrls.addAll(arrayList);
    }

    public void setExtensionId(String str) {
        this.mExtensionId = str;
    }

    public void setImg2(String str) {
        this.img2 = str;
    }

    public void setImg3(String str) {
        this.img3 = str;
    }

    public void setIndicatorColor(String str) {
        int[] iArr = {-381927, 2013265919};
        int[] n2 = m.n(str, iArr, true);
        if (n2 != null && n2.length >= 2) {
            iArr = n2;
        }
        int[] iArr2 = new int[2];
        iArr2[0] = 255;
        iArr2[1] = this.isNewIndicatorType ? 31 : 119;
        int[] f2 = com.jingdong.app.mall.home.floor.view.b.h.a.f(iArr, iArr2);
        this.newIndicatorSelectColor = f2[0];
        this.newIndicatorUnSelectColor = f2[1];
    }

    public void setIndicatorType(int i2) {
        this.isNewIndicatorType = i2 == 2;
    }

    public void setModelId(String str) {
        this.mModelId = str;
    }

    public void setScrollDuration(int i2) {
        if (i2 < 0) {
            return;
        }
        this.mScrollDuration = i2;
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public void setShapedFloorRadii(float[] fArr) {
        if (fArr != null && fArr.length >= 4) {
            this.mShapedFloorRadii = fArr;
        } else {
            Arrays.fill(this.mShapedFloorRadii, d.d(24));
        }
    }

    public void setSlideEventId(String str) {
        this.mSlideEventId = str;
    }

    public void setSourceValues(ArrayList<String> arrayList) {
        this.mSourceValues.clear();
        this.mSourceValues.addAll(arrayList);
    }

    public void setSrvJsonList(ArrayList<b> arrayList) {
        this.mSrvJsonList.clear();
        this.mSrvJsonList.addAll(arrayList);
    }

    public void setViewChangeInterval(int i2) {
        this.viewChangeInterval = i2;
    }
}
