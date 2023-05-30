package com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness;

import android.text.TextUtils;
import com.jingdong.corelib.utils.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import jpbury.t;

/* loaded from: classes3.dex */
public class WareBusinessServiceIconInfoEntity {
    public WareBusinessServiceIconNormalEntity basic;
    public WareBusinessServiceIconEntity littleSkin;
    public List<WareBusinessServiceIconEntity> mFinalIconList;
    public List<WareBusinessServiceIconEntity> mNormalIconList;
    public WareBusinessSelectInfo selectInfo;
    public WareBusinessServiceIconTrustEntity trustworthy;
    public String titleImage = "";
    public String titleText = "";
    public int titlePosition = -1;

    private void sort(List<WareBusinessServiceIconEntity> list) {
        Collections.sort(list, new Comparator<WareBusinessServiceIconEntity>() { // from class: com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessServiceIconInfoEntity.1
            @Override // java.util.Comparator
            public int compare(WareBusinessServiceIconEntity wareBusinessServiceIconEntity, WareBusinessServiceIconEntity wareBusinessServiceIconEntity2) {
                int i2 = wareBusinessServiceIconEntity.sortId;
                int i3 = wareBusinessServiceIconEntity2.sortId;
                if (i2 > i3) {
                    return 1;
                }
                return i2 < i3 ? -1 : 0;
            }
        });
    }

    public void dealData(List<WareBusinessServiceIconEntity> list) {
        List<WareBusinessServiceIconEntity> list2;
        this.titleImage = "";
        this.titleText = "";
        this.titlePosition = -1;
        ArrayList arrayList = new ArrayList();
        if (list != null && !list.isEmpty()) {
            arrayList.addAll(list);
        }
        WareBusinessServiceIconNormalEntity wareBusinessServiceIconNormalEntity = this.basic;
        if (wareBusinessServiceIconNormalEntity != null && (list2 = wareBusinessServiceIconNormalEntity.iconList) != null && !list2.isEmpty()) {
            arrayList.addAll(this.basic.iconList);
        }
        if (!arrayList.isEmpty()) {
            try {
                sort(arrayList);
            } catch (Exception e2) {
                if (Log.D) {
                    Log.d(t.f20145j, e2.getMessage());
                }
            }
        }
        this.mNormalIconList = arrayList;
        mergerAllData(arrayList);
    }

    public boolean hasTrustworthy() {
        List<WareBusinessServiceIconEntity> list;
        WareBusinessServiceIconTrustEntity wareBusinessServiceIconTrustEntity = this.trustworthy;
        return (wareBusinessServiceIconTrustEntity == null || (list = wareBusinessServiceIconTrustEntity.iconList) == null || list.isEmpty()) ? false : true;
    }

    public void mergerAllData(List<WareBusinessServiceIconEntity> list) {
        List<WareBusinessServiceIconEntity> list2;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (this.trustworthy != null) {
            WareBusinessServiceIconEntity wareBusinessServiceIconEntity = new WareBusinessServiceIconEntity();
            if (!TextUtils.isEmpty(this.trustworthy.iconUrl)) {
                wareBusinessServiceIconEntity.imageUrl = this.trustworthy.iconUrl;
            } else {
                WareBusinessServiceIconNormalEntity wareBusinessServiceIconNormalEntity = this.basic;
                if (wareBusinessServiceIconNormalEntity != null && !TextUtils.isEmpty(wareBusinessServiceIconNormalEntity.title)) {
                    wareBusinessServiceIconEntity.text = this.basic.title;
                }
            }
            wareBusinessServiceIconEntity.isTrustItem = true;
            WareBusinessServiceIconTrustEntity wareBusinessServiceIconTrustEntity = this.trustworthy;
            wareBusinessServiceIconEntity.iconInDialog = wareBusinessServiceIconTrustEntity.iconInDialog;
            wareBusinessServiceIconEntity.jumpUrl = wareBusinessServiceIconTrustEntity.guideJumpUrl;
            wareBusinessServiceIconEntity.guideText = wareBusinessServiceIconTrustEntity.guideText;
            wareBusinessServiceIconEntity.currentType = "serviceTrust";
            wareBusinessServiceIconEntity.serviceType = 2;
            arrayList.add(wareBusinessServiceIconEntity);
            this.titleImage = this.trustworthy.iconUrl;
        }
        WareBusinessServiceIconTrustEntity wareBusinessServiceIconTrustEntity2 = this.trustworthy;
        if (wareBusinessServiceIconTrustEntity2 != null && (list2 = wareBusinessServiceIconTrustEntity2.iconList) != null && !list2.isEmpty()) {
            for (WareBusinessServiceIconEntity wareBusinessServiceIconEntity2 : this.trustworthy.iconList) {
                if (wareBusinessServiceIconEntity2 != null && wareBusinessServiceIconEntity2.jichu) {
                    arrayList2.add(wareBusinessServiceIconEntity2);
                } else if (wareBusinessServiceIconEntity2 != null) {
                    wareBusinessServiceIconEntity2.isTrustItem = true;
                    wareBusinessServiceIconEntity2.currentType = "serviceTrust";
                    arrayList.add(wareBusinessServiceIconEntity2);
                }
            }
        }
        WareBusinessServiceIconEntity wareBusinessServiceIconEntity3 = this.littleSkin;
        if (wareBusinessServiceIconEntity3 != null) {
            wareBusinessServiceIconEntity3.serviceType = 3;
            WareBusinessServiceIconTrustEntity wareBusinessServiceIconTrustEntity3 = this.trustworthy;
            if (wareBusinessServiceIconTrustEntity3 != null && !TextUtils.isEmpty(wareBusinessServiceIconTrustEntity3.guideText)) {
                this.littleSkin.guideText = this.trustworthy.guideText;
            }
            arrayList.add(this.littleSkin);
        }
        if (!list.isEmpty()) {
            WareBusinessServiceIconNormalEntity wareBusinessServiceIconNormalEntity2 = this.basic;
            if (wareBusinessServiceIconNormalEntity2 != null) {
                this.titleText = wareBusinessServiceIconNormalEntity2.title;
            }
            WareBusinessServiceIconEntity wareBusinessServiceIconEntity4 = new WareBusinessServiceIconEntity();
            WareBusinessServiceIconNormalEntity wareBusinessServiceIconNormalEntity3 = this.basic;
            if (wareBusinessServiceIconNormalEntity3 != null && !TextUtils.isEmpty(wareBusinessServiceIconNormalEntity3.title)) {
                wareBusinessServiceIconEntity4.text = this.basic.title;
            }
            wareBusinessServiceIconEntity4.serviceType = 1;
            arrayList.add(wareBusinessServiceIconEntity4);
            this.titlePosition = arrayList.size() - 1;
            if (arrayList2.size() > 0) {
                arrayList.addAll(arrayList2);
            }
            arrayList.addAll(list);
        }
        this.mFinalIconList = arrayList;
    }

    public void mergerSelectData() {
        ArrayList arrayList = new ArrayList();
        if (this.trustworthy != null) {
            WareBusinessServiceIconEntity wareBusinessServiceIconEntity = new WareBusinessServiceIconEntity();
            if (!TextUtils.isEmpty(this.trustworthy.iconUrl)) {
                wareBusinessServiceIconEntity.imageUrl = this.trustworthy.iconUrl;
            }
            wareBusinessServiceIconEntity.isTrustItem = true;
            WareBusinessServiceIconTrustEntity wareBusinessServiceIconTrustEntity = this.trustworthy;
            wareBusinessServiceIconEntity.jumpUrl = wareBusinessServiceIconTrustEntity.guideJumpUrl;
            wareBusinessServiceIconEntity.guideText = wareBusinessServiceIconTrustEntity.guideText;
            wareBusinessServiceIconEntity.currentType = "serviceTrust";
            wareBusinessServiceIconEntity.serviceType = 2;
            wareBusinessServiceIconEntity.iconInDialog = wareBusinessServiceIconTrustEntity.iconInDialog;
            arrayList.add(wareBusinessServiceIconEntity);
            Iterator<WareBusinessServiceIconEntity> it = this.trustworthy.iconList.iterator();
            while (it.hasNext()) {
                it.next().isTrustItem = true;
            }
            arrayList.addAll(this.trustworthy.iconList);
        }
        WareBusinessServiceIconEntity wareBusinessServiceIconEntity2 = this.littleSkin;
        if (wareBusinessServiceIconEntity2 != null) {
            wareBusinessServiceIconEntity2.serviceType = 3;
            WareBusinessServiceIconTrustEntity wareBusinessServiceIconTrustEntity2 = this.trustworthy;
            if (wareBusinessServiceIconTrustEntity2 != null && !TextUtils.isEmpty(wareBusinessServiceIconTrustEntity2.guideText)) {
                this.littleSkin.guideText = this.trustworthy.guideText;
            }
            arrayList.add(this.littleSkin);
        }
        this.mFinalIconList = arrayList;
    }
}
