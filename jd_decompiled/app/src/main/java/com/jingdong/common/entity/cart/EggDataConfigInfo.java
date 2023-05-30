package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class EggDataConfigInfo {
    private int end;
    private boolean isExpire;
    private int num;
    private int start;

    public static ArrayList<EggDataConfigInfo> parseArray(JDJSONArray jDJSONArray) {
        if (jDJSONArray == null || jDJSONArray.isEmpty()) {
            return null;
        }
        ArrayList<EggDataConfigInfo> arrayList = new ArrayList<>(jDJSONArray.size());
        for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
            EggDataConfigInfo parseObj = parseObj(jDJSONArray.optJSONObject(i2));
            if (parseObj != null) {
                arrayList.add(parseObj);
            }
        }
        return arrayList;
    }

    public static EggDataConfigInfo parseObj(JDJSONObject jDJSONObject) {
        if (jDJSONObject != null) {
            EggDataConfigInfo eggDataConfigInfo = new EggDataConfigInfo();
            eggDataConfigInfo.setStart(jDJSONObject.optInt("start"));
            eggDataConfigInfo.setEnd(jDJSONObject.optInt("end"));
            eggDataConfigInfo.setNum(jDJSONObject.optInt("num"));
            eggDataConfigInfo.setExpire(false);
            return eggDataConfigInfo;
        }
        return null;
    }

    public int getEnd() {
        return this.end;
    }

    public int getNum() {
        return this.num;
    }

    public int getStart() {
        return this.start;
    }

    public boolean isExpire() {
        return this.isExpire;
    }

    public void setEnd(int i2) {
        this.end = i2;
    }

    public void setExpire(boolean z) {
        this.isExpire = z;
    }

    public void setNum(int i2) {
        this.num = i2;
    }

    public void setStart(int i2) {
        this.start = i2;
    }
}
