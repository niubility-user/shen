package com.jingdong.app.mall.home.floor.model.entity;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.common.entity.JumpEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes4.dex */
public class ListItemFloorEntity<T> extends FloorEntity {
    private static final int CONTENT_HEIGHT = 240;
    private static final int MIN_WIDTH = 160;
    public static final int VIEW_CHANGE_INTERVAL = 4000;
    public f element;
    public JumpEntity jumpEntity;
    private String mAdvertImg;
    private JumpEntity mAdvertJump;
    public h mModel;
    private String showNameImg;
    public String tpl;
    private static ReadWriteLock mLsItemTmpListLock = new ReentrantReadWriteLock();
    private static ReadWriteLock mLsItemListLock = new ReentrantReadWriteLock();
    protected int mListItemCountLimit = 0;
    private int mContentWidth = d.d(160);
    private boolean mIsHaveAdvert = false;
    protected List<T> mLsItems = new ArrayList();
    protected List<T> mLsItemTmp = new ArrayList();
    protected ListItemFloorEntity<T>.MaiDianData mMaiDianData = new MaiDianData();

    /* loaded from: classes4.dex */
    public class MaiDianData {
        protected String rcSourceValue;
        protected String sourceValue;

        public MaiDianData() {
        }

        public void setData(String str, String str2) {
            if (str == null) {
                str = "";
            }
            this.rcSourceValue = str;
            if (str2 == null) {
                str2 = "";
            }
            this.sourceValue = str2;
        }
    }

    public String getAdvertImgUrl() {
        return this.mAdvertImg;
    }

    public JumpEntity getAdvertJump() {
        return this.mAdvertJump;
    }

    public int getContentHeight() {
        return d.d(240);
    }

    public int getContentWidth() {
        return this.mContentWidth;
    }

    public T getItemByPosition(int i2) {
        mLsItemListLock.readLock().lock();
        try {
            return getItemListSize() > i2 ? this.mLsItems.get(i2) : null;
        } finally {
            mLsItemListLock.readLock().unlock();
        }
    }

    public T getItemByTmpPosition(int i2) {
        T t;
        mLsItemTmpListLock.readLock().lock();
        if (i2 >= 0) {
            try {
                if (i2 < this.mLsItemTmp.size()) {
                    t = this.mLsItemTmp.get(i2);
                    return t;
                }
            } finally {
                mLsItemTmpListLock.readLock().unlock();
            }
        }
        t = null;
        return t;
    }

    public List<T> getItemList() {
        return this.mLsItems;
    }

    public int getItemListSize() {
        mLsItemListLock.readLock().lock();
        try {
            List<T> list = this.mLsItems;
            return list != null ? list.size() : 0;
        } finally {
            mLsItemListLock.readLock().unlock();
        }
    }

    public int getItemTmpListSize() {
        mLsItemTmpListLock.readLock().lock();
        try {
            List<T> list = this.mLsItemTmp;
            return list != null ? list.size() : 0;
        } finally {
            mLsItemTmpListLock.readLock().unlock();
        }
    }

    public Object getLastItem() {
        Object obj = 0L;
        mLsItemListLock.readLock().lock();
        try {
            if (!isItemListEmpty()) {
                obj = getListLastItem(this.mLsItems);
            }
            return obj;
        } finally {
            mLsItemListLock.readLock().unlock();
        }
    }

    public int getListItemCountLimit() {
        return this.mListItemCountLimit;
    }

    public T getListLastItem(List<T> list) {
        if (isListEmpty(list)) {
            return null;
        }
        return list.get(list.size() - 1);
    }

    public String getMaiDianSourceValue(boolean z) {
        return z ? this.mMaiDianData.rcSourceValue : this.mMaiDianData.sourceValue;
    }

    public String getShowNameImg() {
        return this.showNameImg;
    }

    public int getTmpItemListSize() {
        mLsItemTmpListLock.readLock().lock();
        try {
            return this.mLsItemTmp.size();
        } finally {
            mLsItemTmpListLock.readLock().unlock();
        }
    }

    public boolean isHaveAdvert() {
        return this.mIsHaveAdvert && !TextUtils.isEmpty(this.mAdvertImg);
    }

    public boolean isItemListEmpty() {
        mLsItemListLock.readLock().lock();
        try {
            return isListEmpty(this.mLsItems);
        } finally {
            mLsItemListLock.readLock().unlock();
        }
    }

    public boolean isListEmpty(List<T> list) {
        return list == null || list.size() == 0;
    }

    public void resetItemList(List<T> list) {
        mLsItemListLock.writeLock().lock();
        try {
            this.mLsItems.clear();
            if (list != null) {
                this.mLsItems.addAll(list);
            }
        } finally {
            mLsItemListLock.writeLock().unlock();
        }
    }

    public void resetItemListFromTmp() {
        mLsItemTmpListLock.readLock().lock();
        try {
            resetItemList(this.mLsItemTmp);
        } finally {
            mLsItemTmpListLock.readLock().unlock();
        }
    }

    public void resetItemTmpList(List<T> list) {
        mLsItemTmpListLock.writeLock().lock();
        try {
            this.mLsItemTmp.clear();
            if (list != null) {
                this.mLsItemTmp.addAll(list);
            }
        } finally {
            mLsItemTmpListLock.writeLock().unlock();
        }
    }

    public void setAdvertImg(String str) {
        if (this.mAdvertJump != null && !TextUtils.isEmpty(str) && !DYConstants.DY_NULL_STR.equalsIgnoreCase(this.mAdvertImg)) {
            this.mAdvertImg = str;
            this.mIsHaveAdvert = true;
            return;
        }
        this.mAdvertImg = "";
        this.mIsHaveAdvert = false;
    }

    public void setAdvertJump(String str) {
        if (TextUtils.isEmpty(str)) {
            this.mAdvertJump = null;
            return;
        }
        try {
            this.mAdvertJump = (JumpEntity) JDJSON.parseObject(str, JumpEntity.class);
        } catch (Exception e2) {
            com.jingdong.app.mall.home.o.a.f.s0(this, e2);
        }
    }

    public void setContentWidth(int i2) {
        if (i2 <= d.d(160)) {
            i2 = d.d(160);
        }
        this.mContentWidth = i2;
    }

    public void setMaiDianData(String str, String str2) {
        this.mMaiDianData.setData(str, str2);
    }

    public void setShowNameImg(String str) {
        this.showNameImg = str;
    }
}
