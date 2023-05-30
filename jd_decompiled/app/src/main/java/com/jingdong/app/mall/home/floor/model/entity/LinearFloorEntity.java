package com.jingdong.app.mall.home.floor.model.entity;

import android.text.TextUtils;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.p;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.app.mall.home.r.c.c;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.g;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes4.dex */
public class LinearFloorEntity extends FloorEntity {
    public static final String LAYERSHOWTIMES = "layerShowTimes";
    private boolean hasSkuAnimation;
    private int layerRemain;
    private int layerShowTimes;
    private int mDisplayUiStyle;
    private c specialExpo;
    private String subFloorBgImg;
    private boolean supportLayerBg;
    private boolean useBgMarginColor;
    protected int mItemCount = 1;
    protected int mItemPadding = 0;
    protected ArrayList<Integer> mItemsWidths = new ArrayList<>();
    protected int mItemWeightCount = 0;
    protected List<f> mItemElements = new CopyOnWriteArrayList();
    protected ReentrantReadWriteLock mItemElementsLock = new ReentrantReadWriteLock();
    private final int MAX_NUM = 6;
    private p.a[] itemParams = new p.a[6];

    public LinearFloorEntity() {
        this.mItemDividerWidth = d.d(1);
    }

    public void addExpoJson(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mJsonExposData.add(b.c(str));
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public int getAverageItemCalculateCount(int i2) {
        int itemWeightCount = getItemWeightCount();
        if (itemWeightCount > 0) {
            i2 = itemWeightCount;
        }
        return super.getAverageItemCalculateCount(i2);
    }

    public int getDisplayUiStyle() {
        return this.mDisplayUiStyle;
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public ArrayList<String> getExpoData() {
        if (this.mItemElements == null) {
            return super.getExpoData();
        }
        this.mExposData.clear();
        for (int i2 = 0; i2 < this.mItemElements.size(); i2++) {
            this.mExposData.add(this.mItemElements.get(i2).j());
        }
        return this.mExposData;
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public ArrayList<b> getExpoJson() {
        return this.mJsonExposData;
    }

    public List<f> getFloorItemElements() {
        return this.mItemElements;
    }

    public f getItemElement(int i2) {
        this.mItemElementsLock.readLock().lock();
        try {
            List<f> list = this.mItemElements;
            return (list == null || i2 < 0 || i2 >= list.size()) ? null : this.mItemElements.get(i2);
        } finally {
            this.mItemElementsLock.readLock().unlock();
        }
    }

    public int getItemViewCount() {
        return this.mItemCount;
    }

    public int getItemWeightCount() {
        return this.mItemWeightCount;
    }

    public int getItemWidth(int i2) {
        if (i2 < 0 || i2 >= this.mItemsWidths.size()) {
            return 0;
        }
        return this.mItemsWidths.get(i2).intValue();
    }

    public int getLayerRemain() {
        int i2 = this.layerRemain;
        if (i2 > 0) {
            return i2;
        }
        return 4;
    }

    public int getLayerShowTimes() {
        return this.layerShowTimes;
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public synchronized p.a getModuleParamsAt(int i2) {
        if (i2 >= 6) {
            return null;
        }
        p.a aVar = this.itemParams[i2];
        if (aVar == null) {
            aVar = new p.a();
            this.itemParams[i2] = aVar;
        }
        return aVar;
    }

    public c getSpecialExpo() {
        return this.specialExpo;
    }

    public String getSubFloorBgImg() {
        return this.subFloorBgImg;
    }

    public boolean isHasSkuAnimation() {
        return this.hasSkuAnimation;
    }

    public boolean isHaveItemWidths() {
        return !this.mItemsWidths.isEmpty();
    }

    public boolean isSupportLayerBg() {
        return this.supportLayerBg;
    }

    public void setDisplayUiStyle(int i2) {
        this.mDisplayUiStyle = i2;
    }

    public void setFloorItemElements(g gVar) {
        this.mItemElementsLock.writeLock().lock();
        try {
            this.mItemElements.clear();
            if (gVar != null) {
                this.mItemElements.addAll(gVar.a());
            }
        } finally {
            this.mItemElementsLock.writeLock().unlock();
        }
    }

    public void setHasSkuAnimation(boolean z) {
        this.hasSkuAnimation = z;
    }

    public void setItemCount(int i2) {
        if (i2 < 1) {
            return;
        }
        this.mItemCount = i2;
    }

    public void setItemPadding(int i2) {
        if (i2 < 0) {
            return;
        }
        this.mItemPadding = i2;
    }

    public void setItemWeightCount(int i2) {
        this.mItemWeightCount = i2;
    }

    public void setItemsWidth(ArrayList<Integer> arrayList) {
        if (arrayList != null) {
            this.mItemsWidths.clear();
            this.mItemsWidths.addAll(arrayList);
        }
    }

    public void setLayerRemain(int i2) {
        this.layerRemain = i2;
    }

    public void setLayerShowTimes(int i2) {
        this.layerShowTimes = i2;
    }

    public void setSpecialExpo(c cVar) {
        this.specialExpo = cVar;
    }

    public void setSubFloorBgImg(String str) {
        this.subFloorBgImg = str;
    }

    public void setSupportLayerBg(boolean z) {
        this.supportLayerBg = z;
    }

    public void setUseBgMarginColor(boolean z) {
        this.useBgMarginColor = z;
    }

    public boolean useBgMarginColor() {
        return this.useBgMarginColor;
    }
}
