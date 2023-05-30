package com.jingdong.app.mall.home.floor.model.entity;

import android.graphics.Path;
import android.graphics.Point;
import android.widget.ImageView;
import androidx.annotation.ColorInt;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.common.b;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.common.i.p;
import com.jingdong.common.utils.StringUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
public class FloorEntity {
    private static final int AVERAGE_ITEM_MAX_COUNT = 4;
    protected static final int COVER_HEIGHT_DEFAULT = 0;
    public static final int ITEM_DIVIDER_WIDTH_DEFAULT = 2;
    private static final int RIGHT_CORNER_TEXT_COLOR_RES_DEFAULT = R.color.c_8B8B8B;
    public static final int[] SEPARATION_TITLE_TEXT_COLOR_DEFAULT = {-12985661, -15629569};
    private boolean dataFromCache;
    private boolean isPlayLottie;
    private String mCloseButtonImg;
    private String mCloseLog;
    private String[] mCloseReason;
    private String mCloseTips;
    private String mFloorId;
    private int mFloorPos;
    private boolean mIsShowTitle = false;
    protected String mTitleText = "";
    private boolean mIsSeparationTitle = false;
    private String mTitleImgUrl = "";
    private int mTitleImgDefaultHeight = d.d(70);
    private Point mPtTitleImgSize = new Point(d.d(384), this.mTitleImgDefaultHeight);
    private int mTitleTextSizePx = d.d(32);
    protected int[] mTitleTextColor = {-16777216, -16777216};
    private int mTitleBarHeight = d.d(70);
    private int mTitleCenterHeight = this.mTitleImgDefaultHeight;
    private Point mTitleTextPadding = new Point(0, d.d(10));
    private boolean mHasRightCorner = false;
    private String mRightCornerText = "";
    private int mRightCornerTextColor = -16777216;
    private int mRightCornerTextSizePx = d.d(24);
    private b mRightCornerArrowColor = b.ARROW_COLOR_TYPE_BLACK;
    private String mRightCornerArrowImgUrl = "";
    private boolean mHasCloseButton = false;
    private int mBottomDividerHeight = 0;
    protected int mDividerColor = 0;
    protected int mItemDividerWidth = 0;
    private int mItemDividerColor = 0;
    public int mLayoutHeight = 0;
    private int mLayoutWidth = d.f9279g;
    protected int mLayoutTop = 0;
    protected int mLayoutLeftRightMargin = 0;
    protected volatile int mCoveredHeightDefault = 0;
    protected float[] mShapedFloorRadii = {0.0f, 0.0f, 0.0f, 0.0f};
    protected final List<Path> mItemDividerPaths = new CopyOnWriteArrayList();
    protected final ArrayList<String> mExposData = new ArrayList<>();
    protected final ArrayList<com.jingdong.app.mall.home.r.c.b> mJsonExposData = new ArrayList<>();
    public ImageView.ScaleType mSingleImageType = ImageView.ScaleType.CENTER_CROP;

    public void addItemDividerPath(int i2, boolean z) {
        Path path = new Path();
        int itemDividerWidth = getItemDividerWidth();
        if (!z) {
            itemDividerWidth = getHorizontalItemDividerHeight();
        } else {
            i2 = getLayoutInnerWidth() - i2;
        }
        if (itemDividerWidth <= 0) {
            return;
        }
        int layoutHeight = getLayoutHeight();
        int verticalItemDividerTopOffset = getVerticalItemDividerTopOffset();
        int i3 = i2 + itemDividerWidth;
        if (!z) {
            verticalItemDividerTopOffset = layoutHeight - i2;
            i3 = getLayoutInnerWidth();
            layoutHeight = verticalItemDividerTopOffset + itemDividerWidth;
            i2 = 0;
        }
        int dividerHorizontalOffset = getDividerHorizontalOffset();
        path.addRect(i2 + dividerHorizontalOffset, verticalItemDividerTopOffset, i3 + dividerHorizontalOffset, layoutHeight, Path.Direction.CCW);
        this.mItemDividerPaths.add(path);
    }

    public int getAverageItemCalculateCount(int i2) {
        if (4 < i2 || 4 % i2 != 0) {
            return i2;
        }
        return 4;
    }

    public int getAverageItemCalculateWidth(int i2) {
        int i3;
        int averageItemCalculateCount = getAverageItemCalculateCount(i2);
        int itemDividerWidth = getItemDividerWidth();
        int layoutInnerWidth = (getLayoutInnerWidth() - ((averageItemCalculateCount - 1) * itemDividerWidth)) / averageItemCalculateCount;
        return (averageItemCalculateCount <= i2 || (i3 = averageItemCalculateCount / i2) <= 0) ? layoutInnerWidth : (layoutInnerWidth * i3) + ((i3 - 1) * itemDividerWidth);
    }

    public int getBottomDividerHeight() {
        return this.mBottomDividerHeight;
    }

    public String getCloseButtonImg() {
        return this.mCloseButtonImg;
    }

    public String getCloseLog() {
        return this.mCloseLog;
    }

    public String[] getCloseReason() {
        return this.mCloseReason;
    }

    public String getCloseTips() {
        return this.mCloseTips;
    }

    public int getDividerColor() {
        return this.mDividerColor;
    }

    public int getDividerHorizontalOffset() {
        return this.mLayoutLeftRightMargin;
    }

    public ArrayList<String> getExpoData() {
        return this.mExposData;
    }

    public ArrayList<com.jingdong.app.mall.home.r.c.b> getExpoJson() {
        return null;
    }

    public String getFloorId() {
        return this.mFloorId;
    }

    public int getFloorPos() {
        return this.mFloorPos;
    }

    public int getHorizontalItemDividerHeight() {
        return getBottomDividerHeight();
    }

    public int getItemDividerColor() {
        return this.mItemDividerColor;
    }

    public List<Path> getItemDividerPaths() {
        return this.mItemDividerPaths;
    }

    public int getItemDividerWidth() {
        return this.mItemDividerWidth;
    }

    public int getLayoutHeight() {
        return this.mLayoutHeight;
    }

    public int getLayoutInnerWidth() {
        return getLayoutWidth() - (getLayoutLeftRightMargin() * 2);
    }

    public int getLayoutLeftRightMargin() {
        return this.mLayoutLeftRightMargin;
    }

    public int getLayoutWidth() {
        return this.mLayoutWidth;
    }

    public p.a getModuleParamsAt(int i2) {
        return null;
    }

    public b getRightCornerArrowColor() {
        return this.mRightCornerArrowColor;
    }

    public String getRightCornerArrowImgUrl() {
        return this.mRightCornerArrowImgUrl;
    }

    public String getRightCornerText() {
        return this.mRightCornerText;
    }

    public int getRightCornerTextColor() {
        return this.mRightCornerTextColor;
    }

    public int getRightCornerTextColorResValue() {
        return RIGHT_CORNER_TEXT_COLOR_RES_DEFAULT;
    }

    public int getRightCornerTextSizePx() {
        return this.mRightCornerTextSizePx;
    }

    public p.a.C0288a getSeparationDownloadParams(int i2) {
        p.a moduleParamsAt = getModuleParamsAt(i2);
        return moduleParamsAt == null ? new p.a.C0288a() : moduleParamsAt.Y;
    }

    public ImageView.ScaleType getSeparationSingleBgImgScaleType() {
        return this.mSingleImageType;
    }

    public float[] getShapedFloorRadii() {
        return this.mShapedFloorRadii;
    }

    public int getTitleBarHeight() {
        return this.mTitleBarHeight;
    }

    public int getTitleCenterHeight() {
        return this.mTitleCenterHeight;
    }

    public int getTitleImgDefaultHeight() {
        return this.mTitleImgDefaultHeight;
    }

    public Point getTitleImgSize() {
        return this.mPtTitleImgSize;
    }

    public String getTitleImgUrl() {
        return this.mTitleImgUrl;
    }

    public String getTitleText() {
        return this.mTitleText;
    }

    public int[] getTitleTextColor() {
        return this.mTitleTextColor;
    }

    public Point getTitleTextPadding() {
        return this.mTitleTextPadding;
    }

    public int getTitleTextSizePx() {
        return this.mTitleTextSizePx;
    }

    protected int getVerticalItemDividerTopOffset() {
        return 0;
    }

    public boolean hasCloseButton() {
        return this.mHasCloseButton;
    }

    public boolean hasRightCorner() {
        return this.mHasRightCorner;
    }

    public boolean isDataFromCache() {
        return this.dataFromCache;
    }

    public boolean isPlayLottie() {
        return this.isPlayLottie;
    }

    public boolean isSeparationTitle() {
        return this.mIsSeparationTitle;
    }

    public boolean isShowTitle() {
        return this.mIsShowTitle;
    }

    public boolean isValid() {
        return true;
    }

    public void resetItemDividerPath() {
        this.mItemDividerPaths.clear();
    }

    public void setBottomDividerHeight(int i2) {
        this.mBottomDividerHeight = i2;
    }

    public void setCloseButtonImg(String str) {
        this.mCloseButtonImg = str;
    }

    public void setCloseLog(String str) {
        this.mCloseLog = str;
    }

    public void setCloseReason(String[] strArr) {
        this.mCloseReason = strArr;
    }

    public void setCloseTips(String str) {
        this.mCloseTips = str;
    }

    public void setDataFromCache(boolean z) {
        this.dataFromCache = z;
    }

    public void setDividerColor(@ColorInt int i2) {
        this.mDividerColor = i2;
    }

    public void setFloorId(String str) {
        String g2 = m.g(str);
        if (StringUtil.isEmpty(g2)) {
            return;
        }
        this.mFloorId = g2;
    }

    public void setFloorPos(int i2) {
        this.mFloorPos = i2;
    }

    public void setHasCloseButton(boolean z) {
        this.mHasCloseButton = z;
    }

    public void setHasRightCorner(boolean z) {
        this.mHasRightCorner = z;
    }

    public void setIsSeparationTitle(boolean z) {
        this.mIsSeparationTitle = z;
    }

    public void setIsShowTitle(boolean z) {
        this.mIsShowTitle = z;
    }

    public void setItemDividerColor(int i2) {
        this.mItemDividerColor = i2;
    }

    public void setItemDividerWidth(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        this.mItemDividerWidth = i2;
    }

    public void setLayoutLeftRightMargin(int i2) {
        this.mLayoutLeftRightMargin = i2;
    }

    public void setLayoutLeftRightMarginBy750Design(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        this.mLayoutLeftRightMargin = d.d(i2);
    }

    public void setPlayLottie(boolean z) {
        this.isPlayLottie = z;
    }

    public void setRightCornerArrowColor(b bVar) {
        this.mRightCornerArrowColor = bVar;
    }

    public void setRightCornerArrowImgUrl(String str) {
        this.mRightCornerArrowImgUrl = str;
    }

    public void setRightCornerText(String str) {
        this.mRightCornerText = str;
    }

    public void setRightCornerTextColor(int i2) {
        this.mRightCornerTextColor = i2;
    }

    public void setSeparationSingleBgImgScaleType(ImageView.ScaleType scaleType) {
        this.mSingleImageType = scaleType;
    }

    public void setShapedFloorRadii(float[] fArr) {
        if (fArr == null || fArr.length < 4) {
            return;
        }
        this.mShapedFloorRadii = fArr;
    }

    public void setTitleImgSize(Point point2) {
        if (point2 == null || point2.equals(0, 0)) {
            return;
        }
        this.mPtTitleImgSize = point2;
    }

    public void setTitleImgUrl(String str) {
        this.mTitleImgUrl = str;
    }

    public void setTitleText(String str) {
        this.mTitleText = str;
    }

    public void setTitleTextColor(int[] iArr) {
        if (iArr == null || iArr.length < 1) {
            return;
        }
        this.mTitleTextColor = iArr;
    }
}
