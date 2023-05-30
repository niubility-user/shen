package com.jingdong.app.mall.home.floor.model.entity;

import android.text.TextUtils;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.g;
import com.jingdong.app.mall.home.r.e.k.a;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes4.dex */
public class BubbleBannerEntity extends FloorEntity {
    public static int TYPE_08005 = 0;
    public static int TYPE_08008 = 1;
    public static int TYPE_08008_V936 = 3;
    public static int TYPE_08009 = 2;
    public static int TYPE_09009 = 4;
    private ArrayList<f> mSmallItemList = new ArrayList<>();
    private int mUiType;
    private f middle;
    private a middleItem;

    private void addExpoJson(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mJsonExposData.add(b.c(str));
    }

    public static boolean isNewUI(g gVar) {
        return gVar != null && 1 == gVar.f10689k;
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public ArrayList<String> getExpoData() {
        if (isValid()) {
            this.mExposData.clear();
            this.mExposData.add(this.middle.j());
            Iterator<f> it = this.mSmallItemList.iterator();
            while (it.hasNext()) {
                f next = it.next();
                if (next != null) {
                    this.mExposData.add(next.j());
                }
            }
            return this.mExposData;
        }
        return super.getExpoData();
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public ArrayList<b> getExpoJson() {
        return this.mJsonExposData;
    }

    public f getMiddle() {
        return this.middle;
    }

    public a getMiddleItem() {
        return this.middleItem;
    }

    public ArrayList<f> getSmallList() {
        return this.mSmallItemList;
    }

    public int getUiType() {
        return this.mUiType;
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public boolean isValid() {
        return (this.mSmallItemList.size() == 0 || this.middle == null) ? false : true;
    }

    public void parseItem(d dVar, ArrayList<f> arrayList, int i2) {
        int size = arrayList.size();
        String str = dVar.a;
        str.hashCode();
        if (str.equals("08005")) {
            this.mUiType = TYPE_08005;
        } else if (!str.equals("08008")) {
            this.mUiType = TYPE_08009;
        } else if (dVar.f10689k == 1) {
            this.mUiType = TYPE_08008_V936;
        } else {
            this.mUiType = TYPE_08008;
        }
        for (int i3 = 0; i3 < size; i3++) {
            f fVar = arrayList.get(i3);
            if (fVar == null) {
                return;
            }
            if (i3 == (size - 1) / 2) {
                this.middle = fVar;
                addExpoJson(fVar.l());
                this.middleItem = new a(fVar, i2, this.mUiType);
            } else {
                this.mSmallItemList.add(fVar);
                addExpoJson(fVar.l());
            }
        }
    }
}
