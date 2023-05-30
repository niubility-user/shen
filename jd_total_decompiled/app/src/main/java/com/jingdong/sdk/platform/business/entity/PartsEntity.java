package com.jingdong.sdk.platform.business.entity;

import android.text.TextUtils;
import com.jingdong.sdk.platform.base.UnProguard;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class PartsEntity extends UnProguard {
    public String bgc;
    public String buried;
    public boolean darkModel;
    public List<PartsItemEntity> imgInfo;
    public int jumpType;
    public String jumpUrl;
    public String tailIcon;
    public int tailIconH;
    public boolean tenthRevision;
    public String text1;
    public String text1C;
    public int text1S;
    public String text2;
    public String text2C;
    public int text2S;
    public String text3Bgc;
    public String text3C;
    public int text3S;
    public String type;
    public boolean text1B = true;
    public boolean viewMore = true;

    public List<PartsItemEntity> dealData() {
        List<PartsItemEntity> list = this.imgInfo;
        if (list == null || list.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (PartsItemEntity partsItemEntity : this.imgInfo) {
            if (!TextUtils.isEmpty(partsItemEntity.imgUrl)) {
                arrayList.add(partsItemEntity);
            }
        }
        return arrayList;
    }

    public boolean isEffective() {
        return (TextUtils.isEmpty(this.text1) || dealData() == null || dealData().size() < 3) ? false : true;
    }
}
