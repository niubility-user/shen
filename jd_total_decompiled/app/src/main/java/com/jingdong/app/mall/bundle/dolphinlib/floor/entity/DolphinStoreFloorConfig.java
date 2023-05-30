package com.jingdong.app.mall.bundle.dolphinlib.floor.entity;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes19.dex */
public class DolphinStoreFloorConfig extends BaseFloorConfig {
    public List<String> bgImages = new ArrayList();
    private int index;
    public String storeBgImageA;
    public String storeBgImageB;
    public String storeBgImageC;
    public String tagTextColor;
    public String titleTextColor;

    public void addBgImages() {
        if (!TextUtils.isEmpty(this.storeBgImageA)) {
            this.bgImages.add(this.storeBgImageA);
        }
        if (!TextUtils.isEmpty(this.storeBgImageB)) {
            this.bgImages.add(this.storeBgImageB);
        }
        if (TextUtils.isEmpty(this.storeBgImageC)) {
            return;
        }
        this.bgImages.add(this.storeBgImageC);
    }

    public String getBgImage() {
        if (this.bgImages.size() > 0) {
            if (this.index >= this.bgImages.size()) {
                this.index = 0;
            }
            String str = this.bgImages.get(this.index);
            this.index++;
            return str;
        }
        return "";
    }
}
