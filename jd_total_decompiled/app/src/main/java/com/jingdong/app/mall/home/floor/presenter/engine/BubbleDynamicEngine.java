package com.jingdong.app.mall.home.floor.presenter.engine;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.model.entity.BubbleDynamicEntity;
import com.jingdong.app.mall.home.r.e.b;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.h;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class BubbleDynamicEngine extends FloorEngine<BubbleDynamicEntity> {
    private void i(d dVar, BubbleDynamicEntity bubbleDynamicEntity) {
        ArrayList<f> arrayList = dVar.f10682c;
        if (arrayList != null && arrayList.size() >= 3) {
            bubbleDynamicEntity.leftElement = arrayList.get(0);
            bubbleDynamicEntity.midElement = arrayList.get(1);
            bubbleDynamicEntity.rightElement = arrayList.get(2);
            return;
        }
        bubbleDynamicEntity.resetData();
    }

    private int[] j(String[] strArr) {
        if (strArr != null) {
            try {
                if (strArr.length > 0) {
                    int[] iArr = new int[strArr.length];
                    for (int i2 = 0; i2 < strArr.length; i2++) {
                        iArr[i2] = (int) Float.parseFloat(strArr[i2]);
                    }
                    return iArr;
                }
                return null;
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return null;
    }

    private void k(d dVar, BubbleDynamicEntity bubbleDynamicEntity) {
        bubbleDynamicEntity.floorHeight = dVar.getJsonInt("floorHeight");
        JDJSONObject jsonObject = dVar.getJsonObject("dataSizeInfo");
        if (jsonObject == null) {
            bubbleDynamicEntity.resetSizeParams();
            return;
        }
        bubbleDynamicEntity.middleWidth = b.getJsonInt(jsonObject, "bgWidth", 0);
        bubbleDynamicEntity.lrSkuRadius = b.getJsonInt(jsonObject, "lrSkuRadius", 20);
        bubbleDynamicEntity.midSkuRadius = b.getJsonInt(jsonObject, "midSkuRadius", 18);
        String jsonString = b.getJsonString(jsonObject, "skuInfo1", "");
        String jsonString2 = b.getJsonString(jsonObject, "textInfo1", "");
        String jsonString3 = b.getJsonString(jsonObject, "skuInfo2", "");
        String jsonString4 = b.getJsonString(jsonObject, "textInfo2", "");
        String jsonString5 = b.getJsonString(jsonObject, "textInfo3", "");
        String jsonString6 = b.getJsonString(jsonObject, "skuInfo4", "");
        String jsonString7 = b.getJsonString(jsonObject, "textInfo4", "");
        if (TextUtils.isEmpty(jsonString6)) {
            jsonString6 = jsonString;
        }
        if (TextUtils.isEmpty(jsonString7)) {
            jsonString7 = jsonString2;
        }
        try {
            bubbleDynamicEntity.lSkuSize = j(jsonString.split("[x,]"));
            bubbleDynamicEntity.lTextSize = j(jsonString2.split(DYConstants.DY_REGEX_COMMA));
            bubbleDynamicEntity.midSkuSize = j(jsonString3.split("[x,]"));
            bubbleDynamicEntity.midTopTextSize = j(jsonString4.split(DYConstants.DY_REGEX_COMMA));
            bubbleDynamicEntity.midBottomTextSize = j(jsonString5.split(DYConstants.DY_REGEX_COMMA));
            bubbleDynamicEntity.rSkuSize = j(jsonString6.split("[x,]"));
            bubbleDynamicEntity.rTextSize = j(jsonString7.split(DYConstants.DY_REGEX_COMMA));
        } catch (Exception e2) {
            bubbleDynamicEntity.resetSizeParams();
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public void e(h hVar, d dVar, BubbleDynamicEntity bubbleDynamicEntity) {
        super.e(hVar, dVar, bubbleDynamicEntity);
        if (hVar == null || dVar == null || bubbleDynamicEntity == null) {
            return;
        }
        k(dVar, bubbleDynamicEntity);
        i(dVar, bubbleDynamicEntity);
        bubbleDynamicEntity.parseData(hVar);
    }
}
