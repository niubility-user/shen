package com.jingdong.app.mall.home.floor.model.entity;

import android.text.TextUtils;
import androidx.core.view.ViewCompat;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.view.b.h.a;
import com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.app.mall.home.r.e.f;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class BubbleBannerV936Entity extends FloorEntity implements ICursorContentViewPresenter {
    private String bgImg;
    private int indicatorSelectColor;
    private int indicatorUnSelectColor;
    private boolean isNeedClipBottom;
    private boolean isShowSubTitle;
    private ArrayList<b> parseExpoJson;
    private int playAnimation;
    private int playAnimationDelay;
    private int playAnimationSpeed;
    private int showRow;
    private ArrayList<f> data = new ArrayList<>();
    private int mCursorWidthSelect = d.d(10);
    private int mCursorWidthUnSelect = d.d(10);
    private int mCursorHeight = d.d(10);
    private int mCursorSpace = d.d(10);
    private int mCursorMarginBottom = d.d(12);

    public void addItemExpo(String str) {
        if (TextUtils.isEmpty(str) || this.mExposData.contains(str)) {
            return;
        }
        this.mExposData.add(str);
    }

    public void addItemExpoJson(b bVar) {
        if (bVar == null || this.mJsonExposData.contains(bVar)) {
            return;
        }
        this.mJsonExposData.add(bVar);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getBannerCursorColor() {
        return this.indicatorUnSelectColor;
    }

    public String getBgImg() {
        return this.bgImg;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorHeight() {
        return this.mCursorHeight;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorMarginBottom() {
        return this.mCursorMarginBottom;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorSelectColor() {
        return this.indicatorSelectColor;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorSpace() {
        return this.mCursorSpace;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorSpaceColor() {
        return ViewCompat.MEASURED_SIZE_MASK;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorWidthUnSelect() {
        return this.mCursorWidthUnSelect;
    }

    public ArrayList<f> getData() {
        return this.data;
    }

    public int getDataSize() {
        return this.data.size();
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public ArrayList<b> getExpoJson() {
        return this.mJsonExposData;
    }

    public boolean getIsShowSubTitle() {
        return this.isShowSubTitle;
    }

    public int getItemColIndex(int i2) {
        return ((i2 / (getShowRow() * 4)) * 4) + ((i2 % (getShowRow() * 4)) % 4);
    }

    public b getItemJson(int i2) {
        ArrayList<b> arrayList = this.parseExpoJson;
        if (arrayList != null && i2 < arrayList.size() && i2 >= 0) {
            return this.parseExpoJson.get(i2);
        }
        return new b();
    }

    public int getItemRowIndex(int i2) {
        return (i2 % (getShowRow() * 4)) / 4;
    }

    public int getPlayAnimation() {
        return this.playAnimation;
    }

    public int getPlayAnimationDelay() {
        return Math.max(this.playAnimationDelay, 1);
    }

    public int getPlayAnimationSpeed() {
        return Math.max(this.playAnimationSpeed, 2);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getSelectWidth() {
        return this.mCursorWidthSelect;
    }

    public int getShowRow() {
        return this.showRow;
    }

    public boolean isNeedClipBottom() {
        return this.isNeedClipBottom;
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public boolean isValid() {
        ArrayList<f> arrayList = this.data;
        return arrayList != null && this.showRow >= 1 && arrayList.size() >= this.showRow * 4;
    }

    public void setBgImg(String str) {
        this.bgImg = str;
    }

    public void setData(ArrayList<f> arrayList) {
        this.data = arrayList;
    }

    public void setIndicatorColor(String str) {
        int[] iArr = {-1, 1308622847};
        int[] n2 = m.n(str, iArr, true);
        if (n2 != null && n2.length >= 2) {
            iArr = n2;
        }
        int[] f2 = a.f(iArr, 255, 77);
        this.indicatorSelectColor = f2[0];
        this.indicatorUnSelectColor = f2[1];
    }

    public void setNeedClipBottom(boolean z) {
        this.isNeedClipBottom = z;
    }

    public void setParseExpoJson(ArrayList<b> arrayList) {
        this.parseExpoJson = arrayList;
    }

    public void setPlayAnimation(int i2) {
        this.playAnimation = i2;
    }

    public void setPlayAnimationDelay(int i2) {
        this.playAnimationDelay = i2;
    }

    public void setPlayAnimationSpeed(int i2) {
        this.playAnimationSpeed = i2;
    }

    public void setShowRow(int i2) {
        this.showRow = i2;
    }

    public void setShowSubTitle(boolean z) {
        this.isShowSubTitle = z;
    }
}
