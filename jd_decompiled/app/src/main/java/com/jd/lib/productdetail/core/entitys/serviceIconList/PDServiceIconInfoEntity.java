package com.jd.lib.productdetail.core.entitys.serviceIconList;

import android.text.TextUtils;
import com.jingdong.corelib.utils.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import jpbury.t;

/* loaded from: classes15.dex */
public class PDServiceIconInfoEntity {
    public PDServiceIconNormalEntity basic;
    public List<PDServiceIconEntity> mFinalIconList;
    public List<PDServiceIconEntity> mNormalIconList;
    public PDServiceIconTrustEntity trustworthy;
    public String titleImage = "";
    public String titleText = "";
    public int titlePosition = -1;

    public void dealData(List<PDServiceIconEntity> list) {
        List<PDServiceIconEntity> list2;
        this.titleImage = "";
        this.titleText = "";
        this.titlePosition = -1;
        ArrayList arrayList = new ArrayList();
        if (list != null && !list.isEmpty()) {
            arrayList.addAll(list);
        }
        PDServiceIconNormalEntity pDServiceIconNormalEntity = this.basic;
        if (pDServiceIconNormalEntity != null && (list2 = pDServiceIconNormalEntity.iconList) != null && !list2.isEmpty()) {
            arrayList.addAll(this.basic.iconList);
        }
        if (!arrayList.isEmpty()) {
            try {
                Collections.sort(arrayList, new Comparator<PDServiceIconEntity>() { // from class: com.jd.lib.productdetail.core.entitys.serviceIconList.PDServiceIconInfoEntity.1
                    @Override // java.util.Comparator
                    public int compare(PDServiceIconEntity pDServiceIconEntity, PDServiceIconEntity pDServiceIconEntity2) {
                        int i2 = pDServiceIconEntity.sortId;
                        int i3 = pDServiceIconEntity2.sortId;
                        if (i2 > i3) {
                            return 1;
                        }
                        return i2 < i3 ? -1 : 0;
                    }
                });
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
        List<PDServiceIconEntity> list;
        PDServiceIconTrustEntity pDServiceIconTrustEntity = this.trustworthy;
        return (pDServiceIconTrustEntity == null || (list = pDServiceIconTrustEntity.iconList) == null || list.isEmpty()) ? false : true;
    }

    public void mergerAllData(List<PDServiceIconEntity> list) {
        List<PDServiceIconEntity> list2;
        ArrayList arrayList = new ArrayList();
        PDServiceIconTrustEntity pDServiceIconTrustEntity = this.trustworthy;
        if (pDServiceIconTrustEntity != null && (list2 = pDServiceIconTrustEntity.iconList) != null && !list2.isEmpty()) {
            arrayList.addAll(this.trustworthy.iconList);
            this.titleImage = this.trustworthy.iconUrl;
        }
        if (!list.isEmpty()) {
            PDServiceIconNormalEntity pDServiceIconNormalEntity = this.basic;
            if (pDServiceIconNormalEntity != null) {
                this.titleText = pDServiceIconNormalEntity.title;
            }
            if (!TextUtils.isEmpty(this.titleImage)) {
                PDServiceIconEntity pDServiceIconEntity = new PDServiceIconEntity();
                PDServiceIconNormalEntity pDServiceIconNormalEntity2 = this.basic;
                if (pDServiceIconNormalEntity2 != null && !TextUtils.isEmpty(pDServiceIconNormalEntity2.title)) {
                    pDServiceIconEntity.text = this.basic.title;
                }
                pDServiceIconEntity.serviceType = 1;
                arrayList.add(pDServiceIconEntity);
                this.titlePosition = arrayList.size() - 1;
            }
            arrayList.addAll(list);
        }
        this.mFinalIconList = arrayList;
    }
}
